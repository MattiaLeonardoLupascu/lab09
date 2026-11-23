package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {
    private final JFrame frame = new JFrame();

    public SimpleGUI(){
        SimpleController controller = new SimpleController();
        JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        JTextField textField = new JTextField();
        canvas.add(textField);
        // Add another Jpanel for having the 2 buttons below the text field
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BorderLayout());
        canvas.add(newPanel,BorderLayout.SOUTH);
        JButton print = new JButton("Print");
        newPanel.add(print,BorderLayout.EAST);
        JButton history = new JButton("Show history");
        newPanel.add(history,BorderLayout.WEST);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Handlers */

        print.addActionListener((ActionEvent l) -> {
            controller.setNextString(textField.getText());
            IO.println("Print:");
            controller.printOnStandardOutput();
            IO.println("End of Print.");
        });

        history.addActionListener((ActionEvent l) -> {
            IO.println("History:");
            for (String elem : controller.getHistory()) {
                IO.println(elem);
            }
            IO.println("End of History.");
        });
    }

    private void display(){
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main() {
        new SimpleGUI().display();
    }
}
