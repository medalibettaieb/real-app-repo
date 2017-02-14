package tn.bettaieb.real_app.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class TodoServices
 */
@Stateful
public class TodoServices implements TodoServicesRemote, TodoServicesLocal {
	@EJB
	private ReportTasks reportTasks;
	private List<String> listOfTasks = new ArrayList<>();

	/**
	 * Default constructor.
	 */
	public TodoServices() {
	}

	@Override
	public void addTask(String taskDescription) {
		listOfTasks.add(taskDescription);
	}

	@Override
	public List<String> findAllTasks() {
		return listOfTasks;
	}

	@Override
	public void reportTask(String task, StateOfTask state) {
		reportTasks.reportTask(task, state);
	}

}
