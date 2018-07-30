package com.example.echo.dulforum;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echo.entity.Comment;
import com.example.echo.service.impl.MemberServiceImpl;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private CommentPage context;

    private List<Comment> commentList;

    private Handler handler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    context.initCommentData();
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView commentContent;

        public ViewHolder(View view) {
            super(view);
            commentContent = (TextView) view.findViewById(R.id.comment_content_text);
        }
    }

    public CommentAdapter(CommentPage context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        CommentAdapter.ViewHolder holder = new CommentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.commentContent.setText(comment.getCommentContent());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public void addComment(final Comment comment) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(new MemberServiceImpl().addComment(comment)) {
                    Log.d("添加评论", "成功");
                }
                else {
                    Log.d("添加评论", "失败");
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }
}
