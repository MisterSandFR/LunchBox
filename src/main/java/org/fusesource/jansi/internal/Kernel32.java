package org.fusesource.jansi.internal;

import java.io.IOException;
import org.fusesource.hawtjni.runtime.ArgFlag;
import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.FieldFlag;
import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniField;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.Library;
import org.fusesource.hawtjni.runtime.MethodFlag;
import org.fusesource.hawtjni.runtime.PointerMath;

@JniClass(
    conditional = "defined(_WIN32) || defined(_WIN64)"
)
public class Kernel32 {

    private static final Library LIBRARY = new Library("jansi", Kernel32.class);
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short FOREGROUND_BLUE;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short FOREGROUND_GREEN;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short FOREGROUND_RED;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short FOREGROUND_INTENSITY;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short BACKGROUND_BLUE;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short BACKGROUND_GREEN;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short BACKGROUND_RED;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short BACKGROUND_INTENSITY;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short COMMON_LVB_LEADING_BYTE;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short COMMON_LVB_TRAILING_BYTE;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short COMMON_LVB_GRID_HORIZONTAL;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short COMMON_LVB_GRID_LVERTICAL;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short COMMON_LVB_GRID_RVERTICAL;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short COMMON_LVB_REVERSE_VIDEO;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static short COMMON_LVB_UNDERSCORE;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static int FORMAT_MESSAGE_FROM_SYSTEM;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static int STD_INPUT_HANDLE;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static int STD_OUTPUT_HANDLE;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static int STD_ERROR_HANDLE;
    @JniField(
        flags = { FieldFlag.CONSTANT}
    )
    public static int INVALID_HANDLE_VALUE;

    @JniMethod(
        flags = { MethodFlag.CONSTANT_INITIALIZER}
    )
    private static final native void init();

    @JniMethod(
        cast = "void *"
    )
    public static final native long malloc(@JniArg(
    cast = "size_t"
) long i);

    public static final native void free(@JniArg(
    cast = "void *"
) long i);

    public static final native int SetConsoleTextAttribute(@JniArg(
    cast = "HANDLE"
) long i, short short0);

    public static final native int CloseHandle(@JniArg(
    cast = "HANDLE"
) long i);

    public static final native int GetLastError();

    public static final native int FormatMessageW(int i, @JniArg(
    cast = "void *"
) long j, int k, int l, @JniArg(
    cast = "void *",
    flags = { ArgFlag.NO_IN, ArgFlag.CRITICAL}
) byte[] abyte, int i1, @JniArg(
    cast = "void *",
    flags = { ArgFlag.NO_IN, ArgFlag.CRITICAL, ArgFlag.SENTINEL}
) long[] along);

    public static final native int GetConsoleScreenBufferInfo(@JniArg(
    cast = "HANDLE",
    flags = { ArgFlag.POINTER_ARG}
) long i, Kernel32.CONSOLE_SCREEN_BUFFER_INFO kernel32_console_screen_buffer_info);

    @JniMethod(
        cast = "HANDLE",
        flags = { MethodFlag.POINTER_RETURN}
    )
    public static final native long GetStdHandle(int i);

    public static final native int SetConsoleCursorPosition(@JniArg(
    cast = "HANDLE",
    flags = { ArgFlag.POINTER_ARG}
) long i, @JniArg(
    flags = { ArgFlag.BY_VALUE}
) Kernel32.COORD kernel32_coord);

    public static final native int FillConsoleOutputCharacterW(@JniArg(
    cast = "HANDLE",
    flags = { ArgFlag.POINTER_ARG}
) long i, char c0, int j, @JniArg(
    flags = { ArgFlag.BY_VALUE}
) Kernel32.COORD kernel32_coord, int[] aint);

    public static final native int WriteConsoleW(@JniArg(
    cast = "HANDLE",
    flags = { ArgFlag.POINTER_ARG}
) long i, char[] achar, int j, int[] aint, @JniArg(
    cast = "LPVOID",
    flags = { ArgFlag.POINTER_ARG}
) long k);

    public static final native int GetConsoleMode(@JniArg(
    cast = "HANDLE",
    flags = { ArgFlag.POINTER_ARG}
) long i, int[] aint);

    public static final native int SetConsoleMode(@JniArg(
    cast = "HANDLE",
    flags = { ArgFlag.POINTER_ARG}
) long i, int j);

    public static final native int _getch();

    public static final native int SetConsoleTitle(@JniArg(
    flags = { ArgFlag.UNICODE}
) String s);

    public static final native int GetConsoleOutputCP();

    public static final native int SetConsoleOutputCP(int i);

    private static final native int ReadConsoleInputW(@JniArg(
    cast = "HANDLE",
    flags = { ArgFlag.POINTER_ARG}
) long i, @JniArg(
    cast = "PINPUT_RECORD",
    flags = { ArgFlag.POINTER_ARG}
) long j, int k, int[] aint);

