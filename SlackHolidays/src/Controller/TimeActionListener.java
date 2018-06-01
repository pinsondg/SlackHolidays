package Controller;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;

public class TimeActionListener implements TimeListener
{
	private SlackSession session;
	
	/**
	 * Constructs a time listener.
	 * 
	 * @param session the slack session to post to
	 */
	public TimeActionListener( SlackSession session ) {
		this.session = session;
	}
	
	/**
	 * Sends a message to the channel when called.
	 * 
	 * @param e the time sender
	 * @param list the list of holidays to print out
	 */
	@Override
	public void timeAction(TimeSender e, List<Holiday> list)
	{
		Collection<SlackChannel> channels = session.getChannels();
		Iterator<SlackChannel> iterator = channels.iterator();
		while (iterator.hasNext()) {
			SlackChannel channel = iterator.next();
			String message = "Upcoming holidays:\n";
			for ( int i = 0; list != null && i < list.size(); i++ ) {
				message += list.get(i).toString() + "\n";
			}
			if ( list.isEmpty() ) {
				message += "No holidays this week :(";
			} else {
				message += "Enjoy your day off! :)";
			}
			session.sendMessage(channel, message);
		}
		
	}

}
