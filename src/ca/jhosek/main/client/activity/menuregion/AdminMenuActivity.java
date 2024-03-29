/**
 * copyright (c) 2011 Andrew Stevko, all rights reserved
 */
package ca.jhosek.main.client.activity.menuregion;



import ca.jhosek.main.client.place.AdminHomePlace;
import ca.jhosek.main.client.place.QueryCoursesPlace;
import ca.jhosek.main.client.place.QueryUsersPlace;
import ca.jhosek.main.client.ui.priv.admin.AdminMenuView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * menu activity of admin users
 * 
 * @author copyright (C) 2011 Andrew Stevko
 *
 */
public class AdminMenuActivity extends AbstractActivity implements AdminMenuView.Presenter {
	private final AdminMenuView view;
	private final PlaceController placeController;
	
	/**
	 * 
	 */
	@Inject
	public AdminMenuActivity( AdminMenuView view, PlaceController placeController ) {
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
	 * @see ca.jhosek.main.client.ui.priv.admin.AdminMenuView.Presenter#goToAdminHome()
	 */
	
	public void goToAdminHome() {
		// 
		placeController.goTo( new AdminHomePlace());
		
	}

	/* (non-Javadoc)
	 * @see ca.jhosek.main.client.ui.priv.admin.AdminMenuView.Presenter#goToQueryUsers()
	 */
	
	public void goToQueryUsers() {
		// 
		placeController.goTo( new QueryUsersPlace());	
	}

	/* (non-Javadoc)
	 * @see ca.jhosek.main.client.ui.priv.admin.AdminMenuView.Presenter#goToQueryCourses()
	 */
	
	public void goToQueryCourses() {
		// 
		placeController.goTo( new QueryCoursesPlace() );
		
	}
}
