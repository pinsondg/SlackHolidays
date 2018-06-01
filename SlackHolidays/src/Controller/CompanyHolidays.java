package Controller;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A hash map of company holidays. To add more holidays you will have to hard code them in yourself.
 * 
 * @author interns
 *
 */
public class CompanyHolidays extends HashMap<LocalDate, String> implements Serializable
{

	private int currentYear;
	
	/**
	 * Default constructor for using the current year.
	 * @throws ParseException
	 */
	public CompanyHolidays() throws ParseException {
		currentYear = Calendar.getInstance().get(Calendar.YEAR);
		setUpMap();
		
	}
	
	/**
	 * Constructor if you want to use a different year.
	 * 
	 * @param year the year to use
	 * @throws ParseException 
	 */
	public CompanyHolidays( int year ) throws ParseException {
		currentYear = year;
		setUpMap();
	}

	/**
	 * Sets up the hash map.
	 * 
	 * @throws ParseException
	 */
	private void setUpMap() throws ParseException
	{
		LocalDate christmas = LocalDate.of(currentYear, 12, 25);
		LocalDate memorialDay = findMemorialDay();
		LocalDate thanksgiving = findThanksgiving();
		LocalDate fourthOfJuly = LocalDate.of(currentYear, 7, 4);
		LocalDate newYears = LocalDate.of(currentYear, 1, 1);
		LocalDate laborDay = findLaborDay();
		LocalDate veterensDay = (LocalDate.of(currentYear, 11, 12));
		
		this.put(newYears, "New Years");
		this.put(fourthOfJuly, "Fourth of July");
		this.put(memorialDay, "Memorial Day");
		this.put(thanksgiving, "Thanksgiving");
		this.put(christmas, "Christmas");
		this.put(laborDay, "Labor Day");
		this.put(veterensDay, "Veterens Day");
	}
	
	
	public static void main( String[] args ) throws ParseException {
		int currentYear = 2018;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("W/u/MM/yyyy");
		
		Date christmas = sdf.parse("12/25/" + currentYear);
		Date fourthOfJuly = sdf.parse("7/4/" + currentYear);
		//Date memorialDay = findMemorialDay(sdf);
		//Date thanksgiving = findThanksgiving( sdf );
		
		System.out.println(christmas.toString());
		//System.out.println(memorialDay.toString());
		System.out.println(fourthOfJuly.toString());
		//System.out.println(thanksgiving.toString());
		
	}
	
	/**
	 * Finds thanksgiving of the current year.
	 * 
	 * @return the date of thanksgiving.
	 * @throws ParseException
	 */
	private LocalDate findThanksgiving() throws ParseException {

		LocalDate firstNov = LocalDate.of(currentYear, 11, 1);
		if ( firstNov.getDayOfWeek().equals(DayOfWeek.THURSDAY) ) {
			firstNov = firstNov.plusWeeks(3);
		} else {
			firstNov = firstNov.with(TemporalAdjusters.next(DayOfWeek.THURSDAY)).plusWeeks(3);
		}
		return firstNov;
	}
	
	/**
	 * Finds labor day of the current year.
	 * 
	 * @return the date of labor day
	 */
	private LocalDate findLaborDay() {
		LocalDate firstSept = LocalDate.of(currentYear, 9, 1);
		while(!firstSept.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
			firstSept = firstSept.plus(1, ChronoUnit.DAYS);
		}
		return firstSept;
	}
	
	/**
	 * Finds memorial day of the current year.
	 * 
	 * @return the date of memorial day
	 * @throws ParseException
	 */
	private LocalDate findMemorialDay() throws ParseException {
		LocalDate lastMay = LocalDate.of(currentYear, 5, 31);
		lastMay = lastMay.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));
		return lastMay;
	}
	
	/**
	 * Given a holiday date it will determine if the holiday is on a weekend
	 * and return the Monday if it is.
	 * 
	 * @return the date its observed on
	 */
	private LocalDate ifOnWeekend( LocalDate date ) {
		
		if ( date.getDayOfWeek().equals(DayOfWeek.SATURDAY) 
				|| date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		}
		
		return date;
		
	}
}
