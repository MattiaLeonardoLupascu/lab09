package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser{
    private static final String TITLE = "A very simple GUI application";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);
    public SimpleGUIWithFileChooser(){
        Controller controller = new Controller();
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        canvas.add(textArea,BorderLayout.CENTER);
        JButton save = new JButton("Save");
        canvas.add(save,BorderLayout.SOUTH);
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BorderLayout());
        JTextField newTextField = new JTextField();
        newTextField.setEditable(false);
        newPanel.add(newTextField,BorderLayout.CENTER);
        JButton browse = new JButton("Browse...");
        newPanel.add(browse,BorderLayout.EAST);
        canvas.add(newPanel,BorderLayout.NORTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
        Handlers
        */
        save.addActionListener((ActionListener) -> {
            controller.writeOnFile(textArea.getText());
        });
        browse.addActionListener((ActionEvent) -> {
            JFileChooser fileChooser = new JFileChooser(); 
            int result = fileChooser.showSaveDialog(frame);
            if (result == fileChooser.APPROVE_OPTION){
                controller.setCurrentFile(fileChooser.getSelectedFile().toString());
                newTextField.setText(fileChooser.getSelectedFile().toString());
            }
            if (result == fileChooser.CANCEL_OPTION){
                JOptionPane.showMessageDialog(frame,"Cancelled operation...");
            }
        });
        
    }
    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*resize the frame to the minimum size prior to displaying */
        frame.pack();
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }
    public static void main() {
        new SimpleGUIWithFileChooser().display();
    }
}
