package de.benseitz.tasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private Task task;
    private String modification;
    private int taskIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        task = (Task) getIntent().getSerializableExtra(Constants.TASK_DETAIL);
        taskIndex = getIntent().getIntExtra(Constants.INDEX_TASK_MODIFIED, 0);
        modification = "";
        TextView notiz;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        notiz = (TextView) findViewById(R.id.detailnotiz);
        this.setTitle(task.getTitel());
        notiz.setText(task.getNotiz());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        boolean important = task.getWichtig();
        int iconKey = (important ? R.mipmap.ic_error_outline_pink_36dp : R.mipmap.ic_error_outline_white_36dp);
        menu.getItem(0).setIcon(iconKey);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.task_important:
                modification = "change";

                boolean important = task.getWichtig();
                task.setWichtig(!important);

                saveAndBack();
                return true;
            case R.id.task_delete_menu:
                modification = "delete";

                saveAndBack();
                return true;
            default:
                modification = "";
                return super.onOptionsItemSelected(item);

        }

    }

    private void saveAndBack() {
        Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
        intent.putExtra(Constants.TASK_DETAIL, task);
        intent.putExtra(Constants.INDEX_TASK_MODIFIED, taskIndex);
        intent.putExtra(Constants.TASK_MODIFICATION, modification);
        startActivity(intent);
    }

}
