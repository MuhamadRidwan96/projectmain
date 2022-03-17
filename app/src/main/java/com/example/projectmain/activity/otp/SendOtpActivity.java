package com.example.projectmain.activity.otp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projectmain.R;
import com.example.projectmain.utils.Utils;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOtpActivity extends AppCompatActivity {
    private Button buttonGetOTP;
    private String nik,username, password, full_name, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        final EditText inputMobile = findViewById(R.id.et_send_otp);
        buttonGetOTP = findViewById(R.id.btn_send_otp);
        final ProgressBar progressBar = findViewById(R.id.progressbar);

        Utils.blackIconStatusBar(SendOtpActivity.this,R.color.light_Background);

        /*get value from register activity*/
        Intent data = getIntent();
        nik = data.getStringExtra("nik");
        username = data.getStringExtra("username");
        password = data.getStringExtra("password");
        full_name = data.getStringExtra("full_name");
        phone = data.getStringExtra("phone");
        email = data.getStringExtra("email");

        inputMobile.setText(phone);


        buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputMobile.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SendOtpActivity.this, "Enter Mobile Phone", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                buttonGetOTP.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        String.format("+62%s", inputMobile.getText().toString()),
                        60,
                        TimeUnit.SECONDS,
                        SendOtpActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetOTP.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(SendOtpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetOTP.setVisibility(View.VISIBLE);

                                Intent intent = new Intent(SendOtpActivity.this, VerifyOtp.class);
                                intent.putExtra("mobile", inputMobile.getText().toString());
                                intent.putExtra("verificationId", verificationId);
                                intent.putExtra("nik", nik);
                                intent.putExtra("username", username);
                                intent.putExtra("password", password);
                                intent.putExtra("full_name", full_name);
                                intent.putExtra("phone", phone);
                                intent.putExtra("email", email);
                                startActivity(intent);

                            }
                        }
                );

            }
        });


    }
}