package com.moduleprojectspring.projectSpring.services.ServicesImp;

import com.moduleprojectspring.projectSpring.entity.CentreVaccination;
import com.moduleprojectspring.projectSpring.entity.Citoyen;
import com.moduleprojectspring.projectSpring.repository.CentreVaccinationRepository;
import com.moduleprojectspring.projectSpring.repository.CityonRepository;
import com.moduleprojectspring.projectSpring.services.CentreVaccinationService;
import com.moduleprojectspring.projectSpring.services.CityonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CitoyenServiceImp implements CityonService {
    private final CityonRepository cityonRepository;
    private  final  CentreVaccinationRepository centreVaccinationRepository;


    @Override
    public Citoyen ajouterCitoyen(Citoyen c) {
        return cityonRepository.save(c);
    }

    @Override
    public Citoyen modifieCitoyen(long id,String nom) {
        //        ////// code to write
        Optional<Citoyen> citoyen=cityonRepository.findById(id);
        if(citoyen.isPresent()){
            Citoyen c=citoyen.get();
            c.setNom(nom);
            cityonRepository.save(c);
        }
        return null;
    }

    @Override
    public void supprimerCitoryen(long id,String nom) {
        Optional<CentreVaccination> centre=centreVaccinationRepository.findCentreVaccinationByNom(nom);

        Optional<Citoyen> c=cityonRepository.findById(id);

        if(c.isPresent()){
            centre.get().getCitoyenList().remove(c.get());
            centreVaccinationRepository.save(centre.get());
            cityonRepository.delete(c.get());
        }
        else throw  new NullPointerException("Citoyen deja n'exist pas ");

    }

    @Override
    public List<Citoyen> listCitoyen() {
        return cityonRepository.findAll();
    }


}
