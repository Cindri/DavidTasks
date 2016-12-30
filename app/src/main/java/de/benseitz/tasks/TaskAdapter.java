package de.benseitz.tasks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class TaskAdapter extends ArrayAdapter<Task> {

    private Context context;
    private ArrayList<Task> taskArrayList;

    private static class ViewHolder {
        public TextView titel;
        public Button buttonDone;
    }

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
        this.context = context;
        this.taskArrayList = tasks;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Task task = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_tasklist, parent, false);
            viewHolder.titel = (TextView)convertView.findViewById(R.id.titel);
            viewHolder.buttonDone = (Button)convertView.findViewById(R.id.task_delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.titel.setText(task.getTitel());

        // Configure colors
        String colorAccentString = context.getString(Integer.parseInt(String.valueOf(R.color.colorAccent)));
        int colorAccent = Color.parseColor(colorAccentString);

        if (task.getWichtig()) {
            viewHolder.titel.setTextColor(colorAccent);
        }

        viewHolder.titel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);

                // DEBUG TaskList ausgeben
                for (int i = 0; i < taskArrayList.size(); i++) {
                    Log.d(TAG, "Element "+i+": "+taskArrayList.get(i).getTitel()+" ("+taskArrayList.get(i)+")");
                }

                // find out the index in array list of the current task
                Log.d(TAG, "Ausgewähltes Element: "+taskArrayList.indexOf(task));
                int selectedTaskIndex = taskArrayList.indexOf(task);

                intent.putExtra(Constants.INDEX_TASK_MODIFIED, selectedTaskIndex);
                intent.putExtra(Constants.TASK_DETAIL, task);
                context.startActivity(intent);
            }
        });

        viewHolder.buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(task);
            }
        });
        return convertView;
    }

}
