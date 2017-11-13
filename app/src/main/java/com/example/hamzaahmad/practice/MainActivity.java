package com.example.hamzaahmad.practice;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText emailView, passView;
    Button btn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailView = (EditText) findViewById(R.id.emailView);
        passView = (EditText) findViewById(R.id.passView);

        mAuth = FirebaseAuth.getInstance();

        btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e = emailView.getText().toString();
                String p = passView.getText().toString();

                if(view == btn){
                    fun(e,p);
                }
            }
        });
    }

    public void fun(String email, String pass){


        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Sign Up Successful", Toast.LENGTH_LONG).show();
                }else{
                    String message = task.getException().getLocalizedMessage();
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
