/*
 * Copyright 2018 ebouzekr.
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
package fr.ups.m1.cisi.homefinder.ui;

import fr.ups.m1.cisi.homefinder.core.IHomeStorageProvider;
import fr.ups.m1.cisi.homefinder.core.home.Home;
import fr.ups.m1.cisi.homefinder.ui.painter.HomeFinderWaypointOverlayPainter;
import fr.ups.m1.cisi.homefinder.ui.widgets.HomeFinderWaypoint;
import fr.ups.m1.cisi.homefinder.ui.widgets.HouseholdWaypoint;
import fr.ups.m1.cisi.homefinder.ui.widgets.OfficeWaypoint;
import fr.ups.m1.cisi.homefinder.utils.HomeMatcher;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JComponent;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

/**
 *
 * @author ebouzekr
 */
public class HomeFinderUI extends javax.swing.JFrame {

    private final IHomeStorageProvider core;
    private final JXMapViewer mapViewer = new JXMapViewer();
    private final Set<HomeFinderWaypoint> waypoints = new HashSet<>();
    private final HomeFinderWaypointOverlayPainter waypointPainter = new HomeFinderWaypointOverlayPainter();
    private final List<Home> suitableHomes = new ArrayList<>();

    private GeoPosition officeLocation;
    private Home selectedHome;

    /**
     * Creates new form HomeFinderUI
     *
     * @param core
     */
    public HomeFinderUI(IHomeStorageProvider core) {
        this.core = core;
        initComponents();
        initMapComponent();
        initStateMachine();
    }

