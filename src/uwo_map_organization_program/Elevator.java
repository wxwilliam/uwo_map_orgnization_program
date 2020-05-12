package uwo_map_organization_program;

/**
 * a child class of the Room class that will save an elevator
 */
public class Elevator extends Room{
	
    private int maximum_weight; // the maximum weight of the elevator
    
    /**
     * @param	room		the room number
     * @param	flo			the floor number of the elevator
     * @param	buil		the building name of the elevator
     * @param	position	the x,y coordinate of the elevator
     * @param	desc		the description of the elevator
     * @param	max			the maximum weight of the elevator
     * set up a new elevator object
     */
    public Elevator(String room, int flo, String buil, int[] position, String desc, int max) {
        super(room, flo, buil, position, desc);
        maximum_weight = max;
    }
    
    /**
     * @return	the maximum weight of the elevator
     */
    public int getMaximum_weight(){
        return maximum_weight;
    }

    /**
     * @param	max		the new maximum weight of the elevator
     * update the maximum weight of the elevator
     */
    public void setMaximum_weight(int max){
        maximum_weight = max;
    }
}
