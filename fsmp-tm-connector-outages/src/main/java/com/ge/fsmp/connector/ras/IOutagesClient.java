package com.ge.fsmp.connector.ras;

/**
 * Created by Gagandeep on 9/19/16.
 */
public interface IOutagesClient {

    String getAssignedTasks(String sso);
    String getAssignedTasks(String projectId, String sso);
    String getProject(String projectId);
    String getCustomer(String projectId);
    String getEquipment(String projectId);
  
}
