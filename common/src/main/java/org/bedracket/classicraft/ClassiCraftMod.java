package org.bedracket.classicraft;

import org.bedracket.classicraft.init.ModItems;

public class ClassiCraftMod {

    public static final String MOD_ID = "classicraft";

    public static void init() {
        ModItems.ITEMS.register();
    }
}
