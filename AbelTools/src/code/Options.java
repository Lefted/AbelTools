package code;
public class Options {

	public static final int CODE_LEFT = 1;
	public static final int CODE_RIGHT = 2;
	public static final int CODE_BOTH = 3;

	private int rowsIn = 0;
	private int rowsOut = 0;
	private int columnsIn = 0;
	private int columnsOut = 0;
	private int codeStyle;
	private boolean showingRowNumbers;
	private boolean compilingLeft = false;

	public Options(int codeStyle, boolean showRowNumbers) {
		this.codeStyle = codeStyle;
		this.showingRowNumbers = showRowNumbers;
	}

	public void validate() {
		if (this.isCompilingLeft()) {
			this.rowsIn = (int) Math.pow(2, columnsIn);
		} else {
			if (this.rowsIn == 0) {
				this.rowsIn = (int) Math.pow(2, columnsIn);
			}
			if (this.rowsOut == 0) {
				this.rowsOut = (int) Math.pow(2, columnsOut);
			}
		}
	}

	public int getRowsIn() {
		return rowsIn;
	}

	public int getRowsOut() {
		return rowsOut;
	}

	public int getColumnsIn() {
		return columnsIn;
	}

	public int getCodeStyle() {
		return codeStyle;
	}

	public boolean isShowingRowNumbers() {
		return showingRowNumbers;
	}

	public int getColumnsOut() {
		return columnsOut;
	}

	public void setColumnsIn(int columnsIn) {
		this.columnsIn = columnsIn;
	}

	public void setColumnsOut(int columnsOut) {
		this.columnsOut = columnsOut;
	}

	public void setRowsIn(int rowsIn) {
		this.rowsIn = rowsIn;
	}

	public void setRowsOut(int rowsOut) {
		this.rowsOut = rowsOut;
	}

	public boolean isCompilingLeft() {
		return compilingLeft;
	}

	public void setCompilingLeft(boolean compileLeft) {
		this.compilingLeft = compileLeft;
	}

}
