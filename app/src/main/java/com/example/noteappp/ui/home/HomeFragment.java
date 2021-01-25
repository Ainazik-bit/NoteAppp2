package com.example.noteappp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteappp.Models.Note;
import com.example.noteappp.OnItemClickListener;
import com.example.noteappp.Prefs;
import com.example.noteappp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private  NoteAdapter adapter;
    ArrayList<Note> list;
    private  Button  delete;
    private Prefs prefs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new NoteAdapter();
        recyclerView = view.findViewById(R.id.recyclerView);
        view.findViewById(R.id.fab).setOnClickListener(v -> openForm());
        setFragmentListener();
        initList();
        list =  new ArrayList<>();
        setNote("Данияр байке");
        setNote("Махабат эже");
        setNote("Асель эже");
        setNote("Шахноза эже");
        setNote("Айжан эже");
        setNote("Атай байке");
        setNote("Бакай байке");
        setNote("Назар ");
        setNote("Омурзак");
        setNote("Айдана");
        adapter.addList(list);
    }

    public void setNote(String title){
        Note n = new Note(title);
        list.add(n);
    }

    private void initList() {
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void longClick(int position) {

                LayoutInflater inflater = LayoutInflater.from(getContext());
                View view = inflater.inflate(R.layout.dialog_layout, null);


                delete = view.findViewById(R.id.delete);
                Button cancel = view.findViewById(R.id.cancel);

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
                        .setView(view);

                final AlertDialog dialog = alert.create();

                delete.setOnClickListener(v -> {
                    adapter.remove(position);
                    dialog.dismiss();
                });

                cancel.setOnClickListener(v ->dialog.dismiss());
                dialog.show();
            }
        });
    }

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("rk_form",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = (Note) result.getSerializable("note");
                        adapter.addItem(note);
                    }
                });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_options, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        prefs = new Prefs(requireContext());

        switch (item.getItemId()){

            case R.id.clear:

                prefs.clearSets();
                requireActivity().finish();
                return true;

        }

        return super.onOptionsItemSelected(item);

    }

    private void openForm() {
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
        navController.navigate(R.id.formFragment);
    }
}