package co.superiortech.pokemonquizzo.co.source.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import co.superiortech.pokemonquizzo.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.superiortech.pokemonquizzo.co.source.entities.pokeInfo;

/**
 * Created by JoseMunoz on 9/9/16.
 */
public class CardItemAdapter extends ArrayAdapter{

    private ArrayList<pokeInfo> data;
    private int resourceId;
    private Context context;


    public CardItemAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = (ArrayList<pokeInfo>)objects;
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)this.context).getLayoutInflater();
            convertView = inflater.inflate(this.resourceId,parent,false);
        }
        CardView card = (CardView) convertView.findViewById(R.id.cardObject);
        ImageView cardImage = (ImageView) convertView.findViewById(R.id.cardImage);
        TextView cardTitle = (TextView) convertView.findViewById(R.id.cardTitle);
        pokeInfo pokemon = this.data.get(position);
        String pokeName = pokemon.getName();

        card.setCardBackgroundColor(Color.parseColor(pokemon.getTypes()[0].getValue()));
        cardTitle.setText(pokeName);
        cardImage.setImageBitmap(getPokemonImage(pokemon));

        return convertView;
    }

    private Bitmap getPokemonImage(pokeInfo item) {
        Bitmap resource = null;
        try {
            resource = BitmapFactory.decodeStream((this.context).getAssets().open(item.getImage()));
        }catch(IOException ex){
            System.out.println("not found! "+ex.getMessage());
        }
        return resource;
    }

}
