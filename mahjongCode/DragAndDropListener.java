package mahjongCode;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JLabel;

import javax.swing.SwingUtilities;

public class DragAndDropListener extends MouseAdapter{
	private Point startPoint, lastLocation;
	private Component draggedObject;
	private GamePanel panel;
	private ArrayList<JLabel> handTiles;
	private double distToFirstHalfway;
	private ArrayList<Double> halfMarkers = new ArrayList<Double>();
	private int tileIndex;

	private Tile tile;
	private Map<JLabel, Tile> handMap;

	private Player player;
	
	public DragAndDropListener(GamePanel panel, ArrayList<JLabel> handTiles){
		this.panel = panel;
		this.handTiles = handTiles;
	}
	private void createMarkers()
	{
		try {
			halfMarkers.clear();
			distToFirstHalfway = this.handTiles.get(0).getX() + (handTiles.get(0).getWidth() * .5);
			halfMarkers.add(distToFirstHalfway);
			for (int i = 1; i < this.handTiles.size(); i++){
				halfMarkers.add(this.handTiles.get(i).getX() + (handTiles.get(i).getWidth() * .5));
			}
		}
		catch (Exception e) {}
	}
	@Override
	public void mousePressed(MouseEvent e){
		createMarkers();
			
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		draggedObject = (Component) e.getSource();
		if(e.getClickCount() == 2 && Main.myTurn)
		{
			tile = (Tile) handMap.get(draggedObject);
			panel.setTilePlayed(tile);
		}
		
		for (int i = 0; i < handTiles.size(); i++){
			if (draggedObject.equals(handTiles.get(i))){
				tileIndex = i;
				panel.yourHand.setComponentZOrder(handTiles.get(i), 0);
				break;
			}
		}
		startPoint = SwingUtilities.convertPoint(draggedObject, e.getPoint(), 
														draggedObject.getParent());
		lastLocation = startPoint.getLocation();
	}
	
	@Override
	public void mouseDragged(MouseEvent e){
		Point location = SwingUtilities.convertPoint(draggedObject, e.getPoint(), 
														draggedObject.getParent());
		Point locTrans = location.getLocation();
		locTrans.translate(draggedObject.getParent().getX(), draggedObject.getParent().getY());
		if(draggedObject.getParent().getBounds().contains(locTrans)){
			Point newLocation = draggedObject.getLocation();
			newLocation.translate(location.x - startPoint.x, location.y - startPoint.y);
			newLocation.x = Math.max(newLocation.x, 0);
			newLocation.x = Math.min(newLocation.x, draggedObject.getParent().getWidth() - draggedObject.getWidth());
			newLocation.y = Math.max(newLocation.y, 0);
			newLocation.y = Math.min(newLocation.y, draggedObject.getParent().getHeight() - draggedObject.getHeight());
			draggedObject.setLocation(newLocation);
			panel.yourHand.repaint();
			startPoint = location;
			//System.out.println(startPoint.x);
		}
	}
	
	@Override 
	public void mouseReleased(MouseEvent e){
		determineLocation(e);
		lastLocation = startPoint.getLocation();
		panel.setCursor(null);
		startPoint = null;
		draggedObject = null;
		createMarkers();
	}
	
	public void makeDraggable(Component component){
		component.addMouseListener(this);
		component.addMouseMotionListener(this);
	}
	
	public void determineLocation(MouseEvent e){		
		double dist = handTiles.get(tileIndex).getX();
		int index = 0;
		if (dist - lastLocation.x == 0){}
		else{
		for(int i = 0; i < halfMarkers.size(); i++){
			if(i > 0){
				if (dist >= halfMarkers.get(i-1) && dist <= halfMarkers.get(i)){
					//System.out.println("after " + (i-1) + " before " + (i));
					index = i;
					break;
				}
			}
			else if(dist > halfMarkers.get(halfMarkers.size() - 1)){
				index = halfMarkers.size();
				//System.out.println("last");
				index = halfMarkers.size() - 1;
				break;
			}
			else if(dist < halfMarkers.get(0)){
			//System.out.println("in 0");
			break;
			}
		}
		JLabel temp = (JLabel) draggedObject;
		handTiles.remove(draggedObject);
		handTiles.add(index, temp);
		this.player = panel.getPlayer();

		ArrayList<Tile> tempHand = new ArrayList<Tile>();
		for(JLabel x : handTiles)
		{
			 tempHand.add(handMap.get(x));
		
		}
		player.setHand(tempHand);
		panel.RedisplayHand(handTiles, panel.yourHand);
		}
	}
	
	public void setMap(Map<JLabel, Tile> m)
	{
		handMap = m;
	}
}
	
