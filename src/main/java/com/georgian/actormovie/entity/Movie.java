package com.georgian.actormovie.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @JoinColumn(name = "movie_id")
  @OneToMany(targetEntity = Actor.class) //orphanRemoval=true is used when mapping table record is delete and parent entity record is deleted but child entity reocrd is not deleted due to cascade.persist
  private List<Actor> actors;
}
