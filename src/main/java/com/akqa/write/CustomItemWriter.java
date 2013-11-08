package com.akqa.write;
 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.batch.item.ItemWriter;

import com.akqa.model.MeetingSchedule;

/**
 * 
 * @author venunarukulla
 *	This method will format the out put returned by the reader/processor of batch:job 
 */
public class CustomItemWriter implements ItemWriter<MeetingSchedule>{

	@Override
	public void write(List<? extends MeetingSchedule> items) throws Exception {
		formatToAkqaStyle(items);	
	}
	
/**
 * This method will format the output the required AKQA test output
 * @param items
 */
	public void formatToAkqaStyle(List<? extends MeetingSchedule> items){
		List<MeetingSchedule> newList = new ArrayList<MeetingSchedule>(items);
		Collections.sort(newList);
		Date date = null;
		System.out.println("##############################Meeting Schedule Output Start##############################");
		for (int i = 0; i < newList.size(); i++) {
			if(date != null && date.equals(newList.get(i).getMeetingDate())){
				System.out.println(newList.get(i).getMeetStartTime()+"  "+newList.get(i).getMeetEndTime()+"  "+newList.get(i).getEmpId());				
			}else{
				date = newList.get(i).getMeetingDate();
				System.out.println((new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)).format(date));				
				System.out.println(newList.get(i).getMeetStartTime()+"  "+newList.get(i).getMeetEndTime()+"  "+newList.get(i).getEmpId());
			}
		}
		System.out.println("##############################Meeting Schedule Output End##############################");
	}
  }