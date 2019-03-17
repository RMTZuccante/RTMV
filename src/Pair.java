public class Pair<K, V> {
    public K key;
    public V value;

    Pair() {
    }

    Pair(K k, V v) {
        key = k;
        value = v;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass() && ((Pair) o).key.equals(key) && ((Pair) o).value.equals(value);
    }

    @Override
    public int hashCode() {
        return key.hashCode() + value.hashCode();
    }
}
