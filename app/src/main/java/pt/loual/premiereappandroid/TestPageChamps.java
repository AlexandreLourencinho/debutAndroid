package pt.loual.premiereappandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import pt.loual.premiereappandroid.model.ManaBox;
import pt.loual.premiereappandroid.tools.GenClef;
import pt.loual.premiereappandroid.tools.Transcodeur;

public class TestPageChamps extends AppCompatActivity {

    private EditText champ1;
    private EditText champ2;
    private EditText champclef;

    private Transcodeur trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_page_champs);

        champ1=(EditText) findViewById(R.id.champ1);
        champ2 = (EditText) findViewById(R.id.champ2);
        champclef = (EditText)findViewById(R.id.champclef);
        champ1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!champclef.getText().toString().equals("")){
                    trans = new Transcodeur(champclef.getText().toString());
                    champ2.setText(trans.encode(champ1.getText().toString()));
                }else{

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        champclef.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(champclef.getText().toString().equals(""))
                {
                    champ1.setEnabled(false);
                    champ2.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });





    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void genererClef(View view)
    {
        GenClef gen = new GenClef();
        champclef = (EditText) findViewById(R.id.champclef);
        champclef.setText(ManaBox.encrypt(gen.randomKey()));
    }









}