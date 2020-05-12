package uwo_map_organization_program;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class editBuilding extends JFrame {
	private JPanel editbuilding;
	public String room;
	public String buildName = "Middlesex";
	public String floorNum = "1";
	public String roomNum;
	private JTextField addbuilding;
	private JTextField addfloor;
	private JTextField addroom;
	private JTextField editbuild;
	private JTextField editfloor;
	private JTextField editroom;
	private JTextField addroomx;
	private JTextField addroomy;
	private JTextField editroomx;
	private JTextField editroomy;
	String whatLayer;
	int locationx;
	int locationy;
	String uplodepicloc;
	JFileChooser file_chooser = new JFileChooser();
	StringBuilder sb = new StringBuilder();
	public void pick_me() throws FileNotFoundException {
		if(file_chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = file_chooser.getSelectedFile();
			Scanner input = new Scanner(file);
			
			while(input.hasNext()) {
				sb.append(input.nextLine());
				sb.append("\n");
			}
			input.close();
		}
		else {
			sb.append("No such file");
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editBuilding frame = new editBuilding();
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
	public editBuilding() {
		setTitle("UWOMapSearchSystem - Edit Page");
		StartUp str = new StartUp();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1240, 728);
		editbuilding = new JPanel();
		editbuilding.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(editbuilding);
		editbuilding.setLayout(null);
		int iconx = 15;
		int icony = 32;
		JLabel roomPin = new JLabel();
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				buildingMap.main(null);
			}
		});
		btnBack.setBounds(6, 6, 117, 29);
		editbuilding.add(btnBack);
		
		JLabel floorPic = new JLabel();
		floorPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				locationx = e.getX();
				locationy = e.getY();
				String maploc = "/img/pin.png";
				addroomx.setText(Integer.toString(locationx));
				addroomy.setText(Integer.toString(locationy));
				editroomx.setText(Integer.toString(locationx));
				editroomy.setText(Integer.toString(locationy));
				System.out.println("location X:"+ locationx);
				System.out.println("location Y:"+ locationy);
			}
		});
		floorPic.setIcon(new ImageIcon(editBuilding.class.getResource("/Map/MC_Map/MC_G.png")));
		JScrollPane scrollPane = new JScrollPane(floorPic);
		scrollPane.setBounds(504, 75, 714, 481);
		editbuilding.add(scrollPane);
		
		JLabel lblPointOfInterest = new JLabel("Point of Interests:");
		lblPointOfInterest.setBounds(504, 568, 134, 16);
		editbuilding.add(lblPointOfInterest);
		
		JButton poielevator = new JButton("Elevator");
		List<JLabel> elevlabelList = new ArrayList<JLabel>();
		poielevator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whatLayer = "Elevator";
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
		poielevator.setBounds(856, 568, 117, 29);
		editbuilding.add(poielevator);
		
		JButton poiwashroom = new JButton("Washroom");
		poiwashroom.setBounds(618, 568, 117, 29);
		List<JLabel> wrlabelList = new ArrayList<JLabel>();
		poiwashroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whatLayer = "Washroom";
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
		editbuilding.add(poiwashroom);
		
		JButton poieatery = new JButton("Eatery");
		List<JLabel> eatlabelList = new ArrayList<JLabel>();
		poieatery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whatLayer = "Eatery";
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
		poieatery.setBounds(738, 568, 117, 29);
		editbuilding.add(poieatery);
		
		JButton poiclassroom = new JButton("Classroom");
		List<JLabel> classlabelList = new ArrayList<JLabel>();
		poiclassroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whatLayer = "Classroom";
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
	
		poiclassroom.setBounds(973, 568, 117, 29);
		editbuilding.add(poiclassroom);
		

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
		btnClear.setBounds(1083, 568, 117, 29);
		editbuilding.add(btnClear);
		
		
