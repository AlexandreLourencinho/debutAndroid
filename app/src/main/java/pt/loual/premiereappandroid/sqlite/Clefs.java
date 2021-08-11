package pt.loual.premiereappandroid.sqlite;

import androidx.annotation.NonNull;

public class Clefs {

    private int clef_id;
    private String clef_nom;
    private String clef_contenu;

    public Clefs()
    {

    }

    public Clefs(int id, String clef_nom, String clef_contenu)
    {
        this.clef_id=id;
        this.clef_nom=clef_nom;
        this.clef_contenu=clef_contenu;
    }

    public Clefs(String clef_nom, String clef_contenu){
        this.clef_nom=clef_nom;
        this.clef_contenu=clef_contenu;
    }

    public void setClef_nom(String clef_nom) {
        this.clef_nom = clef_nom;
    }

    public int getClef_id() {
        return clef_id;
    }

    public String getClef_nom() {
        return clef_nom;
    }

    public String getClef_contenu() {
        return clef_contenu;
    }

    public void setClef_contenu(String clef_contenu) {
        this.clef_contenu = clef_contenu;
    }

    public void setClef_id(int clef_id) {
        this.clef_id = clef_id;
    }

    @NonNull
    @Override
    public String toString(){
        return String.valueOf(this.getClef_nom());
    }
}
