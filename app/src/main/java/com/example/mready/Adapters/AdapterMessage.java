package com.example.mready.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mready.Clase.GetMessage;
import com.example.mready.R;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterMessage extends ArrayAdapter<GetMessage> {

    private Context myContext;
    private int resourceID;
    private LayoutInflater inflater;

    private TextView adapterUserName;
    private TextView adapterMessage;
    private TextView adapterDate;

    public AdapterMessage(@NonNull Context context, int resource, @NonNull List<GetMessage> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.myContext = context;
        this.resourceID = resource;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = inflater.inflate(resourceID, parent, false);

        adapterUserName = view.findViewById(R.id.adapter_username);
        adapterMessage = view.findViewById(R.id.adapter_message);
        adapterDate = view.findViewById(R.id.adapter_date);

        if(getItem(position) != null){
            if(getItem(position).getDisplay_name()==null){
                adapterUserName.setText("Anonymous");
                adapterUserName.setTextColor(Color.YELLOW);
            }else{
                adapterUserName.setText(getItem(position).getDisplay_name());
            }
            adapterMessage.setText(getItem(position).getMessage());
            adapterDate.setText(getItem(position).getCreated_at());
        }

        return view;
    }
}
