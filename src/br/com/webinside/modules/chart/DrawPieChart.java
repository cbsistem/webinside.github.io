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

package br.com.webinside.modules.chart;

import java.util.List;

import br.com.webinside.runtime.integration.AbstractConnector;
import br.com.webinside.runtime.integration.DatabaseAliases;
import br.com.webinside.runtime.integration.InterfaceHeaders;
import br.com.webinside.runtime.integration.InterfaceParameters;
import br.com.webinside.runtime.integration.JavaParameter;
import br.com.webinside.runtime.util.WIMap;

/**
 * DOCUMENT ME!
 *
 * @author Luiz Ricardo To change the template for this generated type comment
 *         go to Window>Preferences>Java>Code Generation>Code and Comments
 */
public class DrawPieChart extends AbstractConnector
    implements InterfaceParameters {
    private PieChart pc;

    /**
     * Creates a new DrawPieChart object.
     */
    public DrawPieChart() {
        pc = new PieChart();
    }

    /**
     * DOCUMENT ME!
     *
     * @param context DOCUMENT ME!
     * @param databases DOCUMENT ME!
     * @param headers DOCUMENT ME!
     */
    public void execute(WIMap context, DatabaseAliases databases,
        InterfaceHeaders headers) {
        String title = context.get(WIChart.CHART_TITLE_VAR);
        String width = context.get(WIChart.CHART_WIDTH_VAR);
        String height = context.get(WIChart.CHART_HEIGHT_VAR);
        String draw3d = context.get(WIChart.CHART_3D_VAR);
        String foregroundAlpha = context.get(WIChart.CHART_PLOT_FOREGROUND_VAR);
        String objectId = context.get(WIChart.CHART_DATASET_OBJ_VAR);

        pc.setTitle(title);
        if (!width.equals("")) {
            pc.setWidth(Integer.parseInt(width));
        }
        if (!height.equals("")) {
            pc.setHeight(Integer.parseInt(height));
        }
        if (!foregroundAlpha.equals("")) {
            pc.setPlotForegroundAlpha(Float.parseFloat(foregroundAlpha));
        }
        pc.setDraw3d(draw3d.equalsIgnoreCase("true"));

        pc.setSectionLabelType(context.get(
                PieChart.PIECHART_SECTION_LABEL_TYPE_VAR));
        pc.setPieDataset(ChartUtil.getPieDataset(context, objectId));

        headers.setContentType("image/png");
        //    try {
        //      ImageIO.write(pc.drawChart(), "png", out);
        //    } catch (IOException e) {
        //      e.printStackTrace();
        //    }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public JavaParameter[] getInputParameters() {
        List list = pc.getInParameters();
        JavaParameter[] jp = new JavaParameter[list.size()];
        pc.getInParameters().toArray(jp);
        return jp;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public JavaParameter[] getOutputParameters() {
        return null;
    }
}
