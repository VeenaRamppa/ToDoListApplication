package individualProject_Classes;
/* Project Class
 * --- this is one of the model class of ToDoList application
 * --- this class holds project details and list of tasks related to the project
 */

import java.util.ArrayList;

public class Project {
	
	private ArrayList<Task> taskList;
	private String projectName;
	
	public Project(String projectName) {
		this.projectName = projectName;
		this.taskList = new ArrayList<Task>();
	}
	
	public void setTaskList(Task task) {
		taskList.add(task);
	}
	
	public ArrayList<Task> getTaskList(){
		return this.taskList;
	}

	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName; 
	}
	
}
