package ch.timofey.recap.security;

public enum AuthorizationSchemas {
    BASIC("Basic"), BEARER("Bearer"), DIGEST("Digest");

    private final String text;

    AuthorizationSchemas(final String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return text;
    }
}
