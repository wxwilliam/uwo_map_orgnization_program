import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import java.awt.List;
import java.awt.Color;
import java.awt.ScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class UWOUniverMap {

	private JFrame frmUwomapsearchsystemStart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UWOUniverMap window = new UWOUniverMap();
					window.frmUwomapsearchsystemStart.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UWOUniverMap() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUwomapsearchsystemStart = new JFrame();
		frmUwomapsearchsystemStart.setTitle("UWOMapSearchSystem - Start");
		frmUwomapsearchsystemStart.setBounds(100, 100, 1024, 728);
		frmUwomapsearchsystemStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUwomapsearchsystemStart.getContentPane().setLayout(null);
		
		JLabel lblMapOfUniversity = new JLabel("Map of University of Western Ontario");
		lblMapOfUniversity.setBounds(283, 44, 474, 67);
		lblMapOfUniversity.setHorizontalAlignment(SwingConstants.CENTER);
		frmUwomapsearchsystemStart.getContentPane().add(lblMapOfUniversity);
		
		JLabel lblChooseABuilding = new JLabel("Press the Start button to use the program");
		lblChooseABuilding.setBounds(363, 154, 301, 16);
		lblChooseABuilding.setHorizontalAlignment(SwingConstants.CENTER);
		frmUwomapsearchsystemStart.getContentPane().add(lblChooseABuilding);
		
		
		JButton middlesexButton = new JButton("Start");
		middlesexButton.setForeground(Color.BLACK);
		middlesexButton.setBounds(429, 182, 170, 67);
		middlesexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUwomapsearchsystemStart.dispose();
				buildingMap.main(null);
			}
		});
		frmUwomapsearchsystemStart.getContentPane().add(middlesexButton);
		
		
		JButton btnFaq = new JButton("FAQ");
		btnFaq.setBounds(459, 588, 117, 29);
		btnFaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUwomapsearchsystemStart.dispose();
				FAQ faq = new FAQ();
				faq.setVisible(true);
			}
		});
		frmUwomapsearchsystemStart.getContentPane().add(btnFaq);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		btnExit.setBounds(901, 671, 117, 29);
		frmUwomapsearchsystemStart.getContentPane().add(btnExit);
		
		JLabel lblLocopic = new JLabel("");
		lblLocopic.setIcon(new ImageIcon(UWOUniverMap.class.getResource("/img/logo.png")));
		lblLocopic.setBounds(352, 261, 334, 315);
		frmUwomapsearchsystemStart.getContentPane().add(lblLocopic);
	}
}
