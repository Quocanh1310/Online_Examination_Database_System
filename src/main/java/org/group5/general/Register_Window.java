package org.group5.general;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import org.group5.connectionSQL.MyConnection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Window.Type;

public class Register_Window extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> comboBox;
    private JLabel label;
    private JLabel userLabel;
    private JLabel passLabel;
    private JCheckBox showPass;
    private JButton summit;
    private Image backgroundImage;

    private boolean checkLength;
    private boolean checkComboBox;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JTextField phoneInput;

    public Register_Window(MainMenu mainmenu) {
    	setType(Type.UTILITY);
    	setBackground(new Color(255, 255, 255));
    	setTitle("EduExam");
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        URL imageURL = MainMenu.class.getResource("/bg.jpg");
        if(imageURL != null) {
            backgroundImage = new ImageIcon(imageURL).getImage();
        } else {
            System.out.println("imageURL is null");
        }

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 880, 550);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setForeground(new Color(255, 255, 51));
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        comboBox = new JComboBox<>();
        comboBox.setForeground(new Color(0, 0, 0));
        comboBox.setFont(new Font("Dubai Medium", Font.PLAIN, 14));
        comboBox.setBackground(new Color(255, 153, 255));
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Teacher", "Student"}));
        comboBox.setBounds(359, 120, 172, 22);
        contentPane.add(comboBox);

        label = new JLabel("You are a ");
        label.setForeground(new Color(0, 0, 0));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial Rounded MT Bold", Font.BOLD | Font.ITALIC, 30));
        label.setBounds(359, 77, 172, 33);
        contentPane.add(label);

        userLabel = new JLabel("User name");
        userLabel.setForeground(new Color(0, 0, 0));
        userLabel.setFont(new Font("Dubai Medium", Font.PLAIN, 24));
        userLabel.setBounds(111, 182, 111, 17);
        contentPane.add(userLabel);

        passLabel = new JLabel("Password");
        passLabel.setForeground(new Color(0, 0, 0));
        passLabel.setFont(new Font("Dubai Medium", Font.PLAIN, 24));
        passLabel.setBounds(111, 261, 101, 20);
        contentPane.add(passLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Dubai Medium", Font.PLAIN, 12));
        usernameField.setBackground(new Color(255, 153, 255));
        usernameField.setBounds(232, 183, 157, 20);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Dubai Medium", Font.PLAIN, 12));
        passwordField.setBackground(new Color(255, 153, 255));
        passwordField.setBounds(232, 261, 157, 20);
        contentPane.add(passwordField);

        showPass = new JCheckBox("Show password");
        showPass.setForeground(new Color(0, 0, 0));
        showPass.setFont(new Font("Dubai Medium", Font.PLAIN, 12));
        showPass.setBackground(new Color(255, 255, 255));
        showPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPass.isSelected())
                    passwordField.setEchoChar((char) 0);
                else
                    passwordField.setEchoChar('*');
            }
        });
        showPass.setBounds(232, 287, 120, 23);
        contentPane.add(showPass);

        summit = new JButton("Register");
        summit.setForeground(new Color(0, 0, 0));
        summit.setBackground(new Color(255, 255, 51));
        summit.setFont(new Font("Dubai Medium", Font.PLAIN, 12));
        summit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                summitAction();
            }
        });
        summit.setBounds(317, 398, 89, 33);
        contentPane.add(summit);

        JButton back = new JButton("Back");
        back.setBackground(new Color(255, 255, 51));
        back.setFont(new Font("Dubai Medium", Font.PLAIN, 12));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
                comboBox.setSelectedIndex(0);
                showPass.setSelected(false);
                setVisible(false);
                mainmenu.setVisible(true);
            }
        });
        back.setBounds(480, 398, 89, 33);
        contentPane.add(back);

        JLabel lastName = new JLabel("Last Name");
        lastName.setForeground(new Color(0, 0, 0));
        lastName.setFont(new Font("Dubai Medium", Font.PLAIN, 24));
        lastName.setBounds(469, 183, 133, 22);
        contentPane.add(lastName);

        lastNameInput = new JTextField();
        lastNameInput.setFont(new Font("Dubai Medium", Font.PLAIN, 12));
        lastNameInput.setBackground(new Color(255, 153, 255));
        lastNameInput.setBounds(599, 185, 157, 20);
        contentPane.add(lastNameInput);
        lastNameInput.setColumns(10);

        JLabel firstName = new JLabel("First Name");
        firstName.setForeground(new Color(0, 0, 0));
        firstName.setFont(new Font("Dubai Medium", Font.PLAIN, 24));
        firstName.setBounds(469, 260, 133, 22);
        contentPane.add(firstName);

        firstNameInput = new JTextField();
        firstNameInput.setFont(new Font("Dubai Medium", Font.PLAIN, 12));
        firstNameInput.setBackground(new Color(255, 153, 255));
        firstNameInput.setColumns(10);
        firstNameInput.setBounds(599, 261, 157, 20);
        contentPane.add(firstNameInput);

        JLabel phoneNum = new JLabel("Phone Number");
        phoneNum.setHorizontalAlignment(SwingConstants.CENTER);
        phoneNum.setFont(new Font("Dubai Medium", Font.PLAIN, 24));
        phoneNum.setBounds(359, 319, 172, 33);
        contentPane.add(phoneNum);

        phoneInput = new JTextField();
        phoneInput.setFont(new Font("Dubai Medium", Font.PLAIN, 12));
        phoneInput.setBackground(new Color(255, 153, 255));
        phoneInput.setColumns(10);
        phoneInput.setBounds(359, 360, 172, 20);
        contentPane.add(phoneInput);
        setVisible(true);
    }

    private void summitAction() {
        if (phoneInput.getText().equals("") || lastNameInput.getText().equals("") || firstNameInput.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "You have to fill up everything !", "Warning!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String lastName = lastNameInput.getText();
        String firstName = firstNameInput.getText();
        String phoneNum = phoneInput.getText();

        checkLength = checkLength(usernameField.getText().length(), passwordField.getPassword().length);
        checkComboBox = checkcomboBox((String) comboBox.getSelectedItem());
        boolean checkUsername = false;

        try {
            if (!checkExistedAccount(usernameField.getText(), (String) comboBox.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "This username is already existed", "Warning!", JOptionPane.WARNING_MESSAGE);
                checkUsername = false;
            } else {
                checkUsername = true;
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        if (checkLength && checkComboBox && checkUsername) {
            try {
                createUserAccount(usernameField.getText(), passwordField.getPassword(), (String) comboBox.getSelectedItem(), lastName, firstName, phoneNum);
                usernameField.setText("");
                passwordField.setText("");
                lastNameInput.setText("");
                firstNameInput.setText("");
                phoneInput.setText("");
                comboBox.setSelectedIndex(0);
                showPass.setSelected(false);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void createUserAccount(String name, char[] pass, String role, String firstName, String lastName, String phoneNum) throws SQLException {
        String password = new String(pass);
        int ID = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = MyConnection.getConnection();

            StringBuilder query = new StringBuilder();
            query.append("INSERT INTO accounts (user_Name, pass_word, role) VALUES (?,?,?)");

            PreparedStatement ps = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.executeUpdate();

            ResultSet pK = ps.getGeneratedKeys();
            if (pK.next())
                ID = pK.getInt(1);
            pushTeacherOrStudentInfomation(ID, role, firstName, lastName, phoneNum);

            JOptionPane.showMessageDialog(null, "Successfully", "Information!", JOptionPane.INFORMATION_MESSAGE);
            ps.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void pushTeacherOrStudentInfomation(int userID, String role, String firstName, String lastName, String phoneNum) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = MyConnection.getConnection();

            StringBuilder query = new StringBuilder();
            query.append("INSERT INTO " + role + " (user_id, firstName, lastName, phoneNumber) VALUES (?,?,?,?)");

            PreparedStatement ps = connection.prepareStatement(query.toString());

            ps.setInt(1, userID);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, phoneNum);
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean checkExistedAccount(String nameToCheck, String selection) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = "SELECT user_Name FROM test.accounts WHERE user_Name = ?;";

        boolean checkExist = true;
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nameToCheck);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                checkExist = false;

            ps.close();
            conn.close();
            return checkExist;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean checkLength(int username, int password) {
        if (username >= 20 || username == 0) {
            JOptionPane.showMessageDialog(null, "Your username length must be below 20, greater than 0", "Warning!", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (password >= 20 || password == 0) {
            JOptionPane.showMessageDialog(null, "Your password length must be below 20, greater than 0", "Warning!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean checkcomboBox(String comboBox) {
        if (comboBox.equals("")) {
            JOptionPane.showMessageDialog(null, "Your selection is empty", "Warning!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}