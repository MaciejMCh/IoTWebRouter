/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Session;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maciej
 */
public class LocalFileSessionStorage extends SessionStorage {

    @Override
    protected void saveSessionPayload(String payload) {
        try {
            ArrayList<String> list = new ArrayList<>();
            list.add(payload);
            Files.write(Paths.get("session.txt"), list);
        } catch (IOException ex) {
            
        }
    }

    @Override
    protected String loadSessionPayload() {
        try {
            String payload = "";
            for (String line : Files.readAllLines(Paths.get("session.txt"))) {
                payload += line;
            }
            return payload;
        } catch (IOException ex) {
            return "";
        }
    }
    
}
