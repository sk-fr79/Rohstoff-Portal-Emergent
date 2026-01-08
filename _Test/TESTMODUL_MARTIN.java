package _Test;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.Service.PdServiceCheckAndAddRoutingKostenTyp;
import panter.gmbh.BasicInterfaces.Service.PdServiceCleanNormalizeAndSeparateStrings;
import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceGetKalenderWocheFromDateBean;
import panter.gmbh.BasicTools.StringParser.PdParser;
import panter.gmbh.BasicTools.StringParser.PdParserResult;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4Confirm;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.ExportSql.EXP_portSqlFromList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
//import panter.gmbh.Echo2.RB.COMP.RB_SelectCascading;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_dropDown;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.BETA.RB_searchAVVCode;
import panter.gmbh.Echo2.RB.COMP.SelV2.RB_SelectCascading;
import panter.gmbh.Echo2.RB.COMP.SelV2.RB_SelectCascading.Menue;
import panter.gmbh.Echo2.RB.COMP.SelV2.RB_SelectCascadingWithString;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SelectTaxWithGroups;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SelectUsers;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_SelectWaehrung;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TableUpdater;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2.HELP2_CALLSETTINGS;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2.HELP2_CONST;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2.HELP2_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.basic_tools.emailv2.EM2_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_DateWarner;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_GridDefinition;
import panter.gmbh.Echo2.components.E2_Grid_V2;
import panter.gmbh.Echo2.components.E2_PopupWithButtonCascading;
import panter.gmbh.Echo2.components.E2_PopupWithButtonCascading.IfActionAgentFactory;
import panter.gmbh.Echo2.components.E2_PopupWithButtonCascading.MenueEntry;
import panter.gmbh.Echo2.components.MyE2_SelectFieldV2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_EmailType;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.ENUM_MESSAGE_PROVIDER;
import panter.gmbh.basics4project.ENUM_REGISTRY;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.KOMMUNIKATION;
import panter.gmbh.basics4project.DB_ENUMS.KOMMUNIKATIONS_TYP;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_KOPF;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.Z_TEST;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.RecWatch;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Lib;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22Archivmedien;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22EmailSend;

/**
 * @author martin
 *
 */
public class TESTMODUL_MARTIN extends E2_BasicModuleContainer {


	
	private E2_Grid  grid = new E2_Grid()._setSize(300,1000);
	private E2_Grid  grid4Raster = new E2_Grid()._setSize(999);

	RecWatch testWatch = null;

