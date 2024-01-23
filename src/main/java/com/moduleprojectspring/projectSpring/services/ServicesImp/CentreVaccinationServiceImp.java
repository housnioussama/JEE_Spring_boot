package com.moduleprojectspring.projectSpring.services.ServicesImp;

import com.moduleprojectspring.projectSpring.entity.CentreVaccination;
import com.moduleprojectspring.projectSpring.entity.Citoyen;
import com.moduleprojectspring.projectSpring.repository.CentreVaccinationRepository;
import com.moduleprojectspring.projectSpring.services.CentreVaccinationService;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class CentreVaccinationServiceImp implements CentreVaccinationService {
    private final CentreVaccinationRepository centreVaccinationRepository;


    @Override
    public CentreVaccination ajouterCentreVaccination(CentreVaccination c) {
        Optional<CentreVaccination> centre=centreVaccinationRepository.findCentreVaccinationByNom(c.getNom());
        if(centre.isEmpty()){
            return centreVaccinationRepository.save(c);
        }
        else   throw new RuntimeException("Centre déja exist");

    }

    @Override
    public CentreVaccination modifieCentreVaccination(long id , String nom,String adresse) {
        Optional<CentreVaccination> centre=centreVaccinationRepository.findById(id);
        if(centre.isPresent()){
            CentreVaccination centre1=centre.get();
            centre1.setNom(nom);
            centre1.setAdress(adresse);
            ajouterCentreVaccination(centre1);

        }
        return null;
    }

    @Override
    public void supprimerCentreVaccination(long id) {
        Optional<CentreVaccination> centre = centreVaccinationRepository.findById(id);
        if(centre.isPresent()){
            centreVaccinationRepository.delete(centre.get());
        }
        else throw  new NullPointerException("Centre Vaccination n'exite pas ");

    }

    @Override
    public List<CentreVaccination> listCentreVaccination() {
        return centreVaccinationRepository.findAll();
    }

    @Override
    public List<Citoyen> listCitoyenPourCentre(String nom) {
        Optional<CentreVaccination> centreVaccination=centreVaccinationRepository.findCentreVaccinationByNom(nom);
        if(centreVaccination.isPresent()){
            return centreVaccination.get().getCitoyenList();
        }
        else  return  null;

    }

    @Override
    public void ajouterCitoyenACentreVaccination(String nomCentre, String nomCitoyen) {
        Optional<CentreVaccination> centreVaccination=centreVaccinationRepository.findCentreVaccinationByNom(nomCentre);
        if(centreVaccination.isPresent()){
            System.out.println("true");
            Citoyen c= Citoyen.builder()
                    .nom(nomCitoyen)
                    .build();
            centreVaccination.get().getCitoyenList().add(c);
            centreVaccinationRepository.save(centreVaccination.get());
        }
        else throw  new NullPointerException("Centre de Vaccination avec le nom "+nomCentre+"n'existe pas ");


    }




}
