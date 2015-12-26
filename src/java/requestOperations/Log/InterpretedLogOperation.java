/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import com.google.gson.*;
import java.util.HashMap;
import model.Medium;
import model.SerializableModel;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public abstract class InterpretedLogOperation extends RequestOperation implements SerializableModel {

    protected String query;
    protected ArrayList<String> options;
    protected ArrayList<String> arguments;

    public abstract ArrayList<String> argumentPropertyNames();
    public abstract HashMap<String, String> optionByPropertyKey();
    
    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {{
            put("query", "query");
        }};
    }
}
