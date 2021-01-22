import java.util.ArrayList;
public class EntityEquip{
    private Armor armorHead;
    private Armor armorBelly;
    private Armor armorLegs;
    private Armor armorFeets;
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<ItemStack> items = new ArrayList<>();

    public EntityEquip(){

    }

    public EntityEquip(Armor pHead, Armor pBelly, Armor pLegs, Armor pFeets, ArrayList<Weapon> pWeapon, ArrayList<ItemStack> pItems){
        armorHead = pHead;
        armorBelly = pBelly;
        armorLegs = pLegs;
        armorFeets = pFeets;
        weapons = pWeapon;
        items = pItems;
    }
    
    public ArrayList<Armor> getArmor(){
        ArrayList<Armor> armor = new ArrayList<>();
        armor.add(armorHead);
        armor.add(armorBelly);
        armor.add(armorLegs);
        armor.add(armorFeets);
        return armor;
    }
    
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }

    public ArrayList<ItemStack> getItems(){
        return items;
    }

    public EntityEquip setItems(ArrayList<ItemStack> pItems){
        items = pItems;
        return this;
    }

    public EntityEquip addItem(ItemStack item){
        items.add(item);
        return this;
    }
    
    public void setArmor(String position, Armor pArmor){
        switch (position){
            case "head":
                armorHead = pArmor;
                break;
            case "belly":
                armorBelly = pArmor;
                break;
            case "legs":
                armorLegs = pArmor;
                break;
            case "feets":
                armorFeets = pArmor;
                break;
        }
    }

    public void setWeapons(ArrayList<Weapon> pWeapon){
        weapons = pWeapon;
    }

    public boolean addWeapon(Weapon pWeapon){
        weapons.add(pWeapon);
        return false;
    }
}
