package com.georgian.actormovie.service;

import com.georgian.actormovie.entity.Actor;
import com.georgian.actormovie.entity.Movie;
import com.georgian.actormovie.repository.ActorRepository;
import com.georgian.actormovie.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
  private final MovieRepository movieRepository;
  private final ActorRepository actorRepository;
  public MovieService(MovieRepository movieRepository,
      ActorRepository actorRepository) {
    this.movieRepository = movieRepository;
    this.actorRepository = actorRepository;
  }

  public ResponseEntity<Object> addMovie(Movie movie){
    Movie newMovie = new Movie();
    newMovie.setName(movie.getName());

    for(Actor actor:movie.getActors()){

      Actor save = actorRepository.save(actor);
      if(!actorRepository.findById(save.getId()).isPresent()){
        return ResponseEntity.unprocessableEntity().body("failed to create actor");
      }
    }

    newMovie.setActors(movie.getActors());
    Movie save = movieRepository.save(movie);
    if(movieRepository.findById(save.getId()).isPresent()){
      return ResponseEntity.accepted().body("successfuly created movie");
    }else return ResponseEntity.unprocessableEntity().body("failed to create movie");

  }
/*
@Transactional usage
what happens if the actor gets saved and an exception is thrown after that.
the movie never gets saved.
Solution : use @Transactional
 */
  public ResponseEntity<Object> deleteMovie(Long id){
    if(movieRepository.findById(id).isPresent()){
      movieRepository.deleteById(id);
      if(movieRepository.findById(id).isPresent()){
        return ResponseEntity.unprocessableEntity().body("failed to deleted specfifed record");
      }else return ResponseEntity.ok().body("succefull deleted movie");
    }else
      return ResponseEntity.unprocessableEntity().body("no record found");
  }
}
