package panter.gmbh.indep.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

import panter.gmbh.indep.myTempFile;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;


/**
 * Klasse zum Eindrucken von Wasserzeichen in Form von Text oder Bild-Dateien.
 * 
 * Benutzung:
 * 1. Einfaches Eindrucken: Eindrucken vom Text "A R C H I V" in ein PDF, 2 mal mit einer Rotation von 20grad  
 * myTempFile t = new PDF_Overlay_Factory().generatePDFwithOverlayText(<PFAD_NAME_PDF_ORIGINAL> ,"A R C H I V" , 2, 20f); 
 * 
 * 2. Einfaches Eindrucken eines Bildes: Das Bild wird mittig auf die Seite gedruckt 
 * myTempFile t = pDFOverlay.generatePDFwithOverlayImage(<PFAD_NAME_PDF_ORIGINAL>, <PFAD_NAME_DES_BILDES>);
 * myTempFile t = pDFOverlay.generatePDFwithOverlayImage(t.getFileName(), File.separator + bibALL.get_WEBROOTPATH()+File.separator + "bilder" + File.separator + "eindruck_kopie.png");
 * 
 * 3. Einfaches Eindrucken eines Textes an einer fixen Position:Text = "A R C H I V" Position x=20, y=40, fontsize = 12, 0grad Rotation 
 * myTempFile t = new PDF_Overlay_Factory().generatePDFwithOverlayTextOnAbsolutePosition(recMedien.get__cCompletePathAndFileName(), "A R C H I V", 20f, 40f,12f, 0f);
 * 
 * 4. Explizites setzen der einzelnen Einstellungen:
 * 		PDF_Overlay_Factory pDFOverlay = new PDF_Overlay_Factory();
 * 		pDFOverlay.setImprintFontSize(45f);
 * 		pDFOverlay.setImprintColor(255, 0 ,0);
 * 		pDFOverlay.setImprintRotation(0f);
 * 		pDFOverlay.setImprintText("Zeile 1", "Zeile 2", "Zeile 3");
 * 		pDFOverlay.setImprintFontName(BaseFont.HELVETICA_BOLD);
 * 		myTempFile t = pDFOverlay.generatePDFwithOverlayText(recMedien.get__cCompletePathAndFileName());
 * 
 *  
 * 
 * @author manfred
 * @date   15.02.2013
 */



public class PDF_Overlay_Factory {
	
  // Stampdateiname mit vollem Pfad
  private String m_sStampfileName = null;
  
  // Farbwerte RGB fuer den Eindruck
  private int    m_ColorRed   = 200;
  private int    m_ColorGreen = 200;
  private int    m_ColorBlue  = 200;
  
  private float    m_FontSize = 100f;
  
  // Rotation der Schrift
  private float    m_RotationDegree   = 0f;
  
  // Anzahl der Aufdrucke auf der Seite (1, 2 oder 3) 
  private int    m_ImprintsOnPage = 1; 
  
  // Mögliche Texte oben, mittig und unten ,
  // sind normalerweise immer gleich
  private String[] m_ImprintText = {"-","-","-"};

  // IOWAGI-definierter Basefont-Name
  private String   m_ImprintFontName = BaseFont.TIMES_BOLD;
    
  // HashSet für die definierbaren Fontnamen
  private HashSet<String> m_FontNames = new HashSet<String>();

  // wenn true, wird der Text/Bild an der angegebenen absoluten Position gesetzt.
  private boolean   m_useAbsolutePosition = false;
  private float     m_absX = 0f;
  private float     m_absY = 0f;
  
  // das Alignement des Imprints
  private int       m_ImprintAlignement = Image.ALIGN_CENTER;
  private HashSet<Integer> m_Alignements = new HashSet<Integer>();
  
  private PdfReader m_pdfReader;
  private PdfStamper m_pdfStamper;
  
  
  
