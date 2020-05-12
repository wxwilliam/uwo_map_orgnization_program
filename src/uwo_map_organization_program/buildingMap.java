package uwo_map_organization_program;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.ScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class buildingMap extends JFrame {

	private JPanel buildingmap;
	public String room;
	public String buildName = "Middlesex";
	public String floorNum = "1";
	public String roomNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					buildingMap frame = new buildingMap();
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
	public buildingMap() {
		StartUp str = new StartUp();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 728);
		buildingmap = new JPanel();
		buildingmap.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(buildingmap);
		setTitle("UWOMapSearchSystem - Search");
		buildingmap.setLayout(null);
		int iconx = 15;
		int icony = 32;
		JLabel roomPin = new JLabel();
		
		JLabel floorPic = new JLabel();
		floorPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int locationx = e.getX();
				int locationy = e.getY();
				System.out.println("location X:"+ locationx);
				System.out.println("location Y:"+ locationy);
			}
		});
		floorPic.setIcon(new ImageIcon(buildingMap.class.getResource("/Map/MC_Map/MC_G.png")));
		JScrollPane scrollPane = new JScrollPane(floorPic);
		scrollPane.setBounds(304, 75, 714, 481);
		buildingmap.add(scrollPane);
		
		JLabel lblSearch = new JLabel("Searchroom:");
		lblSearch.setBounds(23, 47, 117, 16);
		buildingmap.add(lblSearch);
//------------------------------------------------------------------------------------------------------------		
		JLabel lblPointOfInterest = new JLabel("Point of Interests:");
		lblPointOfInterest.setBounds(23, 194, 134, 16);
		buildingmap.add(lblPointOfInterest);
		
		JButton poiElevator = new JButton("Elevator");
		List<JLabel> elevlabelList = new ArrayList<JLabel>();
		poiElevator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomPin.setIcon(null);
				int poix;
				int poiy;
				Building buildpoi = str.search_building(buildName);
				floor floorpoi = buildpoi.search_floor(floorNum);
				PointsOfInterest poi = floorpoi.get_poi();
				List<Elevator> elevator = poi.get_elevator();
				String maploc = "/img/pin.png";
				
				
				for (int i = 0 ; i<elevator.size(); i++) {
					JLabel poipin = new JLabel();
					elevlabelList.add(poipin);
				}
				for (int i = 0 ; i<elevator.size(); i++) {
					poix = elevator.get(i).get_x();
					poiy = elevator.get(i).get_y();
					elevlabelList.get(i).setIcon(new ImageIcon(buildingMap.class.getResource(maploc)));
					elevlabelList.get(i).setBounds(poix-iconx, poiy-icony, 32, 32);
					floorPic.add(elevlabelList.get(i));
				}
			}
		});
		poiElevator.setBounds(33, 222, 117, 29);
		buildingmap.add(poiElevator);
		
		JButton poiWashroom = new JButton("Washroom");
		poiWashroom.setBounds(33, 262, 117, 29);
		List<JLabel> wrlabelList = new ArrayList<JLabel>();
		poiWashroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomPin.setIcon(null);
				int poix;
				int poiy;
				Building buildpoi = str.search_building(buildName);
				floor floorpoi = buildpoi.search_floor(floorNum);
				PointsOfInterest poi = floorpoi.get_poi();
				List<Washroom> washroom = poi.get_washroom();
				String maploc = "/img/pin.png";

				for (int i = 0 ; i<washroom.size(); i++) {
					JLabel poipin = new JLabel();
					wrlabelList.add(poipin);
				}
				for (int i = 0 ; i<washroom.size(); i++) {
					poix = washroom.get(i).get_x();
					poiy = washroom.get(i).get_y();
					wrlabelList.get(i).setIcon(new ImageIcon(buildingMap.class.getResource(maploc)));
					wrlabelList.get(i).setBounds(poix-iconx, poiy-icony, 32, 32);
					floorPic.add(wrlabelList.get(i));
				}
			}
		});
		buildingmap.add(poiWashroom);
		
		JButton poiEatery = new JButton("Eatery");
		List<JLabel> eatlabelList = new ArrayList<JLabel>();
		poiEatery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomPin.setIcon(null);
				int poix;
				int poiy;
				Building buildpoi = str.search_building(buildName);
				floor floorpoi = buildpoi.search_floor(floorNum);
				PointsOfInterest poi = floorpoi.get_poi();
				List<Eatery> eatery = poi.get_eateries();
				String maploc = "/img/pin.png";

				for (int i = 0 ; i<eatery.size(); i++) {
					JLabel poipin = new JLabel();
					eatlabelList.add(poipin);
				}
				for (int i = 0 ; i<eatery.size(); i++) {
					poix = eatery.get(i).get_x();
					poiy = eatery.get(i).get_y();
					eatlabelList.get(i).setIcon(new ImageIcon(buildingMap.class.getResource(maploc)));
					eatlabelList.get(i).setBounds(poix-iconx, poiy-icony, 32, 32);
					floorPic.add(eatlabelList.get(i));
				}
			}
		});
		poiEatery.setBounds(33, 302, 117, 29);
		buildingmap.add(poiEatery);
		
		JButton btnClassroom = new JButton("Classroom");
		List<JLabel> classlabelList = new ArrayList<JLabel>();
		btnClassroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomPin.setIcon(null);
				int poix;
				int poiy;
				Building buildpoi = str.search_building(buildName);
				floor floorpoi = buildpoi.search_floor(floorNum);
				PointsOfInterest poi = floorpoi.get_poi();
				List<Classroom> classroom = poi.get_classrooms();
				String maploc = "/img/pin.png";

				for (int i = 0 ; i<classroom.size(); i++) {
					JLabel poipin = new JLabel();
					classlabelList.add(poipin);
				}
				for (int i = 0 ; i<classroom.size(); i++) {
					poix = classroom.get(i).get_x();
					poiy = classroom.get(i).get_y();
					classlabelList.get(i).setIcon(new ImageIcon(buildingMap.class.getResource(maploc)));
					classlabelList.get(i).setBounds(poix-iconx, poiy-icony, 32, 32);
					floorPic.add(classlabelList.get(i));
				}
			}
		});
		btnClassroom.setBounds(33, 341, 117, 29);
		buildingmap.add(btnClassroom);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0 ; i<elevlabelList.size(); i++) {
					elevlabelList.get(i).setIcon(null);
				}
				for (int i = 0 ; i<wrlabelList.size(); i++) {
					wrlabelList.get(i).setIcon(null);
				}
				for (int i = 0 ; i<eatlabelList.size(); i++) {
					eatlabelList.get(i).setIcon(null);
				}
				for (int i = 0 ; i<classlabelList.size(); i++) {
					classlabelList.get(i).setIcon(null);
				}
			}
		});
		btnClear.setBounds(33, 380, 117, 29);
		buildingmap.add(btnClear);
