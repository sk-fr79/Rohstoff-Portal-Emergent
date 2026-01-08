package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import java.math.BigDecimal;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class BSKC_ExpandBlockSummen extends E2_ExpandableRow
{

	private MyE2_Grid 				oGridWithInfos = new MyE2_Grid(MyE2_Grid.STYLE_GRID_DDARK_BORDER_INSETS_11());
	private E2_NavigationList  		oNaviList = null;
	private String    				cEK_VK = null;
	private String    				cEK_VK_kontra = null;
	
//	
//	//hier die Summen-Definitionen
//	private HashMap<MyE2_String, String>  hmSummenAnzuzeigen = new HashMap<MyE2_String, String>();
	
	//private GridLayoutData  oGLL = MyE2_Grid.LAYOUT_RIGHT(new Insets(5,2,10,2));
	private GridLayoutData  oGLR = 		MyE2_Grid.LAYOUT_RIGHT(new Insets(5,2,10,2));
	private GridLayoutData  oGLR_RED = 	MyE2_Grid.LAYOUT_RIGHT(new Insets(5,2,10,2));

	
	
	
	public BSKC_ExpandBlockSummen(E2_NavigationList  NaviList, String  EK_VK)
	{
		super(new MyE2_String("Summenblock"),new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
	
		this.oNaviList = NaviList;
		this.cEK_VK = EK_VK;
		this.cEK_VK_kontra = "VK";
		
		this.oGLR_RED.setBackground(new E2_ColorAlarm());
		
		if (this.cEK_VK.equals("VK"))
		{
			this.cEK_VK_kontra = "EK";
		}
		
		this.add(this.oGridWithInfos, E2_INSETS.I_2_2_2_2);
		this.get_oButtonClose().doActionPassiv();
		
		this.oNaviList.add_actionAfterListCompleted(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				BSKC_ExpandBlockSummen oThis = BSKC_ExpandBlockSummen.this;
				
				if (oThis.get_bOpen())
				{
					oThis.FillSummenHM();
				}
				else
				{
					oThis.oGridWithInfos.removeAll();
				}
			}
		});
				
		
		this.get_oButtonOpen().add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				BSKC_ExpandBlockSummen oThis = BSKC_ExpandBlockSummen.this;
				oThis.FillSummenHM();
			}
		});
				
		this.get_oButtonClose().add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				BSKC_ExpandBlockSummen oThis = BSKC_ExpandBlockSummen.this;
				oThis.oGridWithInfos.removeAll();
			}
		});
		
		
	}

	
	
	//2012-10-26: neue version beruecksichtig gegenlaeufige abrechnung und abzuege
	private void FillSummenHM() throws myException
	{
		
		String cSQL_For_IDs_Navilist = this.oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_CompleteSQLQueryFor_ID_VECTOR("",true);
		if (S.isFull(cSQL_For_IDs_Navilist))
		{
			//order-Block rausschmeissen
			int iPosOrder = cSQL_For_IDs_Navilist.toUpperCase().indexOf("ORDER");
			if (iPosOrder>0)
			{
				cSQL_For_IDs_Navilist = cSQL_For_IDs_Navilist.substring(0, iPosOrder);
			}
			
		}

		this.oGridWithInfos.removeAll();
		this.oGridWithInfos.setSize(11);
		
		
		
		
		// Step 1: Gesamtmenge
		MyRECORD  recGesamtMenge = new MyRECORD("SELECT SUM(NVL(JT_VPOS_KON.ANZAHL,0)) AS ANZAHL FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_VPOS_KON IN ("+cSQL_For_IDs_Navilist+")");

		// Step 2: Zugeordnet
		MyRECORD  recZuordMenge = new MyRECORD("SELECT SUM(NVL(JT_EK_VK_BEZUG.ANZAHL,0)) AS ANZAHL FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_"+this.cEK_VK+ " IN ("+cSQL_For_IDs_Navilist+")");
		
		// Step 3: Lager
		MyRECORD  recZuordLager = new MyRECORD("SELECT SUM(NVL(JT_VPOS_KON_LAGER.LAGERMENGE,0)) AS ANZAHL FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE ID_VPOS_KON  IN ("+cSQL_For_IDs_Navilist+")");

		
		// Step 5: Fuhrenzuordnung Plan/Echt
		String cSQL_Query1 = this.baue_query_fuhre("LADEMENGE_GUTSCHRIFT", "ANTEIL_LADEMENGE_LIEF", "ANTEIL_ABLADEMENGE_LIEF", "ANTEIL_PLANMENGE_LIEF", "ABZUG_LADEMENGE_LIEF", cSQL_For_IDs_Navilist, "JT_VPOS_TPA_FUHRE.ID_VPOS_KON_EK");
		String cSQL_Query2 = this.baue_query_fuhre_ort("JT_VPOS_TPA_FUHRE_ORT.LADEMENGE_GUTSCHRIFT", "JT_VPOS_TPA_FUHRE_ORT.ANTEIL_LADEMENGE", "JT_VPOS_TPA_FUHRE_ORT.ANTEIL_ABLADEMENGE", "JT_VPOS_TPA_FUHRE_ORT.ANTEIL_PLANMENGE", cSQL_For_IDs_Navilist);
		
		if (this.cEK_VK.equals("VK"))
		{
			
				cSQL_Query1 = this.baue_query_fuhre("ABLADEMENGE_RECHNUNG", "ANTEIL_ABLADEMENGE_ABN", "ANTEIL_LADEMENGE_ABN", "ANTEIL_PLANMENGE_ABN", "ABZUG_ABLADEMENGE_ABN", cSQL_For_IDs_Navilist, "JT_VPOS_TPA_FUHRE.ID_VPOS_KON_VK");
				cSQL_Query2 = this.baue_query_fuhre_ort("JT_VPOS_TPA_FUHRE_ORT.ABLADEMENGE_RECHNUNG", "JT_VPOS_TPA_FUHRE_ORT.ANTEIL_ABLADEMENGE", "JT_VPOS_TPA_FUHRE_ORT.ANTEIL_LADEMENGE", 
														"JT_VPOS_TPA_FUHRE_ORT.ANTEIL_PLANMENGE", cSQL_For_IDs_Navilist);
		}
		MyRECORD  recZuordFU = new MyRECORD(cSQL_Query1);
		MyRECORD  recZuordFUO = new MyRECORD(cSQL_Query2);
		BigDecimal bdSum_Fuhren_Plan_Echt = recZuordFU.get_bdValue("ANZAHL", BigDecimal.ZERO, 0).add(recZuordFUO.get_bdValue("ANZAHL", BigDecimal.ZERO, 0));

		
		// Step 5: Fuhrenzuordnung Echt
		String cSQL_QueryB1 = this.baue_query_fuhre("LADEMENGE_GUTSCHRIFT", "ANTEIL_LADEMENGE_LIEF", "ANTEIL_ABLADEMENGE_LIEF", "0", "ABZUG_LADEMENGE_LIEF", cSQL_For_IDs_Navilist, "JT_VPOS_TPA_FUHRE.ID_VPOS_KON_EK");
		String cSQL_QueryB2 = this.baue_query_fuhre_ort("JT_VPOS_TPA_FUHRE_ORT.LADEMENGE_GUTSCHRIFT", "JT_VPOS_TPA_FUHRE_ORT.ANTEIL_LADEMENGE",
														"JT_VPOS_TPA_FUHRE_ORT.ANTEIL_ABLADEMENGE", "0", cSQL_For_IDs_Navilist);

		if (this.cEK_VK.equals("VK"))
		{
			cSQL_QueryB1 = this.baue_query_fuhre("ABLADEMENGE_RECHNUNG", "ANTEIL_ABLADEMENGE_ABN", "ANTEIL_LADEMENGE_ABN", "0", "ABZUG_ABLADEMENGE_ABN", cSQL_For_IDs_Navilist, "JT_VPOS_TPA_FUHRE.ID_VPOS_KON_VK");
			cSQL_QueryB2 = this.baue_query_fuhre_ort("JT_VPOS_TPA_FUHRE_ORT.ABLADEMENGE_RECHNUNG", "JT_VPOS_TPA_FUHRE_ORT.ANTEIL_ABLADEMENGE", 
													"JT_VPOS_TPA_FUHRE_ORT.ANTEIL_LADEMENGE", "0", cSQL_For_IDs_Navilist);
		}
		
		MyRECORD  recZuordFU_ECHT = new MyRECORD(cSQL_QueryB1);
		MyRECORD  recZuordFUO_ECHT = new MyRECORD(cSQL_QueryB2);
		BigDecimal bdSum_FuhrenECHT = recZuordFU_ECHT.get_bdValue("ANZAHL", BigDecimal.ZERO, 0).add(recZuordFUO_ECHT.get_bdValue("ANZAHL", BigDecimal.ZERO, 0));


		// Step 7: Kontrakte abgerechnet
		String cSQL_QueryC1 = "SELECT SUM(NVL(ANZAHL,0)-NVL(ANZAHL_ABZUG_LAGER,0)) AS ANZAHL FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE NVL(DELETED,'N')='N' AND ID_VPOS_KON_ZUGEORD IN  ("+cSQL_For_IDs_Navilist+")";
		//String cSQL_QueryC1 = "SELECT SUM(NVL(ANZAHL,0)) AS ANZAHL FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE NVL(DELETED,'N')='N' AND ID_VPOS_KON_ZUGEORD IN  ("+cSQL_For_IDs_Navilist+")";
		MyRECORD  recZuord_RG = new MyRECORD(cSQL_QueryC1);


		
		this.oGridWithInfos.add(new labText("Gesamtmenge "+this.cEK_VK+"-Kontrakte"));
		this.oGridWithInfos.add(new labText("Zuordnung"));
		this.oGridWithInfos.add(new labText(this.cEK_VK_kontra+"-Kontrakte"));
		this.oGridWithInfos.add(new labText(" "));
		this.oGridWithInfos.add(new labText((this.cEK_VK.equals("EK")?"Ins":"Vom")+" Lager"));
		this.oGridWithInfos.add(new labText(" "));
		this.oGridWithInfos.add(new labText("Gesamt"));
		this.oGridWithInfos.add(new labText(" "));
		this.oGridWithInfos.add(new labText("Fuhren Plan/Echt"));
		this.oGridWithInfos.add(new labText("Fuhren Echt"));
		this.oGridWithInfos.add(new labText("Menge in "+(this.cEK_VK.equals("EK")?"Gutschrift":"Rechnung")));

		//zeile 2 mit summen
		this.oGridWithInfos.add(new labWert(MyNumberFormater.formatDez(recGesamtMenge.get_bdValue("ANZAHL", BigDecimal.ZERO, 0),0,true)));
		this.oGridWithInfos.add(new labWert(" "));
		this.oGridWithInfos.add(new labWert(MyNumberFormater.formatDez(recZuordMenge.get_bdValue("ANZAHL", BigDecimal.ZERO, 0),0,true)));
		this.oGridWithInfos.add(new labWert("+"));
		this.oGridWithInfos.add(new labWert(MyNumberFormater.formatDez(recZuordLager.get_bdValue("ANZAHL", BigDecimal.ZERO, 0),0,true)));
		this.oGridWithInfos.add(new labWert("="));
		this.oGridWithInfos.add(new labWert(MyNumberFormater.formatDez(  (recZuordMenge.get_bdValue("ANZAHL", BigDecimal.ZERO, 0).add(recZuordLager.get_bdValue("ANZAHL", BigDecimal.ZERO, 0))),0,true)));
		this.oGridWithInfos.add(new labWert("--->"));
		this.oGridWithInfos.add(new labWert(MyNumberFormater.formatDez(  bdSum_Fuhren_Plan_Echt,0,true)));
		this.oGridWithInfos.add(new labWert(MyNumberFormater.formatDez(  bdSum_FuhrenECHT,0,true)));
		this.oGridWithInfos.add(new labWert(MyNumberFormater.formatDez(recZuord_RG.get_bdValue("ANZAHL", BigDecimal.ZERO, 0),0,true)));
		
		//zeile 3 mit differenzen
		this.oGridWithInfos.add(new labWert(" "));
		this.oGridWithInfos.add(new labWert(" "));
		this.oGridWithInfos.add(new labWert(" "));
		this.oGridWithInfos.add(new labWert(" "));
		this.oGridWithInfos.add(new labWert(" "));
		this.oGridWithInfos.add(new labWert(" "));
		this.oGridWithInfos.add(new labWertRED(MyNumberFormater.formatDez(  recGesamtMenge.get_bdValue("ANZAHL", BigDecimal.ZERO, 0).subtract((recZuordMenge.get_bdValue("ANZAHL", BigDecimal.ZERO, 0).add(recZuordLager.get_bdValue("ANZAHL", BigDecimal.ZERO, 0)))),0,true)));
		this.oGridWithInfos.add(new labWert(" "));
		this.oGridWithInfos.add(new labWertRED(MyNumberFormater.formatDez(  recGesamtMenge.get_bdValue("ANZAHL", BigDecimal.ZERO, 0).subtract(bdSum_Fuhren_Plan_Echt),0,true)));
		this.oGridWithInfos.add(new labWertRED(MyNumberFormater.formatDez(  bdSum_Fuhren_Plan_Echt.subtract(bdSum_FuhrenECHT),0,true)));
		this.oGridWithInfos.add(new labWertRED(MyNumberFormater.formatDez(  bdSum_FuhrenECHT.subtract(recZuord_RG.get_bdValue("ANZAHL", BigDecimal.ZERO, 0)),0,true)));
		
	}
	
	/**
	 * 
	 * @param cBedingungsFeld
	 * @param cRegulaereFeld
	 * @param cGegenFeld
	 * @param cPlanFeld
	 * @param cAbzugsFeld
	 * @param cSQLBlock_ID_Liste
	 * @return
	 */
	private String baue_query_fuhre(String cBedingungsFeld, String cRegulaereFeld, String cGegenFeld, String cPlanFeld, String cAbzugsFeld, 
			String cSQLBlock_ID_Liste, String cKontrakt_ID_In_Fuhre)
	{
		String cRueck = "";
		
		cRueck = "SELECT SUM("+
	       " CASE "+
	           " WHEN NVL(" +cBedingungsFeld+ ",'N')='Y' "+
	           " THEN NVL(" +cRegulaereFeld+ "," +cPlanFeld+ ")-NVL("+cAbzugsFeld+",0) "+
	           " ELSE NVL(" +cGegenFeld+ ",  " +cPlanFeld+ ")-  NVL("+cAbzugsFeld+",0) "+
	        " END)  AS ANZAHL FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' " +
	        	" AND NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' AND "+cKontrakt_ID_In_Fuhre + " IN  ("+cSQLBlock_ID_Liste+")";
	
		return cRueck;
	}
	

	/**
	 * 
	 * @param cBedingungsFeld
	 * @param cRegulaereFeld
	 * @param cGegenFeld
	 * @param cSQLBlock_ID_Liste
	 * @return
	 */
	private String baue_query_fuhre_ort(String cBedingungsFeld, String cRegulaereFeld, String cGegenFeld, String cPlanFeld, String cSQLBlock_ID_Liste)
	{
		String cRueck = "";
		
		cRueck = "SELECT    SUM("+
							       " CASE "+
							           " WHEN NVL(" +cBedingungsFeld+ ",'N')='Y' "+
							           " THEN NVL(" +cRegulaereFeld+ ","+cPlanFeld+")-NVL(JT_VPOS_TPA_FUHRE_ORT.ABZUG_MENGE,0) "+
							           " ELSE NVL(" +cGegenFeld+ ",    "+cPlanFeld+")-NVL(JT_VPOS_TPA_FUHRE_ORT.ABZUG_MENGE,0) "+
						  " END) AS ANZAHL  FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT" +
						  " LEFT OUTER JOIN JT_VPOS_TPA_FUHRE ON (JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)" +
						  " WHERE NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' AND  NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' " +
						  " AND JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_KON IN  ("+cSQLBlock_ID_Liste+")";
	
		return cRueck;
	}

	
