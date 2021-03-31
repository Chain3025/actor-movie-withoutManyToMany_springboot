package com.georgian.actormovie.service;

import com.georgian.actormovie.entity.Actor;
import com.georgian.actormovie.entity.Director;
import com.georgian.actormovie.repository.DirectorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectorService {

  private final DirectorRepository directorRepository;

  public DirectorService(DirectorRepository directorRepository) {
    this.directorRepository = directorRepository;
  }

  public ResponseEntity createDirector(Director directorModel){
    Director director = new Director();
    director.setFirstName(directorModel.getFirstName());
    director.setLastName(directorModel.getLastName());
    director.setNationality(directorModel.getNationality());
    director.setDob(directorModel.getDob());
    directorRepository.save(director);
    return ResponseEntity.ok().body("succefully created director");
  }
  @Transactional
  public ResponseEntity updateDirector(Director directorRequest,Long id){
    if(directorRepository.findById(id).isPresent()){
      Director director = directorRepository.findById(id).get();
      if(directorRequest.getId()!=null)
        director.setId(directorRequest.getId());
      if(directorRequest.getFirstName()!=null)
        director.setFirstName(directorRequest.getFirstName());
      if(directorRequest.getLastName()!=null)
        director.setLastName(directorRequest.getLastName());
      if(directorRequest.getDob()!=null)
        director.setDob(directorRequest.getDob());
      if(directorRequest.getNationality()!=null)
        director.setNationality(directorRequest.getNationality());
      Director save = directorRepository.save(director);
      if(directorRepository.findById(save.getId()).isPresent())
        return ResponseEntity.accepted().body("director updated succesfully");
      else return ResponseEntity.unprocessableEntity().body("failed");
    }else
      return ResponseEntity.unprocessableEntity().body("record not found:)");
  }

  public ResponseEntity deleteDirector(Long id){
    if(directorRepository.findById(id).isPresent()) {
      directorRepository.deleteById(id);
      return ResponseEntity.ok().body("deleted director");
    }
    else return ResponseEntity.unprocessableEntity().body("record not found");
  }
}
