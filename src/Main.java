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
			in = new Scanner(System.in);				//changed IN to in author @anjli reviewer @suresh
			lib = library.INSTANCE();				//changed LIb to lib author @anjli reviewer @suresh
			cal = Calendar.getInstance();				//Changed CAL to cal author @anjli reviewer @suresh
			sdf = new SimpleDateFormat("dd/MM/yyyy");		//changed SDF to sdf author @anjli reviewer @suresh
	
			for (member mem : lib.Members()) {			// changed m to mem author @anjli reviewer @suresh
				output(mem);
			}
			output(" ");
			for (book bk : lib.Books()) {				//changed b to bk author @anjli reviewer @suresh
				output(bk);
			}
						
			menu = get_menu();					//changed MENU to menu author @anjli reviewer @suresh
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + sdf.format(CAL.Date()));
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "mem": 					//changed M to mem author @anjli reviewer @suresh
					addMember();
					break;
					
				case "lmem": 					//changed LM to lmem author @anjli reviewer @suresh
					listMembers();
					break;
					
				case "bk": 					//Changed B to bk author @anjli reviewer @suresh 
					addBook();
					break;
					
				case "lb":					//changed LB to lb author @anjli reviewer @suresh
					listBooks();
					break;
					
				case "fb":					//changed FB to fb author @anjli reviewer @suresh
					fixBooks();
					break;
					
				case "loan":					//Changed L to ln author @anjli reviewer @suresh
					borrowBook();
					break;
					
				case "rloan":					//Changed R to rln author @anjli reviewer @suresh
					returnBook();
					break;
					
				case "lloan":					//changed LL to lln author @anjli reviewer @suresh
					listCurrentLoans();
					break;
					
				case "payfine":					//Changed P to pfine author @anjli reviewer @suresh
					payFine();
					break;
					
				case "incdate":					//Changed T to incdate author @anjli reviewer @suresh
					incrementDate();
					break;
					
				case "Quit":					//changed Q to qt author @anjli reviewer @suresh
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
		for (loan loan : lib.CurrentLoans()) {				//Changed LIB to lib author @anjli reviewer @suresh
			output(loan + "\n");
		}		
	}



	private static void listBooks() {
		output("");
		for (book book : lib.Books()) {
			output(book + "\n");
		}		
	}



	private static void listMembers() {
		output("");
		for (member member : lib.Members()) {
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
			cal.incrementDate(days);						//Changed CAL to cal author @anjli reviewer @suresh
			lib.checkCurrentLoans();						//Changed LIB to lib author @anjli reviewer @suresh
			output(sdf.format(cal.Date()));						//changed SDF to sdf and CAL to cal author @anjli reviewer @suresh
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {
		
		String author = input("Enter author: ");
		String title  = input("Enter title: ");
		String callNo = input("Enter call number: ");
		book book = lib.Add_book(author, title, callNo);
		output("\n" + book + "\n");
		
	}

	
	private static void addMember() {
		try {
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String email = input("Enter email: ");
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			member member = lib.Add_mem(lastName, firstName, email, phoneNo);
			output("\n" + member + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return in.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
