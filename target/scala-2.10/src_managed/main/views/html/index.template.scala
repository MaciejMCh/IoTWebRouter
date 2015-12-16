
package views.html

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
import views.html._
/**/
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply():play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](_display_(Seq[Any](/*1.2*/main("Welcome to Play")/*1.25*/ {_display_(Seq[Any](format.raw/*1.27*/("""

    <strong>Stats</strong><br>
    <div id="ping"></div>

   <script type="text/javascript" charset="utf-8" src=""""),_display_(Seq[Any](/*6.57*/routes/*6.63*/.Application.pingJs())),format.raw/*6.84*/(""""></script>
""")))})),format.raw/*7.2*/("""
"""))}
    }
    
    def render(): play.api.templates.HtmlFormat.Appendable = apply()
    
    def f:(() => play.api.templates.HtmlFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Dec 04 16:29:43 CET 2015
                    SOURCE: /Users/maciej/Desktop/IoTWebRouter-Play/app/views/index.scala.html
                    HASH: 0f7e5cb59a9e6cbd668d51e376cbe3c82a758a3b
                    MATRIX: 864->1|895->24|934->26|1085->142|1099->148|1141->169|1184->182
                    LINES: 29->1|29->1|29->1|34->6|34->6|34->6|35->7
                    -- GENERATED --
                */
            