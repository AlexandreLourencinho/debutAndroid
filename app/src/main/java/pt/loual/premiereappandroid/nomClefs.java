package pt.loual.premiereappandroid;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pt.loual.premiereappandroid.sqlite.BddSql;
import pt.loual.premiereappandroid.sqlite.Clefs;

public class nomClefs extends AppCompatActivity {

    private TextView clefSv;
    private EditText chmpKey;
    private Button boutonSauver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nom_clefs);
        clefSv = (TextView) findViewById(R.id.clef);
        boutonSauver = (Button) findViewById(R.id.boutonSauverNomClef);
        chmpKey = (EditText) findViewById(R.id.chmpKey);





        Intent intent = getIntent();
        String clef = intent.getStringExtra(TestPageChamps.EXTRA_MESSAGE_CLEF);

        clefSv.setText(clef);


        boutonSauver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clefSv.getText().toString().equals("")){
                    AlertDialog.Builder alert = new AlertDialog.Builder(nomClefs.this);
                    alert.setMessage("Il y a eu un problème lors de la récupération de la clef.");
                    redirection();
                }else{
                    try {
                        BddSql db = new BddSql(nomClefs.this);
                        Clefs clef = new Clefs();
                        clef.setClef_nom(chmpKey.getText().toString());
                        clef.setClef_contenu(clefSv.getText().toString());
                        db.ajouter(clef);
                        redirection();
                    } catch (Exception e) {
                        AlertDialog.Builder alertAjout = new AlertDialog.Builder(nomClefs.this);
                        alertAjout.setTitle("Problème lors de l'enreistrement de la clef");
                    }

                }
            }
        });


    }

    public void redirection(){
        Intent intent = new Intent(this,TestPageChamps.class);
        startActivity(intent);
    }



}