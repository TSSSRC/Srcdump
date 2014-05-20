package src.scoringsystem.database.models;

import java.util.ArrayList;

import src.scoringsystem.database.Database;

public class DataBaseModelBasic
{
	private String Name;
	private String Surname;
	
	
	public void getModel()
	{
		ArrayList<String> values = Database.selectQuery("table", new String[] {"value"});
		Name = values.get(0);
		Surname = values.get(1);
	}
	
	public void writeModel()
	{
		Database.insertQuery("table", new String[] {Name, Surname});
	}
	
	
	
	/**
	 * @return the name
	 */
    public String getName()
    {
	    return Name;
    }
	/**
	 * @param name the name to set
	 */
    public void setName(String name)
    {
	    Name = name;
    }
	/**
	 * @return the surname
	 */
    public String getSurname()
    {
	    return Surname;
    }
	/**
	 * @param surname the surname to set
	 */
    public void setSurname(String surname)
    {
	    Surname = surname;
    }
}
