/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2007-2015 Broad Institute
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HeatmapScaleDialog.java
 *
 * Created on Sep 30, 2009, 1:10:50 PM
 */

package org.broad.igv.ui;

import org.broad.igv.prefs.Constants;
import org.broad.igv.prefs.PreferencesManager;
import org.broad.igv.renderer.ContinuousColorScale;
import org.broad.igv.track.TrackType;
import org.broad.igv.ui.color.ColorChooserPanel;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;

/**
 * @author jrobinso
 */
public class HeatmapScaleDialog extends javax.swing.JDialog {


    boolean canceled = false;
    ContinuousColorScale colorScale;

    /**
     * Creates new form DataScaleDialog2
     */
    public HeatmapScaleDialog(java.awt.Frame parent, ContinuousColorScale colorScale) {
        super(parent, true);
        this.colorScale = new ContinuousColorScale(colorScale);
        initComponents();

        minField.setText(String.valueOf(colorScale.getMinimum()));
        maxField.setText(String.valueOf(colorScale.getMaximum()));
        neutralFromField.setText(String.valueOf(colorScale.getNegStart()));
        neutralToField.setText(String.valueOf(colorScale.getPosStart()));

        negColor.setSelectedColor(colorScale.getMinColor());
        posColor.setSelectedColor(colorScale.getMaxColor());
        midColor.setSelectedColor(colorScale.getMidColor());
    }


    public boolean isCanceled() {
        return canceled;
    }


    public ContinuousColorScale getColorScale() {
        return colorScale;
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */

    private void initComponents() {
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        minField = new JTextField();
        maxField = new JTextField();
        neutralFromField = new JTextField();
        neutralToField = new JTextField();
        negColor = new ColorChooserPanel();
        posColor = new ColorChooserPanel();
        midColor = new ColorChooserPanel();
        legendPanel = new LegendPanel();
        panel3 = new JPanel();
        jPanel3 = new JPanel();
        cancelButton = new JButton();
        okButton1 = new JButton();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== jPanel2 ========
        {
            jPanel2.setBorder(null);


            //---- jLabel1 ----
            jLabel1.setText("Minimum");

            //---- jLabel2 ----
            jLabel2.setText("Maximum");

            //---- jLabel3 ----
            jLabel3.setText("Midrange");

            //---- jLabel4 ----
            jLabel4.setText("to");

            //---- minField ----
            minField.setText("jTextField1");
            minField.setPreferredSize(new Dimension(120, 28));
            minField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    minFieldFocusLost(e);
                }
            });

