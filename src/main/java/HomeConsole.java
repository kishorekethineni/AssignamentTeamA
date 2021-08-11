import java.util.HashMap;
import java.util.List;

public class HomeConsole {

    public static void main(String... kk){
        System.out.println("...Employee Logs...\n1-Fetch Employees\n2-Add Employee");
        EmployeeDaoImpl fe=new EmployeeDaoImpl();
        int answer= UTILS.getInputAsInt();
        if (answer==1){
            List<EmployeeModel> list=fe.GetAll();
            for (EmployeeModel model:list){
                System.out.println(model);
            }
            System.out.println("Select Employee");
            String empId=UTILS.loopTheUserForList(list,UTILS.getInputAsString(),"Not in list");
            new RUDOperations(empId);
        }else if(answer==2){
            EmployeeDao dao = new EmployeeDaoImpl();
            HashMap<String,String> hashMap=dao.Insert(GetInputs.fetchUserData(null));
            System.out.println(hashMap.get(UTILS.MSG));
        }else{
            System.out.println("Wrong input");
        }
    }

}
