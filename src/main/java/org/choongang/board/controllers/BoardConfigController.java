package org.choongang.board.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.board.service.BoardConfigDeleteService;
import org.choongang.board.service.BoardConfigSaveService;
import org.choongang.commons.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/board/config")
@RequiredArgsConstructor
public class BoardConfigController {

    private final BoardConfigSaveService saveService;
    private final BoardConfigDeleteService deleteService;
    private final BoardConfigValidator validator;

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody RequestBoardConfig form, Errors errors) {
        validator.validate(form, errors);

        errorProcess(errors);

        saveService.save(form);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{bid}")
    public void delete(@PathVariable("bid") String bid) {
        deleteService.delete(bid);
    }

    private void errorProcess(Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors);
        }
    }
}
