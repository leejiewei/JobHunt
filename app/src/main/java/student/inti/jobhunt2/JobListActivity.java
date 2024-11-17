package student.inti.jobhunt2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JobListActivity extends AppCompatActivity {
    private ListView listViewJobs;
    private ArrayList<Job> jobList;
    private DatabaseReference jobsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        listViewJobs = findViewById(R.id.listViewJobs);
        Button buttonAddJob = findViewById(R.id.buttonAddJob);
        Button buttonProfile = findViewById(R.id.buttonProfile);

        jobList = new ArrayList<>();
        jobsRef = FirebaseDatabase.getInstance().getReference("jobs");


        loadJobListings();

        buttonAddJob.setOnClickListener(v -> {
            Intent intent = new Intent(JobListActivity.this, AddJobActivity.class);
            startActivity(intent);
        });

        buttonProfile.setOnClickListener(v -> {
            Intent intent = new Intent(JobListActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
    }


    private void loadJobListings() {
        jobsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobList.clear();


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the Job object from Firebase
                    Job job = snapshot.getValue(Job.class);
                    if (job != null) {
                        jobList.add(job);
                    }
                }


                JobListAdapter adapter = new JobListAdapter(JobListActivity.this, jobList);
                listViewJobs.setAdapter(adapter);


                listViewJobs.setOnItemClickListener((parent, view, position, id) -> {
                    Job selectedJob = jobList.get(position);

                    Intent intent = new Intent(JobListActivity.this, JobDetailActivity.class);
                    intent.putExtra("jobId", selectedJob.getJobId());
                    intent.putExtra("jobTitle", selectedJob.getTitle());
                    intent.putExtra("jobDescription", selectedJob.getDescription());
                    intent.putExtra("jobCompany", selectedJob.getCompany());
                    intent.putExtra("jobLocation", selectedJob.getLocation());
                    startActivity(intent);
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(JobListActivity.this, "Failed to load jobs: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}