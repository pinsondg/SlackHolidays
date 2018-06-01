package Controller;
import java.awt.AWTEvent;
import java.util.List;

public interface TimeListener
{
	
	public void timeAction( TimeSender e, List<Holiday> list );
	
}
