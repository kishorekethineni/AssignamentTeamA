import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCExample {

    private Connection connection=null;
    public JDBCExample(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://localhost:3306/Employees?useUnicode=true&characterEncoding=UTF-8&user=root&password=root";
            connection= DriverManager.getConnection(connectionUrl);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public String pushEmployee(EmployeeModel employeeModel){
        String returnResult="";
       if (connection!=null){
           try {
               Statement statement = connection.createStatement();
               statement.executeUpdate(employeeModel.getInsertQuery());
               returnResult="Inserted";
           } catch (Exception e) {
               e.printStackTrace();
               returnResult=e.getMessage();
           }
       }else{
           returnResult="DB not connected";
       }
       return returnResult;
    }

    public List<EmployeeModel> fetchEmployees(){
        List<EmployeeModel> list=new ArrayList<>();
        if (connection!=null) {
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from employee2");
                while (rs.next()) {
                    list.add(new EmployeeModel(
                            rs.getString("EmployeeId"),
                            rs.getString("EmployeeName"),
                            rs.getString("Dob"),
                            rs.getInt("age"),
                            rs.getString("PhoneNumber"),
                            rs.getString("Email"),
                            rs.getString("Doj")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
