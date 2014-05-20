package src.scoringsystem.database.models;

import java.util.HashMap;

import src.scoringsystem.database.Database;

public class DataBaseModelHashMap
{
	private static HashMap<String , Object> DataModel;
	
	public DataBaseModelHashMap()
	{
		DataModel = new HashMap<>();
	}

	/**
	 * @return the model
	 */
    public HashMap<String , Object> getModel()
    {
	    return DataModel;
    }
    
    public boolean writeToDataBase()
    {
    	Database.writeToDatabase(this);
    	return true;
    }
    
    
    public boolean readFromDataBase()
    {
    	Database.readFromDatabase();
    	return true;
    }
    
    public String getName()
    {
    	return (String)this.getModel().get("name");
    }
    
    public String getSurname()
    {
    	return (String)this.getModel().get("surname");
    }
}
