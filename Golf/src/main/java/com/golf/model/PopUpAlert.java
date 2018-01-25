package com.golf.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PopUpAlert {

    private Alert alert;

    private PopUpAlert(AlertBuilder builder){
        alert = new Alert(builder.type);
        alert.initOwner(builder.stage);
        alert.setTitle(builder.title);
        alert.setHeaderText(builder.header);
        TextArea content = new TextArea(builder.content);
        content.setId("alertContent");
        alert.getDialogPane().setContent(content);
        if(builder.id != null){
            Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setId(builder.id);
        }
    }

    public Alert get() {
        return alert;
    }

    public static class AlertBuilder {
        private final Alert.AlertType type;
        private final Stage stage;
        private String title;
        private String header;
        private String content;
        private String id;

        public AlertBuilder(Alert.AlertType type, Stage owner) {
            this.type = type;
            this.stage = owner;
        }

        public AlertBuilder title(String title) {
            this.title = title;
            return this;
        }

        public AlertBuilder header(String header) {
            this.header = header;
            return this;
        }

        public AlertBuilder content(String content) {
            this.content = content;
            return this;
        }

        public AlertBuilder setButtonId(String id){
            this.id = id;
            return this;
        }

        public PopUpAlert build() {
            return new PopUpAlert(this);
        }

    }
}