//------------------------------------------------------------------------------------------------------------		

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		btnExit.setBounds(901, 643, 117, 29);
		buildingmap.add(btnExit);
		
		JButton btnFaq = new JButton("FAQ");
		btnFaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FAQ.main(null);
			}
		});
		btnFaq.setBounds(787, 643, 117, 29);
		buildingmap.add(btnFaq);
//------------------------------------------------------------------------------------------------------------		
		
		JButton btnRefreshPin = new JButton("Refresh Pin");
		btnRefreshPin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Building buildpin = str.search_building(buildName);
				floor floorpin = buildpin.search_floor(floorNum);
				Room roompin = floorpin.search(roomNum);
				int roomx = roompin.get_x();
				int roomy = roompin.get_y();
				String maploc = "/img/pin.png";
				roomPin.setIcon(new ImageIcon(buildingMap.class.getResource(maploc)));
				roomPin.setBounds(roomx-iconx, roomy-icony, 32, 32);
				floorPic.add(roomPin);
			}
		});
//------------------------------------------------------------------------------------------------------------		
		
		btnRefreshPin.setBounds(415, 42, 117, 29);
		buildingmap.add(btnRefreshPin);
		
		JLabel lblSelectTheBuilding = new JLabel("Select the Building:");
		lblSelectTheBuilding.setBounds(33, 75, 124, 16);
		buildingmap.add(lblSelectTheBuilding);
		
		String[] buildingList = str.get_building_list();
		JComboBox buildingBox = new JComboBox(buildingList);
		buildingBox.setBounds(169, 71, 134, 27);
		
		Building build = str.search_building(buildName);
		String[] floorList = build.get_floor_num_list();
		JComboBox floorBox = new JComboBox(floorList);
		floorBox.setBounds(169, 110, 134, 27);
		
		floor floor_num = build.search_floor(floorNum);
		String[] roomList = floor_num.get_room_num_list();
		JComboBox roomBox = new JComboBox(roomList);
		roomBox.setBounds(169, 149, 134, 27);
		
		buildingBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel floorMod = (DefaultComboBoxModel)floorBox.getModel();
				Building buildpic = str.search_building(buildName);
				floor floorpic = buildpic.search_floor(floorNum);
				String picloca = floorpic.get_img();
				floorPic.setIcon(new ImageIcon(buildingMap.class.getResource(picloca)));
				buildingmap.add(scrollPane);
				floorMod.removeAllElements();
				JComboBox cb = (JComboBox)e.getSource();
				String buildingName = (String)cb.getSelectedItem();
				buildName = buildingName;
			}
		});
		
		floorBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel roomModel = (DefaultComboBoxModel)roomBox.getModel();
				roomModel.removeAllElements();
				JComboBox cb = (JComboBox)e.getSource();
				String floor = (String)cb.getSelectedItem();
				floorNum = floor;
			}
		});
		
		roomBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String room_Num = (String)cb.getSelectedItem();
				roomNum = room_Num;	
				roomPin.setIcon(null);
			}
		});
		
		JButton btnYes = new JButton("Submit");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building build = str.search_building(buildName);
				String[] floorList = build.get_floor_num_list();
				for (int i=0; i < floorList.length;i++) {
					floorBox.addItem(floorList[i]);
				}
				floor floor_num = build.search_floor(floorNum);
				String[] roomList = floor_num.get_room_num_list();
				for (int i=0; i < roomList.length;i++) {
					roomBox.addItem(roomList[i]);
				}
			}
		});
		btnYes.setBounds(186, 42, 117, 29);
		buildingmap.add(btnYes);
		
		buildingmap.add(buildingBox);
		JLabel lblSelectTheFloor = new JLabel("Select the Floor:");
		lblSelectTheFloor.setBounds(33, 114, 109, 16);
		buildingmap.add(lblSelectTheFloor);
		buildingmap.add(floorBox);
		JLabel lblSelectTheRoom = new JLabel("Select the Room:");
		lblSelectTheRoom.setBounds(33, 155, 107, 16);
		buildingmap.add(lblSelectTheRoom);
		buildingmap.add(roomBox);
