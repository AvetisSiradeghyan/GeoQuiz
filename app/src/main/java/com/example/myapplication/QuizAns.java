package com.example.myapplication;

import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class QuizAns {



    public static String[] questions ={

            "Վանաձորը Հայաստանի որերո՞րդ խոշորագույն քաղաքը է հանդիսանում:",
            "Ո՞ր մարզի մարզկենտրոնն է Վանաձորը:",
            "Ո՞րը Վանաձոր քաղաքի թաղամաս չէ:",
            "Որքա՞ն է կազմում Վանաձորի ճանապարհների երկարությունը:",
            "Վանաձորը ո՞ր պետության հետ ունի երկաթուղային կապ:",


    };

    public static String[][] choices = {
            {"Առաջին", "Երկրորդ", "Երրորդ", "Չորրորդ"},
            {"Լոռի", "Տավուշ", "Շիրակ", "Սյունիք"},
            {"Ջունգլիներ", "Դիմաց", "Նորք Մարաշ", "Քիմգործարան"},
            {"152կմ", "256կմ", "85կմ", "161կմ"},
            {"Ոչ մի", "Վրաստան", "Իրան", "Իրան և Վրաստան"},


    };

    public static String[] answers = {
            "Երրորդ",
            "Լոռի",
            "Նորք Մարաշ",
            "161կմ",
            "Վրաստան",



    };
}
