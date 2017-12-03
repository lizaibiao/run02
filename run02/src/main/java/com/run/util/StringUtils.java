package com.run.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StringUtils {

	
	/**
	 * String转List(默认对“，”进行分割)
	 * @param ids
	 * @return
	 */
	public static List<Object> StringToList(String ids,String tip){
		if(ids==null){
			return null;
		}
		if(tip==null){
			tip=",";
		}
        List<Object> list = new ArrayList<Object>();  
        Object []  arr =ids.split(tip);
        list=Arrays.asList(arr);		
		return list;
	} 
	
	public static void main(String[] args) {

	}

}
