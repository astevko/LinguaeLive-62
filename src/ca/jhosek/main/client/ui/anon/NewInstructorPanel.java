/**
 * copyright (c) 2011 Andrew Stevko, all rights reserved
 */
package ca.jhosek.main.client.ui.anon;

import ca.jhosek.main.client.activity.mainregion.NewInstructorFormActivity.Driver;
import ca.jhosek.main.shared.proxy.UserProxy;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author copyright (C) 2011 Andrew Stevko
 *
 */
public interface NewInstructorPanel extends IsWidget,  Editor<UserProxy> {
	public void setPresenter(Presenter presenter);
	public String getEmailAddress();

	public interface Presenter {
		void createNewInstructor( );
		void cancel();
		
	}

	Driver createEditorDriver(EventBus eventBus, RequestFactory requestFactory);
}
