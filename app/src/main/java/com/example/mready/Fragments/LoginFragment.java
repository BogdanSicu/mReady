package com.example.mready.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.example.mready.Clase.DataUser;
import com.example.mready.Http.JsonPlaceHolder;
import com.example.mready.MainActivity;
import com.example.mready.R;

import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {

    private TextView registerText;
    private Button loginButton;
    private EditText loginUserName;
    private EditText loginPassword;

    private volatile DataUser dataUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        initComponents(view);
        addFunctions();

        return view;
    }

    private void initComponents(View view){
        registerText = view.findViewById(R.id.login_register_text);
        loginUserName = view.findViewById(R.id.login_name);
        loginPassword = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.login_button_login);
    }

    private void addFunctions(){

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment = new RegisterFragment();
                 requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,
                        selectedFragment).commit();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(verifyData()){

                    Retrofit retrofit = new Retrofit.Builder()
                            .client(new OkHttpClient.Builder().addInterceptor(new ChuckerInterceptor.Builder(requireContext()).build()).build())
                            .baseUrl("https://intern-hackathon.mready.net/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

                    Call<DataUser> call = jsonPlaceHolder.loginUser(loginUserName.getText().toString(), loginPassword.getText().toString());

                    dataUser = new DataUser();

                    call.enqueue(new Callback<DataUser>() {
                        @Override
                        public void onResponse(Call<DataUser> call, Response<DataUser> response) {

                            if (!response.isSuccessful()) {
                                Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }

                            try{
                                dataUser = response.body();
                            }catch (Exception e){
                                dataUser=null;
                            }

                            if(dataUser!=null && dataUser.getData()!=null){
                                Bundle bundle = new Bundle();
                                bundle.putString("token", dataUser.getData().getToken());

                                Fragment selectedFragment = new FeedFragment();
                                selectedFragment.setArguments(bundle);

                                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,
                                        selectedFragment).commit();

                            }else{
                                Toast.makeText(getContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<DataUser> call, Throwable t) {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    private boolean verifyData(){
        if(loginUserName.getText()==null){
            Toast.makeText(getContext(),"You have to introduce your username", Toast.LENGTH_SHORT).show();
            return false;
        }else if(loginUserName.getText().toString().equals("")){
            Toast.makeText(getContext(),"You have to introduce your username", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(loginPassword.getText()==null){
            Toast.makeText(getContext(),"You have to introduce your username", Toast.LENGTH_SHORT).show();
            return false;
        }else if(loginPassword.getText().toString().equals("")){
            Toast.makeText(getContext(),"You have to introduce your password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}