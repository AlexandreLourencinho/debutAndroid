package pt.loual.premiereappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class TestPageChamps extends AppCompatActivity {

    private EditText champ1;
    private EditText champ2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_page_champs);

        champ1=(EditText) findViewById(R.id.champ1);
        champ2 = (EditText) findViewById(R.id.champ2);
        champ1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                champ2.setText(champ1.getText());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }









}