package com.georgian.actormovie.repository;

import com.georgian.actormovie.entity.MovieActor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieActorRepository extends JpaRepository<MovieActor,Long> {

  @Query(value = "select count(*) from movie_actor where movie_id= :movieId and actor_id= :actorId"
      ,nativeQuery = true)
  Long countfindByActorIdAndMovieId(@Param("actorId")Long actorId,@Param("movieId") Long movieId);

  void deleteByMovieId(Long id);
  @Query(value = "select actor_id from movie_actor where movie_id= :id",
      nativeQuery = true)
  List<Long> actorIdByMovieId(@Param("id") Long id);

  @Query(value = "select movie_id from movie_actor where actor_id= :id",
      nativeQuery = true)
  List<Long> movieIdByActorId(@Param("id") Long id);
}
