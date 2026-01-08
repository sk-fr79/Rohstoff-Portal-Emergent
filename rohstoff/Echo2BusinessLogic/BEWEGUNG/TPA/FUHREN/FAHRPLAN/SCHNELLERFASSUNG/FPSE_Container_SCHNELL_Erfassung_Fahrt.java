package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.SCHNELLERFASSUNG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MyE2_StringSortable;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Tabb_Sheet_For_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.Echo2.components.MaskSearchField.XX_MaskActionAfterFoundNonDB;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.Button4SearchResultList;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Alternative_Result_Button_Generator;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.Echo2.components.unboundDataFields.UB_SelectField;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextArea;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldWithSelector;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField_With_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.unboundDataFields.VALIDATE_INPUT_DATE;
import panter.gmbh.Echo2.components.unboundDataFields.VALIDATE_INPUT_Integer;
import panter.gmbh.Echo2.components.unboundDataFields.VECTOR_UB_FIELDS;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MITARBEITER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MITARBEITER;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP_SelectFahrtenVarianten;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;
import rohstoff.utils.FinderMainAdress;
import rohstoff.utils.MyAdress;
import rohstoff.utils.MyArtikelBezeichnungKunde;
import rohstoff.utils.bibROHSTOFF;

/*
 * modul, zur schnellen erfassung von fahrplanpositionen (fuer mitarbeiter mit nicht allen
 * kenntnissen
 */
public class FPSE_Container_SCHNELL_Erfassung_Fahrt extends Project_BasicModuleContainer
{

	private MyE2_Grid 						oGridBase = 			new MyE2_Grid(8,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

	private		FPSE_UB_SearchFieldAdresse 		oSearchStartAdresse = 	new FPSE_UB_SearchFieldAdresse("ID_ADRESSE_LAGER_START",false,240,100);
	private		FPSE_UB_SearchFieldAdresse 		oSearchZielAdresse = 	new FPSE_UB_SearchFieldAdresse("ID_ADRESSE_LAGER_ZIEL",false,240,100);
	private 	UB_TextField_With_DatePOPUP_OWN tfVormerkungVon = 		new UB_TextField_With_DatePOPUP_OWN("DAT_VORGEMERKT_FP",false,"",100);
	private 	UB_TextField_With_DatePOPUP_OWN tfVormerkungBis = 		new UB_TextField_With_DatePOPUP_OWN("DAT_VORGEMERKT_ENDE_FP",false,"",100);
	
	private 	UB_TextFieldWithSelector 		tfAnrufer = 			new UB_TextFieldWithSelector("ANRUFER_FP",false,"",null,new MyE2_String("<leer>").CTrans());
	private 	UB_TextField 		  			tfAnrufdatum = 			new UB_TextField("ANRUF_DATUM_FP",false,bibALL.get_cDateNOW(),100,10);

	private 	UB_TextArea 					tfTextFieldBemerkung = 	new UB_TextArea("BEMERKUNG",true,"",300,5,400);
	
	private 	MyE2_Button  					oButtonSaveFahrt =  	null;
	private 	VECTOR_UB_FIELDS  				vUnboundFields = 		new VECTOR_UB_FIELDS();
	
	private 	FP_SelectFahrtenVarianten       oSelVarianten = 		null;
	
	private 	Vector<unterGrid_Container_Sorte_Taetigkeit>   vUnterGridContainer = 		new 	Vector<unterGrid_Container_Sorte_Taetigkeit>();
	
	private 	MyE2_Button   					oButtonAddFahrt = new MyE2_Button(E2_ResourceIcon.get_RI("add_field.png"));

	
	public FPSE_Container_SCHNELL_Erfassung_Fahrt() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_FAHRTENSCHNELLERFASSUNG);

		this.oSelVarianten = new FP_SelectFahrtenVarianten(this);
		
		this.oSearchStartAdresse.set_oMaskActionAfterMaskValueIsFound(new actionAfterFoundStartAdress());
		this.oSearchZielAdresse.set_oMaskActionAfterMaskValueIsFound(new actionAfterFoundZielAdress());
		
		this.oButtonSaveFahrt = new MyE2_Button("FAHRT SPEICHERN");
		this.oButtonSaveFahrt.setFont(new E2_FontBold(2));
		this.oButtonSaveFahrt.add_oActionAgent(new actionSaveFahrt());

		this.tfAnrufer.get_oPopUp().get_oContainerEx().setWidth(new Extent(300));
		this.tfAnrufer.get_oPopUp().get_oContainerEx().setHeight(new Extent(200));
		this.tfAnrufer.get_oTextField().set_iWidthPixel(200);
		
		this.tfAnrufdatum.add_InputValidator(new VALIDATE_INPUT_DATE());
		this.tfVormerkungVon.add_InputValidator(new VALIDATE_INPUT_DATE());
		this.tfVormerkungBis.add_InputValidator(new VALIDATE_INPUT_DATE());

		//die felder in einen vector zusammenfassen, damit es einfacher geht
		this.vUnboundFields.add(oSearchStartAdresse);
		this.vUnboundFields.add(oSearchZielAdresse);
		this.vUnboundFields.add(tfVormerkungVon);
		this.vUnboundFields.add(tfVormerkungBis);
		this.vUnboundFields.add(tfAnrufer);
		this.vUnboundFields.add(tfAnrufdatum);
		
		this.vUnterGridContainer.add(new unterGrid_Container_Sorte_Taetigkeit());            //immer mindestens ein container
		
		//fuer die positionsspeicherung muss den datumspopups ein tag mitgegeben werden
		this.tfVormerkungVon.EXT().set_MASK_SAVE_TAG("FUHREN_ERFASSUNG_DATUM_VON");
		this.tfVormerkungBis.EXT().set_MASK_SAVE_TAG("FUHREN_ERFASSUNG_DATUM_BIS");
		
