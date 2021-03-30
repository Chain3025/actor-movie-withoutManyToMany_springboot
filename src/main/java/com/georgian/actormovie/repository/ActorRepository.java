package com.georgian.actormovie.repository;

import com.georgian.actormovie.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {

}
