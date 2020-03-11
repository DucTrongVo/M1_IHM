package m2.ihm;

import m2.ihm.core.home.Core;
import m2.ihm.ui.NupletsUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Main {

    public static void main(String args[]) {
        Core theApplicationCore = new Core();
        NupletsUI ui = new NupletsUI(theApplicationCore);
        ui.setVisible(true);
    }

}
