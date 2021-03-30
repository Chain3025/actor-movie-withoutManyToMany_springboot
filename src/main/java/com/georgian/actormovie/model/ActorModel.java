package com.georgian.actormovie.model;

import com.georgian.actormovie.entity.Movie;
import lombok.Data;

@Data
public class ActorModel {
  private String firstName;
  private String lastName;
  private Movie movie;
}
