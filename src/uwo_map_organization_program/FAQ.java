package uwo_map_organization_program;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FAQ extends JFrame {

	private JPanel faq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FAQ frame = new FAQ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FAQ() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 728);
		faq = new JPanel();
		faq.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(faq);
		setTitle("FAQ");
		faq.setLayout(null);

		
		JLabel lblFaq = new JLabel("FAQ");
		lblFaq.setBounds(422, 6, 182, 140);
		lblFaq.setHorizontalAlignment(SwingConstants.CENTER);
		faq.add(lblFaq);
		
		JButton btnHome = new JButton("Back");
		btnHome.setBounds(6, 6, 117, 29);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				faq.setVisible(false);
				dispose();
				buildingMap.main(null);
			}
		});
		faq.add(btnHome);
		
		JLabel lblWhatIs = new JLabel("1. What is the favorite button for?");
		lblWhatIs.setBounds(398, 193, 244, 16);
		lblWhatIs.setHorizontalAlignment(SwingConstants.CENTER);
		faq.add(lblWhatIs);
		
		JLabel lblPeopleCanAdd = new JLabel("People can add rooms to the favorite list and next time they will access to its information easily.");
		lblPeopleCanAdd.setBounds(191, 208, 712, 39);
		lblPeopleCanAdd.setHorizontalAlignment(SwingConstants.CENTER);
		faq.add(lblPeopleCanAdd);
		
		JLabel lblHowTo = new JLabel("2. How to select the building, floor and room?");
		lblHowTo.setBounds(414, 249, 308, 16);
		faq.add(lblHowTo);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(901, 671, 117, 29);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		faq.add(btnExit);
		
		JButton btnBackToEdit = new JButton("Back to Edit");
		btnBackToEdit.setBounds(117, 6, 117, 29);
		btnBackToEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				editBuilding.main(null);
			}
		});
		faq.add(btnBackToEdit);
		
		JLabel lblUserCanClick = new JLabel("User can click the combo box which shows the building name, floor number and room number,");
		lblUserCanClick.setBounds(243, 277, 619, 16);
		faq.add(lblUserCanClick);
		
		JLabel lblItCanBe = new JLabel("it can be select by the list that pop up.");
		lblItCanBe.setBounds(414, 305, 389, 16);
		faq.add(lblItCanBe);
		
		JLabel lblWhatIs_1 = new JLabel("3. What is the reload button for?");
		lblWhatIs_1.setBounds(414, 344, 308, 16);
		faq.add(lblWhatIs_1);
		
		JLabel lblNewLabel = new JLabel("User can click the reload button if the program get stuck, it will also be used in the editing mod.");
		lblNewLabel.setBounds(243, 368, 619, 16);
		faq.add(lblNewLabel);
		
		JLabel lblHowTo_1 = new JLabel("4. How to display the point of interest function?");
		lblHowTo_1.setBounds(414, 396, 376, 16);
		faq.add(lblHowTo_1);
		
		JLabel lblAfterChoosingThe = new JLabel("After choosing the building and floor, the user can simply click the ");
		lblAfterChoosingThe.setBounds(342, 416, 619, 16);
		faq.add(lblAfterChoosingThe);
		
		JLabel lblPointOfInterest = new JLabel("point of interest button to display the layer.");
		lblPointOfInterest.setBounds(407, 444, 529, 16);
		faq.add(lblPointOfInterest);
	}
}
