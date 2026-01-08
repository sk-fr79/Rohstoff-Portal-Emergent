package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;

public class FU_SelectionComponentVector_Version2 extends E2_SelectionComponentsVector
{
	private FU_SelectorFirmen 					oSelektorLieferanten 	= null;
	private FU_SelectorFirmen 					oSelektorAbnehmer 		= null;
	
	private FU_SelectorFirmenLieferort 			oSelektorLadeOrt 		= null;
	private FU_SelectorFirmenLieferort 			oSelektorAbladeOrt 		= null;

	private FU_SelectorKontraktPosition  		oSelectorEKKon 			= null;
	private FU_SelectorKontraktPosition  		oSelectorVKKon	 		= null;
	
	private FU_SelectorSorten   		 		oSelektorSorteEK 		= null;
	private FU_SelectorSorten   		 		oSelektorSorteVK 		= null;
	
	private FU_SelektorWelcheTypenEinblenden  	oSelTypen 				= new FU_SelektorWelcheTypenEinblenden();
	
	private FU_SelektorDeletedUndeleted         oSelDeletedUndeleted 	= new FU_SelektorDeletedUndeleted();
	private FU_SelektorMitUndOhneUMA            oSelMitUndOhneUma 		= new FU_SelektorMitUndOhneUMA();
	
	//2012-02-28: selektor fuer fuhren mit / ohne komplette mengenfreigabe
	private FU_SelektorMitUndOhneMengenFreigabe  oSelMitUndOhneMFR 		= new FU_SelektorMitUndOhneMengenFreigabe();
	
	//2012-03-01: selector wiegedatum von bis
	private FU_Selektor_WiegePlanDatumVonBis     oSelWiegeDatumVonBis 	= new FU_Selektor_WiegePlanDatumVonBis();
	
	//2013-01-18: selektor nach fuhrenorten (ja oder nein)
	private FU_SelektorMitUndOhneFuhrenOrt       oSelBesitztFuhrenOrt 	= new FU_SelektorMitUndOhneFuhrenOrt();
	
	//2013-06-21: selektor fuer die firmenbetreuer
	private FU_Selektor_WaehleBetreuerFremdfirma   oSelBetreuerInFuhre 	= new FU_Selektor_WaehleBetreuerFremdfirma();
	
	//2013-10-17: selektor fuer freie gruppen
	private FU_SelectorMulti_FreieGruppen   		oSelFreiGruppen 	= null;
	
	
	//2014-10-08: selektor zum suchen aller positionen eines fahrplans
	private FU_Selector_Fahrplan 			     	oSelFahrplan 		= new FU_Selector_Fahrplan();
	
	
	
