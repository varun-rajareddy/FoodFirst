package com.example.foodfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.foodfirst.MainActivity.KEY_NAME1;
import static com.example.foodfirst.Activity3.KEY_NAME3;
import static com.example.foodfirst.Activity3.KEY_NAME4;


public class bookingConfirmation extends AppCompatActivity {

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView retriveName;
    TextView orderName;
    TextView orderPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);


        retriveName = findViewById(R.id.retriveName2);
        orderName = findViewById(R.id.orderedName);
        orderPrice = findViewById(R.id.orderedPrice);

        getName();
        order();

        }


    public void getName() {
        db.collection("loginPerson").document("logindetails")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString(KEY_NAME1);
                    retriveName.setText(name);
                } else {
                    Toast.makeText(bookingConfirmation.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error! :"+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

    }

    public void order() {
        db.collection("loginPerson").document("OrderedDetails")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String orderedName = documentSnapshot.getString(KEY_NAME3);
                    String orderedPrice = documentSnapshot.getString(KEY_NAME4);
                    orderName.setText(orderedName);
                    orderPrice.setText("and price is "+orderedPrice);
                } else {
                    Toast.makeText(bookingConfirmation.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                }
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