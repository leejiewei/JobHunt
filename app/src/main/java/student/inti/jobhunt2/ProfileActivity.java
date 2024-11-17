package student.inti.jobhunt2;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    private EditText editTextNewEmail, editTextNewPassword;
    private Button buttonUpdate;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        editTextNewEmail = findViewById(R.id.newemail);
        editTextNewPassword = findViewById(R.id.newpassword);
        buttonUpdate = findViewById(R.id.update);

        mAuth = FirebaseAuth.getInstance();

        buttonUpdate.setOnClickListener(v -> updateProfile());
    }

    private void updateProfile() {
        String newEmail = editTextNewEmail.getText().toString().trim();
        String newPassword = editTextNewPassword.getText().toString().trim();

        if (newEmail.isEmpty() && newPassword.isEmpty()) {
            Toast.makeText(this, "Please enter new email or password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update Email
        if (!newEmail.isEmpty()) {
            mAuth.getCurrentUser().updateEmail(newEmail)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ProfileActivity.this, "Email updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ProfileActivity.this, "Failed to update email: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        // Update Password
        if (!newPassword.isEmpty()) {
            mAuth.getCurrentUser().updatePassword(newPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ProfileActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ProfileActivity.this, "Failed to update password: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}