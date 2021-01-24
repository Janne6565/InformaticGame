public class Held 
{
    private String name;
    private int level;
    private int health;
    private int maxHealth;
    private EntityEquip equip = new EntityEquip();
    private int credits = 0;

    public Held(String pName, int pLevel, int pHealth, int pMaxHealth){
      name = pName;
      level = pLevel;
      health = pHealth;
      maxHealth = pMaxHealth;
    }

    public float getAttackDamage(int slot) { // Berechnet den Schaden
      return (float) (equip.getWeapons().get(slot).getDamage() + (double) equip.getWeapons().get(slot).getDamage() * level * 0.1);
    }

    public boolean damage(int damage){ // Fügt schaden hinzu und returned ob es überlebt hat
        health -= damage;
        System.out.println(health + " und " + damage);
        if (health > 0){
            return true;
        }else{
            return false;
        }
    }

    public int getCredits(){
        return credits;
    }

    public String getName(){
        return name;
    }
    
    public int getLevel(){
        return level;
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
    
    public Held setName(String pName){
        name = pName;
        return this;
    }
    
    public Held setLevel(int pLevel){
        level = pLevel;
        return this;
    }
    
    public Held setHealth(int pHealth){
        health = pHealth;
        return this;
    }
    
    public Held setMaxHealth(int pMaxHealth){
        maxHealth = pMaxHealth;
        return this;
    }
    
    public Held setEquip(EntityEquip pEquip){
        equip = pEquip;
        return this;
    }

    public Held setCredits(int pCredits){
        credits = pCredits;
        return this;
    }

} 
