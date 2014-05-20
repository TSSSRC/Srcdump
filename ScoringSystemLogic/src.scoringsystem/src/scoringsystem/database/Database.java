package src.scoringsystem.database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import src.scoringsystem.database.models.DataBaseModelHashMap;

public class Database
{
	
	//****Reads****//
	public static String readFromDatabase()
	{
		return "";
	}
	
	
	//****Writes****//
	public static void writeToDatabase(DataBaseModelHashMap model)
	{
		Set<String> set = model.getModel().keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext())
		{
			String key = it.next();
			writeToDatabase(key, model.getModel().get(key));
		}
	}
	
	public static void writeToDatabase(String column, Object value)
	{
		
	}
	
	
	//****Queries****//
	public static ArrayList<String> selectQuery(String table, String[] params)
	{
		return null;
	}
	
	public static void insertQuery(String string, String[] strings)
	{
		
	}
}
