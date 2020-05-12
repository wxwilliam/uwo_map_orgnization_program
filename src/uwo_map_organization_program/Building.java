package uwo_map_organization_program;


import java.util.ArrayList;
import java.util.List;

/**
 *  the class that stores the data of each building
 */
public class Building {
	
	List<floor> floor_list; 			// the list of floor of the building
	
	List<Integer> floor_num_list_int; 	// the list of the number of floors in int
	int num_of_floor;  					// total number of floors of the building
	String buildingName;   				// the building name 
	favorites fav;
	
    /**
	 * @param	buildingName	the name of the building
	 * to create a building object by the name given
	 */
	public Building (String buildingName, favorites fav) {
		this.buildingName = buildingName;
		this.floor_list = new ArrayList<floor>();
		floor tempFloor = new floor (fav, "", 0);
		floor_list.add(tempFloor);
		this.num_of_floor = this.floor_list.size();
		this.floor_num_list_int = new ArrayList<Integer>();
		this.fav = fav;

	}
	
    /**
	 *  @param	new_floor	the floor willing to add
	 *  to add the floor given to the floor list
	 */
	public void add_floor(floor new_floor) {
		if (floor_list.get(0).get_floor_num()==0) {
			floor_list.set(0, new_floor);
			return;
		}
		floor_list.add(new_floor);
		num_of_floor++;
	}
	
    /**
	 * @return	the list of the floor numbers
	 */
	public String[] get_floor_num_list() {
		String[] floor_num_list = new String[floor_list.size()];
		int counter = 0;
		for(int i = 0; i<floor_list.size();i++) {
			floor_num_list[counter]= floor_list.get(i).get_floor_num_string();
			counter++;
		}
		return floor_num_list;
	}

	/**
	 * return the number of floor list in integers
	 * @return	the number of floor list
	 */
	public int[] get_floor_num_list_int(){
		int[] floor_num_list = new int[floor_list.size()];
		int counter = 0;
		for(int i = 0; i<floor_list.size();i++) {
			floor_num_list[counter]= floor_list.get(i).get_floor_num();
			counter++;
		}
		return floor_num_list;
	}
	
    /**
	 * @return	the total number of floors
	 */
	public int get_num_of_floor () {
		return this.num_of_floor;
	}
	
    /**
	 * @param	input	the floor number
	 * @return	the floor object base on the floor number given, return null if could not find the floor
	 */
	public floor search_floor (String input) {
		for (int i = 0;i<floor_list.size();i++) {
			if (floor_list.get(i).get_floor_num_string().equals(input)) {
				return floor_list.get(i);
			}
		}
		System.out.println("Could not find..");
		return null;
	}
	
    /**
	 * @return	the building name
	 */
	public String get_building_name() {
		return this.buildingName;
	}
    
	/**
	 * 
	 * @return	favorite object
	 */
	public favorites get_fav() {
		return this.fav;
	}

	/**
	 * @param target	the name of the floor adding
	 * @param im		the address of the image adding
	 * @param fav		the favorite obj
	 * create a new floor base on the data given and add it to the floor list
	 */
	public void add_floor_string (String target, String im, favorites fav) {
		
		int floor_num = Integer.parseInt(target);
		floor newFloor = new floor(fav,im, floor_num);
		if (floor_list.get(0).get_floor_num()==0) {
			floor_list.set(0, newFloor);
			return;
		}
		floor_list.add(newFloor);
		num_of_floor++;
		
	}
	
    /**
	 * @param	target		the floor willing to delete
	 * remove the target floor from the list
	 */
	public void remove_floor(String target) {
		for (int i = 0; i<floor_list.size();i++) {
			if (floor_list.get(i).get_floor_num_string().equals(target)) {
				floor_list.remove(floor_list.get(i));
				return;
			}
		}
		System.out.println("Error..could not find the floor to delete..");
		
	}
	
	/**
	 * @param target	the name of the floor changing
	 * @param newName	the new name of the floor
	 * change the name of the floor
	 */
	public void change_floor (String target, String newName) {
		for (int i = 0; i<floor_list.size();i++) {
			if (floor_list.get(i).get_floor_num_string().equals(target)) {
				floor_list.get(i).set_floor_name(newName);
				return;
			}
		}
		System.out.println("Error..could not find the floor to delete..");
	}
	
	/**
	 * 
	 * @return	the list of floor objects
	 */
	public List<floor> get_floor_list (){
		return floor_list;
	}
	

	/**
	 * 
	 * @param newName	the new name of the building
	 * change the name of the building 
	 */
	public void set_building_name (String newName) {
		buildingName = newName;
	}
}
