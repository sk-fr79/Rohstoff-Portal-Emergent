package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_UMA_KON_ARTB_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_UMA_KON_ARTB_RUECKLIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KONTRAKT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;

public class FU_MASK_BT_DEFINIERE_UMA_VERTRAG extends __FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG
{
	
	
	public FU_MASK_BT_DEFINIERE_UMA_VERTRAG()
	{
		super();
	}
	
	/**
	 * liest die notwendigen felder aus der fuhrenmaske aus
	 */
	public MyE2_MessageVector PRUEFE_und_SETZE_STATUS_DER_NOTWENDIGEN_FELDER() throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		E2_ComponentMAP  oMAP = this.EXT().get_oComponentMAP();
		
		this.set_lID_ADRESSE_START(oMAP.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ADRESSE_START, true, true, 0l));
		this.set_lID_ADRESSE_ZIEL(oMAP.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ADRESSE_ZIEL, true, true, 0l));
		
		this.set_lID_ARTKELBEZ_EK(oMAP.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ARTIKEL_BEZ_EK, true, true, 0l));
		this.set_lID_ARTKELBEZ_VK(oMAP.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ARTIKEL_BEZ_VK, true, true, 0l));
		
		this.set_lID_UMA_KONTRAKT(oMAP.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_UMA_KONTRAKT, true, true, 0l));

		this.set_lID_ADRESSE_FREMDAUFTRAG(oMAP.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ADRESSE_FREMDAUFTRAG, true, true, 0l));
		this.set_bIsFremdwarenFuhre(oMAP.get_bActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__OHNE_ABRECHNUNG));
		
		this.set_lID_VPOS_TPA_FUHRE(oMAP.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_VPOS_TPA_FUHRE, true, true, 0l));
		

		
		this.KorrigiereAnzeigeFuer_UMA_STATUS(this.get_lID_UMA_KONTRAKT());
		
		return oMV;
	}

	@Override
	protected void INDIVIDUELLE_Anzeige(Long lID_UMA) throws myException
	{
		//hier werden auf der maske die uma-anzeigen aktiviert 
		
		//zuerst die felder auf der maske suchen (komponenten mit den UMA-Tags)
		//MyE2_Grid  oUMA_Anzeige_Grid = (MyE2_Grid) new E2_RecursiveSearch_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.FUHRENMASKE_UMA_ANZEIGE).get_SingleFoundComponent();
		
		FU_MASK_UMA_ANZEIGE    oUMA_Anzeige_Grid  = (FU_MASK_UMA_ANZEIGE) this.EXT().get_oComponentMAP().get__Comp(FU___CONST.HASH_MASK_COMP_UMA_MASKEN_ANZEIGE);
		
		
		
		if (oUMA_Anzeige_Grid!=null )
		{
			oUMA_Anzeige_Grid.setVisible(false);
			oUMA_Anzeige_Grid.removeAll();

			if (lID_UMA != null && lID_UMA.longValue()>0)
			{
				oUMA_Anzeige_Grid.setVisible(true);

				RECORD_UMA_KONTRAKT recUMA = new RECORD_UMA_KONTRAKT(lID_UMA);
				
				GridLayoutData  oGL5 =  MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_5_1_5_1);
				GridLayoutData  oGL20 =  MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_20_1_20_1);
				
				oGL5.setBackground(Color.GREEN);
				oGL20.setBackground(Color.GREEN);
				
				MutableStyle oStyle = new MutableStyle();
				oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(2,Color.BLACK,Border.STYLE_SOLID));
				oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
				
				MyE2_Grid oGridHelp = new MyE2_Grid(5, oStyle);
				
				MyE2_Grid oGridSortenLinks = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
				MyE2_Grid oGridSortenRechts = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
				
				RECLIST_UMA_KON_ARTB_LIEF 		recListSortenLief = 		recUMA.get_DOWN_RECORD_LIST_UMA_KON_ARTB_LIEF_id_uma_kontrakt();
				RECLIST_UMA_KON_ARTB_RUECKLIEF 	recListSortenRueckLief = 	recUMA.get_DOWN_RECORD_LIST_UMA_KON_ARTB_RUECKLIEF_id_uma_kontrakt();
				
				for (int i=0;i<recListSortenLief.get_vKeyValues().size();i++)
				{
					oGridSortenLinks.add(new MyE2_Label(recListSortenLief.get(i).get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("<anr1>")),oGL5);
					oGridSortenLinks.add(new MyE2_Label(recListSortenLief.get(i).get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ARTBEZ1_cUF_NN("<anr1>")),oGL5);
					oGridSortenLinks.add(new MyE2_Label(recListSortenLief.get(i).get_NUTZBAR_PROZENT_cF_NN("<proz>")+" %"),oGL5);
				}
				for (int i=0;i<recListSortenRueckLief.get_vKeyValues().size();i++)
				{
					oGridSortenRechts.add(new MyE2_Label(recListSortenRueckLief.get(i).get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("<anr1>")),oGL5);
					oGridSortenRechts.add(new MyE2_Label(recListSortenRueckLief.get(i).get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ARTBEZ1_cUF_NN("<anr1>")),oGL5);
					oGridSortenRechts.add(new MyE2_Label(recListSortenRueckLief.get(i).get_NUTZBAR_PROZENT_cF_NN("<proz>")+" %"),oGL5);
				}
				
				MyE2_Label oLabelInfo = new MyE2_Label(new MyE2_String("UMA-Kontrakt-ID: ",true,recUMA.get_ID_UMA_KONTRAKT_cF(),false));
				MyE2_Label oLabelUMA_Firma = new MyE2_Label(recUMA.get_UP_RECORD_ADRESSE_id_adresse().get_NAME1_cF_NN("<kunde>"));
				
				oGridHelp.add(oLabelInfo, 				oGL5);
				oGridHelp.add(oLabelUMA_Firma, 			oGL5);
				oGridHelp.add(oGridSortenLinks,			oGL5);
				oGridHelp.add(new MyE2_Label("nach"), oGL5);
				oGridHelp.add(oGridSortenRechts, 		oGL5);
				
				oUMA_Anzeige_Grid.add(oGridHelp,MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0));
			}
		}
	}
	

	
}
