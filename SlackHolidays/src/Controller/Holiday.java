package Controller;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Holiday
{
	
	private String name;
	private LocalDate date;
	
	public Holiday( String name, LocalDate date ) {
		setName( name );
		setDate( date );
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName( String name ) {
		if ( name == null ) {
			this.name = "";
		} else {
			this.name = name;
		}
	}
	
	public void setDate( LocalDate date ) {
		if ( date != null ) {
			this.date = date;
		} else {
			this.date = LocalDate.now();
		}
	}
	
	public boolean equals( Object other ) {
		if ( other instanceof Holiday ) {
			if ( name.equals(this.name) && date.equals(this.date)) {
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public String toString() {
		return name + ", " + date.getDayOfWeek().toString() + "," + date.getMonth() + "/" + date.getDayOfMonth() + "/" + date.getYear();
	}
}
