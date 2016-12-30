package de.benseitz.tasks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvTasks;
    private TaskAdapter taskAdapter;

    private Gson gson = new Gson();

    public ArrayList<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tasksAsJson;

        // Get JSON string from Shared Preferences
        SharedPreferences settings = getSharedPreferences(Constants.TASK_STORAGE_NAME, 0);
        tasksAsJson = settings.getString("tasksAsJson", "");

        if (tasksAsJson.length() == 0) {
            tasksAsJson = "[]";
        }

        tasks = gson.fromJson(tasksAsJson, Constants.TYPE_ARRAY_LIST);

        // Check for external Task modifications
        if (getIntent().hasExtra(Constants.TASK_MODIFICATION)) {
            Task newTask = (Task) getIntent().getSerializableExtra(Constants.TASK_DETAIL);
            int taskIndex = getIntent().getIntExtra(Constants.INDEX_TASK_MODIFIED, 0);

            switch (getIntent().getStringExtra(Constants.TASK_MODIFICATION)) {
                case "change":
                    tasks.remove(taskIndex);
                    tasks.add(taskIndex, newTask);
                    break;
                case "delete":
                    tasks.remove(taskIndex);
                    break;
            }
        }

        lvTasks = (ListView) findViewById(R.id.lvTasks);
        taskAdapter = new TaskAdapter(this, tasks);
        lvTasks.setAdapter(taskAdapter);

        // Beautiful Button to add a task
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                String json = gson.toJson(tasks);
                intent.putExtra(Constants.JSON_TASK_LIST, json);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuItemNewTask:
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                String json = gson.toJson(tasks);
                intent.putExtra(Constants.JSON_TASK_LIST, json);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // At activity stop: Store current task list
        String json = gson.toJson(tasks);
        SharedPreferences settings = getSharedPreferences(Constants.TASK_STORAGE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("tasksAsJson", json);
        editor.apply();
    }

}
