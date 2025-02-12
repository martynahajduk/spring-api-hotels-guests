package be.kdg.programming5.domain;

public enum Nationality {
    ALBANIAN,
    BELGIAN,
    BOSNIAN,
    BULGARIAN,
    CROATIAN,
    CZECH,
    DANISH,
    DUTCH,
    ENGLISH,
    FINNISH,
    FRENCH,
    GERMAN,
    GREEK,
    HUNGARIAN,
    ITALIAN,
    POLISH,
    PORTUGUESE,
    ROMANIAN,
    SPANISH,
    SWEDISH,
    MOLDOVAN,
    AMERICAN,
    BRAZILIAN,
    CANADIAN,
    CHINESE,
    INDIAN,
    JAPANESE,
    MEXICAN,
    RUSSIAN,
    SOUTH_AFRICAN,
    AUSTRALIAN;

    @Override
    public String toString() {
        String name = name();
        StringBuilder stringBuilder = new StringBuilder();
        boolean toUpperCase = true;
        for (char c : name.toCharArray()) {
            if (c == '_') {
                stringBuilder.append(' ');
                toUpperCase = true;
            } else {
                stringBuilder.append(toUpperCase ? Character.toUpperCase(c) : Character.toLowerCase(c));
                toUpperCase = false;
            }
        }
        return stringBuilder.toString();
    }
}

