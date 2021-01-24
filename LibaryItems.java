import java.util.ArrayList;

public class LibaryItems {  // Bibliothek mit Items

    public static ItemStack bone;
    public static ItemStack rottenFlash;
    public static ItemStack string;
    public static ItemStack scrap;
    public static ItemStack snakeSkin;


    public LibaryItems(){
        ArrayList<String> boneIcon = new ArrayList<>();
        boneIcon.add("    _____   ");
        boneIcon.add("   |#####|  ");
        boneIcon.add("    |###|   ");
        boneIcon.add("   |###|    ");
        boneIcon.add("  |#####|   ");


        bone = new ItemStack("Knochen", 1, boneIcon, 5);

        ArrayList<String> rottenFlashIcon = new ArrayList<>();
        rottenFlashIcon.add("    ## #     ");
        rottenFlashIcon.add("   #XX#X#     ");
        rottenFlashIcon.add("  #XXXXXX#     ");
        rottenFlashIcon.add("  #XXXXXX#     ");
        rottenFlashIcon.add("   #XXXX#     ");
        rottenFlashIcon.add("    #XX#     ");

        rottenFlash = new ItemStack("Verrottetes Fleisch", 1, rottenFlashIcon, 1);

        ArrayList<String> stringIcon = new ArrayList<>();
        stringIcon.add("   ____      ");
        stringIcon.add(" ___  ___ ");
        stringIcon.add("  ___      ");
        stringIcon.add("    ___      ");
        stringIcon.add("   ___      ");

        string = new ItemStack("Faden", 1, stringIcon, 20);

        ArrayList<String> IconScrap = new ArrayList<>();
        stringIcon.add("   |######| ");
        stringIcon.add(" |#######| ");
        stringIcon.add(" |#######|  ");
        stringIcon.add("|#######|   ");
        stringIcon.add("|#######|  ");

        scrap = new ItemStack("Schrott", 1, stringIcon, 1);

        ArrayList<String> iconSnakeSkin = new ArrayList<>();
    }
}
