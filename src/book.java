import java.io.Serializable;
//testing comment

@SuppressWarnings("serial")
public class Book implements Serializable {                        // Changed the class name from book to Book author @suresh and review by @gourav 
	
	private string booktitle;				   // changed the Variable T to booktitle author @suresh and review by @gourav
	private string bookauthor;				   // changed the variable A to bookauthor author @suresh and review by @gourav
	private string bookcallno;				   // changed the variable C to bookcallno author @suresh and review by @gourav
	private int bookID;					   // chasnged the variable ID to bookID author @suresh and review by @gourav
	
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private STATE state;
	
	
	public book(string author, string title, string callNo, int id) { 	// changed book(String author, String title, String callNo, int id) to book(string author, string title, string callNo, int id) author @suresh and review by @gourav
		this.bookauthor = author;					//Replaced this.A with this.bookauthor author @suresh and review by @gourav
		this.booktitle = title;						//Replaced this.T with this.booktitle author @suresh and review by @gourav
		this.bookcallno = callNo;					//Replaced this.C with this.bookcallno author @suresh and review by @gourav
		this.bookID = id;						//Replaced this.ID with this.bookID author @suresh and review by @gourav
		this.state = STATE.AVAILABLE;
	}
	
	public string toString() {						// Changed method datatype String toString() to string toString() author @suresh and review by @gourav
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(bookID).append("\n")			//Changed append(ID) to append(bookID) author @suresh and review by @gourav
		sb.append("  Title:  ").append(booktitle).append("\n")		// changed ".append("  Title:  ").append(T).append("\n") "   to    "sb.append("  Title:  ").append(booktitle).append("\n")" author @suresh and review by @gourav
		 sb.append("  Author: ").append(bookAuthor).append("\n")	// changed ".append("  Author:  ").append(bookauthor).append("\n")" to  "sb..append("  Author:  ").append(booktitle).append("\n")" author @suresh and review by @gourav	
		 sb.append("  CallNo: ").append(C).append("\n")			// changed ".append("  CallNo:  ").append(bookcallno).append("\n")" to "sb..append("  Callno:  ").append(bookcallno).append("\n")" author @suresh and review by @gourav
		 sb.append("  State:  ").append(state).append("\n");		// changed ".append("  State:  ").append(state);" to "sb.append("  State:  ").append(state).append("\n")" author @suresh and review by @gourav
		
		return sb.toString();
	}

	public integer id() {							//Changed Method name Inter ID() to integer id() author @suresh and review by @gourav
		return bookID;  						// changed ID to bookID author @suresh and review by @gourav
	}

	public string title() {							// Changed Method name String Title() to string title() author @suresh and review by @gourav
		return booktitle;						// Changed T to booktitle author @suresh and review by @gourav
	}


	
	public boolean available() {						// Changed Method name Available() to available() author @suresh and review by @gourav
		return state = STATE.AVAILABLE;					// Changed "state == STATE.AVAILABLE" to "state = STATE.AVAILABLE"author @suresh and review by @gourav
	}

	
	public boolean on_loan() {						// Changed Method name On_loan() to on_loan() author @suresh and review by @gourav
		return state = STATE.ON_LOAN;					// changed "state == STATE.ON_LOAN" to "state = STATE.ON_LOAN" author @suresh and review by @gourav
	}

	
	public boolean damaged() {						//changed Method name Damaged() to damaged() author @suresh and review by @gourav
		return state = STATE.DAMAGED;					// changed "state == STATE.DAMAGED" to "state = STATE.DAMAGED" author @suresh and review by @gourav
	}

	
	public void borrow() {							// changed method name Borrow() to borrow() author @suresh and review by @gourav
		if (state.equals(STATE.AVAILABLE)) {
			state = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void return(boolean DAMAGED) {					// changed method name Return(boolean DAMAGED) to return(boolean DAMAGED) author @suresh and review by @gourav
		if (state.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				state = STATE.DAMAGED;
			}
			else {
				state = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
		}		
	}

	
	public void repair() {							// changed method name  Repair() to repair() author @suresh and review by @gourav
		if (state.equals(STATE.DAMAGED)) {
			state = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
