package com.viktoria.spring.http.rest;

import com.viktoria.spring.dto.PageResponse;
import com.viktoria.spring.dto.extras.ExtrasCreateEditDto;
import com.viktoria.spring.dto.extras.ExtrasFilter;
import com.viktoria.spring.dto.extras.ExtrasReadDto;
import com.viktoria.spring.service.ExtrasService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/extrasies")
@RequiredArgsConstructor
public class ExtrasRestController {

    private final ExtrasService extrasService;

//    @GetMapping()
//    public PageResponse<ExtrasReadDto> findAll(ExtrasFilter filter, Pageable pageable) {
//        Page<ExtrasReadDto> page = extrasService.findAll(filter, pageable);
//        return PageResponse.of(page);
//    }
//
//    @GetMapping("/{id}")
//    public ExtrasReadDto findById(@PathVariable Long id) {
//        return extrasService.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

    @GetMapping(value = "/{id}/avatar")
    public ResponseEntity<byte[]> findAvatar(@PathVariable("id") Long id) {
        return extrasService.findAvatar(id)
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(notFound()::build);
    }

//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public ExtrasReadDto create(@Validated @RequestBody ExtrasCreateEditDto extras) {
//        return extrasService.create(extras);
//    }
//
//    @PutMapping("/{id}")
//    public ExtrasReadDto update(@PathVariable("id") Long id, @Validated @RequestBody ExtrasCreateEditDto extras) {
//        return extrasService.update(id, extras)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
//        return extrasService.delete(id)
//                ? noContent().build()
//                : notFound().build();
//    }
}
