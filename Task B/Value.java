public class Value {
    private String literal;
    private boolean value;

    public Value(String literal, boolean value) {
        this.literal = literal;
        this.value = value;
    }

    public String getLiteral() {
        return literal;
    }

    public boolean isValue() {
        return value;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

}
