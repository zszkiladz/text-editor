package pl.plauszta.search;

import javax.swing.*;

public class SimpleSearch extends SearchStrategy {

    public SimpleSearch(JTextArea textArea, JTextField searchField) {
        super(textArea, searchField);
    }

    @Override
    public void findFirst() {
        String text = textArea.getText();
        final String found = searchField.getText();
        index.set(text.indexOf(found));
        if (index.get() != -1) {
            textArea.setCaretPosition(index.get() + found.length());
            textArea.select(index.get(), index.get() + found.length());
            textArea.grabFocus();
        }
    }

    @Override
    public void findNext() {
        String text = textArea.getText();
        final String found = searchField.getText();
        index.set(text.indexOf(found, index.get() + 1));
        if (index.get() == -1) {
            index.set(text.indexOf(found));
        }
        if (index.get() != -1) {
            textArea.setCaretPosition(index.get() + found.length());
            textArea.select(index.get(), index.get() + found.length());
            textArea.grabFocus();
        }
    }

    @Override
    public void findPrev() {
        String text = textArea.getText();
        final String found = searchField.getText();
        index.set(text.substring(0, index.get()).lastIndexOf(found));
        if (index.get() == -1) {
            index.set(text.lastIndexOf(found));
        }
        if (index.get() != -1) {
            textArea.setCaretPosition(index.get() + found.length());
            textArea.select(index.get(), index.get() + found.length());
            textArea.grabFocus();
        }
    }
}
