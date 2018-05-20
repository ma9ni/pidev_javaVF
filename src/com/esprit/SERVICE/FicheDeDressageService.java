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
                Userr u = new Userr();
                fdd.setId_membre(u);
                fdd.setDispline(rs.getFloat("displine"));
                fdd.setObeissance(rs.getFloat("obeissance"));
                fdd.setSpecialite(rs.getString("specialite"));
                fdd.setAccompagnement(rs.getFloat("accompagnement"));
                fdd.setInterception(rs.getFloat("interception"));
                fdd.setNoteTotal(rs.getInt("noteTotale"));
                fdd.setDateDebut(rs.getDate("dateDebut"));
                fdd.setDateFin(rs.getDate("dateFin"));
                String req2 = "Select * From Animal where id=?;";
                pss = conn.prepareStatement(req2);
                pss.setInt(1, rs.getInt("id_Animal"));
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

}
