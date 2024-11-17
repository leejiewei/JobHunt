package student.inti.jobhunt2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ApplyJobActivity extends AppCompatActivity {

    private TextView applyJobDetailsTextView;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText contactNumberEditText;
    private Button submitApplicationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applyjobactivity);

        applyJobDetailsTextView = findViewById(R.id.applyJobDetailsTextView);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        contactNumberEditText = findViewById(R.id.contactNumberEditText);
        submitApplicationButton = findViewById(R.id.buttonSubmitApplication);


        String jobTitle = getIntent().getStringExtra("jobTitle");
        String jobDescription = getIntent().getStringExtra("jobDescription");
        String jobCompany = getIntent().getStringExtra("jobCompany");
        String jobLocation = getIntent().getStringExtra("jobLocation");

        // Construct the job application details string
        String applicationDetails = "Applying for Job:\n\n" +
                "Job Title: " + jobTitle + "\n" +
                "Description: " + jobDescription + "\n" +
                "Company: " + jobCompany + "\n" +
                "Location: " + jobLocation;


        applyJobDetailsTextView.setText(applicationDetails);


        submitApplicationButton.setOnClickListener(v -> submitApplication());
    }

    private void submitApplication() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String contactNumber = contactNumberEditText.getText().toString().trim();


        if (name.isEmpty() || email.isEmpty() || contactNumber.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        Toast.makeText(this, "Application submitted successfully!", Toast.LENGTH_SHORT).show();


        clearFields();
    }

    private void clearFields() {
        nameEditText.setText("");
        emailEditText.setText("");
        contactNumberEditText.setText("");
    }
}