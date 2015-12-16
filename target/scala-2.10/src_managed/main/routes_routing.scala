// @SOURCE:/Users/maciej/Desktop/IoTWebRouter-Play/conf/routes
// @HASH:b6f3316946ad65e13f8c10ed1ab7626d3080116a
// @DATE:Fri Dec 04 17:07:23 CET 2015


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_ping0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ping"))))
        

// @LINE:7
private[this] lazy val controllers_Application_pingJs1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/javascripts/ping.js"))))
        

// @LINE:10
private[this] lazy val controllers_Application_admin2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin"))))
        

// @LINE:13
private[this] lazy val controllers_Application_device3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("device"))))
        

// @LINE:16
private[this] lazy val controllers_Assets_at4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ping""","""controllers.Application.ping()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/javascripts/ping.js""","""controllers.Application.pingJs()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin""","""controllers.Application.admin()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """device""","""controllers.Application.device()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_ping0(params) => {
   call { 
        invokeHandler(controllers.Application.ping(), HandlerDef(this, "controllers.Application", "ping", Nil,"GET", """ Home page""", Routes.prefix + """ping"""))
   }
}
        

// @LINE:7
case controllers_Application_pingJs1(params) => {
   call { 
        invokeHandler(controllers.Application.pingJs(), HandlerDef(this, "controllers.Application", "pingJs", Nil,"GET", """""", Routes.prefix + """assets/javascripts/ping.js"""))
   }
}
        

// @LINE:10
case controllers_Application_admin2(params) => {
   call { 
        invokeHandler(controllers.Application.admin(), HandlerDef(this, "controllers.Application", "admin", Nil,"GET", """ Admin""", Routes.prefix + """admin"""))
   }
}
        

// @LINE:13
case controllers_Application_device3(params) => {
   call { 
        invokeHandler(controllers.Application.device(), HandlerDef(this, "controllers.Application", "device", Nil,"GET", """ Devices""", Routes.prefix + """device"""))
   }
}
        

// @LINE:16
case controllers_Assets_at4(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     