package nameless.classicraft.api.common.item;

public interface DampAbleItem {
    int getMax();

    // convert to day
    static int d(float t) {
        return (int) (t * 24000 / 20);
    }

    //convert to hours
    static int h(float t) {
        return d(t) / 24;
    }
}
