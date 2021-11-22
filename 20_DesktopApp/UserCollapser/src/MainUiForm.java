import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainUiForm {

  private JPanel mainPanel;
  private JButton collapseButton;
  private JPanel centralPanel;
  private JTextField lastNameField;
  private JTextField firstNameField;
  private JTextField middleNameField;
  private JLabel lastName;
  private JLabel firstName;
  private JLabel middleName;
  private JCheckBox checkMidNameBox;
  private JPanel downPanel;
  private JPanel backPanel;
  private JTextArea textArea;
  private JLabel collapsedText;
  private JLabel note;

  public JPanel getMainPanel() {
    return mainPanel;
  }

  public MainUiForm() {
    collapseButton.addActionListener(new Action() {
      @Override
      public Object getValue(String key) {
        return null;
      }

      @Override
      public void putValue(String key, Object value) {

      }

      @Override
      public void setEnabled(boolean b) {

      }

      @Override
      public boolean isEnabled() {
        return false;
      }

      @Override
      public void addPropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void removePropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void actionPerformed(ActionEvent e) {
        {
          switch (collapseButton.getText()) {
            case Constants.COLLAPSE -> {
              String firstName = firstNameField.getText();
              String lastName = lastNameField.getText();
              if (isEmpty(firstName, lastName)) {
                showEmptyFieldError();
              } else if (isWrongFormat(firstName, lastName)) {
                showWrongFormatError();
              } else {
                collapseText();
              }
            }
            case Constants.EXPAND -> {
              if (isTextValid(textArea.getText().split(" "))){
                showWrongFormatError();
              } else
              expandText();
            }
          }
        }
      }
    });

    checkMidNameBox.addActionListener(new Action() {
      @Override
      public Object getValue(String key) {
        return null;
      }

      @Override
      public void putValue(String key, Object value) {

      }

      @Override
      public void setEnabled(boolean b) {

      }

      @Override
      public boolean isEnabled() {
        return false;
      }

      @Override
      public void addPropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void removePropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void actionPerformed(ActionEvent e) {
        checkBoxActon();
      }
    });

  }

  private void createUIComponents() {
    // TODO: place custom component creation code here
  }

  public void checkBoxActon() {
    if (checkMidNameBox.isSelected()) {
      middleNameField.setEnabled(false);
      middleNameField.setText(Constants.EMPTY_STRING);
    } else {
      middleNameField.setEnabled(true);
    }
  }

  private boolean isEmpty(String firstName, String lastname) {
    return (firstName.isEmpty() || lastname.isEmpty());
  }

  private boolean isWrongFormat(String firstName, String lastName) {
    return (!firstName.matches(Constants.NAME_FORM) ||
        !lastName.matches(Constants.LAST_NAME_FORM));
  }

  private boolean isTextValid(String[] values){
    return values.length < 2 || isWrongFormat(values[1], values[0]);
  }

  private void showWrongFormatError() {
    JOptionPane.showMessageDialog(mainPanel,
        "Введены некорректные данные",
        "Неверный формат ФИО",
        JOptionPane.PLAIN_MESSAGE);
  }

  private void showEmptyFieldError() {
    JOptionPane.showMessageDialog(mainPanel,
        "Заполните все поля",
        "Не все поля заполнены",
        JOptionPane.PLAIN_MESSAGE);
  }

  private void collapseText() {
    textArea.setVisible(true);
    String fln = lastNameField.getText() + " "
        + firstNameField.getText() + " "
        + middleNameField.getText();
    firstNameField.setText(Constants.EMPTY_STRING);
    lastNameField.setText(Constants.EMPTY_STRING);
    middleNameField.setText(Constants.EMPTY_STRING);
    textArea.setText(fln);
    firstName.setVisible(false);
    firstNameField.setVisible(false);
    lastName.setVisible(false);
    lastNameField.setVisible(false);
    middleName.setVisible(false);
    middleNameField.setVisible(false);
    checkMidNameBox.setVisible(false);
    collapsedText.setVisible(true);
    note.setVisible(false);
    collapseButton.setText(Constants.EXPAND);
  }

  private void expandText() {
      split(textArea.getText());
      firstName.setVisible(true);
      firstNameField.setVisible(true);
      lastName.setVisible(true);
      lastNameField.setVisible(true);
      middleName.setVisible(true);
      middleNameField.setVisible(true);
      textArea.setVisible(false);
      checkMidNameBox.setVisible(true);
      collapsedText.setVisible(false);
      note.setVisible(true);
      checkMidNameBox.setSelected(false);
      middleNameField.setEnabled(true);
      collapseButton.setText(Constants.COLLAPSE);
  }

  private void split(String content) {
    String[] values = content.split(" ", 3);
    switch (values.length) {
      case 2 -> {
        lastNameField.setText(values[0]);
        firstNameField.setText(values[1]);
        middleNameField.setText(Constants.EMPTY_STRING);
      }
      case 3 -> {
        lastNameField.setText(values[0]);
        firstNameField.setText(values[1]);
        middleNameField.setText(values[2]);
      }
    }
  }

}
