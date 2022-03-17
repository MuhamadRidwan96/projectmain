package com.example.projectmain.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectmain.R;
import com.example.projectmain.activity.otp.VerifyOtp;
import com.example.projectmain.model.User;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {
    private EditText et_nik, et_username, et_phone,et_password;
    private User data;
    private String nik, username, password, phone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        et_nik = findViewById(R.id.et_nik_register);
        et_username = findViewById(R.id.et_username_register);
        et_password = findViewById(R.id.et_password_register);
        et_phone = findViewById(R.id.et_phone_register);

        final TextView tv_sign = findViewById(R.id.tv_sign_in);
        final Button btnReg = findViewById(R.id.button_register);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(et_nik.getText().toString()) ||
                        TextUtils.isEmpty(et_username.getText().toString()) ||
                        TextUtils.isEmpty(et_password.getText().toString()) ||
                        TextUtils.isEmpty(et_phone.getText().toString())) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Register.this);
                    dialog.setMessage("Field must be completed");
                    dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else {

                    try {
                        // RegisterUser();
                        sendOtp();
                        //AfterTransaction();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        tv_sign.setOnClickListener(v -> {
            Intent goSign = new Intent(Register.this, SplashLogin.class);
            goSign.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(goSign);
            finish();
        });

    }
    public void sendOtp() {
        if (et_phone.getText().toString().trim().isEmpty()) {
            Toast.makeText(Register.this, "Enter Mobile Phone", Toast.LENGTH_SHORT).show();
            return;
        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                String.format("+62%s", et_phone.getText().toString()),
                60,
                TimeUnit.SECONDS,
                Register.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        getTextField();
                        Intent intent = new Intent(Register.this, VerifyOtp.class);
                        intent.putExtra("mobile", et_phone.getText().toString());
                        intent.putExtra("verificationId", verificationId);
                        intent.putExtra("nik", nik);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        intent.putExtra("phone", phone);
                        startActivity(intent);

                    }
                }
        );
    }
    public void getTextField(){
        nik = et_nik.getText().toString().trim();
        username = et_username.getText().toString().trim();
        password = et_password.getText().toString().trim();
        phone = et_phone.getText().toString().trim();
    }
}
