package fr.opensagres.xdocreport.samples.reporting.springmvc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.samples.reporting.springmvc.model.Developer;
import fr.opensagres.xdocreport.samples.reporting.springmvc.model.Project;

@Controller
@RequestMapping( "/main" )
public class MainController
{

    /**
     * Handles and retrieves the download page
     * 
     * @return the name of the JSP page
     */
    @RequestMapping( value = "/download", method = RequestMethod.GET )
    public String getDownloadPage()
    {

        // Do your work here. Whatever you like
        // i.e call a custom service to do your business
        // Prepare a model to be used by the JSP page

        // This will resolve to /WEB-INF/jsp/downloadpage.jsp
        return "downloadpage";
    }

    @RequestMapping( "/download/docx" )
    public String docxReport( ModelMap model )
    {
        addModel( model );
        return "docxReport";
    }

    @RequestMapping( "/download/pdf" )
    public String staticPdfReport( ModelMap model )
    {
        addModel( model );
        return "pdfReport";
    }

    @RequestMapping( "/download/pdf2" )
    public String dynamicPdfReport( ModelMap model )
    {
        addModel( model );
        model.addAttribute( Options.getTo( ConverterTypeTo.PDF ).via( ConverterTypeVia.XWPF ) );
        return "docxReport";
    }

    private void addModel( ModelMap model )
    {
        Project project = new Project( "XDocReport" );
        model.addAttribute( "project", project );

        List<Developer> developers = new ArrayList<Developer>();
        developers.add( new Developer( "ZERR", "Angelo", "angelo.zerr@gmail.com" ) );
        developers.add( new Developer( "Leclercq", "Pascal", "pascal.leclercq@gmail.com" ) );
        model.addAttribute( "developers", developers );
    }

}
