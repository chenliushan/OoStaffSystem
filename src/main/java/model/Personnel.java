package model;

/**
 * Created by liushanchen on 16/3/28.
 */
public abstract class Personnel {
    private int id = -1;//is the personnel list index - 1.
    private String name;
    private double salary;
    private String password;
    private String title;

    public Personnel(String name, String password, double salary, String title) {
        this.name = name;
        this.password = password;
        this.salary = salary;
        this.title = title;
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
                '}' + "\n";

    }
}
