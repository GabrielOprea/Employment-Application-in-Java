import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Department {

    private ArrayList<Employee> EmployeesList;
    private ArrayList<Job> JobsList;
    private Company owner;

    public Department() {
        JobsList = new ArrayList<>();
        EmployeesList = new ArrayList<>();
    }

    public abstract double getTotalSalaryBudget();

    public void setOwner(Company owner) {
        this.owner = owner;
    }

    public ArrayList<Job> getJobs() {
        return JobsList;
    }

    public void makeUndisponible(Job job) {
        for(Job j: JobsList) {
            if(j.equals(job)) {
                j.setUndisponible();
                owner.notifyAllObservers(new Notification("Jobul " + j.getJobName() + "este indisponibil!" ));
            }
        }
    }

    public void add(Employee employee) {
        EmployeesList.add(employee);
    }

    public void remove(Employee employee) {
        EmployeesList.remove(employee);
    }

    public void add(Job job) {
        JobsList.add(job);
        owner.notifyAllObservers(new Notification("Job nou: " + job.getJobName()));
    }

    public ArrayList<Employee> getEmployees() {
        return EmployeesList;
    }

    public void setEmployees(ArrayList<Employee> employeesList) {
        EmployeesList = employeesList;
    }
}
