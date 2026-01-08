package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.DATUMSKORREKTUR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_Date_Selection_Von_Bis_TF extends MyE2_Grid {

	private RB_lab vonLabel = null;
	private MyE2_TextField vonTF = null;

	private RB_lab bisLabel = null;
	private MyE2_TextField bisTF = null;

	private String str_date_von = null;
	private String str_date_bis = null;

	private MyE2_MessageVector omv = new MyE2_MessageVector();
	


	public E2_Date_Selection_Von_Bis_TF() throws myException {
		super(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		this.vonTF = new MyE2_TextField("", 100,100);
		this.vonTF.set_bEnabled_For_Edit(false);
		//		this.vonTF.setAlignment(newValue);
		this.vonLabel = new RB_lab("Von:");

		this.bisTF = new MyE2_TextField("", 100 ,100);
		this.bisTF.set_bEnabled_For_Edit(false);

		this.bisLabel = new RB_lab("Bis:");

		this.add(vonLabel, 	E2_INSETS.I(2,0,2,0));
		this.add(vonTF,		E2_INSETS.I(2,0,8,0));
		this.add(bisLabel, 	E2_INSETS.I(2,0,2,0));
		this.add(bisTF, 	E2_INSETS.I(2,0,8,0));
		this.add(new MyE2_Button(E2_ResourceIcon.get_RI("calendar.png"), null, new callCalendarPopUp()));
		
	}
	
	public E2_Date_Selection_Von_Bis_TF(int tf_width) throws myException {
		super(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		this.vonTF = new MyE2_TextField("", tf_width,100);
		this.vonTF.set_bEnabled_For_Edit(false);
		//		this.vonTF.setAlignment(newValue);
		this.vonLabel = new RB_lab("Von:");

		this.bisTF = new MyE2_TextField("", tf_width,100);
		this.bisTF.set_bEnabled_For_Edit(false);

		this.bisLabel = new RB_lab("Bis:");

		this.add(vonLabel, 	E2_INSETS.I(2,0,2,0));
		this.add(vonTF,		E2_INSETS.I(2,0,8,0));
		this.add(bisLabel, 	E2_INSETS.I(2,0,2,0));
		this.add(bisTF, 	E2_INSETS.I(2,0,8,0));
		this.add(new MyE2_Button(E2_ResourceIcon.get_RI("calendar.png"), null, new callCalendarPopUp()));
		
	}
	
	public E2_Date_Selection_Von_Bis_TF(String strLabel_von, String strLabel_bis) throws myException {
		super(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		this.vonTF = new MyE2_TextField("", 100,100);
		this.vonTF.set_bEnabled_For_Edit(false);
		//		this.vonTF.setAlignment(newValue);
		this.vonLabel = new RB_lab(strLabel_von);

		this.bisTF = new MyE2_TextField("", 100,100);
		this.bisTF.set_bEnabled_For_Edit(false);

		this.bisLabel = new RB_lab(strLabel_bis);

		this.add(vonLabel, 	E2_INSETS.I(2,0,8,0));
		this.add(vonTF,		E2_INSETS.I(2,0,2,0));
		this.add(bisLabel, 	E2_INSETS.I(2,0,8,0));
		this.add(bisTF, 	E2_INSETS.I(2,0,2,0));
		
		this.add(new MyE2_Button(E2_ResourceIcon.get_RI("calendar.png"), null, new callCalendarPopUp()));

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

	public E2_Date_Selection_Von_Bis_TF _fsa(int iSize){
		this.bisLabel._fsa(iSize);
		this.vonLabel._fsa(iSize);
		this.bisTF.setFont(new E2_FontPlain(iSize));		
		this.vonTF.setFont(new E2_FontPlain(iSize));
		return this;
	}

	public E2_Date_Selection_Von_Bis_TF _i(){
		this.bisLabel._i();
		this.vonLabel._i();
		this.bisTF.setFont(new E2_FontItalic());		
		this.vonTF.setFont(new E2_FontItalic());
		return this;
	}

	public E2_Date_Selection_Von_Bis_TF _b(){
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