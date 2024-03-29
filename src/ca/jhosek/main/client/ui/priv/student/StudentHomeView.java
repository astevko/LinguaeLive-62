/**
 * copyright (c) 2011 Andrew Stevko, all rights reserved
 */
package ca.jhosek.main.client.ui.priv.student;

import java.util.List;

import ca.jhosek.main.client.activity.mainregion.StudentHomeActivity;
import ca.jhosek.main.client.place.StudentHomePlace;
import ca.jhosek.main.shared.proxy.CourseProxy;
import ca.jhosek.main.shared.proxy.PartnerInviteProxy;
import ca.jhosek.main.shared.proxy.SessionProxy;
import ca.jhosek.main.shared.proxy.UserProxy;

import com.google.gwt.user.client.ui.IsWidget;

/**
 *  Student home page 
 * 
 * @author copyright (C) 2011 Andrew Stevko
 * 
 * @see StudentHomeViewImpl
 * @see StudentHomeActivity
 * @see StudentHomePlace
 *
 */
public interface StudentHomeView extends IsWidget {

	public void setPresenter(Presenter presenter);
	public void clear();

	public void showSessionInvites(    List<PartnerInviteProxy> sessionInvites);
	public void showSessions( 		   List<SessionProxy> sessions );
	
	public interface Presenter {

		/**
		 * when user selects a session invite
		 * @param sessionInvite
		 */
		void goToPartnerInvite(PartnerInviteProxy sessionInvite);
		/**
		 * when user selects a session
		 * @param session
		 */
		void goToSession(SessionProxy session);

		/**
		 * @return the currently logged in user
		 */
		UserProxy getLoggedInUser();
		
		/**
		 * view the course
		 * @param course
		 */
		void viewCourse( CourseProxy course );
		void goToAddCourse();		
	}

	void showCourseList(List<CourseProxy> courses);
}
