package com.example.echo.dulforum;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echo.entity.Member;

import org.w3c.dom.Text;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    private MemberPage context;

    private List<Member> memberList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView memberName;

        public ViewHolder(View view) {
            super(view);
            memberName = (TextView) view.findViewById(R.id.member_name_text);
        }
    }

    public MemberAdapter(List<Member> memberList) {
        this.memberList = memberList;
    }

    public MemberAdapter(MemberPage context, List<Member> memberList) {
        this.context = context;
        this.memberList = memberList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        //设置点击监听
        holder.memberName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                final Member member = memberList.get(position);
                //弹窗提示
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setTitle("操作提示");
                dialog.setMessage("你想进行什么操作？");
                //任命版主
                dialog.setPositiveButton("任命为版主", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.setModuleOwner(member);
                    }
                });
                //拉黑
                dialog.setNegativeButton("拉黑", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.blackMember(member);
                    }
                });
                dialog.show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Member member = memberList.get(position);
        holder.memberName.setText(member.getUserName());
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }
}
