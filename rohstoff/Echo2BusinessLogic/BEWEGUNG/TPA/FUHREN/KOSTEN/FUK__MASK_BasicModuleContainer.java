package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FUHREN_KOSTEN_TYP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SelectFieldFuhreKostenTyp;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SelectFieldFuhre_ZollAgenturen;

public class FUK__MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK
{

	/*
	 * variante 1 fuer den aufruf in der Fuhren-Maske, die ID-Fuhre wird hier injiziert
	 */
	public FUK__MASK_BasicModuleContainer(String cMODULKENNER, SQLFieldMAP oFieldMapMask) throws myException
	{
		super(cMODULKENNER);
		this.set_bVisible_Row_For_Messages(true);
		
		FUK__MASK_ComponentMAP oFUK__MASK_ComponentMAP = new FUK__MASK_ComponentMAP(oFieldMapMask);
		
		this.INIT(oFUK__MASK_ComponentMAP, new FUK__MASK(oFUK__MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	

	
	
	private class FUK__MASK_ComponentMAP extends E2_ComponentMAP 
	{
	
		public FUK__MASK_ComponentMAP(SQLFieldMAP  oFM) throws myException
		{
			super(oFM);
			

			BS__SelectFieldFuhreKostenTyp  			oSFKostenTyp = new BS__SelectFieldFuhreKostenTyp(oFM.get_SQLField(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_FUHREN_KOSTEN_TYP),200);
			BS__SelectFieldFuhre_ZollAgenturen   	oSFZollagentur = new BS__SelectFieldFuhre_ZollAgenturen(oFM.get_SQLField(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_ZOLLAGENTUR),200);
			
			oSFKostenTyp.add_oActionAgent(new actionSelectKostenTyp());
			
			//hier kommen die Felder	
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_VPOS_TPA_FUHRE")), 						new MyE2_String("ID-Fuhre"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_VPOS_TPA_FUHRE_KOSTEN")), 				new MyE2_String("ID-Kosten"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ANZAHL"),true,100,12,false), 			new MyE2_String("Anzahl"));
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_("BELEG_VORHANDEN")), 						new MyE2_String("Beleg vorhanden"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("BETRAG_KOSTEN"),true,120,10,false), 		new MyE2_String("Betrag"));
			this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("DATUM_BELEG")), 			new MyE2_String("Belegdatum"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("EINZELPREIS"),true,100,10,false), 		new MyE2_String("Einzelpreis"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("FREMDBELEG_NUMMER"),true,350,50,false), 	new MyE2_String("Fremdbeleg-Nummer"));
			this.add_Component(oSFKostenTyp, 															new MyE2_String("Kosten/Eintrag Typ"));
			this.add_Component(oSFZollagentur, 															new MyE2_String("Zollagentur"));
			
			//this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_ADRESSE_SPEDITION"),true,200,10,false), new MyE2_String("ID_ADRESSE_SPEDITION"));
			this.add_Component(new MyE2_DB_TextArea(oFM.get_("INFO_KOSTEN"),500,5), new MyE2_String("Infos"));
			this.add_Component(new CheckBoxPreisProTonne(oFM.get_("IST_PREIS_PRO_TONNE")), new MyE2_String("Preis pro t"));
			this.add_Component(new MyE2_DB_TextArea(oFM.get_("NAME_SPEDITION"),500,5), new MyE2_String("Spedition"));
			
			/*
	 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
			 */
			this.set_oMAPSettingAgent(new FUK__MASK_MapSettingAgent());
			
