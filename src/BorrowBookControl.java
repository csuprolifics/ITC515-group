import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI ui;
	
	private library lib;							// Changed Variable Name L to lib author @suresh Review by @ gourav 
	private member mem;							// Changed Variable Name M to mem author @suresh Review by @ gourav 
	private enum CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private CONTROL_STATE state;
	
	private List<book> PENDING;
	private List<loan> COMPLETED;
	private book bk;							// Changed Variable Name B to bk
	
	
	public borrowbookcontrol() {						// Changed Method name BorrowBookControl to borrowbookcontrol
		this.lib = lib.INSTANCE();					// Changed this.L = L.INSTANCE() to this.lib = lib.INSTANCE()
		state = CONTROL_STATE.INITIALISED;
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!state.equals(CONTROL_STATE.INITIALISED))
			{							//Braces included
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			}
		this.ui = ui;
		ui.setState(BorrowBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}

		
	public void swiped(int memberId) {					//Changed  Method name Swiped to swiped
		if (!state.equals(CONTROL_STATE.READY))
			{ 							//Braces included
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			}

		mem = lib.getMember(memberId); 					// Changed M = L.getMember(memberId) to mem = lib.getMember(memberId)
		if (mem == null) { 						// Changed M to mem
			ui.display("Invalid memberId");
			return;
		}
		if (lib.memberCanBorrow(mem)) { 					// changed L.memberCanBorrow(M) to lib.memberCanBorrow(mem)
			PENDING = new ArrayList<>();
			ui.setState(BorrowBookUI.UI_STATE.SCANNING);
			state = CONTROL_STATE.SCANNING; }
		else 
		{
			ui.display("Member cannot borrow at this time");
			ui.setState(BorrowBookUI.UI_STATE.RESTRICTED); }
	
	
	public void scanned(int bookId) { 						// changed method name Scanned to scanned
		bk = null;
		if (!state.equals(CONTROL_STATE.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		bk = lib.Book(bookId);							// changed B = L.Book(bookId) to bk = lib.Book(bookId);
		if (bk == null) {							// changed if (B == null) to if (bk == null) 
			ui.display("Invalid bookId");
			return;
		}
		if (!bk.Available()) {							// changed B.Available() to bk.Available()
			ui.display("Book cannot be borrowed");
			return;
		}
		PENDING.add(bk);
		for (book bk : PENDING) {
			ui.display(bk.toString());
		}
		if (lib.loansRemainingForMember(mem) - PENDING.size() == 0) {
			ui.display("Loan limit reached");
			complete();							//changed method name Complete to complete
		}
	}
	
	
	public void complete() {							//changed method name Complete to complete
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			ui.display("\nFinal Borrowing List");
			for (book bk : PENDING) {
				ui.display(bk.toString());
			}
			COMPLETED = new ArrayList<loan>();
			ui.setState(BorrowBookUI.UI_STATE.FINALISING);
			state = CONTROL_STATE.FINALISING;
		}
	}


	public void commitLoans() {
		if (!state.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book bk : PENDING) {
			loan_loan = lib.issueLoan(b, M);
			COMPLETED.add(loan);			
		}
		ui.display("Completed Loan Slip");
		for (loan_loan : COMPLETED) {
			ui.display(loan.toString());
		}
		ui.setState(BorrowBookUI.UI_STATE.COMPLETED);
		state = CONTROL_STATE.COMPLETED;
	}

	
	public void cancel() {
		ui.setState(BorrowBookUI.UI_STATE.CANCELLED);
		state = CONTROL_STATE.CANCELLED;
	}
	
	
}
