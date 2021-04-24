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

import com.example.mready.MainActivity;
import com.example.mready.R;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private TextView registerText;
    private Button loginButton;
    private EditText loginUserName;
    private EditText loginPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        initComponents(view);
        addFunctionalities();

        return view;
    }

    private void initComponents(View view){
        registerText = view.findViewById(R.id.login_register_text);
        loginButton = view.findViewById(R.id.login_button_login);
        loginUserName = view.findViewById(R.id.login_name);
        loginPassword = view.findViewById(R.id.login_password);
    }

    private void addFunctionalities(){

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment = new RegisterFragment();
                 Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,
                        selectedFragment).commit();
            }
        });

    }
}