package fr.opensagres.xdocreport.samples.reporting.springmvc.report;

import fr.opensagres.web.servlet.view.xdocreport.IXDocReportConfiguration;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.samples.reporting.springmvc.model.Developer;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class DocxProjectWithVelocityListConfiguration
    implements IXDocReportConfiguration
{

    public void configure( IXDocReport report )
        throws XDocReportException
    {
        FieldsMetadata metadata = report.createFieldsMetadata();
        metadata.load( "developers", Developer.class, true );
    }
}
