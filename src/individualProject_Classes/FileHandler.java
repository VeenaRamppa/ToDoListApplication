package individualProject_Classes;
/* FileHandler Class
 * 
 * The purpose of this class is used to perform operations related to files.
 * Writing data in to a file, search in file ,reading data from file.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class FileHandler {
	private File file;
	public FileHandler() {
		this.file = new File(Constants.FILE_PATH);
	}	
/* writeToFile(Task task)  
* --- this method is used to write a task in to file,returns a string indicating writing into file is success or not 
*/
	public String writeToFile(Task task) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			if (file.exists()) {
				if (file.length() == 0) {
					bw.append("Task title" + Constants.COMMA + "Due Date" + Constants.COMMA + "Status" + Constants.COMMA
							+ "Project Name");
					bw.append("\n");
				}
			} else {
				file.createNewFile();
				bw.write("Task title" + Constants.COMMA + "Due Date" + Constants.COMMA + "Status" + Constants.COMMA
						+ "Project Name");
			}
			bw.write(task.toString());
			bw.append("\n");
		} catch (IOException e) {
			e.printStackTrace();
			return Constants.ERROR + ": Something went wrong while adding a task " + e.getMessage();
		}
		finally {
			if (bw != null) {
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return Constants.SUCCESS + ": Task added successfully";
	}
/* readFromFile()
 * --- operation of this function is to read task details from the file and it returns list of tasks. 
 */
	public List<Task> readFromFile() {
		BufferedReader br = null;
		List<Task> fileContent = new ArrayList<Task>();
		try {
			if (file.exists()) {
				String currentLine;
				br = new BufferedReader(new FileReader(file));
				br.readLine();
				while ((currentLine = br.readLine()) != null) {
					String[] arr = currentLine.split(",");
					Task readTask = new Task(arr[0], arr[1], arr[2], arr[3]);
					fileContent.add(readTask);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileContent;
	}
/* searchInFile
 * --- this function takes string parameter to be searched in file and if the string is found returns list of tasks related to it.
 */
	public List<Task> searchInFile(String str) {
		BufferedReader br = null;
		List<Task> fileContent = new ArrayList<Task>();
		try {
			if (file.exists()) {
				String currentLine;
				br = new BufferedReader(new FileReader(file));
				br.readLine();
				while ((currentLine = br.readLine()) != null) {
					if (currentLine.contains(str)) {
						String[] arr = currentLine.split(",");
						Task readTask = new Task(arr[0], arr[1], arr[2], arr[3]);
						fileContent.add(readTask);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		}

		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileContent;
	}
// writeToFile(list of tasks) - it is a overloaded function to write list of tasks in to a file.
// It is invoked mainly to update the file after editing the task.
	public String writeToFile(List<Task> listOfTasks) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file,false));
			bw.write("Task title" + Constants.COMMA + "Due Date" + Constants.COMMA + "Status" + Constants.COMMA
					+ "Project Name");
			bw.newLine();
			for (Task tmp : listOfTasks) {
				bw.write(tmp.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "oops something went wront while updating/removing a task " + e.getMessage();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return Constants.SUCCESS;
	}
}