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

package br.com.webinside.runtime.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import br.com.webinside.runtime.net.ssl.HostnameVerifier;
import br.com.webinside.runtime.net.ssl.SSLSocketFactory;

/**
 * Classe de fun��es para uso relacionado com comunica��o.
 *
 * @author Geraldo Moraes
 * @version $Revision: 1.1 $
 */
public class NetFunction {

	private NetFunction() { }
	
    /**
     * Retorna uma conex�o http ou https sem validar o certificado e o hostname.
     *
     * @param url a Url a conextar.
     *
     * @return a conex�o.
     *
     * @throws IOException DOCUMENT ME!
     * @throws MalformedURLException DOCUMENT ME!
     */
    public static HttpURLConnection openConnection(String url)
        throws IOException, MalformedURLException {
        if (url.startsWith("https://")) {
        	SSLSocketFactory socketFactory = new SSLSocketFactory();
        	HostnameVerifier hostnameVerifier = new HostnameVerifier();        	
            URLConnection con = new URL(url).openConnection();
            ((HttpsURLConnection) con).setSSLSocketFactory(socketFactory);
            ((HttpsURLConnection) con).setHostnameVerifier(hostnameVerifier);
            return (HttpsURLConnection) con;
        } else {
            return (HttpURLConnection) new URL(url).openConnection();
        }
    }
    
}
