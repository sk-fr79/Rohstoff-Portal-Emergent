package _Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;

import javax.xml.parsers.ParserConfigurationException;

import org.codehaus.jettison.json.JSONException;
import org.xml.sax.SAXException;

import echopointng.Separator;
import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.webcontainer.command.BrowserOpenWindowCommand;
import panter.gmbh.BasicInterfaces.Check;
import panter.gmbh.BasicInterfaces.Service.PdServiceFindNearestPtFromGeoMidPt;
import panter.gmbh.BasicInterfaces.Service.PdServiceHandleSanktionsChecks;
import panter.gmbh.BasicInterfaces.Service.PdServiceJrxmlParameterReader;
import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceGeoCodeNameStrassePlzOrtLandBean;
import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceWriteInReportVerlaufBean;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.COMP.BETA.E2_KaskadeSuche_Assistant.DEMO_KaskadeSelectionSuche;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.REPORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.GEOCODIERUNG.ENUM_GEO_Error;
import panter.gmbh.basics4project.GEOCODIERUNG.ENUM_GEO_MARKER_COLOR;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Coordinate4Map;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_ErrorMap;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Factory_URLForLocations;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Location;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Locations;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Road_Model;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Routing_JSON_Parser;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Routing_Map;
import panter.gmbh.basics4project.SANKTION.SANKTION_Adresse_scan_popUp;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSelectField_Factory_Extended;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.businesslogic.bewegung2.lager.selector.B2_LIST_Selector_Lager_NG;

public class TESTMODUL_SEBASTIEN extends E2_BasicModuleContainer {

	private RB_TextField id_adresse_field = new RB_TextField(120);
	private RB_TextArea erg_area = new RB_TextArea(600, 10);
	private E2_Grid component_test_area = new E2_Grid()._setSize(1200)._bo_b();
	//	private JSONArray paths_obj;

