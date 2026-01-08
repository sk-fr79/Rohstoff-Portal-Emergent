package panter.gmbh.BasicInterfaces.ServiceBean;

import java.math.BigDecimal;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.webcontainer.command.BrowserOpenWindowCommand;
import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceGeoCodeShowRoute;
import panter.gmbh.BasicTools.StringParser.PdParser;
import panter.gmbh.BasicTools.StringParser.PdParserResult;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.exceptions.myException;

public class PdServiceGeoCodeShowRouteBean implements PdServiceGeoCodeShowRoute {

	@Override
	public void get_route_preview(BigDecimal bdLatitudeStart, BigDecimal bdLongitudeStart, BigDecimal bdLatitudeZiel,
			BigDecimal bdLongitudeZiel) throws myException {


		if (O.isNoOneNull(bdLatitudeStart ,bdLongitudeStart ,bdLatitudeZiel ,bdLongitudeZiel )) {
			String sUrl = ENUM_MANDANT_CONFIG.GEOPOINT_ROUTING_URL.getUncheckedValue();
			PdParserResult resWidth =  new PdParser().parse(sUrl, "width");

			PdParserResult resHeight=null;
			if (resWidth!=null) {
				resHeight =  new PdParser().parse(resWidth.parseTextWithoutBlock, "height");

				if (resHeight!=null) {
					MyLong lWidth = new MyLong(resWidth.value);
					MyLong lHeight = new MyLong(resHeight.value);

					if (lWidth.isOK() && lHeight.isOK()) {
						String urlCode = resHeight.parseTextWithoutBlock;

						urlCode=urlCode.replace("<start_breite>", bdLatitudeStart.toPlainString());
						urlCode=urlCode.replace("<start_laenge>", bdLongitudeStart.toPlainString());
						urlCode=urlCode.replace("<ziel_breite>", bdLatitudeZiel.toPlainString());
						urlCode=urlCode.replace("<ziel_laenge>", bdLongitudeZiel.toPlainString());
						
						ApplicationInstance.getActive().enqueueCommand(
								new BrowserOpenWindowCommand(urlCode,"Karte zu Adresse ","width="+lWidth.get_cUF_LongString()+",height="+lHeight.get_cUF_LongString()));
					}
				}
			}
		}else {
			bibMSG.MV()._addAlarm("Die Geokoordinaten sind nicht vollständig!");
		}

	}
}
