package de.Palkuchen.survival.player;

public enum Group {
    ADMIN("§cAdmin"), TRUSTED("§3Trusted"), VISITOR("§7Visitor");

    private String prefix;

    Group(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
