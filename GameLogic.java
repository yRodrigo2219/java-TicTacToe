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
      if(this.board[i] == this.board[i+1] && this.board[i+1] == this.board[i+2] && this.board[i] != this.gameSymbols[0]){

        this.winnerSequence[0] = i;
        this.winnerSequence[1] = i+1;
        this.winnerSequence[2] = i+2;
        return whoIsPlaying();
      }
    }

    // checkagem vertical
    for(int i = 0; i < 3; i++){
      if(this.board[i] == this.board[i+3] && this.board[i+3] == this.board[i+6] && this.board[i] != this.gameSymbols[0]){
        
        this.winnerSequence[0] = i;
        this.winnerSequence[1] = i+3;
        this.winnerSequence[2] = i+6;
        return whoIsPlaying();
      }
    }

    // checkagens diagonais
    if(this.board[0] == this.board[4] && this.board[4] == this.board[8] && this.board[0] != this.gameSymbols[0]){

      this.winnerSequence[0] = 0;
      this.winnerSequence[1] = 4;
      this.winnerSequence[2] = 8;
      return whoIsPlaying();
    }else if(this.board[2] == this.board[4] && this.board[4] == this.board[6] && this.board[2] != this.gameSymbols[0]){

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

  public int nextMoveWinner(int position){

    System.out.println(position == -1);
    if(position == -1){ // se a checkagem quer ser feita com a board sem ser alterada
      // checkagem horizontal, verificando se o bot tem como ganhar no proximo round
      for(int i = 0; i <= 6; i+=3){
        if(this.board[i] == this.board[i+1] && this.board[i] == this.gameSymbols[2] && this.board[i] != this.gameSymbols[1] && this.board[i+2] == this.gameSymbols[0]){
          return i+2;
        }else if(this.board[i] == this.board[i+2] && this.board[i] == this.gameSymbols[2] && this.board[i] != this.gameSymbols[1] && this.board[i+1] == this.gameSymbols[0]){
          return i+1;
        }else if(this.board[i+1] == this.board[i+2] && this.board[i+1] == this.gameSymbols[2] && this.board[i+1] != this.gameSymbols[1] && this.board[i] == this.gameSymbols[0]){
          return i;
        }
      }

      // checkagem vertical, verificando se o bot tem como ganhar no proximo round
      for(int i = 0; i < 3; i++){
        if(this.board[i] == this.board[i+3] && this.board[i] == this.gameSymbols[2] && this.board[i] != this.gameSymbols[1] && this.board[i+6] == this.gameSymbols[0]){
          return i+6;
        }else if(this.board[i] == this.board[i+6] && this.board[i] == this.gameSymbols[2] && this.board[i] != this.gameSymbols[1] && this.board[i+3] == this.gameSymbols[0]){
          return i+3;
        }else if(this.board[i+3] == this.board[i+6] && this.board[i+3] == this.gameSymbols[2] && this.board[i+3] != this.gameSymbols[1] && this.board[i] == this.gameSymbols[0]){
          return i;
        }
      }

      // checkagens diagonais, verificando se o bot tem como ganhar no proximo round
      if(this.board[0] == this.board[4] && this.board[0] == this.gameSymbols[2] && this.board[0] != this.gameSymbols[1] && this.board[8] == this.gameSymbols[0]){
        return 8;
      }else if(this.board[0] == this.board[8] && this.board[0] == this.gameSymbols[2] && this.board[0] != this.gameSymbols[1] && this.board[4] == this.gameSymbols[0]){
        return 4;
      }else if(this.board[4] == this.board[8] && this.board[4] == this.gameSymbols[2] && this.board[4] != this.gameSymbols[1] && this.board[0] == this.gameSymbols[0]){
        return 0;
      }else if(this.board[2] == this.board[4] && this.board[2] == this.gameSymbols[2] && this.board[2] != this.gameSymbols[1] && this.board[6] == this.gameSymbols[0]){
        return 6;
      }else if(this.board[2] == this.board[6] && this.board[2] == this.gameSymbols[2] && this.board[2] != this.gameSymbols[1] && this.board[4] == this.gameSymbols[0]){
        return 4;
      }else if(this.board[4] == this.board[6] && this.board[4] == this.gameSymbols[2] && this.board[4] != this.gameSymbols[1] && this.board[2] == this.gameSymbols[0]){
        return 2;
      }

      // ao passar por tudo e verificar que o bot nao pode ganhar na proxima jogada, verifica se o player pode

      // checkagem horizontal, verificando se o player tem como ganhar no proximo round
      for(int i = 0; i <= 6; i+=3){
        if(this.board[i] == this.board[i+1] && this.board[i] == this.gameSymbols[1] && this.board[i] != this.gameSymbols[2] && this.board[i+2] == this.gameSymbols[0]){
          return i+2;
        }else if(this.board[i] == this.board[i+2] && this.board[i] == this.gameSymbols[1] && this.board[i] != this.gameSymbols[2] && this.board[i+1] == this.gameSymbols[0]){
          return i+1;
        }else if(this.board[i+1] == this.board[i+2] && this.board[i+1] == this.gameSymbols[1] && this.board[i+1] != this.gameSymbols[2] && this.board[i] == this.gameSymbols[0]){
          return i;
        }
      }

      // checkagem vertical, verificando se o player tem como ganhar no proximo round
      for(int i = 0; i < 3; i++){
        if(this.board[i] == this.board[i+3] && this.board[i] == this.gameSymbols[1] && this.board[i] != this.gameSymbols[2] && this.board[i+6] == this.gameSymbols[0]){
          return i+6;
        }else if(this.board[i] == this.board[i+6] && this.board[i] == this.gameSymbols[1] && this.board[i] != this.gameSymbols[2] && this.board[i+3] == this.gameSymbols[0]){
          return i+3;
        }else if(this.board[i+3] == this.board[i+6] && this.board[i+3] == this.gameSymbols[1] && this.board[i+3] != this.gameSymbols[2] && this.board[i] == this.gameSymbols[0]){
          return i;
        }
      }

      // checkagens diagonais, verificando se o player tem como ganhar no proximo round
      if(this.board[0] == this.board[4] && this.board[0] == this.gameSymbols[1] && this.board[0] != this.gameSymbols[2] && this.board[8] == this.gameSymbols[0]){
        return 8;
      }else if(this.board[0] == this.board[8] && this.board[0] == this.gameSymbols[1] && this.board[0] != this.gameSymbols[2] && this.board[4] == this.gameSymbols[0]){
        return 4;
      }else if(this.board[4] == this.board[8] && this.board[4] == this.gameSymbols[1] && this.board[4] != this.gameSymbols[2] && this.board[0] == this.gameSymbols[0]){
        return 0;
      }else if(this.board[2] == this.board[4] && this.board[2] == this.gameSymbols[1] && this.board[2] != this.gameSymbols[2] && this.board[6] == this.gameSymbols[0]){
        return 6;
      }else if(this.board[2] == this.board[6] && this.board[2] == this.gameSymbols[1] && this.board[2] != this.gameSymbols[2] && this.board[4] == this.gameSymbols[0]){
        return 4;
      }else if(this.board[4] == this.board[6] && this.board[4] == this.gameSymbols[1] && this.board[4] != this.gameSymbols[2] && this.board[2] == this.gameSymbols[0]){
        return 2;
      }
      
      // se ninguem pode ganhar no proximo turno, retorna -1
      return -1;

    }else{
      String fakeBoard[] = new String[9];

      for(int i = 0; i < 9; i++){ // faz uma copia do tabuleiro, desse modo para que nao apontem para o mesmo endereco de memoria, ja que a fakeBoard deve ser modificada
        fakeBoard[i] = this.board[i];
      }
      fakeBoard[position] = this.gameSymbols[2];

      if(this.board[position] != this.gameSymbols[0]){ // se a posicao ja estivar ocupada, retorna -1
        return -1;
      }

      fakeBoard[position] = this.gameSymbols[2]; // simula a jogada que foi dada como argumento e checka se o bot pode ganhar na jogada apos essa

      // checkagem horizontal, verificando se o bot tem como ganhar no proximo round
      for(int i = 0; i <= 6; i+=3){
        if(fakeBoard[i] == fakeBoard[i+1] && fakeBoard[i] == this.gameSymbols[2] && fakeBoard[i] != this.gameSymbols[1] && fakeBoard[i+2] == this.gameSymbols[0]){
          return i+2;
        }else if(fakeBoard[i] == fakeBoard[i+2] && fakeBoard[i] == this.gameSymbols[2] && fakeBoard[i] != this.gameSymbols[1] && fakeBoard[i+1] == this.gameSymbols[0]){
          return i+1;
        }else if(fakeBoard[i+1] == fakeBoard[i+2] && fakeBoard[i+1] == this.gameSymbols[2] && fakeBoard[i+1] != this.gameSymbols[1] && fakeBoard[i] == this.gameSymbols[0]){
          return i;
        }
      }

      // checkagem vertical, verificando se o bot tem como ganhar no proximo round
      for(int i = 0; i < 3; i++){
        if(fakeBoard[i] == fakeBoard[i+3] && fakeBoard[i] == this.gameSymbols[2] && fakeBoard[i] != this.gameSymbols[1] && fakeBoard[i+6] == this.gameSymbols[0]){
          return i+6;
        }else if(fakeBoard[i] == fakeBoard[i+6] && fakeBoard[i] == this.gameSymbols[2] && fakeBoard[i] != this.gameSymbols[1] && fakeBoard[i+3] == this.gameSymbols[0]){
          return i+3;
        }else if(fakeBoard[i+3] == fakeBoard[i+6] && fakeBoard[i+3] == this.gameSymbols[2] && fakeBoard[i+3] != this.gameSymbols[1] && fakeBoard[i] == this.gameSymbols[0]){
          return i;
        }
      }

      // checkagens diagonais, verificando se o bot tem como ganhar no proximo round
      if(fakeBoard[0] == fakeBoard[4] && fakeBoard[0] == this.gameSymbols[2] && fakeBoard[0] != this.gameSymbols[1] && fakeBoard[8] == this.gameSymbols[0]){
        return 8;
      }else if(fakeBoard[0] == fakeBoard[8] && fakeBoard[0] == this.gameSymbols[2] && fakeBoard[0] != this.gameSymbols[1] && fakeBoard[4] == this.gameSymbols[0]){
        return 4;
      }else if(fakeBoard[4] == fakeBoard[8] && fakeBoard[4] == this.gameSymbols[2] && fakeBoard[4] != this.gameSymbols[1] && fakeBoard[0] == this.gameSymbols[0]){
        return 0;
      }else if(fakeBoard[2] == fakeBoard[4] && fakeBoard[2] == this.gameSymbols[2] && fakeBoard[2] != this.gameSymbols[1] && fakeBoard[6] == this.gameSymbols[0]){
        return 6;
      }else if(fakeBoard[2] == fakeBoard[6] && fakeBoard[2] == this.gameSymbols[2] && fakeBoard[2] != this.gameSymbols[1] && fakeBoard[4] == this.gameSymbols[0]){
        return 4;
      }else if(fakeBoard[4] == fakeBoard[6] && fakeBoard[4] == this.gameSymbols[2] && fakeBoard[4] != this.gameSymbols[1] && fakeBoard[2] == this.gameSymbols[0]){
        return 2;
      }

      return -1;
    }
    
  }

  public int botPlay(){
    System.out.println(this.board[0] +","+ this.board[1]+","+this.board[2] +","+ this.board[3]+","+this.board[4]+","+this.board[5]+","+this.board[6]+","+this.board[7]+","+this.board[8]);
    if(this.isEasy){ // se for o bot facil

      List<Integer> boardDisponibility = new ArrayList<>();

      for(int i = 0; i < 9; i++){

        if(this.board[i].equals(gameSymbols[0])){ // se estiver vazio

          boardDisponibility.add(i);
        }
      }

      // por ser um bot facil, ele joga aleatoriamente

      int position = boardDisponibility.get((new Random()).nextInt(boardDisponibility.size()));

      this.board[position] = this.gameSymbols[2];

      return position;

    }else{ // se for o dificil

      if(this.turns == 1){ // se for a primeira jogada do bot

        if(this.board[4] == this.gameSymbols[0]){ // e o espaço central estiver vazio

          this.board[4] = this.gameSymbols[2]; // joga no centro, ja que eh a melhor jogada sempre
          return 4;
        }else{ // se nao, ele joga em um dos cantos qualquer, no caso o canto superior esquerdo
          
          this.board[0] = this.gameSymbols[2];
          return 0;
        }

      }else{ // se nao for o primeiro turno

        // se for feita uma abertura onde o player joga em cantos opostos, a melhor prioridade nao deve ser do canto, como seria se o codigo prosseguisse
        if(this.turns == 3){
          if(this.board[0] == this.board[8] && this.board[0] == this.gameSymbols[1]){
            this.board[5] = this.gameSymbols[2];
            return 5;
          }else if(this.board[2] == this.board[6] && this.board[2] == this.gameSymbols[1]){
            this.board[3] = this.gameSymbols[2];
            return 3;
          }
        }

        int nextMove = nextMoveWinner(-1); // recebe a posicao a ser jogada para ganhar ou impedir a vitoria do player

        if(nextMove != -1){

          this.board[nextMove] = this.gameSymbols[2];
          return nextMove;
        }else{

          // Checka se vai existir a posibilidade de vitoria em 2 jogadas ao jogar em cada posicao, dando preferencia para o canto
          nextMove = nextMoveWinner(0);
          if(nextMove != -1){

            this.board[0] = this.gameSymbols[2];
            return 0;
          }

          nextMove = nextMoveWinner(2);
          if(nextMove != -1){

            this.board[2] = this.gameSymbols[2];
            return 2;
          }

          nextMove = nextMoveWinner(6);
          if(nextMove != -1){

            this.board[6] = this.gameSymbols[2];
            return 6;
          }

          nextMove = nextMoveWinner(8);
          if(nextMove != -1){

            this.board[8] = this.gameSymbols[2];
            return 8;
          }
          System.out.println(this.board[0] +","+ this.board[1]+","+this.board[2] +","+ this.board[3]+","+this.board[4]+","+this.board[5]+","+this.board[6]+","+this.board[7]+","+this.board[8]);

          // Se nenhum dos cantos da possibilidade de vitoria em 2 jogadas, opta por um dos lados que tenha essa possibilidade

          for(int i = 1; i < 8; i+=2){
            nextMove = nextMoveWinner(i);

            if(nextMove != -1){

              this.board[i] = this.gameSymbols[2];
              return i;
            }
          }

          // Se nao ha possibilidade de vitoria, joga na primeira casa disponivel

          for(int i = 0; i < 9; i++){
            if(this.board[i] == this.gameSymbols[0]){

              this.board[i] = this.gameSymbols[2];
              return i;
            }
          }

        }
      }
      return 0;
    }
    
  }
}