//------------------------------------------------------------------------------------------------------------				
		JLabel lblSelectTheBuilding = new JLabel("Select the Building:");
		lblSelectTheBuilding.setBounds(33, 35, 124, 16);
		editbuilding.add(lblSelectTheBuilding);
		
		JLabel lblSelectTheFloor = new JLabel("Select the Floor:");
		lblSelectTheFloor.setBounds(33, 113, 109, 16);
		editbuilding.add(lblSelectTheFloor);
		
		JLabel lblSelectTheRoom = new JLabel("Select the Room:");
		lblSelectTheRoom.setBounds(33, 186, 107, 16);
		editbuilding.add(lblSelectTheRoom);
		
		String[] buildingList = str.get_building_list();
		JComboBox buildingBox = new JComboBox(buildingList);
		buildingBox.setBounds(169, 31, 134, 27);
		
		Building build = str.search_building(buildName);
		String[] floorList = build.get_floor_num_list();
		JComboBox floorBox = new JComboBox(floorList);
		floorBox.setBounds(169, 109, 134, 27);
		
		floor floor_num = build.search_floor(floorNum);
		String[] roomList = floor_num.get_room_num_list();
		JComboBox roomBox = new JComboBox(roomList);
		roomBox.setBounds(169, 182, 134, 27);
		
		
		buildingBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel floorMod = (DefaultComboBoxModel)floorBox.getModel();
				Building buildpic = str.search_building(buildName);
				floor floorpic = buildpic.search_floor(floorNum);
				String picloca = floorpic.get_img();
				floorPic.setIcon(new ImageIcon(editBuilding.class.getResource(picloca)));
				editbuilding.add(scrollPane);
				floorMod.removeAllElements();
				JComboBox cb = (JComboBox)e.getSource();
				String buildingName = (String)cb.getSelectedItem();
				buildName = buildingName;
			}
		});
		
		editbuilding.add(buildingBox);
		
		
		floorBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel roomModel = (DefaultComboBoxModel)roomBox.getModel();
				roomModel.removeAllElements();
				JComboBox cb = (JComboBox)e.getSource();
				String floor = (String)cb.getSelectedItem();
				floorNum = floor;
			}
		});
		
		editbuilding.add(floorBox);
		
		
		roomBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String room_Num = (String)cb.getSelectedItem();
				roomNum = room_Num;	
				roomPin.setIcon(null);			
			}
		});
		
		editbuilding.add(roomBox);
		
//add------------------------------------------------------------------------------------------------------------				

		JLabel lblEnterBuildingName = new JLabel("Enter Building name to add:");
		lblEnterBuildingName.setBounds(43, 56, 175, 16);
		editbuilding.add(lblEnterBuildingName);
		
		addbuilding = new JTextField();
		addbuilding.setBounds(53, 75, 130, 26);
		editbuilding.add(addbuilding);
		addbuilding.setColumns(10);
		
		JButton buildingsubmit = new JButton("Submit");
		buildingsubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buildingname = addbuilding.getText();
				buildingBox.addItem(buildingname);
				str.add_building(buildingname);
			}
		});
		buildingsubmit.setBounds(186, 75, 95, 29);
		editbuilding.add(buildingsubmit);
		
		JLabel lblEnterFloorTo = new JLabel("Enter Floor to add:");
		lblEnterFloorTo.setBounds(43, 130, 140, 16);
		editbuilding.add(lblEnterFloorTo);
		
		addfloor = new JTextField();
		addfloor.setBounds(50, 148, 130, 26);
		editbuilding.add(addfloor);
		addfloor.setColumns(10);
		
		JButton floorsubmit = new JButton("Submit");
		floorsubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String floornumber = addfloor.getText();
				Building build = str.search_building(buildName);
				System.out.println(build.get_building_name());
				System.out.println(build.get_num_of_floor());
				favorites fav = str.get_fav();
				str.add_floor(buildName, floornumber, uplodepicloc);
				System.out.println(build.get_num_of_floor());
				floorBox.addItem(floornumber);
			}
		});
		floorsubmit.setBounds(186, 148, 95, 29);
		editbuilding.add(floorsubmit);
		
		JLabel lblEnterRoomTo = new JLabel("Enter Room to add:");
		lblEnterRoomTo.setBounds(43, 200, 150, 27);
		editbuilding.add(lblEnterRoomTo);
		
		addroom = new JTextField();
		addroom.setBounds(53, 221, 130, 26);
		editbuilding.add(addroom);
		addroom.setColumns(10);
		
		JButton roomsubmit = new JButton("Submit");
		roomsubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roomname = addroom.getText();
				Building build = str.search_building(buildName);
				floor floornum = build.search_floor(floorNum);
				int[] xylist = new int[2];
				xylist[0] = locationx;
				xylist[1] = locationy;
				roomBox.addItem(roomname);
				str.add_room(buildName, floorNum, roomname, xylist);
			}
		});
		roomsubmit.setBounds(186, 221, 95, 29);
		editbuilding.add(roomsubmit);