		//bei der datumsvon-angabe muss noch ein weiter actionagent auf den Datumsbutton gefuegt werden
		this.tfVormerkungVon.get_vActionAgentsZusatz().removeAllElements();
		this.tfVormerkungVon.get_vActionAgentsZusatz().add(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				FPSE_Container_SCHNELL_Erfassung_Fahrt oThis = FPSE_Container_SCHNELL_Erfassung_Fahrt.this;
				if (S.isEmpty(oThis.tfVormerkungBis.get_oTextField().getText()))
				{
					oThis.tfVormerkungBis.get_oTextField().setText(oThis.tfVormerkungVon.get_oTextField().getText());
				}
			}
		});
		
		/*
		 * noch einen fahrtblock eintragen
		 */
		this.oButtonAddFahrt.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				unterGrid_Container_Sorte_Taetigkeit subGrid = new unterGrid_Container_Sorte_Taetigkeit();
				FPSE_Container_SCHNELL_Erfassung_Fahrt.this.vUnterGridContainer.add(subGrid);
				FPSE_Container_SCHNELL_Erfassung_Fahrt.this.__build_Content();
				
				//jetzt die popup-sortenbuttons der Startadresse in die neue Sortenzeile mit uebernehmen
				unterGrid_Container_Sorte_Taetigkeit unterGridContainerundSorteOrig = FPSE_Container_SCHNELL_Erfassung_Fahrt.this.vUnterGridContainer.get(0);
				unterGrid_Container_Sorte_Taetigkeit unterGridContainerundSorteNeu = FPSE_Container_SCHNELL_Erfassung_Fahrt.this.vUnterGridContainer.get(
									FPSE_Container_SCHNELL_Erfassung_Fahrt.this.vUnterGridContainer.size()-1);
				
				MyE2_PopUpMenue oPopUpQuelle = unterGridContainerundSorteOrig.get_PopUpSortenAbholadresse();
				MyE2_PopUpMenue oPopUpZiel = unterGridContainerundSorteNeu.get_PopUpSortenAbholadresse();
				
				for (int i=0;i<oPopUpQuelle.get_vMenueButtons().size();i++)
				{
				    sortenButtonInPopup oButton = (sortenButtonInPopup)oPopUpQuelle.get_vMenueButtons().get(i);
					oPopUpZiel.addButton(new sortenButtonInPopup(oButton.getText(),oButton.get_ID_ARTIKEL(),subGrid),true);
				}
			}
		});
		this.oButtonAddFahrt.setToolTipText(new MyE2_String("Eine weitere Position hinzufügen ").CTrans());
		
		
		this.__build_Content();
		this.add(oGridBase,E2_INSETS.I_2_2_2_2);
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(500), new MyE2_String("Erfassung einer Fahrt ..."));
	}

	
	private void __build_Content() throws myException
	{
		Insets oIn = new Insets(4,1,2,2);
		Insets oIn2 = new Insets(10,1,2,2);
		Insets oIn2a = new Insets(5,1,2,2);
		Insets oIn3 = new Insets(4,20,2,2);
		Insets oIn_1 = new Insets(4,1,2,0);
		Insets oIn_2a = new Insets(5,1,2,0);

		this.oGridBase.removeAll();
		
		oGridBase.add(new MyE2_Label(new MyE2_String("Erfassung einer Fahrt"),MyE2_Label.STYLE_TITEL_BIG()),8, new Insets(4,20,2,20));
		
		oGridBase.add(new MyE2_Label(new MyE2_String("Vorlagen"),MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),1, oIn);
		oGridBase.add(this.oSelVarianten,7, oIn2);

		oGridBase.add(new MyE2_Label(new MyE2_String("Startadresse (A)"),MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),1, oIn);
		oGridBase.add(new E2_ComponentGroupHorizontal(0,oSearchStartAdresse,new adressPopUpButtonHomeAdresses(oSearchStartAdresse),E2_INSETS.I_0_0_10_0),7, oIn2);
		
		oGridBase.add(new MyE2_Label(new MyE2_String("Zieladresse (B)"),MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),1, oIn);
		oGridBase.add(new E2_ComponentGroupHorizontal(0,oSearchZielAdresse,new adressPopUpButtonHomeAdresses(oSearchZielAdresse),E2_INSETS.I_0_0_10_0),7, oIn2);

		
		
		//die sammelliste der Container
		//   ---zuerst die ueberschrift
		oGridBase.add(new MyE2_Label(new MyE2_String("")),1,oIn_1);
		oGridBase.add(new MyE2_Label(new MyE2_String(" "),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),1,oIn2);
		oGridBase.add(new MyE2_Label(new MyE2_String("Anz"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),1,oIn2);
		oGridBase.add(new MyE2_Label(new MyE2_String("Sorte"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),2,oIn_2a);
		oGridBase.add(new MyE2_Label(new MyE2_String("Containertyp"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),1,oIn_2a);
		oGridBase.add(new MyE2_Label(new MyE2_String("Tätigkeit"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),1,oIn_2a);
		oGridBase.add(new MyE2_Label(new MyE2_String("")),1,oIn_1);
		
		//      --- dann den ersten container mit beschriftung
		oGridBase.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Container"),MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),this.oButtonAddFahrt,E2_INSETS.I_0_0_10_0),1, oIn);
		oGridBase.add(this.vUnterGridContainer.get(0).get_oButtonRemoveThisZeile(),1,oIn_1);
		oGridBase.add(this.vUnterGridContainer.get(0).get_Anzahlfield(),1,oIn_1);
		oGridBase.add(this.vUnterGridContainer.get(0).get_oSearchSorte(),1,oIn2a);
		oGridBase.add(this.vUnterGridContainer.get(0).get_PopUpSortenAbholadresse(),1,oIn2a);
		oGridBase.add(this.vUnterGridContainer.get(0).get_oSelectFieldContainer(),1,oIn2a);
		oGridBase.add(this.vUnterGridContainer.get(0).get_Taetigkeit(),1,oIn2a);
		oGridBase.add(this.vUnterGridContainer.get(0).get_BT_StartZiel(),1,oIn2a);
		
		// ... alle ab dem 2. drunter
		for (int i=1;i<this.vUnterGridContainer.size();i++)
		{
			oGridBase.add(new MyE2_Label(new MyE2_String(""),MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),1, oIn_1);
			oGridBase.add(this.vUnterGridContainer.get(i).get_oButtonRemoveThisZeile(),1,oIn_1);
			oGridBase.add(this.vUnterGridContainer.get(i).get_Anzahlfield(),1,oIn_1);
			oGridBase.add(this.vUnterGridContainer.get(i).get_oSearchSorte(),1,oIn2a);
			oGridBase.add(this.vUnterGridContainer.get(i).get_PopUpSortenAbholadresse(),1,oIn2a);
			oGridBase.add(this.vUnterGridContainer.get(i).get_oSelectFieldContainer(),1,oIn2a);
			oGridBase.add(this.vUnterGridContainer.get(i).get_Taetigkeit(),1,oIn2a);
			oGridBase.add(this.vUnterGridContainer.get(i).get_BT_StartZiel(),1,oIn2a);		}
		
		oGridBase.add(new MyE2_Label(new MyE2_String("Bemerkung"),MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),1, oIn);
		oGridBase.add(tfTextFieldBemerkung,7, oIn2);
		
		oGridBase.add(new MyE2_Label(new MyE2_String("Datum von"),MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),1, oIn);
		oGridBase.add(new E2_ComponentGroupHorizontal(0,tfVormerkungVon,
														new MyE2_Label("bis"),
														tfVormerkungBis,
														E2_INSETS.I_0_0_5_0),		7,oIn2);
		
		
		oGridBase.add(new MyE2_Label(new MyE2_String("Anrufer"),MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),1, oIn);
		oGridBase.add(tfAnrufer,7, oIn2);
		
		oGridBase.add(new MyE2_Label(new MyE2_String("Anrufdatum"),MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),1, oIn);
		oGridBase.add(tfAnrufdatum,7, oIn2);
		
		oGridBase.add(new E2_ComponentGroupHorizontal(0,this.oButtonSaveFahrt,E2_INSETS.I_0_0_0_0),8, oIn3);
		
		this.vUnboundFields.set_StyleForInput(true);

	}

	
	
	
	public Vector<unterGrid_Container_Sorte_Taetigkeit> get_vUnterGridContainer()
	{
		return vUnterGridContainer;
	}

	/**
	 * nach einer aenderung der start- oder zieladresse von den Fremdadresse die mitarbeiter holen und 
	 */
	private void __pruefe_MitarbeiterNachSuchvorgang() throws myException
	{
		String cID_ADRESSE_Start = 	FPSE_Container_SCHNELL_Erfassung_Fahrt.this.oSearchStartAdresse.get_oTextFieldForSearchInput().getText();
		String cID_ADRESSE_Ziel = 	FPSE_Container_SCHNELL_Erfassung_Fahrt.this.oSearchZielAdresse.get_oTextFieldForSearchInput().getText();
		
		if (bibALL.isLong(cID_ADRESSE_Start))
		{
			cID_ADRESSE_Start = new MyLong(cID_ADRESSE_Start).get_cUF_LongString();
		}
		else
		{
			cID_ADRESSE_Start = null;
		}
		
		if (bibALL.isLong(cID_ADRESSE_Ziel))
		{
			cID_ADRESSE_Ziel = new MyLong(cID_ADRESSE_Ziel).get_cUF_LongString();
		}
		else
		{
			cID_ADRESSE_Ziel = null;
		}

		
		Vector<String> vListenWerte = new Vector<String>();            // werte fuer das popup
		Vector<String> vFeldWerte = new Vector<String>();              // werte fuer die Maske

		if (cID_ADRESSE_Start != null)
		{
			this.ladeAdresse(cID_ADRESSE_Start, vListenWerte, vFeldWerte);
		}

		if (cID_ADRESSE_Ziel != null)
		{
			this.ladeAdresse(cID_ADRESSE_Ziel, vListenWerte, vFeldWerte);
		}

		this.tfAnrufer.set_Varianten(null, "<leer>", null);
		
		//jetzt die mitarbeiterliste in den jeweiligen popup-auswahl eintragen
		if (vFeldWerte.size()>0)
		{
			String[][] cArrayWerte = new String[vFeldWerte.size()][2];
			for (int i=0;i<vFeldWerte.size();i++)
			{
				cArrayWerte[i][0]=vListenWerte.get(i);
				cArrayWerte[i][1]=vFeldWerte.get(i);
			}
			
			this.tfAnrufer.set_Varianten(cArrayWerte, "<leer>", null);
		}
	}
	
	
	
	private void ladeAdresse(String cID_ADRESSE, Vector<String> vListenWerte ,Vector<String> vFeldWerte) throws myException
	{
		RECORD_ADRESSE recZiel = new RECORD_ADRESSE(cID_ADRESSE);
		
		if (recZiel.get_ADRESSTYP_lValue(new Long(-1)).longValue()==myCONST.ADRESSTYP_LIEFERADRESSE)
		{
			recZiel = recZiel.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
		}
		
//		if (!recZiel.get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()))
//		{
//			RECLIST_MITARBEITER recListMitarbeiter = recZiel.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis();
//			
//			for (int i=0; i<recListMitarbeiter.get_vKeyValues().size();i++)
//			{
//				RECORD_MITARBEITER recMit = recListMitarbeiter.get(i);
//				if (recMit.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().is_AKTIV_YES())
//				{
//					vListenWerte.add("<"+bibALL.HoechstLaenge(recZiel.get_NAME1_cF_NN("")+" "+recZiel.get_NAME2_cUF_NN(""),20)+">  "+
//										recMit.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().get_VORNAME_cUF_NN("")+" "+recMit.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().get_NAME1_cUF_NN(""));
//					vFeldWerte.add(recMit.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().get_VORNAME_cUF_NN("")+" "+recMit.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().get_NAME1_cUF_NN(""));
//				}
//			}
//		}
		
		//2020-09-01: neu: liste sortieren
		
		if (!recZiel.get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT())) {
			RECLIST_MITARBEITER recListMitarbeiter = recZiel.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis();
			
			HMAP<String, String> mapMitarbeiter = new HMAP<String, String>();
			
			for (int i=0; i<recListMitarbeiter.get_vKeyValues().size();i++) {
				RECORD_MITARBEITER recMit = recListMitarbeiter.get(i);
				if (recMit.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().is_AKTIV_YES())	{
					String key ="<"+bibALL.HoechstLaenge(recZiel.get_NAME1_cF_NN("")+" "+recZiel.get_NAME2_cUF_NN(""),20)+">  "+
										recMit.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().get_VORNAME_cUF_NN("")+" "+recMit.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().get_NAME1_cUF_NN("");
					String value = recMit.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().get_VORNAME_cUF_NN("")+" "+recMit.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().get_NAME1_cUF_NN("");
					key = key.replace("    ", " ");
					key = key.replace("   ", " ");
					key = key.replace("  ", " ");
					
					mapMitarbeiter._put(key, value);
				}
			}
			VEK<String> sorter = new VEK<String>()._a(mapMitarbeiter.keySet());
			sorter.sort((a,b)->{return a.compareTo(b);});
			HMAP<String, String> mapSortedMitarbeiter = new HMAP<String, String>();
			for (String s: sorter) {
				mapSortedMitarbeiter._put(s, mapMitarbeiter.get(s));
			}
			
			mapSortedMitarbeiter.getKeys().stream().forEach((s)->{
				vListenWerte.add(s);
				vFeldWerte.add(mapSortedMitarbeiter.get(s));
			});
		}
		
		
		
	

	}
	
	
	
	
	
	/*
	 * klasse mit jeweils einer kombination aus sorte und container, wenn eine fahrt aus mehreren positionen besteht
	 */
	public class unterGrid_Container_Sorte_Taetigkeit 
	{
		private	FPSE_UB_SearchFieldSorte		oSearchSorte = 			null;
		private UB_SelectField  	  			oSelectFieldContainer = null;
		private VECTOR_UB_FIELDS  				vUnboundFieldsSubGrid = new VECTOR_UB_FIELDS();
		private MyE2_PopUpMenue 			    popUpSortenAbholadresse = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("popup.png"),E2_ResourceIcon.get_RI("popup.png"),true);
		private UB_TextField  					tfAnzahl = new UB_TextField("ANZAHL_CONTAINER_FP",false,"1",30,3);
		private UB_TextFieldWithSelector  		oTaetigkeit	= 			null;			
		private FPSE_BT_START_ZIEL   			oButtonStartZiel = 		new FPSE_BT_START_ZIEL();
		
		private MyE2_Button   					oButtonRemoveThisZeile = new MyE2_Button(E2_ResourceIcon.get_RI("clear.png"));
		


		public unterGrid_Container_Sorte_Taetigkeit() throws myException
		{
			this.oSearchSorte = 			new FPSE_UB_SearchFieldSorte("ID_ARTIKEL",true,130,50);
			this.oSearchSorte.set_oAlternative_Result_Button_Generator(new AlternativeSearchButtons());
			
			//dem delete-button dieses objekt selbst mitteilen
			this.oButtonRemoveThisZeile.EXT().set_O_PLACE_FOR_EVERYTHING(this);
			this.oButtonRemoveThisZeile.setToolTipText(new MyE2_String("Löschen dieser Zeile ...").CTrans());
			this.oButtonRemoveThisZeile.add_oActionAgent(new actionRemoveZeile());
			
			this.oSelectFieldContainer = 	new UB_SelectField("ID_CONTAINERTYP_FP",true,
															"SELECT KURZBEZEICHNUNG,ID_CONTAINERTYP FROM "+
															bibE2.cTO()+".JT_CONTAINERTYP ORDER BY KURZBEZEICHNUNG",
															false,true,false,false);

			this.oTaetigkeit = 				new UB_TextFieldWithSelector("TAETIGKEIT_FP",
															false,
															"",
															"SELECT TEXTTITEL,TEXT FROM "+bibE2.cTO()+".JT_TEXTBLOCK,"+
															bibE2.cTO()+".JT_TEXTBLOCK_KAT " +
															" WHERE " +
															" JT_TEXTBLOCK.ID_TEXTBLOCK_KAT=JT_TEXTBLOCK_KAT.ID_TEXTBLOCK_KAT AND UPPER(JT_TEXTBLOCK_KAT.BEZEICHNUNG)='FAHRPLAN_TAETIGKEIT'"
															);

			this.oTaetigkeit.set_bEmptyAllowd(false);
			this.oTaetigkeit.get_oTextField().set_iWidthPixel(100);
			
			this.oSelectFieldContainer.add_InputValidator(new VALIDATE_INPUT_Integer());
			this.oSelectFieldContainer.setWidth(new Extent(150));
			
			this.tfAnzahl.add_InputValidator(new VALIDATE_INPUT_Integer());
			
			this.vUnboundFieldsSubGrid.add(this.oSearchSorte);
			this.vUnboundFieldsSubGrid.add(this.oSelectFieldContainer);
			this.vUnboundFieldsSubGrid.add(this.tfAnzahl);
			this.vUnboundFieldsSubGrid.add(oTaetigkeit);

			this.popUpSortenAbholadresse.get_oContainerEx().setWidth(new Extent(300));
			this.popUpSortenAbholadresse.get_oContainerEx().setHeight(new Extent(200));
			this.popUpSortenAbholadresse.setToolTipText(new MyE2_String("Sorten der Startadresse ...").CTrans());
			
			this.vUnboundFieldsSubGrid.set_StyleForInput(true);
			this.MarkContainerTypen();
		}

		
		private class actionRemoveZeile extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				FPSE_Container_SCHNELL_Erfassung_Fahrt oThis = FPSE_Container_SCHNELL_Erfassung_Fahrt.this;
				
				MyE2_Button ownButton = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();
				
				if (oThis.vUnterGridContainer.size()>1)
				{
					oThis.vUnterGridContainer.remove(ownButton.EXT().get_O_PLACE_FOR_EVERYTHING());
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine Zeile MUSS stehenbleiben ...")));
				}
				oThis.__build_Content();
			}
		}

		public VECTOR_UB_FIELDS 			get_vUnboundFields()  			{	return vUnboundFieldsSubGrid;		}
		public FPSE_UB_SearchFieldSorte 	get_oSearchSorte()				{	return oSearchSorte;				}
		public UB_SelectField 				get_oSelectFieldContainer()		{	return oSelectFieldContainer;		}
		public MyE2_PopUpMenue 				get_PopUpSortenAbholadresse()	{	return popUpSortenAbholadresse;		}
		public UB_TextField   			   	get_Anzahlfield() 				{	return tfAnzahl; 					}
		public UB_TextFieldWithSelector    	get_Taetigkeit() 				{	return oTaetigkeit; 				}
		public FPSE_BT_START_ZIEL           get_BT_StartZiel() 				{   return this.oButtonStartZiel;		}
		public MyE2_Button 					get_oButtonRemoveThisZeile() 	{	return oButtonRemoveThisZeile;		}

		
		public void MarkContainerTypen() throws myException
		{
		
			//alles "entmarkieren"
			this.oSelectFieldContainer.MarkValues(new Vector<String>(),"");
			
			String cID_ADRESSE_Start = 	FPSE_Container_SCHNELL_Erfassung_Fahrt.this.oSearchStartAdresse.get_oTextFieldForSearchInput().getText();
			String cID_ADRESSE_Ziel = 	FPSE_Container_SCHNELL_Erfassung_Fahrt.this.oSearchZielAdresse.get_oTextFieldForSearchInput().getText();
			
			if (bibALL.isLong(cID_ADRESSE_Start))
			{
				cID_ADRESSE_Start = new MyLong(cID_ADRESSE_Start).get_cUF_LongString();
			}
			else
			{
				return;
			}
			
			if (bibALL.isLong(cID_ADRESSE_Ziel))
			{
				cID_ADRESSE_Ziel = new MyLong(cID_ADRESSE_Ziel).get_cUF_LongString();
			}
			else
			{
				return;
			}
			
			
			Vector<String> vOwnLieferadressen = bibROHSTOFF.get_vEigeneLieferadressen();
			
			String cID_fremdAdresse = cID_ADRESSE_Start;
			if (vOwnLieferadressen.contains(cID_fremdAdresse))
			{
				cID_fremdAdresse = cID_ADRESSE_Ziel;
			}
			if (vOwnLieferadressen.contains(cID_fremdAdresse))
			{
				//dann sind beide eigenadressen, nix wird markiert
				return;
			}
			
			
			MyAdress oAdress = new MyAdress(cID_fremdAdresse, false);
			/*
			 * zuerst feststellen, ob es eine lieferadresse ist
			 */
			MyAdress oAdressBase = oAdress.get_oBaseAdressFromLieferadress();
			if (oAdressBase != null)
				cID_fremdAdresse = oAdressBase.get_cID_ADRESSE_orig();
			
			/*
			 * sonst wird nur gegen die erlaubten containertypen selektiert
			 */
			String cQueryContainertyp = "SELECT ID_CONTAINERTYP FROM "+bibE2.cTO()+".JT_CONTAINERTYP " +
			   		" WHERE ID_CONTAINERTYP IN (SELECT ID_CONTAINERTYP FROM "+bibE2.cTO()+".JT_ADR_CONTAINERTYP WHERE ID_ADRESSE="+cID_fremdAdresse+") "+
					"ORDER BY KURZBEZEICHNUNG";
			
			
			String[][] cVarianten = bibDB.EinzelAbfrageInArray(cQueryContainertyp);
			
			this.oSelectFieldContainer.MarkValues(bibALL.get_VectorAusArrayColumn(cVarianten,0),"--> ");
		}
	}
	
	
	
	
	public FPSE_UB_SearchFieldAdresse get_oSearchStartAdresse()
	{
		return oSearchStartAdresse;
	}
	
	public FPSE_UB_SearchFieldAdresse get_oSearchZielAdresse()
	{
		return oSearchZielAdresse;
	}
	
	
	
	private class adressPopUpButtonHomeAdresses extends MyE2_PopUpMenue
	{

		public adressPopUpButtonHomeAdresses(FPSE_UB_SearchFieldAdresse osearchAdresse) throws myException
		{
			super(E2_ResourceIcon.get_RI("lager.png"),E2_ResourceIcon.get_RI("lager.png"),true);
			this.get_oContainerEx().setWidth(new Extent(300));
			this.get_oContainerEx().setHeight(new Extent(200));

			
			this.setToolTipText(new MyE2_String("Eigene Adresse / eigenes Lager einfügen").CTrans());
			

//2013-07-08: neue aufbaumethode, schneller			
			String cQuery = "SELECT A.ID_ADRESSE,A.NAME1,A.ORT FROM "+bibE2.cTO()+".JT_LIEFERADRESSE LA" +
									" INNER JOIN "+bibE2.cTO()+".JT_ADRESSE A ON (LA.ID_ADRESSE_LIEFER=A.ID_ADRESSE) WHERE A.SONDERLAGER IS NULL AND " +
									"LA.ID_ADRESSE_BASIS="+bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF();
			
			String[][] cAdressen = bibDB.EinzelAbfrageInArray(cQuery, "");
			
			
			Vector<RECORD_ADRESSE> vAdressen = new Vector<RECORD_ADRESSE>();
			RECORD_ADRESSE oMandant = new RECORD_ADRESSE(bibALL.get_ID_ADRESS_MANDANT());
			vAdressen.add(oMandant);

			//hauptadresse anfuegen
			this.add_Button(oMandant.get_ID_ADRESSE_cUF(), oMandant.get_NAME1_cF_NN("")+"-"+oMandant.get_ORT_cF_NN("")+"  <H>", osearchAdresse, new E2_FontBold(2));
			
			for (int i=0;i<cAdressen.length;i++) {
				this.add_Button(cAdressen[i][0], cAdressen[i][1]+"-"+cAdressen[i][2]+"  <L>", osearchAdresse, null);
			}
			
			
//			RECLIST_LIEFERADRESSE RECLIST_ADRESSE = oMandant.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis();
//			if (RECLIST_ADRESSE.get_vKeyValues().size()>0)
//			{
//				for (String cID:RECLIST_ADRESSE.get_vKeyValues())
//				{
//					vAdressen.add(RECLIST_ADRESSE.get(cID).get_UP_RECORD_ADRESSE_id_adresse_liefer());
//				}
//			}
//			for (int i=0;i<vAdressen.size();i++)
//			{
//				String cText = vAdressen.get(i).get_NAME1_cF_NN("")+"-"+vAdressen.get(i).get_ORT_cF_NN("")+(i==0?"  <H>":"   <L>");
//				MyE2_Button oButton = new MyE2_Button(new MyE2_String(cText,false));
//				if (i==0)
//				{
//					oButton.setFont(new E2_FontBold(2));    //hauptadresse
//				}
//				oButton.EXT().set_C_MERKMAL(vAdressen.get(i).get_ID_ADRESSE_cUF());
//				oButton.EXT().set_O_PLACE_FOR_EVERYTHING(osearchAdresse);
//				oButton.EXT().set_O_PLACE_FOR_EVERYTHING2(vAdressen.get(i));
//				oButton.add_oActionAgent(new actionAgentForPopupAdressButtons());
//				this.addButton(oButton, true);
//			}
		}
		
		
		private void add_Button(String cID_ADRESSE,String cBeschriftung, FPSE_UB_SearchFieldAdresse osearchAdresse, Font oFont) {
			MyE2_Button oButton = new MyE2_Button(new MyE2_String(cBeschriftung,false));
			oButton.EXT().set_C_MERKMAL(cID_ADRESSE);
			oButton.EXT().set_O_PLACE_FOR_EVERYTHING(osearchAdresse);
			//oButton.EXT().set_O_PLACE_FOR_EVERYTHING2(vAdressen.get(i));
			oButton.add_oActionAgent(new actionAgentForPopupAdressButtons());
			this.addButton(oButton, true);
			
			if (oFont != null)  {
				oButton.setFont(oFont);
			}

		}
		
		private class actionAgentForPopupAdressButtons extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				MyE2_Button oButton = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
				String cID_ADRESSE = oButton.EXT().get_C_MERKMAL();
				((FPSE_UB_SearchFieldAdresse)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_oTextFieldForSearchInput().setText(cID_ADRESSE);
				((FPSE_UB_SearchFieldAdresse)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).FillLabelWithDBQuery(cID_ADRESSE);
				((FPSE_UB_SearchFieldAdresse)oButton.EXT().get_O_PLACE_FOR_EVERYTHING()).perform_XX_MaskActionAfterFound(cID_ADRESSE,true);
			}
		}

	}
	
	
	/*
	 * ActionAfterFoundAdress wird nach dem fuellen/Suchen ausgefuehrt
	 */
	private class actionAfterFoundStartAdress extends XX_MaskActionAfterFoundNonDB
	{
		@Override
		public void doMaskSettingsAfterValueWrittenInMaskField(	String maskValue, MyE2_MaskSearchField searchField,	boolean afterAction) throws myException
		{
			//jetzt den sorten-popup-fuellen
			FPSE_Container_SCHNELL_Erfassung_Fahrt oThis = FPSE_Container_SCHNELL_Erfassung_Fahrt.this;
			
			for (unterGrid_Container_Sorte_Taetigkeit subGrid: oThis.vUnterGridContainer)
			{
				subGrid.MarkContainerTypen();
			}
			
			RECORD_ADRESSE oAdresse = new RECORD_ADRESSE(maskValue);
			if (oAdresse.get_ADRESSTYP_lValue(new Long(0)).intValue()==myCONST.ADRESSTYP_LIEFERADRESSE)
			{
				//dann die hauptadresse raussuchen
				oAdresse = oAdresse.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
			}
			
			
			String cQuery = "SELECT "+ 
								"   DISTINCT NVL(JT_ARTIKEL.ANR1,'--')||' '||NVL(JT_ARTIKEL.ARTBEZ1,'--'), JT_ARTIKEL.ID_ARTIKEL "+
								" FROM "+ 
								"    "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF "+ 
								" INNER JOIN "+ 
								"     "+bibE2.cTO()+".JT_ARTIKEL_BEZ "+ 
								"    ON "+ 
								"    ( "+
								"         "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ =  "+bibE2.cTO()+".JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ "+
								"    ) "+ 
								" INNER JOIN "+ 
								"     "+bibE2.cTO()+".JT_ARTIKEL "+ 
								"    ON "+ 
								"    ( "+
								"         "+bibE2.cTO()+".JT_ARTIKEL_BEZ.ID_ARTIKEL =  "+bibE2.cTO()+".JT_ARTIKEL.ID_ARTIKEL "+
								"    ) "+ 
								" WHERE JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+oAdresse.get_ID_ADRESSE_cUF()+" ORDER BY NVL(JT_ARTIKEL.ANR1,'--')||' '||NVL(JT_ARTIKEL.ARTBEZ1,'--')";
			
			String cArtikelBlock[][] = bibDB.EinzelAbfrageInArray(cQuery,"");
			
			
			//RECLIST_ARTIKELBEZ_LIEF oArtbezLief = oAdresse.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse();
			
			Vector<MyE2_StringSortable>		vButtonText = new Vector<MyE2_StringSortable>();
			
//--- hier mit eine abfrage beschleunigen

			for (int i=0;i<cArtikelBlock.length;i++)
			{
				vButtonText.add(new MyE2_StringSortable(cArtikelBlock[i][0],false,cArtikelBlock[i][1]));
			}
			
			
			for (unterGrid_Container_Sorte_Taetigkeit subGrid:oThis.vUnterGridContainer)
			{
				subGrid.get_PopUpSortenAbholadresse().removeAllButtons();
				for (int i=0;i<vButtonText.size();i++)
				{
					subGrid.get_PopUpSortenAbholadresse().addButton(new sortenButtonInPopup(vButtonText.get(i).CTrans(),(String)vButtonText.get(i).get_oPlace4All(),subGrid),true);
				}
			}
			
			
			oThis.__pruefe_MitarbeiterNachSuchvorgang();
		}
	}
	
	
	private class actionAfterFoundZielAdress extends XX_MaskActionAfterFoundNonDB
	{
		@Override
		public void doMaskSettingsAfterValueWrittenInMaskField(String maskValue, MyE2_MaskSearchField searchField,boolean afterAction) throws myException
		{
			for (unterGrid_Container_Sorte_Taetigkeit subGrid: FPSE_Container_SCHNELL_Erfassung_Fahrt.this.vUnterGridContainer)
			{
				subGrid.MarkContainerTypen();
			}
			 FPSE_Container_SCHNELL_Erfassung_Fahrt.this.__pruefe_MitarbeiterNachSuchvorgang();

		}
	}

	
	
	//zur selektion der gelisteten sorten der startadresse
	private class sortenButtonInPopup extends MyE2_Button
	{
		private unterGrid_Container_Sorte_Taetigkeit SubGrid = null;
		private String 					   ID_ARTIKEL = null;
		
		public sortenButtonInPopup(String text, String cID_ARTIKEL_UF, unterGrid_Container_Sorte_Taetigkeit subGrid)
		{
			super(text);
			this.add_oActionAgent(new actionAgentForPopupSortenButtons());
			this.SubGrid = subGrid;
			this.ID_ARTIKEL = cID_ARTIKEL_UF;
		}
		
		private class actionAgentForPopupSortenButtons extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				sortenButtonInPopup.this.SubGrid.get_oSearchSorte().get_oTextFieldForSearchInput().setText(sortenButtonInPopup.this.ID_ARTIKEL);
				sortenButtonInPopup.this.SubGrid.get_oSearchSorte().FillLabelWithDBQuery(sortenButtonInPopup.this.ID_ARTIKEL);
			}
		}
		public void set_SubGridThisBelongsTo(unterGrid_Container_Sorte_Taetigkeit subGrid)
		{
			SubGrid = subGrid;
		}
		public String get_ID_ARTIKEL()
		{
			return ID_ARTIKEL;
		}

	}


	
	
	private class actionSaveFahrt extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			//daten einsammeln und validieren
			FPSE_Container_SCHNELL_Erfassung_Fahrt oThis = FPSE_Container_SCHNELL_Erfassung_Fahrt.this;
			
			//zuerst pruefen, ob das endedatum leer ist, wenn ja mit dem startdatum fuellen
			if (S.isFull(new MyDate(oThis.tfVormerkungVon.get_oTextField().getText(),"","").get_cUmwandlungsergebnis()))
			{
				if (S.isEmpty(new MyDate(oThis.tfVormerkungBis.get_oTextField().getText(),"","").get_cUmwandlungsergebnis()))
				{
					oThis.tfVormerkungBis.get_oTextField().setText(oThis.tfVormerkungVon.get_oTextField().getText());
				}
			}
			
			VECTOR_UB_FIELDS  vUBFieldsForCheckCorrectInput = new VECTOR_UB_FIELDS();
			vUBFieldsForCheckCorrectInput.addAll(FPSE_Container_SCHNELL_Erfassung_Fahrt.this.vUnboundFields);
			for (unterGrid_Container_Sorte_Taetigkeit subGrid: FPSE_Container_SCHNELL_Erfassung_Fahrt.this.vUnterGridContainer)
			{
				vUBFieldsForCheckCorrectInput.addAll(subGrid.get_vUnboundFields());
			}
			
			bibMSG.add_MESSAGE(vUBFieldsForCheckCorrectInput.get_MV_AllFieldsAreOK_ShowErrorInput());
			
			
			if (bibMSG.get_bIsOK())
			{
				//dann n datensaetze schreiben
				
				Vector<String>  vSQL = new Vector<String>();
				
				try
				{
					// soviele fahren schreiben, wie subgrid-Sorten und container-angaben vorhanden sind
					for (unterGrid_Container_Sorte_Taetigkeit subGrid: FPSE_Container_SCHNELL_Erfassung_Fahrt.this.vUnterGridContainer)
					{
					
						int iAnzahl = new MyInteger(subGrid.get_Anzahlfield().getText(),new Integer(-1),new Integer(-1)).get_iValue();
						
						if (iAnzahl == -1)
							throw new myException(this,"Error no correct number in ANZAHL - field !");
						
						
						//felder aus der eingabe
						String cID_AdresseStart = new MyLong(oThis.oSearchStartAdresse.get_oTextFieldForSearchInput().getText(),new Long(0),new Long(0)).get_cUF_LongString();
						String cID_AdresseZiel = new MyLong(oThis.oSearchZielAdresse.get_oTextFieldForSearchInput().getText(),new Long(0),new Long(0)).get_cUF_LongString();
						
						if (!subGrid.get_BT_StartZiel().is_bStartNachZiel())
						{
							cID_AdresseZiel = new MyLong(oThis.oSearchStartAdresse.get_oTextFieldForSearchInput().getText(),new Long(0),new Long(0)).get_cUF_LongString();
							cID_AdresseStart = new MyLong(oThis.oSearchZielAdresse.get_oTextFieldForSearchInput().getText(),new Long(0),new Long(0)).get_cUF_LongString();
						}
						
						String cID_Sorte = new MyLong(subGrid.get_oSearchSorte().get_oTextFieldForSearchInput().getText(),new Long(0),new Long(0)).get_cUF_LongString();
						
						String cID_Container_Typ = new MyLong(subGrid.get_oSelectFieldContainer().get_ActualWert(),new Long(0),new Long(0)).get_cUF_LongString();
					
						String cTaetigkeit = S.NN(subGrid.get_Taetigkeit().get_oTextField().getText());
						String cBemerkung  = S.NN(oThis.tfTextFieldBemerkung.getText());
						String cAnrufer    = S.NN(oThis.tfAnrufer.get_oTextField().getText());
						
						String cVormerkungVon = new MyDate(oThis.tfVormerkungVon.get_oTextField().getText(),"","").get_cDBFormatErgebnis();
						String cVormerkungBis = new MyDate(oThis.tfVormerkungBis.get_oTextField().getText(),"","").get_cDBFormatErgebnis();
						String cAnrufDatum = 	new MyDate(oThis.tfAnrufdatum.getText(),"","").get_cDBFormatErgebnis();
						
						//jetzt pruefen, ob alles ok
					
						RECORD_ADRESSE oRecStartLager = new RECORD_ADRESSE(cID_AdresseStart);
						RECORD_ADRESSE oRecZielLager = new RECORD_ADRESSE(cID_AdresseZiel);
						
						RECORD_ADRESSE oRecStartBasis = null;
						RECORD_ADRESSE oRecZielBasis = null;
						
						RECORD_ARTIKEL 		oRecArtikel = null;
						
						MyArtikelBezeichnungKunde oArtikelbez_Start = null;
						MyArtikelBezeichnungKunde oArtikelbez_Ziel  = null;
						
						String cID_EAK_CODE = "";
						String cBASEL_CODE = "NULL";
						String cBASEL_NOTIZ = "NULL";
	
						if (oRecStartLager.get_ADRESSTYP_lValue(new Long(-1)) == myCONST.ADRESSTYP_FIRMENINFO)
						{
							oRecStartBasis = oRecStartLager;
						}
						else
						{
							oRecStartBasis = oRecStartLager.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
						}
						
						if (oRecZielLager.get_ADRESSTYP_lValue(new Long(-1)) == myCONST.ADRESSTYP_FIRMENINFO)
						{
							oRecZielBasis = oRecZielLager;
						}
						else
						{
							oRecZielBasis = oRecZielLager.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
						}
						
						if (!cID_Sorte.equals("0"))
						{
							oRecArtikel = new RECORD_ARTIKEL(cID_Sorte);
							
							oArtikelbez_Start = new MyArtikelBezeichnungKunde(oRecStartBasis.get_ID_ADRESSE_cUF(),
																				oRecArtikel.get_ID_ARTIKEL_cUF());
							oArtikelbez_Ziel = new MyArtikelBezeichnungKunde(oRecZielBasis.get_ID_ADRESSE_cUF(),
																				oRecArtikel.get_ID_ARTIKEL_cUF());
	
							// eak-code suchen
							if (! oArtikelbez_Start.get_ID_EAK_CODE().equals("0"))
							{
								cID_EAK_CODE = oArtikelbez_Start.get_ID_EAK_CODE();
							}
							// Basel-Code
							if (!bibALL.isEmpty(oArtikelbez_Start.get_BASEL_CODE()))
								cBASEL_CODE = oArtikelbez_Start.get_BASEL_CODE();
							
							if (!bibALL.isEmpty(oArtikelbez_Start.get_BASEL_NOTIZ()))
								cBASEL_NOTIZ = oArtikelbez_Start.get_BASEL_NOTIZ();
	
						}
								
						RECORD_ADRESSE_extend oRecStartHelp = new RECORD_ADRESSE_extend(oRecStartBasis);
						RECORD_ADRESSE_extend oRecZielHelp = new RECORD_ADRESSE_extend(oRecZielBasis);
						
	
						MySqlStatementBuilder oZuordnung = new MySqlStatementBuilder();
						
						oZuordnung.addSQL_Paar("ID_VPOS_TPA_FUHRE",			"SEQ_VPOS_TPA_FUHRE.NEXTVAL",false);
						
						oZuordnung.addSQL_Paar("ID_ADRESSE_START",			oRecStartBasis.get_ID_ADRESSE_cUF(),false);
						oZuordnung.addSQL_Paar("ID_ADRESSE_ZIEL",			oRecZielBasis.get_ID_ADRESSE_cUF(),false);
	
						oZuordnung.addSQL_Paar("ID_ADRESSE_LAGER_START",	oRecStartLager.get_UnFormatedValue("ID_ADRESSE"),false);
						oZuordnung.addSQL_Paar("ID_ADRESSE_LAGER_ZIEL",		oRecZielLager.get_UnFormatedValue("ID_ADRESSE"),false);
	
						oZuordnung.addSQL_Paar("L_NAME1",					oRecStartLager.get_UnFormatedValue("NAME1"),true);
						oZuordnung.addSQL_Paar("L_NAME2",					oRecStartLager.get_UnFormatedValue("NAME2"),true);
						oZuordnung.addSQL_Paar("L_NAME3",					oRecStartLager.get_UnFormatedValue("NAME3"),true);
						oZuordnung.addSQL_Paar("L_STRASSE",					oRecStartLager.get_UnFormatedValue("STRASSE"),true);
						oZuordnung.addSQL_Paar("L_HAUSNUMMER",				oRecStartLager.get_UnFormatedValue("HAUSNUMMER"),true);
						oZuordnung.addSQL_Paar("L_PLZ",						oRecStartLager.get_UnFormatedValue("PLZ"),true);
						oZuordnung.addSQL_Paar("L_ORT",						oRecStartLager.get_UnFormatedValue("ORT"),true);
						oZuordnung.addSQL_Paar("L_ORTZUSATZ",				oRecStartLager.get_UnFormatedValue("ORTZUSATZ"),true);
						oZuordnung.addSQL_Paar("L_LAENDERCODE",			    oRecStartLager.get_ID_LAND_cUF_NN("--").equals("--")?"":oRecStartLager.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""),true);
						oZuordnung.addSQL_Paar("TEL_LIEFERANT",				oRecStartHelp.get_StandardTelefonNumber(),true);
						oZuordnung.addSQL_Paar("FAX_LIEFERANT",				oRecStartHelp.get_StandardFaxNumber(),true);
						oZuordnung.addSQL_Paar("OEFFNUNGSZEITEN_LIEF",		oRecStartLager.get_ADRESSTYP_lValue(new Long(0))==myCONST.ADRESSTYP_FIRMENINFO	?
																							oRecStartLager.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_OEFFNUNGSZEITEN_cF_NN(""):     		//hauptadresse, oeffnungszeiten in firmeninfo
																							oRecStartLager.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_OEFFNUNGSZEITEN_cF_NN(""),		//hauptadresse, oeffnungszeiten in lieferadresse
																			true);
	
						oZuordnung.addSQL_Paar("A_NAME1",					oRecZielLager.get_UnFormatedValue("NAME1"),true);
						oZuordnung.addSQL_Paar("A_NAME2",					oRecZielLager.get_UnFormatedValue("NAME2"),true);
						oZuordnung.addSQL_Paar("A_NAME3",					oRecZielLager.get_UnFormatedValue("NAME3"),true);
						oZuordnung.addSQL_Paar("A_STRASSE",					oRecZielLager.get_UnFormatedValue("STRASSE"),true);
						oZuordnung.addSQL_Paar("A_HAUSNUMMER",				oRecZielLager.get_UnFormatedValue("HAUSNUMMER"),true);
						oZuordnung.addSQL_Paar("A_PLZ",						oRecZielLager.get_UnFormatedValue("PLZ"),true);
						oZuordnung.addSQL_Paar("A_ORT",						oRecZielLager.get_UnFormatedValue("ORT"),true);
						oZuordnung.addSQL_Paar("A_ORTZUSATZ",				oRecZielLager.get_UnFormatedValue("ORTZUSATZ"),true);
						oZuordnung.addSQL_Paar("A_LAENDERCODE",				oRecZielLager.get_ID_LAND_cUF_NN("--").equals("--")?"":oRecZielLager.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""),true);
						oZuordnung.addSQL_Paar("TEL_ABNEHMER",				oRecZielHelp.get_StandardTelefonNumber(),true);
						oZuordnung.addSQL_Paar("FAX_ABNEHMER",				oRecZielHelp.get_StandardFaxNumber(),true);
						oZuordnung.addSQL_Paar("OEFFNUNGSZEITEN_ABN",		oRecZielLager.get_ADRESSTYP_lValue(new Long(0))==myCONST.ADRESSTYP_FIRMENINFO	?
																							oRecZielLager.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_OEFFNUNGSZEITEN_cF_NN(""):     		//hauptadresse, oeffnungszeiten in firmeninfo
																							oRecZielLager.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_OEFFNUNGSZEITEN_cF_NN(""),		//hauptadresse, oeffnungszeiten in lieferadresse
																							true);
	
						oZuordnung.addSQL_Paar("DATUM_ABHOLUNG",			cVormerkungVon,true);
						oZuordnung.addSQL_Paar("DATUM_ANLIEFERUNG",			cVormerkungVon,true);
						oZuordnung.addSQL_Paar("DATUM_ABHOLUNG_ENDE",		cVormerkungBis,true);
						oZuordnung.addSQL_Paar("DATUM_ANLIEFERUNG_ENDE",	cVormerkungBis,true);
						
						oZuordnung.addSQL_Paar("TRANSPORTKENNZEICHEN",		"",true);
						oZuordnung.addSQL_Paar("ANHAENGERKENNZEICHEN",		"",true);
						
						oZuordnung.addSQL_Paar("TRANSPORTMITTEL",			"LKW",true);
						oZuordnung.addSQL_Paar("ID_ARTIKEL",				oRecArtikel==null ? "":oRecArtikel.get_ID_ARTIKEL_cUF(),false);
						
						String cWertEKVK_Lock = "N";
						
						if (oArtikelbez_Start != null && oArtikelbez_Ziel == null)
						{
							if (oArtikelbez_Start.get_ANR1().equals(oArtikelbez_Ziel.get_ANR1()))
							{
								cWertEKVK_Lock = "Y";
							}
						}
						
						oZuordnung.addSQL_Paar("EK_VK_SORTE_LOCK",		cWertEKVK_Lock,true);
						
						if (oArtikelbez_Start != null && oArtikelbez_Ziel == null)
						{
							oZuordnung.addSQL_Paar("ID_ARTIKEL_BEZ_EK",		oArtikelbez_Start.get_ID_ARTIKEL_BEZ(),false);
							oZuordnung.addSQL_Paar("ANR1_EK",				oArtikelbez_Start.get_ANR1(),true);
							oZuordnung.addSQL_Paar("ANR2_EK",				oArtikelbez_Start.get_ANR2(),true);
							oZuordnung.addSQL_Paar("ARTBEZ1_EK",			oArtikelbez_Start.get_ARTBEZ1(),true);
							oZuordnung.addSQL_Paar("ARTBEZ2_EK",			oArtikelbez_Start.get_ARTBEZ2(),true);
		
							oZuordnung.addSQL_Paar("ID_ARTIKEL_BEZ_VK",		oArtikelbez_Ziel.get_ID_ARTIKEL_BEZ(),false);
							oZuordnung.addSQL_Paar("ANR1_VK",				oArtikelbez_Ziel.get_ANR1(),true);
							oZuordnung.addSQL_Paar("ANR2_VK",				oArtikelbez_Ziel.get_ANR2(),true);
							oZuordnung.addSQL_Paar("ARTBEZ1_VK",			oArtikelbez_Ziel.get_ARTBEZ1(),true);
							oZuordnung.addSQL_Paar("ARTBEZ2_VK",			oArtikelbez_Ziel.get_ARTBEZ2(),true);
						}					
	
						if (oRecArtikel != null)
						{
							oZuordnung.addSQL_Paar("ZOLLTARIFNR",			oRecArtikel.get_ZOLLTARIFNR_cF_NN(""),true);
							oZuordnung.addSQL_Paar("EUNOTIZ",				oRecArtikel.get_EUNOTIZ_cF_NN(""),true);
							oZuordnung.addSQL_Paar("EUCODE",				oRecArtikel.get_EUCODE_cF_NN(""),true);
						}
						
						// aenderung AVV 
						oZuordnung.addSQL_Paar("ID_EAK_CODE",			cID_EAK_CODE,false);
						oZuordnung.addSQL_Paar("BASEL_CODE",			cBASEL_CODE,true);
						oZuordnung.addSQL_Paar("BASEL_NOTIZ",			cBASEL_NOTIZ,true);
						oZuordnung.addSQL_Paar("PRINT_EU_AMTSBLATT",	"Y",true);
						
						//fahrplan-spezifische felder
						oZuordnung.addSQL_Paar("BEMERKUNG",					cBemerkung,true);
						oZuordnung.addSQL_Paar("DAT_VORGEMERKT_FP",			cVormerkungVon,true);
						oZuordnung.addSQL_Paar("DAT_VORGEMERKT_ENDE_FP",	cVormerkungBis,true);
						oZuordnung.addSQL_Paar("DAT_FAHRPLAN_FP",			"",true);
						oZuordnung.addSQL_Paar("ID_MASCHINEN_LKW_FP",		"",true);
						oZuordnung.addSQL_Paar("ID_MASCHINEN_ANH_FP",		"",true);
						oZuordnung.addSQL_Paar("ID_CONTAINERTYP_FP",		cID_Container_Typ.equals("0") ? "":cID_Container_Typ,false);
						oZuordnung.addSQL_Paar("ANZAHL_CONTAINER_FP",		""+iAnzahl,false);
						
						
						oZuordnung.addSQL_Paar("TAETIGKEIT_FP",				cTaetigkeit,true);
						
						//dafuer sorgen, dass alles in einer fahrplangruppe auftaucht !!
						if (vSQL.size()==0)
						{
							oZuordnung.addSQL_Paar("FAHRPLANGRUPPE_FP",			"SEQ_"+bibALL.get_ID_MANDANT()+"_FAHRPLANGRUPPE.NEXTVAL",false);
						}
						else
						{
							oZuordnung.addSQL_Paar("FAHRPLANGRUPPE_FP",			"SEQ_"+bibALL.get_ID_MANDANT()+"_FAHRPLANGRUPPE.CURRVAL",false);
						}
						oZuordnung.addSQL_Paar("EAN_CODE_FP",				"'FU-'||TO_CHAR(SEQ_VPOS_TPA_FUHRE.CURRVAL)",false);
						oZuordnung.addSQL_Paar("FAHRT_ANFANG_FP",			"",true);
						oZuordnung.addSQL_Paar("FAHRT_ENDE_FP",				"",true);
						oZuordnung.addSQL_Paar("ERFASSER_FP",				bibALL.get_KUERZEL(),true);
						oZuordnung.addSQL_Paar("IST_GEPLANT_FP",			"N",true);
						oZuordnung.addSQL_Paar("SORTIERUNG_FP",				"0",true);
						oZuordnung.addSQL_Paar("ANRUFER_FP",				cAnrufer,true);
						oZuordnung.addSQL_Paar("ANRUFDATUM_FP",				cAnrufDatum,true);
						oZuordnung.addSQL_Paar("BEMERKUNG_START_FP",		oRecStartLager.get_BEMERKUNG_FAHRPLAN_cUF_NN(""),true);
						oZuordnung.addSQL_Paar("BEMERKUNG_ZIEL_FP",			oRecZielLager.get_BEMERKUNG_FAHRPLAN_cUF_NN(""),true);
						oZuordnung.addSQL_Paar("KENNER_ALTE_SAETZE_FP",		"N",true);
						oZuordnung.addSQL_Paar("FUHRE_AUS_FAHRPLAN",		"Y",true);
						oZuordnung.addSQL_Paar("FAHRER_FP",					"",true);
						oZuordnung.addSQL_Paar("FUHRE_KOMPLETT",			"N",true);		
						
						String cSQL1 = "INSERT INTO "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE "+ 
						oZuordnung.get_cFieldsBlock(true)+" VALUES "+oZuordnung.get_cValuesBlock(true);
						
						vSQL.add(cSQL1);
					}
				
					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));

					if (bibMSG.get_bIsOK())
					{
						String cFahrplangruppe = bibDB.EinzelAbfrage("SELECT SEQ_"+bibALL.get_ID_MANDANT()+"_FAHRPLANGRUPPE.CURRVAL FROM DUAL");
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurden ",true,""+vSQL.size(),false," Fahrplan-Eintraeg(e) geschrieben ... Gruppe: ",true,cFahrplangruppe,false)));
						
						//jetzt nach einem listencontainer fahrplanpool suchen und wenn gefunden, aktualisieren
						
						//2015-04-17: rekursivsuche ist unnoetig, da da E2_TabbedPaneForFirstContainer in der session steht
//						E2_RecursiveSearch_Component oSearchAll = new E2_RecursiveSearch_Component(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION(),
//																								   bibALL.get_Vector(E2_TabbedPaneForFirstContainer.class.getName()),
//																								   null);
//						
//						if (oSearchAll.get_vAllComponents().size()==1)
//						{
//							E2_TabbedPaneForFirstContainer oTabbed = (E2_TabbedPaneForFirstContainer)oSearchAll.get_vAllComponents().get(0);
							
//							for (E2_Tabb_Sheet_For_BasicModuleContainer oSheet: oTabbed.get_vTabbs())
							for (E2_Tabb_Sheet_For_BasicModuleContainer oSheet: bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().get_vTabbs())
							{
								if (S.NN(oSheet.get_oModuleContainer().get_MODUL_IDENTIFIER()).equals(E2_MODULNAMES.NAME_MODUL_FAHRTENPOOL))
								{
									FU_LIST_ModulContainer oContainer = (FU_LIST_ModulContainer)oSheet.get_oModuleContainer();
									oContainer.get_oNavList().get_vActualID_Segment().add(bibDB.EinzelAbfrage("SELECT "+bibE2.cTO()+".SEQ_VPOS_TPA_FUHRE.CURRVAL FROM DUAL"));
									oContainer.get_oNavList()._REBUILD_ACTUAL_SITE("");
								}
							}
							
						//}
						
						oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
					
				} 
				catch (myException e)
				{
					e.printStackTrace();
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Abfrage der Daten !!!")));
				}
				
				
			}
		
		}
	}



	private class AlternativeSearchButtons extends XX_Alternative_Result_Button_Generator
	{

		private Vector<XX_Button4SearchResultList[]> vRueck = null;
		
		@Override
		public boolean get_bUseAlternative()  throws myException
		{
			boolean bRueck = false;
			
			//starten muss die alternative suche, wenn eine adresse, die nicht eine mandanten-adresse ist, eingegeben wurde
			FPSE_Container_SCHNELL_Erfassung_Fahrt oThis = FPSE_Container_SCHNELL_Erfassung_Fahrt.this;
			
			MyLong  longAdresseStart = new MyLong(oThis.oSearchStartAdresse.get_oTextFieldForSearchInput().getText(), new Long(-1),new Long(-1));
			MyLong  longAdresseZiel = new MyLong(oThis.oSearchZielAdresse.get_oTextFieldForSearchInput().getText(), new Long(-1),new Long(-1));
			
			RECORD_ADRESSE recStartFirma = null;
			RECORD_ADRESSE recZielFirma = null;
			
			//hauptadressen suchen (falls es diese nicht schon sind)
			
			String cID_ADRESSE_START = "-1";
			String cID_ADRESSE_ZIEL = "-1";
			
			if (longAdresseStart.get_lValue() != -1)
			{
				recStartFirma = new FinderMainAdress(longAdresseStart.get_cUF_LongString()).get_recMAIN_ADRESSE();
				if (!recStartFirma.get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()))
				{
					cID_ADRESSE_START =recStartFirma.get_ID_ADRESSE_cUF(); 
				}
				
			}
			if (longAdresseZiel.get_lValue() != -1)
			{
				recZielFirma = new FinderMainAdress(longAdresseZiel.get_cUF_LongString()).get_recMAIN_ADRESSE();

				if (!recZielFirma.get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()))
				{
					cID_ADRESSE_ZIEL =recZielFirma.get_ID_ADRESSE_cUF(); 
				}
			}
			
			String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ID_ARTIKEL IN ("+
								" SELECT ID_ARTIKEL FROM JT_ARTIKEL_BEZ WHERE ID_ARTIKEL_BEZ IN ("+
									" SELECT ID_ARTIKEL_BEZ FROM JT_ARTIKEL_BEZ "+
										" WHERE ID_ARTIKEL_BEZ IN (SELECT ID_ARTIKEL_BEZ FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ADRESSE="+cID_ADRESSE_START+")"+
										" OR    ID_ARTIKEL_BEZ IN (SELECT ID_ARTIKEL_BEZ FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ADRESSE="+cID_ADRESSE_ZIEL+")))" +
							" ORDER BY ANR1";

			RECLIST_ARTIKEL  recListArt = new RECLIST_ARTIKEL(cQuery);
			
			if (recListArt.size()>0)
			{
				this.vRueck = new Vector<XX_Button4SearchResultList[]>();
				
				for (int i=0;i<recListArt.get_vKeyValues().size();i++)
				{
					XX_Button4SearchResultList[] butArray = new XX_Button4SearchResultList[2];
					RECORD_ARTIKEL recArt = recListArt.get(i);
					butArray[0]=new Button4SearchResultList(new MyE2_String("["+recArt.get_ANR1_cUF()+"] "+recArt.get_ARTBEZ1_cUF_NN(""),false));
					butArray[0].EXT().set_C_MERKMAL(recArt.get_ID_ARTIKEL_cUF());
					
					butArray[1]=new Button4SearchResultList(new MyE2_String(recArt.get_ID_ARTIKEL_cF(),false));
					butArray[1].EXT().set_C_MERKMAL(recArt.get_ID_ARTIKEL_cUF());
					
					this.vRueck.add(butArray);
				}
			}
			
			if (this.vRueck != null && this.vRueck.size()>0)
			{
				bRueck = true;
			}
			return bRueck;
		}

		@Override
		public Vector<XX_Button4SearchResultList[]> get_vResultButtons() throws myException
		{
			return this.vRueck;
		}
		
	}



	
	
}
