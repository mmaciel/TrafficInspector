/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 import com.toc.coredx.DDS.*;
 
/**
 *
 * @author Matheus
 */
public class ContextMonitor {
	
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final ContextDataReaderListener dtListener = new ContextDataReaderListener();
		TrafficInspectorMainWindow mainWindow = new TrafficInspectorMainWindow(dtListener);
        mainWindow.setVisible(true);
		dtListener.prepare();
    }
}
