/**
 * copyright (c) 2011 Andrew Stevko, all rights reserved
 */
package ca.jhosek.main.client.activity.menuregion;



import ca.jhosek.main.client.place.MyCoursesPlace;
import ca.jhosek.main.client.place.MyProfilePlace;
import ca.jhosek.main.client.place.StudentHomePlace;
import ca.jhosek.main.client.place.StudentStartPlace;
import ca.jhosek.main.client.ui.priv.student.StudentMenuView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * menu activity of student users
 * 
 * @author copyright (C) 2011 Andrew Stevko
 *
 */
public class StudentMenuActivity extends AbstractActivity implements StudentMenuView.Presenter {
	private final StudentMenuView view;
	private final PlaceController placeController;
	
	/**
	 * 
	 */
	@Inject
	public StudentMenuActivity( StudentMenuView view, PlaceController placeController ) {
		this.view = view;
		this.placeController = placeController;
		// 
		view.setPresenter( this );
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client.ui.AcceptsOneWidget, com.google.web.bindery.event.shared.EventBus)
	 */
	
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		
	}

	/* (non-Javadoc)
	 * @see ca.jhosek.main.client.ui.priv.admin.StudentMenuView.Presenter#goToStudentHome()
	 */
	
	public void goToStudentHome() {
		// 
		placeController.goTo( new StudentHomePlace());
		
	}

	/* (non-Javadoc)
	 * @see ca.jhosek.main.client.ui.priv.admin.StudentMenuView.Presenter#goToQueryUsers()
	 */
	
	public void goToMyProfile() {
		// 
		placeController.goTo( new MyProfilePlace());

		
	}

	/* (non-Javadoc)
	 * @see ca.jhosek.main.client.ui.priv.instructor.InstructorMenuView.Presenter#goToMyClasses()
	 */
	
	public void goToMyClasses() {
		placeController.goTo( new MyCoursesPlace());
		
	}

	/* (non-Javadoc)
	 * @see ca.jhosek.main.client.ui.priv.instructor.InstructorMenuView.Presenter#goToInstructorStart()
	 */
	
	public void goToStudentStart() {
		placeController.goTo( new StudentStartPlace() );
		
	}
	
}
