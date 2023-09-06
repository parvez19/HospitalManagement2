package com.hospitalapplication2.exceptions;

    public class RecordNotAvailableException extends RuntimeException {
        public RecordNotAvailableException(String errorMessage) {
            super(errorMessage);
        }
    }

