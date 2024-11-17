package student.inti.jobhunt2;


public class Job {
    private String jobId;
    private String title;
    private String description;
    private String company;
    private String location;


    public Job() {}


    public Job(String title, String description, String company, String location) {
        this.title = title;
        this.description = description;
        this.company = company;
        this.location = location;
    }

    // Getters
    public String getJobId() {
        return jobId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }


    public String displayJobDetails() {
        return "Job Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Company: " + company + "\n" +
                "Location: " + location;
    }
}