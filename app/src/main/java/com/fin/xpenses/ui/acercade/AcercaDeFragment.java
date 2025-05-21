package com.fin.xpenses.ui.acercade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fin.xpenses.databinding.FragmentAcercadeBinding;

public class AcercaDeFragment extends Fragment {

    private FragmentAcercadeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AcercaDeViewModel slideshowViewModel =
                new ViewModelProvider(this).get(AcercaDeViewModel.class);

        binding = FragmentAcercadeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}