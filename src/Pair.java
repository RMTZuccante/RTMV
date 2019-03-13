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
    public int hashCode() {
        return key.hashCode() + value.hashCode();
    }
}
