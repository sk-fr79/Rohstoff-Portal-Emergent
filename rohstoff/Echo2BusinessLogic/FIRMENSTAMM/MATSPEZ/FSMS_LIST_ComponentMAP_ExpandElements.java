package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MAT_ELEMENT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAT_ELEMENT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_Selector_Multi_Matspez_elemente;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_Selector_Multi_Matspez_elemente.SpecialButtonStartSelectMatSpez;

public class FSMS_LIST_ComponentMAP_ExpandElements extends XX_List_EXPANDER_4_ComponentMAP {

	private String 		cLastID_MAT_SPEZ = 	null;
	private String 		cLastSort = 		null;
	private boolean 	bSortUpDown = 		true;
	
	private int[] 		iBreit = {120,70,30,70,400};

	private MyE2_Grid  oGridExpander = new MyE2_Grid(iBreit, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
	
	public FSMS_LIST_ComponentMAP_ExpandElements(E2_NavigationList NaviList) throws myException {
		super(NaviList);
		this.set_iLeftColumnOffset(3);
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new FSMS_LIST_ComponentMAP_ExpandElements(this.get_oNavigationList());
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

	@Override
	public Component get_ComponentDaughter(String cID_ROW_Unformated) throws myException {
		this.cLastID_MAT_SPEZ = cID_ROW_Unformated;
		this.cLastSort = _DB.ELEMENT$LANG;
		
		this.build_Grid();
		return oGridExpander;
	}

	
	private void build_Grid() throws myException
	{
		this.oGridExpander.removeAll();
		
		
		Vector<String>  vID_Elements_in_Selector = new Vector<String>();
		
		//hier den Firmenstamm-selektor aus der drunterliegenden list suchen und die dort aus selektion definierten elemente hervorheben
		E2_RecursiveSearch_AB_Basis  oSearchSelektorButton = new E2_RecursiveSearch_AB_Basis(bibALL.get_Vector(FS_Selector_Multi_Matspez_elemente.SpecialButtonStartSelectMatSpez.class.getName()));
		if (oSearchSelektorButton.get_vAllComponents().size()>0 && oSearchSelektorButton.get_vAllComponents().get(0) instanceof 
																		FS_Selector_Multi_Matspez_elemente.SpecialButtonStartSelectMatSpez)
		{
			vID_Elements_in_Selector.addAll(((SpecialButtonStartSelectMatSpez)oSearchSelektorButton.get_vAllComponents().get(0)).get_vIDs_Selected_Elements());
		}
		
		
		
		ownRecListElement 			rlElement = 		new ownRecListElement(this.cLastID_MAT_SPEZ);
		Vector<RECORD_MAT_ELEMENT> 	vSort = 			rlElement.GET_SORTED_VECTOR(bibALL.get_Vector(this.cLastSort), this.bSortUpDown);
			
		HashMap<String, String>  	hmVonBisDef = 		new HashMap<String, String>();
		
		hmVonBisDef.put(FSMS_Component_MASK_DAUGHTER_MAT_ELEMENT.cSelektDefAnteil[1][1], FSMS_Component_MASK_DAUGHTER_MAT_ELEMENT.cSelektDefAnteil[1][0]);
		hmVonBisDef.put(FSMS_Component_MASK_DAUGHTER_MAT_ELEMENT.cSelektDefAnteil[2][1], FSMS_Component_MASK_DAUGHTER_MAT_ELEMENT.cSelektDefAnteil[2][0]);
		hmVonBisDef.put(FSMS_Component_MASK_DAUGHTER_MAT_ELEMENT.cSelektDefAnteil[3][1], FSMS_Component_MASK_DAUGHTER_MAT_ELEMENT.cSelektDefAnteil[3][0]);
		hmVonBisDef.put(FSMS_Component_MASK_DAUGHTER_MAT_ELEMENT.cSelektDefAnteil[4][1], FSMS_Component_MASK_DAUGHTER_MAT_ELEMENT.cSelektDefAnteil[4][0]);
		
		
		GridLayoutData 				glGridLT = 			MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2);
		GridLayoutData 				glGridCT = 			MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2);
		//ueberschrift
		this.oGridExpander.add(new ownsortButton(new MyE2_String("Element"),_DB.ELEMENT$LANG), glGridLT);
		this.oGridExpander.add(new ownsortButton(new MyE2_String("Anteil"),_DB.MAT_ELEMENT$ANTEILMIN), glGridCT);
		this.oGridExpander.add(new ownsortButton(new MyE2_String("Relation"),_DB.MAT_ELEMENT$DEFANTEIL), glGridCT);
		this.oGridExpander.add(new ownsortButton(new MyE2_String("Anteil"),_DB.MAT_ELEMENT$ANTEIL), glGridCT);
		this.oGridExpander.add(new ownsortButton(new MyE2_String("Information"),_DB.MAT_ELEMENT$INFOTEXT), glGridLT);
		
		
		GridLayoutData 				glGridL = 			MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_0_2_0);
		GridLayoutData 				glGridC = 			MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_0_2_0);
		GridLayoutData 				glGridLSel = 		MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_0_2_0,new E2_ColorHelpBackground(),1,1);
		GridLayoutData 				glGridCSel = 		MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_0_2_0,new E2_ColorHelpBackground(),1,1);
		
		
		for (RECORD_MAT_ELEMENT recME: vSort) {
			/*
			 * im record sind die gejointen zusatzfelder der erweiterten recordlist
			 */
			MyRECORD  oRec = rlElement.get(recME.get_ID_MAT_ELEMENT_cUF());
			
			GridLayoutData  oGLL = glGridL;
			GridLayoutData  oGLC = glGridC;
			if (vID_Elements_in_Selector.contains(recME.get_ID_ELEMENT_cUF()))
			{
				oGLL = glGridLSel;
				oGLC = glGridCSel;
			}
			
			this.oGridExpander.add(new MyE2_Label(oRec.get_UnFormatedValue(_DB.ELEMENT$LANG,"<element>")), oGLL);
			this.oGridExpander.add(new MyE2_Label(oRec.get_FormatedValue(_DB.MAT_ELEMENT$ANTEILMIN," ")), oGLC);
			if (hmVonBisDef.containsKey(oRec.get_FormatedValue(_DB.MAT_ELEMENT$DEFANTEIL,"-"))) 	{
				this.oGridExpander.add(new MyE2_Label(S.NN(hmVonBisDef.get(oRec.get_UnFormatedValue(_DB.MAT_ELEMENT$DEFANTEIL,"-")))), oGLC);
			} else {
				this.oGridExpander.add(new MyE2_Label(" ??? "), oGLC);
			}
			this.oGridExpander.add(new MyE2_Label(oRec.get_FormatedValue(_DB.MAT_ELEMENT$ANTEIL,"")), oGLC);
			this.oGridExpander.add(new MyE2_Label(oRec.get_UnFormatedValue(_DB.MAT_ELEMENT$INFOTEXT," ")), oGLL);
		}
				
	}
	
	@Override
	public ArrayList<HashMap<String, Component>> get_ComponentListDaughter(	String cID_ROW_Unformated) throws myException {
		return null;
	}

	
	@Override
	public void refreshDaughterComponent() throws myException {
	}


	private class ownsortButton extends MyE2_Button
	{
		private String SortString = null;
		
		public ownsortButton(MyString cText, String cSortString) {
			super(cText, MyE2_Button.StyleTextButton(new E2_ColorLight(),new E2_ColorLight(),Color.BLACK,Color.DARKGRAY));
			this.SortString = cSortString;
			this.setToolTipText(new MyE2_String("Die Liste der Element sortieren ...").CTrans());
			this.add_oActionAgent(new ownActionSort());
		}
		
		private class ownActionSort extends XX_ActionAgent 		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (ownsortButton.this.SortString.equals(FSMS_LIST_ComponentMAP_ExpandElements.this.cLastSort))
				{
					FSMS_LIST_ComponentMAP_ExpandElements.this.bSortUpDown=!FSMS_LIST_ComponentMAP_ExpandElements.this.bSortUpDown;
				}
				else
				{
					FSMS_LIST_ComponentMAP_ExpandElements.this.cLastSort = ownsortButton.this.SortString;
					FSMS_LIST_ComponentMAP_ExpandElements.this.bSortUpDown = true;
				}
				
				FSMS_LIST_ComponentMAP_ExpandElements.this.build_Grid();
			}
		}
		
		
		
	}
	
	
	
	private class ownRecListElement extends RECLIST_MAT_ELEMENT
	{

		public ownRecListElement(String cID_ROW_Unformated) throws myException {
			super("SELECT "+
					_DB.MAT_ELEMENT+"."+_DB.MAT_ELEMENT$ID_MAT_ELEMENT+","+
					_DB.MAT_ELEMENT+"."+_DB.MAT_ELEMENT$ID_MANDANT+","+
					_DB.MAT_ELEMENT+"."+_DB.MAT_ELEMENT$GEAENDERT_VON+","+
					_DB.MAT_ELEMENT+"."+_DB.MAT_ELEMENT$LETZTE_AENDERUNG+","+
					_DB.MAT_ELEMENT+"."+_DB.MAT_ELEMENT$ID_ELEMENT+","+
					_DB.MAT_ELEMENT+"."+_DB.MAT_ELEMENT$ANTEIL+","+
					_DB.MAT_ELEMENT+"."+_DB.MAT_ELEMENT$INFOTEXT+","+
					_DB.MAT_ELEMENT+"."+_DB.MAT_ELEMENT$ID_MAT_SPEZ+","+
					_DB.MAT_ELEMENT+"."+_DB.MAT_ELEMENT$DEFANTEIL+","+
					_DB.MAT_ELEMENT+"."+_DB.MAT_ELEMENT$ANTEILMIN+","+
					_DB.ELEMENT+"."+_DB.ELEMENT$KURZ+","+
					_DB.ELEMENT+"."+_DB.ELEMENT$LANG+
					" FROM "+bibE2.cTO()+"."+_DB.MAT_ELEMENT+" "+
					" INNER JOIN "+_DB.ELEMENT+" ON ("+_DB.MAT_ELEMENT+"."+_DB.MAT_ELEMENT$ID_ELEMENT+"="+_DB.ELEMENT+"."+_DB.ELEMENT$ID_ELEMENT+")"+" WHERE "+_DB.MAT_ELEMENT+"."+"ID_MAT_SPEZ="+cID_ROW_Unformated);
		}
		
	}
	
	
}
