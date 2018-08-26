import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static calendar self;//changed method name Calender to calender by @samatha reviewed by @jashwanth
	private static java.util.Calendar cal;
	
	
	private calendar() { //changed Calender method name to calender by @samatha reviewed by @jashwanth
		cal = java.util.Calendar.getInstance();
	}
	
	public static calendar getInstance() {  //changed Calender method name to calender by @samatha reviewed by @jashwanth
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) {
		cal.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void setDate(Date date) {  //changed  name Date date to date date by @samatha and reviewed by jashwanth
		try {
			cal.setTime(date);
	        cal.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        cal.set(java.util.Calendar.MINUTE, 0);  
	        cal.set(java.util.Calendar.SECOND, 0);  
	        cal.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized date date() { 
		try {
	        cal.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        cal.set(java.util.Calendar.MINUTE, 0);  
	        cal.set(java.util.Calendar.SECOND, 0);  
	        cal.set(java.util.Calendar.MILLISECOND, 0);
			return cal.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized date getDueDate(int loanPeriod) { 
		Date now = Date();
		cal.add(java.util.Calendar.DATE, loanPeriod);
		Date dueDate = cal.getTime();
		cal.setTime(now);
		return dueDate;
	}
	
	public synchronized long getDaysDifference(date targetDate) { //changed Date to date by @samatha reviewed by @jashwanth
		long diffMillis = Date().getTime() - targetDate.getTime();
	    long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
	    return diffDays;
	}

}
