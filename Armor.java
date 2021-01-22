public class Armor{

    private String name;
    private Material material;
    private float durability = 1;
    
    public Armor(String pName, Material pMaterial){
       name = pName;
       material = pMaterial;
    }
    
    public String getName(){
        return name;
    }
    
    public Material getMaterial(){
        return material;
    }
    
    public float getDurability(){
        return durability;
    }
    
    public void setName(String pName){
        name = pName;
    }

    public void setMaterial(Material pMaterial){
        material = pMaterial;
    }
    
    public void setDurability(float pDurability){
        durability = pDurability;
    }
}
