package com.example.validation_ex.controller;

import com.example.validation_ex.dto.ErrorResult;
import com.example.validation_ex.dto.Item;
import com.example.validation_ex.exception.ItemValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@Slf4j
public class ItemController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult handleException(ItemValidationException ex) {
        return new ErrorResult(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @PostMapping
    public Object createItem(@Validated @ModelAttribute Item item, BindingResult bindingResult) {
        if (item.getCost() >= item.getPrice()) {
            bindingResult.reject("costAndPrice", "cost exceeds price");
            throw new ItemValidationException("COST EXCEEDS PRICE");
        }

        if (bindingResult.hasErrors()) {
            log.error("Validation errors: {}", bindingResult.getAllErrors());
            return bindingResult.getAllErrors();
        }
        log.info("Creating new item: {}", item);
        return item;
    }
}
