/**
 * copyright (c) 2011 Andrew Stevko, all rights reserved
 */
package ca.jhosek.main.client.ui.anon;

import ca.jhosek.main.client.activity.mainregion.NewStudentFormActivity;
import ca.jhosek.main.client.place.NewStudentPlace;
import ca.jhosek.main.server.email.NewStudentEmail;
import ca.jhosek.main.shared.proxy.UserProxy;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * New Student Sign Up
 * 
 * @author copyright (C) 2011 Andrew Stevko
 *
 * @see NewStudentPanelImpl
 * @see NewStudentFormActivity
 * @see NewStudentPlace
 * @see NewStudentEmail
 * 
 */
public interface NewStudentPanel extends IsWidget,  Editor<UserProxy>{
	
	/**
	 * @param presenter
	 */
	public void setPresenter(Presenter presenter);
	
	/**
	 * displays/hides user form (initially hidden)
	 * @param showUserForm
	 */
	public void setUserFormVisibility( boolean showUserForm );
	/**
	 * displays/hides invite code form (initially visible)
	 * @param showInviteCodeForm
	 */
	public void setInviteCodeFormVisibility( boolean showInviteCodeForm );
	
	public interface Presenter {
		/**
		 * checks invite code entered by user;
		 * @param inviteCode
		 */
		void acceptInviteCode( String inviteCode );
		
		void createNewStudent( );
		void cancel();
	}

	public void setInviteCode(String courseInviteCode);
}
