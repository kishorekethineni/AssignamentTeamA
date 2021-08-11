import java.util.HashMap;
import java.util.List;

public interface EmployeeDao {


    HashMap<String,String> Insert(EmployeeModel model);

    List<EmployeeModel> GetAll();

    EmployeeModel GetSpecific(String id);

    HashMap<String,String> Delete(String id);

    HashMap<String,String> Update(EmployeeModel model);

}