package com.georgian.actormovie.model;

import java.util.List;
import lombok.Data;

@Data
public class MovieRequest {
  private Long directorId;
  private String movieTitle;
  private List<Long> actorId;

}
