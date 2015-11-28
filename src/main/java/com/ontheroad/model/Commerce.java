package com.ontheroad.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Kouinou on 28/11/2015.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Commerce {

    private String type;
    private String nom;
    private int num_siret;
    private String description;
    private int num_rue;
    private String libelle_rue;
    private int code_postal;
    private String libelle_ville;
    private float latitude;
    private float longitude;
    private String libelle_rep_photos;
    private String libelle_url_logo;

    public Commerce(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNum_siret() {
        return num_siret;
    }

    public void setNum_siret(int num_siret) {
        this.num_siret = num_siret;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLibelle_rep_photos() {
        return libelle_rep_photos;
    }

    public void setLibelle_rep_photos(String libelle_rep_photos) {
        this.libelle_rep_photos = libelle_rep_photos;
    }

    public String getLibelle_url_logo() {
        return libelle_url_logo;
    }

    public void setLibelle_url_logo(String libelle_url_logo) {
        this.libelle_url_logo = libelle_url_logo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Commerce commerce;

        public Builder() {
            commerce = new Commerce();
        }

        public Builder setType(String type) {
            commerce.type = type;
            return this;
        }

        public Builder setNom(String nom) {
            commerce.nom = nom;
            return this;
        }

        public Builder setNum_siret(int num_siret) {
            commerce.num_siret = num_siret;
            return this;
        }

        public Builder setDescription(String description) {
            commerce.description = description;
            return this;
        }

        public Builder setNum_rue(int num_rue) {
            commerce.num_rue = num_rue;
            return this;
        }

        public Builder setLibelle_rue(String libelle_rue) {
            commerce.libelle_rue = libelle_rue;
            return this;
        }

        public Builder setCode_postal(int code_postal) {
            commerce.code_postal = code_postal;
            return this;
        }

        public Builder setLibelle_ville(String libelle_ville) {
            commerce.libelle_ville = libelle_ville;
            return this;
        }

        public Builder setLatitude(float latitude) {
            commerce.latitude = latitude;
            return this;
        }

        public Builder setLongitude(float longitude) {
            commerce.longitude = longitude;
            return this;
        }

        public Builder setLibelle_rep_photos(String libelle_rep_photos) {
            commerce.libelle_rep_photos = libelle_rep_photos;
            return this;
        }

        public Builder setLibelle_url_logo(String libelle_url_logo) {
            commerce.libelle_url_logo = libelle_url_logo;
            return this;
        }

        public Commerce build() {
            return commerce;
        }

    }
}
