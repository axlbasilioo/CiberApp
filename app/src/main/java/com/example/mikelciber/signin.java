package com.example.mikelciber;


import static com.example.mikelciber.MD5.getMd5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class signin extends AppCompatActivity {
    String tmpuser,tmpPass, numeral;
    String encrypted= null;
    String passs;
    int tmpnumeral;
    boolean comparator=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference(); //firebase reference
        EditText username= (EditText)findViewById(R.id.editTextTextPersonName22);
        EditText password= (EditText)findViewById(R.id.editTextTextPassword33);
        Button login=(Button)findViewById(R.id.button1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=(String) username.getText().toString();//get text
                String pass=(String) password.getText().toString();//get text
                Query userData=ref1.child("users").child("users").orderByChild(user).equalTo(user).limitToFirst(1);


                if(user.matches("") && pass.matches("")){
                    Toast.makeText(signin.this, "Ingrese Usuarios", Toast.LENGTH_LONG).show();
                }
                else if(user.matches("") || pass.matches("")){
                    Toast.makeText(signin.this, "Datos Incompletos", Toast.LENGTH_LONG);
                }
                else if(!pass.trim().equals("") && !user.trim().equals("")){
                    String passmd5=getMd5(pass);
                    pass = pass.replaceAll("[^a-zA-Z0-9]", " ");//   //.><"!";
                    ref1.child("users").child("users").child(user).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                Toast.makeText(signin.this, "Usuario en uso", Toast.LENGTH_SHORT).show();
                            }
                            if (!snapshot.exists()){
                                ref1.child("users").child("users").child(user).child("password").setValue(passmd5);
                                Toast.makeText(signin.this, "Registrado Exitosamente", Toast.LENGTH_SHORT).show();
                                Intent login= new Intent(signin.this,MainActivity.class);
                                startActivity(login);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });
        //compracion de campos

    }
    //metodo encriptacion MD5
    public static class MD5 {
        public static String getMd5(String input) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(input.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                String hashtext = no.toString(16);
                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }
                return hashtext;
            }
            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
