package co.superiortech.pokemonquizzo.co.source.controllers;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
//import com.nhaarman.listviewanimations.extras.itemmanipulation.widget.DynamicListView;
import java.util.ArrayList;
import co.superiortech.pokemonquizzo.R;
import co.superiortech.pokemonquizzo.co.source.entities.pokeInfo;
import co.superiortech.pokemonquizzo.co.source.enums.Regions;
import co.superiortech.pokemonquizzo.co.source.json.parser;

public class quizzo extends AppCompatActivity {

    protected EditText txtEntry;
    private ListView lstPokemonEntries;
    private long initialTime = (1*60*1000)-10000L;
    private CountDownTimer timer;
    private String remainingTime;
    private ArrayList<pokeInfo> pokedex = null;
    private ArrayList<String> correctPokemons = null;
    private int pokedexTotal;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.done){
            timer.cancel();
            gameUp();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzo);


        this.correctPokemons = new ArrayList<>();
        this.txtEntry = (EditText) findViewById(R.id.txtEntry);
        this.lstPokemonEntries = (ListView) findViewById(R.id.lstPokemonEntries);

        final ArrayAdapter adapter = new ArrayAdapter<String>(quizzo.this,android.R.layout.simple_list_item_1,this.correctPokemons);

        lstPokemonEntries.setAdapter(adapter);

        txtEntry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean result = false;
                int index = -1;
                System.out.println("algo paso");
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    if((index = pokemonExists(txtEntry.getText().toString())) != -1){
                        correctPokemons.add(pokedex.remove(index).getName());
                        adapter.notifyDataSetChanged();
                        addMillisToCounter();
                        txtEntry.setText("");
                        txtEntry.setTextColor(Color.BLACK);
                    }
                    else{
                        txtEntry.setTextColor(Color.RED);
                    }
                    result = true;
                }

                return  result;
            }
        });
        this.generateTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();

    }

    private void addMillisToCounter(){
        if(this.timer != null){
            this.timer.cancel();
        }
        this.generateTimer();

    }

    private void generateTimer(){
        timer = new CountDownTimer(this.initialTime+10000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int mins = (int) Math.floor(millisUntilFinished/60000);
                int seconds = (int)(millisUntilFinished/1000) - (mins*60);
                remainingTime = "Time Remaining: "+mins+":"+(seconds<10?"0":"")+seconds;
                setTitle(remainingTime);
                initialTime = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                gameUp();
            }
        };
        timer.start();
    }

    private int pokemonExists(String pokeName){
        int result = -1;
        int cont = 0;
        if(this.pokedex == null) {
            this.pokedex = parser.getPokedex(Regions.KANTO, getAssets());
            this.pokedexTotal = this.pokedex.size();
        }

       for(pokeInfo tmp: this.pokedex){
           cont++;
           if(tmp.getName().equals(pokeName)|| tmp.getAlternative().equalsIgnoreCase(pokeName)){
               result = cont-1;
               break;
           }
       }
        return result;
    }

    private void gameUp(){
        setTitle("Time is Up!");
        txtEntry.setText("");
        txtEntry.setEnabled(false);
        new AlertDialog.Builder(quizzo.this).setMessage("You got:\n"+correctPokemons.size()+" out of "+pokedexTotal).setTitle("Time is up!").setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                quizzo.this.finish();
            }
        }).create().show();
    }

}
