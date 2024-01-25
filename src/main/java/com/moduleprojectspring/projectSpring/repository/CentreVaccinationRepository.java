package com.moduleprojectspring.projectSpring.repository;

import com.moduleprojectspring.projectSpring.entity.CentreVaccination;
import com.moduleprojectspring.projectSpring.entity.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CentreVaccinationRepository extends JpaRepository<CentreVaccination, Long> {
    @Override
    Optional<CentreVaccination> findById(Long Long);
    Optional<CentreVaccination> findCentreVaccinationByNom(String nom);
    Optional<CentreVaccination> findCentreVaccinationByNomAndAdress(String nom, String adresse);
}
