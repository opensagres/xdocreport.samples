package fr.opensagres.xdocreport.samples.odt.converters.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.odftoolkit.odfdom.converter.itext.ODF2PDFViaITextConverter;
import org.odftoolkit.odfdom.converter.itext.PDFViaITextOptions;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

import fr.opensagres.xdocreport.samples.odt.converters.Data;

public class ConvertODTBigToPDF
{

    public static void main( String[] args )
    {
        long startTime = System.currentTimeMillis();

        try
        {
            // 1) Load odt with ODFDOM
            OdfTextDocument document = OdfTextDocument.loadDocument( Data.class.getResourceAsStream( "ODTBig.odt" ) );

            // 2) Convert ODFDOM OdfTextDocument 2 PDF with iText
            File outFile = new File( "target/ODTBig.pdf" );
            outFile.getParentFile().mkdirs();

            OutputStream out = new FileOutputStream( outFile );
            PDFViaITextOptions options = PDFViaITextOptions.create().fontEncoding( "windows-1250" );
            ODF2PDFViaITextConverter.getInstance().convert( document, out, options );
        }
        catch ( Throwable e )
        {
            e.printStackTrace();
        }

        System.out.println( "Generate ODTBig.pdf with " + ( System.currentTimeMillis() - startTime ) + " ms." );
    }
}
