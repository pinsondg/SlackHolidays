package Controller;
import java.awt.Event;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackChannelJoined;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackChannelJoinedListener;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;

public class MessageSender implements TimeSender
{

	private List<TimeActionListener> timeListeners;
	private SlackSession session;
	private HolidayFinder holidays;

	public MessageSender() throws InterruptedException, ParseException
	{
		session = SlackSessionFactory.createWebSocketSlackSession(
				"xoxb-5101990913-369659812307-AnEliRD09c9NmHUtle03iq8x");
		registeringAListener(session);
		while (true)
		{
			run();
		}

	}

	/**
	 * Main run method.
	 * @throws ParseException
	 */
	private void run() throws ParseException
	{
		try
		{
			establishConnection();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			return;
		}
		
		timeListeners = new ArrayList<TimeActionListener>();
		this.addListeners(new TimeActionListener(session));
		List<Holiday> list = checkMonday();
		if ( list != null ) {
			notifyListeners(list);
		}

	}
	
	/**
	 * Checks if the current day is a Monday and returns the list of holidays
	 * that week.
	 * 
	 * @return the list of holidays on that week
	 * @throws ParseException thrown if parse exception
	 */
	private List<Holiday> checkMonday() throws ParseException {
		HolidayFinder finder = null;
		List<Holiday> list = null;
		try {
			finder  = new HolidayFinder();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = false;
		Calendar calendar = Calendar.getInstance();
		if ( calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY 
				&& calendar.get(Calendar.HOUR_OF_DAY) == 9 
				&& calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.SECOND) == 0) {
			list = finder.getUpcomingHolidays();
		}
		return list;
		
	}

	/**
	 * Establishes a connection to slack and will retry if it is interrupted.
	 * 
	 * @throws InterruptedException thrown is thread is interrupted
	 */
	private void establishConnection() throws InterruptedException
	{
		boolean messageSent = false;
		int timer = 0;
		while (!session.isConnected())
		{
			try
			{
				session.connect();
				System.out.println("Connected!");
			} catch (IOException e)
			{
				if (messageSent == false )
				{
					System.out.print("Trying to establish connection.");
					messageSent = true;
				} else
				{
					System.out.print(" .");
					Thread.sleep(1500);
					timer++;
				}/* else
				{
					System.err.print("\nCould not re-establish connection."
							+ "\nPlease check internet connection and try again.");
					System.exit(0);
				}*/
			}
		}
	}

	/**
	 * Notifies the time listener
	 */
	@Override
	public void notifyListeners( List<Holiday> list)
	{
		Iterator<TimeActionListener> it = timeListeners.iterator();
		while (it.hasNext())
		{
			it.next().timeAction(this, list);
		}

	}
	

	/**
	 * Adds a time listener to this object.
	 */
	@Override
	public void addListeners(TimeActionListener listener)
	{
		timeListeners.add(listener);
	}

	/**
	 * Registers a listener.
	 * 
	 * @param session the slack session to register to
	 */
	private void registeringAListener(SlackSession session)
	{
		// first define the listener
		SlackMessagePostedListener messagePostedListener = new SlackMessagePostedListener() {
			@Override
			public void onEvent(SlackMessagePosted event, SlackSession session)
			{
				HolidayFinder holidayFinder = null;
				try
				{
					holidayFinder = new HolidayFinder();
				} catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SlackChannel channelOnWhichMessageWasPosted = event.getChannel();
				String messageContent = event.getMessageContent();
				SlackUser messageSender = event.getSender();
				messageContent = messageContent.toLowerCase();
				if (messageContent.contains("next holiday")
						&& !session.sessionPersona().getId().equals(messageSender.getId()))
				{
					try
					{
						session.sendMessage(channelOnWhichMessageWasPosted, "The next holiday is " + holidayFinder.getNextHoliday().toString());
					} catch (ParseException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		// add it to the session
		session.addMessagePostedListener(messagePostedListener);

		// that's it, the listener will get every message post events the bot
		// can get notified on
		// (IE: the messages sent on channels it joined or sent directly to it)
		
		SlackChannelJoinedListener slackChannelJoined = new SlackChannelJoinedListener() {

			@Override
			public void onEvent(SlackChannelJoined event, SlackSession session)
			{
		
				SlackChannel channel = event.getSlackChannel();
				session.sendMessage(channel, "Hello! I am Holiday Bot! I will remind this channel when a holiday is coming up."
						+ " You can also type \"next holiday\" and I will let you know when the next holiday is!");
				System.out.println("This happened");
			}
		};
		session.addChannelJoinedListener(slackChannelJoined);
	}

}
