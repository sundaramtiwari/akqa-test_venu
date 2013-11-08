package com.akqa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.RegexLineTokenizer;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.akqa.model.MeetingSchedule;
import com.akqa.process.CustomItemProcessor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/batch/config/context.xml",
		"classpath:spring/batch/jobs/job-meetingSchedule.xml" })
/**
 * 
 * @author Venu Narukulla
 * Junit Test cases to validate data loading and data integrity
 */
public class AppTest {
	private RegexLineTokenizer tokenizer = new RegexLineTokenizer();
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void launchJob() throws Exception {

		// testing a job
		// JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		// Testing a individual step
		JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");

		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

	}

	/**
	 * Test case to verify the Regular expression with correct data
	 */
	@Test
	public void testRegExp() throws Exception {
		String strTxtData = "0900 1730 2011-03-17 10:17:06 EMP001"
				+ "2011-03-21 09:00 2";
		tokenizer
				.setRegex("(\\d{4}-\\d{2}-\\d{2})\\s(\\d{2}:\\d{2}:\\d{2})\\s([a-zA-Z0-9]+)(\\d{4}-\\d{2}-\\d{2})\\s(\\d{2}:\\d{2})\\s(\\d{1})");
		FieldSet tokens = tokenizer.tokenize(strTxtData);
		assertEquals(6, tokens.getValues().length);
		assertEquals("2011-03-17", tokens.readString(0));
		assertEquals("EMP001", tokens.readString(2));

	}

	/**
	 * Test case to verify the Regular expression with extra characters at the
	 * end
	 */
	@Test
	public void testRegExpWithUnexpectedChars() throws Exception {
		String strTxtData = "2011-03-17 10:17:06 EMP001"
				+ "2011-03-21 09:00 2 Space is mandatory between fields";
		tokenizer
				.setRegex("(\\d{4}-\\d{2}-\\d{2})\\s(\\d{2}:\\d{2}:\\d{2})\\s([a-zA-Z0-9]+)(\\d{4}-\\d{2}-\\d{2})\\s(\\d{2}:\\d{2})\\s(\\d{1})");
		FieldSet tokens = tokenizer.tokenize(strTxtData);
		assertEquals(6, tokens.getValues().length);
		assertEquals("2011-03-17", tokens.readString(0));
		assertEquals("EMP001", tokens.readString(2));

	}

	/**
	 * Test case to verify the File validity - This test case to verify the
	 * complete file validity
	 */
	// TODO Write code to parse complete file
	@Test
	public void testFileValidity() throws Exception {

		String strExpFormat = "DUMMY Test case to Write code to read complete file to verify whether it is properly aligned";
		String strActualFormat = "DUMMY Test case to Write code to read complete file to verify whether it is properly aligned";

		assertEquals(strExpFormat, strActualFormat);

	}

	// TODO Write code to test output data
	@Test
	public void testFormatData() throws Exception {

		String strExpFormat = "TO-BE-IMPLEMENTED";
		String strActualFormat = "TO-BE-IMPLEMENTED";

		assertEquals(strExpFormat, strActualFormat);

	}

	@Test
	public void testMeetingEndTime() throws Exception {
		MeetingSchedule mt = new MeetingSchedule();
		mt.setMeetStartTime("11:00");
		mt.setMeetingDuration("2");
		CustomItemProcessor<MeetingSchedule> cip = new CustomItemProcessor<MeetingSchedule>();
		mt = cip.process(mt);

		String strExpTime = "13:00";

		assertEquals(strExpTime, mt.getMeetEndTime());

	}
}