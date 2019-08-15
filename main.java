import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.EventQueue;

public class main{
  public static void main(String[] args){

    String screenSizes[] = { "896x504", "1280x720" , "1408x792", "1664x936", "1920x1080" }; // 16:9
    int widthArray[] = { 896, 1280, 1408, 1664, 1920 };
    int heightArray[] = { 504, 720, 792, 936, 1080 };

    JFrame sizeSelectionFrame = new JFrame();
    sizeSelectionFrame.setSize(400, 300);
    sizeSelectionFrame.setResizable(false);
    sizeSelectionFrame.setVisible(true);
    sizeSelectionFrame.setLayout(null);
    sizeSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    sizeSelectionFrame.setTitle("Selecao de tamanho de tela");

    JLabel titleName = new JLabel("Tic-Tac-Toe");
    JComboBox comboResolution = new JComboBox(screenSizes);
    JButton comboSelect = new JButton("Selecionar");
    
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        titleName.setBounds(35, 50, 350, 50);
        titleName.setVisible(true);
        titleName.setFont(new Font("Serif", Font.BOLD, 48));

        comboResolution.setSelectedIndex(1);
        comboResolution.setBounds(75,150,250,50);
        
        comboSelect.setBounds(140, 210, 120, 40);
        comboSelect.addActionListener(new ActionListener(){
        
          @Override
          public void actionPerformed(ActionEvent e) {
            int width = widthArray[comboResolution.getSelectedIndex()];
            int heigh = heightArray[comboResolution.getSelectedIndex()];

            Screens screens = new Screens(width, heigh); // seta a proporcao da tela
            screens.mainMenuScreen();

            sizeSelectionFrame.setVisible(false);
          }
        });
      }
    });
    
    sizeSelectionFrame.add(comboSelect);
    sizeSelectionFrame.add(titleName);
    sizeSelectionFrame.add(comboResolution);
    sizeSelectionFrame.getContentPane().validate();
  }
}