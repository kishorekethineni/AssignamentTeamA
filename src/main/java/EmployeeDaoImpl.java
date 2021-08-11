import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeDaoImpl extends DBConnection implements EmployeeDao{


    @Override
    public HashMap<String,String> Insert(EmployeeModel employee) {
        HashMap<String,String> resultMap=new HashMap<>();
        try {
            Connection c = getConnection();
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement(
                        "INSERT INTO Employee2 (EmployeeId, EmployeeName, Dob,age,PhoneNumber,Email,Doj)\n" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?);",
                        new String[]{"EmployeeId"});

                ps.setString(1, employee.getEmployeeId());
                ps.setString(2, employee.getEmployeeName());
                ps.setString(3, employee.getDob());
                ps.setInt(4, employee.getAge());
                ps.setString(5, employee.getPhoneNumber());
                ps.setString(6, employee.getEmail());
                ps.setString(7, employee.getDoj());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    String id = rs.getString(0);
                    employee.setEmployeeId(id);
                }
                resultMap.put(UTILS.KEY,"Success");
                resultMap.put(UTILS.MSG,"Inserted");
                return resultMap;
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put(UTILS.KEY,"Failed");
                resultMap.put(UTILS.MSG,e.getMessage());
                return resultMap;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resultMap.put(UTILS.KEY,"Failed");
            resultMap.put(UTILS.MSG,throwables.getMessage());
            return resultMap;
        }
    }

    @Override
    public List<EmployeeModel> GetAll() {
        List<EmployeeModel> list = new ArrayList<EmployeeModel>();
        Connection c = null;
        String sql = "SELECT * FROM Employees.Employee2";
        try {
            c = DBConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBConnection.close(c);
        }
        return list;
    }

    @Override
    public EmployeeModel GetSpecific(String id) {
        String sql = "SELECT * FROM Employee2 WHERE EmployeeId = ?";
        EmployeeModel Employee = null;
        Connection c = null;
        try {
            c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBConnection.close(c);
        }
        return Employee;
    }

    @Override
    public HashMap<String,String> Delete(String id) {
        HashMap<String, String> hashMap=new HashMap<>();
        Connection c = null;
        try {
            c = DBConnection.getConnection();
            PreparedStatement ps = c
                    .prepareStatement("DELETE FROM Employee2 WHERE EmployeeId=?");
            ps.setString(1, id);
            int count = ps.executeUpdate();
            if (count==1){
                hashMap.put(UTILS.KEY,"Success");
                hashMap.put(UTILS.MSG,"Deleted");
            }else{
                hashMap.put(UTILS.KEY,"Failed");
                hashMap.put(UTILS.MSG,"Not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            hashMap.put(UTILS.KEY,"Failed");
            hashMap.put(UTILS.MSG,e.getMessage());
        }
        return hashMap;
    }

    @Override
    public HashMap<String, String> Update(EmployeeModel employee) {
        HashMap<String, String> hashMap=new HashMap<>();
        try {
            Connection c = getConnection();
            PreparedStatement ps = c
                    .prepareStatement("UPDATE Employee2 SET EmployeeName=?, Dob=?, age=?, PhoneNumber=?, Email=?, Doj=?  WHERE EmployeeId=?");
            ps.setString(1, employee.getEmployeeName());
            ps.setString(2, employee.getDob());
            ps.setInt(3,    employee.getAge());
            ps.setString(4, employee.getPhoneNumber());
            ps.setString(5, employee.getEmail());
            ps.setString(6, employee.getDoj());
            ps.setString(7, employee.getEmployeeId());
            ps.executeUpdate();
            hashMap.put(UTILS.KEY,"Success");
            hashMap.put(UTILS.MSG,"Updated");
        } catch (SQLException e) {
            e.printStackTrace();
            hashMap.put(UTILS.KEY,"Failed");
            hashMap.put(UTILS.MSG,e.getMessage());
            throw new RuntimeException(e);
        }
        return hashMap;
    }
}