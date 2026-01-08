package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.RecursiveSearch.E2_SEARCH_TAGS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KONTRAKT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class UMA_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public UMA_MASK_ComponentMAP() throws myException
	{
		super(new UMA_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_UMA_KONTRAKT")), new MyE2_String("ID-UMA-Kontrakt"));
		this.add_Component(new UMA_MASK_DB_SEARCH_Adresse(oFM.get_("ID_ADRESSE")), new MyE2_String("Kunde"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("BEMERKUNGEN"),500,8), new MyE2_String("Bemerkungen"));
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("DATUM_VERTRAG"),bibALL.get_cDateNOW(),100), new MyE2_String("Datum des Vertrags"));
		
		
		this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER_BETREUER"),true,150), new MyE2_String("Betreuer"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MENGE_ARTIKEL_AUSGANG"),true,150,14,false), new MyE2_String("Menge Liefersorte"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MENGE_ARTIKEL_ZIEL"),true,150,14,false), new MyE2_String("Menge Rückliefersorte"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_(RECORD_UMA_KONTRAKT.FIELD__STARTSALDO_MENGE_AUSGANG),true,150,14,false), new MyE2_String("Startsaldo Liefersorte"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(RECORD_UMA_KONTRAKT.FIELD__STARTSALDO_MENGE_ZIEL),true,150,14,false), new MyE2_String("Startsaldo Rückliefersorte"));

		this.add_Component(UMA_CONST.MASK_COMP_EINHEIT_AUSGANGSORTE, new UMA_MASK_EINHEITEN_LABEL("<EH>",true), new MyE2_String("Einheit Ausgangssorte"));
		this.add_Component(UMA_CONST.MASK_COMP_EINHEIT_ZIELSORTE,    new UMA_MASK_EINHEITEN_LABEL("<EH>",false), new MyE2_String("Einheit Rückliefersorte"));
		
		
		this.add_Component(UMA_CONST.MASK_COMP_TOCHTER_LIEFERSORTE, 		new UMA_MASK_Component_DAUGHTER_LIEFERSORTE(oFM,this,true), new MyE2_String("Liefersorte"));
		this.add_Component(UMA_CONST.MASK_COMP_TOCHTER_RUECKLIEFERSORTE, 	new UMA_MASK_Component_DAUGHTER_LIEFERSORTE(oFM,this,false), new MyE2_String("Rückliefersorte"));

		
		//das adress-feld fuer einen such-vorgang tagged
		this.get__Comp("ID_ADRESSE").EXT().add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.MODUL_UMA_KONTRAKT_MASKENFELD_ID_ADRESSE);
		
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new UMA_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new UMA_MASK_FORMATING_Agent());
	}
	
}
