package com.example.mready.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mready.R;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private ImageView registerBackPress;
    private EditText registerFullName;
    private EditText registerUserName;
    private EditText registerPassword;
    private EditText registerRepeatPassword;
    private Button registerButton;

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
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,
                        selectedFragment).commit();
            }
        });


    }

}