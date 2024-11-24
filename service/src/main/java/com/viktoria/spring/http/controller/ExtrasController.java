package com.viktoria.spring.http.controller;

import com.viktoria.spring.dto.PageResponse;
import com.viktoria.spring.dto.extras.ExtrasCreateEditDto;
import com.viktoria.spring.dto.extras.ExtrasFilter;
import com.viktoria.spring.dto.extras.ExtrasReadDto;
import com.viktoria.spring.service.ExtrasService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/extrasies")
@RequiredArgsConstructor
public class ExtrasController {

    private final ExtrasService extrasService;

    @GetMapping
    public String findAll(Model model,
                          ExtrasFilter filter,
                          @RequestParam(defaultValue = "1") @Min(1) int page,
                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ExtrasReadDto> ExtrasPage = extrasService.findAll(filter, pageable);
        model.addAttribute("extrasies", PageResponse.of(ExtrasPage));
        model.addAttribute("filter", filter);
        return "extras/extrasies";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        return extrasService.findById(id)
                .map(extras -> {
                    model.addAttribute("extras", extras);
                    return "extras/extras";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("extras") ExtrasCreateEditDto extras) {
        model.addAttribute("extras", extras);
        return "extras/registration";
    }

    @PostMapping
    public String create(@ModelAttribute("extras") @Validated ExtrasCreateEditDto extras,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("extras", extras);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/extrasies/registration";
        }
        return "redirect:/extrasies/" + extrasService.create(extras).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("extras") @Validated ExtrasCreateEditDto extras,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("extras", extras);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/extrasies/{id}";
        }
        return extrasService.update(id, extras)
                .map(it -> "redirect:/extrasies/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!extrasService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/extrasies";
    }

}
