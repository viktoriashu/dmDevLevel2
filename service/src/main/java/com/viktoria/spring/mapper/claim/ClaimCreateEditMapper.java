package com.viktoria.spring.mapper.claim;

import com.viktoria.spring.database.entity.Claim;
import com.viktoria.spring.database.entity.ExtrasClaim;
import com.viktoria.spring.database.entity.Sup;
import com.viktoria.spring.database.entity.User;
import com.viktoria.spring.database.repository.ExtrasClaimRepository;
import com.viktoria.spring.database.repository.SupRepository;
import com.viktoria.spring.database.repository.UserRepository;
import com.viktoria.spring.dto.claim.ClaimCreateEditDto;
import com.viktoria.spring.mapper.Mapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClaimCreateEditMapper implements Mapper<ClaimCreateEditDto, Claim> {

    private final UserRepository userRepository;
    private final SupRepository supRepository;
    private final ExtrasClaimRepository extrasClaimRepository;


    @Override
    public Claim map(ClaimCreateEditDto fromObject, Claim toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Claim map(ClaimCreateEditDto object) {
        Claim claim = new Claim();
        copy(object, claim);
        return claim;
    }

    private void copy(ClaimCreateEditDto object, Claim claim) {
        claim.setClient(getUser(object.getClientId()));
        claim.setAdmin(getUser(object.getAdminId()));
        claim.setSup(getSup(object.getSupId()));
        claim.setDataStartRent(object.getDataStartRent());
        claim.setDurationRent(object.getDurationRent());
        claim.setStatus(object.getStatus());
        claim.setPrice(object.getPrice());
    }


    private User getUser(Long userId) {
        return Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElse(null);
    }

    private Sup getSup(Long supId) {
        return Optional.ofNullable(supId)
                .flatMap(supRepository::findById)
                .orElse(null);
    }



}
