package pt.upskill.projeto2.utils;

import javax.swing.JOptionPane;

/**
 * @author upSkill 2020
 * <p>
 * ...
 */

public class Menu {

    public static int yesOrNoInput(String message, String title) {
        int option = JOptionPane.showConfirmDialog(null,message,title,JOptionPane.YES_NO_OPTION);
        return option;
    }
    public static String requestInput(String message) {
        return JOptionPane.showInputDialog(message);
    }

    public static String requestSelection(String name, String[] options) {
        String option = ((String) JOptionPane.showInputDialog(null,
                "Escolha uma opção", name, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]));
        return option;
    }

}
