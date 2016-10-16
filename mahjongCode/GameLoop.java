package mahjongCode;

import java.util.ArrayList;
import java.util.Collections;

public class GameLoop {

	public static final int MAX_TILES = 144;

	
	
	private ArrayList<Tile> tileStack = new ArrayList<Tile>();
	
	public GameLoop()
	{
		//createStack(); //creates the tileStack
		//createPlayers();
		//drawTile();
	}
	
	public ArrayList<Tile> getStack()
	{
		return tileStack;
	}

	public void createStack() 
	{
		int id = 0;
		// create the kungfu tiles
		for(int num = 1; num < 10; num++)
		{
			for(int dup = 0; dup < 4; dup++)
			{
				tileStack.add(new Tile(Tile.KUNGFU, num, id));
				id++;
			}
		}
		// create the stick tiles
		for(int num = 1; num < 10; num++)
		{
			for(int dup = 0; dup < 4; dup++)
			{
				tileStack.add(new Tile(Tile.STICK, num, id));
				id++;
			}
		}
		// create the rock tiles
		for(int num = 1; num < 10; num++)
		{
			for(int dup = 0; dup < 4; dup++)
			{
				tileStack.add(new Tile(Tile.ROCK, num, id));
				id++;
			}
		}
		// create the North tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.NORTH, 0, id));
			id++;
		}
		// create the South tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.SOUTH, 0, id));
			id++;
		}
		// create the West tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.WEST, 0, id));
			id++;
		}
		// create the East tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.EAST, 0, id));
			id++;
		}
		// create the cabbage tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.CABBAGE, 0, id));
			id++;
		}
		// create the tofu tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.TOFU, 0, id));
			id++;
		}
		// create the dagger tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.DAGGER, 0, id));
			id++;
		}
		// create the flower tile
		// create the flower tile
		  for(int dup = 0; dup < 1; dup++) 
		  {
		   for(int num = 1; num < 5; num++)
		   {
		    tileStack.add(new Tile(Tile.RED_FLOWER, num, id));
		    id++;
		   }
		   for(int num = 1; num < 5; num++)
		   {
		    tileStack.add(new Tile(Tile.BLUE_FLOWER, num, id));
		    id++;
		   }
		  }
		
		Collections.shuffle(tileStack);
	}
	
	public void createTestStack()
	{
		int id = 3;
		tileStack.add(new Tile(Tile.KUNGFU, 1, 300));
		tileStack.add(new Tile(Tile.KUNGFU, 1, 301));
		tileStack.add(new Tile(Tile.KUNGFU, 1, 302));
		
		for(int dup = 0; dup < 4; dup++)
		{
			for(int num = 1; num < 10; num++)
			{
				tileStack.add(new Tile(Tile.ROCK, num, id));
				id++;
			}
		}
		for(int dup = 0; dup < 3; dup++)
		{
			tileStack.add(new Tile(Tile.DAGGER, 0, id));
			id++;
		}
		tileStack.add(new Tile(Tile.KUNGFU, 1, 200));
		tileStack.add(new Tile(Tile.KUNGFU, 1, 201));
		tileStack.add(new Tile(Tile.KUNGFU, 1, 202));
		tileStack.add(new Tile(Tile.KUNGFU, 1, 203));
		tileStack.add(new Tile(Tile.KUNGFU, 1, 204));
		tileStack.add(new Tile(Tile.KUNGFU, 1, 205));
		tileStack.add(new Tile(Tile.KUNGFU, 1, 206));
		tileStack.add(new Tile(Tile.KUNGFU, 1, 207));
		tileStack.add(new Tile(Tile.KUNGFU, 1, 208));
		tileStack.add(new Tile(Tile.KUNGFU, 1, 209));
		
		tileStack.add(new Tile(Tile.KUNGFU, 1, 2));
		
		
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.TOFU, 0, id));
			id++;
		}
		for(int dup = 0; dup < 4; dup++)
		{
			for(int num = 1; num < 10; num++)
			{
				tileStack.add(new Tile(Tile.KUNGFU, num, id + 200));
				id++;
			}
		}
		// create the stick tiles
		for(int dup = 0; dup < 4; dup++)
		{
			for(int num = 1; num < 10; num++)
			{
				tileStack.add(new Tile(Tile.STICK, num, id));
				id++;
			}
		}
		// create the rock tiles
		for(int dup = 0; dup < 4; dup++)
		{
			for(int num = 1; num < 10; num++)
			{
				tileStack.add(new Tile(Tile.ROCK, num, id));
				id++;
			}
		}
		// create the North tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.NORTH, 0, id));
			id++;
		}
		// create the South tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.SOUTH, 0, id));
			id++;
		}
		// create the West tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.WEST, 0, id));
			id++;
		}
		// create the East tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.EAST, 0, id));
			id++;
		}
		// create the cabbage tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.CABBAGE, 0, id));
			id++;
		}
		// create the tofu tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.TOFU, 0, id));
			id++;
		}
		// create the dagger tiles
		for(int dup = 0; dup < 4; dup++)
		{
			tileStack.add(new Tile(Tile.DAGGER, 0, id));
			id++;
		}
		// create the flower tile
		// create the flower tile
		  for(int dup = 0; dup < 1; dup++) 
		  {
		   for(int num = 1; num < 5; num++)
		   {
		    tileStack.add(new Tile(Tile.RED_FLOWER, num, id));
		    id++;
		   }
		   for(int num = 1; num < 5; num++)
		   {
		    tileStack.add(new Tile(Tile.BLUE_FLOWER, num, id));
		    id++;
		   }
		  }
		
	}
}

