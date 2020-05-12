package uwo_map_organization_program;

/**
 * a child class of the Room class that will save an eatery
 */
public class Eatery extends Room{
	
    private int rate; // the rate of the eatery
    
    /**
     * @param	room		the room number
     * @param	flo			the floor number of the room
     * @param	buil		the building name of the room
     * @param	position	the x,y coordinate of the room
     * @param	desc		the description of the room
     * @param	r			the rate of the eatery
     * set up a new eatery object
     */
    public Eatery(String room, int flo, String buil, int[] position, String desc, int r) {
        super(room, flo, buil, position, desc);
        rate = r;
    }

    /**
     * @return	the rate of the eatery
     */
    public int getRate(){
        return rate;
    }

    /**
     * @param	r	the new rate of the eatery
     * update the rate of the eatery
     */
    public void setRate(int r){
        rate = r;
    }
}
