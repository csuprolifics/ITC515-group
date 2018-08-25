import java.util.Scanner;


public class ReturnBookUI 
	{

	public static enum UI_STATE { INITIALISED, READY, INSPECTING, COMPLETED };

	private ReturnBookControl returnBookControl;   //changed name
	private Scanner input;
	private UI_STATE state;

	
	public returnBookUI(ReturnBookControl control)          //changed method name author@jashwanth reviewer@anjali
           {
		this.control = control;
		input = new Scanner(System.in);
		state = UI_STATE.INITIALISED;
		control.setUI(this);
	}


	public void run() {		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
				break;
				
			case READY:
				String readBook = input("Scan Book (<enter> completes): ");          //changed bookStr to readBook
				if (readBook.length() == 0) {
					control.scanningComplete();   //changed bookStr to readBook
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
				
			case INSPECTING:
				String answer = input("Is book damaged? (Y/N): ");   //changed string variable Ans to answer
				boolean isDamaged = false;
				if (ans.toUpperCase().equals("Y")) {					
					isDamaged = true;
				}
				control.dischargeLoan(isDamaged);
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
			}
		}
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void display(Object object) {
		output(object);
	}
	
	public void setState(UI_STATE state) {
		this.state = state;
	}

	
}
