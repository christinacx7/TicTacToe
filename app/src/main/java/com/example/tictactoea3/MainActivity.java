package com.example.tictactoea3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private com.example.tictactoea3.TicTacToe game;
    private com.example.tictactoea3.ButtonGridAndTextView TicTacToeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        /* your code here */
        game = new com.example.tictactoea3.TicTacToe();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        int w = size.x / 4;

        ButtonHandler bh = new ButtonHandler();
        TicTacToeView = new com.example.tictactoea3.ButtonGridAndTextView(this, w, 4, bh);
        TicTacToeView.setStatusText(game.result());

        /* set content */
        setContentView(TicTacToeView);
    }

    public void showNewGameDialog( ) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("This game is fun");
        alert.setMessage("Play again?");
        PlayDialog playAgain = new PlayDialog();
        alert.setPositiveButton("Yes", playAgain);
        alert.setNegativeButton("No", playAgain);
        alert.show();
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            for(int  /*  your code here */  row = 0; row < com.example.tictactoea3.TicTacToe.SIDE; row++ ) {
                for(int /*  your code here */ column = 0; column < com.example.tictactoea3.TicTacToe.SIDE; column++) {
                    if( TicTacToeView.isButton( (Button) v, row, column ) ) {
                        int play = game.play(row, column);
                        if ( play == 1)
                            TicTacToeView.setButtonText(row, column, "X");
                        else if ( play == 2 )
                            /*  your code here */
                            TicTacToeView.setButtonText(row, column, "O");
                        if( game.isGameOver( ) ) {
                            TicTacToeView.setStatusBackgroundColor( Color.YELLOW );
                            TicTacToeView.enableButtons( false );
                            TicTacToeView.setStatusText( game.result( ) );
                            showNewGameDialog( );    // offer to play again
                        }
                    }
                }
            }
        }
    }

    private class PlayDialog implements DialogInterface.OnClickListener {
        public void onClick( DialogInterface dialog, int id ) {
            if( id == -1 ) /* YES button */ {
                game.resetGame( );
                TicTacToeView.enableButtons( true );
                TicTacToeView.resetButtons( );
                TicTacToeView.setStatusBackgroundColor( Color.GREEN );
                TicTacToeView.setStatusText( game.result( ) );
            }
            else if( id == -2 ) // NO button
                /*  your code here */
                MainActivity.this.finish();
        }
    }
}
