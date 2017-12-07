package golf;

import io2017.pierogimroku.task.ORMLiteTaskManager;
import io2017.pierogimroku.task.api.ITaskManager;
import io2017.pierogimroku.task.api.ITaskView;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import io2017.pierogimroku.task.api.Task;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by slovi on 07.12.2017.
 */
public class TempTaskTest {
    private TempTasks tempTasksToTest;
    ITaskManager userTaskManager;
    ITaskView taskSearcher;
    @Before
    public void setUp()
    {
        this.userTaskManager = Mockito.mock(ITaskManager.class);
        this.taskSearcher = Mockito.mock(ITaskView.class);
        this.tempTasksToTest = new TempTasks(userTaskManager,taskSearcher);
    }

    @Test
    public void shouldGetOneTaskWithNameSomeTaskAndDescriptionSomething()
    {
        int idToSearch = 0;
        List<Task> tasksToReturnFromMock = Arrays.asList(new Task("Some Task", "Something", null, 0,0,0,0));
        when(taskSearcher.searchTaskByAssignedEmployee(idToSearch)).thenReturn(tasksToReturnFromMock);

        tempTasksToTest.takeTasks(idToSearch);

        verify(taskSearcher, times(1)).searchTaskByAssignedEmployee(idToSearch);
        assertEquals(tasksToReturnFromMock,tempTasksToTest.getUserTasks());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionIfThereAreNoTasksInListFromSearching()
    {
        int idToSearch = 0;
        NoSuchElementException emptyListException = new NoSuchElementException();
        when(taskSearcher.searchTaskByAssignedEmployee(idToSearch)).thenThrow(emptyListException);

        tempTasksToTest.takeTasks(idToSearch);
    }
}
