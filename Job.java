import java.util.ArrayList;
import java.util.LinkedList;

public class Job {
    private String jobName;
    private String companyName;
    private boolean disponible;

    private Constraint graduationYear;
    private Constraint experience;
    private Constraint mean;

    private ArrayList<User> candidates;
    private int noPositions;

    public String getJobName() {
        return jobName;
    }

    public Job() {

    }

    public Job(String jobName, String companyName, int noPositions) {
        this.jobName = jobName;
        this.companyName = companyName;
        this.noPositions = noPositions;

    }

    public void setGraduationYear(Constraint graduationYear) {
        this.graduationYear = graduationYear;
    }

    public void setExperience(Constraint experience) {
        this.experience = experience;
    }

    public void setMean(Constraint mean) {
        this.mean = mean;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setUndisponible() {
        this.disponible = false;
    }

    public void setDisponible() {
        this.disponible = true;
    }

    public void apply(User user) {
        //Using Queue for BFS Traversal
        LinkedList<Consumer> queue = new LinkedList<>();
        queue.add(user);
        queue.add(null);
        //We add additional null values to calculate the level
        int level = 0;
        int prevLevel = -1;
        double prevRating = -1.0;
        Recruiter foundRecruiter = null;
        while(queue.size() != 0) {
            Consumer crtConsumer = queue.poll();
            if (crtConsumer == null) {
                level++;
                queue.add(null);
                if (queue.peek() == null) {
                    System.out.println("No recruiter found!");
                }
                else continue;
            }
            if(crtConsumer instanceof Recruiter) {
                if(level > prevLevel) {
                    prevLevel = level;
                    foundRecruiter = (Recruiter) crtConsumer;
                    prevRating = foundRecruiter.getRating();
                }
                else {
                    double crtRating = ((Recruiter) crtConsumer).getRating();
                    if(crtRating > prevRating) {
                        prevRating = crtRating;
                        prevLevel = level;
                        foundRecruiter = (Recruiter) crtConsumer;
                    }
                }
            }
            for (Consumer c : crtConsumer.getConsumersList()) {
                if(!c.isVisited()) {
                    queue.add(c);
                    c.setVisited();
                }
            }
        }
        foundRecruiter.evaluate(this, user);
    }

    public boolean meetsRequirements(User user) {
        double yearsOfExp = 0;
        double graduationYear = 0;
        double mean = 0;
        double noStudies = 0;

        for(Experience e : user.getExperienceList()) {
            yearsOfExp += e.getYears();
        }

        for(Education e : user.getEducationList()) {
            mean += e.getMediefinal();
            noStudies++;
            if(e.getDatasfarsit() == null) graduationYear = 0;
            if(e.getDatasfarsit().getYear() > graduationYear) {
                graduationYear = e.getDatasfarsit().getYear();
            }
        }
        mean /= noStudies;

        boolean meetsReq = true;

        if(mean < this.mean.getInferiorLimit() || mean > this.mean.getSuperiorLimit())
            meetsReq = false;
        if(yearsOfExp < this.experience.getInferiorLimit() || yearsOfExp > this.experience.getSuperiorLimit())
            meetsReq = false;
        if(graduationYear < this.graduationYear.getInferiorLimit() || graduationYear > this.graduationYear.getSuperiorLimit())
            meetsReq = false;
        return meetsReq;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getNoPositions() {
        return noPositions;
    }
}
