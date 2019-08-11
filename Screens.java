import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class Screens{

  private JFrame mainFrame = new JFrame();

  public Screens(int width, int height) {

    this.mainFrame.setSize(width, height);
    this.mainFrame.setVisible(true);
    this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.mainFrame.setTitle("Tic-Tac-Toe");
  }

  private void resetJFrameComponent(){

    System.out.println(this.mainFrame.getContentPane().getComponentCount());
    if(this.mainFrame.getContentPane().getComponentCount() > 1){
      this.mainFrame.getContentPane().remove(0);
      this.mainFrame.getContentPane().validate();
    }
  }

  public void mainMenuScreen(){

    JPanel mainScreenPanel = new JPanel();
    mainScreenPanel.setSize(this.mainFrame.getWidth(), this.mainFrame.getHeight());
    mainScreenPanel.setLayout(null); // para nao atrapalhar ja que o posicionamento esta sendo feito de maneira absoluta

    JLabel gameName = new JLabel("Tic-Tac-Toe");
    gameName.setBounds(100, 100, 600, 100);
    gameName.setFont(new Font("Serif", Font.PLAIN, 98));

    JButton btnPlayerVsPlayer = new JButton("Jogador Vs Jogador");
    btnPlayerVsPlayer.setBounds(200, 230, 400, 70);
    btnPlayerVsPlayer.addActionListener(new ActionListener(){
    
      @Override
      public void actionPerformed(ActionEvent e) {
        
        gameScreen(true, false);
      }
    });

    JButton btnPlayerVsComputer = new JButton("Jogador Vs Computador");
    btnPlayerVsComputer.setBounds(200, 330, 400, 70);
    btnPlayerVsComputer.addActionListener(new ActionListener(){
    
      @Override
      public void actionPerformed(ActionEvent e) {

        chooseComputerDifficultyPopUp();
      }
    });

    JButton btnExitGame = new JButton("Sair do Jogo");
    btnExitGame.setBounds(200, 460, 400, 70);
    btnExitGame.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e){

        System.exit(0);
      }
    });

    mainScreenPanel.add(gameName);
    mainScreenPanel.add(btnPlayerVsPlayer);
    mainScreenPanel.add(btnPlayerVsComputer);
    mainScreenPanel.add(btnExitGame);

    this.mainFrame.add(mainScreenPanel);

    this.resetJFrameComponent();
  }

  private void chooseComputerDifficultyPopUp(){

    Object[] options = {"Facil",
      "Dificil"};

    JOptionPane.showOptionDialog(this.mainFrame,
      "Escolha o nivel de dificuldade do Computador:",
      "Jogador Vs Computador",
      JOptionPane.YES_NO_OPTION,
      JOptionPane.QUESTION_MESSAGE,
      null,
      options,
      options[1]);
  }

  private void gameScreen(boolean isPlayerVsPlayer, boolean isEasy){

    JPanel gameScreenPanel = new JPanel();
    gameScreenPanel.setSize(this.mainFrame.getWidth(), this.mainFrame.getHeight());
    gameScreenPanel.setLayout(null); // para nao atrapalhar ja que o posicionamento esta sendo feito de maneira absoluta

    JPanel gameBoardPanel = new JPanel();
    gameBoardPanel.setSize(this.mainFrame.getWidth(), this.mainFrame.getHeight());
    gameBoardPanel.setLayout(new GridLayout(3,3)); // tabuleiro

    JLabel labelX = new JLabel("X");
    JLabel labelO = new JLabel("O");

    JLabel labelWinsX = new JLabel("Vitorias: ");
    JLabel xWins = new JLabel(0);
    JLabel labelWinsO = new JLabel("Vitorias: ");
    JLabel oWins = new JLabel(0);

    JLabel labelTies = new JLabel("Empates: ");
    JLabel ties = new JLabel(0);

    JButton boardCells[][] = new JButton[3][3];

    if(isPlayerVsPlayer){


    }else{
      if(isEasy){


      }else{


      }
    }

    this.mainFrame.add(gameScreenPanel);

    this.resetJFrameComponent();

  }

}