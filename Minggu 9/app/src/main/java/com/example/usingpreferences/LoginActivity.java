package com.example.usingpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText mViewUser, mViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewUser = findViewById(R.id.et_emailSignIn);
        mViewPassword = findViewById(R.id.et_passwordSignIn);
        mViewPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                razia();
                return true;
            }
            return false;
        });

        findViewById(R.id.button_signinSignIn).setOnClickListener((v) -> {
            razia();
        });

        findViewById(R.id.button_signupSignIn).setOnClickListener((v) -> {
            startActivity(new Intent(getBaseContext(), RegisterActivity.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }
    }

    private void razia() {
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

        if (TextUtils.isEmpty(user)) {
            mViewUser.setError("This field is required!");
            fokus = mViewUser;
            cancel = true;
        } else if (!cekUser(user)) {
            mViewUser.setError("This username is not found!");
        }

        if (TextUtils.isEmpty(password)) {
            mViewPassword.setError("This field is required!");
            fokus = mViewPassword;
            cancel = true;
        } else if (!cekPassword(password)) {
            mViewUser.setError("This username is not found!");
        }

        if (cancel) fokus.requestFocus();
        else masuk();
    }

    private void masuk() {
        Preferences.setLoggedInUser(getBaseContext(),
                Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(), true);
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }

    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    private boolean cekPassword(String password) {
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }
}