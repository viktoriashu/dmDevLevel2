package com.viktoria.spring.http.rest;

import com.viktoria.spring.dto.PageResponse;
import com.viktoria.spring.dto.sup.SupCreateEditDto;
import com.viktoria.spring.dto.sup.SupFilter;
import com.viktoria.spring.dto.sup.SupReadDto;
import com.viktoria.spring.service.SupService;
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
@RequestMapping("/api/v1/sups")
@RequiredArgsConstructor
public class SupRestController {

    private final SupService supService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<SupReadDto> findAll(SupFilter filter, Pageable pageable) {
        Page<SupReadDto> page = supService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @GetMapping("/{id}")
    public SupReadDto findById(@PathVariable Long id) {
        return supService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/avatar")
    public ResponseEntity<byte[]> findAvatar(@PathVariable("id") Long id) {
        return supService.findAvatar(id)
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(notFound()::build);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SupReadDto create(@Validated @RequestBody SupCreateEditDto sup) {
        return supService.create(sup);
    }

    @PutMapping("/{id}")
    public SupReadDto update(@PathVariable("id") Long id, @Validated @RequestBody SupCreateEditDto sup) {
        return supService.update(id, sup)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return supService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
