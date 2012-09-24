package fr.opensagres.xdocreport.converter;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ConverterApplication extends Application {



	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(ConverterResourceImpl.class);
        return classes;
    }
}
