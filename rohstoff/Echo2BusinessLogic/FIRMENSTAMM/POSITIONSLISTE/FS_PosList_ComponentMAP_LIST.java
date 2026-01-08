package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.POSITIONSLISTE;

import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class FS_PosList_ComponentMAP_LIST extends E2_ComponentMAP {
	
	private FS_PosList_SQLFieldMap oFM = null;
	
	public FS_PosList_ComponentMAP_LIST() throws myException {
		super(new FS_PosList_SQLFieldMap());
		
		this.oFM = (FS_PosList_SQLFieldMap) this.get_oSQLFieldMAP();
		
		
		GridLayoutData  oColLayRight = LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I(3,0,3,0));
		GridLayoutData  oColLayRightTitel = LayoutDataFactory.get_GridLayoutGridRight_DARK_TOP(E2_INSETS.I(3,0,3,0));
		GridLayoutData  oColLayCenter = LayoutDataFactory.get_GridLayoutGridCenter_TOP(E2_INSETS.I(3,0,3,0));
		GridLayoutData  oColLayCenterTitel = LayoutDataFactory.get_GridLayoutGridCenter_DARK_TOP(E2_INSETS.I(3,0,3,0));
		
		
		MyE2_String cTipMenge = new MyE2_String("Menge ohne Abzüge");
		MyE2_String cTipMgAbzug = new MyE2_String("Mengenabzug (absolut und prozentual)");
		MyE2_String cGesamtbetrag = new MyE2_String("Gesamtbetrag ohne Abzüge in der Kundenwährung/Fremdwährung");
		MyE2_String cGesamtAbzug = new MyE2_String("Abzugsbetrag basierend auf allen Abzügen in der Kundenwährung/Fremdwährung (Menge/Preis absolut und prozentual)");
		MyE2_String cZahlBetrag = new MyE2_String("Resultierender Zahlbetrag (Gesamtbetrag minus Abzüge) in der Kundenwährung/Fremdwährung");
		MyE2_String cFibu = new MyE2_String("Wenn markiert, dann wurde die Position in der Fibu bereits geschlossen, damit ist keine manuelle Zahlungserfassung mehr nötig");
		MyE2_String cZAHLUNG_KONTROLLE = new MyE2_String("Manuell erfaßte Zahlung zu dieser Position");
		
		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),			new MyE2_String("?"));
		this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),			new MyE2_String("?"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WE_WA")),						new MyE2_String("WE/WA"),true,true,new MyE2_String("Unterscheidung zwischen Einkaufs- und Verkaufspositionen"),oColLayCenterTitel,oColLayCenter);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANR1_ANR2")),					new MyE2_String("Anr 1-2"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTBEZ1")),					new MyE2_String("Artikelbezeichnung 1"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("C_ANZAHL")),					new MyE2_String("Menge"),			true,true,cTipMenge,oColLayRightTitel,oColLayRight);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.VPOS_RG$EINHEITKURZ)),		new MyE2_String("Eh."),				true,true,cTipMenge,oColLayRightTitel,oColLayRight);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("C_ANZAHL_ABZUG")),				new MyE2_String("M.Abzug"),			true,true,cTipMgAbzug,oColLayRightTitel,oColLayRight);            
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.VPOS_RG$EINZELPREIS_FW)),	new MyE2_String("Einzel"),			true,true,cTipMgAbzug,oColLayRightTitel,oColLayRight);            
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GESAMTPREIS_FW")),				new MyE2_String("G-Preis"),			true,true,cGesamtbetrag,oColLayRightTitel,oColLayRight);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GESAMTPREIS_ABZUG_FW")),		new MyE2_String("(-)"),				true,true,cGesamtAbzug,oColLayRightTitel,oColLayRight);           
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ZAHLBETRAG_FW")),				new MyE2_String("Zahlbetrag"),		true,true,cZahlBetrag,oColLayRightTitel,oColLayRight);           
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WAEHRUNGSSYMBOL")),			new MyE2_String("W"),				true,true,new MyE2_String("Währung des Kunden in der Position"),oColLayCenterTitel,oColLayCenter);
		
		
		this.add_Component(FS_PosList_CONST.HASHKEY_LIST_BT_WERTSCHREIBEN, new FS_PosList_BT_Uebernahme(), new MyE2_String("OK?"),true,oColLayCenterTitel,oColLayCenter);
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.VPOS_RG$ZAHLUNG_KONTROLLE),true,100),	new MyE2_String("Zahlbetrag Manuell"),	true,true,cZAHLUNG_KONTROLLE,oColLayRightTitel,oColLayRight);
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("F_"+_DB.FIBU$BUCHUNG_GESCHLOSSEN),true),	new MyE2_String("FIBU"),	true,true,cFibu,oColLayRightTitel,oColLayRight);
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.VPOS_RG$AUSFUEHRUNGSDATUM)),	new MyE2_String("L.Datum"),	true,true,new MyE2_String("Leistungsdatum"),oColLayRightTitel,oColLayRight);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.VPOS_RG$ZAHLUNGSBED_CALC_DATUM)),	new MyE2_String("Fällig"),	true,true,new MyE2_String("Wenn Rechnung gedruckt wurde, dann Fälligkeitsdatum"),oColLayRightTitel,oColLayRight);
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("K_BUCHUNGSNUMMER")),			new MyE2_String("Buchungsnummer"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LIEFERBEDINGUNGEN")),			new MyE2_String("Lieferbed."));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ZAHLUNGSBEDINGUNGEN")),		new MyE2_String("Zahlungsbed."));
	
		this.set_oMAPSettingAgent(new ownMapSetter());
		
	}
	

	
	
	private class ownMapSetter extends XX_MAP_SettingAgent {

		@Override
		public void __doSettings_BEFORE(E2_ComponentMAP oMap,String STATUS_MASKE) throws myException {

			oMap.get__Comp(FS_PosList_CONST.HASHKEY_LIST_BT_WERTSCHREIBEN).EXT().set_bDisabledFromBasic(false);
			if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT)) {
				//reset
				oMap.get__Comp(_DB.VPOS_RG$ZAHLUNG_KONTROLLE).EXT().set_bDisabledFromBasic(false);
				//uebernahmeknopf ist im edit-modus inaktiv
				oMap.get__Comp(FS_PosList_CONST.HASHKEY_LIST_BT_WERTSCHREIBEN).EXT().set_bDisabledFromBasic(true);
				
				if (oMap.get_oInternalSQLResultMAP().get_booleanActualValue("F_"+_DB.FIBU$BUCHUNG_GESCHLOSSEN)) {
					oMap.get__Comp(_DB.VPOS_RG$ZAHLUNG_KONTROLLE).EXT().set_bDisabledFromBasic(true);
					oMap.get__Comp(_DB.VPOS_RG$ZAHLUNG_KONTROLLE).set_bEnabled_For_Edit(false);
				} 
			}
		}

		@Override
		public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException {
			
		}
		
	}
	
}
