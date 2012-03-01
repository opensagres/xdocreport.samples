package fr.opensagres.xdocreport.webapp.remoting;

import java.io.File;

import fr.opensagres.xdocreport.remoting.resources.services.web.WebAppResourcesServiceListener;

public class CustomWebAppResourcesServiceListener
    extends WebAppResourcesServiceListener
{

    public String getName()
    {
        return "WebApp Demo Resoources";
    }

    @Override
    protected File getRootFolder( File webAppFolder )
    {
        return new File(webAppFolder, "resources");
    }

}
