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
import com.example.mready.Clase.DataUser;
import com.example.mready.Clase.UserAndToken;
import com.example.mready.Http.JsonPlaceHolder;
import com.example.mready.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragment extends Fragment {

    private ImageView registerBackPress;
    private EditText registerFullName;
    private EditText registerUserName;
    private EditText registerPassword;
    private EditText registerRepeatPassword;
    private Button registerButton;

    private volatile DataUser dataUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        initComponents(view);
        addFunctions();

        return view;
    }


    private void initComponents(View view){
        registerBackPress = view.findViewById(R.id.register_back_press);
        registerFullName = view.findViewById(R.id.register_full_name);
        registerUserName = view.findViewById(R.id.register_username);
        registerPassword = view.findViewById(R.id.register_password);
        registerRepeatPassword = view.findViewById(R.id.register_repeat_password);
        registerButton = view.findViewById(R.id.register_button);
    }

    private void addFunctions(){
        registerBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment = new LoginFragment();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,
                        selectedFragment).commit();
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verifyData()){
                    Retrofit retrofit = new Retrofit.Builder()
                            .client(new OkHttpClient.Builder().addInterceptor(new ChuckerInterceptor.Builder(requireContext()).build()).build())
                            .baseUrl("https://intern-hackathon.mready.net/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

                    Call<DataUser> call = jsonPlaceHolder.registerUser(registerUserName.getText().toString(), registerPassword.getText().toString(), registerFullName.getText().toString());

                    dataUser = new DataUser();

                    call.enqueue(new Callback<DataUser>() {
                        @Override
                        public void onResponse(Call<DataUser> call, Response<DataUser> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            dataUser = response.body();

                            Bundle bundle = new Bundle();
                            bundle.putString("token", dataUser.getData().getToken());

                            Fragment selectedFragment = new FeedFragment();
                            selectedFragment.setArguments(bundle);

                            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,
                                    selectedFragment).commit();

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
        if(registerFullName.getText()==null){
            Toast.makeText(getContext(),"You have to introduce your full name", Toast.LENGTH_SHORT).show();
            return false;
        }else if(registerFullName.getText().toString().equals("")){
            Toast.makeText(getContext(),"You have to introduce your full name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(registerUserName.getText()==null){
            Toast.makeText(getContext(),"You have to introduce your username", Toast.LENGTH_SHORT).show();
            return false;
        }else if(registerUserName.getText().toString().equals("")){
            Toast.makeText(getContext(),"You have to introduce your username", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(registerPassword.getText()==null){
            Toast.makeText(getContext(),"You have to introduce your password", Toast.LENGTH_SHORT).show();
            return false;
        }else if(registerPassword.getText().toString().equals("")){
            Toast.makeText(getContext(),"You have to introduce your password", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(registerRepeatPassword.getText()==null){
            Toast.makeText(getContext(),"You have to re-enter your password", Toast.LENGTH_SHORT).show();
            return false;
        }else if(registerRepeatPassword.getText().toString().equals("")){
            Toast.makeText(getContext(),"You have to re-enter your password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}