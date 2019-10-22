package individualProject_UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import individualProject_Classes.Project;
import individualProject_Classes.ToDoList;
import individualProject_Classes.User;

class ToDoListTest {
	ToDoList toDoList = new ToDoList();
/* testAddTask()
 * --- this method is used to check addTask() method of ToDoList class. 
 * --- Supply of valid task title,date and project then a task will be created and written in to file. A boolean value true will be return
 * --- Supply of invalid data like duplicate task title,invalid date,invalid project ,then boolean value false is returned
 */
	void testAddTask() {
		String taskTitle = "Gather requirements";
		Project project = new Project("ToDoList");
		Date taskDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			taskDate = sdf.parse("10/21/2019");
		} catch (ParseException e) {
			e.printStackTrace();
		}
// test case with valid arguments
		assertEquals(true, toDoList.addTask(taskTitle, taskDate, project));
		project = null;
		try {
// test case with invalid arguments			
			assertEquals(false, toDoList.addTask("", taskDate, project));
		} catch (NullPointerException e) {
		}
	}
/* testIsDuplicateTask()
 * --- This test is to check isDuplicateTask() method of ToDoList class.
 * --- isDuplicateTaskName() returns a boolean value true if task exists in file
 * --- isDuplicateTaskName() returns a boolean value false if tasks does not exists in file
 */
	void testIsDuplicateTask() {
		assertEquals(true, toDoList.isDuplicateTaskName("Gather requirements"));
		assertEquals(false, toDoList.isDuplicateTaskName("Project estimation"));
	}
/* testLoadUserTask()
 * --- This test is to check the loadUserTask() method of ToDoList class. 
 * --- loadUserTask() will return a information thru a string containing count on number tasks are completed ,pending.
 */
	@Test
	void testLoadUserTask() {
		User user1 = new User("Veena");
		User user2 = new User("Vihaan");
		String expected, actual;
// test case with positive value
		expected = "Veena you have 2 tasks todo and 0 tasks are done!";
		actual = toDoList.loadUserTask(user1);
		assertEquals(expected, actual);
// test case with negative value
		expected = "No tasks available";
		actual = toDoList.loadUserTask(user2);
		assertEquals(expected, actual);
	}
}
