package com.viktoria.spring.service;

import com.viktoria.spring.database.entity.Extras;
import com.viktoria.spring.database.entity.QExtras;
import com.viktoria.spring.database.repository.ExtrasRepository;
import com.viktoria.spring.database.repository.QPredicate;
import com.viktoria.spring.dto.extras.ExtrasCreateEditDto;
import com.viktoria.spring.dto.extras.ExtrasFilter;
import com.viktoria.spring.dto.extras.ExtrasReadDto;
import com.viktoria.spring.mapper.extras.ExtrasCreateEditMapper;
import com.viktoria.spring.mapper.extras.ExtrasReadMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExtrasService {

    private final ExtrasRepository extrasRepository;
    private final ExtrasReadMapper extrasReadMapper;
    private final ExtrasCreateEditMapper extrasCreateEditMapper;
    private final ImageService imageService;

    public Page<ExtrasReadDto> findAll(ExtrasFilter filter, Pageable pageable) {
        var predicate = QPredicate.builder()
                .add(filter.getName(), QExtras.extras.name::containsIgnoreCase)
                .buildAnd();

        return extrasRepository.findAll(predicate, pageable)
                .map(extrasReadMapper::map);
    }

    public Optional<ExtrasReadDto> findById(Long id) {
        return extrasRepository.findById(id)
                .map(extrasReadMapper::map);
    }

    public Optional<byte[]> findAvatar(Long id) {
        return extrasRepository.findById(id)
                .map(Extras::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    @Transactional
    public ExtrasReadDto create(ExtrasCreateEditDto extrasDto) {
        return Optional.of(extrasDto)
                .map(dto -> {
                    uploadImage(dto.getImage());
                    return extrasCreateEditMapper.map(dto);
                })
                .map(extrasRepository::save)
                .map(extrasReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ExtrasReadDto> update(Long id, ExtrasCreateEditDto extrasDto) {
        return extrasRepository.findById(id)
                .map(entity -> {
                    uploadImage(extrasDto.getImage());
                    return extrasCreateEditMapper.map(extrasDto, entity);
                })
                .map(extrasRepository::saveAndFlush)
                .map(extrasReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return extrasRepository.findById(id)
                .map(entity -> {
                    extrasRepository.delete(entity);
                    extrasRepository.flush();
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
