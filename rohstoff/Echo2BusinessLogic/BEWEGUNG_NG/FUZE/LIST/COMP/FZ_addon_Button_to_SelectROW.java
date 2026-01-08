package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.indep.bibFONT;
import panter.gmbh.indep.exceptions.myException;

public class FZ_addon_Button_to_SelectROW extends MyE2_ButtonInLIST   implements  MyE2IF_IsMarkable{

	private Font fontNormal = null;
	private MyE2IF__Component mother = null;
	
	
	public FZ_addon_Button_to_SelectROW() {
		super("");
		this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL(this.fontNormal));
	}
	
	
	public FZ_addon_Button_to_SelectROW(String s, Font font, MyE2IF__Component p_mother)	{
		super(s);
		this.mother = p_mother;
		this.add_oActionAgent(new ownActionAgent());
		this.fontNormal=(font==null?new E2_FontPlain():font);
		this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL(this.fontNormal));
		this.setLineWrap(true);
	}
	
	private class ownActionAgent extends XX_ActionAgent	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException 	{
			E2_ComponentMAP  oMap = FZ_addon_Button_to_SelectROW.this.mother.EXT().get_oComponentMAP();
			if (oMap != null)	{
				FZ_addon_Button_to_SelectROW.this.mother.EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
			}
		}
	}

	@Override
	public void make_Look_Deleted(boolean bIsDeleted) {
		bibFONT.change_fontToLineThrough(this, bIsDeleted);
	}

	@Override
	public void setForeColorActive(Color ForeColor) {
		this.setForeground(ForeColor);
	}

	@Override
	public void setFontActive(Font font) {
		this.setFont(bibFONT.equal_LineThrough_status(font, this));
	}

	@Override
	public Color get_Unmarked_ForeColor() {
		return this.getForeground();
	}

	@Override
	public Font get_Unmarked_Font() {
		return this.getFont();
	}
}
