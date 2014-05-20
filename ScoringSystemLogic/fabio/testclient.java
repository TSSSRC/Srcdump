package src.scoringsystem.fabio;

import java.util.ArrayList;

public class testclient
{
	public static GameClient gc = new GameClient("196.249.10.29", 2406, "bob", "1234");
	public static DataPackage dp = new DataPackage();
	public static ArrayList<DataPackage> dpAll = new ArrayList<DataPackage>();
	public static int id =0;
	public static void main(String[] args)
	{
		dp.ID = id;
		dp.message="Hello";
		gc.updateData(dp);
		dpAll = gc.getData();
		for(int i=0; i<dpAll.size();i++){	
			System.out.println(dpAll.get(i).message);
		}
	}
	 
}
