package com.example.echo.dulforum;

import android.content.Intent;
import android.media.tv.TvContentRating;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echo.entity.Module;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

    private TextView forumText;

    private TextView findText;

    private TextView meText;

    private TextView titleText;

    private Button addButton;
    //碎片管理
    FragmentManager fragmentManager;

    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        forumText = (TextView) findViewById(R.id.forum_text);
        findText = (TextView) findViewById(R.id.find_text);
        meText = (TextView) findViewById(R.id.me_text);
        titleText = (TextView) findViewById(R.id.title_text);
        addButton = (Button) findViewById(R.id.add_button);

        forumText.setOnClickListener(this);
        findText.setOnClickListener(this);
        meText.setOnClickListener(this);
        addButton.setOnClickListener(this);

        //设置控件可见性
        addButton.setVisibility(View.INVISIBLE);
        if (Register.user != null ) {
            if (Register.user.getUserType().equals("管理员")) {
                addButton.setVisibility(View.VISIBLE);
            }
        }


        //初始化为版块页面
        fragmentManager = getSupportFragmentManager();
        replaceFragment(new ForumFragment());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forum_text:
                titleText.setText("版块");
                if (Register.user != null ) {
                    if (Register.user.getUserType().equals("管理员")) {
                        addButton.setVisibility(View.VISIBLE);
                    }
                }
                replaceFragment(new ForumFragment());
                break;
            case R.id.find_text:
                titleText.setText("发现");
                addButton.setVisibility(View.INVISIBLE);
                replaceFragment(new FindFragment());
                break;
            case R.id.me_text:
                titleText.setText("我的");
                addButton.setVisibility(View.INVISIBLE);
                replaceFragment(new MeFragment());
                break;
            case R.id.add_button:
                if(Register.user != null) {
                    if (Register.user.getUserType().equals("管理员")) {
                        Intent intent = new Intent(this, AddForum.class);
                        startActivityForResult(intent, 1);
                    }
                    else {}
                }
                else {
                    Toast.makeText(this, "权限不够", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.major_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    Toast.makeText(this, "数据返回", Toast.LENGTH_SHORT).show();
                    String addMoudleName = data.getStringExtra("data_return");
                    Module module = new Module();
                    module.setModuleName(addMoudleName);
                    ForumFragment forumFragment = (ForumFragment)fragmentManager.findFragmentById(R.id.major_layout);
                    forumFragment.add(module);
                }
                break;
            default:
                break;
        }
    }
}
