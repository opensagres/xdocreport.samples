package fr.opensagres.xdocreport.samples.odt.converters.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.odftoolkit.odfdom.converter.pdf.PdfConverter;
import org.odftoolkit.odfdom.converter.pdf.PdfOptions;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

import fr.opensagres.xdocreport.samples.odt.converters.Data;

public class ConvertODTResumeToPDF
{

    public static void main( String[] args )
    {
        long startTime = System.currentTimeMillis();

        try
        {
            // 1) Load odt with ODFDOM
            OdfTextDocument document = OdfTextDocument.loadDocument( Data.class.getResourceAsStream( "ODTResume.odt" ) );

            // 2) Convert ODFDOM OdfTextDocument 2 PDF with iText
            File outFile = new File( "target/ODTResume.pdf" );
            outFile.getParentFile().mkdirs();

            OutputStream out = new FileOutputStream( outFile );
            PdfOptions options = null;// PDFViaITextOptions.create().fontEncoding( "windows-1250" );
            PdfConverter.getInstance().convert( document, out, options );
        }
        catch ( Throwable e )
        {
            e.printStackTrace();
        }

        System.out.println( "Generate ODTResume.pdf with " + ( System.currentTimeMillis() - startTime ) + " ms." );
    }
}
