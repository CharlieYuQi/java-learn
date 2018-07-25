package tk.yuqi.tools.tools.copy;

/**
 */
public interface Copier<S, D> {

    void copy(S source, D dest);
}
