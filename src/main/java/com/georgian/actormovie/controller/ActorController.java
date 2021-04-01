package com.georgian.actormovie.controller;

import com.georgian.actormovie.entity.Actor;
import com.georgian.actormovie.entity.Movie;
import com.georgian.actormovie.model.ActorResponse;
import com.georgian.actormovie.repository.ActorRepository;
import com.georgian.actormovie.repository.MovieActorRepository;
import com.georgian.actormovie.repository.MovieRepository;
import com.georgian.actormovie.service.ActorService;
import com.georgian.actormovie.service.MovieService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/actor")
public class ActorController {

  private final ActorService actorService;
  private final ActorRepository actorRepository;
  private final MovieActorRepository movieActorRepository;
  private final MovieRepository movieRepository;

  public ActorController(ActorService actorService,
      ActorRepository actorRepository,
      MovieActorRepository movieActorRepository,
      MovieRepository movieRepository) {
    this.actorService = actorService;
    this.actorRepository = actorRepository;
    this.movieActorRepository = movieActorRepository;
    this.movieRepository = movieRepository;
  }

  @PostMapping
  public ResponseEntity createActor(@RequestBody Actor actorModel){
    return actorService.createActor(actorModel);

  }
  @GetMapping("/id/{id}")
  public ResponseEntity<ActorResponse> getActor(@PathVariable Long id){
    ActorResponse actorResponse = new ActorResponse();
    if(actorRepository.findById(id).isPresent()){
      Actor actor = actorRepository.findById(id).get();
      actorResponse.setId(actor.getId());
      actorResponse.setFirstName(actor.getFirstName());
      actorResponse.setLastName(actor.getLastName());
      actorResponse.setNationality(actor.getNationality());
      actorResponse.setDob(actor.getDob());

      List<Movie> movies = new ArrayList<>();
      List<Long> movieIds = movieActorRepository.movieIdByActorId(id);
      for (Long movieId : movieIds) {
        Optional<Movie> byId = movieRepository.findById(movieId);
        if(byId.isPresent()){
          Movie movie = byId.get();
          movies.add(movie);
        }
      }
      actorResponse.setMovieList(movies);
      return new ResponseEntity<>(actorResponse,HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

  }
  @GetMapping()
  public List<ActorResponse> getAllActor(){
    List<Actor> all = actorRepository.findAll();
    if(all.size()<=0){
      return null;
    }
    List<ActorResponse> actorResponses = new ArrayList<>();
    for (Actor actor : all) {

      ActorResponse actorResponse = new ActorResponse();
      actorResponse.setId(actor.getId());
      actorResponse.setFirstName(actor.getFirstName());
      actorResponse.setLastName(actor.getLastName());
      actorResponse.setNationality(actor.getNationality());
      actorResponse.setDob(actor.getDob());

      List<Movie> movies = new ArrayList<>();
      List<Long> movieIds = movieActorRepository.movieIdByActorId(actor.getId());
      for (Long movieId : movieIds) {
        Optional<Movie> byId = movieRepository.findById(movieId);
        if(byId.isPresent()){
          Movie movie = byId.get();
          movies.add(movie);
        }
      }
      actorResponse.setMovieList(movies);
      actorResponses.add(actorResponse);

    }
    return actorResponses;
  }

  @PutMapping()
  public ResponseEntity updateActor(@RequestParam Long id,@RequestBody Actor actor){
    return actorService.updateActor(actor,id);
  }
  @DeleteMapping("/id/{id}")
  public ResponseEntity deleteActor(@PathVariable Long id){
    return actorService.deleteActor(id);
  }

}
