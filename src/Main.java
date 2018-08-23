import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner in;				// variable name changed from IN to in @anjli reviewer @suresh
	private static library lib;				// variable name changed from LIB to lib @anjli reviewer @suresh
	private static String menu;				// variable name changed from MENU to menu @anjli reviewer @suresh
	private static Calendar cal;				// variable name changed from CAL to cal @anjli reviewer @suresh
	private static SimpleDateFormat sdf;			// variable name changed from SDF to sdf @anjli reviewer @suresh
	
	
	private static string get_menu() { 			//return type and method name changed according to guide lines author @anjli reviewer @suresh
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n");
		sb.append("  Mem  : add member\n");       //object added and ; included author @anjli reviewer @suresh
		sb.append("  LM : list members\n");	//object added and ; included author @anjli reviewer @suresh
		sb.append("\n");			//object added and ; included author @anjli reviewer @suresh
		sb.append("  Bk  : add book\n");	//object added and ; included author @anjli reviewer @suresh
		sb.append("  LB : list books\n");	//object added and ; included author @anjli reviewer @suresh
		sb.append("  FB : fix books\n");	//object added and ; included author @anjli reviewer @suresh
		sb.append("\n");			//object added and ; included author @anjli reviewer @suresh
		sb.append("  Loan  : take out a loan\n");	//object added and ; included author @anjli reviewer @suresh
		sb.append("  Rloan  : return a loan\n");	//object added and ; included author @anjli reviewer @suresh
		sb.append("  LLoan : list loans\n");		//object added and ; included author @anjli reviewer @suresh
		sb.append("\n");				//object added and ; included author @anjli reviewer @suresh
		sb.append("  Payfine  : pay fine\n");		//object added and ; included author @anjli reviewer @suresh
		sb.append("\n");				//object added and ; included author @anjli reviewer @suresh
		sb.append("  Incdate  : increment date\n");	//object added and ; included author @anjli reviewer @suresh
		sb.append("  Quit  : quit\n");			//object added and ; included author @anjli reviewer @suresh
		sb.append("\n");				//object added and ; included author @anjli reviewer @suresh
		sb.append("Choice : ");				//object added author @anjli reviewer @suresh
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			IN = new Scanner(System.in);
			LIB = library.INSTANCE();
			CAL = Calendar.getInstance();
			SDF = new SimpleDateFormat("dd/MM/yyyy");
	
			for (member m : LIB.Members()) {
				output(m);
			}
			output(" ");
			for (book b : LIB.Books()) {
				output(b);
			}
						
			MENU = Get_menu();
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(CAL.Date()));
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember();
					break;
					
				case "LM": 
					listMembers();
					break;
					
				case "B": 
					addBook();
					break;
					
				case "LB": 
					listBooks();
					break;
					
				case "FB": 
					fixBooks();
					break;
					
				case "L": 
					borrowBook();
					break;
					
				case "R": 
					returnBook();
					break;
					
				case "LL": 
					listCurrentLoans();
					break;
					
				case "P": 
					payFine();
					break;
					
				case "T": 
					incrementDate();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.SAVE();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

		private static void payFine() {
		new PayFineUI(new PayFineControl()).run();		
	}


	private static void listCurrentLoans() {
		output("");
		for (loan loan : LIB.CurrentLoans()) {
			output(loan + "\n");
		}		
	}



	private static void listBooks() {
		output("");
		for (book book : LIB.Books()) {
			output(book + "\n");
		}		
	}



	private static void listMembers() {
		output("");
		for (member member : LIB.Members()) {
			output(member + "\n");
		}		
	}



	private static void borrowBook() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() {
		new ReturnBookUI(new ReturnBookControl()).run();		
	}


	private static void fixBooks() {
		new FixBookUI(new FixBookControl()).run();		
	}


	private static void incrementDate() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			CAL.incrementDate(days);
			LIB.checkCurrentLoans();
			output(SDF.format(CAL.Date()));
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {
		
		String author = input("Enter author: ");
		String title  = input("Enter title: ");
		String callNo = input("Enter call number: ");
		book book = LIB.Add_book(author, title, callNo);
		output("\n" + book + "\n");
		
	}

	
	private static void addMember() {
		try {
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String email = input("Enter email: ");
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			member member = LIB.Add_mem(lastName, firstName, email, phoneNo);
			output("\n" + member + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
