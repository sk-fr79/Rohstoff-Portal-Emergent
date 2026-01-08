/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components
 * @author manfred
 * @date 21.07.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_TEXTBLOCK_KAT;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_TEXTBLOCK;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_TEXTBLOCK_KAT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TEXTBLOCK_KAT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author manfred
 * @date 21.07.2020
 *
 */
public class E2_PopUp_For_TextBausteine extends E2_PopUp_For_LookupValue {

	String 					_sKeyTextbausteine = null;
	SqlStringExtended 		_sqlExtended = null;
	
	/**
	 * @author manfred
	 * @date 21.07.2020
	 *
	 * @throws myException
	 */
	public E2_PopUp_For_TextBausteine(String nameOfTextblockGroup) throws myException {
		super();
		_sKeyTextbausteine = nameOfTextblockGroup;
		String sql = "SELECT TEXTTITEL,TEXT FROM "+bibE2.cTO()+".JT_TEXTBLOCK,"+bibE2.cTO()+".JT_TEXTBLOCK_KAT WHERE " +
				" JT_TEXTBLOCK.ID_TEXTBLOCK_KAT=JT_TEXTBLOCK_KAT.ID_TEXTBLOCK_KAT AND UPPER(JT_TEXTBLOCK_KAT.BEZEICHNUNG)= ? ";
		
		_sqlExtended = new SqlStringExtended(sql)._addParameter(new Param_String("", _sKeyTextbausteine));
		setSQL(_sqlExtended);

		addElementAtTopOfList(get_Component_4_add_group_or_text(_sKeyTextbausteine));
		
	}
	
	@Override
	public E2_PopUp_For_TextBausteine _render() {
		return (E2_PopUp_For_TextBausteine) super._render();
	}


	@Override
	public E2_PopUp_For_TextBausteine setComponentToSetValueIn(IF_RB_Component component) {
		return (E2_PopUp_For_TextBausteine) super.setComponentToSetValueIn(component);
	}


	@Override
	public E2_PopUp_For_TextBausteine addElementAtTopOfList(Component component) {
		return (E2_PopUp_For_TextBausteine) super.addElementAtTopOfList(component);
	}


	@Override
	public E2_PopUp_For_TextBausteine addElementAtBottomOfList(Component component) {
		return (E2_PopUp_For_TextBausteine) super.addElementAtBottomOfList(component);
	}


	@Override
	public E2_PopUp_For_TextBausteine setInfoWhenEmpty(String info) {
		return (E2_PopUp_For_TextBausteine) super.setInfoWhenEmpty(info);
	}


	@Override
	public String[][] get_KeyValuePairs() {
		return super.get_KeyValuePairs();
	}


	@Override
	public String[] get_ResultKeyValue() {
		return super.get_ResultKeyValue();
	}


	@Override
	public E2_PopUp_For_TextBausteine setTooltipText(String Tooltip) {
		return (E2_PopUp_For_TextBausteine) super.setTooltipText(Tooltip);
	}


	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy {
		return super.get_Copy(ob);
	}


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(enabled);
	}


	@Override
	public void show_InputStatus(boolean bInputIsOK) {
		super.show_InputStatus(bInputIsOK);
	}