    /**
     * Initialises the MapViewer to be centered on Toulouse and add
     * MouseListener.
     */
    private void initMapComponent() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        tileFactory.setThreadPoolSize(8);
        mapViewer.setTileFactory(tileFactory);
        GeoPosition toulouse = new GeoPosition(43, 36, 16, 1, 26, 38);
        mapViewer.setZoom(5);
        mapViewer.setAddressLocation(toulouse);
        starfielddisplayPanel.add(mapViewer, BorderLayout.CENTER);
        waypointPainter.setWaypoints(waypoints);
        mapViewer.setOverlayPainter(waypointPainter);
        mapViewer.addMouseListener(new MouseListener() {
            /**
             * LOGICAL INTERACTION
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    mapViewerMouseClicked(e);
                } else {
                    //nothing
                }
            }

            /**
             * LOGICAL INTERACTION
             *
             * @param e
             */
            @Override
            public void mousePressed(MouseEvent e) {
                //nothing
            }

            /**
             * LOGICAL INTERACTION
             *
             * @param e
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                //nothing
            }

            /**
             * LOGICAL INTERACTION
             *
             * @param e
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                //nothing
            }

            /**
             * LOGICAL INTERACTION
             *
             * @param e
             */
            @Override
            public void mouseExited(MouseEvent e) {
                //nothing
            }
        });
    }

    private void initStateMachine() {
        //Compl�ter ici
    }

    /**
     * Updates the suitable home list and refreshes the MapViewer.
     */
    private void refreshMap() {
        updateSuitableHomes();
        updateWaypoints();
    }

    /**
     * Refreshes the MapViewer to display waypoints matching suitable homes.
     */
    private void updateWaypoints() {
        waypoints.clear();
        waypoints.add(new OfficeWaypoint(officeLocation));
        for (Home h : suitableHomes) {
            if (h.getType().equals(Home.TYPE_HOUSE)) {
                waypoints.add(new HouseholdWaypoint(new GeoPosition(h.getLatitude(), h.getLongitude()), h.getName(), HouseholdWaypoint.HOME));
            } else {
                waypoints.add(new HouseholdWaypoint(new GeoPosition(h.getLatitude(), h.getLongitude()), h.getName(), HouseholdWaypoint.APARTMENT));
            }
        }
        waypointPainter.setWaypoints(waypoints);
        mapViewer.setOverlayPainter(waypointPainter);
    }

    /**
     * Updates the suitable home list.
     */
    private void updateSuitableHomes() {
        String expectedType;
        suitableHomes.clear();
        if (appartementRadioButton.isSelected()) {
            expectedType = Home.TYPE_APPARTMENT;
        } else {
            expectedType = Home.TYPE_HOUSE;
        }
        for (Home home : core.getHomes()) {
            if (HomeMatcher.homeMatchesRequirements(home, officeLocation, distanceTravailSlider.getValue(), expectedType, superficieSlider.getValue(), Integer.parseInt(nbpiecesSpinner.getValue().toString()), prixSlider.getValue(), piscineCheckBox.isSelected(), parkingCheckBox.isSelected(), terrasseCheckBox.isSelected(), jardinCheckBox.isSelected(), caveCheckBox.isSelected())) {
                suitableHomes.add(home);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        starfielddisplayfiltersPanel = new javax.swing.JPanel();
        distancetravailPanel = new javax.swing.JPanel();
        distanceTravailLabelTitle = new javax.swing.JLabel();
        distanceTravailSlider = new javax.swing.JSlider();
        distanceTravaiValuelLabel = new javax.swing.JLabel();
        kmLabel = new javax.swing.JLabel();
        typebienPanel = new javax.swing.JPanel();
        typebienLabel = new javax.swing.JLabel();
        appartementRadioButton = new javax.swing.JRadioButton();
        maisonRadioButton = new javax.swing.JRadioButton();
        superficiePanel = new javax.swing.JPanel();
        superficieLabel = new javax.swing.JLabel();
        superficieSlider = new javax.swing.JSlider();
        superficieValueLabel = new javax.swing.JLabel();
        mcarreLabel = new javax.swing.JLabel();
        nbpiecesPanel = new javax.swing.JPanel();
        nbpiecesLabel = new javax.swing.JLabel();
        nbpiecesSpinner = new javax.swing.JSpinner();
        prixPanel = new javax.swing.JPanel();
        prixLabel = new javax.swing.JLabel();
        prixSlider = new javax.swing.JSlider();
        prixValueLabel = new javax.swing.JLabel();
        euroLabel = new javax.swing.JLabel();
        commiditesPanel = new javax.swing.JPanel();
        commoditesLabel = new javax.swing.JLabel();
        parkingCheckBox = new javax.swing.JCheckBox();
        piscineCheckBox = new javax.swing.JCheckBox();
        terrasseCheckBox = new javax.swing.JCheckBox();
        jardinCheckBox = new javax.swing.JCheckBox();
        caveCheckBox = new javax.swing.JCheckBox();
        selecthabitatPanel = new javax.swing.JPanel();
        nomBienTitre = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        selectedName = new javax.swing.JLabel();
        typedebienPanel = new javax.swing.JPanel();
        typedebienTitre = new javax.swing.JLabel();
        selectedType = new javax.swing.JLabel();
        localisationPanel = new javax.swing.JPanel();
        localisationTitre = new javax.swing.JLabel();
        selectedLocation = new javax.swing.JLabel();
        selectsuperficiePanel = new javax.swing.JPanel();
        superficieTitre = new javax.swing.JLabel();
        selectedSize = new javax.swing.JLabel();
        selectNbPiecesPanel = new javax.swing.JPanel();
        selectNbPiecesTitre = new javax.swing.JLabel();
        selectedNbRooms = new javax.swing.JLabel();
        selectCommoditesPanel = new javax.swing.JPanel();
        selectCommodites = new javax.swing.JLabel();
        selectedCommodities = new javax.swing.JLabel();
        selectPrixPanel = new javax.swing.JPanel();
        selectPrixTitre = new javax.swing.JLabel();
        selectedPrice = new javax.swing.JLabel();
        euro2Label = new javax.swing.JLabel();
        starfielddisplayPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home Finder");
        setSize(new java.awt.Dimension(1280, 720));

        starfielddisplayfiltersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trouver l'habitat de vos r�ves !", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 18))); // NOI18N
        starfielddisplayfiltersPanel.setLayout(new javax.swing.BoxLayout(starfielddisplayfiltersPanel, javax.swing.BoxLayout.Y_AXIS));

        distancetravailPanel.setToolTipText("Home Finder");
        distancetravailPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        distanceTravailLabelTitle.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        distanceTravailLabelTitle.setText("Distance Lieu de Travail");
        distancetravailPanel.add(distanceTravailLabelTitle);

        distanceTravailSlider.setMaximum(10);
        distanceTravailSlider.setMinimum(1);
        distanceTravailSlider.setValue(2);
        distancetravailPanel.add(distanceTravailSlider);

        distanceTravaiValuelLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        distanceTravaiValuelLabel.setText("0");
        distancetravailPanel.add(distanceTravaiValuelLabel);

        kmLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        kmLabel.setText("km");
        distancetravailPanel.add(kmLabel);

        starfielddisplayfiltersPanel.add(distancetravailPanel);

        typebienPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        typebienLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        typebienLabel.setText("Type de bien ");
        typebienPanel.add(typebienLabel);

        buttonGroup1.add(appartementRadioButton);
        appartementRadioButton.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        appartementRadioButton.setSelected(true);
        appartementRadioButton.setText("Appartement");
        typebienPanel.add(appartementRadioButton);

        buttonGroup1.add(maisonRadioButton);
        maisonRadioButton.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        maisonRadioButton.setText("Maison");
        typebienPanel.add(maisonRadioButton);

        starfielddisplayfiltersPanel.add(typebienPanel);

        superficiePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        superficieLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        superficieLabel.setText("Superficie ");
        superficiePanel.add(superficieLabel);

        superficieSlider.setMaximum(300);
        superficieSlider.setToolTipText("");
        superficieSlider.setValue(30);
        superficiePanel.add(superficieSlider);

        superficieValueLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        superficieValueLabel.setText("0");
        superficiePanel.add(superficieValueLabel);

        mcarreLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        mcarreLabel.setText("m�");
        superficiePanel.add(mcarreLabel);

        starfielddisplayfiltersPanel.add(superficiePanel);

        nbpiecesPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        nbpiecesLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        nbpiecesLabel.setText("Nombre de pi�ces ");
        nbpiecesPanel.add(nbpiecesLabel);
        nbpiecesPanel.add(nbpiecesSpinner);

        starfielddisplayfiltersPanel.add(nbpiecesPanel);

        prixPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        prixLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        prixLabel.setText("Prix ");
        prixPanel.add(prixLabel);

        prixSlider.setMajorTickSpacing(50000);
        prixSlider.setMaximum(600000);
        prixSlider.setMinimum(50000);
        prixSlider.setValue(100000);
        prixPanel.add(prixSlider);

        prixValueLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        prixValueLabel.setText("0");
        prixPanel.add(prixValueLabel);

        euroLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        euroLabel.setText("�");
        prixPanel.add(euroLabel);

        starfielddisplayfiltersPanel.add(prixPanel);

        commiditesPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        commoditesLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        commoditesLabel.setText("Commodit�s ");
        commiditesPanel.add(commoditesLabel);

        parkingCheckBox.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        parkingCheckBox.setText("Parking");
        commiditesPanel.add(parkingCheckBox);

        piscineCheckBox.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        piscineCheckBox.setText("Piscine");
        commiditesPanel.add(piscineCheckBox);

        terrasseCheckBox.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        terrasseCheckBox.setText("Terrasse");
        commiditesPanel.add(terrasseCheckBox);

        jardinCheckBox.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jardinCheckBox.setText("Jardin");
        commiditesPanel.add(jardinCheckBox);

        caveCheckBox.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        caveCheckBox.setText("Cave");
        commiditesPanel.add(caveCheckBox);

        starfielddisplayfiltersPanel.add(commiditesPanel);

        selecthabitatPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Habitat s�lectionn�", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 18))); // NOI18N
        selecthabitatPanel.setLayout(new javax.swing.BoxLayout(selecthabitatPanel, javax.swing.BoxLayout.PAGE_AXIS));

        nomBienTitre.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setText("Nom du bien : ");
        nomBienTitre.add(jLabel1);

        selectedName.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        selectedName.setText("Nom du bien s�lectionn�");
        nomBienTitre.add(selectedName);

        selecthabitatPanel.add(nomBienTitre);

        typedebienPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        typedebienTitre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        typedebienTitre.setText("Type de bien : ");
        typedebienPanel.add(typedebienTitre);

        selectedType.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        selectedType.setText("Type de bien s�lectionn�");
        typedebienPanel.add(selectedType);

        selecthabitatPanel.add(typedebienPanel);

        localisationPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        localisationTitre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        localisationTitre.setText("Localisation : ");
        localisationPanel.add(localisationTitre);

        selectedLocation.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        selectedLocation.setText("Lat, Long du bien s�lectionn�");
        localisationPanel.add(selectedLocation);

        selecthabitatPanel.add(localisationPanel);

        selectsuperficiePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        superficieTitre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        superficieTitre.setText("Superficie : ");
        selectsuperficiePanel.add(superficieTitre);

        selectedSize.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        selectedSize.setText("Superficie du bien s�lectionn�");
        selectsuperficiePanel.add(selectedSize);

        selecthabitatPanel.add(selectsuperficiePanel);

        selectNbPiecesPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        selectNbPiecesTitre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        selectNbPiecesTitre.setText("Nombre de pi�ces : ");
        selectNbPiecesPanel.add(selectNbPiecesTitre);

        selectedNbRooms.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        selectedNbRooms.setText("Nombre de pi�ces du bien s�lectionn�");
        selectNbPiecesPanel.add(selectedNbRooms);

        selecthabitatPanel.add(selectNbPiecesPanel);

        selectCommoditesPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        selectCommodites.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        selectCommodites.setText("Commodit�s : ");
        selectCommoditesPanel.add(selectCommodites);

        selectedCommodities.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        selectedCommodities.setText("Commodit�s du bien s�lectionn�");
        selectCommoditesPanel.add(selectedCommodities);

        selecthabitatPanel.add(selectCommoditesPanel);

        selectPrixPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        selectPrixTitre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        selectPrixTitre.setText("Prix : ");
        selectPrixPanel.add(selectPrixTitre);

        selectedPrice.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        selectedPrice.setText("Prix du bien s�lectionn�");
        selectPrixPanel.add(selectedPrice);

        euro2Label.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        euro2Label.setText("�");
        selectPrixPanel.add(euro2Label);

        selecthabitatPanel.add(selectPrixPanel);

        starfielddisplayfiltersPanel.add(selecthabitatPanel);

        getContentPane().add(starfielddisplayfiltersPanel, BorderLayout.LINE_END);

        starfielddisplayPanel.setLayout(new BorderLayout());
        getContentPane().add(starfielddisplayPanel, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * DIALOGUE
     *
     * @param evt
     */
    private void mapViewerMouseClicked(MouseEvent evt) {
        
    }

    /**
     * Returns the Home associated to the Waypoint located at the Point2D
     * coordinates.
     *
     * @param point mouse click location
     * @return picked house
     */
    private Home pickHome(Point2D point) {
        double mouseX = point.getX();
        double mouseY = point.getY();
        for (HomeFinderWaypoint waypoint : waypoints) {
            JComponent uiComponent = waypoint.getComponent();
            double componentX = uiComponent.getX();
            double componentY = uiComponent.getY();
            double componentWidth = uiComponent.getWidth();
            double componentHeight = uiComponent.getHeight();
            if (mouseX >= componentX && mouseX <= componentX + componentWidth) {
                if (mouseY >= componentY && mouseY <= componentY + componentHeight) {
                    for (Home home : suitableHomes) {
                        if (home.getName().equals(waypoint.getHouseName())) {
                            return home;
                        }
                    }
                }
            }
        }
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton appartementRadioButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox caveCheckBox;
    private javax.swing.JPanel commiditesPanel;
    private javax.swing.JLabel commoditesLabel;
    private javax.swing.JLabel distanceTravaiValuelLabel;
    private javax.swing.JLabel distanceTravailLabelTitle;
    private javax.swing.JSlider distanceTravailSlider;
    private javax.swing.JPanel distancetravailPanel;
    private javax.swing.JLabel euro2Label;
    private javax.swing.JLabel euroLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox jardinCheckBox;
    private javax.swing.JLabel kmLabel;
    private javax.swing.JPanel localisationPanel;
    private javax.swing.JLabel localisationTitre;
    private javax.swing.JRadioButton maisonRadioButton;
    private javax.swing.JLabel mcarreLabel;
    private javax.swing.JLabel nbpiecesLabel;
    private javax.swing.JPanel nbpiecesPanel;
    private javax.swing.JSpinner nbpiecesSpinner;
    private javax.swing.JPanel nomBienTitre;
    private javax.swing.JCheckBox parkingCheckBox;
    private javax.swing.JCheckBox piscineCheckBox;
    private javax.swing.JLabel prixLabel;
    private javax.swing.JPanel prixPanel;
    private javax.swing.JSlider prixSlider;
    private javax.swing.JLabel prixValueLabel;
    private javax.swing.JLabel selectCommodites;
    private javax.swing.JPanel selectCommoditesPanel;
    private javax.swing.JPanel selectNbPiecesPanel;
    private javax.swing.JLabel selectNbPiecesTitre;
    private javax.swing.JPanel selectPrixPanel;
    private javax.swing.JLabel selectPrixTitre;
    private javax.swing.JLabel selectedCommodities;
    private javax.swing.JLabel selectedLocation;
    private javax.swing.JLabel selectedName;
    private javax.swing.JLabel selectedNbRooms;
    private javax.swing.JLabel selectedPrice;
    private javax.swing.JLabel selectedSize;
    private javax.swing.JLabel selectedType;
    private javax.swing.JPanel selecthabitatPanel;
    private javax.swing.JPanel selectsuperficiePanel;
    private javax.swing.JPanel starfielddisplayPanel;
    private javax.swing.JPanel starfielddisplayfiltersPanel;
    private javax.swing.JLabel superficieLabel;
    private javax.swing.JPanel superficiePanel;
    private javax.swing.JSlider superficieSlider;
    private javax.swing.JLabel superficieTitre;
    private javax.swing.JLabel superficieValueLabel;
    private javax.swing.JCheckBox terrasseCheckBox;
    private javax.swing.JLabel typebienLabel;
    private javax.swing.JPanel typebienPanel;
    private javax.swing.JPanel typedebienPanel;
    private javax.swing.JLabel typedebienTitre;
    // End of variables declaration//GEN-END:variables
}
