/*Author: Sonali Guleria
 * Date Created- 11-01-2015
 * Date Modified: 11-14-2015
 * Description: the program parsing the log file and answering below questions:
 * What’s the earliest commit to a file?
 * What user has the most number of commits?
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class P3A2_GULERIA_SONALI_FILES {
	


	/* declaration of all the variables begin*/
	
	private String fileName;
	private int totalFiles,maxAuthor;
	private ArrayList<Integer> authorCount = new ArrayList<>();
	//private ArrayList<String> commit = new ArrayList<>();
    private ArrayList<String> files = new ArrayList<>();
    private ArrayList<String> authorsFile = new ArrayList<>();
    private ArrayList<String> authors = new ArrayList<>();
    private ArrayList<Integer> authorsMaxCount = new ArrayList<>();
    private String revisionc;
	private int revisionCount;
	private String revisionSplit [] = new String[2];
	private ArrayList<String> firstCommits = new ArrayList<>();
	private int max = 0, newMax;
	private ArrayList<Integer> countCommitsMax =new ArrayList<>();;
	private ArrayList<String> countAuthorMax = new ArrayList<>();
	private ArrayList<String> tempAuthor = new ArrayList<>();
	protected File logFile;
	protected FileReader fileReaderLog;
	protected BufferedReader bufferedLog;	
	protected String text,textNew;

	
	



//Files generic information begins 
	
/* method to get total number of files in a log*/
	
	public int getTotalFiles(String t)
	{
		if(t.startsWith("Working file: "))
		{
			totalFiles++;
			
		}
		
		return(totalFiles);
		
		
	}
	
	/*method to store file names */
	
	public void storeFiles(String t)
	{
		if(t.startsWith("Working file: "))
		{
		
			fileName = t.substring("Working file: ".length());
			files.add(fileName);
			
		}
		
	}
	
	/* Method to get all the files*/ 
	
	
	public ArrayList<String> getFiles()
	{
		return(files);
	}
	
	/* method to get required filename*/
	
	public String getFile(int i)
	{
		
		return(files.get(i));
		
	}
	
// Authors information begin
	
	/* method to get list of authors of each file */
	
	public void storeAuthors(String t)
	{
		
		
		if(t.contains("author: "))
				
		{
			
	  String author[] = new String[100];
	  author = t.split("author: ");
	  String authorName=author[1];
	  author = authorName.split(";");
	  authorName = author[0].trim();
	 
	  authors.add(authorName); //maintaining  array of all the authors
	
	  
	  if(!authorsFile.contains(authorName))
	      {
		  
		  authorsFile.add(authorName);
	      }
	 
	    }
		
		 
	if(t.startsWith("=========="))
	{
		maxAuthorNameCount();
		storeAuthorCount(authorsFile);
		authorsFile.clear();   ;//clear for next file
		authors.clear();
		
	}
	
	}
	
	
	/* method to store author count per file*/
	
	public void storeAuthorCount(ArrayList<String> author)
	{
	
		int i = author.size();
		authorCount.add(i);
		
	}
	
	
	/* get method for storeAuthorCount */
	
	public ArrayList <Integer> getAuthorCount()
	
	{
		return(authorCount);
	}
	/* method to get indices of files with maximum commits to it */
	
	public int getMaxAuthors()
	{
		maxAuthor = Collections.max(getAuthorCount());
		
		return(maxAuthor);
	}
	
	/*indices of files with max authors committing to it */
	
	public ArrayList<Integer> storeMaxAuthorIndices()
	{
		getMaxAuthors();
		

		 for (int i = 0; i < getAuthorCount().size(); i++) 
		 {
			
		       if(getAuthorCount().get(i)==(maxAuthor)) 
		    	   
		       {
		    	   authorsMaxCount.add(i);
		    	   
		        }   
		 }	
		 
		 return(authorsMaxCount);
	}
	
