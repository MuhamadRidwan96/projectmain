package com.example.projectmain.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectmain.R;
import com.example.projectmain.activity.MainActivity;
import com.example.projectmain.model.User;
import com.example.projectmain.response.ResponseLogin;
import com.example.projectmain.rest.ApiRequestData;
import com.example.projectmain.rest.RetroServer;
import com.example.projectmain.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashLogin extends AppCompatActivity {
    ImageView bgapp, logo;
    Animation bganim, frombottom, frombottom2 ;
    LinearLayout txtsplash, frmBtm;
    @BindView(R.id.tv_reg)
    TextView tvReg;
    @BindView(R.id.btn_login)
    Button btnLog;
    @BindView(R.id.txtUser)
    EditText txtUser;
    @BindView(R.id.txtPass)
    EditText txtPass;
    private String nik, password;
    private static final String TAG = "SplashLogin";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_login);

        bgapp = (ImageView) findViewById(R.id.bgapp);
        logo = (ImageView) findViewById(R.id.logo);
        txtsplash = (LinearLayout) findViewById(R.id.textsplash);
        frmBtm = (LinearLayout) findViewById(R.id.uinterface);
        sessionManager = new SessionManager(getApplicationContext());
        ButterKnife.bind(this);

        //animation
        bganim = AnimationUtils.loadAnimation(this, R.anim.bganim);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombttom);
        frombottom2 = AnimationUtils.loadAnimation(this,R.anim.frombottom2);
        //fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        bgapp.animate().translationY(-2800).setDuration(2000).setStartDelay(1000);
        txtsplash.animate().translationY(1000).alpha(0).setDuration(2400).setStartDelay(1200);
        frmBtm.setAnimation(frombottom);
        logo.setAnimation(frombottom2);


        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashLogin.this, Register.class);
                startActivity(intent);
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                try {
					onMain();
                    LoginUser();


                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void LoginUser() {
        nik = txtUser.getText().toString().trim();
        password = txtPass.getText().toString().trim();

        String userName = "admin";
        String password = "1234";
        String base     = userName +":"+ password;
        String authHeader ="Basic "+ Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);

        final Call<ResponseLogin>call = RetroServer.getClient().login(authHeader,nik,password);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    sessionManager.createLoginSession(nik,password);

                    Intent intent = new Intent(SplashLogin.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finish();
                    Toast.makeText(SplashLogin.this, "Login Success ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SplashLogin.this, "user not foundS !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(SplashLogin.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean ValidateUser() {
        String user = txtUser.getText().toString().trim();
        if (user.isEmpty()) {
            txtUser.setError("field cannot empty");
            return false;
        } else if (user.length() > 10) {
            txtUser.setError("char must be less than 20");
            return false;
        } else
            txtUser.setError(null);
        return true;
    }

    private boolean ValidatePass() {
        String pass = txtPass.getText().toString().trim();
        if (pass.isEmpty()) {
            txtPass.setError("field cannot empty");
            return false;
        } else
            txtPass.setError(null);
        return true;
    }

    private void onMain() {
        if (ValidateUser() || ValidatePass()) {
            return;
        }

    }


}


