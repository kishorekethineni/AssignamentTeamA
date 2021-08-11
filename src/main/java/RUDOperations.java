import java.util.HashMap;

public class RUDOperations extends DBConnection {
    String id;
    public RUDOperations(String id){
        this.id=id;
        EmployeeDao dao = new EmployeeDaoImpl();
        EmployeeModel model=dao.GetSpecific(id);
        System.out.println("1-Delete\n2-update\n3-read");
        int answer= UTILS.getInputAsInt();
        if (answer==3)
            System.out.println(model.toString());
        else if (answer==1){
            HashMap<String,String> hashMap=dao.Delete(id);
            System.out.println(hashMap.get(UTILS.MSG));
        }else if (answer==2){
            EmployeeModel uModel=GetInputs.fetchUserData(id);
            HashMap<String,String> hashMap=dao.Update(uModel);
            System.out.println(hashMap.get(UTILS.MSG));
        }else{
            System.out.println("Not a valid option");
        }
    }
}
