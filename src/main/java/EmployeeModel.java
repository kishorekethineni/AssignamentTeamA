public class EmployeeModel {
    private String EmployeeId;
    private String EmployeeName;
    private String Dob;
    private int age;
    private String PhoneNumber;
    private String Email;
    private String Doj;

    public EmployeeModel() {
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDoj() {
        return Doj;
    }

    public void setDoj(String doj) {
        Doj = doj;
    }

    @Override
    public String toString() {
        return "Employee Details{" +
                "EmployeeId='" + EmployeeId + '\'' +
                ", EmployeeName='" + EmployeeName + '\'' +
                ", Dob='" + Dob + '\'' +
                ", age=" + age +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Email='" + Email + '\'' +
                ", Doj='" + Doj + '\'' +
                '}';
    }
}