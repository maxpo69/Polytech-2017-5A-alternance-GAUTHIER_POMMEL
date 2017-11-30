package com.example.epulapp.beersapplication.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i, "Bière"+id, "Description de la bière", "https://images.punkapi.com/v2/192.png", 6));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int id, String name, String description, String image_url, float abv ) {
        return new DummyItem(String.valueOf(id), name, description, image_url, String.valueOf(abv));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String name;
        public String description;
        public String image_url;
        public String abv;

        public DummyItem(String id, String name, String description, String image_url, String abv) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.image_url = image_url;
            this.abv = abv;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
