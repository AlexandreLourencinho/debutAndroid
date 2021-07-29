package pt.loual.premiereappandroid;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;

public class MainActivity extends AppCompatActivity
{

    public static final String EXTRA_MESSAGE="pt.alexandre.monapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void envoyerMessage(View view)
    {

        Intent intent = new Intent(this,DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName3);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }


    public void testonsSa(View view)
    {
        TextView msgErreur = (TextView) findViewById(R.id.msgErreur);
        CheckBox chk = (CheckBox) findViewById(R.id.checkBox2);
        if(chk.isChecked()){
            msgErreur.setText("");
            Intent intent = new Intent(this,TestNouvellePage.class);
            EditText editText = (EditText) findViewById(R.id.editTextTextPersonName5);
            String message = editText.getText().toString();
            intent.putExtra(EXTRA_MESSAGE,message);
            startActivity(intent);
        }else{

            msgErreur.setText("Veuillez cocher la checkbox");
            msgErreur.setTextColor(Color.RED);
        }
    }

    public void testChamps(View view)
    {
        Intent intent = new Intent(this,TestPageChamps.class);
        startActivity(intent);

    }



}