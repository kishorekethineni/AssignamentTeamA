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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
            System.out.println(" Connection  - - - - - - - -  New DBConnection created");
        }
        try {
            return DriverManager.getConnection(instance.connectionUrl);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void close(Connection connection)
    {
        try {
            if (connection != null) {
                connection.close();
                connection=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected EmployeeModel processRow(ResultSet rs) throws SQLException {
        EmployeeModel Employee = new EmployeeModel();
        Employee.setEmployeeId(rs.getString("EmployeeId"));
        Employee.setEmployeeName(rs.getString("EmployeeName"));
        Employee.setDob(rs.getString("Dob"));
        Employee.setAge(rs.getInt("age"));
        Employee.setPhoneNumber(rs.getString("PhoneNumber"));
        Employee.setEmail(rs.getString("Email"));
        Employee.setDoj(rs.getString("Doj"));
        return Employee;
    }

}
