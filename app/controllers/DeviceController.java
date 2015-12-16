package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import play.libs.F.Callback;
import com.google.gson.*;
import websocket.requestOperations.RequestOperation;
import websocket.requestOperations.RequestOperationsSerializer;

// import model.Device;

public class DeviceController extends Controller {
    
    public static WebSocket<String> device() {
        return new WebSocket<String>() {
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
                in.onMessage(new Callback<String>() {
                    public void invoke(String event) {
                        JsonObject json = new JsonParser().parse(event).getAsJsonObject();
                        RequestOperation operation;
                        operation = RequestOperationsSerializer.serializeOperation(json, new PlayWebSocketMedium(out));
                        operation.performOperation();
                    }
                }
            );}
        };
    };
    
    
    
}