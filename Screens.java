import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.Color;

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

    resetJFrameComponent();
  }

  private void chooseComputerDifficultyPopUp(){

    Object[] options = {"Facil",
      "Dificil"};

    int action = JOptionPane.showOptionDialog(this.mainFrame,
      "Escolha o nivel de dificuldade do Computador:",
      "Jogador Vs Computador",
      2,
      JOptionPane.QUESTION_MESSAGE,
      null,
      options,
      options[1]);

    if(action == 0){ // se action eh "Facil"

      gameScreen(false, true);
    }else{ // se nao ela eh "Dificil"

      gameScreen(false, false);
    }
  }

  public void gameScreen(boolean isPlayerVsPlayer, boolean isEasy){

    GameLogic gameLogic = new GameLogic(isPlayerVsPlayer, isEasy);

    JPanel gameScreenPanel = new JPanel();
    gameScreenPanel.setSize(this.mainFrame.getWidth(), this.mainFrame.getHeight());
    gameScreenPanel.setLayout(null); // para nao atrapalhar ja que o posicionamento esta sendo feito de maneira absoluta

    JPanel gameBoardPanel = new JPanel();
    int boardSize = 0;
    if(this.mainFrame.getWidth() > this.mainFrame.getHeight()){ // o pega a menor das proporcoes da tela para fazer o tabuleiro e reserva 80px para o restante da interface
      boardSize = this.mainFrame.getHeight()- 120;
    }else{
      boardSize = this.mainFrame.getWidth()- 120;
    }
    gameBoardPanel.setLayout(new GridLayout(3,3)); // tabuleiro
    int xToMakeItAligned = (this.mainFrame.getWidth() - boardSize)/2; // calcula qual é o alinhamento necessario no eixo X
    gameBoardPanel.setBounds(xToMakeItAligned, 80, boardSize, boardSize);

    gameScreenPanel.add(gameBoardPanel);

    JLabel labelX = new JLabel("X");
    JLabel labelO = new JLabel("O");

    labelX.setBounds(30, 10, 40, 40);
    labelX.setFont(new Font("Serif", Font.PLAIN, 34));
    labelO.setBounds(740, 10, 40, 40);
    labelO.setFont(new Font("Serif", Font.PLAIN, 34));

    gameScreenPanel.add(labelX);gameScreenPanel.add(labelO);

    JLabel labelWinsX = new JLabel("Vitorias: ");
    JLabel xWins = new JLabel(Integer.toString(gameLogic.getXWins()));
    JLabel labelWinsO = new JLabel("Vitorias: ");
    JLabel oWins = new JLabel(Integer.toString(gameLogic.getOWins()));

    labelWinsX.setBounds(5, 40, 100, 40);
    labelWinsX.setFont(new Font("Serif", Font.PLAIN, 20));
    xWins.setBounds(95, 40, 40, 40);
    xWins.setFont(new Font("Serif", Font.PLAIN, 20));
    labelWinsO.setBounds(680, 40, 100, 40);
    labelWinsO.setFont(new Font("Serif", Font.PLAIN, 20));
    oWins.setBounds(770, 40, 40, 40);
    oWins.setFont(new Font("Serif", Font.PLAIN, 20));

    gameScreenPanel.add(labelWinsX);gameScreenPanel.add(xWins);gameScreenPanel.add(labelWinsO);gameScreenPanel.add(oWins);

    JLabel labelTies = new JLabel("Empates: ");
    JLabel ties = new JLabel(Integer.toString(gameLogic.getTies()));

    labelTies.setBounds(300, 25, 120, 40);
    labelTies.setFont(new Font("Serif", Font.PLAIN, 20));
    ties.setBounds(405, 25, 40, 40);
    ties.setFont(new Font("Serif", Font.PLAIN, 20));

    gameScreenPanel.add(labelTies);gameScreenPanel.add(ties);

    JButton boardCells[] = new JButton[9];

    Font fontBoardCell = new Font("Serif", Font.BOLD, boardSize/3-20);
    Font fontBoardCellLoser = new Font("Serif", Font.PLAIN, boardSize/6);

    for(int i = 0; i < 9; i++){
      boardCells[i] = new JButton("");
      boardCells[i].setFont(fontBoardCell);
      UIManager.put("Button.disabledText", Color.BLACK);
      boardCells[i].setBackground(Color.WHITE);
      boardCells[i].addActionListener(new ActionListener(){
       
        @Override
        public void actionPerformed(ActionEvent e) {
          boolean isGameRestarting = false;
          for(int i = 0; i < 9; i++){
            if(e.getSource() == boardCells[i]){
              boardCells[i].setText(gameLogic.play(i)); // seta o valor da jogada no botao
              boardCells[i].setEnabled(false); // desativa o botao para que nao se possa mais fazer jogadas nele
              isGameRestarting = false; // permite que o bot jogue

              if(gameLogic.isThereAnyWinner() != -1){ // se alguem ganhou

                for(int j = 0; j < 9; j++){

                  if(gameLogic.getWinnerSequence()[0] == j
                    || gameLogic.getWinnerSequence()[1] == j
                    || gameLogic.getWinnerSequence()[2] == j){ // checka e pinta a sequencia vencedora

                      boardCells[j].setBackground(Color.GREEN);
                  }else{
                    
                    boardCells[j].setFont(fontBoardCellLoser);
                  }

                  boardCells[j].setEnabled(false); // desativa todos os botoes
                }

                if(gameLogic.whoIsPlaying() == 1){

                  gameLogic.addWins(true);
                }else{

                  gameLogic.addWins(false);
                }

                // atualiza as pontuacoes
                xWins.setText(Integer.toString(gameLogic.getXWins()));
                oWins.setText(Integer.toString(gameLogic.getOWins()));

                if(winnerPopUp(gameLogic.getGameSymbols()[gameLogic.whoIsPlaying()]) == 0){ // mostra o vencedor e reinicia o tabuleiro
          
                  gameLogic.newGame();
                  for(int j = 0; j < 9; j++){
        
                    boardCells[j].setFont(fontBoardCell);
                    boardCells[j].setText("");
                    boardCells[j].setBackground(Color.WHITE);
                    boardCells[j].setEnabled(true);
                    isGameRestarting = true; // nao permite que o bot faca a jogada logo apos o jogo reiniciar
                  }
                  
                }
              }else if(gameLogic.getTurns() == 9){

                gameLogic.addTie();

                ties.setText(Integer.toString(gameLogic.getTies()));

                if(tiePopUp() == 0){ // reinicia o tabuleiro
          
                  gameLogic.newGame();
                  for(int j = 0; j < 9; j++){
        
                    boardCells[j].setFont(fontBoardCell);
                    boardCells[j].setText("");
                    boardCells[j].setBackground(Color.WHITE);
                    boardCells[j].setEnabled(true);
                    isGameRestarting = true; // nao permite que o bot faca a jogada logo apos o jogo reiniciar
                  }
                  
                }
              }

              // se for um bot e o jogo nao tiver acabado de reinicar, ele faz a jogada
              if(!gameLogic.getIsPlayerVsPlayer() && !isGameRestarting){
                // escreve a jogada do bot
                int botPlay = gameLogic.botPlay();
                boardCells[botPlay].setText(gameLogic.getGameSymbols()[2]);
                boardCells[botPlay].setEnabled(false);

                if(gameLogic.isThereAnyWinner() != -1){ // se alguem ganhou, tem que re-verificar ja que o bot pode ter ganho

                  for(int j = 0; j < 9; j++){
  
                    if(gameLogic.getWinnerSequence()[0] == j
                      || gameLogic.getWinnerSequence()[1] == j
                      || gameLogic.getWinnerSequence()[2] == j){ // checka e pinta a sequencia vencedora
  
                        boardCells[j].setBackground(Color.GREEN);
                    }else{
                      
                      boardCells[j].setFont(fontBoardCellLoser);
                    }
  
                    boardCells[j].setEnabled(false); // desativa todos os botoes
                  }
  
                  if(gameLogic.whoIsPlaying() == 1){
  
                    gameLogic.addWins(true);
                  }else{
  
                    gameLogic.addWins(false);
                  }
  
                  // atualiza as pontuacoes
                  xWins.setText(Integer.toString(gameLogic.getXWins()));
                  oWins.setText(Integer.toString(gameLogic.getOWins()));
  
                  if(winnerPopUp(gameLogic.getGameSymbols()[gameLogic.whoIsPlaying()]) == 0){ // mostra o vencedor e reinicia o tabuleiro
            
                    gameLogic.newGame();
                    for(int j = 0; j < 9; j++){
          
                      boardCells[j].setFont(fontBoardCell);
                      boardCells[j].setText("");
                      boardCells[j].setBackground(Color.WHITE);
                      boardCells[j].setEnabled(true);
                      isGameRestarting = true; // nao permite que o bot faca a jogada logo apos o jogo reiniciar
                    }
                    
                  }
                }else if(gameLogic.getTurns() == 9){
  
                  gameLogic.addTie();
  
                  ties.setText(Integer.toString(gameLogic.getTies()));
  
                  if(tiePopUp() == 0){ // reinicia o tabuleiro
            
                    gameLogic.newGame();
                    for(int j = 0; j < 9; j++){
          
                      boardCells[j].setFont(fontBoardCell);
                      boardCells[j].setText("");
                      boardCells[j].setBackground(Color.WHITE);
                      boardCells[j].setEnabled(true);
                      isGameRestarting = true; // nao permite que o bot faca a jogada logo apos o jogo reiniciar
                    }
                    
                  }
                }

              }

              return; // para o 'for' ao achar o botao que foi clickado
            }
          }
        }
      });

      gameBoardPanel.add(boardCells[i]); // adiciona as celulas ao tabuleiro
    }

    JButton btnMenu = new JButton("Menu");
    btnMenu.setBounds(700, 520, 80, 40);
    btnMenu.addActionListener(new ActionListener(){
    
      @Override
      public void actionPerformed(ActionEvent e) {

        if(menuPopUp() == 0){  // reinicia o tabuleiro
          
          gameLogic.newGame();
          for(int i = 0; i < 9; i++){

            boardCells[i].setFont(fontBoardCell);
            boardCells[i].setText("");
            boardCells[i].setBackground(Color.WHITE);
            boardCells[i].setEnabled(true);
          }

        }
      }
    });

    gameScreenPanel.add(btnMenu);

    if(isPlayerVsPlayer){

      
    }else{
      if(isEasy){


      }else{


      }
    }

    this.mainFrame.add(gameScreenPanel);

    resetJFrameComponent();

  }

  private int menuPopUp(){

    Object[] options = {"Novo Jogo",
      "Sair para o Menu"};

    int action = JOptionPane.showOptionDialog(this.mainFrame,
      "O que deseja?",
      "Tic-Tac-Toe",
      2,
      JOptionPane.PLAIN_MESSAGE,
      null,
      options,
      options[1]);

    if(action == 1){ // action eh 1 quando o botao "Sair para o Menu" eh precionado
      mainMenuScreen();
    }else if(action == 0){ // cria um novo jogo, nao reinicia os placares
      return 0;
    }

    return -1;
  }

  private int tiePopUp(){
    Object[] options = {"Novo Jogo",
      "Sair para o Menu"};

    int action = JOptionPane.showOptionDialog(this.mainFrame, 
      "Nao ha vencedor", 
      "Empate!", 
      2,
      JOptionPane.PLAIN_MESSAGE,
      null,
      options,
      options[1]);

    if(action == 1){ // action eh 1 quando o botao "Sair para o Menu" eh precionado
      mainMenuScreen();
    }else if(action == 0){ // cria um novo jogo, nao reinicia os placares
      return 0;
    }

    return -1;
  }

  private int winnerPopUp(String winner){
    Object[] options = {"Novo Jogo",
      "Sair para o Menu"};

    int action = JOptionPane.showOptionDialog(this.mainFrame, 
      "O vencedor eh: " + winner, 
      "Parabens!", 
      2,
      JOptionPane.PLAIN_MESSAGE,
      null,
      options,
      options[1]);

    if(action == 1){ // action eh 1 quando o botao "Sair para o Menu" eh precionado
      mainMenuScreen();
    }else if(action == 0){ // cria um novo jogo, nao reinicia os placares
      return 0;
    }

    return -1;
  }

}