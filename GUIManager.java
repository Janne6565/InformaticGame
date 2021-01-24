import java.util.ArrayList;
import java.util.Scanner;

public class GUIManager {  // Dies ist die Klasse, die dafür zuständig ist, das komplette UI zu übernehmen

    private static int timeBetweenAnimation = 500;
    static Scanner scanner = new Scanner(System.in);
    static Wuerfel wuerfel = new Wuerfel(0,1);
    public static void main(String[] args) {

    }

    public static void openMainMenu(Held he) throws InterruptedException { // Öffnet das Haupmenü
        ArrayList<String> optionOne = new ArrayList<>();
        optionOne.add("Kampf");
        ArrayList<String> optionTwo = new ArrayList<>();
        optionTwo.add("Items");
        ArrayList<String> optionThree = new ArrayList<>();
        optionThree.add("Shop");
        ArrayList<String> optionFour = new ArrayList<>();
        optionFour.add("Spiel Verlassen");
        ArrayList<ArrayList<String>> options = new ArrayList<>();
        options.add(optionOne);
        options.add(optionTwo);
        options.add(optionThree);
        options.add(optionFour);
        int action = openMenu(options, false); // Öffnet ein Menü mit den Möglichkeiten: optionOne optionTwo optionThree optionFour
        newFrame();
        switch (action){
            case 1:
                kämpfen(he, LibaryMonsters.getRandomMonster(he.getLevel())); // Startet einen Kampf mit einem zufälligen Monster
                break;
            case 2:
                ArrayList<String> listTypes = new ArrayList<>();
                ArrayList<Integer> listPosition = new ArrayList<>();
                ArrayList<ItemStack> listItems = new ArrayList<>();

                int count = 0;
                for (ItemStack item : he.getEquip().getItems()){
                    if (listTypes.contains(item.getName())){
                        ItemStack itemNow = listItems.get(listPosition.get(listTypes.indexOf(item.getName())));
                        listItems.set(listPosition.get(listTypes.indexOf(item.getName())),itemNow.setCount(itemNow.getCount() + item.getCount()));
                    }else{
                        listTypes.add(item.getName());
                        listPosition.add(count);
                        listItems.add(item);
                        count++;
                    }
                }

                ArrayList<ArrayList<String>> listAllItems = new ArrayList<>();
                System.out.println("Dein jetztiger Kontostand: " + he.getCredits());
                for (ItemStack item : listItems){
                    ArrayList<String> listWord = new ArrayList<>();
                    listWord.add(item.getCount() + "x " + item.getName());
                    listWord.add(item.getWorth() + "€");
                    listAllItems.add(listWord);
                }
                int itemChosen = openMenu(listAllItems, true); // Öffnet ein Menü mit allen Items
                newFrame();
                if (itemChosen == listAllItems.size()) { // Wenn man zurück will
                    openMainMenu(he);
                }else{
                        System.out.print("\n");
                        System.out.print(listItems.get(itemChosen - 1).getCount() + "x " + listItems.get(itemChosen - 1).getName() + "\n");
                        for (String string : listItems.get(itemChosen - 1).getIcon()) {
                            for (int i = 0; i < 50; i++) { // Zeigt das Icon des Items an
                                if (string.length() > i) {
                                    System.out.print(string.charAt(i));
                                }
                            }
                            System.out.print("\n");
                        }
                        ArrayList<String> sell = new ArrayList<>();
                        sell.add("Verkaufen (" + listItems.get(itemChosen - 1).getWorth() + "€)");
                        ArrayList<ArrayList<String>> list = new ArrayList<>();
                        list.add(sell);
                        int whatToDo = openMenu(list, true); // Listet die Optionen auf
                        newFrame();
                        switch (whatToDo) {
                            case 1:
                                he.setCredits(he.getCredits() + listItems.get(itemChosen - 1).getWorth() * listItems.get(itemChosen - 1).getCount());
                                ArrayList<ItemStack> items = new ArrayList<>();
                                for (ItemStack item : he.getEquip().getItems()) {
                                    if (!item.getName().equals(listItems.get(itemChosen - 1).getName())) {
                                        items.add(item);
                                    }
                                }
                                he.getEquip().setItems(items);

                                for (ItemStack item : he.getEquip().getItems()) {
                                    System.out.println(item.getCount() + "x " + item.getName());
                                }
                                openMainMenu(he);
                                break;
                            case 2:
                                openMainMenu(he);
                        }
                    }
                break;
            case 3: // Wenn man Shop auswählt
                ArrayList<Integer> price = new ArrayList<>();
                ArrayList<Weapon> weapons = new ArrayList<>();
                LibaryWeapons weaponsLib = new LibaryWeapons();
                price.add(2);
                weapons.add(weaponsLib.knife);
                price.add(30);
                weapons.add(weaponsLib.longSword);
                price.add(50);
                weapons.add(weaponsLib.scythe);
                ArrayList<ArrayList<String>> listOptions = new ArrayList<>();
                ArrayList<String> heal = new ArrayList<>();
                heal.add("5€ : Heiltrank");
                listOptions.add(heal);
                int i = 0;
                for (Weapon weapon : weapons){ // Listet die Waffen auf
                    if (weapon != null) {
                        ArrayList<String> option = new ArrayList<>();
                        option.add(price.get(i) + "€: " + weapon.getName());
                        listOptions.add(option);
                    }
                    i++;
                }
                int weaponShop = openMenu(listOptions, true);
                if (weaponShop == 1){ // Für den Heiltrank
                    if (he.getCredits() >= 5) {
                        he.setHealth(100);
                        he.setCredits(he.getCredits() - 5);
                        openMainMenu(he);
                    }else{
                        newFrame();
                        System.out.println("Du hast nicht genug geld");
                        openMainMenu(he);
                    }
                }else{
                    if (weaponShop >= listOptions.size()){
                        openMainMenu(he);
                    }else {
                        newFrame();
                        for (String string : weapons.get(weaponShop - 2).getIcon()) { // Zeigt das Icon der Waffe an
                            System.out.print(string + "\n");
                        }
                        System.out.print("\n" + weapons.get(weaponShop - 2).getName()); // Zeigt den Namen der Waffe an
                        System.out.print(price.get(weaponShop - 2) + "\n"); // Zeigt den Preis der Waffe an
                        ArrayList<String> buy = new ArrayList<>();
                        buy.add("Kaufen");
                        ArrayList<ArrayList<String>> list = new ArrayList<>();
                        list.add(buy);
                        int input = openMenu(list, true);
                        if (input > list.size()) {
                            openMainMenu(he);
                        } else {
                            if (he.getCredits() >= price.get(weaponShop - 2)) {
                                he.setCredits(he.getCredits() - price.get(weaponShop - 2));
                                he.getEquip().addWeapon(weapons.get(weaponShop - 2));
                                newFrame();
                                System.out.println("Erfolgreich gekauft");
                                openMainMenu(he);
                            } else {
                                newFrame();
                                System.out.println("Du hast nicht genug Geld");
                                openMainMenu(he);
                            }
                        }
                    }
                }
                break;
            case 4:
                break;
        }
    }

