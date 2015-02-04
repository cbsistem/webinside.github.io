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

package br.com.webinside.runtime.report;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperCompileManager;
import br.com.webinside.runtime.database.impl.ConnectionSql;
import br.com.webinside.runtime.exception.UserException;
import br.com.webinside.runtime.integration.Producer;
import br.com.webinside.runtime.util.Function;
import br.com.webinside.runtime.util.StringA;
import br.com.webinside.runtime.util.WIMap;

/**
 * Classe utilit�ria do WIReport
 *
 * @author Luiz Ruiz
 * @version $Revision: 1.1 $
 *
 * @since 3.0.0
 */
public class ReportFunction {

    public static final String DATABASEALIASES = "wi.databasealiases";
    
    /**
     * Construtor privado para evitar nova inst�ncia da classe
     */
    protected ReportFunction() { }

    /**
     * Converte o mapa de vari�veis do WI em um Map, trocando os pontos por
     * sublinhado
     *
     * @param wiMap mapa de vari�veis do WI
     *
     * @return mapa convertido
     */
    public static Map wiMapConverter(WIMap wiMap) {
        String key = null;
        String keyMap = null;
        Map map = new HashMap();
        Iterator i = wiMap.getAsMap().keySet().iterator();
        while (i.hasNext()) {
            key = (String) i.next();
            if (key.endsWith(".")) {
                continue;
            }
            keyMap = key.replace('.', '_');
            map.put(keyMap, wiMap.get(key));
        }
        return map;
    }

    /**
     * Retorna uma conex�o
     *
     * @param wiMap mapa de vari�veis do WI
     *
     * @return a conex�o
     * @throws UserException
     */
    public static ConnectionSql getWIConnection(WIMap wiMap) 
    throws UserException {
    	ConnectionSql con = null;
        if (wiMap.containsKey(DATABASEALIASES)) {
            Map aliasMap = (Map) wiMap.getObj(DATABASEALIASES);
            if (aliasMap != null && !aliasMap.isEmpty()) {
                String id = wiMap.get("database");
                Map dbConfig = (Map) aliasMap.get(id);
                if (null == dbConfig) {
                    throw new UserException("Banco de dados " + id +
                    		" n�o definido no projeto.");
                }
                String type = (String) dbConfig.get("type");
                String alias = (String) dbConfig.get("alias");
                alias = Producer.execute(wiMap, alias).trim();
                String user = (String) dbConfig.get("user");
                user = Producer.execute(wiMap, user).trim();
                String pass = (String) dbConfig.get("pass");
                pass = Producer.execute(wiMap, pass).trim();
                con = new ConnectionSql(type, alias, user, pass);
                con.connect();
            }
        }
        return con;
    }

    /**
     * Retorna o caminho para o projeto
     *
     * @param projId id do projeto
     * @param cvsUser usu�rio do cvs que est� usando o projeto
     * @param sc contexto do servlet
     *
     * @return caminho para o projeto
     */
    public static String getProjPath(
        String projId, String cvsUser, ServletContext sc) {
        String path = sc.getRealPath("/");
        File webappDir = new File(path).getParentFile();
        if (cvsUser.equals("")) {
            path = new File(webappDir, projId).getAbsolutePath();
        } else {
            path =
                new File(webappDir, projId + "/WI-CVS/" + cvsUser)
                        .getAbsolutePath();
        }
        return path;
    }

    /**
     * Retorna o caminho para o diret�rio de defini��es de relat�rios
     *
     * @param projId projeto dos relat�rios
     * @param cvsUser usu�rio do cvs que est� usando o projeto
     * @param sc contexto do sevlet
     *
     * @return caminho para diret�rio de defini��es
     */
    public static String getReportDefDir(
        String projId, String cvsUser, ServletContext sc) {
        String dir = getProjPath(projId, cvsUser, sc);
        dir += "/WEB-INF/definitions/reports/";
        return dir;
    }

    /**
     * Retorna o caminho para o diret�rio de relat�rios
     * 
     * @param sc
     *            contexto do sevlet
     * 
     * @return caminho para diret�rio de projetos
     */
    public static String getReportDir(WIMap wiMap, ServletContext sc) {
        String projId = wiMap.get("wi.proj.id");
        String reportDir = "";
        if (projId.equals("")) {
            reportDir = sc.getRealPath("/WEB-INF/jasper/");
            reportDir = StringA.change(reportDir, "\\", "/");
        } else {
            reportDir = getProjPath(projId, wiMap.get("cvsUser"), sc);
            reportDir += "/WEB-INF/reports/";
        }
        new File(reportDir).mkdirs();
        reportDir = reportDir.replace('\\', '/');
        return reportDir;
    }
    

    /**
     * Retorna uma conex�o dado um nome de fonte de dados do ambiente.
     *
     * @param datasourceName nome da fonte de dados.
     *
     * @return conex�o.
     */
    public static Connection getConnection(String datasourceName) {
        Connection conn = null;
        try {
            javax.naming.Context jndiContext = new InitialContext();
            DataSource ds = (DataSource) jndiContext.lookup(datasourceName);
            conn = ds.getConnection();
            conn.setAutoCommit(true);
        } catch (SQLException err) {
        } catch (NamingException err) {
        }
        return conn;
    }

    public static String getJasperFilePath(String xmlFilePath) {
        xmlFilePath = xmlFilePath.replaceAll("\\\\", "/");
        // para o caso vindo do Builder
        xmlFilePath = xmlFilePath.replaceAll("/definitions/", "/");
        // para o caso vindo do WIReport
        xmlFilePath = xmlFilePath.replaceAll("/WEB-INF/xml/", 
                "/WEB-INF/jasper/");
        xmlFilePath = xmlFilePath.replaceAll(".jrxml", ".jasper");
        return xmlFilePath;
    }    

    /**
     * Compila os arquivos de relat�rios
     * 
     */
    public static void compile(ServletContext sc, Writer out) 
    throws IOException {
    	String reportPath = sc.getRealPath("/WEB-INF/definitions/reports");
        String[] files = Function.listDir(reportPath, "*.jrxml",false);
        for (int i = 0; i < files.length; i++) {
            files[i] = new File(reportPath, files[i]).getAbsolutePath();
        }
        compile(files, out);
    }

    /**
     * Compila os arquivos de files no diret�rio dir
     * 
     * @param files lista de arquivos jrxml a serem compilados
     * @param dir diret�rio dos relat�rios (.jasper)
     * @throws IOException
     */
    public static void compile(String[] files, Writer out) throws IOException {
        for (int i = 0; i < files.length; i++) {
            String xmlFile = files[i];
            String name = new File(xmlFile).getName();
            out.write((i + 1) + "/" + files.length + ": ");
            out.write(name);
            String jasperFile = getJasperFilePath(xmlFile);
            try {
            	File jf = new File(jasperFile);
            	if (!jf.isFile()) {
            		File dir = jf.getParentFile();
            		if (!dir.isDirectory()) {
            			dir.mkdirs();
            		}
            		JasperCompileManager.compileReportToFile(xmlFile, jasperFile);
            		out.write(" generation successful");
            	} else {
            		out.write(" already exists");
            	}
            } catch (Throwable t) {
        		out.write(" error");
                out.write("\n*** Erro em " + name + " ***: " + t.getMessage());
            }
            out.write("\n");
            out.flush();
        }
        
    }

    


}
