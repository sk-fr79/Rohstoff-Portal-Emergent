package rohstoff.Echo2BusinessLogic._KORRE.LIEF_BED;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * fuellen von fuhren mit fehlender lieferbedingung
 * @author martin
 *
 */
public class KorrigiereLieferbedingungenNachRegeln {

	private Font 					oFontItalic = new E2_FontItalic(-2);

	private Vector<XX_KorrRule> 	vRules = new Vector<XX_KorrRule>();
	
	private KorrCounter  			oCount = new KorrCounter();
	
	private GridLayoutData          glTitel = null;
	private GridLayoutData          glZahlen = null;
	
	private long   					lAnzahl = 0;         //anzahl der fuhren, die geprueft werden
	
	public KorrigiereLieferbedingungenNachRegeln(E2_ServerPushMessageContainer  oMessageContainer) throws myException	{
		super();
		
		this.glTitel = 	MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I(2,2,2,2), new E2_ColorDDark(), 1);
		this.glZahlen = MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorBase(), 1);
		
		
		//alle betreffenden fuhren abfragen
//		public static int     	STATUS_FUHRE__IST_STORNIERT = 					-2;
//		public static int     	STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT = 		-1;
//		public static int     	STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG = 	1;
//		public static int     	STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS = 		2;
//		public static int     	STATUS_FUHRE__UNGEBUCHT= 						3;   ab hier wird geprueft
//		public static int     	STATUS_FUHRE__TEILSGEBUCHT= 					4;
//		public static int     	STATUS_FUHRE__GANZGEBUCHT = 					5;

		String[][] arrID_VPOS_TPA_FUHRE = bibDB.EinzelAbfrageInArray(
				"SELECT ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE "
						+ " WHERE NVL(DELETED,'N')='N' "
						+ " AND   NVL("+_DB.VPOS_TPA_FUHRE$IST_STORNIERT+",'N')='N'"
						+ " AND   NVL("+_DB.VPOS_TPA_FUHRE$STATUS_BUCHUNG+",0)>=3"
//						+"  AND   ID_VPOS_TPA_FUHRE=55963 "            //debug
						);
		
		//hier die regeln einhaengen
		this.vRules.add(new R1_KorrRuleWareneingang_eigener_lkw());
		this.vRules.add(new R2_KorrRuleWarenausgang_eigener_lkw());
		this.vRules.add(new R3_KorrRuleWareneingang_fremd_lkw_Ausweis());
		this.vRules.add(new R4_KorrRuleWareneingang_fremd_lkw_TPA());
		
		
		this.lAnzahl = arrID_VPOS_TPA_FUHRE.length;
		
		for (int i=0;i<lAnzahl;i++){
			RECORD_VPOS_TPA_FUHRE recFUHRE  = new RECORD_VPOS_TPA_FUHRE(arrID_VPOS_TPA_FUHRE[i][0]);
	
			oCount.iZaehlerFuhren++;
			
			if (S.isEmpty(recFUHRE.get_TRANSPORTKENNZEICHEN_cUF_NN(""))) {
				this.oCount.iZaehlerKeinLKW++;
			}

			for (XX_KorrRule oRule: this.vRules) {
				oRule.PRUEFE_LieferBedingung(recFUHRE, this.oCount, true);
			}
			
			if (oMessageContainer!=null && oMessageContainer.get_bIsInterupted()) {
				break;
			}

			this.refresh_Anzeige(oMessageContainer);

			//dann evtl. vorhandene orte pruefen
			RECLIST_VPOS_TPA_FUHRE_ORT  rlFuhreORT = recFUHRE.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL("+_DB.VPOS_TPA_FUHRE_ORT$DELETED+",'N')='N'", null, true);
			
			for (RECORD_VPOS_TPA_FUHRE_ORT recORT: rlFuhreORT.values()) {
				this.oCount.iZaehlerFuhrenOrte++;
				
				for (XX_KorrRule oRule: this.vRules) {
					oRule.PRUEFE_LieferBedingung(recORT,recFUHRE, this.oCount,true);
				}
				
				if (oMessageContainer!=null && oMessageContainer.get_bIsInterupted()) {
					break;
				}
				this.refresh_Anzeige(oMessageContainer);
			}
		}
	}
	
	
	private void refresh_Anzeige(E2_ServerPushMessageContainer  oMessageContainer) {
		if (oMessageContainer!=null) {
			if ( (this.oCount.iZaehlerFuhren+this.oCount.iZaehlerFuhrenOrte)%10==0) {
				oMessageContainer.get_oGridBaseForMessages().setBorder(new Border(new Extent(1), Color.BLACK, Border.STYLE_SOLID));
				oMessageContainer.get_oGridBaseForMessages().removeAll();
				oMessageContainer.get_oGridBaseForMessages().setSize(10);
				
				//titel bloecke
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String("Prüfblock"),oFontItalic,true), MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I(2,2,2,2), new E2_ColorDDDark(), 3));
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String("Sachverhalte"),oFontItalic,true), MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I(2,2,2,2), new E2_ColorDDDark(), 4));
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String("Status Aktualisierung"),oFontItalic,true), MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I(2,2,2,2), new E2_ColorDDDark(), 3));
				
				//ueberschriften der spalten
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String("Fuhren"),oFontItalic,true), this.glTitel);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String("Zusatzorte"),oFontItalic,true), this.glTitel);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String("LKW fehlt"),oFontItalic,true), this.glTitel);
				
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String(this.vRules.get(0).get_UebersichtsText()),oFontItalic,true), this.glTitel);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String(this.vRules.get(1).get_UebersichtsText()),oFontItalic,true), this.glTitel);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String(this.vRules.get(2).get_UebersichtsText()),oFontItalic,true), this.glTitel);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String(this.vRules.get(3).get_UebersichtsText()),oFontItalic,true), this.glTitel);

				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String("Bereits vorhanden"),oFontItalic,true), this.glTitel);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String("Aktualisiert"),oFontItalic,true), this.glTitel);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String("Fehler beim Update"),oFontItalic,true), this.glTitel);
					
				//inhalte dazu
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(""+oCount.iZaehlerFuhren+" / "+this.lAnzahl), this.glZahlen);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(""+oCount.iZaehlerFuhrenOrte), this.glZahlen);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(""+oCount.iZaehlerKeinLKW), this.glZahlen);
				
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(""+this.vRules.get(0).get_lOwnZaehler()), this.glZahlen);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(""+this.vRules.get(1).get_lOwnZaehler()), this.glZahlen);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(""+this.vRules.get(2).get_lOwnZaehler()), this.glZahlen);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(""+this.vRules.get(3).get_lOwnZaehler()), this.glZahlen);

				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(""+oCount.iZaehlerSachverhaltGefundenUndBereitsVorhanden), this.glZahlen);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(""+oCount.iZaehlerSachverhaltGefundenUndAktualisiert), this.glZahlen);
				oMessageContainer.get_oGridBaseForMessages().add(new MyE2_Label(""+oCount.iZaehlerSachverhaltGefundenUndUpdateFehler), this.glZahlen);
				
			}
		}
    }

	
	
}
