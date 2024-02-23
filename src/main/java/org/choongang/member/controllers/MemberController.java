package org.choongang.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.commons.exceptions.BadRequestException;
import org.choongang.member.service.MemberSaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberSaveService saveService;

    @PostMapping
    public ResponseEntity join(@Valid @RequestBody RequestJoin form, Errors errors) {

        if (errors.hasErrors()) {
            throw new BadRequestException(errors);
        }

        saveService.join(form);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
