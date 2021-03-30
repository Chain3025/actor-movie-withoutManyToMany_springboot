package com.georgian.actormovie.controller;

import com.georgian.actormovie.entity.Director;
import com.georgian.actormovie.repository.DirectorRepository;
import com.georgian.actormovie.service.DirectorService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/director")
public class DirectorController {

  private final DirectorService directorService;
  private final DirectorRepository directorRepository;
  public DirectorController(DirectorService directorService,
      DirectorRepository directorRepository) {
    this.directorService = directorService;
    this.directorRepository = directorRepository;
  }

  @PostMapping
  public ResponseEntity<Object> addDirector(@RequestBody Director director){
    Director save = directorRepository.save(director);
    return ResponseEntity.accepted().body("created director");
  }

  @GetMapping
  public List<Director> getDirector(){
    return directorRepository.findAll();
  }
  @PutMapping()
  public ResponseEntity<Object> putDirector(@RequestParam Long id,@RequestBody Director director){
    if(directorRepository.findById(id).isPresent()){
      Director director1 = directorRepository.findById(id).get();
      if(director.getFirstName()!=null)
        director1.setFirstName(director.getFirstName());
      if(director.getLastName()!=null)
        director1.setLastName(director.getLastName());
      Director save = directorRepository.save(director1);
      return ResponseEntity.accepted().body("updated director");
    }else return ResponseEntity.unprocessableEntity().body("reocrd not found");
  }
}
