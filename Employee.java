import java.util.ArrayList;

public class Employee extends Consumer {
    double salary;
    double exp;  //de calculat cu o metoda experienta total
    String companyName;

    public Employee() {
    }

    public void calculateExp() {
        double totalExp = 0;
        ArrayList<Experience> expList= getExperienceList();

        for(Experience exp : expList) {
            totalExp += exp.getYears();
        }
        exp = totalExp;
    }

    public Employee(double salary, String companyName) {
        this();
        this.salary = salary;
        this.companyName = companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

