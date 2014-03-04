package javaapplication6;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

class checkTextPresent {

    UtilitiesFunctions utl = new UtilitiesFunctions();

    checkTextPresent(final JTextArea jTextArea1, final JButton jButton1) {
        final Document document = jTextArea1.getDocument();
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changed(e);
            }
            String str = jTextArea1.getText().trim();

            private void changed(DocumentEvent e) {
                DocumentEvent.EventType type = e.getType();
                if (type.equals(DocumentEvent.EventType.CHANGE)) {
                    checkCondition();
                } else if (type.equals(DocumentEvent.EventType.INSERT)) {
                    checkCondition();
                } else if (type.equals(DocumentEvent.EventType.REMOVE)) {
                    checkCondition();
                }

            }

            public void checkCondition() {
                if (jTextArea1.getText().equals("")) {
                    jButton1.hide();
                    utl.repaintandvalidate(jButton1);

                } else {
                    jButton1.show();
                    utl.repaintandvalidate(jButton1);
                }
            }
        });
    }
}
