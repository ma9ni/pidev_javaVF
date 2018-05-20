/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.SERVICE;

import Utilities.BCrypt;
import Utilities.DataSource;
import com.esprit.entities.Userr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class UserController {

    Connection conn = DataSource.getInstance().getConnection();
    private Statement stmt;
    String hasch;

    /**
     * l'ajou
     *
     * @return t*
     */
    public int ajouterMembre(String username, String email, String pasword, int num, String photo, String adresse) {
        Userr m = new Userr(username, email, pasword, num, photo, adresse);

        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        // preparation de la requette

        String maRequette = "INSERT INTO `fos_user`( `username`, "
                + "`username_canonical`,"
                + " `email`, `email_canonical`,"
                + " `enabled`,  `password`, "
                + " `roles`, `adresse`,  `num_tel`, "
                + " `image`) "
                + "VALUES ( '"
                + m.getUsername() + "','"
                + m.getUsername() + "','"
                + m.getEmail() + "','"
                + m.getEmail() + "',1,'"
                + encrypt(m.getPasword()) + "',"
                + "'a:0:{}','"
                + m.getAdresse() + "',"
                + m.getNum_tel() + ",'"
                + m.getImage() + "'"
                + ");";
        System.out.println(maRequette);

        // execution de la requette
        try {
            stmt.executeUpdate(maRequette);
            System.out.println(" l'ajout est effectue");
            return 1;
        } catch (SQLException houssem_marnissi) {
            System.out.println("erreur lors de l'execution de la requete d'ajout d'un membre \n");
            System.out.println(houssem_marnissi.getMessage());

        }
        return 0;

    }

    //supprission d'un acc
    public int supprimerMembre(Userr a) {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        // preparation de la requette
        String maRequette = "UPDATE `membre` "
                + "SET `etat`= 0"
                + " WHERE id = "
                + a.getId()
                + ";";

        // execution de la requette
        try {
            stmt.executeUpdate(maRequette);
            System.out.println(" l'ajout est effectue");
            return 1;
        } catch (SQLException ahmed_makni) {
            System.out.println("erreur lors de l'exxecution de la requete de la supprission \n");
            System.out.println(ahmed_makni.getMessage());

        }
        return 0;

    }

    //encrypt
    public String encrypt(String password) {
        System.out.println(password);
        String str = new String();
        str = "$2y";
        try {

            hasch = BCrypt.hashpw(password, BCrypt.gensalt());
//            String pwd = StringUtil.replaceAll(hasch, "$2a", "$2y");
            for (int i = 3; i < hasch.length(); i++) {
                str = str + hasch.charAt(i);

            }
            System.out.println(str);
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        //String pwd = StringUtil.replaceAll(hasch, "$2a", "$2y");
        return str;
    }

    //la methode modifier
    public int modifierMembre(Userr m, int id) {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        // preparation de la requette
        String maRequette = "UPDATE `fos_user` SET "
                + "`username`='" + m.getUsername() + "',"
                + "`adresse`='" + m.getAdresse() + "',"
                + "`email`='" + m.getEmail() + "',"
                + "`num`=" + m.getNum_tel() + ","
                + "`photo`='" + m.getImage()
                + "' WHERE id_membre= " + id
                + ";";

        // execution de la requette
        try {
            stmt.executeUpdate(maRequette);
            System.out.println("la modification est ffectuer");
            return 1;
        } catch (SQLException ahmed_makni) {
            System.out.println("erreur lors de l'exxecution de la requete de la modification \n");
            System.out.println(ahmed_makni.getMessage());

        }
        return 0;
    }

    public int modifierpasword(Userr m1, int id) {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        // preparation de la requette

        String maRequette = "UPDATE `fos_user` SET "
                + "`pasword`='" + encrypt(m1.getPasword())
                + "' WHERE id_membre= " + id
                + ";";

        // execution de la requette
        try {
            stmt.executeUpdate(maRequette);
            System.out.println("la modification est ffectuer");
            return 1;
        } catch (SQLException ahmed_makni) {
            System.out.println("erreur lors de l'exxecution de la requete de la modification \n");
            System.out.println(ahmed_makni.getMessage());

        }
        return 0;
    }

    //methode consultation by id
    public Userr getMembre(int id) {
        Userr leResultat = new Userr();
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        // preparation de la requette
        String maRequette = "SELECT `id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `note`, `adresse`, `gouvernorat`, `num_tel`, `type`, `confirmation`, `image` FROM `fos_user` WHERE id=" + id + ";";

        // execution de la requette
        try {
            ResultSet res = stmt.executeQuery(maRequette);
            System.out.println(" la recuperation des donnees est effectue");
            while (res.next()) {
                leResultat.setUsername(res.getString("username"));
                leResultat.setEmail(res.getString("email"));
                leResultat.setPasword(res.getString("password"));
                leResultat.setImage(res.getString("image"));
                leResultat.setAdresse(res.getString("adresse"));
                leResultat.setRole(res.getString("roles"));
                leResultat.setNum_tel(res.getInt("num_tel"));
                leResultat.setId(res.getInt("id"));


                /*et la suite ***/
            }

        } catch (SQLException houssem_marnissi) {
            System.out.println("erreur lors de l'exxecution de la requete de la getmembre \n");
            System.out.println(houssem_marnissi.getMessage());

        }

        return leResultat;
    }
    //methode consultation by email

    public Userr getMembreByEmail(String emails) {
        Userr leResultat = new Userr();
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        System.out.println("'chest lemail" + emails + "aa");

        // execution de la requette
        try {
            // preparation de la requette
            String maRequette = "SELECT `id`, `username`, "
                    + "`username_canonical`, `email`, `email_canonical`, "
                    + "`enabled`, `salt`, `password`, `last_login`,"
                    + " `confirmation_token`, `password_requested_at`,"
                    + " `roles`, `note`, `adresse`, `gouvernorat`, `num_tel`, "
                    + "`type`, `confirmation`, `image` FROM `fos_user` WHERE email='" + emails + "';";

            ResultSet res = stmt.executeQuery(maRequette);
            System.out.println(" la recuperation des donnees est effectue dans getmembrby email");
            while (res.next()) {

                leResultat.setUsername(res.getString("username"));
                leResultat.setEmail(res.getString("email"));
                leResultat.setPasword(res.getString("password"));
                leResultat.setImage(res.getString("image"));
                leResultat.setAdresse(res.getString("adresse"));
                leResultat.setRole(res.getString("roles"));
                leResultat.setNum_tel(res.getInt("num_tel"));
                leResultat.setId(res.getInt("id"));
                /*et la suite ***/
            }
        } catch (SQLException houssem_marnissi) {
            System.out.println("erreur lors de l'exxecution de la requete de la getmembreby email \n");
            System.out.println(houssem_marnissi.getMessage());

        }
        System.out.println("c'est l'utilisateur recuperer par getemembreBy id" + leResultat.getId());
        return leResultat;
    }

    public ArrayList<Userr> selectAll(int id) {
        ArrayList<Userr> le = new ArrayList();
        try {
            String maRequette = "SELECT `id`, `username`, "
                    + "`username_canonical`, `email`, `email_canonical`, "
                    + "`enabled`, `salt`, `password`, `last_login`,"
                    + " `confirmation_token`, `password_requested_at`,"
                    + " `roles`, `note`, `adresse`, `gouvernorat`, `num_tel`, "
                    + "`type`, `confirmation`, `image` FROM `fos_user` WHERE id<>'" + id + "';";
            PreparedStatement st = conn.prepareStatement(maRequette);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Userr e = new Userr();
                e.setId(res.getInt(1));
                e.setRole(res.getString(12));
                e.setImage(res.getString("image"));
                e.setUsername(res.getString("username"));
                e.setNum_tel(res.getInt("num_tel"));
                e.setEmail(res.getString("email"));
                e.setAdresse(res.getString("adresse"));
                // e.setNote(res.getInt(10));
                //  e.setEtat(res.getInt(11));

                le.add(e);
            }
            return le;
        } catch (SQLException ex) {
            System.out.println("ereuur dans l'aafichage");
            return null;
        }
    }

    public int login(String emails, String paswords) {
        Userr leResultat1 = new Userr();
        if (emailExiste(emails) == 1) {
            Userr leResultat2 = getMembreByEmail(emails);

            System.out.println("hadhay hou" + leResultat2);
            System.out.println("email:" + emails + "mot de passe :" + paswords);
            System.out.println(leResultat2.getPasword());
            try {
                String passs = "$2y$13$sLt/hSIea8bcrokcKePzOOeDPiIUjneRaf6iTMI2tR6w981a7lOHq";
                if (BCrypt.checkpw(paswords, leResultat2.getPasword())) {

                    System.out.println("connexion vrais");
                    return 1;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }
        System.out.println("user n'est pas cnnecter");
        return 0;
    }

    public int emailExiste(String email) {
        Userr m1 = new Userr();
        System.out.println("aaaaaaaaaaa");
        System.out.println(m1);
        Userr m2 = getMembreByEmail(email);
        System.out.println("bbbbbbbbb");
        System.out.println(m2);
        if (m2.getId() != 0) {
            System.out.println("mawjoud");
            return 1;
        }
        return 0;
    }

    public String getForgetPasword(String emails) {
        Userr leResultat = new Userr();
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        System.out.println("'chest lemail" + emails + "aa");

        // execution de la requette
        try {
            // preparation de la requette
            String maRequette = "SELECT `pasword` FROM `membre` WHERE `email`='" + emails + "'";

            ResultSet res = stmt.executeQuery(maRequette);
            System.out.println(" la recuperation des donnees est effectue dans getmembrby email");
            while (res.next()) {
                leResultat.setUsername(res.getString("username"));
                leResultat.setEmail(res.getString("email"));
                leResultat.setPasword(res.getString("password"));
                leResultat.setImage(res.getString("image"));
                leResultat.setAdresse(res.getString("adresse"));
                leResultat.setRole(res.getString("role"));
                leResultat.setNum_tel(res.getInt("num_tel"));
                leResultat.setId(res.getInt("id"));

                /*et la suite ***/
                return leResultat.getPasword();
            }
        } catch (SQLException houssem_marnissi) {
            System.out.println("erreur lors de l'exxecution de la requete de la getmembreby email \n");
            System.out.println(houssem_marnissi.getMessage());

        }
        return null;

    }

}
