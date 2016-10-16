package mahjongCode;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")

public class OptionSelector extends JFrame
{

	public static final int IMAGE_HEIGHT = 160;
	public static final int IMAGE_WIDTH = 100;
	private JPanel contentPane, panel, panel_1, panel_2;
	private ArrayList<Tile> temp1, temp2, temp3;
	private boolean picking = true;
	public JRadioButton firstChoice, secondChoice, thirdChoice;

	public void initialize()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					setResizable(false);
					setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public OptionSelector()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(657, 455);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		firstChoice = new JRadioButton("");
		firstChoice.setBounds(10, 75, 20, 23);
		firstChoice.setVisible(false);
		contentPane.add(firstChoice);

		secondChoice = new JRadioButton("");
		secondChoice.setBounds(10, 175, 20, 23);
		secondChoice.setVisible(false);
		contentPane.add(secondChoice);

		thirdChoice = new JRadioButton("");
		thirdChoice.setBounds(10, 275, 20, 23);
		thirdChoice.setVisible(false);
		contentPane.add(thirdChoice);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(firstChoice);
		buttonGroup.add(secondChoice);
		buttonGroup.add(thirdChoice);

		panel = new JPanel();
		panel.setBounds(70, 50, 550, 78);
		contentPane.add(panel);

		panel_1 = new JPanel();
		panel_1.setBounds(70, 150, 550, 78);
		contentPane.add(panel_1);

		panel_2 = new JPanel();
		panel_2.setBounds(70, 250, 550, 78);
		contentPane.add(panel_2);

		JButton btnUseThisChoice = new JButton("Ok");
		btnUseThisChoice.setBounds(408, 371, 100, 32);
		btnUseThisChoice.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (firstChoice.isSelected() || secondChoice.isSelected() || thirdChoice.isSelected())
				{
					picking = false;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You must select an option first.");
				}
			}
		});
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(520, 371, 100, 32);
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				picking = false;
				buttonGroup.clearSelection();
			}
		});
		contentPane.add(btnUseThisChoice);
		contentPane.add(btnCancel);
	}

	public void setPanelContents(int frameNum, ArrayList<Tile> tiles) throws IOException
	{
		if (frameNum == 1 && !tiles.isEmpty())
		{
			temp1 = tiles;
			firstChoice.setVisible(true);
			panel.removeAll();
			for (int i = 0; i < tiles.size(); i++)
			{
				BufferedImage img1 = ImageIO.read(this.getClass().getResourceAsStream(
						tiles.get(i).getImagePath(tiles.get(i).getTileID(), tiles.get(i).getTileNumber())));
				img1 = resize(img1, (int) Math.round(IMAGE_WIDTH * .3), (int) Math.round(IMAGE_HEIGHT * .3));
				Icon img2 = new ImageIcon(img1);
				JLabel lbl = new JLabel(img2);
				lbl.setBounds(0, 0, lbl.getWidth(), lbl.getHeight());
				panel.add(lbl);
			}
			panel.repaint();
			panel.updateUI();
		}
		else if (frameNum == 2 && !tiles.isEmpty())
		{
			temp2 = tiles;
			secondChoice.setVisible(true);
			panel_1.removeAll();
			for (int i = 0; i < tiles.size(); i++)
			{
				BufferedImage img1 = ImageIO.read(this.getClass().getResourceAsStream(
						tiles.get(i).getImagePath(tiles.get(i).getTileID(), tiles.get(i).getTileNumber())));
				img1 = resize(img1, (int) Math.round(IMAGE_WIDTH * .3), (int) Math.round(IMAGE_HEIGHT * .3));
				Icon img2 = new ImageIcon(img1);
				JLabel lbl = new JLabel(img2);
				lbl.setBounds(0, 0, lbl.getWidth(), lbl.getHeight());
				panel_1.add(lbl);
			}
			panel_1.repaint();
			panel_1.updateUI();
		}
		else if (frameNum == 3 && !tiles.isEmpty())
		{
			temp3 = tiles;
			thirdChoice.setVisible(true);
			panel_2.removeAll();
			for (int i = 0; i < tiles.size(); i++)
			{
				BufferedImage img1 = ImageIO.read(this.getClass().getResourceAsStream(
						tiles.get(i).getImagePath(tiles.get(i).getTileID(), tiles.get(i).getTileNumber())));
				img1 = resize(img1, (int) Math.round(IMAGE_WIDTH * .3), (int) Math.round(IMAGE_HEIGHT * .3));
				Icon img2 = new ImageIcon(img1);
				JLabel lbl = new JLabel(img2);
				lbl.setBounds(0, 0, lbl.getWidth(), lbl.getHeight());
				panel_2.add(lbl);
			}
			panel_2.repaint();
			panel_2.updateUI();
		}
	}

	public boolean getPicking()
	{
		return picking;
	}

	public ArrayList<Tile> getChosenList()
	{
		if (firstChoice.isSelected())
		{
			return temp1;
		}
		else if (secondChoice.isSelected())
		{
			return temp2;
		}
		else if (thirdChoice.isSelected())
		{
			return temp3;
		}
		else
		{
			return null;
		}

	}

	public static BufferedImage resize(BufferedImage image, int width, int height)
	{
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

}
