package com.example.mixedapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoviesDTO {
    private List<MovieDto> movies;
}
