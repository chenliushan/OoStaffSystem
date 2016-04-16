package model;

import java.util.List;

/**
 * Created by liushanchen on 16/3/28.
 */
public abstract class Personnel extends Handler {

    private int id = -1;//is the personnel list index - 1.
    private String name;
    private double salary;
    private String password;
    private String title;

    public Personnel(Personnel supervisor,String name, String password, double salary, String title) {
        super(supervisor);
        this.name = name;
        this.password = password;
        this.salary = salary;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void handleApplication(LeavingApplication la, boolean decision){
        if(decision){
            endorse(la);
        }else{
            decline(la);
        }
    }
    public List<LeavingApplication> toBeHandleLa(){
       return getAllShouldBeHandle();
    }

    public int getSupervisorId(){
        if(this.getSupervisor()!=null && this.getSupervisor() instanceof Personnel){
            Personnel p=(Personnel) this.getSupervisor();
           return p.getId();
        }else{
            return -1;
        }
    }
    public String getSupervisorName(){
        if(this.getSupervisor()!=null && this.getSupervisor() instanceof Personnel){
            Personnel p=(Personnel) this.getSupervisor();
            return p.getName();
        }else{
            return "NA";
        }
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInfo(String name, String password, double salary) {
        this.name = name;
        this.password = password;
        this.salary = salary;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return "Personnel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", password='" + password + '\'' +
                ", title='" + title + '\'' +
                ", supervisor='" + getSupervisor() + '\'' +
                '}' + "\n";

    }
    public static class PersonnelStr{
        public static final String ID = "Staff Id";
        public static final String NAME = "Staff Name";
        public static final String SALARY = "Staff Salary";
        public static final String PASSWORD = "Password";
        public static final String TITLE = "Staff Title";

    }
}
