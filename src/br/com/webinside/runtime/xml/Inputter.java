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

package br.com.webinside.runtime.xml;

import java.io.*;
import org.jdom.*;
import org.jdom.input.*;

/**
 * Classe gera um Document Jdom a partir de um arquivo ou texto Xml.
 *
 * @author Geraldo Moraes
 * @version 1.0
 */
public class Inputter {
    private String errorMsg;

    /**
     * Cria um novo Inputter.
     */
    public Inputter() {
    }

    /**
     * Retorna a mensagem caso tenha havido erro na gera��o do Document Jdom.
     * S� passa a existir ap�s a execu��o de algum m�todo input.
     *
     * @return a mensagem de erro.
     */
    public String getErrorMsg() {
        if (errorMsg == null) {
            return "";
        }
        return errorMsg;
    }

    /**
     * Gera um Document Jdom a partir de um arquivo Xml.
     *
     * @param file o arquivo a ser processado. Seu conte�do  deve iniciar com
     *        &lt;?xml ... ?&gt;.
     *
     * @return o Document Jdom do arquivo.
     */
    public Document input(File file) {
        return input(file, null, null);
    }

    /**
     * Gera um Document Jdom a partir de um texto Xml.
     *
     * @param text o texto Xml. Deve iniciar com &lt;?xml ... ?&gt;.
     *
     * @return o Document Jdom do texto.
     */
    public Document input(String text) {
        return input(null, text, null);
    }

    /**
     * Gera um Document Jdom a partir de um InputStream.
     *
     * @param in o InputStream a ser utilizado. Deve iniciar com &lt;?xml ...
     *        ?&gt;.
     *
     * @return o Document Jdom do InputStream.
     */
    public Document input(InputStream in) {
        return input(null, null, in);
    }

    private Document input(File file, String text, InputStream in) {
        errorMsg = "";
        Document doc = null;
        try {
            SAXBuilder builder = new SAXBuilder();
            builder.setValidation(false);
            String url = 
            	"http://apache.org/xml/features/nonvalidating/load-external-dtd";
            builder.setFeature(url, false);
            if (text != null) {
                StringReader reader = new StringReader(text);
                doc = builder.build(reader);
                reader.close();
            } else if (file != null) {
                doc = builder.build(file);
            } else {
                doc = builder.build(in);
            }
        } catch (JDOMException e) {
            errorMsg = e.getMessage();
        } catch (IOException e) {
            errorMsg = e.getMessage();
        }
        if (doc != null) {
            doc.getRootElement().removeAttribute("ID");
        }
        return doc;
    }
}
