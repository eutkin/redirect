package net.eutkin.redirect.util;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
public class Pair<K1, K2> {

    private final K1 _1;
    private final K2 _2;

    private Pair(K1 k1, K2 k2) {
        _1 = k1;
        _2 = k2;
    }

    public static <K1, K2> Pair<K1, K2> pair(K1 _1, K2 _2) {
        return new Pair<>(_1, _2);
    }

    public K1 _1() {
        return _1;
    }

    public K2 _2() {
        return _2;
    }
}
