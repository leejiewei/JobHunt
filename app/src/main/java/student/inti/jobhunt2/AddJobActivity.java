package student.inti.jobhunt2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddJobActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText companyEditText;
    private EditText locationEditText;
    private Button uploadButton;
    private Button deleteButton;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);


        mDatabase = FirebaseDatabase.getInstance().getReference("jobs");

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        companyEditText = findViewById(R.id.companyEditText);
        locationEditText = findViewById(R.id.locationEditText);
        uploadButton = findViewById(R.id.uploadButton);
        deleteButton = findViewById(R.id.deleteButton);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadJobPost();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void uploadJobPost() {
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String company = companyEditText.getText().toString().trim();
        String location = locationEditText.getText().toString().trim();

        // Simple validation
        if (title.isEmpty() || description.isEmpty() || company.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Job object
        Job job = new Job(title, description, company, location);

        // Push the job data to Firebase
        String jobId = mDatabase.push().getKey();
        if (jobId != null) {
            mDatabase.child(jobId).setValue(job)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddJobActivity.this, "Job Post Uploaded", Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(AddJobActivity.this, JobListActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(AddJobActivity.this, "Failed to upload job post", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void clearFields() {
        titleEditText.setText("");
        descriptionEditText.setText("");
        companyEditText.setText("");
        locationEditText.setText("");
        Toast.makeText(this, "Fields Cleared", Toast.LENGTH_SHORT).show();
    }
}