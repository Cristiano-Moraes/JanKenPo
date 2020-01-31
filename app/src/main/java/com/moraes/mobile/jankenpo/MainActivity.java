package com.moraes.mobile.jankenpo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import java.util.Random;
import android.view.View;
import android.media.MediaPlayer;


public class MainActivity extends AppCompatActivity {
    int player = -1;
    int cpu = 0;
    int playerFlag = 0;
    int cpuFlag = 0;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void selecionarPedra(View view){
        this.escolhaJogador(2);

    }
    public void selecionarPapel(View view){
        this.escolhaJogador(0);

    }
    public void selecionarTesoura(View v){
        this.escolhaJogador(1);

    }

    public void playerStarsUpdate(){
        playerFlag++;
        mp = MediaPlayer.create(this, R.raw.winturn);
        mp.start();
        if ( playerFlag == 1 ){
            ImageView star1 = (ImageView) findViewById(R.id.imgPStar1);
            star1.setImageResource(android.R.drawable.star_big_on);

        }
        if ( playerFlag == 2 ){
            ImageView star2 = (ImageView) findViewById(R.id.imgPStar2);
            star2.setImageResource(android.R.drawable.star_big_on);
        }
        if ( playerFlag == 3 ){
            ImageView star3 = (ImageView) findViewById(R.id.imgPStar3);
            star3.setImageResource(android.R.drawable.star_big_on);
        }


    }
    public void cpuStarsUpdate(){
        cpuFlag++;
        mp = MediaPlayer.create(this, R.raw.loseturn);
        mp.start();
        if ( cpuFlag == 1 ){
            ImageView star1 = (ImageView) findViewById(R.id.imgCpuStar1);
            star1.setImageResource(android.R.drawable.star_big_on);
        }
        if ( cpuFlag == 2 ){
            ImageView star2 = (ImageView) findViewById(R.id.imgCpuStar2);
            star2.setImageResource(android.R.drawable.star_big_on);
        }
        if ( cpuFlag == 3 ){
            ImageView star3 = (ImageView) findViewById(R.id.imgCpuStar3);
            star3.setImageResource(android.R.drawable.star_big_on);
        }


    }
    public void escolhaCPU (View v){
        int i = new Random().nextInt(3);
        ImageView viewCPU = findViewById(R.id.lblCPU);
        if ( i == 0 ){ viewCPU.setImageResource(R.drawable.papel); }
        if ( i == 1 ){ viewCPU.setImageResource(R.drawable.tesoura); }
        if ( i == 2 ){ viewCPU.setImageResource(R.drawable.pedra); }
        cpu = i;

    }

    public void escolhaJogador( int i){
        ImageView viewJogador = ( ImageView )findViewById(R.id.lblUsuario);
        if ( i == 0 ){ viewJogador.setImageResource(R.drawable.papel); }
        if ( i == 1 ){ viewJogador.setImageResource(R.drawable.tesoura); }
        if ( i == 2 ){ viewJogador.setImageResource(R.drawable.pedra); }
        player = i;
    }

    public void vencedor(View v){

        this.escolhaCPU(v);
        if ( player == cpu ){
          // empate
            mp = MediaPlayer.create(this, R.raw.coin);
            mp.start();
        }
        if ( player == 0 && cpu == 2 || player == 1 && cpu == 0 || player == 2 && cpu == 1 ){
            this.playerStarsUpdate();
        }
        if ( cpu == 0 && player == 2 || cpu == 1 && player == 0 || cpu == 2 && player == 1 ){
            this.cpuStarsUpdate();
        }
    }
    public void jogar( View v){
        if ( player < 0 ){
            new AlertDialog.Builder(this)
                    .setTitle(" atenção ")
                    .setMessage("Faça a sua jogada")
                    .setNegativeButton("Continuar", null)
                    .show();

        }else {
            this.vencedor(v);
        }
        if( playerFlag >= 3 ){
            new AlertDialog.Builder(this)
                    .setTitle("Você Venceu !!!!")
                    .setMessage("Deseja reiniciar jogo ???")
                    .setPositiveButton("sim",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                   reniciarJogo();
                                }
                            })
                    .setNegativeButton("não", null)
                    .show();
        }
        if (cpuFlag >= 3 ){
            new AlertDialog.Builder(this)
                    .setTitle("Você Perdeu !!!!")
                    .setMessage("Deseja reiniciar jogo ???")
                    .setPositiveButton("sim",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    reniciarJogo();
                                }
                            })
                    .setNegativeButton("não", null)
                    .show();
        }
    }

    public void reniciarJogo( ){
        player = -1;
        cpu = 0;
        playerFlag = 0;
        cpuFlag = 0;

        ImageView viewJogador = ( ImageView )findViewById(R.id.lblUsuario);
        viewJogador.setImageResource(android.R.drawable.ic_menu_help);
        ImageView viewCPU = findViewById(R.id.lblCPU);
        viewCPU.setImageResource(android.R.drawable.ic_menu_help);

        ImageView star = (ImageView) findViewById(R.id.imgCpuStar1);
        star.setImageResource(android.R.drawable.btn_star);

        star = (ImageView) findViewById(R.id.imgCpuStar2);
        star.setImageResource(android.R.drawable.btn_star);

        star = (ImageView) findViewById(R.id.imgCpuStar3);
        star.setImageResource(android.R.drawable.btn_star);

        star = (ImageView) findViewById(R.id.imgPStar1);
        star.setImageResource(android.R.drawable.btn_star);

        star = (ImageView) findViewById(R.id.imgPStar2);
        star.setImageResource(android.R.drawable.btn_star);

        star = (ImageView) findViewById(R.id.imgPStar3);
        star.setImageResource(android.R.drawable.btn_star);
        mp.release();
    }
}
