package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class __RECORD_VPOS_TPA_FUHRE_ORT_EXT extends RECORD_VPOS_TPA_FUHRE_ORT
{

	public __RECORD_VPOS_TPA_FUHRE_ORT_EXT() throws myException
	{
		super();
	}

	public __RECORD_VPOS_TPA_FUHRE_ORT_EXT(long lID_Unformated, MyConnection Conn) throws myException
	{
		super(lID_Unformated, Conn);
	}

	public __RECORD_VPOS_TPA_FUHRE_ORT_EXT(long lID_Unformated) throws myException
	{
		super(lID_Unformated);
	}

	public __RECORD_VPOS_TPA_FUHRE_ORT_EXT(MyConnection Conn) throws myException
	{
		super(Conn);
	}

	public __RECORD_VPOS_TPA_FUHRE_ORT_EXT(RECORD_VPOS_TPA_FUHRE_ORT recordOrig)
	{
		super(recordOrig);
	}

	public __RECORD_VPOS_TPA_FUHRE_ORT_EXT(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public __RECORD_VPOS_TPA_FUHRE_ORT_EXT(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}

	
	//liefert HashMap mit zwei key-werten: BELEGNUMMER und PREIS
	//<BELEGNUMMER>  liefert drei werte fuer die Rechnungs-info-spalte
	//<PREIS>        liefert drei werte fuer die Rechnungs-info-spalte
	
	public HashMap<String, Vector<Component[]>>  get_v_Gutschrift_Rechnungs_Nummern_undZusaetze(int iNachkomma, boolean bEK, boolean bToolTip) throws myException
	{
		int iLagerVorzeichen = bEK?1:-1;

		RECLIST_VPOS_RG 			reclistVPOS_RG =    this.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord(
								"NVL(DELETED,'N')='N' AND ID_VPOS_RG_STORNO_VORGAENGER IS NULL AND ID_VPOS_RG_STORNO_NACHFOLGER IS NULL AND LAGER_VORZEICHEN="+iLagerVorzeichen,null,false);
		
		//bereits abgerechnete positionen summieren (gedruckte rechnungen)
		MyE2_Label[] labelArr = new MyE2_Label[3];
		Component[]  compPreis = new Component[2];

		
		if (reclistVPOS_RG.get_vKeyValues().size()==1)
		{
			RECORD_VPOS_RG recRG = reclistVPOS_RG.get(0);
			if (	recRG.is_DELETED_NO() &&
					S.isEmpty(recRG.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) && 
					S.isEmpty(recRG.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")) &&
					recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null )
			{
				
				//posnummer mit mit 0 aufgefuettert, wenn es einstellige sind
				String cPosNummer = recRG.get_POSITIONSNUMMER_cF_NN("<POS>").length()==1
				                     ?
				                         "0"+recRG.get_POSITIONSNUMMER_cF_NN("<POS>")
				                    :
				                    	recRG.get_POSITIONSNUMMER_cF_NN("<POS>");
				                         
				String cBuchungsnummer = recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_BUCHUNGSNUMMER_cUF_NN("---")+"-"+cPosNummer;
				labelArr[0]=new ownE2_Label(cBuchungsnummer,bToolTip?"Rechnungs- / Gutschriftsnummer":null);
				labelArr[1]=new ownE2_Label(myDateHelper.ChangeFormatStringToDateWithoutYear(recRG.get_AUSFUEHRUNGSDATUM_cF_NN("??.??.????")),bToolTip?"Leistungsdatum":null);
				
				//2012-03-16: anzeige der nettomenge und des resultierenden e-preis auf netto
				//ALT: labelArr[2]=new ownE2_Label(MyNumberFormater.formatDez(recRG.get_ANZAHL_bdValue(new BigDecimal(0)).longValue(),iNachkomma,true),bToolTip?"Rechnungs-/Gutschriftsmenge":null);
				labelArr[2]=new ownE2_Label(MyNumberFormater.formatDez(new __RECORD_VPOS_RG(recRG)._get_ANZAHL_NETTO_bd().longValue(),iNachkomma,true),bToolTip?"Rechnungs-/Gutschrifts-Nettomenge (Positionsmenge minus reale Abzüge)":null);
				
				//2012-03-16: anzeige der nettomenge und des resultierenden e-preis auf netto
				//ALT: compPreis[0]=new MyE2_Label(MyNumberFormater.formatDez(recRG.get_EINZELPREIS_RESULT_FW_bdValue(new BigDecimal(0)),2,true));
				compPreis[0]=new MyE2_Label(MyNumberFormater.formatDez(recRG.get_EPREIS_RESULT_NETTO_MGE_FW_bdValue(new BigDecimal(0)),2,true), new MyE2_String("Rechnungs-/Gutschrift, Effektivpreis auf Nettomenge"));
				
				if (recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
				{
					compPreis[1]=new BSK___JUMPER_TO_RechGut(recRG);	
				}
				else
				{
					compPreis[1]=new MyE2_Label("");
				}
			}
			else
			{
				labelArr[0]=new ownE2_Label("---",bToolTip?"Rechnungs- / Gutschriftsnummer":null);
				labelArr[1]=new ownE2_Label(myDateHelper.ChangeFormatStringToDateWithoutYear(recRG.get_AUSFUEHRUNGSDATUM_cF_NN("??.??.????")),bToolTip?"Leistungsdatum":null);

				//2012-03-16: anzeige der nettomenge und des resultierenden e-preis auf netto
				//ALT: labelArr[2]=new ownE2_Label(MyNumberFormater.formatDez(recRG.get_ANZAHL_bdValue(new BigDecimal(0)).longValue(),iNachkomma,true),bToolTip?"Rechnungs-/Gutschriftsmenge":null);
				labelArr[2]=new ownE2_Label(MyNumberFormater.formatDez(new __RECORD_VPOS_RG(recRG)._get_ANZAHL_NETTO_bd().longValue(),iNachkomma,true),bToolTip?"Rechnungs-/Gutschrifts-Nettomenge (Positionsmenge minus reale Abzüge)":null);
				
				compPreis[0]=new MyE2_Label("--");
				compPreis[1]=new MyE2_Label("");
			}

		}
		else
		{
			//nicht eindeutig oder nicht vorhanden
			
			labelArr[0]=new ownE2_Label("---",bToolTip?"Rechnungs- / Gutschriftsnummer":null);
			labelArr[1]=new ownE2_Label("---",bToolTip?"Leistungsdatum":null);
			labelArr[2]=new ownE2_Label("---",bToolTip?"Rechnungs-/Gutschriftsmenge":null);
			
			compPreis[0]=new MyE2_Label("--");
			compPreis[1]=new MyE2_Label("");

			
		}
		
		//jetzt die labels in die Arrays eintragen
		Vector<Component[]> vRueck1 = new Vector<Component[]>();
		vRueck1.add(labelArr);

		Vector<Component[]> vRueck2 = new Vector<Component[]>();
		vRueck2.add(compPreis);

		HashMap<String, Vector<Component[]>> hmRueck = new HashMap<String, Vector<Component[]>>();
		hmRueck.put("BELEGNUMMER", vRueck1);
		hmRueck.put("PREIS", vRueck2);
		
		return hmRueck;
	}

	
	
	//liefert zwei werte fuer die Fuhre-planmenge-spalte
	public Vector<Component[]>  get_v_Fuhrenummer_undPlanmenge_und_datum(int iNachkomma, boolean bEK, boolean bToolTip) throws myException
	{
		MyE2_Grid oGridHelp = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		oGridHelp.add(new MyE2_Label(this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_BUCHUNGSNR_FUHRE_cUF_NN("ID:"+this.get_ID_VPOS_TPA_FUHRE_cF_NN(""))+" *",
									 new MyE2_String("ID-Fuhre oder Buchungsnummer Fuhre:  Dieser Eintrag kommt von einem Zusatzort in der betreffenden Fuhre ")
										),E2_INSETS.I_0_0_0_0);
		oGridHelp.add(new BSK__JUMPER_TO_Fuhre(this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre()),E2_INSETS.I_0_0_0_0);
		
		
		
		//bereits abgerechnete positionen summieren (gedruckte rechnungen)
		Component[] labelArr = new Component[3];

		//2011-02-03: auch ausfuehrungsdatum anzeigen
		//labelArr[0]=new MyE2_Label(this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_BUCHUNGSNR_FUHRE_cUF_NN("ID:"+this.get_ID_VPOS_TPA_FUHRE_cF_NN("")));
		
		labelArr[0]=oGridHelp;
		labelArr[1]=new ownE2_Label(myDateHelper.ChangeFormatStringToDateWithoutYear(this.get_DATUM_LADE_ABLADE_cF_NN("??.??.????")),bToolTip?"Lade-/Ablade-Datum der Fuhre":null);	
		labelArr[2]=bEK? new ownE2_Label(  	MyNumberFormater.formatDez(this.get_ANTEIL_LADEMENGE_bdValue(this.get_ANTEIL_ABLADEMENGE_bdValue(this.get_ANTEIL_PLANMENGE_bdValue(BigDecimal.ZERO))),iNachkomma,true),bToolTip?"Lademenge oder Planmenge Ladeseite":null)
		                       :
		                 new ownE2_Label(	MyNumberFormater.formatDez(this.get_ANTEIL_ABLADEMENGE_bdValue(this.get_ANTEIL_LADEMENGE_bdValue(this.get_ANTEIL_PLANMENGE_bdValue(BigDecimal.ZERO))),iNachkomma,true),bToolTip?"Ablademenge oder Planmenge Abladeseite":null);
		
		
		//jetzt die labels in die Arrays eintragen
		Vector<Component[]> vRueck = new Vector<Component[]>();
		vRueck.add(labelArr);
		return vRueck;
	}

	
	//liefert einen wert fuer die Fuhre-lade/ablade-menge
	public Vector<Component[]>  get_v_RealMenge(int iNachkomma, boolean bToolTip) throws myException
	{
		//bereits abgerechnete positionen summieren (gedruckte rechnungen)
		MyE2_Label[] labelArr = new MyE2_Label[1];
		labelArr[0]=this.get_DEF_QUELLE_ZIEL_cUF().equals("EK")?
				     new ownE2_Label(MyNumberFormater.formatDez(this.get_ANTEIL_LADEMENGE_bdValue(BigDecimal.ZERO),iNachkomma,true),bToolTip?"Reale Lademenge":null)
				     :
			    	 new ownE2_Label(MyNumberFormater.formatDez(this.get_ANTEIL_ABLADEMENGE_bdValue(BigDecimal.ZERO),iNachkomma,true),bToolTip?"Reale Ablademenge":null);
		
		//jetzt die labels in die Arrays eintragen
		Vector<Component[]> vRueck = new Vector<Component[]>();
		vRueck.add(labelArr);
		return vRueck;
	}

	//liefert einen wert fuer die Fuhre-lade/ablade-abzuege
	public Vector<Component[]>  get_v_Abzuege(int iNachkomma, boolean bToolTip) throws myException
	{
		//bereits abgerechnete positionen summieren (gedruckte rechnungen)
		MyE2_Label[] labelArr = new MyE2_Label[1];
		labelArr[0]=new ownE2_Label(MyNumberFormater.formatDez(this.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO),iNachkomma,true),bToolTip?"Abzüge":null);
		
		//jetzt die labels in die Arrays eintragen
		Vector<Component[]> vRueck = new Vector<Component[]>();
		vRueck.add(labelArr);
		return vRueck;
	}

	
	//2011-12-02: postennummer auch in der kontraktliste
	//liefert einen wert fuer die Fuhre-lade/ablade-menge
	public Vector<Component[]>  get_v_Postennummer(boolean bToolTip) throws myException
	{
		//bereits abgerechnete positionen summieren (gedruckte rechnungen)
		MyE2_Label[] labelArr = new MyE2_Label[1];
		labelArr[0]=new ownE2_Label(this.get_POSTENNUMMER_cUF_NN("-"),bToolTip?"Postennummer Ladeseite":null);
		
		//jetzt die labels in die Arrays eintragen
		Vector<Component[]> vRueck = new Vector<Component[]>();
		vRueck.add(labelArr);
		return vRueck;
	}

	
	private class ownE2_Label extends MyE2_Label
	{
		public ownE2_Label(String cText, String cToolTipToTranslate)
		{
			super(cText);
			if ( cToolTipToTranslate!=null)
			{
				this.setToolTipText(new MyE2_String(cToolTipToTranslate).CTrans());
			}
		}
	}
	

	
	
}
