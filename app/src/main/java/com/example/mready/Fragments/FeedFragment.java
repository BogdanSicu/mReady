package com.example.mready.Fragments;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mready.Adapters.AdapterMessage;
import com.example.mready.Clase.DataUser;
import com.example.mready.Clase.GetMessage;
import com.example.mready.Clase.MyData;
import com.example.mready.Clase.UserAndToken;
import com.example.mready.Http.JsonPlaceHolder;
import com.example.mready.MainActivity;
import com.example.mready.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedFragment extends Fragment {

    private ImageView feedBackPress;
    private ListView feedList;
    private Button feedPostButton;
    private LayoutInflater layoutInflater;
    private Bundle bundle;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        layoutInflater = inflater;

        bundle = this.getArguments();
        assert bundle != null;
        token = bundle.getString("token");


        initComponents(view);
        initMessages();
        addFunctions();

        return view;
    }

    private void initComponents(View view){
        feedBackPress = view.findViewById(R.id.feed_back_press);
        feedList = view.findViewById(R.id.feed_list);
        feedPostButton = view.findViewById(R.id.feed_post_button);
    }

    private void addFunctions(){

        feedBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment selectedFragment = new LoginFragment();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,
                        selectedFragment).commit();
            }
        });

        feedPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment selectedFragment = new NewPostFragment();

                Bundle newBundle = new Bundle();
                newBundle.putString("token", token);
                selectedFragment.setArguments(newBundle);

                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,
                        selectedFragment).commit();
            }
        });
    }

    private void initMessages(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://intern-hackathon.mready.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

        Call<MyData>  call = jsonPlaceHolder.getMessages();

        call.enqueue(new Callback<MyData>() {
            @Override
            public void onResponse(Call<MyData> call, Response<MyData> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                MyData myData = response.body();
                ArrayList<GetMessage> lista = new ArrayList<>();
                lista.addAll(myData.getData());
                AdapterMessage adapterMessage = new AdapterMessage(requireContext().getApplicationContext(), R.layout.adapter_messages, lista, layoutInflater);
                feedList.setAdapter(adapterMessage);
//                Toast.makeText(getContext(), myData.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MyData> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}