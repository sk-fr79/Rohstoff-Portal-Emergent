package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain_LineThrough;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.DB_ENUMS.MAHNUNG_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MAHNUNG_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAHNUNG_POS;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.FIBU.RECORD_FIBU_ext;

public class FIBU_MAHNUNG_Beleg_ListComponent extends MyE2_Grid{

	private E2_NavigationList naviList = null;

	private Vector<String> id_list;

	private HashMap<String, MyE2_TextArea> textField_map;

	private Vector<String> id_selected;

	private RB_lab gesamtLbl;

	private String activeId;

	private boolean neuMahnung = true;

	private RECORD_MAHNUNG recMahnung;

	public FIBU_MAHNUNG_Beleg_ListComponent(E2_NavigationList oNaviList, boolean alle_beleg_selected) throws myException {
		super(7, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS_W100());

		this.naviList = oNaviList;
		this.id_list = new Vector<>(this.naviList.get_vSelectedIDs_Unformated_Select_the_one_and_only());
		this.id_selected = new Vector<>();
		this.gesamtLbl = new RB_lab()._b();
		this.textField_map = new HashMap<String, MyE2_TextArea>();
		this.neuMahnung = true;

		if(alle_beleg_selected){
			this.id_selected.addAll(id_list);
		}

		this.buildHeader();
		this.fillList();

		if(alle_beleg_selected){
			this.setSelectedByDefault(alle_beleg_selected);
		}

	}

	public FIBU_MAHNUNG_Beleg_ListComponent(Vector<String> selectedId_List, boolean alle_beleg_selected) throws myException {
		super(7, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS_W100());

		this.id_list = new Vector<>(selectedId_List);
		this.id_selected = new Vector<>();
		this.gesamtLbl = new RB_lab()._b();
		this.textField_map = new HashMap<String, MyE2_TextArea>();
		this.neuMahnung = true;

		if(alle_beleg_selected){
			this.id_selected.addAll(id_list);
		}

		this.buildHeader();
		this.fillList();

		if(alle_beleg_selected){
			this.setSelectedByDefault(alle_beleg_selected);
		}

	}

	public FIBU_MAHNUNG_Beleg_ListComponent(RECORD_MAHNUNG  recMahnung, boolean extendedMahnung, boolean alle_beleg_selected) throws myException {
		super(7, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS_W100());

		if(extendedMahnung){	
			String[][] geordnetBeleg = bibDB.EinzelAbfrageInArray(new SEL(MAHNUNG_POS.id_fibu)
					.FROM(MAHNUNG_POS._tab())
					.WHERE(new vgl(MAHNUNG_POS.id_mahnung, recMahnung.get_ID_MAHNUNG_cUF_NN("")))
					.ORDERUP(MAHNUNG_POS.manhnung_pos).s());
			this.id_list = bibVECTOR.get_VectorFromArray(geordnetBeleg);
		}else{
			this.id_list = new Vector<String>(
					recMahnung
					.get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_mahnung()
					.get_ID_FIBU_hmString_UnFormated("")
					.values());
		}
		this.id_selected = new Vector<>();
		this.gesamtLbl = new RB_lab()._b()._lw();
		this.neuMahnung = false;
		this.recMahnung = recMahnung;

		this.textField_map = new HashMap<String, MyE2_TextArea>();

		if(alle_beleg_selected){
			this.id_selected.addAll(id_list);
		}

		this.buildHeader();
		this.fillList();
		if(alle_beleg_selected){
			this.setSelectedByDefault(alle_beleg_selected);
		}
	}


	private void buildHeader(){
		this.show_InputStatus(true);

		this.setColumnWidth(0,new Extent (35));
		this.setColumnWidth(2,new Extent (100));
		this.setColumnWidth(2,new Extent (35));
		this.setColumnWidth(5,new Extent (60));
		this.setColumnWidth(6,new Extent (200));
		Insets ins = E2_INSETS.I(5,0,5,0);

		RB_gld gld = new RB_gld()._col(new E2_ColorDDark())._ins(ins)._span_r(2);

		this.add(new RB_lab()								, gld);
		this.add(new RB_lab("Fibu Id")._b()					, gld);
		this.add(new RB_lab("Position")._b()				, gld);

		this.add(new RB_lab()								, gld);
		this.add(new RB_lab("Position Text")._b()			, gld._left_mid());
		this.add(new RB_lab("Aktuelle Mahnstuffe")._b()._lw()	, gld._center_mid());
		this.add(new RB_lab("Betrag")._b(), new RB_gld()._col(new E2_ColorDDark())._ins(ins)._right_mid()._span_r(1));
		this.add(gesamtLbl,new RB_gld()._col(new E2_ColorDDark())._ins(ins)._right_mid()._span_r(1));
	}

