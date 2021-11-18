package com.example.mikelciber;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class mainMenu extends AppCompatActivity {
    public static void pausar(){
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
    Button btn;
    TextView tv, invisibleAutoclicker;
    String printing, v1,tmp;
    int A=0, B=0,C=0,D=0, limitzzz=0,numeral=0,tmpN, tmpA,tmpB,tmpC,tmpD;
    double counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        tv =(TextView)findViewById(R.id.setValueText);
        btn = (Button)findViewById(R.id.incrementation);
        invisibleAutoclicker = (TextView) findViewById(R.id.autoClicker);
        char[] lA={' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char[] lB={' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char[] lC={' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char[] lD={' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        ref1.child("element").child("numeral").child("campo0").setValue(numeral);
        ref1.child("element").child("numeral").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                tmp=String.valueOf(task.getResult().child("campo0").getValue());
                numeral=Integer.parseInt(tmp);
                tmp=String.valueOf(task.getResult().child("campo1").getValue());
                A=Integer.parseInt(tmp);
                tmp=String.valueOf(task.getResult().child("campo2").getValue());
                B=Integer.parseInt(tmp);
                tmp=String.valueOf(task.getResult().child("campo3").getValue());
                C=Integer.parseInt(tmp);
                tmp=String.valueOf(task.getResult().child("campo4").getValue());
                D=Integer.parseInt(tmp);
                v1=Integer.toString(numeral);
                printing=v1+lA[A]+lB[B]+lC[C]+lD[D]+"";
                tv.setText(printing);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref1.child("element").child("numeral").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //tmp=String.valueOf(task.getResult().child("campo0").getValue());
                        tmp=snapshot.child("campo0").getValue().toString();
                        tmpN=Integer.parseInt(tmp);
                        //tmp=String.valueOf(task.getResult().child("campo1").getValue());
                        tmp=snapshot.child("campo1").getValue().toString();
                        tmpA=Integer.parseInt(tmp);
                        //tmp=String.valueOf(task.getResult().child("campo2").getValue());
                        tmp=snapshot.child("campo2").getValue().toString();
                        tmpB=Integer.parseInt(tmp);
                        //tmp=String.valueOf(task.getResult().child("campo3").getValue());
                        tmp=snapshot.child("campo3").getValue().toString();
                        tmpC=Integer.parseInt(tmp);
                        //tmp=String.valueOf(task.getResult().child("campo4").getValue());
                        tmp=snapshot.child("campo4").getValue().toString();
                        tmpD=Integer.parseInt(tmp);
                        //tmpN, tmpA,tmpB,tmpC,tmpD setteados
                        v1=Integer.toString(numeral);
                        printing=v1+lA[tmpA]+lB[tmpB]+lC[tmpC]+lD[tmpD]+"";
                        tmpN=numeral;
                        A=tmpA;
                        B=tmpB;
                        C=tmpC;
                        D=tmpD;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                if ( limitzzz == 27 && A==26 && B == 26 && C == 26 && D == 26 && numeral == 99){
                    tv.setText("LIMITE ALCANZADO");
                }
                v1=Integer.toString(numeral);
                printing=v1+lA[A]+lB[B]+lC[C]+lD[D]+"";
                counter++;
                numeral++;


                if (limitzzz<27) {
                    if (numeral == 10) {
                        numeral = 1;
                        A++;
                        counter++;
                    }
                    if (A == 27) {
                        A = 1;
                        B++;
                    }
                    if (B == 27) {
                        B = 1;
                        C++;
                    }
                    if (C == 27) {
                        C = 1;
                        D++;
                    }
                    if (D == 27) {
                        D = 1;
                        limitzzz++;
                    }
                    if (limitzzz == 27) {
                        A = 26;
                        B = 26;
                        C = 26;
                        D = 26;
                        numeral = 99;
                    }

                }

                ref1.child("element").child("numeral").child("campo0").setValue(numeral);
                ref1.child("element").child("numeral").child("campo1").setValue(A);
                ref1.child("element").child("numeral").child("campo2").setValue(B);
                ref1.child("element").child("numeral").child("campo3").setValue(C);
                ref1.child("element").child("numeral").child("campo4").setValue(D);

                tv.setText(printing);

            }
        });

    }

}
