import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {     				//changed class name loan to Loan author @anljli reviewer @suresh
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };
	
	private int id;								//Changed ID to id author @anljli reviewer @suresh					
	private book bk;							//changed B to bk author @anljli reviewer @suresh
	private member mem; 							//changed M to mem author @anljli reviewer @suresh
	private Date date;							//changed D to date author @anljli reviewer @suresh
	private LOAN_STATE state;

	
	public loan(int loanId, book book, member member, Date dueDate) {
		this.id = loanId;						//Changed ID to id author @anljli reviewer @suresh
		this.bk = book;							//Changed B to bk author @anljli reviewer @suresh
		this.mem = member;						//Changed M to memauthor @anljli reviewer @suresh
		this.date = dueDate;						//Changed D to date author @anljli reviewer @suresh
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
		sb.append("Loan:  ").append(ID).append("\n");						//; included author @anljli reviewer @suresh
		sb.append("  Borrower ").append(M.getId()).append(" : ");				//object added and ; included author @anljli reviewer @suresh
		sb.append(M.getLastName()).append(", ").append(M.getFirstName()).append("\n");		//object added and ; included author @anljli reviewer @suresh
		sb.append("  Book ").append(B.ID()).append(" : " ); 					//object added and ; included author @anljli reviewer @suresh
		sb.append(B.Title()).append("\n");							//object added and ; included author @anljli reviewer @suresh
		sb.append("  DueDate: ").append(sdf.format(D)).append("\n");				//object added and ; included author @anljli reviewer @suresh
		sb.append("  State: ").append(state);							//object added and ; included author @anljli reviewer @suresh
		return sb.toString();
	}


	public member member() {									//Changed Member() to member() author @anljli reviewer @suresh
		return mem;										//changed M to mem author @anljli reviewer @suresh
	}


	public book book() {										//Changed Book() to book() author @anljli reviewer @suresh
		return bk;										//changed B to bk  author @anljli reviewer @suresh
	}


	public void loan() {										//Changed Loan() to loan() author @anljli reviewer @suresh
		state = LOAN_STATE.DISCHARGED;		
	}

}
