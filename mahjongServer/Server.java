package mahjongServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import mahjongCode.GameLoop;
import mahjongCode.Player;
import mahjongCode.Tile;

public class Server
{

	public static ArrayList<ObjectOutputStream> ConnectionArray = new ArrayList<ObjectOutputStream>();
	public static List<String> CurrentUsers = Collections.synchronizedList(new ArrayList<String>());
	public static ArrayList<Socket> ConnectionArraySOCKET = new ArrayList<Socket>();
	public List<Tile> TileStack = Collections.synchronizedList(new ArrayList<Tile>());
	public static ServerGUI serverGUI;
	public int port;
	public ServerSocket SERVERSOCK;
	public static Integer removeTiles = 0;
	public static GameLoop game = new GameLoop();
	public static Server server;
	private static Tile played = null;
	public static boolean remove = false;
	public static ArrayList<Player> playerStates = new ArrayList<Player>();
	public static boolean somethingHappened = false;
	public static int currentPos = 0;
	public static String player1Move = "";
	public static String player2Move = "";
	public static String player3Move = "";
	public static String player4Move = "";
	public static String move = "";
	public static boolean redo = false;
	public static boolean wait = true;
	public static boolean wait2 = true;
	public static ArrayList<Object> winHand = new ArrayList<Object>();
	public static int ready = 0;
	public static boolean check = false;

	public Server(int port)
	{
		this.port = port;
		try
		{
			SERVERSOCK = new ServerSocket(port);
		}
		catch (IOException e)
		{
			System.out.println("Unable to start");
			e.printStackTrace();
		}
		serverGUI = new ServerGUI();
		serverGUI.initialize();

	}

	public List<String> getCurrentUsers()
	{
		return CurrentUsers;
	}

	public List<Tile> getTileStack()
	{
		return TileStack;
	}

