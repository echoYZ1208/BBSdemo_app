package com.example.echo.dulforum;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echo.entity.Comment;
import com.example.echo.entity.Post;
import com.example.echo.service.impl.VisitorServiceImpl;

import org.w3c.dom.Text;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CommentPage extends AppCompatActivity implements View.OnClickListener{

    private Post currentPost;

    private TextView commentPostTitle;

    private TextView commentPostContent;

    private TextView addCommentText;

    private List<Comment> commentList = new ArrayList<>();

    private RecyclerView commentRecyclerView;

    private CommentAdapter adapter;

    private Handler handler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Log.d("CommentTest", "获取评论成功");
                    loadDataToRecyclerView();
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    private void loadDataToRecyclerView() {
        adapter = new CommentAdapter(this, commentList);
        commentRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_page);

        //初始化控件
        Intent intent = getIntent();
        currentPost = (Post) intent.getSerializableExtra("clickPost");
        commentPostTitle = (TextView) findViewById(R.id.commentPage_title_text);
        commentPostContent = (TextView) findViewById(R.id.comment_postContent_text);
        commentPostTitle.setText(currentPost.getTitle());
        commentPostContent.setText(currentPost.getPostContent());
        addCommentText = (TextView) findViewById(R.id.add_comment_text);
        addCommentText.setOnClickListener(this);

        //初始化评论数据
        initCommentData();
        commentRecyclerView = (RecyclerView) findViewById(R.id.comment_recyclerView);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    public void initCommentData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                commentList = new VisitorServiceImpl().getComment(currentPost.getID());
                Log.d("当前帖子id", String.valueOf(currentPost.getID()));
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_comment_text:
                if (Register.user != null) {
                    Toast.makeText(this, "添加评论按钮", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, AddComment.class);
                    startActivityForResult(intent, 1);
                }
                else {
                    Toast.makeText(this, "注册成为会员，即可发表评论！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    //Toast.makeText(this, "评论数据返回", Toast.LENGTH_SHORT).show();
                    String addCommentContent = data.getStringExtra("data_return");
                    Comment comment = new Comment();
                    comment.setCommentContent(addCommentContent);
                    comment.setCommentTime(new Date(System.currentTimeMillis()));
                    comment.setPostID(currentPost.getID());
                    comment.setOwnerID(Register.user.getID());
                    adapter.addComment(comment);
                }
                break;
            default:
                break;
        }
    }
}
