package de.benseitz.tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView notiz;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        notiz = (TextView) findViewById(R.id.detailnotiz);
        Task task = (Task) getIntent().getSerializableExtra(Constants.TASK_DETAIL_KEY);
        this.setTitle(task.getTitel());
        notiz.setText(task.getNotiz());
    }
}
