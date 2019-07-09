package ca.jhosek.main.client.activity.mainregion;

import ca.jhosek.main.client.ui.priv.student.StudentStartView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * Activities on the Student Start page
 * 
 * @author copyright (C) 2011 Andrew Stevko
 *
 */
public class StudentStartActivity extends AbstractActivity implements StudentStartView.Presenter {

	
	private StudentStartView view;
	@SuppressWarnings("unused")
	private PlaceController placeController;

	@Inject
	public StudentStartActivity( StudentStartView view, PlaceController placeController) {
		super();
		this.view = view;
		this.placeController = placeController;
		view.setPresenter(this);
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);

	}
}
