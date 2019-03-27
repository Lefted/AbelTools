package programs.codewandler;

import code.Options;

public class Codewandler {

	private static final int COLUMNS = 8;
	private static final int ROWS = (int) Math.pow(2, COLUMNS);

	private static boolean[][] left;

	public static StringBuffer getStringBuffer(Options options) {
		StringBuffer buffer = new StringBuffer();

		left = new boolean[COLUMNS][ROWS];
		// from 7 to 0 in Array linkeSeite (coulumns)
		for (int i = COLUMNS - 1; i >= 0; i--) {
			final int RESOLUTION = (int) (ROWS / (Math.pow(2, (i + 1))));

			boolean change = false;
			// die zeilen vom punkt aus, an dem sich 0/1 geändert hat
			int x = 0;
			// from 0 to 255 in Array linkeSeite (zeilen/rows)
			for (int j = 0; j < ROWS; j++) {

				left[i][j] = change;

				if ((x + 1) == RESOLUTION) {
					change = (change) ? false : true;
					x = 0;
				} else {
					x++;
				}

			}
		}

		for (int i = 0; i < ROWS; i++) {

			buffer.append("[");
			for (int j = 0; j < COLUMNS; j++) {
				int output = (left[j][i]) ? 1 : 0;

				if (j != (COLUMNS - 1)) {
					buffer.append(output + ", ");
				} else {
					buffer.append(output + "]");
				}
			}

			buffer.append("   ->   ");

			if (!options.isCompilingLeft()) {

				buffer.append(Anzeige.generateHexString(i));
				if (options.isShowingRowNumbers()) {
					buffer.append("    //" + i);
				}
			}
			buffer.append("\n");

		}

		return buffer;
	}

}
