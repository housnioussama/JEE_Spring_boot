package com.moduleprojectspring.projectSpring.controllers;


import com.moduleprojectspring.projectSpring.entity.CentreVaccination;
import com.moduleprojectspring.projectSpring.entity.Citoyen;
import com.moduleprojectspring.projectSpring.services.CentreVaccinationService;
import com.moduleprojectspring.projectSpring.services.CityonService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class web {
    private final CentreVaccinationService centre;
    private final CityonService citoyen;
    @GetMapping("")
    public String listCebtreVaccination(Model model){
        try {
            List<CentreVaccination> centres =centre.listCentreVaccination();
            model.addAttribute("centres",centres);
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
        }

        return "index";
    }



    @PostMapping("/ajouterCentreVaccination")
    public String ajouterCentreVaccination(String nom, String adresse , RedirectAttributes redirectAttributes){
        try{
            CentreVaccination c= CentreVaccination.builder()
                    .nom(nom)
                    .adress(adresse)
                    .build();
            centre.ajouterCentreVaccination(c);
            redirectAttributes.addFlashAttribute("message", "Centre de Vaccination ajouter avec succes");

        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            System.out.print(e.getMessage());

        }




        return "redirect:/";
    }
    @GetMapping("/supprimerCentreVaccination/{id}")
    public String supprimerCentreVaccination(@PathVariable long id,RedirectAttributes redirectAttributes){
        try{
            centre.supprimerCentreVaccination(id);
            redirectAttributes.addFlashAttribute("message","Centre de vaccination est supprimer");

        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());

        }


        return "redirect:/";
    }
    @GetMapping("listCitoyen/{nom}")
    public String listCitoyenParCentre(@PathVariable String nom , Model model){
            List<Citoyen> citoyens=centre.listCitoyenPourCentre(nom);
            model.addAttribute("citoyens" ,citoyens);
            model.addAttribute("nom",nom);

        return "Citoyen";
    }
    @PostMapping("/ajouterCitoyen")
    public String ajouterCitoyen(String nomCitoyen,String nomCentreVaccination, RedirectAttributes redirectAttributes){
                System.out.println(nomCitoyen + nomCentreVaccination);
           try{
               centre.ajouterCitoyenACentreVaccination(nomCentreVaccination,nomCitoyen);
               redirectAttributes.addFlashAttribute("message","Citoyen "+nomCitoyen+" est ajouter avec succes");
           }

            catch(Exception e){
               redirectAttributes.addFlashAttribute("error",e.getMessage());

        }

        return "redirect:/listCitoyen/"+nomCentreVaccination;
    }
    @GetMapping("/supprimerCitoyen/{id}/{nom}")
    public String supprimerCitoyen(@PathVariable long id,@PathVariable String nom, RedirectAttributes redirectAttributes){
        try{
            citoyen.supprimerCitoryen(id,nom);
            redirectAttributes.addFlashAttribute("message","Citoyen a etes supprimer avec succes");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
        }

        return "redirect:/listCitoyen/"+nom;
    }

    @PostMapping("/modifierCentreVaccination")
    public String modifierCentreVaccination(long id,String nom,String adresse,RedirectAttributes redirectAttributes){
        try {
            centre.modifieCentreVaccination(id,nom,adresse);
            redirectAttributes.addFlashAttribute("message","Centre de vaccination "+nom+" a ete modifier ");

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error","Centre  avec le nom "+nom+" déja exist");
        }
        return "redirect:/";
    }

    @PostMapping("/modifierCitoyen")
    public String modifierCitoyen(long id,String nom, String nomCentre,RedirectAttributes redirectAttributes){
        try {
            citoyen.modifieCitoyen(id,nom);
            redirectAttributes.addFlashAttribute("message","Citoyen a ete modifer ");


        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());

        }
        return "redirect:/listCitoyen/"+nomCentre;
    }


}
