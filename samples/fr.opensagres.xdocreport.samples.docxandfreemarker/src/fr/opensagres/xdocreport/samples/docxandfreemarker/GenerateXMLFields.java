package fr.opensagres.xdocreport.samples.docxandfreemarker;

import fr.opensagres.xdocreport.samples.docxandfreemarker.model.DeveloperWithImage;
import fr.opensagres.xdocreport.samples.docxandfreemarker.model.ProjectWithImage;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.annotations.FieldMetadata;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

/**
 * This class generates XML fields available from Pojo context annoted with {@link FieldMetadata} annotation
 */
public class GenerateXMLFields
{

    public static void main( String[] args )
    {
        try
        {
            FieldsMetadata metadata = new FieldsMetadata( TemplateEngineKind.Freemarker );
            metadata.load( "project", ProjectWithImage.class );
            metadata.load( "developers", DeveloperWithImage.class, true );
            metadata.saveXML( System.out );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}
