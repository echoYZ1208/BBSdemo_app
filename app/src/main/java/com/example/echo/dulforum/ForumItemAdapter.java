package com.example.echo.dulforum;


import java.util.List;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echo.entity.Module;
import com.example.echo.service.impl.AdministratorServiceImpl;
import com.example.echo.service.impl.ModuleHostServiceImpl;

public class ForumItemAdapter extends RecyclerView.Adapter<ForumItemAdapter.ViewHolder> {

    private ForumFragment context;

    private List<Module> moduleList;

    private Handler handler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    context.initForumItemData();
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView forumItemText;

        public ViewHolder(View view) {
            super(view);
            forumItemText = (TextView) view.findViewById(R.id.forum_item_text);
        }
    }

    public ForumItemAdapter(ForumFragment context, List<Module> moduleList) {
        this.context = context;
        this.moduleList = moduleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        //论坛版块点击事件 ...其实应该写到onBindView里
        holder.forumItemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Module module = moduleList.get(position);
                //向帖子页面传参，跳转至相应帖子
                Intent intent = new Intent(view.getContext(), PostPage.class);
                intent.putExtra("clickModule", module);
                view.getContext().startActivity(intent); //startActivity是Context的方法！！！
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Module module = moduleList.get(position);
        holder.forumItemText.setText(module.getModuleName());

        //长按删除或是置顶
        holder.forumItemText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                if (Register.user != null ) {
                    if (Register.user.getUserType().equals("管理员")) {//弹窗提示
                        AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                        dialog.setTitle("操作提示");
                        dialog.setMessage("你想进行什么操作？");
                        //删除版块
                        dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                removeItem(module);
                                //删除动画
                                notifyItemRemoved(position);
                                notifyDataSetChanged();
                            }
                        });
                        //置顶版块
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                stick(module);
                            }
                        });
                        dialog.show();
                    }
                    else {
                        Toast.makeText(context.getContext(), "权限不够", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(context.getContext(), "权限不够", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    //添加论坛版块
    public void addItem(final Module module) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(new AdministratorServiceImpl().addModule(module)) {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    Log.d("AddModule", "SUC");
                }
                else {
                    Log.d("AddModule", "FAL");
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();

    }
    //删除版块
    private void removeItem(final Module module) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (new AdministratorServiceImpl().deleteModule(module)) {
                    Log.d("DelModule", "SUC");
                }
                else {
                    Log.d("DelModule", "FAL");
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }
    //置顶版块
    private void stick(Module module) {
    }
}
