import java.util.ArrayList;

public class User extends Consumer implements Observer{
    private ArrayList<String> wantedCompanies;
    private ArrayList<Notification> notifications;

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public Employee convert() {
        Employee e = new Employee();
        e.setExperienceList(getExperienceList());
        e.setEducationList(getEducationList());
        e.setConsumersList(getConsumersList());
        return e;
    }

    public Double getTotalScore() {
        double totalExp = 0;
        for(Experience e : getExperienceList()) {
            totalExp += e.getYears();
        }
        double totalMean = 0;
        double numberOfStudies = getEducationList().size();
        for(Education e : getEducationList()) {
            totalMean += e.getMediefinal();
        }

        totalMean /= numberOfStudies;
        return totalExp * 1.5 + totalMean;

    }

    @Override
    public void update(Notification notification) {
        notifications.add(notification);
    }
}
