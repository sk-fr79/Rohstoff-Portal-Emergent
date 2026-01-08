package rohstoff.Echo2BusinessLogic.BANKENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_Component_MASK_DAUGHTER_EMAIL;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_Component_MASK_DAUGHTER_TELEFON;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER.FS_Component_MASK_DAUGHTER_MITARBEITER;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.FS_Component_MASK_DAUGHTER_ZUSATZINFOS;

public class BANK_MASK_ComponentMAP extends E2_ComponentMAP 
{
	
	
	private BANK_B_MASK_ComponentMAP oBANK_MASK_ZusatzMap = null;
	
	

	public BANK_MASK_ComponentMAP(BANK_MASK_BasicModuleContainer oModuleContainer_MASK) throws myException
	{
		super(new BANK_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		/*
		 * beispiele fuer felder in der map
		 *
		*/
		String[] cFieldsStandard = {"NAME1","NAME2","NAME3","STRASSE","HAUSNUMMER","PLZ","ORT","AKTIV","BEMERKUNGEN"}; 
		String[] cFieldsInfolabs = {"NAME1","NAME2","NAME3","STRASSE","HAUSNUMMER","PLZ","ORT","AKTIV","BEMERKUNGEN"}; 

		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 400);

		
		E2_DropDownSettings ddSprache = new E2_DropDownSettings( "JD_SPRACHE", "BEZEICHNUNG", "ID_SPRACHE", "ISTSTANDARD", true);
		E2_DropDownSettings ddLand = new E2_DropDownSettings( "JD_LAND", "LAENDERCODE||' ('||  NVL(LAENDERNAME,'-')||')'", "ID_LAND", "ISTSTANDARD", true);
		E2_DropDownSettings ddMoney = new E2_DropDownSettings( "JD_WAEHRUNG", "KURZBEZEICHNUNG", "ID_WAEHRUNG", "IST_STANDARD", true);
		
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_ADRESSE")),new MyE2_String("Sprache"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_SPRACHE"),ddSprache.getDD(),false,new Extent(100)),new MyE2_String("Sprache"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_LAND"),ddLand.getDD(),false,new Extent(100)),new MyE2_String("Land"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_WAEHRUNG"),ddMoney.getDD(),false,new Extent(100)),new MyE2_String("Währung"));

		
		this.add_Component(BANK_LIST_BasicModuleContainer.MASK_FIELD_DAUGHTER_TELEFON,new FS_Component_MASK_DAUGHTER_TELEFON(oFM,this),new MyE2_String("Kommunikationsangaben"));
		this.add_Component(BANK_LIST_BasicModuleContainer.MASK_FIELD_DAUGHTER_EMAIL,new FS_Component_MASK_DAUGHTER_EMAIL(oFM,this, true, false),new MyE2_String("E-Mail-Adressen"));
		
		/*
		 * komplette tochtertabellen mit eigener maske / oder komplett-editierbare liste mit navigation
		 */
		this.add_Component(BANK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER,
				new FS_Component_MASK_DAUGHTER_MITARBEITER(oFM,oModuleContainer_MASK),new MyE2_String("Mitarbeiter"));
		
		this.add_Component(BANK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS,
				new FS_Component_MASK_DAUGHTER_ZUSATZINFOS(oFM,oModuleContainer_MASK,true),new MyE2_String("Zusatzinfos"));
		
		
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNGEN")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNGEN")).set_iRows(8);

		
		this.set_oMAPSettingAgent(new BANK_MASK_MapSettingAgent());
		
		this.oBANK_MASK_ZusatzMap = new BANK_B_MASK_ComponentMAP(this);
		
	}


	public BANK_B_MASK_ComponentMAP get_oBANK_MASK_ZusatzMap() 
	{
		return oBANK_MASK_ZusatzMap;
	}
	
}
