package fr.opensagres.xdocreport.samples.docx.converters.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.xwpf.converter.itext.PDFViaITextOptions;
import org.apache.poi.xwpf.converter.itext.XWPF2PDFViaITextConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import fr.opensagres.xdocreport.samples.docx.converters.Data;

public class ConvertDocxLettreRelanceToPDF
{

    public static void main( String[] args )
    {
        long startTime = System.currentTimeMillis();

        try
        {
            // 1) Load docx with POI XWPFDocument
            XWPFDocument document = new XWPFDocument( Data.class.getResourceAsStream( "DocxLettreRelance.docx" ) );

            // 2) Convert POI XWPFDocument 2 PDF with iText
            File outFile = new File( "target/DocxLettreRelance.pdf" );
            outFile.getParentFile().mkdirs();

            OutputStream out = new FileOutputStream( outFile );
            PDFViaITextOptions options = null;// PDFViaITextOptions.create().fontEncoding( "windows-1250" );
            XWPF2PDFViaITextConverter.getInstance().convert( document, out, options );
        }
        catch ( Throwable e )
        {
            e.printStackTrace();
        }

        System.out.println( "Generate DocxLettreRelance.pdf with " + ( System.currentTimeMillis() - startTime )
            + " ms." );
    }
}
