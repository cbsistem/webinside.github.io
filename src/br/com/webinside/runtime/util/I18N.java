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

package br.com.webinside.runtime.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Classe utilizada para internacionaliza��o das mensagens do WI.
 *
 * @author Luiz Augusto Ruiz
 * @version $Revision: 1.1 $
 *
 * @since 3.0
 */
public class I18N implements Serializable {

	private static final long serialVersionUID = 1L;
    private static Map keys;
    /** Pacote dos arquivos de internacionaliza��o */
    private static final String baseName = "br.com.webinside.runtime.Strings";
    private static Map strings;
    private Locale locale = Locale.getDefault();
    private ResourceBundle localStrings;

    /**
     * Cria um novo I18N.
     *
     * @param locale o idioma(locale) a ser utilizado.
     */
    public I18N(Locale locale) {
        this.locale = locale;
        loadKeys();
    }

    /**
     * Cria um novo I18N.
     */
    public I18N() {
    	if (locale.getLanguage().equalsIgnoreCase("en")) {
    		locale = new Locale("en", "us");
    	} else if (locale.getLanguage().equalsIgnoreCase("pt")) {
    		locale = new Locale("pt", "br");
    	}
        loadKeys();
    }
    
    private synchronized static void initKeys() {
        if (keys != null) {
        	return;
        }
        keys = Collections.synchronizedMap(new HashMap());
        ResourceBundle props =
            ResourceBundle.getBundle(baseName, new Locale("pt", "BR"));
        Enumeration en = props.getKeys();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            keys.put(props.getString(key), key);
        }
    }

    private void loadKeys() {
        loadStrings();
        initKeys();
    }

    private void loadStrings() {
        if (strings == null) {
            strings = new Hashtable();
        }
		if (locale.getLanguage().equals("pt") && 
				locale.getCountry().equals("PT")){
			locale = new Locale("pt", "BR");
		}
        if (strings.containsKey(locale.toString())) {
            localStrings = (ResourceBundle) strings.get(locale.toString());
        } else {
        	try {
				localStrings = ResourceBundle.getBundle(baseName, locale);
				strings.put(locale.toString(), localStrings);
        	} catch (Exception err) {
				System.err.println(getClass().getName() + ": " + err);        		
        	}
        }
    }

    /**
     * Retorna uma mensagem no idioma em uso.
     *
     * @param key a mensagem a ser recuperada.
     *
     * @return a mensagem no idioma em uso.
     */
    public String get(String key) {
        String newKey = (String) keys.get(key);
        if (newKey == null) {
            newKey = "#" + key + "#";
        } else {
            try {
                newKey = localStrings.getString(newKey);
            } catch (MissingResourceException ex) {
                newKey = "#" + key + "#";
            }
        }
        return newKey;
    }

}
