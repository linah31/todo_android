package net.penguincoders.doit.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import net.penguincoders.doit.AddNewTask;
import net.penguincoders.doit.MainActivity;
import net.penguincoders.doit.Model.ToDoModel;
import net.penguincoders.doit.R;
import net.penguincoders.doit.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> todoList;
    private DatabaseHandler db;
    private MainActivity activity;
     MediaPlayer mp ;

    CardView cv;

    public ToDoAdapter(DatabaseHandler db, MainActivity activity) {
        this.db = db;
        this.activity = activity;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        db.openDatabase();

        final ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));

        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    db.updateStatus(item.getId(), 1);
                    holder.task.setPaintFlags(holder.task.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    Snackbar.make(buttonView, holder.task.getText()+"  is Done", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //int index = todoList.indexOf(item);
                    //todoList.remove(index);
                    //todoList.add(todoList.size()-1, item);
                    MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.done);


                    mediaPlayer.start();

                } else {
                    db.updateStatus(item.getId(), 0);
                    holder.task.setPaintFlags( holder.task.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));

                }
            }
        });


    }
    public void filterList(ArrayList<ToDoModel> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        todoList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    private boolean toBoolean(int n) {
        return n != 0;
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public Context getContext() {
        return activity;
    }

    public void setTasks(List<ToDoModel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        ToDoModel item = todoList.get(position);
        db.deleteTask(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        ToDoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }



}
