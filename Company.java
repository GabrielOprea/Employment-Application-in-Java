import java.util.ArrayList;

public class Company implements Subject {
    private String name;
    private Manager manager;
    private ArrayList<Department> departments;
    private ArrayList<Recruiter> recruiters;
    private ArrayList<Observer> observers;

    public Company() {
        departments = new ArrayList<>();
        recruiters = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void setName(String name) { }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public Manager getManager() {
        return manager;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void add(Department department) {
        departments.add(department);
    }
    public void add(Recruiter recruiter) {
        recruiters.add(recruiter);
    }
    public void add(Employee employee, Department department) {
        department.add(employee);
    }
    public void remove(Employee employee) {
        for(Department d : departments) {
            if(d.getEmployees().contains(employee)) {
                d.getEmployees().remove(employee);
            }
        }
    }
    public void remove(Department department) {
        ArrayList<Employee> crtList = new ArrayList<>();
        department.setEmployees(crtList);
        departments.remove(department);
    }
    public void remove(Recruiter recruiter) {
        recruiters.remove(recruiter);
    }
    public void move(Department source, Department destination) {

        if(contains(source)) {
            departments.remove(source);
        }

        if(contains(destination)) {
            departments.remove(destination);
        }

        for(Employee e : source.getEmployees()) {
            destination.getEmployees().add(e);
        }

        for(Job j : source.getJobs()) {
            destination.getJobs().add(j);
        }

        departments.add(destination);

    }
    public void move(Employee employee, Department newDepartment) {
        for(Department d : departments) {
            if (d.getEmployees().contains(employee)) {
                d.remove(employee);
            }
        }

        newDepartment.add(employee);
    }
    public boolean contains(Department department) {
        return departments.contains(department);
    }
    public boolean contains(Employee employee) {
        for(Department d : departments) {
            if(d.getEmployees().contains(employee)) {
                return true;
            }
        }
        return false;
    }
    public boolean contains(Recruiter recruiter) {
        return recruiters.contains(recruiter);
    }
    public Recruiter getRecruiter(User user) {
        return null;
    }
    public ArrayList<Job> getJobs() {
        ArrayList<Job> jobList = new ArrayList<>();
        for(Department d : departments) {
            for(Job j : d.getJobs()) {
                if(j.isDisponible()) {
                    jobList.add(j);
                }
            }
        }
        return jobList;
    }

    @Override
    public void addObserver(User user) {
        observers.add(user);
    }

    @Override
    public void removeObserver(User c) {
        if(observers.contains(c)) {
            observers.remove(c);
        }
    }

    @Override
    public void notifyAllObservers(Notification notification) {
        for(Observer o : observers) {
            o.update(notification);
        }
    }

    @Override
    public void notifyObserver(Observer o, Notification notification) {
        o.update(notification);
    }
}
