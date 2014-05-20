/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scoringsystem;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import src.scoringsystem.network.packages.DataPackage;

/**
 * 
 * @author Fabio
 */
public class GameServer
{
	
	/**
     *
     */
	public static int port = 2406;
	/**
     *
     */
	// public static DB database = new DB();
	public static String ip = "";
	public static String ipAddress;
	public static InetSocketAddress insa;
	/**
     *
     */
	public static ServerSocket server;
	/**
     *
     */
	public static ArrayList<Socket> listSockets = new ArrayList<Socket>();
	/**
     *
     */
	public static ArrayList<Integer> listClientsState = new ArrayList<Integer>();
	
	/**
     *
     */
	public static boolean checkLogin(String username, String pWord)
	{
		/**
		 * ResultSet rs; String password; try { rs = database.query("select
		 * password from tblUsers where name = '" + username + "'"); if
		 * (rs.next()) { password = rs.getString("password"); if
		 * (password.equals(pWord)) { return true; } else { return false; } }
		 * else { return false; } } catch (SQLException ex) {
		 * System.out.println("Error while loging in " + ex); } return false;
		 * 
		 */
		return true;// delete from this if want to work noramlly
	}
	
	public static boolean registerClient(String username, String password)
	{
		// try {
		// database.update("INSERT INTO tblUsers (`name`, `password`) VALUES ('"
		// + username + "', '" + password + "');");
		// } catch (SQLException ex) {
		// System.out.println(ex);
		return false;
		// }
		// return true;
	}
	
	public static ArrayList<DataPackage> listData = new ArrayList<DataPackage>();
	private static Runnable accept = new Runnable()
	{
		
		@Override
		public void run()
		{
			new Thread(send).start();
			new Thread(receive).start();
			while (true)
			{
				try
				{
					Socket socket = server.accept();
					
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					String typeClient = (String) ois.readObject();
					
					String username = "";
					
					if (typeClient.equalsIgnoreCase("GameClient"))
					{// Game Client
						System.out.println("Game Client recieved");
						
						ois = new ObjectInputStream(socket.getInputStream());
						username = (String) ois.readObject();
						System.out.println("Username: " + username);
						
						ois = new ObjectInputStream(socket.getInputStream());
						String password = (String) ois.readObject();
						System.out.println("Password: " + password);
						
						boolean accepted = true;
						for (int i = 0; i < listData.size(); i++)
						{
							if (((String)listData.get(i).getBaseDataObject("username")).toLowerCase().equals(username.toLowerCase()))
							{
								accepted = false;
								break;
							}
							
						}
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						if (checkLogin(username, password))
						{// checks in database if the login was succesful
							if (accepted)
							{
								
								oos.writeObject("Welcome to this server...");
								listClientsModel.addElement(username + " - " + socket.getInetAddress().getHostAddress() + " - "
								        + socket.getInetAddress().getHostName());
								System.out.println(username + " connected to the server");
								listClientsState.add(0);
								
								listData.add(new DataPackage());
								listSockets.add(socket);
							}
							else
							{
								oos.writeObject("This user is already connected");
							}
						}
						else
						{
							oos.writeObject("Unsucsesful login");
							accepted = false;
						}
					}
					else if (typeClient.equalsIgnoreCase("RegisterClient"))
					{// Registering
						System.out.println("Register Client");
						
						ois = new ObjectInputStream(socket.getInputStream());
						username = (String) ois.readObject();
						System.out.println("Username: " + username);
						
						ois = new ObjectInputStream(socket.getInputStream());
						String password = (String) ois.readObject();
						System.out.println("Password: " + password);
						boolean accepted = true;
						for (int i = 0; i < listData.size(); i++)
						{
							if (((String)listData.get(i).getBaseDataObject("username")).toLowerCase().equals(username.toLowerCase()))
							{
								accepted = false;
								break;
							}
						}
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						if (accepted)
						{
							if (registerClient(username, password))
							{
								oos.writeObject("Registered succesful");
							}
							else
							{
								oos.writeObject("Registered unsuccesful");
							}
							
						}
					}
					
				}
				catch (Exception ex)
				{
					System.out.println("Error1:" + ex);
				}
			}
			
		}
	};
	private static Runnable send = new Runnable()
	{
		
		@Override
		public void run()
		{
			ObjectOutputStream oos;
			
			while (true)
			{
				for (int i = 0; i < listSockets.size(); i++)
				{
					try
					{
						oos = new ObjectOutputStream(listSockets.get(i).getOutputStream());
						int clientState = listClientsState.get(i);
						oos.writeObject(clientState);
						
						oos = new ObjectOutputStream(listSockets.get(i).getOutputStream());
						oos.writeObject(listData);
						
						if (clientState == 1)
						{// Kicked
							disconnectClient(i);
							i--;
							System.out.println("Kicked User");
							
						}
						else if (clientState == 2)
						{// Server Disconnected
							disconnectClient(i);
							i--;
							System.out.println("Server disconnected");
						}
						
					}
					catch (Exception ex)
					{
						System.out.println("Error2:" + ex);
						System.out.println("User disconnected");
					}
				}
				
			}
			
		}
	};
	private static Runnable receive = new Runnable()
	{
		
		@Override
		public void run()
		{
			String oldData = "";
			ObjectInputStream ois;
			while (true)
			{
				for (int i = 0; i < listSockets.size(); i++)
				{
					
					try
					{
						ois = new ObjectInputStream(listSockets.get(i).getInputStream());
						int receiveState = (Integer) ois.readObject();
						
						ois = new ObjectInputStream(listSockets.get(i).getInputStream());
						DataPackage dp = (DataPackage) ois.readObject();
						
						if (!(oldData.equals(dp.toString())))
						{
							outFile.write(dp.toString());
							System.out.println(dp.toString());
							oldData = dp.toString();
						}
						listData.set(i, dp);
						if (receiveState == 1)
						{// client dc'ed by user
							disconnectClient(i);
							i--;
						}
						
					}
					catch (Exception ex)
					{// Client dc'ed
						disconnectClient(i);
						i--;
						
					}
					
				}
			}
			
		}
	};
	
