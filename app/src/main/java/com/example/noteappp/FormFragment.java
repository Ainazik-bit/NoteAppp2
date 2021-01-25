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

public class FormFragment extends Fragment {

    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        editText = getView().findViewById(R.id.edit_text);
        getView().findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }


    private void save() {
        String text = editText.getText().toString().trim();
        Note note = new Note(text);
        Bundle bundle = new Bundle();
        bundle.putSerializable("note", note);
        getParentFragmentManager().setFragmentResult("rk_form", bundle);
        ((MainActivity) requireActivity()).closeFragment();
    }}
