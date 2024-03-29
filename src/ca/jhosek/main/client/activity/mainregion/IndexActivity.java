package ca.jhosek.main.client.activity.mainregion;

import ca.jhosek.main.client.place.LoginFormPlace;
import ca.jhosek.main.client.place.NewInstructorPlace;
import ca.jhosek.main.client.place.NewStudentPlace;
import ca.jhosek.main.client.ui.anon.IndexView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * Main index page activity
 * 
 * @author copyright (C) 2011 Andrew Stevko
 *
 */
public class IndexActivity extends AbstractActivity implements IndexView.Presenter {

	private IndexView view;
	private PlaceController placeController;

	@Inject
	public IndexActivity( IndexView view, PlaceController placeController) {
		super();
		this.view = view;
		this.placeController = placeController;
		view.setPresenter(this);
	}

	/* (non-Javadoc)
	 * @see ca.jhosek.main.client.ui.anon.IndexView.Presenter#goToLoginForm()
	 */
	
	public void goToLoginForm() {
		// 
		placeController.goTo( new LoginFormPlace( ) );
		
	}

	/* (non-Javadoc)
	 * @see ca.jhosek.main.client.ui.anon.IndexView.Presenter#goToNewInstructorForm()
	 */
	
	public void goToNewInstructorForm() {
		// 
		placeController.goTo( new NewInstructorPlace() );
				
	}

	/* (non-Javadoc)
	 * @see ca.jhosek.main.client.ui.anon.IndexView.Presenter#goToNewStudentForm()
	 */
	
	public void goToNewStudentForm() {
		// 		// 
		placeController.goTo( new NewStudentPlace( "" ) );
		
	}

	
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);

	}


}
