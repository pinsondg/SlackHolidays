package Controller;
import java.util.List;

public interface TimeSender
{
	public void notifyListeners( List<Holiday> list);
	public void addListeners( TimeActionListener listener );
}
