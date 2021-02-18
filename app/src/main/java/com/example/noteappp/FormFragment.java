package com.example.noteappp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.noteappp.Models.Note;
import com.google.android.material.behavior.SwipeDismissBehavior;

import java.text.DateFormat;
import java.util.Date;

public class FormFragment extends Fragment {

    private Note note;
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }


    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle saveInstanceState) {

        //==========================we are receiving note from here=================================================


        editText = getView().findViewById(R.id.edit_text);
        getView().findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        note = (Note) requireArguments().getSerializable("note");
        if (note != null) editText.setText(note.getTitles());
    }

    private void save() {
        String date = DateFormat.getDateTimeInstance().format(new Date());
        String text = editText.getText().toString().trim();
        if(note == null){

            note = new Note(text, date);
            App.getAppDataBase().noteDao().insert(note);

        } else {

            note.setTitles(text);
            App.getAppDataBase().noteDao().update(note);

        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("note", note);
        getParentFragmentManager().setFragmentResult("rk_form", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
        navController.navigateUp();
    }
}
