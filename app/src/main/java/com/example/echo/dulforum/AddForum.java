package com.example.echo.dulforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddForum extends AppCompatActivity implements View.OnClickListener{

    private EditText addForumEditText;

    private Button cancelButton;

    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_forum);

        //初始化控件
        cancelButton = (Button) findViewById(R.id.forum_cancel_button);
        doneButton = (Button) findViewById(R.id.forum_done_button);
        addForumEditText = (EditText) findViewById(R.id.add_forum_editText);

        //设置监听
        doneButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forum_done_button:
                Intent intent = new Intent();
                intent.putExtra("data_return", addForumEditText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.forum_cancel_button:
                finish();
                break;
            default:
                break;
        }
    }
}
