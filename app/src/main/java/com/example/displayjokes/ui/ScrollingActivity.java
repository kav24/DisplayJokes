package com.example.displayjokes.ui;

import android.os.Bundle;

import com.example.displayjokes.R;
import com.example.displayjokes.model.ListItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems = new ArrayList<>();
    private UserInputFragment userInputFragment;
    private static Integer jokesBeingDisplayed = 0;
    public static Integer myBundle = Integer.valueOf(0);

    /**
     * This will build the main activity. State should be saved when the phone rotates
     * and not new activity will be built
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        FragmentManager fm = this.getSupportFragmentManager();
        userInputFragment = (UserInputFragment) fm.findFragmentById(R.id.userInput);

        if(userInputFragment == null){
            userInputFragment = new UserInputFragment(this);
            fm.beginTransaction()
                    .add(R.id.userInputFrame, userInputFragment)
                    .commit();
        }
        recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        jokesBeingDisplayed = (myBundle != null)? myBundle:0;
        for(int i = 0; i < jokesBeingDisplayed; i++) {
            ListItem item = new ListItem();
            listItems.add(item);
        }
        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
