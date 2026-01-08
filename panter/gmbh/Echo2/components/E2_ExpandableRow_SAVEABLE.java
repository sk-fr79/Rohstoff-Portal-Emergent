package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.XXX_UserSetting;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


/*
 * panel, das es erlaubt, bedienelemente zu gruppieren und auszublenden
 */
public class E2_ExpandableRow_SAVEABLE extends MyE2_Row  {
	
	
	public static enum SAVE_KEY {
		ADRESS_MASK_KUNDENMERKMALE_BLOCK_OPEN_CLOSE                //speichert den status der einblendung der kundenmerkmale in der adressmaske
		,
	}
	
	
	
	private		MyE2_Grid 				inner_grid = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private 	MyE2_Button				oButtonOpen = new MyE2_Button(E2_ResourceIcon.get_RI("expandopen.png"));
	private 	MyE2_Button				oButtonClose = new MyE2_Button(E2_ResourceIcon.get_RI("expandclose.png"));
	private 	MyE2_Label				label_InfoWhenClosed = new MyE2_Label(new MyE2_String("Geschlossen"));
	
	private 	Border					oBorderOpen = new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID);
	private 	Border					oBorderClosed = new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID);
	
	private 	boolean 				bOpen = true;
	private 	boolean 				bOpenButtonAtLeftSide = true;
	
	private 	SAVE_KEY 				my_save_key = null;   				
	 
	private 	Component 				f_componentToShowInExtender = null;
	
	private 	int[]                   spalten = {15,400}; 

	private     GridLayoutData     		gl_4_leftComponent = new RB_gld()._left_top()._ins(E2_INSETS.I(0,1,0,1));
	private     GridLayoutData     		gl_4_rightComponent = new RB_gld()._left_top()._ins(E2_INSETS.I(5,1,0,1));
	
	
	/**
	 * 
	 * @param oInfoWhenClosed
	 * @param componentToShowInExtender
	 * @param oBorderopen (can be null)
	 * @param oBorderclosed (can be null)
	 * @param b_openAtStart
	 * @param b_ButtonAtLeftSide
	 * @param i_spalten (can be null)
	 * @param save_key (can be null)
	 * @throws myException
	 */
	public E2_ExpandableRow_SAVEABLE(	MyE2_Label 	oInfoWhenClosed,
										Component 	componentToShowInExtender, 
										Border 		oBorderopen, 
										Border 		oBorderclosed, 
										boolean 	b_openAtStart,
										boolean 	b_ButtonAtLeftSide,
										int[]       i_spalten,
										SAVE_KEY 	save_key) throws myException
	{
		super();
		this.setInsets(E2_INSETS.I_0_0_0_0);
		this.f_componentToShowInExtender = componentToShowInExtender;
		this.my_save_key = save_key;
		
		if (oInfoWhenClosed!=null) {
			this.label_InfoWhenClosed = oInfoWhenClosed;
		}
		
		
		if (i_spalten!=null) {
			this.spalten = i_spalten;
		}
		
		this.inner_grid.set_Spalten(this.spalten);
		
		if (oBorderopen !=null) 	this.oBorderOpen = oBorderopen;
		if (oBorderclosed !=null) 	this.oBorderClosed  = oBorderclosed;

		this.oButtonClose.add_oActionAgent(new ActionClose());
		this.oButtonOpen.add_oActionAgent(new ActionOpen());
		this.oButtonClose.setStyle(new Style_Button_Image_NoBorder());
		this.oButtonOpen.setStyle(new Style_Button_Image_NoBorder());
	
		this.bOpen = b_openAtStart;
		this.bOpenButtonAtLeftSide = b_ButtonAtLeftSide;
		
		if (this.my_save_key!=null) {
			try {
				new ownUserSetting(this.my_save_key).restore_old_status();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.render_status();
	}



	private void render_status() throws myException 	{

		this.removeAll();
		this.inner_grid.removeAll();
		
		Component c1 = null;
		Component c2 = null;
		if (this.bOpenButtonAtLeftSide) {
			c1=this.bOpen?this.oButtonClose:this.oButtonOpen;
			c2=this.bOpen?this.f_componentToShowInExtender:this.label_InfoWhenClosed;
		} else {
			c2=this.bOpen?this.oButtonClose:this.oButtonOpen;
			c1=this.bOpen?this.f_componentToShowInExtender:this.label_InfoWhenClosed;
		}
		
		c1.setLayoutData(this.gl_4_leftComponent);
		c2.setLayoutData(this.gl_4_rightComponent);
		
		this.inner_grid.add(c1);
		this.inner_grid.add(c2);
		
		if (this.bOpen) {
			this.setBorder(this.oBorderOpen);
		} else {
			this.setBorder(this.oBorderClosed);
		}
		
		RowLayoutData  rl = new RowLayoutData();
		rl.setInsets(E2_INSETS.I(0,0,0,0));
		
		this.add(inner_grid,rl);
	}
	
	private void save_actual_status() throws myException {
		new ownUserSetting(this.my_save_key).save_status();
	}
	
	
	private class ActionClose extends XX_ActionAgent{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
			E2_ExpandableRow_SAVEABLE.this.bOpen = false;
			E2_ExpandableRow_SAVEABLE.this.render_status();
			E2_ExpandableRow_SAVEABLE.this.save_actual_status();		
		}
	}
	

	private class ActionOpen extends XX_ActionAgent {
		public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
			E2_ExpandableRow_SAVEABLE.this.bOpen = true;
			E2_ExpandableRow_SAVEABLE.this.render_status();
			E2_ExpandableRow_SAVEABLE.this.save_actual_status();		
		}
	}

	

	public boolean is_Open(){
		return bOpen;
	}


	public MyE2_Button get_oButtonOpen(){
		return oButtonOpen;
	}

	public MyE2_Button get_oButtonClose(){
		return oButtonClose;
	}
	
	
	
	private class Style_Button_Image_NoBorder extends MutableStyle {

		public Style_Button_Image_NoBorder()
		{
			super();
			this.setProperty( AbstractButton.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-30), Border.STYLE_NONE));
			this.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(false));
			this.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(0, Color.WHITE, Border.STYLE_NONE));

		}
		
	}

	
	public MyE2_Grid get_innerContainerGrid()	{
		return this.inner_grid;
	}
	
	
	
	private class ownUserSetting extends XXX_UserSetting {

		private String identififer = null;
		
		public ownUserSetting(SAVE_KEY key) {
			super();
			this.identififer = key.name();
		}

		@Override
		public String get_SessionHash() {
			return this.identififer;
		}

		public void restore_old_status() throws myException {
			E2_ExpandableRow_SAVEABLE.this.bOpen=(!(S.NN((String)this.get_Settings(this.identififer))).equals("N"));
		}
		
		public void save_status() throws myException {
			String value = (E2_ExpandableRow_SAVEABLE.this.bOpen?"Y":"N");
			this.STORE(this.identififer, value);
		}
		
		@Override
		protected String get_OBJECT_TO_STRING(Object oSetting) throws myException {
			return (String)oSetting;
		}

		@Override
		protected Object get_STRING_TO_OBJECT(String cDatabaseSetting) throws myException {
			return cDatabaseSetting;
		}

		
	}


	
	public GridLayoutData get_gl_4_leftComponent() {
	
		return gl_4_leftComponent;
	}



	
	public void set_gl_4_leftComponent(GridLayoutData gl_4_leftComponent) {
	
		this.gl_4_leftComponent = gl_4_leftComponent;
	}



	
	public GridLayoutData get_gl_4_rightComponent() {
	
		return gl_4_rightComponent;
	}



	
	public void set_gl_4_rightComponent(GridLayoutData gl_4_rightComponent) {
	
		this.gl_4_rightComponent = gl_4_rightComponent;
	}
	
}
