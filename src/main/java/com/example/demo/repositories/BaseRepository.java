package com.example.demo.repositories;

import com.example.demo.entities.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


public interface BaseRepository<E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {


}
