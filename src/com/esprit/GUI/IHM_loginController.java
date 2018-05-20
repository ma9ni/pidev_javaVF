/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.SERVICE.UserController;
import com.esprit.entities.Userr;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class IHM_loginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Label erreur;
    @FXML
    private TextField pasword;

    public static Userr membre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void inscription(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_inscription.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void annuler(ActionEvent event) {

        System.exit(0);
    }

    @FXML
    private void connecter(ActionEvent event) throws IOException {
        UserController cm = new UserController();

        if (cm.login(email.getText(), pasword.getText()) == 0) {
            System.out.println(email.getText());
            erreur.setText("login ou mot de passe incorrecte");

        } else {
            membre = cm.getMembreByEmail(email.getText());

            System.out.println("je seuiw" + membre);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accueil_IHM.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(new Scene(root1));
            stage.show();

            Notifications.create()
                    .title("Succée")
                    .text("bienvenue " + membre.getUsername() + " vous etes connecter")
                    .showInformation();

        }
    }

    @FXML
    private void inscription(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_inscription.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void ConnecterFB(ActionEvent event) throws IOException {
        /*  String FBToken ="EAANmZB5FSZAzoBAPxLXXrD5FKR6WojrVpMOeA8SVwBsgfiOIO3BXvrScQl91I3krqkpJWDAGEqE7usFgLxqGleM2bV7bmgAWy4qmhap9ZAxBDcMpqefzaodo7MsHm7T9aZCIZCEeQOdvdQZC4MzYcEdSQ1onefqhhTqBKICEGeRDwwEZAQSqBjpbJXrryHvMCYZD";
        System.out.println("1");

        FacebookClient fbclient = new DefaultFacebookClient(FBToken);
          AccessToken tokenaces =fbclient.obtainAppAccessToken("957655591053114", "31fe9a6ef2e5611feb4a1562ef88ce68");
          System.out.println(tokenaces.getAccessToken());
          System.out.println(tokenaces.getExpires());

                  System.out.println("2");
    User me = fbclient.fetchObject("me", User.class);
        System.out.println("3");
        System.out.println(me.getName());
        System.out.println("4");
        System.out.println(me.getBirthdayAsDate());
        System.out.println(me.getLanguages());
         */

        // String appId = "957655591053114";
        //  String Domaine = "http://google.com";
        //String app_secret = "31fe9a6ef2e5611feb4a1562ef88ce68";
        /*   String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+Domaine+"&scope=user_about_me,"
                + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
                + "user_events,user_photos,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
                + "manage_notifications,manage_pages,publish_actions,read_friendlists,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";

    //  String authUrl = "https://graph.facebook.com/endpoint?key=value&access_token=197825167637579|CsWptVWK9XCc1IMlB4MGq9suxgo";
     String authUrl="https://graph.facebook.com/oauth/authorize?"
             + "type=user_agent&client_id="+appId+"&redirect_uri="+Domaine+"&scope"
             + "=user_about_me,user_actions.books,user_actions.fitness,"
             + "user_actions.music,user_actions.news,user_actions.video,"
             + "user_activities,user_birthday,user_education_history,"
             + "user_events,user_friends,user_games_activity,user_groups,"
             + "user_hometown,user_interests,user_likes,user_location,"
             + "user_photos,user_relationship_details,user_relationships,"
             + "user_religion_politics,user_status,user_tagged_places,"
             + "user_videos,user_website,user_work_history,ads_management,"
             + "ads_read,email,manage_notifications,manage_pages,"
             + "publish_actions,read_friendlists,read_insights,read_mailbox,"
             + "read_page_mailboxes,read_stream,rsvp_event";
        System.setProperty("webdirver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        while(true){
            System.out.println("1");
            if(!driver.getCurrentUrl().contains("facebook.com")){
                System.out.println("2");
            String url = driver.getCurrentUrl();
                System.out.println("3");
            accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                System.out.println("4");

            driver.quit();
                System.out.println("5");
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                System.out.println("6");
             User me = fbClient.fetchObject("me", User.class);
                System.out.println("7");

                System.out.println("aaaaaaaaaa"+me.getName());

            }

        }*/
        //avant dernier
        /*  String domain = "http://radixcode.com";
        String appId = "957655591053114";

        String authUrl = "https://www.facebook.com/v2.12/dialog/oauth?" +
"  client_id=957655591053114" +
"  &redirect_uri=https://www.facebook.com/connect/login_success.html" +
"    access_token=EAANmZB5FSZAzoBAPlc1mFeygu1xUSHBhs7hbtvmFNfQ844cGMPRf8yUgtPZBlZCprWQHALGRpsm9qZBKzjo4INTShyVxbFiEpZADRpNmEc4iBAZCWgdXK8s9PW1wyB4HZCLZAlDGelhr1zJUJlHWwQLiQ6EnM0Pq3rZC1v4lYntgdCigZDZD&scope=email,public_profile,user_birthday";
        System.setProperty("webdirver.chrome.driver", "chromedriver.exe");
        System.out.println("0");
        WebDriver driver = new ChromeDriver();
        System.out.println("0.1");
        driver.get(authUrl);

        System.out.println("0.2");
        String accessToken;

        boolean b = true;
        while(b){

            if(driver.getCurrentUrl().contains("https://www.facebook.com/connect/login_success.html")){
                System.out.println("1");
            String url = driver.getCurrentUrl();
                System.out.println("2");
            accessToken = "EAANmZB5FSZAzoBAPlc1mFeygu1xUSHBhs7hbtvmFNfQ844cGMPRf8yUgtPZBlZCprWQHALGRpsm9qZBKzjo4INTShyVxbFiEpZADRpNmEc4iBAZCWgdXK8s9PW1wyB4HZCLZAlDGelhr1zJUJlHWwQLiQ6EnM0Pq3rZC1v4lYntgdCigZDZD";
                System.out.println("3");

           //driver.quit();
                System.out.println("4");

                FacebookClient fbClient = new DefaultFacebookClient(accessToken,Version.VERSION_2_12);
                System.out.println("5");
                User user = fbClient.fetchObject("me",User.class);
                System.out.println("6");

                System.out.println(user.getEmail());
                System.out.println(user.getName());

           b=false;
            }


    }*/
        String domain = "http://radixcode.com";
        String appId = "1536537523327125";

        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=user_about_me,email,user_photos,user_birthday";

        //+
        User user = null;
        System.setProperty("webdirver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        Boolean test = true;
        while (test) {

            if (!driver.getCurrentUrl().contains("facebook.com")) {
                test = false;
                String url = driver.getCurrentUrl();
                System.out.println(url);
                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                //System.out.println(accessToken);
                driver.quit();

                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                user = fbClient.fetchObject("me", User.class, Parameter.with("fields", "name,email,first_name,last_name,gender"));
                // System.out.println(user.getEmail()+user.getGender());
                System.out.println(user.getGender());
                System.out.println(user.getEmail());
                System.out.println(user.getBirthday());
                System.out.println(user.getFirstName());
                System.out.println(user.getLastName());
                System.out.println(user.getPicture());
                UserController cm = new UserController();
                membre = cm.getMembreByEmail(user.getEmail());

                if (cm.emailExiste(user.getEmail()) == 1) {

                    System.out.println("je seuiw" + membre);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accueil_IHM.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    stage.setScene(new Scene(root1));
                    stage.show();

                    Notifications.create()
                            .title("Succée")
                            .text("bienvenue " + membre.getUsername() + " vous etes connecter")
                            .showInformation();

                } else {
                    cm.ajouterMembre(user.getName() + user.getLastName(), user.getEmail(), "", 0, "", "");

                    System.out.println("je seuiw" + membre);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accueil_IHM.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    stage.setScene(new Scene(root1));
                    stage.show();

                    Notifications.create()
                            .title("Succée")
                            .text("bienvenue " + membre.getUsername() + "  vous étes inscri avec succés vous pouvez saisir vos coordonnés")
                            .showInformation();

                }
            }

        }

    }

    @FXML
    private void forget(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_Forget_Pasword.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    private void forget(MouseDragEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_Forget_Pasword.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void onclickhome(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_Notre_Accueil.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(IHM_loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
