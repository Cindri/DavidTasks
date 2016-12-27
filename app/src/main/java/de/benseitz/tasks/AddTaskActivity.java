package de.benseitz.tasks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {
    private EditText taskTitle;
    private EditText taskNote;
    private Button saveButton;
    private CheckBox importantBox;

    private Gson gson = new Gson();
    private Type token = new TypeToken<ArrayList<Task>>(){}.getType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskTitle = (EditText) findViewById(R.id.taskTitle);
        taskNote = (EditText) findViewById(R.id.taskNote);
        saveButton = (Button) findViewById(R.id.addTask);
        importantBox = (CheckBox) findViewById(R.id.checkBox);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Get values of new task
                Task newTask = new Task(taskTitle.getText().toString(), taskNote.getText().toString(), importantBox.isChecked());

                // Create ArrayList from JSON

                SharedPreferences settings = getSharedPreferences(Constants.TASK_STORAGE_NAME, 0);

                ArrayList<Task> taskList = gson.fromJson(settings.getString("tasksAsJson", ""), token);
                taskList.add(newTask);

                // Save new task to SharedPreferences
                String tasks = gson.toJson(taskList);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("tasksAsJson", tasks);
                editor.apply();

                Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
