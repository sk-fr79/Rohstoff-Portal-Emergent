package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.math.BigDecimal;
import java.util.Iterator;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_TEMP_IMP_SORTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TEMP_IMP_SORTEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class __actionAgentImportSortenBez extends XX_ActionAgent
{
	
	private MyE2_Grid oGridServerAnzeige = new MyE2_Grid(1);




	private int       iCountAlleGeprueften = 				0;
	private int       iCountKeineANR1_oder_ANR2 = 			0;
	private int       iCountLieferantNichtGefunden = 		0;
	private int       iCountLieferantInaktiv = 		0;
	private int       iCountBezeichnerSchonVorhanden = 		0;
	private int       iCountErfolg = 						0;
	
	
	
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException 
	{
		
		new E2_ServerPushMessageContainer_STD(new Extent(400),new Extent(400),new MyE2_String("Artikelimport"),false,true,6000,this.oGridServerAnzeige,E2_INSETS.I_5_5_5_5) 
		{
			@Override
			public void Run_Loop() throws myException 
			{
				VectorSingle		vTextFehler = 		new VectorSingle();
				int 			iAnzahl = 			0;
				
				iCountAlleGeprueften = 				0;
				iCountKeineANR1_oder_ANR2 = 		0;
				iCountLieferantNichtGefunden = 		0;
				iCountLieferantInaktiv = 		0;
				iCountBezeichnerSchonVorhanden = 	0;
				iCountErfolg =						0;
				
				
				MyE2_MessageVector  oMV = new MyE2_MessageVector();
				
				RECLIST_TEMP_IMP_SORTEN reclistSorten = new RECLIST_TEMP_IMP_SORTEN("SELECT * FROM JT_TEMP_IMP_SORTEN");
	
				iAnzahl = reclistSorten.get_vKeyValues().size();
				
				Iterator<RECORD_TEMP_IMP_SORTEN>  iterator = reclistSorten.values().iterator();
				
				while (iterator.hasNext())
				{
					iCountAlleGeprueften++;
					
					RECORD_TEMP_IMP_SORTEN recSorteBez = iterator.next();
					
					RECLIST_ADRESSE  reclistAdresse = new RECLIST_ADRESSE("SELECT * FROM JT_ADRESSE WHERE LIEF_NR="+bibALL.MakeSql(recSorteBez.get_ROHSTOFF_LIEFNR_cUF_NN("999999999999")));
					
					if (reclistAdresse.get_vKeyValues().size()==0)
					{
						__actionAgentImportSortenBez.this.iCountLieferantNichtGefunden++;
						vTextFehler.add("Lieferant nicht gefunden: LIEF_NR: "+recSorteBez.get_ROHSTOFF_LIEFNR_cUF_NN("999999999999"));
					}
					else
					{
						for (int i=0;i<reclistAdresse.get_vKeyValues().size();i++)
						{
							RECORD_ADRESSE  recAdresse = reclistAdresse.get(i);
							
//							if (recAdresse.is_AKTIV_NO())
//							{
//								__actionAgentImportSortenBez.this.iCountLieferantInaktiv++;
//								vTextFehler.add("Lieferant ist inaktiv gekennzeichnet: LIEF_NR: "+recSorteBez.get_ROHSTOFF_LIEFNR_cUF_NN("999999999999"));
//							}
//							else
//							{
								if (recSorteBez.get_ANR1_cUF_NN("00-0000").equals("00-0000") || recSorteBez.get_ANR1_cUF_NN("xx-xxxx").equals("xx-xxxx") || recSorteBez.get_ANR2_cUF_NN("xx").equals("xx"))
								{
									__actionAgentImportSortenBez.this.iCountKeineANR1_oder_ANR2++;
								}
								else
								{
									RECLIST_ARTIKEL  reclistArtikel = new RECLIST_ARTIKEL("select * from jt_artikel where ANR1="+bibALL.MakeSql(recSorteBez.get_ANR1_cUF_NN("xx-xxxx")));
									
									if (reclistArtikel.get_vKeyValues().size()==1)
									{
										RECORD_ARTIKEL  recArtikel = reclistArtikel.get(0);
										
										RECLIST_ARTIKEL_BEZ  reclistArtbez = recArtikel.get_DOWN_RECORD_LIST_ARTIKEL_BEZ_id_artikel("ANR2="+bibALL.MakeSql(recSorteBez.get_ANR2_cUF_NN("xx")),null,true);
										
										if (reclistArtbez.get_vKeyValues().size()!=1)
										{
											__actionAgentImportSortenBez.this.iCountKeineANR1_oder_ANR2++;
											
											vTextFehler.add("ANR2 ist nicht vorhanden: LIEF_NR: "+recSorteBez.get_ROHSTOFF_LIEFNR_cUF_NN("999999999999")+"/  ANR1-ANR2:  "
																											   +recSorteBez.get_ANR1_cUF_NN("xx-xxxx")+"-"
																											   +recSorteBez.get_ANR2_cUF_NN("xx"));
										}
										else
										{
											//pruefen, ob die sortenbezeichner schon vorhanden sind
											RECORD_ARTIKEL_BEZ  recordArtBez = reclistArtbez.get(0);
											
											if (recAdresse.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse("ID_ARTIKEL_BEZ="+recordArtBez.get_ID_ARTIKEL_BEZ_cUF(), null, true).get_vKeyValues().size()==0)
											{
												RECORDNEW_ARTIKELBEZ_LIEF  newArtBezLief = new RECORDNEW_ARTIKELBEZ_LIEF();
												
												newArtBezLief.set_NEW_VALUE_ID_ADRESSE(recAdresse.get_ID_ADRESSE_bdValue(new BigDecimal(0)));
												newArtBezLief.set_NEW_VALUE_ID_ARTIKEL_BEZ(recordArtBez.get_ID_ARTIKEL_BEZ_bdValue(new BigDecimal(0)));
												newArtBezLief.set_NEW_VALUE_ID_EAK_CODE(recArtikel.get_ID_EAK_CODE_cUF());
												newArtBezLief.set_NEW_VALUE_ARTBEZ_TYP("EK");
												newArtBezLief.do_WRITE_NEW_ARTIKELBEZ_LIEF(oMV);
												
												__actionAgentImportSortenBez.this.iCountErfolg++;
											}
											else
											{
												__actionAgentImportSortenBez.this.iCountBezeichnerSchonVorhanden++;
											}
										}
									}
									else
									{
										__actionAgentImportSortenBez.this.iCountKeineANR1_oder_ANR2++;
									}
								}
//							}
						}
					}
					
					if (iCountAlleGeprueften%100==0 || iCountAlleGeprueften==iAnzahl)
					{
						__actionAgentImportSortenBez.this.oGridServerAnzeige.removeAll();
						
						__actionAgentImportSortenBez.this.oGridServerAnzeige.add(new MyE2_Label("... "+iCountAlleGeprueften+" von "+iAnzahl));
						__actionAgentImportSortenBez.this.oGridServerAnzeige.add(new MyE2_Label("... "));
						__actionAgentImportSortenBez.this.oGridServerAnzeige.add(new MyE2_Label("Erfolgreich angefügt: "+iCountErfolg));
						__actionAgentImportSortenBez.this.oGridServerAnzeige.add(new MyE2_Label("Importsatz ist ohne ANR1/ANR2: "+iCountKeineANR1_oder_ANR2));
						__actionAgentImportSortenBez.this.oGridServerAnzeige.add(new MyE2_Label("Lieferant nicht gefunden: "+iCountLieferantNichtGefunden));
						__actionAgentImportSortenBez.this.oGridServerAnzeige.add(new MyE2_Label("Lieferant ist nicht aktiv: "+iCountLieferantInaktiv));
						__actionAgentImportSortenBez.this.oGridServerAnzeige.add(new MyE2_Label("Datensatz schon vorhanden: "+iCountBezeichnerSchonVorhanden));
					}

					if (iCountAlleGeprueften==iAnzahl)
					{
						String cTextAString = "";
						for (String tex: vTextFehler)
						{
							cTextAString=cTextAString+"\n"+tex;
						}
						
						MyE2_TextArea  oText = new MyE2_TextArea(cTextAString, 500, -1, 30);
						__actionAgentImportSortenBez.this.oGridServerAnzeige.add(oText);
					}
					
				}
			}
			@Override
			public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
			{
			}

		};
	}

	
	
	public int getiCountAlleGeprueften() {
		return iCountAlleGeprueften;
	}
	
	
	
	
	public int getiCountKeineANR1_oder_ANR2() {
		return iCountKeineANR1_oder_ANR2;
	}
	
	
	
	
	public int getiCountLieferantNichtGefunden() {
		return iCountLieferantNichtGefunden;
	}
	
	
	
	
	
	
	public int getiCountBezeichnerSchonVorhanden() {
		return iCountBezeichnerSchonVorhanden;
	}
}
