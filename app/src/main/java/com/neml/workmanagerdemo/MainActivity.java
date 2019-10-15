package com.neml.workmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
//OneTimeWorkRequest: Used when we want to perform the work only once.
//PeriodicWorkRequest: Used when we need to perform the task periodically.
        //A click listener for the button
        //inside the onClick method we will perform the work

        findViewById(R.id.buttonEnqueue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Enqueuing the work request
                WorkManager.getInstance().enqueue(workRequest);
            }
        });
    }
}
