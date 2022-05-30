package com.example.farmerama.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.farmerama.R;

public class IntroFragment extends Fragment {

    private ImageView iv;
    private Button next;
    private Button skip;
    private int curPos = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);

        iv = view.findViewById(R.id.ivInfo);
        next = view.findViewById(R.id.next);
        skip = view.findViewById(R.id.skip);

        switch (curPos){
            case 1: iv.setImageResource(R.drawable.intro_farmerama);
                next.setText("Next");
                break;
            case 2: iv.setImageResource(R.drawable.second_farme);
                next.setText("Next");
                break;
            case 3: iv.setImageResource(R.drawable.third_farme);
            next.setText("Proceed");
            break;
            default: return null;
        }

        next.setOnClickListener(v ->{
            if(curPos == 3){
                Navigation.findNavController(getView()).navigate(R.id.loginFragment);
            }
        });

        skip.setOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(R.id.loginFragment);
        });

        return view;
    }

    public void setCurPos(int curPos) {
        this.curPos = curPos;
    }
}
