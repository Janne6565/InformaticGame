public class Material{
    private String name;
    private int strength;
    
    public Material(String pName, int pStrength){
        name = pName;
        strength = pStrength;
    }
    
    public void setStrength(int pStrength){
        strength = pStrength;
    }
    
    public void setName(String pName){
        name = pName;
    }
    
    public int getStrength(){
        return strength;
    }
    
    public String getName(){
        return name;
    }
}
