public class Management extends Department{
    double sum = 0;
    public double getTotalSalaryBudget(){
        for (Employee Emp1 : this.getEmployees()) {
            Emp1.salary = Emp1.salary + 0.16 * Emp1.salary;
            sum = sum + Emp1.salary;
        }
        return sum;
    }
}