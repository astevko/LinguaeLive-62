/**
 * 
 */
package ca.jhosek.main.server.channel;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.jhosek.main.server.domain.User;
import ca.jhosek.main.server.domain.UserDao;

import com.google.appengine.api.channel.ChannelPresence;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 * server side tracking user channel connections
 * 
 * @author andy
 *
 */
public class UserChannelConnectMonitor extends HttpServlet {
	/**
	 * 
	 */
	private final Logger logger = Logger.getLogger(UserChannelConnectMonitor.class.getName());

	/**
	 * initialize Memcache and channelService
	 */
	public UserChannelConnectMonitor() {
		super();
		// initialize the Memcache once per HttpServlet instance
		syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
		channelService = ChannelServiceFactory.getChannelService();
	}

	/**
	 * does nothing special
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	/**
	 * handle POST requests
	 * update memcache with clientId and isOnline flag
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
	
		final ChannelPresence presence = channelService.parsePresence(req);
//		final String isOnlineFlag = presence.isConnected() ? "isOnline" : "";
		
		logger.info("channel session doPost: connection for " + presence.clientId() + " is " + (presence.isConnected() ? "isOnline" : "offline")); 
		logger.info("channel session request query string: " + req.getQueryString());
		
		UserDao userDao = new UserDao();
		User u = userDao.findEmailAddress(presence.clientId());
		if (u != null && !presence.isConnected()) {
			u.setOnlineStatus( "OFFLINE");
		}
		
		// populate cache with isOnlineFlag or blank
	    // syncCache.put( presence.clientId(), isOnlineFlag); 
	    
	    // ok, got a post... send update to related users
	    
	}

	/**
	 * MemcacheService updated with isOnline flags  for every ChannelPresence.clientId()
	 */
	private final MemcacheService syncCache;

	/**
	 * serialized version number
	 */
	private static final long serialVersionUID = 6929097402558622237L;

	/**
	 * ChannelService yay!
	 */
	private final ChannelService channelService;

}
