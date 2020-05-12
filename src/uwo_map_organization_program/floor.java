
package uwo_map_organization_program;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * this class will store the data of each floor
 */
public class floor {

	private List<Room> rooms;  // the list of rooms on the floor
	private favorites favorite;    // the favorite object that stores the favorite positions
	private String im;     // the directory of the image of the floor
	private PointsOfInterest poi;  // the point of interests object
	private int floor_num; // the floor number
	private String floor_num_string;   // the floor number in string
    
	/**
	 * 
	 * @param inputFavorites	the favorite object that stores the favorite positions of the user
	 * @param im				the directory of the image of the floor
	 * @param floor_num			the floor number
	 * 
	 * create a floor object
	 */
	public floor(favorites inputFavorites, String im,int floor_num) {
		this.rooms = new ArrayList <Room>();
		this.favorite = inputFavorites;
		this.im = im;
		this.poi = new PointsOfInterest();
		this.floor_num = floor_num;
		this.floor_num_string = String.valueOf(floor_num);
		int[] temp = new int[2];
		temp[0] = 1;
		temp[1] = 1;
		Room tempRoom = new Room ("XXX",1,"asd",temp,"a");
		rooms.add(tempRoom);
	}
	
    /**
	 * @param	rooms			the list of rooms of this floor
	 * @param	inputFavorites	the favorite object that stores the favorite positions of the user
	 * @param	im				the directory of the image of the floor
	 * @param	floor_num		the floor number
	 * 
	 * create a floor object and save all of the rooms on the floor to poi
	 */
	public floor (List<Room> rooms, favorites inputFavorites, String im, int floor_num) {
		this.rooms = rooms;
		this.favorite = inputFavorites;
		this.im = im;
		this.poi = new PointsOfInterest();
		this.floor_num = floor_num;
		this.floor_num_string = String.valueOf(floor_num);
		for (int i = 0; i<rooms.size() ;i++) {
			if (rooms.get(i).getClass() == Washroom.class) {
				poi.add_room_washroom((Washroom) rooms.get(i));
			}
			else if (rooms.get(i).getClass() == Elevator.class) {
				poi.add_room_elevator((Elevator) rooms.get(i));
			}
			else if(rooms.get(i).getClass() == Eatery.class) {
				poi.add_room_eatery((Eatery)rooms.get(i));
			}
			else if (rooms.get(i).getClass() == Classroom.class) {
				poi.add_room_classroom((Classroom) rooms.get(i));
			}
			else {
				poi.add_room(rooms.get(i));
			}
		}
	}
	
	/**
	 * @param	target	the room object that want to set as favorite
	 * add the target room into the favorite list, error will be printed if it is already in the list
	 */
	public void set_as_favor (Room target) {
		boolean result = favorite.add_room(target);
		if (result == false) {
			System.out.println("Error..the room is already in the list..");
		}
	}
	
    /**
	 * @param	target	the room number of the room searching
	 * @return	the room object of the room that has the same room number as target
	 * 			return null if could not find 
	 */
	public Room search (String target) {
		for (int i = 0; i<rooms.size();i++) {
			if (rooms.get(i).get_roomNumber().equals(target)) {
				return rooms.get(i);
			}
		}
		return null;
		
	}
	
	/**
	 * 
	 * @return	the point of interests object
	 */
	public PointsOfInterest get_poi() {
		return poi;
	}

	/**
	 * @return	the favorite object
	 */
	public favorites show_favorites() {
		return favorite;
	}
	
    /**
	 * @return	the floor number
	 */
	public int get_floor_num () {
		return this.floor_num;
	}

    /**
	 * @return	the floor number in string
	 */
	public String get_floor_num_string() {
		return this.floor_num_string;
	}
	
    /**
	 * @return	the list of the room number in string
	 * save the room number of all the rooms in the rooms list to a new list then return
	 */
	public String[] get_room_num_list () {
		String[] result = new String[rooms.size()];
		int counter = 0;
		for (int i = 0; i<rooms.size();i++) {
			result[counter] = rooms.get(i).get_roomNumber();
			counter++;
		}
		return result;
	}
	
	/**
	 * 
	 * @param target	the room that want to change
	 * @param newRoom	the new room object 
	 * change the target room to the new room
	 */
	public void set_room (Room target, Room newRoom) {
		for (int i = 0;i<rooms.size();i++) {
			if (rooms.get(i).get_roomNumber().equals(target.get_roomNumber())) {
				rooms.set(i, newRoom);
			}
		}
	}
	
    /**
	 * @return	the directory of the floor image
	 */
	public String get_img() {
		return this.im;
	}
	
	/**
	 * 
	 * @param building	the building of the new room
	 * @param floor		the floor of the new room
	 * @param roomNum	the room number of the new room
	 * @param xycor		the x,y coordinate of the new room
	 * 
	 * create a new room and add the room to the room list 
	 */
	public void add_room(String building,String floor, String roomNum, int[] xycor) {
		int floor_num = Integer.parseInt(floor);
		Room newRoom = new Room(roomNum,floor_num, building,xycor,"");
		if (rooms.get(0).get_roomNumber().equals("XXX")) {
			rooms.set(0, newRoom);
			return;
		}
		rooms.add(newRoom);
	}
	
	/**
	 * 
	 * @return	the list of the rooms
	 */
	public List<Room> get_rooms (){
		return rooms;
	}
	
	/**
	 * 
	 * @param floor_num		the room number that willing delete
	 * remove the room from the room list
	 */
	public void remove_room(String floor_num) {
		for (int i = 0;i<rooms.size();i++) {
			if (rooms.get(i).get_roomNumber().equals(floor_num)){
				rooms.remove(rooms.get(i));
				return;
			}
		}
		System.out.println("Error..cannot find the room to delete..");
	}
	
	/**
	 * 
	 * @param newName	the new name of the floor
	 * 
	 * set the name of the floor into the new one 
	 */
	public void set_floor_name (String newName) {
		floor_num_string = newName;
		floor_num = Integer.parseInt(newName);
	}
	
	/**
	 * 
	 * @param room		the target room number
	 * @param type		the type of the room
	 * @return			the new room object
	 * 
	 * remove the target room from the poi list and added it to the new list based on the type given
	 */
	public Room set_poi (String room, String type) {
		Room target_room = this.search(room);
		System.out.println(target_room.get_description());
		poi.remove_room(target_room);
		int[] pos = new int[2];
		pos[0] = target_room.get_x();
		pos[1] = target_room.get_y();
		if (type.equals("Washroom")) {
			Washroom wash = new Washroom(room, target_room.get_floor(),target_room.get_building(), pos, "Washroom", "");
			poi.add_room_washroom(wash);
			return wash;
		}
		else if(type.equals("Eatery")) {
			Eatery eate = new Eatery (room, target_room.get_floor(),target_room.get_building(),pos,"Eatery", 5);
			poi.add_room_eatery(eate);
			return eate;
		}
		else if (type.equals("Elevator")) {
			Elevator elev = new Elevator (room, target_room.get_floor(), target_room.get_building(), pos, "Elevator", 5);
			poi.add_room_elevator(elev);
			return elev;
		}
		else if (type.equals("Classroom")) {
			Classroom cr = new Classroom (room, target_room.get_floor(), target_room.get_building(),pos, "Classroom", 10);
			poi.add_room_classroom(cr);
			return cr;
		}
		else {
			Room roomobj = new Room (room,target_room.get_floor(),target_room.get_building(),pos,"Room");
			poi.add_room(roomobj);
			return roomobj;
		}
		
	}
}