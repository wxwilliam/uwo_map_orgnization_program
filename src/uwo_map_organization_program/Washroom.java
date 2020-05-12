package uwo_map_organization_program;

public class Washroom extends Room{
    private String type;
    public Washroom(String room, int flo, String buil, int[] position, String desc, String ty) {
        super(room, flo, buil, position, desc);
        type = ty;
    }
    public String getType(){
        return type;
    }

    public void setType(String ty){
        type = ty;
    }
}
