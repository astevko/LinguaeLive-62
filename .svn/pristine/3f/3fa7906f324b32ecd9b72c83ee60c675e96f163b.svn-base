package ca.jhosek.main.client;

import ca.jhosek.main.client.domain.CurrentState;
import ca.jhosek.main.client.ui.MainView;
import ca.jhosek.main.shared.proxy.AppRequestFactory;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;


@GinModules({AppGinModule.class})
public interface AppGinjector extends Ginjector {

  PlaceHistoryHandler getPlaceHistoryHandler();  
  MainView getMainView();
  CurrentState getCurrentState();
  AppRequestFactory getRequestFactory();
  AppResources getAppResources();
}