	public TESTMODUL_MARTIN() throws myException {
		super();
		
//		String test = "1.2.3.--";
//		
//		test = test.replaceAll("\\.","");
//				
//		DEBUG._print("<"+test+">");
		this.grid._a(	new RB_dropDown()
						._setRendererGridForResult((com,id)-> {
							 Rec21 artikel = new Rec21(_TAB.artikel)._fill_id(id);	
							 com.getGridForResult()._clear()._setSize(50,200)
							 		._a(new RB_lab(artikel.getUfs(ARTIKEL.anr1)))
							 		._a(new RB_lab(artikel.getUfs(ARTIKEL.artbez1)))
							 		;
							 
						})
						._setRendererGridForDropDown((comp, rl)-> {
							comp.getGridForDropDown()._clear()._setSize(100,200,200);
							for (Rec21 artikel: rl) {
								
								 comp.getGridForDropDown()
							 		._a(new E2_Button()._t(artikel.getUfs(ARTIKEL.anr1))._aaa(e->{comp._renderGridForResult(artikel.getUfs(ARTIKEL.id_artikel)); comp._collapse();}))
							 		._a(new E2_Button()._t(artikel.getUfs(ARTIKEL.artbez1))._aaa(e->{comp._renderGridForResult(artikel.getUfs(ARTIKEL.id_artikel)); comp._collapse();;}))
							 		._a(new E2_Button()._t(artikel.getUfs(ARTIKEL.artbez2))._aaa(e->{comp._renderGridForResult(artikel.getUfs(ARTIKEL.id_artikel)); comp._collapse();;}))
							 		;
	
							}
							
							
						})._populateList(new RecList21(_TAB.artikel)._fillWithAll())
					);
				
				
				
			

		this.add(this.grid, E2_INSETS.I(2));
		
		this.grid._add_raw(new ownButtonTestZusatzNamenEnum())._add_raw(grid4Raster);
		
		this.grid._a(new RB_lab()._t("Test mit unterbrochenem Eventhandling"))._a(new ownTestBr());
		this.grid._a(new RB_lab()._t("Test Adresse speichern"))._a(new E2_Button()._t("Test Adressen-Rec20")._aaa(new ownAdressTestAction()));
		
		this.grid._a(new RB_lab()._t("AVV-Code-Test"))._a(new RB_searchAVVCode());
		this.grid._a(new RB_lab()._t("AVV-Code-Test-die 2."))._a(new ownButtonAVV());
		
		this.grid._a(new RB_lab()._t("Boolesche Gleichheit"))._a(new testGridVglBoolean());
		this.grid._a(new RB_lab()._t("Message: "))._a(new ownBtMessage());
		
		this.grid._a(new RB_lab()._t("Parser: "))._a(new ownBtParsTest());
		this.grid._a(new RB_lab()._t("Datenexport: "))._a(new ownSqlExport());
		
		this.grid._a(new RB_lab()._t("Rec-Kopie mit RAW: "))._a(new ownButtonTestCopyWithRecRawVal());
		this.grid._a(new RB_lab()._t("Alle Adresse alte Methode "))._a(new testButtonAllAdresseOld());
		this.grid._a(new RB_lab()._t("Alle Adresse Neue Methode "))._a(new testButtonAllAdresseNew());

		this.grid._a(new RB_lab()._t("Test Save-Prep"))._a(new bt_testRec21());
		
		this.grid._a(new RB_lab()._t("Test Read JasperVal"))._a(new OwnBtReadJasperVal());

		this.grid._a(new RB_lab()._t("Kalenderwoche"))._a(new ownBt());
		
		this.grid._a(new RB_lab()._t("Fenster schliessen abfangen"))._a(new ownBt2());
		
		this.grid._a(new RB_lab()._t("SelectField mit Mreak"))._a(new OwnSelectFieldWithBreakController());
		
		
		
		this.grid._a(new RB_lab()._t("Button gross"))._a(new ownButtonBig());
		this.grid._a(new RB_lab()._t("Pupup-Test"))._a(new doublePop());
		this.grid._a(new RB_lab()._t("Matching-Test"))._a(new ownTestSeparation());
		this.grid._a(new RB_lab()._t("Hilfe 2.0"))._a(new ownHelpTest());
		
		this.grid._a(new RB_lab()._t("Generic Array"))._a(new ownTestDynArray());

		this.grid._a(new RB_lab()._t("Waehrungen"))._a(new TestGrid());
		
		this.grid._a(new RB_lab()._t("Copy-Test"))._a(new ChangeBt());

		this.grid._a(new RB_lab()._t("Kreditversicherungstest"))._a(new ButtonTesteFuhrenWegenKreditversicherung());


		this.grid._a(new RB_lab()._t("Date-Parse"))._a(new E2_Button()._t("Teste Dates-Parser")._aaa(()-> {
			
			bibMSG.MV()._addInfo("Parsing: 1.1.2011"+MyDate.parse("1.1.2011"));
			bibMSG.MV()._addInfo("Parsing: 14.1.11"+MyDate.parse("14.1.11"));
			bibMSG.MV()._addInfo("Parsing: 31.1.2011"+MyDate.parse("31.1.2011"));
			bibMSG.MV()._addInfo("Parsing: 31.01.2011"+MyDate.parse("31.01.2011"));
			bibMSG.MV()._addInfo("Parsing: 31.01.11"+MyDate.parse("31.1.2011"));

			
		}));
		
		this.grid._a(new RB_lab()._t("Lock-Test Adresse 4346"))	._a(new E2_Button()._t("Create Watcher")._setShapeBigHighLightText()._aaa(()-> {testWatch = new RecWatch(_TAB.adresse, 4346l);}));
		this.grid._a(new RB_lab()._t(""))						._a(new E2_Button()._t("Locktable")._setShapeBigHighLightText()._aaa(()-> {
			try {
				if (testWatch.lock()) {
					bibMSG.MV()._addInfo("Lock erfolgreich !");
					bibDB.Commit();
				} else {
					bibMSG.MV()._addInfo("Fehler beim Lock!");
					bibDB.Rollback();
				}
			} catch (myException e) {
				e.printStackTrace();
			} finally {
				bibDB.Rollback();
			}
			;}));
		
		
		MyE2_ContainerEx  container = new MyE2_ContainerEx();
		container.setWidth(new Extent(300));
		container.setHeight(new Extent(300));
		container.setBorder(new Border(2, Color.BLACK, Border.STYLE_SOLID));
		E2_Grid gInnen = new E2_Grid()._w100()._s(1);
		container.add(gInnen);
		
		
		TestForSelectCascading testCascade = (TestForSelectCascading)new TestForSelectCascading();
		E2_Button bt=(E2_Button)new E2_Button()._t("Test für Menü")._aaa(()->{
			Rec21 r = new Rec21(_TAB.tax)._fill_id(4004);
			testCascade._renderStatus(r);
		});
		gInnen._a(testCascade);
		
		this.grid._a(bt)._a(container);
		
		this.grid._a(new E2_Button()._t("TRIGGERTTEST")._aaa(()-> {
			PAIR<VEK<String>,VEK<String>> ret =new TableUpdater().tableUpdate("JT_ADRESSE");
			VEK<String> all = new VEK<String>()._a(ret.getVal1())._a(ret.getVal2());
			
			bibMSG.MV()._addInfo(S.Concatenate(all, "-", "-"));
			
		}));
		
		
//		MyE2_ContainerEx  scroller = new MyE2_ContainerEx();
//		scroller.setWidth(new Extent(300));
//		scroller.setHeight(new Extent(100));
//		
//		scroller.add(new OwnTelefonGrid());
//		this.grid._a(new RB_lab()._t("Test fuer maskengrid"))._a(scroller);
		
		
//		E2_Button btSmall = new E2_Button()._t("1234455677")._width(20);
//		E2_Grid gSmall = new E2_Grid()._bo_red()._setSize(10)._width(10)._a(btSmall);
//		MyE2_ContainerEx cSmall = new MyE2_ContainerEx();
//		cSmall.setWidth(new Extent(20));
//		cSmall.setHeight(new Extent(20));
//		cSmall.setScrollBarPolicy(ContainerEx.NEVER);
//		cSmall.add(gSmall);
//		this.grid._a(cSmall);
		
		RB_HL_SelectUsers popM = new RB_HL_SelectUsers();
		E2_Button     but = (E2_Button)new E2_Button()._t("Inaktiven User Testen")._aaa(()->{
								popM._setActualValue("1.016");
								//popM._setActualValue("1167");
							});
		
		this.grid._a(but)._a(popM);
		
		RB_HL_SelectTaxWithGroups taxSelect =  new RB_HL_SelectTaxWithGroups() ;
		
		E2_Button btt = (E2_Button) new E2_Button()._setShapeStandardTextButton()._t("Schalten")._aaa(()-> {
			taxSelect._setHidden(new VEK<String>()._a("4.015")._a("4.005"));
			RB_SelectCascadingWithString sle = taxSelect.getSelectCascadingWithString();
			if (taxSelect!=null) {
				Menue m = taxSelect.getSelectCascadingWithString().getFirstRenderedMenue();
				if (m != null) {
					taxSelect.getSelectCascadingWithString()._renderMenue(m);
				}
			}
			//taxSelect.getSelectCascadingWithString()._renderMenue(taxSelect.getSelectCascadingWithString().getFirstRenderedMenue());
		});
		
		this.grid._a(btt)._a(taxSelect);
		
		this.grid._a(new ButtonTestGridDefinition());
		

		this.grid._a(new RB_TextArea()._width(400)._height(100)._col_back_alarm()._bord_black());
		this.grid._a(new RB_TextArea()._col_back_alarm()._bord_black());

//		E2_Button btTestJackson = new E2_Button()._t("Json-Read-Test");
//		btTestJackson._aaa(()-> {
//			File jsonFile = new File(File.separator+bibALL.get_WEBROOTPATH()+File.separator+ENUM_REGISTRY.SUBDIR_TO_JSON.getStringValue()+File.separator+"settingsAfterTransportTypeDef.json");
//			
//			try {
//				B2_JSON_MaskSettingWithTransportArtHelpers helper = new B2_JSON_MaskSettingWithTransportArtHelpers(jsonFile);
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			
//			
//		});
//		
//		
//		this.grid._a(btTestJackson);

		this.grid._a(new TestEmailModule());
		
		
		
		E2_Grid grid4DateDiffTest = new E2_Grid();
		RB_TextField tfVon = new RB_TextField()._w(100);
		RB_TextField tfBis = new RB_TextField()._w(100);
		RB_TextField tfTage = new RB_TextField()._w(100);
		SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
		E2_Button    buttonCalc = new E2_Button()._t("rechne")._aaa(()->{
			try {
				Date date1 = sf.parse(tfVon.getText());
				Date date2 = sf.parse(tfBis.getText());
				tfTage._t(myDateHelper.getDifferenceBitweenDates(date1,date2,new VEK<DayOfWeek>()	._a(DayOfWeek.MONDAY)
																									._a(DayOfWeek.TUESDAY)
																									._a(DayOfWeek.WEDNESDAY)
																									._a(DayOfWeek.THURSDAY)
																									._a(DayOfWeek.FRIDAY)).toString());
			} catch (ParseException e) {
				tfTage._t("Fehler!");
			}
		});
		grid4DateDiffTest._s(4)._a(tfVon)._a(tfBis)._a(buttonCalc)._a(tfTage);
		
		this.grid._a(grid4DateDiffTest);
	}
	
	
	
	private class ChangeBt extends E2_Button {

		public ChangeBt() {
			super();
			
			this._t("Ändere Fuhren 10 mal TA")._setShapeBigHighLightText();
			
			this._aaa(()-> {
			
				Rec21 fuhre = new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(304556);
				
				for (int i=0;i<200;i++) {
					
					fuhre._nv(VPOS_TPA_FUHRE.bemerkung, ""+i, bibMSG.MV());
					
					fuhre._SAVE(true, bibMSG.MV());
					fuhre._rebuildRecord();
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					DEBUG._print(fuhre.getUfs(VPOS_TPA_FUHRE.bemerkung));
				}
				fuhre._nv(VPOS_TPA_FUHRE.bemerkung,fuhre.getUfs(VPOS_TPA_FUHRE.bemerkung)+"...done", bibMSG.MV());

				fuhre._SAVE(true, bibMSG.MV());
				
			});
			
		}
		
	}
	
	
	
	
	private class TestGrid extends E2_Grid {