	public FU_SelectionComponentVector_Version2(E2_NavigationList onavigationList, MyE2_Button  oButtonSwitchToOld, String cMODULE_KENNER) throws myException
	{
		super(onavigationList);

		this.oSelectorEKKon = 		new FU_SelectorKontraktPosition("EK");
		this.oSelectorVKKon = 		new FU_SelectorKontraktPosition("VK");

		this.oSelektorLadeOrt = 	new FU_SelectorFirmenLieferort(this, "EK");
		this.oSelektorAbladeOrt = 	new FU_SelectorFirmenLieferort(this, "VK");
		
		this.oSelektorSorteEK = 	new FU_SelectorSorten(this, "EK");
		this.oSelektorSorteVK = 	new FU_SelectorSorten(this, "VK");

		//2013-10-17: selektor fuer freie gruppen
		this.oSelFreiGruppen =		new FU_SelectorMulti_FreieGruppen(_DB.VPOS_TPA_FUHRE, _DB.VPOS_TPA_FUHRE$ID_VPOS_TPA_FUHRE, cMODULE_KENNER);
		
		Vector<IF_CanBePopulated> vAngeschlosseneEK = new Vector<IF_CanBePopulated>();
		Vector<IF_CanBePopulated> vAngeschlosseneVK = new Vector<IF_CanBePopulated>();
		
		vAngeschlosseneEK.add(this.oSelektorLadeOrt);
		vAngeschlosseneEK.add(this.oSelectorEKKon);
		
		vAngeschlosseneVK.add(this.oSelektorAbladeOrt);
		vAngeschlosseneVK.add(this.oSelectorVKKon);
		
		
		this.oSelektorLieferanten = 	new FU_SelectorFirmen(this,"EK",vAngeschlosseneEK);
		this.oSelektorAbnehmer =  		new FU_SelectorFirmen(this,"VK",vAngeschlosseneVK);
		
		this.oSelektorLieferanten.get_oSelKunden().add_oActionAgent(new ownActionAgent(this.oSelektorLieferanten));
		this.oSelektorAbnehmer.get_oSelKunden().add_oActionAgent(new ownActionAgent(this.oSelektorAbnehmer));

		
		
		this.oSelektorLieferanten.REFRESH_KundenSelektor();
		this.oSelektorAbnehmer.REFRESH_KundenSelektor();

		this.oSelektorLadeOrt.populate("");
		this.oSelektorAbladeOrt.populate("");

		this.oSelectorEKKon.populate("");
		this.oSelectorVKKon.populate("");
		
		
		
		String cSQLBedAdresse = "JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE IN " +
					        "("+
								" SELECT F2.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE F2 WHERE F2.ID_ADRESSE_#STARTZIEL#=#WERT# "+
								" UNION "+
								" SELECT FO2.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FO2 WHERE FO2.ID_ADRESSE=#WERT# " +
								" AND NVL(FO2.DEF_QUELLE_ZIEL,' ')='#EKVK#'" +
								 " AND NVL(FO2.DELETED,'N')='N' "+
								")";

		
		String cSQLBed_Ladeort = "JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE IN " +
					        "("+
								" SELECT F2.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE F2 WHERE F2.ID_ADRESSE_LAGER_#STARTZIEL#=#WERT# "+
								" UNION "+
								" SELECT FO2.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FO2 WHERE FO2.ID_ADRESSE_LAGER=#WERT# " +
								" AND NVL(FO2.DEF_QUELLE_ZIEL,' ')='#EKVK#'" +
								 " AND NVL(FO2.DELETED,'N')='N' "+
								")";
		
		
		String cSQLBed_Sorte = "JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE IN " +
					        	"("+
								" SELECT F2.ID_VPOS_TPA_FUHRE " +
								" FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE F2 " +
								" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ AB ON (F2.ID_ARTIKEL_BEZ_#EKVK#=AB.ID_ARTIKEL_BEZ) "+
								" WHERE AB.ID_ARTIKEL=#WERT# "+
								" UNION "+
								" SELECT FO2.ID_VPOS_TPA_FUHRE " +
								" FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FO2 " +
								" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ AB ON (FO2.ID_ARTIKEL_BEZ=AB.ID_ARTIKEL_BEZ) "+
								" WHERE AB.ID_ARTIKEL=#WERT# "+
								" AND NVL(FO2.DEF_QUELLE_ZIEL,' ')='#EKVK#'" +
								" AND NVL(FO2.DELETED,'N')='N' "+
								")";

		
		
		
		
		FU_Selektor_WiegeDatum_PlusUmfeld  			oSelectorTageUmWiegeDatum = new FU_Selektor_WiegeDatum_PlusUmfeld();
		FU_Selektor_WiegeOderPlanDatum_PlusUmfeld  	oSelectorTageUmDatum = 		new FU_Selektor_WiegeOderPlanDatum_PlusUmfeld();
		ownSelectorFuhrenArt      	  				oSelectorFuhrenArt   = 		new ownSelectorFuhrenArt();

		
		//jetzt den selvector aufbauen
		this.add(new E2_ListSelectorStandard(	oSelektorLieferanten.get_oSelKunden(),	
												bibALL.ReplaceTeilString(  cSQLBedAdresse,bibALL.get_Vector("#STARTZIEL#", "#EKVK#"),bibALL.get_Vector("START", "EK")), 
												null, 
											    null));

		this.add(new E2_ListSelectorStandard(	oSelektorAbnehmer.get_oSelKunden(),	
												bibALL.ReplaceTeilString(  cSQLBedAdresse,bibALL.get_Vector("#STARTZIEL#", "#EKVK#"),bibALL.get_Vector("ZIEL", "VK")), 
												null, 
											    null));
		
		this.add(new E2_ListSelectorStandard(	oSelektorLadeOrt.get_oSelKunden(),
												bibALL.ReplaceTeilString(     cSQLBed_Ladeort, 
														                      bibALL.get_Vector("#STARTZIEL#", "#EKVK#"),bibALL.get_Vector("START", "EK")), 
												null, 
												null));
		

		this.add(new E2_ListSelectorStandard(	oSelektorAbladeOrt.get_oSelKunden(),
												bibALL.ReplaceTeilString(     cSQLBed_Ladeort, 
														                      bibALL.get_Vector("#STARTZIEL#", "#EKVK#"),bibALL.get_Vector("ZIEL", "VK")), 
												null, 
												null));
		
		this.add(new E2_ListSelectorStandard(	oSelektorSorteEK.get_oSelSorten(),
												bibALL.ReplaceTeilString(cSQLBed_Sorte,"#EKVK#","EK"),null,null));
		
		this.add(new E2_ListSelectorStandard(	oSelektorSorteVK.get_oSelSorten(),
												bibALL.ReplaceTeilString(cSQLBed_Sorte,"#EKVK#","VK"),null,null));
		

		this.add(oSelectorTageUmWiegeDatum);
		this.add(oSelectorTageUmDatum);
		this.add(oSelectorFuhrenArt);
		this.add(oSelectorEKKon);
		this.add(oSelectorVKKon);

		this.add(this.oSelTypen);
		this.add(this.oSelDeletedUndeleted);
		this.add(this.oSelMitUndOhneUma);
		this.add(this.oSelMitUndOhneMFR);
		
		this.add(this.oSelBesitztFuhrenOrt);
		
		
		//2012-03-01: zeitraumselektor
		this.add(this.oSelWiegeDatumVonBis);
		
		//2013-06-21: neuer selektor fuer firmenbetreuer
		this.add(this.oSelBetreuerInFuhre);
		
		
		//componente bauen (vertikales grid)
		MyE2_Grid  oGrid = new MyE2_Grid(MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		oGrid.setSize(6);
		oGrid.setOrientation(Grid.ORIENTATION_VERTICAL);
		
		Insets 			oIN = new Insets(3, 1, 3, 1);
		GridLayoutData  oGL = MyE2_Grid.LAYOUT_LEFT_TOP(oIN, null, 1,1);
		
		oGrid.add(oSelektorLieferanten,oGL);
		oGrid.add(oSelektorLadeOrt,oGL);
		oGrid.add(oSelectorEKKon.get_oComponentForSelection(),oGL);
		oGrid.add(oSelektorAbnehmer,oGL);	
		oGrid.add(oSelektorAbladeOrt,oGL);	
		oGrid.add(oSelectorVKKon.get_oComponentForSelection(),oGL);
		
		oGrid.add(oSelectorTageUmWiegeDatum.get_oComponentForSelection(),oGL);
		oGrid.add(oSelectorTageUmDatum.get_oComponentForSelection(),oGL);
		//neuer selektor fuer die explizite definition der ladedaten
		oGrid.add(this.oSelWiegeDatumVonBis.get_oComponentForSelection(new Integer(95), new Integer(100), new Integer(100),null,null),oGL);
		oGrid.add(new E2_ComponentGroupHorizontal(0,oSelektorSorteEK, oSelektorSorteVK, new Insets(0, 0, 5, 0)),oGL);
		
//		oGrid.add(oSelektorSorteEK,oGL);
//		oGrid.add(oSelektorSorteVK,oGL);
		oGrid.add(oSelTypen.get_oComponentForSelection(),MyE2_Grid.LAYOUT_LEFT_TOP(oIN, null, 1,2));
		
		oGrid.add(oSelectorFuhrenArt.get_oComponentForSelection(),MyE2_Grid.LAYOUT_LEFT_TOP(oIN, null, 1,1));
		oGrid.add(oSelDeletedUndeleted.get_oComponentForSelection(),MyE2_Grid.LAYOUT_LEFT_TOP(oIN, null, 1,1));
		oGrid.add(oSelMitUndOhneUma.get_oComponentForSelection(),MyE2_Grid.LAYOUT_LEFT_TOP(oIN, null, 1,1));
		oGrid.add(oSelMitUndOhneMFR.get_oComponentForSelection(),MyE2_Grid.LAYOUT_LEFT_TOP(oIN, null, 1,1));
		
		//2017-06-12: land selektor
		FU_Selector_Land_2						oSelLand 			= new FU_Selector_Land_2();
		this.add(oSelLand);
		oGrid.add(oSelLand.get_oComponentForSelection(),  MyE2_Grid.LAYOUT_LEFT_TOP(oIN, null, 2,1));
		
		oGrid.add(oSelBesitztFuhrenOrt.get_oComponentForSelection(),MyE2_Grid.LAYOUT_LEFT_TOP(oIN, null, 1,1));
		
		//2013-06-21: neuer bearbeiter-selektor
		oGrid.add(this.oSelBetreuerInFuhre.get_grid4Anzeige( bibARR.get_Array(80, 100, 30)), new Insets(4,2,10,2));
		
		//2013-03-05: neuer db-gestuetzter listselektor
		//2021-03-01: umstellung auf den Kombi-DB-Selektor
		E2_ListSelector_DB_Defined2 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined2(cMODULE_KENNER);
		this.add(oDB_BasedSelektor);
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse: "),100, bibARR.get_Array(80, 100)), new Insets(4,2,10,2));

		//2013-10-17: selektor fuer freie gruppen
		this.add(oSelFreiGruppen);
		oGrid.add(oSelFreiGruppen.get_oComponentForSelection(), 	MyE2_Grid.LAYOUT_LEFT_TOP(oIN, null, 1,1));
		
		//2014-10-08: fahrplanselektor
		this.add(this.oSelFahrplan);
		oGrid.add(this.oSelFahrplan.get_oComponentForSelection(),	MyE2_Grid.LAYOUT_LEFT_TOP(oIN, null, 1,1));
		
		oGrid.add(new E2_ComponentGroupHorizontal(0,this.get_AktivPassivComponent(), oButtonSwitchToOld, new Insets(0, 0, 5, 0)),MyE2_Grid.LAYOUT_LEFT_BOTTOM(oIN, null, 1,6));
		
		this.set_oSelComponent(oGrid);
	}
	
	
	//agent, der bei jedem klick auf ein lieferanten/abnehmer-selektion die zugehoerigen ladeorte holt
	private class ownActionAgent extends XX_ActionAgent
	{
		private FU_SelectorFirmen  oSel = null;
		
		public ownActionAgent(FU_SelectorFirmen Sel)
		{
			super();
			this.oSel = Sel;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			for (int i=0;i<oSel.get_vAngeschlossene().size();i++)
			{
				oSel.get_vAngeschlossene().get(i).populate(this.oSel.get_cActual_ID_Adress());
			}
		}
	}
	
	
	
	
	private class ownSelectorFuhrenArt extends E2_ListSelectorStandard
	{
		public ownSelectorFuhrenArt() throws myException
		{
//			super(new ownSelectFieldFuhrenArt(), "JT_VPOS_TPA_FUHRE."+RECORD_VPOS_TPA_FUHRE.FIELD__TYP_STRECKE_LAGER_MIXED+
//					"=#WERT#",  new MyE2_String("Fuhren-Art:"), new Integer(70));
			
			//2012-03-01: fuhren, die einen lager-lager-anteil beherbergen 
			super(new ownSelectFieldFuhrenArt2(), new ownHashMap(),  new MyE2_String("Fuhren-Art:"), new Integer(70));

		}
	}
	
//	private class ownSelectFieldFuhrenArt extends MyE2_SelectField
//	{
//		public ownSelectFieldFuhrenArt() throws myException
//		{
//			super();
//			
//			this.set_ListenInhalt(myCONST.ARRAY_FUHREN_TYP_DROPDOWN,false);
//			this.setWidth(new Extent(100));
//		}
//	}
	

	
	private class ownHashMap extends HashMap<String, String>
	{
/**
 * 		HM_FUHREN_TYP_STRECKE_LAGER_MIXED.put("0", "Strecke");
		HM_FUHREN_TYP_STRECKE_LAGER_MIXED.put("1", "IN-Lager");
		HM_FUHREN_TYP_STRECKE_LAGER_MIXED.put("2", "EX-Lager");
		HM_FUHREN_TYP_STRECKE_LAGER_MIXED.put("3", "MIXED");
		HM_FUHREN_TYP_STRECKE_LAGER_MIXED.put("4", "Lager-zu-Lager");

 */
		public ownHashMap()
		{
			super();
			this.put("-", "");
			this.put("0", "JT_VPOS_TPA_FUHRE."+RECORD_VPOS_TPA_FUHRE.FIELD__TYP_STRECKE_LAGER_MIXED+"=0");
			this.put("1", "JT_VPOS_TPA_FUHRE."+RECORD_VPOS_TPA_FUHRE.FIELD__TYP_STRECKE_LAGER_MIXED+"=1");
			this.put("2", "JT_VPOS_TPA_FUHRE."+RECORD_VPOS_TPA_FUHRE.FIELD__TYP_STRECKE_LAGER_MIXED+"=2");
			this.put("3", "JT_VPOS_TPA_FUHRE."+RECORD_VPOS_TPA_FUHRE.FIELD__TYP_STRECKE_LAGER_MIXED+"=3");
			this.put("4", "JT_VPOS_TPA_FUHRE."+RECORD_VPOS_TPA_FUHRE.FIELD__TYP_STRECKE_LAGER_MIXED+"=4");
			
			//die ermittlung der fuhren, die einen lager-lager-anteil besitzen
			String cID_Adr = bibALL.get_ID_ADRESS_MANDANT();
			String cWhereBlock =  
				"SELECT FU.ID_VPOS_TPA_FUHRE  FROM JT_VPOS_TPA_FUHRE FU "+ 
				" WHERE "+
				" ((SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE_ORT FUO  WHERE FUO.ID_MANDANT=1 AND  FUO.ID_ADRESSE="+cID_Adr+" AND NVL(FUO.DELETED,'N')='N' AND FUO.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE AND FUO.DEF_QUELLE_ZIEL='EK') "+
				" + "+
				" (CASE WHEN ID_ADRESSE_START="+cID_Adr+" THEN 1 ELSE 0 END))>0 "+
				" AND "+
				"  ((SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE_ORT FUO  WHERE FUO.ID_MANDANT=1 AND  FUO.ID_ADRESSE="+cID_Adr+" AND NVL(FUO.DELETED,'N')='N' AND FUO.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE AND FUO.DEF_QUELLE_ZIEL='VK') "+
				" + "+ 
				" (CASE WHEN ID_ADRESSE_ZIEL="+cID_Adr+" THEN 1 ELSE 0 END)) >0 ";
			
			this.put("99", "JT_VPOS_TPA_FUHRE."+RECORD_VPOS_TPA_FUHRE.FIELD__ID_VPOS_TPA_FUHRE+" IN ("+cWhereBlock+")");
		}
	}

	
	private class ownSelectFieldFuhrenArt2 extends MyE2_SelectField
	{

		public ownSelectFieldFuhrenArt2() throws myException
		{
			super();
			String[][] arr_FUHREN_TYP_DROPDOWN = {{"-", "-"},{"Strecke", "0"},{"IN-Lager","1"},{"EX-Lager","2"},{"MIXED","3"},{"REINE Lager-zu-Lager","4"},{"Anteilige Lager-zu-Lager","99"}};

			this.set_ListenInhalt(arr_FUHREN_TYP_DROPDOWN,false);
			this.setWidth(new Extent(100));

			
		}
		
	}
	
	
}
