package uwo_map_organization_program;

/**
 * save the data of a classroom
 *
 */
public class Classroom extends Room {
	
    private int maximum_seats; // the maximum seats of the classroom
    
    /**
     * @param	room		the room number
     * @param	flo			the floor number of the classroom
     * @param	buil		the building name of the classroom
     * @param	position	the x,y coordinate of the classroom
     * @param	desc		the description of the classroom
     * @param	max			the maximum seats of the classroom
     * set up a new classroom object
     */
    public Classroom(String room, int flo, String buil, int[] position, String desc, int max) {
        super(room, flo, buil, position, desc);
        maximum_seats = max;
    }
    
    /**
     * @return the maximum seats for the classroom
     */
    public int getMaximum_seats(){
        return maximum_seats;
    }

    /**
     * 
     * @param max	the new maximum seats 
     * update the maximum seats for the classroom
     */
    public void setMaximum_seats(int max){
        maximum_seats = max;
    }
}
