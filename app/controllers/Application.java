package controllers;

import static java.util.concurrent.TimeUnit.SECONDS;
import play.libs.Akka;
import play.libs.F.Callback0;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.*;
import play.libs.F.Callback;
import requestOperations.RequestOperation;
import requestOperations.DeviceRequestOperationsSerializer;
import requestOperations.AdminRequestOperationsSerializer;
import requestOperations.MobileRequestOperationsSerializer;
import model.Interactor;
import notificationCenter.NotificationCenter;

public class Application extends Controller {
    
    public static WebSocket<String> device() {
        return new WebSocket<String>() {
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
                in.onMessage(new Callback<String>() {
                    public void invoke(String event) {
                        JsonObject json = new JsonParser().parse(event).getAsJsonObject();
                        RequestOperation operation;
                        operation = new DeviceRequestOperationsSerializer().serializeOperation(json, new PlayWebSocketMedium(out));
                        operation.performOperation();
                    }
                });
                in.onClose(new Callback0() {
                    public void invoke() {
                        Interactor.getInstance().mediumClosed(new PlayWebSocketMedium(out));
                    }
                });
            }
        };
    };
    
    public static WebSocket<String> admin() {
        return new WebSocket<String>() {
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
                NotificationCenter.getInstance().addMedium(new PlayWebSocketMedium(out));
                in.onMessage(new Callback<String>() {
                    public void invoke(String event) {
                        JsonObject json = new JsonParser().parse(event).getAsJsonObject();
                        RequestOperation operation;
                        operation = new AdminRequestOperationsSerializer().serializeOperation(json, new PlayWebSocketMedium(out));
                        operation.performOperation();
                    }
                }
            );}
        };
    };
    
    public static WebSocket<String> mobile() {
        return new WebSocket<String>() {
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
                NotificationCenter.getInstance().addMedium(new PlayWebSocketMedium(out));
                in.onMessage(new Callback<String>() {
                    public void invoke(String event) {
                        JsonObject json = new JsonParser().parse(event).getAsJsonObject();
                        RequestOperation operation;
                        operation = new MobileRequestOperationsSerializer().serializeOperation(json, new PlayWebSocketMedium(out));
                        operation.performOperation();
                    }
                }
            );}
        };
    };
}
