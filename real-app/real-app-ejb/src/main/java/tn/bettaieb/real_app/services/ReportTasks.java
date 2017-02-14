package tn.bettaieb.real_app.services;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class ReportTasks
 */
@Singleton
@LocalBean
public class ReportTasks {
	private Map<String, StateOfTask> mapOfAllTasks = new HashMap<>();

	/**
	 * Default constructor.
	 */
	public ReportTasks() {
	}

	public void reportTask(String task, StateOfTask state) {
		mapOfAllTasks.put(task, state);
	}
}
