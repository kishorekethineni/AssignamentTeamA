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
    public static final String ID_PATTERN="[A-Z]{3}(?!0000)[0-9]{4}";
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
                resultSet.put(AGE_KEY,String.valueOf(age));
                resultSet.put(DATE_KEY,String.valueOf(age));
                resultSet.put(MSG,18<age?60>age?"Eligible":"Over Aged":"Under Aged");
                return resultSet;
            }
        }else {
            System.out.println(msg);
            return loopTheUserForDob(pattern,getInputAsString(),msg);
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

    public static int getInputAsInt(){
        Scanner scanner=new Scanner(System.in);
        return scanner.nextInt();
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
