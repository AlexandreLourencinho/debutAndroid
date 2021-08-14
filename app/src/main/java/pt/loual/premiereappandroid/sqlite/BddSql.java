package pt.loual.premiereappandroid.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import pt.loual.premiereappandroid.tools.GenClef;


public class BddSql extends SQLiteOpenHelper implements DAOinterface<Clefs>{

    private static final String TAG = "SQLite";

    private static final String NOM_DB = "Stock_Clefs";

    private static final String TABLE_CLEF = "Clefs";

    private static final String COLONNE_CLEF_ID = "clef_id";
    private static final String COLONNE_CLEF_NOM = " clef_nom";
    private static final String COLONNE_CLEF_CONTENU = "clef_contenu";


    public BddSql(Context context) {
        super(context, NOM_DB,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CLEF + "(" + COLONNE_CLEF_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," + COLONNE_CLEF_NOM + " TEXT," + COLONNE_CLEF_CONTENU
        +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NOM_DB);
        onCreate(sqLiteDatabase);
    }

    public void creerDefaut()
    {
        int compte = this.compteur();
        GenClef genclef = new GenClef();
        if(compte==0){
            Clefs clef0 = new Clefs(0,"Selectionnez une clef", "");
                Clefs clef1 = new Clefs("Première clef", genclef.randomKey());
//                Clefs clef2 = new Clefs("Deuxième clef", genclef.randomKey());
                this.ajouter(clef0);
                this.ajouter(clef1);



        }

    }

    @Override
    public void ajouter(Clefs clef) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeurs = new ContentValues();

        valeurs.put(COLONNE_CLEF_NOM,clef.getClef_nom());
        valeurs.put(COLONNE_CLEF_CONTENU,clef.getClef_contenu());
        db.insert(TABLE_CLEF,null,valeurs);
        db.close();
    }

    @Override
    public boolean modifier(Clefs o) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeurs = new ContentValues();
        valeurs.put(COLONNE_CLEF_NOM,o.getClef_nom());
        valeurs.put(COLONNE_CLEF_CONTENU,o.getClef_contenu());

        return db.update(TABLE_CLEF, valeurs, COLONNE_CLEF_ID + "=?", new String[]{String.valueOf(o.getClef_id())}) == 1;
    }

    @Override
    public boolean supprimer(Clefs o) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLEF,COLONNE_CLEF_ID+ "=?",new String[]{String.valueOf(o.getClef_id())});
            db.close();
            return true;
    }

    @Override
    public Clefs trouverUn(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CLEF,new String[]{COLONNE_CLEF_NOM,COLONNE_CLEF_CONTENU}, COLONNE_CLEF_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null && cursor.moveToFirst()){
          cursor.moveToFirst();
            return new Clefs(Integer.parseInt((cursor.getString(0))), cursor.getString(1), cursor.getString(2));
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Clefs> liste() {
        ArrayList<Clefs> listeClefs = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Clefs",null);
        if(cursor.moveToFirst()){
            do{
                Clefs clef = new Clefs();
                clef.setClef_id(cursor.getInt(0));
                clef.setClef_nom(cursor.getString(1));
                clef.setClef_contenu(cursor.getString(2));
                listeClefs.add(clef);
            }while (cursor.moveToNext());
        }
        return listeClefs;
    }

    @Override
    public int compteur() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CLEF,null);
        int compte = cursor.getCount();
        cursor.close();
        return compte;
    }


    public void supprimerTout(ArrayList<Clefs> listeclefs)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLEF,COLONNE_CLEF_ID+ "=?",new String[]{String.valueOf(0)});
        db.close();
    }
}
