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
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CLEF + "(" + COLONNE_CLEF_ID+" INTEGER PRIMARY KEY," + COLONNE_CLEF_NOM + " TEXT," + COLONNE_CLEF_CONTENU
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
            Clefs clef1 = new Clefs(1,"Première clef", genclef.randomKey());
            this.ajouter(clef1);
        }

    }

    @Override
    public void ajouter(Clefs clef) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeurs = new ContentValues();

        valeurs.put(COLONNE_CLEF_NOM,clef.getClef_nom());

        db.insert(TABLE_CLEF,null,valeurs);
        db.close();
    }

    @Override
    public boolean modifier(Clefs o) {
        return false;
    }

    @Override
    public boolean supprimer(int id) {
        return false;
    }

    @Override
    public Clefs trouverUn(int id) {
        return null;
    }

    @Override
    public ArrayList<Clefs> liste() {
        return null;
    }

    @Override
    public int compteur() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CLEF,null);
        int compte = cursor.getCount();
        cursor.close();
        return compte;
    }
}
