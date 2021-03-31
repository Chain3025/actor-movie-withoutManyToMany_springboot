package com.georgian.actormovie.controller;

import com.georgian.actormovie.entity.Actor;
import com.georgian.actormovie.repository.ActorRepository;
import com.georgian.actormovie.service.ActorService;
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
@RequestMapping("/actor")
public class ActorController {

  private final ActorService actorService;
  private final ActorRepository actorRepository;
  public ActorController(ActorService actorService,
      ActorRepository actorRepository) {
    this.actorService = actorService;
    this.actorRepository = actorRepository;
  }

  @PostMapping
  public ResponseEntity createActor(@RequestBody Actor actorModel){
    return actorService.createActor(actorModel);

  }
  @GetMapping("/id/{id}")
  public Actor getActor(@PathVariable Long id){
    return actorRepository.findById(id).get();
  }
  @GetMapping()
  public List<Actor> getAllActor(){
    return actorRepository.findAll();
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
