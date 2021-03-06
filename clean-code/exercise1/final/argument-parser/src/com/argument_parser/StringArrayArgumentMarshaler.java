package com.argument_parser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.argument_parser.ArgsException.ErrorCode.*;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
    private String[] stringArrayValue = {};

    public void set(Iterator<String> currentArgument) throws ArgsException {
        try {
            for (int i = 0; currentArgument.hasNext(); i++) {
                stringArrayValue[i] = currentArgument.next();
            }
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_STRING);
        }
    }

    public static String[] getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof StringArrayArgumentMarshaler) {
            return ((StringArrayArgumentMarshaler) am).stringArrayValue;
        } else {
            return new String[]{};
        }
    }
}
