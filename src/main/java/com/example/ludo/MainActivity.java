package com.example.ludo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.ViewTarget;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView text3,betAmt , winBalShow;
    ImageView img1;
    EditText walletText;

    boolean isSelectBetAmt=false;
    ImageView img2;
    ImageView diceMove;
    ViewTarget<ImageView, GifDrawable> img9;

    int amtAdd=10;
    int winLossAmt;
    int Wallet;
    int TotalWallet,winAmt;
    Button btn, under,over,equal,min,twice,divide,AddAmt;
    public boolean underClick,overClick,equalClick;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //music player for the dice.............

        final MediaPlayer diceSound=MediaPlayer.create(this,R.raw.dice_sound); //dice sound
        final MediaPlayer predictBtnSound=MediaPlayer.create(this,R.raw.predict_btn_sound);//...sound of select under,over,equal btn.../
        final MediaPlayer SelectBetAmtSound=MediaPlayer.create(this,R.raw.select_bet_amt_sound);
        //.......................
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        text3=findViewById(R.id.text3);
        btn=findViewById(R.id.btn);
        walletText=findViewById(R.id.wallet);
        under=findViewById(R.id.under);
        over=findViewById(R.id.over);
        equal=findViewById(R.id.equal);
        AddAmt=findViewById(R.id.addAmt);
        winBalShow=findViewById(R.id.WinBal);
//.....all amount section............min X2 and /2 id......
        betAmt=findViewById(R.id.betAmt);
        min =findViewById(R.id.min);
        twice =findViewById(R.id.twice);
        divide=findViewById(R.id.divide);
//        ......................


        //........show the default bet amt........//////
        int minBetAmt=20;
        betAmt.setText(String.valueOf(minBetAmt));
        //...............................................//
        underClick=false;

        overClick=false;

        equalClick=false;





//..........Add Balance...............

        AddAmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Wallet=Integer.parseInt(walletText.getText().toString());

                TotalWallet=Wallet;
                text3.setText(String.valueOf(TotalWallet));
            }
        });
        ///..........................




//...........under over and equal....color change........click

        under.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                underClick= underClick();
                predictBtnSound.start();
                under.setBackgroundColor(Color.rgb(29,14,233));
                over.setBackgroundColor(Color.rgb(8,26,126));
                equal.setBackgroundColor(Color.rgb(8,26,126));

                overClick=false;
                equalClick=false;
                // Toast.makeText(MainActivity.this, " over....."+overClick, Toast.LENGTH_SHORT).show();
                // Toast.makeText(MainActivity.this, " under....."+underClick, Toast.LENGTH_SHORT).show();
            }
        });

        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overClick=overClick();
                predictBtnSound.start();
                over.setBackgroundColor(Color.rgb(29,14, 233));
                equal.setBackgroundColor(Color.rgb(8,26,126));
                under.setBackgroundColor(Color.rgb(8,26,126));
                equalClick=false;
                underClick=false;
                // Toast.makeText(MainActivity.this, " under....."+underClick, Toast.LENGTH_SHORT).show();

            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equalClick=equalClick();
                predictBtnSound.start();
                equal.setBackgroundColor(Color.rgb(29,14,233));
                over.setBackgroundColor(Color.rgb(8,26,126));
                under.setBackgroundColor(Color.rgb(8,26,126));
                overClick=false;
                underClick=false;
            }
        });
//...............................................................................

//..........................Bet Amount section ..........code

        twice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelectBetAmt=true;
                SelectBetAmtSound.start();
                //.......if the min btn is clicked than amt will be 20 and then increamet of the bet amt will be twice....///
                amtAdd*=2;

                betAmt.setText(""+amtAdd);

            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectBetAmtSound.start();
                if (amtAdd >= 40) {

                    amtAdd /= 2;

                    betAmt.setText("" + amtAdd);
                }
            }

        });

//...................................................................................


///.........................min Buttom........//
        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectBetAmtSound.start();
                amtAdd=20; //...when min btn click than show bet amt is min....//
                betAmt.setText(String.valueOf(amtAdd));
            }
        });

        //...........................................//

// ...................game plays........................//

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random=new Random();
                int val=random.nextInt(7-1)+1;
                int val2=random.nextInt(7-1)+1;
                int sum=val+val2;
                if (overClick==true||equalClick==true||underClick==true) {   //......without select no entry....//


                    if (isSelectBetAmt == true) {

                        if (TotalWallet>0) {  //....when the wallet have some balance...../
                            // set dice image
                            //.....1st dice ...............

                            if (val == 1) {
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        img1.setImageResource(R.drawable.img1);
                                    }
                                };
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 1000 / 2);

                                Glide.with(MainActivity.this).asGif().load(R.drawable.dicemove).into(img1);
                                diceSound.start();
                            }
                            if (val == 2) {

                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        img1.setImageResource(R.drawable.img2);
                                    }
                                };
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 1000 / 2);

                                Glide.with(MainActivity.this).asGif().load(R.drawable.dicemove).into(img1);
                                diceSound.start();
                            }
                            if (val == 3) {

                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        img1.setImageResource(R.drawable.img3);
                                    }
                                };
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 1000 / 2);

                                Glide.with(MainActivity.this).asGif().load(R.drawable.dicemove).into(img1);
                                diceSound.start();
                            }
                            if (val == 4) {

                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        img1.setImageResource(R.drawable.img4);
                                    }
                                };
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 1000 / 2);

                                Glide.with(MainActivity.this).asGif().load(R.drawable.dicemove).into(img1);
                                diceSound.start();
                            }
                            if (val == 5) {

                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        img1.setImageResource(R.drawable.img5);
                                    }
                                };
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 1000 / 2);

                                Glide.with(MainActivity.this).asGif().load(R.drawable.dicemove).into(img1);
                                diceSound.start();
                            }
                            if (val == 6) {

                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {

                                        img1.setImageResource(R.drawable.img6);
                                    }
                                };
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 1000 / 2);
                                // dice rolling ..........
                                Glide.with(MainActivity.this).asGif().load(R.drawable.dicemove).into(img1);
                                // rolling sound...........
                                diceSound.start();
                            }

                            //.................................//
