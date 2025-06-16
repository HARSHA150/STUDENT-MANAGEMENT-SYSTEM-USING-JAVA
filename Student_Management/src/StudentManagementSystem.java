import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementSystem extends JFrame {
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    public class Student {
        private String name;
        private int age;
        private String grade;
    
        public Student(String name, int age, String grade) {
            this.name = name;
            this.age = age;
            this.grade = grade;
        }
    
        public String getName() {
            return name;
        }
    
        public int getAge() {
            return age;
        }
    
        public String getGrade() {
            return grade;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public void setAge(int age) {
            this.age = age;
        }
    
        public void setGrade(String grade) {
            this.grade = grade;
        }
    }
    public StudentManagementSystem() {
        // Set up the JFrame
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        tableModel = new DefaultTableModel(new Object[]{"Name", "Age", "Grade"}, 0);
        studentTable = new JTable(tableModel);
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        // Add components to the JFrame
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
    }

    private void addStudent() {
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField gradeField = new JTextField();

        Object[] fields = {
                "Name:", nameField,
                "Age:", ageField,
                "Grade:", gradeField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String grade = gradeField.getText();
            tableModel.addRow(new Object[]{name, age, grade});
        }
    }

    private void editStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow >= 0) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            int age = (Integer) tableModel.getValueAt(selectedRow, 1);
            String grade = (String) tableModel.getValueAt(selectedRow, 2);

            JTextField nameField = new JTextField(name);
            JTextField ageField = new JTextField(String.valueOf(age));
            JTextField gradeField = new JTextField(grade);

            Object[] fields = {
                    "Name:", nameField,
                    "Age:", ageField,
                    "Grade:", gradeField
            };

            int option = JOptionPane.showConfirmDialog(this, fields, "Edit Student", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                tableModel.setValueAt(nameField.getText(), selectedRow, 0);
                tableModel.setValueAt(Integer.parseInt(ageField.getText()), selectedRow, 1);
                tableModel.setValueAt(gradeField.getText(), selectedRow, 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to edit", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementSystem app = new StudentManagementSystem();
            app.setVisible(true);
        });
    }
}
