import java.text.ParseException;
import java.util.HashMap;

public class GetInputs {

    public static EmployeeModel fetchUserData(String id){
        EmployeeModel employeeModel=new EmployeeModel();
        System.out.println("\t\t\t\tWelcome to Aspire...");

        if (id==null) {
            System.out.println("Enter Employee ID");
            employeeModel.setEmployeeId(UTILS.loopTheUser(UTILS.ID_PATTERN, UTILS.getInputAsString(), "Valid Employ Id is required"));
        }else{
            System.out.println("Enter details for "+id);
            employeeModel.setEmployeeId(id);
        }
        System.out.println("Enter Employee Name");
        employeeModel.setEmployeeName(UTILS.loopTheUser(UTILS.NAME_PATTERN,UTILS.getInputAsString(),"Valid Employ Id is required"));

        System.out.println("Enter Employee DOB for exp:dd/MM/yyyy");
        HashMap<String,String> hashMap=UTILS.loopTheUserForDob(UTILS.DATE_PATTERN,UTILS.getInputAsString(),"Valid date  is required");
        employeeModel.setDob(hashMap.get(UTILS.DATE_KEY));
        System.out.println("Employee AGE: "+hashMap.get(UTILS.AGE_KEY));
        if (!hashMap.get(UTILS.MSG).equals("Eligible"))
            System.exit(0);

        System.out.println("Enter Employee Phone Number");
        employeeModel.setPhoneNumber(UTILS.loopTheUser(UTILS.PHONE_PATTERN,UTILS.getInputAsString(),"Valid Phone no. is required"));

        System.out.println("Enter Employee Email");
        employeeModel.setEmail(UTILS.loopTheUser(UTILS.NAME_PATTERN,UTILS.getInputAsString(),"Valid Email Id is required"));

        System.out.println("Enter Employee DOJ for exp: dd/MM/yyyy");
        employeeModel.setDoj(UTILS.loopTheUser(UTILS.DATE_PATTERN,UTILS.getInputAsString(),"Valid Employ Id is required"));

        return employeeModel;
    }

}
