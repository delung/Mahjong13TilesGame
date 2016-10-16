package mahjongServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;


import mahjongCode.Player;
import mahjongCode.Tile;

public class ClientManager extends Thread implements Runnable {
	
	Socket SOCK;
	String username;
	public ClientManager(Socket SOCK)
	{
		this.SOCK = SOCK;
	}
	
	public void run(){
		boolean working = true;
		try{
			ObjectInputStream inStream = new ObjectInputStream(SOCK.getInputStream());
			while(working){
				working = handle(inStream);
			}
		}
		catch(SocketException e){
			e.printStackTrace();
			System.out.println("Cannot get inputstream");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked", "static-access" })
	public synchronized boolean handle(ObjectInputStream inStream){
		Object object = null;
		String string;
		try{
			object = inStream.readObject();
			if(object instanceof String)
			{
				string = (String)object;
				if(string.startsWith("!!?"))
				{
					username = string.substring(3, string.length());
					synchronized (Server.class)
					{
						Server.CurrentUsers.add(username);
					}
					Server.serverGUI.addText(Server.serverGUI.getDebugPane(), "User connected: " + username + SOCK.getRemoteSocketAddress());
					Server.serverGUI.addText(Server.serverGUI.getUsersPane(), username);
					Server.serverGUI.addText(Server.serverGUI.getDebugPane(), "Number of users: " + Server.CurrentUsers.size());		
				}
				else if(string.equals("Ready"))
				{
					Server.ready++;
					Server.check = true;
				}
				else if(string.equals("remove1"))
				{
					Server.remove = true;
				}
				else if(string.startsWith("win"))
				{
					Server.somethingHappened = true;
					int i = Integer.parseInt(string.substring(3));
					if(i == 0) Server.player1Move = string.substring(0, 3);
					if(i == 1) Server.player2Move = string.substring(0, 3);
					if(i == 2) Server.player3Move = string.substring(0, 3);
					if(i == 3) Server.player4Move = string.substring(0, 3);
					
				}
				else if(string.startsWith("poong"))
				{
					Server.somethingHappened = true;
					int i = Integer.parseInt(string.substring(5));
					if(i == 0) Server.player1Move = string.substring(0, 5);
					if(i == 1) Server.player2Move = string.substring(0, 5);
					if(i == 2) Server.player3Move = string.substring(0, 5);
					if(i == 3) Server.player4Move = string.substring(0, 5);
					
				}
				else if(string.startsWith("sung"))
				{
					Server.somethingHappened = true;
					int i = Integer.parseInt(string.substring(4));
					if(i == 0) Server.player1Move = string.substring(0, 4);
					if(i == 1) Server.player2Move = string.substring(0, 4);
					if(i == 2) Server.player3Move = string.substring(0, 4);
					if(i == 3) Server.player4Move = string.substring(0, 4);
				}
				else if(string.startsWith("goong"))
				{
					Server.somethingHappened = true;
					int i = Integer.parseInt(string.substring(5));
					if(i == 0) Server.player1Move = string.substring(0, 5);
					if(i == 1) Server.player2Move = string.substring(0, 5);
					if(i == 2) Server.player3Move = string.substring(0, 5);
					if(i == 3) Server.player4Move = string.substring(0, 5);
				}
				else if(string.equals("redo"))
				{
					Server.redo = true;
				}
				else if(string.equals("resume"))
				{
					Server.wait = false;
				}
			}
			else if (object instanceof Integer)
			{
				Server.removeTiles = (Integer) object;
			}
			
			else if (object instanceof Player)
			{
				Player state = (Player) object;
				Server.setPlayer(state);
			}
			
			else if (object instanceof ArrayList<?>)
			{
				ArrayList<?> temp = (ArrayList<?>) object;
				if(temp.get(0) instanceof Object)
				{
					Server.winHand = (ArrayList<Object>) temp;	
				}
			}
			else if (object instanceof Tile)
			{
				synchronized(Server.class){
					// tells the server what tile was just played
					Tile temp = (Tile) object;
					Server.setPlayed(temp);
				}
			}
		}
		
		catch(ClassNotFoundException ce){ce.printStackTrace();}
		catch(IOException e){
			e.printStackTrace();
			for(int i = Server.ConnectionArray.size() - 1; i >= 0; i--)
			{
				if(Server.ConnectionArraySOCKET.get(i).equals(SOCK))
				{
					Server.serverGUI.addText(Server.serverGUI.getDebugPane(), "User " + Server.server.CurrentUsers.get(i) + SOCK.getRemoteSocketAddress() + " disconnected");
					Server.serverGUI.clearText(Server.serverGUI.getUsersPane());
					Server.server.CurrentUsers.remove(i);
					Server.serverGUI.addText(Server.serverGUI.getDebugPane(), "Number of users: " + Server.server.CurrentUsers.size());
					Server.ConnectionArraySOCKET.remove(i);
					Server.ConnectionArray.remove(i);
					for (int i2 = 1; i2 <= Server.server.CurrentUsers.size(); i2++){
						Server.serverGUI.addText(Server.serverGUI.getUsersPane(), Server.server.CurrentUsers.get(i2-1));
					}
					if (!(Server.ConnectionArray.size() == 0)) Server.shareToAll(Server.ConnectionArray.size());
				}

				
			}
			return false;
			
		}
		return true;
	}
	
}
