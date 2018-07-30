package com.example.echo.dulforum;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MeFragment extends Fragment implements View.OnClickListener{

    private Button signButton;

    private Button logoutButton;

    private TextView meText;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.me_fragment,container, false);

        signButton = (Button) view.findViewById(R.id.me_sign_button);
        logoutButton = (Button) view.findViewById(R.id.logout_button);
        meText = (TextView) view.findViewById(R.id.me_text);

        if (Register.user != null) {
            meText.setText(Register.user.getUserName() + " " + Register.user.getUserType());
        }
        else {
            meText.setText("游客");
        }

        signButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_sign_button:
                Intent intent = new Intent(view.getContext(), SignPage.class);
                startActivity(intent);
                break;
            case R.id.logout_button:
                Register.user = null;
                Intent intent1 = new Intent(view.getContext(), Register.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
