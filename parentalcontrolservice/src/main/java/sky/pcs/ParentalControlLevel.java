package sky.pcs;

import java.util.*;
import java.util.function.Predicate;

public enum ParentalControlLevel {

    U("U", 0), PG("PG", 1), TWELVE("12", 2), FIFTEEN("15", 3), EIGHTEEN("18", 4);

    private static final Map<String, Integer> PC_LEVEL_MAP = new HashMap<>();

    static {
        for (ParentalControlLevel s : EnumSet.allOf(ParentalControlLevel.class)) {
            PC_LEVEL_MAP.put(s.id, s.val);
        }
    }

    private final String id;
    private final int val;

    ParentalControlLevel(String id, int val) {
        this.id = id;
        this.val = val;
    }

    public static Optional<ParentalControlLevel> of(String id) {
        final Predicate<ParentalControlLevel> parentalControlLevelPredicate = x -> x.id.equals(id);
        return Arrays.stream(ParentalControlLevel.values()).filter(parentalControlLevelPredicate).findFirst();
    }

    public String getId() {
        return id;
    }

    public int getVal() {
        return val;
    }

    public boolean isHigherThanOrEqualTo(ParentalControlLevel level) {
        return val >= level.getVal();
    }
}
