venu-akqatest
=============

Load Database dump from CSV to MySQL using Spring Batch framework
-----------------------------------------------------------------
I have created this temparory project to read a TEXT file and format data using a Spring Batch framework 
which is a lightweight, comprehensive batch framework designed to enable the development of robust batch applications 
vital for the daily operations of enterprise systems

Requirement: 
------------
		See the "Meeting scheduler test.docx" document located in the project root folder

Project Details:
----------------
		I have selected the Spring batch framework, because we can complete the task in a simple way
		which also allow to extend without restrictions, configurable to schedule to run as a batch job  		
		
MAIN SOURCE CODE (src/main/) 
----------------------------
	 1. CustomItemProcessor.java	-	To extract the meeting duration and calculate the Meeting End Time
	 2. CustomItemWriter.java		-	To format the records as per the requirement
	 3. MeetingSchedule.java   - This is a java bean to map the records in text file.
	 4. App.java  - To launch the application as a standalone application
	 (src/main/resources)
	 5. context.xml   - Job information
	 6. job-meetingSchedule.xml  - Defines Spring Batch job, TXT file location, Item Reader and Item Writer information.

TEST SOURCE CODE: (src/test)
----------------------------
	 1. AppTest.java - Junit test cases. (Only validates data insertion, it may need another test case to test data
	 		format after reading from TXT.I have put a dummy test case to implement this task in the future)
	 2. Resources (same as above XMLs)

How to build and run the solution:
----------------------------------
To run this code your machine should have Java and Maven configured in classpath

	1. Import/download the project from Github
	2. Open command prompt/terminal and navigate to project's home directory 
	3. Navigate to Project home directory (ex: /akqa-test_venu)
	5. Run the below commands (Assuming Java and Maven installed in the machine and available in command prompt)
  	 	1. mvn clean package
   		2. mvn test (To run test Junit cases)
   		3. java -jar target/AkqaTestVenu.jar - To see output(This is a stand alone executable jar file buit with spring boot

	6. If we setup a database connection , this batch job will store all the executation details as Spring Batch job report 
		(Spring batch will create a schema automatically to store the job diagnostics as a report.)

Features:
---------
1. Configurable and decoupled concerns
2. Effective reporting that stores information in database if datasource configured
3. Easy to customize to include new features in the future.
4. Can be scheduled the job to run automatically to format daily meeting schedules.
6. Easy to manage with Maven for Continous Integration
7. Possible to follow Test Driven Development(TDD)

Note:
-----
	There is a duplicate meeting request in the input data, which should actually be taken care at the time of scheduling a meeting
	Meeting start and end times should be restricted at the time of scheduling, so I think it is not a wise idea to fix by 
	formatting though it is a test.
	
   
Known issues: 
------------
1. To separate each record, I have put a semi colon(;) at the end of each record. 
	(There is an alternative way but it needs more time to implement)
	