package com.projectplace.gwt.relhello.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.projectplace.gwt.reltime.client.RelativeTime;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RelativeHello implements EntryPoint {
    private final DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss ZZZZ");
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Button addButton = new Button("Add");
        final Button updateButton = new Button("Update all");
        final TextBox dateField = new TextBox();

        dateField.setText(dateTimeFormat.format(new Date()));
        final Label errorLabel = new Label();
        errorLabel.addStyleName("serverResponseLabelError");

        addButton.addStyleName("tryButton");
        
        updateButton.addClickHandler(new ClickHandler() { 
            public void onClick(ClickEvent event) {
                RelativeTime.updateAllTended();
            }
        });
        RootPanel.get("dateFieldContainer").add(dateField);
        RootPanel.get("tryButtonContainer").add(addButton);
        RootPanel.get("tryButtonContainer").add(updateButton);
        RootPanel.get("errorLabelContainer").add(errorLabel);

        dateField.setFocus(true);
        dateField.selectAll();

        // Create a handler for the sendButton and dateField
        class MyHandler implements ClickHandler, KeyUpHandler {
            /**
             * Fired when the user clicks on the sendButton.
             */
            public void onClick(ClickEvent event) {
                addDate();
            }

            /**
             * Fired when the user types in the dateField.
             */
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    addDate();
                }
            }

            /**
             * Convert the date using @link RelativeTime.
             */
            private void addDate() {
                errorLabel.setText("");
                String dateString = dateField.getText();
                final Label resultLabel = new Label();
                RootPanel.get("resultLabelContainer").insert(resultLabel, 0);
                final RelativeTime relativeFormatter = new RelativeTime();
                try {
                    Date date = dateTimeFormat.parse(dateString);
                    relativeFormatter.tend(resultLabel, date);
                }
                catch (IllegalArgumentException e) {
                    resultLabel.setText("");
                    errorLabel.setText("Error parsing that date: " + e.getMessage());
                }
            }
        }

        MyHandler handler = new MyHandler();
        addButton.addClickHandler(handler);
        dateField.addKeyUpHandler(handler);
    }
}
