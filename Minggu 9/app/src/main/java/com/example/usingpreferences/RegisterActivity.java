package com.example.usingpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText mViewUser, mViewPassword, mViewRepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mViewUser = findViewById(R.id.et_emailSignUp);
        mViewPassword = findViewById(R.id.et_passwordSignUp);
        mViewRepassword = findViewById(R.id.et_passwordSignUp2);

        mViewRepassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                validateForm();
                return true;
            }
            return false;
        });

        findViewById(R.id.button_signupSignUp).setOnClickListener(v -> validateForm());
    }

    private void validateForm() {
        mViewUser.setError(null);
        mViewPassword.setError(null);
        mViewRepassword.setError(null);
        View view = null;
        boolean cancel = false;

        String repassword = mViewRepassword.getText().toString();
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

        if (TextUtils.isEmpty(user)) {
            mViewUser.setError("This field is required");
            view = mViewUser;
            cancel = true;
        } else if (checkUser(user)) {
            mViewUser.setError("This Username is already exist");
            view = mViewUser;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            mViewPassword.setError("This field is required");
            view = mViewPassword;
            cancel = true;
        } else if (!checkPassword(password, repassword)) {
            mViewRepassword.setError("This password is incorrect");
            view = mViewRepassword;
            cancel = true;
        }

        if (cancel) {
            view.requestFocus();
        } else {
            Preferences.setRegisteredUser(getBaseContext(), user);
            Preferences.setRegisteredPass(getBaseContext(), password);
            finish();
        }
    }

    private boolean checkPassword(String password, String repassword) {
        return password.equals(repassword);
    }


    private boolean checkUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}
