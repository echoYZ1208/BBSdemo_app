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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echo.dao.impl.StickPostDaoImpl;
import com.example.echo.entity.Member;
import com.example.echo.entity.Module;
import com.example.echo.entity.Post;
import com.example.echo.entity.StickPost;
import com.example.echo.service.impl.AdministratorServiceImpl;
import com.example.echo.service.impl.CommenServiceImpl;
import com.example.echo.service.impl.MemberServiceImpl;
import com.example.echo.service.impl.ModuleHostServiceImpl;
import com.example.echo.service.impl.VisitorServiceImpl;

import org.w3c.dom.Text;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PostPage extends AppCompatActivity implements View.OnClickListener{

    private Post addPost;

    private boolean isBlack = true;

    public Module currentModule;

    private TextView postTile;

    private List<Post> postList = new ArrayList<>();

    private List<StickPost> stickPostList = new ArrayList<>();

    private Button addPostButton;

    private RecyclerView postRecyclerView;

    private PostAdapter adapter;

    private TextView setOwnerText;

    private Handler handler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Log.d("PostTest", "获取帖子成功");
                    loadDataToRecyclerView();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), "帖子内容含敏感词汇！", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    adapter.addPost(addPost);
                default:
                    break;
            }
            return true;
        }
    });

    private void loadDataToRecyclerView() {
        adapter = new PostAdapter(this, postList, stickPostList);
        postRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_page);
        //初始化控件
        postTile = (TextView) findViewById(R.id.post_title_text);
        currentModule = (Module) getIntent().getSerializableExtra("clickModule");
        String title = currentModule.getModuleName();
        postTile.setText(title);
        addPostButton = (Button) findViewById(R.id.add_post_button);
        addPostButton.setOnClickListener(this);
        setOwnerText = (TextView) findViewById(R.id.set_owner_text);
        setOwnerText.setOnClickListener(this);

        //设置 任命版主按钮 可见性
        setOwnerText.setVisibility(View.INVISIBLE);
        if (Register.user != null ) {
            if (Register.user.getUserType().equals("管理员")) {
                setOwnerText.setVisibility(View.VISIBLE);
            }
        }

        //检测用户是否在黑名单中
        isBlackMember();

        //初始化帖子list数据
        initPostData();
        //配置post RecyclerView
        postRecyclerView = (RecyclerView) findViewById(R.id.post_recyclerView);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        postRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_post_button:
                if (Register.user != null) {
                    if (isBlack == true) {
                        Toast.makeText(this, "您已被拉黑，禁止在此板块发帖！", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(this, AddPost.class);
                        startActivityForResult(intent, 1);
                    }
                }
                else {
                    Toast.makeText(this, "注册成为会员，即可发帖！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.set_owner_text:
                Intent intent1 = new Intent(this, MemberPage.class);
                intent1.putExtra("currentModule", currentModule);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    public void initPostData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                postList = new VisitorServiceImpl().getPost(currentModule.getID());
                stickPostList = new StickPostDaoImpl().getAllStickPost();
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    //Toast.makeText(this, "帖子数据返回", Toast.LENGTH_SHORT).show();
                    String addPostTitle = data.getStringExtra("title_return");
                    String addPostContent = data.getStringExtra("content_return");
                    addPost = new Post();
                    addPost.setTitle(addPostTitle);
                    addPost.setPostContent(addPostContent);
                    addPost.setSendTime(new Date(System.currentTimeMillis()));
                    addPost.setModuleID(currentModule.getID());
                    //检测是否有敏感词汇
                    isHasSenWord(addPost);
                    //adapter.addPost(addPost);
                }
                break;
            case 2:
                if(resultCode == RESULT_OK) {
                    String ownerName = data.getStringExtra("owner_return");
                    final Member member = (Member) getIntent().getSerializableExtra("owner_return");
                    Toast.makeText(this, member.getUserName(), Toast.LENGTH_SHORT).show();
                    //setModuleOwner(member);
                }
                break;
            default:
                break;
        }
    }

    private void isHasSenWord(final Post post) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(new CommenServiceImpl().hasSensitiveWord(post)) {
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }
                else {
                    Message message = new Message();
                    message.what = 3;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    private void isBlackMember() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Register.user != null) {
                    isBlack = new CommenServiceImpl().isInBlackList(currentModule, Register.user);
                }
            }
        }).start();
    }
}
