package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.DATUMSKORREKTUR.E2_Date_Selection_Von_Bis_Container_v2;

public abstract class RB_Date_Selection_Von_Bis extends MyE2_Grid implements IF_RB_Component_Savable{

	private RB_KF Key = null;
	
	private RB_lab vonLabel = null;
	private RB_TextField vonTF = null;

	private RB_lab bisLabel = null;
	private RB_TextField bisTF = null;

	private String str_date_von = null;
	private String str_date_bis = null;

	private MyE2_MessageVector omv = new MyE2_MessageVector();
	
	public RB_Date_Selection_Von_Bis() throws myException {
		
		super(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		this.vonTF = new RB_TextField(100, 100);
		this.vonTF.set_bEnabled_For_Edit(false);
		this.vonLabel = new RB_lab("Von:");

		this.bisTF = new RB_TextField(100, 100);
		this.bisTF.set_bEnabled_For_Edit(false);

		this.bisLabel = new RB_lab("Bis:");

		this.add(vonLabel, 	E2_INSETS.I(2,0,2,0));
		this.add(vonTF,		E2_INSETS.I(2,0,8,0));
		this.add(bisLabel, 	E2_INSETS.I(2,0,2,0));
		this.add(bisTF, 	E2_INSETS.I(2,0,8,0));
		this.add(new MyE2_Button(E2_ResourceIcon.get_RI("calendar.png"), null, new callCalendarPopUp()));
		
	}
	
	public RB_Date_Selection_Von_Bis(int tf_width) throws myException {
		super(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		this.vonTF = new RB_TextField(tf_width,100);
		this.vonTF.set_bEnabled_For_Edit(false);
		//		this.vonTF.setAlignment(newValue);
		this.vonLabel = new RB_lab("Von:");

		this.bisTF = new RB_TextField(tf_width,100);
		this.bisTF.set_bEnabled_For_Edit(false);

		this.bisLabel = new RB_lab("Bis:");

		this.add(vonLabel, 	E2_INSETS.I(2,0,2,0));
		this.add(vonTF,		E2_INSETS.I(2,0,8,0));
		this.add(bisLabel, 	E2_INSETS.I(2,0,2,0));
		this.add(bisTF, 	E2_INSETS.I(2,0,8,0));
		this.add(new MyE2_Button(E2_ResourceIcon.get_RI("calendar.png"), null, new callCalendarPopUp()));
		
	}
	
	public RB_Date_Selection_Von_Bis(String strLabel_von, String strLabel_bis) throws myException {
		super(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		this.vonTF = new RB_TextField(100,100);
		this.vonTF.set_bEnabled_For_Edit(false);
		//		this.vonTF.setAlignment(newValue);
		this.vonLabel = new RB_lab(strLabel_von);

		this.bisTF = new RB_TextField(100,100);
		this.bisTF.set_bEnabled_For_Edit(false);

		this.bisLabel = new RB_lab(strLabel_bis);

		this.add(vonLabel, 	E2_INSETS.I(2,0,8,0));
		this.add(vonTF,		E2_INSETS.I(2,0,2,0));
		this.add(bisLabel, 	E2_INSETS.I(2,0,8,0));
		this.add(bisTF, 	E2_INSETS.I(2,0,2,0));
		
		this.add(new MyE2_Button(E2_ResourceIcon.get_RI("calendar.png"), null, new callCalendarPopUp()));

	}
	
	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	public String get_von() {
		if(S.isFull(str_date_von)){
			return str_date_von.trim();
		}
		return "";	
	}

	public String get_bis() {
		if(S.isFull(str_date_bis)){
			return str_date_bis.trim();
		}
		return "";
	}

	public void set_von(String dateVonAsString) {
		this.str_date_von = dateVonAsString.trim();
		this.vonTF.setText(str_date_von);
	}

	public void set_bis(String dateBisAsString) {
		this.str_date_bis = dateBisAsString.trim();
		this.bisTF.setText(str_date_bis);
	}

	/**
	 * @return Datum in Format YYYY-MM-DD
	 */
	public String get_von_SQLFORMAT(){
		if(S.isFull(str_date_von)){
			return bibALL.BaueDatumSQL(str_date_von, "YYYY-MM-DD");
		}
		return "";
	}

	/**
	 * @return Datum in Format YYYY-MM-DD
	 */
	public String get_bis_SQLFORMAT(){
		if(S.isFull(str_date_von)){
			return bibALL.BaueDatumSQL(str_date_bis, "YYYY-MM-DD");
		} 
		return "";
	}

	/**
	 * @param dateVonAsString: <i><b>Von<b></i> Datum in FORMAT YYYY-MM-DD
	 */
	public void set_von_SQLFORMAT(String dateVonAsString) {
		if(S.isFull(dateVonAsString)){
			this.str_date_von = myDateHelper.ChangeDBFormatStringToNormalString(dateVonAsString);
			this.vonTF.setText(str_date_von);
		}
	}
	/**
	 * @param dateBisAsString: <i><b>Bis<b></i> Datum in FORMAT YYYY-MM-DD
	 */
	public void set_bis_SQLFORMAT(String dateBisAsString) {
		if(S.isFull(dateBisAsString)){
		this.str_date_bis = myDateHelper.ChangeDBFormatStringToNormalString(dateBisAsString);
		this.bisTF.setText(str_date_bis);
		}
	}

	public void set_datum_range(String datumVon, String datumBis){
		this.set_von(datumVon);
		this.set_bis(datumBis);
	}

	public void set_datum_range_SQLFORMAT(String datumVon, String datumBis){
		this.set_von_SQLFORMAT(datumVon);
		this.set_bis_SQLFORMAT(datumBis);
	}

	public RB_Date_Selection_Von_Bis _fsa(int iSize){
		this.bisLabel._fsa(iSize);
		this.vonLabel._fsa(iSize);
		this.bisTF.setFont(new E2_FontPlain(iSize));		
		this.vonTF.setFont(new E2_FontPlain(iSize));
		return this;
	}

	public RB_Date_Selection_Von_Bis _i(){
		this.bisLabel._i();
		this.vonLabel._i();
		this.bisTF.setFont(new E2_FontItalic());		
		this.vonTF.setFont(new E2_FontItalic());
		return this;
	}

	public RB_Date_Selection_Von_Bis _b(){
		this.bisLabel._b();
		this.vonLabel._b();
		this.bisTF.setFont(new E2_FontBold());		
		this.vonTF.setFont(new E2_FontBold());
		return this;
	}

	public MyE2_MessageVector getMessageVector() {
		return omv;
	}
	
	abstract public void saveDatumRange();

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return null;
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	
		
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mark_Neutral() throws myException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mark_MustField() throws myException {
		this.vonTF.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
		this.bisTF.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() throws myException {
		this.vonTF.setBackground(new E2_ColorGray(230));
		this.bisTF.setBackground(new E2_ColorGray(230));
	}

	@Override
	public void mark_FalseInput() throws myException {
		this.vonTF.setBackground(new E2_ColorHelpBackground());
		this.bisTF.setBackground(new E2_ColorHelpBackground());
	}
	
	@Override
	public void set_Alignment(Alignment align) throws myException {
		this.vonTF.setAlignment(align);	
		this.bisTF.set_Alignment(align);
	}

	@Override
	public void rb_set_belongs_to(RB_ComponentMap obj) throws myException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RB_ComponentMap rb_get_belongs_to() throws myException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RB_K getMyKeyInCollection() throws myException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RB_K setMyKeyInCollection(RB_K key) throws myException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class callCalendarPopUp extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
			
			E2_Date_Selection_Von_Bis_Container_v2 pop =new E2_Date_Selection_Von_Bis_Container_v2(str_date_von, str_date_bis) {

				@Override
				public void saveData() throws myException {
					if(this.isbOk()){
					
					str_date_von = this.getStartDate();
					str_date_bis = this.getEndDate();
					vonTF.setText(this.getStartDate());
					bisTF.setText(this.getEndDate());	
					}
				}
			};

			if(S.isFull(str_date_von) && S.isFull(str_date_bis)){
				pop.setDatumRange(new myDateHelper(str_date_von), new myDateHelper(str_date_bis));
			}

			pop.CREATE_AND_SHOW_POPUPWINDOW(new Extent(475), new Extent(325), new MyE2_String("Datumgultigkeit"));

			pop.add_CloseActions(new popUpCloseAction(pop));
		}
	}

	private class popUpCloseAction extends XX_ActionAgentWhenCloseWindow{

		private E2_Date_Selection_Von_Bis_Container_v2 oContainer;

		public popUpCloseAction(E2_Date_Selection_Von_Bis_Container_v2 container) {
			super(container);
			this.oContainer = container;

		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(this.oContainer.isbOk()){
				saveDatumRange();	
			}
		}
	}
}