package com.example.mready.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.example.mready.Adapters.AdapterMessage;
import com.example.mready.Clase.GetMessage;
import com.example.mready.Clase.MyData;
import com.example.mready.Clase.PostData;
import com.example.mready.Clase.PostMessage;
import com.example.mready.Http.JsonPlaceHolder;
import com.example.mready.R;

import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewPostFragment extends Fragment {

    private ImageView newPostBackPress;
    private EditText newPostText;
    private Button newPostButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_post, container, false);

        initComponents(view);

        addFunctions();

        return view;
    }

    private void initComponents(View view) {
        newPostBackPress = view.findViewById(R.id.new_post_back_press);
        newPostText = view.findViewById(R.id.new_post_text);
        newPostButton = view.findViewById(R.id.new_post_button);
    }

    private void addFunctions() {
        newPostBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment = new FeedFragment();
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,
                        selectedFragment).commit();
            }
        });

        newPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .client(new OkHttpClient.Builder().addInterceptor(new ChuckerInterceptor.Builder(requireContext()).build()).build())
                        .baseUrl("https://intern-hackathon.mready.net/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

                if (newPostText.getText() != null && !newPostText.getText().toString().equals("")) {

                    PostMessage postMessage = new PostMessage(newPostText.getText().toString());

                    Call<PostData> call = jsonPlaceHolder.createMessage(postMessage.getMessage());

                    Toast.makeText(getContext(), postMessage.toString(), Toast.LENGTH_SHORT).show();

                    call.enqueue(new Callback<PostData>() {
                        @Override
                        public void onResponse(Call<PostData> call, Response<PostData> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(getContext(), response.body().toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<PostData> call, Throwable t) {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}