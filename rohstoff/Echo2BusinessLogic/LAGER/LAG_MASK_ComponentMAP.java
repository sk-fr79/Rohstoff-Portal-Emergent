package rohstoff.Echo2BusinessLogic.LAGER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class LAG_MASK_ComponentMAP extends E2_ComponentMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 224339594749297170L;

	public LAG_MASK_ComponentMAP() throws myException
	{
		super(new LAG_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BUCHUNGSDATUM"),true,120,10,false), new MyE2_String("BUCHUNGSDATUM"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BUCHUNGSTYP"),true,100,2,false), new MyE2_String("BUCHUNGSTYP"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_ADRESSE_LAGER"),true,200,10,false), new MyE2_String("ID_ADRESSE_LAGER"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_ARTIKEL_SORTE"),true,200,10,false), new MyE2_String("ID_ARTIKEL_SORTE"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_LAGER_KONTO"),true,200,10,false), new MyE2_String("ID_LAGER_KONTO"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_LAGER_KONTO_PARENT"),true,200,10,false), new MyE2_String("ID_LAGER_KONTO_PARENT"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_TPA_FUHRE"),true,200,10,false), new MyE2_String("ID_VPOS_TPA_FUHRE"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_TPA_FUHRE_ORT"),true,200,10,false), new MyE2_String("ID_VPOS_TPA_FUHRE_ORT"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("IST_KOMPLETT")), new MyE2_String("IST_KOMPLETT"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MENGE"),true,200,10,false), new MyE2_String("MENGE"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("PREIS"),true,200,10,false), new MyE2_String("PREIS"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("SALDO"),true,380,19,false), new MyE2_String("SALDO"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("STORNO")), new MyE2_String("STORNO"));
		
		/*
		 * beispiele fuer beliebige felder
		this.add_Component(new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER")), new MyE2_String("Besitzer"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_LAG_WICHTIGKEIT"),oDDWichtigkeit.getDD(),false), new MyE2_String("Wichtig ?"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_LAG")), new MyE2_String("ID"));

		((MyE2_DB_TextArea)this.get__Comp("AUFGABENTEXT")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("ANTWORTTEXT")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("AUFGABENTEXT")).set_iRows(4);
		((MyE2_DB_TextArea)this.get__Comp("ANTWORTTEXT")).set_iRows(4);
		
		((MyE2_DB_TextField)this.get__Comp("AUFGABEKURZ")).set_iWidthPixel(600);
		((MyE2_DB_TextField)this.get__Comp("ANTWORTKURZ")).set_iWidthPixel(600);

		((MyE2_DB_CheckBox)this.get__Comp("ERLEDIGT")).add_oActionAgent(new cbActionAgent());
		*/	

		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new LAG_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new LAG_MASK_FORMATING_Agent());
	}
	
}
