package com.example.displayjokes.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.displayjokes.R;
import java.io.IOException;


public class UserInputFragment extends Fragment
{
    private EditText mUserInput;
    private Button clearButton;
    private Button generateButton;
    private Integer numOfJokes;
    private ViewGroup parent;
    final static String errorMessage = "Please enter a positive integer 1-10";
    private Activity activity;
    private static final int MAX_NUM_JOKES = 10;

    public UserInputFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * This will create the instance of the fragment to be displayed in the main activity
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_input_fragment, container,false);
        parent = container;
        mUserInput = v.findViewById(R.id.numberOfJokes);
        mUserInput.addTextChangedListener(loginTextWatcher);

        generateButton = v.findViewById(R.id.generateWidgets);
        clearButton = v.findViewById(R.id.clearWidget);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onGenerateClicked();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearUI();
            }
        });
        return v;
    }



    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        /**
         * Make sure the app only accepts integers. Block the input button otherwise
         * @param s
         * @param start
         * @param before
         * @param count
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String jokesToGenerate = mUserInput.getText().toString().trim();
            try {
                Integer num = Integer.parseInt(jokesToGenerate);
                boolean isDigitValid = num > 0 && num <= MAX_NUM_JOKES;
                generateButton.setEnabled(isDigitValid);
                if(!isDigitValid){
                    throw new Exception();
                }
                numOfJokes = num;
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(parent.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * This will call the async task and get a new joke string for the selected joke widget
     * @throws IOException
     */
    private void onGenerateClicked() throws IOException {
        try {
            ScrollingActivity.myBundle =  numOfJokes;
            Intent intent = new Intent(activity, ScrollingActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }catch (Exception e){
            e.printStackTrace();
            ScrollingActivity.myBundle =  0;
        }
    }

    /**
     * Clear the app interface
     */
    private void clearUI(){
        try {
            ScrollingActivity.myBundle =  0;
            Intent intent = new Intent(activity, ScrollingActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public UserInputFragment(Activity activity) {
        this.activity = activity;
    }
}
