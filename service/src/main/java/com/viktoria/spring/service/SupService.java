package com.viktoria.spring.service;

import com.viktoria.spring.database.entity.Sup;
import com.viktoria.spring.database.repository.QPredicate;
import com.viktoria.spring.database.repository.SupRepository;
import com.viktoria.spring.dto.sup.SupCreateEditDto;
import com.viktoria.spring.dto.sup.SupFilter;
import com.viktoria.spring.dto.sup.SupReadDto;
import com.viktoria.spring.mapper.sup.SupCreateEditMapper;
import com.viktoria.spring.mapper.sup.SupReadMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import static com.viktoria.spring.database.entity.QSup.sup;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SupService {

    private final SupRepository supRepository;
    private final SupReadMapper supReadMapper;
    private final SupCreateEditMapper supCreateEditMapper;
    private final ImageService imageService;

    public Page<SupReadDto> findAll(SupFilter filter, Pageable pageable) {
        var predicate = QPredicate.builder()
                .add(filter.getModel(), sup.model::containsIgnoreCase)
                .add(filter.getNumberSeats(), sup.numberSeats::eq)
                .buildAnd();

        return supRepository.findAll(predicate, pageable)
                .map(supReadMapper::map);
    }

    public Optional<SupReadDto> findById(Long id) {
        return supRepository.findById(id)
                .map(supReadMapper::map);
    }

    public Optional<byte[]> findAvatar(Long id) {
        return supRepository.findById(id)
                .map(Sup::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    @Transactional
    public SupReadDto create(SupCreateEditDto supDto) {
        return Optional.of(supDto)
                .map(dto -> {
                    uploadImage(dto.getImage());
                    return supCreateEditMapper.map(dto);
                })
                .map(supRepository::save)
                .map(supReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<SupReadDto> update(Long id, SupCreateEditDto supDto) {
        return supRepository.findById(id)
                .map(entity -> {
                    uploadImage(supDto.getImage());
                    return supCreateEditMapper.map(supDto, entity);
                })
                .map(supRepository::saveAndFlush)
                .map(supReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return supRepository.findById(id)
                .map(entity -> {
                    supRepository.delete(entity);
                    supRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }
}
