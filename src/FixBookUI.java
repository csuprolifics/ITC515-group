import java.util.Scanner;


public class fixBookUI { //changed FixBookUI to fixBookUI @samatha reviewed by @jashwanth

	public static enum UI_STATE { INITIALISED, READY, FIXING, COMPLETED };

	private fixBookControl control; //changed FixBookControl to fixBookControl @samatha reviewed by @jashwanth
	private Scanner input;
	private UI_STATE state;

	
	public fixBookUI(fixBookControl control) { //changed FixBookUIto fixBookUI @samatha reviewed by @jashwanth
                                                   //changed FixBookControl to fixBookControl @samatha reviewed by @jashwanth
		this.control = control;
		input = new Scanner(System.in);
		state = UI_STATE.INITIALISED;
		control.setUI(this);
	}


	public void setState(UI_STATE state) {
		this.state = state;
	}

	
	public void run() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch ("state") {  // added strings"" to state 2samatha reviewed by jashwanth
			
			case "READY":  // added strings"" to state 2samatha reviewed by jashwanth
				String bookStr = input("Scan Book (<enter> completes): "); 
				if (bookStr.length() == 0) {
					control.scanningComplete();
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						control.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case "FIXING":
				String ans = input("Fix Book? (Y/N) : ",ans);// added ans by @samatha reviewed by @jashwanth
				boolean fix = false;
				if (ans.toUpperCase().equals("Y")) {
					fix = true;
				}
				control.fixBook(fix);
				break;
								
			case "COMPLETED": // added strings"" to state 2samatha reviewed by jashwanth
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}
		
	}

	
	private String input(string prompt) {// changed String to string @samatha reviewed by @jashwanth
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {
		System.out.println(object);//added system.out.println by @samatha reviewed by jashwanth
	}
	
	
}
