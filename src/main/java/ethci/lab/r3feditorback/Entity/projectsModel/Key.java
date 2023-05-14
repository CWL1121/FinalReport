package ethci.lab.r3feditorback.Entity.projectsModel;

public class Key {
    public String openAiKey;
    public String ttsKey;

    public Key() {
    }

    public Key(String openAiKey, String ttsKey) {
        this.openAiKey = openAiKey;
        this.ttsKey = ttsKey;
    }

    @Override
    public String toString() {
        return "Key{" +
                "openAiKey='" + openAiKey + '\'' +
                ", ttsKey='" + ttsKey + '\'' +
                '}';
    }
}
