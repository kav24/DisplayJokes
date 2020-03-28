package com.example.displayjokes.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.displayjokes.R;

import java.io.IOException;
import java.net.MalformedURLException;

public class UserInputFragment extends Fragment
{
    private EditText mUserInput;
    private Button generateButton;
    private RadioGroup gender;
    private boolean isRadioGroupChecked = false;
    String userGender = null;
    private int numOfJokes;
    private final String maleGender = "Male";
    final static String https = "http://";
    private ViewGroup parent;
    final static String errorMessage = "Please enter a positive integer 1-10";
    //private UserLoginInfo info;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_input_fragment, container,false);
        parent = container;
        mUserInput = v.findViewById(R.id.userInput);


        generateButton = v.findViewById(R.id.generateWidgets);
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
        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }



    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String jokesToGenerate = mUserInput.getText().toString().trim();
            try {
                Integer num = Integer.parseInt(jokesToGenerate);
                boolean isDigitValid = num > 0 && num <= 10;
                if(!isDigitValid){
                    throw new Exception();
                }
                generateButton.setEnabled(isDigitValid);
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
    private void onGenerateClicked() throws IOException {
        try {
            ScrollingActivity.myBundle.putInt(ScrollingActivity.numKey, numOfJokes);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
