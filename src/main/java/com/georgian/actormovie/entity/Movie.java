package com.georgian.actormovie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long directorId;
  private String movieTitle;


}
//  @JoinColumn(name = "movie_id")
//  @OneToMany(targetEntity = Actor.class,cascade = CascadeType.ALL) //orphanRemoval=true is used when mapping table record is delete and parent entity record is deleted but child entity reocrd is not deleted due to cascade.persist
//  //it is used to stop stackoverflow as circular call to one to other
//  private List<Actor> actors;