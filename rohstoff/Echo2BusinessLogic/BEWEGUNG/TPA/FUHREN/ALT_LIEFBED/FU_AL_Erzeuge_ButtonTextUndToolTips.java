package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class FU_AL_Erzeuge_ButtonTextUndToolTips {

	private  MyE2_String cTextAufButton = new MyE2_String("");
	private  String 	 cToolTips = "";
	
	
	public FU_AL_Erzeuge_ButtonTextUndToolTips(	FU_AL_SucheLieferBed 					sucheLieferBed, 
												FU_AL_LieferbedZeigeAktuellStatistik 	sucheAktuelleStatistik) throws myException {
		super();
		

		//wert aus der Fuhre/aus der Rechnungspos/Gutschriftspos
		boolean bHatAktuellenStatistikWert = S.isFull(sucheAktuelleStatistik.get_cLIEFERFBED_STATISTIK());
		String cAktuellerStatistikWert = (S.NN(sucheAktuelleStatistik.get_cLIEFERFBED_STATISTIK()).trim()+"   ").substring(0, 3).toUpperCase();
		//wert aus der Fuhre/oder wie die standard-findung waere
		String cWertFuerUebergabeBeiBuchung = sucheLieferBed.get_cLIEFERBED_incl_FUHRE();
		boolean bWerteGleich = (cAktuellerStatistikWert.equals(
										(cWertFuerUebergabeBeiBuchung.trim()+"   ").substring(0, 3).toUpperCase()));
		
		if (sucheLieferBed.get_bIS_MANDANT()) {
			cTextAufButton = new MyE2_String("<Eigenes Lager>");
			cToolTips = sucheLieferBed.get_cToolTipInfos().CTrans();
		} else if (S.isFull(sucheLieferBed.get_cLIEFERBED_incl_FUHRE())) {
			cTextAufButton = new MyE2_String(cWertFuerUebergabeBeiBuchung,false);
			cToolTips = sucheLieferBed.get_cToolTipInfos().CTrans();
			if (!bWerteGleich && bHatAktuellenStatistikWert) {
				cTextAufButton.addUnTranslated(" (Stat: "+(cAktuellerStatistikWert+"   ").substring(0,3)+")");
				cToolTips = sucheLieferBed.get_cToolTipInfos().CTrans()+" // "+
									new MyE2_String("Der Wert, der in der Statistik benutzt wird, ist (aus RG-Pos): ",true,cAktuellerStatistikWert,false).CTrans();
			}
		} else {
			cTextAufButton = new MyE2_String("<-->");
			cToolTips = sucheLieferBed.get_cToolTipInfos().CTrans();
			if (!bWerteGleich && bHatAktuellenStatistikWert) {
				cTextAufButton.addUnTranslated(" (Stat: "+(cAktuellerStatistikWert+"   ").substring(0,3)+")");
				cToolTips = sucheLieferBed.get_cToolTipInfos().CTrans()+" // "+
						new MyE2_String("Der Wert, der in der Statistik benutzt wird, ist (aus RG-Pos): ",true,cAktuellerStatistikWert,false).CTrans();
			}
		}

		
	}


	public MyE2_String get_cTextAufButton() {
		return cTextAufButton;
	}


	public String get_cToolTips() {
		return cToolTips;
	}

	
	
	
}
