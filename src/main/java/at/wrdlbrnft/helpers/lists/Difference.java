package at.wrdlbrnft.helpers.lists;

import java.util.List;

/**
* Created with IntelliJ Idea 13
* User: Xaver
* Date: 24/04/14
*/
public class Difference<T> {

    private final int addedCount;
    private final int removedCount;
    private final boolean identical;
    private final List<T> added;
    private final List<T> removed;

    Difference(List<T> added, List<T> removed) {
        this.added = added;
        this.removed = removed;

        if (added != null) {
            this.addedCount = added.size();
        } else {
            throw new IllegalStateException("List of added items is null");
        }

        if (removed != null) {
            this.removedCount = removed.size();
        } else {
            throw new IllegalStateException("List of removed items is null");
        }

        this.identical = this.removedCount == 0 && this.addedCount == 0;
    }

    public List<T> getAdded() {
        return added;
    }

    public List<T> getRemoved() {
        return removed;
    }

    public int getAddedCount() {
        return addedCount;
    }

    public int getRemovedCount() {
        return removedCount;
    }

    public boolean isIdentical() {
        return identical;
    }
}
