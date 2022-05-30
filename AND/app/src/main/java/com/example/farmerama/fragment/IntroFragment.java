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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.farmerama.R;
import com.example.farmerama.viewmodel.IntroViewModel;
import com.example.farmerama.viewmodel.MeasurementsViewModel;

public class IntroFragment extends Fragment {

    private ImageView iv;
    private Button next;
    private Button skip;
    private int curPos = 1;
    private IntroViewModel viewModel;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);

        return view;
    }

    public void setCurPos(int curPos) {
        this.curPos = curPos;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        navController = Navigation.findNavController(view);

        viewModel = new ViewModelProvider(getActivity()).get(IntroViewModel.class);

        iv = view.findViewById(R.id.ivInfo);
        next = view.findViewById(R.id.next);
        skip = view.findViewById(R.id.skip);

//        if(viewModel.isSeen()){
////            navController.navigate(R.id.loginFragment);
//            Navigation.findNavController(view).navigate(R.id.loginFragment);
//        }

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
            default: return;
        }

        next.setOnClickListener(v ->{
            if(curPos == 3){
                Navigation.findNavController(getView()).navigate(R.id.loginFragment);
            }
        });

        skip.setOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(R.id.loginFragment);
        });
    }
}


