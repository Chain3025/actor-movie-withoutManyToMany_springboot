package com.georgian.actormovie.controller;

import com.georgian.actormovie.entity.Actor;
import com.georgian.actormovie.entity.Movie;
import com.georgian.actormovie.model.MovieRequest;
import com.georgian.actormovie.model.MovieResponse;
import com.georgian.actormovie.repository.ActorRepository;
import com.georgian.actormovie.repository.MovieActorRepository;
import com.georgian.actormovie.repository.MovieRepository;
import com.georgian.actormovie.service.MovieService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class MovieController {

  private final MovieService movieService;
  private final MovieRepository movieRepository;
  private final MovieActorRepository movieActorRepository;
  private final ActorRepository actorRepository;

  public MovieController(MovieService movieService,
      MovieRepository movieRepository,
      MovieActorRepository movieActorRepository,
      ActorRepository actorRepository) {
    this.movieService = movieService;
    this.movieRepository = movieRepository;
    this.movieActorRepository = movieActorRepository;
    this.actorRepository = actorRepository;
  }

  @PostMapping()
  public ResponseEntity<Object> createMovie(@RequestBody MovieRequest movieRequest){
    return movieService.addMovie(movieRequest);
  }

  @DeleteMapping("/id/{id}")
  public ResponseEntity<Object> deleteMovie(@PathVariable(name = "id") Long id){
    return movieService.deleteMovie(id);
  }
  @PutMapping()
  public ResponseEntity updateMovie(@RequestParam Long id,@RequestBody MovieRequest movie){
    return movieService.updateMovie(id,movie);

    //
  }

  @GetMapping("/id/{id}")
  public MovieResponse getMovie(@PathVariable Long id){
    MovieResponse movieResponse = new MovieResponse();
    Movie movie = movieRepository.findById(id).get();
    movieResponse.setMovieTitle(movie.getMovieTitle());
    movieResponse.setDirectorId(movie.getDirectorId());
    movieResponse.setId(movie.getId());
    List<Long> actorsId = movieActorRepository.actorIdByMovieId(movie.getId());
    List<Actor> actors=new ArrayList<>();
    for (Long actorId : actorsId) {

      Actor actor = actorRepository.findbyActorId(actorId);
      actors.add(actor);
    }
    movieResponse.setActors(actors);
    return movieResponse;
  }
  @GetMapping()
  public List<MovieResponse> getAllMovie(){
    List<MovieResponse> movieResponseList = new ArrayList<>();
    List<Movie> allMovie = movieRepository.findAll();
    for (Movie movie : allMovie) {
      MovieResponse movieResponse = new MovieResponse();
      movieResponse.setMovieTitle(movie.getMovieTitle());
      movieResponse.setId(movie.getId());
      movieResponse.setDirectorId(movie.getDirectorId());

      List<Long> actorsId = movieActorRepository.actorIdByMovieId(movie.getId());
      List<Actor> actors=new ArrayList<>();
      for (Long actorId : actorsId) {

        Actor actor = actorRepository.findbyActorId(actorId);
        actors.add(actor);
      }
      movieResponse.setActors(actors);
      movieResponseList.add(movieResponse);

    }
    return movieResponseList;
  }
}
