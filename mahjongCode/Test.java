package mahjongCode;

import java.util.ArrayList;

public class Test 
{
	
	public static void main(String[] args) 
	{
		Player me = new Player();
		
		me.addTile(new Tile(Tile.ROCK, 2, 23));
		me.addTile(new Tile(Tile.ROCK, 4, 24));
		me.addTile(new Tile(Tile.ROCK, 4, 25));
		
		me.addTile(new Tile(Tile.ROCK, 4, 26));
		
		me.addTile(new Tile(Tile.ROCK, 5, 27));
		me.addTile(new Tile(Tile.ROCK, 6, 28));
		me.addTile(new Tile(Tile.ROCK, 7, 29));
	

		me.addToSpecialSet(new Tile(Tile.NORTH, 0, 30), new Tile(Tile.NORTH, 0, 31), new Tile(Tile.NORTH, 0, 32));
		me.addToSpecialSet(new Tile(Tile.KUNGFU, 1, 33), new Tile(Tile.KUNGFU, 2, 34), new Tile(Tile.KUNGFU, 3, 35));
			
		System.out.println(me.winOnTilePlayed(new Tile(Tile.ROCK, 2, 36)));
		System.out.println(me.winOnTilePlayed2(new Tile(Tile.ROCK, 2, 36)));
		System.out.println(me.winOnTilePlayed3(new Tile(Tile.ROCK, 2, 36)));
		System.out.println(me.winOnTilePlayed4(new Tile(Tile.ROCK, 2, 36)));
		
		ArrayList<Tile> hand = me.winPongPref(new Tile(Tile.ROCK, 2, 36));
		ArrayList<Tile> hand2 = me.winPongPref2(new Tile(Tile.ROCK, 2, 36));
		System.out.println(hand);
		System.out.println(hand2);
		
	}
}
