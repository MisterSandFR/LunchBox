package org.bukkit.craftbukkit.libs.jline.console.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import org.bukkit.craftbukkit.libs.jline.console.ConsoleReader;

class ConsoleReaderInputStream extends SequenceInputStream {

    private static InputStream systemIn = System.in;

    public static void setIn() throws IOException {
        setIn(new ConsoleReader());
    }

    public static void setIn(ConsoleReader reader) {
        System.setIn(new ConsoleReaderInputStream(reader));
    }

    public static void restoreIn() {
        System.setIn(ConsoleReaderInputStream.systemIn);
    }

    public ConsoleReaderInputStream(ConsoleReader reader) {
        super(new ConsoleReaderInputStream.ConsoleEnumeration(reader));
    }

    private static class ConsoleLineInputStream extends InputStream {

        private final ConsoleReader reader;
        private String line = null;
        private int index = 0;
        private boolean eol = false;
        protected boolean wasNull = false;

        public ConsoleLineInputStream(ConsoleReader reader) {
            this.reader = reader;
        }

        public int read() throws IOException {
            if (this.eol) {
                return -1;
            } else {
                if (this.line == null) {
                    this.line = this.reader.readLine();
                }

                if (this.line == null) {
                    this.wasNull = true;
                    return -1;
                } else if (this.index >= this.line.length()) {
                    this.eol = true;
                    return 10;
                } else {
                    return this.line.charAt(this.index++);
                }
            }
        }
    }

    private static class ConsoleEnumeration implements Enumeration {

        private final ConsoleReader reader;
        private ConsoleReaderInputStream.ConsoleLineInputStream next = null;
        private ConsoleReaderInputStream.ConsoleLineInputStream prev = null;

        public ConsoleEnumeration(ConsoleReader reader) {
            this.reader = reader;
        }

        public Object nextElement() {
            if (this.next != null) {
                ConsoleReaderInputStream.ConsoleLineInputStream n = this.next;

                this.prev = this.next;
                this.next = null;
                return n;
            } else {
                return new ConsoleReaderInputStream.ConsoleLineInputStream(this.reader);
            }
        }

        public boolean hasMoreElements() {
            if (this.prev != null && this.prev.wasNull) {
                return false;
            } else {
                if (this.next == null) {
                    this.next = (ConsoleReaderInputStream.ConsoleLineInputStream) this.nextElement();
                }

                return this.next != null;
            }
        }
    }
}
