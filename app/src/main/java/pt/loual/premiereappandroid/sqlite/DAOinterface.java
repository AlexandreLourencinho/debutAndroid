package pt.loual.premiereappandroid.sqlite;

import java.util.ArrayList;

public interface DAOinterface<T> {

    public void ajouter(T o);
    public boolean modifier(T o);
    public boolean supprimer(T o);
    public T trouverUn(int id);
    public ArrayList<T> liste();
    public int compteur();


}
