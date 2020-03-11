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
package fr.ups.m1.cisi.homefinder.utils;

import fr.ups.m1.cisi.homefinder.core.home.Home;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * FUNCTIONAL CORE ADAPTOR
 * @author Alexandre Canny <Alexandre.Canny@irit.fr>
 */
public class HomeMatcher {
    
    public static boolean homeMatchesRequirements(Home theHome,GeoPosition officeLocation, int maxDistance, String type, int minSize, int minRoom, int maxPrice,  boolean requiresPool, boolean requiresParking, boolean requiresTerrace, boolean requiresGarden, boolean requiresCellar){
        if(DistanceCalculator.distanceInKm(officeLocation.getLatitude(), officeLocation.getLongitude(), theHome.getLatitude(), theHome.getLongitude()) <= maxDistance){            
            if(theHome.getType().equals(type) && theHome.getSize() >= minSize && theHome.getNbRoom() >= minRoom && theHome.getPrice() <= maxPrice){
                return !((requiresPool && !theHome.isHasPool())
                        || (requiresParking && !theHome.isHasParking())
                        || (requiresTerrace && !theHome.isHasTerrace())
                        || (requiresGarden && !theHome.isHasGarden())
                        || (requiresCellar && !theHome.isHasGarden()));
            }
            return false;
        }
        return false;
    }
    
}
