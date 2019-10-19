package individualProject_Classes;
/* Task class - 
 * --- this is one of the model class of ToDoList application
 * --- this class holds the task details. Task title,due date,status,project it belongs to.
 * --- task class implements Comparable interface and override of compareTo(Task,Task) is done.  
 */


import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Task implements  Comparable<Task> {

	private String title;
	private Date dueDate;
	private String status;
	private Project project;
	private String dueDateInStr;
	private String projectName;
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	public Task() {
		
	}

	public Task(String title, Date dueDate, Project project) {
		super();
		this.title = title;
		this.setDueDate(dueDate);
		this.project = project;
		this.projectName = project.getProjectName();
		this.status = Constants.START_STATUS;
	}

	public Task(String title, String dueDateStr, String status, String projectName) {
		super();
		this.title = title;
		this.dueDateInStr = dueDateStr;
		this.status = status;
		this.projectName = projectName;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getStatus() {
		return this.status;
	}

	public Project getProject() {
		return this.project;
	}

	public String getTaskTitle() {
		return this.title;
	}

	public Date getTaskDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
		this.dueDateInStr = sdf.format(dueDate);
	}

	public String getProjectName() {
		return projectName;
	}
	public String getDueDateInStr() {
		return dueDateInStr;
	}
	
	@Override
	public String toString() {
		return title + "," + dueDateInStr + "," + status + "," + projectName;
	}

	// Override of compareTo() method to sort tasks by due date
	public static Comparator<Task> DateComparator = new Comparator<Task>() {

		@Override
		public int compare(Task t1, Task t2) {
			return t1.getDueDateInStr().compareTo(t2.getDueDateInStr());
		}
	};

	
	// Override of compareTo() method to sort tasks by Project
	public static Comparator<Task> ProjectComparator = new Comparator<Task>() {
		@Override
		public int compare(Task t1, Task t2) {
			return t1.getProjectName().compareTo(t2.getProjectName());
		}
	};
	
	@Override
	public int compareTo(Task task) {
		// TODO Auto-generated method stub
		return 0;
	}

}
