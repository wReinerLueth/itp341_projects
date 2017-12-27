
package itp341.geoquiz;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import itp341.geoquiz.model.QuizQuestion;


/*
 *	Functionality:
 *		True / False buttons
 *			Displays message based on cheating status and correct answer
 *
 *		Next button
 *			loads next question
 *
 *		Cheat button
 *			launches CheatActivity
 *
 *
 */
public class QuizActivity extends AppCompatActivity {
	//TODO modify key
	public static final String EXTRA_ANSWER_IS_TRUE = "itp341.geoquiz.ANSWER_IS_TRUE";
	public static final String EXTRA_QUIZ_QUESTION = "itp341.geoquiz.QUIZ_QUESTION";
	private static final String TAG = "QuizActivity";

	private static final String BUNDLE_KEY_INDEX = "itp341.geoquiz.INDEX";

	public static final String PREFERENCE_FILENAME = "itp341.geoquiz.pref_file";
	public static final String PREFERENCE_NUM_CORRECT = "itp341.geoquiz.pref_num_correct";
	public static final String PREFERENCE_NUM_INCORRECT = "itp341.geoquiz.pref_num_incorrect";
	public static final String PREFERENCE_NUM_CHEAT = "itp341.geoquiz.pref_num_cheat";


	//View references
	private Button buttonTrue;
	private Button buttonFalse;
	private Button buttonNext;
	private Button buttonCheat;
	private TextView textQuestion;

	private Button buttonReset;
	private TextView textCorrectAmount;
	private TextView textIncorrectAmount;
	private TextView textCheatAmount;


	//Instance variables
	boolean isCheater;		//flag which indicates if user cheated on current question


	//TODO modify data variables


//	String[] questions;		//array of questions
//	int[] answers;			//array of answers (0 for false, 1 for true)


	QuizQuestion[] questions;

	int currentIndex;		//current question

    //score variables
    private int numCorrect = 0;
    private int numIncorrect = 0;
    private int numCheat = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() called");
		setContentView(R.layout.activity_quiz);


		//find view
		textQuestion = (TextView)findViewById(R.id.text_question);
		buttonTrue = (Button)findViewById(R.id.button_true);
		buttonFalse = (Button)findViewById(R.id.button_false);
		buttonNext = (Button)findViewById(R.id.button_next);
		buttonCheat = (Button)findViewById(R.id.button_cheat);

        buttonReset = (Button)findViewById(R.id.button_reset);
        textCorrectAmount = (TextView)findViewById(R.id.text_score_correct_amount);
        textIncorrectAmount = (TextView)findViewById(R.id.text_score_incorrect_amount);
        textCheatAmount = (TextView)findViewById(R.id.text_score_cheat_amount);


        //initialize variables
		isCheater = false;		//assume user has NOT cheated

		//TODO modify initialization of data
//		questions = getResources().getStringArray(R.array.array_questions);
//		answers = getResources().getIntArray(R.array.array_answers);

		questions = new QuizQuestion[5];
		String[] tempQuestions = getResources().getStringArray(R.array.array_questions);
		final int[] tempAnswers = getResources().getIntArray(R.array.array_answers);

		for(int i = 0; i < tempAnswers.length; i++){
			questions[i] = new QuizQuestion(tempQuestions[i], tempAnswers[i]);
		}


		//set up listeners
		buttonTrue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkAnswer(true);			//pass true because this is the True button
			}
		});


		buttonFalse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkAnswer(false);			//pass false because this is the False button
			}
		});

		//TODO modify increment question
	    /*
	     * setup buttonNext event handler
	     *
	     */
		buttonNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentIndex = (currentIndex + 1) % questions.length;
				isCheater = false;
				updateQuestion();
			}
		});

		//TODO modify buttonCheat
	    /*
	     * setup buttonCheat event handler
	     */
		buttonCheat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "cheat button clicked");
				Intent i = new Intent(QuizActivity.this, CheatActivity.class);
				Log.d(TAG, "intent created");
//				boolean answerIsTrue = (tempAnswers[currentIndex] == 1);
//				boolean answerIsTrue = questions[currentIndex].isAnswer();

