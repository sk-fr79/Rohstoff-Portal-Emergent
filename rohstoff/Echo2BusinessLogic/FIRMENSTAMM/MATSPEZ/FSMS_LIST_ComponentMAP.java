package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_Selector_Multi_Matspez_elemente;

public class FSMS_LIST_ComponentMAP extends E2_ComponentMAP
{

	/*
	 * hier wird eine referenz auf den Firmenlisten-selektor FS_Selector_Multi_Matspez_elemente hinterlegt
	 * um die id_list der gefundenen matspez-ids hinterlegen zu koennen (falls aus dem standard-adressenmodul gestartet)
	 */
	private FS_Selector_Multi_Matspez_elemente  oFS_Selector_Multi_Matspez_elemente = null;;
	
	
	public FSMS_LIST_ComponentMAP(E2_NavigationList oNaviList) throws myException
	{
		super(new FSMS_LIST_SQLFieldMap());
		SQLFieldMAP oSQLFM = this.get_oSQLFieldMAP();
		
		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
		
		this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(true, oNaviList),new MyE2_String("+"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("BEZEICHNUNG")),new MyE2_String("Bezeichnung"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("A_ANR1")),new MyE2_String("ANR1"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("A_ARTBEZ1")),new MyE2_String("Artikelbezeichung 1"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("IST_LIEFERANT")),new MyE2_String("Lieferant ?"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("IST_ABNEHMER")),new MyE2_String("Abnehmer ?"));
		//neue felder
		this.add_Component(new FSMS_DB_Select_STATUS_SOLL_IST(oSQLFM.get_(_DB.MAT_SPEZ$SOLL_IST_STATUS),70),new MyE2_String("Soll/Ist"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_(_DB.MAT_SPEZ$DATUM_ERFASSUNG)),new MyE2_String("Datum"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_(_DB.MAT_SPEZ$ZEIT_ERFASSUNG)),new MyE2_String("Zeit"));
		
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ID_MAT_SPEZ")),new MyE2_String("ID(Mat.Spez)"));
		
		((MyE2IF__Component)this.get("BEZEICHNUNG")).EXT().set_oColExtent(new Extent(200));
		((MyE2IF__Component)this.get("A_ANR1")).EXT().set_oColExtent(new Extent(40));
		((MyE2IF__Component)this.get("A_ARTBEZ1")).EXT().set_oColExtent(new Extent(200));
		((MyE2IF__Component)this.get("IST_LIEFERANT")).EXT().set_oColExtent(new Extent(30));
		((MyE2IF__Component)this.get("IST_ABNEHMER")).EXT().set_oColExtent(new Extent(30));
		((MyE2IF__Component)this.get("ID_MAT_SPEZ")).EXT().set_oColExtent(new Extent(50));
		
		this.set_List_EXPANDER_4_ComponentMAP(new FSMS_LIST_ComponentMAP_ExpandElements(oNaviList));
		
		this.add_oSubQueryAgent(new ownSubQueryAgent_MarkSelektor_Results());
	}

	
	/**
	 * fuellt, falls vorhanden beim ersten durchlauf das feld oFS_Selector_Multi_Matspez_elemente und markiert, im falle
	 * gefunden, die passenden materialspezifikationen
	 * @author martin
	 *
	 */
	private class ownSubQueryAgent_MarkSelektor_Results extends XX_ComponentMAP_SubqueryAGENT
	{
		@Override
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException {
		}

		@Override
		public void fillComponents(E2_ComponentMAP oMAP,SQLResultMAP oUsedResultMAP) throws myException {
			
			//hier den Firmenstamm-selektor aus der drunterliegenden list suchen und die dort aus selektion definierten elemente hervorheben
			if (FSMS_LIST_ComponentMAP.this.oFS_Selector_Multi_Matspez_elemente == null)
			{
				E2_RecursiveSearch_AB_Basis  oSearchSelektorButton = new E2_RecursiveSearch_AB_Basis(bibALL.get_Vector(FS_Selector_Multi_Matspez_elemente.SpecialButtonStartSelectMatSpez.class.getName()));
				if (oSearchSelektorButton.get_vAllComponents().size()>0 && oSearchSelektorButton.get_vAllComponents().get(0) instanceof 
																		FS_Selector_Multi_Matspez_elemente.SpecialButtonStartSelectMatSpez)
				{
					FSMS_LIST_ComponentMAP.this.oFS_Selector_Multi_Matspez_elemente = 
							((FS_Selector_Multi_Matspez_elemente.SpecialButtonStartSelectMatSpez)oSearchSelektorButton.get_vAllComponents().get(0)).get_SelectorThisBelongsTo();
				}
			}
			if (FSMS_LIST_ComponentMAP.this.oFS_Selector_Multi_Matspez_elemente != null)
			{
				Vector<String>  vID_MatSpezFound = FSMS_LIST_ComponentMAP.this.oFS_Selector_Multi_Matspez_elemente.get_vFound_ID_Matspez();
				GridLayoutData  glExtender = MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2,null,1);
				if (vID_MatSpezFound!=null && vID_MatSpezFound.contains(oUsedResultMAP.get_cUNFormatedROW_ID()))	{
					glExtender = MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2,new E2_ColorHelpBackground(),1);  
				}
				oMAP.get__Comp("EXTENDER").EXT().set_oLayout_ListElement(glExtender);
				((E2_ListButtonExtendDaughterObject) oMAP.get__Comp("EXTENDER")).setLayoutData(glExtender);
			}
			
		}
		
	}
	
	
	
	
}
