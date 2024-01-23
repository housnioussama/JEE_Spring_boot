package com.moduleprojectspring.projectSpring.services;

import com.moduleprojectspring.projectSpring.entity.Citoyen;
import java.util.List;

public interface CityonService {
    Citoyen ajouterCitoyen(Citoyen c);
    Citoyen modifieCitoyen(long id,String nom);
    void supprimerCitoryen(long id,String nom);
    List<Citoyen> listCitoyen();




}
