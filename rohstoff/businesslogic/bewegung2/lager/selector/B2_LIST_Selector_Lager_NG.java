package rohstoff.businesslogic.bewegung2.lager.selector;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_EXT;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_Neutralisator;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.EnSortStatusButtonGrid;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.ResultButtons;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.businesslogic.bewegung2.list.listSelector.IF_CanBePopulated_NG;

public class B2_LIST_Selector_Lager_NG extends XX_ListSelektor_EXT implements IF_CanBePopulated_NG, IF_CanBeDeactivate
{

	public static String TRENNZEICHEN_FUER_SPEICHER="@";


	private String 							cWhereStringSchablone 		= "";
	private HashMap<String, String>         hmValuePlusWhereblock		= new HashMap<String, String>();

	private MyE2_SelectField                oSelFieldBasis 				= null;
	private LinkedHashMap<String, RB_cb> 	vSelectFieldZusatz 			= new LinkedHashMap<String, RB_cb>();
	private VEK<String> 					vSelectFieldsZusatzWerte 	= new VEK<String>();


	private E2_BasicModuleContainer 		popUpContainer 					= new E2_BasicModuleContainer();
	private E2_Grid    		            	grid4Anzeige 					= new E2_Grid();
	private E2_Grid 						oMultiSelektorGrid				= new E2_Grid();
	private E2_Grid 						internalSelectionHelpToolsGrid 	= new E2_Grid();     

	private ownButtonOpenMultiSelectPopup 	oButtonOpenMultiSelectPopup = new ownButtonOpenMultiSelectPopup();

	private XX_ActionAgent   				oAgent4Select = null;

	private boolean    						bCopyFontAndSizeOfOrigSelectField = false;

	private Extent  						extOfSelectComponentDropDown = new Extent(510);

	private ResultButtons   				resultButtons = null;
	private VEK<String>						sortButtonTextsHeaders = new VEK<>();
	private VEK<ownSortButtonForResults>	sortButtonsForResults = new VEK<>();


	private RecList21 vLagerAdressen = new RecList21(_TAB.adresse);


	public B2_LIST_Selector_Lager_NG() throws myException	{
		super();

		this.INIT("trululu=#WERT#");
	}

	public B2_LIST_Selector_Lager_NG(String WhereStringSchablone, int oSelFieldWidth) throws myException	{
		super();

		this.extOfSelectComponentDropDown = new Extent(oSelFieldWidth);

		this.INIT(WhereStringSchablone);

		//		populate(new VEK<String>()._a());
	}

	public void  INIT(String WhereStringSchablone) throws myException	{
		this.grid4Anzeige._s(1)._bo_no();

		this.oSelFieldBasis = new MyE2_SelectField();
		this.oSelFieldBasis.set_ListenInhalt(new String[][]{{"-",""}}, false);
		this.oSelFieldBasis.setWidth(extOfSelectComponentDropDown);
		this.cWhereStringSchablone = WhereStringSchablone;

		if (this.cWhereStringSchablone!=null && this.cWhereStringSchablone.indexOf("#WERT#")<0)	{
			throw new myException("E2_ListSelectorMultiDropDown2: Constuctor ! please put the string <#WERT#> to the query-block");
		}

		if (S.isFull(this.oSelFieldBasis.get_oDataToView().get_cValueAtPosition(0))) {
			throw new myException("E2_ListSelectorMultiDropDown:Constuctor ! please put an empty value in front !!");
		}

		set_oNeutralisator(new ownSelectFieldNeutralisator_1st_is_neutral());

		fill_Grid4AnzeigeStatusSingle();
	}

	public MyE2_SelectField get_oSelFieldBasis() {
		return oSelFieldBasis;
	}

	public E2_Button get_oButtonOpenMultiSelectPopup(){
		return oButtonOpenMultiSelectPopup;
	}

	public E2_Grid get_grid4Anzeige(){
		return grid4Anzeige;
	}

	private class ownSelectFieldNeutralisator_1st_is_neutral extends XX_ListSelektor_Neutralisator {
		@Override
		public void set_to_Neutral() throws myException	{
			B2_LIST_Selector_Lager_NG.this.LEER_MACHEN();
		}
	}

