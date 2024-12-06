package com.viktoria.spring.mapper.claim;

import com.viktoria.spring.database.entity.Claim;
import com.viktoria.spring.database.entity.Role;
import com.viktoria.spring.database.entity.Status;
import com.viktoria.spring.database.entity.User;
import com.viktoria.spring.database.repository.ExtrasClaimRepository;
import com.viktoria.spring.database.repository.SupRepository;
import com.viktoria.spring.database.repository.UserRepository;
import com.viktoria.spring.dto.claim.ClaimCreateEditDto;
import com.viktoria.spring.mapper.Mapper;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

        Optional<User> maybeClient = getAuthenticatedUser();
        if (maybeClient.isPresent() && maybeClient.get().getRole().equals(Role.USER)) {
            User user = maybeClient.get();
            claim.addClient(user);
        }

        Optional<User> maybeAdmin = getAuthenticatedUser();
        if (maybeAdmin.isPresent() && maybeAdmin.get().getRole().equals(Role.ADMIN)) {
            User user = maybeAdmin.get();
            claim.addAdmin(user);
        }
        //взять из секьюрети контекст холдера
//        claim.setClient(getUser(getAuthenticatedUser(object.)));
//        claim.setClient(getUser(object.getClientId()));
//        claim.setAdmin(getUser(object.getAdminId()));
//        claim.setSup(getSup(object.getSupId()));
        if (claim.getAdmin() == null) {
            claim.setAdmin(userRepository.findById(11L).get());
        }

        supRepository.findById(object.getSupId())
                .ifPresent(claim::setSup);
        claim.setDataStartRent(object.getDataStartRent());
        claim.setDurationRent(object.getDurationRent());
        if (claim.getStatus() == null) {
            claim.setStatus(Status.OPEN);
        } else claim.setStatus(object.getStatus());
//        claim.setStatus(object.getStatus());
//цену получаешь автоматически из сущности сапа
//        claim.setPrice(object.getPrice());

        claim.setPrice(supRepository.findById(object.getSupId()).get().getPrice()
                .multiply(BigDecimal.valueOf(object.getDurationRent())));
//        claim.setPrice(supRepository.findById(object.getSupId()).get().getPrice());
    }


//    private User getUser(Long userId) {
//        return Optional.ofNullable(userId)
//                .flatMap(userRepository::findById)
//                .orElse(null);
//    }

//    private Sup getSup(Long supId) {
//        return Optional.ofNullable(supId)
//                .flatMap(supRepository::findById)
//                .orElse(null);
//    }

//    private Optional<User> getAuthenticatedUser() {
//        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
//                .map(authentication -> (User)(UserDetails) authentication.getPrincipal())
//                .map(User::getId)
//                .flatMap(userRepository::findById);
//    }

    private Optional<User> getAuthenticatedUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(authentication -> (UserDetails) authentication.getPrincipal())
                .map(UserDetails::getUsername)
                .flatMap(userRepository::findByLogin);
    }


}
