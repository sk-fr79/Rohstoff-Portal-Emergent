package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.STAMM;

import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;
import rohstoff.utils.ECHO2.DB_SEARCH_Sorte;


public class FKR_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public FKR_MASK_ComponentMAP() throws myException
	{
		super(new FKR_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		// Dropdown for "Einkauf/Verkauf"
		E2_DropDownSettingsNew  oDDEKVK = new E2_DropDownSettingsNew("SELECT GRUPPE,ID_ARTIKEL_GRUPPE_FIBU FROM JT_ARTIKEL_GRUPPE_FIBU ORDER BY GRUPPE", true, true);
		MyE2_DB_SelectField sf_EK_KV= new MyE2_DB_SelectField(oFM.get_(_DB.FIBU_KONTENREGEL$DEF_EK_VK), new dataToView(new String[][]{{"-", ""}, {"EK", "EK"}, {"VK", "VK"}}, false, bibE2.get_CurrSession()));
		this.add_Component(sf_EK_KV, new MyE2_String("DEF_EK_VK"));

		// Database search field for "Addresse"
		this.add_Component(new DB_SEARCH_Adress(oFM.get_("ID_ADRESSE")), new MyE2_String("ID_ADRESSE"));

		// Database search field for Sorten, because "Artikel" naming is vendor-specific 
		this.add_Component(new DB_SEARCH_Sorte(oFM.get_("ID_ARTIKEL")), new MyE2_String("ID_ARTIKEL"));

		// Data definition and dropdown select field for "Artikelgruppen" 
		E2_DropDownSettingsNew  oDD = new E2_DropDownSettingsNew("SELECT GRUPPE,ID_ARTIKEL_GRUPPE_FIBU FROM JT_ARTIKEL_GRUPPE_FIBU ORDER BY GRUPPE", true, true);
		MyE2_DB_SelectField sf_ARTIKEL_GRUPPE_FIBU= new MyE2_DB_SelectField(oFM.get_(_DB.FIBU_KONTENREGEL$ID_ARTIKEL_GRUPPE_FIBU), new dataToView(oDD.getDD(), false, bibE2.get_CurrSession()));
		this.add_Component(sf_ARTIKEL_GRUPPE_FIBU, new MyE2_String("ID_ARTIKEL_GRUPPE_FIBU"));

		// Data definition and dropdown select field for "Konto" 
		E2_DropDownSettingsNew  oDDFibuKonto = new E2_DropDownSettingsNew("SELECT CONCAT(CONCAT(KONTO, ' '), BESCHREIBUNG), ID_FIBU_KONTO FROM JT_FIBU_KONTO ORDER BY KONTO", true, true);
		MyE2_DB_SelectField sf_FIBU_KONTO = new MyE2_DB_SelectField(oFM.get_(_DB.FIBU_KONTENREGEL$ID_FIBU_KONTO), new dataToView(oDDFibuKonto.getDD(), false, bibE2.get_CurrSession()));
		this.add_Component(sf_FIBU_KONTO, new MyE2_String("ID_FIBU_KONTO"));

		// Data definition and dropdown select field for "Steuersatz" 
		E2_DropDownSettingsNew  oDDSteuersatz = new E2_DropDownSettingsNew("SELECT T.DROPDOWN_TEXT || ' (' || L.LAENDERCODE || ', ' || T.STEUERSATZ || '%)' "
				+ ", ID_TAX FROM JT_TAX T LEFT JOIN JD_LAND L ON (T.ID_LAND = L.ID_LAND) ORDER BY DROPDOWN_TEXT", true, true);
		MyE2_DB_SelectField sf_STEUERSATZ = new MyE2_DB_SelectField(oFM.get_(_DB.FIBU_KONTENREGEL$ID_TAX), new dataToView(oDDSteuersatz.getDD(), false, bibE2.get_CurrSession()));
		this.add_Component(sf_STEUERSATZ, new MyE2_String("ID_FIBU_KONTO"));
		
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_TAX"),true,200,10,false), new MyE2_String("ID_TAX"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_FIBU_KONTENREGEL")), 			new MyE2_String("ID_FIBU_KONTENREGEL"));
		

		////////////////////////////// 2014-11-14: martin: die componentmaps (START)
		FKR_MASK_CrossTable_Land  crt_EK_LAENDER = new FKR_MASK_CrossTable_Land(oFM.get_oSQLFieldPKMainTable(), "EK");
		FKR_MASK_CrossTable_Land  crt_VK_LAENDER = new FKR_MASK_CrossTable_Land(oFM.get_oSQLFieldPKMainTable(), "VK");
		
		this.add_Component(FKR__CONST.MASK_COMPONENT_CROSSTABLE_LAENDER_EK, crt_EK_LAENDER, new MyE2_String("Kreuztabelle Länder EK"));
		this.add_Component(FKR__CONST.MASK_COMPONENT_CROSSTABLE_LAENDER_VK, crt_VK_LAENDER, new MyE2_String("Kreuztabelle Länder VK"));
		////////////////////////////// 2014-11-14: martin: die componentmaps (END)
		
		// Dropdown for "Dienstleistung"
		MyE2_DB_SelectField sf_DL= new MyE2_DB_SelectField(oFM.get_("DIENSTLEISTUNG"), new dataToView(new String[][]{{"-", ""}, {"Y", "Y"}, {"N", "N"}}, false, bibE2.get_CurrSession()));
		this.add_Component(sf_DL, new MyE2_String("Dienstleistung"));		
	
		// Dropdown for "Privat"
		MyE2_DB_SelectField sf_PR= new MyE2_DB_SelectField(oFM.get_("PRIVAT"), new dataToView(new String[][]{{"-", ""}, {"Y", "Y"}, {"N", "N"}}, false, bibE2.get_CurrSession()));
		this.add_Component(sf_PR, new MyE2_String("Privat"));		

		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new FKR_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new FKR_MASK_FORMATING_Agent());
	}
	
}
