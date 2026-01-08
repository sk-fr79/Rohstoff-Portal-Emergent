package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.STAMM;


import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class FKR_MASK extends MyE2_Grid 
{

	
	public FKR_MASK(FKR_MASK_ComponentMAP oMap) throws myException
	{
//		super(4, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		// Grid with four columns
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		
		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("Regelnummer:"), 1, _DB.FIBU_KONTENREGEL$ID_FIBU_KONTENREGEL+"|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I(0,2,0,3));
//Doppelleinig
		//		oFiller.add_Line(this, new MyE2_String("ID_ADRESSE:"), 1, _DB.FIBU_KONTENREGEL$ID_ADRESSE+"|#  |",1,"#Artikel",1,_DB.FIBU_KONTENREGEL$ID_ARTIKEL,1);
		oFiller.add_Line(this, new MyE2_String("Kunde:"), 1, _DB.FIBU_KONTENREGEL$ID_ADRESSE+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Artikel:"), 1, _DB.FIBU_KONTENREGEL$ID_ARTIKEL+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Fibu-Artikelgruppe:"), 1, _DB.FIBU_KONTENREGEL$ID_ARTIKEL_GRUPPE_FIBU+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Steuersatz:"), 1, _DB.FIBU_KONTENREGEL$ID_TAX+"|#  |",3);
		//////////////////////////// 2014-11-14: martin: die componentmaps (START)
		oFiller.add_Separator(this, E2_INSETS.I(0,2,0,3));
		oFiller.add_Line(this, new MyE2_String("Quell-Länder"),4);
		oFiller.add_Line(this, FKR__CONST.MASK_COMPONENT_CROSSTABLE_LAENDER_EK,4);
		oFiller.add_Separator(this, E2_INSETS.I(0,2,0,3));
		oFiller.add_Line(this, new MyE2_String("Ziel-Länder"),4);
		oFiller.add_Line(this, FKR__CONST.MASK_COMPONENT_CROSSTABLE_LAENDER_VK,4);
		oFiller.add_Line(this, new MyE2_String("Dienstleistung?:"), 1, "DIENSTLEISTUNG"+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Privat?:"), 1, "PRIVAT"+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Einkauf/Verkauf:"), 1, _DB.FIBU_KONTENREGEL$DEF_EK_VK+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Resultierendes Buchungskonto:"), 1, _DB.FIBU_KONTENREGEL$ID_FIBU_KONTO+"|#  (auf dieses Konto wird gebucht)|",3);



		
		
		////////////////////////////2014-11-14: martin: die componentmaps (END)
	}

	
	
}
