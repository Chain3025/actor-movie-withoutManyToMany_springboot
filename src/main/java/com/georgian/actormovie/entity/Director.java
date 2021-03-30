package com.georgian.actormovie.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Director {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  @OneToOne(targetEntity = Movie.class,mappedBy = "director")
  @JsonManagedReference
  private Movie movie;
}
