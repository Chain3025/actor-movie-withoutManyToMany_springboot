package com.georgian.actormovie.controller;

import com.georgian.actormovie.entity.Director;
import com.georgian.actormovie.repository.DirectorRepository;
import com.georgian.actormovie.service.DirectorService;
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
    return  directorService.createDirector(director);
  }
  @GetMapping("/id/{id}")
  public ResponseEntity<Director> getDirector(@PathVariable Long id){
    if(directorRepository.findById(id).isPresent()){
      return new ResponseEntity<>(directorRepository.findById(id).get(), HttpStatus.OK);
    }else
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
  @GetMapping
  public List<Director> getAllDirector(){
    return directorRepository.findAll();
  }
  @PutMapping()
  public ResponseEntity<Object> putDirector(@RequestParam Long id,@RequestBody Director director) {
    return directorService.updateDirector(director, id);
  }
  @DeleteMapping("/id/{id}")
  public ResponseEntity deleteDirector(@PathVariable Long id){
    return directorService.deleteDirector(id);
  }
}
