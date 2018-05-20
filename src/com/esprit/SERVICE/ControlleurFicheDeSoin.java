/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.SERVICE;

import com.esprit.entities.Animal;
import com.esprit.entities.FicheDeSoin;
import com.esprit.entities.Userr;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author makni
 */
public class ControlleurFicheDeSoin {

    //Attributs
    //Controle de saisie
    //Instancier
    public int ajouterFicheDeSoin(FicheDeSoin fs) {
        FicheDeSoin fsSoin = new FicheDeSoin(fs.getId_membre(), fs.getObservation(), fs.getMedicament(), fs.getProchainRDV(), fs.getId_animal(), fs.getDateCreation(), 1);
        return fsSoin.ajouterFicheDeSoin();
    }

    public int modifierFicheDeSoin(FicheDeSoin fs) {
        FicheDeSoin fsSoin = new FicheDeSoin(fs.getId(), fs.getId_membre(), fs.getObservation(), fs.getMedicament(), fs.getProchainRDV(), fs.getId_animal(), fs.getDateCreation(), 1);
        return fsSoin.modifierFicheDeSoin();
    }

    public ArrayList ConsulterFicheDeSoin() {
        FicheDeSoin fds = new FicheDeSoin();
        return fds.displayFicheDeSoin();
    }

    public int updateFicheDeSoin(FicheDeSoin fs) {
        FicheDeSoin fsSoin = new FicheDeSoin(fs.getId(), fs.getId_membre(), fs.getObservation(), fs.getMedicament(), fs.getProchainRDV(), fs.getId_animal(), fs.getDateCreation(), 0);
        return fsSoin.supprimerFicheDeSoin();
    }

}
