import java.util.HashMap;
import java.util.List;

public class HomeConsole {

    public static void main(String... args){
        System.out.println("...Employee Logs...\n1-Fetch Employees\n2-Add Employee");
        int answer= UTILS.callMenu(UTILS.getInputAsInt(),new int[]{1,2},"Invalid Entry,Choose Correct Entry");
        if (answer==1){
        	String empId=fetchEmployees();
            new RUDOperations(empId);
        }else if(answer==2){
            EmployeeDao  employeedao = new EmployeeDaoImpl();
            HashMap<String,String> hashMap=employeedao.Insert(GetInputs.fetchUserData(null));
            System.out.println(hashMap.get(UTILS.MSG));
            home();
        }
    }

    public static String fetchEmployees() {
         EmployeeDaoImpl  employeedaoimpl=new EmployeeDaoImpl();
    	 List<EmployeeModel> list= employeedaoimpl.GetAll();
         for (EmployeeModel model:list){
        	 System.out.println(model);
         }
         System.out.println("Select Employee");
         return UTILS.loopTheUserForList(list,UTILS.getInputAsString(),"Not in the list,Enter Valid Employee Id");
    }
    public static void home() {
   	    System.out.println("...Employee Logs...\n1-Fetch Employees\n2-Add Employee\n3-Exit");
        int answer2= UTILS.callMenu(UTILS.getInputAsInt(),new int[]{1,2,3},"Invalid Entry,Choose Correct Entry");
        if(answer2==1) {
        	String empid2=HomeConsole.fetchEmployees();
        	new RUDOperations(empid2);
        }else if(answer2==2) {
       	    EmployeeDao employeedao = new EmployeeDaoImpl();
            HashMap<String,String> hashMap=employeedao.Insert(GetInputs.fetchUserData(null));
            System.out.println(hashMap.get(UTILS.MSG));
        }else {
       	    System.out.println("Thank you");
         	System.exit(0);
        }
    }
}
