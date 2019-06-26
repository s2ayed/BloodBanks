package com.example.bloodbank.helper;

 import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


public class HelperMathod {


// check length password
 public static boolean checkLengthPassword(String newPassword){
     return newPassword.length() > 6;
 }

// check Correspond password  == ConfirmPassword

 public static boolean checkCorrespondPassword(String newPassword,String ConfirmPassword){
     return newPassword.equals(ConfirmPassword);
 }

 // get start fragment
 public static void getStartFragments(FragmentManager supportFragmentManager, int  ReplaceFragment, Fragment fragment) {
  supportFragmentManager.beginTransaction().replace(ReplaceFragment, fragment).commit();
 }


}
