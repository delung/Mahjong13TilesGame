package mahjongCode;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6188359995945478643L;
	private int position;
	private boolean eye = false;
	private String name;

	private ArrayList<Tile> set1 = new ArrayList<Tile>();
	private ArrayList<Tile> set2 = new ArrayList<Tile>();
	private ArrayList<Tile> set3 = new ArrayList<Tile>();
	private ArrayList<Tile> set4 = new ArrayList<Tile>();
	private ArrayList<Tile> set5 = new ArrayList<Tile>();

	public ArrayList<Tile> flowers = new ArrayList<Tile>();

	private ArrayList<Tile> special1 = new ArrayList<Tile>();
	private ArrayList<Tile> special2 = new ArrayList<Tile>();
	private ArrayList<Tile> special3 = new ArrayList<Tile>();
	private ArrayList<Tile> special4 = new ArrayList<Tile>();

	private String special1String = "";
	private String special2String = "";
	private String special3String = "";
	private String special4String = "";

	private boolean special1Boolean = false;
	private boolean special2Boolean = false;
	private boolean special3Boolean = false;
	private boolean special4Boolean = false;

	private ArrayList<Tile> temp1 = new ArrayList<Tile>();
	private ArrayList<Tile> temp2 = new ArrayList<Tile>();
	private ArrayList<Tile> temp3 = new ArrayList<Tile>();

	private int totalScore = 0;
	private int score = 0;
	public int poong = 0;
	public int sung = 0;
	public int bonus = 0;

	private boolean sameSuite = false;
	private boolean suiteKungfu = false;
	private boolean suiteRock = false;
	private boolean suiteStick = false;
	
	private ArrayList<Tile> hand = new ArrayList<Tile>();
	private ArrayList<Tile> copy = new ArrayList<Tile>();

	public Player(int position)
	{

		this.position = position;
	}

	public Player()
	{

	}

	public void reset()
	{
		hand.clear();
		copy.clear();
		flowers.clear();

		special1.clear();
		special1String = "";
		special1Boolean = false;

		special2.clear();
		special2String = "";
		special2Boolean = false;

		special3.clear();
		special3String = "";
		special3Boolean = false;

		special4.clear();
		special4String = "";
		special4Boolean = false;
		
		poong = 0;
		sung = 0;
		bonus = 0;
		score =0;
		
		sameSuite = false;
		suiteKungfu = false;
		suiteRock = false;
		suiteStick = false;
	}

	public ArrayList<Tile> getHand()
	{
		return hand;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setHand(ArrayList<Tile> newHand)
	{
		hand = newHand;
	}

	public void addTile(Tile tile) // adds a tile to the hand
	{
		hand.add(tile);
	}

	public void drawOneTile(ArrayList<Tile> tileStack)
	{
		addTile(tileStack.get(0));
		tileStack.remove(0);
	}

	public void removeTiles() // discards entire hand, debugging only
	{
		hand.clear();
	}

	public void removeSetTiles(Tile one)
	{
		for (int i = 0; i < hand.size(); i++)
		{
			if (hand.get(i).getID() == one.getID())
			{
				hand.remove(i);
				i = i - 1;
				if (i < 0) i = -1;
			}
		}
	}

	public void removeSetTiles(Tile one, Tile two, Tile three)
	{
		for (int i = 0; i < hand.size(); i++)
		{
			if (hand.get(i).getID() == one.getID())
			{
				hand.remove(i);
				i = i - 1;
				if (i < 0) i = -1;
			}
			else if (hand.get(i).getID() == two.getID())
			{
				hand.remove(i);
				i = i - 1;
				if (i < 0) i = -1;
			}
			else if (hand.get(i).getID() == three.getID())
			{
				hand.remove(i);
				i = i - 1;
				if (i < 0) i = -1;
			}
		}
	}

	public void removeSetTiles(Tile one, Tile two, Tile three, Tile four)
	{
		for (int i = 0; i < hand.size(); i++)
		{
			Tile x = hand.get(i);
			if (x.getID() == one.getID())
			{
				hand.remove(i);
				i = i - 1;
				if (i < 0) i = -1;
			}
			else if (x.getID() == two.getID())
			{
				hand.remove(i);
				i = i - 1;
				if (i < 0) i = -1;
			}
			else if (x.getID() == three.getID())
			{
				hand.remove(i);
				i = i - 1;
				if (i < 0) i = -1;
			}
			else if (x.getID() == four.getID())
			{
				hand.remove(i);
				i = i - 1;
				if (i < 0) i = -1;
			}
		}
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public int getPosition()
	{
		return position;
	}

	public ArrayList<Tile> getSpecial1()
	{
		return special1;
	}

	public ArrayList<Tile> getSpecial2()
	{
		return special2;
	}

	public ArrayList<Tile> getSpecial3()
	{
		return special3;
	}

	public ArrayList<Tile> getSpecial4()
	{
		return special4;
	}

	public String getSpecial1String()
	{
		return special1String;
	}

	public String getSpecial2String()
	{
		return special2String;
	}

	public String getSpecial3String()
	{
		return special3String;
	}

	public String getSpecial4String()
	{
		return special4String;
	}

	public boolean getSpecial1Boolean()
	{
		return special1Boolean;
	}

	public boolean getSpecial2Boolean()
	{
		return special2Boolean;
	}

	public boolean getSpecial3Boolean()
	{
		return special3Boolean;
	}

	public boolean getSpecial4Boolean()
	{
		return special4Boolean;
	}

	public void setSpecial1Boolean()
	{
		special1Boolean = false;
	}

	public void setSpecial2Boolean()
	{
		special2Boolean = false;
	}

	public void setSpecial3Boolean()
	{
		special3Boolean = false;
	}

	public void setSpecial4Boolean()
	{
		special4Boolean = false;
	}

	public ArrayList<Tile> getTemp1()
	{
		return temp1;
	}

	public ArrayList<Tile> getTemp2()
	{
		return temp2;
	}

	public ArrayList<Tile> getTemp3()
	{
		return temp3;
	}

	public int getScore()
	{
		return totalScore;
	}
	public int getWinScore()
	{
		return score;
	}
	public void sameSuite(ArrayList<Tile> winHand)
	{
		for(Tile x : winHand)
		{
			if(x.getTileID().equals(Tile.KUNGFU)) suiteKungfu = true;
			else if(x.getTileID().equals(Tile.STICK)) suiteStick = true;
			else if(x.getTileID().equals(Tile.ROCK)) suiteRock = true;
		}
		if(suiteKungfu && suiteStick) sameSuite = false;
		if(suiteStick && suiteRock) sameSuite = false;
		if(suiteKungfu && suiteRock) sameSuite = false;
		else sameSuite = true;
	}
	
	public void winScore(ArrayList<Tile> winHand)
	{
		if (sameSuite)
		{
			score += 3;
		} // same suite + 3
		else
		{
			if (sung > 0)
			{
				score += 6;
			}
			if (poong > 0)
			{
				if (sung > 0)
				{
					score = 3;
				} // poong and sung = chicken shit score = 1
				else
				{
					score += 9;
				} // all poong
			}
		}
		
		for(Tile x : winHand)
		{

			if(x.getTileID().equals(Tile.CABBAGE)) bonus++;
			else if(x.getTileID().equals(Tile.DAGGER)) bonus++;
			else if(x.getTileID().equals(Tile.TOFU)) bonus++;
			else if(x.getTileID().equals(Tile.NORTH)) bonus++;
			else if(x.getTileID().equals(Tile.SOUTH)) bonus++;
			else if(x.getTileID().equals(Tile.WEST)) bonus++;
			else if(x.getTileID().equals(Tile.EAST)) bonus++;
		}
		bonus += flowers.size();
		score += bonus;
		totalScore += score;
	}

	public boolean canSung(Tile played) // Checks if the played tile was even a
										// suite
	{
		return played.getTileID().equals(Tile.KUNGFU) || played.getTileID().equals(Tile.STICK)
				|| played.getTileID().equals(Tile.ROCK);
	}

	public boolean canUpperSung(Tile played) // Checks if you can sung 1 _ _
	{
		boolean upper = false;
		boolean upper2 = false;
		for (Tile x : hand)
		{
			if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber() + 1)
			{
				upper = true;
			}
			if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber() + 2)
			{
				upper2 = true;
			}
		}
		if (upper && upper2) { return true; }
		return false;
	}

	public boolean canLowerSung(Tile played) // Checks if you can sung _ _ 3
	{
		boolean lower = false;
		boolean lower2 = false;
		for (Tile x : hand)
		{
			if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber() - 1)
			{
				lower = true;
			}
			if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber() - 2)
			{
				lower2 = true;
			}
		}
		if (lower && lower2) { return true; }
		return false;
	}

	public boolean canInnerSung(Tile played) // Checks if you can sung _ 2 _
	{
		boolean lower = false;
		boolean upper = false;
		for (Tile x : hand)
		{
			if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber() + 1)
			{
				upper = true;
			}
			if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber() - 1)
			{
				lower = true;
			}
		}
		if (upper && lower) { return true; }
		return false;
	}

	public boolean canPoong(Tile played) // Checks if you can poong based on
											// what was played
	{
		int same = 0;
		if (played.getTileID().equals(Tile.KUNGFU) || played.getTileID().equals(Tile.STICK)
				|| played.getTileID().equals(Tile.ROCK))
		{
			for (Tile x : hand)
			{
				if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber())
				{
					same += 1;
				}
			}
		}

		else
		{
			for (Tile x : hand)
			{
				if (x.getTileID().equals(played.getTileID()))
				{
					same += 1;
				}
			}
		}
		if (same > 1) { return true; }
		return false;

	}

	public boolean canGoong(Tile played) // Check if you can goong based on what
											// was played
	{
		int same = 0;
		if (played.getTileID().equals(Tile.KUNGFU) || played.getTileID().equals(Tile.STICK)
				|| played.getTileID().equals(Tile.ROCK))
		{
			for (Tile x : hand)
			{
				if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber())
				{
					same += 1;
				}
			}
		}

		else
		{
			for (Tile x : hand)
			{
				if (x.getTileID().equals(played.getTileID()))
				{
					same += 1;
				}
			}
		}
		if (same == 3) { return true; }
		return false;
	}

	public boolean canGoongDraw() // Same method as above remove if wanted
	{
		int same = 0;
		for (int x = 0; x < hand.size() - 1; x++)
		{
			same = 0;
			Tile temp = hand.get(x);
			for (int y = x + 1; y < hand.size(); y++)
			{
				Tile temp2 = hand.get(y);
				if (temp.getTileID().equals(temp2.getTileID()) && temp.getTileNumber() == temp2.getTileNumber())
				{
					same += 1;
				}
				if (same == 3) { return true; }
			}
		}
		return false;
	}

	public boolean canGoongAdd() // Same method as above remove if wanted
	{
		int same = 0;
		for (int x = 0; x < hand.size(); x++)
		{
			same = 0;
			Tile temp = hand.get(x);
			if (!special1.isEmpty())
			{
				for (Tile tile : special1)
				{
					if (temp.getTileID().equals(tile.getTileID()) && temp.getTileNumber() == tile.getTileNumber())
						same++;
					if (same == 3) return true;
				}
				same = 0;
			}

			if (!special2.isEmpty())
			{
				for (Tile tile : special2)
				{
					if (temp.getTileID().equals(tile.getTileID()) && temp.getTileNumber() == tile.getTileNumber())
						same++;
					if (same == 3) return true;
				}
				same = 0;
			}

			if (!special3.isEmpty())
			{
				for (Tile tile : special3)
				{
					if (temp.getTileID().equals(tile.getTileID()) && temp.getTileNumber() == tile.getTileNumber())
						same++;
					if (same == 3) return true;
				}
				same = 0;
			}

			if (!special4.isEmpty())
			{
				for (Tile tile : special4)
				{
					if (temp.getTileID().equals(tile.getTileID()) && temp.getTileNumber() == tile.getTileNumber())
						same++;
					if (same == 3) return true;
				}
				same = 0;
			}
		}
		return false;
	}

	public ArrayList<Tile> goongFromHand()
	{
		ArrayList<Tile> temp = new ArrayList<Tile>();
		Tile one = null;
		Tile two = null;
		Tile three = null;
		@SuppressWarnings("unused")
		int same = 0;
		for (int x = 0; x < hand.size() - 1; x++)
		{
			same = 0;
			Tile tempX = hand.get(x);
			one = null;
			two = null;
			three = null;
			for (int y = 1; y < hand.size(); y++)
			{
				Tile tempY = hand.get(y);
				if (tempX.getTileID().equals(tempY.getTileID()) && tempX.getTileNumber() == tempY.getTileNumber()
						&& one == null)
				{
					one = tempY;
				}
				else if (tempX.getTileID().equals(tempY.getTileID()) && tempX.getTileNumber() == tempY.getTileNumber()
						&& two == null)
				{
					two = tempY;
				}
				else if (tempX.getTileID().equals(tempY.getTileID()) && tempX.getTileNumber() == tempY.getTileNumber())
				{
					three = tempY;
				}

				if (one != null && two != null && three != null)
				{
					temp.add(one);
					temp.add(two);
					temp.add(three);
					temp.add(tempX);
					return temp;
				}
			}
		}
		return temp;
	}

	public Tile goongAddToSet()
	{
		int same = 0;
		for (int x = 0; x < hand.size(); x++)
		{
			same = 0;
			Tile temp = hand.get(x);
			if (!special1.isEmpty())
			{
				for (Tile tile : special1)
				{
					if (temp.getTileID().equals(tile.getTileID()) && temp.getTileNumber() == tile.getTileNumber())
						same++;
					if (same == 3) return temp;
				}
				same = 0;
			}

			if (!special2.isEmpty())
			{
				for (Tile tile : special2)
				{
					if (temp.getTileID().equals(tile.getTileID()) && temp.getTileNumber() == tile.getTileNumber())
						same++;
					if (same == 3) return temp;
				}
				same = 0;
			}

			if (!special3.isEmpty())
			{
				for (Tile tile : special3)
				{
					if (temp.getTileID().equals(tile.getTileID()) && temp.getTileNumber() == tile.getTileNumber())
						same++;
					if (same == 3) return temp;
				}
				same = 0;
			}

			if (!special4.isEmpty())
			{
				for (Tile tile : special4)
				{
					if (temp.getTileID().equals(tile.getTileID()) && temp.getTileNumber() == tile.getTileNumber())
						same++;
					if (same == 3) return temp;
				}
				same = 0;
			}
		}
		return null;
	}

	public boolean winOnDraw()
	{
		// check for special sets
		// check for first set
		// check for second set
		// check for third set
		// check for fourth set
		// check for an eye
		int sets = 0;
		resetSets(hand);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		sungUpperCheck(hand);
		sungLowerCheck(hand);
		sungInnerCheck(hand);
		poongCheck(hand);
		eyeCheck(hand);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye) { return true; }

		return false;
	}

	public boolean winOnDraw2()
	{

		int sets = 0;
		resetSets(hand);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}

		eyeCheck(hand);
		sungUpperCheck(hand);
		sungLowerCheck(hand);
		sungInnerCheck(hand);
		poongCheck(hand);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye) { return true; }

		return false;
	}
	
	public boolean winOnDraw3()
	{

		int sets = 0;
		resetSets(hand);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		
		poongCheck(hand);
		sungUpperCheck(hand);
		sungLowerCheck(hand);
		sungInnerCheck(hand);
		eyeCheck(hand);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye) { return true; }

		return false;
	}
	
	public boolean winOnDraw4()
	{

		int sets = 0;
		resetSets(hand);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		
		eyeCheck(hand);
		poongCheck(hand);
		sungUpperCheck(hand);
		sungLowerCheck(hand);
		sungInnerCheck(hand);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye) { return true; }

		return false;
	}

	public boolean winOnTilePlayed(Tile tile)
	{
		copy = new ArrayList<Tile>(hand);
		copy.add(tile);

		int sets = 0;
		resetSets(copy);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		sungUpperCheck(copy);
		sungLowerCheck(copy);
		sungInnerCheck(copy);
		poongCheck(copy);
		eyeCheck(copy);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye) { return true; }
		return false;
	}

	public boolean winOnTilePlayed2(Tile tile)
	{
		copy = new ArrayList<Tile>(hand);
		copy.add(tile);

		int sets = 0;
		resetSets(copy);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}

		eyeCheck(copy);
		sungUpperCheck(copy);
		sungLowerCheck(copy);
		sungInnerCheck(copy);
		poongCheck(copy);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye) { return true; }
		return false;
	}
	
	public boolean winOnTilePlayed3(Tile tile)
	{
		copy = new ArrayList<Tile>(hand);
		copy.add(tile);

		int sets = 0;
		resetSets(copy);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}

		poongCheck(copy);
		sungUpperCheck(copy);
		sungLowerCheck(copy);
		sungInnerCheck(copy);
		eyeCheck(copy);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye) { return true; }
		return false;
	}
	
	public boolean winOnTilePlayed4(Tile tile)
	{
		copy = new ArrayList<Tile>(hand);
		copy.add(tile);

		int sets = 0;
		resetSets(copy);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		
		eyeCheck(copy);
		poongCheck(copy);
		sungUpperCheck(copy);
		sungLowerCheck(copy);
		sungInnerCheck(copy);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye) { return true; }
		return false;
	}
	
	public ArrayList<Tile> winSungPref()
	{
		copy = new ArrayList<Tile>(hand);
		ArrayList<Tile> winningHand = new ArrayList<Tile>();
		int sets = 0;
		resetSets(copy);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		sungUpperCheck(copy);
		sungLowerCheck(copy);
		sungInnerCheck(copy);
		poongCheck(copy);
		eyeCheck(copy);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye)
		{
			if (!special1.isEmpty()) quickAdd(special1, winningHand);
			if (!special2.isEmpty()) quickAdd(special2, winningHand);
			if (!special3.isEmpty()) quickAdd(special3, winningHand);
			if (!special4.isEmpty()) quickAdd(special4, winningHand);

			if (!set1.isEmpty()) quickAdd(set1, winningHand);
			if (!set2.isEmpty()) quickAdd(set2, winningHand);
			if (!set3.isEmpty()) quickAdd(set3, winningHand);
			if (!set4.isEmpty()) quickAdd(set4, winningHand);

			if (!set5.isEmpty()) quickAdd(set5, winningHand);

			return winningHand;
		}
		return winningHand;
	}
	
	public ArrayList<Tile> winSungPref2()
	{
		copy = new ArrayList<Tile>(hand);
		ArrayList<Tile> winningHand = new ArrayList<Tile>();
		int sets = 0;
		resetSets(copy);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		
		eyeCheck(copy);
		sungUpperCheck(copy);
		sungLowerCheck(copy);
		sungInnerCheck(copy);
		poongCheck(copy);


		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye)
		{
			if (!special1.isEmpty()) quickAdd(special1, winningHand);
			if (!special2.isEmpty()) quickAdd(special2, winningHand);
			if (!special3.isEmpty()) quickAdd(special3, winningHand);
			if (!special4.isEmpty()) quickAdd(special4, winningHand);

			if (!set1.isEmpty()) quickAdd(set1, winningHand);
			if (!set2.isEmpty()) quickAdd(set2, winningHand);
			if (!set3.isEmpty()) quickAdd(set3, winningHand);
			if (!set4.isEmpty()) quickAdd(set4, winningHand);

			if (!set5.isEmpty()) quickAdd(set5, winningHand);

			return winningHand;
		}
		return winningHand;
	}
	
	public ArrayList<Tile> winPongPref()
	{
		copy = new ArrayList<Tile>(hand);
		ArrayList<Tile> winningHand = new ArrayList<Tile>();
		int sets = 0;
		resetSets(copy);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		poongCheck(copy);
		sungUpperCheck(copy);
		sungLowerCheck(copy);
		sungInnerCheck(copy);
		eyeCheck(copy);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye)
		{
			if (!special1.isEmpty()) quickAdd(special1, winningHand);
			if (!special2.isEmpty()) quickAdd(special2, winningHand);
			if (!special3.isEmpty()) quickAdd(special3, winningHand);
			if (!special4.isEmpty()) quickAdd(special4, winningHand);

			if (!set1.isEmpty()) quickAdd(set1, winningHand);
			if (!set2.isEmpty()) quickAdd(set2, winningHand);
			if (!set3.isEmpty()) quickAdd(set3, winningHand);
			if (!set4.isEmpty()) quickAdd(set4, winningHand);

			if (!set5.isEmpty()) quickAdd(set5, winningHand);

			return winningHand;
		}
		return winningHand;
	}
	
	public ArrayList<Tile> winPongPref2()
	{
		copy = new ArrayList<Tile>(hand);
		ArrayList<Tile> winningHand = new ArrayList<Tile>();
		int sets = 0;
		resetSets(copy);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		
		eyeCheck(copy);
		poongCheck(copy);
		sungUpperCheck(copy);
		sungLowerCheck(copy);
		sungInnerCheck(copy);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye)
		{
			if (!special1.isEmpty()) quickAdd(special1, winningHand);
			if (!special2.isEmpty()) quickAdd(special2, winningHand);
			if (!special3.isEmpty()) quickAdd(special3, winningHand);
			if (!special4.isEmpty()) quickAdd(special4, winningHand);

			if (!set1.isEmpty()) quickAdd(set1, winningHand);
			if (!set2.isEmpty()) quickAdd(set2, winningHand);
			if (!set3.isEmpty()) quickAdd(set3, winningHand);
			if (!set4.isEmpty()) quickAdd(set4, winningHand);

			if (!set5.isEmpty()) quickAdd(set5, winningHand);

			return winningHand;
		}
		return winningHand;
	}

	public ArrayList<Tile> winSungPref(Tile tile)
	{
		copy = new ArrayList<Tile>(hand);
		ArrayList<Tile> winningHand = new ArrayList<Tile>();
		copy.add(tile);
		int sets = 0;
		resetSets(copy);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		sungUpperCheck(copy);
		sungLowerCheck(copy);
		sungInnerCheck(copy);
		poongCheck(copy);
		eyeCheck(copy);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye)
		{
			if (!special1.isEmpty()) quickAdd(special1, winningHand);
			if (!special2.isEmpty()) quickAdd(special2, winningHand);
			if (!special3.isEmpty()) quickAdd(special3, winningHand);
			if (!special4.isEmpty()) quickAdd(special4, winningHand);

			if (!set1.isEmpty()) quickAdd(set1, winningHand);
			if (!set2.isEmpty()) quickAdd(set2, winningHand);
			if (!set3.isEmpty()) quickAdd(set3, winningHand);
			if (!set4.isEmpty()) quickAdd(set4, winningHand);

			if (!set5.isEmpty()) quickAdd(set5, winningHand);

			return winningHand;
		}
		return winningHand;
	}
	
	public ArrayList<Tile> winSungPref2(Tile tile)
	{
		copy = new ArrayList<Tile>(hand);
		ArrayList<Tile> winningHand = new ArrayList<Tile>();
		copy.add(tile);
		int sets = 0;
		resetSets(copy);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		
		eyeCheck(copy);
		sungUpperCheck(copy);
		sungLowerCheck(copy);
		sungInnerCheck(copy);
		poongCheck(copy);
		

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye)
		{
			if (!special1.isEmpty()) quickAdd(special1, winningHand);
			if (!special2.isEmpty()) quickAdd(special2, winningHand);
			if (!special3.isEmpty()) quickAdd(special3, winningHand);
			if (!special4.isEmpty()) quickAdd(special4, winningHand);

			if (!set1.isEmpty()) quickAdd(set1, winningHand);
			if (!set2.isEmpty()) quickAdd(set2, winningHand);
			if (!set3.isEmpty()) quickAdd(set3, winningHand);
			if (!set4.isEmpty()) quickAdd(set4, winningHand);

			if (!set5.isEmpty()) quickAdd(set5, winningHand);

			return winningHand;
		}
		return winningHand;
	}

	public ArrayList<Tile> winPongPref(Tile tile)
	{
		copy = new ArrayList<Tile>(hand);
		ArrayList<Tile> winningHand = new ArrayList<Tile>();
		copy.add(tile);
		int sets = 0;
		resetSets(copy);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		poongCheck(copy);
		sungUpperCheck(copy);
		sungLowerCheck(copy);
		sungInnerCheck(copy);
		eyeCheck(copy);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye)
		{
			if (!special1.isEmpty()) quickAdd(special1, winningHand);
			if (!special2.isEmpty()) quickAdd(special2, winningHand);
			if (!special3.isEmpty()) quickAdd(special3, winningHand);
			if (!special4.isEmpty()) quickAdd(special4, winningHand);

			if (!set1.isEmpty()) quickAdd(set1, winningHand);
			if (!set2.isEmpty()) quickAdd(set2, winningHand);
			if (!set3.isEmpty()) quickAdd(set3, winningHand);
			if (!set4.isEmpty()) quickAdd(set4, winningHand);

			if (!set5.isEmpty()) quickAdd(set5, winningHand);

			return winningHand;
		}
		return winningHand;
	}
	
	public ArrayList<Tile> winPongPref2(Tile tile)
	{
		copy = new ArrayList<Tile>(hand);
		ArrayList<Tile> winningHand = new ArrayList<Tile>();
		copy.add(tile);
		int sets = 0;
		resetSets(copy);
		if (!special1.isEmpty())
		{
			sets++;
		}
		if (!special2.isEmpty())
		{
			sets++;
		}
		if (!special3.isEmpty())
		{
			sets++;
		}
		if (!special4.isEmpty())
		{
			sets++;
		}
		
		eyeCheck(copy);
		poongCheck(copy);
		sungUpperCheck(copy);
		sungLowerCheck(copy);
		sungInnerCheck(copy);

		if (!set1.isEmpty())
		{
			sets++;
		}
		if (!set2.isEmpty())
		{
			sets++;
		}
		if (!set3.isEmpty())
		{
			sets++;
		}
		if (!set4.isEmpty())
		{
			sets++;
		}
		if (!set5.isEmpty())
		{
			eye = true;
		}

		if (sets == 4 && eye)
		{
			if (!special1.isEmpty()) quickAdd(special1, winningHand);
			if (!special2.isEmpty()) quickAdd(special2, winningHand);
			if (!special3.isEmpty()) quickAdd(special3, winningHand);
			if (!special4.isEmpty()) quickAdd(special4, winningHand);

			if (!set1.isEmpty()) quickAdd(set1, winningHand);
			if (!set2.isEmpty()) quickAdd(set2, winningHand);
			if (!set3.isEmpty()) quickAdd(set3, winningHand);
			if (!set4.isEmpty()) quickAdd(set4, winningHand);

			if (!set5.isEmpty()) quickAdd(set5, winningHand);

			return winningHand;
		}
		return winningHand;
	}

	public void quickAdd(ArrayList<Tile> set, ArrayList<Tile> winningHand)
	{
		for (Tile x : set)
			winningHand.add(x);
	}

	private void eyeCheck(ArrayList<Tile> hand)
	{
		// 7 7
		// size 14
		// this is 13
		// 2nd is 12
		@SuppressWarnings("unused")
		boolean dup1 = false;

		Tile dup01 = null;

		for (int x = 0; x < hand.size() - 1; x++)
		{
			Tile temp1 = hand.get(x);
			if (!temp1.getUsed()) // if not used then check for another unused
									// piece
			{
				for (int y = x + 1; y < hand.size(); y++)
				{
					Tile temp2 = hand.get(y);
					if (!temp2.getUsed())
					{
						if (temp1.getTileID().equals(temp2.getTileID())
								&& temp1.getTileNumber() == temp2.getTileNumber())
						{
							dup1 = true;

							dup01 = temp2;
						}
					}
					if (dup01 != null)
					{
						addEyeSet(temp1, dup01);
						temp1.setUsed();
						dup01.setUsed();
						dup01 = null;
						break;
					}
				}

			}
		}
	}

	private void sungUpperCheck(ArrayList<Tile> hand)
	{
		// 1 2 3
		@SuppressWarnings("unused")
		boolean upper = false;
		@SuppressWarnings("unused")
		boolean upper2 = false;
		Tile up1 = null;
		Tile up2 = null;
		for (int x = 0; x < hand.size() - 1; x++)
		{
			Tile temp1 = hand.get(x);
			if (!temp1.getUsed()) // if not used then check for another unused
									// piece
			{
				if (temp1.getTileID().equals(Tile.KUNGFU) || temp1.getTileID().equals(Tile.STICK)
						|| temp1.getTileID().equals(Tile.ROCK))
				{
					for (int y = x + 1; y < hand.size(); y++)
					{
						Tile temp2 = hand.get(y);
						if (!temp2.getUsed())
						{
							if (temp1.getTileID().equals(temp2.getTileID())
									&& temp1.getTileNumber() == temp2.getTileNumber() - 1)
							{
								upper = true;

								up1 = temp2;
							}
							else if (temp1.getTileID().equals(temp2.getTileID())
									&& temp1.getTileNumber() == temp2.getTileNumber() - 2)
							{
								upper2 = true;

								up2 = temp2;
							}
						}
						if (up1 != null && up2 != null)
						{
							addToSet(temp1, up1, up2);
							temp1.setUsed();
							up1.setUsed();
							up2.setUsed();
							up1 = null;
							up2 = null;
							break;
						}
					}
				}
				up1 = null;
				up2 = null;
			}
		}
	}

	private void sungLowerCheck(ArrayList<Tile> hand)
	{
		// 3 2 1
		@SuppressWarnings("unused")
		boolean lower = false;
		@SuppressWarnings("unused")
		boolean lower2 = false;
		Tile low1 = null;
		Tile low2 = null;
		for (int x = 0; x < hand.size() - 1; x++)
		{
			Tile temp1 = hand.get(x);
			if (!temp1.getUsed()) // if not used then check for another unused
									// piece
			{
				if (temp1.getTileID().equals(Tile.KUNGFU) || temp1.getTileID().equals(Tile.STICK)
						|| temp1.getTileID().equals(Tile.ROCK))
				{
					for (int y = x + 1; y < hand.size(); y++)
					{
						Tile temp2 = hand.get(y);
						if (!temp2.getUsed())
						{
							if (temp1.getTileID().equals(temp2.getTileID())
									&& temp1.getTileNumber() == temp2.getTileNumber() + 1)
							{
								lower = true;

								low1 = temp2;
							}
							else if (temp1.getTileID().equals(temp2.getTileID())
									&& temp1.getTileNumber() == temp2.getTileNumber() + 2)
							{
								lower2 = true;

								low2 = temp2;
							}
						}
						if (low1 != null && low2 != null)
						{
							addToSet(low2, low1, temp1);
							temp1.setUsed();
							low1.setUsed();
							low2.setUsed();
							low1 = null;
							low2 = null;
							break;
						}
					}
				}
				low1 = null;
				low2 = null;
			}
		}
	}

	private void sungInnerCheck(ArrayList<Tile> hand)
	{
		// 2 1 3
		@SuppressWarnings("unused")
		boolean upper = false;
		@SuppressWarnings("unused")
		boolean lower = false;
		Tile up1 = null;
		Tile low1 = null;
		for (int x = 0; x < hand.size() - 1; x++)
		{
			Tile temp1 = hand.get(x);
			if (!temp1.getUsed()) // if not used then check for another unused
									// piece
			{
				if (temp1.getTileID().equals(Tile.KUNGFU) || temp1.getTileID().equals(Tile.STICK)
						|| temp1.getTileID().equals(Tile.ROCK))
				{
					for (int y = x + 1; y < hand.size(); y++)
					{
						Tile temp2 = hand.get(y);
						if (!temp2.getUsed())
						{
							if (temp1.getTileID().equals(temp2.getTileID())
									&& temp1.getTileNumber() == temp2.getTileNumber() - 1)
							{
								upper = true;

								up1 = temp2;
							}
							else if (temp1.getTileID().equals(temp2.getTileID())
									&& temp1.getTileNumber() == temp2.getTileNumber() + 1)
							{
								lower = true;

								low1 = temp2;
							}
						}
						if (up1 != null && low1 != null)
						{
							addToSet(low1, temp1, up1);
							temp1.setUsed();
							low1.setUsed();
							up1.setUsed();
							up1 = null;
							low1 = null;
							break;
						}
					}
				}
				up1 = null;
				low1 = null;
			}
		}
	}

	private void poongCheck(ArrayList<Tile> hand)
	{
		// 6 6 6
		boolean dup1 = false;
		@SuppressWarnings("unused")
		boolean dup2 = false;
		Tile dup01 = null;
		Tile dup02 = null;
		for (int x = 0; x < hand.size() - 1; x++)
		{
			Tile temp1 = hand.get(x);
			if (!temp1.getUsed()) // if not used then check for another unused
									// piece
			{
				if (temp1.getTileID().equals(Tile.KUNGFU) || temp1.getTileID().equals(Tile.STICK)
						|| temp1.getTileID().equals(Tile.ROCK))
				{
					for (int y = x + 1; y < hand.size(); y++)
					{
						Tile temp2 = hand.get(y);
						if (!temp2.getUsed())
						{
							if (temp1.getTileID().equals(temp2.getTileID())
									&& temp1.getTileNumber() == temp2.getTileNumber() && !dup1)
							{
								dup1 = true;
								dup01 = temp2;
							}
							else if (temp1.getTileID().equals(temp2.getTileID())
									&& temp1.getTileNumber() == temp2.getTileNumber())
							{
								dup2 = true;
								dup02 = temp2;
							}
						
							if (dup01 != null && dup02 != null)
							{
								addToSet(temp1, dup01, dup02);
								temp1.setUsed();
								dup01.setUsed();
								dup02.setUsed();
								dup01 = null;
								dup02 = null;
								dup1 = false;
								dup2 = false;
								break;
							}
						}
					}
				}

				else
				{
					for (int y = x + 1; y < hand.size(); y++)
					{
						Tile temp2 = hand.get(y);
						if (!temp2.getUsed())
						{
							if (temp1.getTileID().equals(temp2.getTileID()) && !dup1)
							{
								dup1 = true;
								dup01 = temp2;
							}
							else if (temp1.getTileID().equals(temp2.getTileID()))
							{
								dup2 = true;
								dup02 = temp2;
							}
						}
					}
					if (dup01 != null && dup02 != null)
					{
						addToSet(temp1, dup01, dup02);
						temp1.setUsed();
						dup01.setUsed();
						dup02.setUsed();
						dup01 = null;
						dup02 = null;
						dup1 = false;
						dup2 = false;
						break;
					}
				}

			}
			dup01 = null;
			dup02 = null;
			dup1 = false;
			dup2 = false;
		}
	}

	public void addToSpecialString(String str)
	{
		// Adds string to special sets, for sounds
		if (special1String.equals(""))
		{
			special1String = str;
			special1Boolean = true;
		}
		else if (special2String.equals(""))
		{
			special2String = str;
			special2Boolean = true;
		}
		else if (special3String.equals(""))
		{
			special3String = str;
			special3Boolean = true;
		}
		else if (special4String.equals(""))
		{
			special4String = str;
			special4Boolean = true;
		}
	}

	private void addToSet(Tile one, Tile two, Tile three)
	{
		// checks for an empty set and adds to it
		if (set1.isEmpty())
		{
			set1.add(one);
			set1.add(two);
			set1.add(three);
		}
		else if (set2.isEmpty())
		{
			set2.add(one);
			set2.add(two);
			set2.add(three);
		}
		else if (set3.isEmpty())
		{
			set3.add(one);
			set3.add(two);
			set3.add(three);
		}
		else if (set4.isEmpty())
		{
			set4.add(one);
			set4.add(two);
			set4.add(three);
		}
	}

	public void addToSpecialSetOne(Tile one)
	{
		int same = 0;
		same = 0;
		Tile temp = one;

		for (Tile tile : special1)
		{
			if (temp.getTileID().equals(tile.getTileID()) && temp.getTileNumber() == tile.getTileNumber()) same++;
			if (same == 3)
			{
				special1.add(one);
				return;
			}
		}
		same = 0;

		for (Tile tile : special2)
		{
			if (temp.getTileID().equals(tile.getTileID()) && temp.getTileNumber() == tile.getTileNumber()) same++;
			if (same == 3)
			{
				special2.add(one);
				return;
			}
		}
		same = 0;

		for (Tile tile : special3)
		{
			if (temp.getTileID().equals(tile.getTileID()) && temp.getTileNumber() == tile.getTileNumber()) same++;
			if (same == 3)
			{
				special3.add(one);
				return;
			}
		}
		same = 0;

		for (Tile tile : special4)
		{
			if (temp.getTileID().equals(tile.getTileID()) && temp.getTileNumber() == tile.getTileNumber()) same++;
			if (same == 3)
			{
				special4.add(one);
				return;
			}
		}

	}

	public void addToSpecialSet(Tile one, Tile two, Tile three)
	{
		// checks for an empty set and adds to it
		if (special1.isEmpty())
		{
			special1.add(one);
			special1.add(two);
			special1.add(three);
		}
		else if (special2.isEmpty())
		{
			special2.add(one);
			special2.add(two);
			special2.add(three);
		}
		else if (special3.isEmpty())
		{
			special3.add(one);
			special3.add(two);
			special3.add(three);
		}
		else if (special4.isEmpty())
		{
			special4.add(one);
			special4.add(two);
			special4.add(three);
		}
	}

	public void addToSpecialSet(Tile one, Tile two, Tile three, Tile four)
	{
		// checks for an empty set and adds to it
		if (special1.isEmpty())
		{
			special1.add(one);
			special1.add(two);
			special1.add(three);
			special1.add(four);
		}
		else if (special2.isEmpty())
		{
			special2.add(one);
			special2.add(two);
			special2.add(three);
			special2.add(four);
		}
		else if (special3.isEmpty())
		{
			special3.add(one);
			special3.add(two);
			special3.add(three);
			special3.add(four);
		}
		else if (special4.isEmpty())
		{
			special4.add(one);
			special4.add(two);
			special4.add(three);
			special3.add(four);
		}
	}

	private void addEyeSet(Tile one, Tile two)
	{
		if (set5.isEmpty())
		{
			set5.add(one);
			set5.add(two);
		}
	}

	public void resetSets(ArrayList<Tile> hand)
	{
		set1.clear();
		set2.clear();
		set3.clear();
		set4.clear();
		set5.clear();
		eye = false;
		for (Tile x : hand)
			x.setNotUsed();
	}

	public void upperSung(Tile played)
	{
		Tile upper = null;
		Tile upper2 = null;
		for (Tile x : hand)
		{
			if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber() + 1)
			{
				upper = x;
			}
			if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber() + 2)
			{
				upper2 = x;
			}
		}
		if (upper != null && upper2 != null)
		{
			temp3.add(played);
			temp3.add(upper);
			temp3.add(upper2);
			// addToTempSet(played, upper, upper2);
			// removeSetTiles(played, upper, upper2);
		}
	}

	public void lowerSung(Tile played)
	{
		Tile lower = null;
		Tile lower2 = null;
		for (Tile x : hand)
		{
			if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber() - 1)
			{
				lower = x;
			}
			if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber() - 2)
			{
				lower2 = x;
			}
		}
		if (lower != null && lower2 != null)
		{
			temp1.add(lower2);
			temp1.add(lower);
			temp1.add(played);
			// addToTempSet(lower2, lower, played);
			// removeSetTiles(lower2, lower, played);
		}
	}

	public void innerSung(Tile played)
	{
		Tile lower = null;
		Tile upper = null;
		for (Tile x : hand)
		{
			if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber() + 1)
			{
				upper = x;
			}
			if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber() - 1)
			{
				lower = x;
			}
		}
		if (upper != null && lower != null)
		{
			temp2.add(lower);
			temp2.add(played);
			temp2.add(upper);
			// addToTempSet(lower, played, upper);
			// removeSetTiles(lower, played, upper);
		}
	}

	public ArrayList<Tile> poong(Tile played)
	{
		ArrayList<Tile> temp = new ArrayList<Tile>();
		Tile one = null;
		Tile two = null;
		if (played.getTileID().equals(Tile.KUNGFU) || played.getTileID().equals(Tile.STICK)
				|| played.getTileID().equals(Tile.ROCK))
		{
			for (Tile x : hand)
			{
				if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber()
						&& one == null)
				{
					one = x;
				}
				else if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber())
				{
					two = x;
				}
			}

		}

		else
		{
			for (Tile x : hand)
			{
				if (x.getTileID().equals(played.getTileID()) && one == null)
				{
					one = x;
				}
				else if (x.getTileID().equals(played.getTileID()))
				{
					two = x;
				}
			}
		}
		if (one != null && two != null)
		{
			temp.add(one);
			temp.add(two);
			temp.add(played);
		}
		return temp;
	}

	public ArrayList<Tile> goong(Tile played)
	{
		ArrayList<Tile> temp = new ArrayList<Tile>();
		Tile one = null;
		Tile two = null;
		Tile three = null;
		if (played.getTileID().equals(Tile.KUNGFU) || played.getTileID().equals(Tile.STICK)
				|| played.getTileID().equals(Tile.ROCK))
		{
			for (Tile x : hand)
			{
				if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber()
						&& one == null)
				{
					one = x;
				}
				else if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber()
						&& two == null)
				{
					two = x;
				}
				else if (x.getTileID().equals(played.getTileID()) && x.getTileNumber() == played.getTileNumber())
				{
					three = x;
				}
			}

		}

		else
		{
			for (Tile x : hand)
			{
				if (x.getTileID().equals(played.getTileID()) && one == null)
				{
					one = x;
				}
				else if (x.getTileID().equals(played.getTileID()) && two == null)
				{
					two = x;
				}
				else if (x.getTileID().equals(played.getTileID()))
				{
					three = x;
				}
			}
		}
		if (one != null && two != null && three != null)
		{
			temp.add(one);
			temp.add(two);
			temp.add(three);
			temp.add(played);
		}
		return temp;
	}

	public boolean flowerCheck(Tile tile)
	{
		if (tile.getTileID().equals(Tile.BLUE_FLOWER)) return true;
		if (tile.getTileID().equals(Tile.RED_FLOWER)) return true;
		return false;
	}

	public boolean drawnFlower(ArrayList<Tile> list)
	{
		if (flowerCheck(list.get(list.size() - 1))) return true;
		return false;
	}
	
	public boolean winFlower()
	{
		if(flowers.size() > 6) return true;
		return false;
	}

	public ArrayList<Tile> getFlowers()
	{
		return flowers;
	}
}