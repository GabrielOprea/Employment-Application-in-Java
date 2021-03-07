public class Recruiter extends Employee {
    private double rating = 5.0;

    public double getRating() {
        return rating;
    }

    public void incrementRating() {
        rating += 0.1;
    }

    public double evaluate(Job job, User user) {
        double score = rating * user.getTotalScore();
        Application.getInstance().getCompany(job.getCompanyName())
                .getManager().addRequest(new Request(job, user, this, score));
        incrementRating();
        return score;
    }
}
