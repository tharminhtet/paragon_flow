package com.tharminhtet.paragonflow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.tharminhtet.paragonflow.R.id.imageView;

/**
 * Created by Thar Min Htet on 8/5/2017.
 */

public class InputAdapter extends ArrayAdapter<Input>{

    public InputAdapter(Context context, ArrayList<Input> inputs) {
        super(context, 0, inputs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.input_item, parent, false);
        }

        Input currentInput = getItem(position);

        TextView countView = (TextView) listItemView.findViewById(R.id.count_text_view);
        countView.setText(""+(position+1));  //Turning int to string

        TextView staffView = (TextView) listItemView.findViewById(R.id.staff_text_view);
        staffView.setText(currentInput.getStaff());

        TextView serviceView = (TextView) listItemView.findViewById(R.id.service_text_view);
        serviceView.setText(currentInput.getService());

        TextView priceView = (TextView) listItemView.findViewById(R.id.price_text_view);
        priceView.setText(""+currentInput.getPrice());

        return listItemView;
    }
}
