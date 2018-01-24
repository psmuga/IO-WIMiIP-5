package golf.view;

import io2017.pierogimroku.task.api.TaskLook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

/**
 * Created by Michał Słowikowski.
 */
public class TaskEditTest extends GuiTest {
    private Parent parent;

    @Override
    protected Parent getRootNode() {
        parent = null;
        try{
            parent = FXMLLoader.load(getClass().getClassLoader().getResource("golf/view/TaskEdit.fxml"));
            return parent;
        }catch (IOException ex) {
            System.out.println("FXML file not found");
        }
        return null;
    }

    @Test
    public void windowShouldContainNameAssigneeStatusDescriptionEstimatedTimePriorityAndOwner() {
        exists("#name");
        exists("#assignee");
        exists("#status");
        exists("#description");
        exists("#estimatedTime");
        exists("#priority");
        exists("#owner");
    }

    @Test
    public void estimatedTimeFieldShouldContainOnlyNumbers(){

        TextField estimatedTime = find("#estimatedTime");
        verifyThat("#estimatedTime",hasText(""));

        estimatedTime.setText("abcd");
        verifyThat("#estimatedTime",hasText(""));

        estimatedTime.setText("123");
        verifyThat("#estimatedTime",hasText("123"));

        estimatedTime.setText("a1bc2d3");
        verifyThat("#estimatedTime",hasText("123"));
    }

    @Test
    public void priorityFieldShouldContainOnlyNumbers(){

        TextField priority = find("#priority");
        verifyThat("#priority",hasText(""));

        priority.setText("abcd");
        verifyThat("#priority",hasText(""));

        priority.setText("123");
        verifyThat("#priority",hasText("123"));

        priority.setText("a1bc2d3");
        verifyThat("#priority",hasText("123"));
    }

    @Test
    public void shouldAlertShowIfThereAreEmptyFields(){
        click("#ok");
        find(hasText("Please try again or exit"));
    }
}
