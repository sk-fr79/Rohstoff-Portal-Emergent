package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Complex;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_ATTACH;
import panter.gmbh.indep.S;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder_Simple;
import panter.gmbh.indep.exceptions.myException;

public class ES_mask_complex_LISTE_ANLAGEN extends MyE2_Grid  implements  IF_RB_Component_Complex {

	private static int[] iBreite = {30,30,100,100,100,100};
	
	private Vector<ES_RECORD_ARCHIVMEDIEN>  	vArchivmedienAllowedAll   = new Vector<ES_RECORD_ARCHIVMEDIEN>();
	
	private RB_ComponentMap 					rb_Mask_outside = null;
	private Vector<RB_Validator_Component> 		vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	
	private RB_KF Key = null;

	private E2_Grid    							grid4addon_attatchments = new E2_Grid()._a(new MyE2_Label("-"), new RB_gld()._center_mid()._ins(6,0,0,0)) ;
	
	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	public ES_mask_complex_LISTE_ANLAGEN(RB_ComponentMap oMaskOutside) throws myException {
		super(ES_mask_complex_LISTE_ANLAGEN.iBreite, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		this.rb_Mask_outside = 	oMaskOutside;
	}

	
	
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}


	
	
	private MyE2_String format_Programmkenner(String kenner) {
		return Archiver_CONST.PROGRAMMKENNER.get_Erklaerung(kenner);
	}
	
	
	/**
	 * 
	 * @return number of attachments 
	 */
	public int get_number_attachments_4_save() {
		int i=0;
		for (ES_RECORD_ARCHIVMEDIEN rec: vArchivmedienAllowedAll) {
			if (rec.get_ownCheckBox4EmailSendList().isSelected()) {i++;}
		}
		return i;
	}
	


	/**
	 * beruecksichtigt die vorgabe, dass ein original nur einmal angehaengt sein darf
	 * @return
	 * @throws myException
	 */
	public boolean get_allowAllSelected_to_attach() throws myException {
		boolean bRueck = true;
		for (ES_RECORD_ARCHIVMEDIEN rec: vArchivmedienAllowedAll) {
			bRueck = bRueck&&rec.allowAdding(this.rb_Mask_outside.getRbDataObjectActual());
		}
		return bRueck;
	}




	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		
		//moegliche angehaengte archivmedien werden hier durchgereicht (nur bei edit und neueingabe, sonst werden nur die zugeordneten angezeigt)
		Vector<String>  vIDs_Archivmedien_Vorschlag = ((ES_RB_DataObject)dataObject).get_v__id_archivmedien_vorschlag();
		Vector<String>  vIDs_Archivmedien_Zusatz = ((ES_RB_DataObject)dataObject).get_v_id_archivmedien_possible();
		
		Vector<ES_RECORD_ARCHIVMEDIEN>  	vArchLocal = new Vector<ES_RECORD_ARCHIVMEDIEN>();
		Vector<String>   					vArchLocalAdded = new Vector<String>();
		
