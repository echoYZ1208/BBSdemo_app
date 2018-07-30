package com.example.echo.dulforum;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.echo.entity.Member;
import com.example.echo.entity.Module;
import com.example.echo.service.impl.AdministratorServiceImpl;
import com.example.echo.service.impl.ModuleHostServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MemberPage extends AppCompatActivity {

    private List<Member> memberList = new ArrayList<>();

    private RecyclerView memberRecyclerView;

    private MemberAdapter adapter;

    private Handler handler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Log.d("memberTest", "获取成员成功");
                    loadDataToRecyclerView();
                    break;
                case 2:
                    Toast.makeText(MemberPage.this, "任命版主成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case 3:
                    Toast.makeText(MemberPage.this, "拉黑会员成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    private void loadDataToRecyclerView() {
        adapter = new MemberAdapter(this, memberList);
        memberRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_page);

        initdata();
        memberRecyclerView = (RecyclerView) findViewById(R.id.member_recyclerView);
        memberRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        memberRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private void initdata() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                memberList = new AdministratorServiceImpl().getMember();
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

    public void setModuleOwner(final Member moduleOwner) {
        final Module currentModule = (Module)getIntent().getSerializableExtra("currentModule");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(new AdministratorServiceImpl().addModuleHost(currentModule, moduleOwner)) {
                    Log.d("设置版主", "成功");
                }
                else {
                    Log.d("设置版主", "失败");
                }
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        }).start();
    }

    public void blackMember(final Member member) {
        final Module currentModule = (Module)getIntent().getSerializableExtra("currentModule");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(new ModuleHostServiceImpl().black(member, currentModule)) {
                    Message message = new Message();
                    message.what = 3;
                    handler.sendMessage(message);
                    Log.d("拉黑", "成功");
                }
                else {
                    Log.d("拉黑", "失败");
                }
            }
        }).start();
    }


}
