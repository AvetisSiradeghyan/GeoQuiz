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


//    String town;
//    public QuizAns() {
//        a();
//    }
//
//    public void a() {
//
//        Bundle extras = getIntent().getExtras();
//        if(extras != null){
//            town = extras.getString("title");
//        }
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user != null){
//
//
//            FirebaseFirestore.getInstance().collection("Quiz").whereEqualTo("Town", town)
//                    .get()
//                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                        @Override
//                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                            for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots){
//                                queryDocumentSnapshot.get("Choices", Map.class);
//
//                            }
//
//
//
//                        }
//                    });
//        }
//    }
    public static String questions[] ={

            "Հայաստանի որերո՞րդ խոշորագույն քաղաքը է հանդիսանում",
            "Ո՞ր մարզի մարզկենտրոնն է",
            "Ո՞րը Վանաձոր քաղաքի թաղամաս չէ",
            "Որքա՞ն է կազմում Վանաձորի ճանապարհների երկարությունը",
            "Ո՞ր պետության հետ ունի երկաթուղային կապ",


    };

    public static  String choices [][] = {
            {"Առաջին", "Երկրորդ", "Երրորդ", "Չորրորդ"},
            {"Լոռի", "Տավուշ", "Շիրակ", "Սյունիք"},
            {"Ջունգլիներ", "Դիմաց", "Նորք Մարաշ", "Քիմգործարան"},
            {"152կմ", "256կմ", "85կմ", "161կմ"},
            {"Ոչ մի", "Վրաստան", "Իրան", "Իրան և Վրաստան"},


    };

    public static String answers [] = {
            "Երրորդ",
            "Լոռի",
            "Նորք Մարաշ",
            "161կմ",
            "Վրաստան",



    };
}
