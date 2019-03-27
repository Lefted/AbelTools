package code;
public class Code {

	public static StringBuffer generateCode(Options options) {
		StringBuffer buffer = null;

		if (options.getCodeStyle() == Options.CODE_LEFT) {
			buffer = generateLeft(options);
		} else if (options.getCodeStyle() == Options.CODE_RIGHT) {
			buffer = generateRight(options);
		} else if (options.getCodeStyle() == Options.CODE_BOTH) {
			buffer = generateBoth(options);
		}

		return buffer;
	}

	private static StringBuffer generateLeft(Options options) {

		StringBuffer buffer = new StringBuffer();
		final int COLUMNS = options.getColumnsIn();
		final int ROWS = options.getRowsIn();
		boolean[][] result = new boolean[COLUMNS][ROWS];
		
		if (options.isCompilingLeft()) {
			// INIT DATA
			// reverse loop through the columns
			for (int i = COLUMNS - 1; i >= 0; i--) {

				final int RESOLUTION = (int) (ROWS / (Math.pow(2, (i + 1))));
				boolean change = false;
				int counter = 0;

				// loop through the rows
				for (int j = 0; j < ROWS; j++) {
					result[i][j] = change;

					// switch change
					if ((counter + 1) == RESOLUTION) {
						change = (change) ? false : true;
						counter = 0;
					} else {
						counter++;
					}
				}
			}
		}

		// RETURN DATA
		for (int i = 0; i < ROWS; i++) {
			buffer.append("[");

			for (int j = 0; j < COLUMNS; j++) {
				if (options.isCompilingLeft()) {
					final int OUTPUT = (result[j][i]) ? 1 : 0;

					if (j != (COLUMNS - 1)) {
						buffer.append(OUTPUT + ", ");
					} else {
						buffer.append(OUTPUT + "]");
					}
				} else {
					if (j != (COLUMNS - 1)) {
						buffer.append(" ,");
					} else {
						buffer.append(" ]");
					}
				}
			}

			if (options.isShowingRowNumbers()) {
				buffer.append("   //" + i);
			}
			buffer.append("\n");
		}
		return buffer;
	}

	private static StringBuffer generateRight(Options options) {
		StringBuffer buffer = new StringBuffer();
		final int COLUMNS = options.getColumnsOut();
		final int ROWS = options.getRowsOut();

		for (int i = 0; i < ROWS; i++) {
			buffer.append("[");
			for (int j = 0; j < COLUMNS; j++) {

				if (j != (COLUMNS - 1)) {
					buffer.append(" ,");
				} else {
					buffer.append(" ];");
				}
			}
			if (options.isShowingRowNumbers()) {
				buffer.append("   //" + i);
			}
			buffer.append("\n");
		}

		return buffer;
	}

	private static StringBuffer generateBoth(Options options) {
		StringBuffer buffer = new StringBuffer();

		final int COLUMNS = options.getColumnsIn();
		final int ROWS = options.getRowsIn();
		boolean[][] result = new boolean[COLUMNS][ROWS];

		if (options.isCompilingLeft()) {
			// INIT DATA
			// reverse loop through the columns
			for (int i = COLUMNS - 1; i >= 0; i--) {

				final int RESOLUTION = (int) (ROWS / (Math.pow(2, (i + 1))));
				boolean change = false;
				int counter = 0;

				// loop through the rows
				for (int j = 0; j < ROWS; j++) {
					result[i][j] = change;

					// switch change
					if ((counter + 1) == RESOLUTION) {
						change = (change) ? false : true;
						counter = 0;
					} else {
						counter++;
					}
				}
			}
		}

		// OUTPUT DATA
		for (int i = 0; i < ROWS; i++) {
			buffer.append(getLeftRow(i, result, options));
			buffer.append("   ->   ");
			buffer.append(getRightRow(i, options));

			if (options.isShowingRowNumbers()) {
				buffer.append("   //" + i);
			}
			buffer.append("\n");
		}

		return buffer;
	}

	private static StringBuffer getLeftRow(int row, boolean[][] result, Options options) {
		StringBuffer buffer = new StringBuffer();

		buffer.append("[");
		for (int i = 0; i < options.getColumnsIn(); i++) {
			if (options.isCompilingLeft()) {
				int output = (result[i][row]) ? 1 : 0;

				if (i != (options.getColumnsIn() - 1)) {
					buffer.append(output + ",");
				} else {
					buffer.append(output + "]");
				}
			} else {
				if (i != (options.getColumnsIn() - 1)) {
					buffer.append(" ,");
				} else {
					buffer.append(" ]");
				}
			}
		}
		return buffer;
	}

	private static StringBuffer getRightRow(int row, Options options) {
		StringBuffer buffer = new StringBuffer();

		buffer.append("[");
		for (int i = 0; i < options.getColumnsOut(); i++) {
			if (i != (options.getColumnsOut() - 1)) {
				buffer.append(" ,");
			} else {
				buffer.append(" ];");
			}
		}
		return buffer;
	}

}
