package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.layout.GridLayoutData;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;

public class HD_FehlerBerichte extends Vector<HD_FehlerBericht>
{

	private static int[] iSpalten = {100,100,100,100,100,100};
	
	private GridLayoutData oCent = MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 1);
	private GridLayoutData oLeft = MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 1);
	
	
	public HD_FehlerBerichte()
	{
		super();
	}

	
	public MyE2_Grid  get_GridWithMeldungen()
	{
		MyE2_Grid oGridRueck = new MyE2_Grid(HD_FehlerBerichte.iSpalten, MyE2_Grid.STYLE_GRID_BORDER_W100(Color.BLACK));
		
		oGridRueck.add(new MyE2_Label(new MyE2_String("Fehler-Status: Fuhre ist unvollständig und kann nicht bewertet werden!"), MyE2_Label.STYLE_ERROR_BIG()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDark(), 6));
		oGridRueck.add(new MyE2_Label(new MyE2_String("Ladeseite (Einkauf oder Startlager)"),new E2_FontBoldItalic(),true),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDark(), 3));
		oGridRueck.add(new MyE2_Label(new MyE2_String("Abladeseite (Verkauf oder Ziellager)"),new E2_FontBoldItalic(),true),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDark(), 3));
		
		
		//zuerst die ek- vk-faelle zaehlen
		Vector<HD_FehlerBericht> vEKFehler = this.get_vFehler(true);
		Vector<HD_FehlerBericht> vVKFehler = this.get_vFehler(false);
		
		int iNum = Math.max(vEKFehler.size(), vVKFehler.size());
		
		
		for (int i=0; i<iNum; i++)
		{
			if (i<vEKFehler.size())
			{
				HD_FehlerBericht oFehler = vEKFehler.get(i);
				
				oGridRueck.add(new MyE2_Label(oFehler.get_cFehlerIstWo(),		new E2_FontBold(),true), this.oLeft);
				oGridRueck.add(new MyE2_Label(oFehler.get_cFehlerBetrifft(),	new E2_FontPlain(),true), this.oLeft);
				oGridRueck.add(new MyE2_Label(oFehler.get_cFehlerInfo(),		new E2_FontItalic(),true), this.oCent);
			}
			else
			{
				oGridRueck.add(new MyE2_Label("",	new E2_FontPlain()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 3));
			}
			
			if (i<vVKFehler.size())
			{
				HD_FehlerBericht oFehler = vVKFehler.get(i);
				
				oGridRueck.add(new MyE2_Label(oFehler.get_cFehlerIstWo(),		new E2_FontBold(),true), this.oLeft);
				oGridRueck.add(new MyE2_Label(oFehler.get_cFehlerBetrifft(),	new E2_FontPlain(),true), this.oLeft);
				oGridRueck.add(new MyE2_Label(oFehler.get_cFehlerInfo(),		new E2_FontItalic(),true), this.oCent);
			}
			else
			{
				oGridRueck.add(new MyE2_Label("",	new E2_FontPlain()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 3));
			}
		}

		return oGridRueck;
		
	}
	
	
	public int get_iAnzahlFehler(boolean bEK)
	{
		int iCount = 0;
		for (HD_FehlerBericht oFehler: this)
		{
			if (!(oFehler.get_bEK()^bEK))
			{
				iCount++;
			}
		}
		
		return iCount;
	}
	
	
	private Vector<HD_FehlerBericht> get_vFehler(boolean bEK)
	{
		 Vector<HD_FehlerBericht> vRueck = new Vector<HD_FehlerBericht>();
		 
		for (HD_FehlerBericht oFehler: this)
		{
			if (!(oFehler.get_bEK()^bEK))
			{
				vRueck.add(oFehler);
			}
		}

		 
		 return vRueck;
	}
	
	
	//2014-02-26: E2_MessageVector erzeugen und retunieren
	public MyE2_MessageVector  get_FehlerAsMessageVector() {
		MyE2_MessageVector  oMV_Rueck = new MyE2_MessageVector();
		
		for (HD_FehlerBericht oFehler: this)
		{
			oMV_Rueck.add(new MyE2_Alarm_Message(new MyE2_String(oFehler.get_cFehlerBetrifft().CTrans(),false," -- ",false,oFehler.get_cFehlerIstWo().CTrans(),false," -- ",false,oFehler.get_cFehlerInfo().CTrans(),false)));
		}

		
		return oMV_Rueck;
	}
	
	
}
