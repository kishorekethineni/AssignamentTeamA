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

    public EmployeeModel(String employeeId, String employeeName, String dob, int age, String phoneNumber, String email, String doj) {
        EmployeeId = employeeId;
        EmployeeName = employeeName;
        Dob = dob;
        this.age = age;
        PhoneNumber = phoneNumber;
        Email = email;
        Doj = doj;
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
        return   "---Details of " + EmployeeId + "---\n" +
                "   EmployeeName=" + EmployeeName + "\n" +
                "   EmployeeID=" + EmployeeId+ "\n" +
                "   Dob=" + Dob + "\n" +
                "   age=" + age + "\n" +
                "   PhoneNumber=" + PhoneNumber + "\n" +
                "   Email=" + Email + "\n" +
                "   Doj=" + Doj + "\n";
    }

    public String getInsertQuery(){
        return "INSERT INTO Employee2 (EmployeeId, EmployeeName, Dob,age,PhoneNumber,Email,Doj)\n" +
                "VALUES (\'"+EmployeeId+"\',\'"+ EmployeeName+"\',\'"+ Dob+"\',"+age+",\'"+PhoneNumber+"\',\'"+Email+"\',\'"+Doj+"\');";
    }
}
