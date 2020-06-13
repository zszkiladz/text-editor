package pl.plauszta.search;

import javax.swing.*;

public class SearchAction {
    private final JTextArea textArea;
    private final JTextField textSearch;
    private SearchStrategy currentSearch;

    public SearchAction(JTextField textSearch, JTextArea textArea) {
        this.textSearch = textSearch;
        this.textArea = textArea;
        currentSearch = new SimpleSearch(textArea, textSearch);
    }

    public void changeSearch(boolean isRegex) {
        currentSearch = createCurrentSearch(isRegex);
    }

    private SearchStrategy createCurrentSearch(boolean isRegex) {
        return isRegex ? new RegexSearch(textArea, textSearch) : new SimpleSearch(textArea, textSearch);
    }

    public void findFirst() {
        currentSearch.findFirst();
    }

    public void findNext() {
        currentSearch.findNext();
    }

    public void findPrevious() {
        currentSearch.findPrev();
    }

    public void selectText() {
        currentSearch.selectText();
    }
}