			/*
			 * ermoeglicht formatierungen von zusatzinfos in der maske
			 */
			this.set_oSubQueryAgent(new FUK__MASK_FORMATING_Agent());
			
			
			/*
			 * berechnungen beim speichern
			 */
			this.add_oMAPValidator(new ownValidatorBeforeSave());
			
		}
		
	}
	
	
	private class actionSelectKostenTyp extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BS__SelectFieldFuhreKostenTyp 	oSelectTyp = 	(BS__SelectFieldFuhreKostenTyp)oExecInfo.get_MyActionEvent().getSource();
			E2_ComponentMAP       			oMAP = 			oSelectTyp.EXT().get_oComponentMAP();

			RECORD_FUHREN_KOSTEN_TYP  recKostenTyp = null;
			if (S.isFull(oSelectTyp.get_ActualWert()))
			{
				recKostenTyp = new RECORD_FUHREN_KOSTEN_TYP(bibALL.ReplaceTeilString(oSelectTyp.get_ActualWert(),".",""));
			}
			
			if (recKostenTyp==null || recKostenTyp.is_BETRIFFT_ZOLL_NO())
			{
				oMAP.set_ActiveADHOC(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_ZOLLAGENTUR, 	false, true);
			}
			else
			{
				oMAP.set_ActiveADHOC(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_ZOLLAGENTUR, 	true, true);
			}
			
		}
	}
	
	
	
	
	//spezieller schalter fuer preis/t
	private class CheckBoxPreisProTonne extends MyE2_DB_CheckBox
	{

		public CheckBoxPreisProTonne(SQLField osqlField) throws myException
		{
			super(osqlField);
			this.add_oActionAgent(new ownActionAgent());
		}

		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				CheckBoxPreisProTonne oThis = (CheckBoxPreisProTonne)oExecInfo.get_MyActionEvent().getSource();
				E2_ComponentMAP       oMAP = oThis.EXT().get_oComponentMAP();
				
				if (oThis.isSelected())
				{
					oMAP.set_ActiveADHOC(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ANZAHL, 		true, true);
					oMAP.set_ActiveADHOC(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__EINZELPREIS, 	true, true);
					
					oMAP.set_ActiveADHOC(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__BETRAG_KOSTEN, false, true);
					oMAP.get__DBComp(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__BETRAG_KOSTEN).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(true);
					
					//in das feld FIELD__BETRAG_KOSTEN den wert 0 stellen, da sonst bei speichern das leere feld
				}
				else
				{
					oMAP.set_ActiveADHOC(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ANZAHL, 		false, true);
					oMAP.set_ActiveADHOC(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__EINZELPREIS, 	false, true);
					
					oMAP.set_ActiveADHOC(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__BETRAG_KOSTEN, true, true);
					oMAP.get__DBComp(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__BETRAG_KOSTEN).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(false);
				}
			}
		}
	}

	
	
	private class ownValidatorBeforeSave extends XX_MAP_ValidBeforeSAVE
	{

		@Override
		public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE)	throws myException
		{
			boolean bPreisProTonne = S.NN(oMap.get_cActualDBValueFormated(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__IST_PREIS_PRO_TONNE)).equals("Y");
			
			if (bPreisProTonne)
			{
				BigDecimal bdMenge = oMap.get_bdActualDBValue(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ANZAHL, BigDecimal.ZERO, BigDecimal.ZERO);
				BigDecimal bdEPreis = oMap.get_bdActualDBValue(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__EINZELPREIS, BigDecimal.ZERO, BigDecimal.ZERO);
				
				BigDecimal bdGesamt = bdMenge.multiply(bdEPreis);
				bdGesamt = bdGesamt.setScale(2, BigDecimal.ROUND_HALF_UP);
				
				oMap.set_cActualDatabaseValueToMask(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__BETRAG_KOSTEN,MyNumberFormater.formatDez(bdGesamt, 2, true));
				oInputMap.put(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__BETRAG_KOSTEN,MyNumberFormater.formatDez(bdGesamt, 2, true));
			}
			
			return new MyE2_MessageVector();
		}
		
	}

	
	
	
	private class FUK__MASK extends MyE2_Column  implements IF_BaseComponent4Mask	
	{
		private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
		
		public FUK__MASK(FUK__MASK_ComponentMAP oMap) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
		
			E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
	
			
			// 5 Grids
			MyE2_Grid oGrid0 = new MyE2_Grid(2,0);
			
		
			this.add(oGrid0, E2_INSETS.I_2_2_2_2);
			
			String cWaehrung = "<WAEHRUNG>";
			
			if (S.isFull(bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cUF()))
			{
				cWaehrung = new RECORD_WAEHRUNG(bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cUF()).get_WAEHRUNGSSYMBOL_cUF_NN("<WS>");
			}
			
			//hier kommen die Felder	
			oFiller.add_Line(oGrid0, new MyE2_String("Kostentyp: "), 1, 			"ID_FUHREN_KOSTEN_TYP|#  |#  ID-Fuhre:|ID_VPOS_TPA_FUHRE|#     |#   ID-Kosten:|ID_VPOS_TPA_FUHRE_KOSTEN",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Zollagentur:"), 1,	 		"ID_ZOLLAGENTUR|#  |",3);

			oFiller.add_Line(oGrid0, new MyE2_String("Preis/Tonne:"), 1,	 		"IST_PREIS_PRO_TONNE|#  |",3);
			
			oFiller.add_Line(oGrid0, new MyE2_String("Gesamtbetrag:"), 1, 			"BETRAG_KOSTEN|#  |#(=|ANZAHL|# x |EINZELPREIS|#"+cWaehrung+")",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Datum Beleg:"), 1, 			"DATUM_BELEG|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Fremdbeleg-Nummer:"), 1, 		"FREMDBELEG_NUMMER|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Information:"), 1, 			"INFO_KOSTEN|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Spedition:"), 1, 				"NAME_SPEDITION|#  |",3);
	
			oFiller.add_Line(oGrid0, new MyE2_String("Beleg liegt vor:"), 1, 		"BELEG_VORHANDEN|#  |",3);
			
			this.vMaskGrids.add(oGrid0);
			
		}

		@Override
		public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
		{
			return this.vMaskGrids;
		}
	
		
	}

	
	private class FUK__MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
	
		}
	
	}
	
	private class FUK__MASK_MapSettingAgent extends XX_MAP_SettingAgent {
		
		public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
		{
		
	
		}
	
		public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
		{
			SQLResultMAP oResult = oMap.get_oInternalSQLResultMAP();
			
			if (oResult==null)   //neueingabe
			{
				oMap.set_cActualDatabaseValueToMask(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__IST_PREIS_PRO_TONNE, "N");
				this.do_Disable(oMap, bibALL.get_Vector( 	RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ANZAHL, 
						 									RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__EINZELPREIS,
						 									RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_ZOLLAGENTUR), true);
				oMap.get__DBComp(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__BETRAG_KOSTEN).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(false);

			}
			else
			{
				boolean bPreisProTonne = oResult.get_UnFormatedValue(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__IST_PREIS_PRO_TONNE).equals("Y");
				
				if (bPreisProTonne)
				{
					this.do_Disable(oMap, bibALL.get_Vector(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ANZAHL, RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__EINZELPREIS), false);
					this.do_Disable(oMap, bibALL.get_Vector(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__BETRAG_KOSTEN), true);
					oMap.get__DBComp(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__BETRAG_KOSTEN).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(true);
				}
				else
				{
					this.do_Disable(oMap, bibALL.get_Vector(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ANZAHL, RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__EINZELPREIS), true);
					this.do_Disable(oMap, bibALL.get_Vector(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__BETRAG_KOSTEN), false);
					oMap.get__DBComp(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__BETRAG_KOSTEN).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(false);
				}
				
				
				String cSelect_ID_KostenTyp = 	oResult.get_UnFormatedValue(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_FUHREN_KOSTEN_TYP);
				RECORD_FUHREN_KOSTEN_TYP  recKostenTyp = null;
				if (S.isFull(cSelect_ID_KostenTyp))
				{
					recKostenTyp = new RECORD_FUHREN_KOSTEN_TYP(cSelect_ID_KostenTyp);
				}
				
				//das feld FIELD__ID_ZOLLAGENTUR ist nur aktiv bei zoll-belegen
				if (recKostenTyp==null || recKostenTyp.is_BETRIFFT_ZOLL_NO())
				{
					this.do_Disable(oMap, bibALL.get_Vector(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_ZOLLAGENTUR), true);
				}
				else
				{
					this.do_Disable(oMap, bibALL.get_Vector(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_ZOLLAGENTUR), false);
				}
				
			}
		}
		
		private void do_Disable(E2_ComponentMAP oMap,Vector<String> vFieldList,boolean bDisalbed) throws myException
		{
			for (String cField: vFieldList)
			{
				oMap.get__Comp(cField).EXT().set_bDisabledFromInteractive(bDisalbed);
			}
		}
	
	}
	
	



}
