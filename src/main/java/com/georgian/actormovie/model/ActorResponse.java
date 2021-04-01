package com.georgian.actormovie.model;

import com.georgian.actormovie.entity.Movie;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class ActorResponse {
  private Long id;
  private String firstName;
  private String lastName;
  private String nationality;
  private LocalDate dob;
  private List<Movie> movieList;
}
