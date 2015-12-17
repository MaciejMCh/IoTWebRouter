/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversation;

import java.util.ArrayList;
import model.Medium;

/**
 *
 * @author maciej
 */
public class Notifier {
    private static Notifier instance = null;
    public static Notifier getInstance() {
        if(instance == null) {
            instance = new Notifier();
        }
        return instance;
    }
    
    protected ArrayList<Medium> listeningMediums = new ArrayList();
}
