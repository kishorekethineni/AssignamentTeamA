import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {


    private static DBConnection instance;

    private final String connectionUrl = "jdbc:mysql://mysqljava.cokejsmbaxif.us-east-2.rds.amazonaws.com/Employees?useUnicode=true&characterEncoding=UTF-8&user=admin&password=8247745215";


    public DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
         
        }
        try {
            return DriverManager.getConnection(instance.connectionUrl);
        } catch (SQLException throwable) {
            throw throwable;
        }
    }

    public static void close(Connection connection)
    {
        try {
            if (connection != null) {
                connection.close();
                connection=null;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    protected EmployeeModel processRow(ResultSet resultset) throws SQLException {
        EmployeeModel Employee = new EmployeeModel();
        Employee.setEmployeeId(resultset.getString("EmployeeId"));
        Employee.setEmployeeName(resultset.getString("EmployeeName"));
        Employee.setDateOfBirth(resultset.getString("Dob"));
        Employee.setAge(resultset.getInt("age"));
        Employee.setPhoneNumber(resultset.getString("PhoneNumber"));
        Employee.setEmail(resultset.getString("Email"));
        Employee.setDateOfJoining(resultset.getString("Doj"));
        return Employee;
    }

}
