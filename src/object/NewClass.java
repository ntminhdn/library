/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhiLong
 */
public class NewClass extends Thread {

    private int progress = 0;
    Random random = new Random();

    @Override
    public void run() {
        while (progress < 100) {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            progress += random.nextInt(10);
        }
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

}
