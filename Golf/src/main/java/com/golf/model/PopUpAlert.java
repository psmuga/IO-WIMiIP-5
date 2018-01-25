package com.golf.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Created by Michał Słowikowski.
 */
public class PopUpAlert {
    private Alert alert;

    private PopUpAlert(AlertBuilder builder){
        alert = new Alert(builder.type);
        alert.initOwner(builder.stage);
        alert.setTitle(builder.title);
        alert.setHeaderText(builder.header);
        alert.setContentText(builder.content);
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

        public PopUpAlert build() {
            return new PopUpAlert(this);
        }

    }
}