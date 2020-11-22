package com.example.foodfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodfirst.models.loginPerson;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity{

    Button LoginBtn;
    EditText personName;
    EditText personNumber;


    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        personName = findViewById(R.id.personName);
        personNumber = findViewById(R.id.personNumber);

        LoginBtn= findViewById(R.id.loginbtn);
        db = FirebaseFirestore.getInstance();

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=personName.getText().toString();
                String number=personNumber.getText().toString();
                loginDetails(name, number);

            }
        });



    }


    public void loginDetails(String personName, String personNumber) {
    loginPerson name1 = new loginPerson(personName,personNumber);
        db.collection("loginPerson")
                .add(name1)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "logged in" , Toast.LENGTH_LONG).show();
            }
        })
.addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(getApplicationContext(), "Error! :"+e.getMessage(),Toast.LENGTH_LONG).show();
    }
});
    }
}