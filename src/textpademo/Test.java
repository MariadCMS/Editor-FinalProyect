/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textpademo;

/**
 *
 * @author MdCarmen
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;

public class Test extends JFrame {

    Test(JTextPane jTextPane) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int findLastNonWordChar (String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar (String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }

    public Test () {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);

        final StyleContext cont = StyleContext.getDefaultStyleContext();
        final AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLUE);
        final AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
        DefaultStyledDocument doc = new DefaultStyledDocument() {
            public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        //if (text.substring(wordL, wordR).matches("(\\W)*(private|public|protected)"))
                            //if (text.substring(wordL, wordR).matches("(\\W)*(<html>|<head>|<title>|</title>|</head>|<body>|</body>|</html>)"))
                             //if (text.substring(wordL, wordR).matches("(\\W)*(HTML|HEAD|TITLE|BODY)"))   
                            if (text.substring(wordL, wordR).matches("(\\W)*(ADDRESS|APPLET|AREA|A|BASE|BASEFONT|BIG|BLOCKQUOTE|BODY|BR|B|CAPTION|CENTER|CITE|CODE|DD|DFN|DIR|DIV|DL|DT|EM|FONT|FORM|H1|H2|H3|H4|H5|H6|HEAD|HR|HTML|IMG|INPUT|ISINDEX|I|KBD|LINK|LI|MAP|MENU|META|OL|OPTION|PARAM|PRE|P|SAMP|SCRIPT|SELECT|SMALL|STRIKE|STRONG|STYLE|SUB|SUP|TABLE|TD|TEXTAREA|TH|TITLE|TR|TT|UL|U|VAR|)"))
                                 setCharacterAttributes(wordL, wordR - wordL, attr, false);
                        else
                            setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove (int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offs);

              //  if (text.substring(before, after).matches("(\\W)*(private|public|protected)")) {
                   //if (text.substring(before, after).matches("(\\W)*(<html>|<head>|<title>|</title>|</head>|<body>|</body>|</html>)")) {
                  // if (text.substring(before, after).matches("(\\W)*(HTML|HEAD|TITLE|BODY)")) {
                       if (text.substring(before, after).matches("(\\W)*(ADDRESS|APPLET|AREA|A|BASE|BASEFONT|BIG|BLOCKQUOTE|BODY|BR|B|CAPTION|CENTER|CITE|CODE|DD|DFN|DIR|DIV|DL|DT|EM|FONT|FORM|H1|H2|H3|H4|H5|H6|HEAD|HR|HTML|IMG|INPUT|ISINDEX|I|KBD|LINK|LI|MAP|MENU|META|OL|OPTION|PARAM|PRE|P|SAMP|SCRIPT|SELECT|SMALL|STRIKE|STRONG|STYLE|SUB|SUP|TABLE|TD|TEXTAREA|TH|TITLE|TR|TT|UL|U|VAR|)")) {
                       setCharacterAttributes(before, after - before, attr, false);
                } else {
                    setCharacterAttributes(before, after - before, attrBlack, false);
                }
            }
        };
        JTextPane txt = new JTextPane(doc);
        txt.setText("");
        add(new JScrollPane(txt));
        setVisible(true);
    }

    public static void main (String args[]) {
        new Test();
    }
}
