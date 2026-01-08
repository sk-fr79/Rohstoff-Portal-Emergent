package panter.gmbh.Echo2.RB.EMAIL;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class RB_Email_test_ui extends E2_BasicModuleContainer{

	private MyE2_TextField 		id_email_field;

	private E2_Grid				mainGrid;
	private E2_Grid				upGrid;
	private E2_Grid				downGrid;

	private E2_Grid emailTargetGrid;

	private E2_Grid cc_emailTargetGrid 		= new E2_Grid()._s(1);
	private E2_Grid bcc_emailTargetGrid  	= new E2_Grid()._s(1);
	private E2_Grid anlageGrid 				= new E2_Grid()._s(1);

	private RB_Email geladetMail;

	private RB_Email neueMail;

	public RB_Email_test_ui() throws myException {
		super();

		mainGrid 	= new E2_Grid()._bo_dd()._setSize(new int[]{1000});

		upGrid 		= new E2_Grid()._s(2);
		downGrid 	= new E2_Grid()._s(2);

		_build_up_grid();

		mainGrid.add(upGrid);

		this.add(mainGrid);
	}

	private void _build_up_grid() throws myException{

		id_email_field = new MyE2_TextField("1414", 300,40);

		RB_gld gldinsets = new RB_gld()._ins(5, 5, 5, 5);

		upGrid._gld(gldinsets)
		._a_lm(id_email_field)
		._a_lm(new MyE2_Button("Mail laden", null, new ownLadeAction()))._gld(new RB_gld()._span(2))
		._a_cm(new MyE2_Button("neu Mail generieren / Speichern", null, new ownSpeichernAction()))
		._a_cm(new MyE2_Button("test send", null, new ownSendMail()))
		;


	}

	private void _fill_down_grid(RB_Email mail) throws myException{

		downGrid.removeAll();

		MyE2_TextArea textArea = new MyE2_TextArea(mail.getMail().getText(),300,8000, 5 );
		textArea.set_bEnabled_For_Edit(false);

		emailTargetGrid = new E2_Grid()._s(1);

		anlageGrid = new E2_Grid()._s(1);

		emailTargetGrid.removeAll();
		cc_emailTargetGrid.removeAll();
		bcc_emailTargetGrid.removeAll();



		for(String zielAdresse: mail.getMail().getZielAdresseListe()){
			switch(mail.getMail().getMailTyp()){
			case SINGLE:
				emailTargetGrid._a_lt(new RB_lab(zielAdresse)._i()._col_fore(new Color(0,128,0)));
				break;
			case CC:
				cc_emailTargetGrid._a_lt(new RB_lab(zielAdresse)._i()._col_fore(new Color(0,0,128)));
				break;
			case BCC:
				bcc_emailTargetGrid._a_lt(new RB_lab(zielAdresse)._i()._col_fore(Color.DARKGRAY));
				break;
			default:
				break;
			}
		}

		for(RECORD_ARCHIVMEDIEN anlage:mail.getMail().getAnlageVector()){
			anlageGrid._a_lt(new RB_lab(anlage.get_ID_ARCHIVMEDIEN_cUF_NN("")
					+" - Table:"+anlage.get_TABLENAME_cUF_NN("")
					+" - Id:"+anlage.get_ID_TABLE_cUF_NN("")
					+" - Dateiname:" + anlage.get_DATEINAME_cUF_NN("")));
		}

		downGrid = new E2_Grid()._setSize(new int[]{150, 400})
				._gld(new RB_gld()._ins(5, 5, 5, 5))
				._a_lm(new RB_lab("betreff")._b())
				._a_lm(new RB_lab(mail.getMail().getBetreff()))
				._a_lm(new RB_lab("Status")._b())
				._a_lm(new RB_lab(mail.getMail().getSendStatus().name()))
				._a_lm(new RB_lab("Text")._b())
				._a_lm(textArea)
				._a_lm(new RB_lab("Typ")._b())
				._a_lm(new RB_lab(mail.getMail().getMailTyp().name()))
				._a_lm(new RB_lab("Von")._b())
				._a_lm(new RB_lab(mail.getMail().getAbsender()))
				._a_lm(new RB_lab("Zu")._b())
				._a_lm(emailTargetGrid)
				._a_lm(new RB_lab("Zu (BCC):")._b())
				._a_lm(bcc_emailTargetGrid)
				._a_lm(new RB_lab("Zu (CC):")._b())
				._a_lm(cc_emailTargetGrid)
				._a_lm(new RB_lab("Anlagen")._b())
				._a_lm(anlageGrid);
		mainGrid.add(downGrid);
	}

	private class ownLadeAction extends XX_ActionAgent{



		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(S.isFull(id_email_field.getText())){


				geladetMail = new RB_Email(new RECORD_EMAIL_SEND(id_email_field.getText()));
				_fill_down_grid(geladetMail);
			}
		}

	}

	private class ownSpeichernAction extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			if(S.isFull(id_email_field.getText())){
				geladetMail
			
				._send_typ(SEND_TYPE.SINGLE)
	
				._text("Are u alive !")

//				._delete_anlage(_TAB.vkopf_kon_druck, bibALL.get_Vector("4223"))
				._delete_anlage(new RECORD_ARCHIVMEDIEN("160405"))
				._delete_anlage(new RECORD_ARCHIVMEDIEN("160404"))
				._to("tata@toto.com")
				._remove_targets(bibALL.get_Vector("toto@tata.com","bla@bla.com"))	
				._save();
				
				_fill_down_grid(geladetMail);
			}
			else{

				String test = "sebastien.franck84@gmail.com";

				neueMail = 
						new RB_Email()
						._betreff("Hello world !")
						//						._absender("toto@tata.com")
						//												._send_typ_bcc()
						//												._send_typ_cc()
						._send_typ(SEND_TYPE.SINGLE)
						//												._absender_by_userId("1168")
						//												._text("Are u alive !")
						//												._setTable(_TAB.fibu)
						//						._setId("1470")
						//												._set_table_and_id(_TAB.fibu, "1470")
						//												._add_anlage(_TAB.fibu, "10577")
						//												._add_anlage(_TAB.fibu, bibALL.get_Vector("10577"))
						._add_anlage(new RECORD_ARCHIVMEDIEN("160405"))
						._add_anlage(new RECLIST_ARCHIVMEDIEN(ARCHIVMEDIEN.id_archivmedien.fieldName() +" =160404", ""))
						._to("tata@toto.com")
						._to(bibALL.get_Vector("toto@tata.com","bla@bla.com"))
						;
				;
				_fill_down_grid(neueMail);
			}
		}
	}

	private class ownSendMail extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			neueMail._send();

		}

	}


}
