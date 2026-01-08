package rohstoff.Echo2BusinessLogic.EAK;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class EAK_ComponentMapTRANSLATION extends E2_ComponentMAP
{

	
	private String BASENAME = null;
	
	
	/**
	 * @param BASENAME (Kann sein: BRANCHE / GRUPPE / CODE)
	 * @throws myException
	 */
	public EAK_ComponentMapTRANSLATION(		String 						cBASENAME, 
											String 						cID_MOTHERTABLE_Unformated, 
											EAK_BasicModuleContainer  	oBasicContainerForAll,
											E2_NavigationList			oNaviListTranslation) throws myException
	{
		super(new EAK_SQLFieldMapTRANSLATION(cBASENAME,cID_MOTHERTABLE_Unformated));
		
		this.BASENAME = cBASENAME;
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		
		this.add_Component("SELEKTOR",new E2_CheckBoxForList(), new MyE2_String("?"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_SPRACHE"),oBasicContainerForAll.get_cSPRACHEN(), false),new MyE2_String("Sprache"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(this.BASENAME+"_UEBERSETZUNG")),new MyE2_String("Übersetzung"));

		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_EAK_"+cBASENAME+"_SP")),new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_EAK_"+this.BASENAME)),new MyE2_String("ID (Ref)"));

		((MyE2_DB_SelectField)this.get__Comp("ID_SPRACHE")).setWidth(new Extent(100));
		((MyE2_DB_TextField)this.get__Comp(this.BASENAME+"_UEBERSETZUNG")).set_iWidthPixel(750);
		
		((MyE2_DB_TextField)this.get__Comp(this.BASENAME+"_UEBERSETZUNG")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_Label)this.get__Comp("ID_EAK_"+this.BASENAME)).setFont(new E2_FontPlain(-2));
		((MyE2_DB_SelectField)this.get__Comp("ID_SPRACHE")).setFont(new E2_FontPlain(-2));

		
		this.get__Comp("ID_EAK_"+cBASENAME+"_SP").EXT().set_bIsVisibleInList(false);
		this.get__Comp("ID_EAK_"+cBASENAME).EXT().set_bIsVisibleInList(false);
	
		this.get__Comp("ID_SPRACHE").EXT().set_oColExtent(new Extent(105));
		this.get__Comp(this.BASENAME+"_UEBERSETZUNG").EXT().set_oColExtent(new Extent(755));
	
		// sortierung abschalten
		((MyE2IF__DB_Component)this.get__Comp("ID_SPRACHE")).EXT_DB().set_bIsSortable(false);
		((MyE2IF__DB_Component)this.get__Comp(this.BASENAME+"_UEBERSETZUNG")).EXT_DB().set_bIsSortable(false);
		((MyE2IF__DB_Component)this.get__Comp("ID_EAK_"+cBASENAME+"_SP")).EXT_DB().set_bIsSortable(false);
		((MyE2IF__DB_Component)this.get__Comp("ID_EAK_"+this.BASENAME)).EXT_DB().set_bIsSortable(false);
		
		// layout titel
		((MyE2IF__Component)this.get__Comp("SELEKTOR")).EXT().set_oLayout_ListElement(oBasicContainerForAll.get_oGridLayoutData());
		((MyE2IF__Component)this.get__Comp("ID_SPRACHE")).EXT().set_oLayout_ListElement(oBasicContainerForAll.get_oGridLayoutData());
		((MyE2IF__Component)this.get__Comp(this.BASENAME+"_UEBERSETZUNG")).EXT().set_oLayout_ListElement(oBasicContainerForAll.get_oGridLayoutData());
		((MyE2IF__Component)this.get__Comp("ID_EAK_"+cBASENAME+"_SP")).EXT().set_oLayout_ListElement(oBasicContainerForAll.get_oGridLayoutData());
		((MyE2IF__Component)this.get__Comp("ID_EAK_"+this.BASENAME)).EXT().set_oLayout_ListElement(oBasicContainerForAll.get_oGridLayoutData());

		// layout elemente
		((MyE2IF__Component)this.get__Comp("SELEKTOR")).EXT().set_oLayout_ListTitelElement(oBasicContainerForAll.get_oGridLayoutData());
		((MyE2IF__Component)this.get__Comp("ID_SPRACHE")).EXT().set_oLayout_ListTitelElement(oBasicContainerForAll.get_oGridLayoutData());
		((MyE2IF__Component)this.get__Comp(this.BASENAME+"_UEBERSETZUNG")).EXT().set_oLayout_ListTitelElement(oBasicContainerForAll.get_oGridLayoutData());
		((MyE2IF__Component)this.get__Comp("ID_EAK_"+cBASENAME+"_SP")).EXT().set_oLayout_ListTitelElement(oBasicContainerForAll.get_oGridLayoutData());
		((MyE2IF__Component)this.get__Comp("ID_EAK_"+this.BASENAME)).EXT().set_oLayout_ListTitelElement(oBasicContainerForAll.get_oGridLayoutData());
	
		// Komponenten fuer die ueberschrift (klein und kursiv)
		// ueber der edit-spalte eine new-komponente einfuegen
		E2_BASIC_EditListButtonPanel oPanel = new E2_BASIC_EditListButtonPanel(	oNaviListTranslation,
																				true,true,true,null,
																				null,
																				E2_MODULNAMES.NAME_MODUL_EAK+"_"+cBASENAME,
																				"SPRACHE_",
																				MyE2_Row.STYLE_NO_BORDER_NO_INSETS(),
																				E2_INSETS.I_1_1_1_1,
																				new Insets(10,1,1,1));
		
		((MyE2IF__Component)this.get__Comp("ID_SPRACHE")).EXT().set_oCompTitleInList(new MyE2_Label(new MyE2_String("Sprache"),MyE2_Label.STYLE_SMALL_ITALIC()));
		((MyE2IF__Component)this.get__Comp(this.BASENAME+"_UEBERSETZUNG")).EXT().set_oCompTitleInList(oPanel);
		((MyE2IF__Component)this.get__Comp("ID_EAK_"+cBASENAME+"_SP")).EXT().set_oCompTitleInList(new MyE2_Label(new MyE2_String("ID(Sprache)"),MyE2_Label.STYLE_SMALL_ITALIC()));
		((MyE2IF__Component)this.get__Comp("ID_EAK_"+this.BASENAME)).EXT().set_oCompTitleInList(new MyE2_Label(new MyE2_String("ID(Basis)"),MyE2_Label.STYLE_SMALL_ITALIC()));

		
	}

}
