package ca.jhosek.main.client.domain;

import java.util.logging.Logger;

import ca.jhosek.main.client.event.SessionChatEvent;
import ca.jhosek.main.client.event.SessionInviteUpdateEvent;
import ca.jhosek.main.client.event.SessionUpdateEvent;
import ca.jhosek.main.client.event.UserStatusChangeEvent;
import ca.jhosek.main.client.place.PartnerInvitePlace;
import ca.jhosek.main.client.place.SessionControlPlace;
import ca.jhosek.main.shared.proxy.SessionProxy;

import com.google.gwt.appengine.channel.client.Channel;
import com.google.gwt.appengine.channel.client.ChannelFactory.ChannelCreatedCallback;
import com.google.gwt.appengine.channel.client.Socket;
import com.google.gwt.appengine.channel.client.SocketError;
import com.google.gwt.appengine.channel.client.SocketListener;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

/**
 * Class used to handle GAE Channel callbacks from the server. 
 * Created by CurrentState
 * 
 * @author andy
 * @see CurrentState
 * 
 */
class ChannelCallback implements ChannelCreatedCallback {

	/**
	 * This is the listener for Messages and events on the ChannelCallbackSocket
	 * 
	 * @author andy
	 * 
	 */
	private final class ChannelSocketListener implements SocketListener {
		/**
		 * called when closed
		 * 
		 * @see com.google.gwt.appengine.channel.client.SocketListener#onClose()
		 */
		@Override
		public void onClose() {
//			//logDialog.addLogEntry("Channel onClose() called.");
			logger.warning("Channel closed!");
			// TODO: update current status to OFFLINE
			Timer restartChannelTimer = new Timer() {

				@Override
				public void run() {
//					//logDialog.addLogEntry("Attempting to reopen the channel.");
					// restart the channel
					currentState.connectUserToChannel();
				}
				
			};
			restartChannelTimer.schedule(2000);
		}

		/**
		 * called when SocketError
		 * 
		 * @see com.google.gwt.appengine.channel.client.SocketListener#onError(com.google.gwt.appengine.channel.client.SocketError)
		 */
		@Override
		public void onError(final SocketError error) {
//			//logDialog.addLogEntry("Channel onError() called.");
			logger.warning("Error: " + error.getDescription());
			// TODO: warn user of network congestion
			Window.setStatus("Network congestion encountered.");
			
		}

