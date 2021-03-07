import java.util.ArrayList;
import java.util.Collections;

public class Manager extends Employee {

    private ArrayList<Request> requests;

    public Manager() {
        requests = new ArrayList<>();
    }

    public void process(Job job) {
        ArrayList<Request> sortedReq = new ArrayList<>();
        Company wantedCompany = Application.getInstance().getCompany(job.getCompanyName());
        for(Request req : requests) {
            if(req.getKey().equals(job)) {
                sortedReq.add(req);
            }
        }

        Collections.sort(sortedReq);
        ArrayList<User> userList = Application.getInstance().getUsersList();

        for(int i = 0; i < sortedReq.size() && i < job.getNoPositions(); i++) {
            User crtUser = (User) sortedReq.get(i).getValue1();
            if (userList.contains(crtUser)) {
                Employee crtEmployee = crtUser.convert();
                crtEmployee.setCompanyName(job.getCompanyName());

                ArrayList<Department> depList = wantedCompany.getDepartments();
                for (Department d : depList) {
                    if (d.getJobs().contains(job)) {
                        d.add(crtEmployee);
                    }
                }
                userList.remove(crtUser);
            }
        }

        for(int i = job.getNoPositions(); i < sortedReq.size(); i++) {
            wantedCompany.notifyObserver((Observer) sortedReq.get(i).getValue1(),
                    new Notification("Ai fost respins, va rog sa incercati la anul"));
        }
    }
    public void addRequest(Request req) {
        requests.add(req);
    }
}


