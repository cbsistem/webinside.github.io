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
 * Classe que tranforma um texto para o formato HTML e vice-versa. No formato
 * HTML carateres especiais s�o convertidos com &...;
 *
 * @author Geraldo Moraes
 * @version $Revision: 1.1 $
 *
 * @since 3.0
 */
public class StringAHtml extends StringA {
    /** Caracteres no formato HTML. */
    private static final String[] HTML_CHARS =
    {
        "&aacute;", "&Aacute;", "&eacute;", "&Eacute;", "&iacute;", "&Iacute;",
        "&oacute;", "&Oacute;", "&uacute;", "&Uacute;", "&agrave;", "&Agrave;",
        "&egrave;", "&Egrave;", "&igrave;", "&Igrave;", "&ograve;", "&Ograve;",
        "&ugrave;", "&Ugrave;", "&acirc;", "&Acirc;", "&ecirc;", "&Ecirc;",
        "&icirc;", "&Icirc;", "&ocirc;", "&Ocirc;", "&ucirc;", "&Ucirc;",
        "&auml;", "&Auml;", "&euml;", "&Euml;", "&iuml;", "&Iuml;", "&ouml;",
        "&Ouml;", "&uuml;", "&Uuml;", "&atilde;", "&Atilde;", "&otilde;",
        "&Otilde;", "&ccedil;", "&Ccedil;"
    };
    /** S�mbolos no formato HTML. */
    private static final String[] HTML_SYMBOLS =
    {"&quot;", "&nbsp;", "&lt;", "&gt;", "&amp;"};
    /** Caracteres no formato de texto. */
    private static final String[] TEXT_CHARS =
    {
        "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
        "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
        "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
        "�", "�", "�", "�"
    };
    /** S�mbolos no formato de texto. */
    private static final String[] TEXT_SYMBOLS = {"\"", " ", "<", ">", "&"};

    /**
     * Cria um novo objeto da classe.
     */
    public StringAHtml() {
    }

    /**
     * Cria um novo objeto da classe.
     *
     * @param text o texto inicial como String.
     */
    public StringAHtml(String text) {
        this.set(text);
    }

    /**
     * Cria um novo objeto da classe.
     *
     * @param text o texto inicial como StringA.
     */
    public StringAHtml(StringA text) {
        this.set(text);
    }

    /**
     * Converte um texto no formato HTML com & para o formato normal com os
     * caracteres especiais. Tanto converte os simbolos (ex: &lt;,&gt;) como
     * converte as letras acentuadas (ex: &aacute;).
     *
     * @return texto normal com simbolos e caracteres acentuados.
     */
    public String htmlToTextFull() {
        return htmlToText(true, true);
    }

    /**
     * Converte um texto no formato HTML com & para o formato normal com os
     * caracteres especiais.
     *
     * @param chars indica se converter� os caracteres (ex: &aacute;).
     * @param symbols indica se converter� os simbolos(ex: &lt;,&gt;).
     *
     * @return texto normal com caracteres especiais.
     */
    public String htmlToText(boolean chars, boolean symbols) {
        return htmlToText(this.toString(), chars, symbols);
    }

    /**
     * Converte um texto no formato HTML com & para o formato normal com os
     * caracteres especiais.
     *
     * @param text o texto no formato html a ser processado.
     * @param chars indica se converter� os caracteres (ex: &aacute;).
     * @param symbols indica se converter� os simbolos (ex: &lt;,&gt;).
     *
     * @return texto normal com caracteres especiais.
     */
    public static String htmlToText(String text, boolean chars, boolean symbols) {
        Map map = new HashMap();
        if (text == null) {
            text = "";
        }
        if (symbols) {
            for (int i = 0; i < HTML_SYMBOLS.length; i++) {
                map.put(HTML_SYMBOLS[i], TEXT_SYMBOLS[i]);
            }
        }
        if (chars) {
            for (int i = 0; i < HTML_CHARS.length; i++) {
                map.put(HTML_CHARS[i], TEXT_CHARS[i]);
            }
        }
        return StringA.xmlEntityToText(text, map);
    }

    /**
     * Converte um texto normal com caracteres especiais para o formato HTML
     * com &. Tanto converte os simbolos (ex: &lt;&lg;) como converte as
     * letras acentuadas (ex: ��).
     *
     * @return texto no formato HTML com simbolos e caracteres  acentuados
     *         convertidos.
     */
    public String textToHtmlFull() {
        return textToHtml(true, true);
    }

    /**
     * Converte um texto normal com caracteres especiais para o formato HTML
     * com &.
     *
     * @param chars indica se converter� os caracteres (ex: ��).
     * @param symbols indica se converter� os simbolos (ex: &lt;&lg;).
     *
     * @return texto no formato HTML com caracteres  especiais convertidos.
     */
    public String textToHtml(boolean chars, boolean symbols) {
        return textToHtml(this.toString(), chars, symbols);
    }

    /**
     * Converte um texto normal com caracteres especiais para o formato HTML
     * com &.
     *
     * @param text o texto a ser processado.
     * @param chars indica se converter� os caracteres (ex: ��).
     * @param symbols indica se converter� os simbolos (ex: &lt;&lg;).
     *
     * @return texto no formato HTML com caracteres  especiais convertidos.
     */
    public static String textToHtml(String text, boolean chars, boolean symbols) {
        Map map = new HashMap();
        if (text == null) {
            text = "";
        }
        if (symbols) {
            for (int i = 0; i < TEXT_SYMBOLS.length; i++) {
                map.put(TEXT_SYMBOLS[i], HTML_SYMBOLS[i]);
            }
        }
        if (chars) {
            for (int i = 0; i < TEXT_CHARS.length; i++) {
                map.put(TEXT_CHARS[i], HTML_CHARS[i]);
            }
        }
        return StringA.textToXmlEntity(text, map, true);
    }
}
