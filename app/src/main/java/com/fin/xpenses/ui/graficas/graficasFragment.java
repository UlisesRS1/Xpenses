package com.fin.xpenses.ui.graficas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fin.xpenses.databinding.FragmentGraficasBinding;

public class graficasFragment extends Fragment {

    private FragmentGraficasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        graficasViewModel galleryViewModel =
                new ViewModelProvider(this).get(graficasViewModel.class);

        binding = FragmentGraficasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textGraficas;
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}