    private static final native int PeekConsoleInputW(@JniArg(
    cast = "HANDLE",
    flags = { ArgFlag.POINTER_ARG}
) long i, @JniArg(
    cast = "PINPUT_RECORD",
    flags = { ArgFlag.POINTER_ARG}
) long j, int k, int[] aint);

    public static final native int GetNumberOfConsoleInputEvents(@JniArg(
    cast = "HANDLE",
    flags = { ArgFlag.POINTER_ARG}
) long i, int[] aint);

    public static final native int FlushConsoleInputBuffer(@JniArg(
    cast = "HANDLE",
    flags = { ArgFlag.POINTER_ARG}
) long i);

    public static Kernel32.INPUT_RECORD[] readConsoleInputHelper(long handle, int count, boolean peek) throws IOException {
        int[] length = new int[1];
        long inputRecordPtr = 0L;

        try {
            inputRecordPtr = malloc((long) (Kernel32.INPUT_RECORD.SIZEOF * count));
            if (inputRecordPtr == 0L) {
                throw new IOException("cannot allocate memory with JNI");
            } else {
                int res = peek ? PeekConsoleInputW(handle, inputRecordPtr, count, length) : ReadConsoleInputW(handle, inputRecordPtr, count, length);

                if (res == 0) {
                    throw new IOException("ReadConsoleInputW failed");
                } else {
                    Kernel32.INPUT_RECORD[] records;

                    if (length[0] <= 0) {
                        records = new Kernel32.INPUT_RECORD[0];
                        return records;
                    } else {
                        records = new Kernel32.INPUT_RECORD[length[0]];

                        for (int i = 0; i < records.length; ++i) {
                            records[i] = new Kernel32.INPUT_RECORD();
                            Kernel32.INPUT_RECORD.memmove(records[i], PointerMath.add(inputRecordPtr, (long) (i * Kernel32.INPUT_RECORD.SIZEOF)), (long) Kernel32.INPUT_RECORD.SIZEOF);
                        }

                        Kernel32.INPUT_RECORD[] akernel32_input_record = records;

                        return akernel32_input_record;
                    }
                }
            }
        } finally {
            if (inputRecordPtr != 0L) {
                free(inputRecordPtr);
            }

        }
    }

    public static Kernel32.INPUT_RECORD[] readConsoleKeyInput(long handle, int count, boolean peek) throws IOException {
        Kernel32.INPUT_RECORD[] evts;
        int keyEvtCount;
        Kernel32.INPUT_RECORD[] res;
        int i;

        do {
            evts = readConsoleInputHelper(handle, count, peek);
            keyEvtCount = 0;
            res = evts;
            i = evts.length;

            for (int arr$ = 0; arr$ < i; ++arr$) {
                Kernel32.INPUT_RECORD len$ = res[arr$];

                if (len$.eventType == Kernel32.INPUT_RECORD.KEY_EVENT) {
                    ++keyEvtCount;
                }
            }
        } while (keyEvtCount <= 0);

        res = new Kernel32.INPUT_RECORD[keyEvtCount];
        i = 0;
        Kernel32.INPUT_RECORD[] akernel32_input_record = evts;
        int i = evts.length;

        for (int i$ = 0; i$ < i; ++i$) {
            Kernel32.INPUT_RECORD evt = akernel32_input_record[i$];

            if (evt.eventType == Kernel32.INPUT_RECORD.KEY_EVENT) {
                res[i++] = evt;
            }
        }

        return res;
    }

    static {
        Kernel32.LIBRARY.load();
        init();
    }

