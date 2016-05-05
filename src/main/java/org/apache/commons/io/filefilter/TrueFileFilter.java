package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class TrueFileFilter implements IOFileFilter, Serializable {

    public static final IOFileFilter TRUE = new TrueFileFilter();
    public static final IOFileFilter INSTANCE = TrueFileFilter.TRUE;

    public boolean accept(File file) {
        return true;
    }

    public boolean accept(File file, String s) {
        return true;
    }
}