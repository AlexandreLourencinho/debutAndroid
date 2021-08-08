package pt.loual.premiereappandroid;



import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    public static final String EXTRA_MESSAGE="pt.alexandre.monapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);
        Animation topAnim = AnimationUtils.loadAnimation(this,R.anim.anim_logo);
        ImageView logo = findViewById(R.id.imageView);
        logo.setAnimation(topAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,TestPageChamps.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }

//    public void envoyerMessage(View view)
//    {
//
//        Intent intent = new Intent(this,DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName3);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE,message);
//        startActivity(intent);
//    }
//
//
//    public void testonsSa(View view)
//    {
//        TextView msgErreur = (TextView) findViewById(R.id.msgErreur);
//        CheckBox chk = (CheckBox) findViewById(R.id.checkBox2);
//        if(chk.isChecked()){
//            msgErreur.setText("");
//            Intent intent = new Intent(this,TestNouvellePage.class);
//            EditText editText = (EditText) findViewById(R.id.editTextTextPersonName5);
//            String message = editText.getText().toString();
//            intent.putExtra(EXTRA_MESSAGE,message);
//            startActivity(intent);
//        }else{
//
//            msgErreur.setText(R.string.chk_bx);
//            msgErreur.setTextColor(Color.RED);
//        }
//    }
//
//    public void testChamps(View view)
//    {
//        Intent intent = new Intent(this,TestPageChamps.class);
//        startActivity(intent);
//
//    }



}