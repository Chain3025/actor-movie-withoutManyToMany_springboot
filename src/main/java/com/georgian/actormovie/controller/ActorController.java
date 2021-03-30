package com.georgian.actormovie.controller;

import com.georgian.actormovie.model.ActorModel;
import com.georgian.actormovie.service.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actor")
public class ActorController {

  private final ActorService actorService;

  public ActorController(ActorService actorService) {
    this.actorService = actorService;
  }

  @PostMapping
  public ResponseEntity createActor(@RequestBody ActorModel actorModel){
    return actorService.createActor(actorModel);

  }
}
