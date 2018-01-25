package kroger.odilonj.api.utils;

public class StringUtil {

	public static String stringAsCamelCase(String string) {
		if(isCamelCase(string))
			return string;
		
		String[] split = string.split("_");
		return split[0] + split[1].substring(0,1).toUpperCase() + split[1].substring(1);
	}

	private static boolean isCamelCase(String string) {
		return !string.contains("_");
	}
	
}
