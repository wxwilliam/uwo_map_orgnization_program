package uwo_map_organization_program;

import java.util.ArrayList;
import java.util.List;

/**
 * save the favorite rooms chosen by the user
 */
public class favorites{
	private List<Room> favor_point; // the list that saved all of the rooms that chosen by user

	/**
	 * create a new empty favorite list
	 */
	public favorites () {
		favor_point = new ArrayList<Room>();
	}

	
	/**
	 * 
	 * @param target	the room that willing to check	
	 * @return	true if the room is in the favorite list, false otherwise
	 * check if the target room is currently in the favorite list
	 */
	public boolean check_room (Room target) {
		for (int i = 0; i<favor_point.size(); i++) {
			if (favor_point.get(i).get_roomNumber() == target.get_roomNumber()) {
				if (favor_point.get(i).get_building().equals(target.get_building())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param target	the room that willing to add
	 * @return	true if added the room successfully, false otherwise
	 * add the target room into the favorite list
	 */
	public boolean add_room (Room target) {
		if (check_room(target)==true) {
			return false;
		}
		
		favor_point.add(target);
		return true;
	}
	
	/**
	 * 
	 * @param target	the room that willing to delete
	 * @return	true if deleted the room successfully, false otherwise
	 * delete the target room from the favorite list
	 */
	public boolean delete_room (Room target) {
		if (check_room(target)==false) {
			return false;
		}
		favor_point.remove(target);
		return true;
	}
	
	/**
	 * 
	 * @return	the room number of all rooms in the favorite list
	 * return the room number of all rooms in the favorite list in string
	 */
	public String[] get_fav_list () {
		String[] result = new String [favor_point.size()];
		int count = 0;
		for (int i = 0; i<favor_point.size();i++) {
			result[count] = favor_point.get(i).get_roomNumber();
			count ++;
		}
		return result;
	}
	
	/**
	 * 
	 * @param target	the name of the room that want to check
	 * @return	the Room object of the room number given, null if cannot find
	 * search the Room from the favorite list based on the room number given
	 */
	public Room get_fav_room(String target) {
		for (int i = 0; i<favor_point.size();i++) {
			if (favor_point.get(i).get_roomNumber().equals(target)) {
				return favor_point.get(i);
			}
		}
		System.out.println("cannot find the room..");
		return null;
	}
	
}