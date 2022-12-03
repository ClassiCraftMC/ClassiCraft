package nameless.classicraft.api.san;

public interface ISanHandler {

    void setSan(float san);
    void setMaxSan(float maxSan);

    void regenSan(float san);

    float getSan();
    float getMaxSan();

    void reduceSan(float san);

}
