package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


//NEU_09   ---  geschenktabelle bei mitarbeitern
public class FSM_MASK_Component_DAUGHTER_GESCHENKE extends MyE2_DBC_DaughterTable
{

	public FSM_MASK_Component_DAUGHTER_GESCHENKE(		SQLFieldMAP 			fieldMAPMotherTable, 
														E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldMap_ADRESSE_GESCHENK = new SQLFieldMAP("JT_ADRESSE_GESCHENK",bibE2.get_CurrSession());
		oSQLFieldMap_ADRESSE_GESCHENK.addCompleteTable_FIELDLIST("JT_ADRESSE_GESCHENK",":ID_ADRESSE_GESCHENK:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMap_ADRESSE_GESCHENK.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE_GESCHENK","ID_ADRESSE_GESCHENK","ID_ADRESSE_GESCHENK",new MyE2_String("ID-Adresse-Geschenk"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ADRESSE_GESCHENK.NEXTVAL FROM DUAL",true),false);

		oSQLFieldMap_ADRESSE_GESCHENK.add_SQLField(new SQLFieldJoinOutside("JT_ADRESSE_GESCHENK","ID_ADRESSE","ID_ADRESSE",
											new MyE2_String("ID-Adresse"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_ADRESSE")),false);

		oSQLFieldMap_ADRESSE_GESCHENK.get_("DATUMGESCHENK").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		oSQLFieldMap_ADRESSE_GESCHENK.get_("AKTIV").set_cDefaultValueFormated("Y");

		
		RECORD_MANDANT  recMandant = new RECORD_MANDANT(bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF());
		
		RECLIST_MANDANT_ZUSATZ  recListZusatz = recMandant.get_DOWN_RECORD_LIST_MANDANT_ZUSATZ_id_mandant("NVL(FIELDNAME,'')='VORGABEWERT_MITARBEITERGESCHENK'",null,true);

		//falls im mandanten ein zusatzfeld namens VORGABEWERT_MITARBEITERGESCHENK vorhanden ist, dann diese laden
		if (recListZusatz.get_vKeyValues().size()==1)
		{
			oSQLFieldMap_ADRESSE_GESCHENK.get_("GESCHENK").set_cDefaultValueFormated(recListZusatz.get(0).get_TEXT_VALUE_cUF_NN(""));
		}
		
		
		oSQLFieldMap_ADRESSE_GESCHENK.initFields();
		
		
		E2_ComponentMAP 			oMapGeschenk = 		new E2_ComponentMAP(oSQLFieldMap_ADRESSE_GESCHENK);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		
		MyE2_DB_TextField			oTF_DatumGeschenk = 		new MyE2_DB_TextField(oSQLFieldMap_ADRESSE_GESCHENK.get_SQLField("DATUMGESCHENK"));
		MyE2_DB_TextField			oTF_GESCHENK = 				new MyE2_DB_TextField(oSQLFieldMap_ADRESSE_GESCHENK.get_SQLField("GESCHENK"));
		MyE2_DB_TextField			oTF_WERT = 					new MyE2_DB_TextField(oSQLFieldMap_ADRESSE_GESCHENK.get_SQLField("WERT"));
		
		MyE2_DB_CheckBox            oCB_Kalender =   		 	new MyE2_DB_CheckBox(oSQLFieldMap_ADRESSE_GESCHENK.get_SQLField("GESCHENK_KALENDER"));
		MyE2_DB_CheckBox            oCB_Wein =   				new MyE2_DB_CheckBox(oSQLFieldMap_ADRESSE_GESCHENK.get_SQLField("GESCHENK_WEIN"));
		MyE2_DB_CheckBox            oCB_Sekt =   		 		new MyE2_DB_CheckBox(oSQLFieldMap_ADRESSE_GESCHENK.get_SQLField("GESCHENK_SEKT"));
		MyE2_DB_CheckBox            oCB_Spargel =   		 	new MyE2_DB_CheckBox(oSQLFieldMap_ADRESSE_GESCHENK.get_SQLField(_DB.ADRESSE_GESCHENK$GESCHENK_SPARGEL));
		
//		MyE2_DB_CheckBox            oCB_WeihnachtsGeschenk =    new MyE2_DB_CheckBox(oSQLFieldMap_ADRESSE_GESCHENK.get_SQLField("WEIHNACHTSGESCHENK"));
//		MyE2_DB_CheckBox            oCB_SommerGeschenk =   		 new MyE2_DB_CheckBox(oSQLFieldMap_ADRESSE_GESCHENK.get_SQLField("SOMMERGESCHENK"));
		
		MyE2_DB_CheckBox            oCB_AKTIV =   				new ownAktivCheckBox(oSQLFieldMap_ADRESSE_GESCHENK.get_SQLField("AKTIV"));

		oTF_DatumGeschenk.set_iWidthPixel(100);
		oTF_DatumGeschenk.EXT().set_oColExtent(new Extent(110));

		oTF_GESCHENK.set_iWidthPixel(300);
		oTF_GESCHENK.EXT().set_oColExtent(new Extent(310));
		
		oTF_WERT.set_iWidthPixel(100);
		oTF_WERT.EXT().set_oColExtent(new Extent(110));

//		oCB_WeihnachtsGeschenk.setToolTipText(new MyE2_String("Weihnachtsgeschenk ?").CTrans());
//		oCB_SommerGeschenk.setToolTipText(new MyE2_String("Sommergeschenk ?").CTrans());
		
		
		oMapGeschenk.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapGeschenk.add_Component(oTF_GESCHENK,new MyE2_String("Beschreibung Geschenk"));

		oMapGeschenk.add_Component(oCB_Kalender,new MyE2_String("Kalender"));
		oMapGeschenk.add_Component(oCB_Wein,new MyE2_String("Wein"));
		oMapGeschenk.add_Component(oCB_Sekt,new MyE2_String("Sekt"));
		oMapGeschenk.add_Component(oCB_Spargel,new MyE2_String("Spargel"));

		//2014-02-20: weihnachtsgeschenk/sommergeschenk nicht mehr noetig
		//oMapGeschenk.add_Component(oCB_WeihnachtsGeschenk,new MyE2_String("WG"));
		//oMapGeschenk.add_Component(oCB_SommerGeschenk,new MyE2_String("SG"));
		
		oMapGeschenk.add_Component(oTF_DatumGeschenk,new MyE2_String("Datum"));
		oMapGeschenk.add_Component(oTF_WERT,new MyE2_String("Wert (EUR)"));
		oMapGeschenk.add_Component(oCB_AKTIV,new MyE2_String("Aktiv ?"));

		oMapGeschenk.get__Comp(_DB.MITARBEITER$GESCHENK_KALENDER).EXT().set_oColExtent(new Extent(60));
		oMapGeschenk.get__Comp(_DB.MITARBEITER$GESCHENK_WEIN).EXT().set_oColExtent(new Extent(60));
		oMapGeschenk.get__Comp(_DB.MITARBEITER$GESCHENK_SEKT).EXT().set_oColExtent(new Extent(60));
		oMapGeschenk.get__Comp(_DB.MITARBEITER$GESCHENK_SPARGEL).EXT().set_oColExtent(new Extent(60));
		
		
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_oContainerExScrollHeight(new Extent(200));
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapGeschenk,
							null);
		
	}

	
	
	private class ownAktivCheckBox extends MyE2_DB_CheckBox
	{

		private SQLField 	sqlField = null;;
		
		public ownAktivCheckBox(SQLField osqlField) throws myException
		{
			super(osqlField);
			this.sqlField 		= osqlField;
			this.setText(new MyE2_String("Aktiv").CTrans());
			
			this.add_oActionAgent(new ownActionAgent());
			this.setToolTipText(new MyE2_String("Die Geschenk-Häkchen entfernen ...").CTrans());
		}
		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				ownAktivCheckBox oThis = ownAktivCheckBox.this;
				
				
				try
				{
					if (!oThis.isSelected())
					{
						E2_ComponentMAP oMapInList = oThis.EXT().get_oComponentMAP();
						((MyE2_DB_CheckBox)oMapInList.get__Comp("GESCHENK_KALENDER")).setSelected(false);
						((MyE2_DB_CheckBox)oMapInList.get__Comp("GESCHENK_WEIN")).setSelected(false);
						((MyE2_DB_CheckBox)oMapInList.get__Comp("GESCHENK_SEKT")).setSelected(false);
						((MyE2_DB_CheckBox)oMapInList.get__Comp("WEIHNACHTSGESCHENK")).setSelected(false);
						((MyE2_DB_CheckBox)oMapInList.get__Comp("SOMMERGESCHENK")).setSelected(false);
					}
				} 
				catch (myException e)
				{
					e.printStackTrace();
				}
			}
		}
	
		
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			ownAktivCheckBox oRueck=null;
			try
			{
				oRueck = new ownAktivCheckBox(this.sqlField);
			} catch (myException e)
			{
				
				throw new myExceptionCopy(e.get_ErrorMessage().get_cMessage().COrig());
			}
			return oRueck;
		}

		
		
	}
	
}