    @JniClass(
        flags = { ClassFlag.STRUCT, ClassFlag.TYPEDEF},
        conditional = "defined(_WIN32) || defined(_WIN64)"
    )
    public static class INPUT_RECORD {

        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "sizeof(INPUT_RECORD)"
        )
        public static int SIZEOF;
        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "KEY_EVENT"
        )
        public static short KEY_EVENT;
        @JniField(
            accessor = "EventType"
        )
        public short eventType;
        @JniField(
            accessor = "Event.KeyEvent"
        )
        public Kernel32.KEY_EVENT_RECORD keyEvent = new Kernel32.KEY_EVENT_RECORD();

        @JniMethod(
            flags = { MethodFlag.CONSTANT_INITIALIZER}
        )
        private static final native void init();

        public static final native void memmove(@JniArg(
    cast = "void *",
    flags = { ArgFlag.NO_IN, ArgFlag.CRITICAL}
) Kernel32.INPUT_RECORD kernel32_input_record, @JniArg(
    cast = "const void *",
    flags = { ArgFlag.NO_OUT, ArgFlag.CRITICAL}
) long i, @JniArg(
    cast = "size_t"
) long j);

        static {
            Kernel32.LIBRARY.load();
            init();
        }
    }

    @JniClass(
        flags = { ClassFlag.STRUCT, ClassFlag.TYPEDEF},
        conditional = "defined(_WIN32) || defined(_WIN64)"
    )
    public static class KEY_EVENT_RECORD {

        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "sizeof(KEY_EVENT_RECORD)"
        )
        public static int SIZEOF;
        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "CAPSLOCK_ON"
        )
        public static int CAPSLOCK_ON;
        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "NUMLOCK_ON"
        )
        public static int NUMLOCK_ON;
        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "SCROLLLOCK_ON"
        )
        public static int SCROLLLOCK_ON;
        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "ENHANCED_KEY"
        )
        public static int ENHANCED_KEY;
        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "LEFT_ALT_PRESSED"
        )
        public static int LEFT_ALT_PRESSED;
        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "LEFT_CTRL_PRESSED"
        )
        public static int LEFT_CTRL_PRESSED;
        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "RIGHT_ALT_PRESSED"
        )
        public static int RIGHT_ALT_PRESSED;
        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "RIGHT_CTRL_PRESSED"
        )
        public static int RIGHT_CTRL_PRESSED;
        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "SHIFT_PRESSED"
        )
        public static int SHIFT_PRESSED;
        @JniField(
            accessor = "bKeyDown"
        )
        public boolean keyDown;
        @JniField(
            accessor = "wRepeatCount"
        )
        public short repeatCount;
        @JniField(
            accessor = "wVirtualKeyCode"
        )
        public short keyCode;
        @JniField(
            accessor = "wVirtualScanCode"
        )
        public short scanCode;
        @JniField(
            accessor = "uChar.UnicodeChar"
        )
        public char uchar;
        @JniField(
            accessor = "dwControlKeyState"
        )
        public int controlKeyState;

        @JniMethod(
            flags = { MethodFlag.CONSTANT_INITIALIZER}
        )
        private static final native void init();

        public String toString() {
            return "KEY_EVENT_RECORD{keyDown=" + this.keyDown + ", repeatCount=" + this.repeatCount + ", keyCode=" + this.keyCode + ", scanCode=" + this.scanCode + ", uchar=" + this.uchar + ", controlKeyState=" + this.controlKeyState + '}';
        }

        static {
            Kernel32.LIBRARY.load();
            init();
        }
    }

    @JniClass(
        flags = { ClassFlag.STRUCT, ClassFlag.TYPEDEF},
        conditional = "defined(_WIN32) || defined(_WIN64)"
    )
    public static class CONSOLE_SCREEN_BUFFER_INFO {

        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "sizeof(CONSOLE_SCREEN_BUFFER_INFO)"
        )
        public static int SIZEOF;
        @JniField(
            accessor = "dwSize"
        )
        public Kernel32.COORD size = new Kernel32.COORD();
        @JniField(
            accessor = "dwCursorPosition"
        )
        public Kernel32.COORD cursorPosition = new Kernel32.COORD();
        @JniField(
            accessor = "wAttributes"
        )
        public short attributes;
        @JniField(
            accessor = "srWindow"
        )
        public Kernel32.SMALL_RECT window = new Kernel32.SMALL_RECT();
        @JniField(
            accessor = "dwMaximumWindowSize"
        )
        public Kernel32.COORD maximumWindowSize = new Kernel32.COORD();

        @JniMethod(
            flags = { MethodFlag.CONSTANT_INITIALIZER}
        )
        private static final native void init();

        public int windowWidth() {
            return this.window.width() + 1;
        }

        public int windowHeight() {
            return this.window.height() + 1;
        }

        static {
            Kernel32.LIBRARY.load();
            init();
        }
    }

    @JniClass(
        flags = { ClassFlag.STRUCT, ClassFlag.TYPEDEF},
        conditional = "defined(_WIN32) || defined(_WIN64)"
    )
    public static class COORD {

        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "sizeof(COORD)"
        )
        public static int SIZEOF;
        @JniField(
            accessor = "X"
        )
        public short x;
        @JniField(
            accessor = "Y"
        )
        public short y;

        @JniMethod(
            flags = { MethodFlag.CONSTANT_INITIALIZER}
        )
        private static final native void init();

        public Kernel32.COORD copy() {
            Kernel32.COORD rc = new Kernel32.COORD();

            rc.x = this.x;
            rc.y = this.y;
            return rc;
        }

        static {
            Kernel32.LIBRARY.load();
            init();
        }
    }

    @JniClass(
        flags = { ClassFlag.STRUCT, ClassFlag.TYPEDEF},
        conditional = "defined(_WIN32) || defined(_WIN64)"
    )
    public static class SMALL_RECT {

        @JniField(
            flags = { FieldFlag.CONSTANT},
            accessor = "sizeof(SMALL_RECT)"
        )
        public static int SIZEOF;
        @JniField(
            accessor = "Left"
        )
        public short left;
        @JniField(
            accessor = "Top"
        )
        public short top;
        @JniField(
            accessor = "Right"
        )
        public short right;
        @JniField(
            accessor = "Bottom"
        )
        public short bottom;

        @JniMethod(
            flags = { MethodFlag.CONSTANT_INITIALIZER}
        )
        private static final native void init();

        public short width() {
            return (short) (this.right - this.left);
        }

        public short height() {
            return (short) (this.bottom - this.top);
        }

        static {
            Kernel32.LIBRARY.load();
            init();
        }
    }
}
