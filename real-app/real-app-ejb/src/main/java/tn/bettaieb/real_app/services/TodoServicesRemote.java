package tn.bettaieb.real_app.services;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface TodoServicesRemote {
	void addTask(String taskDescription);

	List<String> findAllTasks();

	void reportTask(String task, StateOfTask state);
}
