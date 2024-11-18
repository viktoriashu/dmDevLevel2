package com.viktoria.spring.mapper.extras;

import com.viktoria.spring.database.entity.Extras;
import com.viktoria.spring.dto.extras.ExtrasCreateEditDto;
import com.viktoria.spring.mapper.Mapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class ExtrasCreateEditMapper implements Mapper<ExtrasCreateEditDto, Extras> {

    @Override
    public Extras map(ExtrasCreateEditDto fromObject, Extras toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Extras map(ExtrasCreateEditDto object) {
        Extras extras = new Extras();
        copy(object, extras);
        return extras;
    }

    private void copy(ExtrasCreateEditDto object, Extras extras) {
        extras.setName(object.getName());
        extras.setDescription(object.getDescription());
        Optional.ofNullable(object.getImage())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> {
                    extras.setImage(image.getOriginalFilename());
                });
        extras.setPrice(object.getPrice());
    }

}
