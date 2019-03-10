package com.dsc.android.bootcamp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName, etEmail, etPassword, etConfirm;
    private Button btnRegister;
    private String name, email, password, confirm;
    private TinyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new TinyDB(this);

        initView();

        btnRegister.setOnClickListener(this);

    }

    private void initView(){

        // initializing components
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirm);
        btnRegister = findViewById(R.id.btnRegister);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnRegister:
                getInfo();
                register();
            break;
        }
    }

    private void getInfo(){

        name  = etName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString();
        confirm = etConfirm.getText().toString();

    }

    private void register(){

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()){
            Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_LONG).show();
        }
        else{
            if(password.equals(confirm)){
                db.putString("name", name);
                db.putString("email", email);
                db.putString("password", password);
                Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
            }
            else{
                Toast.makeText(this, "Password did not match", Toast.LENGTH_LONG).show();
            }
        }

    }
}
