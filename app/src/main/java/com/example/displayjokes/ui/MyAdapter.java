package com.example.displayjokes.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.displayjokes.R;
import com.example.displayjokes.http.GetJokeTask;
import com.example.displayjokes.model.Joke;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;
    private OnButtonClickedListener bListener;
    //private final View.OnClickListener mOnClickListener = new MyOnClickListener();

     public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        this.bListener = bListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item,parent, false);
        return new ViewHolder(v, bListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Button getJokeButton;
        public TextView jokeText;
        OnButtonClickedListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnButtonClickedListener onButtonClickedListener) {
            super(itemView);
            final Joke[] newJoke = new Joke[1];
            getJokeButton = (Button) itemView.findViewById(R.id.generateJoke);
            jokeText = (TextView) itemView.findViewById(R.id.jokeText);
            this.onNoteListener = onButtonClickedListener;
            getJokeButton.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("StaticFieldLeak")
                @Override
                public void onClick(View v) {
                    try{
                        System.out.println(getAdapterPosition());
                        new GetJokeTask() {
                            @Override
                            protected void onPostExecute(Joke joke) {
                                super.onPostExecute(joke);
                                System.out.println(joke.getJoke());
                                jokeText.setText(joke.getJoke());
                            }
                        }.execute();

                    }catch (Exception e){

                    }
                }
            });
        }
    }

    public interface OnButtonClickedListener {
        void onItemClicked(int position);
    }/*

    public void setOnItemClickedListener(View.OnClickListener listener){
         ScrollingActivity.myBundle.putInt(ScrollingActivity.numKey, );
    }*/


}