//------------------------------------------------------------------------------------------------------------				
		JLabel lblFavorite = new JLabel("Favorite：");
		lblFavorite.setBounds(23, 424, 80, 16);
		buildingmap.add(lblFavorite);
		favorites fav = str.get_fav();
		
		JComboBox favoriteBox = new JComboBox(fav.get_fav_list());
		favoriteBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomPin.setIcon(null);
				int favposx;
				int favposy;
				JComboBox cb = (JComboBox)e.getSource();
				String favoriteroom = (String)cb.getSelectedItem();
				Room roomobj = str.get_fav().get_fav_room(favoriteroom);
				String buildnam = roomobj.get_building();
				Building build = str.search_building(buildnam);
				String floornumber = Integer.toString(roomobj.get_floor());
				floor floorobj = build.search_floor(floornumber);
				favposx = roomobj.get_x();
				favposy = roomobj.get_y();
				floorPic.setIcon(new ImageIcon(buildingMap.class.getResource(floorobj.get_img())));
				buildingmap.add(scrollPane);
				String maploc = "/img/pin.png";
				roomPin.setIcon(new ImageIcon(buildingMap.class.getResource(maploc)));
				roomPin.setBounds(favposx-iconx, favposy-icony, 32, 32);
				floorPic.add(roomPin);
				
			}
		});
		favoriteBox.setBounds(33, 442, 124, 27);
		buildingmap.add(favoriteBox);
		
		JButton setFavorite = new JButton("Set room as favorite");
		setFavorite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building build = str.search_building(buildName);
				floor floornum = build.search_floor(floorNum);
				Room room = floornum.search(roomNum);
				favorites favor = str.get_fav();
				favor.add_room(room);
				favoriteBox.addItem(room.get_roomNumber());
			}
		});
		setFavorite.setBounds(865, 34, 153, 29);
		buildingmap.add(setFavorite);
//------------------------------------------------------------------------------------------------------------				
		JButton btnEditPage = new JButton("Edit Page");
		btnEditPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				editBuilding.main(null);
			}
		});
		btnEditPage.setBounds(675, 643, 117, 29);
		buildingmap.add(btnEditPage);
		
		JButton btnRefreshImage = new JButton("Refresh Image");
		btnRefreshImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building buildpic = str.search_building(buildName);
				floor floorpic = buildpic.search_floor(floorNum);
				String picloca = floorpic.get_img();
				floorPic.setIcon(new ImageIcon(buildingMap.class.getResource(picloca)));
				buildingmap.add(scrollPane);
			}
		});
		btnRefreshImage.setBounds(304, 42, 117, 29);
		buildingmap.add(btnRefreshImage);
		
		JButton btnReload = new JButton("Reload");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				buildingMap.main(null);
			}
		});
		btnReload.setBounds(901, 6, 117, 29);
		buildingmap.add(btnReload);
		
		
		
		
		
		
		
		
	}
}
