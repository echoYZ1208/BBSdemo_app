package com.example.echo.dulforum;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.echo.service.impl.VisitorServiceImpl;

public class SignPage extends AppCompatActivity implements View.OnClickListener{

    private EditText userNameEditText;

    private EditText passwordEditEtxt;

    private EditText passwordAgainEditText;

    private EditText emailEditText;

    private Button doneButton;

    private Handler handler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Toast.makeText(SignPage.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignPage.this, Register.class);
                    startActivity(intent);
                    break;
                case 2:
                    Toast.makeText(SignPage.this, "注册失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_page);

        userNameEditText = (EditText) findViewById(R.id.sign_userName_editText);
        passwordAgainEditText = (EditText) findViewById(R.id.makeTrue_password_editText);
        passwordEditEtxt = (EditText) findViewById(R.id.sign_password_editText);
        emailEditText = (EditText) findViewById(R.id.email_editText);
        doneButton = (Button) findViewById(R.id.sign_done_button);

        doneButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_done_button:
                String name = userNameEditText.getText().toString();
                String password = passwordEditEtxt.getText().toString();
                String passwordAgain = passwordAgainEditText.getText().toString();
                String email = emailEditText.getText().toString();

                if(password.equals(passwordAgain)) {
                    register(name, email, password);
                }
                else {
                    Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void register(final String name, final String email, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(new VisitorServiceImpl().register(name, email, password)) {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
                else {
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }
}
