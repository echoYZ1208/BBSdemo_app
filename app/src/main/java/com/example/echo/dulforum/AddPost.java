package com.example.echo.dulforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPost extends AppCompatActivity implements View.OnClickListener{

    private EditText postTitleEditText;

    private EditText postContentEditText;

    private Button doneButton;

    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        //初始化控件
        postTitleEditText = (EditText) findViewById(R.id.add_postTitle_editText);
        postContentEditText = (EditText) findViewById(R.id.add_post_content_editText);
        doneButton = (Button) findViewById(R.id.post_done_button);
        cancelButton = (Button) findViewById(R.id.post_cancel_button);

        //设置监听
        doneButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post_done_button:
                Intent intent = new Intent();
                intent.putExtra("title_return", postTitleEditText.getText().toString());
                intent.putExtra("content_return", postContentEditText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.post_cancel_button:
                finish();
                break;
            default:
                break;
        }
    }
}
