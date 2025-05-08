package com.fin.xpenses.testLibrary;

import android.util.Log;

public class TestLoombokFuntions {
    private LombookTestAnnotation test;

    public TestLoombokFuntions(){
        this.test = new LombookTestAnnotation();
    }

    public void testLombok(){
        test.setId(1);
        test.setName("uli");
        test.setLastName("rodriguez");
        Log.d("test",test.toString());
    }
}
