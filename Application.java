import java.lang.reflect.Array;
import java.util.ArrayList;

public class Application {
    private ArrayList<Company> CompaniesList;
    private ArrayList<User> UsersList;
    private static Application INSTANCE = null;

    private Application() {
        CompaniesList = new ArrayList<>();
        UsersList = new ArrayList<>();
    }

    public static Application getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Application();
        }
        return INSTANCE;
    }

    public ArrayList<Company> getCompanies() {
        return CompaniesList;
    }

    public ArrayList<User> getUsersList() {
        return UsersList;
    }

    public Company getCompany(String name) {
        for (Company Comp1 : CompaniesList)
            if (Comp1.getName().equals(name))
                return Comp1;
            return null;
    }


    public void add(Company company) {
        CompaniesList.add(company);
    }

    public void add(User user) {
        UsersList.add(user);
    }

    public boolean remove(Company company) {
        if (!CompaniesList.contains(company))
            return false;
        else {
            CompaniesList.remove(company);
            return true;
        }
    }

    public boolean remove(User user) {
        if (!UsersList.contains(user))
            return false;
        else {
            UsersList.remove(user);
            return true;
        }
    }

    public ArrayList<Job> getJobs(ArrayList<String> companies){
        ArrayList<Job> jobs = new ArrayList<>();
        for (String name : companies) {
            Company crtCompany = getCompany(name);
            for(Job j : crtCompany.getJobs()) {
                if(j.isDisponible())
                    jobs.add(j);
            }
        }
        return jobs;
    }


}