/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.util.Date;


/**
 *
 * @author houssem
 */
public class Adoption {

    private int idAdoption;
    private User idMembre;
    private Date dateAnnonce;
    private String lieu;
    private int etatAdoption;
    private String description;
    private String type;
    private Animal idAnimal;
//constructeurs

    public Adoption() {
    }

    public Adoption(User idMembre, String lieu, String description, String type, Animal idAnimal) {
        this.idMembre = idMembre;
        this.lieu = lieu;
        this.description = description;
        this.type = type;
        this.idAnimal = idAnimal;
    }

    public Adoption(User idMembre, Date dateAnnonce, String lieu, int etatAdoption, String description) {
        this.idMembre = idMembre;
        this.dateAnnonce = dateAnnonce;
        this.lieu = lieu;
        this.etatAdoption = etatAdoption;
        this.description = description;
    }
// les getters et stters

    public Animal getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Animal idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    // getters and setters
    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdAdoption() {
        return idAdoption;
    }

    public void setIdAdoption(int idAdoption) {
        this.idAdoption = idAdoption;
    }

    public User getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(User idMembre) {
        this.idMembre = idMembre;
    }

    public Date getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(Date dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getEtatAdoption() {
        return etatAdoption;
    }

    public void setEtatAdoption(int etatAdoption) {
        this.etatAdoption = etatAdoption;
    }

    @Override
    public String toString() {
        return "Adoption{" + "idAdoption=" + idAdoption + ", idMembre=" + idMembre + ", dateAnnonce=" + dateAnnonce + ", lieu=" + lieu + ", etatAdoption=" + etatAdoption + ", description=" + description + ", type=" + type + ", idAnimal=" + idAnimal + '}';
    }
    
    

    
}