	/**
	 * 
	 * @param index
	 */
	public static void disconnectClient(int index)
	{
		try
		{
			listClientsModel.remove(index);
			listClientsState.remove(index);
			listData.remove(index);
			listSockets.remove(index);
			
		}
		catch (Exception ex)
		{
		}
	}
	
	public static FileWriter outFile;
	/**
     *
     */
	public static JFrame frame;
	/**
     *
     */
	public static JPanel content;
	/**
     *
     */
	public static JPanel panel1;
	/**
     *
     */
	public static JPanel panel2;
	/**
     *
     */
	public static JPanel panel3;
	/**
     *
     */
	public static JButton btn_disconnect;
	/**
     *
     */
	public static JList<?> listClients;
	/**
     *
     */
	public static DefaultListModel<?> listClientsModel;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
        try {
            outFile = new FileWriter(new File("log.txt"));
            outFile.write("Data Recieved By Server:");
        } catch (IOException ex) {
            System.out.println("Error: Text file: " + ex);
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }

        try {

            //insa = new InetSocketAddress("41.132.26.162", port);
            ip = InetAddress.getLocalHost().getHostAddress() + ":" + port;
            ipAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println(InetAddress.getLocalHost());
            //server.bind(insa);
            //System.out.println(server.getInetAddress().getHostAddress());
            server = new ServerSocket(port, 0, InetAddress.getLocalHost());

            new Thread(accept).start();

        } catch (IOException ex) {
            System.out.println("Error3: " + ex);
            System.exit(0);
        }
        btn_disconnect = new JButton();
        btn_disconnect.setText("Disconnect");
        btn_disconnect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = listClients.getSelectedIndex();
                if (selected != -1) {
                    try {
                        listClientsState.set(selected, 1);

                    } catch (Exception ex) {
                        System.out.println("Error4: " + ex);
                    }
                }
            }
        });
        listClientsModel = new DefaultListModel<Object>();
        listClients = new JList<Object>(listClientsModel);
        listClients.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (lse.getValueIsAdjusting()) {
                    System.out.println(listClients.getSelectedIndex());
                }
            }
        });

        frame = new JFrame();
        frame.setTitle("Server - " + ip);
        frame.setResizable(false);

        frame.addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent we) {
            }

            public void windowClosing(WindowEvent we) 
            {
                try {
                    outFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                while (listSockets.size() != 0) {
                    try {
                        for (int i = 0; i < listClientsState.size(); i++) {
                            listClientsState.set(i, 2);


                        }

                        System.exit(0);
                        }
                
                     catch (Exception ex) {
                    
                     }
                }
            }

			@Override
            public void windowActivated(WindowEvent arg0)
            {
	            // TODO Auto-generated method stub
	            
            }

			@Override
            public void windowClosed(WindowEvent arg0)
            {
	            // TODO Auto-generated method stub
	            
            }

			@Override
            public void windowDeactivated(WindowEvent arg0)
            {
	            // TODO Auto-generated method stub
	            
            }

			@Override
            public void windowDeiconified(WindowEvent arg0)
            {
	            // TODO Auto-generated method stub
	            
            }

			@Override
            public void windowIconified(WindowEvent arg0)
            {
	            // TODO Auto-generated method stub
	            
            }
        }
                

        );
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 1, 1, 1));
        panel1.add(btn_disconnect);

        panel2 = new JPanel();
        panel2.add(new JLabel(ip));

        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(1, 1));
        panel3.add(panel1, BorderLayout.NORTH);
        panel3.add(new JScrollPane(listClients), BorderLayout.CENTER);
        panel3.add(panel2, BorderLayout.SOUTH);

        content = new JPanel();
        content.setLayout(new GridLayout(1, 1, 1, 1));
        content.add(panel3);

        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.setContentPane(content);
        frame.pack();
        frame.setSize(350, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);




    }
}
