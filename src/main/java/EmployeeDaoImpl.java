import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EmployeeDaoImpl extends DBConnection implements EmployeeDao{

    private static final String BASE_URL="https://ancient-harbor-78065.herokuapp.com/api/";
    @Override
    public HashMap<String,String> Insert(EmployeeModel employee) {
        System.out.println("Loading...");
        HashMap<String,String> hashMap=new HashMap<>();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,employee.toJsonObject());
        Request request = new Request.Builder()
                .url(BASE_URL+"employees")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString=response.body().string();
            JsonElement jelement = new JsonParser().parse(responseString);
            JsonObject  jobject = jelement.getAsJsonObject();
            if (jobject.has("message")){
                hashMap.put(UTILS.MSG,jobject.get("message").getAsString());
//                hashMap.put(UTILS.KEY,jobject.get("employeeId").getAsString());
            }else{
                hashMap.put(UTILS.MSG,"Unable to connect to server");
                hashMap.put(UTILS.KEY,"");
            }
        } catch (IOException e) {
            e.printStackTrace();
            hashMap.put(UTILS.MSG,e.getMessage());
            hashMap.put(UTILS.KEY,"");
        }
        return hashMap;
    }

    @Override
    public List<EmployeeModel> GetAll() {
        System.out.println("Loading...");
        List<EmployeeModel> list = new ArrayList<EmployeeModel>();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL+"employees")
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString=response.body().string();
            Gson gson = new Gson();
            Type listType = new TypeToken<List<EmployeeModel>>(){}.getType();
            list .addAll( gson.fromJson(responseString, listType));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public EmployeeModel GetSpecific(String id) {
        System.out.println("Loading...");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL+"employees?id="+id)
                .method("GET", null)
                .build();
        try{
            Response response = client.newCall(request).execute();
            String responseString=response.body().string();
            Gson gson = new Gson();
            return gson.fromJson(responseString, EmployeeModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public HashMap<String,String> Delete(String id) {
        System.out.println("Loading...");
        HashMap<String, String> hashMap=new HashMap<>();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(BASE_URL+"deleteEmployee/"+id)
                .method("DELETE", body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            JsonElement jelement = new JsonParser().parse(response.body().string());
            JsonObject  jobject = jelement.getAsJsonObject();
            if (jobject.has("message")){
                hashMap.put(UTILS.MSG,jobject.get("message").getAsString());
                hashMap.put(UTILS.KEY,jobject.get("employeeId").getAsString());
            }else{
                hashMap.put(UTILS.MSG,"Unable to connect to server");
                hashMap.put(UTILS.KEY,"Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            hashMap.put(UTILS.MSG,e.getMessage());
            hashMap.put(UTILS.KEY,"Failed");
        }
        return hashMap;
    }

    @Override
    public HashMap<String, String> Update(EmployeeModel employee) {
        System.out.println("Loading...");
        HashMap<String, String> hashMap=new HashMap<>();
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, employee.toJsonObject());
            Request request = new Request.Builder()
                    .url(BASE_URL+"updateEmployee")
                    .method("PUT", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            JsonElement jelement = new JsonParser().parse(response.body().string());
            JsonObject  jobject = jelement.getAsJsonObject();
            if (jobject.has("message")){
                hashMap.put(UTILS.MSG,jobject.get("message").getAsString());
                hashMap.put(UTILS.KEY,jobject.get("employeeId").getAsString());
            }else{
                hashMap.put(UTILS.MSG,"Unable to connect to server");
                hashMap.put(UTILS.KEY,"Failed");
            }
        } catch (Exception throwables) {
        	throwables.printStackTrace();
            hashMap.put(UTILS.KEY,"Failed");
            hashMap.put(UTILS.MSG,throwables.getMessage());
        }
        return hashMap;
    }

	@Override
	public List<String> fetchIds(String coloumn) {
        System.out.println("Loading...");
		 List<String> list = new ArrayList<String>();
	        Connection connection = null;
	        String sql = "SELECT "+coloumn+" FROM Employees.Employee2";
	        try {
	            connection=getConnection();
	            Statement statement = connection.createStatement();
	            ResultSet resultset = statement.executeQuery(sql);
	            while (resultset.next()) {
	                list.add(resultset.getString(1));
	            }
	        } catch (SQLException throwables) {
	        	throwables.printStackTrace();
	            throw new RuntimeException(throwables);
	        } finally {
	            DBConnection.close(connection);
	        }
	        return list;
	}
}