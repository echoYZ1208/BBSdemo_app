package com.example.echo.dulforum;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echo.entity.Member;
import com.example.echo.service.impl.MemberServiceImpl;
import com.example.echo.service.impl.VisitorServiceImpl;

public class Register extends AppCompatActivity implements View.OnClickListener{

    public static Member user = null;

    private Button registerButton;

    private Button signButton;

    private TextView visiterText;

    private EditText userNameEditText;

    private EditText passwordEditText;

    private String userName;

    private String password;

    private Handler handler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Toast.makeText(Register.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, HomePage.class);
                    startActivity(intent);
                    break;
                case 2:
                    Toast.makeText(Register.this, "用户名密码错误", Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = (Button) findViewById(R.id.register_button);
        signButton = (Button) findViewById(R.id.sign_button);
        userNameEditText = (EditText) findViewById(R.id.userName_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        visiterText = (TextView) findViewById(R.id.visiter_text);

        registerButton.setOnClickListener(this);
        signButton.setOnClickListener(this);
        visiterText.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_button:
                userName = userNameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                login(userName, password);
                break;
            case R.id.sign_button:
                Intent intentToSign = new Intent(this, SignPage.class);
                startActivity(intentToSign);
                break;
            case R.id.visiter_text:
                Intent intentToHome = new Intent(this, HomePage.class);
                startActivity(intentToHome);
                break;
            default:
                break;

        }
    }

    private void login(final String userName, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                user = new MemberServiceImpl().logIn(userName, password);
                if(user != null) {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    Log.d("登录", "成功");
                }
                else {
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                    Log.d("登录", "失败");
                }
            }
        }).start();
    }
}
