package palight.serverCosmetics.cosmetics.companion;

import java.util.*;

public class CompanionManager {
    private Map<UUID, Companion> companions = new HashMap();

    public void addCompanion(UUID uuid, Companion companion) {
        companions.put(uuid, companion);
    }

    public Map<UUID, Companion> getCompanions() {
        return companions;
    }

    public void removeCompanion(UUID uuid) { companions.remove(uuid); }
}
