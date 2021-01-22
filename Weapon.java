import java.util.ArrayList;

public class Weapon
{
    private String name;
    private int damage;
    private float durability = 1;
    private float breakability;
    private ArrayList<String> icon;
    private int strength;
    private ArrayList<String> info;

    public Weapon(String pName, int pDamage, float pbreakability, int pStrength, ArrayList<String> pIcon){
        name = pName;
        damage = pDamage;
        breakability = pbreakability;
        icon = pIcon;
        strength = pStrength;
    }
    
    public boolean use(){
        durability -= breakability / 100;
        if (durability > 0.5){
            return true;
        }
        return false;
    }
    
    public String getName() {
        return name;
    }
    
    public int getDamage(){
        return damage;
    }
    
    public float getDurability(){
        return durability;
    }
    
    public float getBreakability(){
        return breakability;
    }

    public int getStrength(){
        return strength;
    }

    public ArrayList<String> getIcon(){
        return icon;
    }

    public ArrayList<String> getInfo(){
        return info;
    }
    
    public Weapon setDamage(int pDamage){
        damage = pDamage;
        return this;
    }
    
    public Weapon setKockback(int pbreakability){
        breakability = pbreakability;
        return this;
    }

    public Weapon setStrength(int pStrength){
        strength = pStrength;
        return this;
    }
    
    public Weapon setName(String pName){
        name = pName;
        return this;
    }

    public Weapon setIcon(ArrayList<String> pIcon){
        icon = pIcon;
        return this;
    }

    public Weapon setDurability(float pDurability){
        durability = pDurability;
        return this;
    }

    public Weapon setInfo(ArrayList<String> pInfo){
        info = pInfo;
        return this;
    }
}
