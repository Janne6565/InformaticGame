import java.util.ArrayList;

public class Monster
{
    String name;
    String type;
    int level;
    int health;
    int maxHealth;
    EntityEquip equip;
    ArrayList<String> skin;
    Loot loot;

    public Monster(String pName, String pType, int pLevel, int pMaxHealth, EntityEquip pEquip, ArrayList<String> pSkin, Loot pLoot){
        name = pName;
        type = pType;
        level = pLevel;
        maxHealth = pMaxHealth;
        health = pMaxHealth;
        equip = pEquip;
        skin = pSkin;
        loot = pLoot;
    }
    
    public String getName(){
        return name;
    }
    
    public String getType(){
        return type;
    }
    
    public int getLevel(){
        return level;
    }

    public Loot getLoot(){
        return loot;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getMaxHealth(){
        return maxHealth;
    }
    
    public EntityEquip getEquip(){
        return equip;
    }

    public ArrayList<String> getSkin(){
        return skin;
    }

    public Monster setName(String pName){
        name = pName;
        return this;
    }
    
    public Monster setType(String pType){
        type = pType;
        return this;
    }
    
    public Monster setLevel(int pLevel){
        level = pLevel;
        return this;
    }
    
    public Monster setHealth(int pHealth){
        health = pHealth;
        return this;
    }
    
    public Monster setMaxHealth(int pMaxHealth){
        maxHealth = pMaxHealth;
        return this;
    }
    
    public Monster setEquip(EntityEquip pEquip){
        equip = pEquip;
        return this;
    }

    public Monster setSkin(ArrayList<String> pSkin){
        skin = pSkin;
        return this;
    }

    public Monster setLoot(Loot pLoot){
        loot = pLoot;
        return this;
    }

    public boolean damage(float damage){
        health -= damage;
        if (health > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isAlive(){
        if (health > 0){
            return true;
        }else{
            return false;
        }
    }
}