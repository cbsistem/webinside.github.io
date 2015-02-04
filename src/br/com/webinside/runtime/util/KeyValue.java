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

/**
 * Classe que corresponde a um Bean de chave e valor.
 *
 * @author Geraldo Moraes
 * @version $Revision: 1.1 $
 *
 * @since 3.0
 */
public class KeyValue implements Serializable {

	private static final long serialVersionUID = 1L;
    private String key;
    private Object value;

    /**
     * Cria um novo KeyValue.
     */
    public KeyValue() {
        key = "";
    }

    /**
     * Cria um novo KeyValue.
     *
     * @param k a chave.
     * @param v o valor.
     */
    public KeyValue(String k, Object v) {
        setKey(k);
        value = v;
    }

    /**
     * Define a chave.
     *
     * @param k a chave.
     */
    public void setKey(String k) {
        if (k != null) {
            key = k;
        }
    }

    /**
     * Retorna a chave.
     *
     * @return a chave.
     */
    public String getKey() {
        return key;
    }

    /**
     * Define o valor.
     *
     * @param v o valor.
     */
    public void setValue(Object v) {
        value = v;
    }

    /**
     * Retorna o valor.
     *
     * @return o valor.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Retorna o valor como uma String.
     *
     * @return o valor.
     */
    public String getValueString() {
        String v = value.toString();
        if (v == null) {
            v = "";
        }
        return v;
    }
}
