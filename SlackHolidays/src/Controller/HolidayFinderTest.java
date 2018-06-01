package Controller;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

public class HolidayFinderTest
{

	@Test
	public void testGetNextHolidayMemorialDay2018() throws ParseException
	{
		LocalDate cal1 = LocalDate.of(2018, 5, 26);
		HolidayFinder finder = new HolidayFinder( cal1 );
		LocalDate memorialDayDate = LocalDate.of(2018, 5, 28);
		Holiday memorialDay = new Holiday( "Memorial Day", memorialDayDate );
		
		assertEquals( memorialDay, finder.getNextHoliday());
		
	}
	
	@Test
	public void testGetNextHolidayMemorialDay2019() throws ParseException
	{
		LocalDate cal1 = LocalDate.of(2019, 5, 20);
		HolidayFinder finder = new HolidayFinder( cal1 );
		LocalDate memorialDayDate = LocalDate.of(2019, 5, 27);
		Holiday memorialDay = new Holiday( "Memorial Day", memorialDayDate );
		
		assertEquals( memorialDay, finder.getNextHoliday());
		
	}
	
	@Test
	public void testGetNextHolidayThanksgiving2019() throws ParseException
	{
		LocalDate cal1 = LocalDate.of(2019, 11, 1);
		HolidayFinder finder = new HolidayFinder( cal1 );
		LocalDate thanksgivingDayDate = LocalDate.of(2019, 11, 28);
		Holiday thanksgiving = new Holiday( "Thanksgiving", thanksgivingDayDate );
		
		assertEquals( thanksgiving, finder.getNextHoliday());
		
	}
	
	@Test
	public void testGetNextHolidayNewYears2019() throws ParseException
	{
		LocalDate cal1 = LocalDate.of(2018, 12, 26);
		HolidayFinder finder = new HolidayFinder( cal1 );
		LocalDate newYearsDate = LocalDate.of(2019, 1, 1);
		Holiday newYears = new Holiday( "New Years", newYearsDate );
		
		assertEquals( newYears, finder.getNextHoliday());
		
	}
	
	@Test
	public void testUpcomingHolidaysEndOfYear() throws ParseException {
		LocalDate cal1 = LocalDate.of(2018, 12, 27 );
		HolidayFinder finder = new HolidayFinder( cal1 );
		LocalDate newYearsDate = LocalDate.of(2019, 1, 1);
		Holiday newYears = new Holiday( "New Years", newYearsDate );
		
		List<Holiday> list = finder.getUpcomingHolidays( cal1 );
		Holiday actual = list.get(0);
		System.out.println(list.toString());
		assertEquals( newYears, actual );
		
		
	}
	

}
