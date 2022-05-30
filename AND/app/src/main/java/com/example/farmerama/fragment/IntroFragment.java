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
import androidx.navigation.Navigation;

import com.example.farmerama.R;

public class IntroFragment extends Fragment {

    private ImageView iv;
    private Button proceed;
    private Button skip;
    private int currentPosition;

    public IntroFragment(int position) {
        currentPosition = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        iv = view.findViewById(R.id.ivInfo);
        proceed = view.findViewById(R.id.next);
        skip = view.findViewById(R.id.skip);

    }

    private void setupViews() {
        switch (currentPosition) {
            case 0:
                iv.setImageResource(R.drawable.intro_farmerama);
                proceed.setVisibility(View.INVISIBLE);
                break;

            case 1:
                iv.setImageResource(R.drawable.second_farme);
                proceed.setVisibility(View.INVISIBLE);
                break;

            case 2:
                iv.setImageResource(R.drawable.third_farme);
                proceed.setVisibility(View.VISIBLE);
                break;
            default:
                return;
        }

        skip.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.loginFragment));

        proceed.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.loginFragment));
    }
}


