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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteappp.App;
import com.example.noteappp.Models.Note;
import com.example.noteappp.OnItemClickListener;
import com.example.noteappp.Prefs;
import com.example.noteappp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private  NoteAdapter adapter;
    List<Note> list = new ArrayList<>();
    private  Button  delete;
    private Prefs prefs;
    private boolean isUpdating = false;
    private int temp;
    private Note note;
    public static final String PARENT_KEY = "101";
    private Button sortAlphabet;
    private Button sortData;

    private Boolean haha = true;
    private Boolean sortedByName = true;
    private Boolean sortedByDate = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new NoteAdapter();
        loadData();
    }

    private void loadData() {
        List<Note> list = App.database.noteDao().getAll();
        if(list != null){
            adapter.setList( list);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        view.findViewById(R.id.fab).setOnClickListener(v -> openForm(null));
        setFragmentListener();
        initList();


    }

    private void initList() {
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                note = adapter.getItem(position);
                temp = position;
                isUpdating = true;
                openForm(note);
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
                    App.getAppDataBase().noteDao().delete(adapter.getItem(position));
                    Note note = adapter.getItem(position);
                    adapter.remove(position);
                    dialog.dismiss();
                });

                cancel.setOnClickListener(v ->dialog.dismiss());
                dialog.show();
            }
        });
    }

    //=================================here we receive note from formFragment====================================================
    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("rk_form",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        note = (Note) result.getSerializable("note");

                        if(isUpdating ){
                            adapter.setItem(note, temp);

                        } else {
                        adapter.addItem(note);
                        Log.e("GGG", "onFragmentResult: " +  note.getTitles());
                        }
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
            case R.id.Sort:
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View view = inflater.inflate(R.layout.sort_dialog, null);


                sortAlphabet = view.findViewById(R.id.sortAlphabet);
                sortData  = view.findViewById(R.id.sortData);

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
                        .setView(view);

                final AlertDialog dialog = alert.create();

                sortAlphabet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sortedByName();
                        dialog.dismiss();

                    }
                });

                sortData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sortedByDate();
                        dialog.dismiss();
                    }
                });

                dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortedByName() {
        if (sortedByName) {
            sortedByName = false;
            loadData();
        } else {
            loadDataSortedByName();
            sortedByName = true;
        }
    }

    private void sortedByDate() {
        if (sortedByDate) {
            loadDataSortedByDateDESC();
            sortedByDate = false;
        } else {
            loadDataSortedByASC();
            sortedByDate = true;
        }
    }

    private void loadDataSortedByName(){
        list = (ArrayList<Note>) App.getAppDataBase().noteDao().getAllByName();
        adapter.setList(list);
    }

    private void loadDataSortedByDateDESC(){
        list = (ArrayList<Note>) App.getAppDataBase().noteDao().getAllByDateDESC();
        adapter.setList(list);
    }
    private void loadDataSortedByASC(){
        list = (ArrayList<Note>) App.getAppDataBase().noteDao().getAllByDateASC();
        adapter.setList(list);
    }

    private void openForm(Note note) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("note", note);
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment);
        navController.navigate(R.id.formFragment, bundle);
    }
}