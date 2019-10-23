package individualProject_Classes;
/* ToDoList class 
 * --- This class is the controller part of the ToDoList application.
 * --- This class methods are invoked by view part that is the User class
 */
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class ToDoList {
	private List<Task> listOfTasks;
	private List<Task> tmpTaskList;
	private List<Project> listOfProjects;
	private FileHandler fileHandler;
	private Scanner scan3 = new Scanner(System.in).useDelimiter("\n");
	private Scanner scan4 = new Scanner(System.in);
	private boolean quit = false;
	private String tmpTaskName;
	private String msg;
	private Task oldTask;
	private Task newTask;
	public ToDoList() {
		this.setListOfTasks(new ArrayList<Task>());
		this.setListOfProjects(new ArrayList<Project>());
		this.fileHandler = new FileHandler();
	}
	public void setListOfTasks(List<Task> taskList) {
		this.listOfTasks = taskList;
		this.tmpTaskList = taskList;
	}
	public void setListOfProjects(List<Project> listOfProjects) {

		this.listOfProjects = listOfProjects;
	}
	public List<Task> getListOfTasks() {
		return this.listOfTasks;
	}
	public List<Project> getListOfProjects() {
		return this.listOfProjects;
	}
	public void addProjects(Project project) {
		listOfProjects.add(project);
	}
	/*
	 * isDuplicateTaskName() --- this method is used to check the repetition of the
	 * task --- Input parameter to this method is the Task class object and returns
	 * a boolean value --- this method is invoked before adding/writing the task in
	 * to the file
	 */
	public boolean isDuplicateTaskName(String taskName) {
		List<Task> fileContent = fileHandler.readFromFile();
		boolean taskFlag = false;
		Iterator<Task> iterator = fileContent.iterator();
		while (iterator.hasNext()) {
			String tmpTaskName = ((Task) iterator.next()).getTaskTitle();
			if (tmpTaskName.equalsIgnoreCase(taskName)) {
				taskFlag = true;
			}
		}
		return taskFlag;
	}
	/*
	 * chkProjects() --- this method is used to check for the valid project name ---
	 * this method accepts a string value as input parameter and returns the object
	 * of Project class --- this method is invoked when a user inputs project name
	 * while creating/adding the task to the project
	 */
	public Project chkProject(String projectName) {
		Project tmpProject = null;
		Iterator<Project> iterator = listOfProjects.iterator();
		while (iterator.hasNext()) {
			tmpProject = (Project) iterator.next();
			if (tmpProject.getProjectName().equals(projectName)) {
				return tmpProject;
			}
		}
		return null;
	}
	/*
	 * loadUserTask() --- this method is used to find the number of complete and
	 * pending tasks for a user --- this method is invoked to display the total
	 * number of tasks are completed / pending --- this method is invoked while
	 * displaying available options in Main menu
	 */
	public String loadUserTask(User user) {
		List<Task> fileContent = fileHandler.readFromFile();
		String retString = null;
		int completedTask = 0;
		int incompleteTask = 0;
		if (fileContent.size() != 0) {
			for (int i = 0; i <= fileContent.size() - 1; i++) {
				String[] taskDetails = fileContent.get(i).toString().split(",");
				if (taskDetails[2].equals(Constants.END_STATUS)) {
					completedTask++;
				} else {
					incompleteTask++;
				}
			}
			retString = user.getUserName() + " you have " + incompleteTask + " tasks todo and " + completedTask
					+ " tasks are done!";
		} else {
			retString = "No tasks available";
		}
		return retString;
	}
	/*
	 * addTask() --- This method is used to add a task with the supplied details and
	 * write the same in to a file --- this method accepts 3 string values task
	 * title,taskDate and project name --- this method returns a boolean value
	 * indicating adding of a task is success or not
	 * 
	 */
	public boolean addTask(String taskName, Date taskDate, Project project) {
		boolean chkFlag = false;
		fileHandler = new FileHandler();
		Task task = null;
		try {
			task = new Task(taskName, taskDate, project);
		} catch (NullPointerException e) {
			// e.printStackTrace();
		}
		// write the contents of listOfTasks to file here
		if (task != null) {
			String fileWriteStr = fileHandler.writeToFile(task);
			if ((fileWriteStr.split(":")[0].equals(Constants.SUCCESS))) {
				chkFlag = true;
			}
		}
		return chkFlag;
	}
	/*
	 * showTaskListByDate() --- this method is used to show the list of tasks sorted
	 * by date --- this method uses the sort() method of Collections class to sort
	 * the tasks by date
	 */
	public String showTaskListByDate() {
		List<Task> fileContentDate = fileHandler.readFromFile();
		String retStr = "";
		if (fileContentDate.size() != 0) {
			retStr = Constants.SUCCESS;
			Collections.sort(fileContentDate, Task.DateComparator);
			System.out.println("Tasks list sorted by Date");
			printTask(fileContentDate);
		} else {
			retStr = Constants.ERROR;
			System.out.println("No tasks available ");
		}
		return retStr;
	}
	/*
	 * showTaskListByProject() --- this method is used to show the list of tasks
	 * sorted by project name --- this method uses the Collections class sort()
	 * method to sort the tasks by project
	 * 
	 */
	public String showTaskListByProject() {
		List<Task> fileContentProject = fileHandler.readFromFile();
		String retStr = "";
		if (fileContentProject.size() != 0) {
			retStr = Constants.SUCCESS;
			Collections.sort(fileContentProject, Task.ProjectComparator);
			System.out.println("Tasks list sorted by Project");
			printTask(fileContentProject);
		} else {
			System.out.println("No tasks available ");
			retStr = Constants.ERROR;
		}
		return retStr;
	}
	/*
	 * printTask() --- this method is used by showTaskListByDate() &
	 * showTaskListByProject() operations to show the list of tasks
	 */
	private void printTask(List<Task> sortedTaskDets) {
		System.out.println("---------------------------------------------------------------------------------------\n");
		System.out.println("Task title\t\tTaskDueDate\t    Status\t\tProject Name \n");
		System.out.println("---------------------------------------------------------------------------------------- \n");
		for (int i = 0; i <= sortedTaskDets.size() - 1; i++) {
			String[] taskDetails = sortedTaskDets.get(i).toString().split(",");
			System.out.print(String.format("%-25s", taskDetails[0]));
			System.out.print(String.format("%-20s", taskDetails[1]));
			System.out.print(String.format("%-20s", taskDetails[2]));
			System.out.print(String.format("%-10s", taskDetails[3]));
			System.out.println();
		}
	}
	/*
	 * editTask() --- this method provides the menu details to edit a task --- this
	 * operation accepts project name in which the task to modified by title/due
	 * date/status and write back the details in to file --- this method provides an
	 * option to remove a task from the project --- this method provides an option
	 * to go back to the main menu
	 */
	public void editTask() {
		boolean validTaskName = false;
		System.out.println("In which project task to be modified ? \n\nEnter the project name : ");
		String editProjectName = scan3.next();
		Project project = chkProject(editProjectName);
		while (project == null) {
			System.out.println("Enter valid project name ");
			editProjectName = scan3.next();
			project = chkProject(editProjectName);
		}
		tmpTaskList = fileHandler.searchInFile(editProjectName);
		System.out.println("List of tasks for the project " + editProjectName + " are as below:");
		System.out.println("----------------------------------------------------------------");
		for (Task t : tmpTaskList) {
			System.out.println(t.toString().replace(',', '\t'));
		}
		System.out.println();
		System.out.println("Enter the title of the task to be modified");
		tmpTaskName = scan3.next();
		validTaskName = isValidTaskTitle(tmpTaskList, tmpTaskName);
		while (!validTaskName) {
			System.out.println("Enter the task title as it is :");
			tmpTaskName = scan3.next();
			validTaskName = isValidTaskTitle(tmpTaskList, tmpTaskName);
		}
		while (!quit) {
			editMenu();
			System.out.println("\nEnter your option: ");
			int choice;
			while (!scan4.hasNextInt()) {
				System.out.println("Please enter number only");
				scan4.next();

			}
			choice = scan4.nextInt();
			oldTask = new Task();
			newTask = new Task();
			for (Task tmpTask : tmpTaskList) {
				if (tmpTask.getTaskTitle().equals(tmpTaskName)) {
					oldTask = tmpTask;
					newTask = tmpTask;
				}
			}
			List<Task> listOfOtherProject = getOtherProjectTask(editProjectName);
			switch (choice) {
			case 1:
				System.out.println("Ente new title:");
				String title = scan3.next();
				newTask.setTitle(title);
				msg = update(oldTask, newTask, tmpTaskList, listOfOtherProject);
				System.out.println(msg);
				break;
			case 2:
				System.out.println("Enter new date(MM/dd/yyyy):");
				String date = scan3.next();
				Date newDate = null;
				try {
					newDate = DateValidator.isThisDateValid(date);
					while (newDate == null) {
						System.out.println("Enter the correct date in (MM/dd/yyyy) format :");
						newDate = DateValidator.isThisDateValid(scan3.next());
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				newTask.setDueDate(newDate);
				msg = update(oldTask, newTask, tmpTaskList, listOfOtherProject);
				System.out.println(msg);
				break;
			case 3:
				System.out.println("Please enter the new status(Progress or Done):");
				String status = scan3.next();
				if (status.equalsIgnoreCase("Progress")) {
					newTask.setStatus(Constants.PROGRESS_STATUS);
				} else if (status.equalsIgnoreCase("Done")) {
					newTask.setStatus(Constants.END_STATUS);
				}
				msg = update(oldTask, newTask, tmpTaskList, listOfOtherProject);
				System.out.println(msg);
				break;
			case 4:
				msg = removeTask(tmpTaskName, tmpTaskList, listOfOtherProject);
				System.out.println(msg);
				break;
			case 5:
				quit = true;
				break;
			}
		}
	}
/* 
 * editMenu() - contains the menu holding list of options to edit a task 
 */
	public void editMenu() {
		System.out.println("\nEdit Menu: \n" + "----------------\n" + "Press 1 to change task title \n"
				+ "Press 2 to change due date \n" + "Press 3 to change status \n" + "Press 4 to remove task \n"
				+ "Press 5 to go to Main menu \n");
	}
// removeTask() - this operation is used to remove a task from the project and update the file accordingly
	public String removeTask(String str, List<Task> tmpTaskList, List<Task> listOfOtherProject) {
		Iterator<Task> iterator = tmpTaskList.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getTaskTitle().equals(str)) {
				iterator.remove();
			}
		}
		for (Task tmp : listOfOtherProject) {
			tmpTaskList.add(tmp);
		}
		String msg = fileHandler.writeToFile(tmpTaskList);
		return "Remove of task is " + msg;
	}

/*
 * update() - this method is invoked whenever user modifies the title,status,duedate to upate the changes in to file
 */
	public String update(Task oldTask, Task newTask, List<Task> tmpTaskList, List<Task> listOfOtherProject) {
		int index = -1;
		for (Task t : tmpTaskList) {
			if (t.equals(oldTask))
				index = tmpTaskList.indexOf(oldTask);
		}
		tmpTaskList.set(index, newTask);
		for (Task tmp : listOfOtherProject) {
			tmpTaskList.add(tmp);
		}
		String retStr = fileHandler.writeToFile(tmpTaskList);
		return "Updating of the file is " + retStr;
	}
/*
 * getOtherProjectTask() - this method is used to get the list other project task details while updating the file after editing a task
 */
	private List<Task> getOtherProjectTask(String projectName) {
		List<Task> taskList = fileHandler.readFromFile();
		List<Task> listOfOtherProject = new ArrayList<Task>();
		for (int i = 0; i < taskList.size(); i++) {
			Task tmpTask = taskList.get(i);
			if (!(tmpTask.getProjectName().equalsIgnoreCase(projectName))) {
				listOfOtherProject.add(tmpTask);
			}
		}
		return listOfOtherProject;
	}
/*
 * isValidTaskTitle() - this method is used while editing a task
 * --- when user enters the title of the task to be edited this method validates the user input against the value in the file
 */
	public boolean isValidTaskTitle(List<Task> tmpTaskList, String tmpTaskName) {
		boolean taskFlag = false;
		if (tmpTaskList.size() >= 0) {
			Iterator<Task> iterator = tmpTaskList.iterator();
			for (; iterator.hasNext();) {
				if (iterator.next().getTaskTitle().equalsIgnoreCase(tmpTaskName)) {
					taskFlag = true;
				}
			}
		}
		return taskFlag;
	}
}