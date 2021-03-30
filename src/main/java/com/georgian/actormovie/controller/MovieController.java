package com.georgian.actormovie.controller;

import com.georgian.actormovie.entity.Movie;
import com.georgian.actormovie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class MovieController {

  private final MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @PostMapping()
  public ResponseEntity<Object> createMovie(@RequestBody Movie movie){
    return movieService.addMovie(movie);
  }

  @DeleteMapping("/id/{id}")
  public ResponseEntity<Object> deleteMovie(@PathVariable(name = "id") Long id){
    return movieService.deleteMovie(id);
  }
}