//------------------------------------------------------------------------------------------------------
		
		JLabel lblNewLabel = new JLabel("Select the Building:");
		lblNewLabel.setBounds(33, 259, 124, 16);
		editbuilding.add(lblNewLabel);
		
		JLabel lblSelectTheFloor_1 = new JLabel("Select the Floor:");
		lblSelectTheFloor_1.setBounds(33, 339, 107, 16);
		editbuilding.add(lblSelectTheFloor_1);
		
		JLabel lblSelectTheRoom_1 = new JLabel("Select the Room:");
		lblSelectTheRoom_1.setBounds(33, 415, 109, 16);
		editbuilding.add(lblSelectTheRoom_1);
		
		String[] editbuildingList = str.get_building_list();
		JComboBox editBuildingbox = new JComboBox(editbuildingList);
		editBuildingbox.setBounds(169, 255, 134, 27);
		
		Building editbuildingg = str.search_building(buildName);
		String[] editfloorList = editbuildingg.get_floor_num_list();
		JComboBox editFloorbox = new JComboBox(editfloorList);
		editFloorbox.setBounds(169, 335, 134, 27);
		
		floor editfloor_num = editbuildingg.search_floor(floorNum);
		String[] editroomList = editfloor_num.get_room_num_list();
		JComboBox editRoombox = new JComboBox(editroomList);
		editRoombox.setBounds(169, 411, 134, 27);
		
		
		editBuildingbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel floorMod = (DefaultComboBoxModel)editFloorbox.getModel();
				Building buildpic = str.search_building(buildName);
				floor floorpic = buildpic.search_floor(floorNum);
				String picloca = floorpic.get_img();
				floorPic.setIcon(new ImageIcon(editBuilding.class.getResource(picloca)));
				editbuilding.add(scrollPane);
				floorMod.removeAllElements();
				JComboBox cb = (JComboBox)e.getSource();
				String buildingName = (String)cb.getSelectedItem();
				buildName = buildingName;
			}
		});
		
		
		
		
		editFloorbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel roomModel = (DefaultComboBoxModel)editRoombox.getModel();
				roomModel.removeAllElements();
				JComboBox cb = (JComboBox)e.getSource();
				String floor = (String)cb.getSelectedItem();
				floorNum = floor;
			}
		});
		
		
		
		
		editRoombox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String room_Num = (String)cb.getSelectedItem();
				roomNum = room_Num;	
				roomPin.setIcon(null);	
			}
		});
		
		
		editbuilding.add(editBuildingbox);
		editbuilding.add(editFloorbox);
		editbuilding.add(editRoombox);
		
		
