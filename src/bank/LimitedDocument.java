package bank;

import javax.swing.text.*;

class LimitedDocument extends PlainDocument {
    private int limit;

    public LimitedDocument(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        // Only insert if the resulting length is within the limit
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}