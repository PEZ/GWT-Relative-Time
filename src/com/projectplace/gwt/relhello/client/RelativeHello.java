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
        final Button sendButton = new Button("Try");
        final TextBox dateField = new TextBox();

        dateField.setText(dateTimeFormat.format(new Date()));
        final Label resultLabel = new Label();
        final Label errorLabel = new Label();
        errorLabel.addStyleName("serverResponseLabelError");

        sendButton.addStyleName("tryButton");

        RootPanel.get("dateFieldContainer").add(dateField);
        RootPanel.get("tryButtonContainer").add(sendButton);
        RootPanel.get("resultLabelContainer").add(resultLabel);
        RootPanel.get("errorLabelContainer").add(errorLabel);

        dateField.setFocus(true);
        dateField.selectAll();

        // Create a handler for the sendButton and dateField
        class MyHandler implements ClickHandler, KeyUpHandler {
            /**
             * Fired when the user clicks on the sendButton.
             */
            public void onClick(ClickEvent event) {
                convertDate();
            }

            /**
             * Fired when the user types in the dateField.
             */
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    convertDate();
                }
            }

            /**
             * Convert the date using @link RelativeTime.
             */
            private void convertDate() {
                errorLabel.setText("");
                String dateString = dateField.getText();
                final RelativeTime relativeFormatter = new RelativeTime();
                try {
                    Date date = dateTimeFormat.parse(dateString);
                    resultLabel.setText(relativeFormatter.format(date));
                }
                catch (IllegalArgumentException e) {
                    resultLabel.setText("");
                    errorLabel.setText("Error parsing that date: " + e.getMessage());
                }
            }
        }

        MyHandler handler = new MyHandler();
        sendButton.addClickHandler(handler);
        dateField.addKeyUpHandler(handler);
    }
}
