package com.emp.constant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmployeeServiceConstant {
	//public static final String APPLICATION_NAME = "EmployeeSelfService";
	public static final String APPLICATION_NAME = "LFC";
	public static final String ERROR_MSG = "error_msg";
	public static final String SUCCESS_MSG = "success_msg";
	public static final String BANKTYPE = "IDBI";

	
	public static String getExtenionFromMultiart(String fileName) {
		return FilenameUtils.getExtension(fileName);
	}
	
	public static boolean isNullOrEmpty(final Collection<?> c) {
		return c == null || c.isEmpty();
	}

	public static boolean isNullOrEmpty(final Map<?, ?> m) {
		return m == null || m.isEmpty();
	}

	public static void staticGarbageCollection() {
		System.gc();
		System.runFinalization();
	}

	public static List<String> getListFromString(String str) {
		List<String> list = Stream.of(str.split(",")).collect(Collectors.toList());
		return list;
	}

	public static List<String> skipToken() {
		List<String> inputString = new ArrayList<String>();
		inputString.add("/");
		inputString.add("AccessLFC");
		inputString.add("Login");
		inputString.add("Logout");
		inputString.add("InvalidateSession");
		return inputString;
	}
	
	public static String StringIsNullJsonObject(JSONObject obj, String parameter) {
         String val = "";
         if(obj.has(parameter)) {
                val = obj.getString(parameter) != null ? obj.getString(parameter) : "";
         }
         obj = null;
         return val;
   }
 
  public static Date DateIsNullJsonObject(JSONObject obj, String parameter) throws JSONException, java.text.ParseException {
 
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
         Date mydate = null;
         if(obj.has(parameter)) {
                if((obj.getString(parameter)!="")) {
                      mydate = sdf.parse(obj.getString(parameter));
                }
         }
         obj = null;
         return mydate;
   }
 
public static Date DateConvertJsonObject(JSONObject obj, String parameter) throws JSONException, java.text.ParseException {
 
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
         Date mydate = null;
         if(obj.has(parameter)) {
                if((obj.getString(parameter)!="")) {
                      String forDate = obj.getString(parameter);
                      mydate = sdf.parse(obj.getString(parameter));
                }
         }
         obj = null;
         return mydate;

  }
	
	
}
