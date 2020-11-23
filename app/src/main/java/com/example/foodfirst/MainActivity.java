package com.example.foodfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
    public static final String TAG = "MainActivity";
    public static final String KEY_NAME1 = "name";
    public static final String KEY_NAME2 = "number";

    public EditText personName;
    public EditText personNumber;

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        personName = findViewById(R.id.personName);
        personNumber = findViewById(R.id.personNumber);

        Button LoginBtn= (Button) findViewById(R.id.loginbtn);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             openActivity2();
                loginDetails();
            }
        });
    }

    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }


    public void loginDetails() {

        String name = personName.getText().toString();
        String number = personNumber.getText().toString();

        Map<String, Object> details = new HashMap<>();
        details.put(KEY_NAME1 , name);
        details.put(KEY_NAME2 , number);
        db.collection("loginPerson").document("logindetails")
                .set(details)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_LONG).show();
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