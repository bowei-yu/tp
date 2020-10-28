package seedu.studybananas.ui.util;

import java.util.ArrayList;
import java.util.List;

import seedu.studybananas.logic.Logic;
import seedu.studybananas.model.flashcard.FlashcardSet;

public class SingletonClickedFlashcardSetState implements Observable {
    private static SingletonClickedFlashcardSetState instance;

    private List<Observer> observers = new ArrayList<>();
    private FlashcardSet clickedFlashcardSet;
    private Logic logic;

    private SingletonClickedFlashcardSetState(Logic logic) {
        clickedFlashcardSet = null;
        this.logic = logic;
    }

    /**
     * Special constructor, updateFlashcardSetState depends on the {@Code Logic} which updates the view.
     * @param logic
     * @return
     */
    public static synchronized SingletonClickedFlashcardSetState getInstance(Logic logic) {
        if (instance == null) {
            instance = new SingletonClickedFlashcardSetState(logic);
        }
        return instance;
    }


    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void inform() {
        for (Observer observer: observers) {
            observer.update(clickedFlashcardSet);
        }
    }

    /**
     * update the state. (No one subscribe to this state!)
     */
    public void updateState(FlashcardSet flashcardSet) {
        if (flashcardSet.equals(this.clickedFlashcardSet)) {
            return;
        }

        this.clickedFlashcardSet = flashcardSet;
        logic.setFlashcardSetToView(flashcardSet);
        inform();
    }
}
