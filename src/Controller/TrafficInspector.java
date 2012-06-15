// Author: Matheus Maciel
// Class: TrafficInspector
// Last update: 15/06/2012
// Reasoning for last update: Comments

// Package 
package Controller;

// Imports 
import View.TrafficInspectorMainWindow;
 
// Application main class 
// Here the main window is called, along with the AppManager and the ContextDataReaderListener 
public class TrafficInspector {	
    public static void main(String[] args) {
        MAIN_WINDOW = new TrafficInspectorMainWindow();
        INSPECTOR_MANAGER = new AppManager(MAIN_WINDOW);       
        ContextDataReaderListener dtListener = new ContextDataReaderListener(INSPECTOR_MANAGER, MAIN_WINDOW);
        INSPECTOR_MANAGER.updateNewDataReader(dtListener);
        INSPECTOR_MANAGER.initializeListener();
        
        MAIN_WINDOW.setVisible(true);
	dtListener.prepare();
    }
    
    private static AppManager INSPECTOR_MANAGER;
    private static TrafficInspectorMainWindow MAIN_WINDOW;
}
