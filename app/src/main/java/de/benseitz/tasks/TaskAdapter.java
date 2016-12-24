package de.benseitz.tasks;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {

    private Context context;
    private static class ViewHolder {
        public TextView titel;
        public Button buttonDone;
    }

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        viewHolder.titel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(Constants.TASK_DETAIL_KEY, task);
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
