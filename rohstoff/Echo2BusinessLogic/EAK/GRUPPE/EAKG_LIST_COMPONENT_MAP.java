package rohstoff.Echo2BusinessLogic.EAK.GRUPPE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObjects_LISTHEADLINE;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.EAK.EAK_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.EAK.EAK_List_EXPANDER_Translation;

public class EAKG_LIST_COMPONENT_MAP extends E2_ComponentMAP
{

	public EAKG_LIST_COMPONENT_MAP(EAK_BasicModuleContainer TOP_CONTAINER, E2_NavigationList oNavigationListFromMother) throws myException
	{
		super(new EAKG_LIST_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		this.add_Component("CHECKBOX_SELECT",new E2_CheckBoxForList(), new MyE2_String("?"));
		this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(false, null),new MyE2_String("+"));

		this.add_Component(new EAKG_LIST_BUTTON_Goto_CODES(oFM.get_("ID_EAK_GRUPPE_B")), new MyE2_String("?"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("B_KEY_BRANCHE")),new MyE2_String("Branche"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("KEY_GRUPPE")),new MyE2_String("Code"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("GRUPPE")),new MyE2_String("Gruppe"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_EAK_BRANCHE")),new MyE2_String("ID(Branche)"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_EAK_GRUPPE")),new MyE2_String("ID(Gruppe)"));
		
		
		((MyE2_DB_TextField)this.get__Comp("B_KEY_BRANCHE")).set_iWidthPixel(50);
		((MyE2_DB_TextField)this.get__Comp("KEY_GRUPPE")).set_iWidthPixel(50);
		((MyE2_DB_TextField)this.get__Comp("GRUPPE")).set_iWidthPixel(700);
		
		((MyE2_DB_TextField)this.get__Comp("B_KEY_BRANCHE")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_TextField)this.get__Comp("KEY_GRUPPE")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_TextField)this.get__Comp("GRUPPE")).setFont(new E2_FontPlain(-2));
		
		((MyE2_DB_Label)this.get__Comp("ID_EAK_BRANCHE")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_Label)this.get__Comp("ID_EAK_GRUPPE")).setFont(new E2_FontPlain(-2));
	
		this.get__Comp("ID_EAK_BRANCHE").EXT().set_bIsVisibleInList(false);
		this.get__Comp("ID_EAK_GRUPPE").EXT().set_bIsVisibleInList(false);
	
		this.get__Comp("KEY_GRUPPE").EXT().set_oColExtent(new Extent(55));
		this.get__Comp("GRUPPE").EXT().set_oColExtent(new Extent(705));
		
		
		// dem button wird der topcontainer uebergeben
		((EAKG_LIST_BUTTON_Goto_CODES)this.get__Comp("ID_EAK_GRUPPE_B")).EXT().set_O_PLACE_FOR_EVERYTHING(TOP_CONTAINER);

		
		
		// in die extender-spalte einen header-button vorsehen
		//jetzt der spalte mit dem Tochterlisten-extender einen header verpassen
		MyE2IF__Component   oExtender = this.get__Comp("EXTENDER");
		oExtender.EXT().set_oCompTitleInList(new E2_ListButtonExtendDaughterObjects_LISTHEADLINE(oNavigationListFromMother));

		this.set_List_EXPANDER_4_ComponentMAP(new EAK_List_EXPANDER_Translation(oNavigationListFromMother,"GRUPPE",TOP_CONTAINER));

		
	}


}
