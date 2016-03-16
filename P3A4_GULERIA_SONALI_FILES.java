/*Author: Sonali Guleria
 * Date Created- 11-01-2015
 * Date Modified: 11-14-2015
 * Description: the program parsing the log file and stores results to csv answering below questions:
 * First column: the name of every file changed
 * Second column: Number of commits
 * Third column: First date of commit
 * Fourth column: Last date of commit
 * What’s the earliest commit to a file?
 * What user has the most number of commits?
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P3A4_GULERIA_SONALI_FILES {

	/* declaration of all the variables begin*/
	private ArrayList<String> authorsNames = new ArrayList<>();
	private File logFile;
	private FileReader fileReaderLog;
	protected BufferedReader bufferedLog;	
	private ArrayList<String> allAuthors= new ArrayList<>(); ;
	private ArrayList <String> auth= new ArrayList<> ();
	private ArrayList <String> newAuthor= new ArrayList<> ();
	private ArrayList <String> newAuthorFiles = new ArrayList<> ();
	private ArrayList <Integer> authorCountCom = new ArrayList<> ();
	private ArrayList <Integer> authorComFile = new ArrayList<> ();
	private ArrayList <Date> firstCommitsUser = new ArrayList<> ();
	private ArrayList <Date> lastCommitsUser = new ArrayList<> ();
	private ArrayList <Date> dateNew = new ArrayList<>();
	protected String text;
	private String fileN;
	

	/*method to get fileName */
	
	public void getFileName(String t)
	{
		fileN = t;
	}
	
	/*method to read file*/
	
	public void readFile(String t)
	
	{
		try{
			
			logFile = new File(t);
			fileReaderLog = new FileReader(logFile);
			bufferedLog = new BufferedReader(fileReaderLog);

		}
		
		catch (FileNotFoundException e)
	   	   {
				System.out.println("File Not Found. Make sure it exists.");
			}
		
	   	catch(IOException ex) 
	   	 {
	   		System.out.println("Error reading file");
	   	 }

	}
	
// Authors information begin
	
	/* method to store all authors for entire file */
	
	public void allAuthors(String text) 
	
	{
		
		if(text.contains("author: "))
			
		{
			
	  String authorAll[] = new String[100];
	  authorAll = text.split("author: ");
	  String authorNames=authorAll[1];
	  authorAll = authorNames.split(";");
	  authorNames = authorAll[0].trim();
	  authorsNames.add(authorNames);  //list of all the authors
		
	  if(!auth.contains(authorNames))
	  {
	   auth.add(authorNames);  
	  }
	
		}
		
	  if(text.startsWith("=========="))
		{ 
		 
		  allAuthors.addAll(auth);
		  auth.clear();
		}
        } 
	  


	/* method to get all unique user and their associated file count */
	
	public void getUniqueAuthors()
	{
		
		
		for(int i =0 ; i<allAuthors.size() ; i++)
			
		{
			int  n = Collections.frequency(allAuthors, allAuthors.get(i));
			
			if(!newAuthor.contains(allAuthors.get(i)))
			{
				newAuthor.add(allAuthors.get(i));
				authorComFile.add(n);
			}

		}


	}
	/*method to get list of unique authors*/
	
	public ArrayList <String> getAllAuthors()
	{
		return newAuthor;
		
	}
	
	/*method to get Single author */
	
	public String getAuthor(int i)
	
	{
		return newAuthor.get(i);
	
	}
	
	/*method to get the interacted files count */
	
	public int getTotalFileAuthor(int i)
	{
		return authorComFile.get(i);
		
	}
	
	/* method to store users and related number of revisions.commits they worked on */
	
	
	public void getTotalCommitsUsers()
	
	{
		
        for(int i =0 ; i<authorsNames.size() ; i++)
			
		{
			int  n = Collections.frequency(authorsNames, authorsNames.get(i));
			
			if(!newAuthorFiles.contains(authorsNames.get(i)))
			
			{
		       
				newAuthorFiles.add(authorsNames.get(i));
				authorCountCom.add(n);
				
			}
		}	
	}
	
	
	/*method to get total commit count*/
	
	public int getTotalCommitAuthor(int i)
	{
		return authorCountCom.get(i);
		
	}
	
	/*method to get entire list of authors*/
	
	public ArrayList<String> getNewAuthor()
	{
		return newAuthor;
		
	}
	
	
	/*method to get first and last commit of a user*/
	
	public void getFirstLastCommit() throws IOException
	{
	             String readLine;
	           
				 for(int i =0; i<newAuthor.size();i++)
					 
				 {
					 readFile(fileN);
					 
					 while((readLine = bufferedLog.readLine()) != null)
					 {
						   
						   String date[] = new String[100];
						    String dateSplit;
					     	if(readLine.contains(newAuthor.get(i)) && readLine.startsWith("date:"))
							{
			
					     		
								date = readLine.split("date: ");
						        dateSplit = date[1];
						        date = dateSplit.split(";");
								dateSplit= date[0].trim();
								dateSplit = dateSplit.substring(0,18);
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
								Date dateN = new Date();
						
								try {
									dateN = simpleDateFormat.parse(dateSplit);
									
								} catch (ParseException e) {
									System.out.println("Bad date");
								}
				     
								dateNew.add(dateN);
							} 	
					 }
					 
					 Collections.sort(dateNew);
					 
					 firstCommitsUser.add(dateNew.get(0));
					 lastCommitsUser.add((dateNew.get(dateNew.size()-1)));
					 dateNew.clear();
			     }

				  
	}
				 
	/*getter for first  commit date*/
	
	public String getFirstCommitDate(int i)
	{
		Date tempDate = firstCommitsUser.get(i);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = simpleDateFormat.format(tempDate);
        return date;
		
	}
	
	/*getter for last commit date*/
	
	public String getLastCommitDate(int i)
	{
		
		Date tempDate = lastCommitsUser.get(i);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = simpleDateFormat.format(tempDate);
		return date;
	}


	
}


