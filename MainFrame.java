import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MainFrame extends JFrame implements ActionListener, KeyListener {

    private JTextField deciField, binField, octField, hexField;
    private final DecimalSubject decimalSubject;
    private final BinaryObserver binaryObserver;
    private final OctalObserver octalObserver;
    private final HexObserver hexObserver;
    private JCheckBox binBox, octBox, hexBox;

    public MainFrame() {
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        decimalSubject = new DecimalSubject();
        binaryObserver = new BinaryObserver();
        octalObserver = new OctalObserver();
        hexObserver = new HexObserver();
        init();
    }

    private void init() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JLabel deciLabel = new JLabel("Enter decimal value");
        deciField = new JTextField(20);
        deciField.setPreferredSize(new Dimension(25, 20));
        deciField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        deciField.addKeyListener(this);

        topPanel.add(deciLabel);
        topPanel.add(deciField);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));

        JPanel binPanel = new JPanel(new GridLayout(2, 1));
        binBox = new JCheckBox("Binary value");
        binBox.setFocusPainted(false);
        binBox.addActionListener(this);
        binField = new JTextField(20);
        binField.setEditable(false);
        binPanel.add(binBox);
        binPanel.add(binField);

        JPanel octPanel = new JPanel(new GridLayout(2, 1));
        octBox = new JCheckBox("Octal value");
        octBox.setFocusPainted(false);
        octBox.addActionListener(this);
        octField = new JTextField(20);
        octField.setEditable(false);
        octPanel.add(octBox);
        octPanel.add(octField);

        JPanel hexPanel = new JPanel(new GridLayout(2, 1));
        hexBox = new JCheckBox("Hex value");
        hexBox.setFocusPainted(false);
        hexBox.addActionListener(this);
        hexField = new JTextField(20);
        hexField.setEditable(false);
        hexPanel.add(hexBox);
        hexPanel.add(hexField);

        bottomPanel.add(binPanel);
        bottomPanel.add(octPanel);
        bottomPanel.add(hexPanel);

        panel.add(topPanel);
        panel.add(bottomPanel);

        add(panel);
    }

    private void updateValues() {
        binField.setText(binaryObserver.getBinary());
        octField.setText(octalObserver.getOctal());
        hexField.setText(hexObserver.getHex());
    }

    private void setDecimalSubject(Integer decimal) {
        decimalSubject.setNumber(decimal);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if ((e.getKeyCode() >= 48 && e.getKeyCode() <= 57) || e.getKeyCode() == 8) {
            if (deciField.getText().isEmpty()) {
                setDecimalSubject(null);
                updateValues();
                return;
            }
            String decimalStr = deciField.getText();
            int decimal;
            try {
                decimal = Integer.parseInt(decimalStr);
            } catch (NumberFormatException ex) {
                return;
            }

            setDecimalSubject(decimal);
            updateValues();
        } else {
            String text = deciField.getText();
            deciField.setText(text.substring(0, text.length() - 1));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("binary value")) {
            if (binBox.isSelected()) {
                decimalSubject.attach(binaryObserver);
                decimalSubject.notifyObservers();
            } else {
                binaryObserver.setBinary("");
                decimalSubject.detach(binaryObserver);
            }
            updateValues();
        } else if (e.getActionCommand().equalsIgnoreCase("octal value")) {
            if (octBox.isSelected()) {
                decimalSubject.attach(octalObserver);
                decimalSubject.notifyObservers();
            } else {
                octalObserver.setOctal("");
                decimalSubject.detach(octalObserver);
            }
            updateValues();
        } else if (e.getActionCommand().equalsIgnoreCase("hex value")) {
            if (hexBox.isSelected()) {
                decimalSubject.attach(hexObserver);
                decimalSubject.notifyObservers();
            } else {
                hexObserver.setHex("");
                decimalSubject.detach(hexObserver);
            }
            updateValues();
        }
    }
}
