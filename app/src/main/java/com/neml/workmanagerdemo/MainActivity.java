package com.neml.workmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_TASK_DESC ="key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data data = new Data.Builder()
                .putString(KEY_TASK_DESC,"Sample Data")
                .build();
        final OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).setInputData(data).build();
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


        //Getting the TextView
        final TextView textView = findViewById(R.id.textViewStatus);

        WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        String status =  workInfo.getState().name();
                        textView.append(status + "\n");
                    }
                });
    }
}
