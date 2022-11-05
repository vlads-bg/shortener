package com.shortener.challenge.infrastructure.api;

import com.shortener.challenge.infrastructure.shorturl.ShortURLRequest;
import com.shortener.challenge.infrastructure.shorturl.models.ShortUrlResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ShortUrlAPI {

    @PostMapping(
            value = "/shortener/{url}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new short url")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registered successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> shortener(@PathVariable(name = "url") String url, @RequestBody ShortURLRequest input);

    @GetMapping("/{code}")
    @Operation(summary = "Redirect the short URL")
    void redirect(@PathVariable(name = "code") String code, HttpServletRequest request, HttpServletResponse response);

    @GetMapping(value = "/status/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status recovered successfully"),
            @ApiResponse(responseCode = "404", description = "URL code was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> status(@PathVariable(name = "code") String code);
}
