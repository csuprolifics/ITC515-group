import java.io.Serializable;
//testing comment

@SuppressWarnings("serial")
public class Book implements Serializable {                        // Changed the class name from book to Book author @suresh and review by @gourav 
	
	private String booktitle;				   // changed the Variable T to booktitle author @suresh and review by @gourav
	private String bookauthor;				   // changed the variable A to bookauthor author @suresh and review by @gourav
	private String bookcallno;				   // changed the variable C to bookcallno author @suresh and review by @gourav
	private int bookID;					   // chasnged the variable ID to bookID author @suresh and review by @gourav
	
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private STATE state;
	
	
	public book(String author, String title, String callNo, int id) {
		this.bookauthor = author;					//Replaced this.A with this.bookauthor author @suresh and review by @gourav
		this.booktitle = title;						//Replaced this.T with this.booktitle author @suresh and review by @gourav
		this.bookcallno = callNo;					//Replaced this.C with this.bookcallno author @suresh and review by @gourav
		this.bookID = id;						//Replaced this.ID with this.bookID author @suresh and review by @gourav
		this.state = STATE.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(bookID).append("\n")			//Changed append(ID) to append(bookID)
		sb.append("  Title:  ").append(booktitle).append("\n")		// changed ".append("  Title:  ").append(T).append("\n") "   to    "sb.append("  Title:  ").append(booktitle).append("\n")"
		 sb.append("  Author: ").append(bookAuthor).append("\n")	// changed ".append("  Author:  ").append(bookauthor).append("\n")" to  "sb..append("  Author:  ").append(booktitle).append("\n")"		
		 sb.append("  CallNo: ").append(C).append("\n")			// changed ".append("  CallNo:  ").append(bookcallno).append("\n")" to "sb..append("  Callno:  ").append(bookcallno).append("\n")"
		 sb.append("  State:  ").append(state).append("\n");		// changed ".append("  State:  ").append(state);" to "sb.append("  State:  ").append(state).append("\n");"
		
		return sb.toString();
	}

	public Integer ID() {
		return bookID;  						// changed ID to bookID.
	}

	public String Title() {
		return booktitle;						// Changed T to booktitle.
	}


	
	public boolean Available() {
		return state = STATE.AVAILABLE;					// Changed "state == STATE.AVAILABLE" to "state = STATE.AVAILABLE".
	}

	
	public boolean On_loan() {
		return state = STATE.ON_LOAN;					// changed "state == STATE.ON_LOAN" to "state = STATE.ON_LOAN".
	}

	
	public boolean Damaged() {
		return state = STATE.DAMAGED;					// changed "state == STATE.DAMAGED" to "state = STATE.DAMAGED".
	}

	
	public void Borrow() {
		if (state.equals(STATE.AVAILABLE)) {
			state = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void Return(boolean DAMAGED) {
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

	
	public void Repair() {
		if (state.equals(STATE.DAMAGED)) {
			state = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