//edit-------------------------------------------------------------------------------------------------------------
		
		editbuild = new JTextField();
		editbuild.setBounds(53, 301, 130, 26);
		editbuilding.add(editbuild);
		editbuild.setColumns(10);

		JLabel lblEnterBuildingName_1 = new JLabel("Enter Building name to edit:");
		lblEnterBuildingName_1.setBounds(43, 281, 175, 16);
		editbuilding.add(lblEnterBuildingName_1);
		
		
		JButton buildingedit = new JButton("Edit");
		buildingedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String editbuildtext = editbuild.getText();
				str.change_building(buildName, editbuildtext);
			}
		});
		buildingedit.setBounds(186, 301, 95, 29);
		editbuilding.add(buildingedit);
		
		JLabel lblEnterFloorTo_1 = new JLabel("Enter Floor to edit:");
		lblEnterFloorTo_1.setBounds(43, 356, 124, 16);
		editbuilding.add(lblEnterFloorTo_1);
		
		editfloor = new JTextField();
		editfloor.setBounds(53, 377, 130, 26);
		editbuilding.add(editfloor);
		editfloor.setColumns(10);
		
		JButton flooredit = new JButton("Edit");
		flooredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String editfloortext = editfloor.getText();
				str.change_floor(buildName, floorNum, editfloortext);
			}
		});
		flooredit.setBounds(186, 377, 95, 29);
		editbuilding.add(flooredit);
		
		JLabel lblEnterRoomTo_1 = new JLabel("Enter Room to edit:");
		lblEnterRoomTo_1.setBounds(43, 432, 124, 16);
		editbuilding.add(lblEnterRoomTo_1);
		
		editroom = new JTextField();
		editroom.setBounds(53, 453, 130, 26);
		editbuilding.add(editroom);
		editroom.setColumns(10);
		
		JButton roomedit = new JButton("Edit");
		roomedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String editroomtext = editroom.getText();
				str.change_room(buildName, floorNum, roomNum, editroomtext);
			}
		});
		roomedit.setBounds(186, 453, 95, 29);
		editbuilding.add(roomedit);
		
//---------------------------------------------------------------------------------------------------------
			
		JLabel lblSelectTheBuilding_1 = new JLabel("Select the Building to remove:");
		lblSelectTheBuilding_1.setBounds(33, 491, 195, 16);
		editbuilding.add(lblSelectTheBuilding_1);
		
		JLabel lblSelectTheFloor_2 = new JLabel("Select the Floor to remove:");
		lblSelectTheFloor_2.setBounds(33, 540, 222, 16);
		editbuilding.add(lblSelectTheFloor_2);
		
		JLabel lblSelectTheRoom_2 = new JLabel("Select the Room to remove:");
		lblSelectTheRoom_2.setBounds(33, 591, 222, 16);
		editbuilding.add(lblSelectTheRoom_2);
		
		String[] removebuildingList = str.get_building_list();
		JComboBox removeBuildingbox = new JComboBox(removebuildingList);
		removeBuildingbox.setBounds(53, 510, 130, 27);
		
		Building removebuildingg = str.search_building(buildName);
		String[] removefloorList = removebuildingg.get_floor_num_list();
		JComboBox removeFloorbox = new JComboBox(removefloorList);
		removeFloorbox.setBounds(53, 557, 130, 27);
		
		floor removefloor_num = removebuildingg.search_floor(floorNum);
		String[] removeroomList = removefloor_num.get_room_num_list();
		JComboBox removeRoombox = new JComboBox(removeroomList);
		removeRoombox.setBounds(53, 607, 130, 27);
		
		
		
		removeBuildingbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel floorMod = (DefaultComboBoxModel)removeFloorbox.getModel();
				Building buildpic = str.search_building(buildName);
				floor floorpic = buildpic.search_floor(floorNum);
				String picloca = floorpic.get_img();
				floorPic.setIcon(new ImageIcon(editBuilding.class.getResource(picloca)));
				editbuilding.add(scrollPane);
				floorMod.removeAllElements();
				JComboBox cb = (JComboBox)e.getSource();
				String buildingName = (String)cb.getSelectedItem();
				buildName = buildingName;
			}
		});
		
		removeFloorbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel roomModel = (DefaultComboBoxModel)removeRoombox.getModel();
				roomModel.removeAllElements();
				JComboBox cb = (JComboBox)e.getSource();
				String floor = (String)cb.getSelectedItem();
				floorNum = floor;
			}
		});
		
		removeRoombox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String room_Num = (String)cb.getSelectedItem();
				roomNum = room_Num;	
				roomPin.setIcon(null);	
			}
		});
		
		editbuilding.add(removeBuildingbox);
		editbuilding.add(removeFloorbox);
		editbuilding.add(removeRoombox);
