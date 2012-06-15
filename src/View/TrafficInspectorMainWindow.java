// Author: Matheus Maciel
// Class: TrafficInspectorMainWindow
// Last update: 15/06/2012
// Reasoning for last update: Comments

// Package 
package View;

// Imports 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Traffic Inspector interface's main window 
// All interface elements must have their listeners added in AppManager class 
// This way the interface remains separated from the model and controller layers of the application 
// Every interface element must publish methods to allow them to be operated. This way, the controller can access those elements without breaking them 
public class TrafficInspectorMainWindow extends JFrame {

    public TrafficInspectorMainWindow() {
        super("Traffic Inspector");
        super.setSize(800, 600);

        mainTabbedPanel = new javax.swing.JTabbedPane();
        truckTrackingPanel = new javax.swing.JPanel();
        truckTrackingCombo = new javax.swing.JComboBox();
        truckInformationButton = new javax.swing.JButton();
        truckLacIcon = new JLabel (new ImageIcon("logo_1.gif"));
        groupLacIcon = new JLabel (new ImageIcon("logo_1.gif"));
        gatewayLacIcon = new JLabel (new ImageIcon("logo_1.gif"));
        truckGroupCombo = new javax.swing.JComboBox();
        truckGatewayCombo = new javax.swing.JComboBox();
        truckToggleGlobalButton = new javax.swing.JButton();
        truckCustomCombo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        truckNotificationArea = new javax.swing.JTextArea();
        truckConnectCheck = new javax.swing.JCheckBox();
        truckTrackingFilterRadio = new javax.swing.JRadioButton();
        truckGroupFilterRadio = new javax.swing.JRadioButton();
        truckGatewayFilterRadio = new javax.swing.JRadioButton();
        truckCustomFilterRadio = new javax.swing.JRadioButton();
        groupPanel = new javax.swing.JPanel();
        groupConnectCheck = new javax.swing.JCheckBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        groupCombo = new javax.swing.JComboBox();
        groupInformationButton = new javax.swing.JButton();
        groupCustomCombo = new javax.swing.JComboBox();
        groupToggleGlobalButton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        groupNotificationArea = new javax.swing.JTextArea();
        groupFilterRadio = new javax.swing.JRadioButton();
        groupCustomFilterRadio = new javax.swing.JRadioButton();
        gatewayPanel = new javax.swing.JPanel();
        gatewayConnectCheck = new javax.swing.JCheckBox();
        gatewayCombo = new javax.swing.JComboBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        gatewayCustomCombo = new javax.swing.JComboBox();
        gatewayToggleGlobalButton = new javax.swing.JButton();
        gatewayInformationButton = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        gatewayNotificationArea = new javax.swing.JTextArea();
        gatewayFilterRadio = new javax.swing.JRadioButton();
        gatewayCustomFilterRadio = new javax.swing.JRadioButton();
        customFilteringPanel = new javax.swing.JPanel();
        filterExpressionLabel = new javax.swing.JLabel();
        expressionField = new javax.swing.JTextField();
        availableFilterLabel = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        customFilterArea = new javax.swing.JTextArea();
        saveFilterButton = new javax.swing.JButton();
        resetFilterButton = new javax.swing.JButton();
        truckTrackingFilterTabRadio = new javax.swing.JRadioButton();
        groupFilterTabRadio = new javax.swing.JRadioButton();
        gatewayFilterTabRadio = new javax.swing.JRadioButton();  

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // <editor-fold defaultstate="collapsed" desc="Button groups">        
        truckFilterGroup = new ButtonGroup();
        truckFilterGroup.add(truckCustomFilterRadio);
        truckFilterGroup.add(truckGatewayFilterRadio);
        truckFilterGroup.add(truckGroupFilterRadio);
        truckFilterGroup.add(truckTrackingFilterRadio);
        
        groupFilterGroup = new ButtonGroup();
        groupFilterGroup.add(groupFilterRadio);
        groupFilterGroup.add(groupCustomFilterRadio);
        
        gatewayFilterGroup = new ButtonGroup();
        gatewayFilterGroup.add(gatewayFilterRadio);
        gatewayFilterGroup.add(gatewayCustomFilterRadio);
        
        customFilterGroup = new ButtonGroup();
        customFilterGroup.add(truckTrackingFilterTabRadio);
        customFilterGroup.add(groupFilterTabRadio);
        customFilterGroup.add(gatewayFilterTabRadio);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Truck Information tab">
        truckTrackingCombo.setModel(new javax.swing.DefaultComboBoxModel());
        
        truckInformationButton.setText("Check truck information");

        truckGroupCombo.setModel(new javax.swing.DefaultComboBoxModel());
        
        truckGatewayCombo.setModel(new javax.swing.DefaultComboBoxModel());        

        truckToggleGlobalButton.setText("Toggle Global List");

        truckCustomCombo.setModel(new javax.swing.DefaultComboBoxModel());
        
        // Information table for Truck Tracking Information 
        Object [] truckInformationColumnNames = { "Truck Least Significative Bits", "Truck Most Significative Bits", "Gateway Least Significative Bits", "Gateway Most Significative Bits", "Group", "Tracker", "Latitude", "Longitude", "Speed", "Time" };
        truckInformationTableModel = new DefaultTableModel(truckInformationColumnNames, 0);
        truckInformationTable = new JTable(truckInformationTableModel);        
        jScrollPane1.setViewportView(truckInformationTable);
        
        truckNotificationArea.setColumns(20);
        truckNotificationArea.setRows(5);
        jScrollPane2.setViewportView(truckNotificationArea);

        truckConnectCheck.setText("Connect to topic");
        truckConnectCheck.setSelected(true);

        truckTrackingFilterRadio.setText("Truck Filtering");

        truckGroupFilterRadio.setText("Group Filtering");

        truckGatewayFilterRadio.setText("Gateway Filtering");

        truckCustomFilterRadio.setText("Custom Filtering");

        javax.swing.GroupLayout truckTrackingPanelLayout = new javax.swing.GroupLayout(truckTrackingPanel);
        truckTrackingPanel.setLayout(truckTrackingPanelLayout);
        truckTrackingPanelLayout.setHorizontalGroup(
            truckTrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(truckTrackingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(truckTrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(truckTrackingCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(truckGatewayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(truckCustomCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(truckToggleGlobalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(truckConnectCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(truckGroupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(truckTrackingFilterRadio)
                    .addComponent(truckGroupFilterRadio)
                    .addComponent(truckGatewayFilterRadio)
                    .addComponent(truckCustomFilterRadio)
                    .addComponent(truckLacIcon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(truckTrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE))
                .addContainerGap())
        );
        truckTrackingPanelLayout.setVerticalGroup(
            truckTrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(truckTrackingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(truckTrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(truckTrackingPanelLayout.createSequentialGroup()
                        .addComponent(truckConnectCheck)
                        .addGap(13, 13, 13)
                        .addComponent(truckTrackingFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(truckTrackingCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(truckGroupFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(truckGroupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(truckGatewayFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(truckGatewayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(truckCustomFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(truckCustomCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(truckToggleGlobalButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(truckLacIcon)
                        .addGap(0, 104, Short.MAX_VALUE))
                    .addGroup(truckTrackingPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        mainTabbedPanel.addTab("Truck Tracking Information", truckTrackingPanel);

        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Group Information tab">
        groupConnectCheck.setText("Connect to topic");
        groupConnectCheck.setSelected(true);

        // Information table for Group tab 
        Object [] groupInformationColumnNames = { "Truck Least Significative Bits", "Truck Most Significative Bits", "Gateway Least Significative Bits", "Gateway Most Significative Bits", "Group" };
        groupInformationTableModel = new DefaultTableModel(groupInformationColumnNames, 0);
        groupInformationTable = new JTable(groupInformationTableModel);
        jScrollPane5.setViewportView(groupInformationTable);

        groupCombo.setModel(new javax.swing.DefaultComboBoxModel());

        groupInformationButton.setText("Check Group Information");

        groupCustomCombo.setModel(new javax.swing.DefaultComboBoxModel());

        groupToggleGlobalButton.setText("Toggle Global List");

        groupNotificationArea.setColumns(20);
        groupNotificationArea.setRows(5);
        jScrollPane6.setViewportView(groupNotificationArea);

        groupFilterRadio.setText("Group Filtering");

        groupCustomFilterRadio.setText("Custom Filtering");

        javax.swing.GroupLayout groupPanelLayout = new javax.swing.GroupLayout(groupPanel);
        groupPanel.setLayout(groupPanelLayout);
        groupPanelLayout.setHorizontalGroup(
            groupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(groupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(groupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(groupConnectCheck)
                        .addComponent(groupCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(groupCustomCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(groupToggleGlobalButton, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                    .addComponent(groupFilterRadio)
                    .addComponent(groupCustomFilterRadio)
                    .addComponent(groupLacIcon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(groupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );
        groupPanelLayout.setVerticalGroup(
            groupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(groupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(groupPanelLayout.createSequentialGroup()
                        .addComponent(groupConnectCheck)
                        .addGap(13, 13, 13)
                        .addComponent(groupFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(groupCustomFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupCustomCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(groupToggleGlobalButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupLacIcon)
                        .addGap(0, 128, Short.MAX_VALUE))
                    .addComponent(jScrollPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
        mainTabbedPanel.addTab("Group Information", groupPanel);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Gateway Information Tab">
        gatewayConnectCheck.setText("Connect to topic");
        gatewayConnectCheck.setSelected(true);

        gatewayCombo.setModel(new javax.swing.DefaultComboBoxModel());

        // Information table for Gateway tab
        Object [] gatewayInformationColumnNames = { "Gateway Least Significative Bits", "Gateway Most Significative Bits", "Participant Type", "CPU Usage", "Free Memory", "Connected Vehicles" };
        gatewayInformationTableModel = new DefaultTableModel(gatewayInformationColumnNames, 0);
        gatewayInformationTable = new JTable(gatewayInformationTableModel);        
        jScrollPane7.setViewportView(gatewayInformationTable);
        
        gatewayNotificationArea.setColumns(20);
        gatewayNotificationArea.setRows(5);
        jScrollPane8.setViewportView(gatewayNotificationArea);  

        gatewayCustomCombo.setModel(new javax.swing.DefaultComboBoxModel());

        gatewayToggleGlobalButton.setText("Toggle Global Listing");

        gatewayInformationButton.setText("Check Gateway Information");

        gatewayNotificationArea.setColumns(20);
        gatewayNotificationArea.setRows(5);
        jScrollPane8.setViewportView(gatewayNotificationArea);

        gatewayFilterRadio.setText("Gateway Filtering");

        gatewayCustomFilterRadio.setText("Custom Filtering");

        javax.swing.GroupLayout gatewayPanelLayout = new javax.swing.GroupLayout(gatewayPanel);
        gatewayPanel.setLayout(gatewayPanelLayout);
        gatewayPanelLayout.setHorizontalGroup(
            gatewayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gatewayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gatewayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gatewayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(gatewayToggleGlobalButton, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addComponent(gatewayCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gatewayCustomCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gatewayConnectCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(gatewayFilterRadio)
                    .addComponent(gatewayCustomFilterRadio)
                    .addComponent(gatewayLacIcon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gatewayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );
        gatewayPanelLayout.setVerticalGroup(
            gatewayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gatewayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gatewayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gatewayPanelLayout.createSequentialGroup()
                        .addComponent(gatewayConnectCheck)
                        .addGap(13, 13, 13)
                        .addComponent(gatewayFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gatewayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(gatewayCustomFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gatewayCustomCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(gatewayToggleGlobalButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gatewayLacIcon)
                        .addGap(0, 128, Short.MAX_VALUE))
                    .addComponent(jScrollPane7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainTabbedPanel.addTab("Gateway Load Information", gatewayPanel);

        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Custom Filtering Tab">
        filterExpressionLabel.setText("Type the desired filter expression:");

        availableFilterLabel.setText("Current available filters:");

        customFilterArea.setColumns(20);
        customFilterArea.setRows(5);
        jScrollPane9.setViewportView(customFilterArea);

        saveFilterButton.setText("Save Filter");

        resetFilterButton.setText("Reset Expression");

        truckTrackingFilterTabRadio.setText("Truck Tracking Information");

        groupFilterTabRadio.setText("Group Information");

        gatewayFilterTabRadio.setText("Gateway Load Information");

        javax.swing.GroupLayout customFilteringPanelLayout = new javax.swing.GroupLayout(customFilteringPanel);
        customFilteringPanel.setLayout(customFilteringPanelLayout);
        customFilteringPanelLayout.setHorizontalGroup(
            customFilteringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customFilteringPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customFilteringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customFilteringPanelLayout.createSequentialGroup()
                        .addGroup(customFilteringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filterExpressionLabel)
                            .addComponent(expressionField, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(availableFilterLabel)
                            .addGroup(customFilteringPanelLayout.createSequentialGroup()
                                .addComponent(truckTrackingFilterTabRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(groupFilterTabRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gatewayFilterTabRadio))
                            .addGroup(customFilteringPanelLayout.createSequentialGroup()
                                .addComponent(saveFilterButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetFilterButton)))
                        .addGap(0, 268, Short.MAX_VALUE))
                    .addComponent(jScrollPane9))
                .addContainerGap())
        );
        customFilteringPanelLayout.setVerticalGroup(
            customFilteringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customFilteringPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filterExpressionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(expressionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customFilteringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(truckTrackingFilterTabRadio)
                    .addComponent(groupFilterTabRadio)
                    .addComponent(gatewayFilterTabRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customFilteringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveFilterButton)
                    .addComponent(resetFilterButton))
                .addGap(18, 18, 18)
                .addComponent(availableFilterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        mainTabbedPanel.addTab("Custom Data Filtering", customFilteringPanel);

        // </editor-fold>
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTabbedPanel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTabbedPanel))
        );
    }

    // <editor-fold defaultstate="collapsed" desc="Buttons and associated methods">
    public JButton getTruckToggleGlobalButton() {
        return truckToggleGlobalButton;
    }
    
    public JButton getGroupToggleGlobalButton() {
        return groupToggleGlobalButton;
    }
    
    public JButton getGatewayToggleGlobalButton() {
        return gatewayToggleGlobalButton;
    }
    
    public JButton getSaveFilterButton() {
        return saveFilterButton;
    }
    
    public JButton getResetFilterButton() {
        return resetFilterButton;
    }
            
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="TextFields and associated methods">
    public JTextField getExpressionField () {
        return expressionField;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="ButtonGroups and associated methods">
    public void clearTruckFilterGroup () {
        truckFilterGroup.clearSelection();
    }
    
    public void clearGroupFilterGroup () {
        groupFilterGroup.clearSelection();
    }
        
    public void clearGatewayFilterGroup () {
        gatewayFilterGroup.clearSelection();
    }
    
    public boolean isTruckFilterGroupSelected() {
        if (truckFilterGroup.getSelection() != null) {
            return true;
        }
        else
            return false;
    }
    
    public boolean isGroupFilterGroupSelected() {
        if (groupFilterGroup.getSelection() != null) {
            return true;
        }
        else
            return false;
    }
    
    public boolean isGatewayFilterGroupSelected() {
        if (gatewayFilterGroup.getSelection() != null) {
            return true;
        }
        else
            return false;
    }
    
    public int getCustomFilterSelection() {
        if (truckTrackingFilterTabRadio.isSelected()) {
            return 1;
        }
        if (groupFilterTabRadio.isSelected()) {
            return 2;
        }
        if(gatewayFilterTabRadio.isSelected()) {
            return 3;
        }
        return -1;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="ComboBoxes and associated methods">
    public int truckGetSelectedTruck() {
        return truckTrackingCombo.getSelectedIndex();
    }
    
    public int truckGetSelectedGroup() {
        return Integer.parseInt(truckGroupCombo.getSelectedItem().toString());
    }
    
    public int truckGetSelectedGateway() {
        return truckGatewayCombo.getSelectedIndex();
    }
    
    public int truckGetCustomSelected() {
        return truckCustomCombo.getSelectedIndex();
    }
    
    public int groupGetSelectedGroup() {
        return Integer.parseInt(groupCombo.getSelectedItem().toString());
    }
    
    public int groupGetCustomSelected() {
        return groupCustomCombo.getSelectedIndex();
    }

    public int gatewayGetSelectedGateway() {
        return gatewayCombo.getSelectedIndex();
    }
    
    public int gatewayGetCustomSelected() {
        return gatewayCustomCombo.getSelectedIndex();
    }
    
    public JComboBox getTruckTrackingCombo() {
        return truckTrackingCombo;
    }
    
    public JComboBox getTruckGatewayCombo() {
        return truckGatewayCombo;
    }
    
    public JComboBox getTruckGroupCombo() {
        return truckGroupCombo;
    }
    
    public JComboBox getTruckCustomCombo() {
        return truckCustomCombo;
    }
    
    public JComboBox getGroupCombo() {
        return groupCombo;
    }
    
    public JComboBox getGroupCustomCombo() {
        return groupCustomCombo;
    }
    
    public JComboBox getGatewayCombo() {
        return gatewayCombo;
    }
    
    public JComboBox getGatewayCustomCombo() {
        return gatewayCustomCombo;
    }
    
    public void updateTruckCombo(String msg) {
        truckTrackingCombo.addItem(msg);
    }

    public void updateTruckGroupCombo(String msg) {
        truckGroupCombo.addItem(msg);
    }

    public void updateTruckGatewayCombo(String msg) {
        truckGatewayCombo.addItem(msg);
    }

    public void updateTruckCustomCombo(String msg) {
        truckCustomCombo.addItem(msg);
    }

    public void updateGroupCombo(String msg) {
        groupCombo.addItem(msg);
    }

    public void updadeGroupCustomCombo(String msg) {
        groupCustomCombo.addItem(msg);
    }

    public void updateGatewayCombo(String msg) {
        gatewayCombo.addItem(msg);
    }

    public void updateGatewayCustomCombo(String msg) {
        gatewayCustomCombo.addItem(msg);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="CheckBoxes and associated methods">
    public JCheckBox getTruckConnectCheck() {
        return truckConnectCheck;
    }
    
    public JCheckBox getGroupConnectCheck() {
        return truckConnectCheck;
    }
    
    public JCheckBox getGatewayConnectCheck() {
        return truckConnectCheck;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="RadioButtons and associated methods">
    public boolean truckTrackingFilterSelected() {
        return truckTrackingFilterRadio.isSelected();
    }

    public boolean truckGroupFilterSelected() {
        return truckGroupFilterRadio.isSelected();
    }

    public boolean truckGatewayFilterSelected() {
        return truckGatewayFilterRadio.isSelected();
    }
    
    public boolean truckCustomFilterSelected() {
        return truckCustomFilterRadio.isSelected();
    }
    
    public boolean groupFilterSelected() {
        return groupFilterRadio.isSelected();
    }
    
    public boolean groupCustomFilterSelected() {
        return groupCustomFilterRadio.isSelected();
    }

    public boolean gatewayFilterSelected() {
        return gatewayFilterRadio.isSelected();
    }
    
    public boolean gatewayCustomFilterSelected() {
        return gatewayCustomFilterRadio.isSelected();                
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="TextAreas and associated methods">    
    public void updateTruckNotificationArea(String msg) {
        truckNotificationArea.append(msg + "\n");
    }

    public void updateGroupNotificationArea(String msg) {
        groupNotificationArea.append(msg + "\n");
    }
    
    public void updateGatewayNotificationArea(String msg) {
        gatewayNotificationArea.append(msg + "\n");
    }

    public void updateCustomFilterArea(String msg) {
        customFilterArea.setText("");
        customFilterArea.append(msg);
    }
    
    public void updateNotificationAreas(String msg) {
        updateTruckNotificationArea(msg);
        updateGroupNotificationArea(msg);
        updateGatewayNotificationArea(msg);
    }
    // </editor-fold>    
    
    // <editor-fold defaultstate="collapsed" desc="Tables and associated methods">
    public void addRowTruckInformation (Object[] parameters) {
        DefaultTableModel truckTableModel = (DefaultTableModel) truckInformationTable.getModel();
        truckTableModel.addRow(parameters);
    }
    
    public void removeRowTruckInformation (int rowIndex) {
        DefaultTableModel truckTableModel = (DefaultTableModel) truckInformationTable.getModel();
        truckTableModel.removeRow(truckInformationTable.getRowCount());
    }
    
    public void truckInformationTableUpdated (Object [][] newObjects, Object [] columnIdentifiers) {
        DefaultTableModel truckTableModel = (DefaultTableModel) truckInformationTable.getModel();
        truckTableModel.setDataVector(newObjects, columnIdentifiers);        
    }
    
    public void groupInformationTableUpdated (Object [][] newObjects, Object [] columnIdentifiers) {
        DefaultTableModel groupTableModel = (DefaultTableModel) groupInformationTable.getModel();
        groupTableModel.setDataVector(newObjects, columnIdentifiers);  
    }
    
    public void gatewayInformationTableUpdated (Object [][] newObjects, Object [] columnIdentifiers) {
        DefaultTableModel gatewayTableModel = (DefaultTableModel) gatewayInformationTable.getModel();
        gatewayTableModel.setDataVector(newObjects, columnIdentifiers);        
    }
    
    public DefaultTableModel getTruckTableModel () {
        return truckInformationTableModel;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Interface component declarations">    
    private javax.swing.JTabbedPane mainTabbedPanel;
    
    private javax.swing.JPanel groupPanel;
    private javax.swing.JPanel gatewayPanel;    
    private javax.swing.JPanel customFilteringPanel;
    private javax.swing.JPanel truckTrackingPanel;
    
    private javax.swing.JLabel filterExpressionLabel;
    private javax.swing.JLabel availableFilterLabel;
    private javax.swing.JLabel truckLacIcon;
    private javax.swing.JLabel groupLacIcon;
    private javax.swing.JLabel gatewayLacIcon;
    
    private javax.swing.JCheckBox gatewayConnectCheck;
    private javax.swing.JCheckBox groupConnectCheck;
    private javax.swing.JCheckBox truckConnectCheck;
    
    private javax.swing.JButton gatewayInformationButton;
    private javax.swing.JButton gatewayToggleGlobalButton;
    private javax.swing.JButton groupInformationButton;    
    private javax.swing.JButton groupToggleGlobalButton;
    private javax.swing.JButton resetFilterButton;
    private javax.swing.JButton saveFilterButton;
    private javax.swing.JButton truckInformationButton;
    private javax.swing.JButton truckToggleGlobalButton;

    private javax.swing.JRadioButton gatewayCustomFilterRadio;
    private javax.swing.JRadioButton gatewayFilterRadio;
    private javax.swing.JRadioButton groupCustomFilterRadio;
    private javax.swing.JRadioButton groupFilterRadio;
    private javax.swing.JRadioButton truckCustomFilterRadio;
    private javax.swing.JRadioButton truckGatewayFilterRadio;
    private javax.swing.JRadioButton truckGroupFilterRadio;
    private javax.swing.JRadioButton truckTrackingFilterRadio;
    private JRadioButton truckTrackingFilterTabRadio;
    private JRadioButton groupFilterTabRadio;
    private JRadioButton gatewayFilterTabRadio;    
    
    private javax.swing.JTextArea gatewayNotificationArea;
    private javax.swing.JTextArea groupNotificationArea;
    private javax.swing.JTextArea truckNotificationArea;
    private javax.swing.JTextArea customFilterArea;
    
    private javax.swing.JComboBox truckGatewayCombo;
    private javax.swing.JComboBox truckGroupCombo;
    private javax.swing.JComboBox truckTrackingCombo;
    private javax.swing.JComboBox truckCustomCombo;
    private javax.swing.JComboBox gatewayCombo;
    private javax.swing.JComboBox gatewayCustomCombo;
    private javax.swing.JComboBox groupCombo;
    private javax.swing.JComboBox groupCustomCombo;    
    
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    
    private javax.swing.JTextField expressionField;
    
    private DefaultTableModel truckInformationTableModel;
    private DefaultTableModel groupInformationTableModel;
    private DefaultTableModel gatewayInformationTableModel;
    
    private JTable groupInformationTable;
    private JTable truckInformationTable;
    private JTable gatewayInformationTable;
    
    private ButtonGroup truckFilterGroup;
    private ButtonGroup groupFilterGroup;
    private ButtonGroup gatewayFilterGroup;
    private ButtonGroup customFilterGroup;
    // </editor-fold>    
}