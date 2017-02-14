package tn.bettaieb.real_app.services;

import java.util.List;

import javax.ejb.Local;

@Local
public interface TodoServicesLocal {
	void addTask(String taskDescription);

	List<String> findAllTasks();
}
