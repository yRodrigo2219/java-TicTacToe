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

import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class Screens{

  private JFrame mainFrame = new JFrame();
  // Variaveis de alinhamento da tela
  private int mainWidth, mainHeight, titleHeight, buttonHeight, buttonSpace, boardSize;

  // Timer para validar a tela a cada 0.5s, impedindo que haja problemas de exibicao por mais de 0.5s
  private Timer timerFrame = new Timer(500, new ActionListener(){
  
    @Override
    public void actionPerformed(ActionEvent e) {
      mainFrame.getContentPane().validate();
    }
  });

  public Screens(int width, int height) {

    this.boardSize = height - 120;
    this.buttonSpace = height/30;
    this.buttonHeight = (height/3)*2;
    this.mainWidth = width;
    this.mainWidth = width;
    this.titleHeight = height/3;

    this.mainFrame.setSize(width, height);
    this.mainFrame.setResizable(false);
    this.mainFrame.setVisible(true);
    this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.mainFrame.setTitle("Tic-Tac-Toe");

    this.timerFrame.setRepeats(true);
    this.timerFrame.start();
  }

  
  /* ***************************************************************
  * Metodo: resetJFrameComponent
  * Funcao: valida a troca de pagina, deve ser chamado apos a criacao da
            nova pagina para se ter uma navegacao em stack
  * Parametros: 
  * Retorno: 
  *************************************************************** */
  private void resetJFrameComponent(){

    System.out.println(this.mainFrame.getContentPane().getComponentCount());
    if(this.mainFrame.getContentPane().getComponentCount() > 1){
      this.mainFrame.getContentPane().remove(0);
      this.mainFrame.getContentPane().validate();
    }
  }
  
  /* ***************************************************************
  * Metodo: mainMenuScreen
  * Funcao: cria a tela de menu principal, onde se pode selecionar o
            modo de jogo
  * Parametros: 
  * Retorno: 
  *************************************************************** */
  public void mainMenuScreen(){

    JPanel mainScreenPanel = new JPanel();
    mainScreenPanel.setSize(this.mainWidth, this.mainHeight);
    mainScreenPanel.setLayout(null); // para nao atrapalhar ja que o posicionamento esta sendo feito de maneira absoluta

    JLabel gameName = new JLabel("Tic-Tac-Toe", SwingConstants.CENTER);
    gameName.setVerticalAlignment(SwingConstants.CENTER);
    gameName.setSize(this.mainWidth, this.titleHeight);
    gameName.setFont(new Font("Serif", Font.BOLD, this.titleHeight/2));

    Font buttonFont = new Font("Serif", Font.PLAIN, this.buttonHeight/12);
    LineBorder buttonBorder = new LineBorder(Color.BLACK, 2, true);

    JButton btnPlayerVsPlayer = new JButton("Jogador Vs Jogador");
    btnPlayerVsPlayer.setBounds(this.mainWidth/4, this.titleHeight, this.mainWidth/2, this.buttonHeight/4);
    btnPlayerVsPlayer.setFont(buttonFont);
    btnPlayerVsPlayer.setBorder(buttonBorder);
    btnPlayerVsPlayer.addActionListener(new ActionListener(){
    
      @Override
      public void actionPerformed(ActionEvent e) {
        
        gameScreen(true, false);
      }
    });

    JButton btnPlayerVsComputer = new JButton("Jogador Vs Computador");
    btnPlayerVsComputer.setBounds(this.mainWidth/4, this.titleHeight + this.buttonHeight/4 + this.buttonSpace, this.mainWidth/2, this.buttonHeight/4);
    btnPlayerVsComputer.setFont(buttonFont);
    btnPlayerVsComputer.setBorder(buttonBorder);
    btnPlayerVsComputer.addActionListener(new ActionListener(){
    
      @Override
      public void actionPerformed(ActionEvent e) {

        chooseComputerDifficultyPopUp();
      }
    });

    JButton btnExitGame = new JButton("Sair do Jogo");
    btnExitGame.setBounds(this.mainWidth/4, this.titleHeight + this.buttonHeight/2 + this.buttonSpace*3, this.mainWidth/2, this.buttonHeight/4);
    btnExitGame.setFont(buttonFont);
    btnExitGame.setBorder(buttonBorder);
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

  /* ***************************************************************
  * Metodo: chooseComputerDifficultyPopUp
  * Funcao: popup para selecao da dificuldade do computador
  * Parametros: 
  * Retorno: 
  *************************************************************** */
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
    }else if(action == 1){ // se nao ela eh "Dificil"

      gameScreen(false, false);
    }
  }

  /* ***************************************************************
  * Metodo: chooseComputerDifficultyPopUp
  * Funcao: cria a tela de jogo
  * Parametros: eh passado se o jogo se trata de um modo jogador vs
                jogador ou um modo jogador vs computador
  * Retorno: 
  *************************************************************** */
  public void gameScreen(boolean isPlayerVsPlayer, boolean isEasy){

    GameLogic gameLogic = new GameLogic(isPlayerVsPlayer, isEasy);

    JPanel gameScreenPanel = new JPanel();
    gameScreenPanel.setSize(this.mainWidth, this.mainHeight);
    gameScreenPanel.setLayout(null); // para nao atrapalhar ja que o posicionamento esta sendo feito de maneira absoluta

    JPanel gameBoardPanel = new JPanel();
    gameBoardPanel.setLayout(new GridLayout(3,3)); // tabuleiro
    gameBoardPanel.setBounds((this.mainWidth - this.boardSize)/2, 80, this.boardSize, this.boardSize);

    gameScreenPanel.add(gameBoardPanel);

    JLabel labelX = new JLabel("X", SwingConstants.CENTER);
    JLabel labelO = new JLabel("O", SwingConstants.CENTER);

    labelX.setBounds(30, 10, 40, 40);
    labelX.setFont(new Font("Serif", Font.PLAIN, 34));
    labelO.setBounds(this.mainWidth - 110, 10, 40, 40);
    labelO.setFont(new Font("Serif", Font.PLAIN, 34));

    gameScreenPanel.add(labelX);
    gameScreenPanel.add(labelO);

    JLabel labelWinsX = new JLabel("Vitorias: ");
    JLabel xWins = new JLabel(Integer.toString(gameLogic.getXWins()));
    JLabel labelWinsO = new JLabel("Vitorias: ");
    JLabel oWins = new JLabel(Integer.toString(gameLogic.getOWins()));

    labelWinsX.setBounds(5, 40, 100, 40);
    labelWinsX.setFont(new Font("Serif", Font.PLAIN, 20));
    xWins.setBounds(95, 40, 40, 40);
    xWins.setFont(new Font("Serif", Font.PLAIN, 20));
    labelWinsO.setBounds(this.mainWidth - 140, 40, 100, 40);
    labelWinsO.setFont(new Font("Serif", Font.PLAIN, 20));
    oWins.setBounds(this.mainWidth - 50, 40, 40, 40);
    oWins.setFont(new Font("Serif", Font.PLAIN, 20));

    gameScreenPanel.add(labelWinsX);
    gameScreenPanel.add(xWins);
    gameScreenPanel.add(labelWinsO);
    gameScreenPanel.add(oWins);

    JLabel labelTies = new JLabel("Empates: ", SwingConstants.CENTER);
    JLabel ties = new JLabel(Integer.toString(gameLogic.getTies()), SwingConstants.CENTER);

    labelTies.setBounds(this.mainWidth/2-100, 25, 120, 40);
    labelTies.setFont(new Font("Serif", Font.PLAIN, 20));
    ties.setBounds(this.mainWidth/2, 25, 40, 40);
    ties.setFont(new Font("Serif", Font.PLAIN, 20));

    gameScreenPanel.add(labelTies);
    gameScreenPanel.add(ties);

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
                if(botPlay == -1){ // -1 eh retornado quando o tabuleiro esta cheio
                  return;
                }
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
    btnMenu.setBounds(this.mainFrame.getWidth()-100, this.mainFrame.getHeight()-80, 80, 40);
    btnMenu.addActionListener(new ActionListener(){
    
      @Override
      public void actionPerformed(ActionEvent e) {

        if(menuPopUp() == 0){  // se foi clicakado em novo jogo, reinicia o tabuleiro, permanecem as vitorias
          
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

    this.mainFrame.add(gameScreenPanel);

    resetJFrameComponent();

  }

  /* ***************************************************************
  * Metodo: menuPopUp
  * Funcao: popup de um menu durante o jogo para possivel selecao de
            se reiniciar a partida atual, sem reiniciar o placar, ou
            para se voltar ao menu principal
  * Parametros: 
  * Retorno: retorna 0 caso se queira reiniciar o jogo, -1 por padrao
              nada acontece
  *************************************************************** */
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

  /* ***************************************************************
  * Metodo: tiePopUp
  * Funcao: popup que aparece caso haja empate, dando opcoes de se
            criar um novo jogo ou voltar ao menu principal
  * Parametros: 
  * Retorno: retorna 0 caso se queira reiniciar o jogo, -1 por padrao
              nada acontece
  *************************************************************** */
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

  /* ***************************************************************
  * Metodo: winnerPopUp
  * Funcao: popup que aparece caso haja um vencedor, dando opcoes de
            se criar um novo jogo ou voltar ao menu principal
  * Parametros: 
  * Retorno: retorna 0 caso se queira reiniciar o jogo, -1 por padrao
              nada acontece
  *************************************************************** */
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