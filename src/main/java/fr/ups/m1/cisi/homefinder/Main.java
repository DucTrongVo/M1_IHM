/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m1.cisi.homefinder;

import fr.ups.m1.cisi.homefinder.core.CoreImpl;
import fr.ups.m1.cisi.homefinder.core.IHomeStorageProvider;
import fr.ups.m1.cisi.homefinder.ui.HomeFinderUI;
import javax.swing.JFrame;

public class Main {

    public static void main(String args[]) {
        IHomeStorageProvider core = new CoreImpl(/*Path to .db file here*/);
        JFrame applicationFrame = new HomeFinderUI(core);
        applicationFrame.setSize(1280, 720);
        applicationFrame.setVisible(true);
    }

}