//	@Override
//	public E2_PopUp_For_LookupValue fillComponentValueFromKey(boolean Key) {
//		return super.fillComponentValueFromKey(Key);
//	}


	@Override
	public E2_PopUp_For_TextBausteine setKeyInComponent() {
		return (E2_PopUp_For_TextBausteine) super.setKeyInComponent();
	}


	@Override
	public E2_PopUp_For_TextBausteine setValueInComponent() {
		return (E2_PopUp_For_TextBausteine) super.setValueInComponent();
	}

	@Override
	public E2_PopUp_For_TextBausteine setIconActive(E2_ResourceIcon icon) {
		return (E2_PopUp_For_TextBausteine) super.setIconActive(icon);
	}
	
	@Override
	public E2_PopUp_For_TextBausteine setIconInactive(E2_ResourceIcon icon) {
		return (E2_PopUp_For_TextBausteine) super.setIconInactive(icon);
	}
	

	private Component get_Component_4_add_group_or_text(String cNameTextblockGroup)
	{
		
		if (bibALL.get_bIST_SUPERVISOR())
		{
			MyE2_Button oButton = new MyE2_Button(new MyE2_String("Erfassen",true," <",false,cNameTextblockGroup,false,">",false));
			oButton.add_oActionAgent(new ownActionAgent(cNameTextblockGroup));
			oButton.setLineWrap(true);
			oButton.setFont(new E2_FontItalic(-2));
			return oButton;
		}
		else
		{
			MyE2_Label oLabel = new MyE2_Label(new MyE2_String("Kategorie: ",true," <",false,cNameTextblockGroup,false,">",false));
			oLabel.setLineWrap(true);
			oLabel.setFont(new E2_FontItalic(-2));
			return oLabel;
		}
	}

	
	
	
	
	/**
	 * Klasse mit alten Elementen da 
	 * übernommen von MyE2_Textfield_WithSelectorEASY
	 * @date 21.07.2020
	 *
	 */
	private class ownActionAgent extends XX_ActionAgent
	{
		private String 					cKategorie = null;
		private MyE2_TextField  		oTextTitel = new MyE2_TextField("",400,30);
		private MyE2_TextArea  		 	oTextArea = new MyE2_TextArea("", 400, 500, 10);
		private RECORD_TEXTBLOCK_KAT  	recKAT = null;
		
		public ownActionAgent(String Kategorie) 
		{
			super();
			this.cKategorie = Kategorie;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			//nachsehen, ob es die kategorie gibt, wenn nein, dann anlegen
			RECLIST_TEXTBLOCK_KAT reclistKat = new RECLIST_TEXTBLOCK_KAT("SELECT * FROM "+bibE2.cTO()+".JT_TEXTBLOCK_KAT WHERE BEZEICHNUNG="+bibALL.MakeSql(this.cKategorie));
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			
			
			if (reclistKat.get_vKeyValues().size()==0)
			{
				RECORDNEW_TEXTBLOCK_KAT  recNew = new RECORDNEW_TEXTBLOCK_KAT();
				recNew.set_NEW_VALUE_BEZEICHNUNG(cKategorie);
				recNew.set_NEW_VALUE_IST_STANDARD("N");
				recKAT = recNew.do_WRITE_NEW_TEXTBLOCK_KAT(oMV);
			}
			else
			{
				recKAT = reclistKat.get(0);
			}
			
			if (oMV.get_bIsOK())
			{
				//dann einen Text erfassen
				this.oTextArea.setText("");
				this.oTextTitel.setText("");
				MyE2_Grid gridInput = new MyE2_Grid(1,0);
				gridInput.add(this.oTextTitel,E2_INSETS.I_0_0_0_1);
				gridInput.add(this.oTextArea,E2_INSETS.I_0_0_0_1);
				
				new ownMessageBox(new MyE2_String("Einen neuen Text erfassen"),
														new MyE2_String("Speichern"),
														new MyE2_String("Abbruch"),
														true,
														true,
														gridInput,
														new ownActionAgentSave(),
														new Extent(450),
														new Extent(340));
			}
			else
			{
				bibMSG.add_MESSAGE(oMV);
			}
		}
		


		/**
		 * 
		 * @date 21.07.2020
		 *
		 */
		private class ownActionAgentSave extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				if (S.isFull(oTextArea.getText()) && S.isFull(oTextTitel.getText()))
				{
					RECORDNEW_TEXTBLOCK textNew = new RECORDNEW_TEXTBLOCK();
					textNew.set_NEW_VALUE_ID_TEXTBLOCK_KAT(recKAT.get_ID_TEXTBLOCK_KAT_lValue(new Long(-1)));
					textNew.set_NEW_VALUE_TEXT(oTextArea.getText());
					textNew.set_NEW_VALUE_TEXTTITEL(oTextTitel.getText());
					textNew.set_NEW_VALUE_ISTSTANDARD("N");
					
					MyE2_MessageVector oMV = new MyE2_MessageVector();
					textNew.do_WRITE_NEW_TEXTBLOCK(oMV);
					
					if (oMV.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Text wurde geschrieben ..."));
						E2_PopUp_For_TextBausteine.this._render();
					}
					else
					{
						bibMSG.add_MESSAGE(oMV);
					}
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Ein leerer Text wird nicht gespeichert !!!"));
				}
			}
		}
		
		private class ownMessageBox extends E2_MessageBoxYesNo
		{

			public ownMessageBox(MyE2_String TextTitelZeile,
					MyE2_String TextYes, MyE2_String TextNo, boolean bShowYes,
					boolean bShowNo, MyE2IF__Component ComponentToShow,
					XX_ActionAgent ActionAgentStart, Extent iWidth,
					Extent iHeight) throws myException {
				super(TextTitelZeile, TextYes, TextNo, bShowYes, bShowNo, ComponentToShow,
						ActionAgentStart, iWidth, iHeight);
			}
		}
	}
	
	
	

}
