package com.viktoria.spring.service;

import com.viktoria.spring.database.entity.QClaim;
import com.viktoria.spring.database.repository.ClaimRepository;
import com.viktoria.spring.database.repository.QPredicate;
import com.viktoria.spring.dto.claim.ClaimCreateEditDto;
import com.viktoria.spring.dto.claim.ClaimFilter;
import com.viktoria.spring.dto.claim.ClaimReadDto;
import com.viktoria.spring.mapper.claim.ClaimCreateEditMapper;
import com.viktoria.spring.mapper.claim.ClaimReadMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClaimService{

    private final ClaimRepository claimRepository;
    private final ClaimReadMapper claimReadMapper;
    private final ClaimCreateEditMapper claimCreateEditMapper;

    public Page<ClaimReadDto> findAll(ClaimFilter filter, Pageable pageable) {
        var predicate = QPredicate.builder()
                .add(filter.getId(), QClaim.claim.id::eq)
                .buildAnd();

        return claimRepository.findAll(predicate, pageable)
                .map(claimReadMapper::map);
    }

    public Optional<ClaimReadDto> findById(Long id) {
        return claimRepository.findById(id)
                .map(claimReadMapper::map);
    }

    @Transactional
    public ClaimReadDto create(ClaimCreateEditDto claimDto) {
        return Optional.of(claimDto)
                .map(claimCreateEditMapper::map)
                .map(claimRepository::save)
                .map(claimReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ClaimReadDto> update(Long id, ClaimCreateEditDto claimDto) {
        return claimRepository.findById(id)
                .map(entity -> claimCreateEditMapper.map(claimDto, entity))
                .map(claimRepository::saveAndFlush)
                .map(claimReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return claimRepository.findById(id)
                .map(entity->{
                    claimRepository.delete(entity);
                    claimRepository.flush();
                    return true;
                })
                .orElse(false);
    }



}
