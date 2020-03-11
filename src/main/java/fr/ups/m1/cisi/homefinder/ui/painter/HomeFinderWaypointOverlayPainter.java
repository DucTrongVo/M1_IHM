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
package fr.ups.m1.cisi.homefinder.ui.painter;

import fr.ups.m1.cisi.homefinder.ui.widgets.HomeFinderWaypoint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import javax.swing.JComponent;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 * PHYSICAL INTERACTION
 * @author Alexandre Canny <Alexandre.Canny@irit.fr>
 */
public class HomeFinderWaypointOverlayPainter extends WaypointPainter<HomeFinderWaypoint>{

    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        getWaypoints().forEach((waypoint) -> {
            Point2D point = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());
            Rectangle rectangle = map.getViewportBounds();
            int waypointX = (int)(point.getX() - rectangle.getX());
            int waypointY = (int)(point.getY() - rectangle.getY());
            JComponent uiComponent = waypoint.getComponent();
            uiComponent.setLocation(waypointX-uiComponent.getWidth()/2, waypointY-uiComponent.getHeight()/2);
            g.drawImage(waypoint.getImage(), uiComponent.getX(), uiComponent.getY(), null);
        });
    }   
    
    
        
}
