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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import br.com.webinside.runtime.core.ExecuteParams;
import br.com.webinside.runtime.exception.UserException;

/**
 * Classe abstrata para valida��o de variaveis no servidor
 * 
 * @author Geraldo Moraes
 * @version 1.0
 */
public abstract class AbstractValidation implements InterfaceValidation {
	
	private static Map<String, Properties> messages = 
		new HashMap<String, Properties>();
	
	private ExecuteParams wiParams;

    public abstract String execute(String var, String[] args)
        throws UserException;
    
	protected String messagesName() {
		return "br.com.webinside.runtime.validation.Messages";
	}

	protected String defaultLocale() {
		return "pt_BR";
	}

    public String execute(ExecuteParams wiParams, String var, String[] args) 
    throws UserException {
        this.wiParams = wiParams;
        for (int i = 0; i < args.length; i++) {
            args[i] = Producer.execute(wiParams.getWIMap(), args[i]);
        }
        return execute(var, args);
    }
    
    public ExecuteParams getWiParams() {
		return wiParams;
	}

    public Locale getLocale() {
    	return IntFunction.getLocale(wiParams.getWIMap());
    }
    
    public String getMessage(String key, Object...args) {
    	try {
        	Properties props = loadMessages(messagesName() + "_" + getLocale());
        	if (props.isEmpty()) {
        		// ler mensagens para locale padrao
        		props = loadMessages(messagesName() + "_" + defaultLocale());
        	}
        	String resp = props.getProperty(key);
        	if (resp == null) resp = "";
        	return String.format(resp.trim(), args);
	    } catch (Exception err) {
	    	wiParams.getErrorLog().write("Validation", "getMessage", err);
	    	err.printStackTrace();
	    }
    	return "";
    }
     
    private Properties loadMessages(String key) throws Exception {
    	Properties props = messages.get(key);
    	if (props == null) {
    		String propsFile = key.replaceAll("\\.", "/") + ".properties";
    		props = IntFunction.loadProperties(props, propsFile);
    		messages.put(key, props);
    	}	
    	return props;
    }
    
}
