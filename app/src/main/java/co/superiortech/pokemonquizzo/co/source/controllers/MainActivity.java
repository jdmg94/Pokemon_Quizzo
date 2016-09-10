package co.superiortech.pokemonquizzo.co.source.controllers;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.superiortech.pokemonquizzo.R;

public class MainActivity extends AppCompatActivity {

    private Button btnStartQuiz;
    private Button btnOtherRegions;
    private Button btnAbout;
    private TextView lblMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartQuiz    = (Button) findViewById(R.id.btnStartQuiz);
        btnOtherRegions = (Button) findViewById(R.id.btnOtherRegions);
        btnAbout        = (Button) findViewById(R.id.btnAbout);
        lblMain         = (TextView) findViewById(R.id.lblMain);

        lblMain.setTypeface(Typeface.createFromAsset(getResources().getAssets(),"fonts/Pokemon Hollow.ttf"));

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openQuizzo = new Intent(getBaseContext(),quizzo.class);
                startActivity(openQuizzo);

            }
        });

        btnOtherRegions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openRegions = new Intent(MainActivity.this,regionsActivity.class);
                startActivity(openRegions);
            }
        });


    }
}
