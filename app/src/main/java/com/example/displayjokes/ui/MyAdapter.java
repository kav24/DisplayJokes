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
import com.example.displayjokes.model.ListItem;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;

     public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }
    /**
     * This will create the view holder.
     */
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    /**
     * This will handle the creation of individual jokes
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        public Button getJokeButton;
        public TextView jokeText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            final Joke[] newJoke = new Joke[1];
            getJokeButton = (Button) itemView.findViewById(R.id.generateJoke);
            jokeText = (TextView) itemView.findViewById(R.id.jokeText);
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
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}

