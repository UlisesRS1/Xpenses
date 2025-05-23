package com.fin.xpenses.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fin.xpenses.R;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.databinding.FragmentHomeBinding;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.repository.IMovimientoRepository;
import com.fin.xpenses.repository.MovimientoRepository;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DatabaseHelper databaseHelper;
    private IMovimientoRepository iMovimientoRepository;
    private List<Movimiento> movimientos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        run();

        return root;
    }

    private void run() {
        init();
        showDataInList();
    }
    private void init() {
        this.databaseHelper = new DatabaseHelper(requireContext());
        this.iMovimientoRepository = new MovimientoRepository(this.databaseHelper);
        this.movimientos = this.iMovimientoRepository.obtenerTodosLosMovimientos();
        String text = "";
        for (Movimiento movimiento : this.movimientos) {
            text += movimiento.getDescripcion() + "\n";
        }
        Log.d("HomeFragment", "init: " + text);
    }

    private void showDataInList() {
        this.binding.lvInicioF.setAdapter(new HomeAdaptador(requireContext(), this.movimientos));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}