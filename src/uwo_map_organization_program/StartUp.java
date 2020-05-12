package uwo_map_organization_program;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StartUp {

	List<Building> building_list;
	favorites fav;
	MetaData start;
	
	/**
	 * read the json files and import the building with it's floors and rooms into the list
	 */
	public StartUp() {
		start = new MetaData();
		fav = new favorites();
		building_list = new ArrayList<Building>();
		JSONArray inputJsonArray = start.readBuildingArray("/json/superjson.json");
		for (Object temp2:inputJsonArray) {
			JSONObject inputJson = (JSONObject) temp2;
			String buildingName = (String) inputJson.get("building name");
			Building build = new Building(buildingName,fav);
			building_list.add(build);
			Long tempNum = (Long)inputJson.get("numOfFloor");
			Integer temppNum = new Integer (tempNum.intValue());
			int numOfFloor = temppNum.intValue();
			JSONArray floor_index = (JSONArray) inputJson.get("floor numbers");
			for (Object temp:floor_index) {
				Long tempNum2 = (Long) temp;
				Integer temppNum2 = new Integer (tempNum2.intValue());
				int current_floor = temppNum2.intValue();
				List<Room> room_list = start.getFloor(inputJson, current_floor);
				String img = start.getMap(inputJson, current_floor);
				floor floor_obj = new floor(room_list,fav,img, current_floor);
				build.add_floor(floor_obj);

			}
		}
		
	
		
	}
	
	/**
	 * 
	 * @return	the list of the name of the buildings 
	 */
	public String[] get_building_list() {
		String[] result = new String[building_list.size()];
		int counter = 0;
		for (int i = 0; i<building_list.size();i++) {
			result[counter] = building_list.get(i).get_building_name();
			counter++;
		}
		return result;
	}
	
	/**
	 * 
	 * @param target	the building want to search
	 * @return	the building object of the name given
	 */
	public Building search_building (String target) {
		for (int i = 0; i<building_list.size();i++) {
			if (building_list.get(i).get_building_name().equals(target)) {
				return building_list.get(i);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param target	the name of the building
	 * add a new building to the list
	 */
	public void add_building(String target) {
		Building newBuilding = new Building(target,fav);
		building_list.add(newBuilding);
		start.reWrite(building_list);
	}
	
	/**
	 * 
	 * @param target	the target building name
	 * @param newName	the new name of the building
	 * change the building name
	 */
	public void change_building (String target, String newName) {
		for (int i = 0;i<building_list.size();i++) {
			if (building_list.get(i).get_building_name().equals(target)) {
				building_list.get(i).set_building_name(newName);
				start.reWrite(building_list);
				return;
			}
		}
	}
	
	/**
	 * 
	 * @param target	the target building name
	 * remove the target building from the list
	 */
	public void remove_building (String target) {
		for (int i = 0;i<building_list.size();i++) {
			if (building_list.get(i).get_building_name().equals(target)) {
				building_list.remove(building_list.get(i));
				start.reWrite(building_list);
				return;
			}
		}
		System.out.println("cannot find the building to delete..");
	}
	
	/**
	 * 
	 * @param building	the building name
	 * @param target	the floor name 
	 * @param im		the address of the image
	 * 
	 * add a new floor to the building given
	 */
	public void add_floor (String building, String target, String im) {
		Building build = this.search_building(building);
		if (build == null) {
			System.out.println("Cannot find the building..");
			return;
		}
		build.add_floor_string(target, im,fav);
		start.reWrite(building_list);
	}
	
	/**
	 * 
	 * @param building	the target building
	 * @param target	the target floor
	 * 
	 * remove the floor from the building
	 */
	public void remove_floor (String building, String target) {
		Building build = this.search_building(building);
		if (build == null) {
			System.out.println("Cannot find the building..");
			return;
		}
		build.remove_floor(target);
		start.reWrite(building_list);
	}
	
	/**
	 * 
	 * @param building		the target building
	 * @param target		the target floor
	 * @param newName		the new name of the floor
	 * change the name of the floor
	 */
	public void change_floor (String building, String target, String newName) {
		Building build = this.search_building(building);
		if (build == null) {
			System.out.println("Cannot find the building..");
			return;
		}
		build.change_floor(target, newName);
		start.reWrite(building_list);
	}
	
	/**
	 * 
	 * @param building	the target building
	 * @param floor		the target floor
	 * @param target	the target room
	 * @param xy		the x,y coordinate
	 * add a new room to the list
	 */
	public void add_room (String building, String floor, String target, int[] xy) {
		Building build = this.search_building(building);
		if (build == null) {
			System.out.println("Cannot find the building..");
			return;
		}
		floor target_floor = build.search_floor(floor);
		target_floor.add_room(building, floor, target, xy);
		start.reWrite(building_list);
		
	}
	
	/**
	 * 
	 * @param building	the target building
	 * @param floor		the target floor
	 * @param target	the target room
	 * 
	 * remove the room from the list
	 */
	public void remove_room (String building, String floor, String target) {
		Building build = this.search_building(building);
		if (build == null) {
			System.out.println("Cannot find the building..");
			return;
		}
		floor target_floor = build.search_floor(floor);
		target_floor.remove_room(target);
		start.reWrite(building_list);
	}
	
	/**
	 * 
	 * @param building	the target building
	 * @param floor		the target floor
	 * @param target	the target room
	 * @param newName	the new name of the room
	 * 
	 * change the name of the room
	 */
	public void change_room (String building, String floor, String target, String newName) {
		Building build = this.search_building(building);
		if (build == null) {
			System.out.println("Cannot find the building..");
			return;
		}
		floor target_floor = build.search_floor(floor);
		Room target_room = target_floor.search(target);
		target_room.setRoom_number(newName);
		start.reWrite(building_list);
	}
	
	/**
	 * 
	 * @return	the favorite object
	 */
	public favorites get_fav () {
		return fav;
	}
	
	/**
	 * rewrite the json file
	 */
	public void rewrite () {
		start.reWrite(building_list);
	}
	

	
}
