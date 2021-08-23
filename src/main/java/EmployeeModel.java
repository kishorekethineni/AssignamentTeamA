public class EmployeeModel {


    private String EmployeeId;

    private String EmployeeName;

    private String DateOfBirth;

    private int Age;

    private String PhoneNumber;

    private String Email;

    private String DateOfJoining;




    public EmployeeModel(String employeeId, String employeeName, String dateOfBirth, int age, String phoneNumber, String email, String dateOfJoining) {
        EmployeeId = employeeId;
        EmployeeName = employeeName;
        DateOfBirth = dateOfBirth;
        Age = age;
        PhoneNumber = phoneNumber;
        Email = email;
        DateOfJoining = dateOfJoining;
    }

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

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
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

    public String getDateOfJoining() {
        return DateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        DateOfJoining = dateOfJoining;
    }

    @Override
    public String toString() {
        return   "---Details of " + EmployeeId + "---\n" +
                "   EmployeeName=" + EmployeeName + "\n" +
                "   EmployeeID=" + EmployeeId+ "\n" +
                "   Dob=" + DateOfBirth + "\n" +
                "   age=" + Age + "\n" +
                "   PhoneNumber=" + PhoneNumber + "\n" +
                "   Email=" + Email + "\n" +
                "   Doj=" + DateOfJoining + "\n";
    }

    public String getInsertQuery(){
        return "INSERT INTO Employee2 (EmployeeId, EmployeeName, Dob,age,PhoneNumber,Email,Doj)\n" +
                "VALUES (\'"+EmployeeId+"\',\'"+ EmployeeName+"\',\'"+ DateOfBirth+"\',"+Age+",\'"+PhoneNumber+"\',\'"+Email+"\',\'"+DateOfJoining+"\');";
    }

    public String toJsonObject(){
        return "{\n" +
                "    \"employeeId\": \""+EmployeeId+"\",\n" +
                "    \"employeeName\": \""+EmployeeName+"\",\n" +
                "    \"dateOfBirth\": \""+DateOfBirth+"\",\n" +
                "    \"age\": "+Age+",\n" +
                "    \"phoneNumber\": \""+PhoneNumber+"\",\n" +
                "    \"email\": \""+Email+"\",\n" +
                "    \"dateOfJoining\": \""+DateOfJoining+"\"\n" +
                "}";
    }
}
