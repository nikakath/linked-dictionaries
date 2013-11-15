package taojava.util;

import java.util.Iterator;

/**
 * Dictionaries implemented as linked structures.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class LinkedDictionary<K,V> implements Dictionary<K,V> {

    // +-------+-----------------------------------------------------------
    // | Notes |
    // +-------+
/*
    We implement dictionaries using unordered linked lists with a dummy
    node at the front.  New elements are added at the front because
    we predict that get is more frequently called on recent elements
    (or maybe just because we're lazy).

    To help with various operations, an internal find method finds
    a key and returns the node immediately preceding the key.
    Since we predict that clients will do successive calls to
    containsKey and get (or perhaps get and remove, or similar pairs),
    we cache the previous result.
 */

    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The front of the linked list.
     */
    LDNode<K,V> front;

    /**
     * A cached node from find.
     */
    LDNode<K,V> cached;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new linked dictionary.
     */
    public LinkedDictionary() {
         this.front = new LDNode<K,V>(null,null);
         this.cached = this.front;
    } // LinkedDictionary

    // +-----------+-------------------------------------------------------
    // | Observers |
    // +-----------+

    @Override
    public V get(K key) throws Exception {
        LDNode<K,V> prev = find(key);
        return prev.next.value;
    } // get(K)

    @Override
    public boolean containsKey(K key) {
        try {
            // If find succeeds, the key is there.
            find(key);
            return true;
        } catch (Exception e) {
            // If find doesn't succed, the key is not there.
            return false;
        } // try/catch
    } // containsKey(K)

    // +----------+--------------------------------------------------------
    // | Mutators |
    // +----------+

    @Override
    public void set(K key, V value) {
        this.front.next = new LDNode<K,V>(key,value,this.front.next);
    } // set(K,V)

    @Override
    public V remove(K key) throws Exception {
        // The following throws an exception if it's not there, which is fine
        LDNode<K,V> prev = find(key);
        // The following lines should be safe because find only succeeds 
        // if the next node exists.
        // Remember the value
        V val = prev.next.value;
        // Skip over the node.  Yay garbage collection!
        prev.next = prev.next.next;
        // And we're done.
        return val;
    } // remove(K)

    @Override
    public void clear() {
        // I love garbage collection.  In C, we'd have to individually
        // free all of the nodes.
        front.next = null;
    } // clear

    // +-----------+-------------------------------------------------------
    // | Iterators |
    // +-----------+

    @Override
    public Iterator<K> keys() {
        return new Iterator<K>() {
            @Override
            public K next() {
                // STUB
                return null;
            } // next()

            @Override
            public boolean hasNext() {
                // STUB
                return false;
            } // hasNext

            @Override
            public void remove() throws UnsupportedOperationException,
                    IllegalStateException {
                throw new UnsupportedOperationException();
            } // remove
        }; // new Iterator<K>
    } // keys()

    @Override
    public Iterator<V> values() {
        return new Iterator<V>() {
            @Override
            public V next() {
                // STUB
                return null;
            } // next()

            @Override
            public boolean hasNext() {
                // STUB
                return false;
            } // hasNext

            @Override
            public void remove() throws UnsupportedOperationException,
                    IllegalStateException {
                throw new UnsupportedOperationException();
            } // remove
        }; // new Iterator<V>
    } // keys()

    // +-----------------+-------------------------------------------------
    // | Local Utilities |
    // +-----------------+

    /**
     * Find the node with a specified key.
     *
     * @return the node immediately before the found node
     * @throws Exception if no node has the given key.
     */
    public LDNode<K,V> find(K key) throws Exception {
        // Special case: The cached node does the job.
        if ((this.cached.next != null) && 
                (key.equals(this.cached.next.key))) {
            return this.cached;
        } // The cached node does the job
        
        LDNode<K,V> prev = this.front;
        while (prev.next != null) {
            if (key.equals(prev.next.key)) {
                this.cached = prev;
                return prev;
            } // if
        } // if

        // If we've gotten through the while loop, no elements
        // remain, and so it's not there.
        throw new Exception("No element with key '" +  key + "'");
    } // find
} // Dictionary<K,V>

/**
 * Nodes in a linked dictionary.
 */
class LDNode<K,V> {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The key in the key/value pair.
     */
    K key;

    /**
     * The value in the key/value pair.
     */
    V value;

    /**
     * The next node.
     */
    LDNode<K,V> next;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new key/value pair with no successor.
     */
    public LDNode(K key, V value) {
        this(key, value, null);
    } // LDNode(K,V)

    /**
     * Create a new key/value pair with a designated successor.
     */
    public LDNode(K key, V value, LDNode<K,V> next) {
        this.key = key;
        this.value = value;
        this.next = null;
    } // LDNode(K,V,LDNode<K,V>)

} // LDNode<K,V>    
