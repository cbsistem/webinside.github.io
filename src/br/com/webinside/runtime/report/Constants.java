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

package br.com.webinside.runtime.report;

import java.util.Arrays;

/**
 * Define as constantes utilizadas no WIReport
 *
 * @author Luiz Ruiz
 * @version $Revision: 1.1 $
 *
 * @since 3.0.10
 */
public class Constants {
    /** DOCUMENT ME! */
    public static final String ZOOM = "zoom";
    
    public static final String WIREPORT_APPNAME = "wireport";
    /**
     * Lista de a��es. Deve haver uma constante declarada com o �ndice
     * equivalente � ac�o.
     */
    private static final String[] ACTION_NAMES =
    {
        "save", "add", "remove", "moveup", "movedown", "fieldwizard",
        "showexpressionwizard", "evalexpression", "newfield", "newgroup", 
		"undo", "saveas", "setborders"
    };
    /** A��o salvar */
    public static final int ACTION_SAVE = 0;
    /** A��o adicionar */
    public static final int ACTION_ADD = 1;
    /** A��o remover */
    public static final int ACTION_REMOVE = 2;
    /** A��o mover uma posi��o acima */
    public static final int ACTION_MOVE_UP = 3;
    /** A��o mover uma posi��o abaixo */
    public static final int ACTION_MOVE_DOWN = 4;
    /** A��o WIzard de campo */
    public static final int ACTION_FIELD_WIZARD = 5;
    /** A��o mostra WIzard de express�o */
    public static final int ACTION_EXPRESSION_WIZARD = 6;
    /** A��o avaliar express�o */
    public static final int ACTION_EVAL_EXPRESSION = 7;
    /** A��o adicionar campo */
    public static final int ACTION_NEW_FIELD = 8;
    /** A��o adicionar grupo */
    public static final int ACTION_NEW_GROUP = 9;
    /** A��o desfazer */
    public static final int ACTION_UNDO = 10;
    /** A��o salvar como*/
    public static final int ACTION_SAVEAS = 11;
    /** A��o configurar bordas*/
    public static final int ACTION_SET_BORDERS = 12;

    /**
     * Transforma a a��o em um inteiro constante. Geralmente utilizado para
     * converter o parametro passado pela p�gina HTML para o tipo  utilizado
     * nas classes.
     *
     * @param action a String da a��o.
     *
     * @return o inteiro correspondente � constante da a��o.
     */
    public static final int parseAction(String action) {
        int ret = Arrays.asList(ACTION_NAMES).indexOf(action.toLowerCase());
        return ret;
    }
}
