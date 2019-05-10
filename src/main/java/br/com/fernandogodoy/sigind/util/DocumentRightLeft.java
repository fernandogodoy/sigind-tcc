package br.com.fernandogodoy.sigind.util;


import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fernando
 */
public class DocumentRightLeft extends PlainDocument {

    private Integer maxCaracter = 0;
//    Pattern pattern = Pattern.compile("[a-zA-ZâÂãÃáÁàÀäÄéÉèÈêÊëËíÍìÌîÎïÏóÓòÒôÔõÕöÖúÚùÙûÛüÜçÇ~´`;:.,\\/|]");
    Pattern pattern = Pattern.compile("[^0-9]");

    public DocumentRightLeft(Integer maxCaracter) {
        this.maxCaracter = maxCaracter;
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        Integer tamanho = getLength();
        if (tamanho < maxCaracter) {
            String texto = getText(0, getLength());
            remove(0, getLength());
            super.insertString(0, new StringBuffer(texto + str.replaceAll(pattern.toString(), "")).toString(), a);
        } else {
            super.insertString(0, "", a);
        }
    }
}
