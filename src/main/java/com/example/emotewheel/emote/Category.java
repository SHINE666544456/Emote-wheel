package com.example.emotewheel.emote;

public enum Category {
    GREET("Greetings"),
    POSE("Poses"),
    DANCE("Dances"),
    ACTION("Actions"),
    MOOD("Moods"),
    SILLY("Silly"),
    MAID("Maid & Cat");

    public final String label;
    Category(String label) { this.label = label; }
}
