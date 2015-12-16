// @SOURCE:/Users/maciej/Desktop/IoTWebRouter-Play/conf/routes
// @HASH:b6f3316946ad65e13f8c10ed1ab7626d3080116a
// @DATE:Fri Dec 04 17:07:23 CET 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:16
// @LINE:13
// @LINE:10
// @LINE:7
// @LINE:6
package controllers {

// @LINE:16
class ReverseAssets {
    

// @LINE:16
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:13
// @LINE:10
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:6
def ping(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ping")
}
                                                

// @LINE:7
def pingJs(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/javascripts/ping.js")
}
                                                

// @LINE:13
def device(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "device")
}
                                                

// @LINE:10
def admin(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin")
}
                                                
    
}
                          
}
                  


// @LINE:16
// @LINE:13
// @LINE:10
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:16
class ReverseAssets {
    

// @LINE:16
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:13
// @LINE:10
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:6
def ping : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.ping",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ping"})
      }
   """
)
                        

// @LINE:7
def pingJs : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.pingJs",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/javascripts/ping.js"})
      }
   """
)
                        

// @LINE:13
def device : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.device",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "device"})
      }
   """
)
                        

// @LINE:10
def admin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.admin",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:16
// @LINE:13
// @LINE:10
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:16
class ReverseAssets {
    

// @LINE:16
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:13
// @LINE:10
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:6
def ping(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ping(), HandlerDef(this, "controllers.Application", "ping", Seq(), "GET", """ Home page""", _prefix + """ping""")
)
                      

// @LINE:7
def pingJs(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.pingJs(), HandlerDef(this, "controllers.Application", "pingJs", Seq(), "GET", """""", _prefix + """assets/javascripts/ping.js""")
)
                      

// @LINE:13
def device(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.device(), HandlerDef(this, "controllers.Application", "device", Seq(), "GET", """ Devices""", _prefix + """device""")
)
                      

// @LINE:10
def admin(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.admin(), HandlerDef(this, "controllers.Application", "admin", Seq(), "GET", """ Admin""", _prefix + """admin""")
)
                      
    
}
                          
}
        
    