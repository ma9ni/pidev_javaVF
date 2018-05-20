/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import Utilities.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ahmed
 */
public class Categorie {

    // nos attributs
    private int idCategorie;
    private int idCategorieId;
    private String nom;

    // getters
    public int getIdCategorie() {
        return idCategorie;
    }

    public int getIdCategorieId() {
        return idCategorieId;
    }

    public String getNom() {
        return nom;
    }

    // setters
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public void setIdCategorieId(int idCategorieId) {
        this.idCategorieId = idCategorieId;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    //constructeur
    /**
     * 1*
     */
    public Categorie() {
    }

    public Categorie(int id) {
        this.idCategorie = id;
    }

    /**
     * 2*
     */
    public Categorie(int idCategorie, int idCategorieId, String nom) {
        this.idCategorie = idCategorie;
        this.idCategorieId = idCategorieId;
        this.nom = nom;
    }

    public Categorie(String nom) {
        this.nom = nom;
    }
    Connection conn = DataSource.getInstance().getConnection();
    private Statement stmt;

    public ArrayList<Categorie> getAllCategorie() {
        ArrayList<Categorie> laListe = new ArrayList<>();
        Categorie leResultat;

        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        // preparation de la requette
        String maRequette = "SELECT `Id_Categorie`, `Id_Categorie_Id`, `Nom_Categorie` FROM `categorie`";
        ResultSet res = null;
        // execution de la requette
        try {
            res = stmt.executeQuery(maRequette);
            while (res.next()) {
                //recuperation des champs
                leResultat = new Categorie();
                leResultat.idCategorie = res.getInt("Id_Categorie");
                leResultat.nom = res.getString("Nom_Categorie");
                leResultat.idCategorieId = res.getInt("Id_Categorie_Id");
                //ajout de l'element a la liste
                laListe.add(leResultat);
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de l'exxecution de la requete de selection\n");
            System.out.println(ex.getMessage());

        }

        return laListe;
    }

    public Categorie getCategorieViaNom() {
        Categorie leResultat = null;
        String maRequette = "SELECT `Id_Categorie`, `Id_Categorie_Id`, `Nom_Categorie` "
                + "FROM `categorie` "
                + "WHERE Nom_Categorie Like '" + this.nom + "' ;";
        ResultSet res = null;
        System.out.println(maRequette);
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(maRequette);
            while (res.next()) {
                //recuperation des champs
                leResultat = new Categorie();
                leResultat.idCategorie = res.getInt("Id_Categorie");
                leResultat.nom = res.getString("Nom_Categorie");
                leResultat.idCategorieId = res.getInt("Id_Categorie_Id");
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de l'exxecution de la requete de selection\n");
            System.out.println(ex.getMessage());
        }

        return leResultat;
    }

    public Categorie getCategorieViaId() {
        Categorie leResultat = null;
        String maRequette = "SELECT `Id_Categorie`, `Id_Categorie_Id`, `Nom_Categorie` "
                + "FROM `categorie` "
                + "WHERE Id_Categorie Like '" + this.idCategorie + "' ;";
        ResultSet res = null;
        System.out.println(maRequette);
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(maRequette);
            while (res.next()) {
                //recuperation des champs
                leResultat = new Categorie();
                leResultat.idCategorie = res.getInt("Id_Categorie");
                leResultat.nom = res.getString("Nom_Categorie");
                leResultat.idCategorieId = res.getInt("Id_Categorie_Id");
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de l'exxecution de la requete de selection\n");
            System.out.println(ex.getMessage());
        }

        return leResultat;
    }

    @Override
    public String toString() {
        return getCategorieViaId().getNom();
    }

}
