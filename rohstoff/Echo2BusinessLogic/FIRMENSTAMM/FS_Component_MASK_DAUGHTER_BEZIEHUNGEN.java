package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class FS_Component_MASK_DAUGHTER_BEZIEHUNGEN extends MyE2_DBC_DaughterTable
{

	public FS_Component_MASK_DAUGHTER_BEZIEHUNGEN(	SQLFieldMAP 			fieldMAPMotherTable, 
													E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldMapEMAIL = new SQLFieldMAP("JT_BEZIEHUNG",bibE2.get_CurrSession());
		oSQLFieldMapEMAIL.addCompleteTable_FIELDLIST("JT_BEZIEHUNG",":ID_BEZIEHUNG:ID_ADRESSE_1:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMapEMAIL.add_SQLField(new SQLFieldForPrimaryKey("JT_BEZIEHUNG","ID_BEZIEHUNG","ID_BEZIEHUNG",new MyE2_String("ID-EMAIL"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_BEZIEHUNG.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMapEMAIL.add_SQLField(new SQLFieldJoinOutside("JT_BEZIEHUNG","ID_ADRESSE_1","ID_ADRESSE_1",
											new MyE2_String("ID-Adresse"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_ADRESSE")), false);

		oSQLFieldMapEMAIL.get_("ID_ADRESSE_2").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMapEMAIL.get_("ID_BEZIEHUNGSDEF").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		oSQLFieldMapEMAIL.initFields();
		
		E2_DropDownSettings oDDValues = new E2_DropDownSettings("JT_BEZIEHUNGSDEF", "KURZBEZEICHNUNG", "ID_BEZIEHUNGSDEF", null, true);
		
		E2_ComponentMAP 			oMapBeziehungen = new E2_ComponentMAP(oSQLFieldMapEMAIL);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		
		MyE2_DB_TextArea			oTF_Bemerkung = 		new MyE2_DB_TextArea(oSQLFieldMapEMAIL.get_SQLField("BEMERKUNG"));
		MyE2_DB_SelectField			oSelectDef	=			new MyE2_DB_SelectField(oSQLFieldMapEMAIL.get_("ID_BEZIEHUNGSDEF"),oDDValues.getDD(),false);
		DB_SEARCH_Adress			oSearchAdress = 		new DB_SEARCH_Adress(oSQLFieldMapEMAIL.get_("ID_ADRESSE_2"));
		
		oTF_Bemerkung.set_iWidthPixel(350);
		oTF_Bemerkung.set_iRows(4);
		oSelectDef.setWidth(new Extent(150));

		oTF_Bemerkung.EXT().set_oColExtent(new Extent(350));
		oSelectDef.EXT().set_oColExtent(new Extent(150));
	
		
		oTF_Bemerkung.EXT_DB().set_bIsSortable(false);
		oSelectDef.EXT_DB().set_bIsSortable(false);
		oSearchAdress.EXT_DB().set_bIsSortable(false);
		
		oMapBeziehungen.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapBeziehungen.add_Component(oSelectDef,new MyE2_String("Art der Beziehung"));
		oMapBeziehungen.add_Component(oSearchAdress,new MyE2_String("Adress-Suche"));
		oMapBeziehungen.add_Component(oTF_Bemerkung,new MyE2_String("Bemerkung"));
	
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		oMapBeziehungen.add_oMAPValidator(new ValidBeziehung(ocomponentMAP_from_Mother));
		
		//this.set_oContainerExScrollHeight(new Extent(530));
		this.set_to_100_percent();
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapBeziehungen,
							null);
		
	}


	
	/*
	 * validierer, sorgt dafuer, dass keine adresse auf sich selbst abgebildet werden kann
	 */
	private class ValidBeziehung extends XX_MAP_ValidBeforeSAVE
	{
		private E2_ComponentMAP oComponentMAP_Mother = null;                 // map der maske
		
		public ValidBeziehung(E2_ComponentMAP ComponentMapMother)
		{
			super();
			oComponentMAP_Mother = ComponentMapMother;
		}


		public MyE2_MessageVector _doValidation( E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE)
		{
			/*
			 * pruefen, ob beide adress-ids gleich sind, das darf nicht sein !!
			 * Dies muss nur bei einem EDIT-vorgang sein, da bei einem neueingabevorgang
			 * noch gar keine id_adresse existiert, die kollidieren koennte 
			 */
			MyE2_MessageVector vRueck = new MyE2_MessageVector();
			
			if (this.oComponentMAP_Mother.get_oInternalSQLResultMAP() != null)
			{
				String cID_MotherAdress = this.oComponentMAP_Mother.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				
				/*
				 * id ohne leerzeichen
				 */
				String cADRESS_Beziehung = bibALL.ReplaceTeilString((String)oInputMap.get("ID_ADRESSE_2"),".","");
				
				if (cID_MotherAdress.trim().equals(cADRESS_Beziehung.trim()))
				{
					vRueck.add_MESSAGE(new MyE2_Alarm_Message("Eine Adresse darf nicht auf sich selbst bezogen sein !!"), false);
				}
			}
			return vRueck;
		}
		
	}
	
	
	
	
	
	
}
