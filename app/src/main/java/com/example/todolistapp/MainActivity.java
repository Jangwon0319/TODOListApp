package com.example.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment todoFragment;
    private Fragment journalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Fragment 초기화
        todoFragment = new TodoFragment();
        journalFragment = new JournalFragment();

        // 첫 번째 Fragment로 TodoFragment를 보여줌
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, todoFragment, "TODO_FRAGMENT")
                .add(R.id.fragment_container, journalFragment, "JOURNAL_FRAGMENT")
                .hide(journalFragment)
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_todo) {
                showFragment(todoFragment);
            } else if (itemId == R.id.nav_journal) {
                showFragment(journalFragment);
            }
            return true;
        });
    }

    private void showFragment(Fragment fragmentToShow) {
        getSupportFragmentManager().beginTransaction()
                .hide(todoFragment)
                .hide(journalFragment)
                .show(fragmentToShow)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 각각의 프래그먼트를 태그로 찾아서 호출
        Fragment todoFragment = getSupportFragmentManager().findFragmentByTag("TODO_FRAGMENT");
        Fragment journalFragment = getSupportFragmentManager().findFragmentByTag("JOURNAL_FRAGMENT");

        if (todoFragment instanceof TodoFragment) {
            todoFragment.onActivityResult(requestCode, resultCode, data);
        }
        if (journalFragment instanceof JournalFragment) {
            journalFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
