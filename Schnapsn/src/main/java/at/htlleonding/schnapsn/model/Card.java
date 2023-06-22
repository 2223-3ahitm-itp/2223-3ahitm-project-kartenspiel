package at.htlleonding.schnapsn.model;

public class Card {
    private String name;
    private CardType type;
    private Integer value;

    public Card(String name, CardType type, Integer value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public CardType getType() {
        return type;
    }
    public Integer getValue() {
        return value;
    }
}
