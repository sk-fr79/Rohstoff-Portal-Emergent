/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN
 * @author martin
 * @date 29.11.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.F;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO_MASK_ComponentMAP;

/**
 * @author martin
 * @date 29.11.2018
 * generische klasse, die feststellt, ob das sonderverfahren zur fuellung der Steuer/Steuertexte bei Fuhren ohne Abrechnung zum Tragen kommt
 */
public class __FU_FUO_handlingTaxdefOhneAbrechnung {

	/**
	 * 
	 * @author martin
	 * @date 29.11.2018
	 *
	 * @param compButtonOnMask
	 * @return s                PAIR<null,Fehlerlemdung> on error (mit MessageVektor fuer die fehlerbeschreibung), 
	 *                          PAIR<true,leerem Messagevektor>, wenn eine ausnahme (fuhre ohne abrechnung) gefunden wurde und die Steuer gesetzt wurde,
	 *                          PAIR<false,leerem MessageVektor>, wenn dieses vorgehen unterdrueckt wurde oder wenn es eine abrechnungsfuhre ist 
	 * @throws myException
	 */
	public PAIR<Boolean, MyE2_MessageVector> setTaxAndTaxMessageForFuhrenOhneAbrechnung(E2_ComponentMAP p_map) throws myException {
		
		//zuerst pruefen, ob das verfahren hier ueberhaupt zum Tragen kommt:
		if (ENUM_MANDANT_DECISION.HANDELSDEF_FREMDWAREN_FUHREN_MANUELL.is_NO_FromSession()) {
			return new PAIR<Boolean, MyE2_MessageVector>()
					._setVal1(false)
					._setVal2(F.MV());   //in der rufenden einheit wie bisher
			
		} else {
		
			//den steuertext holen
			String dummySteuerVermerk = ENUM_MANDANT_CONFIG.FUHRE_OHNE_ABRECH_TEXT.getUncheckedValue();
			String dummySteuer = "0";
			
			if (p_map==null) {
				return new PAIR<Boolean, MyE2_MessageVector>()
						._setVal1(null)
						._setVal2(F.MV()._addAlarmUT("Systemerror: Mask null is not allowed! <339da442-5dd5-44fe-be8d-16e9f560ebf0>")
										);
	
			} else {
				
				try {
					if (p_map instanceof FUO_MASK_ComponentMAP) {
						
						FUO_MASK_ComponentMAP mapFO = 	(FUO_MASK_ComponentMAP)p_map;
						FU_MASK_ComponentMAP mapF = 	(FU_MASK_ComponentMAP)mapFO.get_oFU_FUO_DaughterComponent().EXT().get_oComponentMAP();
						
						//jetzt nachsehen, ob es Fremdware ist
						boolean fuhreOhneAbrechnung = 		mapF.get_bActualDBValue(VPOS_TPA_FUHRE.ohne_abrechnung.fn());
						boolean fuhreOrtOhneAbrechnung = 	mapFO.get_bActualDBValue(VPOS_TPA_FUHRE_ORT.ohne_abrechnung.fn());
						
						if (fuhreOhneAbrechnung && !fuhreOrtOhneAbrechnung) {
							return new PAIR<Boolean, MyE2_MessageVector>()
									._setVal1(null)
									._setVal2(F.MV()._addAlarmUT("Wenn die Hauptfuhre ohne Abrechnung ist, dann muss der Zusatzort auch ohne Abrechnung sein !")
													);
	
							
						} else if (!fuhreOhneAbrechnung && fuhreOrtOhneAbrechnung) {
							return new PAIR<Boolean, MyE2_MessageVector>()
									._setVal1(null)
									._setVal2(F.MV()._addAlarmUT("Wenn die Hauptfuhre Abrechnungsfuhre ist, dann muss der Zusatzort Abrechnungsposition sein !")
													);
						} else {
							if (fuhreOhneAbrechnung && fuhreOrtOhneAbrechnung) {
								
								mapFO.get__DBComp(VPOS_TPA_FUHRE_ORT.steuersatz.fn()).set_cActualMaskValue(dummySteuer);
								mapFO.get__DBComp(VPOS_TPA_FUHRE_ORT.eu_steuer_vermerk.fn()).set_cActualMaskValue(dummySteuerVermerk);
								mapFO.get__DBComp(VPOS_TPA_FUHRE_ORT.id_handelsdef.fn()).set_cActualMaskValue("");
								mapFO.get__DBComp(VPOS_TPA_FUHRE_ORT.id_tax.fn()).set_cActualMaskValue("");
	
								return new PAIR<Boolean, MyE2_MessageVector>()._setVal1(Boolean.TRUE)._setVal2(F.MV()._addInfo("Proforma-Steuer und -Vermerk für Fremdwarenfuhren gesetzt !"));
								
							} else {
								//alles ok, weiter mit dem standardverfahren
								return new PAIR<Boolean, MyE2_MessageVector>()._setVal1(Boolean.FALSE)._setVal2(F.MV());
							}
						}
						
						
					} else if (p_map instanceof FU_MASK_ComponentMAP) {
						
						FU_MASK_ComponentMAP mapF = 	(FU_MASK_ComponentMAP)p_map;
						
						//jetzt nachsehen, ob es Fremdware ist
						boolean fuhreOhneAbrechnung = 		mapF.get_bActualDBValue(VPOS_TPA_FUHRE.ohne_abrechnung.fn());
						
						if (fuhreOhneAbrechnung) {
							mapF.get__DBComp(VPOS_TPA_FUHRE.steuersatz_ek.fn()).set_cActualMaskValue(dummySteuer);
							mapF.get__DBComp(VPOS_TPA_FUHRE.eu_steuer_vermerk_ek.fn()).set_cActualMaskValue(dummySteuerVermerk);
							mapF.get__DBComp(VPOS_TPA_FUHRE.steuersatz_vk.fn()).set_cActualMaskValue(dummySteuer);
							mapF.get__DBComp(VPOS_TPA_FUHRE.eu_steuer_vermerk_vk.fn()).set_cActualMaskValue(dummySteuerVermerk);
							mapF.get__DBComp(VPOS_TPA_FUHRE.id_handelsdef.fn()).set_cActualMaskValue("");
							mapF.get__DBComp(VPOS_TPA_FUHRE.id_tax_ek.fn()).set_cActualMaskValue("");
							mapF.get__DBComp(VPOS_TPA_FUHRE.id_tax_vk.fn()).set_cActualMaskValue("");
							
							return new PAIR<Boolean, MyE2_MessageVector>()._setVal1(Boolean.TRUE)._setVal2(F.MV()._addInfo("Proforma-Steuer und -Vermerk für Fremdwarenfuhren gesetzt !"));
						} else {
							//alles ok, weiter mit dem standardverfahren
							return new PAIR<Boolean, MyE2_MessageVector>()._setVal1(Boolean.FALSE)._setVal2(F.MV());
						}
						
					} else {
						return new PAIR<Boolean, MyE2_MessageVector>()
								._setVal1(null)
								._setVal2(F.MV()._addAlarm("Die Maske scheint weder Fuhre- noch Fuhrenort zu sein !")
												);
					}
				} catch (myException e) {
					return new PAIR<Boolean, MyE2_MessageVector>()
							._setVal1(null)
							._setVal2(F.MV()._addAlarmUT("Unknown Error <068724c2-ef3f-4ffb-bab7-09cefc7a9e62>")
											._add(e.get_ErrorMessage())
											);
				} catch (Exception e) {
					e.printStackTrace();
					return new PAIR<Boolean, MyE2_MessageVector>()
							._setVal1(null)
							._setVal2(F.MV()._addAlarmUT("Unknown Error <860df4af-ab07-4dc4-878b-2ffe6c8c6779>")
											._addAlarmUT(e.getLocalizedMessage())
									);
				}
			}
		}
	}
	
}
 