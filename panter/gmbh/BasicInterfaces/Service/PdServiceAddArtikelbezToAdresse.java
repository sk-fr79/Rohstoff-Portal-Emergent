package panter.gmbh.BasicInterfaces.Service;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HighLevelSearchAVVCode;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;


/**
 * Klasse , um eine zuordnung adresse und kundenspezifische artikelbezeichnung zu definieren
 * abstract, damit fuer jeden fall eine eigene klasse definiert werden muss (groessenspeicherung)
 * @author martin
 *
 */
public class PdServiceAddArtikelbezToAdresse extends E2_BasicModuleContainer {

	private Rec21_adresse			f_recAdress = 	null;
	private Rec21_artikel_bez		f_recArtBez = 	null;
	private E2_Grid                 f_grid = 		new E2_Grid();
	private selectAVV_Code          f_selectAVV = 	new selectAVV_Code();
	
	
	//wenn gespeichert wurde, dann ist dieser wert gefuellt
	private RECORD_ARTIKELBEZ_LIEF  f_artbez_lief_stored = null;
	
	private MyE2_Button  			btSave = new MyE2_Button(new MyE2_String("Speichern"),MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder(new E2_FontBold(2)));

	
	public PdServiceAddArtikelbezToAdresse(Rec21 recAdress, Rec21 recArtBez) throws myException {
		super();
		this.f_recAdress = new Rec21_adresse(recAdress);
		this.f_recArtBez = new Rec21_artikel_bez(recArtBez);

		this.add(this.f_grid, E2_INSETS.I(2,2,2,2));
		
		this.btSave.add_oActionAgent(new saveAction());
		this.btSave.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				PdServiceAddArtikelbezToAdresse.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		});
		
		RB_gld l_title = 	new RB_gld()._ins(E2_INSETS.I(5,5,5,5))._left_top()._col(new E2_ColorDark());
		RB_gld l_body = 	new RB_gld()._ins(E2_INSETS.I(5,5,5,5))._left_top();
		
		f_grid._setSize(150,150,400,20,20)._bo(new Border(1, new E2_ColorDDark(), Border.STYLE_SOLID))
			._a(new MyE2_Label(new MyE2_String("Hinzufügen einer kundenspezifischen Artikelbezeichnung: "),new E2_FontBold(),true), new RB_gld()._span(5)._ins(E2_INSETS.I(2,2,2,10))._left_top())
			
			._a(new MyE2_Label(new MyE2_String("Firma"),			new E2_FontItalic()),l_title)
			._a(new MyE2_Label(new MyE2_String("Sortenbezeichnung"),new E2_FontItalic()),l_title)
			._a(new MyE2_Label(new MyE2_String("AVV-Code"),			new E2_FontItalic()),l_title)
			._a(new MyE2_Label(new MyE2_String(""), 				new E2_FontItalic()),l_title)
			._a(new MyE2_Label(new MyE2_String(""), 				new E2_FontItalic()),l_title)
			
			._a(new MyE2_Label(this.f_recAdress.get__FullNameAndAdress_name_anschrift(),true),l_body)
			._a(new MyE2_Label(this.f_recArtBez.__get_ANR1_ANR2_ARTBEZ1(),true),l_body)
			._a(this.f_selectAVV,l_body)
			._a(new setKleinAnliefererAVV(),l_body._c()._center_mid())
			._a(new setAVVExMandant(),l_body._c()._center_mid())
			._a(new E2_Grid()._a(this.btSave),l_body._c()._span(5))
			;
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(750), new Extent(400), new MyE2_String("Sortenbezeichnung zu Kunde hinzufügen"));
		
	}
	
	public Rec21_adresse get_recAdress() {
		return f_recAdress;
	}

	public Rec21_artikel_bez get_recArtBez() {
		return f_recArtBez;
	}
	

	public RECORD_ARTIKELBEZ_LIEF get_artbez_lief_stored() {
		return f_artbez_lief_stored;
	}

	
	private class setKleinAnliefererAVV extends MyE2_Button {

		public setKleinAnliefererAVV() throws myException {
			super(E2_ResourceIcon.get_RI("wizard.png"));
			this._ttt(new MyE2_String("AVV-Code aus der Artikelbezeichnung laden:  KLEINANLIEFERER-AVV"));
			
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					String id = PdServiceAddArtikelbezToAdresse.this.f_selectAVV.hole_StandardAVV_Code_kleinanlieferer();
					if (S.isEmpty(id)) {
						bibMSG.add_MESSAGE(new MyE2_Warning_Message("KLEINANLIEFERER-AVV dieser Sorte ist leer !"));
					}
				}
			} );
		}
	}
	
	private class setAVVExMandant extends MyE2_Button {

		public setAVVExMandant() throws myException {
			super(E2_ResourceIcon.get_RI("wizard_mini.png"));
			this._ttt(new MyE2_String("AVV-Code aus der Artikelbezeichnung laden:  AVV-Code ex Mandant"));
			
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					String id = PdServiceAddArtikelbezToAdresse.this.f_selectAVV.hole_StandardAVV_Code_ex_mandant();
					if (S.isEmpty(id)) {
						bibMSG.add_MESSAGE(new MyE2_Warning_Message("AVV-Code ex Mandant dieser Sorte ist leer !"));
					}
				}
			} );
		}
	}
	

	
	
	private class selectAVV_Code extends RB_HighLevelSearchAVVCode {

		public selectAVV_Code() throws myException {
			super();
		}

		@Override
		public Rec21 get_actual_artikel() throws myException {
			PdServiceAddArtikelbezToAdresse oThis = PdServiceAddArtikelbezToAdresse.this;
			return oThis.f_recArtBez.get_up_Rec21(ARTIKEL.id_artikel);
		}
		
	}
	
	
	/**
	 * speichert den status
	 * @author martin
	 *
	 */
	private class saveAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			PdServiceAddArtikelbezToAdresse oThis = PdServiceAddArtikelbezToAdresse.this;
			
			if (S.isEmpty(oThis.f_selectAVV.rb_readValue_4_dataobject())) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst einen AVV-Code definieren !")));
			} else {
			
				RECORDNEW_ARTIKELBEZ_LIEF abl = new RECORDNEW_ARTIKELBEZ_LIEF();
				abl.nv(ARTIKELBEZ_LIEF.id_adresse,oThis.f_recAdress.getFs(ADRESSE.id_adresse));
				abl.nv(ARTIKELBEZ_LIEF.id_artikel_bez,oThis.f_recArtBez.getFs(ARTIKEL_BEZ.id_artikel_bez));
				abl.nv(ARTIKELBEZ_LIEF.id_eak_code,oThis.f_selectAVV.rb_readValue_4_dataobject());
				abl.nv(ARTIKELBEZ_LIEF.artbez_typ,"EK");

				RECORD_ARTIKELBEZ_LIEF r = abl.do_WRITE_NEW_ARTIKELBEZ_LIEF(bibMSG.MV());
				if (bibMSG.MV().get_bIsOK()) {
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Artikelbezeichnung wurde hinzugefügt !")));
				}
			}
			
		}
		
	}


	
	public MyE2_Button get_btSave() {
	
		return btSave;
	}


	
	
	
}
