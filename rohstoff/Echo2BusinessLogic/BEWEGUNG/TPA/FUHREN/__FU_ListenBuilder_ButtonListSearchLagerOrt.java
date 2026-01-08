package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.Button4SearchResultList;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


//ausgelagerte methode zum aufbau der populisten/lagerorte in fuhren/fuhrenorte
public class __FU_ListenBuilder_ButtonListSearchLagerOrt
{
	
	private Vector<XX_Button4SearchResultList[]>  vRueck = new Vector<XX_Button4SearchResultList[]> ();
	

	public __FU_ListenBuilder_ButtonListSearchLagerOrt(RECLIST_ADRESSE reclistAdressen ) throws myException
	{
		super();
		
		//JT_ADRESSE.ID_ADRESSE,VORNAME,NAME1,NAME2,NAME3,STRASSE,HAUSNUMMER,JT_ADRESSE.ID_LAND,LAENDERCODE,PLZ,ORT,ORTZUSATZ,'N' as IST_STANDARD,OEFFNUNGSZEITEN "
		//ab  2013-05-28: ID_ADRESSE_FREMDWARE
		for (int i=0;i<reclistAdressen.get_vKeyValues().size();i++)
		{
			RECORD_ADRESSE recAdr = reclistAdressen.get(i);
			
			XX_Button4SearchResultList[] butZeile = new XX_Button4SearchResultList[6];
			butZeile[0] = new Button4SearchResultList(reclistAdressen.get(i).get_PLZ_cUF_NN("<plz>"));
			butZeile[1] = new Button4SearchResultList(reclistAdressen.get(i).get_ORT_cUF_NN("<ort>"),MyE2_Button.StyleTextButtonSTD(new E2_FontBold()));
			butZeile[2] = new Button4SearchResultList(reclistAdressen.get(i).get_NAME1_cUF_NN("<name1>")+" "+reclistAdressen.get(i).get_NAME2_cUF_NN("<name2>"));
			butZeile[3] = new Button4SearchResultList(reclistAdressen.get(i).get_STRASSE_cUF_NN("<strasse>"));
			butZeile[4] = new Button4SearchResultList(reclistAdressen.get(i).get_ID_ADRESSE_cF_NN(""));
			butZeile[0].EXT().set_C_MERKMAL(reclistAdressen.get(i).get_ID_ADRESSE_cUF());
			butZeile[1].EXT().set_C_MERKMAL(reclistAdressen.get(i).get_ID_ADRESSE_cUF());
			butZeile[2].EXT().set_C_MERKMAL(reclistAdressen.get(i).get_ID_ADRESSE_cUF());
			butZeile[3].EXT().set_C_MERKMAL(reclistAdressen.get(i).get_ID_ADRESSE_cUF());
			butZeile[4].EXT().set_C_MERKMAL(reclistAdressen.get(i).get_ID_ADRESSE_cUF());
			
			//2013-05-28: neue spalte, die lager anzeigt, die als fremdwarenlager definiert sind
			if (recAdr.containsKey(_DB.LIEFERADRESSE$ID_ADRESSE_FREMDWARE) &&  S.isFull(recAdr.get_UnFormatedValue(_DB.LIEFERADRESSE$ID_ADRESSE_FREMDWARE, ""))) {
				butZeile[5] = new Button4SearchResultList("FL");
				butZeile[5].EXT().set_C_MERKMAL(reclistAdressen.get(i).get_ID_ADRESSE_cUF());
				butZeile[5].setFont(new E2_FontBold());
				butZeile[5].setBackground(new E2_ColorHelpBackground());
			} else {
				butZeile[5] = new Button4SearchResultList("-");
				butZeile[5].EXT().set_C_MERKMAL(reclistAdressen.get(i).get_ID_ADRESSE_cUF());
			}
			
			if (i==0)  //hauptadresse
			{
				for (int l=0;l<4;l++)
				{
					butZeile[l].setFont(new E2_FontBold());
				}
			}
			
			//aenderung 2011-01-28: inaktive deaktivieren
			if (recAdr.is_AKTIV_NO()) {
				for (int k=0;k<6;k++) {
					butZeile[k].set_bEnabled_For_Edit(false);
				}
			}
			
			this.vRueck.add(butZeile);
		}
	}

	public Vector<XX_Button4SearchResultList[]> get_vButtonArrays()
	{
		return vRueck;
	}

	
	
	
	
}
