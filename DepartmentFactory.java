public class DepartmentFactory {

    //use getDepartment method to get an Object of type Department
    public Department getDepartment(String departmentType){
        if(departmentType == null){
            return null;
        }
        if(departmentType.equalsIgnoreCase("IT")){
            return new IT();

        } else if(departmentType.equalsIgnoreCase("Marketing")){
            return new Marketing();

        } else if(departmentType.equalsIgnoreCase("Management")){
            return new Management();

        } else if(departmentType.equalsIgnoreCase("Finance")){
            return new Finance();
        }
        return null;
    }
}