		/**
		 * listening for a message when a message arrives...
		 * 
		 * Message format - pipe "|" delimited - first field defines payload -
		 * 
		 * @see com.google.gwt.appengine.channel.client.SocketListener#onMessage(java.lang.String)
		 */
		@Override
		public void onMessage(final String message) {
			logger.info("Received: " + message);
			//logDialog.addLogEntry(message);
			try {
				// parse message into two tokens
				final String[] tokens = message.split("\\|");

				final String key = tokens[0].trim(); // always first field
				final Long timestamp = new Long(tokens[tokens.length - 1].trim()); // always
																	// last
																	// field

				if (key.compareTo("status") == 0) {
					// --------------------------------------------
					// user online status advertised
					// @see User.sendMessage()
					// status | email@ddress | [ONLINE|OFFLINE|UNKONWN] |
					// timestamp
					// @see SessionDao.setOnlineStatus to in session
					// status | email@ddress | INSESSION | sessionid | timestamp
					eventBus.fireEvent(new UserStatusChangeEvent(tokens[1].trim(), tokens[2].trim(), timestamp));

				} else if (key.compareTo("affiliates") == 0) {
					// --------------------------------------------
					// user affiliates
					// @see User.getAffiliates()
					// affiliates | email | email | email

				} else if (key.compareTo("sessionInvite") == 0) {
					// --------------------------------------------
					// session update coming down
					// @see SessionInviteDao.sendNotification(...)
					// sessionInvite | session invite id | timestamp
					final String sessionInviteId = tokens[1].trim();
					final Place partnerInvitePlace = new PartnerInvitePlace(sessionInviteId);
					// is new place not the current place?
					if (!partnerInvitePlace.equals(placeController.getWhere())) {
						// current session invite is in focus; send user there
						// immediately
						if (Window
								.confirm("Another user is about to start a session with you. Are you ready?")) {
							placeController.goTo(partnerInvitePlace);
						}
					} else {
						eventBus.fireEvent(new SessionInviteUpdateEvent(sessionInviteId));
					}

				} else if (key.compareTo("sessionChat") == 0) {
					// --------------------------------------------
					// session chat appended to...
					final String sessionId = tokens[1].trim();
					eventBus.fireEvent(	new SessionChatEvent(sessionId, tokens[2].trim()));
					
				} else if (key.compareTo("session") == 0) {
					// --------------------------------------------
					final Long newSessionId = new Long(tokens[1].trim());
					final SessionProxy currentSession = currentState.getInFocusSession();
					// is this the same session as current?
					if (newSessionId.equals(currentState.getInFocusSessionId())) {
						// update current session info
						//logDialog.addLogEntry(">update session info " + newSessionId);
						eventBus.fireEvent(new SessionUpdateEvent(tokens[1].trim()));						
					} else {
						// switch to next session forward by cancelling current session without saving them forward user to new
						if (currentSession != null && isActive(currentSession) )  {
							// if currently session is in progress and SessionControl is shown
							// then cancel current session (without saving)  
							currentSession.setCancelled(true);	// temporarily
						}
						// AND 
						// send user to new session immediately 
						placeController
								.goTo(new SessionControlPlace(newSessionId));
					}

				} else {
					// --------------------------------------------
					logger.warning("ignored unknown notification " + key);
				}
			} catch (final NumberFormatException e) {
				// not a proper message
				logger.warning("unparsable message:" + message);

			} catch (final ArrayIndexOutOfBoundsException e) {
				// not a proper message
				logger.warning("unparsable message:" + message);
			}
		}

		@Override
		public void onOpen() {
			logger.info("Channel opened! checking for open sessions");
			//logDialog.addLogEntry("Channel opened! checking for open sessions");

			// check for inprogress sessions
			currentState.checkForInprogressSessions();
		}
	}
	/**
	 * isActive means...
	 * not canceled and has a start time and does not have a start time
	 * 
	 * @param session
	 * @return is the session active
	 */
	private boolean isActive( SessionProxy session ) {
		
		return ( !session.getCancelled() && session.getStartTime() != null && session.getStopTime() == null );
	}

	private static final Logger logger = Logger.getLogger(ChannelCallback.class
			.getName());

	/**
	 * CurrentState initializes the ChannelCallback on ctor
	 */
	private final CurrentState currentState;

	/**
	 * track the socket created so that we can close it.
	 */
	private Socket socket = null;

	/**
	 * show this dialog
	 */
//	private final Channel//logDialog //logDialog; // = new Channel//logDialog( false,
												// false );

	private final PlaceController placeController;

	private final EventBus eventBus;


	/**
	 * show to //logDialog
	 * 
	 * @param currentState
	 *            TODO
	 */
	public ChannelCallback(final CurrentState currentState,
			final EventBus eventBus,
			final PlaceController placeController) {
		this.currentState = currentState;
		this.eventBus = eventBus;
		this.placeController = placeController;
//		this.//logDialog = new Channel//logDialog(false, false);
//		//logDialog.show();
	}

	/**
	 * close the Channel's socket
	 * 
	 * @see CurrentState.logoutUser()
	 */
	public void closeChannel() {
		if (socket != null) {
			socket.close();
			socket = null;
		}
	}

	@Override
	public void onChannelCreated(final Channel channel) {
		if (socket != null) {
			//logDialog.addLogEntry("Closing previously open channel.");

			// close previous channels
			closeChannel();
		}
		socket = channel.open(new ChannelSocketListener());
	}
}