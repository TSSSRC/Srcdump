/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scoringsystem.fabio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Fabio
 */
public class GameClient
{
	
	/**
	 * 
	 * @param ipA
	 * @param p
	 * @param name
	 */
	private int runnableState = -1;
	
	@SuppressWarnings("unused")
	public GameClient(String ipA, int p, String name, String password)
	{
		
		try
		{
			String local;
			
			try
			{
				local = InetAddress.getLocalHost().getHostAddress() + ":" + port;
			}
			catch (UnknownHostException ex)
			{
				local = "Network Error";
			}
			
			ip = ipA;
			port = p;
			
			socket = new Socket(ip, port);
			
			username = name;
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject("GameClient");
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(username);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(password);
			
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			String response = (String) ois.readObject();
			
			if (response.equals("This user is already connected"))
			{
				runnableState = 0;
			}
			else if (response.equals("Unsucsesful login"))
			{
				runnableState = 1;
			}
			else
			{
				runnableState = 2;
				
			}
			
			new Thread(send).start();
			new Thread(receive).start();
			
		}
		catch (Exception ex)
		{
			System.out.println("Error: " + ex);
			runnableState = 3;
		}
	}
	
	public int getRunnableState()
	{
		return runnableState;
	}
	
	public void close()
	{
		try
		{
			socket.close();
		}
		catch (IOException ex)
		{
			Logger.getLogger(RegisterClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
     *
     */
	public int state = 0;
	/**
     *
     */
	public boolean connected = true;
	Runnable send = new Runnable()
	{
		
		@Override
		public void run()
		{
			ObjectOutputStream oos;
			while (connected)
			{
				if (socket != null)
				{
					try
					{
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(state);
						ourData.username = username;
						
						if (ourData != null)
						{
							oos = new ObjectOutputStream(socket.getOutputStream());
							System.out.println("Data Being sent");
							System.out.println(ourData.toString());
							oos.writeObject(ourData);
						}
						else
						{
							break;
						}
						if (state == 1)
						{
							connected = false;
							socket = null;
							System.exit(0);
							
						}
					}
					catch (Exception ex)
					{
						System.out.println("Error: " + ex);
					}
				}
				else
				{
					break;
				}
			}
		}
	};
	Runnable receive = new Runnable()
	{
		
		@SuppressWarnings("unchecked")
        @Override
		public void run()
		{
			
			ObjectInputStream ois;
			
			while (connected)
			{
				try
				{
					ois = new ObjectInputStream(socket.getInputStream());
					int receiveState = (Integer) ois.readObject();
					
					if (receiveState == 1)
					{
						connected = false;
						socket = null;
						System.exit(0);
						
					}
					else if (receiveState == 2)
					{
						connected = false;
						socket = null;
						System.exit(0);
					}
					
					ois = new ObjectInputStream(socket.getInputStream());
					Object obIn = ois.readObject();
					ArrayList<DataPackage> listData = null;
					if (obIn instanceof ArrayList<?>)
					{
						listData = (ArrayList<DataPackage>) obIn;
					}
					
					for (int i = 0; i < listData.size(); i++)
					{
						DataPackage dp = listData.get(i);
						if (listData.size() != other.size())
						{
							if (listData.size() > other.size())
							{
								other.add(dp);
							}
							if (listData.size() < other.size())
							{
								other.remove(0);
							}
						}
						else
						{
							other.set(i, dp);
						}
						
					}
				}
				catch (Exception ex)
				{
				}
			}
		}
	};
	/**
     *
     */
	public static Socket socket;
	/**
     *
     */
	public static int port = 2406;
	/**
     *
     */
	public static String ip = "";
	/**
     *
     */
	public String username = "";
	/**
     *
     */
	
	/**
     *
     */
	public ArrayList<DataPackage> other = new ArrayList<DataPackage>();
	/**
     *
     */
	public DataPackage ourData = new DataPackage();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<DataPackage> getData()
	{
		for (int i = 0; i < other.size(); i++)
		{
			try
			{
				DataPackage dp = other.get(i);
				if (dp.username.equalsIgnoreCase(username))
				{
					System.out.println("Data Received");
					System.out.println(other.get(i).toString());
				}
			}
			catch (Exception ex)
			{
			}
			
		}
		
		return other;
	}
	
	/**
	 * 
	 * @param od
	 */
	public void updateData(DataPackage od)
	{
		ourData = od;
	}
}
