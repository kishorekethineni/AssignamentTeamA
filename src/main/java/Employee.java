import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.io.*;


/*
Ceated by Team A
Kishore Pyla
Sushmitha
VenkatSai
Kishore K

Description: Project on Employee Detail
Created Date:27-07-2021
Modified By:Kishore K
Modified On:04-08-2021 01:10 pm
Reviewed By: Jaya
*/

public class Employee extends EmployeeDaoImpl{

    public  static final String AGE_KEY="age";
    public  static final String MSG_KEY="msg";
    public static void main(String... employee)  throws Exception {


//        System.out.println(fetchUserData(connecttoDB).toString());
        System.out.println("Do you want to fetch all data 1=yes, 0=No");
        int fetch=getInputAsInt();
//        if (fetch==1)
//            System.out.println(Arrays.toString(connecttoDB.fetchEmployees().toArray(new EmployeeModel[0])));
    }


    private static EmployeeModel fetchUserData(){
        EmployeeModel employeeModel=new EmployeeModel();
        System.out.println("\t\t\t\tWelcome to Aspire...");
        System.out.println("Enter Employee ID");
        employeeModel.setEmployeeId(getEmployeeID());
        System.out.println("Enter Employee Name");
        employeeModel.setEmployeeName(getEmployeeName());
        System.out.println("Enter Employee DOB for exp:dd/MM/yyyy");
        try{
        /*

          The try statement allows you to define a block of code to be tested for errors while it is being executed.

         */
            String dob=getEmployeeDob();
            employeeModel.setDateOfBirth(dob);
            System.out.println("Enter Employee AGE");
            HashMap<String,String> hashMap=getEmployeeAge(dob);
            System.out.println("Age: "+hashMap.get(AGE_KEY)+" "+hashMap.get(MSG_KEY));
            if (!hashMap.get(MSG_KEY).equals("Eligible"))
                System.exit(0);
        } catch (ParseException | AgeException e) {
        /*

           The catch statement allows you to define a block of code to be executed, if an error occurs in the try block.

         */
            if (e.getClass()==AgeException.class){
                System.out.println(((AgeException) e).getMessage());
            }else
                System.out.println("Parsing Excep: date is not in good format");
            e.printStackTrace();
        }


        System.out.println("Enter Employee Phone Number");
        employeeModel.setPhoneNumber(getEmployeePhoneNumber());
        System.out.println("Enter Employee Email");
        employeeModel.setEmail(getEmployeeEmail());
        System.out.println("Enter Employee DOJ for exp: dd/MM/yyyy");
        employeeModel.setDateOfJoining(getEmployeeDoj());

        return employeeModel;
    }
    private static String getEmployeeID(){
        String Name=getInputAsString();
        if (Name==null){
            System.out.println("Please enter ID");
            return getEmployeeID();
        }else {
            if (validatePattern("[A-Z]{3}(?!0000)[0-9]{4}",Name)&&Name.toLowerCase().contains("ace")){
                return Name;
            } else {
                System.out.println("Please enter valid Emp Id");
                return getEmployeeID();
            }
        }
    }


    private static boolean validatePattern(String regex,String  value){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    private static String getEmployeeName(){
        String Name=getInputAsString();
        if (Name==null){
            System.out.println("Please enter name");
            return getEmployeeName();
        }else {
            if (validatePattern("(?i)(^[a-z])((?![? .,'-]$)[ .]?[a-z]){3,24}$",Name))
                return Name;
            else {
                System.out.println("Not a proper name");
                return getEmployeeName();
            }
        }
    }

    /*
      The throw statement allows you to create a custom error.

     */
    private static String getEmployeeDob() throws ParseException, AgeException {
        String dob=getInputAsString();
        if (dob==null){
            System.out.println("Please enter Date");
            return getEmployeeDob();
        }else {
            if (!dob.contains("/")){
                System.out.println("Please use / to sepearate Date");
                return getEmployeeDob();
            }else{
                String[] dates=dob.split("/");
                if (dates.length!=3) {
                    System.out.println("Please enter valid Date");
                    return getEmployeeDob();
                }else{
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate todayDate = LocalDate.now();
                    LocalDate dobDate = LocalDate.parse(dates[0]+"/"+dates[1]+"/"+dates[2], dtf);
                    if (todayDate.isBefore(dobDate)){
                        System.out.println("Please don't enter the future Date");
                        return getEmployeeDob();
                    }else{
                       return dob;
                    }
                }
            }
        }
    }

    private static HashMap<String,String> getEmployeeAge(String dob){
        HashMap<String,String> hashMap=new HashMap<>();
        String[] dates=dob.split("-");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dobDate = LocalDate.parse(dates[0]+"/"+dates[1]+"/"+dates[2], dtf);
        LocalDate todayDate = LocalDate.now();
        int age=Period.between(dobDate, todayDate).getYears();
        if (age>16&&age<60) {
            hashMap.put(AGE_KEY,String.valueOf(age));
            hashMap.put(MSG_KEY,"Eligible");
        }else{
            String msg="";
            if (age<16)
                msg="You are to young to register and your age is "+age;
            else if(age>60)
                msg="Thanks for all your efforts for "+age+" years. " +
                        "Its time to enjoy";
            hashMap.put(AGE_KEY,String.valueOf(age));
            hashMap.put(MSG_KEY,msg);
        }
        return hashMap;
    }

    private static String getEmployeePhoneNumber(){
        String phoneNumber=getInputAsString();
        if (phoneNumber==null) {
            System.out.println("Please enter Phone Number");
            return getEmployeePhoneNumber();
        }else{
            if (validatePattern("^(0|\\+91)?-?[7896]\\d{9}$",phoneNumber))
                return phoneNumber;
            else {
                System.out.println("Phone Number should be 10 digits");
                return getEmployeePhoneNumber();
            }
        }
    }
    private static String getEmployeeEmail(){
        String phoneNumber=getInputAsString();
        if (phoneNumber==null) {
            System.out.println("Please enter Email");
            return getEmployeeEmail();
        }else{
            String regex="^[a-zA-Z0-9_.+-]+@(?:(?:[a-zA-Z0-9-]+\\.)?[a-zA-Z]+\\.)?(aspiresys|AspireSys)\\.com$";
//            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches())
                return phoneNumber;
            else {
                System.out.println("Email id should contains @ and .");
                return getEmployeeEmail();
            }
        }
    }

    private static String getEmployeeDoj(){
        String date=getInputAsString();
        if (date==null) {
            System.out.println("Please enter date ");
            return getEmployeeDoj();
        }else{
            return date;
        }
    }

    private static String getInputAsString(){
        Scanner scanner=new Scanner(System.in);
        return scanner.next();
    }

    private static int getInputAsInt(){
        Scanner scanner=new Scanner(System.in);
        return scanner.nextInt();
    }
}
