package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextArea_INROW;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObjects_LISTHEADLINE;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentGrid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_LIST_EXPANDER_4_Compressed_Entries;

public class WF_HEAD_LIST_ComponentMap extends E2_ComponentMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5999216296545210060L;
	
	private boolean showDeleted = true;
	private boolean showOwnOnly = true;

	/**
	 * @return the showDeleted
	 */
	public boolean isShowDeleted()
	{
		return showDeleted;
	}

	/**
	 * @param showDeleted the showDeleted to set
	 */
	public void setShowDeleted(boolean showDeleted)
	{
		this.showDeleted = showDeleted;
	}

	/**
	 * @return the showOwnOnly
	 */
	public boolean isShowOwnOnly()
	{
		return showOwnOnly;
	}

	/**
	 * @param showOwnOnly the showOwnOnly to set
	 */
	public void setShowOwnOnly(boolean showOwnOnly)
	{
		this.showOwnOnly = showOwnOnly;
	}

	
	
	public WF_HEAD_LIST_ComponentMap(WF_HEAD__NaviList oNavigationListHead, WF_HEAD_MASK_BasicModuleContainer oHeadMask) throws myException
	{
		super(new WF_HEAD_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		
		E2_CheckBoxForList cbForList = new E2_CheckBoxForList();
		cbForList.EXT().set_oColExtent(new Extent(18,Extent.PX));
		this.add_Component(WF_HEAD_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,	cbForList ,new MyE2_String("?"));
		this.add_Component(WF_HEAD_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

				
		
		this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(true, oNavigationListHead),new MyE2_String("+"));

		MyE2_DB_MultiComponentGrid oGridNamen = new MyE2_DB_MultiComponentGrid(1,120,null);
		oGridNamen.setWidth(new Extent(100,Extent.PERCENT));
		MyE2_DB_Label_INROW lab1 = new MyE2_DB_Label_INROW(oFM.get_("ID_USER_BESITZER"),true);
		MyE2_DB_Label_INROW lab2 = new MyE2_DB_Label_INROW(oFM.get_("ID_USER_SUPERVISOR"),true);
		lab1.EXT().set_oLayout_ListElement_AND_Titel(new RB_gld()._ins(2,2,4,2));
		lab2.EXT().set_oLayout_ListElement_AND_Titel(new RB_gld()._ins(2,2,4,2));
		oGridNamen.add_Component(lab1, new MyE2_String("Besitzer"), null);
		oGridNamen.add_Component(lab2, new MyE2_String("Supervisor"), null);
		
		
//		MyE2_DB_MultiComponentColumn oColNamen = new MyE2_DB_MultiComponentColumn();
//		oColNamen.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_USER_BESITZER")), new MyE2_String("Besitzer"), null);
//		oColNamen.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_USER_SUPERVISOR")), new MyE2_String("Supervisor"), null);
		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_LAUFZETTEL")), new MyE2_String("ID"));
		
		//2016-06-28: direkt editieren
		WF_HEAD_LIST_BT_editInList  edit = new WF_HEAD_LIST_BT_editInList(oFM.get_(LAUFZETTEL.erzeugt_von));
		edit.EXT_DB().set_bIsSortable(false);
		this.add_Component(edit, new MyE2_String("Bearb."));
		
		this.add_Component(new MyE2_DB_Label(oFM.get_(WF_HEAD_CONST.HASH_SONDERFELD_WORKFLOW_PRIVAT_NN)),new MyE2_String("Priv"));
		
//		this.add_Component(new MyE2_DB_Label(oFM.get_("PRIVAT")),new MyE2_String("Priv"));		
		
		this.add_Component(WF_HEAD_CONST.HASH_MEHRZEILER_USER,oGridNamen, new MyE2_String("Besitzer/Supervisor"));
		
		MyE2_DB_MultiComponentColumn oColBeschreibung = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_TextArea oTA = new MyE2_DB_TextArea(oFM.get_("TEXT"),720,6);
		oTA.__setStyleFactory(new StyleFactory_TextArea_INROW());

		oColBeschreibung.add_Component(oTA,new MyE2_String("Beschreibung"), null);
		this.add_Component(WF_HEAD_CONST.HASH_MEHRZEILER_BESCHREIBUNG,oColBeschreibung, new MyE2_String("Beschreibung"));

		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_LAUFZETTEL_STATUS")), new MyE2_String("Status"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANGELEGT_AM")), new MyE2_String("Angelegt am"));

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_USER_ABGESCHLOSSEN_VON")), new MyE2_String("Abgeschl. von"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ABGESCHLOSSEN_AM")), new MyE2_String("Abgeschl. am"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(WF_HEAD_CONST.HASH_SONDERFELD_GEANDERT_VON)), new MyE2_String("Geändert von"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(WF_HEAD_CONST.HASH_SONDERFELD_LETZTE_AENDERUNG)), new MyE2_String("Zuletzt geändert"));

		
		
		
		//this.add_Component(new MyE2_DB_Label(oFM.get_("ID_MANDANT")), new MyE2_String("ID_MANDANT"));
		//((MyE2IF__Component)this.get("ID_ADRESSE")).EXT().set_oColExtent(new Extent(50));
		GridLayoutData layout = new GridLayoutData();
		layout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
//		this.get_hmRealComponents().get("ID_USER_BESITZER").EXT().set_oLayout_ListElement(layout);
		this.get__Comp("ID_LAUFZETTEL_STATUS").EXT().set_oLayout_ListElement(layout);
//		this.get_hmRealComponents().get("ID_USER_SUPERVISOR").EXT().set_oLayout_ListElement(layout);
		this.get__Comp("ID_USER_ABGESCHLOSSEN_VON").EXT().set_oLayout_ListElement(layout);
		
		this.get__Comp(WF_HEAD_CONST.HASH_SONDERFELD_GEANDERT_VON).EXT().set_bIsVisibleInList(false);
		this.get__Comp(WF_HEAD_CONST.HASH_SONDERFELD_LETZTE_AENDERUNG).EXT().set_bIsVisibleInList(false);
		this.get__Comp("ID_LAUFZETTEL").EXT().set_bIsVisibleInList(false);
		
		
		
		this.get__Comp("EXTENDER").EXT().set_oCompTitleInList(new E2_ListButtonExtendDaughterObjects_LISTHEADLINE(oNavigationListHead));

		this.set_oSubQueryAgent(new WF_HEAD_LIST_FORMATING_Agent());
		
		
		// neue Unterliste mit Hierarchie
		WF_LIST_EXPANDER_4_Compressed_Entries oListExpander = new WF_LIST_EXPANDER_4_Compressed_Entries(oNavigationListHead);
		
		this.set_List_EXPANDER_4_ComponentMAP(oListExpander);

		
	}
	


}
