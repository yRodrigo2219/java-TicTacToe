import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic{

  private boolean isPlayerVsPlayer, isEasy;
  private String gameSymbols[] = {"","X","O"};
  private int turns;
  private int winnerSequence[] = {-1, -1, -1};
  private int xWins, oWins, ties;
  private String board[] = new String[9];

  public GameLogic(boolean isPlayerVsPlayer, boolean isEasy){

    this.newGame();
    this.xWins = 0;
    this.oWins = 0;
    this.isPlayerVsPlayer = isPlayerVsPlayer;
    this.isEasy = isEasy;
  }

  public boolean getIsPlayerVsPlayer(){
    return this.isPlayerVsPlayer;
  }

  public boolean getIsEasy(){
    return this.isEasy;
  }

  public void newGame(){

    this.turns = 0;
    for(int i = 0; i < 9; i++){

      this.board[i] = gameSymbols[0];
    }
  }

  public int getXWins(){

    return this.xWins;
  }

  public int getTurns(){
    
    return this.turns;
  }

  public int getOWins(){

    return this.oWins;
  }

  public int getTies(){

    return this.ties;
  }

  public int[] getWinnerSequence(){

    return this.winnerSequence;
  }

  public void addWins(boolean isX){

    if(isX){

      this.xWins = this.xWins + 1;
    }else{

      this.oWins = this.oWins + 1;
    }
  }

  public void addTie(){

    this.ties = this.ties + 1;
  }

  public String[] getGameSymbols(){

    return this.gameSymbols;
  }

  public int whoIsPlaying(){
    
    if(this.turns%2 == 0){ // se par, player1 esta jogando

      return 1;
    }else{ // se nao, player2

      return 2;
    }

  }

  public int isThereAnyWinner(){

    // checkagem horizontal
    for(int i = 0; i <= 6; i = i+3){
      if(board[i] == board[i+1] && board[i+1] == board[i+2] && board[i] != gameSymbols[0]){

        this.winnerSequence[0] = i;
        this.winnerSequence[1] = i+1;
        this.winnerSequence[2] = i+2;
        return whoIsPlaying();
      }
    }

    // checkagem vertical
    for(int i = 0; i < 3; i++){
      if(board[i] == board[i+3] && board[i+3] == board[i+6] && board[i] != gameSymbols[0]){
        
        this.winnerSequence[0] = i;
        this.winnerSequence[1] = i+3;
        this.winnerSequence[2] = i+6;
        return whoIsPlaying();
      }
    }

    // checkagens diagonais
    if(board[0] == board[4] && board[4] == board[8] && board[0] != gameSymbols[0]){

      this.winnerSequence[0] = 0;
      this.winnerSequence[1] = 4;
      this.winnerSequence[2] = 8;
      return whoIsPlaying();
    }else if(board[2] == board[4] && board[4] == board[6] && board[2] != gameSymbols[0]){

      this.winnerSequence[0] = 2;
      this.winnerSequence[1] = 4;
      this.winnerSequence[2] = 6;
      return whoIsPlaying();
    }

    turns++;
    return -1;
  }

  public String play(int i){ // 'i' eh o indice do array de botoes

    int player = whoIsPlaying();
    this.board[i] = gameSymbols[player];

    return board[i];
  }

  public int botPlay(){

    if(this.isEasy){ // se for o bot facil

      List<Integer> boardDisponibility = new ArrayList<>();

      for(int i = 0; i < 9; i++){

        if(this.board[i].equals(gameSymbols[0])){ // se estiver vazio

          boardDisponibility.add(i);
        }
      }

      int position = boardDisponibility.get((new Random()).nextInt(boardDisponibility.size()));

      this.board[position] = gameSymbols[2];

      return position;

    }else{ // se for o dificil

      

      return -1;
    }

  }


}