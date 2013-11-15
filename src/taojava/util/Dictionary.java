package taojava.util;

import java.util.Iterator;

/**
 * Simple dictionaries (aka maps, tables, hashes, associative arrays).
 * We've taken a relatively minimalist approach in this design.
 *
 * @author Samuel A. Rebelsky
 * @author CSC 207 2013F
 */
public interface Dictionary<K,V> {

    // +-----------+-------------------------------------------------------
    // | Observers |
    // +-----------+

    /**
     * Get the value associated with a particular key.
     *
     * @throws Exception if the key is not in the dictionary.
     */
    public V get(K key) throws Exception;

    /**
     * Determine if the dictionary contains a value with a particular
     * key.  (Useful in checking the precondition for get.)
     */
    public boolean containsKey(K key);

    // +----------+--------------------------------------------------------
    // | Mutators |
    // +----------+

    /**
     * Set the value associated with a particular key.  If there was
     * previously a value with that key, replaces it.
     */
    public void set(K key, V value);

    /**
     * Remove the value associated with a particular key.  Future calls
     * to get with the same key should throw exceptions (at least until
     * the next call to set with the same key).
     *
     * @return the value associated with the key
     * @throws Exception if the key is not in the dictionary
     */ 
    public V remove(K key) throws Exception;

    /**
     * Remove all of the items from the dictionary.
     */
    public void clear();

    // +-----------+-------------------------------------------------------
    // | Iterators |
    // +-----------+

    /**
     * Get an iterator for the keys in the dictionary.  The keys are
     * iterated in no particular order.
     */
    public Iterator<K> keys();

    /**
     * Get an iterator for the values in the dictionary.  The values are
     * iterated in no particular order.  If a value appears more than once
     * in the dictionary (i.e., if it is associated with more than one key),
     * it appears more than once in the iteration.
     */
    public Iterator<V> values();
} // Dictionary<K,V>
