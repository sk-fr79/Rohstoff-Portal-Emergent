package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.QUERYAGENT_MarkiereInaktiveInNaviList;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MITARBEITERTYP;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_ListCompShowGeopoint;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_ListCompShowGeopointOSM;
import rohstoff.Echo2BusinessLogic._4_ALL.TelFaxIcon;

public class FSM_LIST_ComponentMAP extends E2_ComponentMAP
{


	
	public FSM_LIST_ComponentMAP() throws myException
	{
		super(new FSM_LIST_SQLFieldMap());
		
		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
		
		SQLFieldMAP oSQLFM = this.get_oSQLFieldMAP();

		if(ENUM_MANDANT_DECISION.USE_GEOPOINT_SHOW.is_YES()) {
			//2017-02-10: geocoding aufruf 
			this.add_Component(FSM_CONST.LIST_GEOCODE_MIT, new FS_ListCompShowGeopoint(), new MyE2_String("G"));
			this.add_Component(FSM_CONST.LIST_GEOCODE_MIT_OSM, new FS_ListCompShowGeopointOSM(), new MyE2_String("OSM"));
		}

		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("W_ANREDE")),new MyE2_String("Anrede"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("VORNAME")),new MyE2_String("Vorname"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("NAME1")),new MyE2_String("Name 1"));
		//2014-04-25: neues mitarbeiter-typ-grid
		this.add_Component(FSM_CONST.NAME_OF_MITARBEITERTYPGRID,new MitarbeiterTypGrid(),new MyE2_String("Mitarbeitertyp"));
//		variante alt
//		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("V_KURZBEZEICHNUNG")),new MyE2_String("Mitarbeitertyp"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ORT")),new MyE2_String("Ort"));
		
		// NEU
		this.add_Component(FSM_CONST.NAME_OF_TELGRID,new TelGrid(),new MyE2_String("Telefon"));
		this.add_Component(FSM_CONST.NAME_OF_MAILGRID,new MailGrid(),new MyE2_String("eMail"));
		
		//2015-04-22: den aktiv-schalter in der liste aktivieren
		//		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("AKTIV")),new MyE2_String("Akt."),true,true,new MyE2_String("Mitarbeiter ist aktiv"),null,null);
		this.add_Component(new FSM_LIST_COMP_CheckboxToggleActive(oSQLFM.get_("AKTIV")),new MyE2_String("Aktiv"));       

		
		
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("U_IST_ANSPRECHPARTNER")),new MyE2_String("AP"),true,true,new MyE2_String("Mitarbeiter ist Ansprechpartner (allgemein)"),null,null);
		
		
		//2014-02-21: felder raus
