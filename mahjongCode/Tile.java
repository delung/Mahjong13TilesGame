package mahjongCode;

import java.io.Serializable;

public class Tile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8629289706264019283L;
	
	public static final String KUNGFU = "kungfu";
	public static final String STICK = "stick";
	public static final String ROCK = "rock";
	public static final String NORTH = "north";
	public static final String WEST = "west";
	public static final String SOUTH = "south";
	public static final String EAST = "east";
	public static final String CABBAGE = "cabbage";
	public static final String TOFU = "tofu";
	public static final String DAGGER = "dagger";
	public static final String BLUE_FLOWER = "blueFlower";
	public static final String RED_FLOWER = "redFlower";

	
	private String tileID;
	private int tileNum;
	private int id;
	private boolean used = false;
	
	public Tile(String tileID, int tileNum, int id)
	{
		this.tileID = tileID;
		this.tileNum = tileNum;
		this.id = id;
	}
	
	public Tile(Tile tile)
	{
		this.tileID = tile.getTileID();
		this.tileNum = tile.getTileNumber();
		this.id = tile.getID();
	}
	

	@Override
	public boolean equals(Object other)
	{
		if(other == null) return false;
		if(this.getClass() != other.getClass()) return false;
		
		Tile tile = (Tile) other;
		return (tileID.equals(tile.getTileID()) && tileNum == tile.getTileNumber());
	}
	
	@Override
	public String toString()
	{
		return tileID + tileNum + "(" + id + ")";
	}
	
 	public String getImagePath(String tileID, int tileNum){
		String filename = "/res/tiles/";
		switch (tileID){
		case "kungfu":
			filename += "kungfu" + tileNum;
			break;
		case "stick":
			filename += "stick" + tileNum;
			break;
		case "rock":
			filename += "rock" + tileNum;
			break;
		case "north":
			filename += "north";
			break;
		case "east":
			filename += "east";
			break;
		case "south":
			filename += "south";
			break;
		case "west":
			filename += "west";
			break;
		case "cabbage":
			filename += "cabbage";
			break;
		case "tofu":
			filename += "tofu";
			break;
		case "dagger":
			filename += "dagger";
			break;
		case "blueFlower":
			filename += "blueFlower" + tileNum;
			break;
		case "redFlower":
			filename += "redFlower" + tileNum;
			break;
		
		}
		filename += ".png";
		return filename;
	}

	public String getTileID() { return tileID; }
	public int getTileNumber() { return tileNum; }
	public int getID() { return id; }
	public boolean getUsed() { return used; }
	
	public void setUsed() { used = true; }
	public void setNotUsed() { used = false; }
}
