package pl.plauszta.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class SearchWorker extends SwingWorker<Void, Void> {

    private static final Logger log = LoggerFactory.getLogger(SearchWorker.class);

    private SearchAction searchAction;
    private SearchMethod searchMethod;

    public SearchWorker(SearchAction searchAction, SearchMethod searchMethod) {
        this.searchAction = searchAction;
        this.searchMethod = searchMethod;
    }

    @Override
    protected Void doInBackground() throws Exception {
        log.info("Start searching...");
        switch (searchMethod) {
            case FIRST:
                searchAction.findFirst();
                break;
            case NEXT:
                searchAction.findNext();
                break;
            case PREVIOUS:
                searchAction.findPrevious();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + searchMethod);
        }
        return null;
    }

    @Override
    protected void done() {
        searchAction.selectText();
    }
}
