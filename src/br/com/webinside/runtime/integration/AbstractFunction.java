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

import java.util.Locale;

import br.com.webinside.runtime.core.ExecuteParams;
import br.com.webinside.runtime.exception.UserException;
import br.com.webinside.runtime.util.WIMap;

public abstract class AbstractFunction implements InterfaceFunction {
	
	private ExecuteParams wiParams;
	private WIMap wiMap;

    public abstract String execute(String[] args)
        throws UserException;

    public String execute(ExecuteParams wiParams, String[] args) 
    throws UserException {
        this.wiParams = wiParams;
        if (wiMap == null && wiParams != null) {
        	wiMap = wiParams.getWIMap();
        }
        for (int i = 0; i < args.length; i++) {
            args[i] = Producer.execute(wiMap, args[i]);
        }
        return execute(args);
    }
    
    public void setWiMap(WIMap wiMap) {
		this.wiMap = wiMap;
	}

	public WIMap getWiMap() {
		return wiMap;
    }

    public ExecuteParams getWiParams() {
		return wiParams;
	}

    public Locale getLocale() {
    	return IntFunction.getLocale(getWiMap());
    }

}
