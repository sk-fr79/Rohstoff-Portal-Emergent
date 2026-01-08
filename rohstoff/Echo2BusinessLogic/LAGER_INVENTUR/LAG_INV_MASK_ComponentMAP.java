package rohstoff.Echo2BusinessLogic.LAGER_INVENTUR;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSelectField_Factory;
import rohstoff.utils.ECHO2.DB_SEARCH_Sorte;


public class LAG_INV_MASK_ComponentMAP extends E2_ComponentMAP 
{

	private boolean m_bNurNullbuchungen = false;
	
	public LAG_INV_MASK_ComponentMAP(boolean bNurNullbuchungen) throws myException
	{
		super(new LAG_INV_MASK_SQLFieldMAP());
		
		this.m_bNurNullbuchungen = bNurNullbuchungen;
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("BUCHUNGSDATUM")), new MyE2_String("Buchungsdatum"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BUCHUNGSZEIT"),true,50,5,true), new MyE2_String("Buchungszeit"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_LAGER_INVENTUR"),true,100,10,false), new MyE2_String("InventurID"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MENGE"),true,100,12,false), new MyE2_String("Menge"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("PREIS"),true,100,10,false), new MyE2_String("Preis"));
		
		MyE2_DB_SelectField  oSelLager = (new LAG_LagerSelectField_Factory()).getDBSelectField(oFM.get_("ID_ADRESSE_LAGER"));
		this.add_Component(oSelLager,new MyE2_String("Lager"));
//		this.add_Component(new LAG_DBSelectFieldOwnLAGER(oFM.get_("ID_ADRESSE_LAGER")),new MyE2_String("Lager"));
		//this.add_Component("ID_ADRESSE_LAGER",new LAG_SelectFieldOwnLAGER(), new MyE2_String("Lager"));

		this.add_Component(new DB_SEARCH_Sorte(oFM.get_("ID_ARTIKEL_SORTE")), new MyE2_String("Sorte"));
		
		// Bei Nullbuchungen düren Menge und Preis nicht geändert werden 
		if (bNurNullbuchungen){
			((MyE2_DB_TextField)this.get__Comp("MENGE")).EXT().set_bDisabledFromBasic(true);
			((MyE2_DB_TextField)this.get__Comp("PREIS")).EXT().set_bDisabledFromBasic(true);
		}

		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new LAG_INV_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new LAG_INV_MASK_FORMATING_Agent());
	}
	
}
