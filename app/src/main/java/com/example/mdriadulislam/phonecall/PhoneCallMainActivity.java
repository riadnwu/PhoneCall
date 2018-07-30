package com.example.mdriadulislam.phonecall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneCallMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button callButton,messageButton;
    EditText numberText;
    String phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call_main);
        numberText = findViewById(R.id.numberText);
        messageButton=findViewById(R.id.message);
        messageButton.setOnClickListener(this);
        callButton=findViewById(R.id.call);
        callButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.call) {
            phoneNumber="";
            phoneNumber="tel:"+numberText.getText().toString();
            Call(phoneNumber);
        }
        else if(view.getId()== R.id.message)
        {
            Intent intent=new Intent(this,MessageMainActivity.class);
            phoneNumber="";
            phoneNumber=numberText.getText().toString();
            intent.putExtra(MessageMainActivity.numberId,phoneNumber);
            startActivity(intent);
            overridePendingTransition(R.anim.phone_to_message,R.anim.message_to_phone);

        }

    }

    public void Call(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(phoneNumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PhoneCallMainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            startActivity(intent);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   Call(phoneNumber);
            }
        }
    }

}