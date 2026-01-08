package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.REGELN;

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
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_Component_MASK_DAUGHTER_TELEFON;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;
import rohstoff.utils.ECHO2.DB_SEARCH_Sorte;


public class FIBU_KONTEN_REGELN_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public FIBU_KONTEN_REGELN_MASK_ComponentMAP() throws myException
	{
		super(new FIBU_KONTEN_REGELN_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		

		// Data definition and dropdown select field for "Konto" 
		E2_DropDownSettingsNew  oDDFibuKonto = new E2_DropDownSettingsNew("SELECT CONCAT(CONCAT(KONTO, ' '), BESCHREIBUNG), ID_FIBU_KONTO FROM JT_FIBU_KONTO ORDER BY KONTO", true, true);
		MyE2_DB_SelectField sf_FIBU_KONTO = new MyE2_DB_SelectField(oFM.get_(_DB.FIBU_KONTENREGEL$ID_FIBU_KONTO), new dataToView(oDDFibuKonto.getDD(), false, bibE2.get_CurrSession()));
		this.add_Component(sf_FIBU_KONTO, new MyE2_String("ID_FIBU_KONTO"));

//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_TAX"),true,200,10,false), new MyE2_String("ID_TAX"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_FIBU_KONTENREGEL_NEU")), 			new MyE2_String("ID_FIBU_KONTENREGEL"));
		
		
/*		this.add_Component(FIBU_KONTEN_REGELN__CONST.MASK_COMPONENT_CONDITION_DAUGHTER,
				new ConditionDaughterComponentMap(oFM, this),new MyE2_String("Regeln"));
*/
		

		////////////////////////////// 2014-11-14: martin: die componentmaps (START)
		FIBU_KONTEN_REGELN_MASK_CrossTable_Land  crt_EK_LAENDER = new FIBU_KONTEN_REGELN_MASK_CrossTable_Land(oFM.get_oSQLFieldPKMainTable(), "EK");
		
		// this.add_Component(FIBU_KONTEN_REGELN__CONST.MASK_COMPONENT_CROSSTABLE_LAENDER_EK, crt_EK_LAENDER, new MyE2_String("Kreuztabelle Länder EK"));
		////////////////////////////// 2014-11-14: martin: die componentmaps (END)
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new FIBU_KONTEN_REGELN_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new FIBU_KONTEN_REGELN_MASK_FORMATING_Agent());
	}
	
}
