package com.moduleprojectspring.projectSpring.repository;

import com.moduleprojectspring.projectSpring.entity.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityonRepository extends JpaRepository<Citoyen,Long> {
    Optional<Citoyen> findById(long id);

}
