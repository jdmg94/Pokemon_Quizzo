package co.superiortech.pokemonquizzo.co.source.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import co.superiortech.pokemonquizzo.R;
import co.superiortech.pokemonquizzo.co.source.timeHelper.TimeHelper;

public class quizzo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzo);

        Timer tsk = new Timer();
        Date rightNow = new Date();
        Date fiveMins = DateUtils.addMinutes(new Date(),5);
        TimeHelper helper = new TimeHelper(rightNow,fiveMins);
        tsk.scheduleAtFixedRate(helper,0,1000);

    }



}
