package itp341.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import itp341.geoquiz.model.QuizQuestion;


/*
 *	Functionality:
 *		Show Answer Button
 *			Displays answer to question (either true or false) using String resource
 *			Sets intent boolean extra value to false
 *
 *		Back Button
 *			User will terminate CheatActivity by pressing the back button
 *			
 *	Sending Intent Result back to Quiz Activity:
 * 		Instead of using Result_OK or Result_CANCELED, we will create an Intent 
 * 		and pass an boolean Extra that will indicate if the user cheated or not
 * 
 * 	Second Thoughts:
 * 		If the user loads this activity but decides not to click Show Answer, 
 * 		then that is NOT considered cheating so the boolean extra should indicate false
 * 
 */

public class CheatActivity extends AppCompatActivity {
	private static final String TAG = "CheatActivity";

	public static final String EXTRA_DID_USER_CHEAT = "extra_did_user_cheat";
	private boolean didUserCheat = false;

	// View references
	TextView textAnswer;
	Button buttonShowAnswer;

	// instance variables
//	boolean answerIsTrue;
	QuizQuestion currentQuestion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() called");
		setContentView(R.layout.activity_cheat);
		Log.d(TAG, getCallingActivity().toString());

		//find views
		textAnswer = (TextView) findViewById(R.id.text_answer);
		buttonShowAnswer = (Button) findViewById(R.id.button_show_answer);

		// If user opens activity and decides not to cheat, we should
		// set boolean extra to false

		//TODO modify intent

//		answerIsTrue = getIntent()
//				.getBooleanExtra(QuizActivity.EXTRA_ANSWER_IS_TRUE, false);

		currentQuestion = (QuizQuestion) getIntent().getSerializableExtra(QuizActivity.EXTRA_QUIZ_QUESTION);

	    /*
	     * 	setup buttonShowAnswer event handler
	     * 		- displays the correct answer
	     * 		- sets result value to indicate cheating
	     */
		buttonShowAnswer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {		//user has decided to cheat after all
				if (currentQuestion.isAnswer()) {
					textAnswer.setText(R.string.label_true);
				}
				else  {
					textAnswer.setText(R.string.label_false);
				}
				//if this was clicked, then user definitely cheated
				didUserCheat = true;
				Intent i = new Intent();	//notice NO PARAMETERS -- this is a "dummy" intent
				i.putExtra(EXTRA_DID_USER_CHEAT, didUserCheat);
				//call setResult to prepare this data for parent activity

				setResult(Activity.RESULT_OK, i);

				//that's it!


			}
		});
	}


}
