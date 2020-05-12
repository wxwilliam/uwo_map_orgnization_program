package uwo_map_organization_program;

import java.util.ArrayList;
import java.util.List;

/**
 * the class that will store different kind of rooms into different array
 */
public class PointsOfInterest {

	private List<Washroom> washrooms;
	private List<Eatery> eateries;
	private List<Elevator> elevator;
	private List<Classroom> classrooms;
	private List<Room> rooms;
	
	/**
	 * set up a new poi object
	 */
	public PointsOfInterest() {
		washrooms = new ArrayList<Washroom>();
		eateries = new ArrayList<Eatery>();
		elevator = new ArrayList<Elevator>();
		classrooms = new ArrayList<Classroom>();
		rooms = new ArrayList<Room>();

	}
	
	/**
	 * 
	 * @param target_room	the target room want to check
	 * @return	1			if the room is a washroom
	 * 			2			if the room is a eatery
	 * 			3 			if the room is a elevator
	 * 			4			if the room is a classroom
	 * 			0			if the room is a regular room
	 * 
	 * check what type the target room is 
	 */
	public int check_room_type (Room target_room) {
		if (washrooms.contains(target_room)) {
			return 1;
		}
		else if (eateries.contains(target_room)) {
			return 2;
		}
		else if (elevator.contains(target_room)) {
			return 3;
		}
		else if (classrooms.contains(target_room)) {
			return 4;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * @param	target	a washroom object that willing to add into the array
	 */
	public void add_room_washroom(Washroom target) {
		
		washrooms.add(target);
		
	}
	
	/**
	 * @param	target	an eatery object that willing to add into the array
	 */
	public void add_room_eatery(Eatery target) {
		eateries.add(target);
	}
	
	/**
	 * @param	target	an elevator object that willing to add into the array
	 */
	public void add_room_elevator (Elevator target) {
		elevator.add(target);
	}
	
	/**
	 * @param	target	a classroom object that willing to add into the array
	 */
	public void add_room_classroom (Classroom target) {
		classrooms.add(target);
	}
	
	/**
	 * @param	target	a room object that willing to add into the array
	 */
	public void add_room (Room target) {
		rooms.add(target);
	}

	/**
	 * @return	all of the saved washrooms
	 */
	public List<Washroom> get_washroom() {
		return washrooms;
	}
	
	/**
	 * @return	all of the saved eateries
	 */
	public List<Eatery> get_eateries() {
		return eateries;
	}
	
	/**
	 * @return	all of the saved elevators
	 */
	public List<Elevator> get_elevator() {
		return elevator;
	}
	
	/**
	 * @return	all of the saved classrooms
	 */
	public List<Classroom> get_classrooms() {
		return classrooms;
	}
	
	/**
	 * @return	the number of the saved washrooms
	 */
	public int get_washroom_num () {
		return washrooms.size();
	}
	
	/**
	 * @return	the number of the saved eateries
	 */
	public int get_eateries_num () {
		return eateries.size();
	}
	
	/**
	 * @return	the number of the saved elevators
	 */
	public int get_elevator_num() {
		return elevator.size();
	}
	
	/**
	 * @return	the number of the saved classrooms
	 */
	public int get_classrooms_num() {
		return classrooms.size();
	}
	
	/**
	 * 
	 * @param target	the room want to remove
	 * remove the room from the the list
	 */
	public void remove_room (Room target) {
		int result = this.check_room_type(target);
		System.out.println(result);
		if (result == 1) {
			washrooms.remove(target);
		}
		else if (result == 2) {
			eateries.remove(target);
		}
		else if (result == 3) {
			elevator.remove(target);
		}
		else if (result == 4) {
			classrooms.remove(target);
		}
		else {
			rooms.remove(target);
		}
	}
}
