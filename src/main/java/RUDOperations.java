import java.util.HashMap;

public class RUDOperations extends DBConnection {
    String id;
    public RUDOperations(String id){
        this.id=id;
        EmployeeDao employeedao = new EmployeeDaoImpl();
        EmployeeModel employeemodel=employeedao.GetSpecific(id);
        if (employeemodel!=null)
            System.out.println("1-Delete\n2-update\n3-read\n4-Exit");
        else
            System.exit(0);
        int answer= UTILS.callMenu(UTILS.getInputAsInt(),new int[]{1,2,3,4},"Invalid Entry,Choose Correct Entry");
        if (answer==3) {
            System.out.println(employeemodel.toString());
            HomeConsole.home();
        }else if (answer==1){
            HashMap<String,String> hashMap=employeedao.Delete(id);
            System.out.println(hashMap.get(UTILS.MSG));
            HomeConsole.home();
        }else if (answer==2){
            EmployeeModel uModel=GetInputs.fetchUserData(id);
            HashMap<String,String> hashMap=employeedao.Update(uModel);
            System.out.println(hashMap.get(UTILS.MSG));
            HomeConsole.home();
        }else {
        	System.out.println("Thank you");
          	System.exit(0);

         }
    }
    
}
