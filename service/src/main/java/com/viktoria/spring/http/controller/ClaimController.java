package com.viktoria.spring.http.controller;

import com.viktoria.spring.database.entity.Status;
import com.viktoria.spring.dto.PageResponse;
import com.viktoria.spring.dto.claim.ClaimCreateEditDto;
import com.viktoria.spring.dto.claim.ClaimFilter;
import com.viktoria.spring.dto.claim.ClaimReadDto;
import com.viktoria.spring.service.ClaimService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @GetMapping
    public String findAll(Model model, ClaimFilter filter, Pageable pageable) {
        Page<ClaimReadDto> page = claimService.findAll(filter, pageable);
        model.addAttribute("claims", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "claim/claims";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return claimService.findById(id)
                .map(claim -> {
                    model.addAttribute("claim", claim);
                    return "claim/claim";
                })
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("claim") ClaimCreateEditDto claim) {
        model.addAttribute("claim", claim);
        model.addAttribute("statuses", Status.values());
        return "claim/registration";
    }

    @PostMapping
    public String create(@ModelAttribute("claim") @Validated ClaimCreateEditDto claim,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("claim", claim);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/claims/registration";
        }
        claimService.create(claim);
        return "redirect:/claims/" + claimService.create(claim).getId();
    }


    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("claim") @Validated ClaimCreateEditDto claim,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("claim", claim);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/claims/{id}";
        }
        return claimService.update(id, claim)
                .map(it -> "redirect:/claims/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!claimService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/claims";
    }

}
