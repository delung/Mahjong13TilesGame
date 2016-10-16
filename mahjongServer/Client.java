package mahjongServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * The client class allows a game or application to communicate with a server of type Server in the mahjongServer package through the use of ObjectInput/OutputStream
 * @author Nick Werblun
 * @author Derek Lung
 * @version 1.0 8/6/15
 * 
 */
public class Client{

	ObjectInputStream inStream;
	ObjectOutputStream outStream;
	
	/**
	 * Constructor takes arguments for a connection source and runs the "connect" method. Execeptions are thrown if the connection fails.
	 * @param host
	 * @param port
	 * @param username
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public Client(String host, int port, String username) throws UnknownHostException, IOException{
		connect(host, port, username);
	}
	/**
	 * Makes use of the Socket class to connect to a remote server. Once the socket is connected, an inputstream from the server and an output stream to the server are established.
	 * @param host
	 * @param port
	 * @param username
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void connect(String host, int port, String username)
	{
		boolean connecting = true;
		while(connecting){
		try{
		@SuppressWarnings("resource")
		Socket SOCKET = new Socket(host, port);
		InputStream is = SOCKET.getInputStream();
		inStream = new ObjectInputStream(is);
		outStream = new ObjectOutputStream(SOCKET.getOutputStream());
		outStream.flush();
		connecting = false;
		}
		catch(IOException e){e.printStackTrace();}
		}
	}
	/**
	 * Receives any object the server sends and returns it for use in other classes. 
	 * @return an object of type "Object"
	 * @throws IOException
	 */
	public Object receive() throws IOException
	{
		try
		{
			return inStream.readObject();
		}
		catch(ClassNotFoundException e){throw new IOException();}
	}
	/**
	 * Accepts any object and sends it to the server
	 * @param Object o
	 * @throws IOException
	 */
	public void send(Object o) throws IOException
	{
		if(outStream == null) throw new IOException();
		outStream.writeObject(o);
		outStream.reset();
		outStream.flush();
	}
	
	/*
	public static void main(String[] args) throws UnknownHostException, IOException{
			String host = "107.199.245.55";
			Object object = null;
			//String host = "localhost";
			String username = JOptionPane.showInputDialog("Username:");
			int port = 27016; 
			
			Client client = new Client(host, port, username);
			client.send(username);
			while(true)
			{
				object = client.receive();
			}
			
	}
	*/
}
