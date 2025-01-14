package com.example.mixedapp.controller;

import com.example.mixedapp.dto.MovieDto;
import com.example.mixedapp.dto.MoviesDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = RequestMethod.GET)
public class MainController {
    @GetMapping
    public MoviesDTO hello() {
        MoviesDTO movies = new MoviesDTO();
        MovieDto movieDto = new MovieDto(
                1,
                "testMovie",
                "https://yts.mx/assets/images/movies/the_pastor_2024/background.jpg",
                "summaryTest");
        List<MovieDto> moviesList = new ArrayList<>();
        moviesList.add(movieDto);
        movies.setMovies(moviesList);
        return movies;
    }
}