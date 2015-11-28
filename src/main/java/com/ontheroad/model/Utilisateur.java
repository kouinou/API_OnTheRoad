package com.ontheroad.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Kouinou on 28/11/2015.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Utilisateur {

    private int id_utilisateur;
    private String auth_token;
    private char sexe;
    private String prenom;
    private String nom;
    private String mail;
    private int telephone;
    private String user;
    private String password;
    private int is_premium;
    private int num_rue;
    private String libelle_rue;
    private int code_postal;
    private String libelle_ville;
    private float latitude;
    private float longitude;

    public Utilisateur(){

    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_premium() {
        return is_premium;
    }

    public void setIs_premium(int is_premium) {
        this.is_premium = is_premium;
    }

    public int getNum_rue() {
        return num_rue;
    }

    public void setNum_rue(int num_rue) {
        this.num_rue = num_rue;
    }

    public String getLibelle_rue() {
        return libelle_rue;
    }

    public void setLibelle_rue(String libelle_rue) {
        this.libelle_rue = libelle_rue;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public String getLibelle_ville() {
        return libelle_ville;
    }

    public void setLibelle_ville(String libelle_ville) {
        this.libelle_ville = libelle_ville;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public static Builder buider(){
        return new Builder();
    }

    public static class Builder{

        private Utilisateur utilisateur;

        public Builder(){
            utilisateur = new Utilisateur();
        }

        public Builder setId_utilisateur(int id_utilisateur) {
            utilisateur.id_utilisateur = id_utilisateur;
            return this;
        }

        public Builder setSexe(char sexe) {
            utilisateur.sexe = sexe;
            return this;
        }

        public Builder setPrenom(String prenom) {
            utilisateur.prenom = prenom;
            return this;
        }

        public Builder setNom(String nom) {
            utilisateur.nom = nom;
            return this;
        }

        public Builder setMail(String mail) {
            utilisateur.mail = mail;
            return this;
        }

        public Builder setTelephone(int telephone) {
            utilisateur.telephone = telephone;
            return this;
        }

        public Builder setUser(String user) {
            utilisateur.user = user;
            return this;
        }

        public Builder setPassword(String password) {
            utilisateur.password = password;
            return this;
        }

        public Builder setIs_premium(int is_premium) {
            utilisateur.is_premium = is_premium;
            return this;
        }

        public Builder setNum_rue(int num_rue) {
            utilisateur.num_rue = num_rue;
            return this;
        }

        public Builder setLibelle_rue(String libelle_rue) {
            utilisateur.libelle_rue = libelle_rue;
            return this;
        }

        public Builder setCode_postal(int code_postal) {
            utilisateur.code_postal = code_postal;
            return this;
        }

        public Builder setLibelle_ville(String libelle_ville) {
            utilisateur.libelle_ville = libelle_ville;
            return this;
        }

        public Builder setLatitude(float latitude) {
            utilisateur.latitude = latitude;
            return this;
        }

        public Builder setLongitude(float longitude) {
            utilisateur.longitude = longitude;
            return this;
        }

        public Utilisateur build() {
            return utilisateur;
        }
    }


}
