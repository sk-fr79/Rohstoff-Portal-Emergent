
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_LIST_comp_anlagen_email;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_INFO;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class ADI_LIST_ComponentMap extends E2_ComponentMAP 
{
	public ADI_LIST_ComponentMap(RECORD_ADRESSE rec_adresse, E2_NavigationList oNaviList) throws myException
	{
		super(new ADI_LIST_SqlFieldMAP(rec_adresse));

		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();

		this.add_Component(ADI_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),	new MyE2_String("?"));
		this.add_Component(ADI_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),	new MyE2_String("?"));
		
		this.add_Component(ADI_CONST.ADI_NAMES.ANLAGE_COMP.db_val(),  									
				new E2_LIST_comp_anlagen_email(oNaviList), 	
				new MyE2_String("A/E"),	
				true,new MyE2_String("Zeige Anlagen- und eMail-Popup"),null,null);							

		MyE2_DB_MultiComponentColumn datumColumn = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn usersColumn = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn visitorColumn = new MyE2_DB_MultiComponentColumn();
		
		//hier kommen die Felder  
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ADRESSE_INFO.datumeintrag),true),     		new MyE2_String("Erfassung am"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ADRESSE_INFO.kuerzel),true),     			new MyE2_String("Erfassung von"));

		this.add_Component(new ADI_MASK_SelectField_Anlass(ADRESSE_INFO.id_aktionsanlass),     	new MyE2_String("Anlass der Info"));

		datumColumn.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ADRESSE_INFO.datumereignis),true),     	new MyE2_String("Datum Ereignis"), 	null);
		datumColumn.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ADRESSE_INFO.folgedatum),true),     	new MyE2_String("Wiedervorlage"), 	null);
		datumColumn.add_Component(new RB_CheckBox(ADRESSE_INFO.wiederholungmonatlich),     					new MyE2_String("WV Monatlich"), 	null);
		datumColumn.add_Component(new RB_CheckBox(ADRESSE_INFO.wiederholungjaehrlich),     					new MyE2_String("WV Jährlich"), 	null);
		datumColumn.add_Component(new ADI_LIST_ButtonInList_Nachrichten(), 									new MyE2_String("--Senden--"), 		"HASH_MELDUNG_SENDEN");
		this.add_Component("HASHKEY_1", datumColumn, new MyE2_String("Datum Ereignis/Wiedervorlage/WV monatlich/WV jährlich"));

		usersColumn.add_Component(new ADI_MASK_SelectField_Betreuer(ADRESSE_INFO.id_user),     				new MyE2_String("Betreuer"), 		null);
		usersColumn.add_Component(new ADI_MASK_SelectField_Betreuer(ADRESSE_INFO.id_user_ersatz),     		new MyE2_String("Betreuer(2)"), 	null);
		usersColumn.add_Component(new ADI_MASK_SelectField_Betreuer(ADRESSE_INFO.id_user_sachbearbeiter),   new MyE2_String("Sachbearbeiter"), 	null);
		this.add_Component("HASHKEY_2", usersColumn, new MyE2_String("Betreuer/Betreuer(2)/Sachbearbeiter"));
		
		ADI_MASK_SelectField_BesucherErgebnis besuchErg1 = new ADI_MASK_SelectField_BesucherErgebnis(ADRESSE_INFO.id_besuchsergebnis1);
		besuchErg1.setWidth(new Extent(125));
		
		ADI_MASK_SelectField_BesucherErgebnis besuchErg2 = new ADI_MASK_SelectField_BesucherErgebnis(ADRESSE_INFO.id_besuchsergebnis2);
		besuchErg2.setWidth(new Extent(125));
				
		ADI_MASK_SelectField_BesucherErgebnis besuchErg3 = new ADI_MASK_SelectField_BesucherErgebnis(ADRESSE_INFO.id_besuchsergebnis3);
		besuchErg3.setWidth(new Extent(125));

		visitorColumn.add_Component(besuchErg1, new MyE2_String("Ergebnis 1"), null);
		visitorColumn.add_Component(besuchErg2, new MyE2_String("Ergebnis 2"), null);
		visitorColumn.add_Component(besuchErg3, new MyE2_String("Ergebnis 3"), null);
		this.add_Component("HASHKEY_3", visitorColumn, new MyE2_String("Besuchersergebnis"));
		
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ADRESSE_INFO.text),true),     				new MyE2_String("Wortlaut der Information"));

		this.set_Factory4Records(new factory4Records());
	}


	private class factory4Records extends E2_ComponentMAP_Factory4Records {
		@Override
		public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
			return new RECORD_ADRESSE_INFO(cID_MAINTABLE);
		}

	}


}

