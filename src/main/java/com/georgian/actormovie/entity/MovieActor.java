package com.georgian.actormovie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@IdClass(MovieActorId.class)
public class MovieActor {

  @Id
  private Long movieId;
  @Id
  private Long actorId;
}