//remove------------------------------------------------------------------------------------
		
		JButton buildingremove = new JButton("Remove");
		buildingremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str.remove_building(buildName);
				removeBuildingbox.removeItem(buildName);
			}
		});
		buildingremove.setBounds(186, 509, 95, 29);
		editbuilding.add(buildingremove);
		
		JButton floorremove = new JButton("Remove");
		floorremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str.remove_floor(buildName, floorNum);
				removeFloorbox.removeItem(floorNum);
			}
		});
		floorremove.setBounds(186, 556, 95, 29);
		editbuilding.add(floorremove);
		
		JButton roomremove = new JButton("Remove");
		roomremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str.remove_room(buildName, floorNum, roomNum);
				removeRoombox.removeItem(roomNum);
			}
		});
		roomremove.setBounds(186, 606, 95, 29);
		editbuilding.add(roomremove);
		
		JLabel lblXCoordinate = new JLabel("X coordinate:");
		lblXCoordinate.setBounds(277, 221, 95, 16);
		editbuilding.add(lblXCoordinate);
		
		JLabel lblYCoordinate = new JLabel("Y coordinate:");
		lblYCoordinate.setBounds(277, 231, 88, 16);
		editbuilding.add(lblYCoordinate);
		
		addroomx = new JTextField();
		addroomx.setBounds(364, 221, 130, 16);
		editbuilding.add(addroomx);
		addroomx.setColumns(10);
		
		addroomy = new JTextField();
		addroomy.setBounds(364, 236, 130, 16);
		editbuilding.add(addroomy);
		addroomy.setColumns(10);
		
		JLabel label = new JLabel("X coordinate:");
		label.setBounds(277, 453, 95, 16);
		editbuilding.add(label);
		
		JLabel label_1 = new JLabel("Y coordinate:");
		label_1.setBounds(277, 463, 88, 16);
		editbuilding.add(label_1);
		
		editroomx = new JTextField();
		editroomx.setColumns(10);
		editroomx.setBounds(362, 450, 130, 16);
		editbuilding.add(editroomx);
		
		editroomy = new JTextField();
		editroomy.setColumns(10);
		editroomy.setBounds(362, 466, 130, 16);
		editbuilding.add(editroomy);
//------------------------------------------------------------------------------------------------	
		
		String[] poibuildingList = str.get_building_list();
		JComboBox poibuildingbox = new JComboBox(poibuildingList);
		poibuildingbox.setBounds(504, 596, 134, 27);
		
		Building poibuildingg = str.search_building(buildName);
		String[] poifloorList = poibuildingg.get_floor_num_list();
		JComboBox poifloorbox = new JComboBox(poifloorList);
		poifloorbox.setBounds(504, 621, 134, 27);
		
		floor poifloor_num = poibuildingg.search_floor(floorNum);
		String[] poiroomList = poifloor_num.get_room_num_list();
		JComboBox poiroombox = new JComboBox(poiroomList);
		poiroombox.setBounds(504, 647, 134, 27);
		
		
		poibuildingbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel floorMod = (DefaultComboBoxModel)poifloorbox.getModel();
				Building buildpic = str.search_building(buildName);
				floor floorpic = buildpic.search_floor(floorNum);
				String picloca = floorpic.get_img();
				floorPic.setIcon(new ImageIcon(editBuilding.class.getResource(picloca)));
				editbuilding.add(scrollPane);
				floorMod.removeAllElements();
				JComboBox cb = (JComboBox)e.getSource();
				String buildingName = (String)cb.getSelectedItem();
				buildName = buildingName;
			}
		});
		
		poifloorbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel roomModel = (DefaultComboBoxModel)poiroombox.getModel();
				roomModel.removeAllElements();
				JComboBox cb = (JComboBox)e.getSource();
				String floor = (String)cb.getSelectedItem();
				floorNum = floor;
			}
		});
				
		
		poiroombox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String room_Num = (String)cb.getSelectedItem();
				roomNum = room_Num;	
				roomPin.setIcon(null);	
			}
		});
		editbuilding.add(poibuildingbox);
		editbuilding.add(poifloorbox);
		editbuilding.add(poiroombox);
