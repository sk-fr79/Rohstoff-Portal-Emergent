package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSZ_LIST_SQLFieldMap_AdressInfo extends SQLFieldMAP
{

	
	public FSZ_LIST_SQLFieldMap_AdressInfo(boolean bInfos) throws myException
	{
		super("JT_ADRESSE_INFO",bibE2.get_CurrSession());
		
		this.addCompleteTable_FIELDLIST("JT_ADRESSE_INFO",":ID_ADRESSE_INFO:ID_ADRESSE:IST_MESSAGE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE_INFO","ID_ADRESSE_INFO","ID_ADRESSE_INFO",new MyE2_String("ID-Adresse-Info"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ADRESSE_INFO.NEXTVAL FROM DUAL",true), false);
	
		boolean isMessage = !bInfos;
		
		/*
		 * restrict: id_adresse_basis - je nach situation
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ADRESSE_INFO","ID_ADRESSE","ID_ADRESSE",new MyE2_String("Basis-Adresse"),"NULL",bibE2.get_CurrSession()), false);
		if (isMessage) {
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ADRESSE_INFO","IST_MESSAGE","IST_MESSAGE",new MyE2_String("Message oder Info"),"'Y'",bibE2.get_CurrSession()), false);
		} else	{
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ADRESSE_INFO","IST_MESSAGE","IST_MESSAGE",new MyE2_String("Message oder Info"),"'N'",bibE2.get_CurrSession()), false);
		}
		
		/*
		 * standard-vorgaben definieren
		 */
		this.get_("KUERZEL").set_cDefaultValueFormated(bibALL.get_KUERZEL());
		this.get_("DATUMEINTRAG").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		
		this.get_("MESSAGE_TYP").set_cDefaultValueFormated("ALLGEMEIN");
		this.get_("MESSAGE_SOFORT").set_cDefaultValueFormated("N");
		
		if (isMessage) {
			this.get_("MESSAGE_TYP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		}
		
		this.initFields();
		
		this.clear_ORDER_SEGMENT();
		this.add_ORDER_SEGMENT("JT_ADRESSE_INFO.DATUMEREIGNIS DESC");
		this.add_ORDER_SEGMENT("JT_ADRESSE_INFO.ID_ADRESSE_INFO DESC");
		
	}

}
