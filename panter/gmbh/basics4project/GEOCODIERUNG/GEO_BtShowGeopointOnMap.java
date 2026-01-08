/**
 * 
 */
package panter.gmbh.basics4project.GEOCODIERUNG;

import java.math.BigDecimal;

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
 * @author martin
 *
 */
public abstract class GEO_BtShowGeopointOnMap extends E2_Button {
	
	public abstract BigDecimal getLatitude() throws myException;
	public abstract BigDecimal getLongitude() throws myException;
	
	/**
	 * @throws myException 
	 * 
	 */
	public GEO_BtShowGeopointOnMap() throws myException {
		super();
		
		this._image(E2_ResourceIcon.get_RI("geo_karte.png"), true);
		this._ttt(S.ms("Karte zu den GPS-Koordinaten der Adresse aufrufen ..."));
		
		this._aaa(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				BigDecimal bdLatitude = 	GEO_BtShowGeopointOnMap.this.getLatitude();
				BigDecimal bdLongitude = 	GEO_BtShowGeopointOnMap.this.getLongitude();
				
				if (bdLatitude==null || bdLongitude == null) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Koordinaten nicht korrekt oder nicht vorhanden")));
				} else {
				
					//die konstante identitifizieren
					String longitude = MyNumberFormater.formatDez(bdLongitude, 8, false, '.',null, false);
					String latitude = MyNumberFormater.formatDez(bdLatitude, 8, false, '.',null, false);
					
					String geopointUrl = ENUM_MANDANT_CONFIG.GEOPOINT_URL.getUncheckedValue();
					
					PdParserResult resWidth =  new PdParser().parse(geopointUrl, "width");
					PdParserResult resHeight=null;
					if (resWidth!=null) {
						resHeight =  new PdParser().parse(resWidth.parseTextWithoutBlock, "height");
						if (resHeight!=null) {
							MyLong lWidth = new MyLong(resWidth.value);
							MyLong lHeight = new MyLong(resHeight.value);
							
							if (lWidth.isOK() && lHeight.isOK()) {
								String urlCode = resHeight.parseTextWithoutBlock;
								
								urlCode=urlCode.replace("<breite>", latitude);
								urlCode=urlCode.replace("<laenge>", longitude);
								
								ApplicationInstance.getActive().enqueueCommand(
										new BrowserOpenWindowCommand(urlCode,"Karte zu Adresse ","width="+lWidth.get_cUF_LongString()+",height="+lHeight.get_cUF_LongString()));
							} else {
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Fehler in der Mandanten-Konstante <URL zum Aufruf von Karte einer Adresse> (1)")));
							}
						} else {
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Fehler in der Mandanten-Konstante <URL zum Aufruf von Karte einer Adresse> (2)")));
						}
					}  else {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Fehler in der Mandanten-Konstante <URL zum Aufruf von Karte einer Adresse> (3)")));
					}
				}
			}
		});
	}
	
}
