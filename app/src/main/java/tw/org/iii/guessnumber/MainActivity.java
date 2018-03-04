package tw.org.iii.guessnumber;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView log, mesg;
    private EditText input;
    private Button guess;
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = findViewById(R.id.log);
        input = findViewById(R.id.input);
        mesg = findViewById(R.id.mesg);
        guess = findViewById(R.id.guess);
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGuess();
            }
        });
        mesg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesg.setVisibility(View.GONE);
            }
        });

        initGame();
    }

    private void initGame(){

        answer = createAnswer(3);
        input.setText("");
        log.setText("");
        answer = "123";
        Log.v("brad", answer);
    }

    private void doGuess(){
        String strInput = input.getText().toString();

        input.setText("");

        String result = checkAB(answer, strInput);
        log.append(strInput + ":" + result + "\n");

        if (result.equals("3A0B")){
            showDialog();
        }

    }

    private void showDialog(){
        //mesg.setVisibility(View.VISIBLE);

        AlertDialog alert = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("WINNER");
        builder.setMessage("恭喜老爺,賀喜夫人");
        alert = builder.create();
        alert.show();





    }


    static String checkAB(String a, String g) {
        int A, B; A = B = 0;

        for (int i=0 ;i<a.length(); i++) {
            if (g.charAt(i) == a.charAt(i)) {
                A++;
            }else if (a.indexOf(g.charAt(i)) != -1) {
                B++;
            }
        }

        return A + "A" + B +"B";
    }

    static String createAnswer(int d) {
        int[] poker = new int[10];
        for (int i=0; i<poker.length; i++) poker[i] = i;

        for (int i=poker.length; i>0; i--) {
            int rand = (int)(Math.random()*i);
            int temp = poker[rand];
            poker[rand] = poker[i-1];
            poker[i-1] = temp;
        }

        String ret = "";
        for (int i=0; i<d; i++) {
            ret += poker[i];
        }

        return ret;
    }

}
