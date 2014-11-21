package at.wrdlbrnft.helpers.lists;

/**
* Created with IntelliJ Idea 13
* User: Xaver
* Date: 24/04/14
*/
public class UpdateValueContainer<T> {
    private final T oldItem;
    private final T newItem;

    public UpdateValueContainer(T oldItem, T newItem) {
        this.oldItem = oldItem;
        this.newItem = newItem;
    }

    public T getOldItem() {
        return oldItem;
    }

    public T getNewItem() {
        return newItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateValueContainer that = (UpdateValueContainer) o;

        if (newItem != null ? !newItem.equals(that.newItem) : that.newItem != null) return false;
        if (oldItem != null ? !oldItem.equals(that.oldItem) : that.oldItem != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oldItem != null ? oldItem.hashCode() : 0;
        result = 31 * result + (newItem != null ? newItem.hashCode() : 0);
        return result;
    }
}
