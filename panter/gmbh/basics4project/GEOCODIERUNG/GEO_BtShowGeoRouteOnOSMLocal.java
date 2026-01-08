/**
 * 
 */
package panter.gmbh.basics4project.GEOCODIERUNG;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.webcontainer.command.BrowserOpenWindowCommand;
import panter.gmbh.BasicTools.StringParser.PdParser;
import panter.gmbh.BasicTools.StringParser.PdParserResult;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author Manfred
 *
 */
public abstract class GEO_BtShowGeoRouteOnOSMLocal extends E2_Button {
	
	
	/**
	 * Methode gibt die Koordinaten der Punkte im Vektor zurück
	 * @author manfred
	 * @date 17.10.2018
	 *
	 * @return
	 * @throws myException 
	 */
	public abstract GEO_Factory_URLForLocations getURLFactory() throws myException;
	
	
	
	/**
	 * @throws myException 
	 * 
	 */
	public GEO_BtShowGeoRouteOnOSMLocal() throws myException {
		super();
		
		this._image(E2_ResourceIcon.get_RI("routing_show.png"), true);
		this._ttt(S.ms("Karte mit dem Routing aufrufen ..."));
		
		this._aaa(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				// binden der Factory
				GEO_Factory_URLForLocations urlFactory = getURLFactory();
				
				if (!urlFactory.bHasPoints()){
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Es wurden keine Koordinaten angegeben.")));
					return;
				}
				
				
				// lesen der URL für das Routing
				String sURL = urlFactory.getURL4Route();
				
				////
				String sPageUrl =  ENUM_MANDANT_CONFIG.URL_GEO_SHOW_ROUTE.getUncheckedValue();
				int _width = 1000;
				int _height= 800;
				int _showpopups = 0;
				
				// width-Block rausschneinden...
				PdParserResult resWidth =  new PdParser().parse(sPageUrl, "width");
				if (resWidth != null && S.isFull(resWidth.value)) {
					_width = new MyLong(resWidth.value).get_iValue();
				}
				// height-Block rausschneiden...
				PdParserResult resHeight= new PdParser().parse(sPageUrl, "height");
				if (resHeight != null && S.isFull(resHeight.value)) {
					_height = new MyLong(resHeight.value).get_iValue();
				}
				// height-Block rausschneiden...
				PdParserResult showpopup= new PdParser().parse(sPageUrl, "showpopups");
				if (showpopup != null && S.isFull(showpopup.value)) {
					_showpopups = new MyLong(showpopup.value).get_iValue();
				}

				if (_showpopups > 0){
					sURL += String.format("&showpopups=%d",_showpopups);
				}
				ApplicationInstance.getActive().enqueueCommand(
						new BrowserOpenWindowCommand(sURL,"Kartendarstellung", String.format("width=%d,height=%d",_width,_height)) );
				
			}
		});
	}
	
}