  /**
   * Default Constructor
   * Initialisiert die Schriftnamen
   * @author manfred
   * @date   14.02.2013
   */
  public PDF_Overlay_Factory() {
	  
	// Fontnamen intialisieren  
    m_FontNames.add( BaseFont.COURIER); 
	m_FontNames.add( BaseFont.COURIER_BOLD); 
	m_FontNames.add( BaseFont.COURIER_OBLIQUE);
    m_FontNames.add( BaseFont.COURIER_BOLDOBLIQUE);
    m_FontNames.add( BaseFont.HELVETICA);
    m_FontNames.add( BaseFont.HELVETICA_BOLD);
    m_FontNames.add( BaseFont.HELVETICA_OBLIQUE);
    m_FontNames.add( BaseFont.HELVETICA_BOLDOBLIQUE);
    m_FontNames.add( BaseFont.SYMBOL);
    m_FontNames.add( BaseFont.TIMES_ROMAN);
    m_FontNames.add( BaseFont.TIMES_BOLD);
    m_FontNames.add( BaseFont.TIMES_ITALIC);
    m_FontNames.add( BaseFont.TIMES_BOLDITALIC);
    m_FontNames.add( BaseFont.ZAPFDINGBATS);
	
    m_Alignements.add(PdfContentByte.ALIGN_CENTER);
    m_Alignements.add(PdfContentByte.ALIGN_LEFT);
    m_Alignements.add(PdfContentByte.ALIGN_RIGHT);
    
    
  }
  
  
  /**
   * Setzt die Rotation des Imprints in grad ( 0 <= rotation <= 360)
   * Default: 0f
   * 
   * @author manfred
   * @date   14.02.2013
   * @param rotation
   */
  public void setImprintRotation(float rotation){
	  m_RotationDegree = rotation < 0f ? 0f : (rotation > 360f ? 0 : rotation );
  }
  
  /**
   * Setzt die Absolute position des Imprints
   * Wenn diese Methode aufgerufen wurde, wird nur noch 1 Imprint an der vorgegebenen Stelle angedruckt
   * Das Koordinatensystem beginnt links oben (0,0)
   * 
   * @author manfred
   * @date   15.02.2013
   * @param x
   * @param y
   */
  public void setImprintAbsolutePosition(float x, float y){
	 m_useAbsolutePosition = true;
	 m_absX = x<0 ? 0 : x;
	 m_absY = y<0 ? 0 : y;
  }
  

  /**
   * Setzt die Ausrichtung des einzudruckenden Objekts
   * Links/Mitte/Rechts
   *  
   *  PdfContentByte.ALIGN_LEFT
   *  PdfContentByte.ALIGN_CENTER
   *  PdfContentByte.ALIGN_RIGHT
   *  
   * @author manfred
   * @date   15.02.2013
   * @param Alignement
   */
  public void setImprintAlignement ( int Alignement ){
	  if (m_Alignements.contains(Alignement)) {
		  m_ImprintAlignement = Alignement;
	  }
  }

  
  /**
   * Setzt den Namen des Fonts 
   * Fontname muss ein Wert aus der Klasse "BaseFont" sein 
   * z.B. BaseFont.COURIER
   * COURIER_BOLD   
   * COURIER_OBLIQUE   
   * COURIER_BOLDOBLIQUE   
   * HELVETICA   
   * HELVETICA_BOLD   
   * HELVETICA_OBLIQUE   
   * HELVETICA_BOLDOBLIQUE   
   * SYMBOL   
   * TIMES_ROMAN   
   * TIMES_BOLD   (Default)
   * TIMES_ITALIC   
   * TIMES_BOLDITALIC   
   * ZAPFDINGBATS   
   * 
   * 
   * @author manfred
   * @date   14.02.2013
   * @param FontName
   */
  public void setImprintFontName(String FontName){
	  if (m_FontNames.contains(FontName)){
		  m_ImprintFontName = FontName;
	  }
  }
  
  
  /**
   * Setzt die Anzahl der Imprints auf der Seite
   * egal ob Bild oder Text
   * @author manfred
   * @date   14.02.2013
   * @param ImprintsOnPage
   */
  public void setNumOfImprintsOnPage(int ImprintsOnPage){
	  m_ImprintsOnPage = ImprintsOnPage < 1 ? 1 : (ImprintsOnPage > 3 ? 3 : ImprintsOnPage);
  }
  
