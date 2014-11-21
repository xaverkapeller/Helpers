package at.wrdlbrnft.helpers.lists;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created with Android Studio.
 * User: Xaver
 * Date: 10.12.13
 * Time: 16:29
 */
public class Lists {

    public static <T> List<T> newList() {
        return new ArrayList<T>();
    }
    public static <T> LinkedList<T> newLinkedList() {
        return new LinkedList<>();
    }

    public static <T> List<T> newList(List<T> list) {
        return new ArrayList<>(list);
    }

    public static <T> List<T> newList(T... array) {
        final List<T> list = new ArrayList<>();
        Collections.addAll(list, array);
        return list;
    }

    public static <T> List<T> newList(Class<T> targetType, T... array) {
        final List<T> list = new ArrayList<>();
        Collections.addAll(list, array);
        return list;
    }

    public static <T> List<T> newList(Class<T> targetType, List<? extends T> list) {
        return new ArrayList<>(list);
    }

    public static <T> List<T> newList(Collection<T> set) {
        final List<T> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    public static <T> List<T> safe(List<T> list) {
        return list != null ? list : new ArrayList<T>();
    }

    public static <T> List<T> newList(int capacity) {
        return new ArrayList<T>(capacity);
    }

    public static <O, T extends O> List<O> transform(List<T> list, Class<O> targetType) {
        return (List<O>) list;
    }

    public static <T> T[] toArray(Class<T> cls, List<? extends T> list) {
        if(list == null) {
            return null;
        }
        final T[] array = (T[]) Array.newInstance(cls, list.size());
        return list.toArray(array);
    }

    public static <T> List<T> combine(List<T> a, List<T> b) {
        final int aSize = a != null ? a.size() : 0;
        final int bSize = b != null ? b.size() : 0;
        final List<T> output = new ArrayList<T>(aSize + bSize);
        if(a != null) {
            output.addAll(a);
        }
        if(b != null) {
            output.addAll(b);
        }
        return output;
    }

    public static <T> Difference<T> compare(List<T> oldList, List<T> newList) {
        final List<T> removed = newList(oldList);
        final List<T> added = newList(newList);

        added.removeAll(oldList);
        removed.removeAll(newList);

        return new Difference<T>(added, removed);
    }

    public static <T extends UniqueIdProvider> UpdateInfo<T> getUpdateInfo(Difference<T> difference) {
        if (difference.isIdentical()) {
            return new UpdateInfo<T>(new ArrayList<T>(), new ArrayList<T>(), new ArrayList<UpdateValueContainer<T>>());
        } else {
            List<T> difAdd = difference.getAdded();
            List<T> difRem = difference.getRemoved();

            List<T> toAdd;
            List<T> toRemove;
            List<UpdateValueContainer<T>> toUpdate = newList();

            if (difRem.size() == 0) {
                toAdd = difAdd;
                toRemove = newList();
            } else if (difAdd.size() == 0) {
                toRemove = difRem;
                toAdd = newList();
            } else {
                toAdd = newList();
                for (T added : difAdd) {
                    if (added != null) {
                        Object addedId = added.getUniqueId();
                        if (addedId != null) {
                            boolean matchFound = false;
                            for (int i = 0; i < difRem.size() && !matchFound; i++) {
                                T removed = difRem.get(i);
                                if (removed != null) {
                                    Object removedId = removed.getUniqueId();
                                    if (matchFound = addedId.equals(removedId)) {
                                        UpdateValueContainer<T> action = new UpdateValueContainer<T>(removed, added);
                                        toUpdate.add(action);
                                        difRem.remove(removed);
                                    }
                                }
                            }
                            if (!matchFound) {
                                toAdd.add(added);
                            }
                        }
                    }
                }
                difRem.removeAll(toAdd);
                difRem.removeAll(toUpdate);
                toRemove = difRem;
            }

            return new UpdateInfo<T>(toAdd, toRemove, toUpdate);
        }
    }

    public static <T extends UniqueIdProvider> UpdateInfo<T> getUpdateInfo(List<T> oldList, List<T> newList) {
        Difference<T> difference = compare(oldList, newList);
        return getUpdateInfo(difference);
    }

    public static <T> boolean notNullOrEmpty(List<T> list) {
        return list != null && !list.isEmpty();
    }
}
