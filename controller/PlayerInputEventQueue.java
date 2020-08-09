package controller;

import controller.observer.UFOCreateEvent;
import model.machinegun.MachineGun;
import model.missile.Missile;
import model.MousePointer;
import model.Shooter;
import model.ufo.UFO;
import model.vitalityWave.VitalityWave;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Random;

public class PlayerInputEventQueue {

    public LinkedList<InputEvent> queue = new LinkedList<>();

    public void processInputEvents() {

        while (!queue.isEmpty()) {
            InputEvent inputEvent = queue.removeFirst();

            switch (inputEvent.type) {
                case InputEvent.MOUSE_PRESSED:
                    MouseEvent e = (MouseEvent) inputEvent.event;
                    Missile m = new Missile(e.getX(), e.getY());
                    Main.gameData.friendObject.add(m);
                    break;
                case InputEvent.MOUSE_MOVED:
                    MousePointer mp = (MousePointer) Main.gameData.fixedObject.get(0);
                    MouseEvent me = (MouseEvent) inputEvent.event;
                    mp.location.x = me.getX();
                    mp.location.y = me.getY();
                    break;
                case InputEvent.KEY_PRESSED:
                    var shooter = Main.gameData.fixedObject.get(Main.INDEX_SHOOTER);
                    KeyEvent ke = (KeyEvent) inputEvent.event;
                    switch (ke.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            shooter.location.y -= Shooter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_DOWN:
                            shooter.location.y += Shooter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_LEFT:
                            shooter.location.x -= Shooter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_RIGHT:
                            shooter.location.x += Shooter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_SPACE: //respawn
                            int number_x = 350;
                            int number_y = 400;
                            shooter.location.x = number_x;
                            shooter.location.y = number_y;
                            break;
                        case KeyEvent.VK_Q:
                            int number__x = (int)shooter.location.x;
                            int number__y = (int)shooter.location.y;
                            //UFO ufo = new UFO(100, 100);
                            //Main.gameData.enemyObject.add(ufo);

                            //Add a new missile that is 200 units higher in the vertical direction of the shooter object
                            Missile n = new Missile(number__x, number__y - 200);

                            //add it to the GameData
                            Main.gameData.friendObject.add(n);
                            break;

                        case KeyEvent.VK_W:
                            int numberx2 = (int)shooter.location.x;
                            int numbery2 = (int)shooter.location.y;

                           MachineGun p = new MachineGun(numberx2, numbery2 - 100);
                           MachineGun q = new MachineGun(numberx2 - 10, numbery2 - 200);
                            MachineGun r = new MachineGun(numberx2 - 20, numbery2 - 150);
                            MachineGun s = new MachineGun(numberx2 - 30, numbery2 - 200);
                            MachineGun t = new MachineGun(numberx2 + 10, numbery2 - 150);
                            MachineGun u = new MachineGun(numberx2 + 20, numbery2 - 200);
                            MachineGun v = new MachineGun(numberx2 + 30, numbery2 - 150);


                            //add it to the GameData
                           Main.gameData.friendObject.add(p);
                           Main.gameData.friendObject.add(q);
                            Main.gameData.friendObject.add(r);
                            Main.gameData.friendObject.add(s);
                            Main.gameData.friendObject.add(t);
                            Main.gameData.friendObject.add(u);
                            Main.gameData.friendObject.add(v);

                            break;

                        case KeyEvent.VK_E:
                            int number___x = (int)shooter.location.x;
                            int number___y = (int)shooter.location.y;

                            //Add a new vitality wave that is 100 units higher in the vertical direction of the shooter object
                            VitalityWave o = new VitalityWave(number___x, number___y);

                            //add it to the GameData
                            Main.gameData.friendObject.add(o);

                    }
                    break;
                case InputEvent.UFO_CREATE:
                    UFOCreateEvent ue = (UFOCreateEvent) inputEvent.event;
                    Main.addUFOwithListener(ue.getX(), ue.getY());
            }
        }
    }
}
