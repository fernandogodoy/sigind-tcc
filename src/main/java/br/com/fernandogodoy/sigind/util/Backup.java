/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernado
 */
public class Backup {
    
    public void realizaBackup() throws IOException {
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec("\"C:\\Program Files (x86)\\PostgreSQL\\8.4\\bin\\pg_dump.exe\" -i -h localhost -p 5432 -U postgres -F c -b -v -f \"C:\\Users\\Fernado\\Desktop\\Sigind.sql\" sigind");
            if (p != null) {
                OutputStream outputStream = p.getOutputStream();
                outputStream.write("senha\r\n".getBytes());
                outputStream.flush();
                outputStream.close();
                InputStreamReader streamReader = new InputStreamReader(p.getErrorStream());
                BufferedReader reader = new BufferedReader(streamReader);
                String linha;
                while ((linha = reader.readLine()) != null) {
                    System.out.println(linha);
                }
            }
            JOptionPane.showMessageDialog(null, "Backup realizado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar realizar o backup!\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }


    }

    public static void main(String args[]) throws IOException {
        Backup b = new Backup();
        b.realizaBackup();

    }
}
