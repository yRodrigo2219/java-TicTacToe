/* ***************************************************************
* Autor: Rodrigo Santos do Carmo
* Matricula: 
* Inicio: 11/08/2019
* Ultima alteracao: 15/08/2019
* Nome: Tic-Tac-Toe
* Funcao: Um jogo da velha com diferentes modos de jogo, onde eh possi-
          vel jogar contra outro player (offline) ou contra o computador
          em duas dificuldades, facil e dificil, onde o facil joga de ma-
          neira random e o dificil nao perde
*************************************************************** */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.EventQueue;

public class Principal{
  /* ***************************************************************
  * Metodo: main
  * Funcao: Ponto inicial do programa, cria a tela de selecao de resolucao
  * Parametros: 
  * Retorno: 
  *************************************************************** */
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