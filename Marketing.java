public class Marketing extends Department{
    double sum = 0;
    public double getTotalSalaryBudget(){
        for (Employee Emp1 : this.getEmployees()) {
            if(Emp1.salary > 5000) {
                Emp1.salary = Emp1.salary + 0.1 * Emp1.salary;
                sum = sum + Emp1.salary;
            }
            if(Emp1.salary < 3000)
                sum = sum + Emp1.salary;
            if(Emp1.salary >= 3000 && Emp1.salary <= 5000 ){
                Emp1.salary = Emp1.salary + 0.16 * Emp1.salary;
                sum = sum + Emp1.salary;
            }
        }
        return sum;
    }
}