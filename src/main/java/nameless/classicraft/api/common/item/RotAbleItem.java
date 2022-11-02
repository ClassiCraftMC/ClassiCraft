package nameless.classicraft.api.common.item;

/**
 * @author DustW
 */
public interface RotAbleItem {
    int getMax();

    /** 天（秒） */
    static int d(float t) {
        return (int) (t * 24000 / 20);
    }

    static int h(float t) {
        return d(t) / 24;
    }
}
