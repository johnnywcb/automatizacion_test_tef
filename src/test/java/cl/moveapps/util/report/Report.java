package cl.moveapps.util.report;

import cl.moveapps.exception.AppConfigException;
import cl.moveapps.test.CoopeuchAutomatedWebTest;
import cl.moveapps.util.properties.AppConfigUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

/**
 * Created by marcelomagana on 9/26/17.
 */
public class Report {
    private static final Logger logger = LogManager.getLogger(Report.class);
    private static Report INSTANCE = new Report();
    private Properties prop = null;
    private String path = "";

    private Report() {
    
    }
    
    public static synchronized Report getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new Report();
        }
        return INSTANCE;
        
    }
    
    public synchronized void createReport(CoopeuchAutomatedWebTest clazz, List<String> images, String parametroNombreDocumento) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraphOne = createImagePreviousParagraphText(document, ParagraphAlignment.CENTER, 20, "EVIDENCIA DE PRUEBAS TEST " + clazz.getClass().getSimpleName ());
        //XWPFRun paragraphOneRunTwo = paragraphOne.createRun();
        //paragraphOneRunTwo.setText(" More text in paragraph one...");
        XWPFParagraph paragraphTwo = document.createParagraph();
        paragraphTwo.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun paragraphTwoRunOne = paragraphTwo.createRun();
        //paragraphTwoRunOne.setBold(true);
        paragraphTwoRunOne.setFontSize(12);
        paragraphTwoRunOne.setFontFamily("Verdana");
        paragraphTwoRunOne.setColor("000070");
        paragraphTwoRunOne.setText(date);
        paragraphTwoRunOne.addBreak();
        
        
        XWPFParagraph paragraphThree = document.createParagraph();
        paragraphThree.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraphThreeRunOne = paragraphThree.createRun();
        //paragraphThreeRunOne.setBold(true);
        paragraphThreeRunOne.setFontSize(14);
        paragraphThreeRunOne.setFontFamily("Verdana");
        paragraphThreeRunOne.setColor("000070");
        LocalDateTime localDateTime = LocalDateTime.now();
       // paragraphThreeRunOne.setText(localDateTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
        paragraphThreeRunOne.addBreak();
        
        //inicio
        int x = 1;
        for (String i : images) {
            createImagePreviousParagraphText(document, ParagraphAlignment.LEFT, 10, "Evidencia N° " +x);
            logger.info("Path before paragraph: " + i);
            createImageWithinDocumentParagraph(document, i);
            x++;
        }
        //XWPFParagraph paragraphFour = createImagePreviousParagraphText(document, ParagraphAlignment.LEFT, 10, "LOGIN");
        //XWPFParagraph paragraphFive = createImageWithinDocumentParagraph(document, "");
        
        //fin
        
        createDocument(document, clazz.getClass().getSimpleName(), parametroNombreDocumento);
    }
    
    private void createDocument(XWPFDocument document, String className, String parametroNombreDocumento) {
        prepareVariables();
        String pathname = path + className +"_"+ parametroNombreDocumento + ".docx";
        FileOutputStream outStream;
        try {
            outStream = new FileOutputStream(pathname);
            document.write(outStream);
            outStream.close();
        } catch (IOException e) {
            logger.error(e);
        }
    }
    
    private void createImageWithinDocumentParagraph(XWPFDocument document, String imagePath) {
        InputStream pic = null;
        double width = 0;
        double height = 0;
        try {
            pic = new FileInputStream(imagePath);
            logger.info("Path After Paragraph: " + imagePath);
            BufferedImage bimg = ImageIO.read(new File(imagePath));
            width = bimg.getWidth() / 3;
            height = bimg.getHeight() / 3;
        } catch (IOException e1) {
            logger.error(e1);
        }
        XWPFParagraph paragraphFive = document.createParagraph();
        paragraphFive.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun paragraphFiveRunOne = paragraphFive.createRun();
        try {
            paragraphFiveRunOne.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, imagePath, Units.toEMU(width), Units.toEMU(height));
            pic.close();
        } catch (InvalidFormatException | IOException e1) {
            logger.error(e1);
        }
    }
    
    private XWPFParagraph createImagePreviousParagraphText(XWPFDocument document, ParagraphAlignment left, int size, String paragraphText) {
        XWPFParagraph paragraphFour = document.createParagraph();
        paragraphFour.setAlignment(left);
        XWPFRun paragraphFourRunOne = paragraphFour.createRun();
        paragraphFourRunOne.setBold(true);
        paragraphFourRunOne.setUnderline(UnderlinePatterns.SINGLE);
        paragraphFourRunOne.setFontSize(size);
        paragraphFourRunOne.setFontFamily("Verdana");
        paragraphFourRunOne.setColor("000070");
        paragraphFourRunOne.setText(paragraphText);
        return paragraphFour;
    }
    
    private void prepareVariables() {
        try {
            prop = AppConfigUtil.getInstance().getProperties();
        } catch (AppConfigException e) {
            logger.error(e);
        }
        path = (String) prop.get("report.path");
    }
    
}
