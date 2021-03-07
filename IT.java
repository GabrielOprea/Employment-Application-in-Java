public class IT extends Department{
    double sum = 0;
    public double getTotalSalaryBudget(){
        for (Employee Emp1 : this.getEmployees())
                sum = sum + Emp1.salary;
        return sum;
    }
}
