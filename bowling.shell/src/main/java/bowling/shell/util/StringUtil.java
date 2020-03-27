package bowling.shell.util;

public class StringUtil {

	public static boolean validateStringDomainChars(String string2validate, String stringDomainChars) {

		for (char ch : string2validate.toCharArray()) {
			if (stringDomainChars.indexOf(ch) == -1) {
				return false;
			}
		}
		return true;
	}
}
