package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FS_Component_MASK_DAUGHTER_CONTAINERTYPEN extends MyE2_DBC_DaughterTable
{

	public FS_Component_MASK_DAUGHTER_CONTAINERTYPEN(	SQLFieldMAP 			fieldMAPMotherTable, 
														E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldMapADR_CONTAINERTYP = new SQLFieldMAP("JT_ADR_CONTAINERTYP",bibE2.get_CurrSession());
		oSQLFieldMapADR_CONTAINERTYP.addCompleteTable_FIELDLIST("JT_ADR_CONTAINERTYP",":ID_ADR_CONTAINERTYP:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMapADR_CONTAINERTYP.add_SQLField(new SQLFieldForPrimaryKey("JT_ADR_CONTAINERTYP","ID_ADR_CONTAINERTYP","ID_ADR_CONTAINERTYP",new MyE2_String("ID-Containertyp"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ADR_CONTAINERTYP.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMapADR_CONTAINERTYP.add_SQLField(new SQLFieldJoinOutside("JT_ADR_CONTAINERTYP","ID_ADRESSE","ID_ADRESSE",
											new MyE2_String("ID-Adresse"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_ADRESSE")), false);

		oSQLFieldMapADR_CONTAINERTYP.get_("DATUM_ZUGRIFF").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		
		oSQLFieldMapADR_CONTAINERTYP.initFields();

		
		E2_ComponentMAP 				oMapContainerTyp = new E2_ComponentMAP(oSQLFieldMapADR_CONTAINERTYP);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();

		
		MyE2_DB_SelectField			oSelContainerTyp = 		new MyE2_DB_SelectField(oSQLFieldMapADR_CONTAINERTYP.get_("ID_CONTAINERTYP"), "SELECT KURZBEZEICHNUNG,ID_CONTAINERTYP FROM "+bibE2.cTO()+".JT_CONTAINERTYP ORDER BY KURZBEZEICHNUNG", 
																			false,
																			false);
		MyE2_DB_TextField			oTF_Anzahl = 			new MyE2_DB_TextField(oSQLFieldMapADR_CONTAINERTYP.get_SQLField("ANZAHL"));
		MyE2_DB_TextField			oTF_Anzahl_geplant = 	new MyE2_DB_TextField(oSQLFieldMapADR_CONTAINERTYP.get_SQLField("ANZAHL_GEPLANT"));
		MyE2_DB_TextArea			oTF_Beschreibung = 		new MyE2_DB_TextArea(oSQLFieldMapADR_CONTAINERTYP.get_SQLField("BESCHREIBUNG"));
		MyE2_DB_TextField			oTF_Datum_Zugriff = 		new MyE2_DB_TextField(oSQLFieldMapADR_CONTAINERTYP.get_SQLField("DATUM_ZUGRIFF"));
		
		oSelContainerTyp.setWidth(new Extent(200));
		oTF_Anzahl.set_iWidthPixel(30);
		oTF_Anzahl_geplant.set_iWidthPixel(30);
		oTF_Beschreibung.set_iWidthPixel(450);
		oTF_Beschreibung.set_iRows(3);
		oTF_Datum_Zugriff.set_iWidthPixel(90); 

		oSelContainerTyp.EXT().set_oColExtent(new Extent(210));
		oTF_Anzahl.EXT().set_oColExtent(new Extent(40));
		oTF_Anzahl_geplant.EXT().set_oColExtent(new Extent(40));
		oTF_Beschreibung.EXT().set_oColExtent(new Extent(460));
		oTF_Datum_Zugriff.EXT().set_oColExtent(new Extent(100));
		
		oMapContainerTyp.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		
		oMapContainerTyp.add_Component(oSelContainerTyp,new MyE2_String("Containertyp"));
		oMapContainerTyp.add_Component(oTF_Anzahl,new MyE2_String("IST"));
		oMapContainerTyp.add_Component(oTF_Datum_Zugriff,new MyE2_String("Datum"));
		oMapContainerTyp.add_Component(oTF_Beschreibung,new MyE2_String("Bemerkungen"));
		oMapContainerTyp.add_Component(oTF_Anzahl_geplant,new MyE2_String("SOLL"));
	
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

//		this.set_oContainerExScrollHeight(new Extent(470));
//		this.set_oContainerExScrollWidth(new Extent(FS_CONST.MASK_WIDTH-50));
		this.set_to_100_percent();
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapContainerTyp,
							null);
		
	}

}
