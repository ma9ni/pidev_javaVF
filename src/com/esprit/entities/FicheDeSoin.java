/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import Utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author makni
 */
public class FicheDeSoin {

    //Les attributs
    private int id;
    private Userr id_membre;
    private String observation;
    private String medicament;
    private Date prochainRDV;
    private Animal id_animal;
    private Date dateCreation;
    private int etat;
    Animal a;

    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
//Les attributs DB
    Connection conn = DataSource.getInstance().getConnection();
    private ResultSet rs;
    private ResultSet rss;
    private PreparedStatement pss;
    private PreparedStatement ps;
    private Statement stmt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Userr getId_membre() {
        return id_membre;
    }

    public void setId_membre(Userr id_membre) {
        this.id_membre = id_membre;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public Date getProchainRDV() {
        return prochainRDV;
    }

    public void setProchainRDV(Date prochainRDV) {
        this.prochainRDV = prochainRDV;
    }

    public Animal getId_animal() {
        return id_animal;
    }

    public void setId_animal(Animal id_animal) {
        this.id_animal = id_animal;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public FicheDeSoin() {
    }

    public FicheDeSoin(int id, Userr id_membre, String observation, String medicament, Date prochainRDV, Animal id_animal, Date dateCreation, int etat) {
        this.id = id;
        this.id_membre = id_membre;
        this.observation = observation;
        this.medicament = medicament;
        this.prochainRDV = prochainRDV;
        this.id_animal = id_animal;
        this.dateCreation = dateCreation;
        this.etat = etat;
    }

    public FicheDeSoin(int id, String observation, String medicament, Date prochainRDV) {
        this.id = id;
        this.observation = observation;
        this.medicament = medicament;
        this.prochainRDV = prochainRDV;

    }

    public FicheDeSoin(Userr id_membre, String observation, String medicament, Date prochainRDV, Animal id_animal, Date dateCreation, int etat) {

        this.id_membre = id_membre;
        this.observation = observation;
        this.medicament = medicament;
        this.prochainRDV = prochainRDV;
        this.id_animal = id_animal;
        this.dateCreation = dateCreation;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "FicheDeSoin{" + "id=" + id + ", id_membre=" + id_membre + ", observation=" + observation + ", medicament=" + medicament + ", prochainRDV=" + prochainRDV + ", id_animal=" + id_animal + ", dateCreation=" + dateCreation + ", etat=" + etat + ", formater=" + formater + '}';
    }

////nos methodes
    /*
* *** L'ajout ****
     */
    public int ajouterFicheDeSoin() {
        /**
         * Creation de Statement**
         */
        try {

            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        /**
         * Creation de la Requette**
         *
         */

        String dateprrdv = formater.format(this.prochainRDV);
        String datepcre = formater.format(this.dateCreation);

        String req = "INSERT INTO `f_soin`(`id`, `id_membre`, `observation`, `medicament`, `dateCreation`,`prochainRDV`, `id_animal`, `etat`) VALUES (" + this.id + "," + this.id_membre.getId() + ",'" + this.observation + "','" + this.medicament + "','" + datepcre + "','" + dateprrdv + "'," + this.id_animal.getId() + ",1)";
        try {
            /**
             * execution de la requette**
             */
            stmt.executeUpdate(req);
            return 1;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return 0;

    }

    /*
* *** Modification ****
     */
    public int modifierFicheDeSoin() {
        /**
         * Creation de Statement**
         */
        try {

            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        /**
         * Creation de la Requette**
         */

        String date = formater.format(prochainRDV);
        System.out.println("Ahmeeeed date" + date);
        String req = "UPDATE `f_soin` SET `id_membre`=" + this.id_membre + ",`observation`='" + this.observation + "',`medicament`='" + this.medicament + "',`prochainRDV`='" + date + "' WHERE `id`=" + this.id + ";";
        System.out.println(req);
        try {
            /*
             * execution de la requette**
             */
            stmt.executeUpdate(req);
            System.out.println("Updaaaaate Bieeeen");
            return 1;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            System.out.println("Ereeeeeeeeer update");
        }
        return 0;

    }

    /*
* *** Recherche ****

    /*
* *** Consultation ****
     */
    public ArrayList<FicheDeSoin> displayFicheDeSoin() {
        ArrayList<FicheDeSoin> ficheDeSoins = new ArrayList<>();
        String req = "Select * From f_soin where etat=1 and  id_membre =" + 8;
        try {
            ps = conn.prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                FicheDeSoin fs = new FicheDeSoin();
                fs.id = rs.getInt(1);
                Userr u = new Userr(rs.getInt("id_membre"));
                this.setId_membre(u);
                fs.observation = rs.getString(3);
                fs.medicament = rs.getString("medicament");
                fs.dateCreation = rs.getDate("dateCreation");
                fs.prochainRDV = rs.getDate("prochainRDV");
                String req2 = "Select * From animal where id=?;";
                pss = conn.prepareStatement(req2);
                pss.setInt(1, rs.getInt("id_animal"));
                rss = pss.executeQuery();
                while (rss.next()) {
                    a = new Animal(rss.getInt("id"), rss.getString("nom"), rss.getString("description"));
                }
                System.out.println(a);
                fs.setId_animal(a);
                ficheDeSoins.add(fs);
            }
            return ficheDeSoins;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Erreuuur d'execution req");
        }
        return ficheDeSoins;

    }

    /*
* *** Supprission ****
     */
    public int supprimerFicheDeSoin() {
        try {
            String req = "update f_soin set etat= 0 where id =? ";
            ps = conn.prepareStatement(req);
            ps.setInt(1, this.id);
            ps.execute();

            return 1;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;

    }

}
