package Controller;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a holiday with a name and a date.
 * 
 * @author interns
 *
 */
public class Holiday
{
	
	private String name;
	private LocalDate date;
	
	/**
	 * Constructs a holiday object.
	 * @param name
	 * @param date
	 */
	public Holiday( String name, LocalDate date ) {
		setName( name );
		setDate( date );
	}
	
	/**
	 * Gets the date of the holiday.
	 * 
	 * @return the holiday date
	 */
	public LocalDate getDate() {
		return this.date;
	}
	
	/**
	 * Gets the name of the holiday.
	 * 
	 * @return the holiday name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the holiday name.
	 * 
	 * @param name the holiday name
	 */
	public void setName( String name ) {
		if ( name == null ) {
			this.name = "";
		} else {
			this.name = name;
		}
	}
	
	/**
	 * Sets the holiday date.
	 * 
	 * @param date the date the holiday is on
	 */
	public void setDate( LocalDate date ) {
		if ( date != null ) {
			this.date = date;
		} else {
			this.date = LocalDate.now();
		}
	}
	
	/**
	 * Determines if one holiday is the same as another holiday.
	 */
	public boolean equals( Object other ) {
		if ( other instanceof Holiday ) {
			if ( name.equals(this.name) && date.equals(this.date)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the holiday as a string.
	 */
	@SuppressWarnings("deprecation")
	public String toString() {
		return name + ", " + date.getDayOfWeek().toString() + "," + date.getMonth() + "/" + date.getDayOfMonth() + "/" + date.getYear();
	}
}
