package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_BRANCHE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_CODE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_GRUPPE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_EAK_CODE_ext extends RECORD_EAK_CODE 
{

	public RECORD_EAK_CODE_ext() throws myException 
	{
		super();
	}

	public RECORD_EAK_CODE_ext(long lIDUnformated, MyConnection Conn)
			throws myException {
		super(lIDUnformated, Conn);

	}

	public RECORD_EAK_CODE_ext(long lIDUnformated) throws myException {
		super(lIDUnformated);
	}

	public RECORD_EAK_CODE_ext(MyConnection Conn) throws myException {
		super(Conn);
	}

	public RECORD_EAK_CODE_ext(RECORD_EAK_CODE recordOrig) {
		super(recordOrig);
	}

	public RECORD_EAK_CODE_ext(String cIDOrWHEREBLOCKORSQL, MyConnection Conn)
			throws myException {
		super(cIDOrWHEREBLOCKORSQL, Conn);
	}

	public RECORD_EAK_CODE_ext(String cIDOrWHEREBLOCKORSQL) throws myException {
		super(cIDOrWHEREBLOCKORSQL);
	}
	
	
	public String get_complete_Code() throws myException
	{
		String cInfoText = "";
		
		String cSQL1 = "SELECT    JT_EAK_BRANCHE.KEY_BRANCHE, " +
								" JT_EAK_GRUPPE.KEY_GRUPPE," +
								" JT_EAK_CODE.KEY_CODE, " +
								" JT_EAK_CODE.CODE ," +
								" JT_EAK_CODE.ID_EAK_CODE, " +
								" NVL(JT_EAK_CODE.GEFAEHRLICH,'N') "+
						" FROM " +
								bibE2.cTO()+".JT_EAK_GRUPPE,"+
								bibE2.cTO()+".JT_EAK_BRANCHE,"+
								bibE2.cTO()+".JT_EAK_CODE "+
								" WHERE " +
								" JT_EAK_BRANCHE.ID_EAK_BRANCHE = JT_EAK_GRUPPE.ID_EAK_BRANCHE  AND"+ 
								" JT_EAK_GRUPPE.ID_EAK_GRUPPE = JT_EAK_CODE.ID_EAK_GRUPPE  AND "+
								" JT_EAK_CODE.ID_EAK_CODE="+this.get_ID_EAK_CODE_cUF_NN("");
		
		
		
		String[][] cErgebnis = bibDB.EinzelAbfrageInArray(cSQL1,"");

		if (cErgebnis == null)
		{
			cInfoText = "@@@ERROR ";
		}
		else if (cErgebnis.length == 0)
		{
			cInfoText = "";
		}
		else
		{
			String cIDSprache = bibALL.get_ID_SPRACHE_USER();
			String cUebersetzung = bibDB.EinzelAbfrage("SELECT CODE_UEBERSETZUNG FROM "+bibE2.cTO()+".JT_EAK_CODE_SP WHERE ID_EAK_CODE="+cErgebnis[0][4]+" AND ID_SPRACHE="+cIDSprache,"","","");
			
			String cGefaehrlich = "   ";
			if (cErgebnis[0][5].toUpperCase().equals("Y"))
				cGefaehrlich = "(*)";
			
			if (!bibALL.isEmpty(cUebersetzung))
				cErgebnis[0][3] = cUebersetzung;
			
			cInfoText = cErgebnis[0][0]+" "+cErgebnis[0][1]+" "+cErgebnis[0][2]+" "+cGefaehrlich+" "+cErgebnis[0][3];
		}
		
		return cInfoText;

	}

	
	
	public String get_AVV_Code_Gesamt() throws myException
	{
		String cRueck = "";
		
		RECORD_EAK_GRUPPE  	recGruppe = this.get_UP_RECORD_EAK_GRUPPE_id_eak_gruppe();
		RECORD_EAK_BRANCHE  recBranche = recGruppe.get_UP_RECORD_EAK_BRANCHE_id_eak_branche();
		
		cRueck = recBranche.get_KEY_BRANCHE_cUF_NN("")+" "+recGruppe.get_KEY_GRUPPE_cUF_NN("")+" "+this.get_KEY_CODE_cUF_NN("");
		
		if (this.is_GEFAEHRLICH_YES())
		{
			cRueck =cRueck+"(*)";
		}
		return cRueck;
	}
	

	public MyE2_Label get_Label_AVV_Code(Insets oIN) throws myException
	{
		MyE2_Label  oLabelAVV = new MyE2_Label(this.get_AVV_Code_Gesamt());
		
		GridLayoutData  oGL = new GridLayoutData();
		oGL.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
		if (oIN!=null)
		{
			oGL.setInsets(oIN);
		}

		if (this.is_GEFAEHRLICH_YES())
		{
			oGL.setBackground(new E2_ColorAlarm());
		}
		
		oLabelAVV.setLayoutData(oGL);
		
		return oLabelAVV;
	}
	
	
	
}
