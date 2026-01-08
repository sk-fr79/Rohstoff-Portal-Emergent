package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EMAIL_SEND_ext;

public class ES_LIST_ComponentMap extends E2_ComponentMAP 
{

	private String base_TableName = null;
	private String id_BaseTable = null;
			
	
	public ES_LIST_ComponentMap(String cWhere, String baseTableName, String idBaseTable) throws myException
	{
		super(new ES_LIST_SqlFieldMAP());
		
		this.base_TableName = 	baseTableName;
		this.id_BaseTable = 	idBaseTable;
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		E2_CheckBoxForList cb = new E2_CheckBoxForList();
		cb.EXT().set_oLayout_ListElement(new RB_gld()._left_top()._ins(2, 4, 2, 2));
		
		this.add_Component(ES_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,	cb,new MyE2_String("?"));
		this.add_Component(new showSendStatus(oFM.get_(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND4)), 	new MyE2_String("Stat"));
		
		this.add_Component(new ES_LIST_BT_SendEMail(oFM.get_(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND5),this.base_TableName), 	new MyE2_String("Senden"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.EMAIL_SEND$SENDER_ADRESS)), 	new MyE2_String("Absender"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.EMAIL_SEND$BETREFF)), 			new MyE2_String("Betreff"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.EMAIL_SEND$TEXT)), 				new MyE2_String("eMail"));
		this.add_Component(new ownListcomp4Targets(oFM.get_(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND2)), 	new MyE2_String("Ziele"));
		this.add_Component(new ownListcomp4Attachements(oFM.get_(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND3)), 	new MyE2_String("Anlagen"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.EMAIL_SEND$ID_EMAIL_SEND)), 	new MyE2_String("ID"));
		
		((MyE2_DB_Label_INROW)this.get__Comp(_DB.EMAIL_SEND$SENDER_ADRESS)).EXT().set_oColExtent(new Extent(200));
		((MyE2_DB_Label_INROW)this.get__Comp(_DB.EMAIL_SEND$BETREFF)).EXT().set_oColExtent(new Extent(250));
		((MyE2_DB_Label_INROW)this.get__Comp(_DB.EMAIL_SEND$BETREFF)).setLineWrap(true);
		((MyE2_DB_Label_INROW)this.get__Comp(_DB.EMAIL_SEND$BETREFF)).setFont(new E2_FontPlain(-2));
		((MyE2_DB_Label_INROW)this.get__Comp(_DB.EMAIL_SEND$TEXT)).EXT().set_oColExtent(new Extent(400));
		((MyE2_DB_Label_INROW)this.get__Comp(_DB.EMAIL_SEND$TEXT)).setLineWrap(true);
		((MyE2_DB_Label_INROW)this.get__Comp(_DB.EMAIL_SEND$TEXT)).setFont(new E2_FontPlain(-2));
		((MyE2_DB_Label_INROW)this.get__Comp(_DB.EMAIL_SEND$ID_EMAIL_SEND)).EXT().set_oColExtent(new Extent(60));
		
		((MyE2_DB_PlaceHolder)this.get__Comp(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND2)).EXT().set_oColExtent(new Extent(200));
		((MyE2_DB_PlaceHolder)this.get__Comp(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND3)).EXT().set_oColExtent(new Extent(200));
		((MyE2_DB_PlaceHolder)this.get__Comp(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND4)).EXT().set_oColExtent(new Extent(20));
		((MyE2_DB_PlaceHolder)this.get__Comp(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND5)).EXT().set_oColExtent(new Extent(20));
		((MyE2_DB_PlaceHolder)this.get__Comp(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND2)).EXT_DB().set_bIsSortable(false);
		((MyE2_DB_PlaceHolder)this.get__Comp(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND3)).EXT_DB().set_bIsSortable(false);
		((MyE2_DB_PlaceHolder)this.get__Comp(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND4)).EXT_DB().set_bIsSortable(false);
		((MyE2_DB_PlaceHolder)this.get__Comp(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND5)).EXT_DB().set_bIsSortable(false);
		
		
		this.set_oSubQueryAgent(new ES_LIST_FORMATING_Agent());
		
		if (S.isFull(cWhere)) {
			oFM.add_BEDINGUNG_STATIC(cWhere);
		}
		
		this.set_Factory4Records(new ownRecordFactory());
	}
	
	private class ownRecordFactory extends E2_ComponentMAP_Factory4Records {

		@Override
		public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
			return new RECORD_EMAIL_SEND(cID_MAINTABLE);
		}
		
	}
	
	
	private class showSendStatus extends MyE2_DB_PlaceHolder {

		public showSendStatus(SQLField osqlField) throws myException {
			super(osqlField);
		}

		@Override
		public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
			RECORD_EMAIL_SEND_ext  recSend = new RECORD_EMAIL_SEND_ext((RECORD_EMAIL_SEND)this.EXT().get_oComponentMAP().get_Record4MainTable());
			this.removeAll();
			this.setSize(1);
			this.add(recSend.get_SendStatusLabel());

		}
		public Object get_Copy(Object objHelp) throws myExceptionCopy {
			try {
				return new showSendStatus(this.EXT_DB().get_oSQLField());
			} catch (myException e) {
				e.printStackTrace();
				throw new myExceptionCopy(e.ErrorMessage);
			}
		}

	
	}
	
	
	
	private class ownListcomp4Targets extends MyE2_DB_PlaceHolder {
		public ownListcomp4Targets(SQLField osqlField) throws myException {
			super(osqlField);
		}

		@Override
		public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
			this.removeAll();
			this.setSize(2);
			RECORD_EMAIL_SEND  recSend = (RECORD_EMAIL_SEND)this.EXT().get_oComponentMAP().get_Record4MainTable();
			for (RECORD_EMAIL_SEND_TARGETS rT: recSend.get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_email_send()) {
				this.add(new ownLabel(rT.get_TARGET_ADRESS_cUF_NN(""),150), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(3,1,3,1)));
				this.add(new MyE2_CheckBox(rT.is_SEND_OK_YES(),true));
			}
		}
		
		public Object get_Copy(Object objHelp) throws myExceptionCopy {
			try {
				return new ownListcomp4Targets(this.EXT_DB().get_oSQLField());
			} catch (myException e) {
				e.printStackTrace();
				throw new myExceptionCopy(e.ErrorMessage);
			}
		}

	}

	
	private class ownListcomp4Attachements extends MyE2_DB_PlaceHolder {
		public ownListcomp4Attachements(SQLField osqlField) throws myException {
			super(osqlField);
		}

		@Override
		public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
			this.removeAll();
			this.setSize(5);
			RECORD_EMAIL_SEND  recSend = (RECORD_EMAIL_SEND)this.EXT().get_oComponentMAP().get_Record4MainTable();
			
			for (RECORD_EMAIL_SEND_ATTACH ra: recSend.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send()) {
				//MutableStyle  StyleTextInGrid = new MutableStyle();
				ES_RECORD_ARCHIVMEDIEN recAM = new ES_RECORD_ARCHIVMEDIEN(ra.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien(), null);
				this.add(new ES_BtDownload(recAM), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(3,1,3,1)));
				this.add(new ownLabel(recAM.get_DOWNLOADNAME_cUF_NN(""),120), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(3,1,3,1)));
				this.add(new ownLabel(recAM.get_MEDIENKENNER_cUF_NN(""),70), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(3,1,3,1)));
				this.add(new MyE2_Label(""));
				this.add(new ownLabel(ES_LIST_ComponentMap.this.format_Programmkenner(recAM.get_PROGRAMM_KENNER_cUF_NN("")).CTrans(),150), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(3,1,3,1)));
			}
		}
		public Object get_Copy(Object objHelp) throws myExceptionCopy {
			try {
				return new ownListcomp4Attachements(this.EXT_DB().get_oSQLField());
			} catch (myException e) {
				e.printStackTrace();
				throw new myExceptionCopy(e.ErrorMessage);
			}
		}

	}
	
	private MyE2_String format_Programmkenner(String kenner) {
		return Archiver_CONST.PROGRAMMKENNER.get_Erklaerung(kenner);
	}
	
	private class ownLabel extends MyE2_TextField {

		public ownLabel(String cText, int breite) throws myException {
			super(cText,breite,200);
			this.setToolTipText(cText);
			this.setFont(new E2_FontPlain(-2));
			this.set_bEnabled_For_Edit(false);
			this.setBackground(new E2_ColorBase());
			this.setBorder(new Border(new Extent(0), new E2_ColorBase(), Border.STYLE_NONE));
		}
	}

	public String get_base_TableName() {
		return base_TableName;
	}

	public String get_id_BaseTable() {
		return id_BaseTable;
	}



	
}
