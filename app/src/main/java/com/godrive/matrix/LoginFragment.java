package com.godrive.matrix;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends OnBoardingBaseFragment {

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);
        submitButton.setText(getString(R.string.login));

        // test database connection
        // DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("message");
        // myRef.setValue("Hello, World!");

        // login the submitButton and register
        submitButton.setText(getString(R.string.login));
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = usernameEditText.getText().toString();
                final String password = new String(Hex.encodeHex(DigestUtils.md5(passwordEditText.getText().toString())));
                database.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(username) && Objects.equals(password, dataSnapshot.child(username).child("user_password").getValue())) {
                            Config.username = username;
                            startActivity(new Intent(getActivity(), ControlPanel.class));
                        } else {
                            Toast.makeText(getActivity(),"Please try to login again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_login;
    }

}

