package com.georgian.actormovie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Actor {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  @Transient
  private String movieName;
  @ManyToOne(cascade = CascadeType.ALL)
  @JsonIgnore // it ignore the movie call object as actor don't get movie detail
  private Movie movie;

  public String getMovieName() {
    return getMovie().getName();
  }
}
