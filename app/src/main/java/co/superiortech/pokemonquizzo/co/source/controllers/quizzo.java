package co.superiortech.pokemonquizzo.co.source.controllers;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.PictureDrawable;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import co.superiortech.pokemonquizzo.R;
import co.superiortech.pokemonquizzo.co.source.adapters.CardItemAdapter;
import co.superiortech.pokemonquizzo.co.source.entities.pokeInfo;
import co.superiortech.pokemonquizzo.co.source.enums.Regions;
import co.superiortech.pokemonquizzo.co.source.json.parser;

public class quizzo extends AppCompatActivity {

    protected EditText txtEntry;
    private GridView grdPokemonEntries;
    private long initialTime = (12 * 60 * 1000);// - 10000L;
    private CountDownTimer timer;
    private String remainingTime;
    private ArrayList<pokeInfo> pokedex = null;
    private ArrayList<pokeInfo> correctPokemons = null;
    private int pokedexTotal;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.done) {
            gameUp("Game Over!");
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzo);

        this.correctPokemons = new ArrayList<>();
        this.txtEntry = (EditText) findViewById(R.id.txtEntry);
        this.grdPokemonEntries = (GridView) findViewById(R.id.lstPokemonEntries);
        final CardItemAdapter adapter = new CardItemAdapter(quizzo.this, R.layout.layout_regions_row, this.correctPokemons);


        grdPokemonEntries.setAdapter(adapter);
        this.getPokedex();
        txtEntry.setHint(correctPokemons.size() + "/" + pokedexTotal);

        txtEntry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean result = false;
                int index = -1;
                String entryText = txtEntry.getText().toString();
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if ((index = pokemonExists(entryText, pokedex)) != -1) {
                        correctPokemons.add(pokedex.remove(index));
                        adapter.notifyDataSetChanged();
                        //addMillisToCounter();
                        txtEntry.setText("");
                        txtEntry.setHint(correctPokemons.size() + "/" + pokedexTotal);
                        txtEntry.setTextColor(Color.BLACK);
                        grdPokemonEntries.post(new Runnable() {
                            @Override
                            public void run() {
                                grdPokemonEntries.smoothScrollToPosition(correctPokemons.size() - 1);
                            }
                        });
                        if (pokedex.size() < 1) {
                            //algo tiene que hacer cuando el usuario gane
                        }
                    } else if ((index = pokemonExists(entryText, quizzo.this.correctPokemons)) != -1) {
                        String correctName = quizzo.this.correctPokemons.get(index).getName();
                        Snackbar.make(v, correctName + " is already on the list!", Snackbar.LENGTH_SHORT).show();
                        txtEntry.setText("");

                    } else {
                        Snackbar.make(v, "Not found!", Snackbar.LENGTH_SHORT).show();
                        txtEntry.setText("");
                    }

                    result = true;
                }

                return result;
            }
        });
        this.generateTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        /**
         * for final version
         */
        //this.finish();

        timer.cancel();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.addMillisToCounter();
    }

    //DEPRECATED

    private void addMillisToCounter() {
        if (this.timer != null) {
            this.timer.cancel();
        }
        this.generateTimer();
    }


    private void generateTimer() {
        timer = new CountDownTimer(this.initialTime, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int mins = (int) Math.floor(millisUntilFinished / 60000);
                int seconds = (int) (millisUntilFinished / 1000) - (mins * 60);
                remainingTime = "Time Remaining: " + mins + ":" + (seconds < 10 ? "0" : "") + seconds;

                setTitle(remainingTime);
                initialTime = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                gameUp("Time is Up!");
            }
        };
        timer.start();
    }

    private void getPokedex() {
        if (this.pokedex == null) {
            this.pokedex = parser.getPokedex(Regions.KANTO, getAssets());
            this.pokedexTotal = this.pokedex.size();
        }
    }

    private int pokemonExists(String pokeName, ArrayList<pokeInfo> cList) {
        int result = -1;
        int cont = 0;

        for (pokeInfo tmp : cList) {
            cont++;
            if (tmp.getName().equalsIgnoreCase(pokeName) || tmp.getAlternative().equalsIgnoreCase(pokeName)) {
                result = cont - 1;
                break;
            }
        }
        return result;
    }

    private void gameUp(String message) {
        if(this.timer != null){
            this.timer.cancel();
        }
        setTitle(message);
        txtEntry.setText("");
        txtEntry.setEnabled(false);
        new AlertDialog.Builder(quizzo.this).setMessage("You got:\n" + correctPokemons.size() + " out of " + pokedexTotal).setTitle(message).setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                quizzo.this.finish();
            }
        }).create().show();
    }
}
