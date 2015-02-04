/*
 * WEBINSIDE - Ferramenta de produtividade Java
 * Copyright (c) 2011-2012 LINEWEB Solu��es Tecnol�gicas Ltda.
 * Copyright (c) 2009-2010 Inc�gnita Intelig�ncia Digital Ltda.
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou modific�-lo 
 * sob os termos da GNU LESSER GENERAL PUBLIC LICENSE (LGPL) conforme publicada 
 * pela Free Software Foundation; vers�o 2.1 da Licen�a.
 * Este programa � distribu�do na expectativa de que seja �til, por�m, SEM 
 * NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU 
 * ADEQUA��O A UMA FINALIDADE ESPEC�FICA.
 * 
 * Consulte a GNU LGPL para mais detalhes.
 * Voc� deve ter recebido uma c�pia da GNU LGPL junto com este programa; se n�o, 
 * veja em http://www.gnu.org/licenses/ 
 */

package br.com.webinside.runtime.integration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import br.com.webinside.runtime.core.ExecuteParams;
import br.com.webinside.runtime.exception.UserException;
import br.com.webinside.runtime.util.Function;
import br.com.webinside.runtime.util.WIMap;

/**
 * @author Luiz Ruiz
 *
 */
public class AntConnector implements InterfaceConnector {

    private static String antVersion;

    public void execute(ExecuteParams wiParams) throws UserException {
        WIMap wiMap = wiParams.getWIMap();
        try {
            String antFile = wiMap.get("wi.proj.path") + "/WEB-INF/antscripts/" 
            	+ wiMap.get("antFile");            
            runAnt(new File(antFile), wiMap.getAsMap(), wiMap.get("task"));
        } catch (Exception e) {
            throw new UserException(e);
        } finally {
            wiMap.remove("task");
            wiMap.remove("antFile");           
        }
    }

    public boolean exit() {
        return false;
    }

    public static void runAnt(File build, Map properties) throws Exception {
        runAnt(build, properties, null);
    }

    public static void runAnt(File build, Map properties, String task) 
    		throws Exception {
        Project project = new Project();
        project.setCoreLoader(AntConnector.class.getClassLoader());
        project.init();
        project.setUserProperty("ant.version", getAntVersion());
        project.setUserProperty("ant.file", build.getAbsolutePath());        
        for(Iterator it = properties.keySet().iterator(); it.hasNext();) {
            String key = (String) it.next();
            Object value = properties.get(key);
            if (value instanceof String) {
                String s = (String) value;
                if (!s.trim().equals("")) {
                    project.setUserProperty(key, s);
                }
            }
        }
        ProjectHelper.getProjectHelper().parse(project, build);
        if (task == null || task.equals("")) {
            project.executeTarget(project.getDefaultTarget());
        } else {
            project.executeTarget(task);
        }
    }
    
    private static String getAntVersion() throws BuildException {
        if (antVersion == null) {
            try {
                Properties props = new Properties();
                InputStream in =
                    Function.class.getResourceAsStream(
                            "/org/apache/tools/ant/version.txt");
                props.load(in);
                in.close();

                StringBuffer msg = new StringBuffer();
                msg.append("Apache Ant version ");
                msg.append(props.getProperty("VERSION"));
                msg.append(" compiled on ");
                msg.append(props.getProperty("DATE"));
                antVersion = msg.toString();
            } catch (IOException ioe) {
                throw new BuildException("Could not load the version information:"
                                         + ioe.getMessage());
            } catch (NullPointerException npe) {
                throw new BuildException("Could not load the version information.");
            }
        }
        return antVersion;
    }

    
}
