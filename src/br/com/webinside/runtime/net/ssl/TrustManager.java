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

package br.com.webinside.runtime.net.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

public class TrustManager implements X509TrustManager {
	
	public boolean isClientTrusted(X509Certificate[] cert) {
		return true;
	}

	public boolean isServerTrusted(X509Certificate[] cert) {
		return true;
	}

	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[0];
	}

	public void checkClientTrusted(X509Certificate[] chain, String authType) 
	throws CertificateException {
		// empty
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) 
	throws CertificateException {
		// empty
	}
	
}
