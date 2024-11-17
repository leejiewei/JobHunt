package student.inti.jobhunt2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class JobDetailActivity extends AppCompatActivity {

    private TextView jobDetailsTextView;
    private Button applyJobButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobdetailactivity);

        jobDetailsTextView = findViewById(R.id.jobDetailsTextView);
        applyJobButton = findViewById(R.id.buttonApplyJob);


        String jobTitle = getIntent().getStringExtra("jobTitle");
        String jobDescription = getIntent().getStringExtra("jobDescription");
        String jobCompany = getIntent().getStringExtra("jobCompany");
        String jobLocation = getIntent().getStringExtra("jobLocation");

        // Construct the job details string
        String jobDetails = "Job Title: " + jobTitle + "\n" +
                "Description: " + jobDescription + "\n" +
                "Company: " + jobCompany + "\n" +
                "Location: " + jobLocation;


        jobDetailsTextView.setText(jobDetails);


        applyJobButton.setOnClickListener(v -> {
            Intent intent = new Intent(JobDetailActivity.this, ApplyJobActivity.class);
            intent.putExtra("jobTitle", jobTitle);
            intent.putExtra("jobDescription", jobDescription);
            intent.putExtra("jobCompany", jobCompany);
            intent.putExtra("jobLocation", jobLocation);
            startActivity(intent);
        });
    }
}