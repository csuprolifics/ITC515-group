import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {					//class name changed member to Member author @anjli reviewer @suresh

	private String lname;							//Variable name changed LN to lname author @anjli reviewer @suresh
	private String fname;							//Variable name changed FN to fname author @anjli reviewer @suresh
	private String emailid;							//Variable name changed EM to emailid author @anjli reviewer @suresh
	private int pno;							//Variable name changed PN to pno author @anjli reviewer @suresh
	private int iD;								//variable name changed ID to iD author @anjli reviewer @suresh
	private double fines;							//Variable name Changed FINES to fines author @anjli reviewer @suresh
	
	private Map<Integer, loan> LNS;

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.lname = lastName;						//Changed this.LN to this.lname author @anjli reviewer @suresh
		this.fname = firstName;						//changed this.FN to this.fname author @anjli reviewer @suresh
		this.emailid = email;						//Changed this.EM to this.emailid author @anjli reviewer @suresh
		this.pno = phoneNo;						//Changed this.PN to this.pno author @anjli reviewer @suresh
		this.iD = id;							//Changed this.ID to this.iD author @anjli reviewer @suresh
		
		this.LNS = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(iD).append("\n");				//; included and changed ID to iD author @anjli reviewer @suresh
		sb.append("  Name:  ").append(lname).append(", ").append(FN).append("\n");  //object and ; included and changed LN to lname author @anjli reviewer @suresh
		sb.append("  Email: ").append(emailid).append("\n");				//object and ; included and changed EM to emailid author @anjli reviewer @suresh
		sb.append("  Phone: ").append(pno);					//object and ; included and changed PN to pno author @anjli reviewer @suresh
		sb.append("\n");							//object and ; included author @anjli reviewer @suresh
		sb.append(String.format("  Fines Owed :  $%.2f", fines));		//object and ; included and changed FINES to fines author @anjli reviewer @suresh
		sb.append("\n");							//object and ; included author @anjli reviewer @suresh
		
		for (loan loan : LNS.values()) {
			sb.append(loan).append("\n");
		}		  
		return sb.toString();
	}

	
	public int getId() {
		return iD;						//changed ID to iD author @anjli reviewer @suresh
	}

	
	public List<loan> getLoans() {
		return new ArrayList<loan>(LNS.values());
	}

	
	public int getNumberOfCurrentLoans() {
		return LNS.size();
	}

	
	public double getFinesOwed() {
		return fines;						// Changed FINES to fines author @anjli reviewer @suresh
	}

	
	public void takeOutLoan(loan loan) {
		if (!LNS.containsKey(loan.getId())) {
			LNS.put(loan.getId(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() {
		return lname;						//Changed LN to lname author @anjli reviewer @suresh
	}

	
	public String getFirstName() {
		return fname;    					//Changed FN to fname author @anjli reviewer @suresh
	}


	public void addFine(double fine) {
		fines += fine;
	}
	
	public double payFine(double amount) {
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > fines) {
			change = amount - fines;
			fines = 0;
		}
		else {
			fines -= amount;
		}
		return change;
	}


	public void dischargeLoan(loan loan) {
		if (LNS.containsKey(loan.getId())) {
			LNS.remove(loan.getId());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