            //---- maxField ----
            maxField.setText("jTextField2");
            maxField.setPreferredSize(new Dimension(120, 28));
            maxField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    maxFieldFocusLost(e);
                }
            });

            //---- neutralFromField ----
            neutralFromField.setText("jTextField3");
            neutralFromField.setPreferredSize(new Dimension(120, 28));
            neutralFromField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    neutralFromFieldFocusLost(e);
                }
            });

            //---- neutralToField ----
            neutralToField.setText("jTextField4");
            neutralToField.setPreferredSize(new Dimension(120, 28));
            neutralToField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    neutralToFieldFocusLost(e);
                }
            });

            //---- negColor ----
            negColor.setPreferredSize(new Dimension(60, 28));
            negColor.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setNegColorAction(e);
                }
            });

            //---- posColor ----
            posColor.setPreferredSize(new Dimension(60, 28));
            posColor.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    posColorActionPerformed(e);
                }
            });

            //---- midColor ----
            midColor.setPreferredSize(new Dimension(60, 28));
            midColor.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    midColorActionPerformed(e);
                }
            });

            //======== legendPanel ========
            {
                legendPanel.setBackground(new Color(238, 238, 238));
                legendPanel.setOpaque(false);

                GroupLayout legendPanelLayout = new GroupLayout(legendPanel);
                legendPanel.setLayout(legendPanelLayout);
                legendPanelLayout.setHorizontalGroup(
                        legendPanelLayout.createParallelGroup()
                                .add(0, 443, Short.MAX_VALUE)
                );
                legendPanelLayout.setVerticalGroup(
                        legendPanelLayout.createParallelGroup()
                                .add(0, 54, Short.MAX_VALUE)
                );
            }

            //======== panel3 ========
            {
                panel3.setLayout(null);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel3.getComponentCount(); i++) {
                        Rectangle bounds = panel3.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel3.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel3.setMinimumSize(preferredSize);
                    panel3.setPreferredSize(preferredSize);
                }
            }

            GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup()
                            .add(jPanel2Layout.createSequentialGroup()
                                    .add(panel3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .add(6, 6, 6)
                                    .add(jPanel2Layout.createParallelGroup()
                                            .add(negColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .add(posColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .add(midColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .add(6, 6, 6)
                                    .add(jPanel2Layout.createParallelGroup()
                                            .add(jLabel1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                                            .add(jLabel2)
                                            .add(jLabel3, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
                                    .add(6, 6, 6)
                                    .add(jPanel2Layout.createParallelGroup()
                                            .add(minField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .add(maxField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .add(neutralFromField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .add(6, 6, 6)
                                    .add(jLabel4)
                                    .add(6, 6, 6)
                                    .add(neutralToField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .add(GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(legendPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
            );
            jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup()
                            .add(jPanel2Layout.createSequentialGroup()
                            .add(jPanel2Layout.createParallelGroup()
                                    .add(panel3, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                    .add(jPanel2Layout.createSequentialGroup()
                                            .add(negColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .add(5, 5, 5)
                                            .add(posColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .add(5, 5, 5)
                                            .add(midColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .add(jPanel2Layout.createSequentialGroup()
                                            .add(jLabel1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                            .add(5, 5, 5)
                                            .add(jLabel2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                            .add(5, 5, 5)
                                            .add(jLabel3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                                    .add(jPanel2Layout.createSequentialGroup()
                                            .add(minField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .add(5, 5, 5)
                                            .add(maxField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .add(5, 5, 5)
                                            .add(neutralFromField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .add(jPanel2Layout.createSequentialGroup()
                                            .add(66, 66, 66)
                                            .add(jLabel4, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                                    .add(jPanel2Layout.createSequentialGroup()
                                    .add(66, 66, 66)
                                    .add(neutralToField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .add(41, 41, 41)
                            .add(legendPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .add(41, 41, 41))
            );
        }
        contentPane.add(jPanel2);
        jPanel2.setBounds(10, 70, 465, 230);

        //======== jPanel3 ========
        {

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cancelButtonActionPerformed(e);
                }
            });

            //---- okButton1 ----
            okButton1.setText("OK");
            okButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    okButton1ActionPerformed(e);
                }
            });

            GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup()
                            .add(GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addContainerGap(291, Short.MAX_VALUE)
                            .add(cancelButton)
                            .addPreferredGap(LayoutStyle.RELATED)
                            .add(okButton1)
                            .add(12, 12, 12))
            );
            jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup()
                            .add(GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel3Layout.createParallelGroup(GroupLayout.BASELINE)
                                    .add(okButton1)
                                    .add(cancelButton))
                            .add(8, 8, 8))
            );
        }
        contentPane.add(jPanel3);
        jPanel3.setBounds(10, 295, 470, jPanel3.getPreferredSize().height);

        //---- label1 ----
        label1.setText("Heatmap settings for selected tracks");
        contentPane.add(label1);
        label1.setBounds(40, 10, 345, label1.getPreferredSize().height);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        canceled = true;
        setVisible(false);
    }

    private void okButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        setVisible(false);
    }

    private void setNegColorAction(java.awt.event.ActionEvent evt) {
        colorScale.setMinColor(negColor.getSelectedColor());
        legendPanel.repaint();

    }

    private void posColorActionPerformed(java.awt.event.ActionEvent evt) {
        colorScale.setMaxColor(posColor.getSelectedColor());
        legendPanel.repaint();
    }

    private void midColorActionPerformed(java.awt.event.ActionEvent evt) {
        colorScale.setMidColor(midColor.getSelectedColor());
        legendPanel.repaint();
    }

    private void minFieldFocusLost(java.awt.event.FocusEvent evt) {
        minFieldActionPerformed(null);
    }

    private void maxFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_maxFieldFocusLost
        maxFieldActionPerformed(null);
    }//GEN-LAST:event_maxFieldFocusLost

    private void neutralFromFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_neutralFromFieldFocusLost
        neutralFromFieldActionPerformed(null);
    }//GEN-LAST:event_neutralFromFieldFocusLost

    private void neutralToFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_neutralToFieldFocusLost
        neutralToFieldActionPerformed(null);
    }//GEN-LAST:event_neutralToFieldFocusLost

    private void minFieldActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            colorScale.setNegEnd(Double.parseDouble(minField.getText()));
            legendPanel.repaint();
        }
        catch (NumberFormatException e) {
            minField.setText(String.valueOf(colorScale.getMinimum()));
        }
    }

    private void maxFieldActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            colorScale.setPosEnd(Double.parseDouble(maxField.getText()));
            legendPanel.repaint();
        }
        catch (NumberFormatException e) {
            maxField.setText(String.valueOf(colorScale.getMaximum()));
        }
    }

    private void neutralFromFieldActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            colorScale.setNegStart(Double.parseDouble(neutralFromField.getText()));
            legendPanel.repaint();
        }
        catch (NumberFormatException e) {
            neutralFromField.setText(String.valueOf(colorScale.getNegStart()));
        }

    }

    private void neutralToFieldActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            colorScale.setPosStart(Double.parseDouble(neutralToField.getText()));
            legendPanel.repaint();
        }
        catch (NumberFormatException e) {
            neutralToField.setText(String.valueOf(colorScale.getPosStart()));
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HeatmapScaleDialog dialog = new HeatmapScaleDialog(new javax.swing.JFrame(),
                        (ContinuousColorScale) PreferencesManager.getPreferences().getDefaultColorScale(TrackType.COPY_NUMBER));
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    private JPanel jPanel2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField minField;
    private JTextField maxField;
    private JTextField neutralFromField;
    private JTextField neutralToField;
    private ColorChooserPanel negColor;
    private ColorChooserPanel posColor;
    private ColorChooserPanel midColor;
    private JPanel legendPanel;
    private JPanel panel3;
    private JPanel jPanel3;
    private JButton cancelButton;
    private JButton okButton1;
    private JLabel label1;


    class LegendPanel extends JPanel {


        public void paint(Graphics g) {

            super.paint(g);

            if (colorScale == null) {
                return;
            }

            DecimalFormat formatter = new DecimalFormat("0.0");

            Graphics2D g2D = null;

            try {
                g2D = (Graphics2D) g.create();
                if (PreferencesManager.getPreferences().getAntiAliasing()) {
                    g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                }
                g2D.setFont(FontManager.getFont(10));


                double max = colorScale.getMaximum();
                double min = colorScale.getMinimum();
                double neutralFrom = colorScale.getNegStart();
                double neutralTo = colorScale.getPosStart();


                // Width and height of the legend.
                int margin = 10;
                int w = getWidth() - 2 * margin;
                int h = getHeight() / 2;

                // Want to vary value from min -> max over legend
                int npts = 50;
                double dx = ((double) w) / npts;
                double delta = (max - min) / (npts - 1);

                // Paint gradient
                for (int i = 0; i < npts - 1; i++) {
                    double val = min + i * delta;
                    Color c = colorScale.getColor((float) val);
                    g2D.setColor(c);

                    int x0 = margin + (int) (i * dx);
                    int x1 = margin + (int) ((i + 1) * dx);
                    g2D.fillRect(x0, 0, (x1 - x0), h);
                }

                // Paint tickmarks
                double[] values = {min, neutralFrom, neutralTo, max};
                double wPrime = (w - 2 * margin - dx) / (max - min);
                g2D.setColor(Color.BLACK);
                for (int i = 0; i < values.length; i++) {
                    double val = values[i];
                    int x0 = (int) (margin + dx + (val - min) * wPrime);
                    g2D.fillRect(x0, 0, 2, h);
                    g2D.drawString(formatter.format(val), x0 - 10, h + 15);
                }
            }
            finally {
                g2D.dispose();
            }
        }
    }

}
