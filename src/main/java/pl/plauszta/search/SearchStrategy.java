package pl.plauszta.search;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

abstract class SearchStrategy {
    protected AtomicInteger index = new AtomicInteger(-1);
    protected JTextArea textArea;
    protected JTextField searchField;

    SearchStrategy(JTextArea textArea, JTextField searchField) {
        this.textArea = textArea;
        this.searchField = searchField;
    }

    abstract void findFirst();

    abstract void findNext();

    abstract void findPrev();
}
