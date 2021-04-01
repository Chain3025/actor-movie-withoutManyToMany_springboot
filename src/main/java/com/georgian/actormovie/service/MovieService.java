package com.georgian.actormovie.service;

import com.georgian.actormovie.entity.Movie;
import com.georgian.actormovie.entity.MovieActor;
import com.georgian.actormovie.model.MovieRequest;
import com.georgian.actormovie.repository.ActorRepository;
import com.georgian.actormovie.repository.MovieActorRepository;
import com.georgian.actormovie.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {
  private final MovieRepository movieRepository;
  private final ActorRepository actorRepository;
  private final MovieActorRepository movieActorRepository;

  public MovieService(MovieRepository movieRepository,
      ActorRepository actorRepository,
      MovieActorRepository movieActorRepository) {
    this.movieRepository = movieRepository;
    this.actorRepository = actorRepository;
    this.movieActorRepository = movieActorRepository;
  }
@Transactional
  public ResponseEntity<Object> addMovie(MovieRequest movieRequest){
    Movie movie = new Movie();
    movie.setMovieTitle(movieRequest.getMovieTitle());
    movie.setDirectorId(movieRequest.getDirectorId());
    Movie save = movieRepository.save(movie);
    movieRequest.getActorId().forEach(actorId -> {
      MovieActor movieActor = new MovieActor();
      movieActor.setMovieId(save.getId());
      movieActor.setActorId(actorId);
      movieActorRepository.save(movieActor);
    });
  return ResponseEntity.accepted().body("movie added");
}
/*
@Transactional usage
what happens if the actor gets saved and an exception is thrown after that.
the movie never gets saved.
Solution : use @Transactional
 */
  public ResponseEntity updateMovie(Long id,MovieRequest movieRequest){
    if(movieRepository.findById(id).isPresent()){
      Movie movie = movieRepository.findById(id).get();
      if(movieRequest.getMovieTitle()!=null)
        movie.setMovieTitle(movieRequest.getMovieTitle());
      if(movieRequest.getDirectorId()!=null)
        movie.setDirectorId(movieRequest.getDirectorId());
      Movie save = movieRepository.save(movie);
      for (Long actorId : movieRequest.getActorId()) {
        Long count = movieActorRepository.countfindByActorIdAndMovieId(actorId, id);
        if(count ==0){
          MovieActor movieActor = new MovieActor();
          movieActor.setMovieId(save.getId());
          movieActor.setActorId(actorId);
          movieActorRepository.save(movieActor);
        }
      }
      return ResponseEntity.accepted().body("update movie");
    }else return ResponseEntity.unprocessableEntity().body("record not found");
  }
  @Transactional
  public ResponseEntity<Object> deleteMovie(Long id){
    if(movieRepository.findById(id).isPresent()){
      movieRepository.deleteById(id);
      movieActorRepository.deleteByMovieId(id);
      if(movieRepository.findById(id).isPresent()){
        return ResponseEntity.unprocessableEntity().body("failed to deleted specfifed record");
      }else return ResponseEntity.ok().body("succefull deleted movie");
    }else
      return ResponseEntity.unprocessableEntity().body("no record found");
  }
}
