package com.example.docbaorss.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.docbaorss.R;

public class ProfileActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_profile);

      SharedPreferences preferences = getSharedPreferences("User", MODE_PRIVATE);
      String profile = preferences.getString("profile","");

      TextView textView = findViewById(R.id.tvnameProfile);
      textView.setText(profile);
   }
}
