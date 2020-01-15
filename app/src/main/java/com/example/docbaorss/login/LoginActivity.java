package com.example.docbaorss.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.docbaorss.R;
import com.example.docbaorss.view.MainActivity;

public class LoginActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_login);

      EditText txtUsername = findViewById(R.id.edtId);
      EditText txtPassword = findViewById(R.id.edtPassWord);
      Button btnLogin = findViewById(R.id.btnLogin);
      Button btnRegister = findViewById(R.id.btnRegister);

      btnLogin.setOnClickListener(v -> {
         String User = txtUsername.getText().toString();
         String Pass = txtPassword.getText().toString();
         SharedPreferences preferences = getSharedPreferences("User", MODE_PRIVATE);


         String userDetails = preferences.getString(User + Pass +"data","Tài khoản hoặc mật khẩu không chính xác !");
         SharedPreferences.Editor editor = preferences.edit();
         editor.putString("profile", userDetails);
         editor.commit();


         Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
         startActivity(intent);


      });
      btnRegister.setOnClickListener(v -> {
         Intent intent = new Intent(LoginActivity.this, Register.class);
         startActivity(intent);
      });

   }

}
