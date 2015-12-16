package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
// import model.Device;

public class DeviceController extends Controller {
    
    public static WebSocket<String> device() {
        return new WebSocket<String>() {
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
                out.write("device connected");
            }
        };
    };
    
    
    
}