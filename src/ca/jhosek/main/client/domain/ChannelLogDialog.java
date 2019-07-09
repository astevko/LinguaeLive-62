/**
 * 
 */
package ca.jhosek.main.client.domain;

import java.util.Date;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A panel with a scrolling text box displaying timestampped log entries from the ChannelService
 * 
 * @author andy
 *
 */
public class ChannelLogDialog extends DialogBox {

	/**
	 * vertical list of log entry Labels
	 */
	private VerticalPanel logList;

	/**
	 * create a new dialog
	 * 
	 * @param modal  
	 * @param autoHide 
	 */
	public ChannelLogDialog(boolean autoHide, boolean modal) {
		super(autoHide, modal);
		// header message
		setText("Channel Log");

		// log entry list
	    logList = new VerticalPanel();

	    // wrapped in a scroll panel
	    ScrollPanel scrollPanel = new ScrollPanel( logList );
	    
	    // main widget
	    setWidget(scrollPanel);
	    
	    
	    addLogEntry("Log panel initialized.");
	}

	/**
	 * call this to add a log entry
	 * @param logEntry
	 */
	@SuppressWarnings("deprecation")
	public void addLogEntry(String logEntry) {
		// append the mm:ss time stamp with the last
		String[] t = new Date().toGMTString().split(":");		
		logList.add(new Label(t[1] + ":" + t[2].substring(0, 2) + logEntry));		
	}

}
