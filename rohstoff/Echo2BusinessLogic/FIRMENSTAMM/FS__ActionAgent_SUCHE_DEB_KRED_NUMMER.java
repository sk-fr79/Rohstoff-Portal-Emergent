package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.math.BigDecimal;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD_LIST;
import panter.gmbh.indep.exceptions.myException;

public class FS__ActionAgent_SUCHE_DEB_KRED_NUMMER extends XX_ActionAgent 
{
	private FS_MASK_ComponentMAP  oMap = null;

	private String cNEXT_DEBITOR = "";
	private String cNEXT_KREDITOR = "";
	
	/*
	 * beim automatischen ausfuehren mit der Auswahl des Landes wird in der SQLResultMap geschaut, ob die Felder schon belegt sind,
	 * beim manuellen Anwaehlen ueber den Zauberstab direkt aus der maske
	 */
	private boolean bInhaltAusResultMap = true;
	
	public FS__ActionAgent_SUCHE_DEB_KRED_NUMMER(FS_MASK_ComponentMAP oMap, boolean bInhaltAUSResultMap) 
	{
		super();
		this.oMap = oMap;
		this.bInhaltAusResultMap = bInhaltAUSResultMap;
	}



	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException 
	{
		this.cNEXT_DEBITOR="";
		this.cNEXT_KREDITOR="";
		
		int idLand = this.oMap.get_bdActualDBValue("ID_LAND", BigDecimal.ZERO,  BigDecimal.ZERO).intValue();
		
		if (idLand==0)
		{
			//2013-12-05: meldung hier nicht mehr noetig, da an anderer stelle das bemaengelt wird
			//bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Bitte ein Land festlegen !!")));
			
			return;
		}

		int idLandMandant = bibALL.get_RECORD_MANDANT().get_ID_LAND_lValue(new Long(0)).intValue();
		
		if (idLandMandant==0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst das Land des Mandanten im Mandanten-Stamm festlegen !!")));
			return;
		}
		
		long iStartWertDeb 	= 0;
		long iEndWertDeb 	= 0;
		long iStartWertKred = 0;
		long iEndWertKred 	= 0;

		
		if (idLand==idLandMandant)
		{
			iStartWertDeb = 	bibALL.get_RECORD_MANDANT().get_NUMKREIS_DEBITOR_INLAND_VON_lValue(new Long(0));
			iEndWertDeb = 		bibALL.get_RECORD_MANDANT().get_NUMKREIS_DEBITOR_INLAND_BIS_lValue(new Long(0));
			iStartWertKred = 	bibALL.get_RECORD_MANDANT().get_NUMKREIS_KREDITOR_INLAND_VON_lValue(new Long(0));
			iEndWertKred = 		bibALL.get_RECORD_MANDANT().get_NUMKREIS_KREDITOR_INLAND_BIS_lValue(new Long(0));
		}
		else 
		{
			RECORD_LAND recLand = new RECORD_LAND(idLand);
			if (recLand.is_INTRASTAT_JN_YES())
			{
				iStartWertDeb = 	bibALL.get_RECORD_MANDANT().get_NUMKREIS_DEBITOR_EU_VON_lValue(new Long(0));
				iEndWertDeb = 		bibALL.get_RECORD_MANDANT().get_NUMKREIS_DEBITOR_EU_BIS_lValue(new Long(0));
				iStartWertKred = 	bibALL.get_RECORD_MANDANT().get_NUMKREIS_KREDITOR_EU_VON_lValue(new Long(0));
				iEndWertKred = 		bibALL.get_RECORD_MANDANT().get_NUMKREIS_KREDITOR_EU_BIS_lValue(new Long(0));
			}
			else
			{
				iStartWertDeb = 	bibALL.get_RECORD_MANDANT().get_NUMKREIS_DEBITOR_NICHT_EU_VON_lValue(new Long(0));
				iEndWertDeb = 		bibALL.get_RECORD_MANDANT().get_NUMKREIS_DEBITOR_NICHT_EU_BIS_lValue(new Long(0));
				iStartWertKred = 	bibALL.get_RECORD_MANDANT().get_NUMKREIS_KREDITOR_NICHT_EU_VON_lValue(new Long(0));
				iEndWertKred = 		bibALL.get_RECORD_MANDANT().get_NUMKREIS_KREDITOR_NICHT_EU_BIS_lValue(new Long(0));
			}
		}
		
		
		
		this.cNEXT_DEBITOR = this.ermittleWert(iStartWertDeb, iEndWertDeb, "DEBITOR");
		this.cNEXT_KREDITOR = this.ermittleWert(iStartWertKred, iEndWertKred, "KREDITOR");
		
		
		String cValueDebInResultMAP = "";
		String cValueKredInResultMAP = "";
		
		if (this.oMap.get_E2_ComponentMAP_Firmeninfo().get_oInternalSQLResultMAP()!=null)    //bei neueingabe immer eintragen
		{
			if (this.bInhaltAusResultMap)
			{
				cValueDebInResultMAP=this.oMap.get_E2_ComponentMAP_Firmeninfo().get_oInternalSQLResultMAP().get_UnFormatedValue("DEBITOR_NUMMER");
				cValueKredInResultMAP=this.oMap.get_E2_ComponentMAP_Firmeninfo().get_oInternalSQLResultMAP().get_UnFormatedValue("KREDITOR_NUMMER");
			}
			else 
			{
				cValueDebInResultMAP=this.oMap.get_E2_ComponentMAP_Firmeninfo().get_cActualDBValueFormated("DEBITOR_NUMMER");
				cValueKredInResultMAP=this.oMap.get_E2_ComponentMAP_Firmeninfo().get_cActualDBValueFormated("KREDITOR_NUMMER");
			}
		}		
		
		boolean bGeaendert = false;
		
		if (S.isFull(this.cNEXT_DEBITOR) && S.isEmpty(cValueDebInResultMAP))
		{
			this.oMap.get_E2_ComponentMAP_Firmeninfo().get__DBComp("DEBITOR_NUMMER").set_cActualMaskValue(this.cNEXT_DEBITOR);
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Debitor-Nummer <", true, this.cNEXT_DEBITOR,false,"> wurde eingetragen !", true)));
			bGeaendert=true;
		}
		
		if (S.isFull(this.cNEXT_KREDITOR) && S.isEmpty(cValueKredInResultMAP))
		{
			this.oMap.get_E2_ComponentMAP_Firmeninfo().get__DBComp("KREDITOR_NUMMER").set_cActualMaskValue(this.cNEXT_KREDITOR);
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Kreditor-Nummer <", true, this.cNEXT_KREDITOR,false,"> wurde eingetragen !", true)));
			bGeaendert=true;
		}
		if (!bGeaendert)
		{
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Keine Änderung an Debitor/Kreditor - Nummer", true)));
		}
		
	}
	
	
	
