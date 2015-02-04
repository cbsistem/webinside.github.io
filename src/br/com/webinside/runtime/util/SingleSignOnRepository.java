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

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que guarda as informa��es a serem repassadas entre os projetos 
 * @author Geraldo Moraes
 *
 */
public class SingleSignOnRepository { 

	private static Map tokens = new HashMap();
	private static Map owners = new HashMap();
	
	public static void addToken(String token, Map map, String owner) {
		tokens.put(token, map);
		owners.put(token, owner);
	}
	
	public static void delToken(String token) {
		tokens.remove(token);
		owners.remove(token);
	}
	
	public static Map getToken(String token) {
		return (Map)tokens.get(token);
	}

	public static boolean isOwner(String token, String owner) {
		String aux = (String)owners.get(token); 
		return (aux != null && aux.equals(owner));
	}
	
}
