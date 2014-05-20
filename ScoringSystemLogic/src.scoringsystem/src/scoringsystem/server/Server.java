package src.scoringsystem.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import src.scoringsystem.network.packages.DataPackage;

public class Server
{
    public static int port = 2406;
    public static String ip = "";
    public static String ipAddress;
    public static InetSocketAddress insa;
    public static ServerSocket server;
    public static ArrayList<Socket> socketList = new ArrayList<Socket>();
    public static HashMap<Socket, Integer> socketState = new HashMap<Socket, Integer>();
    public static ArrayList<DataPackage> dataPackageList = new ArrayList<DataPackage>();
    
    
}
