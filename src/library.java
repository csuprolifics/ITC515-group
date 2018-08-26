import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable {						//class name changed library to Library author @gourav reviewer @samatha
	
	private static final String library_file = "library.obj";			//variable name changed LIBRARY_FILE to library_file 
	private static final int loan_limit = 2;					//variable name changed LOAN_LIMIT to loan_limit
	private static final int loan_period = 2;					//VARIABLE NAME CHANGED LOAN_PERIOD TO loan_period
	private static final double fine_per_day = 1.0;					//VARIABLE NAME CHANGED FINE_PER_DAY TO fine_per_day
	private static final double max_fines_owed = 5.0;				//VARIABLE NAME CHANGED MAX_FINES _OEWD TO max_fines_owed
	private static final double damage_fee = 2.0;
	
	private static library self;
	private int bid;								//VARIABLE CHANGED BID TO bid
	private int mid;								//VARIABLE CHANGED MID TO mid
	private int lid;								//VARIABLE CHANGED LID TO lid						
	private Date loadDate;
	
	private Map<Integer, book> catalog;
	private Map<Integer, member> members;
	private Map<Integer, loan> loans;
	private Map<Integer, loan> currentLoans;
	private Map<Integer, book> damagedBooks;
	

	private library() {
		catalog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		bid = 1;
		mid = 1;		
		lid = 1;		
	}

	
	public static synchronized library INSTANCE() {		
		if (self == null) {
			Path path = Paths.get(LIBRARY_FILE);			
			if (Files.exists(path)) {	
				try (ObjectInputStream lof = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {
			    
					self = (library) lof.readObject();
					Calendar.getInstance().setDate(self.loadDate);
					lof.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new library();
		}
		return self;
	}

	
	public static synchronized void SAVE() {
		if (self != null) {
			self.loadDate = Calendar.getInstance().Date();
			try (ObjectOutputStream lof = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) 
{
				lof.writeObject(self);
				lof.flush();
				lof.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int BookID() {
		return bid;					//VARIABLE CHANGED BID TO bid
	}
	
	
	public int MemberID() {
		return mid;					//VARIABLE CHANGED MID TO mid
	}
	
	
	private int nextbid()
 {
		return bid++;					//VARIABLE CHANGED BID TO bid
	}

	
	private int nextmid() {
		return mid++;					//VARIABLE CHANGED MID TO mid
	}

	
	private int nextlid() {
		return lid++;					//VARIABLE CHANGED LID TO lid
	}

	
	public List<member> Members() {		
		return new ArrayList<member>(members.values()); 
	}


	public List<book> Books() {		
		return new ArrayList<book>(catalog.values()); 
	}


	public List<loan> CurrentLoans() {
		return new ArrayList<loan>(currentLoans.values());
	}


	public member Add_mem(String lastName, String firstName, String email, int phoneNo) 
{		
		member member = new member(lastName, firstName, email, phoneNo, nextmid());
		members.put(member.getId(), member);		
		return member;
	}

	
	public book Add_book(String a, String t, String c) 
{		
		book b = new book(a, t, c, nextbid());
		catalog.put(bid(), b);		
		return b;
	}

	
	public member getMember(int memberId) 
{
		if (members.containsKey(memberId)) 
			return members.get(memberId);
		return null;
	}

	
	public book Book(int bookId) {
		if (catalog.containsKey(bookId)) 
			return catalog.get(bookId);		
		return null;
	}

	
	public int loanLimit() {
		return loan_limit;
	}

	
	public boolean memberCanBorrow(member member) {		
		if (member.getNumberOfCurrentLoans() == loan_limit ) 			//variable name changed LOAN_LIMIT to loan_limit
			return false;
				
		if (member.getFinesOwed() >= max_fines_owed) 				//variable name changed MAX_FINES_OEWD TO max_fines_owed
			return false;
				
		for (loan loan : member.getLoans()) 
			if (loan.isOverDue()) 
				return false;
			
		return true;
	}

	
	public int loansRemainingForMember(member member) {		
		return LOAN_LIMIT - member.getNumberOfCurrentLoans();
	}

	
	public loan issueLoan(book book, member member) {
		Date dueDate = Calendar.getInstance().getDueDate(LOAN_PERIOD);
		loan loan = new loan(nextLID(), book, member, dueDate);
		member.takeOutLoan(loan);
		book.Borrow();
		loans.put(loan.getId(), loan);
		currentLoans.put(book.ID(), loan);
		return loan;
	}
	
	
	public loan getLoanByBookId(int bookId) {
		if (currentLoans.containsKey(bookId)) {
			return currentLoans.get(bookId);
		}
		return null;
	}

	
	public double calculateOverDueFine(loan loan) {
		if (loan.isOverDue()) {
			long daysOverDue = Calendar.getInstance().getDaysDifference(loan.getDueDate());
			double fine = daysOverDue * FINE_PER_DAY;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = calculateOverDueFine(currentLoan);
		member.addFine(overDueFine);	
		
		member.dischargeLoan(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(damage_fee);
			damagedBooks.put(book.ID(), book);
		}
		currentLoan.Loan();
		currentLoans.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoans.values()) {
			loan.checkOverDue();
		}		
	}


	public void repairBook(book currentBook) {
		if (damagedBooks.containsKey(currentBook.ID())) {
			currentBook.Repair();
			damagedBooks.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
