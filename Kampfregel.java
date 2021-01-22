import java.util.ArrayList;
import java.util.Scanner;

public class Kampfregel
{
    Held he;
    Wuerfel wuerfel6 = new Wuerfel(0,6);
    Wuerfel wuerfel10 = new Wuerfel(0,10);
    Held heldActive;
    Monster monsterActive;
    Scanner scanner = new Scanner(System.in);
    int timeBetweenAnimation = 750;
    
    public void newFrame(){
        for (int i = 0; i < 50; i++){
            System.out.println("\n");
        }
    }

    public Kampfregel() throws InterruptedException{
        for (int i = 0; i < 50; i++){
            System.out.println("");
        }
        System.out.println("Nutzername: ");
        String username = scanner.nextLine();
        Held held = new Held(username, 1, 100, 100);
        firstRound(held);
    }

    public void firstRound(Held he) throws InterruptedException{
        LibaryWeapons weapons = new LibaryWeapons();
        LibaryMonsters monsters = new LibaryMonsters();
        GUIManager.openChest(he, weapons.oldDagger);
        System.out.println("Oh nein, ein Zombie hat den Dolch gesehen. Töte ihn Schnell, bevor er noch mehr Schaden verursachen kann!");
        Thread.sleep(5000);
        GUIManager.kämpfen(he, monsters.oldZombie);
    }
    

}
