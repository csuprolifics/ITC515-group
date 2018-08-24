import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner in;						//Changed variable name IN to in author @anjli reviewer @suresh
	private static library lib;						//Changed variable name LIB to lib @anjli reviewer @suresh
	private static String menu;						//Changed variable name MENU to menu @anjli reviewer @suresh
	private static Calendar cal;						//Changed variable name CAL to cal @anjli reviewer @suresh
	private static SimpleDateFormat sdf;					// Changed variable name SDF to sdf @anjli reviewer @suresh
	
	
	private static string get_menu() { 					// method name changed String Get_menu() to string get_menu() @anjli reviewer @suresh
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n");
		sb.append("  Mem  : add member\n");				//changed .append("  M  : add member\n") to sb.append("  Mem  : add member\n"); @anjli reviewer @suresh
		sb.append("  LMem : list members\n");				//changed .append("  LM : list members\n") to sb.append("  LMem : list members\n"); @anjli reviewer @suresh
		sb.append("\n");
		sb.append("  Bk  : add book\n");				//changed .append("  B  : add book\n") to sb.append("  Bk  : add book\n"); @anjli reviewer @suresh
		sb.append("  LB : list books\n");				//changed .append("  LB : list books\n") to sb.append("  LB : list books\n"); @anjli reviewer @suresh
		sb.append("  FB : fix books\n");				//changed .append("  FB : fix books\n") to sb.append("  FB : fix books\n"); @anjli reviewer @suresh
		sb.append("\n");
		sb.append("  Ln  : take out a loan\n");				//changed .append("  L  : take out a loan\n") to sb.append("  Ln  : take out a loan @anjli reviewer @suresh\n");
		sb.append("  Rln  : return a loan\n");				//changed .append("  R  : return a loan\n") to sb.append("  Rln  : return a loan\n"); @anjli reviewer @suresh
		sb.append("  LLn : list loans\n");				//changed .append("  LLn : list loans\n") to sb.append("  LLn : list loans\n"); @anjli reviewer @suresh
		sb.append("\n");
		sb.append("  Pfine  : pay fine\n");				//changed .append("  P  : pay fine\n") to sb.append("  Pfine  : pay fine\n"); @anjli reviewer @suresh
		sb.append("\n");
		sb.append("  Incdate  : increment date\n");			//changed .append("  T  : increment date\n") to sb.append("  Incdate  : increment date @anjli reviewer @suresh\n");
		sb.append("  Qt  : quit\n");					//changed .append("  Q  : quit\n") to sb.append("  Qt  : quit\n"); @anjli reviewer @suresh
		sb.append("\n");
		sb.append("Choice : ");						//changed .append("Choice : ");to sb.append("Choice : "); @anjli reviewer @suresh
		  
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
					
				case "ln":					//Changed L to ln author @anjli reviewer @suresh
					borrowBook();
					break;
					
				case "rln":					//Changed R to rln author @anjli reviewer @suresh
					returnBook();
					break;
					
				case "lln":					//changed LL to lln author @anjli reviewer @suresh
					listCurrentLoans();
					break;
					
				case "pfine":					//Changed P to pfine author @anjli reviewer @suresh
					payFine();
					break;
					
				case "incdate":					//Changed T to incdate author @anjli reviewer @suresh
					incrementDate();
					break;
					
				case "Qt":					//changed Q to qt author @anjli reviewer @suresh
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
