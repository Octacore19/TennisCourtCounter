package com.chegsmania.tenniscourtcounter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mPlayerOnePointScoreTextView;
    private TextView mPlayerOneSetScoreTextView;
    private TextView mPlayerOneServePoint;

    private TextView mPlayerTwoPointScoreTextView;
    private TextView mPlayerTwoSetScoreTextView;
    private TextView mPlayerTwoServePoint;

    private CardView mDeuceText;
    private TextView mPlayerOnePointLabel;
    private TextView mPlayerTwoPointLabel;

    private int playerOnePointScored;
    private int playerTwoPointScored ;
    private int playerOneSetPoint = 0;
    private int playerTwoSetPoint = 0;
    private int playerOneServePoint = 0;
    private int playerTwoServePoint = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPlayerOnePointScoreTextView = findViewById(R.id.tv_player1_point);
        mPlayerOneSetScoreTextView = findViewById(R.id.tv_player1_sets);
        mPlayerOneServePoint = findViewById(R.id.tv_player1_serve);

        mPlayerTwoPointScoreTextView = findViewById(R.id.tv_player2_point);
        mPlayerTwoSetScoreTextView = findViewById(R.id.tv_player2_sets);
        mPlayerTwoServePoint = findViewById(R.id.tv_player2_serve);

        mDeuceText = findViewById(R.id.tv_deuce);
        mPlayerOnePointLabel = findViewById(R.id.point_label_player_one);
        mPlayerTwoPointLabel = findViewById(R.id.point_label_player_two);

        Button mPlayerOnePointBtn = findViewById(R.id.btn_player1_pts);
        mPlayerOnePointBtn.setOnClickListener(this);

        Button mPlayerTwoPointBtn = findViewById(R.id.btn_player2_pts);
        mPlayerTwoPointBtn.setOnClickListener(this);

        Button reset = findViewById(R.id.btn_reset);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_player1_pts:
                if (playerOnePointScored < 60){
                    if (playerOnePointScored < 30){
                        playerOnePointScored = playerOnePointScored + 15;
                    } else {
                        playerOnePointScored = playerOnePointScored + 10;
                    }
                    mPlayerOnePointScoreTextView.setText(String.valueOf(playerOnePointScored));
                }
                if (playerOnePointScored == 40 && playerTwoPointScored == 40 || playerOnePointScored == 50 && playerTwoPointScored == 50){
                    mDeuceText.setVisibility(View.VISIBLE);
                    mPlayerOnePointScoreTextView.setVisibility(View.INVISIBLE);
                    mPlayerOnePointLabel.setVisibility(View.INVISIBLE);
                    mPlayerTwoPointScoreTextView.setVisibility(View.INVISIBLE);
                    mPlayerTwoPointLabel.setVisibility(View.INVISIBLE);

                    playerOnePointScored = 40;
                    playerTwoPointScored = 40;
                    mPlayerTwoPointScoreTextView.setText(String.valueOf(playerTwoPointScored));
                }
                if (playerOnePointScored == 50 && playerTwoPointScored == 40){
                    mDeuceText.setVisibility(View.GONE);
                    mPlayerOnePointScoreTextView.setVisibility(View.VISIBLE);
                    mPlayerOnePointLabel.setVisibility(View.VISIBLE);
                    mPlayerTwoPointScoreTextView.setVisibility(View.VISIBLE);
                    mPlayerTwoPointLabel.setVisibility(View.VISIBLE);
                    playerTwoPointScored = 40;
                    mPlayerOnePointScoreTextView.setText(getString(R.string.advantage));
                    mPlayerTwoPointScoreTextView.setText(String.valueOf(playerTwoPointScored));
                }else if (playerOnePointScored == 60){
                    mPlayerOnePointScoreTextView.setText(getString(R.string.game_point));
                    mPlayerOnePointScoreTextView.setTextSize(80);
                    findViewById(R.id.btn_player2_pts).setEnabled(false);
                    increasePlayerOneServePoint();
                }else
                if (playerOnePointScored == 50 && playerOnePointScored > playerTwoPointScored){
                    mPlayerOnePointScoreTextView.setText(getString(R.string.game_point));
                    mPlayerOnePointScoreTextView.setTextSize(80);
                    findViewById(R.id.btn_player2_pts).setEnabled(false);
                    increasePlayerOneServePoint();
                }
                break;

            case R.id.btn_player2_pts:
                if (playerTwoPointScored < 60){
                    if (playerTwoPointScored < 30){
                        playerTwoPointScored = playerTwoPointScored + 15;
                    } else {
                        playerTwoPointScored = playerTwoPointScored + 10;
                    }
                    mPlayerTwoPointScoreTextView.setText(String.valueOf(playerTwoPointScored));
                }

                if (playerOnePointScored == 40 && playerTwoPointScored == 40 || playerOnePointScored == 50 && playerTwoPointScored == 50){
                    mDeuceText.setVisibility(View.VISIBLE);
                    mPlayerOnePointScoreTextView.setVisibility(View.INVISIBLE);
                    mPlayerOnePointLabel.setVisibility(View.INVISIBLE);
                    mPlayerTwoPointScoreTextView.setVisibility(View.INVISIBLE);
                    mPlayerTwoPointLabel.setVisibility(View.INVISIBLE);
                    playerOnePointScored = 40;
                    playerTwoPointScored = 40;
                    mPlayerOnePointScoreTextView.setText(String.valueOf(playerOnePointScored));
                }
                if (playerTwoPointScored == 50 && playerOnePointScored == 40){
                    mDeuceText.setVisibility(View.GONE);
                    mPlayerOnePointScoreTextView.setVisibility(View.VISIBLE);
                    mPlayerOnePointLabel.setVisibility(View.VISIBLE);
                    mPlayerTwoPointScoreTextView.setVisibility(View.VISIBLE);
                    mPlayerTwoPointLabel.setVisibility(View.VISIBLE);
                    playerOnePointScored = 40;
                    mPlayerTwoPointScoreTextView.setText(getString(R.string.advantage));
                    mPlayerOnePointScoreTextView.setText(String.valueOf(playerOnePointScored));
                }else if (playerTwoPointScored == 60){
                    mPlayerTwoPointScoreTextView.setText(getString(R.string.game_point));
                    mPlayerTwoPointScoreTextView.setTextSize(80);
                    findViewById(R.id.btn_player1_pts).setEnabled(false);
                    increasePlayerTwoServePoint();
                }
                else if (playerTwoPointScored == 50 && playerTwoPointScored > playerOnePointScored){
                    mPlayerTwoPointScoreTextView.setText(getString(R.string.game_point));
                    mPlayerTwoPointScoreTextView.setTextSize(80);
                    findViewById(R.id.btn_player1_pts).setEnabled(false);
                    increasePlayerTwoServePoint();
                }
                break;

            case R.id.btn_reset:
                int love = Integer.parseInt(getString(R.string.love_value));
                mPlayerOnePointScoreTextView.setText(String.valueOf(love));
                mPlayerOnePointScoreTextView.setTextSize(112);
                mPlayerOnePointScoreTextView.setVisibility(View.VISIBLE);
                mPlayerOnePointLabel.setVisibility(View.VISIBLE);
                mPlayerTwoPointScoreTextView.setText(String.valueOf(love));
                mPlayerTwoPointScoreTextView.setTextSize(112);
                mPlayerTwoPointScoreTextView.setVisibility(View.VISIBLE);
                mPlayerTwoPointLabel.setVisibility(View.VISIBLE);
                findViewById(R.id.btn_player2_pts).setEnabled(true);
                findViewById(R.id.btn_player1_pts).setEnabled(true);
                mDeuceText.setVisibility(View.GONE);
                mPlayerOneServePoint.setText(String.valueOf(love));
                mPlayerTwoServePoint.setText(String.valueOf(love));
                mPlayerOneSetScoreTextView.setText(String.valueOf(love));
                mPlayerTwoSetScoreTextView.setText(String.valueOf(love));
        }
    }

    private void increasePlayerOneServePoint() {
        playerOneServePoint++;
        mPlayerOneServePoint.setText(String.valueOf(playerOneServePoint));
        playerOnePointScored = 0;
        playerTwoPointScored = 0;
        mPlayerOnePointScoreTextView.setText(String.valueOf(playerOnePointScored));
        mPlayerOnePointScoreTextView.setTextSize(112);
        findViewById(R.id.btn_player2_pts).setEnabled(true);
        mPlayerTwoPointScoreTextView.setText(String.valueOf(playerTwoPointScored));

        if (playerOneServePoint >= 6 && (playerOneServePoint - playerTwoServePoint) >= 2){
            playerOneSetPoint++;
            mPlayerOneSetScoreTextView.setText(String.valueOf(playerOneSetPoint));
            playerOneServePoint = 0;
            playerTwoServePoint = 0;
            mPlayerOneServePoint.setText(String.valueOf(playerOneServePoint));
            mPlayerTwoServePoint.setText(String.valueOf(playerTwoServePoint));
        }
    }

    private void increasePlayerTwoServePoint() {
        playerTwoServePoint++;
        mPlayerTwoServePoint.setText(String.valueOf(playerTwoServePoint));
        playerOnePointScored = 0;
        playerTwoPointScored = 0;
        mPlayerOnePointScoreTextView.setText(String.valueOf(playerOnePointScored));
        mPlayerTwoPointScoreTextView.setTextSize(112);
        findViewById(R.id.btn_player1_pts).setEnabled(true);
        mPlayerTwoPointScoreTextView.setText(String.valueOf(playerTwoPointScored));

        if (playerTwoServePoint >= 6 && (playerTwoServePoint - playerOneServePoint) >= 2){
            playerTwoSetPoint++;
            mPlayerTwoSetScoreTextView.setText(String.valueOf(playerTwoSetPoint));
            playerOneServePoint = 0;
            playerTwoServePoint = 0;
            mPlayerOneServePoint.setText(String.valueOf(playerOneServePoint));
            mPlayerTwoServePoint.setText(String.valueOf(playerTwoServePoint));
        }
    }
}
