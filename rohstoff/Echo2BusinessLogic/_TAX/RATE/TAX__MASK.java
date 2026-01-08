package rohstoff.Echo2BusinessLogic._TAX.RATE;



import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class TAX__MASK extends MyE2_Grid  {
  
	private static int[] breite = {200,200,200,200};
	
	public TAX__MASK(TAX__MASK_ComponentMAP oMap) throws myException 	{
		super(breite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		oFiller.add_Separator(this, E2_INSETS.I_0_0_0_0);
		
//		//hier kommen die Felder	
//		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$ID_TAX).EXT().get_cList_or_Mask_Titel(), 		1,_DB.TAX$ID_TAX+"|#  |",1,"|# ",1,"|# ",1);
//		oFiller.add_Separator(this, E2_INSETS.I_0_0_0_0);
//
//		//2013-10-01: aktiv-schalter
//		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$AKTIV).EXT().get_cList_or_Mask_Titel(), 			1,_DB.TAX$AKTIV+"|#  |",3);
//		oFiller.add_Line(this, 	oMap.get__Comp("HISTORISCH").EXT().get_cList_or_Mask_Titel(), 			1, "HISTORISCH"+"|#  |",3);
//		oFiller.add_Separator(this, E2_INSETS.I_0_0_0_0);
//
//		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$ID_LAND).EXT().get_cList_or_Mask_Titel(), 		1, _DB.TAX$ID_LAND+"|#  |",3);
//		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$DROPDOWN_TEXT).EXT().get_cList_or_Mask_Titel(), 	1, _DB.TAX$DROPDOWN_TEXT+"|#  |",3);
//		oFiller.add_Line(this,	oMap.get__Comp(_DB.TAX$STEUERSATZ).EXT().get_cList_or_Mask_Titel(), 	1, _DB.TAX$STEUERSATZ+"|#  |",3);
//		oFiller.add_Line(this,	oMap.get__Comp(_DB.TAX$STEUERVERMERK).EXT().get_cList_or_Mask_Titel(), 	1, _DB.TAX$LEERVERMERK+"|# (Erzeugt einen Leer-Vermerk) |",3);
//		oFiller.add_Line(this,	new MyE2_String(""), 													1, _DB.TAX$STEUERVERMERK+"|# |",3);
//		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$TAXTYP).EXT().get_cList_or_Mask_Titel(), 		1, _DB.TAX$TAXTYP+"|#  |",3);
//		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$INFO_TEXT_USER).EXT().get_cList_or_Mask_Titel(), 1, _DB.TAX$INFO_TEXT_USER+"|#  |",3);
//		oFiller.add_Separator(this, E2_INSETS.I_0_0_0_0);
//		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$WECHSELDATUM).EXT().get_cList_or_Mask_Titel(), 	1, _DB.TAX$WECHSELDATUM+"|#  |",3);
//		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$STEUERSATZ_NEU).EXT().get_cList_or_Mask_Titel(), 1, _DB.TAX$STEUERSATZ_NEU+"|#  |",3);
//		oFiller.add_Separator(this, E2_INSETS.I_0_0_0_0);
//		oFiller.add_Line(this, 	oMap.get__Comp(TAX.id_fibu_konto_gs.fn()).EXT().get_cList_or_Mask_Titel(), 1, TAX.id_fibu_konto_gs.fn()+"|#  |",3);
//		oFiller.add_Line(this, 	oMap.get__Comp(TAX.id_fibu_konto_re.fn()).EXT().get_cList_or_Mask_Titel(), 1, TAX.id_fibu_konto_re.fn()+"|#  |",3);


		//hier kommen die Felder	
		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$ID_TAX).EXT().get_cList_or_Mask_Titel(), 		1,_DB.TAX$ID_TAX+"|#  |",1,"|# ",1,"|# ",1);
		oFiller.add_Separator(this, E2_INSETS.I_0_0_0_0);

		//2013-10-01: aktiv-schalter
		oFiller.add_Line(this, 	"#Merkmale", 			1,_DB.TAX$AKTIV+        "|#    |#  |"+TAX.historisch.fn(),3);
		oFiller.add_Separator(this, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(this, 	"#Gruppe/Sort", 		1,TAX.id_tax_group.fn()+"|#    |#Sortierung|"+TAX.sort.fn(),3);
//		oFiller.add_Line(this, 	oMap.get__Comp("HISTORISCH").EXT().get_cList_or_Mask_Titel(), 			1, "HISTORISCH"+"|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I_0_0_0_0);

		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$ID_LAND).EXT().get_cList_or_Mask_Titel(), 		1, _DB.TAX$ID_LAND+"|#  |",3);
		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$DROPDOWN_TEXT).EXT().get_cList_or_Mask_Titel(), 	1, _DB.TAX$DROPDOWN_TEXT+"|#  |",3);
		oFiller.add_Line(this,	oMap.get__Comp(_DB.TAX$STEUERSATZ).EXT().get_cList_or_Mask_Titel(), 	1, _DB.TAX$STEUERSATZ+"|#  |",3);
		oFiller.add_Line(this,	oMap.get__Comp(_DB.TAX$STEUERVERMERK).EXT().get_cList_or_Mask_Titel(), 	1, _DB.TAX$LEERVERMERK+"|# (Erzeugt einen Leer-Vermerk) |",3);
		oFiller.add_Line(this,	new MyE2_String(""), 													1, _DB.TAX$STEUERVERMERK+"|# |",3);
		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$TAXTYP).EXT().get_cList_or_Mask_Titel(), 		1, _DB.TAX$TAXTYP+"|#  |",3);
		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$INFO_TEXT_USER).EXT().get_cList_or_Mask_Titel(), 1, _DB.TAX$INFO_TEXT_USER+"|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$WECHSELDATUM).EXT().get_cList_or_Mask_Titel(), 	1, _DB.TAX$WECHSELDATUM+"|#  |",3);
		oFiller.add_Line(this, 	oMap.get__Comp(_DB.TAX$STEUERSATZ_NEU).EXT().get_cList_or_Mask_Titel(), 1, _DB.TAX$STEUERSATZ_NEU+"|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(this, 	oMap.get__Comp(TAX.id_fibu_konto_gs.fn()).EXT().get_cList_or_Mask_Titel(), 1, TAX.id_fibu_konto_gs.fn()+"|#  |",3);
		oFiller.add_Line(this, 	oMap.get__Comp(TAX.id_fibu_konto_re.fn()).EXT().get_cList_or_Mask_Titel(), 1, TAX.id_fibu_konto_re.fn()+"|#  |",3);
		
		
	}

	
	
}
