package com.run.util.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * getAnnotation的用法
 * @author run
 *
 */
public class Test02 {
	
	@Retention(RetentionPolicy.RUNTIME)
	@interface Demo {
	   String str();
	   int val();
	}
	
	
	public static class PackageDemo {

		   @Demo(str = "Demo Annotation", val = 100)
		   public static void example() {
		      PackageDemo ob = new PackageDemo();

		      try {
		         Class c = ob.getClass();
		         Method m = c.getMethod("example");
		         Demo annotation = m.getAnnotation(Demo.class);
		         System.out.println(annotation.str() + " " + annotation.val());
		      } catch (NoSuchMethodException exc) {
		         exc.printStackTrace();
		      }
		   }
		   public static void main(String args[]) {
			      example();
			   }
	}
}
