package panter.gmbh.Echo2.components.messageBox;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorE2String;

public class E2_MessageBoxBasic extends E2_MessageBox {

	private GridLayoutData layoutData4Message = null;
	
	
	public E2_MessageBoxBasic(	MyE2_String titlebar, VectorE2String body, GridLayoutData p_layoutData4Message, int iBreite, int iHoehe) throws myException {
		super();
		this.set_titel(titlebar==null?new MyE2_String("Bitte wählen: "):titlebar);
		for (MyE2_String s: body) {
			this.a(s);
		}
		
		this.layoutData4Message=p_layoutData4Message;
		if (this.layoutData4Message==null) {
			this.layoutData4Message=	new RB_gld()._center_top()._span(2);
		}
		
		this.get_buttonOK().set_Text(new MyE2_String("OK"));
		this.get_buttonCancel().set_Text(new MyE2_String("Abbruch"));
		
		this.render_message_box();
		
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(iBreite), new Extent(iHoehe), titlebar);
	}

	@Override
	public void render_message_box() throws myException {
		
		MyE2_Grid  grid = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		for (MyE2_String s: this.get_infos()) {
			grid._add(new MyE2_Label(s), this.layoutData4Message);
		}
		
		grid._add(new E2_Grid4MaskSimple()._add(this.get_buttonOK(), new RB_gld()._ins(E2_INSETS.I(0,0,0,0)))._add(this.get_buttonCancel() , new RB_gld()._ins(E2_INSETS.I(10,0,0,0))), new RB_gld()._ins(E2_INSETS.I(0,10,0,0)));

		grid.set_Spalten(bibARR.ia(200,200));

		this.add(grid,E2_INSETS.I(10,10,10,10));
		
		
		this.get_buttonOK().setStyle(MyE2_Button.StyleTextButtonSTD(new E2_FontBold()));
		this.get_buttonCancel().setStyle(MyE2_Button.StyleTextButtonSTD(new E2_FontBold()));

		this.get_buttonOK().add_oActionAgent(new ownActionClose(true));
		this.get_buttonCancel().add_oActionAgent(new ownActionClose(false));
	}
	

	private class ownActionClose extends XX_ActionAgent {
		private boolean ok_or_cancel = true;
		
		public ownActionClose(boolean ok) {
			super();
			this.ok_or_cancel=ok;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_MessageBoxBasic.this.CLOSE_AND_DESTROY_POPUPWINDOW(this.ok_or_cancel);
		}
	}
	
	
}
