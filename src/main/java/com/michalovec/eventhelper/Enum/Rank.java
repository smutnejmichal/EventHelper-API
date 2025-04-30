package com.michalovec.eventhelper.Enum;

import org.bukkit.ChatColor;

public enum Rank {

    ADMIN(ChatColor.LIGHT_PURPLE + "ᴀᴅᴍɪɴ", new String[]{}),
    GUEST(ChatColor.GRAY + "ʜʀᴀᴄ", new String[]{});


    private final String display;
    private final String[] permissions;

    Rank(String display, String[] permissions){
        this.display = display;
        this.permissions = permissions;
    }

    public String getDisplay() { return display; }

    public String[] getPermissions() { return permissions; }




}
