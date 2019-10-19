package individualProject_UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import individualProject_Classes.Project;
import individualProject_Classes.Task;
import individualProject_Classes.ToDoList;
import individualProject_Classes.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserClassTest {
	ToDoList toDoList = new ToDoList();
	@Test
	void testAddTask() {
		
		User testUser = new User("Vihaan");
		String taskTitle = "Gather requirements";
		Project project = new Project("ToDoList");
		Date taskDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			taskDate = sdf.parse("10/21/2019");
		}catch(ParseException e) {
			e.printStackTrace();
		}
		assertEquals(true,toDoList.addTask(taskTitle, taskDate, project));
	}
	
	

}
