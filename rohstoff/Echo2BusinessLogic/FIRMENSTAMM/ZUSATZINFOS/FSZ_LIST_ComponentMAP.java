package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS;

import java.util.Iterator;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_IF_Rec21;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_GenericListComponentShowAddonDocuments;
import panter.gmbh.Echo2.components.DB.E2_LabelCeateTimeStamp;
import panter.gmbh.Echo2.components.DB.E2_LabelCreateUser;
import panter.gmbh.Echo2.components.DB.E2_LabelLastChangeTimeStamp;
import panter.gmbh.Echo2.components.DB.E2_LabelLastChangeUser;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FSZ_LIST_ComponentMAP extends E2_ComponentMAP implements E2_ComponentMAP_IF_Rec21 {

	
	private boolean typInfos = false;
	
	
	
	
	public FSZ_LIST_ComponentMAP(boolean bInfos) throws myException	{
		super(new FSZ_LIST_SQLFieldMap_AdressInfo(bInfos));
		this.typInfos = bInfos;
		this.init();
	}

	public FSZ_LIST_ComponentMAP(boolean bInfos, SQLFieldMAP map) throws myException	{
		super(map);
		this.typInfos = bInfos;
		this.init();
	}

	
	private void init() throws myException {
		boolean isMessage = !this.typInfos;
		
		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
		
		SQLFieldMAP oSQL_FM_AI = this.get_oSQLFieldMAP();

		E2_DropDownSettings oDDValues1 = new E2_DropDownSettings("JT_BESUCHSERGEBNIS", "KURZBEZEICHNUNG", "ID_BESUCHSERGEBNIS", null, true);
	    E2_DropDownSettings oDDValues2 = new E2_DropDownSettings("JT_AKTIONSANLASS", "KURZBEZEICHNUNG", "ID_AKTIONSANLASS", null, true);
	    
	    
	    //2010-12-27: dropdrown fuer messagetypen
	    String[][] cDefArray = new String[myCONST.HM_ADRESS_INFO_TYP.size()+1][2];
	    Iterator<String> iterator = myCONST.HM_ADRESS_INFO_TYP.keySet().iterator();
	    
	    cDefArray[0][0]="-";
	    cDefArray[0][1]="";
	    int iCount = 1;
	    while (iterator.hasNext())
	    {
	    	String cKey = iterator.next();
	    	cDefArray[iCount][1]=cKey;
	    	cDefArray[iCount][0]=myCONST.HM_ADRESS_INFO_TYP.get(cKey);
	    	iCount++;
	    }
	    
	    
	    MyE2_DB_SelectField  oSelMessageTyp = new MyE2_DB_SelectField(oSQL_FM_AI.get_SQLField("MESSAGE_TYP"), cDefArray, true);
	    
	    
	    // col1
		MyE2_DB_TextField			oTF_DatumEintrag = 			new MyE2_DB_TextField(oSQL_FM_AI.get_SQLField("DATUMEINTRAG"));
		MyE2_DB_TextField			oTF_Kuerzel = 				new MyE2_DB_TextField(oSQL_FM_AI.get_SQLField("KUERZEL"));
		MyE2_DB_SelectField			oSelectAnlass	=			new MyE2_DB_SelectField(oSQL_FM_AI.get_("ID_AKTIONSANLASS"),oDDValues2.getDD(),false);

		// col2
		MyE2_DB_TextField			oTF_DatumEreignis = 		new MyE2_DB_TextField(oSQL_FM_AI.get_SQLField("DATUMEREIGNIS"));
		MyE2_DB_TextField			oTF_FolgeDatum = 			new MyE2_DB_TextField(oSQL_FM_AI.get_SQLField("FOLGEDATUM"));		
		MyE2_DB_CheckBox			oCb_WiederholMonat = 		new MyE2_DB_CheckBox(oSQL_FM_AI.get_SQLField("WIEDERHOLUNGMONATLICH"));
		MyE2_DB_CheckBox			oCb_WiederholJahr = 		new MyE2_DB_CheckBox(oSQL_FM_AI.get_SQLField("WIEDERHOLUNGJAEHRLICH"));
		
		MyE2_DB_TextArea			oTA_InfoText = 				new MyE2_DB_TextArea(oSQL_FM_AI.get_SQLField("TEXT"));
		
		//col3
		MyE2_DB_SelectField			oSelectErgebnis1	=		new MyE2_DB_SelectField(oSQL_FM_AI.get_("ID_BESUCHSERGEBNIS1"),oDDValues1.getDD(),false);
		MyE2_DB_SelectField			oSelectErgebnis2	=		new MyE2_DB_SelectField(oSQL_FM_AI.get_("ID_BESUCHSERGEBNIS2"),oDDValues1.getDD(),false);
		MyE2_DB_SelectField			oSelectErgebnis3	=		new MyE2_DB_SelectField(oSQL_FM_AI.get_("ID_BESUCHSERGEBNIS3"),oDDValues1.getDD(),false);
		
	    MyE2_DB_MultiComponentColumn oCol1 = new MyE2_DB_MultiComponentColumn();
	    MyE2_DB_MultiComponentColumn oCol2 = new MyE2_DB_MultiComponentColumn();
	    MyE2_DB_MultiComponentColumn oCol3 = new MyE2_DB_MultiComponentColumn();
	    MyE2_DB_MultiComponentColumn oCol4 = new MyE2_DB_MultiComponentColumn();
		
	    MyE2_DB_MultiComponentColumn oCol5 = new MyE2_DB_MultiComponentColumn();
	    	
	    oCol1.add_Component(oTF_DatumEintrag,	new MyE2_String("Erfassung am"),null);
	    oCol1.add_Component(oTF_Kuerzel,		new MyE2_String("Erfassung von"),null);
	    oCol1.add_Component(oSelectAnlass,		new MyE2_String("Anlass der Info"),null);
	    
	    oCol2.add_Component(oTF_DatumEreignis,	new MyE2_String("Datum Ereignis"),null);
	    oCol2.add_Component(oTF_FolgeDatum,		new MyE2_String("Wiedervorlage"),null);
	    oCol2.add_Component(oCb_WiederholMonat,	new MyE2_String("WV Monatlich"),null);
	    oCol2.add_Component(oCb_WiederholJahr,	new MyE2_String("WV Jährlich"),null);
	    oCol2.add_Component(new FSZ_LIST_BT_AdoHocMeldung_NG(),	new MyE2_String("--senden--"),"HASH_MELDUNG_SENDEN");
		
	    oCol3.add_Component(oSelectErgebnis1, 	new MyE2_String("Ergebnis 1"),null);
	    oCol3.add_Component(oSelectErgebnis2, 	new MyE2_String("Ergebnis 2"),null);
	    oCol3.add_Component(oSelectErgebnis3, 	new MyE2_String("Ergebnis 3"),null);

	    
	    E2_FontPlain  oFont = new E2_FontPlain(-2);
	    oCol4.add_Component(new DB_Component_USER_DROPDOWN_NEW(oSQL_FM_AI.get_("ID_USER"),true,150,oFont), new MyE2_String("Betreuer"),null);
	    oCol4.add_Component(new DB_Component_USER_DROPDOWN_NEW(oSQL_FM_AI.get_("ID_USER_ERSATZ"),true,150,oFont), new MyE2_String("Betreuer(2)"),null);
	    oCol4.add_Component(new DB_Component_USER_DROPDOWN_NEW(oSQL_FM_AI.get_("ID_USER_SACHBEARBEITER"),true,150,oFont), new MyE2_String("Sachbearbeiter"),null);
	    

	    
	    /*
	     * breite festlegen
	     */
	    oTF_DatumEintrag.set_iWidthPixel(100);
		oTF_DatumEreignis.set_iWidthPixel(100);
		oTF_FolgeDatum.set_iWidthPixel(100);
				
		oTA_InfoText.set_iWidthPixel(520);
		oTA_InfoText.set_iRows(6);
		oTA_InfoText.EXT_DB().set_bIsSortable(false);
		
		oSelectAnlass.setWidth(new Extent(100));
		oSelectErgebnis1.setWidth(new Extent(100));
		oSelectErgebnis2.setWidth(new Extent(100));
		oSelectErgebnis3.setWidth(new Extent(100));

		if (this.typInfos) {
			this.add_Component("HASHKEY_1",oCol1,new MyE2_String("Erfassung/Kürzel/Anlass"));		
			this.add_Component("HASHKEY_2",oCol2,new MyE2_String("Datum Ereignis/Wiedervorlage"));
			this.add_Component("HASHKEY_4",oCol4,new MyE2_String("Nachricht-Empfänger"));
		}
		
		this.add_Component(oTA_InfoText,new MyE2_String("Wortlaut der Information"));
		
		
		if (this.typInfos)  {
			this.add_Component("HASHKEY_3",oCol3,new MyE2_String("Ergebnisse"));
		}
		
		this.add_Component(new MyE2_DB_Label(oSQL_FM_AI.get_("ID_ADRESSE_INFO")), new MyE2_String("ID(Adr.Info)"));
		
		oCol5.add_Component(oSelMessageTyp, new MyE2_String("Meldungstyp"),null);
		oCol5.add_Component(new FSZ_HelpButton_Erklaerung_wann_was_angezeigt_wird(), new MyE2_String("--?--"),"HASHKEY_B1");
		
		if (isMessage) {
			this.add_Component("HASHKEY_5",	oCol5,			new MyE2_String("Meldungstyp"));
			this.add_Component(new MyE2_DB_CheckBox(oSQL_FM_AI.get_(ADRESSE_INFO.aktiv)), 		S.ms("Aktiv"));
			this.add_Component("HASHKEY_6", new E2_LabelLastChangeUser()._setSortTerms(ADRESSE_INFO.geaendert_von.tnfn()+" ASC ", ADRESSE_INFO.geaendert_von.tnfn()+" DESC ", 				S.ms("Änderung von")), 		S.ms("Änderung"));
			this.add_Component("HASHKEY_7", new E2_LabelLastChangeTimeStamp()._setSortTerms(ADRESSE_INFO.letzte_aenderung.tnfn()+" ASC ", ADRESSE_INFO.letzte_aenderung.tnfn()+" DESC ", 	S.ms("Änderung Dat.")), 	S.ms("Änder.Datum"));
			this.add_Component("HASHKEY_8", new E2_LabelCreateUser()._setSortTerms(ADRESSE_INFO.erzeugt_von.tnfn()+" ASC ", ADRESSE_INFO.erzeugt_von.tnfn()+" DESC ",						S.ms("Erstellt von")), 	S.ms("Erzeugt."));
			this.add_Component("HASHKEY_9", new E2_LabelCeateTimeStamp()._setSortTerms(ADRESSE_INFO.erzeugt_am.tnfn()+" ASC ", ADRESSE_INFO.erzeugt_am.tnfn()+" DESC ", 					S.ms("Erstell.Dat.")), 	S.ms("Erz. Datum"));
			
			//this.add_Component("HASHKEY_5",oCol5,new MyE2_String("Meldungstyp"));
		}
		
		//2015-09-02: anzeige der upload-dokumente direkt in der liste
		int[] breit = {100,15}; 
		this.add_Component(E2_GenericListComponentShowAddonDocuments.LISTKEY4COMPONENTMAP,
				new E2_GenericListComponentShowAddonDocuments(breit), E2_GenericListComponentShowAddonDocuments.LISTINFO4COMPONENTMAP);

		
		
		//2011-11-17: meldungen sollten immer sofort aufgehen
	// 	if (!bInfos) this.add_Component(new MyE2_DB_CheckBox(oSQL_FM_AI.get_("MESSAGE_SOFORT")), new MyE2_String("Sofort!"));
		
		((MyE2_DB_Label)this.get("ID_ADRESSE_INFO")).EXT().set_bIsVisibleInList(false);

	}
	
	/**
	 * 
	 */
	@Override
	public  FSZ_LIST_ComponentMAP get_Copy(Object objHelp) throws myExceptionCopy 	{
		try {
			FSZ_LIST_ComponentMAP oRueck = new FSZ_LIST_ComponentMAP(this.typInfos,this.get_oSQLFieldMAP());
			return oRueck;
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
}
