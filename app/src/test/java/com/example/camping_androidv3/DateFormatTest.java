package com.example.camping_androidv3;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

import android.util.Log;

public class DateFormatTest {

  @Test
  public void testDateFormatIsCorrect() {
    String dateInscription = "2025-05-26";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    format.setLenient(false); //force validation stricte

    try {
      format.parse(dateInscription);
      //si parsing réussit alors format correct
      assertTrue(true);

      System.out.println("La date est au bon format");
    } catch (ParseException e) {
      fail("La date n'est pas au bon format : yyyy-MM-dd");
    }
  }
}
