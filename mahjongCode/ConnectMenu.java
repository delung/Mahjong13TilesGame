package mahjongCode;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;

@SuppressWarnings("serial")
public class ConnectMenu extends JFrame
{
	private String host;
	private int port;
	private String username;
	private String serverName;
	private boolean connected = false;
	private DefaultListModel<String> listOfServers = new DefaultListModel<String>();
	
	private JPanel contentPane;
	
	private JButton btnConnect;
	private JButton btnCancel;
	private JButton btnSave;
	
	private JTextField usernameText;
	private JTextField hostText;
	private JTextField portText;
	private JTextField serverNameText;
	
	private JLabel usernamelbl;
	private JLabel hostlbl;
	private JLabel portlbl;
	private JLabel serverNamelbl;
	
	private JList<String> listServer;
	private JLabel serverlbl;
	
	private WriteAndRead writeAndRead;
	private JLabel usernameWarning;
	private JLabel hostWarning;
	private JLabel portWarning;
	private JLabel serverNameWarning;
	private JButton btnDelete;
	
	
	public ConnectMenu()
	{
		setTitle("Mahjong v1.00");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		
		writeAndRead = new WriteAndRead();
		writeAndRead.read();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameText = new JTextField();
		usernameText.setBounds(20, 50, 325, 25);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		usernamelbl = new JLabel("Username:");
		usernamelbl.setBounds(20, 20, 110, 25);
		contentPane.add(usernamelbl);
		
		hostText = new JTextField();
		hostText.setBounds(20, 120, 200, 25);
		contentPane.add(hostText);
		hostText.setColumns(10);
		
		hostlbl = new JLabel("Host IP:");
		hostlbl.setBounds(20, 90, 56, 16);
		contentPane.add(hostlbl);
		
		portlbl = new JLabel("Port:");
		portlbl.setBounds(246, 90, 56, 16);
		contentPane.add(portlbl);
		
		portText = new JTextField();
		portText.setBounds(246, 120, 100, 22);
		contentPane.add(portText);
		portText.setColumns(10);
		
		listServer = new JList<String>(listOfServers);
		listServer.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!listServer.isSelectionEmpty())
				{
					String[] info = listServer.getSelectedValue().split("/");
					serverNameText.setText(info[0]);
					hostText.setText(info[1]);
					portText.setText(info[2]);
				}
				
			}
		});
		listServer.setBounds(20, 250, 330, 100);
		contentPane.add(listServer);
	
		serverNameText = new JTextField();
		serverNameText.setBounds(20, 190, 200, 22);
		contentPane.add(serverNameText);
		serverNameText.setColumns(10);
		
		serverNamelbl = new JLabel("Server Name");
		serverNamelbl.setBounds(20, 160, 81, 16);
		contentPane.add(serverNamelbl);
		
		serverlbl = new JLabel("Saved:");
		serverlbl.setBounds(20, 225, 56, 16);
		contentPane.add(serverlbl);
		
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!usernameText.getText().isEmpty())
				{
					username = usernameText.getText();
					usernameWarning.setVisible(false);
				}
				else usernameWarning.setVisible(true);
				if(!hostText.getText().isEmpty())
				{
					host = hostText.getText();
					hostWarning.setVisible(false);
				}
				else hostWarning.setVisible(true);
			
				if(!portText.getText().isEmpty())
				{
					port = Integer.parseInt(portText.getText());
					portWarning.setVisible(false);
				}
				else portWarning.setVisible(true);
				
				if(!portText.getText().isEmpty() && !usernameText.getText().isEmpty() && !hostText.getText().isEmpty())
				{
					connected = true;
				}
			}
		});
		btnConnect.setBounds(370,50,100,25);
		contentPane.add(btnConnect);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(370, 85, 100, 25);
		contentPane.add(btnCancel);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listServer.clearSelection();
				if(!serverNameText.getText().isEmpty())
				{
					serverName = serverNameText.getText();
					serverNameWarning.setVisible(false);
				}
				else serverNameWarning.setVisible(true);
				if(!hostText.getText().isEmpty())
				{
					host = hostText.getText();
					hostWarning.setVisible(false);
				}
				else hostWarning.setVisible(true);
			
				if(!portText.getText().isEmpty())
				{
					port = Integer.parseInt(portText.getText());
					portWarning.setVisible(false);
				}
				else portWarning.setVisible(true);
				if(!portText.getText().isEmpty() && !serverNameText.getText().isEmpty() && !hostText.getText().isEmpty())
				{
					writeAndRead.write(serverName, host, port);
					writeAndRead.read();
					listServer.setModel(listOfServers);
				}
			}
		});
		btnSave.setBounds(370, 250, 100, 25);
		contentPane.add(btnSave);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!listServer.isSelectionEmpty())
				{
					writeAndRead.remove(listServer.getSelectedValue());
					writeAndRead.read();
					listServer.setModel(listOfServers);
				}
				listServer.clearSelection();
			}
		});
		btnDelete.setBounds(370, 285, 100, 25);
		contentPane.add(btnDelete);
		
		usernameWarning = new JLabel("Username required dipshit!");
		usernameWarning.setForeground(Color.RED);
		usernameWarning.setBounds(95, 20, 182, 25);
		contentPane.add(usernameWarning);
		usernameWarning.setVisible(false);
		
		hostWarning = new JLabel("No IP faggot!");
		hostWarning.setForeground(Color.RED);
		hostWarning.setBounds(74, 90, 110, 16);
		contentPane.add(hostWarning);
		hostWarning.setVisible(false);
		
		portWarning = new JLabel("Idiot.");
		portWarning.setForeground(Color.RED);
		portWarning.setBounds(280, 90, 56, 16);
		contentPane.add(portWarning);
		portWarning.setVisible(false);
		
		serverNameWarning = new JLabel("C'mon, step it up.");
		serverNameWarning.setForeground(Color.RED);
		serverNameWarning.setBounds(113, 160, 150, 16);
		contentPane.add(serverNameWarning);
		serverNameWarning.setVisible(false);
		
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
				validate();
				setResizable(false);
				setVisible(true);
			}
				
		});
	}
	public String getUser() { return username; }
	public String getServer() { return serverName; }
	public String getHost() { return host; }
	public int getPort() { return port; }
	public boolean getConnect() { return connected; }
	
	private class WriteAndRead {
		
		private FileWriter fileWriter;
		private FileReader fileReader;
		private BufferedWriter bufferedWriter;
		private BufferedReader bufferedReader;
		
		public WriteAndRead()
		{
			try {
				fileWriter = new FileWriter("config.txt", true);
				fileReader = new FileReader("config.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void write(String name, String host, int port)
		{
			try 
			{
				fileWriter = new FileWriter("config.txt", true);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(name + "/");
				bufferedWriter.write(host + "/");
				bufferedWriter.write(port + "\n");
				
				bufferedWriter.flush();
				fileWriter.flush();
				bufferedWriter.close();
				fileWriter.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try
				{
					bufferedWriter.close();
					fileWriter.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void read() 
		{
			try 
			{
				fileReader = new FileReader("config.txt");
				bufferedReader = new BufferedReader(fileReader);
				String line = bufferedReader.readLine();
				listOfServers.clear();
				while (line != null) 
				{
					listOfServers.addElement(line);
					line = bufferedReader.readLine();
				}
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try
				{
					bufferedReader.close();
					fileReader.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void remove(String str) {

			try {
				File original = new File("config.txt");
				System.out.println(original.getAbsolutePath());
				File temp = new File(original.getAbsolutePath()+"Temp");
				
				fileReader = new FileReader(original);
				bufferedReader = new BufferedReader(fileReader);

				fileWriter = new FileWriter(temp);
				bufferedWriter = new BufferedWriter(fileWriter);
				
				
				String line = bufferedReader.readLine();
				System.out.println(line);
				System.out.println(str);
				while (line != null) 
				{
					if(!line.equals(str)) bufferedWriter.write(line + "\n");
					line = bufferedReader.readLine();
				}
				bufferedWriter.flush();
				fileWriter.flush();
				
				bufferedReader.close();
				bufferedWriter.close();
				
				fileReader.close();
				fileWriter.close();

				System.gc();
				
				
						
				if(!original.delete()) {
					System.out.println("Could not delete file");
			        return;
				}
				if(!temp.renameTo(original)) {
					System.out.println("Could not rename file");
			        return;
				}
	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}
	/**
	public static void main(String[] args)
	{
		ConnectMenu menu = new ConnectMenu();
		menu.initialize();
	}
	**/
}
