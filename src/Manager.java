public class Manager {

    private final String managerTc;
    private String managerName;
    private Department managerDepartment;

    public Manager(
            String managerTc,
            String managerName,
            Department managerDepartment
    ){
        this.managerTc = managerTc;
        this.managerName = managerName;
        this.managerDepartment = managerDepartment;
    }

    public Manager(
            String managerTc
    ){
        this.managerTc = managerTc;
    }

    public String getManagerTc() {
        return managerTc;
    }

    public String getManagerName() {
        return managerName;
    }

    public Department getManagerDepartment() {
        return managerDepartment;
    }


}
