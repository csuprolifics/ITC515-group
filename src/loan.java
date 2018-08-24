import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {     				//changed class name loan to Loan author @anljli reviewer @suresh
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };
	
	private int id;								//Changed ID to id						
	private book bk;							//changed B to bk
	private member mem; 							//changed M to mem
	private Date date;							//changed D to date
	private LOAN_STATE state;

	
	public loan(int loanId, book book, member member, Date dueDate) {
		this.id = loanId;						//Changed ID to id
		this.bk = book;							//Changed B to bk
		this.mem = member;						//Changed M to mem
		this.date = dueDate;						//Changed D to date
		this.state = LOAN_STATE.CURRENT;
	}

	
	public void checkOverDue() {
		if (state == LOAN_STATE.CURRENT &&
			Calendar.getInstance().Date().after(D)) {
			this.state = LOAN_STATE.OVER_DUE;			
		}
	}

	
	public boolean isOverDue() {
		return state == LOAN_STATE.OVER_DUE;
	}

	
	public Integer getId() {
		return ID;
	}


	public Date getDueDate() {
		return D;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n");						//; included
		sb.append("  Borrower ").append(M.getId()).append(" : ");				//object added and ; included
		sb.append(M.getLastName()).append(", ").append(M.getFirstName()).append("\n");		//object added and ; included
		sb.append("  Book ").append(B.ID()).append(" : " ); 					//object added and ; included
		sb.append(B.Title()).append("\n");							//object added and ; included
		sb.append("  DueDate: ").append(sdf.format(D)).append("\n");				//object added and ; included
		sb.append("  State: ").append(state);							//object added and ; included
		return sb.toString();
	}


	public member member() {									//Changed Member() to member()
		return mem;										//changed M to mem
	}


	public book book() {										//Changed Book() to book()
		return bk;										//changed B tobk
	}


	public void loan() {										//Changed Loan() to loan()
		state = LOAN_STATE.DISCHARGED;		
	}

}