	public void waitForClients(ServerSocket SERVERSOCK)
	{
		serverGUI.addText(serverGUI.getDebugPane(), "Waiting for clients...");

		while (ConnectionArray.size() < 4 && CurrentUsers.size() < 4)
		{
			try
			{
				if (!(ConnectionArray.size() == 0)) shareToAll(ConnectionArray.size());
				Socket client = SERVERSOCK.accept();
				ConnectionArray.add(new ObjectOutputStream(client.getOutputStream()));
				ConnectionArraySOCKET.add(client);
				Thread t = new Thread(new ClientManager(client));
				t.start();
				if (!(ConnectionArray.size() == 0)) shareToAll(ConnectionArray.size());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void shareToAll(Object o)
	{
		for (ObjectOutputStream stream : ConnectionArray)
		{
			try
			{
				Thread.sleep(100);
				stream.writeObject(o);
				stream.reset();
				stream.flush();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void distributeTileStack(Object o, int playerNum)
	{
		try
		{
			ConnectionArray.get(playerNum).writeObject(o);
			ConnectionArray.get(playerNum).reset();
			ConnectionArray.get(playerNum).flush();
			Thread.sleep(100);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void draw13(int playerNum)
	{
		try
		{
			// draw 13 tiles
			Thread.sleep(100);
			ConnectionArray.get(playerNum).writeObject("draw13");
			ConnectionArray.get(playerNum).reset();
			ConnectionArray.get(playerNum).flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static boolean checkConnection()
	{
		if (Server.CurrentUsers.size() < 4) return false;
		else
		{
			return true;
		}
	}

	public static void setPlayed(Tile tile)
	{
		played = tile;
	}

	public static void setPlayer(Player player)
	{
		if (player.getPosition() == 0) playerStates.set(0, player);
		if (player.getPosition() == 1) playerStates.set(1, player);
		if (player.getPosition() == 2) playerStates.set(2, player);
		if (player.getPosition() == 3) playerStates.set(3, player);
	}

	public static void main(String[] args) throws InterruptedException
	{
		server = new Server(Integer.parseInt(JOptionPane.showInputDialog("Port:")));
		while (!serverGUI.started)
		{
			Thread.sleep(1);
		}
		// ArrayList for flowers each player had in the beginning

		Server.playerStates.add(null);
		Server.playerStates.add(null);
		Server.playerStates.add(null);
		Server.playerStates.add(null);

		server.waitForClients(server.SERVERSOCK);
		Server.shareToAll(server.getCurrentUsers());

		game.createStack();
		// game.createTestStack();
		server.TileStack = game.getStack();
		for (int i = 0; i <= 3; i++)
		{
			serverGUI.addText(serverGUI.getDebugPane(), "Giving Player " + (i + 1) + " tile stack.");
			Server.distributeTileStack(server.TileStack, i);
		}

		// Tells the clients to draw 13 tiles
		// Thread sleep is to make sure the server receives how many flowers
		// each client has
		// Also recieves the state for each player

		for (int i = 0; i < 4; i++)
		{
			Server.draw13(i);
			Thread.sleep(500);
			Server.serverGUI.addText(Server.serverGUI.getDebugPane(), "Server Removed: " + Server.removeTiles);
			Server.shareToAll(Server.removeTiles);
		}
		Thread.sleep(50);
		Server.shareToAll("update");
		Thread.sleep(50);
		Server.shareToAll(Server.playerStates);
		boolean checkForMove = true;
		boolean gamePlay = true;
		boolean winMenu = true;
		while (gamePlay)
		{

			if (Server.ready == 4)
			{
				Server.shareToAll("reset");

				game = null;
				game = new GameLoop();
				game.createStack();

				Server.wait = true;
				Server.winHand.clear();

				Server.player1Move = "";
				Server.player2Move = "";
				Server.player3Move = "";
				Server.player4Move = "";
				Server.move = "";

				Thread.sleep(500);

				server.TileStack = game.getStack();
				for (int i = 0; i <= 3; i++)
				{
					serverGUI.addText(serverGUI.getDebugPane(), "Giving Player " + (i + 1) + " tile stack.");
					Server.distributeTileStack(server.TileStack, i);
				}

				for (int i = 0; i < 4; i++)
				{
					Server.draw13(i);
					Thread.sleep(500);
					Server.serverGUI.addText(Server.serverGUI.getDebugPane(), "Server Removed: " + Server.removeTiles);
					Server.shareToAll(Server.removeTiles);
				}
				Server.shareToAll("update");
				Thread.sleep(100);
				Server.shareToAll(Server.playerStates);
				checkForMove = true;
				Server.ready = 0;
			}

			// game logic here
			outer: for (int i = 0; i < 4; i++)
			{
				if (!Server.checkConnection())
				{
					gamePlay = false;
					break outer;
				}
				Server.shareToAll("pos" + i);
				Server.distributeTileStack("draw", i);

				Thread.sleep(50);
				Server.shareToAll("update");
				Thread.sleep(50);
				Server.shareToAll(Server.playerStates);

				Thread.sleep(50);
				Server.shareToAll(Server.removeTiles);
				Thread.sleep(50);
				Server.distributeTileStack("checkHand", i);
				Thread.sleep(50);
				Server.distributeTileStack("play", i);

				while (Server.played == null)
				{
					// server updates and waits for a tile to be played
					if (Server.player1Move.equals("win") || Server.player2Move.equals("win")
							|| Server.player3Move.equals("win") || Server.player4Move.equals("win"))
					{
						if (Server.player1Move.equals("win")) Server.currentPos = 0;
						if (Server.player2Move.equals("win")) Server.currentPos = 1;
						if (Server.player3Move.equals("win")) Server.currentPos = 2;
						if (Server.player4Move.equals("win")) Server.currentPos = 3;
						Server.move = "win";

					}
					else if (Server.player1Move.equals("goong") || Server.player2Move.equals("goong")
							|| Server.player3Move.equals("goong") || Server.player4Move.equals("goong"))
					{
						if (Server.player1Move.equals("goong")) Server.currentPos = 0;
						if (Server.player2Move.equals("goong")) Server.currentPos = 1;
						if (Server.player3Move.equals("goong")) Server.currentPos = 2;
						if (Server.player4Move.equals("goong")) Server.currentPos = 3;
						Server.move = "goong";
					}

					if (!Server.move.equals(""))
					{
						Server.serverGUI.addText(Server.serverGUI.getDebugPane(),
								"Player " + (Server.currentPos + 1) + " : " + Server.move);
					}

					if (Server.move.equals("goong"))
					{
						Server.distributeTileStack(Server.move + "Self", i);
					}
					else
					{
						Server.distributeTileStack(Server.move, i);
					}

					if (!Server.move.equals(""))
					{
						// waits for a choice
						while (Server.wait)
						{
							if (!Server.winHand.isEmpty())
							{
								Server.shareToAll("update");
								Thread.sleep(50);
								Server.shareToAll(Server.playerStates);
								Server.shareToAll(Server.winHand);
								check = false;
								while (winMenu)
								{
									if (check)
									{
										Server.shareToAll("ready1");
										check = false;
									}
									if (Server.ready == 4) break outer;
								}
							}
							Thread.sleep(10);
						}
						Server.winHand.clear();
						if (!Server.redo)
						{
							Server.shareToAll("disableButton");
							Server.distributeTileStack("draw", i);
							Thread.sleep(50);
							Server.shareToAll("update");
							Thread.sleep(50);
							Server.shareToAll(Server.playerStates);
							Thread.sleep(50);
							Server.shareToAll(Server.removeTiles);
							Thread.sleep(50);
							Server.distributeTileStack("checkHand", i);
							Thread.sleep(50);
							Server.distributeTileStack("play", i);
							Server.move = "";
							Server.somethingHappened = false;
							Server.player1Move = "";
							Server.player2Move = "";
							Server.player3Move = "";
							Server.player4Move = "";
						}
					}

					Thread.sleep(10);
				}
				Server.move = "";
				Server.somethingHappened = false;
				Server.player1Move = "";
				Server.player2Move = "";
				Server.player3Move = "";
				Server.player4Move = "";
				Server.wait = true;
				Server.shareToAll("update");
				Thread.sleep(50);
				Server.shareToAll(Server.playerStates);

				Server.serverGUI.addText(Server.serverGUI.getDebugPane(), "Player " + (i + 1) + played.toString());
				Server.shareToAll(played); // echoes the played tile to all
				// clients
				Server.played = null;

				while (checkForMove)
				{
					Thread.sleep(5000); // wait for 5 seconds for someone to
					// press a button: win/poong/gong/sung
					while (Server.somethingHappened)
					{
						if (Server.player1Move.equals("win") || Server.player2Move.equals("win")
								|| Server.player3Move.equals("win") || Server.player4Move.equals("win"))
						{
							if (Server.player1Move.equals("win")) Server.currentPos = 0;
							if (Server.player2Move.equals("win")) Server.currentPos = 1;
							if (Server.player3Move.equals("win")) Server.currentPos = 2;
							if (Server.player4Move.equals("win")) Server.currentPos = 3;
							Server.move = "win";

						}
						else if (Server.player1Move.equals("goong") || Server.player2Move.equals("goong")
								|| Server.player3Move.equals("goong") || Server.player4Move.equals("goong"))
						{
							if (Server.player1Move.equals("goong")) Server.currentPos = 0;
							if (Server.player2Move.equals("goong")) Server.currentPos = 1;
							if (Server.player3Move.equals("goong")) Server.currentPos = 2;
							if (Server.player4Move.equals("goong")) Server.currentPos = 3;
							Server.move = "goong";
						}
						else if (Server.player1Move.equals("poong") || Server.player2Move.equals("poong")
								|| Server.player3Move.equals("poong") || Server.player4Move.equals("poong"))
						{
							if (Server.player1Move.equals("poong")) Server.currentPos = 0;
							if (Server.player2Move.equals("poong")) Server.currentPos = 1;
							if (Server.player3Move.equals("poong")) Server.currentPos = 2;
							if (Server.player4Move.equals("poong")) Server.currentPos = 3;
							Server.move = "poong";
						}
						else if (Server.player1Move.equals("sung") || Server.player2Move.equals("sung")
								|| Server.player3Move.equals("sung") || Server.player4Move.equals("sung"))
						{
							if (Server.player1Move.equals("sung")) Server.currentPos = 0;
							if (Server.player2Move.equals("sung")) Server.currentPos = 1;
							if (Server.player3Move.equals("sung")) Server.currentPos = 2;
							if (Server.player4Move.equals("sung")) Server.currentPos = 3;
							Server.move = "sung";
						}

						if (!Server.move.equals(""))
						{
							Server.serverGUI.addText(Server.serverGUI.getDebugPane(),
									"Player " + (Server.currentPos + 1) + " : " + Server.move);
						}

						if (Server.currentPos == 0) Server.player1Move = "";
						if (Server.currentPos == 1) Server.player2Move = "";
						if (Server.currentPos == 2) Server.player3Move = "";
						if (Server.currentPos == 3) Server.player4Move = "";

						int tempI = i;

						Thread.sleep(50);
						Server.shareToAll("disableButton");
						Thread.sleep(50);

						Server.shareToAll("update");
						Thread.sleep(50);

						Server.shareToAll(Server.playerStates);

						Server.distributeTileStack(Server.move, Server.currentPos);

						while (Server.wait)
						{
							if (!Server.winHand.isEmpty())
							{
								Server.shareToAll("update");
								Thread.sleep(50);
								Server.shareToAll(Server.playerStates);
								Server.shareToAll(Server.winHand);
								check = false;
								while (winMenu)
								{
									Thread.sleep(100);
									if (check)
									{
										Server.shareToAll("ready1");
										check = false;
									}
									if (Server.ready == 4) break outer;
								}
							}
							Thread.sleep(10);
						}
						Server.winHand.clear();
						Server.wait = true;
						Server.shareToAll("update");
						Thread.sleep(50);
						Server.shareToAll(Server.playerStates);

						if (Server.move.equals("goong"))
						{
							Server.distributeTileStack("draw", Server.currentPos);
							Thread.sleep(50);
							Server.shareToAll("update");
							Thread.sleep(50);
							Server.shareToAll(Server.playerStates);
							Server.shareToAll(Server.removeTiles);
						}
						Server.move = "";
						if (!Server.redo)
						{
							i = Server.currentPos;
							Server.shareToAll("pos" + i);
							Server.shareToAll("Event");
							Server.shareToAll("update");
							Thread.sleep(50);
							Server.shareToAll(Server.playerStates);
							Thread.sleep(50);
							Server.distributeTileStack("play", i);
							while (Server.played == null)
							{
								if (Server.player1Move.equals("win") || Server.player2Move.equals("win")
										|| Server.player3Move.equals("win") || Server.player4Move.equals("win"))
								{
									if (Server.player1Move.equals("win")) Server.currentPos = 0;
									if (Server.player2Move.equals("win")) Server.currentPos = 1;
									if (Server.player3Move.equals("win")) Server.currentPos = 2;
									if (Server.player4Move.equals("win")) Server.currentPos = 3;
									Server.move = "win";

								}
								else if (Server.player1Move.equals("goong") || Server.player2Move.equals("goong")
										|| Server.player3Move.equals("goong") || Server.player4Move.equals("goong"))
								{
									if (Server.player1Move.equals("goong")) Server.currentPos = 0;
									if (Server.player2Move.equals("goong")) Server.currentPos = 1;
									if (Server.player3Move.equals("goong")) Server.currentPos = 2;
									if (Server.player4Move.equals("goong")) Server.currentPos = 3;
									Server.move = "goong";
								}

								if (!Server.move.equals(""))
								{
									Server.serverGUI.addText(Server.serverGUI.getDebugPane(),
											"Player " + (Server.currentPos + 1) + " : " + Server.move);
								}

								if (Server.move.equals("goong"))
								{
									Server.distributeTileStack(Server.move + "Self", Server.currentPos);
									Thread.sleep(50);
									while (Server.wait)
									{

										if (!Server.winHand.isEmpty())
										{
											Server.shareToAll("update");
											Thread.sleep(50);
											Server.shareToAll(Server.playerStates);
											Server.shareToAll(Server.winHand);
											check = false;
											while (winMenu)
											{
												Thread.sleep(100);
												if (check)
												{
													Server.shareToAll("ready1");
													check = false;
												}
												if (Server.ready == 4) break outer;
											}
										}
										Thread.sleep(10);
									}
									Server.winHand.clear();
									Server.wait = true;
									Server.shareToAll("disableButton");
									Server.distributeTileStack("draw", Server.currentPos);
									Thread.sleep(50);
									Server.shareToAll("update");
									Thread.sleep(50);
									Server.shareToAll(Server.playerStates);
									Thread.sleep(50);
									Server.shareToAll(Server.removeTiles);
									Thread.sleep(50);
									Server.distributeTileStack("checkHand", Server.currentPos);
									Thread.sleep(50);
									Server.distributeTileStack("play", Server.currentPos);
									Server.move = "";
									Server.somethingHappened = false;
									Server.player1Move = "";
									Server.player2Move = "";
									Server.player3Move = "";
									Server.player4Move = "";
								}
								Thread.sleep(10);
							}
							Server.wait = true;
							Server.move = "";
							Server.serverGUI.addText(Server.serverGUI.getDebugPane(),
									"Player " + (i + 1) + played.toString());
							Server.shareToAll(played);
							Thread.sleep(50);
							Server.shareToAll("update");
							Thread.sleep(50);
							Server.somethingHappened = false;
							Server.played = null;
							Thread.sleep(5000);
						}
						else
						{
							if (Server.player1Move.equals("") && Server.player2Move.equals("")
									&& Server.player3Move.equals("") && Server.player4Move.equals(""))
							{
								Server.somethingHappened = false;
							}
							i = tempI;
						}
						Server.redo = false;

					}
					checkForMove = false;
				}
				Server.shareToAll("disableButton"); // disable buttons / TIME IS
				// UP
				Server.played = null;
				Server.shareToAll("update");
				Thread.sleep(50);
				Server.shareToAll(Server.playerStates);
				checkForMove = true;
			}

		}

		JOptionPane.showMessageDialog(null, "Player disconnected. The server will now close.");
		serverGUI.btnStopServer.doClick();
		serverGUI.dispose();

	}
}
