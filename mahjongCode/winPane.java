package mahjongCode;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import mahjongServer.Client;

import javax.swing.JButton;
import java.awt.Font;

public class winPane extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int IMAGE_HEIGHT = 160;
	public static final int IMAGE_WIDTH = 100;

	private JPanel contentPane, winPanel, playerPanel1, playerPanel2, playerPanel3;
	private JLabel name1, name2, name3, name4, readylbl, points;
	private String user;
	private Player self;
	private JButton btnReady;
	private int rdy = 0;
	@SuppressWarnings("unused")
	private Client client;


	public void initialize() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setResizable(false);
					setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public winPane(String user, Player yourself, Client client, int score) {
		this.client = client;
		this.user = user;
		this.self = yourself;
		setTitle("Winning Hand");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(761, 556);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		winPanel = new JPanel();
		winPanel.setBounds(40, 60, 520, 78);
		contentPane.add(winPanel);
		
		playerPanel1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) winPanel.getLayout();
		flowLayout_1.setHgap(1);
		playerPanel1.setBounds(40, 187, 490, 65);
		contentPane.add(playerPanel1);

		playerPanel2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) playerPanel2.getLayout();
		flowLayout.setHgap(1);
		playerPanel2.setBounds(40, 265, 490, 65);
		contentPane.add(playerPanel2);

		playerPanel3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) playerPanel3.getLayout();
		flowLayout_2.setHgap(1);
		playerPanel3.setBounds(40, 343, 490, 65);
		contentPane.add(playerPanel3);

		name1 = new JLabel();
		name1.setHorizontalAlignment(SwingConstants.CENTER);
		name1.setText(user);
		name1.setBounds(200, 25, 200, 20);
		contentPane.add(name1);

		name2 = new JLabel();
		name2.setBounds(542, 187, 200, 20);
		contentPane.add(name2);

		name3 = new JLabel();
		name3.setBounds(542, 265, 200, 20);
		contentPane.add(name3);

		name4 = new JLabel();
		name4.setBounds(542, 343, 200, 20);
		contentPane.add(name4);
		
		btnReady = new JButton("Replay");
		btnReady.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try 
				{
					client.send("Ready");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				btnReady.setEnabled(false);
			}
		});
		btnReady.setBounds(40, 421, 131, 65);
		contentPane.add(btnReady);
		
		readylbl = new JLabel(rdy + "/4 Ready");
		readylbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		readylbl.setBounds(200, 421, 136, 65);
		contentPane.add(readylbl);
		
		points = new JLabel(score + " pts");
		points.setFont(new Font("Times New Roman", Font.BOLD, 18));
		points.setBounds(582, 60, 100, 78);
		contentPane.add(points);
		
		

	}
	public void update()
	{
		rdy++;
		readylbl.setText(rdy + "/4 Ready");
		readylbl.updateUI();
		readylbl.repaint();
	}
	
	public void setPanelContents(ArrayList<Tile> tiles, Player playerLeft, Player playerRight, Player playerTop) throws IOException {
		
		ArrayList<Tile> tiles2 = new ArrayList<Tile>();
		ArrayList<Tile> tiles3 = new ArrayList<Tile>();
		ArrayList<Tile> tiles4 = new ArrayList<Tile>();
		if (!tiles.isEmpty()) {
			winPanel.removeAll();
			for (int i = 0; i < tiles.size(); i++) 
			{
				BufferedImage img1 = ImageIO.read(this.getClass().getResourceAsStream(
						tiles.get(i).getImagePath(tiles.get(i).getTileID(), tiles.get(i).getTileNumber())));
				img1 = resize(img1, (int) Math.round(IMAGE_WIDTH * .25), (int) Math.round(IMAGE_HEIGHT * .25));
				Icon img2 = new ImageIcon(img1);
				JLabel lbl = new JLabel(img2);
				lbl.setBounds(0, 0, lbl.getWidth(), lbl.getHeight());
				winPanel.add(lbl);
			}
			if(playerRight.getName().equals(user))
			{
				tiles2 = self.getHand();
				name2.setText(self.getName());
			}
			else {
				tiles2 = playerRight.getHand();
				name2.setText(playerRight.getName());
			}
			for (int i = 0; i < tiles2.size(); i++) {
				BufferedImage img1 = ImageIO.read(this.getClass().getResourceAsStream(
						tiles2.get(i).getImagePath(tiles2.get(i).getTileID(), tiles2.get(i).getTileNumber())));
				img1 = resize(img1, (int) Math.round(IMAGE_WIDTH * .20), (int) Math.round(IMAGE_HEIGHT * .20));
				Icon img2 = new ImageIcon(img1);
				JLabel lbl = new JLabel(img2);
				lbl.setBounds(0, 0, lbl.getWidth(), lbl.getHeight());
				playerPanel1.add(lbl);
			}
			
			if(playerTop.getName().equals(user))
			{
				tiles3 = self.getHand();
				name3.setText(self.getName());
			}
			else {
				tiles3 = playerTop.getHand();
				name3.setText(playerTop.getName());
			}
			for (int i = 0; i < tiles3.size(); i++) {
				BufferedImage img1 = ImageIO.read(this.getClass().getResourceAsStream(
						tiles3.get(i).getImagePath(tiles3.get(i).getTileID(), tiles3.get(i).getTileNumber())));
				img1 = resize(img1, (int) Math.round(IMAGE_WIDTH * .20), (int) Math.round(IMAGE_HEIGHT * .20));
				Icon img2 = new ImageIcon(img1);
				JLabel lbl = new JLabel(img2);
				lbl.setBounds(0, 0, lbl.getWidth(), lbl.getHeight());
				playerPanel2.add(lbl);
			}
			
			if(playerLeft.getName().equals(user))
			{
				tiles4 = self.getHand();
				name4.setText(self.getName());
			}
			else {
				tiles4 = playerLeft.getHand();
				name4.setText(playerLeft.getName());
			}
			for (int i = 0; i < tiles4.size(); i++) {
				BufferedImage img1 = ImageIO.read(this.getClass().getResourceAsStream(
						tiles4.get(i).getImagePath(tiles4.get(i).getTileID(), tiles4.get(i).getTileNumber())));
				img1 = resize(img1, (int) Math.round(IMAGE_WIDTH * .20), (int) Math.round(IMAGE_HEIGHT * .20));
				Icon img2 = new ImageIcon(img1);
				JLabel lbl = new JLabel(img2);
				lbl.setBounds(0, 0, lbl.getWidth(), lbl.getHeight());
				playerPanel3.add(lbl);
			}
			
			winPanel.repaint();
			winPanel.updateUI();
		}
	}

	public static BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}
}