//poi------------------------------------------------------------------------------------------------		
		
		JButton poiadd = new JButton("Add");
		poiadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building build = str.search_building(buildName);
				floor floornum = build.search_floor(floorNum);
				Room roomnumber = floornum.search(roomNum);
				floornum.set_room(roomnumber, floornum.set_poi(roomNum, whatLayer));
				str.rewrite();
			}
		});
		poiadd.setBounds(760, 620, 117, 29);
		editbuilding.add(poiadd);
		
		JButton poiedit = new JButton("Edit");
		poiedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building build = str.search_building(buildName);
				floor floornum = build.search_floor(floorNum);
				Room roomnumber = floornum.search(roomNum);
				floornum.set_room(roomnumber, floornum.set_poi(roomNum, whatLayer));
				str.rewrite();
			}
		});
		poiedit.setBounds(866, 620, 117, 29);
		editbuilding.add(poiedit);
		
		JButton poiremove = new JButton("Remove");
		poiremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building build = str.search_building(buildName);
				floor floornum = build.search_floor(floorNum);
				Room roomnumber = floornum.search(roomNum);
				floornum.set_room(roomnumber, floornum.set_poi(roomNum, "Room"));
				str.rewrite();
			}
		});
		poiremove.setBounds(973, 620, 117, 29);
		editbuilding.add(poiremove);
		
		JButton addFloorPic = new JButton("Open File");
		addFloorPic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    uplodepicloc = file.getAbsolutePath();
                    try {
						FileInputStream in = new FileInputStream(uplodepicloc);
						FileOutputStream ou = new FileOutputStream("/Users/williamwu/Development/eclipes/CS2212Assignment/src/Map/test.png");
						BufferedInputStream bin = new BufferedInputStream(in);
						BufferedOutputStream bou = new BufferedOutputStream(ou);
						int b=0;
						while(b!=-1) {
							b=bin.read();
							bou.write(b);
						}
						bin.close();
						bou.close();
                    } catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    try {
                        floorPic.setIcon(new ImageIcon(ImageIO.read(file)));
                        editbuilding.add(scrollPane);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
				
				
			}
		});
		addFloorPic.setBounds(277, 148, 117, 29);
		editbuilding.add(addFloorPic);
		
		JButton editFloorPic = new JButton("Open File");
		editFloorPic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    uplodepicloc = file.getAbsolutePath();
                    try {
						FileInputStream in = new FileInputStream(uplodepicloc);
						FileOutputStream ou = new FileOutputStream("/Users/williamwu/Development/eclipes/CS2212Assignment/src/Map/test.png");
						BufferedInputStream bin = new BufferedInputStream(in);
						BufferedOutputStream bou = new BufferedOutputStream(ou);
						int b=0;
						while(b!=-1) {
							b=bin.read();
							bou.write(b);
						}
						bin.close();
						bou.close();
                    } catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    try {
                        floorPic.setIcon(new ImageIcon(ImageIO.read(file)));
                        editbuilding.add(scrollPane);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
			}
		});
		editFloorPic.setBounds(277, 377, 117, 29);
		editbuilding.add(editFloorPic);
		
		JLabel lblNewLabel_1 = new JLabel("------------------------------------------------------------");
		lblNewLabel_1.setBounds(6, 245, 486, 16);
		editbuilding.add(lblNewLabel_1);
		
		JLabel label_2 = new JLabel("------------------------------------------------------------");
		label_2.setBounds(6, 481, 486, 16);
		editbuilding.add(label_2);
		
		JLabel label_3 = new JLabel("------------------------------------------------------------");
		label_3.setBounds(6, 632, 486, 16);
		editbuilding.add(label_3);
		
		JLabel label_4 = new JLabel("------------------------------------------------------------");
		label_4.setBounds(6, 22, 486, 16);
		editbuilding.add(label_4);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		btnExit.setBounds(1117, 671, 117, 29);
		editbuilding.add(btnExit);
		
		
		JButton btnFaq = new JButton("FAQ");
		btnFaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FAQ.main(null);
			}
		});
		btnFaq.setBounds(1004, 671, 117, 29);
		editbuilding.add(btnFaq);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
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
		btnRefresh.setBounds(299, 30, 117, 29);
		editbuilding.add(btnRefresh);
		
		JButton btnRefreshPin = new JButton("Refresh Pin");
		btnRefreshPin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maploc = "/img/pin.png";
				Building buildpin = str.search_building(buildName);
				floor floorpin = buildpin.search_floor(floorNum);
				Room roompin = floorpin.search(roomNum);
				int roomx = roompin.get_x();
				int roomy = roompin.get_y();
				roomPin.setIcon(new ImageIcon(editBuilding.class.getResource(maploc)));
				roomPin.setBounds(roomx-iconx, roomy-icony, 32, 32);
				floorPic.add(roomPin);
			}
		});
		btnRefreshPin.setBounds(299, 72, 117, 29);
		editbuilding.add(btnRefreshPin);
		
		JButton btnRefreshMap = new JButton("Refresh Map");
		btnRefreshMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building buildpic = str.search_building(buildName);
				floor floorpic = buildpic.search_floor(floorNum);
				String picloca = floorpic.get_img();
				System.out.println(picloca);
				System.out.println(floorPic);
				floorPic.setIcon(new ImageIcon(editBuilding.class.getResource(picloca)));
				editbuilding.add(scrollPane);
			}
		});
		btnRefreshMap.setBounds(299, 51, 117, 29);
		editbuilding.add(btnRefreshMap);
		
		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building build = str.search_building(buildName);
				String[] floorList = build.get_floor_num_list();
				for (int i=0; i < floorList.length;i++) {
					editFloorbox.addItem(floorList[i]);
				}
				floor floor_num = build.search_floor(floorNum);
				String[] roomList = floor_num.get_room_num_list();
				for (int i=0; i < roomList.length;i++) {
					editRoombox.addItem(roomList[i]);
				}
			}
		});
		btnRefresh_1.setBounds(299, 254, 117, 29);
		editbuilding.add(btnRefresh_1);
		
		JButton btnRefreshMap_1 = new JButton("Refresh Map");
		btnRefreshMap_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building buildpic = str.search_building(buildName);
				floor floorpic = buildpic.search_floor(floorNum);
				String picloca = floorpic.get_img();
				System.out.println(picloca);
				System.out.println(floorPic);
				floorPic.setIcon(new ImageIcon(editBuilding.class.getResource(picloca)));
				editbuilding.add(scrollPane);
			}
		});
		btnRefreshMap_1.setBounds(299, 275, 117, 29);
		editbuilding.add(btnRefreshMap_1);
		
		JButton btnRefreshPin_1 = new JButton("Refresh Pin");
		btnRefreshPin_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maploc = "/img/pin.png";
				Building buildpin = str.search_building(buildName);
				floor floorpin = buildpin.search_floor(floorNum);
				Room roompin = floorpin.search(roomNum);
				int roomx = roompin.get_x();
				int roomy = roompin.get_y();
				roomPin.setIcon(new ImageIcon(editBuilding.class.getResource(maploc)));
				roomPin.setBounds(roomx-iconx, roomy-icony, 32, 32);
				floorPic.add(roomPin);
			}
		});
		btnRefreshPin_1.setBounds(299, 296, 117, 29);
		editbuilding.add(btnRefreshPin_1);
		
		JButton btnRefresh_2 = new JButton("Refresh");
		btnRefresh_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building build = str.search_building(buildName);
				String[] floorList = build.get_floor_num_list();
				for (int i=0; i < floorList.length;i++) {
					removeFloorbox.addItem(floorList[i]);
				}
				floor floor_num = build.search_floor(floorNum);
				String[] roomList = floor_num.get_room_num_list();
				for (int i=0; i < roomList.length;i++) {
					removeRoombox.addItem(roomList[i]);
				}
			}
		});
		btnRefresh_2.setBounds(299, 497, 117, 29);
		editbuilding.add(btnRefresh_2);
		
		JButton btnRefreshMap_2 = new JButton("Refresh Map");
		btnRefreshMap_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building buildpic = str.search_building(buildName);
				floor floorpic = buildpic.search_floor(floorNum);
				String picloca = floorpic.get_img();
				System.out.println(picloca);
				System.out.println(floorPic);
				floorPic.setIcon(new ImageIcon(editBuilding.class.getResource(picloca)));
				editbuilding.add(scrollPane);
			}
		});
		btnRefreshMap_2.setBounds(299, 518, 117, 29);
		editbuilding.add(btnRefreshMap_2);
		
		JButton btnRefreshPin_2 = new JButton("Refresh Pin");
		btnRefreshPin_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maploc = "/img/pin.png";
				Building buildpin = str.search_building(buildName);
				floor floorpin = buildpin.search_floor(floorNum);
				Room roompin = floorpin.search(roomNum);
				int roomx = roompin.get_x();
				int roomy = roompin.get_y();
				roomPin.setIcon(new ImageIcon(editBuilding.class.getResource(maploc)));
				roomPin.setBounds(roomx-iconx, roomy-icony, 32, 32);
				floorPic.add(roomPin);
			}
		});
		btnRefreshPin_2.setBounds(299, 539, 117, 29);
		editbuilding.add(btnRefreshPin_2);
		
		JButton btnRefresh_3 = new JButton("Refresh");
		btnRefresh_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building build = str.search_building(buildName);
				String[] floorList = build.get_floor_num_list();
				for (int i=0; i < floorList.length;i++) {
					poifloorbox.addItem(floorList[i]);
				}
				floor floor_num = build.search_floor(floorNum);
				String[] roomList = floor_num.get_room_num_list();
				for (int i=0; i < roomList.length;i++) {
					poiroombox.addItem(roomList[i]);
				}
			}
		});
		btnRefresh_3.setBounds(628, 596, 117, 29);
		editbuilding.add(btnRefresh_3);
		
		JButton btnRefreshMap_3 = new JButton("Refresh Map");
		btnRefreshMap_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building buildpic = str.search_building(buildName);
				floor floorpic = buildpic.search_floor(floorNum);
				String picloca = floorpic.get_img();
				System.out.println(picloca);
				System.out.println(floorPic);
				floorPic.setIcon(new ImageIcon(editBuilding.class.getResource(picloca)));
				editbuilding.add(scrollPane);
			}
		});
		btnRefreshMap_3.setBounds(628, 617, 117, 29);
		editbuilding.add(btnRefreshMap_3);
		
		JButton btnRefreshPin_3 = new JButton("Refresh Pin");
		btnRefreshPin_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maploc = "/img/pin.png";
				Building buildpin = str.search_building(buildName);
				floor floorpin = buildpin.search_floor(floorNum);
				Room roompin = floorpin.search(roomNum);
				int roomx = roompin.get_x();
				int roomy = roompin.get_y();
				roomPin.setIcon(new ImageIcon(editBuilding.class.getResource(maploc)));
				roomPin.setBounds(roomx-iconx, roomy-icony, 32, 32);
				floorPic.add(roomPin);
			}
		});
		btnRefreshPin_3.setBounds(628, 638, 117, 29);
		editbuilding.add(btnRefreshPin_3);
		
		JButton btnReload = new JButton("Reload");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				editBuilding.main(null);
			}
		});
		btnReload.setBounds(1117, 6, 117, 29);
		editbuilding.add(btnReload);
		
	}
}
