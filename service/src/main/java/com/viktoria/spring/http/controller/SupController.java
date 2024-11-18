package com.viktoria.spring.http.controller;

import com.viktoria.spring.dto.PageResponse;
import com.viktoria.spring.dto.sup.SupCreateEditDto;
import com.viktoria.spring.dto.sup.SupFilter;
import com.viktoria.spring.dto.sup.SupReadDto;
import com.viktoria.spring.service.SupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sups")
@RequiredArgsConstructor
public class SupController {

    private final SupService supService;

    @GetMapping
    public String findAll(Model model, SupFilter filter, Pageable pageable) {
        Page<SupReadDto> page = supService.findAll(filter, pageable);
        model.addAttribute("sups", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "sup/sups";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        return supService.findById(id)
                .map(sup -> {
                    model.addAttribute("sup", sup);
                    return "sup/sup";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("sup") SupCreateEditDto sup) {
        model.addAttribute("sup", sup);
        return "sup/registration";
    }

    @PostMapping
    public String create(@ModelAttribute("sup") @Validated SupCreateEditDto sup, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("sup", sup);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/sups/registration";
        }
        return "redirect:/sups/" + supService.create(sup).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("sup") @Validated SupCreateEditDto sup,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("sup", sup);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/sups/{id}";
        }
        return supService.update(id, sup)
                .map(it -> "redirect:/sups/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!supService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/sups";
    }
}
