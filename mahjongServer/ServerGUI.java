package mahjongServer;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledDocument;

public class ServerGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean started = false;
	private JPanel contentPane;
	private JButton btnStartServer;
	public JButton btnStopServer;
	private JTextPane debugTextPane;
	private JTextPane usersPresentTextPane;
	private JSplitPane infoPane;
	public JScrollPane debugPane, usersPresent;
	/**
	 * Create the frame.
	 */
	public ServerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		infoPane = new JSplitPane();
		infoPane.setBounds(10, 65, 414, 186);
		infoPane.setDividerLocation((int)(.70 * infoPane.getWidth()) );
		infoPane.setEnabled(false);
		contentPane.add(infoPane);
		
		debugTextPane = new JTextPane();
		debugPane = new JScrollPane(debugTextPane);
		debugTextPane.setEditable(false);
		infoPane.setLeftComponent(debugPane);

		
		usersPresentTextPane = new JTextPane();
		usersPresent = new JScrollPane(usersPresentTextPane);
		usersPresentTextPane.setBackground(SystemColor.menu);
		usersPresentTextPane.setEditable(false);
		infoPane.setRightComponent(usersPresent);

		
		usersPresentTextPane.setText("Users present:");
		
		btnStartServer = new JButton("Start server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				started = true;
			}
		});
		btnStartServer.setBounds(10, 31, 115, 23);
		contentPane.add(btnStartServer);
		
		btnStopServer = new JButton("Stop server");
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(ObjectOutputStream s:Server.ConnectionArray)
				{
					try {
						s.flush();
						s.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				dispose();
				exit();
			}
		});
		btnStopServer.setBounds(135, 31, 115, 23);
		contentPane.add(btnStopServer);
	}
	
	public void initialize()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				setTitle("Server manager");
				setResizable(false);
				setVisible(true);
			}
				
		});

	}
	public void addText(JTextPane textPane, String string){
	StyledDocument doc = textPane.getStyledDocument();
	//  Add some text

	try
	{
	    doc.insertString(doc.getLength(), "\n" + string, null );
	}
	catch(Exception e) { System.out.println(e); }
	}
	
	
	public JTextPane getUsersPane() {
		return usersPresentTextPane;
	}

	public JTextPane getDebugPane() {
		return debugTextPane;
	}
	public void clearText(JTextPane textPane)
	{
		textPane.setText(null);
	}
	
	public void exit(){
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}
