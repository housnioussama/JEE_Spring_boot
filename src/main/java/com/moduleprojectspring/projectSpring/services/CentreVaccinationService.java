package com.moduleprojectspring.projectSpring.services;

import com.moduleprojectspring.projectSpring.entity.CentreVaccination;
import com.moduleprojectspring.projectSpring.entity.Citoyen;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CentreVaccinationService {
    CentreVaccination ajouterCentreVaccination(CentreVaccination c);
    CentreVaccination modifieCentreVaccination(long id,String nom,String adress);
    void supprimerCentreVaccination(long id);
    List<CentreVaccination> listCentreVaccination();
List<Citoyen> listCitoyenPourCentre(String nom);
void ajouterCitoyenACentreVaccination(String nomCentre,String nomCitoyen);


}
