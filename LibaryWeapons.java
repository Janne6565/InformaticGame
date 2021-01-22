import java.util.ArrayList;

public class LibaryWeapons {
    public static Weapon oldDagger;
    public static Weapon stick;
    public static Weapon longSword;
    public static Weapon scythe;
    public static Weapon knife;
    public static Weapon whip;

    public LibaryWeapons(){
        ArrayList<String> iconDagger = new ArrayList<>();
        iconDagger.add("    _    ");
        iconDagger.add("   | |   ");
        iconDagger.add(" __| |__ ");
        iconDagger.add(" _______ ");
        iconDagger.add("   |_|   ");
        oldDagger = new Weapon("Alter Dolch", 5, 10, 50, iconDagger);
        ArrayList<String> infoDagger = new ArrayList<>();
        infoDagger.add("Ein kleiner alter Dolch");
        oldDagger.setInfo(infoDagger);
        oldDagger.setDurability((float) 0.7);

        ArrayList<String> iconStick = new ArrayList<>();
        iconStick.add("   |_");
        iconStick.add("   |");
        iconStick.add(" __|");
        iconStick.add("   |");
        iconStick.add("  _|");
        iconStick.add("   |---");
        stick = new Weapon("Stick", 1, 10, 2, iconStick);

        ArrayList<String> iconLongSword = new ArrayList<>();
        iconLongSword.add("    _    ");
        iconLongSword.add("   | |    ");
        iconLongSword.add("   | |    ");
        iconLongSword.add("   | |    ");
        iconLongSword.add("   | |    ");
        iconLongSword.add("  _| |_   ");
        iconLongSword.add(" |_____|  ");
        iconLongSword.add("   |_|    ");
        longSword = new Weapon("Langschwert", 10, 4, 40, iconLongSword);

        ArrayList<String> iconScythe = new ArrayList<>();
        iconScythe.add("   ________   ");
        iconScythe.add("  |___| |___|");
        iconScythe.add(" |___|   ");
        iconScythe.add("|__|   ");
        iconScythe.add("|__|  ");
        iconScythe.add("|__| ");
        iconScythe.add("|__|");
        iconScythe.add("|__| ");
        scythe = new Weapon("Sense", 20, 1, 60, iconScythe);

        ArrayList<String> iconKnife = new ArrayList<>();
        iconKnife.add("   _______");
        iconKnife.add("  |    _|");
        iconKnife.add("  |   _|");
        iconKnife.add("  |  _|");
        iconKnife.add("  |  |");
        iconKnife.add("  |  |");
        iconKnife.add("  | _|");
        knife = new Weapon("Messer", 10,1, 1, iconKnife);

        ArrayList<String> iconWhip = new ArrayList<>();
        iconWhip.add("           ||        ");
        iconWhip.add("          ||         ");
        iconWhip.add("         ||          ");
        iconWhip.add("         ||          ");
        iconWhip.add("        ||           ");
        iconWhip.add("        ||           ");
        iconWhip.add("       ||            ");
        iconWhip.add("       ||            ");
        iconWhip.add("      |##|           ");
        iconWhip.add("      |##|           ");
        whip = new Weapon("Peitsche", 15, 20, 2, iconWhip);
    }

}
