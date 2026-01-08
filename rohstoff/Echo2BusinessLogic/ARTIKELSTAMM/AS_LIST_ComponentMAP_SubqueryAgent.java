package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import java.util.HashMap;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


public class AS_LIST_ComponentMAP_SubqueryAgent extends XX_ComponentMAP_SubqueryAGENT
{

	// die einheit/preis wird in der hashmap gehalten
	private HashMap<String,String>  hmEinheiten = new HashMap<String,String>();
	
	private GridLayoutData  oGLRED = new GridLayoutData();
	private GridLayoutData  oGLNORM = new GridLayoutData();
	
	
	public AS_LIST_ComponentMAP_SubqueryAgent() throws myException
	{
		super();
		String[][] cEINHEIT = bibDB.EinzelAbfrageInArray("SELECT ID_EINHEIT,EINHEITKURZ FROM "+bibE2.cTO()+".JT_EINHEIT","");
		if (cEINHEIT == null)
			throw new myException("");
		
		this.hmEinheiten.put("","---");
		for (int i=0;i<cEINHEIT.length;i++)
		{
			this.hmEinheiten.put(cEINHEIT[i][0],cEINHEIT[i][1]);
		}
		
		this.oGLRED.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		this.oGLRED.setBackground(new E2_ColorAlarm());
		this.oGLRED.setInsets(new Insets(2,1,2,2));
		
		this.oGLNORM.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		this.oGLNORM.setBackground(new E2_ColorBase());
		this.oGLNORM.setInsets(new Insets(2,1,2,2));
		
		
		
	}
	
	

	/*
	 * subquery-agent, füllt die infofelder wieviele mitarbeiter/Infos usw eine adresse hat
	 */
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException
	{
		HashMap<String, MyE2IF__Component> hmRealComponents = oMAP.get_REAL_ComponentHashMap();

		((MyE2_Label)hmRealComponents.get(AS___CONST.LABEL_EINHEIT_PREIS)).setText("");
		
	}

	public void fillComponents(E2_ComponentMAP oMAP,SQLResultMAP oUsedResultMAP) throws myException
	{
		HashMap<String, MyE2IF__Component> hmRealComponents = oMAP.get_REAL_ComponentHashMap();
		
		MyE2_Label oLabel = (MyE2_Label)hmRealComponents.get(AS___CONST.LABEL_EINHEIT_PREIS);
		
		String cID_EINHEIT_PREIS = (String)oUsedResultMAP.get_UnFormatedValue("ID_EINHEIT_PREIS");
		
		if (cID_EINHEIT_PREIS != null)
		{
			String cEinheit = (String)this.hmEinheiten.get(cID_EINHEIT_PREIS);
			if (cEinheit != null)
			{
				oLabel.set_Text(cEinheit);
			}
		}

		
		//sowohl fuer den aufbau der liste, als auch bei korrigierten werten aendern,
		// deshalb sowohl in: 	cbGefahr.EXT().set_oLayout_ListElement(this.oGLRED);
		// als auch in :		cbGefahr.setLayoutData(this.oGLRED);
		
		//die gefahrenspalten rot hinterlegen
		MyE2_DB_CheckBox cbGefahr = (MyE2_DB_CheckBox)oMAP.get__DBComp("GEFAHRGUT");
		if (cbGefahr.isSelected())
		{
			cbGefahr.EXT().set_oLayout_ListElement(this.oGLRED);
			cbGefahr.setLayoutData(this.oGLRED);
		}
		else
		{
			cbGefahr.EXT().set_oLayout_ListElement(this.oGLNORM);
			cbGefahr.setLayoutData(this.oGLNORM);
		}
		
		
		//CODE_GES_IN
		MyE2_DB_Label_INROW lAVV_IN = (MyE2_DB_Label_INROW)oMAP.get__DBComp("CODE_GES_IN");
		if (S.NN(lAVV_IN.getText()).indexOf("*")>0)
		{
			lAVV_IN.EXT().set_oLayout_ListElement(this.oGLRED);
			lAVV_IN.get_oRowContainer().setLayoutData(this.oGLRED);
		}
		else
		{
			lAVV_IN.EXT().set_oLayout_ListElement(this.oGLNORM);
			lAVV_IN.get_oRowContainer().setLayoutData(this.oGLNORM);
		}

		//CODE_GES_OUT
		MyE2_DB_Label_INROW lAVV_OUT = (MyE2_DB_Label_INROW)oMAP.get__DBComp("CODE_GES_OUT");
		if (S.NN(lAVV_OUT.getText()).indexOf("*")>0)
		{
			lAVV_OUT.EXT().set_oLayout_ListElement(this.oGLRED);
			lAVV_OUT.get_oRowContainer().setLayoutData(this.oGLRED);
		}
		else
		{
			lAVV_OUT.EXT().set_oLayout_ListElement(this.oGLNORM);
			lAVV_OUT.get_oRowContainer().setLayoutData(this.oGLNORM);
		}

		
	}

}
