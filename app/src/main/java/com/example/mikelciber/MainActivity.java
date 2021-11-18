package com.example.mikelciber;

//import static com.example.mikelciber.MD5.getMd5;

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

import com.google.android.gms.common.util.Hex;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class MainActivity extends AppCompatActivity {
    String tmpuser,tmpPass, numeral;
    String encrypted= null;
    int tmpnumeral=0;
    boolean comparator=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference(); //firebase reference
        EditText username= (EditText)findViewById(R.id.editTextTextPersonName2);
        EditText password= (EditText)findViewById(R.id.editTextTextPassword3);
        TextView invisible=(TextView)findViewById(R.id.invisibleButton);
        Button signin = (Button) findViewById(R.id.Registro);
        Button login=(Button)findViewById(R.id.button);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=(String) username.getText().toString();//get text
                String pass=(String) password.getText().toString();//get text

                tmpnumeral++;
                numeral = String.valueOf(tmpnumeral);
                //compracion de campos
                if(user.matches("") && pass.matches("")){
                    Toast.makeText(MainActivity.this, "Error: datos incompletos", Toast.LENGTH_LONG).show();
                }
                if(pass.trim().equals("")){
                    Toast.makeText(MainActivity.this, "Error: Compruebe que los datos \n esten completos", Toast.LENGTH_LONG);
                }
                if(user.trim().equals("")){
                    Toast.makeText(MainActivity.this, "Error: Compruebe que los datos \n esten completos", Toast.LENGTH_LONG);
                }

                //campos llenos
                if(!user.isEmpty() && !pass.isEmpty()){
                    try {
                        //realizar referencias query a firebase
                        DatabaseReference loginQuery = FirebaseDatabase.getInstance().getReference();
                        //limpiar un campo de caracteres sospechosos posibles de inyeccion de codigo
                        pass = pass.replaceAll("[^a-zA-Z0-9]", " ");//   //.><"!";
                        //encriptado
                        String passmd5=getMd5(pass);
                        //metodo de consulta
                        loginQuery.child("users").child("users").child(user).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    String passs=snapshot.child("password").getValue().toString();//password query


                                    //pass validation
                                    if(passs.equals(passmd5)){
                                        Toast.makeText(MainActivity.this, "Usuario encontrado", Toast.LENGTH_SHORT).show();
                                                    Intent main = new Intent(MainActivity.this, mainMenu.class);
                                                    //main.putExtra("md5",passmd5);
                                                    startActivity(main);
                                                    finish();
                                    }else{
                                        Toast.makeText(MainActivity.this, "datos incorrectos", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(MainActivity.this, "Usuario no existente", Toast.LENGTH_SHORT).show();
                                }
                                

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sgn = new Intent(MainActivity.this,signin.class);
                startActivity(sgn);
            }
        });

    }

}
//metodo encriptacion MD5
class MD5 {
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