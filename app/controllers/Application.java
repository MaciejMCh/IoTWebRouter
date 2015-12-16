package controllers;

import static java.util.concurrent.TimeUnit.SECONDS;
import models.Pinger;
import play.libs.Akka;
import play.libs.F.Callback0;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import scala.concurrent.duration.Duration;
import views.html.index;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.*;

public class Application extends Controller {
    
    public static WebSocket<String> device() {
        return new WebSocket<String>() {
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
                out.write("device connected");
            }
        };
    };
    
    public static WebSocket<String> admin() {
        return new WebSocket<String>() {
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
                out.write("admin connected");
            }
        };
    };
    
    public static WebSocket<String> ping() {
        return new WebSocket<String>() {
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
                out.write("ping connected");
            }
        };
    };

    public static Result pingJs() {
        return ok(views.js.ping.render());
    }

    public static Result index() {
        return ok(index.render());
    }
}