		//zuerst die sammeln und ankreuzen, die evtl. bereits vorhanden sind
		if (dataObject.rb_MASK_STATUS().isStatusEdit() || dataObject.rb_MASK_STATUS().isStatusView()) {
			
			RECORD_EMAIL_SEND  recEmailSend = (RECORD_EMAIL_SEND)dataObject.get_RecORD();
			for (RECORD_EMAIL_SEND_ATTACH recAttach: recEmailSend.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send(null,null,true)) {
				ES_RECORD_ARCHIVMEDIEN  recArchLoc = new ES_RECORD_ARCHIVMEDIEN(recAttach.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien(),this);
				recArchLoc.get_ownCheckBox4EmailSendList().setSelected(true);
				if (!(vArchLocalAdded.contains(recArchLoc.get_ID_ARCHIVMEDIEN_cUF()))) {
					vArchLocal.add(recArchLoc);
					vArchLocalAdded.add(recArchLoc.get_ID_ARCHIVMEDIEN_cUF());
				}
			}
			
			//jetzt die optionalen dranhaengen
			for (String id_Optional: vIDs_Archivmedien_Zusatz) {
				if (!vArchLocalAdded.contains(id_Optional)) {
					ES_RECORD_ARCHIVMEDIEN  recArchLoc = new ES_RECORD_ARCHIVMEDIEN(new RECORD_ARCHIVMEDIEN(id_Optional),this);
					vArchLocal.add(recArchLoc);
					vArchLocalAdded.add(id_Optional);
				}
			}
			
		} else {
		
			//danach die, die bei neueingabe ausgewaehlt wurden
			boolean bSetSelected = (vArchLocal.size()==0);   //gibt es bereits welche, werden die zusaetzlichen nur zur auswahl gestellt
			
			for (String cID_Archivmedien: vIDs_Archivmedien_Vorschlag ) {
				ES_RECORD_ARCHIVMEDIEN  recArchLoc = new ES_RECORD_ARCHIVMEDIEN(new RECORD_ARCHIVMEDIEN(cID_Archivmedien),this);
				recArchLoc.get_ownCheckBox4EmailSendList().setSelected(bSetSelected);
				if (!(vArchLocalAdded.contains(recArchLoc.get_ID_ARCHIVMEDIEN_cUF()))) {
					vArchLocal.add(recArchLoc);
					vArchLocalAdded.add(recArchLoc.get_ID_ARCHIVMEDIEN_cUF());
				}
			}
		}
		this.fill_Grid(vArchLocal);
	}

	
	/**
	 * prueft, ob in der sendeliste ein original hinterlegt ist
	 * @return
	 * @throws myException 
	 */
	public boolean get_bIsOriginalSelected4Save() throws myException {
		boolean bOrigIsSelected = false;
		for (ES_RECORD_ARCHIVMEDIEN rec: this.vArchivmedienAllowedAll) {
			if (rec.is_IST_ORIGINAL_YES()&&rec.get_ownCheckBox4EmailSendList().isSelected()) {
				bOrigIsSelected=true;
			}
		}
		return bOrigIsSelected;
	}
	
	
	public void fill_Grid(Vector<ES_RECORD_ARCHIVMEDIEN> vArchivmedien) throws myException {
		
		this.removeAll();
		
		if (vArchivmedien!=null) {
			this.vArchivmedienAllowedAll=vArchivmedien;
		}
		
		
		GridLayoutData glTitel = MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(3,1,3,1), new E2_ColorDDark(), 1);

		//titelzeile
		this.add(this.grid4addon_attatchments, new RB_gld()._col( new E2_ColorDDark())._ins(1, 0, 1, 2));
		this.add(new ownLabel("-"), glTitel);
		this.add(new ownLabel("Beschreibung"), glTitel);
		this.add(new ownLabel("Dateiname"), glTitel);
		this.add(new ownLabel("Herkunft"), glTitel);
		this.add(new ownLabel("Verwendung"), glTitel);

		for (ES_RECORD_ARCHIVMEDIEN ra: this.vArchivmedienAllowedAll) {
			MutableStyle  StyleTextInGrid = new MutableStyle();
			if (ra.get_ownCheckBox4EmailSendList().isSelected()) {
				StyleTextInGrid.setProperty(Label.PROPERTY_FONT, new E2_FontBold());
				StyleTextInGrid.setProperty(Label.PROPERTY_FOREGROUND, Color.BLACK);
			} else {
				StyleTextInGrid.setProperty(Label.PROPERTY_FONT, new E2_FontItalic());
				StyleTextInGrid.setProperty(Label.PROPERTY_FOREGROUND, Color.DARKGRAY);
			}
			this.add(new ES_BtDownload(ra), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(3,1,3,1)));
			this.add(ra.get_ownCheckBox4EmailSendList(), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(3,1,3,1)));
			this.add(new ownLabel(ra.get_DATEIBESCHREIBUNG_cUF_NN(""),StyleTextInGrid), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(3,1,3,1)));
			this.add(new ownLabel(ra.get_DOWNLOADNAME_cUF_NN(""),StyleTextInGrid), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(3,1,3,1)));
			this.add(new ownLabel(ra.get_MEDIENKENNER_cUF_NN(""),StyleTextInGrid), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(3,1,3,1)));
			this.add(new ownLabel(this.format_Programmkenner(ra.get_PROGRAMM_KENNER_cUF_NN("")),StyleTextInGrid), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(3,1,3,1)));
		}
	}

	private class ownLabel extends MyE2_Label {

		public ownLabel(Object cText, MutableStyle style) {
			super(cText,style);
			this.setLineWrap(true);
			this.setFont(new E2_FontPlain(-2));
		}
		public ownLabel(Object cText) {
			super(cText);
			this.setLineWrap(true);
			this.setFont(new E2_FontPlain(-2));
		}
	}

	
	
	
	


	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void mark_MustField() throws myException {
	}

	@Override
	public void mark_Disabled() throws myException {
	}

	@Override
	public void mark_FalseInput() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}


	@Override
	public Vector<MyRECORD_IF_FILLABLE> maskContents_Transfer_To_Record_And_Prepare_Save(MyE2_MessageVector oMV, RB_ComponentMap thismask) throws myException {
		return null;
	}

	@Override
	public Vector<MySqlStatementBuilder> get_vSQL_StatementBuilders_Others(MyE2_MessageVector oMV, RB_ComponentMap thismask) throws myException {
		Vector<MySqlStatementBuilder> vStmb = new Vector<MySqlStatementBuilder>();
		for (ES_RECORD_ARCHIVMEDIEN recLocal: this.vArchivmedienAllowedAll) {
			String sql = recLocal.get_Statement4EmailSendList(thismask.getRbDataObjectActual());
			if (S.isFull(sql)) {
				vStmb.add(new MySqlStatementBuilder_Simple(sql));
			}
		}
		return vStmb;
	}

	@Override
	public MyE2_MessageVector makeBindingDaughterRecords_to_MotherTable(RB_Dataobject DataObjectMother, Vector<MyRECORD_IF_FILLABLE> v_DaughterRecords) throws myException {
		return null;
	}

	

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}
	

	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		//nur die checkboxen disablen
		for (ES_RECORD_ARCHIVMEDIEN rec: this.vArchivmedienAllowedAll) {
			if (rec.is_IST_ORIGINAL_NO()) {
				rec.get_ownCheckBox4EmailSendList().set_bEnabled_For_Edit(enabled);
			} else {
				rec.get_ownCheckBox4EmailSendList().set_bEnabled_For_Edit(false);     //originale koennen nur via intern prozesse zu mails zugeordnet werden
			}
		}
	}

	
	//2016-09-13: eine button einblenden, der es ermoeglicht, weitere anlagen anzuhaenge
	public void add_button_4_adding_attachments(ES_MASK_BT_Add_Attachments bt_add_attachment) throws myException {
		this.grid4addon_attatchments._clear()._a(bt_add_attachment, new RB_gld()._ins(1,1,1,1));
	}

	
}
