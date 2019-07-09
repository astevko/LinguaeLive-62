/**
 * copyright (c) 2011 Andrew Stevko, all rights reserved
 */
package ca.jhosek.main.client.ui.priv.student;

import ca.jhosek.main.client.activity.mainregion.AddCourseMemberActivity;
import ca.jhosek.main.client.place.AddCourseMemberPlace;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Add A Course Member View 
 * 
 * @author copyright (C) 2011 Andrew Stevko
 * 
 * @see AddCourseMemberPlace
 * @see AddCourseMemberViewImpl
 * @see AddCourseMemberActivity
 */
public interface AddCourseMemberView extends IsWidget {

	public interface Presenter {
		void saveCourseMember( String inviteCode );
		void cancel();	// goes to currentUser home page
	}		
	public void setPresenter(Presenter presenter);

}
