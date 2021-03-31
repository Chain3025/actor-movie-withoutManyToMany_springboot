package com.georgian.actormovie.repository;

import com.georgian.actormovie.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {

  @Query(value = "select * from actor where id= :actorId"
      ,nativeQuery = true)
  Actor findbyActorId(@Param("actorId")Long actorId);
}
