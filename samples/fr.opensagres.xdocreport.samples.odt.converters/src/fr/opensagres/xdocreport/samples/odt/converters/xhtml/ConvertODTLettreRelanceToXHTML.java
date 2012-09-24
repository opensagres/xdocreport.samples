package fr.opensagres.xdocreport.samples.odt.converters.xhtml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.odftoolkit.odfdom.converter.xhtml.ODF2XHTMLConverter;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

import fr.opensagres.xdocreport.samples.odt.converters.Data;

public class ConvertODTLettreRelanceToXHTML
{

    public static void main( String[] args )
    {
        long startTime = System.currentTimeMillis();

        try
        {
            // 1) Load odt with ODFDOM
            OdfTextDocument document =
                OdfTextDocument.loadDocument( Data.class.getResourceAsStream( "ODTLettreRelance.odt" ) );

            // 2) Convert ODFDOM OdfTextDocument 2 PDF with iText
            File outFile = new File( "target/ODTLettreRelance.htm" );
            outFile.getParentFile().mkdirs();

            OutputStream out = new FileOutputStream( outFile );
            ODF2XHTMLConverter.getInstance().convert( document, out, null );
        }
        catch ( Throwable e )
        {
            e.printStackTrace();
        }

        System.out.println( "Generate ODTLettreRelance.htm with " + ( System.currentTimeMillis() - startTime ) + " ms." );
    }
}
