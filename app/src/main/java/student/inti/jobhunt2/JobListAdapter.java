package student.inti.jobhunt2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class JobListAdapter extends ArrayAdapter<Job> {
    private Context context;
    private List<Job> jobList;

    public JobListAdapter(Context context, List<Job> jobList) {
        super(context, 0, jobList);
        this.context = context;
        this.jobList = jobList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.job_item, parent, false);
        }


        Job job = jobList.get(position);


        TextView titleTextView = convertView.findViewById(R.id.textViewJobTitle);
        TextView companyTextView = convertView.findViewById(R.id.textViewCompany);

        titleTextView.setText(job.getTitle());
        companyTextView.setText(job.getCompany());

        return convertView;
    }
}
