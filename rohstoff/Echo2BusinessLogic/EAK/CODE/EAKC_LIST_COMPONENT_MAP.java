package rohstoff.Echo2BusinessLogic.EAK.CODE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObjects_LISTHEADLINE;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.EAK.EAK_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.EAK.EAK_List_EXPANDER_Translation;

public class EAKC_LIST_COMPONENT_MAP extends E2_ComponentMAP
{
	public EAKC_LIST_COMPONENT_MAP(EAK_BasicModuleContainer TOP_CONTAINER, E2_NavigationList oNavigationListFromMother) throws myException
	{
		super(new EAKC_LIST_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		this.add_Component("CHECKBOX_SELECT",new E2_CheckBoxForList(), new MyE2_String("?"));
		this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(false, null),new MyE2_String("+"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("B_KEY_BRANCHE")),new MyE2_String("Branche"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("G_KEY_GRUPPE")),new MyE2_String("Gruppe"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("KEY_CODE")),new MyE2_String("Code"));

		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("GEFAEHRLICH")), new MyE2_String("*"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("CODE")),new MyE2_String("Code-Text"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("TRANSPORT_OK")), new MyE2_String("TP"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("LAGERUNG_OK")), new MyE2_String("LG"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("VERARBEITUNG_OK")), new MyE2_String("VA"));

		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_EAK_GRUPPE")),new MyE2_String("ID(Gruppe)"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_EAK_CODE")),new MyE2_String("ID(Code)"));
		
		
		
		((MyE2_DB_TextField)this.get__Comp("B_KEY_BRANCHE")).set_iWidthPixel(50);
		((MyE2_DB_TextField)this.get__Comp("G_KEY_GRUPPE")).set_iWidthPixel(50);
		((MyE2_DB_TextField)this.get__Comp("KEY_CODE")).set_iWidthPixel(50);
		((MyE2_DB_TextField)this.get__Comp("CODE")).set_iWidthPixel(700);
		
		((MyE2_DB_TextField)this.get__Comp("B_KEY_BRANCHE")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_TextField)this.get__Comp("G_KEY_GRUPPE")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_TextField)this.get__Comp("KEY_CODE")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_TextField)this.get__Comp("CODE")).setFont(new E2_FontPlain(-2));
		
		((MyE2_DB_Label)this.get__Comp("ID_EAK_GRUPPE")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_Label)this.get__Comp("ID_EAK_CODE")).setFont(new E2_FontPlain(-2));
	
		this.get__Comp("ID_EAK_GRUPPE").EXT().set_bIsVisibleInList(false);
		this.get__Comp("ID_EAK_CODE").EXT().set_bIsVisibleInList(false);
	
		this.get__Comp("KEY_CODE").EXT().set_oColExtent(new Extent(55));
		this.get__Comp("CODE").EXT().set_oColExtent(new Extent(705));
		
		
		// in die extender-spalte einen header-button vorsehen
		//jetzt der spalte mit dem Tochterlisten-extender einen header verpassen
		MyE2IF__Component   oExtender = this.get__Comp("EXTENDER");
		oExtender.EXT().set_oCompTitleInList(new E2_ListButtonExtendDaughterObjects_LISTHEADLINE(oNavigationListFromMother));

		this.set_List_EXPANDER_4_ComponentMAP(new EAK_List_EXPANDER_Translation(oNavigationListFromMother,"CODE",TOP_CONTAINER));

		this.set_oSubQueryAgent(new ownMarkerSubQueryAgent());
		
	}
	
	
	
	// markiert die gefaehrliche stoffe
	public class ownMarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {
		
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
		{
		}

		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{ 
			
			// jetzt dienstleistungen markieren
			String cGefahr = bibALL.null2leer((String)oUsedResultMAP.get_UnFormatedValue("GEFAEHRLICH"));
			if (cGefahr.equals("Y"))
			{
				((MyE2_DB_TextField)oMAP.get__Comp("CODE")).setBackground(new E2_ColorAlarm());
			}
		}
	}
	

	

}