/* storing the maximum commits per by any author file*/
	
	public void maxAuthorNameCount()
	{
		
		max =0;
		for(int j=0;j<authors.size();j++)
		{
			
			newMax = Collections.frequency(authors, authors.get(j));
			
		  if(newMax>max)
		  {
		      max = newMax;
		
		  }
		  
		}
		  
		  countCommitsMax.add(max);
		  maxAuthorsName();	
	}
	/* storing the Author Name with maximum commits per file*/
	
	public void maxAuthorsName()
	
	{

	
		ArrayList<String> tempAuthor2 = new ArrayList<>();
		
		for(int j=0;j<authors.size();j++ )
		{

			if((Collections.frequency(authors, authors.get(j))) == max)
			
			{
				tempAuthor.add(authors.get(j));	
			}
			
		}
		
		tempAuthor2.add(tempAuthor.get(0));
			
			
			for(int k=0; k<tempAuthor.size();k++)
			{
				
				if(!tempAuthor2.contains(tempAuthor.get(k)))
				{
					
					tempAuthor2.add(tempAuthor.get(k));
				}
			}
			
			if(tempAuthor2.size()==1)
			{
				countAuthorMax.add(tempAuthor2.get(0));
			}
			else
			{
				String joinAuthors = String.join(",", tempAuthor2);
				countAuthorMax.add(joinAuthors);
				
			}
			
			tempAuthor.clear();
			tempAuthor2.clear();
			
			
		}
	
	
	/* getter for count of max commits for a file*/
	
	public ArrayList <Integer> getCountCommitsMax()
	{

		return(countCommitsMax);
		
	}
	
/* getter for user with  max commits of all file*/
	
	public ArrayList <String> getAuthorCommitsMax()
	{

		return(countAuthorMax);
		
	}

/*getter for max commits per file*/
	
	public String getMaxAuthor(int i)
	{

		return(countAuthorMax.get(i));
		
	}
	
	
/*getter for maximum commits per file*/
	public int getMaxCommits(int i)
	{

		return(countCommitsMax.get(i));
		
	}
	
/*First commit date of the file */
	
	public void findFirstCommit(String t)
	{
		
		
		if(t.startsWith("date: "))
		{
		 
			String commitDates[];
			String commitDate;
			commitDates = t.split("date: ");
		    commitDate = commitDates[1];
			commitDates = commitDate.split(";");
			commitDate = commitDates[0];
			firstCommits.add(commitDate);
			
		}
		
     }
	
/* get first Commits of all file*/
	
	
	public ArrayList <String> getFirstCommit()
	{
		return(firstCommits);
	}
  
/*get first commit of a file*/
	
	public String getCommitFile(int i)
	{
		return(firstCommits.get(i));
	}
	
	
	/*class for revisions */
	
	public class REVISIONS
	{
	
		private int maxRevision;
		private ArrayList<Integer> revisions = new ArrayList<>();
		private ArrayList<Integer> revIndices = new ArrayList<>();
		private String maxFileName;
		
		
		/* method to get revisions per file*/
		
		public ArrayList<Integer> storeRevisions(String t)
		{
			
			
		if(t.startsWith("total revisions"))
		{
			
			revisionSplit = t.split("total revisions:");
			revisionc = revisionSplit[1].trim();
			revisionSplit =revisionc.split(";");
			revisionc = revisionSplit[0].trim();
			revisionCount = Integer.parseInt(revisionc);
		    revisions.add(revisionCount);
			
		}
		
		return(revisions);
		
	   }
		
		
		/*method to get max revision*/
		public int getMaxRevValue()
		{
			maxRevision = Collections.max(getRevisions());
			return maxRevision;
		}
		
		/*indices of files with max revisions*/
		
		public ArrayList<Integer> storeMaxRevisionIndices(int maxRevision)
		{
			 
			

			 for (int i = 0; i < getRevisions().size(); i++) 
			 {
				
			       if(getRevisions().get(i)==(maxRevision)) 
			    	   
			       {
			    	   revIndices.add(i);
			    	   
			        }
			       
			 }	
			 
			 return(revIndices);
		}
	
	/* returning max Revision files
		
       public String getMaxRevisionFile()
       
       {
    	   
    	   System.out.println("The total number of files with maximum revisions are:" + getrevIndices().size());
    	   for(Integer j : getrevIndices())
    	   {
    		   
    		  maxFileName = getFile(j);
    		  
    	   }
    	   
    	   return(maxFileName);
       }

	 */	
		
	//getter for Revisions	
		
       public ArrayList<Integer> getRevisions()
       {
    	   return(this.revisions);
       }
       
   //getter for maximum revision indices 
       public ArrayList<Integer> getrevIndices()
       {
    	   return(this.revIndices);
       }
	
}
	
}

	








