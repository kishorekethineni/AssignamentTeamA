import java.text.ParseException;
import java.util.HashMap;

public class GetInputs {

    public static EmployeeModel fetchUserData(String id){
        EmployeeModel employeeModel=new EmployeeModel();
        System.out.println("\t\t\t\tWelcome to Aspire...");

        if (id==null) {
            System.out.println("Enter Employee ID");
            EmployeeDaoImpl employeedaoimpl=new EmployeeDaoImpl();
            String empid=UTILS.loopTheUserForList(employeedaoimpl.fetchIds("EmployeeId"),UTILS.ID_PATTERN, UTILS.getInputAsString(), "Enter Valid Employee ID","Employee Already Exists");
          
            employeeModel.setEmployeeId(empid);
        }else{
            System.out.println("Enter details for "+id);
            employeeModel.setEmployeeId(id);
        }
        System.out.println("Enter Employee Name");
        employeeModel.setEmployeeName(UTILS.loopTheUser(UTILS.NAME_PATTERN,UTILS.getInputAsString(),"Enter Valid Name"));

        System.out.println("Enter Employee DOB for exp:dd/MM/yyyy");
        HashMap<String,String> hashMap=UTILS.loopTheUserForDob(UTILS.DATE_PATTERN,UTILS.getInputAsString(),"Enter Valid Date Of Birth");
        employeeModel.setDateOfBirth(hashMap.get(UTILS.DATE_KEY));
        System.out.println("Employee AGE: "+hashMap.get(UTILS.AGE_KEY) +" "+hashMap.get(UTILS.MSG));
        employeeModel.setAge(Integer.parseInt(hashMap.get(UTILS.AGE_KEY)));
        if (!hashMap.get(UTILS.MSG).equals("Eligible"))
            System.exit(0);

        if (id==null) {
        	 System.out.println("Enter Employee Phone Number");
            EmployeeDaoImpl employeedaoimpl=new EmployeeDaoImpl();
            String phoneno=UTILS.loopTheUserForList(employeedaoimpl.fetchIds("PhoneNumber"),UTILS.PHONE_PATTERN, UTILS.getInputAsString(), "Enter Valid Phone number","Phone Number Already Exists");
            employeeModel.setPhoneNumber(phoneno);
        }else{
        	System.out.println("Enter Employee Phone Number");
            String phoneno=UTILS.loopTheUser(UTILS.PHONE_PATTERN, UTILS.getInputAsString(), "Enter Valid Phone number");
        	employeeModel.setPhoneNumber(phoneno);
        }
       

        if (id==null) {
        	System.out.println("Enter Employee Email");
           EmployeeDaoImpl employeedaoimpl=new EmployeeDaoImpl();
           String email=UTILS.loopTheUserForList(employeedaoimpl.fetchIds("Email"),UTILS.EMAIL_PATTERN, UTILS.getInputAsString(), "Enter Valid Email","Email Already Exists");
           employeeModel.setEmail(email);
       }else{
    	   System.out.println("Enter Employee Email");
           String email=UTILS.loopTheUser(UTILS.EMAIL_PATTERN, UTILS.getInputAsString(), "Enter Valid Email");
       		employeeModel.setEmail(email);
       }
        

        System.out.println("Enter Employee DOJ for exp: dd/MM/yyyy");
        employeeModel.setDateOfJoining(UTILS.loopTheUserForDoj(UTILS.DATE_PATTERN,employeeModel.getDateOfBirth(),UTILS.getInputAsString(),"Enter Valid Date Of Joining"));
        return employeeModel;
    }

}