		public TestGrid() throws myException {
			super();
			
			RB_SelectWaehrung sel = new RB_SelectWaehrung();
			
			E2_Button bt = (E2_Button) new E2_Button()._t("5525 zudefineren")._aaa(()->{sel._setWaehrungenForAdresse(5525l);});
			
			this._a(sel)._a(bt);
		}
		
		
	}
	
	
	
	
	private class ownBt extends E2_Button {

		public ownBt() {
			super();
			this._t("Datums-Spielereien");
			
			this._aaa(new ownAction());
		}
		
		private class ownAction extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				int iKalenderWoche = new PdServiceGetKalenderWocheFromDateBean().getKalenderWoche(new GregorianCalendar(2017, 11, 31).getTime(), null);
				
				Pair<Date> dp = new PdServiceGetKalenderWocheFromDateBean().getStartAndEndFromKW(new GregorianCalendar(2018, 04, 16).getTime(), null);
				Pair<Date> dp2 = new PdServiceGetKalenderWocheFromDateBean().getStartAndEndFromKW(33, 2018, null);
				
				DEBUG._print("Kalenderwoche von: 31.12.2017 "+ iKalenderWoche);
				DEBUG._print("Kalenderwoche von-bis : 16.05.2018 "+ dp.getVal1()+" ---> "+dp.getVal2());
				
