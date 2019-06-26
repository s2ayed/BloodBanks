package com.example.bloodbank.helper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.bloodbank.R;
import com.example.bloodbank.ui.fragment.LoginFragment;

public class HelperMathod {


// check length password
 public static boolean checkLengthPassword(String newPassword){
   if ( newPassword.length() <= 6){
    return false;
   }
  return true;
 }

// check Correspond password  == ConfirmPassword

 public static boolean checkCorrespondPassword(String newPassword,String ConfirmPassword){
  if (!newPassword.equals(ConfirmPassword)  ) {
   return false;

  }
   return true;
 }

 // get start fragment
 public static void getStartFragments(FragmentManager supportFragmentManager, int  ReplaceFragment, Fragment fragment) {
  supportFragmentManager.beginTransaction().replace(ReplaceFragment, fragment).commit();
 }


}
