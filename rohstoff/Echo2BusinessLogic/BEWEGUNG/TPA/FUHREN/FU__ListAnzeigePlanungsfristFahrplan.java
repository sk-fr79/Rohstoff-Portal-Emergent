/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN
 * @author martin
 * @date 01.03.2021
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.text.SimpleDateFormat;
import java.util.Date;

import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_DateWarner;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.PdDateTools;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposTpaFuhre;

/**
 * @author martin
 * @date 01.03.2021
 *
 */
public class FU__ListAnzeigePlanungsfristFahrplan extends E2_UniversalListComponent {

	
	private static 	String key = "FU__ListAnzeigePlanungsfristFahrplan <e030e5ee-bd85-4ab0-a62e-bbb8e73ef521>";
	
	private Long    gelbGrenze = 3l;
	private Long    rotGrenze = 2l;
	
	
	/**
	 * @author martin
	 * @date 01.03.2021
	 *
	 */
	public FU__ListAnzeigePlanungsfristFahrplan() {
		super();
		this.EXT().set_bLineWrapListHeader(true);
		this.EXT().set_oLayout_ListTitelElement(new RB_gld()._center_top()._col_back_d());
		this._w100();
		
		this.gelbGrenze = Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAHRT_PLANUNG_GELB.getCheckedValue());
		this.rotGrenze = Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAHRT_PLANUNG_ROT.getCheckedValue());

	}


	@Override
	public String key() throws myException {
		return FU__ListAnzeigePlanungsfristFahrplan.key;
	}

	
	@Override
	public String userText() throws myException {
		return "Fahrplan\nFälligkeit";
	}

	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	
	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		
	
		try {
			
			E2_DateWarner dw = new E2_DateWarner()._setFont(new E2_FontBold(1))._setGelbeGrenze(this.gelbGrenze)._setRoteGrenze(this.rotGrenze);

			this._clear()._setSize(40);
			Rec21_VposTpaFuhre fuhre =	new Rec21_VposTpaFuhre(((E2_ComponentMAP_V2) this.EXT().get_oComponentMAP()).getRec21());
			
			if (fuhre.is_no_db_val(VPOS_TPA_FUHRE.ist_storniert) && fuhre.is_no_db_val(VPOS_TPA_FUHRE.deleted)) {
				
				if (fuhre.isFahrplanFuhre()) {
					
					Date datumVormerk = 	fuhre.getDateDbValue(VPOS_TPA_FUHRE.dat_vorgemerkt_fp);
					Date datumFahrPlan = 	fuhre.getDateDbValue(VPOS_TPA_FUHRE.dat_fahrplan_fp);
					
					if (fuhre.isGeplantInFahrplan()) {     //isGeplantInFahrplan heist: hat einen LKW und ein Fahrplan-Datum
						//dann ist fuhre rot, wenn der tag des fahrplans rum ist, aber kein gewicht vorhanden !
						//dann wird es rot, wenn der Tag rum ist und die fahrt nicht durchgefuehrt
						
						dw._calc(datumFahrPlan,true);
						
						if (fuhre.isFahrplanFuhreDurchgefuehrt()) {
							dw._setForceGreen(true)._setColorOK(new E2_ColorBase())._setOkClosed(true);
							dw._renderWith("P",  (warn)-> {
								String toolTip = "Fahrplan-Fuhre: geplant für "+new SimpleDateFormat("dd.MM.yyyy").format(datumFahrPlan)+
										" und durchgeführt !";
								return toolTip;
							});
							
						} else {
							//fuer einen vergangenen Tag geplant, nicht durchgefuehrt heisst: 
							if (dw.getDateDiff()<0) {
								dw._setForceGreen(true)._setColorOK(new E2_ColorBase())._renderWith("P",  (warn)-> {
									String toolTip = "Fahrplan-Fuhre: geplant am "+new SimpleDateFormat("dd.MM.yyyy").format(datumFahrPlan)+
											", wurde aber noch nicht gewogen / hat noch keine Menge!";
									return toolTip;
								});
							} else {
								//fuer heut oder zukunft im fahrplan: gelb
								dw._setForceGreen(true)._setColorOK(new E2_ColorBase())._renderWith("P",  (warn)-> {
									String toolTip = "Fahrplan-Fuhre: geplant am "+new SimpleDateFormat("dd.MM.yyyy").format(datumFahrPlan)+"!";
									return toolTip;
								});
							}
						}
						
					} else {
					
						if (datumVormerk!=null) {
							
							if (fuhre.isFahrplanFuhreDurchgefuehrt()) {
								dw._setColorOK(new E2_ColorBase())._setOkClosed(true);
								dw._calc(datumVormerk,true)._setFontColor(new E2_ColorDDDark())._renderWith("(V)", (warn)-> {
									String infoZeile = "";
									infoZeile = "Vormerkung für Fahrplan zum ";
									infoZeile += "<"+new SimpleDateFormat("dd.MM.yyyy").format(datumVormerk)+">, nicht im Fahrplan, aber abgeschlossen";
									return infoZeile;
								});
							} else {
								dw._setColorOK(new E2_ColorBase())._calc(datumVormerk,true)._renderWith("V", (warn)-> {
									String infoZeile = "";
									infoZeile = "Vormerkung zum ";
									infoZeile += "<"+new SimpleDateFormat("dd.MM.yyyy").format(datumVormerk)+">";
									switch (dw.getStatusAmpelAktuell()) {
									case GREEN:
										infoZeile+=": Offen, aber noch nicht dringend!";
										break;
									case DARKRED:
										infoZeile+=": ÜBERSCHRITTEN !! Dringend"; 
										break;
									case RED:
										infoZeile+=": Ist DRINGEND zu bearbeiten! "; 
										break;
									case YELLOW:
										infoZeile+=": Ist demnächst zu bearbeiten! "; 
										break;
									default:
										break;
									}
									
									return infoZeile;
								});
							}
						} else {
							//kann eigentlich nicht sein (normalerweise kann  keine Fahrplanfuhre ohne FP-vormerkdaten geschrieben werden
							dw._setForceRed(true);
							Date date = PdDateTools.addDayToDate(-100 ,new Date());
							dw._calc(date,true)._renderWith("???", null);
						}
					}
				}
			}
			
			if (dw.isRendered()) {
				this._a(dw, new RB_gld()._center_mid());
			} else {
				this._a("-", new RB_gld()._center_mid());
			}


			
		} catch (Exception e) {
			e.printStackTrace();
			this._clear()._a("@ERR", new RB_gld()._col_back_alarm());
		}
		
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new FU__ListAnzeigePlanungsfristFahrplan();
	}

	
}
