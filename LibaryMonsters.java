import java.util.ArrayList;

public class LibaryMonsters { // Monster Bibliothek
    public static Monster oldZombie;
    public static Monster skelleton;
    public static Monster spider;

    private static Wuerfel wuerfel = new Wuerfel(1, 3);

    private static ArrayList<Monster> monster = new ArrayList<>();
    public LibaryMonsters(){
        LibaryWeapons weaponsLibary = new LibaryWeapons();
        ArrayList<Weapon> weapons = new ArrayList<>();
        wuerfel.setMin(1);
        wuerfel.setMax(5);
        weapons.add(weaponsLibary.stick);

        EntityEquip equipOldZombie = new EntityEquip(null, null, new Armor("Alte Lederhose", new Material("Leder", 1)), null, weapons, null);
        ArrayList<String> skinOldZombie = new ArrayList<>();
        skinOldZombie.add("  _____                      ");
        skinOldZombie.add("  |* *|                      ");
        skinOldZombie.add("  |___|                      ");
        skinOldZombie.add("   _||_                      ");
        skinOldZombie.add("  |    |                     ");
        skinOldZombie.add("--|    |--                   ");
        skinOldZombie.add("  |    |                     ");
        skinOldZombie.add("  |    |                     ");
        skinOldZombie.add("  |____|                     ");
        skinOldZombie.add("   ||||                      ");
        skinOldZombie.add("   ||||                      ");
        Loot lootOldZombie = new Loot();
        LibaryItems items = new LibaryItems();
        lootOldZombie.getDrops().add(items.rottenFlash.setCount(2));
        oldZombie = new Monster("Alter Zombie", "Nahkampf", 1, 20, equipOldZombie, skinOldZombie, lootOldZombie);
        oldZombie.setHealth(5);
        monster.add(oldZombie);

        ArrayList<Weapon> weaponsSkellet = new ArrayList<>();
        weaponsSkellet.add(weaponsLibary.longSword);
        EntityEquip equipSkelleton = new EntityEquip(null, null, null, null, weaponsSkellet, null);
        ArrayList<String> skinSkelleton = new ArrayList<>();
        skinSkelleton.add("    _______                      ");
        skinSkelleton.add("    | x x |                      ");
        skinSkelleton.add("    | __  |                      ");
        skinSkelleton.add("    |_____|                      ");
        skinSkelleton.add("     __||__                     ");
        skinSkelleton.add("   || o  x ||                      ");
        skinSkelleton.add("  | | -  | | |                    ");
        skinSkelleton.add(" |  ||     |  |                   ");
        skinSkelleton.add(" |  | -  o |  |                   ");
        skinSkelleton.add("    |  | - |                      ");
        skinSkelleton.add("    |______|                      ");
        skinSkelleton.add("     | || |                      ");
        skinSkelleton.add("     | || |                      ");
        skinSkelleton.add("     | || |                      ");
        Loot lootSkelleton = new Loot();
        lootSkelleton.getDrops().add(items.bone.setCount(wuerfel.wuerfeln()));
        skelleton = new Monster("Skellet", "Nahkampf", 1, 20, equipSkelleton, skinSkelleton, lootSkelleton);
        monster.add(skelleton);

        ArrayList<String> iconSpider = new ArrayList<>();
        iconSpider.add("                  ");
        iconSpider.add("                  ");
        iconSpider.add("                  ");
        iconSpider.add("                  ");
        iconSpider.add("                    ");
        iconSpider.add("    _______          ");
        iconSpider.add("   | x   x |         ");
        iconSpider.add("   |_______|         ");
        iconSpider.add("  ###     ###      ");
        iconSpider.add(" ###       ###     ");
        iconSpider.add("####       ####  ");
        Loot lootSpider = new Loot();
        lootSpider.addDrop(LibaryItems.string.setCount(wuerfel.wuerfeln()));
        ArrayList<Weapon> weaponsSpider = new ArrayList<>();
        weaponsSpider.add(weaponsLibary.whip);
        EntityEquip equipSpider = new EntityEquip(null, null, null, null, weapons, null);
        spider = new Monster("Spinne", "Nahkampf", 1, 25, equipSpider, iconSpider, lootSpider);
        monster.add(spider);
    }

    public static Monster getRandomMonster(int level){
        wuerfel.setMax(monster.size()-1);
        wuerfel.setMin(0);
        return monster.get(wuerfel.wuerfeln());
    }
}
