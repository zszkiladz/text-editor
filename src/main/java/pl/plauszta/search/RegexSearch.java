package pl.plauszta.search;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegexSearch extends SearchStrategy {

    RegexSearch(JTextArea textArea, JTextField found) {
        super(textArea, found);
    }

    @Override
    void findFirst() {
        String text = textArea.getText();
        final String found = searchField.getText();
        Pattern pattern = Pattern.compile(found);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            index.set(matcher.start());
        }
        if (index.get() != -1) {
            textArea.setCaretPosition(matcher.end());
            textArea.select(matcher.start(), matcher.end());
            textArea.grabFocus();
        }
    }

    @Override
    void findNext() {
        String text = textArea.getText();
        final String found = searchField.getText();
        Pattern pattern = Pattern.compile(found);
        Matcher matcher = pattern.matcher(text);
        matcher.region(index.get() == -1 ? 0 : index.get() + 1, text.length());
        if (matcher.find()) {
            index.set(matcher.start());
        } else {
            matcher.region(0, text.length());
            if (matcher.find()) {
                index.set(matcher.start());
            }
        }
        if (index.get() != -1) {
            textArea.setCaretPosition(matcher.end());
            textArea.select(matcher.start(), matcher.end());
            textArea.grabFocus();
        }
    }

    @Override
    void findPrev() {
        String text = textArea.getText();
        final String found = searchField.getText();
        Pattern pattern = Pattern.compile(found);
        Matcher matcher = pattern.matcher(text);
        int nextIndex = -1;
        while (matcher.find()) {
            if (matcher.start() < index.get()) {
                nextIndex = matcher.start();
                matcher.region(nextIndex + 1, text.length());
            }
        }
        index.set(nextIndex);
        if (nextIndex == -1) {
            findLastIndex(text, found, matcher, index);
        }
        matcher.region(index.get() == -1 ? 0 : index.get(), text.length());
        matcher.find();
        if (index.get() != -1) {
            textArea.setCaretPosition(matcher.end());
            textArea.select(matcher.start(), matcher.end());
            textArea.grabFocus();
        }
    }

    private void findLastIndex(String text, String found, Matcher matcher, AtomicInteger index) {
        int nextIndex = -1;
        matcher.region(0, text.length());
        while (matcher.find()) {
            nextIndex = matcher.start();
            if (nextIndex + found.length() < text.length()) {
                matcher.region(nextIndex + 1, text.length());
            }
        }
        index.set(nextIndex);
    }
}
