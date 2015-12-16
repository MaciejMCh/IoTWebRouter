
package views.js

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.js._
/**/
object ping extends BaseScalaTemplate[play.api.templates.JavaScriptFormat.Appendable,Format[play.api.templates.JavaScriptFormat.Appendable]](play.api.templates.JavaScriptFormat) with play.api.templates.Template0[play.api.templates.JavaScriptFormat.Appendable] {

    /**/
    def apply():play.api.templates.JavaScriptFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.1*/("""$(function() """),format.raw/*1.14*/("""{"""),format.raw/*1.15*/("""
    var WS = window['MozWebSocket'] ? MozWebSocket : WebSocket
    var dateSocket = new WS(""""),_display_(Seq[Any](/*3.31*/routes/*3.37*/.Application.ping().webSocketURL(request))),format.raw/*3.78*/("""")

    var receiveEvent = function(event) """),format.raw/*5.40*/("""{"""),format.raw/*5.41*/("""
        $("#ping").html("Last ping: "+event.data);
    """),format.raw/*7.5*/("""}"""),format.raw/*7.6*/("""

    dateSocket.onmessage = receiveEvent
"""),format.raw/*10.1*/("""}"""),format.raw/*10.2*/(""")
"""))}
    }
    
    def render(): play.api.templates.JavaScriptFormat.Appendable = apply()
    
    def f:(() => play.api.templates.JavaScriptFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Dec 04 16:29:43 CET 2015
                    SOURCE: /Users/maciej/Desktop/IoTWebRouter-Play/app/views/ping.scala.js
                    HASH: 5c15a09ad052ec08d0b9cc4a68b82fedacef74d4
                    MATRIX: 880->0|920->13|948->14|1077->108|1091->114|1153->155|1223->198|1251->199|1333->255|1360->256|1429->298|1457->299
                    LINES: 29->1|29->1|29->1|31->3|31->3|31->3|33->5|33->5|35->7|35->7|38->10|38->10
                    -- GENERATED --
                */
            