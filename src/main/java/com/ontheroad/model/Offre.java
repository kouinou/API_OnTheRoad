package com.ontheroad.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

/**
 * Created by Kouinou on 28/11/2015.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Offre {

    private Timestamp date_debut;
    private Timestamp date_fin;
    private String promotion;
    private String descritpion;
    private String libelle_rep_photos;

    public Offre() {

    }

    public Timestamp getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Timestamp date_debut) {
        this.date_debut = date_debut;
    }

    public Timestamp getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Timestamp date_fin) {
        this.date_fin = date_fin;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getLibelle_rep_photos() {
        return libelle_rep_photos;
    }

    public void setLibelle_rep_photos(String libelle_rep_photos) {
        this.libelle_rep_photos = libelle_rep_photos;
    }

    public static Builder builder () {
        return new Builder();
    }

    public static class Builder {

        private Offre offre;

        public Builder() {

            offre = new Offre();
        }

        public Builder setDate_debut(Timestamp date_debut) {
            offre.date_debut = date_debut;
            return this;
        }

        public Builder setDate_fin(Timestamp date_fin) {
            offre.date_fin = date_fin;
            return this;
        }

        public Builder setPromotion(String promotion) {
            offre.promotion = promotion;
            return this;
        }

        public Builder setDescritpion(String descritpion) {
            offre.descritpion = descritpion;
            return this;
        }

        public Builder setLibelle_rep_photos(String libelle_rep_photos) {
            offre.libelle_rep_photos = libelle_rep_photos;
            return this;
        }

        public Offre build() {
            return offre;
        }
    }
}
