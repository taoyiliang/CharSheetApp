/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import com.google.gdata.data.Person;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.client.spreadsheet.FeedURLFactory;

import com.google.gdata.client.spreadsheet.CellQuery;

import com.google.gdata.data.Feed;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.CellFeed;

import com.google.gdata.data.Entry;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.CellEntry;

import com.google.gdata.data.spreadsheet.Cell;
import com.google.gdata.util.ServiceException;
import java.io.IOException;

//import sample.util.SimpleCommandLineParser;
//TODO this seems important mostly to get the user/pass

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author TaoYiLiang
 */

  // this all from https://code.google.com/p/gdata-java-client/source/browse/trunk/java/sample/spreadsheet/IndexClient.java
  
  public class DriveInterface{
    private SpreadsheetService service;
    private FeedURLFactory factory;
    private SpreadsheetEntry spreadsheet;
    private WorksheetEntry worksheet;
    
    public DriveInterface(String username,String password) throws Exception {
      factory = FeedURLFactory.getDefault();
      service = new SpreadsheetService("gdata-sample-spreadsheetindex"); //TODO is this right?
      service.setUserCredentials(username,password);
      
    }
   
    public List<SpreadsheetEntry> getSpreadsheetEntries() throws Exception
    {
      SpreadsheetFeed feed = service.getFeed(factory.getSpreadsheetsFeedUrl(),SpreadsheetFeed.class);
      return feed.getEntries();
    }
    public List<WorksheetEntry> getWorksheetEntries(SpreadsheetEntry spreadsheet) throws Exception
    {
      return spreadsheet.getWorksheets();
    }
    
    public void getDesiredSheet(String spreadname, String workname) throws Exception
    {
      List<SpreadsheetEntry> spreads = getSpreadsheetEntries();
      System.out.println("Searching for spreadsheet...");
      for (SpreadsheetEntry ss : spreads)
      {
        System.out.println("  Checking "+ss.getTitle().getPlainText());
        if (spreadname.equals(ss.getTitle().getPlainText()))
        {
          System.out.println("  Found!");
          spreadsheet = ss;
          break;
        }
      }
      List<WorksheetEntry> works = getWorksheetEntries(spreadsheet);
      System.out.println("Searching for worksheet...");
      for (WorksheetEntry ws : works)
      {
        System.out.println("  Checking "+ws.getTitle().getPlainText());
        if (workname.equals(ws.getTitle().getPlainText()))
        {
          System.out.println("  Found!");
          worksheet = ws;
          break;
        }
      }
    }
    
    public void ChangeCellEntry(Integer row,Integer col, String val) throws IOException, ServiceException
    {
      URL cellFeedURL = worksheet.getCellFeedUrl();
      CellFeed cellFeed = service.getFeed(cellFeedURL, CellFeed.class);
      CellEntry newEntry = new CellEntry(row,col,val);
      service.insert(cellFeedURL, newEntry);
    }
    
    public void ReadCellEntry(Integer row,Integer col) throws IOException, ServiceException
    {
      URL cellFeedURL = worksheet.getCellFeedUrl();
      CellFeed cellFeed = service.getFeed(cellFeedURL, CellFeed.class);
      //TODO
    }
  
  //example usage
  //the biggest problem is getting the username and password securely.
  /*public static void main(String[] args) throws Exception {

    IndexClient client = new IndexClient(username, password);

    for (SpreadsheetEntry spreadsheet : client.getSpreadsheetEntries())
    {
      System.out.print(spreadsheet.getTitle().getPlainText());
    }
  }*/
  
}
