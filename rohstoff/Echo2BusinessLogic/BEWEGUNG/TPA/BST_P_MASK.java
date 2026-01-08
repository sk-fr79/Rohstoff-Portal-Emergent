package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_GRID;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__ARTBEZ_ANZEIGE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import echopointng.Separator;


/*
 * enthaelt die definition der maske
 */
public class BST_P_MASK extends MyE2_Column  implements IF_BaseComponent4Mask	
{
	private BST_P_MASK__ComponentMAP 	oBST_P_ComponentMAP_MASK = 	null;
	private E2_ComponentMAP  			oMapMaskFuhre = 			null;

	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public BST_P_MASK(BST_P_MASK__ComponentMAP maskMAP) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		this.oBST_P_ComponentMAP_MASK = maskMAP;
		
		FU_MASK_ComponentMAP oFU_MASK_ComponentMAP = this.oBST_P_ComponentMAP_MASK.get_oFU_MASK_ComponentMAP();
		
		this.oMapMaskFuhre = this.oBST_P_ComponentMAP_MASK.get_oFU_MASK_ComponentMAP();
		
		E2_MaskFiller oFill = new E2_MaskFiller(this.oBST_P_ComponentMAP_MASK,null,null,E2_INSETS.I_0_2_10_2,E2_INSETS.I_0_2_10_2, new Alignment(Alignment.LEFT,Alignment.CENTER));

		/*
		 * grid seite 1
		 */
		MyE2_TabbedPane oTabbedPane = new MyE2_TabbedPane(null);
		oTabbedPane.set_bAutoHeight(true);

		
		FU_MASK_GRID oFUG0 = null;
		FU_MASK_GRID oFUG1 = null;
		FU_MASK_GRID oFUG2 = null;
		FU_MASK_GRID oFUG3 = null;
		FU_MASK_GRID oFUG4 = null;
		FU_MASK_GRID oFUG5 = null;
		FU_MASK_GRID oFUG6 = null;
		FU_MASK_GRID oFUG7 = null;
		
		
		
		
		MyE2_Grid oGrid1 = new MyE2_Grid(7,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oTabbedPane.add_Tabb(new MyE2_String("Spedition"),oGrid1);
		
		oTabbedPane.add_Tabb(new MyE2_String("Fuhre"),								oFUG0 =	new FU_MASK_GRID(0,oFU_MASK_ComponentMAP), new ownActionAgent());
		oTabbedPane.add_Tabb(new MyE2_String("Adressen/Bemerk./Artikelbez."),		oFUG1 =	new FU_MASK_GRID(1,oFU_MASK_ComponentMAP));
		oTabbedPane.add_Tabb(new MyE2_String("Diverse"),							oFUG2 =	new FU_MASK_GRID(2,oFU_MASK_ComponentMAP));
		oTabbedPane.add_Tabb(new MyE2_String("Kost./Prot./Sonderpos."),				oFUG3 =	new FU_MASK_GRID(3,oFU_MASK_ComponentMAP));
		oTabbedPane.add_Tabb(new MyE2_String("Abzüge Ladeort"),						oFUG4 =	new FU_MASK_GRID(4,oFU_MASK_ComponentMAP));
		oTabbedPane.add_Tabb(new MyE2_String("Abzüge Abladeort"),					oFUG5 =	new FU_MASK_GRID(5,oFU_MASK_ComponentMAP));
		oTabbedPane.add_Tabb(new MyE2_String("Zusatzorte"),							oFUG6 =	new FU_MASK_GRID(6,oFU_MASK_ComponentMAP));
		oTabbedPane.add_Tabb(new MyE2_String("Fahrplan"),							oFUG7 =	new FU_MASK_GRID(7,oFU_MASK_ComponentMAP));

		
		// tab nummer 1
		
		oFill.add_Line(oGrid1,new MyE2_String("IDs (Kopf/Pos)"),1,"ID_VKOPF_TPA|ID_VPOS_TPA|# |#Positionsnummer|POSITIONSNUMMER|#|#Typ|POSITION_TYP",6);
		
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));
		
		oFill.add_Line(oGrid1,new MyE2_String("Artikel TPA"),1,"ID_ARTIKEL_BEZ|#(|ANR1|#/|ANR2|#)|#ID Artikel|ID_ARTIKEL",6);
		
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));

		//den inneren block einfuegen
		oGrid1.add(BS__CompMap_FieldMAP_Gemeinsamkeiten.get_MASK_GRID_Preise(maskMAP,"TPA",null,null),6,E2_INSETS.I_0_0_2_0);

		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));

		oFill.add_Line(oGrid1,new MyE2_String("Einheiten"),1,"|#Menge|EINHEITKURZ|#Mengen-Divisor:|MENGENDIVISOR|#Preiseinheit|EINHEIT_PREIS_KURZ",6);
		
		oFill.add_Line(oGrid1,new MyE2_String("Steuersatz "),1,			"STEUERSATZ",2);
		oFill.add_Line(oGrid1,new MyE2_String("Lieferbed. "),1,			"LIEFERBEDINGUNGEN",2);
		oFill.add_Line(oGrid1,new MyE2_String("Zahlungsbedingungen"),1,		"ID_ZAHLUNGSBEDINGUNGEN|#   |ZAHLUNGSBEDINGUNGEN|# |ZAHLTAGE|# |FIXMONAT|# |FIXTAG|# |SKONTO_PROZENT",2);
		
		// ENZ_ALARM
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));
		oFill.add_Line(oGrid1,new MyE2_String("Bemerkung (int.) "),1,	"BEMERKUNG_INTERN",2);
		
		this.vMaskGrids.add(oGrid1);
		this.vMaskGrids.add(oFUG0);
		this.vMaskGrids.add(oFUG1);
		this.vMaskGrids.add(oFUG2);
		this.vMaskGrids.add(oFUG3);
		this.vMaskGrids.add(oFUG4);
		this.vMaskGrids.add(oFUG5);
		this.vMaskGrids.add(oFUG6);
		this.vMaskGrids.add(oFUG7);
		
		this.add(oTabbedPane);
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_ComponentMAP  oMap = BST_P_MASK.this.oMapMaskFuhre;
			
			//jetzt die Sortenbezeichnungslabels aufbauen
			FU__ARTBEZ_ANZEIGE  oArtbezAnzeigeEK = (FU__ARTBEZ_ANZEIGE)oMap.get__Comp(FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_EK);
			FU__ARTBEZ_ANZEIGE  oArtbezAnzeigeVK = (FU__ARTBEZ_ANZEIGE)oMap.get__Comp(FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_VK);
		    oArtbezAnzeigeEK.baue_anzeige("","","EK");
			oArtbezAnzeigeVK.baue_anzeige("","","VK");

			oArtbezAnzeigeEK.baue_anzeige(true);
			oArtbezAnzeigeVK.baue_anzeige(false);
			
		}
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	
}
