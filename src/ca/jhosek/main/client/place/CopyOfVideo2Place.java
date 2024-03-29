/**
 * copyright (c) 2011 Andrew Stevko, all rights reserved
 */
package ca.jhosek.main.client.place;

import ca.jhosek.main.client.activity.mainregion.Video1Activity;
import ca.jhosek.main.client.ui.anon.Video1View;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * user is anon, show video page
 *   
 * @author copyright (C) 2012 Andrew Stevko
 * @see CopyOfVideo2Place
 * @see Video1Activity
 * @see Video1View
 *
 */
public class CopyOfVideo2Place extends Place {

	@Prefix("video")
	public static class Tokenizer implements PlaceTokenizer<CopyOfVideo2Place> {

		/* (non-Javadoc)
		 * @see com.google.gwt.place.shared.PlaceTokenizer#getPlace(java.lang.String)
		 */
		public CopyOfVideo2Place getPlace(String token) {
			// 
			return new CopyOfVideo2Place();
		}

		/* (non-Javadoc)
		 * @see com.google.gwt.place.shared.PlaceTokenizer#getToken(com.google.gwt.place.shared.Place)
		 */
		public String getToken(CopyOfVideo2Place place) {
			// 
			return "";
		}
		
	}
}
