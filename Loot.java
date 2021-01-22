import java.util.ArrayList;

public class Loot {
    ArrayList<ItemStack> drops = new ArrayList<>();

    public Loot(){

    }

    public ArrayList<ItemStack> getDrops(){
        return drops;
    }

    public Loot setDrops(ArrayList<ItemStack> pDrops){
        drops = pDrops;
        return this;
    }

    public Loot addDrop(ItemStack pItem){
        drops.add(pItem);
        return this;
    }

    public Loot clear(){
        drops.clear();
        return this;
    }
}
