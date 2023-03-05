/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author 21621
 */
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

public class AutoCloseApp {

    public static void main(String[] args) throws AWTException {

        final Timer timer = new Timer();
        final long delay = 3 * 60 * 1000; // 3 minutes en millisecondes

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Robot robot;
                try {
                    robot = new Robot();
                    int mouseX = MouseInfo.getPointerInfo().getLocation().x;
                    int mouseY = MouseInfo.getPointerInfo().getLocation().y;
                    int screenX = robot.getPixelColor(mouseX, mouseY).getRed();
                    // Si l'utilisateur n'a pas bougé la souris depuis 3 minutes, on ferme l'application
                    if (screenX == 0) {
                        System.exit(0);
                    }
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        }, delay);
    }
}

