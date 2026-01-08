package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class PROJEKT_LIST_ComponentMap extends E2_ComponentMAP 
{

	public PROJEKT_LIST_ComponentMap() throws myException
	{
		super(new PROJEKT_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(PROJEKT_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(PROJEKT_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		this.add_Component(new MyE2_DB_Label(oFM.get_("PROJEKTNAME")), new MyE2_String("Name"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("U_NAME_KUERZEL")), new MyE2_String("Projektleiter"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("PROJEKTBEGIN")), new MyE2_String("Beginn"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("PROJEKTDEADLINE")), new MyE2_String("Deadline"));
		this.add_Component(PROJEKT_LIST_BasicModuleContainer.NAME_OF_TAGE_DIFF_IN_LIST,new MyE2_Label(""), new MyE2_String("Diff-Tage"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("WIEDERVORLAGE")), new MyE2_String("Wiedervorlage"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("AKTIV")), new MyE2_String("Aktiv"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("BEENDET")), new MyE2_String("Beendet"));

		this.add_Component(new MyE2_DB_TextArea(oFM.get_("PROJEKTBESCHREIBUNG")), new MyE2_String("Beschreibung"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_PROJEKT")), new MyE2_String("ID"));
		
		this.get__Comp("PROJEKTBESCHREIBUNG").EXT().set_bIsVisibleInList(false);
		this.get__Comp("ID_PROJEKT").EXT().set_bIsVisibleInList(false);
		
		((MyE2_DB_TextArea)this.get__Comp("PROJEKTBESCHREIBUNG")).set_iWidthPixel(400);
		((MyE2_DB_TextArea)this.get__Comp("PROJEKTBESCHREIBUNG")).set_iRows(3);

		GridLayoutData TableRightTOP = new GridLayoutData();
		TableRightTOP.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
		
		((MyE2_Label)this.get__Comp(
				PROJEKT_LIST_BasicModuleContainer.NAME_OF_TAGE_DIFF_IN_LIST)).setLayoutData(TableRightTOP);
		
		this.set_oSubQueryAgent(new PROJEKT_LIST_FORMATING_Agent());
	}

}
