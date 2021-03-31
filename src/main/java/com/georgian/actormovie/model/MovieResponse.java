package com.georgian.actormovie.model;

import com.georgian.actormovie.entity.Actor;
import java.util.List;
import lombok.Data;

@Data
public class MovieResponse {
  private Long id;
  private Long directorId;
  private String movieTitle;
  private List<Actor> actors;
}
