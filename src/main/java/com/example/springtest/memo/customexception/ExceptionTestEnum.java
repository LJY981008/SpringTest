package com.example.springtest.memo.customexception;

public class ExceptionTestEnum {
    public enum Exceptions{
        TESTA("TestA"), TESTB("TestB"), TESTC("TestC");
        private String exceptionsString;
        Exceptions(String exceptionsString){this.exceptionsString = exceptionsString;}
        public String getExceptionsString(){return this.exceptionsString;}

        @Override
        public String toString(){ return getExceptionsString() + " Exception.";}
    }
}
