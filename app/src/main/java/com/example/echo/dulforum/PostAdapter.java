package com.example.echo.dulforum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echo.entity.Post;
import com.example.echo.entity.StickPost;
import com.example.echo.service.impl.MemberServiceImpl;
import com.example.echo.service.impl.ModuleHostServiceImpl;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    private PostPage context;

    private List<Post> postList;

    private List<StickPost> stickPostList;

    private Handler handler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    context.initPostData();
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    //都节点位置
    int headPosition = 0;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView postTopic;
        TextView postContent;
        TextView stickText;

        public ViewHolder(View view) {
            super(view);
            postTopic = (TextView) view.findViewById(R.id.post_topic_text);
            postContent = (TextView) view.findViewById(R.id.post_content_text);
            stickText = (TextView) view.findViewById(R.id.stick_text);
        }
    }


    public PostAdapter(PostPage context, List<Post> postList, List<StickPost> stickPostList) {
        this.context = context;
        this.postList = postList;
        this.stickPostList = stickPostList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        //设置点击监听
        holder.postTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Post post = postList.get(position);
                //向评论页面传参
                Intent intent = new Intent(view.getContext(), CommentPage.class);
                intent.putExtra("clickPost", post);
                view.getContext().startActivity(intent);
            }
        });
        holder.postContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Post post = postList.get(position);
                //向评论页面传参
                Intent intent = new Intent(view.getContext(), CommentPage.class);
                intent.putExtra("clickPost", post);
                view.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Post post = postList.get(position);
        holder.postTopic.setText(post.getTitle());
        holder.postContent.setText(post.getPostContent());

        //判断是否置顶
        for (int i=0; i<stickPostList.size(); i++) {
            if(stickPostList.get(i).getPostID() == post.getID()) {
                holder.stickText.setVisibility(View.VISIBLE);
            }
        }

        //判断是否是版主
        if (Register.user != null) {
            if (context.currentModule.getOwnerID() == Register.user.getID()
                    || Register.user.getUserType().equals("管理员")) {
                //设置长按置顶或者删除
                holder.postTopic.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        //弹窗提示
                        AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                        dialog.setTitle("操作提示");
                        dialog.setMessage("你想进行什么操作？");
                        //删除版块
                        dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                removePost(post);
                                //删除动画
                                notifyItemRemoved(position);
                                notifyDataSetChanged();
                            }
                        });
                        //置顶版块
                        dialog.setNegativeButton("置顶", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                stickPost(post);
                            }
                        });
                        dialog.show();
                        return true;
                    }
                });
                holder.postContent.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        //弹窗提示
                        AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                        dialog.setTitle("操作提示");
                        dialog.setMessage("你想进行什么操作？");
                        //删除版块
                        dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                removePost(post);
                                //删除动画
                                notifyItemRemoved(position);
                                notifyDataSetChanged();
                            }
                        });
                        //置顶版块
                        dialog.setNegativeButton("置顶", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                stickPost(post);
                            }
                        });
                        dialog.show();
                        return true;
                    }
                });
            }

        }

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void addPost(final Post post) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(new MemberServiceImpl().addPost(post)) {
                    Log.d("添加帖子", "成功");
                }
                else {
                    Log.d("添加帖子", "失败");
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void removePost(final Post post) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(new ModuleHostServiceImpl().deletePost(post)) {
                    Log.d("删除帖子", "成功");
                }
                else {
                    Log.d("删除帖子", "失败");
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void stickPost(final Post post) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(new ModuleHostServiceImpl().stickPost(post)) {
                    Log.d("置顶帖子", "成功");
                }
                else {
                    Log.d("删除帖子", "失败");
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

}
