package com.georgian.actormovie.service;

import com.georgian.actormovie.entity.Actor;
import com.georgian.actormovie.entity.Movie;
import com.georgian.actormovie.repository.ActorRepository;
import com.georgian.actormovie.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActorService {


  private final ActorRepository actorRepository;
  private final MovieRepository movieRepository;
  public ActorService(ActorRepository actorRepository,
      MovieRepository movieRepository) {
    this.actorRepository = actorRepository;
    this.movieRepository = movieRepository;
  }

  public ResponseEntity createActor(Actor actorModel){
    Actor actor = new Actor();
    actor.setFirstName(actorModel.getFirstName());
    actor.setLastName(actorModel.getLastName());
    actor.setNationality(actorModel.getNationality());
    actor.setDob(actorModel.getDob());
    actorRepository.save(actor);
    return ResponseEntity.ok().body("succefully created actor");
  }
  @Transactional
  public ResponseEntity updateActor(Actor actorRequest,Long id){
    if(actorRepository.findById(id).isPresent()){
      Actor actor = actorRepository.findById(id).get();
      if(actorRequest.getId()!=null)
        actor.setId(actorRequest.getId());
      if(actorRequest.getFirstName()!=null)
        actor.setFirstName(actorRequest.getFirstName());
      if(actorRequest.getLastName()!=null)
        actor.setLastName(actorRequest.getLastName());
      if(actorRequest.getDob()!=null)
        actor.setDob(actorRequest.getDob());
      if(actorRequest.getNationality()!=null)
        actor.setNationality(actorRequest.getNationality());
      Actor save = actorRepository.save(actor);
      if(actorRepository.findById(save.getId()).isPresent())
        return ResponseEntity.accepted().body("actor updated succesfully");
      else return ResponseEntity.unprocessableEntity().body("failed");
    }else
      return ResponseEntity.unprocessableEntity().body("record not found:)");
  }

  public ResponseEntity deleteActor(Long id){
    if(actorRepository.findById(id).isPresent()) {
      actorRepository.deleteById(id);
      return ResponseEntity.ok().body("deleted actor");
    }
    else return ResponseEntity.unprocessableEntity().body("record not found");
  }
}
