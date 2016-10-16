package mahjongCode;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import mahjongServer.Client;

public class GamePanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel playerLeftHand = new JPanel();
	private final JPanel playerRightHand, playerTopHand;
	
	private JButton btnSong;
	private JButton btnGong;
	private JButton btnPong;
	private JButton btnWin;
	public JButton btnSort;
	
	public JPanel yourHand;
	public JLabel lblPlayerNameLeft;
	public JLabel lblPlayerNameTop;
	public JLabel lblPlayerNameRight;
	public JLabel lblPlayerNameYou;
	public JLabel lblWaitingForPlayers;
	public JLabel tilesRemaining;
	public JLabel boardImg;
	public JPanel bigTile;
	public JPanel shadowRealm;
	public JPanel TopPlayerFlowerPanel, LeftPlayerFlowerPanel, RightPlayerFlowerPanel, MyFlowerPanel;
	
	public ArrayList<JLabel> handTiles = new ArrayList<JLabel>();
	public ArrayList<JLabel> flowerTiles = new ArrayList<JLabel>();
	public ArrayList<JLabel>playerLabels = new ArrayList<JLabel>();
	private DragAndDropListener lis;
	private Tile played = null;
	private Tile bigTileLastPlayed;
	private Map<JLabel, Tile> handMap = new HashMap<JLabel, Tile>();
	private BufferedImage tileBack = null;
	private Player player = null;
	private Client client = null;
	
	public static final int IMAGE_HEIGHT = 160;
	public static final int IMAGE_WIDTH = 100;
	private JPanel matchLeft4;
	private JPanel matchLeft3;
	private JPanel matchLeft2;
	private JPanel matchLeft1;
	private JPanel matchTop4;
	private JPanel matchTop3;
	private JPanel matchTop2;
	private JPanel matchTop1;
	private JPanel matchRight4;
	private JPanel matchRight3;
	private JPanel matchRight2;
	private JPanel matchRight1;
	private JPanel matchPanelYou2;
	private JPanel matchPanelYou4;
	private JPanel matchPanelYou3;
	private JPanel matchPanelYou1;
	public JLabel lblCurrentPlayerTurn;
	private JLabel yourScore;
	private JLabel leftScore;
	private JLabel rightScore;
	private JLabel topScore;
	
	/**
	 * Create the frame.
	 */
	public GamePanel() {
		setTitle("Mahjong v1.00");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1336, 768);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		topScore = new JLabel("Score:");
		topScore.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		topScore.setBounds(855, 89, 106, 29);
		contentPane.add(topScore);
		
		leftScore = new JLabel("Score:");
		leftScore.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		leftScore.setUI(new VerticalLabelUI(true));
		leftScore.setBounds(83, 525, 47, 125);
		contentPane.add(leftScore);
		
		lblPlayerNameYou = new JLabel("Player Name", SwingConstants.LEFT);
		lblPlayerNameYou.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblPlayerNameYou.setBounds(462, 624, 251, 26);
		contentPane.add(lblPlayerNameYou);
		
		lblPlayerNameRight = new JLabel("Player Name", SwingConstants.CENTER);
		lblPlayerNameRight.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblPlayerNameRight.setUI(new VerticalLabelUI());
		lblPlayerNameRight.setBounds(1211, 260, 29, 250);
		contentPane.add(lblPlayerNameRight);
		playerLabels.add(lblPlayerNameRight);
		
		lblPlayerNameTop = new JLabel("Player Name", SwingConstants.CENTER);
		lblPlayerNameTop.setBounds(443, 89, 400, 29);
		contentPane.add(lblPlayerNameTop);
		lblPlayerNameTop.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		playerLabels.add(lblPlayerNameTop);
		
		
		lblPlayerNameLeft = new JLabel("Player Name", SwingConstants.CENTER);
		lblPlayerNameLeft.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblPlayerNameLeft.setUI(new VerticalLabelUI(true));
		lblPlayerNameLeft.setBounds(83, 260, 47, 250);
		contentPane.add(lblPlayerNameLeft);
		playerLabels.add(lblPlayerNameLeft);
		
		
		try {
			tileBack = ImageIO.read(this.getClass().getResourceAsStream("/res/tiles/back.png"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		tileBack = resize(tileBack, (int) Math.round(IMAGE_WIDTH * .4), (int) Math.round(IMAGE_HEIGHT * .4));
		
		yourHand = new JPanel();
		yourHand.setOpaque(false);
		yourHand.setBorder(null);
		yourHand.setBounds(325, 657, 649, 83);
		yourHand.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(yourHand);
		yourHand.validate();
		lis = new DragAndDropListener(this, handTiles);
		
		JPanel playPanel = new JPanel();
		playPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		playPanel.setBounds(131, 125, (int)(.8 * getWidth()), (int)(.65 * getHeight()));
		contentPane.add(playPanel);
		playPanel.setLayout(null);
		
		BufferedImage BGimg = null;
		BufferedImage BGimg2 = null;
		try {
			BGimg = ImageIO.read(this.getClass().getResourceAsStream("/res/board.png"));
			BGimg2 = ImageIO.read(this.getClass().getResourceAsStream("/res/panel.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Can't load image");
		}
		BGimg = resize(BGimg, 1068, 499);
		ImageIcon playScreenBG = new ImageIcon(BGimg); 
		ImageIcon mainScreenBG = new ImageIcon(BGimg2);
		
		bigTile = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) bigTile.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		bigTile.setBounds(847, 116, 160, 250);
		bigTile.setOpaque(false);
		playPanel.add(bigTile);
		
		MyFlowerPanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) MyFlowerPanel.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(1);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		MyFlowerPanel.setOpaque(false);
		MyFlowerPanel.setBorder(null);
		MyFlowerPanel.setBounds(10, 441, 200, 40);
		playPanel.add(MyFlowerPanel);
		
		RightPlayerFlowerPanel = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) RightPlayerFlowerPanel.getLayout();
		flowLayout_5.setAlignment(FlowLayout.TRAILING);
		flowLayout_5.setHgap(0);
		flowLayout_5.setVgap(1);
		RightPlayerFlowerPanel.setOpaque(false);
		RightPlayerFlowerPanel.setBounds(1017, 288, 40, 200);
		playPanel.add(RightPlayerFlowerPanel);
		
		LeftPlayerFlowerPanel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) LeftPlayerFlowerPanel.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEADING);
		flowLayout_3.setHgap(0);
		flowLayout_3.setVgap(1);
		LeftPlayerFlowerPanel.setOpaque(false);
		LeftPlayerFlowerPanel.setBounds(20, 11, 40, 200);
		playPanel.add(LeftPlayerFlowerPanel);
		
		TopPlayerFlowerPanel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) TopPlayerFlowerPanel.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		flowLayout_4.setVgap(0);
		flowLayout_4.setHgap(1);
		TopPlayerFlowerPanel.setOpaque(false);
		TopPlayerFlowerPanel.setBorder(null);
		TopPlayerFlowerPanel.setBounds(858, 21, 200, 40);
		playPanel.add(TopPlayerFlowerPanel);
		
		shadowRealm = new JPanel();
		FlowLayout flowLayout = (FlowLayout) shadowRealm.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		shadowRealm.setBounds(70, 35, 585, 395);
		shadowRealm.setOpaque(false);
		playPanel.add(shadowRealm);
		
		lblCurrentPlayerTurn = new JLabel("'s Turn");
		lblCurrentPlayerTurn.setFont(new Font("GungsuhChe", Font.PLAIN, 16));
		lblCurrentPlayerTurn.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTurn.setBounds(847, 377, 160, 34);
		playPanel.add(lblCurrentPlayerTurn);
		tilesRemaining = new JLabel();
		tilesRemaining.setFont(new Font("GungsuhChe", Font.PLAIN, 16));
		tilesRemaining.setBounds(847, 49, 179, 56);
		playPanel.add(tilesRemaining);
		JLabel boardImg = new JLabel();
		boardImg.setSize(1068, 499);
		boardImg.setIcon(playScreenBG);
		playPanel.add(boardImg);
		FlowLayout flowLayout_7 = (FlowLayout) playerLeftHand.getLayout();
		flowLayout_7.setVgap(3);
		flowLayout_7.setHgap(3);
		
		playerLeftHand.setBorder(null);
		playerLeftHand.setOpaque(false);
		playerLeftHand.setBounds(12, 172, 61, 415);
		contentPane.add(playerLeftHand);
		
		playerTopHand = new JPanel();
		playerTopHand.setOpaque(false);
		playerTopHand.setBorder(null);
		playerTopHand.setBounds(325, 2, 649, 74);
		contentPane.add(playerTopHand);
		
		playerRightHand = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) playerRightHand.getLayout();
		flowLayout_6.setVgap(3);
		flowLayout_6.setHgap(3);
		playerRightHand.setOpaque(false);
		playerRightHand.setBorder(null);
		playerRightHand.setBounds(1259, 172, 61, 425);
		contentPane.add(playerRightHand);
		
			
		matchPanelYou1 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) matchPanelYou1.getLayout();
		flowLayout_8.setHgap(1);
		flowLayout_8.setVgap(1);
		matchPanelYou1.setOpaque(false);
		matchPanelYou1.setBorder(null);
		matchPanelYou1.setBounds(5, 690, 125, 40);
		contentPane.add(matchPanelYou1);
		
		matchPanelYou3 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) matchPanelYou3.getLayout();
		flowLayout_10.setVgap(1);
		matchPanelYou3.setOpaque(false);
		matchPanelYou3.setBorder(null);
		matchPanelYou3.setBounds(5, 640, 125, 40);
		contentPane.add(matchPanelYou3);
		
		matchPanelYou4 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) matchPanelYou4.getLayout();
		flowLayout_11.setVgap(1);
		matchPanelYou4.setOpaque(false);
		matchPanelYou4.setBorder(null);
		matchPanelYou4.setBounds(135, 640, 125, 40);
		contentPane.add(matchPanelYou4);
		
		matchPanelYou2 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) matchPanelYou2.getLayout();
		flowLayout_9.setHgap(1);
		flowLayout_9.setVgap(1);
		matchPanelYou2.setOpaque(false);
		matchPanelYou2.setBorder(null);
		matchPanelYou2.setBounds(135, 690, 125, 40);
		contentPane.add(matchPanelYou2);
		
		matchRight1 = new JPanel();
		FlowLayout flowLayout_23 = (FlowLayout) matchRight1.getLayout();
		flowLayout_23.setHgap(1);
		flowLayout_23.setVgap(1);
		matchRight1.setOpaque(false);
		matchRight1.setBorder(null);
		matchRight1.setBounds(1290, 632, 40, 108);
		contentPane.add(matchRight1);
		
		matchRight2 = new JPanel();
		FlowLayout flowLayout_22 = (FlowLayout) matchRight2.getLayout();
		flowLayout_22.setHgap(1);
		flowLayout_22.setVgap(1);
		matchRight2.setOpaque(false);
		matchRight2.setBorder(null);
		matchRight2.setBounds(1240, 632, 40, 108);
		contentPane.add(matchRight2);
		
		matchRight3 = new JPanel();
		FlowLayout flowLayout_21 = (FlowLayout) matchRight3.getLayout();
		flowLayout_21.setHgap(1);
		flowLayout_21.setVgap(1);
		matchRight3.setOpaque(false);
		matchRight3.setBorder(null);
		matchRight3.setBounds(1190, 632, 40, 108);
		contentPane.add(matchRight3);
		
		matchRight4 = new JPanel();
		FlowLayout flowLayout_20 = (FlowLayout) matchRight4.getLayout();
		flowLayout_20.setHgap(1);
		flowLayout_20.setVgap(1);
		matchRight4.setOpaque(false);
		matchRight4.setBorder(null);
		matchRight4.setBounds(1140, 632, 40, 108);
		contentPane.add(matchRight4);
		
		matchTop1 = new JPanel();
		FlowLayout flowLayout_18 = (FlowLayout) matchTop1.getLayout();
		flowLayout_18.setHgap(1);
		flowLayout_18.setVgap(1);
		matchTop1.setOpaque(false);
		matchTop1.setBorder(null);
		matchTop1.setBounds(1200, 0, 125, 40);
		contentPane.add(matchTop1);
		
		matchTop2 = new JPanel();
		FlowLayout flowLayout_17 = (FlowLayout) matchTop2.getLayout();
		flowLayout_17.setHgap(1);
		flowLayout_17.setVgap(1);
		matchTop2.setOpaque(false);
		matchTop2.setBorder(null);
		matchTop2.setBounds(1070, 0, 125, 40);
		contentPane.add(matchTop2);
		
		matchTop3 = new JPanel();
		FlowLayout flowLayout_19 = (FlowLayout) matchTop3.getLayout();
		flowLayout_19.setHgap(1);
		flowLayout_19.setVgap(1);
		matchTop3.setOpaque(false);
		matchTop3.setBorder(null);
		matchTop3.setBounds(1200, 43, 125, 40);
		contentPane.add(matchTop3);
		
		matchTop4 = new JPanel();
		FlowLayout flowLayout_16 = (FlowLayout) matchTop4.getLayout();
		flowLayout_16.setHgap(1);
		flowLayout_16.setVgap(1);
		matchTop4.setOpaque(false);
		matchTop4.setBorder(null);
		matchTop4.setBounds(1070, 43, 125, 40);
		contentPane.add(matchTop4);
		
		matchLeft1 = new JPanel();
		FlowLayout flowLayout_12 = (FlowLayout) matchLeft1.getLayout();
		flowLayout_12.setHgap(1);
		flowLayout_12.setVgap(1);
		matchLeft1.setOpaque(false);
		matchLeft1.setBorder(null);
		matchLeft1.setBounds(0, 0, 40, 112);
		contentPane.add(matchLeft1);
		
		matchLeft2 = new JPanel();
		FlowLayout flowLayout_13 = (FlowLayout) matchLeft2.getLayout();
		flowLayout_13.setHgap(1);
		flowLayout_13.setVgap(1);
		matchLeft2.setOpaque(false);
		matchLeft2.setBorder(null);
		matchLeft2.setBounds(50, 0, 40, 112);
		contentPane.add(matchLeft2);
		
		matchLeft3 = new JPanel();
		FlowLayout flowLayout_14 = (FlowLayout) matchLeft3.getLayout();
		flowLayout_14.setHgap(1);
		flowLayout_14.setVgap(1);
		matchLeft3.setOpaque(false);
		matchLeft3.setBorder(null);
		matchLeft3.setBounds(100, 0, 40, 112);
		contentPane.add(matchLeft3);
		
		matchLeft4 = new JPanel();
		FlowLayout flowLayout_15 = (FlowLayout) matchLeft4.getLayout();
		flowLayout_15.setHgap(1);
		flowLayout_15.setVgap(1);
		matchLeft4.setOpaque(false);
		matchLeft4.setBorder(null);
		matchLeft4.setBounds(150, 0, 40, 112);
		contentPane.add(matchLeft4);
		
		
		btnSort = new JButton("Sort Hand");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player != null && !player.getHand().isEmpty()){
				ArrayList<Tile> temp = player.getHand();
				int handSize = temp.size();
				int lowest = 0;
				ArrayList<Tile> sortList = new ArrayList<Tile>();
				
				for (int i = 0; i < handSize; i++){
					for (int i2 = 0; i2 < temp.size(); i2++){
						if (temp.get(i2).getID() < temp.get(lowest).getID()){
							lowest = i2;
						}
					}
					sortList.add(temp.get(lowest));
					temp.remove(lowest);
					lowest = 0;
				}
				try {
					player.setHand(sortList);
					displayHand(player.getHand());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			}
		});
		btnSort.setBounds(1004, 674, 126, 40);
		contentPane.add(btnSort);
		
		lblWaitingForPlayers = new JLabel("Waiting for players: ");
		lblWaitingForPlayers.setBounds(587, 69, 184, 43);
		contentPane.add(lblWaitingForPlayers);
		lblWaitingForPlayers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel pongSongPanel = new JPanel();
		pongSongPanel.setBounds(299, 621, 167, 29);
		contentPane.add(pongSongPanel);
		pongSongPanel.setOpaque(false);
		pongSongPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnPong = new JButton("Pong");
		btnPong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.send("poong" + player.getPosition());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPong.setHorizontalAlignment(SwingConstants.LEFT);
		btnPong.setBounds(0, 0, btnPong.getWidth(), btnPong.getHeight());
		pongSongPanel.add(btnPong);
		
		btnSong = new JButton("Song");
		btnSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.send("sung" + player.getPosition());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSong.setBounds(0, 0, btnSong.getWidth(), btnSong.getHeight());
		pongSongPanel.add(btnSong);
		
		btnSong.setEnabled(false);
		btnPong.setEnabled(false);
		
		JPanel gongWinPanel = new JPanel();
		gongWinPanel.setBounds(835, 621, 167, 29);
		contentPane.add(gongWinPanel);
		gongWinPanel.setOpaque(false);
		gongWinPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnGong = new JButton("Gong");
		btnGong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					client.send("goong" + player.getPosition());
					Main.myTurn = false;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGong.setHorizontalAlignment(SwingConstants.LEFT);
		btnGong.setBounds(0, 0, btnGong.getWidth(), btnGong.getHeight());
		gongWinPanel.add(btnGong);
		
		btnWin = new JButton("Win!");
		btnWin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					client.send("win" + player.getPosition());
					Main.notWin = false;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnWin.setBounds(0, 0, btnWin.getWidth(), btnWin.getHeight());
		gongWinPanel.add(btnWin);
		btnGong.setEnabled(false);
		btnWin.setEnabled(false);
		
		yourScore = new JLabel("Score:");
		yourScore.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		yourScore.setBounds(725, 624, 98, 26);
		contentPane.add(yourScore);
		
		rightScore = new JLabel("Score:");
		rightScore.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		rightScore.setUI(new VerticalLabelUI());
		rightScore.setBounds(1211, 152, 29, 95);
		contentPane.add(rightScore);
		
		JLabel mainImg = new JLabel();
		mainImg.setSize(1330, 740);
		mainImg.setIcon(mainScreenBG);
		contentPane.add(mainImg);
		
	}
	public void initialize()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				setTitle("Mahjong v1.00");
				try {
					setIconImage(ImageIO.read(this.getClass().getResourceAsStream("/res/ricehat.png")));
				} catch (IOException e) {
					e.printStackTrace();
				}
				setResizable(false);
				setVisible(true);
			}
				
		});

	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public void enableSongBtn()
	{
		btnSong.setEnabled(true);
	}
	public void disableSongBtn()
	{
		btnSong.setEnabled(false);
	}
	public void enablePongBtn()
	{
		btnPong.setEnabled(true);
	}
	public void disablePongBtn()
	{
		btnPong.setEnabled(false);
	}
	public void enableGongBtn()
	{
		btnGong.setEnabled(true);
	}
	public void disableGongBtn()
	{
		btnGong.setEnabled(false);
	}
	public void enableWinBtn()
	{
		btnWin.setEnabled(true);
	}
	public void disableWinBtn()
	{
		btnWin.setEnabled(false);
	}
	
	public void setClient(Client client)
	{
		this.client = client;
	}
	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	public void putFlower(ArrayList<Tile> flowers) throws IOException{
		MyFlowerPanel.removeAll();
		flowerTiles.clear();
		for (int i = 0; i < flowers.size(); i++){
			if (flowers.get(i).equals(null)){}
			else{
			BufferedImage img1 = ImageIO.read(this.getClass().getResourceAsStream(flowers.get(i).getImagePath(flowers.get(i).getTileID(), flowers.get(i).getTileNumber())));
			img1 = resize(img1, (int) Math.round(IMAGE_WIDTH * .25), (int) Math.round(IMAGE_HEIGHT * .25));
			ImageIcon img = new ImageIcon(img1);
			flowerTiles.add(new JLabel());
			flowerTiles.get(i).setIcon(img);
			flowerTiles.get(i).setBounds(0,0, img.getIconWidth(), img.getIconHeight());
			MyFlowerPanel.add(flowerTiles.get(i));
			MyFlowerPanel.repaint();
			}
		}
		MyFlowerPanel.updateUI();
	}
	
	public void displayHand(ArrayList<Tile> hand) throws IOException{
		yourHand.removeAll();
		handTiles.clear();
		handMap.clear();
		for (int i = 0; i < hand.size(); i++){
			if (hand.get(i) == null){}
			else{
			BufferedImage img1 = ImageIO.read(this.getClass().getResourceAsStream(hand.get(i).getImagePath(hand.get(i).getTileID(), hand.get(i).getTileNumber())));
			img1 = resize(img1, (int) Math.round(IMAGE_WIDTH * .4), (int) Math.round(IMAGE_HEIGHT * .4));
			ImageIcon img = new ImageIcon(img1);
			handTiles.add(new JLabel());
			handTiles.get(i).setIcon(img);
			handTiles.get(i).setBounds(0,0, img.getIconWidth(), img.getIconHeight());
			lis.makeDraggable(handTiles.get(i));
			handMap.put(handTiles.get(i), hand.get(i));
			yourHand.add(handTiles.get(i));
			yourHand.repaint();
			}
		}
		yourHand.updateUI();
		lis.setMap(handMap);
		showSpecialSets(player, "you");
		yourScore.setText("Score: " + player.getScore());
	}
	
	public void reset()
	{
		bigTile.removeAll();
		bigTile.repaint();
		bigTile.updateUI();
		
		shadowRealm.removeAll();
		shadowRealm.repaint();
		shadowRealm.updateUI();
	}
	
	public void changeBigTile(Tile tile) throws IOException{
		BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream(tile.getImagePath(tile.getTileID(), tile.getTileNumber())));
		img = resize(img, (int) Math.round(IMAGE_WIDTH * 1.5), (int) Math.round(IMAGE_HEIGHT * 1.5));
		ImageIcon img1 = new ImageIcon(img);
		JLabel tilePic = new JLabel(img1);
		tilePic.setBounds(0, 0, img1.getIconWidth(), img1.getIconHeight());
		if (bigTileLastPlayed != null){
		addToShadowRealm(bigTileLastPlayed);
		}
		bigTile.removeAll();
		bigTile.add(new JLabel(new ImageIcon(img)));
		bigTileLastPlayed = tile;
		bigTile.repaint();
		bigTile.updateUI();
	}
	
	public void addToShadowRealm(Tile tile) throws IOException{
		BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream(tile.getImagePath(tile.getTileID(), tile.getTileNumber())));
		img = resize(img, (int) Math.round(IMAGE_WIDTH * .3), (int) Math.round(IMAGE_HEIGHT * .3));
		ImageIcon img1 = new ImageIcon(img);
		JLabel tilePic = new JLabel(img1);
		tilePic.setBounds(0,0, img1.getIconWidth(), img1.getIconHeight());
		shadowRealm.add(tilePic);
		shadowRealm.repaint();
		shadowRealm.updateUI();
	}
	
	public void RedisplayHand(ArrayList<JLabel> handTiles, JPanel panel){
		panel.removeAll();
		for(int i = 0; i < handTiles.size(); i++){
			panel.add(handTiles.get(i));
			panel.repaint();
		}
		panel.updateUI();
	}
	
	public void RedisplayFlowers(ArrayList<JLabel> flowers, JPanel panel){
		panel.removeAll();
		for(int i = 0; i < flowers.size(); i++){
			panel.add(flowers.get(i));
			panel.repaint();
		}
		panel.updateUI();
	}
	
	public void setTilePlayed(Tile tile)
	{
		this.played = tile;
	}
	
	public Player getPlayer() { return player; }
	public Tile getJustPlayed() { return bigTileLastPlayed; }
	public void setLastPlayedNull() { bigTileLastPlayed = null; }
	
	public Tile getTilePlayed()
	{
		if(played == null)
		{
			return null;
		}
		Tile temp = new Tile(played);
		played = null;
		return temp;
	}
	
	public void displayOtherPlayerHands(Player player, String playerPos) throws IOException{
		Icon img = new ImageIcon(tileBack);
		BufferedImage smallerBack = resize(tileBack,(int) Math.round(IMAGE_WIDTH * .25), (int) Math.round(IMAGE_HEIGHT * .25));
		Icon smallerBackIcon = new ImageIcon(smallerBack);
		if (playerPos.equals("right")){
			RotatedIcon imgRotate = new RotatedIcon(smallerBackIcon, RotatedIcon.Rotate.UP);
			playerRightHand.removeAll();
			for (int i = 0; i < player.getHand().size(); i++){
				playerRightHand.add(new JLabel(imgRotate));
			}
			//draw the flowers
			RightPlayerFlowerPanel.removeAll();
			for (int i = 0; i < player.flowers.size(); i++){
				BufferedImage img1 = ImageIO.read(this.getClass().getResourceAsStream(player.flowers.get(i).getImagePath(player.flowers.get(i).getTileID(), player.flowers.get(i).getTileNumber())));
				img1 = resize(img1, (int) Math.round(IMAGE_WIDTH * .25), (int) Math.round(IMAGE_HEIGHT * .25));
				Icon img2 = new ImageIcon(img1);
				RotatedIcon imgRotate1 = new RotatedIcon(img2, -90);
				RightPlayerFlowerPanel.add(new JLabel(imgRotate1));
			}
			RightPlayerFlowerPanel.repaint();
			RightPlayerFlowerPanel.updateUI();
			playerRightHand.repaint();
			playerRightHand.updateUI();
			showSpecialSets(player, "right");
			rightScore.setText("Score: " + player.getScore());
		}
		else if (playerPos.equals("top")){
			playerTopHand.removeAll();
			for (int i = 0; i < player.getHand().size(); i++){
				playerTopHand.add(new JLabel(img));
			}
			//draw the flowers
			TopPlayerFlowerPanel.removeAll();
			for (int i = 0; i < player.flowers.size(); i++){
				BufferedImage img1 = ImageIO.read(this.getClass().getResourceAsStream(player.flowers.get(i).getImagePath(player.flowers.get(i).getTileID(), player.flowers.get(i).getTileNumber())));
				img1 = resize(img1, (int) Math.round(IMAGE_WIDTH * .25), (int) Math.round(IMAGE_HEIGHT * .25));
				Icon img2 = new ImageIcon(img1);
				RotatedIcon imgRotate3 = new RotatedIcon(img2, RotatedIcon.Rotate.UPSIDE_DOWN);
				TopPlayerFlowerPanel.add(new JLabel(imgRotate3));
			}
			TopPlayerFlowerPanel.repaint();
			TopPlayerFlowerPanel.updateUI();
			playerTopHand.repaint();
			playerTopHand.updateUI();
			showSpecialSets(player, "top");
			topScore.setText("Score: " + player.getScore());
		}
		else if (playerPos.equals("left")){
			RotatedIcon imgRotate4 = new RotatedIcon(smallerBackIcon, RotatedIcon.Rotate.DOWN);
			playerLeftHand.removeAll();
			for (int i = 0; i < player.getHand().size(); i++){
				playerLeftHand.add(new JLabel(imgRotate4));
			}
			LeftPlayerFlowerPanel.removeAll();
			//draw the flowers
			for (int i = 0; i < player.flowers.size(); i++){
				// LeftPlayerFlowerPanel.removeAll(); <-------------- WOW
				BufferedImage img1 = ImageIO.read(this.getClass().getResourceAsStream(player.flowers.get(i).getImagePath(player.flowers.get(i).getTileID(), player.flowers.get(i).getTileNumber())));
				img1 = resize(img1, (int) Math.round(IMAGE_WIDTH * .25), (int) Math.round(IMAGE_HEIGHT * .25));
				Icon img2 = new ImageIcon(img1);
				RotatedIcon imgRotate5 = new RotatedIcon(img2, RotatedIcon.Rotate.DOWN);
				LeftPlayerFlowerPanel.add(new JLabel(imgRotate5));		
			}
			LeftPlayerFlowerPanel.repaint();
			LeftPlayerFlowerPanel.updateUI();
			playerLeftHand.repaint();
			playerLeftHand.updateUI();
			showSpecialSets(player, "left");
			leftScore.setText("Score: " + player.getScore());
		}
	}
	
	public void showSpecialSets(Player player, String pos) throws IOException{
		JPanel set1 = null, set2 = null, set3 = null, set4 = null;
		if (pos.equals("left")){
			set1 = matchLeft1;
			set2 = matchLeft2;
			set3 = matchLeft3;
			set4 = matchLeft4;
		}
		else if (pos.equals("right")){
			set1 = matchRight1;
			set2 = matchRight2;
			set3 = matchRight3;
			set4 = matchRight4;
		}
		else if (pos.equals("top")){
			set1 = matchTop1;
			set2 = matchTop2;
			set3 = matchTop3;
			set4 = matchTop4;
		}
		else if (pos.equals("you")){
			set1 = matchPanelYou1;
			set2 = matchPanelYou2;
			set3 = matchPanelYou3;
			set4 = matchPanelYou4;
		}
		if (player.getSpecial1() != null){
			
			ArrayList<Tile>temp = player.getSpecial1();
			set1.removeAll();
			for(int i = 0; i < player.getSpecial1().size(); i++){
				BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream(temp.get(i).getImagePath(temp.get(i).getTileID(), temp.get(i).getTileNumber())));
				img = resize(img, (int) Math.round(IMAGE_WIDTH * .25), (int) Math.round(IMAGE_HEIGHT * .25));
				Icon tileimg = new ImageIcon(img);
				RotatedIcon rotated;
				if (pos.equals("left")){
					rotated = new RotatedIcon(tileimg, RotatedIcon.Rotate.DOWN);
				}
				else if (pos.equals("top")){
					rotated = new RotatedIcon(tileimg, RotatedIcon.Rotate.UPSIDE_DOWN);
				}
				else if (pos.equals("right")){
					rotated = new RotatedIcon(tileimg, RotatedIcon.Rotate.UP);
				}
				else{
					rotated = new RotatedIcon(tileimg, 0);
				}
				set1.add(new JLabel(rotated));
			}
			set1.repaint();
			set1.updateUI();
		}
		if (player.getSpecial2() != null){
			ArrayList<Tile>temp = player.getSpecial2();
			set2.removeAll();
			for(int i = 0; i < player.getSpecial2().size(); i++){
				BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream(temp.get(i).getImagePath(temp.get(i).getTileID(), temp.get(i).getTileNumber())));
				img = resize(img, (int) Math.round(IMAGE_WIDTH * .25), (int) Math.round(IMAGE_HEIGHT * .25));
				ImageIcon tileimg = new ImageIcon(img);
				RotatedIcon rotated;
				if (pos.equals("left")){
					rotated = new RotatedIcon(tileimg, RotatedIcon.Rotate.DOWN);
				}
				else if (pos.equals("top")){
					rotated = new RotatedIcon(tileimg, RotatedIcon.Rotate.UPSIDE_DOWN);
				}
				else if (pos.equals("right")){
					rotated = new RotatedIcon(tileimg, RotatedIcon.Rotate.UP);
				}
				else{
					rotated = new RotatedIcon(tileimg, 0);
				}
				set2.add(new JLabel(rotated));
			}
			set2.repaint();
			set2.updateUI();
		}
		if (player.getSpecial3() != null){
			ArrayList<Tile>temp = player.getSpecial3();
			set3.removeAll();
			for(int i = 0; i < player.getSpecial3().size(); i++){
				BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream(temp.get(i).getImagePath(temp.get(i).getTileID(), temp.get(i).getTileNumber())));
				img = resize(img, (int) Math.round(IMAGE_WIDTH * .25), (int) Math.round(IMAGE_HEIGHT * .25));
				ImageIcon tileimg = new ImageIcon(img);
				RotatedIcon rotated;
				if (pos.equals("left")){
					rotated = new RotatedIcon(tileimg, RotatedIcon.Rotate.DOWN);
				}
				else if (pos.equals("top")){
					rotated = new RotatedIcon(tileimg, RotatedIcon.Rotate.UPSIDE_DOWN);
				}
				else if (pos.equals("right")){
					rotated = new RotatedIcon(tileimg, RotatedIcon.Rotate.UP);
				}
				else{
					rotated = new RotatedIcon(tileimg, 0);
				}
				set3.add(new JLabel(rotated));				
			}
			set3.repaint();
			set3.updateUI();
		}
		if (player.getSpecial4() != null){
			ArrayList<Tile>temp = player.getSpecial4();
			set4.removeAll();
			for(int i = 0; i < player.getSpecial4().size(); i++){
				BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream(temp.get(i).getImagePath(temp.get(i).getTileID(), temp.get(i).getTileNumber())));
				img = resize(img, (int) Math.round(IMAGE_WIDTH * .25), (int) Math.round(IMAGE_HEIGHT * .25));
				ImageIcon tileimg = new ImageIcon(img);
				RotatedIcon rotated;
				if (pos.equals("left")){
					rotated = new RotatedIcon(tileimg, RotatedIcon.Rotate.DOWN);
				}
				else if (pos.equals("top")){
					rotated = new RotatedIcon(tileimg, RotatedIcon.Rotate.UPSIDE_DOWN);
				}
				else if (pos.equals("right")){
					rotated = new RotatedIcon(tileimg, RotatedIcon.Rotate.UP);
				}
				else{
					rotated = new RotatedIcon(tileimg, 0);
				}
				set4.add(new JLabel(rotated));
			}
		}
		set4.repaint();
		set4.updateUI();
	}

}
