package com.georgian.actormovie.controller;

import com.georgian.actormovie.entity.Movie;
import com.georgian.actormovie.repository.MovieRepository;
import com.georgian.actormovie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class MovieController {

  private final MovieService movieService;
  private final MovieRepository movieRepository;
  public MovieController(MovieService movieService,
      MovieRepository movieRepository) {
    this.movieService = movieService;
    this.movieRepository = movieRepository;
  }

  @PostMapping()
  public ResponseEntity<Object> createMovie(@RequestBody Movie movie){
    return movieService.addMovie(movie);
  }

  @DeleteMapping("/id/{id}")
  public ResponseEntity<Object> deleteMovie(@PathVariable(name = "id") Long id){
    return movieService.deleteMovie(id);
  }

  @GetMapping("/id/{id}")
  public Movie getMovie(@PathVariable Long id){
    return movieRepository.findById(id).get();
  }
}
