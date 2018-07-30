package com.example.mdriadulislam.phonecall;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MessageMainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String numberId="NUMBER";
    Button messageButton;
    EditText messageText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_main);
        messageText=findViewById(R.id.messageText);
        messageButton=findViewById(R.id.messageButton);
        messageButton.setOnClickListener(this);
        overridePendingTransition(R.anim.phone_to_message,R.anim.message_to_phone);

    }

    @Override
    public void onClick(View view) {
      if (view.getId()== R.id.messageButton)
      {
          Intent numberIntent=getIntent();
          String number=numberIntent.getStringExtra(numberId);
          String message=messageText.getText().toString();
          sendMessage(message,number);
      }
    }

    public void sendMessage(String message,String number)
    {
        Intent messageIntent=new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",number,null));
        messageIntent.putExtra("sms_body",message);
        startActivity(messageIntent);

    }
}
