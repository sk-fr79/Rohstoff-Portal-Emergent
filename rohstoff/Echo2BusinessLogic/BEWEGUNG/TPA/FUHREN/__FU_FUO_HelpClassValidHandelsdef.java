package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.math.BigDecimal;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_TAX_EXT;

public class __FU_FUO_HelpClassValidHandelsdef {
	
	/**
	 * 
	 * @param rHD
	 * @param rTAX
	 * @param oMAP
	 * @param fieldSTEUERSATZ
	 * @param fieldEU_STEUER_VERMERK
	 * @param field_ID_TAX
	 * @param field_INTRASTAT
	 * @param field_TRANSIT
	 * @param field_Leistungsdatum
	 * @param PlatzhalterMeldung
	 * @return
	 * @throws myException
	 */
	public static MyE2_MessageVector pruefe_HandelsDefGegenMaske( RECORD_HANDELSDEF  	rHD,
																	RECORD_TAX   	  	rTAX,
																	E2_ComponentMAP   	oMAP,
																	boolean            bEK_SEITE,
																	String     			fieldID_ADRESSE,
																	String    			fieldSTEUERSATZ,
																	String    			fieldEU_STEUER_VERMERK,
																	String 				field_ID_TAX,
																	String 				field_INTRASTAT,
																	String  			field_TRANSIT,
																	String    			field_Leistungsdatum,
																	String     			PlatzhalterMeldung) throws myException {
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		//zuerst checken, ob es eine lagerseite ist, dann wird nichts validiert
		if (oMAP.get_LActualDBValue(fieldID_ADRESSE, -1l, -1l).longValue()==bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(-2l).longValue()) {
			return oMV;
		}
		
		RECORD_TAX_EXT recTax2 = new RECORD_TAX_EXT(rTAX);
		MyDate leistungsDatum = new MyDate(oMAP.get_cActualDBValueFormated(field_Leistungsdatum));
		
		//jetzt die vergleiche starten (EK)
		if (recTax2.getBDSteuerSatzKorrigiert(leistungsDatum).compareTo(
				oMAP.get_bdActualDBValue(fieldSTEUERSATZ, BigDecimal.ZERO, BigDecimal.ZERO))!=0) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(
					new MyE2_String(bibALL.ReplaceTeilString("USt./Steuertext: Der Steuersatz der Maske #TEXT# ist nicht der Steuersatz der USt./Steuertext-Definition!","#TEXT#",S.NN(PlatzhalterMeldung)))));
		}
		if (!rTAX.get_STEUERVERMERK_cUF_NN("").equals(oMAP.get_cActualDBValueFormated(fieldEU_STEUER_VERMERK))) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(
					new MyE2_String(bibALL.ReplaceTeilString("USt./Steuertext: Der Steuervermerk der Maske #TEXT# ist nicht der Steuervermerk der USt./Steuertext-Definition!","#TEXT#",S.NN(PlatzhalterMeldung)))));
		}
		if (rTAX.get_ID_TAX_lValue(-2l).longValue()!=oMAP.get_LActualDBValue(field_ID_TAX, -1l, -1l).longValue()) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(
					new MyE2_String(bibALL.ReplaceTeilString("USt./Steuertext: Der eingestellte Steuerdefinition der Maske #TEXT# ist nicht die Steuerdefinition der USt./Steuertext-Definition!","#TEXT#",S.NN(PlatzhalterMeldung)))));
		}
		if (bEK_SEITE) {
			if (!rHD.get_INTRASTAT_MELD_IN_cUF_NN("").equals(oMAP.get_cActualDBValueFormated(field_INTRASTAT))) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(
						new MyE2_String(bibALL.ReplaceTeilString("USt./Steuertext: Die Einstellung Intrastat #TEXT# der Maske ist nicht die der USt./Steuertext-Definition!","#TEXT#",S.NN(PlatzhalterMeldung)))));
			}
			if (!rHD.get_TRANSIT_EK_cF_NN("").equals(oMAP.get_cActualDBValueFormated(field_TRANSIT))) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(
						new MyE2_String(bibALL.ReplaceTeilString("USt./Steuertext: Die Einstellung Transit #TEXT# der Maske ist nicht die der USt./Steuertext-Definition!","#TEXT#",S.NN(PlatzhalterMeldung)))));
			}
		} else {
			if (!rHD.get_INTRASTAT_MELD_OUT_cUF_NN("").equals(oMAP.get_cActualDBValueFormated(field_INTRASTAT))) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(
						new MyE2_String(bibALL.ReplaceTeilString("USt./Steuertext: Die Einstellung Intrastat #TEXT# der Maske ist nicht die der USt./Steuertext-Definition!","#TEXT#",S.NN(PlatzhalterMeldung)))));
			}
			if (!rHD.get_TRANSIT_VK_cF_NN("").equals(oMAP.get_cActualDBValueFormated(field_TRANSIT))) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(
						new MyE2_String(bibALL.ReplaceTeilString("USt./Steuertext: Die Einstellung Transit #TEXT# der Maske ist nicht die der USt./Steuertext-Definition!","#TEXT#",S.NN(PlatzhalterMeldung)))));
			}
		}

		return oMV;
		
	}
																	
						
	
}
