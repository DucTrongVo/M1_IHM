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

import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * PHYSICAL INTERACTION
 * @author Alexandre Canny <Alexandre.Canny@irit.fr>
 */
public abstract class HomeFinderWaypoint extends DefaultWaypoint{
    
    protected BufferedImage image;
    protected String homeName;
    
    
    public HomeFinderWaypoint(GeoPosition coord, String name){
        super(coord);
        homeName = name;
    }
    
    public abstract JComponent getComponent();
    
    public String getHouseName(){
        return homeName;
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public int getX(){
        return getComponent().getX();
    }
    
    public int getY(){
        return getComponent().getY();
    }
    
}
