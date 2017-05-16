/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoc.age.network;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public final String callerIp;
    public final int callerPort;
    public final String workerIp;
    public final int workerPort;
    public final String category;
    public final String id;
    public final String task;
    public final HashMap<String,String> taskExtraData;
    public boolean request = true;
    public String response;
    public HashMap<String,String> responseExtraData;

    public Task(String callerIp, int callerPort, String workerIp, int workerPort, String category, String id, String task, HashMap<String, String> taskExtraData) {
        this.callerIp = callerIp;
        this.callerPort = callerPort;
        this.workerIp = workerIp;
        this.workerPort = workerPort;
        this.category = category;
        this.id = id;
        this.task = task;
        this.taskExtraData = taskExtraData;
    }
    
}
