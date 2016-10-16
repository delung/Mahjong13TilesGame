package mahjongCode;

import java.awt.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import mahjongServer.Client;

public class Main
{

	static GamePanel gameGUI = new GamePanel();
	static Client client;
	static Player me = new Player();
	static Player right = null;
	static Player top = null;
	static Player left = null;
	static winPane win;
	public static boolean myTurn = false;
	static List<String> CurrentUsers = null;
	static boolean notWin = true;
	static Audio audio;
	// !!? is used to mark a username command

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, InterruptedException
	{

		ConnectMenu menu = new ConnectMenu();
		menu.initialize();
		while (!menu.getConnect())
		{
			Thread.sleep(10);
		}
		String username = menu.getUser();
		String host = menu.getHost();
		int port = menu.getPort();

		menu.dispose();

		ArrayList<?> Temp = new ArrayList<Object>();
		List<?> TempList;
		ArrayList<Tile> TileStack = new ArrayList<Tile>();
		Object object = null;
		Tile played = null;
		Tile justPlayed = null;
		int currentPos = 0;
		username = "!!?" + username;

		me.setName(username.substring(3));
		client = new Client(host, port, username);
		client.send(username);
		gameGUI.initialize();
		gameGUI.setPlayer(me);
		gameGUI.setClient(client);
		gameGUI.lblPlayerNameYou.setText(username.substring(3, username.length()));
		gameGUI.btnSort.setEnabled(false);
		audio = new Audio();
		waitForPlayers();

		while (true)
		{
			try
			{
				object = client.receive();
				if (object instanceof String)
				{
					String command = (String) object;
					if (command.startsWith("pos"))
					{
						currentPos = Integer.parseInt(command.substring(3));
						gameGUI.lblCurrentPlayerTurn.setText(CurrentUsers.get(currentPos) + "'s Turn.");
					}
					else if (command.equals("checkHand"))
					{
						if (me.canGoongDraw())
						{
							gameGUI.enableGongBtn();
						}
						if (me.canGoongAdd())
						{
							gameGUI.enableGongBtn();
						}
						if (me.winOnDraw() || me.winOnDraw2() || me.winOnDraw3() || me.winOnDraw4())
						{
							gameGUI.enableWinBtn();
						}
						if (me.winFlower())
						{
							gameGUI.enableWinBtn();
						}
					}
					else if (command.equals("reset"))
					{
						win.dispose();
						audio.stopClip();
						me.reset();
						left = null;
						top = null;
						right = null;

						TileStack.clear();
						played = null;
						justPlayed = null;

						gameGUI.reset();
						gameGUI.setLastPlayedNull();

						currentPos = 0;
						gameGUI.displayHand(me.getHand());
					}
					else if (command.equals("play"))
					{
						myTurn = true;
						notWin = true;
						// checks if the drawn tile is a flower
						while (played == null && myTurn && notWin)
						{
							Thread.sleep(100);
							played = gameGUI.getTilePlayed();

						}
						justPlayed = played;
						if (played != null)
						{
							client.send(played);
							gameGUI.changeBigTile(played);
							for (int i = 0; i < me.getHand().size(); i++)
							{
								if (played.getID() == me.getHand().get(i).getID())
								{
									me.getHand().remove(i);
								}
							}
							gameGUI.displayHand(me.getHand());
							audio.playClip("play");
						}
						played = null;
						myTurn = false;
					}
					else if (command.equals("draw"))
					{
						ArrayList<Tile> copy = new ArrayList<Tile>(TileStack);
						gameGUI.tilesRemaining.setText("Remaining Tiles: " + (TileStack.size() - 1));
						int drawn = 0;
						me.drawOneTile(copy);
						drawn++;
						while (me.drawnFlower(me.getHand()))
						{
							me.flowers.add(me.getHand().get(me.getHand().size() - 1));
							me.getHand().remove(me.getHand().size() - 1);
							me.drawOneTile(copy);
							drawn++;
						}
						gameGUI.displayHand(me.getHand());
						gameGUI.putFlower(me.flowers);
						client.send(drawn);
						audio.playClip("draw");
					}
					else if (command.equals("draw13"))
					{
						ArrayList<Tile> copy = new ArrayList<Tile>(TileStack);
						int drawn = 0;
						while (me.getHand().size() < 13)
						{
							me.drawOneTile(copy);
							drawn++;
							if (me.drawnFlower(me.getHand()))
							{
								me.flowers.add(me.getHand().get(me.getHand().size() - 1)); // adds last tile to flower
								me.getHand().remove(me.getHand().size() - 1); // removes last flower tile
							}
						}
						gameGUI.displayHand(me.getHand());
						gameGUI.putFlower(me.flowers);
						gameGUI.btnSort.setEnabled(true);
						client.send(drawn);
					}
					else if (command.equals("remove"))
					{
						TileStack.remove(0);
						gameGUI.tilesRemaining.setText("Remaining Tiles: " + TileStack.size());
					}

					else if (command.equals("update"))
					{
						client.send(me);
						if (me.getSpecial1Boolean()) me.setSpecial1Boolean();
						else if (me.getSpecial2Boolean()) me.setSpecial2Boolean();
						else if (me.getSpecial3Boolean()) me.setSpecial3Boolean();
						else if (me.getSpecial4Boolean()) me.setSpecial4Boolean();
					}

					else if (command.equals("disableButton"))
					{
						gameGUI.disablePongBtn();
						gameGUI.disableGongBtn();
						gameGUI.disableSongBtn();
						gameGUI.disableWinBtn();
					}
					else if (command.equals("poong"))
					{
						ArrayList<Tile> poong = me.poong(gameGUI.getJustPlayed());

						OptionSelector o = new OptionSelector();
						o.initialize();
						o.setPanelContents(1, poong);
						o.setPanelContents(2, new ArrayList<Tile>());
						o.setPanelContents(3, new ArrayList<Tile>());
						while (o.getPicking())
						{
							Thread.sleep(10);
							// return;
						}
						ArrayList<Tile> chosenList = o.getChosenList();
						if (chosenList != null)
						{
							me.addToSpecialSet(chosenList.get(0), chosenList.get(1), chosenList.get(2));
							me.removeSetTiles(chosenList.get(0), chosenList.get(1), chosenList.get(2));
							me.addToSpecialString("pong");
							gameGUI.displayHand(me.getHand());
							gameGUI.showSpecialSets(me, "you");
							audio.playClip("pong");
							me.poong++;

						}
						else
						{
							client.send("redo");
						}
						o.dispose();
						client.send("resume");

					}
					else if (command.equals("goong"))
					{

						ArrayList<Tile> goong = me.goong(gameGUI.getJustPlayed());
						OptionSelector o = new OptionSelector();
						o.initialize();
						o.setPanelContents(1, goong);
						o.setPanelContents(2, new ArrayList<Tile>());
						o.setPanelContents(3, new ArrayList<Tile>());
						while (o.getPicking())
						{
							Thread.sleep(10);
							// return;
						}
						ArrayList<Tile> chosenList = o.getChosenList();
						if (chosenList != null)
						{
							me.addToSpecialSet(chosenList.get(0), chosenList.get(1), chosenList.get(2),
									chosenList.get(3));
							me.removeSetTiles(chosenList.get(0), chosenList.get(1), chosenList.get(2),
									chosenList.get(3));
							me.addToSpecialString("gong");
							gameGUI.displayHand(me.getHand());
							gameGUI.showSpecialSets(me, "you");
							audio.playClip("gong");
							me.poong++;

						}
						else
						{
							client.send("redo");
						}
						o.dispose();
						client.send("resume");
					}

					else if (command.equals("goongSelf"))
					{
						if (me.canGoongDraw())
						{
							ArrayList<Tile> goong = me.goongFromHand();
							OptionSelector o = new OptionSelector();
							o.initialize();
							o.setPanelContents(1, goong);
							o.setPanelContents(2, new ArrayList<Tile>());
							o.setPanelContents(3, new ArrayList<Tile>());
							while (o.getPicking())
							{
								Thread.sleep(10);
								// return;
							}
							ArrayList<Tile> chosenList = o.getChosenList();
							if (chosenList != null)
							{
								me.addToSpecialSet(chosenList.get(0), chosenList.get(1), chosenList.get(2),
										chosenList.get(3));
								me.removeSetTiles(chosenList.get(0), chosenList.get(1), chosenList.get(2),
										chosenList.get(3));
								me.addToSpecialString("gong");
								gameGUI.displayHand(me.getHand());
								gameGUI.showSpecialSets(me, "you");
								audio.playClip("gong");
								me.poong++;

							}
							else
							{
								client.send("redo");
							}
							o.dispose();
							client.send("resume");
						}
						else if (me.canGoongAdd())
						{
							ArrayList<Tile> goong = new ArrayList<Tile>();
							goong.add(me.goongAddToSet());
							OptionSelector o = new OptionSelector();
							o.initialize();
							o.setPanelContents(1, goong);
							o.setPanelContents(2, new ArrayList<Tile>());
							o.setPanelContents(3, new ArrayList<Tile>());
							while (o.getPicking())
							{
								Thread.sleep(10);
								// return;
							}
							ArrayList<Tile> chosenList = o.getChosenList();
							if (chosenList != null)
							{
								me.addToSpecialSetOne(chosenList.get(0));
								me.removeSetTiles(chosenList.get(0));
								me.addToSpecialString("gong");
								gameGUI.displayHand(me.getHand());
								gameGUI.showSpecialSets(me, "you");
								audio.playClip("gong");
								me.poong++;

							}
							else
							{
								client.send("redo");
							}
							o.dispose();
							client.send("resume");
						}
					}

					else if (command.equals("sung"))
					{
						ArrayList<Tile> lower = new ArrayList<Tile>();
						ArrayList<Tile> inner = new ArrayList<Tile>();
						ArrayList<Tile> upper = new ArrayList<Tile>();

						if (me.canLowerSung(gameGUI.getJustPlayed()))
						{
							me.lowerSung(gameGUI.getJustPlayed());
							lower = me.getTemp1();
						}
						if (me.canInnerSung(gameGUI.getJustPlayed()))
						{
							me.innerSung(gameGUI.getJustPlayed());
							inner = me.getTemp2();
						}
						if (me.canUpperSung(gameGUI.getJustPlayed()))
						{
							me.upperSung(gameGUI.getJustPlayed());
							upper = me.getTemp3();
						}
						OptionSelector o = new OptionSelector();
						o.initialize();
						o.setPanelContents(1, lower);
						o.setPanelContents(2, inner);
						o.setPanelContents(3, upper);
						while (o.getPicking())
						{
							Thread.sleep(10);
							// return;
						}
						ArrayList<Tile> chosenList = o.getChosenList();
						if (chosenList != null)
						{
							me.addToSpecialSet(chosenList.get(0), chosenList.get(1), chosenList.get(2));
							me.removeSetTiles(chosenList.get(0), chosenList.get(1), chosenList.get(2));
							me.addToSpecialString("song");
							gameGUI.displayHand(me.getHand());
							gameGUI.showSpecialSets(me, "you");
							audio.playClip("song");
							me.sung++;
						}
						else
						{
							client.send("redo");
						}
						o.dispose();
						lower.clear();
						inner.clear();
						upper.clear();
						client.send("resume");

					}
					else if (command.equals("ready1"))
					{
						win.update();
					}
					else if (command.equals("Event"))
					{
						gameGUI.bigTile.removeAll();
						gameGUI.setLastPlayedNull();
					}

					else if (command.equals("win"))
					{
						ArrayList<Tile> sungWin = new ArrayList<Tile>();
						ArrayList<Tile> poongWin = new ArrayList<Tile>();
						ArrayList<Tile> flowerWin = new ArrayList<Tile>();
						ArrayList<Object> winHand = new ArrayList<Object>();
						
						if(me.winFlower()) flowerWin = me.getFlowers();
						
						if (me.getHand().size() == 2 || me.getHand().size() == 5 || me.getHand().size() == 8
								|| me.getHand().size() == 11 || me.getHand().size() == 14)
						{
							sungWin = new ArrayList<Tile>(me.winSungPref());
							poongWin = new ArrayList<Tile>(me.winPongPref());
							if(sungWin.isEmpty()) sungWin = new ArrayList<Tile>(me.winSungPref2());
							if(poongWin.isEmpty()) poongWin = new ArrayList<Tile>(me.winPongPref2());
							me.bonus++;
						}
						else
						{
							sungWin = new ArrayList<Tile>(me.winSungPref(gameGUI.getJustPlayed()));
							poongWin = new ArrayList<Tile>(me.winPongPref(gameGUI.getJustPlayed()));
							if(sungWin.isEmpty()) sungWin = new ArrayList<Tile>(me.winSungPref2(gameGUI.getJustPlayed()));
							if(poongWin.isEmpty()) poongWin = new ArrayList<Tile>(me.winPongPref2(gameGUI.getJustPlayed()));
						}
						OptionSelector o = new OptionSelector();
						o.initialize();
						o.setPanelContents(1, sungWin);
						o.setPanelContents(2, poongWin);
						o.setPanelContents(3, flowerWin);
						while (o.getPicking())
						{
							Thread.sleep(10);
							// return;
						}
						ArrayList<Tile> chosenList = o.getChosenList();
						if (chosenList != null)
						{
							gameGUI.displayHand(me.getHand());
							gameGUI.showSpecialSets(me, "you");
							me.winScore(chosenList);
							winHand = new ArrayList<Object>();
							winHand.add(me.getName());
							winHand.add(chosenList);
							winHand.add(me.getWinScore());
							
						}
						else
						{
							client.send("redo");
						}
						o.dispose();
						client.send(winHand);
					}

				}
				else if (object instanceof Integer)
				{
					int x = (int) object;
					for (int i = 0; i < x; i++)
					{
						TileStack.remove(0);
					}
					gameGUI.tilesRemaining.setText("Remaining Tiles: " + TileStack.size());
				}
				else if (object instanceof Tile)
				{

					if (justPlayed == null && !myTurn)
					{
						justPlayed = (Tile) object;
						gameGUI.changeBigTile(justPlayed);
						audio.playClip("play");

						if (me.canSung(justPlayed))
						{
							if (me.getPosition() - 1 == currentPos)
							{
								if (me.canLowerSung(justPlayed))
								{
									gameGUI.enableSongBtn();
								}
								if (me.canInnerSung(justPlayed))
								{
									gameGUI.enableSongBtn();
								}
								if (me.canUpperSung(justPlayed))
								{
									gameGUI.enableSongBtn();
								}
							}
							if (me.getPosition() + 3 == currentPos)
							{
								if (me.canLowerSung(justPlayed))
								{
									gameGUI.enableSongBtn();
								}
								if (me.canInnerSung(justPlayed))
								{
									gameGUI.enableSongBtn();
								}
								if (me.canUpperSung(justPlayed))
								{
									gameGUI.enableSongBtn();
								}
							}
						}
						if (me.canPoong(justPlayed))
						{
							gameGUI.enablePongBtn();
						}
						if (me.canGoong(justPlayed))
						{
							gameGUI.enableGongBtn();
						}
						if (me.winOnTilePlayed(justPlayed) || me.winOnTilePlayed2(justPlayed) || me.winOnTilePlayed3(justPlayed) || me.winOnTilePlayed4(justPlayed))
						{
							gameGUI.enableWinBtn();
						}
					}
					justPlayed = null;
					myTurn = false;
				}
				else if (object instanceof ArrayList<?>)
				{
					Temp = (ArrayList<?>) object;
					if (Temp.get(0) instanceof Tile)
					{
						// initialize the tile stack
						TileStack = (ArrayList<Tile>) Temp;
					}
					else if (Temp.get(0) instanceof Player)
					{
						setStates((ArrayList<Player>) Temp);
						redisplayStuff();
						checkForSounds();
					}
					else if (Temp.get(0) instanceof String)
					{
						String user = (String) Temp.get(0);
						ArrayList<Tile> winHand = (ArrayList<Tile>) Temp.get(1);
						Integer score = (Integer) Temp.get(2);
						win = new winPane(user, me, client, score);
						win.initialize();
						win.setPanelContents(winHand, left, right, top);
						audio.playClip("win");
						

					}
				}
				else if (object instanceof List<?>)
				{
					TempList = (List<?>) object;
					if (TempList.get(0) instanceof String)
					{
						setUpNames(username, TempList);
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "You were disconnected. Exiting game.");
				gameGUI.dispose();
				break;
			}
		}
	}

	public static void soundPlayed()
	{
		if (me.getSpecial1Boolean()) me.setSpecial1Boolean();
		else if (me.getSpecial2Boolean()) me.setSpecial2Boolean();
		else if (me.getSpecial3Boolean()) me.setSpecial3Boolean();
		else if (me.getSpecial4Boolean()) me.setSpecial4Boolean();
	}

	public static void checkForSounds() throws MalformedURLException
	{
		if (right.getSpecial1Boolean())
		{
			audio.playClip(right.getSpecial1String());
		}
		else if (right.getSpecial2Boolean())
		{
			audio.playClip(right.getSpecial2String());
		}
		else if (right.getSpecial3Boolean())
		{
			audio.playClip(right.getSpecial3String());
		}
		else if (right.getSpecial4Boolean())
		{
			audio.playClip(right.getSpecial4String());
		}

		if (top.getSpecial1Boolean())
		{
			audio.playClip(top.getSpecial1String());
		}
		else if (top.getSpecial2Boolean())
		{
			audio.playClip(top.getSpecial2String());
		}
		else if (top.getSpecial3Boolean())
		{
			audio.playClip(top.getSpecial3String());
		}
		else if (top.getSpecial4Boolean())
		{
			audio.playClip(top.getSpecial4String());
		}

		if (left.getSpecial1Boolean())
		{
			audio.playClip(left.getSpecial1String());
		}
		else if (left.getSpecial2Boolean())
		{
			audio.playClip(left.getSpecial2String());
		}
		else if (left.getSpecial3Boolean())
		{
			audio.playClip(left.getSpecial3String());
		}
		else if (left.getSpecial4Boolean())
		{
			audio.playClip(left.getSpecial4String());
		}
	}

	public static void waitForPlayers()
	{
		boolean waiting = true;
		Object object;
		for (Component c : gameGUI.getContentPane().getComponents())
		{
			c.setVisible(false);
		}

		gameGUI.lblWaitingForPlayers.setVisible(true);
		gameGUI.lblWaitingForPlayers.setText("Waiting for players: 1/4");
		while (waiting)
		{
			try
			{
				object = client.receive();
				if ((Integer) object == 4)
				{
					waiting = false;
					break;
				}
				if (object instanceof Integer)
				{
					gameGUI.lblWaitingForPlayers.setText("Waiting for players: " + object + "/4");
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "You were disconnected. Exiting game.");
				gameGUI.dispose();
				break;
			}

		}

		for (Component c : gameGUI.getContentPane().getComponents())
		{
			c.setVisible(true);
		}
		gameGUI.lblWaitingForPlayers.setVisible(false);
	}

	@SuppressWarnings("unchecked")
	public static void setUpNames(String username, List<?> temp)
	{
		int position = 0;

		CurrentUsers = (List<String>) temp;

		for (int i = 1; i <= CurrentUsers.size(); i++)
		{
			if (CurrentUsers.get(i - 1).equals(username.substring(3, username.length())))
			{
				position = i - 1;
			}
		}
		me.setPosition(position);
		if (position == 0)
		{
			gameGUI.playerLabels.get(0).setText(CurrentUsers.get(1));
			gameGUI.playerLabels.get(1).setText(CurrentUsers.get(2));
			gameGUI.playerLabels.get(2).setText(CurrentUsers.get(3));
		}
		else if (position == 1)
		{
			gameGUI.playerLabels.get(0).setText(CurrentUsers.get(2));
			gameGUI.playerLabels.get(1).setText(CurrentUsers.get(3));
			gameGUI.playerLabels.get(2).setText(CurrentUsers.get(0));
		}

		else if (position == 2)
		{
			gameGUI.playerLabels.get(0).setText(CurrentUsers.get(3));
			gameGUI.playerLabels.get(1).setText(CurrentUsers.get(0));
			gameGUI.playerLabels.get(2).setText(CurrentUsers.get(1));
		}
		else if (position == 3)
		{
			gameGUI.playerLabels.get(0).setText(CurrentUsers.get(0));
			gameGUI.playerLabels.get(1).setText(CurrentUsers.get(1));
			gameGUI.playerLabels.get(2).setText(CurrentUsers.get(2));
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Something went wrong setting up names. Tell Nick or Derek");
		}
	}

	public static void setStates(ArrayList<Player> state)
	{
		if (me.getPosition() == 0)
		{
			right = state.get(1); // player 2
			top = state.get(2); // player 3
			left = state.get(3); // player 4
		}
		else if (me.getPosition() == 1)
		{
			right = state.get(2); // player 3
			top = state.get(3); // player 4
			left = state.get(0); // player 1
		}

		else if (me.getPosition() == 2)
		{
			right = state.get(3); // player 4
			top = state.get(0); // player 1
			left = state.get(1); // player 2
		}
		else if (me.getPosition() == 3)
		{
			right = state.get(0); // player 1
			top = state.get(1); // player 2
			left = state.get(2); // player 3
		}
	}

	public static void sendState() throws IOException
	{
		client.send(me);
	}

	public static void redisplayStuff() throws IOException
	{
		if (right != null && left != null && top != null)
		{
			gameGUI.displayOtherPlayerHands(right, "right");
			gameGUI.displayOtherPlayerHands(left, "left");
			gameGUI.displayOtherPlayerHands(top, "top");
		}
	}
}
