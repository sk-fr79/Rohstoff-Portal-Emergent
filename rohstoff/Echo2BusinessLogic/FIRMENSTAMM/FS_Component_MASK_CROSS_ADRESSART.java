package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.XX_FormatCheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSKLASSE_DEF;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSKLASSE_DEF_EXT;

public class FS_Component_MASK_CROSS_ADRESSART extends	MyE2_DBC_CrossConnection
{

	public FS_Component_MASK_CROSS_ADRESSART(SQLFieldMAP osqlFieldMAP) throws myException
	{
		super((SQLFieldForPrimaryKey)osqlFieldMAP.get("ID_ADRESSE"),
				"JT_ADRESSKLASSE", 
				"JT_ADRESSKLASSE_DEF", 
				"ID_ADRESSKLASSE",
				"ID_ADRESSE", 
				"ID_ADRESSKLASSE_DEF",
				"ID_ADRESSKLASSE_DEF", 
				"JT_ADRESSKLASSE_DEF.KURZBEZEICHNUNG",
				null, 
				null,
				null, 
				"JT_ADRESSKLASSE_DEF.KURZBEZEICHNUNG",    //2015-05-28: order-statement zugefuegt 
				6,
				new E2_FontPlain(), new MyE2_String("Fehler bei der Zuordnung des Adresss-Typs!"), MyE2_DBC_CrossConnection.CROSSTYP_ALLOW_ALL);
		
		
		this.set_oFormatCheckBoxes(new ownFormatCheckBox());
		this.add_ActionAgentToCheckboxes(new ownActionFormat());
		
	}

	private class ownActionFormat extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FS_Component_MASK_CROSS_ADRESSART.this.MakeFormatting();
		}
		
	}
	
	
	//2015-08-26: formatierung mit der Farbe der adressklasse als hintergrund
	private class ownFormatCheckBox extends XX_FormatCheckBox {
		@Override
		public void doFormat(MyE2_CheckBox oCB) throws myException {
			String id_adressklasse_def = oCB.EXT().get_C_MERKMAL().replace(".", "");
			
			RECORD_ADRESSKLASSE_DEF_EXT rd = new RECORD_ADRESSKLASSE_DEF_EXT(new RECORD_ADRESSKLASSE_DEF(id_adressklasse_def));
			
			Color col = rd.generate_Color();
			
			if (col!=null) {
				if (oCB.isSelected()) {
					oCB.setBorder(new Border(4,col,Border.STYLE_SOLID));
					oCB.setInsets(E2_INSETS.I(3,1,3,1));
				} else {
					oCB.setBorder(new Border(2,col,Border.STYLE_SOLID));
					oCB.setInsets(E2_INSETS.I(3,1,3,1));
				}
			} else {
				oCB.setBorder(null);
				oCB.setInsets(E2_INSETS.I(3,1,3,1));
			}
			
		}
		
	}
	
	
	
}
