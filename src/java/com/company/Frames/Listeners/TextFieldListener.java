package com.company.Frames.Listeners;

import com.company.Frames.Utils.DataCarrier;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * Listener that in case of any update of a TextField updates value in a DataCarrier
 */
public class TextFieldListener implements DocumentListener {
    private final DataCarrier<String> data;

    public TextFieldListener(DataCarrier<String> data) {
        this.data = data;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        update(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        update(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        update(e);
    }

    private void update(DocumentEvent e) {
        try {
            Document document = e.getDocument();
            String text = document.getText(0, document.getLength());
            data.setValue(text);
        } catch (Exception ignored) {
        }
    }
}
