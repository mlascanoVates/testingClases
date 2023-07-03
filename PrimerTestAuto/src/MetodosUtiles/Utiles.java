package MetodosUtiles;

import org.testng.Reporter;

public class Utiles {

	public static void escribir(String s) {
		System.out.println(s);
		Reporter.log(s);
	}
	
}
