package programs.codewandler;

public class PinCodes {

	public static String returnPinCodes(char index) {
		String pinCode = null;
		String[] zeichen = new String[16];

		zeichen[0] = "1, 1, 1, 1, 1, 1, 0";
		zeichen[1] = "0, 1, 1, 0, 0, 0, 0";
		zeichen[2] = "1, 1, 0, 1, 1, 0, 1";
		zeichen[3] = "1, 1, 1, 1, 0, 0, 1";
		zeichen[4] = "0, 1, 1, 0, 0, 1, 1";
		zeichen[5] = "1, 0, 1, 1, 1, 0, 1";
		zeichen[6] = "1, 0, 1, 1, 1, 1, 1";
		zeichen[7] = "1, 1, 1, 0, 0, 0, 0";
		zeichen[8] = "1, 1, 1, 1, 1, 1, 1";
		zeichen[9] = "1, 1, 1, 1, 0, 1, 1";
		zeichen[10] = "1, 1, 1, 0, 1, 1, 1";
		zeichen[11] = "1, 1, 1, 1, 1, 1, 1";
		zeichen[12] = "1, 0, 0, 1, 1, 1, 1";
		zeichen[13] = "1, 1, 1, 1, 1, 1, 0";
		zeichen[14] = "1, 0, 0, 1, 1, 1, 1";
		zeichen[15] = "1, 0, 0, 0, 1, 1, 1";
		// [a1, b1, c1, d1, e1, f1, g1, a2, b2, c2, d2, e2, f2, g2])

		int arrayIndex = 0;

		switch (index) {
		case '0':
			arrayIndex = 0;
			break;
		case '1':
			arrayIndex = 1;
			break;
		case '2':
			arrayIndex = 2;
			break;
		case '3':
			arrayIndex = 3;
			break;
		case '4':
			arrayIndex = 4;
			break;
		case '5':
			arrayIndex = 5;
			break;
		case '6':
			arrayIndex = 6;
			break;
		case '7':
			arrayIndex = 7;
			break;
		case '8':
			arrayIndex = 8;
			break;
		case '9':
			arrayIndex = 9;
			break;
		case 'A':
			arrayIndex = 10;
			break;
		case 'B':
			arrayIndex = 11;
			break;
		case 'C':
			arrayIndex = 12;
			break;
		case 'D':
			arrayIndex = 13;
			break;
		case 'E':
			arrayIndex = 14;
			break;
		case 'F':
			arrayIndex = 15;
			break;
		default:
			arrayIndex = 0;
			break;
		}

		for (int i = 0; i < 16; i++) {
			pinCode = zeichen[arrayIndex];
		}

		return pinCode;
	}
}
