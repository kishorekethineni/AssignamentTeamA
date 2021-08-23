import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UTILS {
    public static final String DATE_KEY="date";
    public static final String AGE_KEY="age";
    public static final String KEY="key";
    public static final String MSG="msg";
    public static final String ID_PATTERN="[ACE]{3}(?!0000)[0-9]{4}";
    public static final String NAME_PATTERN="(?i)(^[a-z])((?![? .,'-]$)[ .]?[a-z]){3,24}$";
    public static final String DATE_PATTERN="^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    public static final String EMAIL_PATTERN="^[a-zA-Z0-9_.+-]+@(?:(?:[a-zA-Z0-9-]+\\.)?[a-zA-Z]+\\.)?(aspiresys|AspireSys)\\.com$";
    public static final String PHONE_PATTERN="^(0|\\+91)?-?[7896]\\d{9}$";

    public static final String ID="ID";
    public static final String IDS="IDS";
    public static final String NAME="NAME";
    public static final String DOB="DOB";
    public static final String DOJ="DOJ";
    public static final String EMAIL="EMAIL";
    public static final String PHONE="PHONE";


    public static String loopTheUser(String pattern,String value,String msg){
        if (validatePattern(pattern,value)){
            return value;
        }else {
            System.out.println(msg);
            return loopTheUser(pattern,getInputAsString(),msg);
        }
    }
    
    
    public static int callMenu(int selection,int[] arr,String msg) {
    	int result=0;
    	for(int i:arr) {
    		if(selection==i) {
        		result= selection;
        	}	
    	}
    	if(result!=0) {
    		return result;
    	}else {
    		System.out.println(msg);
    		return callMenu(getInputAsInt(),arr,msg);
    	}
   
    }


    public static HashMap<String,String> loopTheUserForDob(String pattern,String value,String msg){
        if (validatePattern(pattern,value)){
            HashMap<String,String> resultSet=new HashMap<>();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String[] dates=value.split("/");
            LocalDate todayDate = LocalDate.now();
            LocalDate dobDate = LocalDate.parse(dates[0]+"/"+dates[1]+"/"+dates[2], dtf);
            if (todayDate.isBefore(dobDate)){
                System.out.println("Please don't enter the future Date");
                return loopTheUserForDob(pattern,getInputAsString(),msg);
            }else{
                int age= Period.between(dobDate, todayDate).getYears();
                resultSet.put(AGE_KEY,age+"");
                resultSet.put(DATE_KEY,value);
                resultSet.put(MSG,18<age?60>age?"Eligible":"\nThanks for your efforts, It's time to take rest":"\nYou are too young to Register");
                return resultSet;
            }
        }else {
            System.out.println(msg);
            return loopTheUserForDob(pattern,getInputAsString(),msg);
        }
    }
    public static String loopTheUserForDoj(String pattern,String dob,String value,String msg){
        if (validatePattern(pattern,value)){
            String[] dobdates =dob.split("/");
            String[] dojdates =value.split("/");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          
            LocalDate todayDate = LocalDate.now();
            LocalDate dobDate = LocalDate.parse(dojdates[0]+"/"+dojdates[1]+"/"+dojdates[2], dtf);
            if (todayDate.isBefore(dobDate)){
                System.out.println("Please don't enter the future Date");
                return loopTheUserForDoj(pattern,dob,getInputAsString(),msg);
            }else {
            	int diff=Integer.parseInt(dojdates[2])-Integer.parseInt(dobdates[2]);
            	if (diff>18&&diff<60){
            		return value;
            	}else{
            		System.out.println(msg);
            		return loopTheUserForDoj(pattern,dob,getInputAsString(),msg);
            	}
            }
        }else {
            System.out.println(msg);
            return loopTheUserForDoj(pattern,dob,getInputAsString(),msg);
        }
    }

    public static String loopTheUserForList(List<EmployeeModel> list, String value, String msg){
        String[] status =new String[]{null};
        list.forEach(model -> {
            if (model.getEmployeeId().equals(value)) {
                status[0]= model.getEmployeeId();
            }
        });
        if (status[0]!=null)
            return status[0];
        else{
            System.out.println(msg);
            return loopTheUserForList(list,getInputAsString(),msg);
        }
    }
    public static String loopTheUserForList(List<String> list, String pattern,String value, String msg,String msg2){
    	 if (validatePattern(pattern,value)){
    		 if(list.contains(value)) {							
    			 	
    			 System.out.print(msg2);
    			 return loopTheUserForList(list,pattern,getInputAsString(),msg,msg2);
    		 }else {
    			 return value;
    		 }
    	
         }else {
             System.out.println(msg);
             return loopTheUserForList(list,pattern,getInputAsString(),msg,msg2);
         }
    }

    public static int getInputAsInt(){
    	try{
    		Scanner scanner=new Scanner(System.in);
    	   return scanner.nextInt();
    	}catch(Exception e) {
    		System.out.println("Not in the list,Enter Valid Employee Id");
    		return getInputAsInt();
    	}
      
    }
    public static String getInputAsString(){
        Scanner scanner=new Scanner(System.in);
        return scanner.next();	
    }

    public static boolean validatePattern(String regex,String  value){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
