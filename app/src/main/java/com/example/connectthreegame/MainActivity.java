package com.example.connectthreegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Class variables
    int count=0;
    int activePlayer=0;
    int[] winner={2,2,2,2,2,2,2,2,2};
    String winnerIs="";
    boolean gameAllowed=true;
    //Defines sequences of winning positions
    int[][] sequences={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    //Function to drop the coins when the grid is touched
    public void dropIn(View view){
        int id_req = view.getId();
        ImageView imageView = findViewById(id_req);
        //To get the tag of the image which was tapped
        int tapped_counter = Integer.parseInt(imageView.getTag().toString());

        if(gameAllowed && winner[tapped_counter]==2){

            winner[tapped_counter] = activePlayer;
            if (count % 2 == 0) {
                imageView.setTranslationY(-1500);
                imageView.setImageResource(R.drawable.red);
                imageView.animate().translationYBy(1500).setDuration(500);
                count++;
                activePlayer = 1; //Yellow
            } else {
                imageView.setTranslationY(-1500);
                imageView.setImageResource(R.drawable.yellow);
                imageView.animate().translationYBy(1500).setDuration(500);
                count++;
                activePlayer = 0; //Red
            }

            //To check if any player won
            for (int[] a : sequences) {
                if (winner[a[0]] == winner[a[1]] && winner[a[1]] == winner[a[2]] && winner[a[2]] != 2) {
                    if (activePlayer == 0) {
                        winnerIs = "Yellow";
                    } else {
                        winnerIs = "Red";
                    }
                    Toast.makeText(this, winnerIs, Toast.LENGTH_SHORT).show();
                    gameAllowed=false;
                }
            }
        }
        else {
            if(winnerIs.equals(""))
                Toast.makeText(this, "Draw Match , click on play again", Toast.LENGTH_SHORT).show();
            else if(!gameAllowed)
                Toast.makeText(this, "Click on Play Again", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "You can't insert coin", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickMe(View view){
        GridLayout gridLayout=findViewById(R.id.gridLayout);
        int count_child=gridLayout.getChildCount();
        for(int i=0;i<count_child;i++)
        {
            ImageView imageView=(ImageView)gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }
        count=0;
        activePlayer=0;
        winner= new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        gameAllowed=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}