				DEBUG._print("Kalenderwoche 33/2018 "+ dp2.getVal1()+" ---> "+dp2.getVal2());
				
			}
		}
	}
	
	
	
	private class ownBtMessage extends E2_Button {
		
		public ownBtMessage() {
		
			this._t("Meldung loslassen ")._fo_bold();
		
			this._aaa(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					 bibMSG.add_MESSAGE(ENUM_MESSAGE_PROVIDER.MESSAGE_QUALIFYING_AH7_STEUERSATZ.generateMessages(null, null,"Weitere Info ---","Test"));
				}
			});
		}		
	}
	
	
	private class ownSqlExport extends E2_Button {
		
		public ownSqlExport() {
		
			this._t("SQL-Exporter ")._fo_bold();
		
			this._aaa(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					new EXP_portSqlFromList(_TAB.tax);
				}
			});
		}		
	}
	
	
	
	
	
	private class ownBtParsTest extends E2_Button {
		public ownBtParsTest() {
			super();
			this._t("Parsing ")._fo_bold();
			this._aaa(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					PdParserResult res=  new PdParser().parse("123<A:B>", "A");
					
					if (res != null) {
						DEBUG._print(res.key);
						DEBUG._print(res.value);
						DEBUG._print(res.parseTextWithoutBlock);
					}
				}
			});
			
		}
	
	}
	
	
	private class ownButtonAVV extends E2_Button {

		/**
		 * 
		 */
		public ownButtonAVV() {
			super();
			this._t("AVV-Ohne Anzeige")._fo_bold();
			
			this._aaa(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					RB_searchAVVCode sc= new RB_searchAVVCode();
					
					sc.getButtonSelect().doActionPassiv();
				}
			});
			
		}
		
	}
	
	
	public class testButtonAllAdresseOld extends E2_Button {

		public testButtonAllAdresseOld() {
			super();
			this._t("Alle Adresse - alte Methode")._style(E2_Button.StyleTextButtonCentered())._aaa(new ownAction());
		}
		
		private class ownAction extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				String[][] ids = bibDB.EinzelAbfrageInArray("SELECT ID_ADRESSE FROM JT_ADRESSE ORDER BY 1");
				
				VEK<String> vIds = new VEK<String>()._addVektor(()->{ Vector<String> ret = new Vector<String>(); for (int i=0;i<ids.length;i++) {ret.add(ids[i][0]); } return ret; });

				Date start = new Date();
				for (String id: vIds) {
					Rec20 r = new Rec20(_TAB.adresse)._fill_id(new Long(id));
					RecList20 rl = r.get_down_reclist20(VKOPF_RG.id_adresse, "", "");
					if (r.getUfs(ADRESSE.id_adresse).equals("5525")) {
						continue;
					}
						
					RecList20 rlf = r.get_down_reclist20(VPOS_TPA_FUHRE.id_adresse_start, "", "");
					DEBUG._print("-------------------------------------------------------------------------");
					DEBUG._print("Normal: "+r.getUfs(ADRESSE.id_adresse)+" --> Rechnungen: "+rl.size()+"-> Fuhren: "+rlf.size());
				}
				DEBUG._print("von "+start.toString()+" --- bis :"+new Date().toString());
			}
		}
	}
	
	public class testButtonAllAdresseNew extends E2_Button {

		public testButtonAllAdresseNew() {
			super();
			this._t("Alle Adresse - Prepared Methode")._style(E2_Button.StyleTextButtonCentered())._aaa(new ownAction());
		}
		
		private class ownAction extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				String[][] ids = bibDB.EinzelAbfrageInArray("SELECT ID_ADRESSE FROM JT_ADRESSE ORDER BY 1");
				
				VEK<String> vIds = new VEK<String>()._addVektor(()->{ Vector<String> ret = new Vector<String>(); for (int i=0;i<ids.length;i++) {ret.add(ids[i][0]); } return ret; });

				Date start = new Date();
				for (String id: vIds) {
					Rec21 r = new Rec21(_TAB.adresse)._fill_id(new Long(id));
					RecList21 rl = r.get_down_reclist21(VKOPF_RG.id_adresse, "", "");
					if (r.getUfs(ADRESSE.id_adresse).equals("5525")) {
						continue;
					}
					RecList21 rlf = r.get_down_reclist21(VPOS_TPA_FUHRE.id_adresse_start, "", "");

					DEBUG._print("-------------------------------------------------------------------------");
					DEBUG._print("Prepared: "+r.getUfs(ADRESSE.id_adresse)+" --> Rechnungen: "+rl.size()+"-> Fuhren: "+rlf.size());
				}
				DEBUG._print("von "+start.toString()+" --- bis :"+new Date().toString());
			}
		}
	}
	
	
	private class ownButtonTestCopyWithRecRawVal extends E2_Button {

		/**
		 * 
		 */
		public ownButtonTestCopyWithRecRawVal() {
			super();
			this._t("Fuhre Testkopie")._fo_bold();
			
			this._aaa(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					Rec20 rQuelle = new Rec20(_TAB.vpos_tpa_fuhre)._fill_id("304427");
					Rec20 rZiel = new Rec20(_TAB.vpos_tpa_fuhre);
					
					for (IF_Field f: _TAB.vpos_tpa_fuhre.get_fields()) {
						rQuelle._setNewVal(f, rQuelle.getRawVal(f), bibMSG.MV());
					}
					
					//DEBUG._print(rZiel.get_StatementBuilder(true).get_CompleteInsertString(_TAB.vpos_tpa_fuhre.n()));
					rQuelle._SAVE(true, bibMSG.MV());
					
				}
			});
			
		}
		
	}
	
	
	
	

	private class ownButtonTestZusatzNamenEnum extends E2_Button {

		public ownButtonTestZusatzNamenEnum() {
			super();
			this._t("Teste Enum");
			this.add_oActionAgent(new ownAction());
		}
		
		private class ownAction extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				ENUM_MANDANT_ZUSATZ_FELDNAMEN.write_all_new();
			}
		}
	}
	
	
	
	private class ownTestBr extends E2_Button  {

		
		public ownTestBr() throws myException {
			super();
			
			this.setBreak4PopupController(new E2_Break4PopupController());
			
			this._t("Test break in ActionLoop");
			
			this.getBreak4PopupController()._registerBreak(new ownBreak1())._registerBreak(new ownBreak2());
			
			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Test1"));
				}
			});
		}
	}
	
	
	private class ownBreak1 extends E2_Break4Popup {

		private RB_cb    check1 = new RB_cb()._un_check();
		
		/**
		 * 
		 */
		public ownBreak1() {
			super();
			this.setTitle(new MyE2_String("Brecher 1"));
			
			this.getOwnSaveButton()._aaa(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					check1.setSelected(true);
				}
			});
			
		}

		@Override
		protected boolean check4break(MyE2_MessageVector mv) throws myException {
			return !this.check1.isSelected();
		}

		@Override
		public E2_BasicModuleContainer generatePopUpContainer() throws myException {
			E2_BasicModuleContainer container = new ownPopupContainer();
			E2_Grid g = new E2_Grid()._s(1)._a(this.check1);
			g._a(this.getOwnSaveButton());
			g._a(this.getOwnCloseButton());
			
			container.add(g, E2_INSETS.I(5));
			return container;
		}
		
		private class ownPopupContainer extends E2_BasicModuleContainer {
		}

	}
	
	private class ownBreak2 extends E2_Break4Popup {

		private RB_cb    check1 = new RB_cb()._un_check();
		
		public ownBreak2() {
			super();
			this.setTitle(new MyE2_String("Brecher 2"));
			
			this.getOwnSaveButton()._aaa(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					check1.setSelected(true);
				}
			});

		}
	
		@Override
		protected boolean check4break(MyE2_MessageVector mv) throws myException {
			//mv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Fehler beim check4reak")));
			return !this.check1.isSelected();
			
		}

		@Override
		public E2_BasicModuleContainer generatePopUpContainer() throws myException {
			E2_BasicModuleContainer container = new ownPopupContainer();
			E2_Grid g = new E2_Grid()._s(1)._a(this.check1);
			g._a(this.getOwnSaveButton());
			g._a(this.getOwnCloseButton());

			container.add(g, E2_INSETS.I(5));
			return container;
		}
		
		private class ownPopupContainer extends E2_BasicModuleContainer {
		}

	}


	private class ownAdressTestAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			Rec20  recAdr = new Rec20(_TAB.adresse)._fill_id(3002);
			
			DEBUG.System_println("Wert des neuen Feldes:" + recAdr.getOverheadFields().get("AH7_QUELLE_SICHER").get_FieldValueUnformated());
			
			recAdr._nv(ADRESSE.name3, recAdr.get_fs_dbVal(ADRESSE.name3)+"A", bibMSG.MV());
			
			recAdr._SAVE(true, bibMSG.MV());
		}
		
	}
	
	
	
	private class testGridVglBoolean extends E2_Grid {

		/**
		 * @throws myException 
		 * 
		 */
		public testGridVglBoolean() throws myException {
			super();
			this._a(new bt());
			
		}

		private class bt extends E2_Button {
			public bt() {
				super();
				this._t("???")._aaa(new XX_ActionAgent() {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						boolean b1=false;
						boolean b2=false;
						
						boolean c1=true;
						boolean c2=true;
						
						boolean d1=true;
						boolean d2=false;
						
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(S.ms("false=false:"+((b1==b2)?"Ja":"Nein"))));
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(S.ms("true=true:"+((c1==c2)?"Ja":"Nein"))));
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(S.ms("true=false:"+((d1==d2)?"Ja":"Nein"))));
						
						
						
					}
				});
			}
			
		}
	}
	
	
	
	public class bt_testRec21 extends E2_Button  {

		
		public bt_testRec21() {
			super();
			this._t("Teste Rec21")._standard_text_button();
			this._aaa(new ownAction());
		}

		
		private class ownAction extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				int iAnzahl = 10;
				
				
				Date startPrep = new Date();
				
				for (int i=0; i<iAnzahl; i++) {
					Rec21 r = new Rec21(_TAB.z_test)._setPrepared();
					
					r	._setNewVal(Z_TEST.test_bigdecimal, 	new BigDecimal(100.332), bibMSG.MV())
						._setNewVal(Z_TEST.test_long, 			new Long(100), bibMSG.MV())
						._setNewVal(Z_TEST.test_text, 	        "ABC", bibMSG.MV())
						._setNewVal(Z_TEST.test_boolean, 	    "Y", bibMSG.MV())
						._setNewVal(Z_TEST.test_date, 	        new Date(), bibMSG.MV())
						;
					
					Rec21 recNew = r._SAVE(true, bibMSG.MV()).getRec20LastRead();
					recNew._setNewVal(Z_TEST.test_date, new Date(), bibMSG.MV());
					recNew._SAVE(true, bibMSG.MV());

					
		//				recNew._newVal(Z_TEST.test_long, new Long(((Long)recNew.getRawResultValueObjectCorrected(Z_TEST.test_long))+1), bibMSG.MV());
		//				recNew._newVal(Z_TEST.test_bigdecimal, ((BigDecimal)recNew.getRawResultValueObjectCorrected(Z_TEST.test_bigdecimal)).add(new BigDecimal(1)), bibMSG.MV());
		//				try {
		//					Thread.sleep(5000);
		//				} catch (InterruptedException e) {
		//					// TODO Auto-generated catch block
		//					e.printStackTrace();
		//				}
	//				recNew._SAVE(true, bibMSG.MV());
					
//					bibMSG.MV()._addInfo("Dazugefügt ID:" +recNew.getRec20LastRead().get_key_value());
					
//					RecList21 rl = new RecList21(_TAB.z_test)._fill("", "");
//					for (Rec21 re: rl) {
//						re._newVal(Z_TEST.test_long, new Long(((Long)recNew.getRawResultValueObjectCorrected(Z_TEST.test_long))+1), bibMSG.MV());
//						re._newVal(Z_TEST.test_bigdecimal, ((BigDecimal)recNew.getRawResultValueObjectCorrected(Z_TEST.test_bigdecimal)).add(new BigDecimal(1)), bibMSG.MV());
//						re._newVal(Z_TEST.test_date, recNew.getRawResultValueObjectCorrected(Z_TEST.test_date), bibMSG.MV());
//		
//					}
					
				}
				Date endPrep = new Date();
				
				DEBUG._print(""+iAnzahl+" x prepared: "+startPrep.toString()+" - "+endPrep.toString());
				

				Date startNotPrep = new Date();
				
				for (int i=0; i<iAnzahl; i++) {
					Rec21 r = new Rec21(_TAB.z_test);
					
					
					
					r	._setNewVal(Z_TEST.test_bigdecimal, 	new BigDecimal(100.332), bibMSG.MV())
						._setNewVal(Z_TEST.test_long, 			new Long(100), bibMSG.MV())
						._setNewVal(Z_TEST.test_text, 	        "ABC", bibMSG.MV())
						._setNewVal(Z_TEST.test_boolean, 	    "Y", bibMSG.MV())
						._setNewVal(Z_TEST.test_date, 	        new Date(), bibMSG.MV())
						;
					
					Rec21 recNew = r._SAVE(true, bibMSG.MV()).getRec20LastRead();
					recNew._setNewVal(Z_TEST.test_date, new Date(), bibMSG.MV());
					recNew._SAVE(true, bibMSG.MV());
					
		//				recNew._newVal(Z_TEST.test_long, new Long(((Long)recNew.getRawResultValueObjectCorrected(Z_TEST.test_long))+1), bibMSG.MV());
		//				recNew._newVal(Z_TEST.test_bigdecimal, ((BigDecimal)recNew.getRawResultValueObjectCorrected(Z_TEST.test_bigdecimal)).add(new BigDecimal(1)), bibMSG.MV());
		//				try {
		//					Thread.sleep(5000);
		//				} catch (InterruptedException e) {
		//					// TODO Auto-generated catch block
		//					e.printStackTrace();
		//				}
					
//					bibMSG.MV()._addInfo("Dazugefügt ID:" +recNew.getRec20LastRead().get_key_value());
					
//					RecList21 rl = new RecList21(_TAB.z_test)._fill("", "");
//					for (Rec21 re: rl) {
//						re._newVal(Z_TEST.test_long, new Long(((Long)recNew.getRawResultValueObjectCorrected(Z_TEST.test_long))+1), bibMSG.MV());
//						re._newVal(Z_TEST.test_bigdecimal, ((BigDecimal)recNew.getRawResultValueObjectCorrected(Z_TEST.test_bigdecimal)).add(new BigDecimal(1)), bibMSG.MV());
//						re._newVal(Z_TEST.test_date, recNew.getRawResultValueObjectCorrected(Z_TEST.test_date), bibMSG.MV());
//		
//					}
					
				}
				
				ApplicationContext cont = new ClassPathXmlApplicationContext("panter/gmbh/BasicInterfaces/spring-context.xml");
				PdServiceCheckAndAddRoutingKostenTyp myService = (PdServiceCheckAndAddRoutingKostenTyp)cont.getBean("pdServiceCheckAndAddRoutingKostenTyp", PdServiceCheckAndAddRoutingKostenTyp.class);
				
				if (myService.isKostenTypRoutingSingularAndPresent()) {
					DEBUG._print("Test");
				}
				
				Date endNotPrep = new Date();
				DEBUG._print(""+iAnzahl+" x unprepared: "+startNotPrep.toString()+" - "+endNotPrep.toString());
	
				bibMSG.MV()._addInfo(""+iAnzahl+" x prepared: "+startPrep.toString()+" - "+endPrep.toString());
				bibMSG.MV()._addInfo(""+iAnzahl+" x normal: "+startNotPrep.toString()+" - "+endNotPrep.toString());
			}
			
		}
		
		
	}
	
	
	
	private class OwnBtReadJasperVal extends E2_Button {

		public OwnBtReadJasperVal() {
			super();
			this._s_BorderText()._t("Jasperwert holen")._aaa(()->	{		
																		bibMSG.MV()._addInfo(jasper.Service.read("AH7_STEUERTABELLE_PATH","DE"));
																		bibMSG.MV()._addInfo(jasper.Service.getReportBasePath());
																		bibMSG.MV()._addInfo(jasper.Service.getReportPathALL());
																		bibMSG.MV()._addInfo(jasper.Service.getReportPathMandant());
																	});
		}
		
	}
	
	
	
	private class ownBt2 extends E2_Button {

		private ownWindow 		popper = null;
		/**
		 * 
		 */
		public ownBt2() throws myException{
			super();
			popper = new ownWindow(); 
			
			//this._t("Fenster mit Schliessverbot")._standard_text_button()._aaa(()->{ popper=new ownPopContainer(); });
			
			this._t("Fenster mit Schliessverbot")._standard_text_button()._aaa(()->{ 	popper.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Test"));});
			this.setBreak4PopupController(new E2_Break4PopupController()._registerBreak(new Break4Confirm())._registerBreak(new Break4Confirm()));
			
		}


		private class ownWindow extends E2_BasicModuleContainer {
			public ownWindow() throws myException {
				super();
				ownBtClose btClose = new ownBtClose();
				this.setButtonForClosingWindow(btClose);
				this.add(btClose, E2_INSETS.I(20));
			}
			
		}
		
		
		private class ownBtClose extends E2_Button {
			
			public ownBtClose() throws myException {
				super();
				this._t("ABC-Closes")._standard_text_button();
				this._aaaInFront(()->{
//					popper.get_oWindowPane().forceClose();
					}, true);
				this._aaa(()->{
					//popper.get_oWindowPane().setDefaultCloseOperation(WindowPane.DISPOSE_ON_CLOSE);
					popper.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					});
				
				this.setBreak4PopupController(new E2_Break4PopupController()._registerBreak(new Break4Confirm()));
			}
			
		}

	}
	
	
	
	private class OwnSelectFieldWithBreakController extends MyE2_SelectFieldV2 {
		HashMap<String, String> hm = new HashMap<String,String>(){{put("Aaa","111"); put("BBB","222"); put("CCC","333");}};

		public OwnSelectFieldWithBreakController() throws myException {
			super();
			this.set_ListenInhalt(hm, false);
			this.setBreak4PopupController(new OwnBreakController());
		}
		
		private class OwnBreakController extends E2_Break4PopupController {

			public OwnBreakController() {
				super();
				Break4Confirm breaker = new Break4Confirm();
				breaker.getOwnCloseButton()._aaa(()->{OwnSelectFieldWithBreakController.this.set_ActiveValue(OwnSelectFieldWithBreakController.this.getLastSetValue());});
				this._registerBreak(breaker);
				
				
			}
			
		}
		
	}
	
	
	
	
	private class ownButtonBig extends Button {

		/**
		 * @param text
		 */
		public ownButtonBig() {
			super("TEST");
			
			this.setFont(new E2_FontBold());
			
			this.setHeight(new Extent(50));
			this.setWidth(new Extent(200));
			this.setBackground(Color.GREEN);
			//this.setAlignment(new Alignment(Alignment.LEFT, Alignment.DEFAULT));
			this.setProperty(Button.PROPERTY_TEXT_POSITION,new Alignment(Alignment.RIGHT, Alignment.CENTER));
			//this.setTextPosition(newValue);
			
			
		}
		
	}
	
	
	
//	private class doublePop extends E2_PopupWithButtonCascading {
//
//		/**
//		 * @throws myException 
//		 * 
//		 */
//		public doublePop() throws myException {
//			super(S.ms("test-popup"));
//			
//			this._addComponent(new E2_Button()._t("Testpopup-Button1"));
//			this._addComponent(new E2_Button()._t("Testpopup-Button2"));
//			this._addComponent(new doublePop1());
//		}
//
//		
//		private class doublePop1 extends E2_PopupWithButtonCascading {
//
//			/**
//			 * @throws myException 
//			 * 
//			 */
//			public doublePop1() throws myException {
//				super(S.ms("Testpopup-innen"));
//				this.getPopUp().get_grid_innen().setColumnWidth(0, null);
//
////				E2_PopUp motherPop = doublePop.this.getPopUp();
//				
////				this.getPopUp().getMotherPopupsToCollapseInClick().add(motherPop);
//				
//				//this.getPopUp()._set_container_width(new Extent(300));
//				
//				
////				this._addComponent((E2_Button)new E2_Button()._t("Testpopup-Button3")._f(new E2_FontPlain())._aaa(()->{motherPop.setExpanded(false);}));
////				this._addComponent((E2_Button)new E2_Button()._t("Testpopup-Button4")._f(new E2_FontPlain())._aaa(()->{motherPop.setExpanded(false);}));
//				this._addComponent((E2_Button)new E2_Button()._t("Testpopup-Button3")._f(new E2_FontPlain()));
//				this._addComponent((E2_Button)new E2_Button()._t("Testpopup-Button4")._f(new E2_FontPlain()));
//				
//				this._addComponent(new doublePop2());
//				
//				//this.getPopUp().get_grid_innen()._setSize(400);
//				
//			}
//			
//			
//			private class doublePop2 extends E2_PopupWithButtonCascading {
//
//				/**
//				 * @throws myException 
//				 * 
//				 */
//				public doublePop2() throws myException {
//					super(S.ms("Testpopup-innen-Stufe2"));
//					this.getPopUp().get_grid_innen().setColumnWidth(0, null);
//
////					E2_PopUp motherPop = doublePop.this.getPopUp();
////					E2_PopUp motherPop2 = doublePop1.this.getPopUp();
//					
////					this.getPopUp().getMotherPopupsToCollapseInClick().add(motherPop);
////					this.getPopUp().getMotherPopupsToCollapseInClick().add(motherPop2);
//
//					
////					this._addComponent((E2_Button)new E2_Button()._t("Testpopup-Button5")._f(new E2_FontPlain())._aaa(()->{motherPop.setExpanded(false);motherPop2.setExpanded(false);}));
////					this._addComponent((E2_Button)new E2_Button()._t("Testpopup-Button6")._f(new E2_FontPlain())._aaa(()->{motherPop.setExpanded(false);motherPop2.setExpanded(false);}));
//					this._addComponent((E2_Button)new E2_Button()._t("Testpopup-Button5")._f(new E2_FontPlain()));
//					this._addComponent((E2_Button)new E2_Button()._t("Testpopup-Button6")._f(new E2_FontPlain()));
//					
//				}
//				
//			}
//
//			
//		}
//
//		
//	}
	
	
	private class doublePop extends E2_PopupWithButtonCascading {

		/**
		 * @throws myException 
		 * 
		 */
		public doublePop() throws myException {
			super(S.ms("test-popup"), new ownActionCreator(), null);

			this._init()._populate(new VEK<MenueEntry>()	._a(new MenueEntry("text1", "101"))
													._a(new MenueEntry("text2", "102"))
													._a(new MenueEntry("text3", "103"))
													._a(new MenueEntry("s1-text", 
															new VEK<MenueEntry>()	._a(new MenueEntry("s1-text1", "1001"))
																					._a(new MenueEntry("s1-text2", "1002"))
																					._a(new MenueEntry("s1-text3", "1003"))
																					._a(new MenueEntry("s2-text", 
																							new VEK<MenueEntry>()	._a(new MenueEntry("s2-text1", "10001"))
																													._a(new MenueEntry("s2-text2", "10002"))
																													._a(new MenueEntry("s2-text3", "10003")))
															))));
		}

		
		
		
		
	}

	private class ownActionCreator implements IfActionAgentFactory {
		
		@Override
		public VEK<XX_ActionAgent> getActionAgents(MenueEntry entry) throws myException {
			VEK<XX_ActionAgent> v = new VEK<XX_ActionAgent>()._a(new ownActionAgent(entry));
			
			return v ;
		}
		
		private class ownActionAgent extends XX_ActionAgent {
			private MenueEntry entry = null;
			
			/**
			 * @param entry
			 */
			public ownActionAgent(MenueEntry entry) {
				super();
				this.entry = entry;
			}


			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.MV()._addWarn("Klick on button: "+this.entry.getDbVal());
			}
		}
		
	}
	
	
	
	private class ownTestSeparation extends E2_Button {

		public ownTestSeparation() {
			super();
			
			this._t("Starte Vergleich")._s_BorderText();
			
			this._aaa(new ownAction());
		}
		
		private class ownAction extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {

				DEBUG.System_print(new PdServiceCleanNormalizeAndSeparateStrings()._addStdReplacers().getCompleteCycle(new VEK<String>()._a("So schön ist ")._a(" ist es bei 33 Grad")));

				DEBUG._print("------------");
				
				DEBUG.System_print(new PdServiceCleanNormalizeAndSeparateStrings()._addStdReplacers().getCompleteCycleLongestN(
						new VEK<String>()._a("So schön ist ")._a(" ist es bei 33 Grad"),4,3));

				
				String sql = "SELECT * FROM JT_ADRESSE WHERE ID_ADRESSE=-1";
				Rec21  r21 = new Rec21(_TAB.adresse)._fill_sql(sql);
				Rec20  r20 = new Rec20(_TAB.adresse)._fill_sql(sql);
				
				if (r21.is_newRecordSet()) {
					bibMSG.MV()._addAlarm("Rec21 ist neu");
					
				}
				if (r20.is_newRecordSet()) {
					bibMSG.MV()._addAlarm("Rec20 ist neu");
				}

				String test = ENUM_REGISTRY.SANKTION_MAX_VEKTOR_LENGTH.getStringValue();
				bibMSG.MV()._addAlarm("<"+S.NN(test)+">");
				
				BigDecimal bdTest = ENUM_REGISTRY.SANKTION_MAX_VEKTOR_LENGTH.getBdValue(new BigDecimal(-1));
				bibMSG.MV()._addAlarm("<"+bdTest.longValue()+">");
			
				
				
				
				//				
//				comp._addLeftSide("OSAMa")._addLeftSide("bin")._addLeftSide("Laden");
//				comp._addRightSide("ObAMa")._addRightSide("bin")._addRightSide("Laden");
//				
//				VEK<String> v = new VEK<String>()._a("ObAMa")._a("bin")._a("Laden");
//				
//				
//				LongStream.range(0, PdServicePermutater.factorial(v.size())).forEachOrdered(i -> {
//			        System.out.println(i + ": " + PdServicePermutater.permutation(i, v));
//			    });
//				
////				PdServicePermutater.permutation(3, v);
//				
//				
//				if (comp.isMatching()) {
//					bibMSG.MV()._addWarn("Treffer");
//				}
				
				
			}
		}
		
		
	}
	
	
	
	private class ownHelpTest extends E2_Button {

		public ownHelpTest() {
			super();
			this._t("HILFE")._s_BorderText();
			HashMap<HELP2_CALLSETTINGS, Object> hm = new HashMap<HELP2_CALLSETTINGS, Object>();
			hm.put(HELP2_CALLSETTINGS.VORGABE_MODUL, E2_MODULNAME_ENUM.MODUL.AH7_PROFIL_LIST.get_callKey());
			
			this._aaa(()->{ new HELP2_LIST_BasicModuleContainer(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_FIBU_LIST).CREATE_AND_SHOW_POPUPWINDOW(
									new Extent(HELP2_CONST.HELP2_NUM_CONST.LISTPOPUP_WIDTH.getValue()),
									new Extent(HELP2_CONST.HELP2_NUM_CONST.LISTPOPUP_HEIGHT.getValue()), S.ms("Hilfe 2.0"));});
			
		}
	}

	
	private class ownTestDynArray extends E2_Button {

		public ownTestDynArray() {
			super();
			this._t("Generic Array")._s_BorderText();
			
			VEK<String> vs = new VEK<String>()._a(null, null,"1");
			
			String[] as = vs.getArray(); 
			
			this._aaa(()->{
				if (as!=null) {
					for (String s: as) {
						DEBUG._print(S.NN(s,"<leeres member>"));
					}
				} else {
					DEBUG._print("Ist leer");
				}
			});
			
		}
	}

	

	
	
	
	
	private class OwnTelefonGrid extends E2_Grid_V2 {

		public OwnTelefonGrid() throws myException {
			super();
			
			this._width(500);
			
			this.rb_set_db_value_manual("5525");
		}

		@Override
		public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
			Rec21 adresse = (Rec21)dataObject.get_RecORD();
			
			this.rb_set_db_value_manual(adresse.getUfs(ADRESSE.id_adresse));
		}

		@Override
		public void rb_set_db_value_manual(String valueFormated) throws myException {
			
			MyLong l = new MyLong(valueFormated);
			
			if (l.isOK()) {
				this._clear()._setSize(100,200,200)
				._a(new RB_lab("Typ")._b(),new RB_gld()._col_back_dd())
				._a(new RB_lab("Vorwahl")._b(),new RB_gld()._col_back_dd())
				._a(new RB_lab("Nummer")._b(),new RB_gld()._col_back_dd());
				
				RecList21  rlTel =  new Rec21(_TAB.adresse)._fill_id(l.getLong()).get_down_reclist21(KOMMUNIKATION.id_adresse);

				for (Rec21 r: rlTel) {
					String typ = "";
					if (r.get_up_Rec21(KOMMUNIKATIONS_TYP.id_kommunikations_typ).is_ExistingRecord()) {
						typ = r.get_up_Rec21(KOMMUNIKATIONS_TYP.id_kommunikations_typ).getUfs(KOMMUNIKATIONS_TYP.bezeichnung);
					}
					
					this._a(new RB_lab()._t(typ), new RB_gld()._ins(2, 0, 2, 1))
						._a(new RB_lab()._t(r.getUfs(KOMMUNIKATION.wert_vorwahl, "<vorwahl>")), new RB_gld()._ins(2, 0, 2, 1))
						._a(new RB_lab()._t(r.getUfs(KOMMUNIKATION.wert_rufnummer, "<numm>")), new RB_gld()._ins(2, 0, 2, 1))
						;
				}
				
			} else {
				this._clear()._setSize(100,200,200)
					._a(new RB_lab("Typ")._b(),new RB_gld()._col_back_dd())
					._a(new RB_lab("Vorwahl")._b(),new RB_gld()._col_back_dd())
					._a(new RB_lab("Nummer")._b(),new RB_gld()._col_back_dd());
				
			}
			
		}
	}

	
	
	
	
	
	
	
	private class TestForSelectCascading extends RB_SelectCascading<Rec21> {

		public TestForSelectCascading() throws myException {
			super();
		
			RecList21 rlSteuerKetegorie = new RecList21(_TAB.tax_group)._fillWithAll();
			
			VEK<Rec21> v_Groups = rlSteuerKetegorie.get_sorted_vector(new Comparator<Rec21>() {
				@Override
				public int compare(Rec21 o1, Rec21 o2) {
					try {
						return o1.getUfs(TAX_GROUP.kurztext).compareTo(o2.getUfs(TAX_GROUP.kurztext));
					} catch (myException e) {
						e.printStackTrace();
						return 0;
					}
				}
			});

			VEK<Menue> vSubMenues = new VEK<>();
			for (Rec21 taxGroup: v_Groups) {
				Menue m = new Menue(taxGroup.getUfs(TAX_GROUP.kurztext));
				for (Rec21 r: taxGroup.get_down_reclist21(TAX.id_tax_group)) {
					m._addItem( r);
				}
				vSubMenues._a(m);
			}
			
			SEL sel = new SEL(_TAB.tax).FROM(_TAB.tax).WHERE(new VglNull(TAX.id_tax_group));
			
			RecList21 rlTaxesWithoutGroup = new RecList21(_TAB.tax)._fill(sel.s());
			//jetzt zuerst die submenues
			Menue baseMenue = new Menue("Startauswahl");
			for (Menue subMenue: vSubMenues) {
				baseMenue._addItem(subMenue);
			}

			//jetzt die einzelnen
			baseMenue._addItem(new Rec21(_TAB.tax));
			for (Rec21 rec: rlTaxesWithoutGroup) {
				baseMenue._addItem( rec);
			}
	
			_renderMenue(baseMenue);
			
			
		}


		@Override
		public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		}

		@Override
		public void rb_set_db_value_manual(String valueFormated) throws myException {
		}

		@Override
		public void setShapeOfActionButton(MenueButton button, Rec21 target) throws myException {
			
			String text = S.NN(((Rec21)target).getUfs(TAX.dropdown_text),"-");
			
			button.setText(text);
			button.setFont(new E2_FontPlain());
			button.setStyle(RB_SelectCascading.StyleForActionButton());
			button.setLayoutData(new RB_gld()._ins(1,1,1,2));
			
			//button._height(40);
		}

		@Override
		public void setShapeOfSubMenueButton(MenueButton button, Menue subMenu) throws myException {
			String text =subMenu.getTitle();
			
			button.setText(text);
			button.setStyle(RB_SelectCascading.StyleForSubMenueButton());
			button.setFont(new E2_FontBoldItalic());
			button.setLayoutData(new RB_gld()._ins(1,1,1,2));
		}

		@Override
		public void setShapeOfReturnButton(MenueButton button, Menue backMenue) throws myException {
			
			String text = S.NN(button.getOwnMenue().getTitle(),"???");
			
			button.setText(text);
			button.setStyle(RB_SelectCascading.StyleForReturnMenueButton());
			button.setFont(new E2_FontBoldItalic());
			button.setLayoutData(new RB_gld()._ins(1,2,1,5));
		}

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.RB.COMP.RB_SelectCascading#isSame(java.lang.Object, java.lang.Object)
		 */
		@Override
		public boolean isSame(Rec21 t1, Rec21 t2) {
			try {
				return t1.getId()==t2.getId();
			} catch (myException e) {
				return false;
			}
		}

		@Override
		public void setHighLightStatusOfActualValueButton(RB_SelectCascading<Rec21>.MenueButton bt) {
			bt._setBorder(Color.BLACK);
		}

		@Override
		public void resetHighLightStatusOfActualValueButton(RB_SelectCascading<Rec21>.MenueButton bt) {
			bt._setBorder(new E2_ColorDDDark());
		}

		@Override
		public void addActionsAgentsAndValidatorsToTargetButton(RB_SelectCascading<Rec21>.MenueButton button,	Rec21 target) {
			button._aaa(()->{bibMSG.MV()._addInfo("Benutzer: "+target.getUfs(USER.name));
				});
		}

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.RB.COMP.SelV2.RB_SelectCascading#executeClickOnMenueButton(java.lang.Object, panter.gmbh.Echo2.RB.COMP.SelV2.RB_SelectCascading.MenueButton)
		 */
		@Override
		public MyE2_MessageVector executeClickOnMenueButton(Rec21 rec,	RB_SelectCascading<Rec21>.MenueButton menueButton) throws myException {
			if (rec.is_newRecordSet()) {
				return	bibMSG.getNewMV()._addInfo("Jetzt wird alles leergemacht ...");
			} 
			return bibMSG.getNewMV()._addInfo(rec.getUfs(TAX.dropdown_text));
		}
		
	}
	

	private class ButtonTesteFuhrenWegenKreditversicherung extends E2_Button {

		public ButtonTesteFuhrenWegenKreditversicherung() {
			super();
			
			this._t("KreditversicherungsFakturierungsFristen")._setShapeBigHighLightText();
			
			this._aaa(new OwnAction());
		}

		private class OwnAction extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			   //RecList21 rlFuhren = new RecList21(_TAB.vpos_tpa_fuhre)._fillWithAll();
				
				SqlStringExtended sqlIds = new SqlStringExtended("select id_vpos_tpa_fuhre from jt_vpos_tpa_fuhre where datum_abladen is not null");
				
				String[][] s = bibDB.EinzelAbfrageInArray(sqlIds);
				
				
				for (String[] f1: s) {
					Rec21 f = new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(Long.parseLong(f1[0]));
					if (f.getRawVal(VPOS_TPA_FUHRE.datum_abladen)!=null) {
						SqlStringExtended query = KV_Lib.getSQLExtKreditversicherungMitFakturierungsFrist("MIN("+KREDITVERS_KOPF.fakturierungsfrist.tnfn()+")", 
												f.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_ziel), (Date) f.getRawVal(VPOS_TPA_FUHRE.datum_abladen));
						
						String numbers =bibDB.EinzelAbfrageInArray(query)[0][0].trim();
						
						if (!numbers.equals("0") && !numbers.equals("")) {
							DEBUG._print("ID: "+f.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_ziel)+"  Tage:"+bibDB.EinzelAbfrageInArray(query)[0][0]);
						}
						
					} else {
						DEBUG._print("ID: kein leistungsdatum");
					}
				}
				
			}
			
		}
	}	

	
	
	private class ButtonTestGridDefinition extends E2_Button {

		public ButtonTestGridDefinition() {
			super();
			
			this._t("Teste neues GridDefinition")._setShapeBigHighLightText();
			
			this._aaa(new ActionTestGridDefinition());
			
			
			
			
			
		}
		
	}
	
	
	private class ActionTestGridDefinition extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
			Rec21 rec = new Rec21(_TAB.abzugsgrund);
			
			Rec21 recCopy = new Rec21(rec);
			
			E2_GridDefinition  gd = new E2_GridDefinition();
			gd._add(0,5,new RB_lab("Test auf zeile 0, spalte 5"));
			gd._add(3,5,new RB_lab("Test auf zeile 3, spalte 5"));
			
			gd._add(0,0,new RB_lab("Test auf zeile 0, spalte 0")); 
			gd._add(2,2,new RB_lab("Test auf zeile 2, spalte 2"),40,300,new RB_gld()._span(2)._span_r(2)); 

			gd._setBaseColumnWidth(new Integer(100))._setBaseRowHeight(new Integer(30))._setPaceholderLayout(new RB_gld()._col_back_green());
			
			
			E2_BasicModuleContainer container = new E2_BasicModuleContainer();
			
			container.add(gd.applyTo(null)._bord_black(), E2_INSETS.I(10,10,10,10));
			container.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Test"));
			
		}
	}


	
	private class TestGridRec22 extends E2_Grid {

		private Rec22  adresse = null;

		public TestGridRec22() {
			super();
			this._setSize(100,100)._a(new ownButtonRead())._a(new ownButtonSave());
		}
		
		private class ownButtonRead extends E2_Button {
			public ownButtonRead() {
				super();
				
				this._t("Lese adresse 5525")._aaa(()->{
					adresse = new Rec22(_TAB.adresse)._fill_id(5525l);
					String name3 = S.NN(adresse.getStringResultValueNative(ADRESSE.name3))+"1";
					adresse._setNewVal(ADRESSE.erstkontakt, new Date(), bibMSG.MV());
					adresse._setNewVal(ADRESSE.name3, name3, bibMSG.MV());
				})._setShapeStandardTextButton();
			}
		}
		
		private class ownButtonSave extends E2_Button {
			public ownButtonSave() {
				super();
				
				this._t("Speichere adresse 5525")._aaa(()->{
					if (adresse==null) {
						bibMSG.MV()._addAlarm(S.ms("Adresse ist leer!"));
					} else {
						adresse = adresse._SAVE(true, bibMSG.MV());
						if (adresse!=null) {
							bibMSG.MV()._addInfo(S.ms("Gespeichert !"));
							bibMSG.MV()._addInfo(adresse.getStringResultValueNative("NAME3"));
						}
					}
				})._setShapeStandardTextButton();
			}
		}
		
		
		
	}

	
	
	
	private class TestEmailModule extends E2_Grid {

		private Rec22  adresse = null;
		private OwnTextField textField = new OwnTextField();

		public TestEmailModule() {
			super();
			this._setSize(100,100)._a(textField)._a(new OwnButtonOpenMails());
		}
		
		private class OwnButtonOpenMails extends E2_Button {
			public OwnButtonOpenMails() {
				super();
				
				this._t("Öffne eMailer")._aaa(()->{
					try {
						Long idAdresse = Long.parseLong(textField.getText());
						
						Rec22 adressTest = new Rec22(_TAB.adresse);
						for (IF_Field f: ADRESSE.values()) {
//							DEBUG.println(f.fieldName()+": "+adressTest.getValue(f));
							adressTest._setNewVal(ADRESSE.ausweis_ablauf_datum, new Date());
							DEBUG.println(f.fieldName()+" (Last) : "+adressTest.getValueLast(f));
							DEBUG.println(f.fieldName()+" (DB)   : "+adressTest.getValueDb(f));
							DEBUG.println("---------------");
						}
//						
						
						//hier wird eine email erzeugt und gespeichert
						Rec22EmailSend r2 = new Rec22EmailSend()._setAllowArchivmedienDifferentTableAndId(true);
						r2._setFromAdress("martinpanter@googlemail.com")
								._setSendTyp(SEND_TYPE.SINGLE)
								._setMailText("Dies ist eine TestMail")
								._setSubject("Test Test Test")
								._setTABBelongsTo(_TAB.adresse)
								._setTABIdBelongsTo(idAdresse)
//								._setEmailType(ENUM_EmailType.MAILVERSAND_WAAGE_STORNO)
//								._setMailEmailVerificationKey("123333")
								._addAttachment(new Rec22Archivmedien()._fill_id(163469l))
								._addTarget("manfred@panter-datentechnik.de")
								._addTarget("martin@panter-datentechnik.de")
								._saveAll(true);
						
						r2._sendEmail(null);
						
						EM2_LIST_BasicModuleContainer list  = new EM2_LIST_BasicModuleContainer(_TAB.adresse,idAdresse);
						list._setAllowSendButton(true)._setAllowAddTarget(false)._init();
						
						list.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Emailer für Adresse: "+idAdresse.toString()));

					} catch (NumberFormatException e) {
						e.printStackTrace();
						bibMSG.MV()._add(e);
					} catch (Exception e) {
						e.printStackTrace();
						bibMSG.MV()._add(e);
					}
					
					
				})._setShapeStandardTextButton();
			}
		}
		

		
		private class OwnTextField extends RB_TextField {
			public OwnTextField() {
				super(200);
				_t("3005");
			}
		}
		
		
		
	}
	
	
	
	
	
}
