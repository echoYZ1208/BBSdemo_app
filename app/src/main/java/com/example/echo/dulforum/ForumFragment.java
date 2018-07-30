package com.example.echo.dulforum;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.echo.entity.Module;
import com.example.echo.service.impl.VisitorServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ForumFragment extends Fragment {

    private Handler handler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Log.d("ModuleTest", "获取版块成功");
                    loadDataToRecyclerView();
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    private void loadDataToRecyclerView() {
        forumItemAdapter = new ForumItemAdapter(this, moduleList);
        forumRecyclerView.setAdapter(forumItemAdapter);
    }

    //private List<ForumItem> forumItemList = new ArrayList<>();
    private List<Module> moduleList = new ArrayList<>();

    private RecyclerView forumRecyclerView;

    private ForumItemAdapter forumItemAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.forum_fragment,container, false);
        //配置论坛版块 碎片 recyclerView
        initForumItemData();
        forumRecyclerView = (RecyclerView) view.findViewById(R.id.forum_recyclerView);
        //forumRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        forumRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        forumRecyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }


    public void initForumItemData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                moduleList = new VisitorServiceImpl().getModule();
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();

    }

    public void add(Module module) {
        forumItemAdapter.addItem(module);
    }

}
