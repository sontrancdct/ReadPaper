package com.example.docbaorss.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.docbaorss.R;
import com.example.docbaorss.view.MainActivity;

public class Register extends AppCompatActivity {

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.register);


      EditText edtemail = findViewById(R.id.edtemail);
      EditText edtID = findViewById(R.id.edtid);
      EditText edtPass = findViewById(R.id.edtPass);

      Button btnRegiter = findViewById(R.id.btnDone);

      btnRegiter.setOnClickListener(v -> {
         SharedPreferences preferences = getSharedPreferences("User", MODE_PRIVATE);
         String newUser = edtID.getText().toString();
         String newPass = edtPass.getText().toString();
         String newEmail = edtemail.getText().toString();

         SharedPreferences.Editor editor = preferences.edit();
         editor.putString(newUser + newPass + "data", newUser + "\n" + newEmail);
         editor.commit();

         Toast.makeText(this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
         Intent intent = new Intent(Register.this, LoginActivity.class);
         startActivity(intent);



      });
   }



}