	public TESTMODUL_SEBASTIEN() throws myException {
		super();

		E2_Button bt_testSanktion = new E2_Button()._t("test_sanktion_pruefung")
				._aaa(new test_sanktion())._standard_text_button();

		E2_Button bt_popup = new E2_Button()._t("test popup")._standard_text_button();
		bt_popup._aaa(() -> test_popup());

		E2_Button bt_jaspertest = new E2_Button()._t("test report_log insert")._standard_text_button()
				._aaa(new own_email_test_schicken());

		E2_Button bt_test_new_sha256_hash = new E2_Button()._t("Adresse SHA 256 test")._standard_text_button();
		bt_test_new_sha256_hash._aaa(()->test_sha256_hash());

		E2_Button bt_test_new_sanktion_query = new E2_Button()._t("DEBUG SANKTION DB QUERIES")._standard_text_button();
		bt_test_new_sanktion_query._aaa(new test_sanktion_name());

		E2_Button bt_test_combination = new E2_Button()._t("Test all possible pairs")._standard_text_button();
		bt_test_combination._aaa(new test_possible_pairs());

		E2_Button bt_test_polyline_display = new E2_Button()._t("5525->3112 route generieren (Polyline)")._standard_text_button();
		bt_test_polyline_display._aaa(()->generate_polyline());

		E2_Button bt_test_jdbc_normalize = new E2_Button()._t("normalize jdbc")._standard_text_button();
		bt_test_jdbc_normalize._aaa(()->jdbc_string_normalize_test());

		DEMO_KaskadeSelectionSuche oDemoKaskadeSuche = new DEMO_KaskadeSelectionSuche();

		E2_Button bt_test_correction = new E2_Button()._t("test correction: bibArray")._standard_text_button();
		bt_test_correction._aaa(()->test_correction());

		E2_Button bt_testJrxmlParse = new E2_Button()._t("Test JRXML Parser")._standard_text_button();
		bt_testJrxmlParse._aaa(()->new PdServiceJrxmlParameterReader()
				._init("/daten/entwicklungstools/tomcat/webapps/rohstoff_app/reports/ALLE/AW_WARENSTROEME_save.jrxml").readParameter());
		

		//---------------------------------



		B2_LIST_Selector_Lager_NG oSelLager = new B2_LIST_Selector_Lager_NG();
		XX_ActionAgent oActionAgent = new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.MV()._addAlarm("LAGER SELEKTOR , WHERE BLOCK = " + oSelLager.get_WhereBlock());			
			}
		};

		oSelLager.add_ActionAgentToComponent(oActionAgent);


		//		B2_LIST_Selector_Firma_NG oSelFirma = new B2_LIST_Selector_Firma_NG(oSelLager, null, "tralalala = #WERT#");
		//		this.add(oSelFirma.get_oComponentForSelection(),E2_INSETS.I(0,10,5,0));
		//		this.add(oSelLager.get_oComponentForSelection(),E2_INSETS.I(0,0,0,20));
		//		
		//----------------------------	

		this.add(
				new E2_Grid()._setSize(160, 120, 16)
				//				._a("ID adresse", new RB_gld()._ins(5))
				//				._a(id_adresse_field, 			new RB_gld()._ins(5)._span(2))
				//				._a(bt_testSanktion, 			new RB_gld()._ins(5)._span(3))
				//				._a(bt_popup, 					new RB_gld()._span(3)._left_mid()._ins(5))
				//				._a(bt_jaspertest, 				new RB_gld()._span(3)._left_mid()._ins(5))
				//				._a(bt_test_new_sha256_hash, 	new RB_gld()._span(3)._left_mid()._ins(5))
				//				._a(bt_test_new_sanktion_query, new RB_gld()._span(3)._left_mid()._ins(5))
				//				._a(bt_test_combination, 		new RB_gld()._span(3)._left_mid()._ins(5))
				//				._a(bt_test_polyline_display, 	new RB_gld()._span(3)._left_mid()._ins(5))
				//				._a(bt_test_jdbc_normalize, 	new RB_gld()._span(3)._left_mid()._ins(5))
				//				._a(bt_test_correction, 		new RB_gld()._span(3)._left_mid()._ins(5))
				//				._a(new Separator(), 			new RB_gld()._span(3)._left_mid()._ins(5))
				
				._a(new LAG_LagerSelectField_Factory_Extended(true).setSelectfieldWidth(800).render(), new RB_gld()._span(3)._left_mid()._ins(5))
				._a(bt_testJrxmlParse, new RB_gld()._span(3)._left_mid()._ins(5))
				);


		//
		//		this.add(erg_area);
		//		this.add(component_test_area);
		//		this.add(new Separator());
		//		this.add(new UUID_Generator());
		//		this.add(new Separator());
		//		this.add(new ownGeocoordinatedisplayer());
		//		this.add(oDemoKaskadeSuche);

	}


	/**
	 * @author sebastien
	 * @return 
	 * @date 27.08.2019
	 *
	 * @return
	 */
	private String[][] test_correction() {
		String[][] oTestArray = null;
		if(oTestArray == null) {
			oTestArray = new String[0][2];
		}
		return oTestArray;
	}


	private class test_sanktion_name extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			//			VEK<String> test_vektor_1 = new VEK<String>()._a("OSAMA")._a("LADEN")._a("BIN")._a("");
			//			VEK<String> test_vektor_2 = new VEK<String>()._a("MUGABE")._a("ROBERT")._a("")._a("");
			//			VEK<String> test_vektor_3 = new VEK<String>()._a("KIM")._a("JONG")._a("EUN")._a("");
			//			VEK<String> test_vektor_4 = new VEK<String>()._a("SEBASTIEN")._a("FRANCK")._a("")._a("");
			//			VEK<String> test_vektor_5 = new VEK<String>()._a("METALLVERWERTUNGSGESELLSCHAFT")._a("MBH")._a("")._a("");

			String id_2_test = id_adresse_field.getText();
			if(S.isFull(id_2_test)) {
				//				Rec21_adresse rec2test = new Rec21_adresse()._fill_id(id_2_test);

				PdServiceHandleSanktionsChecks sanktion_service = new PdServiceHandleSanktionsChecks()
						._send_meldung(false)
						.initWithAdresses(new VEK<String>()._a(id_2_test)/*._a("40506")._a("17642")._a("28172")*/);

				;

				component_test_area._clear()._a(sanktion_service.render_ergebnis());

				//				new SANKTION_Check_In_SanktionDB_4_Name().check(bibMSG.MV(),rec2test);
				erg_area.setText(id_2_test);
			}
		}

	}

	private class test_sanktion extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			String test_id = id_adresse_field.getText();

			//			VEK<String> vTests = new VEK<String>()._a(
			//					"20602"
			//					,"30545"
			//					,"31075"
			//					,"36390"
			//					,"39147"
			//					,"39766"
			//					,"40147"
			//					,"41352"
			//					,"42394"
			//					,"42398"
			//					,"5525"
			//					,"3112");

			if (S.isFull(test_id)) {

				long start_time = System.nanoTime();
				PdServiceHandleSanktionsChecks testx = new PdServiceHandleSanktionsChecks()._send_meldung(true)
						.initWithAdresses(new VEK<String>()._a(test_id));
				long end_time = System.nanoTime();
				erg_area.setText(""+testx.has_freigabe(test_id) + " (time: "+((end_time-start_time)/ 1000000)+ "ms)");
			}
		}
	}

	private class own_email_test_schicken extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Rec21 rec_report = new Rec21(_TAB.report)._fill_id(1226);

			E2_JasperHASH ojh = new test_jHash(rec_report);

			new PdServiceWriteInReportVerlaufBean().save(ojh, new MyE2_MessageVector());
		}

	}

	private void test_sha256_hash() throws myException{
		String hash = new Rec21_adresse(new Rec21(_TAB.adresse)._fill_id(id_adresse_field.get__actual_maskstring_in_view())).generate_sha256_hashwert();
		erg_area.setText(hash);
	}

	private class test_jHash extends E2_JasperHASH {
		public test_jHash(Rec21 rec_report) throws myException {
			super(rec_report.get_ufs_dbVal(REPORT.name_of_reportfile), new JasperFileDef_PDF());	// TODO Auto-generated constructor stub
		}

		@Override
		protected MailBlock create_MailBlock() throws myException {
			return null;
		}

		@Override
		public boolean get_bIsDesignedForMail() throws myException {
			return false;
		}

		@Override
		public void doActionAfterCreatedFile() throws myException {

		}

	}

	private void test_popup() throws myException {
		new SANKTION_Adresse_scan_popUp(new E2_NavigationList(), "test");
	}

	private class test_possible_pairs extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			try {
				String string = "2019-06-13";
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				Date date;
				date = format.parse(string);

				erg_area.setText(""+getKwPerDate(date));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public int getKwPerDate(Date ldate) {
		Calendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		gregorianCalendar.setMinimalDaysInFirstWeek(4);

		gregorianCalendar.setTime(ldate);

		return gregorianCalendar.get(Calendar.WEEK_OF_YEAR);
	}

	/*private E2_Grid generate_possible_pairs_grid(VEK<String> vInput) throws myException{
		E2_Grid grd = new E2_Grid()._setSize(100,100)._bo_ddd();

		E2_Grid title_grid = new E2_Grid()._s(vInput.size());
		for(String inp: vInput) {
			title_grid._a(inp, new RB_gld()._ins(5,1,5,1)._center_mid());
		}
		grd._a(title_grid, new RB_gld()._ins(1)._center_mid()._span(2));

		VEK<VEK<String>> vErg = all_possible_pairs(vInput);
		for(VEK<String> row : vErg) {
			grd._a(row.get(0), new RB_gld()._ins(1)._center_mid())._a(row.get(1), new RB_gld()._ins(1)._center_mid());
		}
		return grd;
	}

	private  VEK<VEK<String>> all_possible_pairs(VEK<String> vInput) throws myException{

		VEK<VEK<String>> vRueck = new VEK<VEK<String>>();

		VEK<String> vIntermediate = new VEK<String>();

		for(int i = 0; i<vInput.size(); i++) {
			for(int j = i; j<vInput.size(); j++) {
				if(vInput.get(i).equals(vInput.get(j))) {
					continue;
				}else {
					vIntermediate._a(new VEK<String>()._a(vInput.get(i)+";"+vInput.get(j)));
				}
			}
		}
		List<Object> tmp = Arrays.stream(vIntermediate.toArray()).distinct().collect(Collectors.toList());
		for(int i = 0; i<tmp.size(); i++) {
			vRueck._a(new VEK<String>()._a( ((String)tmp.get(i)).split(";")));
		}
		return vRueck;
	}*/


	private void generate_polyline() throws myException {
		GEO_Routing_Map routing_instance = new GEO_Routing_Map();

		Rec21_adresse start_record 	= new Rec21_adresse()._fill_id(5525);
		Rec21_adresse ziel_record 	= new Rec21_adresse()._fill_id(3112);

		routing_instance._add_adresse_coordinates(start_record.getMyBdActual(ADRESSE.latitude),start_record.getMyBdActual(ADRESSE.longitude));

		//ziel punkt
		routing_instance._add_adresse_coordinates(ziel_record.getMyBdActual(ADRESSE.latitude),ziel_record.getMyBdActual(ADRESSE.longitude));

		String osrm_server_adresse = ENUM_MANDANT_CONFIG.OSRM_LKW_URL.getUncheckedValue();

		String abfrage_osrm = routing_instance.get_abfrage_fuer_osrm();

		try {
			URL osrm_url = new URL(osrm_server_adresse+abfrage_osrm+"?geometries=polyline");

			URLConnection osrm_connection = osrm_url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(osrm_connection.getInputStream()));

			String inputLine;

			StringBuffer buff = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				buff.append(inputLine);
			}
			VEK<GEO_Road_Model> parser_response = null;

			if(buff !=null && buff.length()>0) {
				parser_response = new GEO_Routing_JSON_Parser().parse_osrm_response(buff);

				String rueckGeoJson = parser_response.get(0).get_polyline();

				erg_area.setText(rueckGeoJson);
			}

		}catch (MalformedURLException e) {
			bibMSG.MV()._addAlarm(e.getMessage());
		} catch (IOException e) {
			bibMSG.MV()._addAlarm(e.getMessage());
		} catch (JSONException e) {
			bibMSG.MV()._addAlarm(e.getMessage());
		}

	}

	/*private class FileNameChecker extends E2_Button{
		public FileNameChecker() {
			super();
			this._standard_text_button()._t("Dateiname Korrektur")._fsa(3)._b();
			this._aaa(()-> {
				try {
					open_file_name_checker();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

		}

		private void open_file_name_checker() throws myException, IOException {
			UP_DOWN_FILENAME_Container cont = new UP_DOWN_FILENAME_Container();
		}
	}*/

	private void jdbc_string_normalize_test (){

		String 	cJdbcString1 = "jdbc:oracle:thin:@//192.168.111.161:1521/XEPDB1";
		String  cJdbcString2 = "jdbc:oracle:thin:@192.168.111.105:1521:oraleb";

		Check<String> entscheider = (teststr)-> {return S.isFull(teststr);};

		VEK<String> vJdbc1 = new VEK<String>()._addValidated(entscheider, cJdbcString1.replaceAll("[^a-zA-Z\\d.]","@").split("@"));
		VEK<String> vJdbc2 = new VEK<String>()._addValidated(entscheider, cJdbcString2.replaceAll("[^a-zA-Z\\d.]","@").split("@"));



		erg_area._t(
				cJdbcString1.replaceAll("[^a-zA-Z\\d.]","@") + " -- IP: "+  vJdbc1.get(3) + " - SID: " + vJdbc1.get(5)
				+ "\n" + 
				cJdbcString2.replaceAll("[^a-zA-Z\\d.]","@") + " -- IP: "+  vJdbc2.get(3) + " - SID: " + vJdbc2.get(5)
				);

	}

	private class UUID_Generator extends E2_Grid{

		private RB_selField nb_of_uuid = null;

		private E2_Grid result_grid = null;

		public UUID_Generator() throws myException {
			super();
			this._setSize(120, 200);

			this.nb_of_uuid = new RB_selField()._populate(new String[] {"1","2","5","10"});
			this.result_grid = new E2_Grid()._setSize(300);

			E2_Button gen_knopf = new E2_Button()._t("UUID generieren")._standard_text_button()._image("reload.png")._fsa(2)._b();
			gen_knopf._aaa(()->uuid_generator());

			nb_of_uuid._setWidth(75);
			nb_of_uuid.setSelectedIndex(0);
			this
			._a(nb_of_uuid, new RB_gld()._ins(1)._left_mid())
			._a(gen_knopf, new RB_gld()._ins(1)._left_mid())._a("", new RB_gld()._ins(1)._left_mid()._span(2))
			._a(result_grid, new RB_gld()._ins(3)._left_mid()._span(2));

		}


		private E2_Grid uuid_generator() throws myException {
			int nb_of_Iteration = new BigDecimal(nb_of_uuid.get__actual_maskstring_in_view()).intValue();

			if(nb_of_Iteration>0) {

				result_grid._clear()._bo_dd()._setSize(400);

				for(int i = 0; i<nb_of_Iteration;i++) {
					this.result_grid._a(new RB_lab(UUID.randomUUID().toString())._fsa(1), new RB_gld()._ins(3)._left_mid());
				}
			}

			return this;
		}
	}

	private class ownGeocoordinatedisplayer extends E2_Grid{

		private RB_TextField idAdresseField = new RB_TextField(100);
		private E2_Button searchgeocoordinate = new E2_Button()._standard_text_button();

		private RB_lab researchResultLbl = new RB_lab();

		private VEK<String[]> searchResult = new VEK<>();
		private GEO_Locations vLox;

		public ownGeocoordinatedisplayer() {
			super();

			searchgeocoordinate._t("Suchen")._aaa(()->search());
			researchResultLbl._t(""+searchResult.size());

			E2_Button x_knopf = new E2_Button()._t("Display Coordinate");
			x_knopf._aaa(()->find_nearest_point_from_geographic_midpoint(this.vLox));

			this
			._a(idAdresseField, new RB_gld()._ins(2)._left_mid())
			._a(searchgeocoordinate, new RB_gld()._ins(2)._left_mid())
			._a(new RB_lab("Geocoordinate gefunden:")._bi(), new RB_gld()._ins(2)._left_mid())
			._a(researchResultLbl,new RB_gld()._ins(2)._left_mid())
			._a(x_knopf , new RB_gld()._ins(2)._left_mid())
			;


		}

		private void search() throws myException {
			searchResult._clear();

			String isoLaenderCode = "";

			String id_adresse = bibALL.convertID2UnformattedID(idAdresseField.get__actual_maskstring_in_view());

			if(S.isFull(id_adresse)) {
				Rec21 adresseRec = new Rec21(_TAB.adresse)._fill_id(id_adresse);

				MyLong idLand = new MyLong(adresseRec.getUfs(ADRESSE.id_land));
				if (idLand.isOK()) {
					isoLaenderCode = new Rec20(_TAB.land)._fill_id(idLand.get_oLong()).getUfs(LAND.iso_country_code);
				}

				String strasse = adresseRec.getUfs(ADRESSE.strasse,"");
				String hnr = adresseRec.getUfs(ADRESSE.hausnummer, "");
				String plz = adresseRec.getUfs(ADRESSE.plz,"");
				String ort = adresseRec.getUfs(ADRESSE.ort,"");

				GEO_ErrorMap errorMap = new GEO_ErrorMap("Maskenabfrage");

				GEO_Locations locs = new PdServiceGeoCodeNameStrassePlzOrtLandBean().getLocations(id_adresse, isoLaenderCode, strasse, hnr, plz, ort, errorMap);

				if (locs==null || locs.size() == 0) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(ENUM_GEO_Error.NO_COORDINATE.user_text()));
				} else {

					locs.setSearchDataForWeight(id_adresse, hnr, strasse, plz, ort, isoLaenderCode,60);

					locs.calculateWeights();

					this.vLox = locs;

					for(GEO_Location loc : locs) {
						this.searchResult._a(new String[] {""+loc.get_latitude().get_longValue(),""+loc.get_longitude().get_longValue()});
					}

					researchResultLbl._t(""+this.searchResult.size());
				}
			}
		}

		/**
		 * http://www.geomidpoint.com/calculation.html</br>
		 * [@ §A. Geographic midpoint]
		 * 
		 * @author sebastien
		 * @date 01.04.2019
		 *
		 * @return
		 * @throws myException
		 */
		private void find_nearest_point_from_geographic_midpoint(GEO_Locations vLocs) throws myException{

			GEO_Factory_URLForLocations factory = new GEO_Factory_URLForLocations();


			GEO_Location findLocation = new PdServiceFindNearestPtFromGeoMidPt().find_nearest_point_from_geographic_midpoint(vLocs);

			GEO_Coordinate4Map selected_point = new GEO_Coordinate4Map(
					""+findLocation.get_latitude().get_bdWert().doubleValue(), 
					""+findLocation.get_longitude().get_bdWert().doubleValue(), "ELECTED", ENUM_GEO_MARKER_COLOR.BLUE);
			factory.addGeoCoordiante(selected_point);

			ApplicationInstance.getActive().enqueueCommand(
					new BrowserOpenWindowCommand(factory.getURL4Points(),"Kartendarstellung",String.format("width=%d,height=%d",1200,1000)));

		}
	}
}