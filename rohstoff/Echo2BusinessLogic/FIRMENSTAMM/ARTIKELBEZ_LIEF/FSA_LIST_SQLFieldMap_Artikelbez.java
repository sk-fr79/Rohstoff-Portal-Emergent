package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ARTIKELBEZ_LIEF;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


//NEU_09
public class FSA_LIST_SQLFieldMap_Artikelbez extends SQLFieldMAP
{

	
	/**
	 * @param cEK_VK MUSS entweder "EK" oder "VK" sein
	 * @throws myException
	 */
	public FSA_LIST_SQLFieldMap_Artikelbez(String cEK_VK) throws myException
	{
		super("JT_ARTIKELBEZ_LIEF",bibE2.get_CurrSession());
		
		String cTyp = "'"+cEK_VK+"'";
		
		this.addCompleteTable_FIELDLIST("JT_ARTIKELBEZ_LIEF",":ID_ARTIKELBEZ_LIEF:ARTBEZ_TYP:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		
		this.addCompleteTable_FIELDLIST("JT_ARTIKEL_BEZ",":ANR2:AKTIV:",true,false,"B_");
		this.addCompleteTable_FIELDLIST("JT_ARTIKEL",":ANR1:AKTIV:"+_DB.ARTIKEL$IST_PRODUKT+":"+_DB.ARTIKEL$DIENSTLEISTUNG+":"+_DB.ARTIKEL$END_OF_WASTE+":",true,false,"A_");
		
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ARTIKELBEZ_LIEF","ID_ARTIKELBEZ_LIEF","ID_ARTIKELBEZ_LIEF",new MyE2_String("ID-Artikelbez-Lief"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ARTIKELBEZ_LIEF.NEXTVAL FROM DUAL",true),false);

		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ARTIKELBEZ_LIEF","ARTBEZ_TYP","ARTBEZ_TYP",new MyE2_String("Art-Bez-Typ"),cTyp,bibE2.get_CurrSession()),false);
		
		// 2 primary keys fuer die verknuepfungen der tabellen wg sortierung nach ANR1,ANR2
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ARTIKEL_BEZ","ID_ARTIKEL_BEZ","ID_ARTIKEL_BEZ2",new MyE2_String("ID-Artikelbez"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ARTIKEL_BEZ.NEXTVAL FROM DUAL",true),false);
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ARTIKEL","ID_ARTIKEL","ID_ARTIKEL2",new MyE2_String("ID-Artikel"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ARTIKEL.NEXTVAL FROM DUAL",true),false);
		
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ARTIKELBEZ_LIEF","ID_ADRESSE","ID_ADRESSE",new MyE2_String("Basis-Adresse"),"NULL",bibE2.get_CurrSession()),false);

		//20170926: weiteres feld mit avv-bezeichner, um danach sortieren zu koennen
		//this.add_SQLField(new SQLField("("+new SelAvvText(ARTIKELBEZ_LIEF.id_eak_code.tnfn(), true).s()+")", "AVVCODE", new MyE2_String("AVV-Code")), false);
		
		// jetzt verknuepfung kodieren
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ"));
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_ARTIKEL.ID_ARTIKEL=JT_ARTIKEL_BEZ.ID_ARTIKEL"));
		
		// jetzt die noetige sortierung einstellen
		this.clear_ORDER_SEGMENT();
		this.add_ORDER_SEGMENT("JT_ARTIKEL.ANR1");
		this.add_ORDER_SEGMENT("JT_ARTIKEL_BEZ.ANR2");
		
		this.get_("ID_ARTIKEL_BEZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.initFields();
		
	}

}