  /**
   * Setzt die Fontgröße  
   * muss größer als 1f sein
   * Default: 100f
   * 
   * @author manfred
   * @date   14.02.2013
   * @param FontSize
   */
  public void setImprintFontSize(float FontSize){
	  m_FontSize = FontSize < 1f ? 1f : FontSize ;
  }
  
  
  /**
   * Setzen Farbe des Eindrucks
   * @author manfred
   * @date   14.02.2013
   * @param Red
   * @param Green
   * @param Blue
   */
  public void setImprintColor(int Red, int Green, int Blue){
	  // Pruefen und korrigieren des Wertebereichs
	  m_ColorRed = Red < 0 ? 0 : (Red > 255 ? 255 : Red);
	  m_ColorGreen = Green < 0 ? 0 : (Green > 255 ? 255 : Green);
	  m_ColorBlue = Blue < 0 ? 0 : (Blue > 255 ? 255 : Blue);
  }
  
  
  
  /**
   * Setzt den Text des Imprints
   * Standard-Anzeige ist 1 Imprint Zentral auf der Seite
   * @author manfred
   * @date   14.02.2013
   * @param sText
   */
  public void setImprintText(String sText){
	  m_ImprintText[0] = sText;
	  m_ImprintText[1] = sText;
	  m_ImprintText[2] = sText;
  }

  
  
  /**
   * Setzt den Text des Imprints und die Anzahl der Imprints auf der Seite
   * falls mehr als 1 Eindruck zentriert gedruckt werden soll 
   * Möglich sind: 
   * 	1 Zentriert
   * 	2 Oben und unten
   * 	3 Oben, Mittig und Unten
   * 
   * @author manfred
   * @date   14.02.2013
   * @param sText
   * @param ImprintsOnPage
   */
  public void setImprintText(String sText, int ImprintsOnPage){
	  m_ImprintsOnPage = ImprintsOnPage < 1 ? 1 : (ImprintsOnPage > 3 ? 3 : ImprintsOnPage);
	  m_ImprintText[0] = sText;
	  m_ImprintText[1] = sText;
	  m_ImprintText[2] = sText;

  }
  
  
    
  /**
   * Setzt den Text des Imprints, wenn 2 Zeilig (oben und unten) 
   * und beide Zeilen sollen verschiedene Texte beinhalten 
   * Setzt automatisch die Anzahl der Imprints
   * @author manfred
   * @date   14.02.2013
   * @param sTextTop
   * @param sTextBottom
   */
   public void setImprintText(String sTextTop,  String sTextBottom ){
	  m_ImprintsOnPage = 2;

	  m_ImprintText[0] = sTextTop;
	  m_ImprintText[1] = sTextBottom;
	  m_ImprintText[2] = "";

  }

   
  /**
   * 
   * Setzt den Text des Imprints, wenn 3 Zeilig (oben, mitte und unten) 
   * und alle 3 Zeilen sollen verschiedenen
   * Setzt automatisch die Anzahl der Imprints
   * Text beinhalten
   * @author manfred
   * @date   14.02.2013
   * @param sTextTop
   * @param sTextMiddle
   * @param sTextBottom
   */
   public void setImprintText(String sTextTop, String sTextMiddle, String sTextBottom ){
	  m_ImprintsOnPage = 3;
	  m_ImprintText[0] = sTextTop;
	  m_ImprintText[1] = sTextMiddle;
	  m_ImprintText[2] = sTextBottom;
  }
  
   
   
   /**
    * Falls ein Bild (jpg, png) als Imprint gedruckt werden soll, muss man hier den Dateinamen
    * mit komplettem absolutem Pfad angeben
    * 
    * @author manfred
    * @date   14.02.2013
    * @param PathToImprintFile
    */
   public void setImprintFile(String PathToImprintFile){
	   m_sStampfileName = PathToImprintFile;
   }
   
  


