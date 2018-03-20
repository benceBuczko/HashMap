import java.util.LinkedList;

import static java.lang.Math.abs;

public class HashMap<K, V> {

    private int bucketSize = 16;

    // This holds all the data. Its a primitive array where every element is a Linked List.
    // They Linked List holds elements of type KeyValue
    private LinkedList<KeyValue>[] elements = new LinkedList[bucketSize];

    public void add(K key, V value) {
        // find out which position of the primitive array to use:
        int position = getHash(key);
        LinkedList<KeyValue> list = elements[position];

        // If the key already exists throw an error.

        if(list != null){
            for(KeyValue keyValue : list){
                if(keyValue.key == key){
                    throw new IllegalArgumentException("Already in the HashMap!");
                }
            }
            list.add(new KeyValue(key,value));
        }

        // Make a new instance of the KeyValue class, fill it with the key, value parameters, then add it to the list.
        list = new LinkedList<KeyValue>();
        list.add(new KeyValue(key,value));
        elements[position] = list;

        resizeIfNeeded();
    }

    public V getValue(K key) {
        // 1. Calculate the hash of the key. This defines which element to get from the "elements" array
        // 2. Find in the List in this position the KeyValue element that has this key, then return its value.
        //    If none of the items in the list has this key throw error.
        int position = getHash(key);
        LinkedList<KeyValue> list = elements[position];

        if(list != null){
            for(KeyValue keyValue : list){
                if(keyValue.key == key){
                    return (V) keyValue.value;
                }
            }

        }
        throw new IllegalArgumentException("Not in HashMap!");
    }

    private int getHash(K key) {
        // This function converts somehow the key to an integer between 0 and bucketSize
        // In C# GetHashCode(), in Java hashCode() is a function of Object, so all non-primitive types
        // can easily be converted to an integer.
        return abs(key.hashCode()) % bucketSize;
    }

    public void remove(K key){
        int position = getHash(key);
        LinkedList<KeyValue> list = elements[position];
        KeyValue toRemove = null;

        if(list != null){
            for(KeyValue keyValue : list){
                if(keyValue.key == key){
                    toRemove = keyValue;
                }
            }
            list.remove(toRemove);
        }
    }

    public void clearAll(){
        for(LinkedList list : elements){
            if(list != null) list.clear();
        }
    }

    private void resizeIfNeeded(){
        // If it holds more elements than bucketSize * 2, destroy and recreate it
        // with the double size of the elements array.
        // if it holds less elements than bucketSize / 2, destroy and recreate it
        // with half size of the elements array.
    }
}



