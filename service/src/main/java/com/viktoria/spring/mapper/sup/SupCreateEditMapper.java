package com.viktoria.spring.mapper.sup;

import com.viktoria.spring.database.entity.Sup;
import com.viktoria.spring.dto.sup.SupCreateEditDto;
import com.viktoria.spring.mapper.Mapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class SupCreateEditMapper implements Mapper<SupCreateEditDto, Sup> {

    @Override
    public Sup map(SupCreateEditDto fromObject, Sup toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Sup map(SupCreateEditDto object) {
        Sup sup = new Sup();
        copy(object, sup);
        return sup;
    }

    private void copy(SupCreateEditDto object, Sup sup) {
        sup.setModel(object.getModel());
        sup.setNumberSeats(object.getNumberSeats());
        sup.setDescription(object.getDescription());
        Optional.ofNullable(object.getImage())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> {
                    sup.setImage(image.getOriginalFilename());
                });
        sup.setPrice(object.getPrice());
    }
}
