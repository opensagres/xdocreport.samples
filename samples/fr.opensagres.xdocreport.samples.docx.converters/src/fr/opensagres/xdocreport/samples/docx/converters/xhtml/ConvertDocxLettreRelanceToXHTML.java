package fr.opensagres.xdocreport.samples.docx.converters.xhtml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.xwpf.converter.xhtml.XWPF2XHTMLConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import fr.opensagres.xdocreport.samples.docx.converters.Data;

public class ConvertDocxLettreRelanceToXHTML
{

    public static void main( String[] args )
    {
        long startTime = System.currentTimeMillis();

        try
        {
            // 1) Load docx with POI XWPFDocument
            XWPFDocument document = new XWPFDocument( Data.class.getResourceAsStream( "DocxLettreRelance.docx" ) );

            // 2) Convert POI XWPFDocument 2 PDF with iText
            File outFile = new File( "target/DocxLettreRelance.htm" );
            outFile.getParentFile().mkdirs();

            OutputStream out = new FileOutputStream( outFile );
            XWPF2XHTMLConverter.getInstance().convert( document, out, null );
        }
        catch ( Throwable e )
        {
            e.printStackTrace();
        }

        System.out.println( "Generate DocxLettreRelance.htm with " + ( System.currentTimeMillis() - startTime )
            + " ms." );
    }
}
