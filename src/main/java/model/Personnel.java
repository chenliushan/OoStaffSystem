package model;

/**
 * Created by liushanchen on 16/3/28.
 */
public abstract class Personnel {
    private int id=-1;
    private String name;
    private double salary;
    private String password;

    public Personnel(String name, String password, double salary) {

        this.name = name;
        this.password = password;
        this.salary = salary;
    }

    public int getId() {
        return id;
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

    public abstract boolean applyALeave(String startDate, String endDate);

    public abstract boolean HandleLeaveApplication(boolean decision);

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

    @Override
    public String toString() {
        return "Personnel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
