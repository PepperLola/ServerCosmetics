package palight.serverCosmetics.cosmetics.companion;

import java.util.ArrayList;
import java.util.List;

public class CompanionManager {
    private List<Companion> companions = new ArrayList<>();

    public void addCompanion(Companion companion) {
        companions.add(companion);
    }

    public List<Companion> getCompanions() {
        return companions;
    }
}
