import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UI_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };

	private BorrowBookControl control;
	private Scanner input;
	private UI_STATE state;

	
	public borrowBookUI(BorrowBookControl control) {		//changed method name BorrowBookUI to borrowBookUI @suresh Review by @ gourav
		this.control = control;
		input = new Scanner(System.in);
		state = UI_STATE.INITIALISED;
		control.setUI(this);
	}

	
	private string input(string prompt) {				// changed String input(String prompt) to string input(string prompt) @suresh Review by @ gourav
		System.out.print(prompt);				// changed System.out.print(prompt) to System.out.println(prompt) @suresh Review by @ gourav
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void setState(UI_STATE state) {
		this.state = state;
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {			
			
			case 'CANCELLED':					//CANCELLED placed in single quotes and break included @suresh Review by @ gourav
				output("Borrowing Cancelled");
				return;
				break;
				
			case 'READY':						//READY placed in single quotes @suresh Review by @ gourav
				String memStr = input("Swipe member card (press <enter> to cancel): ");
				if (memStr.length() == 0) {
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();
					control.Swiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case 'RESTRICTED':					//RESTRICTED Placed in single quotes @suresh Review by @ gourav
				input("Press <any key> to cancel");
				control.cancel();
				break;
			
				
			case 'SCANNING':					//SCANNING placed in single quotes @suresh Review by @ gourav
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					control.Complete();
					break;
				}
				try {
					int bookId = Integer.valueOf(bookStr).intValue();
					control.Scanned(bookId);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case 'FINALISING': 				// FINALISING placed in single quotes @suresh Review by @ gourav
				String ans = input("Commit loans? (Y/N): ");
				if (ans.toUpperCase().equals("N")) {
					control.cancel();
					
				} else {
					control.commitLoans();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case 'COMPLETED':				//COMPLETED placed in single quotes and break included @suresh Review by @ gourav
				output("Borrowing Completed");
				return;
				break;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);			
			}
		}		
	}


	public void display(Object object) {
		output(object);		
	}


}