//   ..............second dice ......

                            if (val2 == 1) {

                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        img2.setImageResource(R.drawable.img1);
                                    }
                                };
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 1000 / 2);

                                Glide.with(MainActivity.this).asGif().load(R.drawable.dicemove).into(img2);
                            }
                            if (val2 == 2) {
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        img2.setImageResource(R.drawable.img2);
                                    }
                                };
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 1000 / 2);

                                Glide.with(MainActivity.this).asGif().load(R.drawable.dicemove).into(img2);
                            }
                            if (val2 == 3) {
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        img2.setImageResource(R.drawable.img3);
                                    }
                                };
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 1000 / 2);

                                Glide.with(MainActivity.this).asGif().load(R.drawable.dicemove).into(img2);
                            }
                            if (val2 == 4) {
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        img2.setImageResource(R.drawable.img4);
                                    }
                                };
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 1000 / 2);

                                Glide.with(MainActivity.this).asGif().load(R.drawable.dicemove).into(img2);
                            }
                            if (val2 == 5) {
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        img2.setImageResource(R.drawable.img5);
                                    }
                                };
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 1000 / 2);

                                Glide.with(MainActivity.this).asGif().load(R.drawable.dicemove).into(img2);
                            }
                            if (val2 == 6) {
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        img2.setImageResource(R.drawable.img6);
                                    }
                                };
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 1000 / 2);

                                Glide.with(MainActivity.this).asGif().load(R.drawable.dicemove).into(img2);
                            }
                            if (underClick == true) {
                                if (sum < 7) {
                                    // Toast.makeText(MainActivity.this, "under ", Toast.LENGTH_SHORT).show();

//                        totalWallet= (int) (amtAdd*2.3);

                                    betAmtInc();

                                } else {
                                    Toast.makeText(MainActivity.this, "Better to next Time", Toast.LENGTH_LONG).show();
                                    betAmtDec();
                                }
                                overClick = false;
                                equalClick = false;
                            }
                            Runnable runnable = new Runnable() {
                                @Override
                                public void run() {

                                }
                            };
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(runnable, 1000 / 2);

                        }
                        else {
                            Toast.makeText(MainActivity.this, "Please add amt", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Please select bet amount", Toast.LENGTH_SHORT).show();
                    }
                    if (overClick==true)//.............amt increment and decrement condition............////////
                    {
                        if (sum>7) {
                            // Toast.makeText(MainActivity.this, "over ", Toast.LENGTH_SHORT).show();
                            betAmtInc();
                        }else{
                            Toast.makeText(MainActivity.this, "Better to next Time", Toast.LENGTH_SHORT).show();
                            betAmtDec();
                        }
                        underClick=false;
                        equalClick=false;
                    }
                    if (equalClick==true){
                        if (sum==7) {
                            // Toast.makeText(MainActivity.this, "equal ", Toast.LENGTH_SHORT).show();
                            TotalWallet+=amtAdd*5;

                            text3.setText(String.valueOf(TotalWallet));

                        }else{
                            Toast.makeText(MainActivity.this, "Better to next Time", Toast.LENGTH_SHORT).show();
                            betAmtDec();
                        }
                        overClick=false;
                        underClick=false;
                    }


                } else {
                    Toast.makeText(MainActivity.this, "please select pridiction...", Toast.LENGTH_LONG).show();
                }

            }//............... btn onClick method closed...............//////////
        });   //..............btn onClickListner closed....../
    }//........onCreate method closed........./

    public void betAmtInc() {
        if (TotalWallet>0) {
            winAmt = amtAdd * 3;
            Toast.makeText(this, " betAmtInc", Toast.LENGTH_SHORT).show();
            TotalWallet += winAmt;
            // winBalShow.setText(String.valueOf(winAmt));
            text3.setText(String.valueOf(TotalWallet));
        }
    }




    public void betAmtDec() {
        if (TotalWallet>0){
            TotalWallet-=amtAdd;
            Toast.makeText(this, " betAmtDec", Toast.LENGTH_SHORT).show();
            text3.setText(String.valueOf(TotalWallet));
        }}


    public boolean overClick() {
        return true;
    }

    public boolean underClick(){
        return true;
    }
    public boolean equalClick(){
        return true;
    }

}