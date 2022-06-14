package View;

import Model.InputModel;
import Model.Model;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Home {
    JFrame f;
    // Table
    JTable j;

    // Constructor

    Home(File a, File b) throws IOException {
        InputModel model = new InputModel();
        model.input(a, b);
        model.calculate();

        List<Model> result = model.result;
        // Frame initialization
        f = new JFrame();

        // Frame Title
        f.setTitle("Bioinformatics Toolkit");
        // Data to be displayed in the JTable
        Object[][] data = new Object[result.size()][7];
        int idx = 0;
        for(Model m : result) {
            data[idx] = m.getModel();
            idx++;
        }
        // Column Names
        String[] columnNames = { "S.No", "Gene Name", "Gene Sequence Length", "Synonymous Change", "Non Synonymous Change", "Synonymous Sites", "Non Synonymous Sites", "dn", "ds", "dn/ds"};

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setFont(new Font("Arial", Font.PLAIN, 14));
        j.setRowHeight(25);
        j.getTableHeader().setFont( new Font( "Arial" , Font.BOLD, 15 ));
        j.setBackground(new Color(235, 235, 235));
        j.setBounds(100, 60, 200, 300);


        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
    }

    public static boolean exportToCSV(JTable tableToExport,
                                      String pathToExportTo) {

        try {

            TableModel model = tableToExport.getModel();
            FileWriter csv = new FileWriter(new File(pathToExportTo));

            for (int i = 0; i < model.getColumnCount(); i++) {
                csv.write(model.getColumnName(i) + ",");
            }

            csv.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    csv.write(model.getValueAt(i, j).toString() + ",");
                }
                csv.write("\n");
            }

            csv.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
