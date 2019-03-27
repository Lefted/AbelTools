package programs.codewandler;

public class Anzeige {

	static String leerEnde = "1, 1, 1, 1, 1, 1, 0";
	static String leerAnfang = "1, 1, 1, 1, 1, 1, 0";

	public static String generateHexString(int number) {
		String output = "";
		String hexNumber = Integer.toHexString(number).toUpperCase();

		char character1 = 0;
		char character2 = 0;

		int errorCode = 0;

		
		try {
			character1 = hexNumber.charAt(0);
		} catch (Exception e) {
			errorCode = 1;
		}
		try {
			character2 = hexNumber.charAt(1);
		} catch (Exception e) {
			errorCode = 2;
		}

		if (errorCode == 0) {
			output = "[" + PinCodes.returnPinCodes(character1) + ", " + PinCodes.returnPinCodes(character2) + "];";

		}
		if (errorCode == 1) {
			output = "[" + leerAnfang + PinCodes.returnPinCodes(character2) + "];";
		}
		if (errorCode == 2) {
			output = "[" + PinCodes.returnPinCodes(character1) + ", " + leerEnde + "];";
		}
		return output;
	}
}
