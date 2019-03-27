package code;

public class Debug {

	public static void main(String[] args) {
		Options options = new Options(Options.CODE_LEFT, false);
		options.setColumnsIn(3);
		options.setRowsIn(8);
		options.setCompilingLeft(true);
		
		System.out.println(Code.generateCode(options));
		
	}
	
	
}
