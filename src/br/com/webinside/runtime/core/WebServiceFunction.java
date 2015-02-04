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

package br.com.webinside.runtime.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import br.com.webinside.runtime.util.StringA;

public class WebServiceFunction {

    public static MimeHeaders getHeaders(HttpServletRequest request) {
        MimeHeaders headers = new MimeHeaders();
        Enumeration e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String headerName = (String) e.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.addHeader(headerName, headerValue);
        }
        return headers;
    }

    public static void putHeaders(MimeHeaders headers,
        HttpServletResponse response) {
        Iterator it = headers.getAllHeaders();
        while (it.hasNext()) {
            MimeHeader header = (MimeHeader) it.next();
            String value = header.getValue();
            response.setHeader(header.getName(), value);            
        }
    }

    public static String toString(SOAPMessage message) {
        String aux = "";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            message.writeTo(baos);
            baos.close();
            String text = baos.toString("ISO-8859-1");
            String search = "SOAP-ENV:Body>";
            if (text.toLowerCase().indexOf(search.toLowerCase())==-1) {
            	search="soap:Body>";
            }
            aux = StringA.piece(text, "<" + search, 2, 2, false);
            aux = StringA.piece(aux, "</" + search, 1, 1, false);
            aux = StringA.change(aux, "><", ">\r\n<");
        } catch (UnsupportedEncodingException err) {
        } catch (IOException err) {
        } catch (SOAPException err) {
        }
        return aux;
    }
}
