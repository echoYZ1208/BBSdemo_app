package com.example.echo.dulforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddComment extends AppCompatActivity implements View.OnClickListener{

    private EditText addCommentEditEtxt;

    private Button doneButton;

    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        //初始化控件
        addCommentEditEtxt = (EditText) findViewById(R.id.add_comment_editText);
        doneButton = (Button) findViewById(R.id.comment_done_button);
        cancelButton = (Button) findViewById(R.id.comment_cancel_button);

        //设置监听
        doneButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.comment_done_button:
                Intent intent = new Intent();
                intent.putExtra("data_return", addCommentEditEtxt.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.comment_cancel_button:
                finish();
                break;
            default:
                break;
        }
    }
}
