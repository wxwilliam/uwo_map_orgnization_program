package uwo_map_organization_program;

public class Room {
    private String room_number;
    private int floor;
    private String building;
    private int x_coordinate;
    private int y_coordinate;
    private String description;

    /**
     * 
     * @param room		the room number
     * @param flo		the floor number
     * @param buil		the name of the building
     * @param position	the x,y coordinate
     * @param desc		the description of the room
     * 
     * create a new room
     */
    public Room(String room, int flo, String buil, int[] position, String desc){
        room_number = room;
        floor = flo;
        building = buil;
        x_coordinate = position[0];
        y_coordinate = position[1];
        description = desc;

    }
    
    /**
     * 
     * @return	the room number
     */
    public String get_roomNumber(){
        return room_number;
    }

    
    /**
     * 
     * @return	the floor of the room
     */
    public int get_floor(){
        return floor;
    }

    /**
     * 
     * @return	the name of the building
     */
    public String get_building(){
        return building;
    }

    /**
     * @return the x coordinate
     */
    public int get_x () {
    	return x_coordinate;
    }
    
    /**
     * 
     * @return	the y coordinate
     */
    public int get_y () {
    	return y_coordinate;
    }

    /**
     * 
     * @return	the description of the room
     */
    public String get_description(){
        return description;
    }

    /**
     * 
     * @param num	the new room number
     * update the room number
     */
    public void setRoom_number(String num){
        room_number = num;
    }

    /**
     * 
     * @param num	the new floor 
     * update the floor 
     */
    public void setFloor(int num){
        floor = num;
    }

    /**
     * 
     * @param str	the new building name
     * update the building name 
     */
    public void setBuilding(String str){
        building = str;
    }

    /**
     * 
     * @param position	the new x,y coordinate
     * update the room coordinate
     */
    public void setPosition(int[] position){
        x_coordinate = position[0];
        y_coordinate = position[1];
    }

    /**
     * 
     * @param des	the new description
     * update the room description
     */
    public void setDescription(String des){
        description = des;
    }

}