    public static void openChest(Held he, Weapon we) throws InterruptedException { // Um Loot zu erhalten
        // Animation der Kiste:
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        Thread.sleep(timeBetweenAnimation);
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   _________________________________");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |________________________________|");
        Thread.sleep(timeBetweenAnimation);
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   _________________________________");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |________________________________|");
        System.out.println("");
        Thread.sleep(timeBetweenAnimation);
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   _________________________________");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |________________________________|");
        System.out.println("");
        System.out.println("");
        Thread.sleep(timeBetweenAnimation);
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("   Drücke Enter um die Kiste zu öffnen");
        System.out.println("");
        System.out.println("   _________________________________");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |________________________________|");
        System.out.println("");
        System.out.println("");
        scanner.nextLine();
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |________________________________|");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        Thread.sleep((long) (timeBetweenAnimation/2.2));
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |________________________________|");
        System.out.println("");
        System.out.println("");
        Thread.sleep((long) (timeBetweenAnimation/2.2));
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |________________________________|");
        System.out.println("");
        Thread.sleep((long) (timeBetweenAnimation/2.2));
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |________________________________|");
        Thread.sleep((long) (timeBetweenAnimation/2.2));
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        Thread.sleep((long) (timeBetweenAnimation/2.2));
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        Thread.sleep((long) (timeBetweenAnimation/2.2));
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |                                |");
        Thread.sleep((long) (timeBetweenAnimation/2.2));
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        System.out.println("   |             ooooooo            |");
        Thread.sleep((long) (timeBetweenAnimation/2.2));
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        System.out.println("   |             ooooooo            |");
        Thread.sleep((long) (timeBetweenAnimation/2.2));
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   |                                |");
        System.out.println("   |                                |");
        Thread.sleep((long) (timeBetweenAnimation/2.2));
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   |                                |");
        Thread.sleep((long) (timeBetweenAnimation/2.2));
        newFrame();
        System.out.println("Du hast einen " + we.getName() + " bekommen.");
        for (String line : we.getIcon()){
            System.out.println(line);
        }
        System.out.println("\nDrücke Enter um mehr Informationen zu erhalten");
        scanner.nextLine();
        newFrame();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("        Du hast eine Kiste bekommen");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("   |                                |");
        Thread.sleep((long) (timeBetweenAnimation/2.2));
        newFrame();
        System.out.println("Du hast einen " + we.getName() + " bekommen.");
        for (String line : we.getIcon()){
            System.out.println(line);
        }
        System.out.print("\n");
        System.out.println(we.getName());
        System.out.println("Schaden: " + we.getDamage() + " (Wie viel Schaden die Waffe an dem Gegner verursacht)");
        System.out.println("Haltbarkeit: " + we.getDurability() + " (Wie viel Haltbarkeit die Waffe hat)");
        System.out.println("Zerbrechlichkeit: " + we.getBreakability() + " (Wie schnell die Waffe an Haltbarkeit verliert)");
        System.out.println("Stärke: " + we.getStrength() + " (Wie viel Prozent des Schadens parriert werden kann)");
        System.out.println("                                                                                                  Drücke Enter um fortzuschreiten");
        scanner.nextLine();
        newFrame();
        he.getEquip().addWeapon(we);
    }

    private static void newFrame() {
        for (int i = 0; i < 60; i++){
            System.out.println("");
        }
    }

    public static void kämpfen(Held he, Monster mo) throws InterruptedException{ // Für einen Kampf mit einem Monster
        newFrame();
        System.out.println("Du wurdest von " + mo.getName() + " in einen Kampf verwickelt.\nDrücke Enter um fortzufahren");
        scanner.nextLine();
        showHandlers(he, mo);
    }

    private static void showHandlers(Held he, Monster mo) throws InterruptedException {
        if (mo.getHealth() < 0){
            mo.setHealth(mo.getMaxHealth());
        }
        newFrame();
        ArrayList<String> skinPlayer = new ArrayList<>();
        skinPlayer.add(he.getName() + "                   ");
        skinPlayer.add("   |-----|       ");
        skinPlayer.add("   | '  '|       ");
        skinPlayer.add("   |  _  |       ");
        skinPlayer.add("   |_____|       ");
        skinPlayer.add("    _| |_        ");
        skinPlayer.add(" --|     |--     ");
        skinPlayer.add("-  |     |  --   ");
        skinPlayer.add("   |     |       ");
        skinPlayer.add("   |     |       ");
        skinPlayer.add("   |_____|       ");
        skinPlayer.add("    || ||        ");
        skinPlayer.add("    || ||        ");
        skinPlayer.add("    ||D||D       ");
        System.out.println(he.getName() + ": [" + getHealthBar(he) + "] " + he.getHealth() + "/" + he.getMaxHealth() + "           " + mo.getName() + ": [" + getHealthBar(mo) + "] " + mo.getHealth() + "/" + mo.getMaxHealth());
        System.out.println("\n___________________________________");
        int countLine = 0;
        for (String line : skinPlayer){
            int count = 0;
            System.out.print("|");
            for (Character charac : line.toCharArray()){
                if (count < 16) {
                    System.out.print(charac);
                }
                count++;
            }
            System.out.print(" ");
            System.out.print(" ");
            count = 0;
            if (mo.getSkin().size() > countLine) {
                for (Character charac : mo.getSkin().get(countLine).toCharArray()) {
                    if (count < 16) {
                        System.out.print(charac);
                    }
                    count++;
                }
            }else{
                System.out.print("                ");
            }
            System.out.print("|\n");
            countLine++;
        }
        System.out.println("|__________________________________|");
        System.out.println("| 1. Angriff | 2. Item | 3. Flucht |");
        System.out.println("|__________________________________|");
        openFightMenu(scanner.nextInt(), he, mo);
    }

    public static ArrayList<Integer> openFightMenu(int menu, Held he, Monster mo) throws InterruptedException { // Kampfmenü Öffnen
        newFrame();

        System.out.println(he.getName() + ": [" + getHealthBar(he) + "] " + he.getHealth() + "/" + he.getMaxHealth() + "           " + mo.getName() + ": [" + getHealthBar(mo) + "] " + mo.getHealth() + "/" + mo.getMaxHealth());

        switch (menu){
            case 1:
                System.out.println("Wähle aus, mit welcher Waffe du angreifen willst.\n");
                ArrayList<ArrayList<String>> weapons = new ArrayList<>();

                for (Weapon weapon : he.getEquip().getWeapons()){ // Auflistung aller Waffen
                    ArrayList<String> listStrings = new ArrayList<>();
                    listStrings.add(weapon.getName());
                    listStrings.add("Schaden: " + weapon.getDamage());
                    listStrings.add("Haltbarkeit: " + (int) (weapon.getDurability() / (weapon.getBreakability() / 100)) + "/" + (int) ( 1 / (weapon.getBreakability() / 100)));
                    weapons.add(listStrings);
                }


                int action = openMenu(weapons, true);

                if (action - 1 < he.getEquip().getWeapons().size()) {
                    if (!mo.damage((float) he.getAttackDamage(action - 1))) {
                        he.getEquip().getWeapons().get(action - 1).use();
                        int durability = (int) (he.getEquip().getWeapons().get(action - 1).getDurability() / (he.getEquip().getWeapons().get(action - 1).getBreakability() / 100));
                        if ((int) (durability) <= 0){ //Wenn Waffe Kaputt
                            LibaryItems libaryItems = new LibaryItems();
                            he.getEquip().getItems().add(libaryItems.scrap.setCount(1));
                            System.out.println("Deine Waffe ist kaputt gegangen!!");
                            ArrayList<Weapon> weaponsNow = he.getEquip().getWeapons();
                            weaponsNow.remove(action - 1);
                            he.getEquip().setWeapons(weaponsNow);
                            scanner.nextLine();
                        }
                        newFrame();
                        System.out.println("Du hast " + mo.getName() + " besiegt");
                        ArrayList<ItemStack> drops = mo.getLoot().getDrops();
                        System.out.print("Du hast ");
                        boolean isDropping = false;
                        for (ItemStack item : drops){
                            if (item != null) {
                                isDropping = true;
                                he.getEquip().addItem(item);
                                System.out.print(item.getName() + " ");
                            }
                        }
                        if (!isDropping){
                            System.out.print("nichts ");
                        }
                        System.out.print("bekommen.\n");
                        System.out.println("Drücke Enter um fortzustreiten");
                        scanner.nextLine();
                        openMainMenu(he);
                    }else{
                        he.getEquip().getWeapons().get(action - 1).use();
                        float damage = mo.getEquip().getWeapons().get(0).getDamage();
                        System.out.println("Möchtest du den Angriff blocken? Dies verbraucht Haltbarkeit (1 = ja; 0 = nein)");
                        int block = scanner.nextInt();
                        if (block == 1) {
                            float percentage = (float) he.getEquip().getWeapons().get(action - 1).getStrength() / 100;
                            damage =(float) (damage * (1 - percentage));
                            he.getEquip().getWeapons().get(action - 1).use();
                        }
                        if (he.damage((int) (damage))){ // Wenn der Player es überlebt
                            if ((he.getEquip().getWeapons().get(action - 1).getDurability() / (he.getEquip().getWeapons().get(action - 1).getBreakability() / 100)) < 1){ // Wenn Waffe Kaputt
                                he.getEquip().getItems().add(LibaryItems.scrap.setCount(1));
                                System.out.println("Deine Waffe ist kaputt gegangen!!");
                                he.getEquip().getWeapons().remove(action - 1);
                                scanner.nextLine();
                            }
                            showHandlers(he, mo);
                        }else{
                            System.out.println("Du bist gestorben");
                        }
                    }
                } else {
                    showHandlers(he, mo);
                }

                break;
            case 2: // Item Auswahl
                ArrayList<String> listTypes = new ArrayList<>();
                ArrayList<Integer> listPosition = new ArrayList<>();
                ArrayList<ItemStack> listItems = new ArrayList<>();

                int count = 0;
                for (ItemStack item : he.getEquip().getItems()){
                    if (listTypes.contains(item.getName())){
                        ItemStack itemNow = listItems.get(listPosition.get(listTypes.indexOf(item.getName())));
                        listItems.set(listPosition.get(listTypes.indexOf(item.getName())),itemNow.setCount(itemNow.getCount() + item.getCount()));
                    }else{
                        listTypes.add(item.getName());
                        listPosition.add(count);
                        listItems.add(item);
                        count++;
                    }
                }

                ArrayList<ArrayList<String>> listAllItems = new ArrayList<>();
                System.out.println("Dein jetztiger Kontostand: " + he.getCredits());
                for (ItemStack item : listItems){
                    ArrayList<String> listWord = new ArrayList<>();
                    listWord.add(item.getCount() + "x " + item.getName());
                    listWord.add(item.getWorth() + "€");
                    listAllItems.add(listWord);
                }
                int itemChosen = openMenu(listAllItems, true);
                newFrame();
                if (itemChosen == listAllItems.size()) {
                    showHandlers(he, mo);
                }else {
                    System.out.print("\n");
                    System.out.print(listItems.get(itemChosen - 1).getCount() + "x " + listItems.get(itemChosen - 1).getName() + "\n");
                    for (String string : listItems.get(itemChosen - 1).getIcon()) {
                        for (int i = 0; i < 50; i++) {
                            if (string.length() > i) {
                                System.out.print(string.charAt(i));
                            }
                        }
                        System.out.print("\n");
                    }
                    ArrayList<String> sell = new ArrayList<>();
                    sell.add("Verkaufen (" + listItems.get(itemChosen - 1).getWorth() + "€)");
                    ArrayList<ArrayList<String>> list = new ArrayList<>();
                    list.add(sell);
                    int whatToDo = openMenu(list, true);
                    newFrame();
                    System.out.println(he.getEquip().getItems().toString());
                    switch (whatToDo) {
                        case 1:
                            he.setCredits(he.getCredits() + listItems.get(itemChosen - 1).getWorth() * listItems.get(itemChosen - 1).getCount());
                            ArrayList<ItemStack> items = new ArrayList<>();
                            for (ItemStack item : he.getEquip().getItems()) {
                                if (!item.getName().equals(listItems.get(itemChosen - 1).getName())) {
                                    items.add(item);
                                }
                            }
                            he.getEquip().setItems(items);

                            for (ItemStack item : he.getEquip().getItems()) {
                                System.out.println(item.getCount() + "x " + item.getName());
                            }
                            showHandlers(he, mo);
                            break;
                        case 2:
                            showHandlers(he, mo);
                    }
                }
                break;
            case 3:

                break;
        }
        ArrayList<Integer> error = new ArrayList<>();
        error.add(0,0);
        return error;
    }


    public static int openMenu(ArrayList<ArrayList<String>> listAnswers, boolean cancel){ // Öffnet ein Menü mit listAnswers als auswahlmöglichkeiten | Cancel sagt, ob man zurück gehen kann
        System.out.println("___________________________________________________________________");
        int countLine = 1;

        if (cancel){
            ArrayList<String> cancelList = new ArrayList<>();
            cancelList.add("Abbrechen");
            listAnswers.add(cancelList);
        }

        for (ArrayList<String> list : listAnswers) {
            int countLineInLine = 0;
            for (String line : list) {
                if (countLineInLine == 0){
                    System.out.print(countLine + ". ");
                }else{
                    System.out.print("   ");
                }
                for (int i = 0; i < 64; i++) {
                    if (line.length() > i) {
                        System.out.print(line.charAt(i));
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.print("|\n");
                countLineInLine ++;
            }
            countLine++;
            System.out.print("___________________________________________________________________|\n");
        }


        return scanner.nextInt();
    }

    public static String getHealthBar(Monster monster){ // Gibt eine Lebensbar zurück
        String healthBar = "";
        for (int i = 0; i + 2 <= monster.getMaxHealth(); i+=2){
            if (i <= monster.getHealth()){
                healthBar += "#";
            }else{
                healthBar += ".";
            }
        }
        return healthBar;
    }

    public static String getHealthBar(Held held){ // Gibt eine Lebensbar zurück
        String healthBar = "";
        for (int i = 0; i + 10 <= held.getMaxHealth(); i+=10){
            if (i <= held.getHealth()){
                healthBar += "#";
            }else{
                healthBar += ".";
            }
        }
        return healthBar;
    }
}