//		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("U_WEIHNACHTSGESCHENK")),new MyE2_String("WG"),true,true,new MyE2_String("Mitarbeiter erhält ein Weihnachtsgeschenk"),null,null);
//		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("U_SOMMERGESCHENK")),new MyE2_String("SG"),true,true,new MyE2_String("Mitarbeiter erhält ein Sommergeschenk"),null,null);
		
		
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("U_ASP_ABNAHMEANGEBOT")),new MyE2_String("AAG"),true,true,new MyE2_String(this.get_TT("ABNAHMEANGEBOT/PREISINFO")),null,null);     
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("U_ASP_ANGEBOT")),new MyE2_String("ANG"),true,true,new MyE2_String(this.get_TT("ANGEBOT")),null,null);     
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("U_ASP_EK_KONTRAKT")),new MyE2_String("EKK"),true,true,new MyE2_String(this.get_TT("EK-KONTRAKT")),null,null);     
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("U_ASP_VK_KONTRAKT")),new MyE2_String("VKK"),true,true,new MyE2_String(this.get_TT("VK-KONTRAKT")),null,null);     
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("U_ASP_RECHNUNG")),new MyE2_String("RE"),true,true,new MyE2_String(this.get_TT("RECHNUNG")),null,null);     
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("U_ASP_GUTSCHRIFT")),new MyE2_String("GUT"),true,true,new MyE2_String(this.get_TT("GUTSCHRIFT")),null,null);     
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("U_ASP_TRANSPORT")),new MyE2_String("TPA"),true,true,new MyE2_String(this.get_TT("TRANSPORTAUFTRAG")),null,null);     
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("U_ASP_FIBU")),new MyE2_String("FIBU"),true,true,new MyE2_String(this.get_TT("FIBU/MAHNWESEN")),null,null);
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("U_ID_MITARBEITER")),new MyE2_String("ID(Mitarb.)"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ID_ADRESSE")),new MyE2_String("ID(Adresse)"));
		
		
//		//2012-01-31: listen-variante des qualifiers
//		this.add_Component(FS_CONST.LIST_COL_DAUGHTER_MITARBEITER_QUALIFIER, new FSM_MASK_LIST_Qualifier(oSQLFM.get_("ID_ADRESSE"),true),new MyE2_String("Typ-Qualifizierer"));
//		
//		((MyE2IF__Component)this.get(FS_CONST.LIST_COL_DAUGHTER_MITARBEITER_QUALIFIER)).EXT().set_oLayout_ListElement_AND_Titel(MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_2_2_2_2));
		
		
		((MyE2IF__Component)this.get("W_ANREDE")).EXT().set_oColExtent(new Extent(80));
		((MyE2IF__Component)this.get("NAME1")).EXT().set_oColExtent(new Extent(150));
		((MyE2IF__Component)this.get("VORNAME")).EXT().set_oColExtent(new Extent(150));
//		((MyE2IF__Component)this.get("V_KURZBEZEICHNUNG")).EXT().set_oColExtent(new Extent(150));
		((MyE2IF__Component)this.get("ORT")).EXT().set_oColExtent(new Extent(150));
		((MyE2IF__Component)this.get("U_IST_ANSPRECHPARTNER")).EXT().set_oColExtent(new Extent(50));
		((MyE2IF__Component)this.get("U_ID_MITARBEITER")).EXT().set_oColExtent(new Extent(50));
		((MyE2IF__Component)this.get("ID_ADRESSE")).EXT().set_oColExtent(new Extent(50));
		

		
		
		// NEU
		((MyE2IF__Component)this.get("ORT")).EXT().set_bIsVisibleInList(false);
		((MyE2IF__Component)this.get("U_ID_MITARBEITER")).EXT().set_bIsVisibleInList(false);
		((MyE2IF__Component)this.get("ID_ADRESSE")).EXT().set_bIsVisibleInList(false);

		//2014-02-21: felder raus
//		((MyE2_DB_CheckBox)this.get("U_WEIHNACHTSGESCHENK")).setToolTipText(new MyE2_String("Weihnachtsgeschenk").CTrans());   //NEU_09
//		((MyE2_DB_CheckBox)this.get("U_SOMMERGESCHENK")).setToolTipText(new MyE2_String("Sommergeschenk").CTrans());	   //NEU_09
		
		
		// einblenden der Telefonnummer und mail-adressen
		this.set_oSubQueryAgent(new SubQueryAgent());
		this.add_oSubQueryAgent(new QUERYAGENT_MarkiereInaktiveInNaviList("AKTIV"));
		
	}

	
	private String get_TT(String cVorgangsTyp)
	{
		String cRueck = "Mitarbeiter wird als Ansprechpartner fuer Belege vom Typ: "+cVorgangsTyp+" herangezogen. " +
						"Standard Telefon und Fax sowie Mail werden den Beleg-Kopfsätzen benutzt";
		return cRueck;
	}
	
	
	// NEU
	private class TelGrid extends MyE2_Grid
	{

		public TelGrid() 
		{
			super(5, 0);
			this.add(new MyE2_Label("--"),4,E2_INSETS.I_2_0_0_0);
		}
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			return new TelGrid();
		}
	}
	
	private class MailGrid extends MyE2_Grid
	{

		public MailGrid() 
		{
			super(1, 0);
			this.add(new MyE2_Label("--"),1,E2_INSETS.I_2_0_0_0);
		}
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			return new MailGrid();
		}
	}

	
	private class MitarbeiterTypGrid extends MyE2_Grid
	{
		public MitarbeiterTypGrid() 
		{
			super(1, 0);
			this.add(new MyE2_Label("--"),1,E2_INSETS.I_2_0_0_0);
		}
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			return new MitarbeiterTypGrid();
		}
	}
	
	
	private class SubQueryAgent extends XX_ComponentMAP_SubqueryAGENT
	{

		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
		{
			((TelGrid)oMAP.get__Comp(FSM_CONST.NAME_OF_TELGRID)).removeAll();
			((TelGrid)oMAP.get__Comp(FSM_CONST.NAME_OF_TELGRID)).add(new MyE2_Label("--"),4,E2_INSETS.I_2_0_0_0);
			
			((MailGrid)oMAP.get__Comp(FSM_CONST.NAME_OF_MAILGRID)).removeAll();
			((MailGrid)oMAP.get__Comp(FSM_CONST.NAME_OF_MAILGRID)).add(new MyE2_Label("--"),1,E2_INSETS.I_2_0_0_0);
		}

		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
			String cQueryTel = "SELECT " +
						"   JT_KOMMUNIKATION.WERT_LAENDERVORWAHL," +
						"   JT_KOMMUNIKATION.WERT_VORWAHL," +
						"   JT_KOMMUNIKATION.WERT_RUFNUMMER," +
						"   NVL('('||JT_KOMMUNIKATIONS_TYP.KURZBEZEICHNUNG||')',' - '), " +
						"   NVL("+_DB.KOMMUNIKATIONS_TYP$IST_TEL_NUMMER+",'N'),"+
						"   NVL("+_DB.KOMMUNIKATIONS_TYP$IST_FAX_NUMMER+",'N') "+
					" FROM " + 
					bibE2.cTO()+".JT_ADRESSE, "+bibE2.cTO()+".JT_KOMMUNIKATION, "+bibE2.cTO()+".JT_KOMMUNIKATIONS_TYP" +
					"  WHERE " +
					" JT_ADRESSE.ID_ADRESSE=JT_KOMMUNIKATION.ID_ADRESSE AND" +
					" JT_KOMMUNIKATION.ID_KOMMUNIKATIONS_TYP=JT_KOMMUNIKATIONS_TYP.ID_KOMMUNIKATIONS_TYP (+)" +
					" AND JT_ADRESSE.ID_ADRESSE="+oUsedResultMAP.get_cUNFormatedROW_ID();
			
			String cQueryMail = "SELECT JT_EMAIL.EMAIL FROM "+bibE2.cTO()+".JT_EMAIL WHERE ID_ADRESSE="+oUsedResultMAP.get_cUNFormatedROW_ID();
			
			String[][] cAnserTel = bibDB.EinzelAbfrageInArray(cQueryTel, "");
			String[][] cAnserMail = bibDB.EinzelAbfrageInArray(cQueryMail, "");
			
			TelGrid 			oTelGrid =	(TelGrid)oMAP.get__Comp(FSM_CONST.NAME_OF_TELGRID);
			MailGrid 			oMailGrid = (MailGrid)oMAP.get__Comp(FSM_CONST.NAME_OF_MAILGRID);
			MitarbeiterTypGrid 	oMitGrid = (MitarbeiterTypGrid)oMAP.get__Comp(FSM_CONST.NAME_OF_MITARBEITERTYPGRID);

			oTelGrid.removeAll();
			oMailGrid.removeAll();
			oMitGrid.removeAll();
			
			if (cAnserTel == null) {
				oTelGrid.add(new MyE2_Label("@@ERR@@"),1,E2_INSETS.I_2_0_0_0);
			} else {
				for (int i=0;i<cAnserTel.length;i++) {
					oTelGrid.add(new MyE2_Label(cAnserTel[i][0]), E2_INSETS.I_1_1_5_1);
					oTelGrid.add(new MyE2_Label(cAnserTel[i][1]), E2_INSETS.I_1_1_5_1);
					oTelGrid.add(new MyE2_Label(cAnserTel[i][2]), E2_INSETS.I_1_1_5_1);
					oTelGrid.add(new MyE2_Label(cAnserTel[i][3],MyE2_Label.STYLE_SMALL_ITALIC()), new Insets(4,1,1,1));
					oTelGrid.add(new TelFaxIcon(S.NN(cAnserTel[i][4]).equals("Y"), S.NN(cAnserTel[i][5]).equals("Y"), cAnserTel[i][3], true), E2_INSETS.I_1_1_1_1);
				}
			}

			if (cAnserMail == null) {
				oMailGrid.add(new MyE2_Label("@@ERR@@"),1,E2_INSETS.I_2_0_0_0);
			} else  {
				for (int i=0;i<cAnserMail.length;i++) {
					oMailGrid.add(new MyE2_Label(cAnserMail[i][0]), E2_INSETS.I_1_1_1_1);
				}
			}

			String  cTYP1 = null;
			String  cTYP2 = null;
			String  cTYP3 = null;
			String  cTYP4 = null;
			if (oUsedResultMAP.get_LActualDBValue("U_"+_DB.MITARBEITER$ID_MITARBEITERTYP, true).intValue()>0) {
				cTYP1 =S.NN(oUsedResultMAP.get_FormatedValue("V_KURZBEZEICHNUNG")); 
			}
			
			if (oUsedResultMAP.get_LActualDBValue("U_"+_DB.MITARBEITER$ID_MITARBEITERTYP2, true).intValue()>0) {
				cTYP2 =S.NN(new RECORD_MITARBEITERTYP(""+oUsedResultMAP.get_LActualDBValue("U_"+_DB.MITARBEITER$ID_MITARBEITERTYP2, true).intValue()).get_KURZBEZEICHNUNG_cF_NN("<-->")); 
			}
			if (oUsedResultMAP.get_LActualDBValue("U_"+_DB.MITARBEITER$ID_MITARBEITERTYP3, true).intValue()>0) {
				cTYP3 =S.NN(new RECORD_MITARBEITERTYP(""+oUsedResultMAP.get_LActualDBValue("U_"+_DB.MITARBEITER$ID_MITARBEITERTYP3, true).intValue()).get_KURZBEZEICHNUNG_cF_NN("<-->")); 
			}
			if (oUsedResultMAP.get_LActualDBValue("U_"+_DB.MITARBEITER$ID_MITARBEITERTYP4, true).intValue()>0) {
				cTYP4 =S.NN(new RECORD_MITARBEITERTYP(""+oUsedResultMAP.get_LActualDBValue("U_"+_DB.MITARBEITER$ID_MITARBEITERTYP4, true).intValue()).get_KURZBEZEICHNUNG_cF_NN("<-->")); 
			}
			
			oMitGrid.add(new MyE2_Label(S.NN(cTYP1),MyE2_Label.STYLE_SMALL_ITALIC()), E2_INSETS.I(1,1,1,1));
			oMitGrid.add(new MyE2_Label(S.NN(cTYP2),MyE2_Label.STYLE_SMALL_ITALIC()), E2_INSETS.I(1,1,1,1));
			oMitGrid.add(new MyE2_Label(S.NN(cTYP3),MyE2_Label.STYLE_SMALL_ITALIC()), E2_INSETS.I(1,1,1,1));
			oMitGrid.add(new MyE2_Label(S.NN(cTYP4),MyE2_Label.STYLE_SMALL_ITALIC()), E2_INSETS.I(1,1,1,1));
			
			
		}
		
	}

	
}
