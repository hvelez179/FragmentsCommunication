package com.londonappbrewery.quizzler;


public class TrueFalse {

    private int mQuestionID;
    private boolean mAnswer;

    public TrueFalse(int questionResourceID, boolean trueOrFalse) {
        mQuestionID = questionResourceID;
        mAnswer = trueOrFalse;
    }
}
