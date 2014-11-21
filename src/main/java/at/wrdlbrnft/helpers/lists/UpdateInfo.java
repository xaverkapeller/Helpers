package at.wrdlbrnft.helpers.lists;

import java.util.List;

/**
 * Created with IntelliJ Idea 13
 * User: Xaver
 * Date: 24/04/14
 */
public class UpdateInfo<T> {
    private final List<T> toAdd;
    private final List<T> toRemove;
    private final List<UpdateValueContainer<T>> toUpdate;
    private final boolean updateRequired;

    public UpdateInfo(List<T> toAdd, List<T> toRemove, List<UpdateValueContainer<T>> toUpdate) {
        this.toAdd = toAdd;
        this.toRemove = toRemove;
        this.toUpdate = toUpdate;
        this.updateRequired = Lists.notNullOrEmpty(toAdd)
                || Lists.notNullOrEmpty(toRemove)
                || Lists.notNullOrEmpty(toUpdate);
    }

    public List<T> getToAdd() {
        return toAdd;
    }

    public List<T> getToRemove() {
        return toRemove;
    }

    public List<UpdateValueContainer<T>> getToUpdate() {
        return toUpdate;
    }

    public boolean isUpdateRequired() {
        return updateRequired;
    }
}
