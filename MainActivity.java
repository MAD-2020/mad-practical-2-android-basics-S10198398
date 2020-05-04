package sg.edu.np.WhackAMole;

import androidx.appcompat.app.AppCompatActivity;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.

        - Feel free to modify the function to suit your program.
    */

    private TextView CountTextView;

    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Integer score = 0;
    private static final String TAG = "Whack-A-Mole";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CountTextView = (TextView) findViewById(R.id.CountTextView);
        Button1 = (Button) findViewById(R.id.Button1);
        Button2 = (Button) findViewById(R.id.Button2);
        Button3 = (Button) findViewById(R.id.Button3);
        final List<Button> buttonList = new ArrayList<>();
        buttonList.add(Button1);
        buttonList.add(Button2);
        buttonList.add(Button3);
        setNewMole(buttonList);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<3; i++) {
                    if (buttonList.get(i).getId() == v.getId()) {
                        if(i == 0){
                            Log.v(TAG, "Button, Left clicked");
                        }
                        else if (i == 1){
                            Log.v(TAG, "Button, middle clicked");
                        }
                        else if (i == 2){
                            Log.v(TAG, "Button, right clicked");
                        }
                        if (buttonList.get(i).getText() == "O"){
                            score -= 1;
                            Log.v(TAG, "Missed, score deducted");
                        }
                        else if (buttonList.get(i).getText() == "*"){
                            score += 1;
                            Log.v(TAG, "Hit, score added!");
                        }
                    }
                }

                CountTextView.setText(score.toString());
                setNewMole(buttonList);
            }
        };
        Button1.setOnClickListener(listener);
        Button2.setOnClickListener(listener);
        Button3.setOnClickListener(listener);
        Log.v(TAG, "Finished Pre-Initialisation!");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TAG, "Starting GUI!");
    }


    public void setNewMole(List<Button> buttonList)
    {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        for(int i = 0; i < 3; i++){
            if(i == randomLocation){
                buttonList.get(i).setText("*");
            }
            else {
                buttonList.get(i).setText("O");
            }
        }
    }
}
