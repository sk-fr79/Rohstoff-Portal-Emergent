package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorter;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_onlyWhenVisisble;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibFONT;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.RECORD_BEWEGUNG_VEKTOR_SPEC;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;

public class FZ_LIST_comp_ContainerFuhreJump extends MyE2_DB_PlaceHolder_onlyWhenVisisble implements MyE2IF_IsMarkable {

	private E2_NavigationList 				navilist = null;
	private ownButtonLikeLabel_in_row 		buttonFuhreId	= new ownButtonLikeLabel_in_row(new MyE2_String("")); 
	
	private static int[] breite = {60,20};
	
	public FZ_LIST_comp_ContainerFuhreJump(SQLField osqlField, E2_NavigationList p_navilist) throws myException {
		super(osqlField);
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.set_Spalten(FZ_LIST_comp_ContainerFuhreJump.breite); 
		this.navilist = p_navilist;
		MyE2_Grid oGrid4titel = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGrid4titel.add(new E2_ButtonListSorter(new MyE2_String("Fuhre"),
												VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.t().s()+" DESC",
												VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.t().s()+" ASC",
												this.navilist,false), E2_INSETS.I(0,0,2,0));
		oGrid4titel.add(new FZ_LIST_comp_Jump_to_fuhre(this.navilist));
		this.EXT().set_oCompTitleInList(oGrid4titel);
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		RECORD_BEWEGUNG_VEKTOR_SPEC recRow = (RECORD_BEWEGUNG_VEKTOR_SPEC)this.EXT().get_oComponentMAP().get_Record4MainTable();

		this.removeAll();
		
		if (S.isFull(recRow.get__id_vpos_tpa_fuhre())) {
			this.buttonFuhreId = new ownButtonLikeLabel_in_row(new MyE2_String(recRow.get_UP_RECORD_BEWEGUNG_id_bewegung().get_ID_VPOS_TPA_FUHRE_cF_NN("")));
			
			this.add(this.buttonFuhreId,FZ__CONST.gl);
			this.add(new FZ_LIST_comp_Jump_to_fuhre(recRow.get__id_vpos_tpa_fuhre()),FZ__CONST.gl);
		} else {
			this.buttonFuhreId  = new ownButtonLikeLabel_in_row(new MyE2_String(""));
			this.add(new MyE2_Label(""));
		}
		
	}

	
	private class ownButtonLikeLabel_in_row extends MyE2_Button  {
		public ownButtonLikeLabel_in_row(MyE2_String  s)	{
			super(s);
			this.add_oActionAgent(new ownActionAgent());
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL());
		}
		
		private class ownActionAgent extends XX_ActionAgent	{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException 	{
				E2_ComponentMAP  oMap = FZ_LIST_comp_ContainerFuhreJump.this.EXT().get_oComponentMAP();
				if (oMap != null)	{
					FZ_LIST_comp_ContainerFuhreJump.this.EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
				}
			}
		}
	}
	
	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy 	{
		try {
			return new FZ_LIST_comp_ContainerFuhreJump(this.EXT_DB().get_oSQLField(),this.navilist);
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

	
	
	
	private class FZ_LIST_comp_Jump_to_fuhre extends MyE2_Button {

		private String 									id_vpos_tpa_fuhre = null;
		private E2_NavigationList 						navi_list = null;
		
		public FZ_LIST_comp_Jump_to_fuhre(String p_id_vpos_tpa_fuhre) throws myException 	{
			super(E2_ResourceIcon.get_RI("kompass_fuhre.png"));
			this.setToolTipText(new MyE2_String("Sprung zur Fuhre, die dieser Warenbewegung zugrundeliegt ...").CTrans());
			
			this.id_vpos_tpa_fuhre = p_id_vpos_tpa_fuhre;
			
			this.add_oActionAgent(new ownActionZuAllenFuhren());
		}


		public FZ_LIST_comp_Jump_to_fuhre(E2_NavigationList p_navilist) throws myException 	{
			super(E2_ResourceIcon.get_RI("kompass_fuhre.png"));
			this.setToolTipText(new MyE2_String("Sprung zur Fuhre, dene die ausgewählten Warenbewegungen zugrundeliegen ...").CTrans());
			
			this.navi_list = p_navilist;
			
			this.add_oActionAgent(new ownActionZuAllenFuhren());
		}


		
		private class ownActionZuAllenFuhren extends XX_ActionAgentJumpToTargetList 	{
			
			public ownActionZuAllenFuhren() throws myException {
				super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Zeige die korrellierenden Fuhren");
			}
			
			@Override
			public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException	{
				FZ_LIST_comp_Jump_to_fuhre oThis = FZ_LIST_comp_Jump_to_fuhre.this;
				
				VectorSingle  vPOS = new VectorSingle();
				
				if (oThis.navi_list != null) {
					for (String id_vector: oThis.navi_list.get_vSelectedIDs_Unformated()) {
						RECORD_BEWEGUNG_VEKTOR_SPEC rv = new RECORD_BEWEGUNG_VEKTOR_SPEC(new RECORD_BEWEGUNG_VEKTOR(id_vector));
						if (S.isFull(rv.get__id_vpos_tpa_fuhre())) {
							vPOS.add(rv.get__id_vpos_tpa_fuhre());
						}
					}
				} else {
					vPOS.add(FZ_LIST_comp_Jump_to_fuhre.this.id_vpos_tpa_fuhre);
				}
				return vPOS;
			}
			
			
			@Override
			public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException {
				MyE2_MessageVector  oMV = new MyE2_MessageVector();
				
				if (vTargetList.size()==0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
				}
				return oMV;
			}
		}
	}


	@Override
	public void make_Look_Deleted(boolean bIsDeleted) {
		bibFONT.change_fontToLineThrough(this.buttonFuhreId, bIsDeleted);
	}

	@Override
	public void setForeColorActive(Color ForeColor) {
		this.buttonFuhreId.setForeground(ForeColor);
	}

	@Override
	public void setFontActive(Font font) {
		this.buttonFuhreId.setFont(bibFONT.equal_LineThrough_status(font, this.buttonFuhreId));
	}

	@Override
	public Color get_Unmarked_ForeColor() {
		if (this.buttonFuhreId.getForeground()==null) {
			return Color.BLACK;
		}
		return this.buttonFuhreId.getForeground();
	}

	@Override
	public Font get_Unmarked_Font() {
		if (this.buttonFuhreId.getFont()==null) {
			return new E2_FontPlain();
		}
		return this.buttonFuhreId.getFont();
	}
	
}
