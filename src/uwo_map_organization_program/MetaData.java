package uwo_map_organization_program;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MetaData {

    /**
     * Generate JSON output file
     * @param: JSONObj that want to store (normally is buildingJSON)
     */
    public static void writeJSON(JSONObject jsonObject){
        try (FileWriter file = new FileWriter("rooms.json")) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate a JSONObject (building)
     * @param numbers: List of levels
     * @param floors: List of JSONObject (roomJSONList)
     * @return : JSONObject (building)
     */
    public static JSONObject buildingJSON(String buildingName, int numOfFloor,int[] numbers, ArrayList<JSONObject> floors){
        JSONObject building = new JSONObject();
        building.put("building name",buildingName);
        building.put("numOfFloor", numOfFloor);

        JSONArray numberList = new JSONArray();
        for (int i = 0; i < numbers.length; i++){
            numberList.add(numbers[i]);
        }
        building.put("floor numbers",numberList);
        JSONArray floorList = new JSONArray();

        for (int j = 0; j < floors.size(); j++){
            floorList.add(floors.get(j));
        }
        building.put("floor list", floorList);
        return building;
    }

    /**
     * create JSON object that contains list of rooms
     * @param roomList: List of Rooms objects
     * @return JSON obj
     */
    public static JSONObject roomJSONList(List<Room> roomList, String map, int level){
        JSONArray roomArray =  new JSONArray();
        JSONObject roomJSONList = new JSONObject();
        for (int i = 0; i < roomList.size(); i++){// Generate list of Room objects
            Room target = roomList.get(i);
            String roomNum = target.get_roomNumber();
            int floor = target.get_floor();
            String building = target.get_building();
            int x = target.get_x();
            int y = target.get_y();
            String description = target.get_description();
            JSONObject roomObj = roomJSON(roomNum, floor, building, x, y, description);

            JSONObject roomDetails = new JSONObject();
            roomDetails.put("roomNumber", roomNum);
            roomDetails.put("floor", floor);
            roomDetails.put("building", building);
            roomDetails.put("x_coordinate", x);
            roomDetails.put("y_coordinate", y);
            roomDetails.put("description", description);
            // Special rooms
            if (description.equals("Classroom")){
                Classroom replaced = (Classroom) target;
                int tempmax = replaced.getMaximum_seats();
                roomDetails.put("maximum_seats",tempmax);
            } else if (description.equals("Eatery")){
                Eatery replaced = (Eatery) target;
                int tempmax = replaced.getRate();
                roomDetails.put("rate",tempmax);
            }  else if (description.equals("Elevator")){
                Elevator replaced = (Elevator) target;
                int tempmax = replaced.getMaximum_weight();
                roomDetails.put("maximum_weight",tempmax);
            } else if (description.equals("Washroom")){
                Washroom replaced = (Washroom) target;
                String tempmax = replaced.getType();
                roomDetails.put("type",tempmax);
            }
            JSONObject roomObject = new JSONObject();
            roomObject.put("room", roomDetails);
            roomArray.add(roomObject);
        }
        roomJSONList.put("floor map",map);
        roomJSONList.put("floor level",level);
        roomJSONList.put("floor",roomArray);
        return roomJSONList;
    }

    /**
     *
     * @param roomNumber: room number
     * @param floor: which floor
     * @param building: which building
     * @param x_coordinate: x coordinate
     * @param y_coordinate: y coordinate
     * @param description: type of room
     * @return: room JSON object
     */
    public static JSONObject roomJSON(String roomNumber, int floor, String building, int x_coordinate, int y_coordinate, String description){
        JSONObject roomDetails = new JSONObject();
        roomDetails.put("roomNumber", roomNumber);
        roomDetails.put("floor", floor);
        roomDetails.put("building", building);
        roomDetails.put("x_coordinate", x_coordinate);
        roomDetails.put("y_coordinate", y_coordinate);
        roomDetails.put("description", description);

        JSONObject roomObject = new JSONObject();
        roomObject.put("room", roomDetails);

        return roomObject;
    }

    /**
     * Read file name, and return JSONObj
     * @return: A JSONObject which is building
     */
    public static JSONObject readBuilding(String file) {
    	URL url = MetaData.class.getResource(file);
        JSONParser jsonParser = new JSONParser();
        JSONObject building = new JSONObject();

        try (FileReader reader = new FileReader(url.getPath())) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            building = (JSONObject) obj;

        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return building;
    }

    /**
     *
     * @param building: which building you want to see
     * @return: list of floors in that building
     */
    public static JSONArray getFloorList ( JSONObject building){
        JSONArray floor_list = (JSONArray) building.get("floor numbers");
        return floor_list;
    }


    /**
     *
     * @param building: which building
     * @param floorNum: which floor
     * @return: address of the map
     */
    public static String getMap (JSONObject building, int floorNum){
        String map="";
        JSONArray floorList = (JSONArray) building.get("floor list");
        for (Object flo : floorList) {
            JSONObject floor = (JSONObject) flo;
            
            Long templevel1 = (Long)floor.get("floor level");
    		Integer templevel = new Integer (templevel1.intValue());
    		int level = templevel.intValue();
            
            if (floorNum == level){
                map = (String) floor.get("floor map");
            }
        }
        return map;
    }


    /**
     *
     * @param building: which building
     * @param floorNum: which floor
     * @return: list of Room object in that floor
     */
    public static List<Room> getFloor(JSONObject building, int floorNum){
        // Get list of floors
        JSONArray floorList = (JSONArray) building.get("floor list");
        List<Room> roomList = new ArrayList<Room>();
        for (Object flo : floorList) {// Search floor number
            JSONObject floor = (JSONObject) flo;// Get list of rooms
            
            Long tempNum = (Long)floor.get("floor level");
    		Integer temppNum = new Integer (tempNum.intValue());
    		int tempppNum = temppNum.intValue();
    		
            if (floorNum == tempppNum) {// Floor number matched
                JSONArray roomJSONList = (JSONArray) floor.get("floor");
                for (Object obj : roomJSONList) {// For each room...
                	JSONObject tempJSONroom = (JSONObject) obj;
                    JSONObject JSONroom = (JSONObject) tempJSONroom.get("room");// Get room info

                    // Create Room objects
                    String roomNum = (String) JSONroom.get("roomNumber");
                    
                    Long tempFloor1 = (Long)JSONroom.get("floor");
            		Integer temppFloor = new Integer (tempFloor1.intValue());
            		int floorNumber = temppNum.intValue();
                    
                    String buildingName = (String) JSONroom.get("building");
                    
                    Long tempx1 = (Long)JSONroom.get("x_coordinate");
            		Integer tempx = new Integer (tempx1.intValue());
            		int x_coordinate = tempx.intValue();
                    
                    Long tempy1 = (Long)JSONroom.get("y_coordinate");
            		Integer tempy = new Integer (tempy1.intValue());
            		int y_coordinate = tempy.intValue();
                    
                    String description = (String) JSONroom.get("description");
                    int[] position = {x_coordinate, y_coordinate};
                    if (description.equals("Classroom")){

                        Long tempmax1 = (Long)JSONroom.get("maximum_seats");
                		Integer tempmax = new Integer (tempmax1.intValue());
                		int max = tempmax.intValue();

                        Classroom room = new Classroom(roomNum,floorNum,buildingName,position,description,max);
                        roomList.add(room);
                    } else if (description.equals("Eatery")){

                        Long temprate1 = (Long)JSONroom.get("rate");
                		Integer temprate = new Integer (temprate1.intValue());
                		int rate = temprate.intValue();

                        Eatery room = new Eatery(roomNum,floorNum,buildingName,position,description,rate);
                        roomList.add(room);
                    }  else if (description.equals("Elevator")){

                        Long tempmax1 = (Long)JSONroom.get("maximum_weight");
                		Integer tempmax = new Integer (tempmax1.intValue());
                		int max = tempmax.intValue();

                        Elevator room = new Elevator(roomNum,floorNum,buildingName,position,description,max);
                        roomList.add(room);
                    } else if (description.equals("Washroom")){
                        String ty = (String) JSONroom.get("type");
                        Washroom room = new Washroom(roomNum,floorNum,buildingName,position,description,ty);
                        roomList.add(room);
                    } else {
                        Room room = new Room(roomNum, floorNum, buildingName, position, description);
                        roomList.add(room);
                    }
                }
            }

        }
        return roomList;
    }

    /**
     *
     * @param file: file name
     * @return: array of bulidings
     */
    public static JSONArray readBuildingArray(String file){
        URL url = MetaData.class.getResource(file);
        JSONParser jsonParser = new JSONParser();
        JSONArray array = new JSONArray();

        try (FileReader reader = new FileReader(url.getPath())) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            array = (JSONArray) obj;
                    return array;
            } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * Write JSONArray into a file
     * @param jsonArray: inpiuts
     * @param fileName: file location
     */
    public static void writeJSONArr(JSONArray jsonArray, String fileName){
        URL url = MetaData.class.getResource("/json");
        File loca = new File(url.getPath()+"/"+fileName);
        try (FileWriter file = new FileWriter(loca)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Rewrite/ Update JSON file
     * @param buildList: list of Buildings
     */
    public static void reWrite(List<Building> buildList){
        JSONArray big_json = new JSONArray();
        for (int i = 0; i < buildList.size(); i++){
            Building eachBuilding = buildList.get(i);
            List<floor> floorList = eachBuilding.get_floor_list();
            ArrayList<JSONObject> roomJSONArray = new ArrayList<JSONObject>();
            int numOfFloor = eachBuilding.get_num_of_floor();
            String buildingName = eachBuilding.get_building_name();
            int [] index = eachBuilding.get_floor_num_list_int();
            for (int j = 0; j <floorList.size(); j++){
                floor eachFloor = floorList.get(j);
                String map = eachFloor.get_img();
                int level = eachFloor.get_floor_num();
                List<Room> roomList = eachFloor.get_rooms();
                JSONObject roomsJSON = roomJSONList(roomList,map,level);
                roomJSONArray.add(roomsJSON);
            }
            JSONObject buildingJSON = buildingJSON(buildingName, numOfFloor, index, roomJSONArray);
            big_json.add(buildingJSON);
        }
        writeJSONArr(big_json,"superjson.json");
    }
}