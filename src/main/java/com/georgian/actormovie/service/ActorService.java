package com.georgian.actormovie.service;

import com.georgian.actormovie.entity.Actor;
import com.georgian.actormovie.model.ActorModel;
import com.georgian.actormovie.repository.ActorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ActorService {


  private final ActorRepository actorRepository;

  public ActorService(ActorRepository actorRepository) {
    this.actorRepository = actorRepository;
  }

  public ResponseEntity createActor(ActorModel actorModel){
    Actor actor = new Actor();
    actor.setFirstName(actorModel.getFirstName());
    actor.setLastName(actorModel.getLastName());
    actorRepository.save(actor);
    return ResponseEntity.ok().body("succefully created actor")
  }
}
