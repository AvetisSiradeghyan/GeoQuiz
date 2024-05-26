package com.example.myapplication;

public class Quiz_Model {

    public String getQuestion1() {
        return question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public String getQuestion4() {
        return question4;
    }

    public String getQuestion5() {
        return question5;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public String getAns5() {
        return ans5;
    }

    public String getChoice1_1() {
        return choice1_1;
    }

    public String getChoice1_2() {
        return choice1_2;
    }

    public String getChoice1_3() {
        return choice1_3;
    }

    public String getChoice1_4() {
        return choice1_4;
    }

    public String getChoice2_1() {
        return choice2_1;
    }

    public String getChoice2_2() {
        return choice2_2;
    }

    public String getChoice2_3() {
        return choice2_3;
    }

    public String getChoice3_1() {
        return choice3_1;
    }

    public String getChoice2_4() {
        return choice2_4;
    }

    public String getChoice3_2() {
        return choice3_2;
    }

    public String getChoice3_3() {
        return choice3_3;
    }

    public String getChoice3_4() {
        return choice3_4;
    }

    public String getChoice4_1() {
        return choice4_1;
    }

    public String getChoice4_2() {
        return choice4_2;
    }

    public String getChoice4_3() {
        return choice4_3;
    }

    public String getChoice4_4() {
        return choice4_4;
    }

    public String getChoice5_1() {
        return choice5_1;
    }

    public String getChoice5_2() {
        return choice5_2;
    }

    public String getChoice5_3() {
        return choice5_3;
    }

    public String getChoice5_4() {
        return choice5_4;
    }

    public String[] getAnswers() {
        answers = new  String[]{
                ans1,
                ans2,
                ans3,
                ans4,
                ans5,
        };
        return answers;
    }

    public String[][] getChoices() {
        choices  = new String[][]{
                {choice1_1, choice1_2, choice1_3, choice1_4},
                {choice2_1, choice2_2, choice2_3, choice2_4},
                {choice3_1, choice3_2, choice3_3, choice3_4},
                {choice4_1, choice4_2, choice4_3, choice4_4},
                {choice5_1, choice5_2, choice5_3, choice5_4},
        };
        return choices;
    }

    public String[] getQuestions() {
        questions = new String[]{
                question1,
                question2,
                question3,
                question4,
                question5
        };
        return questions;
    }

    String question1, question2, question3, question4, question5, ans1, ans2, ans3, ans4, ans5, choice1_1, choice1_2, choice1_3,
            choice1_4, choice2_1, choice2_2, choice2_3, choice3_1, choice2_4, choice3_2, choice3_3, choice3_4, choice4_1, choice4_2, choice4_3
            , choice4_4,choice5_1,choice5_2, choice5_3, choice5_4;

    public String[] answers;
    public String[][] choices;
    public String[] questions;

    public Quiz_Model(String question1, String question2, String question3, String question4, String question5, String ans1, String ans2, String ans3, String ans4, String ans5, String choice1_1, String choice1_2, String choice1_3, String choice1_4, String choice2_1, String choice2_2, String choice2_3, String choice3_1, String choice2_4, String choice3_2, String choice3_3, String choice3_4, String choice4_1, String choice4_2, String choice4_3, String choice4_4, String choice5_1, String choice5_2, String choice5_3, String choice5_4) {
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.ans5 = ans5;
        this.choice1_1 = choice1_1;
        this.choice1_2 = choice1_2;
        this.choice1_3 = choice1_3;
        this.choice1_4 = choice1_4;
        this.choice2_1 = choice2_1;
        this.choice2_2 = choice2_2;
        this.choice2_3 = choice2_3;
        this.choice3_1 = choice3_1;
        this.choice2_4 = choice2_4;
        this.choice3_2 = choice3_2;
        this.choice3_3 = choice3_3;
        this.choice3_4 = choice3_4;
        this.choice4_1 = choice4_1;
        this.choice4_2 = choice4_2;
        this.choice4_3 = choice4_3;
        this.choice4_4 = choice4_4;
        this.choice5_1 = choice5_1;
        this.choice5_2 = choice5_2;
        this.choice5_3 = choice5_3;
        this.choice5_4 = choice5_4;

    }
}






