package com.georgian.actormovie.service;

import com.georgian.actormovie.repository.DirectorRepository;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {

  private final DirectorRepository directorRepository;

  public DirectorService(DirectorRepository directorRepository) {
    this.directorRepository = directorRepository;
  }
}
