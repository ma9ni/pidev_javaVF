/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.SERVICE;

import Utilities.DataSource;
import com.esprit.entities.FicheDeDressage;
import com.esprit.entities.Userr;
import com.esprit.entities.Animal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author makni
 */
public class FicheDeDressageService {

    static Connection conn = DataSource.getInstance().getConnection();
    private ResultSet rs = null;
    private ResultSet rss = null;
    private PreparedStatement ps = null;
    private PreparedStatement pss = null;
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    Animal a;

    public ArrayList displayFicheDeDressage() {
        FicheDeDressage fdd;
        ArrayList<FicheDeDressage> fiList = new ArrayList<>();
        try {
            String req = "Select * From f_dressage where etat=1 and  id_membre = " + 12;
            ps = conn.prepareStatement(req);

            rs = ps.executeQuery();
            while (rs.next()) {
                fdd = new FicheDeDressage();
                fdd.setId(rs.getInt("id"));
                Userr u = new Userr(rs.getInt("id_membre"));
                fdd.setId_membre(u);
                fdd.setDispline(rs.getFloat("displine"));
                fdd.setObeissance(rs.getFloat("obeissance"));
                fdd.setSpecialite(rs.getString("specialite"));
                fdd.setAccompagnement(rs.getFloat("accompagnement"));
                fdd.setInterception(rs.getFloat("interception"));
                fdd.setNoteTotal(rs.getInt("noteTotale"));
                fdd.setDateDebut(rs.getDate("dateDebut"));
                fdd.setDateFin(rs.getDate("dateFin"));
                String req2 = "Select * From animal where id=?;";
                pss = conn.prepareStatement(req2);
                pss.setInt(1, rs.getInt("id_animal"));
                rss = pss.executeQuery();
                while (rss.next()) {
                    a = new Animal(rss.getInt("id"), rss.getString("nom"), rss.getString("description"));
                }
                System.out.println(a);
                fdd.setId_animal(a);
                fiList.add(fdd);
            }
            return fiList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return fiList;
        }
    }

    public int ajouterFicheDeDressage(FicheDeDressage fdd) {

        String datdebu = formater.format(fdd.getDateDebut());
        String datfin = formater.format(fdd.getDateFin());
        String req1 = "INSERT INTO `f_dressage`(`id_membre`, `displine`, `obeissance`, `specialite`, `accompagnement`, `interception`, `noteTotale`, `dateDebut`, `dateFin`, `id_animal`, `etat`) VALUES (?,?,?,?,?,?,?,?,?,?,1)";
        try {

            ps = conn.prepareStatement(req1);
            ps.setInt(1, 12);
            ps.setFloat(2, fdd.getDispline());
            ps.setFloat(3, fdd.getObeissance());
            ps.setString(4, fdd.getSpecialite());
            ps.setFloat(5, fdd.getAccompagnement());
            ps.setFloat(6, fdd.getInterception());
            ps.setFloat(7, fdd.getNoteTotal());
            ps.setString(8, datdebu);
            ps.setString(9, datfin);
            ps.setInt(10, fdd.getId_animal().getIdAnimal());
            ps.execute();
            System.out.println("Insertion Ok");
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public int modifierFicheDeDressage(FicheDeDressage fdd) {
        try {
            String datdebu = formater.format(fdd.getDateDebut());
            String datfin = formater.format(fdd.getDateFin());
            String req = "UPDATE `f_dressage` SET `id_membre`=?,`displine`=?,`obeissance`=?,`specialite`=?,`accompagnement`=?,`interception`=?,`noteTotale`=?,`dateDebut`=?,`dateFin`=?,`id_animal`=?,`etat`=1 Where id =? ";
            ps = conn.prepareStatement(req);
            ps.setInt(1, fdd.getId_membre().getId());
            ps.setFloat(2, fdd.getDispline());
            ps.setFloat(3, fdd.getObeissance());
            ps.setString(4, fdd.getSpecialite());
            ps.setFloat(5, fdd.getAccompagnement());
            ps.setFloat(6, fdd.getInterception());
            ps.setFloat(7, fdd.getNoteTotal());
            ps.setString(8, datdebu);
            ps.setString(9, datfin);
            ps.setInt(10, fdd.getId_animal().getIdAnimal());
            ps.setInt(11, fdd.getId());
            ps.execute();
            return 1;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public ArrayList<Animal> selectAllAnimal() {

        ArrayList<Animal> ani = new ArrayList<>();
        PreparedStatement pr;

        try {
            pr = conn.prepareStatement("SELECT * FROM animal ");
            ResultSet res = pr.executeQuery();
            while (res.next()) {
                Animal a = new Animal();
                a.setIdAnimal(res.getInt("id"));
                a.setNom(res.getString("nom"));
                a.setNomproprietaire(res.getString("nomproprietaire"));
                a.setDescription(res.getString("description"));
                a.setSexe(res.getString("sexe"));
                a.setDescription(res.getString("Datedenaissance"));
                a.setPhoto(res.getString("image"));
                a.setRace(res.getString("race"));
                ani.add(a);

            }
        } catch (SQLException ex) {
            ex.toString();
        }

        return ani;

    }

    public int supprimerFicheDeDressage(FicheDeDressage fdd) {
        try {
            String req = "update f_dressage set etat= 0 where id =? ";
            ps = conn.prepareStatement(req);
            ps.setInt(1, fdd.getId());
            ps.execute();
            return 1;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
