package com.example.bloodbank.helper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.bloodbank.R;
import com.example.bloodbank.ui.fragment.LoginFragment;

public class HelperMathod {



 public static boolean checkLengthPassword(String newPassword){

   if ( newPassword.length() <= 6){
    return false;
   }
  return true;
 }


 public static boolean checkCorrespondPassword(String newPassword,String ConfirmPassword){
  if (!newPassword.equals(ConfirmPassword)  ) {
   return false;

  }
   return true;
 }

 }
