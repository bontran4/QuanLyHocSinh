package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText txtUsernameDK, txtPasswordDK, txtRepasswordDK;
    Button btnSignup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtUsernameDK = (EditText) findViewById(R.id.usernameDK);
        txtPasswordDK = (EditText) findViewById(R.id.passwordDK);
        txtRepasswordDK = (EditText) findViewById(R.id.repasswordDK);
        btnSignup = (Button) findViewById(R.id.signupDK);
        DB = new DBHelper(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = txtUsernameDK.getText().toString();
                String pass = txtPasswordDK.getText().toString();
                String repass = txtRepasswordDK.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(SignupActivity.this, "Đang thiếu thông tin!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(SignupActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(SignupActivity.this, "Đăng ký không thành công!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignupActivity.this, "Người dùng đã tồn tại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignupActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}