	private void updateBetragSumme() throws myException{	
		BigDecimal gesamtSumme =new BigDecimal(0);

		BigDecimal BD0 = new BigDecimal("0");
        String waehrung = "EUR";
		for(String id:id_selected){
			RECORD_FIBU_ext recFibuExt = new RECORD_FIBU_ext(id);
			if(recFibuExt.get_ENDBETRAG_FREMD_WAEHRUNG_bdValue(BD0).equals(BD0)){
				gesamtSumme = gesamtSumme.add(recFibuExt.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BD0).multiply(recFibuExt.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BD0)));
			}else{
				gesamtSumme = gesamtSumme.add(recFibuExt.get_ENDBETRAG_FREMD_WAEHRUNG_bdValue(BD0).multiply(recFibuExt.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BD0)));
			}
			waehrung = recFibuExt.get_WAEHRUNG_FREMD_cUF_NN("EUR");
		}
		
		if(gesamtSumme.signum()==-1 || gesamtSumme.signum()==0){
			gesamtLbl._col_fore(Color.RED);
		}else{
			gesamtLbl._col_fore(Color.BLACK);
		}

		gesamtLbl._t("(Gesamt: " + MyNumberFormater.formatDez(gesamtSumme.toPlainString(), 2, true) + " "+waehrung+")");
	}

	private void fillList() throws myException{
		int pos =1;

		BigDecimal BD0 =  new BigDecimal("0.0");

		for(String id: id_list){

			RECORD_FIBU_ext recFibuExt = new RECORD_FIBU_ext(id);

			BigDecimal betrag = recFibuExt.get_ENDBETRAG_FREMD_WAEHRUNG_bdValue(BD0).multiply(recFibuExt.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BD0));

			if(betrag.equals(new BigDecimal("0.0"))){
				betrag = recFibuExt.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BD0).multiply(recFibuExt.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BD0));
			}

			int newMahnungStuffe = getRecordMahnstuffe(recFibuExt);

			String fremdBelegNummer = "";

			if(neuMahnung){
				if(S.isFull(recFibuExt.get_BUCHUNGSINFO_cUF_NN(""))){
					fremdBelegNummer = recFibuExt.get_BUCHUNGSINFO_cUF_NN("");
				}else{
					fremdBelegNummer = recFibuExt.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_BUCHUNGSNUMMER_cF_NN("");
				}
			}else{
				fremdBelegNummer = new RECORD_MAHNUNG_POS(
						MAHNUNG_POS.id_mahnung+"="+recMahnung.get_ID_MAHNUNG_cUF_NN("")+ 
						" AND " + MAHNUNG_POS.id_fibu + "=" +id).get_TEXT_cUF_NN("");

			}

			Color col = new E2_ColorBase();

			if(id.equals(activeId)){
				col = new E2_ColorLLight();
			}

			MyE2_TextArea txt = null;

			MyE2_CheckBox chkBox = new MyE2_CheckBox(false, false);
			if(textField_map.get(id) == null){
				txt = new MyE2_TextArea(fremdBelegNummer, 400, 2000, 2);
				textField_map.put(id, txt);
			}else{
				txt = textField_map.get(id);
			}


			chkBox._aaa(new ownCheckboxAgent());
			chkBox.EXT().set_C_MERKMAL(id);

			String position_as_string = id_selected.contains(id)?""+pos:"";

			E2_Font font = null;
			if(id_selected.contains(id)){
				chkBox.setSelected(true);
				font = new E2_FontPlain();
			}else{
				font =new E2_FontPlain_LineThrough();
				txt.set_bEnabled_For_Edit(false);
			}

			txt.setFont(font);


			this.add(chkBox, 														new RB_gld()._center_mid()._col(col));
			this.add(new RB_lab(id)._f(font) ,										new RB_gld()._center_mid()._col(col));
			this.add(new RB_lab(position_as_string)._f(font),						new RB_gld()._center_mid()._col(col));
			this.add(new Position_Component(id, "", id_list)._f(font),				new RB_gld()._ins(2, 2, 2, 2)._left_mid()._col(col));
			this.add(txt,															new RB_gld()._ins(5, 5, 5, 5)._center_mid()._col(col));
			this.add(new Stuffe_Label(newMahnungStuffe),							new RB_gld()._ins(2, 2, 2, 2)._center_mid()._col(col));
			this.add(
					new RB_lab(MyNumberFormater.formatDez(betrag,2,true)+ " "+recFibuExt.get_WAEHRUNG_FREMD_cF_NN("")
					)._f(font),new RB_gld()._ins(2, 2, 2, 2)._right_mid()._col(col));

			if(id_selected.contains(id)){
				pos++;
			}
		}	

		this.updateBetragSumme();
	}

	private void refresh() throws myException{
		this.removeAll();
		this.buildHeader();
		this.fillList();
	}

	public Vector <FIBU_MAHNUNG_Beleg_Model> getSelectedId_geordnet(){
		Vector<FIBU_MAHNUNG_Beleg_Model> returnedDataVector = new Vector<>();
		Integer position = 1;
		for(String id: id_list){
			if(id_selected.contains(id)){
				returnedDataVector.add(new FIBU_MAHNUNG_Beleg_Model(id, position, textField_map.get(id).getText()));
				position++;
			}
		}
		return returnedDataVector;
	}

	public Vector<String> getUnselectedId(){
		Vector<String> returnedDataVector = new Vector<>(id_list);
		for(String id: id_list){
			if(id_selected.contains(id)){
				returnedDataVector.remove(id);
			}
		}
		return returnedDataVector;
	}

	public void setSelectedByDefault(boolean selectedByDefault){
		for(Component comp: this.getComponents()){
			if(comp instanceof MyE2_CheckBox){
				((MyE2_CheckBox)comp).setSelected(selectedByDefault);
			}
		}
	}

	private class Stuffe_Label extends RB_lab{

		public Stuffe_Label(int stuffe) {
			super("");

			E2_ResourceIcon ikon = null;

			switch(stuffe){
			case 1:
				ikon = E2_ResourceIcon.get_RI("m1.png");
				break;
			case 2:
				ikon = E2_ResourceIcon.get_RI("m2.png");
				break;
			case 3:
				ikon = E2_ResourceIcon.get_RI("m3.png");
				break;
			default:
				ikon = E2_ResourceIcon.get_RI("empty.png");
				break;
			}

			this.setIcon(ikon);
		}
	}

	@Override
	public void show_InputStatus(boolean bInputIsOK) {
		if(! bInputIsOK){
			this.setStyle(MyE2_Grid.STYLE_GRID_BORDER(new E2_ColorAlarm()));
		}
	}

	private int getRecordMahnstuffe(RECORD_FIBU_ext recFibuExt) throws myException{

		int iRueck = 0;

		RECLIST_MAHNUNG_POS reclistMahnungen = recFibuExt.get_DOWN_RECORD_LIST_MAHNUNG_POS_id_fibu();

		for (int i=0;i<reclistMahnungen.get_vKeyValues().size();i++)
		{
			if (reclistMahnungen.get(i).get_UP_RECORD_MAHNUNG_id_mahnung().get_MAHNSTUFE_lValue(-1l)>iRueck)
			{
				iRueck=reclistMahnungen.get(i).get_UP_RECORD_MAHNUNG_id_mahnung().get_MAHNSTUFE_lValue(-1l).intValue();
			}
		}
		return iRueck;
	}

	private class ownCheckboxAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_CheckBox oComp = (MyE2_CheckBox)oExecInfo.get_MyActionEvent().getSource();

			String dinge = oComp.EXT().get_C_MERKMAL();

			if(oComp.isSelected()){
				id_selected.add(dinge);
				textField_map.get(dinge).set_bEnabled_For_Edit(true);
			}else{
				id_selected.remove(dinge);
				textField_map.get(dinge).set_bEnabled_For_Edit(false);
			}
			refresh();
		}

	}

	private class Position_Component extends E2_Up_Down_Item_in_List{

		public Position_Component(String id, String text, Vector<String> item_list) throws myException {
			super(id, text, item_list);
		}

		@Override
		public void refreshListComponent() throws myException {
			activeId = this.getId();
			refresh();	
		}

	}


}