  /**
   * Einfache generierung mit einem Text in vorgegebener Farbe
   * Der Text wird in Grau eingedruckt
   * 
   * @author manfred
   * @date   14.02.2013
   * @param PDFFilename
   * @param ImprintText
   * @param ImprintsOnPage
   * @param rotation
   * @return
   */
  public myTempFile generatePDFwithOverlayText(String PDFFilename, String ImprintText, int ImprintsOnPage, float rotation) {
	 	  
	  // Text Setzen
	  this.setImprintText(ImprintText);
	  
	  // Anzahl der Imprints
	  this.setNumOfImprintsOnPage(ImprintsOnPage);
	  
	  // rotation
	  this.setImprintRotation(rotation);

	  // PDF generieren
	  return generatePDFwithOverlayText(PDFFilename);
  }

  
  
  /**
   * Einfache Generierung mit einem Text an absoluter Position
   * Der Text wird in Grau eingedruckt
   * @author manfred
   * @date   15.02.2013
   * @param PDFFilename
   * @param ImprintText
   * @param horizontalPos
   * @param vertikalPos
   * @param rotation
   * @return
   */
  public myTempFile generatePDFwithOverlayTextOnAbsolutePosition(String PDFFilename, String ImprintText, float horizontalPos, float vertikalPos, float fontsize ,float rotation) {
 	  
	  // Text Setzen
	  this.setImprintText(ImprintText);
	  
	  // Position setzen
	  this.setImprintAbsolutePosition(horizontalPos, vertikalPos);
	  this.setImprintAlignement(PdfContentByte.ALIGN_LEFT);
	  
	  // Fontsize
	  this.setImprintFontSize(fontsize);
	  
	  // Anzahl der Imprints
	  this.setNumOfImprintsOnPage(1);
	  
	  // rotation
	  this.setImprintRotation(rotation);

	  // PDF generieren
	  return generatePDFwithOverlayText(PDFFilename);
  }
  
  
  
  
  /**
   * Generierung des PDF mit explizit gesetzten Vorgaben 
   * die mit den Memberfunktionen gesetzt wurden
   * @author manfred
   * @date   14.02.2013
   * @param PDFFilename
   * @return
   */
  public myTempFile generatePDFwithOverlayText(String PDFFilename) {
	  myTempFile oFileResult = null;
	  
	
	  // den Reader initialisieren
	  try {
		  
		  // falls absolute Positionierung, nur ein Imprint möglich
		  if (m_useAbsolutePosition){
			  this.setNumOfImprintsOnPage(1);
		  }
		  
		  // original-Dateiname ermitteln
		  String fileName = new File(PDFFilename).getName();
		  fileName = fileName.substring(0, fileName.indexOf("."));
		  
		  oFileResult = new myTempFile(fileName, ".pdf", true );
		  oFileResult.set_bDeleteAtFinalize(true);
		  
		  m_pdfReader = new PdfReader(PDFFilename);
		  m_pdfStamper = new PdfStamper(m_pdfReader, new FileOutputStream(oFileResult.getFileName()));
		  
		  // Alle Seiten bearbeiten
		  for(int i=1; i<= m_pdfReader.getNumberOfPages(); i++){
		
			  for (int j=1; j <= m_ImprintsOnPage; j++ ){
				  setImprintText(i,j);
			  }
		  }
		  
	       m_pdfStamper.close();
	          
	      oFileResult.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	  return oFileResult;
  }
  
  
  
  
  
  /**
   * Generierung des PDF mit einem Bild als Imprint
   * Die Position muss vorher gesetzt sein
   * @author manfred
   * @date   14.02.2013
   * @param PDFFilename
   * @return
   */
  public myTempFile generatePDFwithOverlayImage(String PDFFilename, String ImageFilename) {
	  myTempFile oFileResult = null;
	  
	  // übernehmen der Bilddatei
	  m_sStampfileName = ImageFilename;
	
	  
	  // den Reader initialisieren
	  try {
		  // falls absolute Positionierung, nur ein Imprint möglich, der immer links ausgerichtet ist.
		  if (m_useAbsolutePosition){
			  this.setNumOfImprintsOnPage(1);
		  }
		  
		  // original-Dateiname ermitteln
		  String fileName = new File(PDFFilename).getName();
		  int idx = fileName.indexOf(".");
		  if (idx > 0){
			  fileName = fileName.substring(0,idx );
		  }
		 
		  // das Bild lesen, das eingedruckt weden soll
  		  Image image = Image.getInstance(m_sStampfileName);

		  
		  oFileResult = new myTempFile(fileName + "_1", "pdf", true );
		  oFileResult.set_bDeleteAtFinalize(true);
		 
		  m_pdfReader = new PdfReader(PDFFilename);
		  m_pdfStamper = new PdfStamper(m_pdfReader, new FileOutputStream(oFileResult.getFileName()));
		  
		  // Alle Seiten bearbeiten
		  for(int i=1; i<= m_pdfReader.getNumberOfPages(); i++){
		
			  for (int j=1; j <= m_ImprintsOnPage; j++ ){
				  setImprintImage(i,j,image);
			  }
		  }
		  
	       m_pdfStamper.close();
	          
	      oFileResult.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	  return oFileResult;
  }
  
  
  
  
  /**
   * Setzt den einzelnen Imprint auf der Seite.
   * Textinhalt und Position werden aus den parametern pos ermittelt.
   * 
   * @author manfred
   * @date   14.02.2013
   * @param page
   * @param pos
   * @throws DocumentException
   * @throws IOException
   */
  private void setImprintImage(int page, int pos, Image image) throws DocumentException, IOException{
	  
	// den Content der Seite holen
	PdfContentByte content = m_pdfStamper.getUnderContent(page);
	float fcorrHeight = image.getHeight() / 2;
	float fcorrWidth  = image.getWidth() / 2;
	int align = Image.ALIGN_CENTER;
	
	switch (m_ImprintAlignement) {
		case PdfContentByte.ALIGN_CENTER:
			fcorrHeight = image.getHeight() / 2;
			fcorrWidth  = image.getWidth() / 2;
			break;
		case PdfContentByte.ALIGN_LEFT:
			fcorrHeight = image.getHeight() / 2;
			fcorrWidth  = 0f;
		break;
		case PdfContentByte.ALIGN_RIGHT:
			fcorrHeight = image.getHeight() / 2;
			fcorrWidth  = image.getWidth();
			break;

		default:
			align = Image.ALIGN_CENTER;
		break;
	}

	// Positionierung setzen
	image.setAbsolutePosition(getHorizontalPos(content)-fcorrWidth, getVerticalPos(content, pos)-fcorrHeight);
	
	// Bild einfuegen
	content.addImage(image);
  }

  
  
  /**
   * Setzt den einzelnen Imprint auf der Seite.
   * Textinhalt und Position werden aus den parametern pos ermittelt.
   * 
   * @author manfred
   * @date   14.02.2013
   * @param page
   * @param pos
   * @throws DocumentException
   * @throws IOException
   */
  private void setImprintText(int page, int pos) throws DocumentException, IOException{
	  
	  // den Content der Seite holen
	  PdfContentByte content = m_pdfStamper.getUnderContent(page);
	  
	  // font
	  BaseFont bf1 = BaseFont.createFont(m_ImprintFontName, BaseFont.WINANSI, BaseFont.EMBEDDED);
      
	  
	  content.beginText();
     
      // fontsize
      content.setFontAndSize(bf1, m_FontSize);
      
      // farbe
      content.setRGBColorFill(m_ColorRed, m_ColorGreen, m_ColorBlue);
      
      // text, aktuell immer zentriert auf den gegebenen Punkt
      content.showTextAligned(
    		  m_ImprintAlignement,
    		  getImprintText(pos),
    		  getHorizontalPos(content),
    		  getVerticalPos(content, pos),
    		  m_RotationDegree );
            
      content.endText();
      
  }
  
  
  /**
   * Gibt den Text des Imprints der Position zurück
   * @author manfred
   * @date   14.02.2013
   * @param pos
   * @return
   */
  private String getImprintText(int pos){
	  return m_ImprintText[m_ImprintsOnPage - pos];
  }
  
  
  /**
   * Gibt die horizontale Position des Imprints zurück,
   * ist momentan immer mittig
   * 
   * @author manfred
   * @date   14.02.2013
   * @param content
   * @return
   */
  private float getHorizontalPos(PdfContentByte content){
	  float fPos = 0f;
	  
	  float width = content.getPdfDocument().getPageSize().getWidth();
	  
	  if (m_useAbsolutePosition){
		  // Koordinatensystem umdrehen in der Horizontalen nicht nötig.
		  fPos = m_absX;
	  } else {
		  fPos = width / 2; 
	  }
	  return fPos;
  }
  
  
  /**
   * gibt die Vertikale Position des Imprints zurück
   * pos kann 1,2 oder 3 sein.
   * 
   * Die tatsächliche Position ist abhängig davon, ob 1, 2 oder 3 Imprints gedruckt werden sollen
   * 
   * @author manfred
   * @date   14.02.2013
   * @param content
   * @param pos
   * @return
   */
  private float getVerticalPos(PdfContentByte content, int pos){
	  float fRet = 0f;
	  float height = content.getPdfDocument().getPageSize().getHeight();

	  if (m_useAbsolutePosition){
		  // Koordinatensystem umdrehen
		  fRet = height - m_absY;
	  } else {
		  float fSection = height / new Float(m_ImprintsOnPage + 1).floatValue() ;
		  fRet = fSection * (pos);
	  }
      return fRet;
  }


  /**
   * 2015-03-23: fontnames  
   * @return HashSet of fontnames 
   */
  public HashSet<String> get_FontNames() {
	  return m_FontNames;
  }
  
  
  
//  public myTempFile generatePDFwithOverlay(String PDFFilename, String StampFilename){
//	  myTempFile oFileResult = null;
//	  
//	  try {
//		  // die Ergebnisdatei heisst genau wie die Ursprungsdatei
//		  oFileResult = new myTempFile("test_COPY", "pdf", true );
//	
//		  m_pdfReader = new PdfReader(PDFFilename);
//
//          m_pdfStamper = new PdfStamper(m_pdfReader, new FileOutputStream(oFileResult.getFileName()));
//
//          String sTest = File.separator + bibALL.get_WEBROOTPATH()+File.separator + "bilder" + File.separator + "eindruck_kopie.png";
//          Image image = Image.getInstance(sTest);
//
//          for(int i=1; i<= m_pdfReader.getNumberOfPages(); i++){
//
//              //put content under
//              PdfContentByte content = m_pdfStamper.getUnderContent(i);
//              
//              float width = content.getPdfDocument().getPageSize().getWidth() / 2;
//              float height = content.getPdfDocument().getPageSize().getHeight() / 2;
////              
////              image.setAbsolutePosition(50f, 550f);
////              content.addImage(image);
////
////              //put content over
////              content = pdfStamper.getOverContent(i);
////              image.setAbsolutePosition(50f, 250f);
////              content.addImage(image);
//
//
//              
//              // Text als Stamp
//              content = m_pdfStamper.getUnderContent(i);
//              BaseFont bf1 = BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);
//             
//              content.beginText();
//              content.setFontAndSize(bf1, 100);
//              content.setColorStroke(Color.blue);
//              content.setColorFill(Color.red);
//              content.showTextAligned(PdfContentByte.ALIGN_CENTER,"Archiv",width,height,0);
//              content.endText();
// 
//              // Text als Stam
//              bf1 = BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);
//              
//              content.beginText();
//              content.setFontAndSize(bf1, 100);
//              content.setRGBColorFill(200, 100, 50);
//              content.showTextAligned(PdfContentByte.ALIGN_CENTER,"KOPIE",width,50,0);
//              content.endText();
//             
//              
//              //Text over the existing page
////              BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
////                      BaseFont.WINANSI, BaseFont.EMBEDDED);
////              content.beginText();
////              content.setFontAndSize(bf, 18);
////              content.showTextAligned(PdfContentByte.ALIGN_LEFT,"Page No: " + i,430,15,0);
////              content.endText();
//
//          }
//
//          m_pdfStamper.close();
//          
//          oFileResult.close();
//          
//          
//      } catch (IOException e) {
//          e.printStackTrace();
//      } catch (DocumentException e) {
//          e.printStackTrace();
//      }
//      
//      return oFileResult;
//  }
  
}