	public VEK<String> get_SelectedValues() throws myException {
		VEK<String> vRet = new VEK<String>();

		if (!bibALL.isEmpty(this.oSelFieldBasis.get_ActualWert()) )  {
			vRet.add( this.oSelFieldBasis.get_ActualWert() ) ;
		}

		if (this.vSelectFieldsZusatzWerte.size() > 0)	{
			for (String cWert:this.vSelectFieldsZusatzWerte) {
				vRet._addIfNotIN(cWert);
			}
		}
		return vRet;
	}

	@Override
	public String get_WhereBlock() throws myException {
		String cWhere = "";
		if (this.vSelectFieldsZusatzWerte.size()==0) {
			cWhere = "(" +this.get_WhereBlockSingleStatement(this.oSelFieldBasis.get_ActualWert())+")";
		} else	{
			String fieldname = this.cWhereStringSchablone.replaceAll("="," IN ");
			String werte_liste ="";
			if (S.isFull(fieldname)) {
				VEK<String> vWert = this.vSelectFieldsZusatzWerte;
				werte_liste = "(" + S.Concatenate(vWert._addInFront("-1"),",","")+")";
				cWhere = "(" + bibALL.ReplaceTeilString(fieldname,"#WERT#",werte_liste) + ")";
			}

		}
		return cWhere;
	}


	private String get_WhereBlockSingleStatement(String cWert) throws myException {
		String cWhere = "";
		if (S.isFull(cWert)) {
			if (this.cWhereStringSchablone == null && this.hmValuePlusWhereblock.size()>0) {
				cWhere = this.hmValuePlusWhereblock.get(cWert);
			} else {
				//klassisch via eingelagertem #WERT#
				cWhere = bibALL.ReplaceTeilString(this.cWhereStringSchablone,"#WERT#",cWert);
			}
		}else {
			cWhere = "1=1";
		}
		return cWhere;
	}

	@Override
	public Component get_oComponentWithoutText() {
		return this.grid4Anzeige;
	}

	public VEK<String> getSortButtonTextsHeaders() {
		return sortButtonTextsHeaders;
	}

