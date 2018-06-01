package Controller;
import java.awt.AWTEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HolidayFinder
{
	private LocalDate cal;
	private CompanyHolidays holidays;
	private File file;

	/**
	 * Establishes a holiday finder with the default calendar.
	 * 
	 * @throws ParseException
	 */
	public HolidayFinder() throws ParseException
	{
		holidays = new CompanyHolidays();
		cal = LocalDate.now();
		file = new File("holidays.txt");
	}

	/**
	 * Constructor given a calendar.
	 * 
	 * @param cal
	 *            the calendar to give
	 * @throws ParseException 
	 */
	public HolidayFinder(LocalDate cal) throws ParseException
	{
		holidays = new CompanyHolidays();
		this.cal = cal;
	}

	public List<Holiday> getUpcomingHolidays() throws ParseException
	{
		List<Holiday> list = new ArrayList<Holiday>();
		LocalDate day = LocalDate.now();
		CompanyHolidays nextYearHolidays = new CompanyHolidays(Calendar.getInstance().get(Calendar.YEAR) + 1);
		for ( int i = 1; i < 7; i++ ) {
			Holiday holiday = null;
			String holidayName = null;
			if ( cal.getYear() == day.getYear() ) {
				holidayName = this.holidays.get(day);
			} else {
				holidayName = nextYearHolidays.get(day);
			}

			if ( holidayName != null ) {
				holiday = new Holiday( holidayName, day );
				list.add(holiday);
			}
			day = day.plus(1, ChronoUnit.DAYS);
		}
		
		return list;
	}
	
	public List<Holiday> getUpcomingHolidays( LocalDate day ) throws ParseException
	{
		List<Holiday> list = new ArrayList<Holiday>();
		CompanyHolidays nextYearHolidays = new CompanyHolidays(Calendar.getInstance().get(Calendar.YEAR) + 1);
		for ( int i = 1; i <= 7; i++ ) {
			Holiday holiday = null;
			String holidayName = null;
			if ( cal.getYear() == day.getYear() ) {
				holidayName = this.holidays.get(day);
			} else {
				holidayName = nextYearHolidays.get(day);
			}

			if ( holidayName != null ) {
				holiday = new Holiday( holidayName, day );
				list.add(holiday);
			}
			day = day.plus(1, ChronoUnit.DAYS);
		}
		
		return list;
	}


	public Holiday getNextHoliday() throws ParseException
	{
		Holiday holiday = null;
		LocalDate localCalendar = cal;
		
		CompanyHolidays holidaysNextYear = new CompanyHolidays(cal.getYear() + 1);
		boolean found = false;
		while (!found && cal.getYear() == localCalendar.getYear() )
		{
			String holidayName = holidays.get(localCalendar);
			if (holidayName != null)
			{
				holiday = new Holiday(holidayName, localCalendar);
				found = true;
			}
			localCalendar = localCalendar.plus(1, ChronoUnit.DAYS);
		}
		while (!found && cal.getYear() + 1 == localCalendar.getYear())
		{
			String holidayName = holidaysNextYear.get(localCalendar);
			if (holidayName != null)
			{
				holiday = new Holiday(holidayName, localCalendar);
				found = true;
			}
			localCalendar = localCalendar.plus(1, ChronoUnit.DAYS);
		}
		return holiday;
	}


}
