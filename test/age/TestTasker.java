/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package age;

import daoc.age.network.Tasker;

/**
 *
 * @author diego
 */
public class TestTasker {
    
    public static void main(String[] args) {
        Tasker tasker = new Tasker();
        tasker.initECS();
        tasker.openTaskReception();
    }
    
}
