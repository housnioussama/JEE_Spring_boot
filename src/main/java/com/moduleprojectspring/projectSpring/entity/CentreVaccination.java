package com.moduleprojectspring.projectSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Builder
@Data@AllArgsConstructor@NoArgsConstructor
public class CentreVaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String adress;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Citoyen> citoyenList;
}
