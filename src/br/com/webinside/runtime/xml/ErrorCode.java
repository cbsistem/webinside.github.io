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

/**
 * Classe que cont�m os poss�veis resultados das opera��es com os elementos que
 * integram o WI.
 *
 * @author Geraldo Moraes
 * @version 1.0
 */
public class ErrorCode {
    /** Sucesso na execu��o. N�o houve erro. */
    public static final int NOERROR = 1;
    /** Erro desconhecido. */
    public static final int UNKNOWN = 0;
    /** Elemento � nulo. */
    public static final int NULL = -1;
    /** Elemento � vazio. */
    public static final int EMPTY = -2;
    /** Elemento j� existe. */
    public static final int EXIST = -5;
    /** Elemento n�o existe. */
    public static final int NOEXIST = -6;
	/** Erro do Servidor WebApp. */
	public static final int WEBAPP = -7;
}