	public void setEnabled(boolean bEnabled) throws myException{
		this.oSelFieldBasis.set_bEnabled_For_Edit(bEnabled);
		this.oButtonOpenMultiSelectPopup.set_bEnabled_For_Edit(bEnabled);
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {

		this.oAgent4Select=oAgent;
		this.oSelFieldBasis.remove_AllActionAgents();

		for (XX_ActionAgent a : this.get_vAgents4ActiveComponentsBeforeSelection()){
			this.oSelFieldBasis.add_oActionAgent(a);
		}

		this.oSelFieldBasis.add_oActionAgent(oAgent);

		for (XX_ActionAgent a2 : this.get_vAgents4ActiveComponentsAfterSelection()){
			this.oSelFieldBasis.add_oActionAgent(a2);
		}
	}

	@Override
	public void doInternalCheck()	{}

	public void LEER_MACHEN() throws myException
	{
		this.vSelectFieldsZusatzWerte._clear();
		this.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(0);
		this.oSelFieldBasis.cleanFromShadow();
		this.oSelFieldBasis.set_ActiveValue_OR_FirstValue(null);
		this.oSelFieldBasis.set_bEnabled_For_Edit(true);
		this.fill_Grid4AnzeigeStatusSingle();
	}

	public void preloadAdressen(String cFirmaAdresseID)throws myException{

		if(S.isFull(cFirmaAdresseID)) {

			SEL hauptadresse_sel= new SEL("JT_ADRESSE.*")
					.FROM(_TAB.adresse)
					.WHERE(new vgl(ADRESSE.id_adresse, cFirmaAdresseID ));

			SEL lieferadresse_sel = 
					new SEL("JT_ADRESSE.*")
					.FROM(_TAB.adresse)
					.WHERE(ADRESSE.id_adresse.tnfn(), COMP.IN.ausdruck(), "("+
							(new SEL(LIEFERADRESSE.id_adresse_liefer)
									.FROM(_TAB.lieferadresse)
									.WHERE(new vgl(LIEFERADRESSE.id_adresse_basis, ""+cFirmaAdresseID))
									.AND(LIEFERADRESSE.id_adresse_liefer,COMP.GE.ausdruck(), "1000")   // Manfred: wegen Sonderlager sinnvoll
									.s()
									+")")

							).ORDER("JT_ADRESSE.aktiv DESC, JT_ADRESSE.ORT, JT_ADRESSE.NAME1, JT_ADRESSE.NAME2 ASC")	;

			SEL sonderlager_sel = new SEL("JT_ADRESSE.*")
					.FROM(_TAB.adresse)
					.WHERE(new vgl(ADRESSE.id_adresse, bibSES.get_ID_ADRESSE_LAGER_STRECKE() ));	

			for(Rec21 rec : new RecList21(_TAB.adresse)._fill(hauptadresse_sel.s())) {
				this.vLagerAdressen._add(rec);	
			}
			for(Rec21 rec : new RecList21(_TAB.adresse)._fill(lieferadresse_sel.s())){
				this.vLagerAdressen._add(rec);	
			}
			if(cFirmaAdresseID.equals(bibALL.get_ID_ADRESS_MANDANT())) {
				for(Rec21 rec : new RecList21(_TAB.adresse)._fill(sonderlager_sel.s())) {
					this.vLagerAdressen._add(rec);	
				}
			}
		}

	}

	private class ownButtonOpenMultiSelectPopup extends E2_Button
	{
		private int iAnzahlZusatzSelektionen = 0;


		public ownButtonOpenMultiSelectPopup()
		{
			super();
			this._t("0")._font(new E2_FontBold())._width(20);
			this.set_StatusHasMultiSelect(0);
			this._standard_text_button();	
			this._aaa(new actionAgentCreatePopup());

		}

		public void set_StatusHasMultiSelect(int anzahlZusatzWerte)   //anzahlZusatzWerte kann nur null oder >=2 werden
		{
			B2_LIST_Selector_Lager_NG oThis = B2_LIST_Selector_Lager_NG.this;

			this.iAnzahlZusatzSelektionen = anzahlZusatzWerte;

			if (this.iAnzahlZusatzSelektionen==0)
			{
				this.setForeground(Color.BLACK);
				this.setBackground(new E2_ColorDark());
			}
			else
			{
				this.setForeground(Color.BLACK);
				this.setBackground(new E2_ColorHelpBackground());
			}

			this.setText(""+this.iAnzahlZusatzSelektionen+"");
			this.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));

			String cToolTipText = S.ms("Die Auswahl auf weitere Bereiche erweitern").CTrans();

			if (anzahlZusatzWerte>=2)   //dann die selektionswahl im tooltip anzeigen
			{
				cToolTipText = S.ms("Erweiterte Auswahl:").CTrans() +" \n\n";
				for (int i=0;i<oThis.vSelectFieldsZusatzWerte.size();i++)
				{
					cToolTipText = cToolTipText+oThis.oSelFieldBasis.get_DataToView().get_ViewStringToData(oThis.vSelectFieldsZusatzWerte.get(i), false)+"\n";
				}
			}

			this.setToolTipText(cToolTipText);


		}
	}

	private class actionAgentCreatePopup extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			popUpContainer.removeAll();

			oMultiSelektorGrid._s(1)._bo_no()._clear(); 

			E2_Button btSpeichern = new E2_Button()
					._t("OK")
					._ttt(S.ms("Auswahl speichern und Mehrfachselektion ausführen"))
					._s_BorderTextCentered()._font(new E2_FontBold());

			E2_Button btAbbruch = new E2_Button()
					._t("Abbrechen")
					._ttt(S.ms("Auswahl abbrechen, nichts verändern"))
					._s_BorderTextCentered();
			btAbbruch._aaa(()->cancelListe());

			E2_Button btClear = new E2_Button()
					._t("Selektion löschen")
					._ttt(S.ms("Alle Felder löschen"))
					._s_BorderTextCentered()
					._image(E2_ResourceIcon.get_RI("multi_select_delete.png"));
			btClear._aaa(()-> clearMultiSelect() );

			for (XX_ActionAgent a : B2_LIST_Selector_Lager_NG.this.get_vAgents4ActiveComponentsBeforeSelection()){
				btClear._aaa(a);
			}
			btClear._aaa(B2_LIST_Selector_Lager_NG.this.oAgent4Select);
			for (XX_ActionAgent a2 :B2_LIST_Selector_Lager_NG.this.get_vAgents4ActiveComponentsAfterSelection()){
				btClear._aaa(a2);
			}

			for (XX_ActionAgent a3 : B2_LIST_Selector_Lager_NG.this.get_vAgents4ActiveComponentsBeforeSelection()){
				btSpeichern._aaa(a3);
			}
			btSpeichern.add_oActionAgent(B2_LIST_Selector_Lager_NG.this.oAgent4Select);
			for (XX_ActionAgent a4 :B2_LIST_Selector_Lager_NG.this.get_vAgents4ActiveComponentsAfterSelection()){
				btSpeichern._aaa(a4);
			}
			btSpeichern._aaaInFront(()->saveList(), true);

			int iBreiteButtons[] = {80,150,120};

			fill_multiSelektorGrid();

			E2_Button alles_selektieren_bt 		= new E2_Button()._t("Alle Lager")				._aaa(()->selectAllLager(false))._standard_text_button()._insets(2,0,2,0);
			E2_Button lager_plus_strecke_bt	 	= new E2_Button()._t("Alle Lager+Streckenlager")._aaa(()->selectAllLager(true))._standard_text_button()._insets(2,0,2,0);
			E2_Button sonderlager_bt			= new E2_Button()._t("Nur sonderlager")			._aaa(()->selectSonderLager())._standard_text_button()._insets(2,0,2,0);
			E2_Button physikalisch_bt 			= new E2_Button()._t("Nur physikalisch Lager")	._aaa(()->selectPhysikalischLager())._standard_text_button()._insets(2,0,2,0);

			getInternalSelectionHelpToolsGrid()._clear()._s(4)
			._a(alles_selektieren_bt, 	new RB_gld()._ins(4,1,4,1)._center_mid())
			._a(lager_plus_strecke_bt, 	new RB_gld()._ins(4,1,4,1)._center_mid())
			._a(sonderlager_bt, 		new RB_gld()._ins(4,1,4,1)._center_mid())
			._a(physikalisch_bt, 		new RB_gld()._ins(4,1,4,1)._center_mid());

			if(S.isFull(oSelFieldBasis.get_ActualWert())) {
				vSelectFieldZusatz.get(oSelFieldBasis.get_ActualWert()).setSelected(true);
			}

			if(vSelectFieldsZusatzWerte.size()>0) {
				for( String oSelectedId : vSelectFieldsZusatzWerte) {
					if(!oSelectedId.equals("-1")) {
						vSelectFieldZusatz.get(oSelectedId).setSelected(true);
					}
				}
			}

			getSortButtonTextsHeaders()._clear()._a("Land")._a("Ort")._a("Name")._a("Aktiv")._a("ID");

			int iHeight = 500;

			MyE2_ContainerEx oContainerEx = new MyE2_ContainerEx(oMultiSelektorGrid);
			oContainerEx.setHeight(new Extent((iHeight*65)/100));
			popUpContainer.RESET_Content();
			popUpContainer.add(internalSelectionHelpToolsGrid, E2_INSETS.I_5_0_5_0);
			popUpContainer.add(new E2_Button()._image("checkbox_quartett_invert.png")._aaa(()->invertselection()), E2_INSETS.I(10,2,2,2));

			popUpContainer.add(fill_multiSelektor_headerGrid(), E2_INSETS.I_5_0_5_0);
			popUpContainer.add(oContainerEx, E2_INSETS.I_5_0_5_0);
			popUpContainer.add(new E2_ComponentGroupHorizontal_NG(btSpeichern,btClear,btAbbruch,iBreiteButtons),new Insets(5,10,5,5));

			popUpContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(iHeight), new MyE2_String("Weitere Bereiche zufügen .."));

		}

		private void invertselection() throws myException{
			for (RB_cb  ocb: vSelectFieldZusatz.values()) {
				ocb.setSelected(!ocb.isSelected());
			}
		}

		private void selectAllLager(boolean auch_sonderlager) throws myException {
			for (RB_cb  ocb: vSelectFieldZusatz.values()) {
				ocb.setSelected(false);
			}
			for (RB_cb  ocb: vSelectFieldZusatz.values()) {
				Rec21_adresse rec_adresse = (Rec21_adresse)ocb.EXT().get_O_PLACE_FOR_EVERYTHING();
				if(rec_adresse.is_sonderlager()) {
					if(auch_sonderlager) {
						ocb.setSelected(true);
					}
				}else {
					ocb.setSelected(true);
				}
			}
		}

		private void selectPhysikalischLager() throws myException {
			for (RB_cb  ocb: vSelectFieldZusatz.values()) {
				ocb.setSelected(false);
			}
			for (RB_cb  ocb: vSelectFieldZusatz.values()) {
				Rec21_adresse rec_adresse = (Rec21_adresse)ocb.EXT().get_O_PLACE_FOR_EVERYTHING();
				if(! rec_adresse.is_sonderlager() && rec_adresse.is_yes_db_val(ADRESSE.aktiv)) {
					ocb.setSelected(true);
				}
			}
		}

		private void selectSonderLager() throws myException {
			for (RB_cb  ocb: vSelectFieldZusatz.values()) {
				ocb.setSelected(false);
			}
			for (RB_cb  ocb: vSelectFieldZusatz.values()) {
				Rec21_adresse rec_adresse = (Rec21_adresse)ocb.EXT().get_O_PLACE_FOR_EVERYTHING();
				if(rec_adresse.is_sonderlager()) {
					ocb.setSelected(true);
				}
			}
		}

		private void saveList() throws myException {
			B2_LIST_Selector_Lager_NG 	oThis 			= 	B2_LIST_Selector_Lager_NG.this;
			VEK<String>  				vZusatzWerte 	= new VEK<String>();

			for (RB_cb  ocb: vSelectFieldZusatz.values())
			{
				if (ocb.isSelected())
				{
					vZusatzWerte._addIfNotIN(ocb.EXT().get_C_MERKMAL());
				}
			}

			oThis.vSelectFieldsZusatzWerte.removeAllElements();
			if (vZusatzWerte.size()==0)
			{
				oThis.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(0);
				oThis.oSelFieldBasis.set_ActiveValue_OR_FirstValue(null);
				oThis.oSelFieldBasis.set_bEnabled_For_Edit(true);
				oThis.fill_Grid4AnzeigeStatusSingle();
			}
			else if (vZusatzWerte.size()==1)
			{
				oThis.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(0);
				oThis.oSelFieldBasis.set_ActiveValue(vZusatzWerte.get(0));
				oThis.oSelFieldBasis.set_bEnabled_For_Edit(true);
				oThis.fill_Grid4AnzeigeStatusSingle();
			}
			else
			{
				oThis.vSelectFieldsZusatzWerte.addAll(vZusatzWerte);
				oThis.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(vZusatzWerte.size());
				oThis.oSelFieldBasis.set_ActiveValue_OR_FirstValue(null);
				oThis.oSelFieldBasis.set_bEnabled_For_Edit(false);
				oThis.fill_Grid4AnzeigeStatusMulti();
			}

			getPopUpContainer().CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}



		private void clearMultiSelect() throws myException {
			B2_LIST_Selector_Lager_NG.this.LEER_MACHEN();
			B2_LIST_Selector_Lager_NG.this.fill_Grid4AnzeigeStatusSingle();
			getPopUpContainer().CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}


		private void cancelListe() throws myException {
			getPopUpContainer().CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}

	}

	private E2_Grid fill_multiSelektor_headerGrid() throws myException{
		RB_gld gl_lt = new RB_gld()._left_top()._ins(1);
		E2_Grid grd = new E2_Grid()._clear()._setSize(30,70,200,380,75,75)._a("");



		for(int i=0;i<sortButtonTextsHeaders.size();i++) {
			grd._a(sortButtonsForResults._ar(new ownSortButtonForResults(i, S.ms(sortButtonTextsHeaders.get(i)),  E2_Button.baseStyleBold()) 
					), gl_lt._c());
		}
		return grd;
	}


	public void fill_multiSelektorGrid() throws myException
	{
		E2_Grid grid_in_popUp = this.getMultiSelektorGrid();

		grid_in_popUp._bo_no()._setSize(30,70,200,380,75,75).removeAll();

		if(resultButtons == null) {
			resultButtons = new ResultButtons("");
			resultButtons = create_result_buttons(grid_in_popUp);
		}

		for(ResultButton[] bt: resultButtons) {

			RB_cb cbAdresse = new RB_cb()._b();
			cbAdresse.EXT().set_C_MERKMAL(new Rec21_adresse(bt[0].rec21).getUfs(ADRESSE.id_adresse));
			cbAdresse.EXT().set_O_PLACE_FOR_EVERYTHING(new Rec21_adresse(bt[0].rec21));
			this.vSelectFieldZusatz.put(new Rec21_adresse(bt[0].rec21).getUfs(ADRESSE.id_adresse), cbAdresse);

			grid_in_popUp
			._a(cbAdresse)
			._a(bt[0], 	new RB_gld()._ins(2, 1, 2, 1))
			._a(bt[1], 				new RB_gld()._ins(2, 1, 2, 1))
			._a(bt[2], 				new RB_gld()._ins(2, 1, 2, 1))
			._a(bt[3], 				new RB_gld()._ins(2, 1, 2, 1))
			._a(bt[4], 				new RB_gld()._ins(2, 1, 2, 1));

		}


	}

	private ResultButtons create_result_buttons(E2_Grid grid_in_popUp) throws myException {

		for(Rec21 rec: vLagerAdressen) {

			ResultButton[] btsLine = new ResultButton[5];

			Rec21_adresse recAdresse = new Rec21_adresse(rec);

			String adresse_name 		= recAdresse.get__Name_flexible(" ");
			String land_iso_code 		= "";
			String ort_plz				= "";
			String hauptadresse_aktiv 	= "";

			boolean is_sonderlager = 	recAdresse.is_sonderlager();
			boolean is_hauptadresse = 	recAdresse.__is_main_adresse();
			boolean is_inaktiv = 		recAdresse.is_no_db_val(ADRESSE.aktiv);

			if(!is_sonderlager) {
				ort_plz = recAdresse.getUfs(ADRESSE.ort) + " (" +recAdresse.getUfs(ADRESSE.plz) +")";

				if(recAdresse.getRecLand() != null) {
					land_iso_code = 	recAdresse.getRecLand().getUfs(LAND.iso_country_code,"").toUpperCase();
				}
				hauptadresse_aktiv = (recAdresse.is_no_db_val(ADRESSE.aktiv)?"Inaktiv":"");
			}

			String id_adresse = "(" + recAdresse.get_key_value()+")";

			btsLine[0] = (ResultButton) new ResultButton(land_iso_code,		recAdresse, null)._t(land_iso_code);
			btsLine[1] = (ResultButton) new ResultButton(ort_plz, 			recAdresse, null)._t(ort_plz);
			btsLine[2] = (ResultButton) new ResultButton(adresse_name, 		recAdresse, null)._t(adresse_name);
			btsLine[3] = (ResultButton) new ResultButton(hauptadresse_aktiv,recAdresse, null)._t(hauptadresse_aktiv);
			btsLine[4] = (ResultButton) new ResultButton(id_adresse, 		recAdresse, null)._t(id_adresse);

			for(ResultButton bt: btsLine) {
				bt._aaa( ()->this.vSelectFieldZusatz.get(""+bt.rec21.getId()).setSelected(!this.vSelectFieldZusatz.get(""+bt.rec21.getId()).isSelected()) );
				if(is_hauptadresse) {
					bt._b();
				}
				if(is_inaktiv && !is_sonderlager) {
					bt._fc(Color.LIGHTGRAY);
				}
			}


			resultButtons._a(btsLine);

		}
		return resultButtons;
	}

	public String get_MemStringStatusSelektor() throws myException  {
		String cRueck = "";
		if (this.vSelectFieldsZusatzWerte.size()>=2) {
			cRueck = B2_LIST_Selector_Lager_NG.TRENNZEICHEN_FUER_SPEICHER;
			for (String cWert:this.vSelectFieldsZusatzWerte) {
				cRueck=cRueck+cWert+B2_LIST_Selector_Lager_NG.TRENNZEICHEN_FUER_SPEICHER;
			}
		}
		else {
			cRueck = this.oSelFieldBasis.get_ActualWert();
		}
		return cRueck;	
	}


	public void set_MemStringStatusSelektor(String cWerteString) throws myException {
		this.LEER_MACHEN();

		if (S.isFull(cWerteString)) {
			Vector<String> vWerte = bibALL.TrenneZeile(cWerteString, B2_LIST_Selector_Lager_NG.TRENNZEICHEN_FUER_SPEICHER);

			if (vWerte.size()==1) {
				this.oSelFieldBasis.set_ActiveValue_OR_FirstValue(cWerteString);
				this.oSelFieldBasis.set_bEnabled_For_Edit(true);
				this.fill_Grid4AnzeigeStatusSingle();
			} else {
				this.vSelectFieldsZusatzWerte.addAll(vWerte);
				this.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(this.vSelectFieldsZusatzWerte.size());
				this.oSelFieldBasis.set_bEnabled_For_Edit(false);
				this.fill_Grid4AnzeigeStatusMulti();
			}
		}
	}

	public boolean get_bCopyFontAndSizeOfOrigSelectField()
	{
		return this.bCopyFontAndSizeOfOrigSelectField;
	}

	public void set_bCopyFontAndSizeOfOrigSelectField(boolean CopyFontAndSizeOfOrigSelectField)
	{
		this.bCopyFontAndSizeOfOrigSelectField = CopyFontAndSizeOfOrigSelectField;
	}



	protected void fill_Grid4AnzeigeStatusMulti() {
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().setSize(2);

		this.get_grid4Anzeige()._a(
				new E2_Grid()._bo_ddd()._setSize(this.get_oSelFieldBasis().getWidth().getValue())
				._a( new RB_lab()._t("<Mehrfach>")._b()._fsa(-2), new RB_gld()._ins(1)._center_top())
				, new RB_gld()._ins(0,0,5,0)._left_top());
		this.get_grid4Anzeige()._a(this.get_oButtonOpenMultiSelectPopup(), new RB_gld()._left_top());

	}

	protected void fill_Grid4AnzeigeStatusSingle() {
		this.get_oSelFieldBasis().setWidth(this.extOfSelectComponentDropDown);
		this.get_grid4Anzeige()._clear()._s(2)
		._a(this.get_oSelFieldBasis(), 				new RB_gld()._ins(0,0,5,0)._left_mid())
		._a(this.get_oButtonOpenMultiSelectPopup(), 	new RB_gld()._ins(0,0,5,0)._left_mid());

	}


	public Extent get_extOfSelectComponentDropDown() {
		return extOfSelectComponentDropDown;
	}


	public void set_extOfSelectComponentDropDown(Extent extOfSelectComponentDropDown) {
		this.extOfSelectComponentDropDown = extOfSelectComponentDropDown;
		this.get_oSelFieldBasis().setWidth(this.extOfSelectComponentDropDown);

	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.get_oComponentWithoutText();
	}

	public E2_BasicModuleContainer getPopUpContainer() {
		return popUpContainer;
	}

	public E2_Grid getMultiSelektorGrid() {
		return oMultiSelektorGrid;
	}

	public E2_Grid getInternalSelectionHelpToolsGrid() {
		return internalSelectionHelpToolsGrid;
	}


	@Override
	public void populate(VEK<String> cID_ADRESSE) throws myException {

		this.vLagerAdressen.clear();
		this.resultButtons = null;
		this.vSelectFieldsZusatzWerte.clear();
		this.vSelectFieldZusatz.clear();
		if(cID_ADRESSE.size()>0 && S.isFull(cID_ADRESSE.get(0))) {

			preloadAdressen(cID_ADRESSE.get(0));

			fill_multiSelektorGrid();

			String[][] arrLager		 	= new String[vLagerAdressen.size()][2];

			for(int i=0; i<vLagerAdressen.size();i++) {

				arrLager[i][1] = vLagerAdressen.get(i).get_ufs_dbVal(ADRESSE.id_adresse);

				String beschriftung = lagerNameKonverter(vLagerAdressen.get(i));

				if(S.isFull(vLagerAdressen.get(i).getUfs(ADRESSE.sonderlager,""))){
					beschriftung = vLagerAdressen.get(i).getUfs(ADRESSE.name1);
				}else if(vLagerAdressen.get(i).is_no_db_val(ADRESSE.aktiv) && S.isEmpty(vLagerAdressen.get(i).getUfs(ADRESSE.sonderlager,""))) {
					beschriftung = "(Inaktiv) "+beschriftung;
				}

				arrLager[i][0] = beschriftung;

			}
			String[][] arrKomplet = bibALL.add_EmptyPairInFrontOfArray(arrLager, "-");

			this.get_oSelFieldBasis().set_ListenInhalt(arrKomplet,false);
		}else {
			this.get_oSelFieldBasis().set_ListenInhalt(new String[][]{{"-",""}}, false);
		}
		this.get_oSelFieldBasis().set_ActiveInhalt_or_FirstInhalt("-");
	}

	private String lagerNameKonverter(Rec21 recAddr) throws myException {
		String beschriftung = 
				recAddr.getUfs(ADRESSE.ort,"N/A")+" "+ 
						recAddr.getUfs(ADRESSE.plz,"N/A")+" "+
						recAddr.getUfs(ADRESSE.name1,"-")+"-"+
						recAddr.getUfs(ADRESSE.name2,"-")+" ("+
						recAddr.getFs(ADRESSE.id_adresse,"") +")";
		return beschriftung;
	}


	private class ownSortButtonForResults extends E2_Grid{
		private int    					columnNrSort = 	-1;

		private MyE2_String         	text = 					null;
		private E2_Button    	     	button_to_sort = 			null;
		private EnSortStatusButtonGrid  sortstatus_actual =         EnSortStatusButtonGrid.NEUTRAL;

		public ownSortButtonForResults( int column, MyE2_String p_text, E2_MutableStyle  style) {
			super();
			this._nB();

			this.columnNrSort = column;

			this.text = p_text;

			this.button_to_sort = new E2_Button()._t(text)._style(style)._aaa(()->sort_action());

			this.button_to_sort.setFocusTraversalParticipant(false);

			this.setSize(2);
			this._init();
		}

		private void sort_action() throws myException {

			//den naechsten eigenen status feststellen
			EnSortStatusButtonGrid myOwnStatus = this.sortstatus_actual.get_next();

			//alle neutralisieren
			for (ownSortButtonForResults  sortButton: sortButtonsForResults) {
				sortButton.set_sortstatus_actual(EnSortStatusButtonGrid.NEUTRAL);
			}

			//dan den eigenen richtig setzen und dem resultButtos - vector die spalte und den status mitteilen
			resultButtons.setActualSortCol(columnNrSort);
			resultButtons.setActualSortStatus(myOwnStatus);
			set_sortstatus_actual(myOwnStatus);

			resultButtons.sort();
			fill_multiSelektorGrid();

		}

		private void _init() {
			this.removeAll();
			this._a(this.button_to_sort, new RB_gld()._ins(0));
			this._a(new RB_lab()._icon(this.sortstatus_actual.icon()),new RB_gld()._ins(2,0,0,0));
		}

		public void set_sortstatus_actual(EnSortStatusButtonGrid p_sortstatus_actual) {
			this.sortstatus_actual = p_sortstatus_actual;
			this._init();
		}

	}
}
