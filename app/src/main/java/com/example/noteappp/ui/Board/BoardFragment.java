package com.example.noteappp.ui.Board;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteappp.Models.BoardRabbit;
import com.example.noteappp.OnItemClickListener;
import com.example.noteappp.Prefs;
import com.example.noteappp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class BoardFragment extends Fragment implements BoardAdapter.ClickListener {

    private BoardAdapter adapter;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnSkip).setOnClickListener(v -> closed());
        ViewPager2 viewPager = view.findViewById(R.id.ViewPager);
        adapter = new BoardAdapter(this);
        viewPager.setAdapter(adapter);
        adapter.setOnItemClickListener(new BoardAdapter.ClickListener() {
            @Override
            public void onClick(int position) {
                closed();
            }
        });
        addRabbit();

        tabLayout = view.findViewById(R.id.indicator);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText((""))).attach();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });
    }

    private void closed() {
        Prefs prefs = new Prefs(requireContext());
        prefs.saveBoardStatus();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigateUp();
    }

    private void addRabbit() {
        ArrayList<BoardRabbit> list = new ArrayList<>();


        list.add(new BoardRabbit("Добро пожаловать в Заметки !",
                "Откажитесь от бумаги.\n" +
                        "Пусть копии важных документов будут на всех ваших устройствах,\n" +
                        "храните информацию в своем смартфоне", R.raw.note3));
        list.add(new BoardRabbit("Насыщенные заметки",
                "Сохраняйте идеи и фото,\n" +
                        " записывайте аудио со встреч и лекций \n" +
                        "все это с телефона или планшета и даже без интернета.",
                R.raw.note2));
        list.add(new BoardRabbit("Помощник",
                "Создовайте списки дел ✔ \n" +
                        "Сохроняйте идеи ✔\n" +
                        "Планируйте свой день ✔", R.raw.nnnnote));
        adapter.addRabbitToBoard(list);
    }

    @Override
    public void onClick(int position) {

    }
}
