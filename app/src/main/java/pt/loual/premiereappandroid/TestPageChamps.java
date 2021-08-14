package pt.loual.premiereappandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import pt.loual.premiereappandroid.model.ManaBox;
import pt.loual.premiereappandroid.sqlite.BddSql;
import pt.loual.premiereappandroid.sqlite.Clefs;
import pt.loual.premiereappandroid.tools.GenClef;
import pt.loual.premiereappandroid.tools.Transcodeur;

public class TestPageChamps extends AppCompatActivity {

    private EditText champ1;
    private EditText champ2;
    private EditText champclef;
    private Button boutonGen;
    private Button boutonSauver;
    private Spinner spinnerClefs;
    private ArrayList<Clefs> listeClefs;
    private ArrayAdapter<Clefs> listeClefsAdapt;

    private Transcodeur trans;

    public static final String EXTRA_MESSAGE_CLEF="pt.alexandre.monapp.MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_page_champs);

        champ1 = (EditText) findViewById(R.id.champ1);
        champ2 = (EditText) findViewById(R.id.champ2);
        champclef = (EditText) findViewById(R.id.champclef);
        boutonGen = findViewById(R.id.boutonGen);
        boutonSauver = findViewById(R.id.boutonSauver);
        spinnerClefs = findViewById(R.id.spinnerClefs);

        BddSql db = new BddSql(this);
//        db.supprimerTout(db.liste());
        db.creerDefaut();
        listeClefs = db.liste();
        listeClefsAdapt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1,this.listeClefs);
        this.spinnerClefs.setAdapter(listeClefsAdapt);
        registerForContextMenu(this.spinnerClefs);
        spinnerClefs.setSelection(0);



        champ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (champclef.getText().toString().equals("")) {
                    champ1.setEnabled(false);
                    champ2.setEnabled(false);
                } else {
                    champ1.setEnabled(true);
                    champ2.setEnabled(true);
                }
            }
        });
        champ1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!champclef.getText().toString().equals("")) {
                    if (champ1.isFocused()) {
                        trans = new Transcodeur(champclef.getText().toString());
                        champ2.setText(trans.encode(champ1.getText().toString()));
                    }
                } else {
                    champ1.setEnabled(false);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        champclef.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (champclef.getText().toString().equals("")) {
                    champ1.setEnabled(false);
                    champ2.setEnabled(false);
                } else {
                    champ1.setEnabled(true);
                    champ2.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        champ2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!champclef.getText().toString().equals("")) {
                    if (champ2.isFocused()) {
                        trans = new Transcodeur(champclef.getText().toString());
                        champ1.setText(trans.decode(champ2.getText().toString()));
                    }

                } else {


                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        spinnerClefs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,int position, long id){
                if(position!=-1) {
                    Clefs clef = (Clefs) parentView.getItemAtPosition(position);
                    champclef.setText(clef.getClef_contenu());
                }else{
                    champclef.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//                champclef.setText("");
            }
        });

        boutonGen.setOnClickListener(this::genererClef);
        boutonSauver.setOnClickListener(this::sauverClef);
//        champclef.setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void genererClef(View view) {
        GenClef gen = new GenClef();
        champclef = (EditText) findViewById(R.id.champclef);
        champclef.setText(ManaBox.encrypt(gen.randomKey()));
    }

    public void sauverClef(View view) {
        if (champclef.getText().toString().equals("")) {
            AlertDialog.Builder consrt = new AlertDialog.Builder(TestPageChamps.this);
            consrt.setCancelable(false);
            consrt.setMessage("Vous devez d'abord avoir une clef. Si vous n'en avez pas, générez en une à l'aide du bouton \" générer \" ");
//            consrt.setCancelable(true);
            consrt.setPositiveButton("ok", (dialog, which) -> dialog.dismiss());
            consrt.show();
        }else{
            try {
                Intent intent = new Intent(this,nomClefs.class);
                String keyRecup = champclef.getText().toString();

                intent.putExtra(EXTRA_MESSAGE_CLEF,keyRecup);
                startActivity(intent);
//                db.ajouter(clefs);
//                AlertDialog.Builder consrt = new AlertDialog.Builder(TestPageChamps.this);
//                consrt.setMessage("La clef à été enregistrée ").show();
            } catch (Exception e) {
                AlertDialog.Builder consrt = new AlertDialog.Builder(TestPageChamps.this);
                consrt.setMessage("Un problème est survenu lors de l'enregistrement de la clef ").show();
            }

        }
    }


}