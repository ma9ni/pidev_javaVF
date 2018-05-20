/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.SERVICE;

import com.esprit.entities.Adoption;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author houssem
 */
public class ControlleurAdoption {

    public int ajoutDonnation(int idMembre, Date dateAnnonce, String lieu, int etatAdoption, String description) {
        Adoption a = new Adoption(idMembre, dateAnnonce, lieu, etatAdoption, description);
        return a.ajouterDonnation();
    }

    public int ajoutDelegation(int idMembre, Date dateAnnonce, String lieu, int etatAdoption, String description) {
        Adoption a = new Adoption(idMembre, dateAnnonce, lieu, etatAdoption, description);
        return a.ajouterDelegation();
    }

    public int ajoutDonnation(int idMembre, String lieu, String description, String type, int idAnimal) {
        Adoption a = new Adoption(idMembre, lieu, description, type, idAnimal);
        return a.ajouterDonnation();
    }

    public int ajoutDelegation(int idMembre, String lieu, String description, String type, int idAnimal) {
        Adoption a = new Adoption(idMembre, lieu, description, type, idAnimal);
        return a.ajouterDelegation();
    }

    public ArrayList<Adoption> selectAll() {
        Adoption a = new Adoption();
        return a.selectAll();
    }

}