//alt	
//	private void FillSummenHM() throws myException
//	{
//		
//		String cSQL_For_IDs_Navilist = this.oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_CompleteSQLQueryFor_ID_VECTOR("",true);
//		if (S.isFull(cSQL_For_IDs_Navilist))
//		{
//			//order-Block rausschmeissen
//			int iPosOrder = cSQL_For_IDs_Navilist.toUpperCase().indexOf("ORDER");
//			if (iPosOrder>0)
//			{
//				cSQL_For_IDs_Navilist = cSQL_For_IDs_Navilist.substring(0, iPosOrder);
//			}
//			
//		}
//
//		this.oGridWithInfos.removeAll();
//		this.oGridWithInfos.setSize(11);
//		
//		
//		
//		
//		// Step 1: Gesamtmenge
//		MyRECORD  recGesamtMenge = new MyRECORD("SELECT SUM(NVL(JT_VPOS_KON.ANZAHL,0)) AS ANZAHL FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_VPOS_KON IN ("+cSQL_For_IDs_Navilist+")");
//
//		// Step 2: Zugeordnet
//		MyRECORD  recZuordMenge = new MyRECORD("SELECT SUM(NVL(JT_EK_VK_BEZUG.ANZAHL,0)) AS ANZAHL FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_"+this.cEK_VK+ " IN ("+cSQL_For_IDs_Navilist+")");
//		
//		// Step 3: Lager
//		MyRECORD  recZuordLager = new MyRECORD("SELECT SUM(NVL(JT_VPOS_KON_LAGER.LAGERMENGE,0)) AS ANZAHL FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE ID_VPOS_KON  IN ("+cSQL_For_IDs_Navilist+")");
//
//		
//		// Step 5: Fuhrenzuordnung Plan/Echt
//		String cSQL_Query1 = "SELECT SUM(NVL(ANTEIL_LADEMENGE_LIEF,ANTEIL_PLANMENGE_LIEF)) AS ANZAHL FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N' AND ID_VPOS_KON_EK IN  ("+cSQL_For_IDs_Navilist+")";
//		String cSQL_Query2 = "SELECT SUM(NVL(ANTEIL_LADEMENGE,ANTEIL_PLANMENGE))  AS ANZAHL  FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT" +
//								" LEFT OUTER JOIN JT_VPOS_TPA_FUHRE ON (JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)" +
//								" WHERE NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' AND  NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' AND ID_VPOS_KON IN  ("+cSQL_For_IDs_Navilist+")";
//		if (this.cEK_VK.equals("VK"))
//		{
//			cSQL_Query1 = "SELECT SUM(NVL(ANTEIL_ABLADEMENGE_ABN,ANTEIL_PLANMENGE_ABN)) AS ANZAHL  FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N' AND ID_VPOS_KON_VK IN  ("+cSQL_For_IDs_Navilist+")";
//			cSQL_Query2 = "SELECT SUM(NVL(ANTEIL_ABLADEMENGE,ANTEIL_PLANMENGE))  AS ANZAHL  FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT" +
//							" LEFT OUTER JOIN JT_VPOS_TPA_FUHRE ON (JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)" +
//							" WHERE NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' AND  NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' AND ID_VPOS_KON IN  ("+cSQL_For_IDs_Navilist+")";
//		}
//		MyRECORD  recZuordFU = new MyRECORD(cSQL_Query1);
//		MyRECORD  recZuordFUO = new MyRECORD(cSQL_Query2);
//		BigDecimal bdSum_Fuhren_Plan_Echt = recZuordFU.get_bdValue("ANZAHL", BigDecimal.ZERO, 0).add(recZuordFUO.get_bdValue("ANZAHL", BigDecimal.ZERO, 0));
//
//		
//		// Step 6: Fuhrenzuordnung Echt
//		String cSQL_QueryB1 = "SELECT SUM(NVL(ANTEIL_LADEMENGE_LIEF,0)) AS ANZAHL FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N' AND ID_VPOS_KON_EK IN  ("+cSQL_For_IDs_Navilist+")";
//		String cSQL_QueryB2 = "SELECT SUM(NVL(ANTEIL_LADEMENGE,0))  AS ANZAHL  FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT" +
//								" LEFT OUTER JOIN JT_VPOS_TPA_FUHRE ON (JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)" +
//								" WHERE NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' AND  NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' AND ID_VPOS_KON IN  ("+cSQL_For_IDs_Navilist+")";
//		if (this.cEK_VK.equals("VK"))
//		{
//			cSQL_QueryB1 = "SELECT SUM(NVL(ANTEIL_ABLADEMENGE_ABN,0)) AS ANZAHL  FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N' AND ID_VPOS_KON_VK IN  ("+cSQL_For_IDs_Navilist+")";
//			cSQL_QueryB2 = "SELECT SUM(NVL(ANTEIL_ABLADEMENGE,0))  AS ANZAHL  FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT" +
//							" LEFT OUTER JOIN JT_VPOS_TPA_FUHRE ON (JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)" +
//							" WHERE NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' AND  NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' AND ID_VPOS_KON IN  ("+cSQL_For_IDs_Navilist+")";
//		}
//		MyRECORD  recZuordFU_ECHT = new MyRECORD(cSQL_QueryB1);
//		MyRECORD  recZuordFUO_ECHT = new MyRECORD(cSQL_QueryB2);
//		BigDecimal bdSum_FuhrenECHT = recZuordFU_ECHT.get_bdValue("ANZAHL", BigDecimal.ZERO, 0).add(recZuordFUO_ECHT.get_bdValue("ANZAHL", BigDecimal.ZERO, 0));
//
//
//		// Step 7: Kontrakte abgerechnet
//		String cSQL_QueryC1 = "SELECT SUM(NVL(ANZAHL,0)) AS ANZAHL FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE NVL(DELETED,'N')='N' AND ID_VPOS_KON_ZUGEORD IN  ("+cSQL_For_IDs_Navilist+")";
//		MyRECORD  recZuord_RG = new MyRECORD(cSQL_QueryC1);
//
//
//		
//		this.oGridWithInfos.add(new labText("Gesamtmenge "+this.cEK_VK+"-Kontrakte"));
//		this.oGridWithInfos.add(new labText("Zuordnung"));
//		this.oGridWithInfos.add(new labText(this.cEK_VK_kontra+"-Kontrakte"));
//		this.oGridWithInfos.add(new labText(" "));
//		this.oGridWithInfos.add(new labText((this.cEK_VK.equals("EK")?"Ins":"Vom")+" Lager"));
//		this.oGridWithInfos.add(new labText(" "));
//		this.oGridWithInfos.add(new labText("Gesamt"));
//		this.oGridWithInfos.add(new labText(" "));
//		this.oGridWithInfos.add(new labText("Fuhren Plan/Echt"));
//		this.oGridWithInfos.add(new labText("Fuhren Echt"));
//		this.oGridWithInfos.add(new labText("Menge in "+(this.cEK_VK.equals("EK")?"Gutschrift":"Rechnung")));
//
//		//zeile 2 mit summen
//		this.oGridWithInfos.add(new labWert(recGesamtMenge.get_FormatedValue("ANZAHL", "0")));
//		this.oGridWithInfos.add(new labWert(" "));
//		this.oGridWithInfos.add(new labWert(recZuordMenge.get_FormatedValue("ANZAHL", "0")));
//		this.oGridWithInfos.add(new labWert("+"));
//		this.oGridWithInfos.add(new labWert(recZuordLager.get_FormatedValue("ANZAHL", "0")));
//		this.oGridWithInfos.add(new labWert("="));
//		this.oGridWithInfos.add(new labWert(MyNumberFormater.formatDez(  (recZuordMenge.get_bdValue("ANZAHL", BigDecimal.ZERO, 0).add(recZuordLager.get_bdValue("ANZAHL", BigDecimal.ZERO, 0))),0,true)));
//		this.oGridWithInfos.add(new labWert("--->"));
//		this.oGridWithInfos.add(new labWert(MyNumberFormater.formatDez(  bdSum_Fuhren_Plan_Echt,0,true)));
//		this.oGridWithInfos.add(new labWert(MyNumberFormater.formatDez(  bdSum_FuhrenECHT,0,true)));
//		this.oGridWithInfos.add(new labWert(recZuord_RG.get_FormatedValue("ANZAHL", "0")));
//		
//		//zeile 3 mit differenzen
//		this.oGridWithInfos.add(new labWert(" "));
//		this.oGridWithInfos.add(new labWert(" "));
//		this.oGridWithInfos.add(new labWert(" "));
//		this.oGridWithInfos.add(new labWert(" "));
//		this.oGridWithInfos.add(new labWert(" "));
//		this.oGridWithInfos.add(new labWert(" "));
//		this.oGridWithInfos.add(new labWertRED(MyNumberFormater.formatDez(  recGesamtMenge.get_bdValue("ANZAHL", BigDecimal.ZERO, 0).subtract((recZuordMenge.get_bdValue("ANZAHL", BigDecimal.ZERO, 0).add(recZuordLager.get_bdValue("ANZAHL", BigDecimal.ZERO, 0)))),0,true)));
//		this.oGridWithInfos.add(new labWert(" "));
//		this.oGridWithInfos.add(new labWertRED(MyNumberFormater.formatDez(  recGesamtMenge.get_bdValue("ANZAHL", BigDecimal.ZERO, 0).subtract(bdSum_Fuhren_Plan_Echt),0,true)));
//		this.oGridWithInfos.add(new labWertRED(MyNumberFormater.formatDez(  bdSum_Fuhren_Plan_Echt.subtract(bdSum_FuhrenECHT),0,true)));
//		this.oGridWithInfos.add(new labWertRED(MyNumberFormater.formatDez(  bdSum_FuhrenECHT.subtract(recZuord_RG.get_bdValue("ANZAHL", BigDecimal.ZERO, 0)),0,true)));
//		
//		
//	}

	
	
	private class labText extends MyE2_Label
	{

		public labText(String text)
		{
			super(new MyE2_String(text), new E2_FontPlain());
			this.setLayoutData(BSKC_ExpandBlockSummen.this.oGLR);
		}
		
	}
	
	private class labWert extends MyE2_Label
	{

		public labWert(String text)
		{
			super(text, new E2_FontBold(2));
			this.setLayoutData(BSKC_ExpandBlockSummen.this.oGLR);
		}
		
	}
	

	private class labWertRED extends MyE2_Label
	{

		public labWertRED(String text)
		{
			super(text, new E2_FontBold(2));
			this.setLayoutData(BSKC_ExpandBlockSummen.this.oGLR_RED);
		}
		
	}

	
}
