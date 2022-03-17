package com.example.projectmain.activity.otp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectmain.R;
import com.example.projectmain.login.SplashLogin;
import com.example.projectmain.response.ResponseLogin;
import com.example.projectmain.rest.RetroServer;
import com.example.projectmain.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtp extends AppCompatActivity {
    private EditText inputCode1, inputCode2, inputCode3, inputCode4, inputCode5, inputCode6;
    private String verificationId;
    private String nik, username, password,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_otp);

        Intent data = getIntent();
        nik         = data.getStringExtra("nik");
        username    = data.getStringExtra("username");
        password    = data.getStringExtra("password");
        phone       = data.getStringExtra("phone");

        Utils.blackIconStatusBar(VerifyOtp.this,R.color.light_Background);
        TextView textMobile = findViewById(R.id.tv_mobile);
        textMobile.setText(String.valueOf(getIntent().getStringExtra("mobile")
        ));

        inputCode1 = findViewById(R.id.et_input_code_1);
        inputCode2 = findViewById(R.id.et_input_code_2);
        inputCode3 = findViewById(R.id.et_input_code_3);
        inputCode4 = findViewById(R.id.et_input_code_4);
        inputCode5 = findViewById(R.id.et_input_code_5);
        inputCode6 = findViewById(R.id.et_input_code_6);

        setupOtpInput();
        final ProgressBar progressBar = findViewById(R.id.progressbar);
        final Button buttonVerify = findViewById(R.id.btn_verify_otp);
        final TextView textViewResend = findViewById(R.id.tv_resend);

        verificationId = getIntent().getStringExtra("verificationId");

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputCode1.getText().toString().trim().isEmpty()
                        || inputCode2.getText().toString().trim().isEmpty()
                        || inputCode3.getText().toString().trim().isEmpty()
                        || inputCode4.getText().toString().trim().isEmpty()
                        || inputCode5.getText().toString().trim().isEmpty()
                        || inputCode6.getText().toString().trim().isEmpty()){
                    Toast.makeText(VerifyOtp.this, "Please Enter Valid Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = inputCode1.getText().toString()+
                        inputCode2.getText().toString()+
                        inputCode3.getText().toString()+
                        inputCode4.getText().toString()+
                        inputCode5.getText().toString()+
                        inputCode6.getText().toString();

                if (verificationId != null){
                    progressBar.setVisibility(View.VISIBLE);
                    buttonVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId,
                            code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    buttonVerify.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()){

                                        Intent intent = new Intent(VerifyOtp.this,SplashLogin.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);
                                        RegisterUser();
                                    }else {
                                        Toast.makeText(VerifyOtp.this, "Invalid Verification Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

        textViewResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        String.format("+62%s", textMobile.getText().toString()),
                        60,
                        TimeUnit.SECONDS,
                        VerifyOtp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                               // buttonGetOTP.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                buttonVerify.setVisibility(View.VISIBLE);
                                Toast.makeText(VerifyOtp.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                buttonVerify.setVisibility(View.VISIBLE);

                                Intent intent = new Intent(VerifyOtp.this, VerifyOtp.class);
                                intent.putExtra("mobile", textMobile.getText().toString());
                                intent.putExtra("verificationId",verificationId);
                                startActivity(intent);


                            }
                        }
                );
            }
        });

    }

    private void setupOtpInput() {
        inputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputCode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void RegisterUser() {
        String userName = "admin";
        String passWord = "1234";
        String base = userName+":"+passWord;
        String authHeader ="Basic " + Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
        final Call<ResponseLogin> responseLoginCall = RetroServer.getClient().register(authHeader,nik,username,password,phone);
        responseLoginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                Toast.makeText(VerifyOtp.this, "Register Successfully ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(VerifyOtp.this, "Error ! " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
