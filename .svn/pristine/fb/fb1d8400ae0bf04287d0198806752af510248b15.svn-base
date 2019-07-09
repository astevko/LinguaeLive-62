/**
 * copyright (c) 2011 Andrew Stevko, all rights reserved
 */
package ca.jhosek.main.client.ui.priv.admin;

import ca.jhosek.main.client.ui.priv.admin.AdminCourseViewImpl.Driver;

import com.google.gwt.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * Admin Course View (embedded within QueryCourses...) 
 * 
 * @author copyright (C) 2011 Andrew Stevko
 * @see QueryCoursesActivity.java
 * @see QueryCoursesView.java
 */
public interface AdminCourseView extends IsWidget {

	public interface Presenter {
		void saveCourseEdits();
		void resetCourse();
	}		
	public void setPresenter(Presenter presenter);
	public void resetView();
	public void acceptEdits();
//	public void refreshContactInfos();
	public Driver createEditorDriver(EventBus eventBus,
			RequestFactory requestFactory);

}
