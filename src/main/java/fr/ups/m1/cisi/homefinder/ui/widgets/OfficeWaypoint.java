/*
 * Copyright 2018 Alexandre Canny <Alexandre.Canny@irit.fr>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.ups.m1.cisi.homefinder.ui.widgets;

import fr.ups.m1.cisi.homefinder.ui.utils.ImagePanel;
import java.awt.Dimension;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * PHYSICAL INTERACTION
 * @author Alexandre Canny <Alexandre.Canny@irit.fr>
 */
public class OfficeWaypoint extends HomeFinderWaypoint {

    private JComponent uiComponent;    

    public OfficeWaypoint(GeoPosition coord) {
        super(coord, "Office");
        createComponent();
    }

    private void createComponent() {
        try {
            image = ImageIO.read(OfficeWaypoint.class.getResource("workplace.png"));
            uiComponent = new ImagePanel(image); 
            uiComponent.setSize(new Dimension(32,32));
            uiComponent.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JComponent getComponent() {
        return uiComponent;
    }

}
