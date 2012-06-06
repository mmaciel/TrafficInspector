import java.util.*;
import com.toc.coredx.DDS.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.event.*;


public class TrafficInspectorMainWindow extends JFrame {
    public TrafficInspectorMainWindow (final ContextDataReaderListener AppListener) {
        super ("Traffic Inspector");
		super.setSize(800, 600);   
		
		mainTabbedPanel = new javax.swing.JTabbedPane();
        truckTrackingPanel = new javax.swing.JPanel();
        truckTrackingCombo = new javax.swing.JComboBox();
        truckInformationButton = new javax.swing.JButton();
        truckGroupCombo = new javax.swing.JComboBox();
        truckGatewayCombo = new javax.swing.JComboBox();
        truckToggleGlobalButton = new javax.swing.JButton();
        truckCustomCombo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        truckInformationArea = new javax.swing.JTextArea();
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
        groupInformationArea = new javax.swing.JTextArea();
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
        gatewayInformationArea = new javax.swing.JTextArea();
        gatewayCustomCombo = new javax.swing.JComboBox();
        gatewayToggleGlobalButton = new javax.swing.JButton();
        gatewayInformationButton = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        gatewayNotificationArea = new javax.swing.JTextArea();
        gatewayFilterRadio = new javax.swing.JRadioButton();
        gatewayCustomFilterRadio = new javax.swing.JRadioButton();
        customFilteringPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea9 = new javax.swing.JTextArea();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		ButtonGroup truckFilterGroup = new ButtonGroup();
		truckFilterGroup.add(truckCustomFilterRadio);  
		truckFilterGroup.add(truckGatewayFilterRadio); 
		truckFilterGroup.add(truckGroupFilterRadio);
        truckFilterGroup.add(truckTrackingFilterRadio);		
		
		truckTrackingCombo.setModel(new javax.swing.DefaultComboBoxModel());
		truckTrackingCombo.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String truckName = (String)cb.getSelectedItem();
				int truckIndex = (int)cb.getSelectedIndex();
				System.out.println("--- " + truckName + " selected!");
				System.out.println("--- " + truckIndex + " selected!");	
				if (truckTrackingFilterSelected()) updateTruckInformationArea(ContextDataReaderListener.truckInformationMessages.get(truckIndex).toString());
			}
		});

        truckInformationButton.setText("Check truck information");

        truckGroupCombo.setModel(new javax.swing.DefaultComboBoxModel());
		truckGroupCombo.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String groupName = (String)cb.getSelectedItem();
				int groupIndex = (int)cb.getSelectedIndex();
				if (truckGroupFilterSelected()) TrafficInspectorMainWindow.updateTruckInformationArea(ContextDataReaderListener.sortTrucksByGroup(ContextDataReaderListener.truckMessages, Integer.parseInt(groupName)));
			}
		});

        truckGatewayCombo.setModel(new javax.swing.DefaultComboBoxModel());
		truckGatewayCombo.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String gatewayName = (String)cb.getSelectedItem();
				int gatewayIndex = (int)cb.getSelectedIndex();
				if (truckGatewayFilterSelected()) TrafficInspectorMainWindow.updateTruckInformationArea(ContextDataReaderListener.sortTrucksByGateway(ContextDataReaderListener.truckMessages, Integer.parseInt(gatewayName)));
			}
		});

        truckToggleGlobalButton.setText("Toggle Global List");

        truckCustomCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        truckInformationArea.setColumns(20);
        truckInformationArea.setRows(5);
        jScrollPane1.setViewportView(truckInformationArea);

        truckNotificationArea.setColumns(20);
        truckNotificationArea.setRows(5);
        jScrollPane2.setViewportView(truckNotificationArea);

        truckConnectCheck.setText("Connect to topic");
		truckConnectCheck.setSelected(true);
		
		truckConnectCheck.addActionListener (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JCheckBox cb = (JCheckBox)e.getSource();
				if (cb.isSelected() == true) {
					System.out.println("Connected to truck topic!");
				}
			}
		});
		
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
                    .addGroup(truckTrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(truckTrackingCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(truckInformationButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(truckGatewayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(truckCustomCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(truckToggleGlobalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(truckConnectCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(truckGroupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(truckTrackingFilterRadio)
                    .addComponent(truckGroupFilterRadio)
                    .addComponent(truckGatewayFilterRadio)
                    .addComponent(truckCustomFilterRadio))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(truckInformationButton)
                        .addGap(13, 13, 13)
                        .addComponent(truckGroupFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(truckGroupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(truckGatewayFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(truckGatewayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(truckCustomFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(truckCustomCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(truckToggleGlobalButton)
                        .addGap(0, 102, Short.MAX_VALUE))
                    .addGroup(truckTrackingPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        mainTabbedPanel.addTab("Truck Tracking Information", truckTrackingPanel);

        groupConnectCheck.setText("Connect to topic");
		groupConnectCheck.setSelected(true);

        groupInformationArea.setColumns(20);
        groupInformationArea.setRows(5);
        jScrollPane5.setViewportView(groupInformationArea);

        groupCombo.setModel(new javax.swing.DefaultComboBoxModel());

        groupInformationButton.setText("Check Group Information");

        groupCustomCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                        .addComponent(groupInformationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(groupCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(groupCustomCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(groupToggleGlobalButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(groupFilterRadio)
                    .addComponent(groupCustomFilterRadio))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupInformationButton)
                        .addGap(13, 13, 13)
                        .addComponent(groupCustomFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupCustomCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(groupToggleGlobalButton)
                        .addGap(0, 116, Short.MAX_VALUE))
                    .addComponent(jScrollPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainTabbedPanel.addTab("Group Information", groupPanel);

        gatewayConnectCheck.setText("Connect to topic");
		gatewayConnectCheck.setSelected(true);

        gatewayCombo.setModel(new javax.swing.DefaultComboBoxModel());

        gatewayInformationArea.setColumns(20);
        gatewayInformationArea.setRows(5);
        jScrollPane7.setViewportView(gatewayInformationArea);

        gatewayCustomCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                        .addComponent(gatewayInformationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gatewayToggleGlobalButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gatewayCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gatewayCustomCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gatewayConnectCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(gatewayFilterRadio)
                    .addComponent(gatewayCustomFilterRadio))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gatewayInformationButton)
                        .addGap(13, 13, 13)
                        .addComponent(gatewayCustomFilterRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gatewayCustomCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(gatewayToggleGlobalButton)
                        .addGap(0, 116, Short.MAX_VALUE))
                    .addComponent(jScrollPane7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainTabbedPanel.addTab("Gateway Load Information", gatewayPanel);

        jLabel13.setText("Type the desired filter expression:");

        jTextField1.setText("jTextField1");

        jLabel14.setText("Current available filters:");

        jTextArea9.setColumns(20);
        jTextArea9.setRows(5);
        jScrollPane9.setViewportView(jTextArea9);

        jButton9.setText("Save Filter");

        jButton10.setText("Reset Expression");

        javax.swing.GroupLayout customFilteringPanelLayout = new javax.swing.GroupLayout(customFilteringPanel);
        customFilteringPanel.setLayout(customFilteringPanelLayout);
        customFilteringPanelLayout.setHorizontalGroup(
            customFilteringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customFilteringPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customFilteringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customFilteringPanelLayout.createSequentialGroup()
                        .addGroup(customFilteringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addGroup(customFilteringPanelLayout.createSequentialGroup()
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10)))
                        .addGap(0, 268, Short.MAX_VALUE))
                    .addComponent(jScrollPane9))
                .addContainerGap())
        );
        customFilteringPanelLayout.setVerticalGroup(
            customFilteringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customFilteringPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customFilteringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        mainTabbedPanel.addTab("Custom Data Filtering", customFilteringPanel);

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
	
	
	/* ComboBox indexes */
	public static int truckGetSelectedTruck () {
		return truckTrackingCombo.getSelectedIndex();
	}
	
	/* RadioButton selection */
	public static boolean truckTrackingFilterSelected () {
		return truckTrackingFilterRadio.isSelected();
	}
	
	public static boolean truckGroupFilterSelected() {
		return truckGroupFilterRadio.isSelected();
	}
	
	public static boolean truckGatewayFilterSelected() {
		return truckGatewayFilterRadio.isSelected();
	}
	
	/* TextArea updates */
	public static void updateTruckInformationArea (String msg) {
		truckInformationArea.setText("");
		truckInformationArea.append(msg + "\n");
	}
	
	public static void updateTruckNotificationArea (String msg) {
		truckNotificationArea.append (msg + "\n");
	}	
	
	public static void updateGroupInfomationArea (String msg) {
		groupInformationArea.setText("");
		groupInformationArea.append(msg + "\n");		
	}
	
	public static void updateGroupNotificationArea (String msg) {
		groupNotificationArea.append(msg + "\n");
	}
	
	public static void updateGatewayMessageArea (String msg) {
		gatewayInformationArea.setText("");
		gatewayInformationArea.append(msg + "\n");
	}

	public static void updateGatewayNotificationArea (String msg) {
		gatewayNotificationArea.append(msg + "\n");
	}
	
	public static void updateNotificationAreas (String msg) {
		updateTruckNotificationArea(msg);
		updateGroupNotificationArea(msg);
		updateGatewayNotificationArea(msg);
	}
	
	/* ComboBox updates */
	public static void updateTruckCombo (String msg) {
		truckTrackingCombo.addItem(msg);
	}
	
	public static void updateTruckGroupCombo (String msg) {
		truckGroupCombo.addItem(msg);
	}
	
	public static void updateTruckGatewayCombo (String msg) {
		truckGatewayCombo.addItem(msg);
	}
	
	public static void updateTruckCustomCombo (String msg) {
		truckCustomCombo.addItem(msg);
	}
	
	public static void updateGroupCombo (String msg) {
		groupCombo.addItem(msg);
	}
	
	public static void updadeGroupCustomCombo (String msg) {
		groupCombo.addItem(msg);
	}
	
	public static void updateGatewayCombo (String msg) {
		gatewayCombo.addItem(msg);
	}	
	
	public static void updateGatewayCustomCombo (String msg) {
		gatewayCustomCombo.addItem(msg);
	} 
	
	private javax.swing.JPanel customFilteringPanel;
    private javax.swing.JCheckBox gatewayConnectCheck;
    private javax.swing.JButton gatewayInformationButton;
    private javax.swing.JPanel gatewayPanel;
    private javax.swing.JButton gatewayToggleGlobalButton;
    private javax.swing.JCheckBox groupConnectCheck;   
    private javax.swing.JButton groupInformationButton;
	
	private static javax.swing.JRadioButton gatewayCustomFilterRadio;
    private static javax.swing.JRadioButton gatewayFilterRadio;
    private static javax.swing.JRadioButton groupCustomFilterRadio;
    private static javax.swing.JRadioButton groupFilterRadio;
    private static javax.swing.JRadioButton truckCustomFilterRadio;    
    private static javax.swing.JRadioButton truckGatewayFilterRadio;    
    private static javax.swing.JRadioButton truckGroupFilterRadio;
    private static javax.swing.JRadioButton truckTrackingFilterRadio;
	
	private static javax.swing.JTextArea gatewayNotificationArea;
    private static javax.swing.JTextArea gatewayInformationArea;
	private static javax.swing.JTextArea groupInformationArea;
	private static javax.swing.JTextArea groupNotificationArea;
	private static javax.swing.JTextArea truckNotificationArea;
	private static javax.swing.JTextArea truckInformationArea;
    private static javax.swing.JTextArea jTextArea9;
	
	private static javax.swing.JComboBox truckGatewayCombo;
	private static javax.swing.JComboBox truckGroupCombo;
	private static javax.swing.JComboBox truckTrackingCombo;
	private static javax.swing.JComboBox truckCustomCombo;
	private static javax.swing.JComboBox gatewayCombo;
	private static javax.swing.JComboBox gatewayCustomCombo;
	private static javax.swing.JComboBox groupCombo;
	private static javax.swing.JComboBox groupCustomCombo;
	
	private javax.swing.JPanel groupPanel;
    private javax.swing.JButton groupToggleGlobalButton;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTabbedPane mainTabbedPanel;
    private javax.swing.JCheckBox truckConnectCheck;    
    private javax.swing.JButton truckInformationButton;
    private javax.swing.JButton truckToggleGlobalButton;    
    private javax.swing.JPanel truckTrackingPanel;
}