	private String ermittleWert(long lStartWert, long lEndWert, String cTyp) throws myException
	{
		String cRueck = "";
		String cFeldName = cTyp+"_NUMMER";
		
		String cAusschlussTabelle = "JT_DEB_NUM_AUSSCHLUSS";
		if (cTyp.equals("KREDITOR"))
		{
			cAusschlussTabelle = "JT_KRED_NUM_AUSSCHLUSS";
		}

		String cQueryDeb = 
			"SELECT NVL(MIN(OHNE_LUECKE),-1) AS WERT FROM "+ 
			"( "+
			" SELECT ROWNUM+"+lStartWert+" -1 AS OHNE_LUECKE,NUM AS LUECKE FROM ( "+
				" SELECT TO_NUMBER(REGEXP_SUBSTR("+cFeldName+",'^[0-9]*$')) AS NUM "+  
					" FROM JT_FIRMENINFO "+
					" WHERE "+cFeldName+" IS NOT NULL "+
					" AND     TO_NUMBER(REGEXP_SUBSTR("+cFeldName+",'^[0-9]*$'))>0 "+ 
					" AND     TO_NUMBER(REGEXP_SUBSTR("+cFeldName+",'^[0-9]*$'))>="+lStartWert+	" "+
					" AND     TO_NUMBER(REGEXP_SUBSTR("+cFeldName+",'^[0-9]*$'))<="+lEndWert+	" "+
					" AND     REGEXP_LIKE ("+cFeldName+", '^[0-9]*$') "+
				" UNION " +
   			    " SELECT NUMMER FROM "+bibE2.cTO()+"."+cAusschlussTabelle+"  "+
   			    "     WHERE NUMMER>0 "+
   			    "     AND   NUMMER>="+lStartWert+	" "+
   			    "     AND   NUMMER<="+lEndWert+	" "+
			 	" ORDER BY 1 ) "+
				" ) "+
				" WHERE LUECKE<>OHNE_LUECKE"; 

		MyRECORD_LIST  recList = new MyRECORD_LIST(cQueryDeb,"WERT");

		if (recList.get_(0).get_longValue("WERT")==-1)
		{
			//kann mehrere moglichkeiten geben, unter anderen, keine Adressen vorhanden oder die reihen lueckenlos,
			//deshalb weitere kontrollabfrage
			String cQueryDebKontrolle = 
				"SELECT NVL(MAX(OHNE_LUECKE),-1) AS WERT FROM "+ 
				"( "+
				" SELECT ROWNUM+"+lStartWert+" -1 AS OHNE_LUECKE,NUM AS LUECKE FROM ( "+
					" SELECT TO_NUMBER(REGEXP_SUBSTR("+cFeldName+",'^[0-9]*$')) AS NUM "+  
						" FROM JT_FIRMENINFO "+
						" WHERE "+cFeldName+" IS NOT NULL "+
						" AND     TO_NUMBER(REGEXP_SUBSTR("+cFeldName+",'^[0-9]*$'))>0 "+ 
						" AND     TO_NUMBER(REGEXP_SUBSTR("+cFeldName+",'^[0-9]*$'))>="+lStartWert+	" "+
						" AND     TO_NUMBER(REGEXP_SUBSTR("+cFeldName+",'^[0-9]*$'))<="+lEndWert+	" "+
						" AND     REGEXP_LIKE ("+cFeldName+", '^[0-9]*$') "+
				" UNION " +
				" SELECT NUMMER FROM "+bibE2.cTO()+"."+cAusschlussTabelle+"  "+
   			    "     WHERE NUMMER>0 "+
   			    "     AND   NUMMER>="+lStartWert+	" "+
   			    "     AND   NUMMER<="+lEndWert+	" "+
				" ORDER BY 1 ) )";
			
			MyRECORD_LIST  recList2 = new MyRECORD_LIST(cQueryDebKontrolle,"WERT");
			if (recList2.get_(0).get_longValue("WERT")==-1)
			{
				//dann war nichts in der tabelle, der minimal-wert wird zurueckgegeben
				cRueck = ""+lStartWert;
			}
			else
			{
				//dann was alles sauber gefuellt und der endwert max-wert+1 ist die rueckgabe
				long lHelpNEU = (recList2.get_(0).get_longValue("WERT")+1);
				if (lHelpNEU>lEndWert)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Im "+cTyp+"-Nummernkreis ",true,""+lStartWert,false," bis ",true,""+lEndWert,false," ist keine Nummer mehr frei !",true)));
					return "";
				}
				else
				{
					cRueck = ""+lHelpNEU;
				}
			}
		}
		else
		{
			//dann hat er was gefunden
			cRueck = recList.get_(0).get_UnFormatedValue("WERT");
		}
		
		
		if (cRueck.equals("0"))
		{
			cRueck = "";
		}
		
		if (S.isEmpty(cRueck))
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Im "+cTyp+"-Nummernkreis ",true,""+lStartWert,false," bis ",true,""+lEndWert,false," ist keine Nummer mehr frei !",true)));
		}
		
		return cRueck;
		
	}
	
	
}


