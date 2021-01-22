import java.util.ArrayList;

public class ItemStack {
    String name;
    int count;
    ArrayList<String> icon;
    int worth;

    public ItemStack(String pName, int pCount, ArrayList<String> pIcon, int pWorth){
        name = pName;
        count = pCount;
        icon = pIcon;
        worth = pWorth;
    }

    public String getName(){
        return name;
    }

    public int getCount(){
        return count;
    }

    public ArrayList<String> getIcon(){
        return icon;
    }

    public int getWorth(){
        return worth;
    }

    public ItemStack setName(String pName){
        name = pName;
        return this;
    }

    public ItemStack setCount(int pCount){
        count = pCount;
        return this;
    }

    public ItemStack setIcon(ArrayList<String> pIcon){
        icon = pIcon;
        return this;
    }

    public ItemStack setWorth(int pWorth){
        worth = pWorth;
        return this;
    }
}
