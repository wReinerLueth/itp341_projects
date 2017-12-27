package itp341.geoquiz.model;

import java.io.Serializable;

/**
 * Created by empty on 9/25/2017.
 */

public class QuizQuestion implements Serializable {
    private String question;
    private boolean answer;

    @Override
    public String toString() {
        return "QuizQuestion{" +
                "question='" + question + '\'' +
                ", answer=" + answer +
                '}';
    }

    public QuizQuestion(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public QuizQuestion(String question, int boolAsInt) {
        this.question = question;
        if(boolAsInt == 0) this.answer = false;
        else this.answer = true;
    }

    public String getQuestion() {

        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
