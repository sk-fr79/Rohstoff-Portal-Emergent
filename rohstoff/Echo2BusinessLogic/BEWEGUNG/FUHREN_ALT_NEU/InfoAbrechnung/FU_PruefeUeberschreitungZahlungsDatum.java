/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU
 * @author martin
 * @date 27.11.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.InfoAbrechnung;

import java.util.Date;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.E2_ErrorButton;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.ENUM_DEF_ABRECHNUNGSTYP;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_KOPF;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Lib;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposTpaFuhre;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposTpaFuhreOrt;

/**
 * @author martin
 * @date 27.11.2019
 *
 */
public class FU_PruefeUeberschreitungZahlungsDatum extends E2_UniversalListComponent {

	
	/**
	 * @author martin
	 * @date 27.11.2019
	 *
	 */
	public FU_PruefeUeberschreitungZahlungsDatum() {
		this.EXT().set_bLineWrapListHeader(true);	
		this.EXT().set_oColExtent(new Extent(BewegungsGrid.spaltenVoll));
	}

	
	

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new FU_PruefeUeberschreitungZahlungsDatum();
	}




	@Override
	public String key() throws myException {
		return "6341b2c7-1506-4f03-8aee-3168a86e753c";
	}

	@Override
	public String userText() throws myException {
		return "Warnhinweis Abrechnung";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	
	
	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear();
		
		try {
			E2_ComponentMAP_V2 map = (E2_ComponentMAP_V2) this.EXT().get_oComponentMAP();
			Rec21 rec = map.getRec21();
			
			if (rec.get_tab()==_TAB.vpos_tpa_fuhre) {
				Rec21_VposTpaFuhre recF = new Rec21_VposTpaFuhre(rec);

				//jetzt feststellen, wie viele zeilen (fuhren=1 + (max(anzahl fuhrenorte links und rechts))
				//angezeigt werden muessen
				VEK<Rec21_VposTpaFuhreOrt> fuhrenOrte = recF.getFuhrenOrte();
				
				int countLeft  = 1;
				int countRight = 1;    //jeweils eine zeile von der hauptfuhre
				
				for (Rec21_VposTpaFuhreOrt fo: fuhrenOrte) {
					if (fo.isEK()) {
						countLeft++;
					} else {
						countRight++;
					}
				}
				
				//fuer jeden potentiellen beleg ein kaestchen (jeweils ein marker fuer die fuhre und einer fuer die position
				int countZeilen = countLeft;
				if (countRight>countZeilen) {
					countZeilen = countRight;
				}

				
				//jetzt ein array aus componenten aufbauen, die fuhren-fuhrenort-situation abbilden
				Component[][] compArr = new Component[countZeilen][2];
				
 
				for (int i=0;i<2;i++) {      //0 = links,  1=rechts 
					E2_Grid  grid = null;
					ENUM_DEF_ABRECHNUNGSTYP typ = i==0?recF.getAbrechTypLadeseite():recF.getAbrechTypAbladeseite();
					
					switch (typ) {
					case LAGER:
						grid = 	new BewegungsGrid()._setShapeHome();
						break;
						
					case LEER_ZU_NULL:
						grid = 	new BewegungsGrid()._setShapeLeer();
						break;
						
					case VERBINDLICHKEIT:
						grid = 	new BewegungsGrid()._setShapeVerbindlichkeit();
						break;
					
					case FORDERUNG:
						
						
						int verlaengerteFaktFrist = 0;
						try {
							//jetzt nachsehen, ob eine verlaengerte fakturierungsfrist gilt, wenn ja, hinweis darauf
							// jetzt nachsehen, ob zum leistungszeitpunkt eine kreditversicherung existiert
							SqlStringExtended query = KV_Lib.getSQLExtKreditversicherungMitFakturierungsFrist(	"MIN(" + KREDITVERS_KOPF.fakturierungsfrist.tnfn() + ")",
																													recF.getLongDbValue(i==0?VPOS_TPA_FUHRE.id_adresse_start:VPOS_TPA_FUHRE.id_adresse_ziel), 
																													(Date) recF.getRawVal(i==0?VPOS_TPA_FUHRE.datum_abladen:VPOS_TPA_FUHRE.datum_abladen));
							String[][] kvTage = bibDB.EinzelAbfrageInArray(query);
							verlaengerteFaktFrist = Integer.parseInt(kvTage[0][0]);
						} catch (Exception e) {
							verlaengerteFaktFrist = 0;
						}

						
						if (verlaengerteFaktFrist>0) {
							//dann auf die spalte der verlaengerten Fakt-Frist hinweisen
// 20191209: fakturierungsfrist erstmal weg							grid = new BewegungsGrid()._setShapeVerlaengerteFaktFrist();
							
							if (i==0) {
								grid = new BewegungsGrid()._setShapeForderung(recF.getLeistungsDatumLadeSeite(), recF.getZahlungsBedingungLadeSeite(), recF.getAktiveRG_PositionLadeSeite(), true);
							} else {
								grid = new BewegungsGrid()._setShapeForderung(recF.getLeistungsDatumAbladeSeite(), recF.getZahlungsBedingungAbladeSeite(), recF.getAktiveRG_PositionAbladeSeite(), false);
							}
							
							
						} else {
						
							if (i==0) {
								grid = new BewegungsGrid()._setShapeForderung(recF.getLeistungsDatumLadeSeite(), recF.getZahlungsBedingungLadeSeite(), recF.getAktiveRG_PositionLadeSeite(), true);
							} else {
								grid = new BewegungsGrid()._setShapeForderung(recF.getLeistungsDatumAbladeSeite(), recF.getZahlungsBedingungAbladeSeite(), recF.getAktiveRG_PositionAbladeSeite(), false);
							}
						}
						break;

					default:
						grid = new E2_Grid()._setSize(50)._a(new RB_lab()._t("Error"),new RB_gld()._center_top());
						break;
					}
					
					compArr[0][i]=grid;
				}
				
				
				
				//jetzt erst die ek-orte durcharbeiten
				int iZeileLinks = 1;
				int iZeileRechts = 1;
				
				for (Rec21_VposTpaFuhreOrt fo: fuhrenOrte) {
					
					E2_Grid  grid = null;
					switch (fo.getAbrechTyp()) {
					case LAGER:
						grid = 	new BewegungsGrid()._setShapeHome();
						break;
						
					case LEER_ZU_NULL:
						grid = 	new BewegungsGrid()._setShapeLeer();
						break;
						
					case VERBINDLICHKEIT:
						grid = 	new BewegungsGrid()._setShapeVerbindlichkeit();
						break;
					
					case FORDERUNG:
						
						int verlaengerteFaktFrist = 0;
						try {
							//jetzt nachsehen, ob eine verlaengerte fakturierungsfrist gilt, wenn ja, hinweis darauf
							// jetzt nachsehen, ob zum leistungszeitpunkt eine kreditversicherung existiert
							SqlStringExtended query = KV_Lib.getSQLExtKreditversicherungMitFakturierungsFrist(	"MIN(" + KREDITVERS_KOPF.fakturierungsfrist.tnfn() + ")",
																													fo.getLongDbValue(VPOS_TPA_FUHRE_ORT.id_adresse), 
																													(Date) fo.getRawVal(VPOS_TPA_FUHRE_ORT.datum_lade_ablade));
							String[][] kvTage = bibDB.EinzelAbfrageInArray(query);
							verlaengerteFaktFrist = Integer.parseInt(kvTage[0][0]);
						} catch (Exception e) {
							verlaengerteFaktFrist = 0;
						}

						
						if (verlaengerteFaktFrist>0) {
							//dann auf die spalte der verlaengerten Fakt-Frist hinweisen
							//grid = new BewegungsGrid()._setShapeVerlaengerteFaktFrist();
							grid = new BewegungsGrid()._setShapeForderung(fo.getLeistungsDatum(), fo.getZahlungsBedingung(), fo.getAktiveRG_Position(), !fo.isEK());
						} else {
							grid = new BewegungsGrid()._setShapeForderung(fo.getLeistungsDatum(), fo.getZahlungsBedingung(), fo.getAktiveRG_Position(), fo.isEK());
						}
						break;


					default:
						grid = new E2_Grid()._setSize(50)._a(new RB_lab()._t("Error"),new RB_gld()._center_mid());
						break;
					}


					if (fo.isEK()) {
						compArr[iZeileLinks++][0]=grid;
					} else {
						compArr[iZeileRechts++][1]=grid;
					}
				}
				

				//jetzt das compArr mit leeren labels auffuellen
				for (int i=0; i < compArr.length; i++) {
					if (compArr[i][0]==null) {
						compArr[i][0]=new RB_lab("");
					}
					if (compArr[i][1]==null) {
						compArr[i][1]=new RB_lab("");
					}
				}
				
				E2_Grid gBase = new E2_Grid()._setSize(BewegungsGrid.spalteHalb, BewegungsGrid.spalteHalb);

				for (int i=0; i < compArr.length; i++) {
					gBase._a(compArr[i][0], new RB_gld()._center_top()._ins(0, 0, 0, 4));
					gBase._a(compArr[i][1], new RB_gld()._center_top()._ins(0, 0, 0, 4));
				}
				
				this._a(gBase);
				
			} else {
				
				//hier muss die version fuer den bg_vektor stehen
				this._clear()._a(new RB_lab()._t("Falscher Bewegungssatz !"));
			}
		} catch (Exception e) {
			e.getStackTrace();
			
			this._a(new E2_ErrorButton(e), new RB_gld()._center_top()._ins(3));
			
		}
	}

	
	
	
	
	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}
}