//				i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
				i.putExtra(EXTRA_QUIZ_QUESTION, questions[currentIndex]);
				startActivityForResult(i, 0);
			}
		});

        // TODO: update reset listeners
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //TODO
	    /*
	     * restore any saved instance
	     */
		if (savedInstanceState != null) {
			currentIndex = savedInstanceState.getInt(BUNDLE_KEY_INDEX, 0);
		}

		updateQuestion();
		loadScores();
		updateScore();
	}

	//TODO modify update question
    /*
     * 	updateQuestion
     * 		Uses the current index to update the text view with the current question
     */
	private void updateQuestion() {
		textQuestion.setText(questions[currentIndex].getQuestion());
	}

	//TODO modify check answer
	/*
     * 	checkAnswer
     * 		input: boolean - indicates if user pressed true or false
     * 		side-effect: displays a Toast based on 1) user's answer and 2) whether they cheated or not
     *
     * 		scenarios --> Toast message
     * 			User didn't cheat and answered incorrectly 	--> incorrect
     * 			User didn't cheat and answered correctly 	--> correct
     * 			User did cheat and answered incorrectly 	--> incorrect_judgment
     * 			User did cheat and answered correctly 		--> correct_judgment
     */
	private void checkAnswer(boolean userPressedTrue) {

		boolean answerIsTrue = (questions[currentIndex].isAnswer());

		int messageResId = 0;

		if (isCheater) {
			numCheat++;
			if (userPressedTrue == answerIsTrue) {
				messageResId = R.string.toast_correct_judgment;
				numCorrect++;
			} else {
				messageResId = R.string.toast_incorrect_judgment;
				numIncorrect++;
			}
		} else {
			if (userPressedTrue == answerIsTrue) {
				messageResId = R.string.toast_correct;
				numCorrect++;
			} else {
				messageResId = R.string.toast_incorrect;
				numIncorrect++;
			}
		}

		updateScore();
		saveScore(); // could put this in onPause()
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
				.show();
	}

	/*
     * 	onActivityResult
     * 		check if user cheated and update isCheater
     */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG, "onActivityResult");
		isCheater = data.getBooleanExtra(CheatActivity.EXTRA_DID_USER_CHEAT, false);
	}

	//TODO implement onSaveInstanceState
    /*
     * 	onSaveInstanceState
     * 		save currentIndex
     */
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		Log.d(TAG, "onSaveInstanceState");
		savedInstanceState.putInt(BUNDLE_KEY_INDEX, currentIndex);
	}

	// TODO: loadScores
	//	saves numCorrect, numIncorrect, numCheat to SharedPreferences
	private void loadScores() {
		SharedPreferences prefs = getSharedPreferences(PREFERENCE_FILENAME, MODE_PRIVATE);
		numCorrect = prefs.getInt(PREFERENCE_NUM_CORRECT, 0);
		numIncorrect = prefs.getInt(PREFERENCE_NUM_INCORRECT, 0);
		numCheat = prefs.getInt(PREFERENCE_NUM_CHEAT, 0);

	}

	// TODO: updateScores
	//	updates textViews with values numCorrect, numIncorrect, numCheat
	private void updateScore() {
		textCorrectAmount.setText(String.valueOf(numCorrect));
		textCheatAmount.setText(String.valueOf(numCheat));
		textIncorrectAmount.setText(String.valueOf(numIncorrect));
	}

	// TODO: saveScores
	//	reads numCorrect, numIncorrect, numCheat from SharedPreferences
	private void saveScore() {
		// get sharedPreference object
		SharedPreferences prefs = getSharedPreferences(PREFERENCE_FILENAME, MODE_PRIVATE);
		// get the editor
		SharedPreferences.Editor editor = prefs.edit(); // now we can store data

		editor.putInt(PREFERENCE_NUM_CORRECT, numCorrect);
		editor.putInt(PREFERENCE_NUM_CHEAT, numCheat);
		editor.putInt(PREFERENCE_NUM_INCORRECT, numIncorrect);

		// commit or save the changes
		editor.commit();

	}


}