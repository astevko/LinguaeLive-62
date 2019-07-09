/**
 * copyright (c) 2011 Andrew Stevko, all rights reserved
 */
package ca.jhosek.main.client.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.ui.client.adapters.HasTextEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author copyright (C) 2011 Andrew Stevko
 *
 * from http://thezukunft.com/2010/02/05/an-editable-label-for-gwt-with-uibinder-and-eventhandlers/
 */
public class LabelEditor extends Composite implements HasValue<String>, IsEditor<LeafValueEditor<String>>, HasText {
	// logger boilerplate
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(LabelEditor.class
			.getName());
	private static EditableLableUiBinder uiBinder = GWT
	.create(EditableLableUiBinder.class);
	@UiField TextBox editBox;
	@UiField Label editLabel;
	@UiField DeckPanel deckPanel;
	@UiField FocusPanel focusPanel;

	interface EditableLableUiBinder extends UiBinder<Widget, LabelEditor> {
	}

	public LabelEditor() {
		initWidget(uiBinder.createAndBindUi(this));

		deckPanel.showWidget(0);

		focusPanel.addFocusHandler(new FocusHandler() {
			public void onFocus(FocusEvent event) {
				switchToEdit();
			}
		});

		editLabel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				switchToEdit();
			}
		});

//		editBox.addBlurHandler(new BlurHandler() {
//			@Override
//			public void onBlur(BlurEvent event) {
//				switchToLabel();
//			}
//		});
//
//		editBox.addKeyPressHandler(new KeyPressHandler() {
//
//			@Override
//			public void onKeyPress(KeyPressEvent event) {
//
//				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
//					switchToLabel();
//				}
//				else if (event.getCharCode() == KeyCodes.KEY_ESCAPE) {
//					editBox.setText(editLabel.getText()); // reset to the original value
//				}
//			}
//		});

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.logical.shared.HasValueChangeHandlers#addValueChangeHandler(com.google.gwt.event.logical.shared.ValueChangeHandler)
	 */
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<String> handler) {
		// 
		return addHandler( handler, ValueChangeEvent.getType() );
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasValue#getValue()
	 */
	public String getValue() {
		// 
		return editLabel.getText();
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object)
	 */
	public void setValue(String value) {
		// 
		editLabel.setText( value );
		editBox.setText(value);

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object, boolean)
	 */
	public void setValue(String value, boolean fireEvents) {
		// 
		if( fireEvents ) ValueChangeEvent.fireIfNotEqual(this, getValue(), value );
		setValue( value );

	}

	public void switchToEdit() {
		if(deckPanel.getVisibleWidget() == 1) return;
		editBox.setText(getValue());
		deckPanel.showWidget(1);
		editBox.setFocus(true);
	}

	public void switchToLabel() {
		if(deckPanel.getVisibleWidget() == 0) return;
		setValue(editBox.getText(), true); // fires events, too
		deckPanel.showWidget(0);
	}

	/**
	 * just like switchToLabel but looses edits
	 */
	public void reset() {
		if(deckPanel.getVisibleWidget() == 0) return;
		// reset text box
		editBox.setText( getValue() );
		// setValue(editBox.getText(), true); // fires events, too
		deckPanel.showWidget(0);
	}
	/**
	 * accepts edits and clears ui
	 */
	public void acceptEdits() {
		switchToLabel();
	}
	/* (non-Javadoc)
	 * @see com.google.gwt.editor.client.IsEditor#asEditor()
	 */
	public LeafValueEditor<String> asEditor() {
		// 
		return HasTextEditor.of(this);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasText#getText()
	 */
	public String getText() {
		// 
		return getValue();
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasText#setText(java.lang.String)
	 */
	public void setText(String text) {
		// 
		setValue(text);
		
	}

}
