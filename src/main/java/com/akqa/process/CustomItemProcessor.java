package com.akqa.process;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.batch.item.ItemProcessor;

import com.akqa.model.MeetingSchedule;
/**
 * 
 * @author venunarukulla
 * This class will process item by item,
 * item is the meeting schedule entry for each user
 * @param <T>
 */
public class CustomItemProcessor<T> implements
		ItemProcessor<MeetingSchedule, MeetingSchedule> {

	@Override
	public MeetingSchedule process(MeetingSchedule item) throws Exception {
		
		setMeetingEndTime(item);
		
		//System.out.println("process... " + item);
		
		return item;
	}

	/**
	 * This method will extract the duration from MeetingSchedule bean and calculates the end time
	 * @param item
	 * @return
	 */
	public MeetingSchedule setMeetingEndTime(MeetingSchedule item) {
		String pattern = "HH:mm";
		Date date;
		try {
			date = new SimpleDateFormat(pattern, Locale.ENGLISH).parse(item
					.getMeetStartTime());
			Long startMilliSecs = date.getTime();
			Long resultMilli = startMilliSecs + 3600000
					* Integer.parseInt(item.getMeetingDuration());
			item.setMeetEndTime((new SimpleDateFormat(pattern, Locale.ENGLISH))
					.format(new Date(resultMilli)));
		} catch (ParseException e) {
			System.out.println("Exception:-(to be removed it later)" + e);
		}
		return item;
	}
}