/**
 * copyright (c) 2011 Andrew Stevko, all rights reserved
 */
package ca.jhosek.main.client.ui.priv;

import java.util.List;

import ca.jhosek.main.client.activity.mainregion.MyCoursesActivity;
import ca.jhosek.main.client.place.MyCoursesPlace;
import ca.jhosek.main.shared.proxy.CourseProxy;

import com.google.gwt.user.client.ui.IsWidget;

/**
 *  My Courses user page 
 * 
 * @author copyright (C) 2011 Andrew Stevko
 * 
 * @see MyCoursesPlace
 * @see MyCoursesViewImpl
 * @see MyCoursesActivity
 * 
 *
 */
public interface MyCoursesView extends IsWidget {

	public void setPresenter(Presenter presenter);
	/**
	 * display no query results
	 */
	public void emptyQueryResults();
	/**
	 * @param courses list of courses
	 */
	public void showCourseList( List<CourseProxy> courses );

	/**
	 * clear ui
	 */
	public void clear();

	public interface Presenter {
		/**
		 * goto add a course view (instructor) or add a course member (student)
		 */
		void addACourse();
		/**
		 * view the course
		 * @param course
		 */
		void viewCourse( CourseProxy course );
	}
}
