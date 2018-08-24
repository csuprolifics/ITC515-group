import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner in;						//Changed variable name IN to in
	private static library lib;						//Changed variable name LIB to lib
	private static String menu;						//Changed variable name MENU to menu
	private static Calendar cal;						//Changed variable name CAL to cal
	private static SimpleDateFormat sdf;					// Changed variable name SDF to sdf
	
	
	private static string get_menu() { 					// method name changed String Get_menu() to string get_menu()
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n");
		sb.append("  Mem  : add member\n");				//changed .append("  M  : add member\n") to sb.append("  Mem  : add member\n");
		sb.append("  LMem : list members\n");				//changed .append("  LM : list members\n") to sb.append("  LMem : list members\n");
		sb.append("\n");
		sb.append("  Bk  : add book\n");				//changed .append("  B  : add book\n") to sb.append("  Bk  : add book\n");
		sb.append("  LB : list books\n");				//changed .append("  LB : list books\n") to sb.append("  LB : list books\n");
		sb.append("  FB : fix books\n");				//changed .append("  FB : fix books\n") to sb.append("  FB : fix books\n");
		sb.append("\n");
		sb.append("  Ln  : take out a loan\n");				//changed .append("  L  : take out a loan\n") to sb.append("  Ln  : take out a loan\n");
		sb.append("  Rln  : return a loan\n");				//changed .append("  R  : return a loan\n") to sb.append("  Rln  : return a loan\n");
		sb.append("  LLn : list loans\n");				//changed .append("  LLn : list loans\n") to sb.append("  LLn : list loans\n");
		sb.append("\n");
		sb.append("  Pfine  : pay fine\n");				//changed .append("  P  : pay fine\n") to sb.append("  Pfine  : pay fine\n");
		sb.append("\n");
		sb.append("  Incdate  : increment date\n");			//changed .append("  T  : increment date\n") to sb.append("  Incdate  : increment date\n");
		sb.append("  Qt  : quit\n");					//changed .append("  Q  : quit\n") to sb.append("  Qt  : quit\n");
		sb.append("\n");
		sb.append("Choice : ");						//changed .append("Choice : ");to sb.append("Choice : ");
		  
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
